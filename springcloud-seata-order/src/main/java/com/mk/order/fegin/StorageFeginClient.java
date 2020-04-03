package com.mk.order.fegin;

import com.mk.util.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("springcloud-seata-storage")
public interface StorageFeginClient {

    /**
     * 扣减库存
     * @param productId
     * @param amount
     * @return
     * @throws Exception
     */
    @PostMapping("/storage/reduce")
    public Result reduceStock(@RequestParam("productId") Long productId, @RequestParam("amount") Integer amount);
}
