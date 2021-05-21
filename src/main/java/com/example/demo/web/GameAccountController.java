package com.example.demo.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.GameAccount;
import com.example.demo.entity.ResultInfo;
import com.example.demo.entity.User;
import com.example.demo.service.AccountService;
import com.example.demo.service.UserService;
import com.example.demo.util.AccountUtil;
import com.example.demo.util.UserUtil;
import com.example.demo.util.ZqbPublicUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/account")
@Controller
public class GameAccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    @RequestMapping("/accountList")
    public String getUserList(Model model, Integer page, String desc) {
        if (null == page || 0 == page) {
            page = 1;
        }
        QueryWrapper<GameAccount> wrapper = new QueryWrapper<>();
        if (!ZqbPublicUtil.strEmpty(desc)) {
            model.addAttribute("queryDesc", desc);
            wrapper.like("title", desc);
        } else {
            model.addAttribute("queryDesc", null);
        }
        IPage<GameAccount> iPage = accountService.page(new Page<>(page, 10), wrapper);
        model.addAttribute("accountList", iPage.getRecords());
        model.addAttribute("current", page);
        model.addAttribute("pre", iPage.getCurrent() - 1);
        model.addAttribute("next", iPage.getCurrent() + 1);
        return "accountList";
    }

    @RequestMapping("/accountPurchase")
    public String accountPurchase() {
        return "account-purchase";
    }

    @RequestMapping("/accountSale")
    @ResponseBody
    public ResultInfo accountSale(GameAccount account) {
        User loginUser = (User) UserUtil.getUser(User.class);
        ResultInfo info = new ResultInfo();
        if (null == loginUser) {
            info.setFlag(false);
            info.setErrorMsg("请先登录");
            return info;
        }
        account.setLessorUID(loginUser.getUid());
        boolean flag = accountService.saveAccount(account);
        info.setFlag(flag);
        return info;
    }

    @RequestMapping("/success")
    public String success() {
        return "success";
    }

    @RequestMapping("/detail")
    public String details(Model model) {
        String accountId = AccountUtil.getSelAccountId();
        if (ZqbPublicUtil.strEmpty(accountId)) {
            return "index";
        }
        QueryWrapper<GameAccount> wrapper = new QueryWrapper<>();
        wrapper.eq("accountId", accountId);
        try {
            GameAccount account = accountService.getOne(wrapper);
            if (null == account) {
                return "index";
            }
            QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
            userQueryWrapper.eq("uid", account.getLessorUID());
            User user = userService.getOne(userQueryWrapper);
            if (null == user) {
                return "index";
            }
            model.addAttribute("selAccount", account);

            return "details";
        } catch (Exception e) {
            e.printStackTrace();
            return "index";
        }
    }

    @RequestMapping("/accountDetail")
    @ResponseBody
    public ResultInfo accountDetail(String accountId){
        ResultInfo info = new ResultInfo();
        if (ZqbPublicUtil.strEmpty(accountId)) {
            info.setFlag(false);
            return info;
        }

        AccountUtil.setAccId(accountId);
        info.setFlag(true);
        return info;
    }
}
