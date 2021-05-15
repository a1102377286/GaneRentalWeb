package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.GameAccount;
import com.example.demo.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService extends ServiceImpl<AccountMapper, GameAccount> {
    @Autowired(required = false)
    private AccountMapper accountMapper;

    public boolean saveAccount(GameAccount account) {
        try {
            accountMapper.insert(account);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
