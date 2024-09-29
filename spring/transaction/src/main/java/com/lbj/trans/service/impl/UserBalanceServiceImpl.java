package com.lbj.trans.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lbj.trans.mapper.UserBalanceMapper;
import com.lbj.trans.pojo.UserBalance;
import com.lbj.trans.service.UserBalanceService;
import com.lbj.trans.service.UserProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: {baijiang.lai}
 * @CreateTime: 2024-09-27
 * @Description:
 */

@Service
public class UserBalanceServiceImpl extends ServiceImpl<UserBalanceMapper, UserBalance> implements UserBalanceService {
    @Autowired
    private UserProductService userProductService;

    @Override
    public boolean updateBalance(Long fromUserId, Long toUserId) {
        // 用户余额更新
        updateFromUserBalance(fromUserId);

        updateToUserBalance(toUserId);

        int num = 10 / 0;

        // 用户产品更新
        userProductService.updateProductQuantity(fromUserId, toUserId);
        return true;
    }

    private void updateFromUserBalance(Long fromUserId) {
        UserBalance fromUserBalance = getById(fromUserId);
        fromUserBalance.setBalance(fromUserBalance.getBalance() - 100);
        updateById(fromUserBalance);
    }

    private void updateToUserBalance(Long toUserId) {
        UserBalance toUserBalance = getById(toUserId);
        toUserBalance.setBalance(toUserBalance.getBalance() + 100);
        updateById(toUserBalance);
    }
}
