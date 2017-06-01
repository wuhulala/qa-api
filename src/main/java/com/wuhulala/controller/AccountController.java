package com.wuhulala.controller;


import com.wuhulala.dal.model.Account;
import com.wuhulala.service.AccountService;
import com.wuhulala.util.BaseResult;
import com.wuhulala.util.ReturnCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/*
 * @author Wuhulala
 * @version 1.0
 * @updateTime 2016/12/25
 */

@RestController
@RequestMapping("/account")
@Api(value = "account", description = "账户接口")
public class AccountController extends BaseController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    /**
     * 登录
     *
     * @param account 账户信息
     * @return BaseResult
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "账户登录", notes = "账户登录")
    public BaseResult<Account> login(@RequestBody Account account, HttpServletRequest request) {
        return accountService.login(account, request) == null ? fillErrorBaseResult(ReturnCode.LOGIN_ERROR) : fillSuccessBaseResult(account);
    }

    /*
     * 注册
     * @param name
     * @param password
     * @return
     */

    @RequestMapping(value = "/register", method = RequestMethod.POST)
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
    @RequestMapping(value = "/{id}/pass", method = RequestMethod.PUT)
    public BaseResult<?> editPass(@PathVariable("id") Long id,
                                  @RequestParam("password") String password,
                                  @RequestParam("new_password") String newPassword) {
        BaseResult<?> result = new BaseResult<>();
        int tmp = accountService.editPassword(id, password, newPassword);

        if (tmp == 0) result.setReturnCode(ReturnCode.EDIT_PASS_ERROR);
        if (tmp == 1) result.setReturnCode(ReturnCode.EDIT_PASS_PASSWORD_IS_ERROR);
        if (tmp == 2) result.setReturnCode(ReturnCode.SUCCESS);

        return result;
    }

    /*
     * 退出登录
     */

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public BaseResult<?> logout(@PathVariable("id") Long id) {
        BaseResult<?> result = new BaseResult<>();
        int tmp = accountService.deleteSession(id);
        if (tmp == 0) result.setReturnCode(ReturnCode.LOGOUT_ERROR);
        if (tmp == 1) result.setReturnCode(ReturnCode.SUCCESS);

        return result;
    }
}
