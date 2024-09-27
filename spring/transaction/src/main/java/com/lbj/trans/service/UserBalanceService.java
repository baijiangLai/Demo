package com.lbj.trans.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lbj.trans.pojo.UserBalance;

public interface UserBalanceService extends IService<UserBalance> {
    boolean updateBalance(Long fromUserId, Long toUserId);
}
