package com.wuhulala.util;


/**
 * @author zhougui735  
 * @date 2015年5月17日 下午3:57:03 
 */

public enum ReturnCode {

	SUCCESS(200,"200", "成功"),
	ERROR(500,"-1", "系统繁忙"),
	ERROR_500(500,"500","系统内部出错误"),
	ERROR_404(404,"404","资源未找到"),
	ERROR_SQL(554,"554","数据库内部错误"),
	ERROR_USER(555,"555","数据库内部错误"),

	//登录信息
	LOGIN_SUCCESS(200,"1001","登录成功"),
	LOGIN_ERROR(555,"1002","用户名或密码错误"),
	LOGIN_NAME_IS_NOT_EXIST(555,"1003","用户名不存在"),
	LOGIN_PASSWORD_IS_ERROR(555,"1004","密码错误"),

	//注册信息
	REGISTER_SUCCESS(200,"1101","注册成功"),
	REGISTER_ERROR(555,"1102","注册失败"),
	REGISTER_NAME_IS_EXIST(555,"1103","用户名已存在"),

	//修改密码
	EDIT_PASS_SUCCESS(555,"1201","修改密码成功"),
	EDIT_PASS_ERROR(555,"1202","修改密码错误"),
	EDIT_PASS_PASSWORD_IS_ERROR(555,"1203","原密码错误"),

	//退出
	LOGOUT_ERROR(555,"1301","退出登录错误");

	public static ReturnCode getReturnCode(String returnCode){
		for( ReturnCode e : ReturnCode.values() ){
			if( e.getMsgCode().equals(returnCode)){
				return e ;
			}
		}
		return null ;
	}
	
	ReturnCode(int code ,String resultCode, String resultMsg) {
        this.code = code;
        this.msgCode = resultCode;
        this.msg = resultMsg;
    }

    private int code;

    private String msgCode;

    private String msg;

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
