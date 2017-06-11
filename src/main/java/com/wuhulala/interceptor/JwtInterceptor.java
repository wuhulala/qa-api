package com.wuhulala.interceptor;

import com.wuhulala.auth.JwtManager;
import com.wuhulala.exception.NotLoginException;
import com.wuhulala.util.FilterUtils;
import com.wuhulala.util.ReturnCode;
import com.wuhulala.util.TokenUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * author： wuhulala
 * date： 2017/6/1
 * version: 1.0
 * description: 作甚的
 */
@ConfigurationProperties(prefix = "jwt.excluded")
public class JwtInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(JwtInterceptor.class);

    @Autowired
    private JwtManager manager;

    private List<String> urls = new ArrayList<>();


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        String url = request.getRequestURI();

        if (this.isMatch(url)) {
            return true;
        } else if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);

            return true;
        } else {

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                logger.error("Missing or invalid Authorization header");
                FilterUtils.writeErrorMsg(response, ReturnCode.ERROR_401);
                return false;
            }

            final String token = authHeader.substring(7);

            try {
                final Claims claims = TokenUtils.parseToken(token);
                String key = claims.getSubject();
                String jwtValue = manager.getJwt(key);
                if (jwtValue == null) {
                    throw new NotLoginException("Not Login");
                }
                if (!jwtValue.equals(token)) {
                    throw new SignatureException("Invalid token");
                }
                manager.refreshJwt(key);
                request.setAttribute("claims", claims);
            }catch (final NotLoginException e){
                logger.error(e.getMessage());
                FilterUtils.writeErrorMsg(response, ReturnCode.NOT_LOGIN_ERROR);
                return false;
            }
            catch (final SignatureException e) {
                logger.error("Invalid token");
                FilterUtils.writeErrorMsg(response, ReturnCode.ERROR_401);
                return false;
            }

            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    /**
     * 判断是否与目标URL匹配。
     *
     * @param targetUrl
     * @return
     */
    private boolean isMatch(String targetUrl) {
        if (CollectionUtils.isNotEmpty(urls)) {
            for (String configUrl : urls) {
                if (StringUtils.isNotBlank(targetUrl)
                        && StringUtils.isNotBlank(configUrl)
                        && targetUrl.endsWith(configUrl)) {
                    return true;
                }
            }
        }
        return false;
    }




    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}