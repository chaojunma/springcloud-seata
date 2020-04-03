package com.mk.storage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mk.entity.Storage;
import com.mk.util.Result;

public interface StorageService extends IService<Storage> {


    /**
     * 扣减库存
     * @param productId
     * @param amount
     * @return
     * @throws Exception
     */
    public Result reduceStock(Long productId, Integer amount) throws Exception;
}
