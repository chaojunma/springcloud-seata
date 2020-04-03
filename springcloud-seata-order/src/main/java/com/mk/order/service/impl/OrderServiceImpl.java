package com.mk.order.service.impl;


import com.mk.util.Result;
import com.mk.vo.OrderVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mk.entity.Order;
import com.mk.enums.OrderStatus;
import com.mk.order.fegin.AccountFeginClient;
import com.mk.order.fegin.StorageFeginClient;
import com.mk.order.mapper.OrderMapper;
import com.mk.order.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private OrderMapper mapper;

    @Autowired
    private AccountFeginClient accountFeginClient;

    @Autowired
    private StorageFeginClient storageFeginClient;

    @Override
    @GlobalTransactional
    public Result placeOrder(OrderVO orderVO) {

        Order order = Order.builder()
                .userId(orderVO.getUserId())
                .productId(orderVO.getProductId())
                .count(orderVO.getAmount())
                .money(orderVO.getPrice())
                .status(OrderStatus.INIT.getCode())
                .build();

        mapper.insert(order);

        log.info("保存订单{}", order.getId() != null ? "成功" : "失败");
        log.info("当前 XID: {}", RootContext.getXID());

        // 扣减库存
        log.info("开始扣减库存");
        Result storageResult = storageFeginClient.reduceStock(orderVO.getProductId(), orderVO.getAmount());
        log.info("扣减库存结果:{}", storageResult);

        // 扣减余额
        log.info("开始扣减余额");
        Result payResult = accountFeginClient.reduceBalance(orderVO.getUserId(), orderVO.getPrice());
        log.info("扣减余额结果:{}", payResult);

        order.setStatus(OrderStatus.SCUCCESS.getCode());
        Integer updateOrderRecord = mapper.updateById(order);
        log.info("更新订单:{} {}", order.getId(), updateOrderRecord > 0 ? "成功" : "失败");

        return Result.builder()
                .success(storageResult.isSuccess() && payResult.isSuccess())
                .build();
    }
}
