package com.wuhulala.api.rest;

import com.wuhulala.model.User;
import com.wuhulala.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Wuhulala
 * @version 1.0
 * @updateTime 2016/11/18
 */
@RestController
@RequestMapping("/user")
@Api(value = "user",description = "用户接口")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "get user by pagination",notes = "根据查询条件获取用户")
    public Map<String, Object> allUser(HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        List<User>list = new ArrayList<>();
        for(int i = 0 ; i < 10 ; i++){
            list.add(new User(i+1,"user"+(i+1),null));
        }
        map.put("number",list.size());
        map.put("users",list);
        map.put("server","9001");
        LOGGER.info(request.getRemoteAddr()+":"+request.getRequestURI()+"------请求了");

        return map;
    }



}
