package com.mk.storage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mk.entity.Storage;
import com.mk.storage.mapper.StorageMapper;
import com.mk.storage.service.StorageService;
import com.mk.util.Result;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class StorageServiceImpl extends ServiceImpl<StorageMapper, Storage> implements StorageService {


    @Autowired
    private StorageMapper mapper;

    /**
     * 扣减库存
     * @param productId
     * @param amount
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result reduceStock(Long productId, Integer amount) throws Exception {
        log.info("当前 XID: {}", RootContext.getXID());

        // 检查库存
        Storage storage = checkStock(productId, amount);

        log.info("开始扣减 {} 库存", productId);
        // 扣减库存
        UpdateWrapper<Storage> wrapper = new UpdateWrapper<>();
        wrapper.eq("product_id", productId);
        Storage entity = Storage.builder()
                                .used(storage.getUsed() + amount)
                                .residue(storage.getResidue() - amount)
                                .build();
        Integer record = mapper.update(entity, wrapper);
        log.info("扣减 {} 库存结果:{}", productId, record > 0 ? "操作成功" : "扣减库存失败");


        return Result.builder()
                .success(record > 0)
                .message(record > 0 ? "操作成功" : "扣减库存失败")
                .build();
    }


    /**
     * 检查库存是否足以抵扣
     * @param productId
     * @param amount
     * @throws Exception
     */
    public Storage checkStock(Long productId, Integer amount) throws Exception {
        log.info("检查 {} 库存", productId);

        QueryWrapper<Storage> wrapper = new QueryWrapper<>();
        wrapper.eq("product_id", productId);
        Storage storage = mapper.selectOne(wrapper);
        if(storage != null) {
            // 可用库存
            Integer residue = storage.getResidue();
            if (residue < amount) {
                log.warn("{} 库存不足，当前库存:{}", productId, residue);
                throw new Exception("库存不足");
            }
        }

        return storage;
    }



}
