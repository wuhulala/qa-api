package com.wuhulala.service;


import com.wuhulala.auth.JwtManager;
import com.wuhulala.dal.mapper.AccountMapper;
import com.wuhulala.dal.model.Account;
import com.wuhulala.util.PasswordUtil;
import com.wuhulala.util.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


/**
 * @author Wuhulala
 * @version 1.0
 * @updateTime 2016/12/25
 */
@Service
public class AccountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);


    private final JwtManager jwtManager;
    private final AccountMapper accountMapper;

    @Autowired
    public AccountService(AccountMapper accountMapper,JwtManager jwtManager) {
        this.accountMapper = accountMapper;
        this.jwtManager = jwtManager;
    }


    @Transactional
    public Account login(Account account, HttpServletRequest request) {
        String newPass = PasswordUtil.createPassword(account.getPassword());

        account.setPassword(newPass);
        Account result = accountMapper.login(account);

        if (null != result) {
            String token = TokenUtils.generateToken(result.getId(), result.getName(), getRolesString(result.getId()));
            result.setToken(token);
            jwtManager.addJwt(result.getId() + "", token);
            result.setLastLogin(new Date());
            accountMapper.updateLastLogin(result);
            return result;
        }
        return null;
    }

    /**
     * 注册
     *
     * @return 1 用户名已存在
     * 2 数据库错误
     * 3 注册成功
     */
    @Transactional
    public int register(String name, String password) {
        if (accountMapper.findByName(name) != null) {
            return 1;
        }
        String newPass = PasswordUtil.createPassword(password);
        Account account = new Account(name, newPass);
        accountMapper.insert(account);
        return account.getId() == null ? 2 : 3;
    }

    /**
     * 修改密码
     *
     * @return 0 用户不存在
     * 1 原密码错误
     * 2 修改成功
     */
    @Transactional
    public int editPassword(Long id, String password, String newPassword) {

        Account account = accountMapper.findById(id);
        if (null != account) {
            if (PasswordUtil.authenticatePassword(account.getPassword(), password)) return 1;
            account.setPassword(PasswordUtil.createPassword(newPassword));
            accountMapper.updatePassword(account);
            jwtManager.delJwt(id +"");
            return 2;
        }
        return 0;
    }

    /**
     * 删除session
     * <p>
     * 0 已删除或不存在
     * 1 删除成功
     */
    public int deleteSession(Long accountId) {
        boolean flag = jwtManager.delJwt(accountId+"");
        if (flag) {
            LOGGER.info("用户" + accountId + "退出登录");
            return 1;
        }
        return 0;
    }

    public String getRolesString(Long userId) {
        return "user";
    }
}
