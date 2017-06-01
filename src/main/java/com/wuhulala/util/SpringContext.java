package com.wuhulala.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * author： wuhulala
 * date： 2017/6/1
 * version: 1.0
 * description: 作甚的
 */
@Component
public class SpringContext extends ApplicationObjectSupport {

    private static ApplicationContext instance;

    public static ApplicationContext getContext() {
        return instance;
    }

    @PostConstruct
    private void init() {
        instance = getApplicationContext();
    }

}
