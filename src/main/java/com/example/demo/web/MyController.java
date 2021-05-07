package com.example.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/details")
    public String details(){
        return "details";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
}
