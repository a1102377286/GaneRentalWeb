package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@TableName(value = "user")
@Data
public class User extends Model<User> {
    @TableId(type = IdType.ASSIGN_UUID)
    private String uid;
    private String username;
    private String password;
    private String name; // 用户昵称
    private String pictureURL; // 用户头像地址
    private Double money; // 用户余额 暂定与人民币10:1
    /*
       用户支付和提现时都需要输入支付密码 6位数字
     */
    private String payPassword; // 支付密码 在数据库中需要md5加密保存
}
