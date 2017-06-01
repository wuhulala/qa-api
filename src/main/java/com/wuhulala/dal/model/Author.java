package com.wuhulala.dal.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @author xueaohui
 * @version 1.0
 * @date 2017/3/12
 * @link https://github.com/wuhulala
 */
public class Author {
    private Long id;
    @JSONField(serialize = false)
    private Account account;
    private String nickName; //昵称
    private String avatar;  //头像
    private Date gmtCreate;
    private Date gmtModify;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }
}
