package com.wuhulala.controller;

import com.wuhulala.ApiConstants;
import com.wuhulala.util.BaseResult;
import com.wuhulala.util.ReturnCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * author： wuhulala
 * date： 2017/5/20
 * version: 1.0
 * description: 基础Controller类
 */
public class BaseController {

    @Value("${spring.profiles.active}")
    private String profileName;

    /**
     * 通用错误处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public BaseResult<String> handlerException(RuntimeException e) {
        BaseResult<String> result = new BaseResult<>();
        result.setReturnCode(ReturnCode.ERROR_500);
        if (!ApiConstants.PRODUCT_PROFILE_NAME.equals(profileName)) {
            //如果不是生产环境，把异常写出来
            result.setData(e.getMessage());
        }
        return result;
    }

    /**
     * 填充正确结果
     *
     * @param <T>
     * @return
     */
    public <T> BaseResult<T> fillSuccessBaseResult(T data) {
        return fillBaseResult(data, ReturnCode.SUCCESS);
    }

    /**
     * 填充错误信息结果
     *
     * @param returnCode
     * @param <T>
     * @return
     */
    public <T> BaseResult<T> fillErrorBaseResult(ReturnCode returnCode) {
        return fillBaseResult(null, returnCode);
    }


    /**
     * 填充信息
     *
     * @param data
     * @param returnCode
     * @param <T>
     * @return
     */
    public <T> BaseResult<T> fillBaseResult(T data, ReturnCode returnCode) {
        BaseResult<T> baseResult = new BaseResult<>();
        baseResult.setData(data);
        baseResult.setReturnCode(returnCode);
        return baseResult;
    }

}
