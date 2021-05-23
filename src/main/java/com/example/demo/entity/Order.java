package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName(value = "rentalOrder")
@Data
public class Order {
    @TableId(type = IdType.ASSIGN_UUID)
    private String orderID; // 用于确定订单的主键
    private String startTime; // 订单开始时间
    private String endTime; // 订单结束时间
    private Double payMoney; // 该订单需要支付的金额
    private Boolean isPay; // 是否已经支付 T为已支付

    private String tenantryUID; // 承租方UID
    private String lessorUID; // 出租方UID

    private String commodityID; // 商品信息
    private String changed; // 修改时间

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
    }

    public Boolean getPay() {
        return isPay;
    }

    public void setPay(Boolean pay) {
        isPay = pay;
    }

    public String getTenantryUID() {
        return tenantryUID;
    }

    public void setTenantryUID(String tenantryUID) {
        this.tenantryUID = tenantryUID;
    }

    public String getLessorUID() {
        return lessorUID;
    }

    public void setLessorUID(String lessorUID) {
        this.lessorUID = lessorUID;
    }

    public String getCommodityID() {
        return commodityID;
    }

    public void setCommodityID(String commodityID) {
        this.commodityID = commodityID;
    }

    public String getChanged() {
        return changed;
    }

    public void setChanged(String changed) {
        this.changed = changed;
    }

}
