package com.example.demo.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.Order;
import com.example.demo.entity.ResultInfo;
import com.example.demo.entity.User;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;
import com.example.demo.util.TimeUtil;
import com.example.demo.util.UserUtil;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

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

    @RequestMapping("/userOrder")
    public String getUserOrder(Model model){
        User loginUser = (User) UserUtil.getUser(User.class);
        String uid = loginUser.getUid();
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("tenantryUID", uid);
        List<Order> tenantryList = orderService.list(wrapper);

        QueryWrapper<Order> lessorWrapper = new QueryWrapper<>();
        lessorWrapper.eq("lessorUID", uid);
        List<Order> lessorList = orderService.list(lessorWrapper);

        model.addAttribute("tenantryOrder", tenantryList);
        model.addAttribute("lessorOrder", lessorList);
        return "center";
    }

    @RequestMapping("/center")
    public String userCenter(Model model){
        User user = (User) UserUtil.getUser(User.class);
        String uid = user.getUid();
        QueryWrapper<Order> tenantryWrapper = new QueryWrapper<>();
        tenantryWrapper.eq("tenantryUID", uid);
        QueryWrapper<Order> lessorWrapper = new QueryWrapper<>();
        lessorWrapper.eq("lessorUID", uid);
        try {
            List<Order> tenantryList = orderService.list(tenantryWrapper);
            if (null == tenantryList) {
                tenantryList = new ArrayList<>();
            }
            List<Order> lessorList = orderService.list(lessorWrapper);
            if (null == lessorList) {
                lessorList = new ArrayList<>();
            }
            for (Order order : lessorList) {
                order.setChanged(TimeUtil.convertDate(order.getChanged()));
            }
            for (Order order : tenantryList) {
                order.setChanged(TimeUtil.convertDate(order.getChanged()));
            }
            model.addAttribute("lessorList", lessorList);
            model.addAttribute("tenantryList", tenantryList);
            model.addAttribute("user", user);
        } catch (Exception e) {
            return "center";
        }

        model.addAttribute("user", user);
        return "center";
    }
}
