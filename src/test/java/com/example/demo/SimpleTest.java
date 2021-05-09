package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleTest {
    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired
    private UserService userService;
    @Test
    public void test(){

//        List<User> userList = userMapper.selectList(null);
//        Assert.assertEquals(1, userList.size());
//        userList.forEach(System.out::println);
        User user = new User();

        userService.save(user);
    }
}
