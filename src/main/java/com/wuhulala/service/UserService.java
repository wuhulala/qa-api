package com.wuhulala.service;

import org.springframework.stereotype.Service;
import com.wuhulala.model.User;

/**
 * @author Wuhulala
 * @version 1.0
 * @updateTime 2016/11/20
 */
@Service
public class UserService {

    public User login(String name,String pass){
        if("admin".equals(name)){
            if("123456".equals(pass)){
                User user = new User(name,pass);
                user.setToken(name+pass);
                return user;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

}
