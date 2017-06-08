package com.wuhulalaframework;

import com.wuhulala.TntClass;
import com.wuhulala.auth.JwtManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @Configuration 注解加上回默认注册为一个Bean
 *
 * author： wuhulala
 * date： 2017/6/8
 * version: 1.0
 * description: 作甚的
 */
@Configuration
@ConditionalOnMissingClass("TntClass")
public class WuhulalaTestCondition {

    private JwtManager jwtManager;

  /*  public WuhulalaTestCondition(){
        System.out.println("------------WuhulalaTestCondition--------------");
    }*/

    public WuhulalaTestCondition(JwtManager jwtManager){
        System.out.println("------------WuhulalaTestCondition--------------" + jwtManager);
    }


    public WuhulalaTestCondition(){
        System.out.println("------------WuhulalaTestCondition2222222--------------" + jwtManager);
    }

    //如果ApplicationContext中不存在TNTClass的Bean，则创建一个TNT的Bean
    @Bean
    @ConditionalOnMissingBean
    public TntClass tntClass(){
        System.out.println("我没有TNT Class、、、");
        return new TntClass(new JwtManager(), "Red TNT");
    }

    @Bean
    @ConditionalOnProperty(
            prefix = "spring.mvc.formcontent.putfilter",
            name = {"enabled"},
            matchIfMissing = true
    )
    public List testConditionalOnProperty(){
        System.out.println("OrderedHttpPutFormContentFilter 关闭了");
        return new ArrayList();
    }
}
