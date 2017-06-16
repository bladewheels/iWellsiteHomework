package com.iwellsite.homework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by mshields on 2017-06-16.
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    String index(){
        return "index";
    }
}