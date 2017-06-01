package com.wuhulala.util;

/**
 * author： wuhulala
 * date： 2017/5/31
 * version: 1.0
 * description: 作甚的
 */
public class Meta {
    private int code;

    private String msgCode;

    private String msg;

    public Meta(ReturnCode returnCode) {
        this.code = returnCode.getCode();
        this.msgCode = returnCode.getMsgCode();
        this.msg = returnCode.getMsg();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }
}
