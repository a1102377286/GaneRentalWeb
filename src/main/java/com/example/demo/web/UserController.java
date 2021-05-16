package com.example.demo.web;

import com.example.demo.entity.ResultInfo;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public ResultInfo login(User user){
        String username = user.getUsername();
        String password = user.getPassword();
        User loginUser = userService.getUser(username, password);
        ResultInfo info = new ResultInfo();
        if (null == loginUser) {
            info.setFlag(false);
            info.setErrorMsg("账号或密码错误！");
        } else {
            info.setFlag(true);
            UserUtil.setVal(loginUser);
//            redirectIndex();
        }
        return info;
    }

    @RequestMapping("/checkUsername")
    @ResponseBody
    public ResultInfo checkUsername(String username){
        boolean flag = userService.checkUsername(username);
        ResultInfo info = new ResultInfo();
        info.setFlag(flag);
        return info;
    }

    @RequestMapping
    private String redirectIndex() {
        return "redirect:/index";
    }

    @RequestMapping("/register")
    @ResponseBody
    public ResultInfo register(User user) {
        boolean flag = userService.saveUser(user);
        ResultInfo info = new ResultInfo();
        info.setFlag(flag);
        return info;
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        UserUtil.removeUser();
        return "redirect:/index";
    }

    @RequestMapping("/isLogin")
    @ResponseBody
    public ResultInfo isLogin() {
        User user = (User) UserUtil.getUser(User.class);
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setFlag(null != user);
        return resultInfo;
    }
}
