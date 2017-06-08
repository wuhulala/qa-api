package com.wuhulala;

import com.wuhulala.auth.JwtManager;

/**
 * author： wuhulala
 * date： 2017/6/8
 * version: 1.0
 * description: 作甚的
 */
public class TntClass {

    private String name;

    //如果只设置一个构造函数，会自动中注入
    //如果设置多个构造函数。
    //1.使用@Bean的配置方法  根据@Bean设置的方法创建的方法匹配构造参数，即根据重载的方式设置的.
    //2.@Component方法 默认使用无参构造函数。

    private JwtManager jwtManager;

    public TntClass() {
        System.out.println("我有十吨TNT....");
    }

    public TntClass(JwtManager jwtManager){
        System.out.println("我缓存中有 十吨TNT...." + jwtManager);
    }

    public TntClass(JwtManager jwtManager,String name){
        System.out.println("My Name is "+ this.name +" , 我缓存中有 十吨TNT...." + jwtManager);
        this.name = name;
    }
}
