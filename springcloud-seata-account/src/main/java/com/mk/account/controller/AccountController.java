package com.mk.account.controller;

import com.mk.util.Result;
import com.mk.account.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private PayService payService;

    /**
     * 订单支付
     * @param userId
     * @param price
     * @return
     * @throws Exception
     */
    @PostMapping("/reduce")
    public Result reduceBalance(@RequestParam("userId") Long userId,
                                @RequestParam("price") Integer price) throws Exception {
        return payService.reduceBalance(userId, price);
    }
}
