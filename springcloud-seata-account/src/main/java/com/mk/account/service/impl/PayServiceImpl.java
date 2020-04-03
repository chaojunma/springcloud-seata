package com.mk.account.service.impl;

import com.mk.entity.Account;
import com.mk.account.mapper.AccountMapper;
import com.mk.account.service.PayService;
import com.mk.util.Result;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class PayServiceImpl extends ServiceImpl<AccountMapper, Account> implements PayService {

    @Autowired
    private AccountMapper mapper;


    /**
     * 账户支付
     * @param userId
     * @param price
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result reduceBalance(Long userId, Integer price) throws Exception {
        log.info("当前 XID: {}", RootContext.getXID());

        // 验证账户余额是否足以抵扣
        Account account = checkBalance(userId, price);

        log.info("开始扣减用户 {} 余额", userId);
        UpdateWrapper<Account> wrapper = new UpdateWrapper<>();
        wrapper.eq("user_id", userId);
        Account entity = Account.builder()
                                .used(account.getUsed() + price)
                                .residue(account.getTotal() - price)
                                .build();
        Integer record = mapper.update(entity, wrapper);
        log.info("扣减用户 {} 余额结果:{}", userId, record > 0 ? "操作成功" : "扣减余额失败");


        return Result.builder()
                .success(record > 0)
                .message(record > 0 ? "操作成功" : "扣余额失败")
                .build();
    }


    /**
     * 验证账户余额是否足以抵扣
     * @param userId
     * @param price
     * @return
     * @throws Exception
     */
    private Account checkBalance(Long userId, Integer price) throws Exception {
        log.info("检查用户 {} 余额", userId);
        QueryWrapper<Account> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        Account account = mapper.selectOne(wrapper);
        if (account != null) {
            Integer balance = account.getResidue();
            if (balance < price) {
                log.warn("用户 {} 余额不足，当前余额:{}", userId, balance);
                throw new Exception("余额不足");
            }
        }
        return account;
    }
}
