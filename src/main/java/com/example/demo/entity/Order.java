package com.example.demo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private String orderID; // 用于确定订单的主键
    private Date startTime; // 订单开始时间
    private Date endTime; // 订单结束时间
    private Double payMoney; // 该订单需要支付的金额
    private Boolean isPay; // 是否已经支付 T为已支付

    private String tenantryUID; // 承租方UID
    private String lessorUID; // 出租方UID

    private String commodityID; // 商品信息
    private Date changed; // 修改时间
}
