package com.wuhulala.controller;


import com.wuhulala.ApiConstants;
import com.wuhulala.dal.model.Account;
import com.wuhulala.service.AccountService;
import com.wuhulala.util.BaseResult;
import com.wuhulala.util.ReturnCode;
import com.wuhulala.util.ValidParams;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/*
 * @author Wuhulala
 * @version 1.0
 * @updateTime 2016/12/25
 */

@RestController
@RequestMapping("/api")
@CrossOrigin(ApiConstants.CROSS_ADDRESS)
public class AccountController extends BaseController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    /**
     * 登录
     *
     * @param account 账户信息
     * @return BaseResult
     */
    @PostMapping(value = "/session")
    public BaseResult<Account> login(@RequestBody Account account, HttpServletRequest request) {
        Account result = accountService.login(account, request);
        return result == null ? fillErrorBaseResult(ReturnCode.LOGIN_ERROR) : fillSuccessBaseResult(result);
    }

    /*
     * 注册
     * @param name
     * @param password
     * @return
     */

    @PostMapping(value = "/account")
    public BaseResult<?> register(String name, String password) {
        BaseResult<?> result = new BaseResult<>();
        int tmp = accountService.register(name, password);
        if (tmp == 1) result.setReturnCode(ReturnCode.REGISTER_NAME_IS_EXIST);
        if (tmp == 2) result.setReturnCode(ReturnCode.REGISTER_ERROR);
        if (tmp == 3) result.setReturnCode(ReturnCode.REGISTER_SUCCESS);
        return result;
    }

    /*
     * 修改密码
     */
    @PutMapping(value = "account/{id}/pass")
    public BaseResult editPass(@PathVariable("id") Long id,
                                  @RequestBody PasswordParam param) {
        String validResult = param.validParams();
        if (StringUtils.EMPTY.equals(validResult)) {
            int tmp = accountService.editPassword(id, param.getPassword(), param.getNew_password());
            if (tmp == 0) return fillErrorBaseResult(ReturnCode.EDIT_PASS_ERROR);
            else if (tmp == 1) return fillErrorBaseResult(ReturnCode.EDIT_PASS_PASSWORD_IS_ERROR);
            else if (tmp == 2) return fillSuccessBaseResult(validResult);
            else return fillErrorBaseResult(ReturnCode.ERROR);
        } else {
            return fillBaseResult(validResult, ReturnCode.ERROR_USER);
        }
    }

    /*
     * 退出登录
     */
    @DeleteMapping(value = "session")
    public BaseResult<?> logout(@RequestBody Long id) {
        BaseResult<?> result = new BaseResult<>();
        int tmp = accountService.deleteSession(id);
        if (tmp == 0) result.setReturnCode(ReturnCode.LOGOUT_ERROR);
        if (tmp == 1) result.setReturnCode(ReturnCode.SUCCESS);
        return result;
    }


}

class PasswordParam implements ValidParams<String> {
    private String password;
    private String new_password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    @Override
    public String validParams() {
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isEmpty(password)) {
            sb.append("旧密码为空");
        }
        if (StringUtils.isEmpty(password)) {
            sb.append("新密码为空");
        }
        return sb.toString();
    }
}
