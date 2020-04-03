package com.mk.order.controller;

import com.mk.order.service.OrderService;
import com.mk.util.Result;
import com.mk.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 下订单
     * @param orderVO
     * @return
     */
    @PostMapping("/placeOrder")
    public Result placeOrder(@RequestBody OrderVO orderVO){
        log.info("收到下单请求,用户:{}, 商品:{}", orderVO.getUserId(), orderVO.getProductId());
        return orderService.placeOrder(orderVO);
    }

}
