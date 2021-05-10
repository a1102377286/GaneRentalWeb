package com.example.demo.util;

import com.alibaba.fastjson.JSON;

import com.example.demo.entity.User;
import redis.clients.jedis.Jedis;


public class UserUtil {

    static Jedis jedis = JedisUtil.getJedis();
    static public void setVal(User user) {
        jedis.set("user", "Y");
        jedis.set("myUser", JSON.toJSONString(user));
    }

    static public Object getUser(Class clazz) {
        String flag = jedis.get("user");
        if ("N".equals(flag)) {
            return null;
        }
        String value = jedis.get("myUser");
        return JSON.parseObject(value,clazz);
    }

    static public void removeUser() {
        jedis.set("user","N");
    }

}
