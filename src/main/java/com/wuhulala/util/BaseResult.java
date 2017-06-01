package com.wuhulala.util;

import com.alibaba.fastjson.annotation.JSONField;
import com.sun.org.apache.regexp.internal.RE;

import java.io.Serializable;


public class BaseResult<T> implements Serializable {

	@JSONField(serialize = false)
	private ReturnCode returnCode = ReturnCode.SUCCESS;

	private Meta meta;
	private T data;


	public ReturnCode getReturnCode() {
		return returnCode;
	}

	public BaseResult<?> setReturnCode(ReturnCode returnCode) {
		this.returnCode = returnCode;
		return this;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Meta getMeta() {
		this.meta = new Meta(returnCode);
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}
}
