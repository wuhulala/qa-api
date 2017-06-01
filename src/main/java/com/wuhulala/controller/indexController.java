package com.wuhulala.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Wuhulala
 * @version 1.0
 * @updateTime 2016/11/18
 */
@Controller
public class indexController {
    @RequestMapping("/")
    public String index(){
        return "redirect:swagger-ui.html";
    }
}
