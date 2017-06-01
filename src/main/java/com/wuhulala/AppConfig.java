package com.wuhulala;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.wuhulala.interceptor.ExecuteTimeHandlerInterceptor;

/**
 * @author Wuhulala
 * @version 1.0
 * @updateTime 2016/11/25
 */
@Configuration
public class AppConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private Environment env;

    @Bean
    public ExecuteTimeHandlerInterceptor executeTimeHandlerInterceptor(){
        return new ExecuteTimeHandlerInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(executeTimeHandlerInterceptor());
    }

    @Bean
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1024*1024*50);
        multipartResolver.setDefaultEncoding("UTF-8");
        return multipartResolver;
    }

}
