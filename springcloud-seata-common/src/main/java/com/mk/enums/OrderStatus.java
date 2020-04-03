package com.mk.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {

    INIT(0, "创建中"),
    SCUCCESS(1, "订单成功"),
    FAIL(2, "订单失败");

    OrderStatus(int code, String status) {
        this.code = code;
        this.status = status;
    }

    private int code;

    private String status;


    public String getStatus(int code) {
        OrderStatus[] values = OrderStatus.values();
        for (OrderStatus value : values) {
            if(value.code == code) {
                return value.status;
            }
        }

        return null;
    }

}
