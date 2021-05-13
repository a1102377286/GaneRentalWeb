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
    /**
     * 每小时租赁价格
     */
    private Double money;
    /**
     * 商品的图片，多个URL间用，隔开
     */
    private String pictureURL;
    /**
     * 出租方id
     */
    private String lessorUID;
    private String gameId;

    private String username;
    private String password;
    private String descript;
}
