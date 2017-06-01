package com.wuhulala.util;

import com.alibaba.fastjson.JSON;

/**
 * 令牌基类
 *
 * @author Wuhulala
 * @version 1.0
 * @updateTime 2017/1/29
 */
public class Token {
    private Long userId;
    private Long expireTime;
    private String ipAddress;
    private String url;

    public Token() {
    }

    public Token(Long data, Long expireTime, String ipAddress, String url) {
        this.userId = data;
        this.expireTime = expireTime;
        this.ipAddress = ipAddress;
        this.url = url;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public String generateBytes() {
        return userId+"&&"+expireTime+"&&"+ipAddress+"&&"+url;
    }
}
