package com.example.demo.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.GameAccount;
import com.example.demo.entity.User;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/account")
public class GameAccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping("/userList")
    public List<User> getUserList(Model model) {
        Page<GameAccount> iPage = accountService.page(new Page<>(1,10), new QueryWrapper<>());
        model.addAttribute("accountList", iPage.getRecords());
        model.addAttribute("pre", iPage.getCurrent() - 1);
        model.addAttribute("next", iPage.getCurrent() + 1);
        return null;
    }
}