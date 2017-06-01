package com.wuhulala.dal.model;

import java.util.Date;

/**
 * 类别
 *
 * @author xueaohui
 * @version 1.0
 * @date 2017/3/12
 * @link https://github.com/wuhulala
 */
public class Kind {
    private Integer id;
    private String name;
    private String description;
    private Date gmtCreate;
    private Date gmtModify;

    public Kind() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
