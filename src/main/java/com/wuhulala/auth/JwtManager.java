package com.wuhulala.auth;

import com.wuhulala.util.TokenUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * author： wuhulala
 * date： 2017/6/1
 * version: 1.0
 * description: jwt验证器
 */
@Component
public class JwtManager {
    private static final String JWT_PREFIX = "jwt:";


    @Resource(name = "redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    @Resource(name = "valueOperations")
    private ValueOperations<String, Object> valueOperations;


    public void addJwt(String key, String jwtBody) {
        String realKey = getRealKey(key);
        Date date = new Date();

        valueOperations.set(realKey, jwtBody, TokenUtils.EXP_TIMES, TimeUnit.SECONDS);
    }

    public String getJwt(String key) {
        String realKey = getRealKey(key);
        return (String) valueOperations.get(realKey);
    }

    public boolean delJwt(String key) {
        if (getJwt(key) != null) {
            String realKey = getRealKey(key);
            redisTemplate.delete(realKey);
            return true;
        }
        return false;
    }

    private String getRealKey(String key) {
        return JWT_PREFIX.concat(String.valueOf(key));
    }

    public void refreshJwt(String key) {
        addJwt(key,getJwt(key));
    }
}
