package com.wuhulala.auth;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import java.io.IOException;

/**
 * author： wuhulala
 * date： 2017/6/7
 * version: 1.0
 * description: 作甚的
 */
public class TestFilter implements Filter{

    @Autowired
    JwtManager jwtManager;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("TestFilter--------init----------------------" + jwtManager);

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("TestFilter-----------doFilter-------------------" + jwtManager);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("TestFilter------------------------------" + jwtManager);

    }
}
