package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * 用户出租账号的实体类
 */
@TableName(value = "gameAccount")
@Data
public class GameAccount extends Model<GameAccount> {
    @TableId(type = IdType.ASSIGN_UUID)
    private String accountId;
}
