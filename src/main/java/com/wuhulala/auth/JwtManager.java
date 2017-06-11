package com.wuhulala.auth;

import com.wuhulala.cache.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * author： wuhulala
 * date： 2017/6/1
 * version: 1.0
 * description: jwt验证器
 */
@Component
@ConfigurationProperties(prefix = "jwt.token")
public class JwtManager {
    private  String prefix;

    private long expire;

    @Autowired
    private CacheService<String,String> cacheService;


    public void addJwt(String key, String jwtBody) {
        String realKey = getRealKey(key);
        cacheService.setValue(realKey, jwtBody, expire);
    }

    public String getJwt(String key) {
        String realKey = getRealKey(key);
        return  cacheService.getValue(realKey);
    }

    public boolean delJwt(String key) {
        if (getJwt(key) != null) {
            String realKey = getRealKey(key);
            cacheService.delValue(realKey);
            return true;
        }
        return false;
    }

    private String getRealKey(String key) {
        return prefix.concat(String.valueOf(key));
    }

    public void refreshJwt(String key) {
        addJwt(key, getJwt(key));
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }
}
