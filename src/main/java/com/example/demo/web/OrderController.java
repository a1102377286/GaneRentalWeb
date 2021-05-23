package com.example.demo.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.GameAccount;
import com.example.demo.entity.Order;
import com.example.demo.entity.ResultInfo;
import com.example.demo.entity.User;
import com.example.demo.service.AccountService;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;
import com.example.demo.util.AccountUtil;
import com.example.demo.util.UserUtil;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.Date;

@RequestMapping("/order")
@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @RequestMapping("/createOrder")
    @ResponseBody
    public ResultInfo rental(Integer rentalTime){
        ResultInfo info = new ResultInfo();
        User user = (User) UserUtil.getUser(User.class);
        if (null == user) {
            info.setFlag(false);
            info.setErrorMsg("请先登陆");
            return info;
        }
        GameAccount account = AccountUtil.getAccount();
        String tenantryUID = user.getUid();
        String lessorUID = account.getLessorUID();
        if (tenantryUID.equals(lessorUID)) {
            info.setFlag(false);
            info.setErrorMsg("不能租赁自己发布的账号");
            return info;
        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("uid", tenantryUID);
        User updateUser = userService.getOne(userQueryWrapper);

        QueryWrapper<GameAccount> accountQueryWrapper = new QueryWrapper<>();
        accountQueryWrapper.eq("accountID", account.getAccountId());
        GameAccount gameAccount = accountService.getOne(accountQueryWrapper);
        String isRental = gameAccount.getRental();
        if ("T".equals(isRental)) {
            info.setFlag(false);
            info.setErrorMsg("账号正在租赁中");
            return info;
        }

        Order order = new Order();
        order.setLessorUID(lessorUID);
        order.setTenantryUID(updateUser.getUid());
        Date now = new Date();
        order.setStartTime(String.valueOf(now.getTime()));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.HOUR_OF_DAY, rentalTime);
        Date end = calendar.getTime();

        order.setEndTime(String.valueOf(end.getTime()));
        order.setCommodityID(account.getAccountId());

        Double money = rentalTime * account.getMoney();
        Double myMoney = updateUser.getMoney();
        if (null == myMoney){
            myMoney = 0.00D;
        }
        if (myMoney < money) {
            info.setFlag(false);
            info.setErrorMsg("余额不足");
            return info;
        }
        order.setPayMoney(money);
        order.setChanged(String.valueOf(now.getTime()));
        Double sub = money - myMoney;
        user.setMoney(sub);
        try {
            orderService.save(order);

            userService.update(updateUser, userQueryWrapper);
            account.setRental("T");
            accountService.update(account, accountQueryWrapper);
            info.setFlag(true);

        } catch (Exception e) {
            e.printStackTrace();
            info.setFlag(false);
            info.setErrorMsg("租赁失败");
        }
        return info;

    }
}
