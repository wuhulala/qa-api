package com.wuhulala.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * author： wuhulala
 * date： 2017/6/1
 * version: 1.0
 * description: 作甚的
 */
@Component
public class SpringContext implements ApplicationContextAware {

    private static ApplicationContext instance;

    public static ApplicationContext getContext() {
        return instance;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        instance = applicationContext;
    }
}
