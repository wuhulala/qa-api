package com.wuhulala.model;


import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * @author Wuhulala
 * @version 1.0
 * @updateTime 2016/11/18
 */
public class User implements Serializable{
    private int id;
    private String name;
    private String pass;
    @JSONField(serialize = false)
    private String token;

    public User(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    public User(int id, String name, String pass) {
        this.id = id ;
        this.name = name;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
