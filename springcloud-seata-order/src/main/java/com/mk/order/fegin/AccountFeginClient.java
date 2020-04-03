package com.mk.order.fegin;

import com.mk.util.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("springcloud-seata-account")
public interface AccountFeginClient {

    /**
     * 账户支付
     * @param userId
     * @param price
     * @return
     * @throws Exception
     */
    @PostMapping("/account/reduce")
    public Result reduceBalance(@RequestParam("userId") Long userId, @RequestParam("price")Integer price);
}
