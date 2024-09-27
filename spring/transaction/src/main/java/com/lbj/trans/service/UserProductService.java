package com.lbj.trans.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lbj.trans.pojo.UserProduct;

public interface UserProductService extends IService<UserProduct> {
    boolean updateProductQuantity(Long fromUserId, Long toUserId);
}
