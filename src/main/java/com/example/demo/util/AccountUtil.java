package com.example.demo.util;

import com.alibaba.fastjson.JSON;
import com.example.demo.entity.GameAccount;
import redis.clients.jedis.Jedis;

public class AccountUtil {
    static Jedis jedis = JedisUtil.getJedis();

    public static void setVal(GameAccount account) {
        if (null == account) {
            return;
        }
        jedis.set("selAccount", JSON.toJSONString(account));
    }

    public static GameAccount getAccount() {
        String acc = jedis.get("selAccount");
        if (ZqbPublicUtil.strEmpty(acc)) {
            return null;
        }
        GameAccount account = JSON.parseObject(acc, GameAccount.class);
        return account;
    }
}
