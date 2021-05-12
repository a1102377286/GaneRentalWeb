package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.util.ZqbPublicUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {
    @Autowired(required = false)
    private UserMapper userMapper;

    public boolean checkUsername(String username) {
        if (ZqbPublicUtil.strEmpty(username)) {
            return true;
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        User user = userMapper.selectOne(wrapper);
        if (null == user) {
            return true;
        }
        return false;
    }

    public boolean saveUser(User user) {
        if (null == user) {
            return false;
        }
        try {
            userMapper.insert(user);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 登陆功能，通过账号密码查询
     * @param username
     * @param password
     * @return
     */
    @Transactional
    public User getUser(String username, String password) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        wrapper.eq("password", password);
        try {
            User user = userMapper.selectOne(wrapper);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
