package com.wuhulalaframework;

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
// @ConditionalOnMissingClass({"TntClass22"}) 没啥鸟用
public class WuhulalaStarter {

    private JwtManager jwtManager;

  /*  public WuhulalaTestCondition(){
        System.out.println("------------WuhulalaTestCondition--------------");
    }*/

    public WuhulalaStarter(JwtManager jwtManager){
        System.out.println("------------WuhulalaTestCondition--------------" + jwtManager);
    }


    public WuhulalaStarter(){
        System.out.println("------------WuhulalaTestCondition2222222--------------" + jwtManager);
    }

    //如果ApplicationContext中不存在TNTClass的Bean，则创建一个TNT的Bean
    @Bean
    @ConditionalOnMissingBean
    public TntClass tntClass(){
        System.out.println("===========我没有TNT Bean、、、===========");
        return new TntClass(new JwtManager(), "Red TNT");
    }

    @Bean
    @ConditionalOnMissingClass({"com.wuhulalaframework.TntClass"}) //不会进入 必须全路径
    public List testConditionalOnMissingClass(){
        System.out.println("============================com.wuhulalaframework.TntClass 竟然没有。。。=====================");
        return new ArrayList();
    }

    @Bean
    @ConditionalOnMissingClass({"com.wuhulala.TntClass2"}) //进入
    public List testConditionalOnMissingClass2(){
        System.out.println("============================com.wuhulala.TntClass2 竟然没有。。。=====================");
        return new ArrayList();
    }

    @Bean
    @ConditionalOnMissingClass({"com.wuhulala.TntClass2","TTTTT"}) //逻辑为 and
    public List testConditionalOnMissingClass3(){
        System.out.println("============================com.wuhulala.TntClass2 and TTTT 竟然都没有。。。=====================");
        return new ArrayList();
    }

    @Bean
    @ConditionalOnProperty(
            prefix = "spring.mvc.formcontent.putfilter",
            name = {"enabled"},
            matchIfMissing = true
    )
    public List testConditionalOnProperty(){
        System.out.println("===============================【OrderedHttpPutFormContentFilter 关闭了】");
        return new ArrayList();
    }
}
