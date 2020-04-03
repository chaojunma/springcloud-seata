package com.mk.account.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mk.entity.Account;
import com.mk.util.Result;

public interface PayService extends IService<Account> {

    /**
     * 账户支付
     * @param userId
     * @param price
     * @return
     * @throws Exception
     */
    public Result reduceBalance(Long userId, Integer price) throws Exception;
}
