package com.wuhulala.filter;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wuhulala.auth.JwtManager;
import com.wuhulala.util.BaseResult;
import com.wuhulala.util.ReturnCode;
import com.wuhulala.util.SpringContext;
import com.wuhulala.util.TokenUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * author： wuhulala
 * date： 2017/6/1
 * version: 1.0
 * description: 作甚的
 */

public class JwtFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(JwtFilter.class);
    private JwtManager manager;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {


        logger.debug("初始化 JwtFilter。。。。。。。。。。。。。。");
    }


    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
            throws IOException, ServletException {
        ApplicationContext context = SpringContext.getContext();
        manager = context.getBean(JwtManager.class);

        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        final String authHeader = request.getHeader("authorization");

        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);

            chain.doFilter(req, res);
        } else {

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                logger.error("Missing or invalid Authorization header");
                writeErrorMsg(response, ReturnCode.ERROR_401);
                return;
            }

            final String token = authHeader.substring(7);

            try {
                final Claims claims = TokenUtils.parseToken(token);
                String key = claims.getSubject();
                String jwtValue = manager.getJwt(key);
                if (jwtValue == null) {
                    throw new SignatureException("Invalid token");
                }
                if (!jwtValue.equals(token)){
                    throw new SignatureException("Invalid token");
                }
                manager.refreshJwt(key);
                request.setAttribute("claims", claims);
            } catch (final SignatureException e) {
                logger.error("Invalid token");
                writeErrorMsg(response, ReturnCode.ERROR_401);
                return;
            }

            chain.doFilter(req, res);
        }
    }

    @Override
    public void destroy() {

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
}