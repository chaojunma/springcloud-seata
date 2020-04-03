package com.mk.order.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.mk.entity.Order;
import com.mk.util.Result;
import com.mk.vo.OrderVO;

public interface OrderService extends IService<Order> {

    /**
     * 下订单
     * @param orderVO
     * @return
     */
    public Result placeOrder(OrderVO orderVO);
}
