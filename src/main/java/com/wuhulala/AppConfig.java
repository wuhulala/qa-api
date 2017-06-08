package com.wuhulala;

import com.wuhulala.auth.TestFilter;
import com.wuhulala.interceptor.ExecuteTimeHandlerInterceptor;
import com.wuhulala.interceptor.JwtInterceptor;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.Filter;
import java.util.HashSet;
import java.util.Set;

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

    @Bean
    public JwtInterceptor jwtInterceptor(){
        JwtInterceptor result = new JwtInterceptor();
        String urls = env.getProperty("jwt.excluded.urls");
        Set<String> excludedUrls = new HashSet<>();
        CollectionUtils.addAll(excludedUrls , urls.split(","));
        result.setExcludedUrls(excludedUrls);
        return result;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(executeTimeHandlerInterceptor());
        registry.addInterceptor(jwtInterceptor());
    }

    @Bean
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1024*1024*50);
        multipartResolver.setDefaultEncoding("UTF-8");
        return multipartResolver;
    }



    @Bean
    public FilterRegistrationBean demoFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(testFilter());
        registration.addUrlPatterns("/api/*");
        registration.setName("testFilter1");

        return registration;
    }



    /*@Bean
    public DelegatingFilterProxy testProxy(){
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName("testFilter");
        return proxy;
    }*/

    @Bean
    public Filter testFilter(){
        System.out.println("-------------configuration testFilter---------------");

        return new TestFilter();
    }

}
