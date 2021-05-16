package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * 用户出租账号的实体类
 */
@TableName(value = "gameaccount")
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

    /**
     * 商品标题
     */
    private String title;
    private String username;
    private String password;
    private String descript;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public String getLessorUID() {
        return lessorUID;
    }

    public void setLessorUID(String lessorUID) {
        this.lessorUID = lessorUID;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }
}
