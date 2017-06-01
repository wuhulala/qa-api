package com.wuhulala.dal.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @author Wuhulala
 * @version 1.0
 * @date 2016/12/25
 */
public class Account {
    private Long id;
    private String name;
    private String token;
    @JSONField(serialize = false)
    private String password;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastLogin;

    public Account() {
    }


    public Account(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }


    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
