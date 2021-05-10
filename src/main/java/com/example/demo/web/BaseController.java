package com.example.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用于前端页面的转跳
 */
@Controller
public class BaseController {
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/details")
    public String details() {
        return "details";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/material_deal")
    public String material_deal() {
        return "material-deal";
    }

    @RequestMapping("/navigation")
    public String navigation() {
        return "navigation";
    }

    @RequestMapping("/center")
    public String userCenter(String username){
        return "center";
    }

    @RequestMapping("/account-deal")
    public String accountdeal(){
        return "account-deal";
    }

    @RequestMapping("/account-purchase")
    public String accountpurchase(){
        return "account-purchase";
    }

}
