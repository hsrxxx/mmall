package com.huang.mmall.vo;

import lombok.Data;

import java.util.List;

@Data
public class OrdersVO {
    //User
    private String userName;

    //Orders
    private String serialnumber;
    private Float cost;
    private String userAddress;

    //orderDetail
    private List<OrderDetailVO> orderDetailVOList;
}
