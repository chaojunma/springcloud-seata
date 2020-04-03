package com.mk.storage.controller;

import com.mk.storage.service.StorageService;
import com.mk.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/storage")
public class StorageController {

    @Autowired
    private StorageService storageService;

    /**
     * 扣减库存
     * @param productId
     * @param productId
     * @return
     * @throws Exception
     */
    @PostMapping("/reduce")
    public Result reduceStock(@RequestParam("productId") Long productId,
                                @RequestParam("amount") Integer amount) throws Exception {
        return storageService.reduceStock(productId, amount);
    }
}
