package com.wuhulala.interceptor;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wuhulala.auth.JwtManager;
import com.wuhulala.util.BaseResult;
import com.wuhulala.util.ReturnCode;
import com.wuhulala.util.TokenUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * author： wuhulala
 * date： 2017/6/1
 * version: 1.0
 * description: 作甚的
 */
public class JwtInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(JwtInterceptor.class);

    @Autowired
    private JwtManager manager;

    private Set<String> excludedUrls = new HashSet<>();


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
                writeErrorMsg(response, ReturnCode.ERROR_401);
                return false;
            }

            final String token = authHeader.substring(7);

            try {
                final Claims claims = TokenUtils.parseToken(token);
                String key = claims.getSubject();
                String jwtValue = manager.getJwt(key);
                if (jwtValue == null) {
                    throw new SignatureException("Invalid token");
                }
                if (!jwtValue.equals(token)) {
                    throw new SignatureException("Invalid token");
                }
                manager.refreshJwt(key);
                request.setAttribute("claims", claims);
            } catch (final SignatureException e) {
                logger.error("Invalid token");
                writeErrorMsg(response, ReturnCode.ERROR_401);
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
        if (CollectionUtils.isNotEmpty(excludedUrls)) {
            for (String configUrl : excludedUrls) {
                if (StringUtils.isNotBlank(targetUrl)
                        && StringUtils.isNotBlank(configUrl)
                        && targetUrl.endsWith(configUrl)) {
                    return true;
                }
            }
        }
        return false;
    }


    private void writeErrorMsg(HttpServletResponse response, ReturnCode returnCode) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ObjectMapper mapper = new ObjectMapper();
        BaseResult baseResult = new BaseResult();
        baseResult.setReturnCode(returnCode);
        response.getWriter().write(JSON.toJSONString(baseResult));
    }

    public void setExcludedUrls(Set<String> excludedUrls) {
        this.excludedUrls = excludedUrls;
    }
}