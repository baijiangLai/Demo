package com.lbj.trans.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lbj.trans.mapper.UserProductMapper;
import com.lbj.trans.pojo.UserProduct;
import com.lbj.trans.service.UserProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * @Author: {baijiang.lai}
 * @CreateTime: 2024-09-27
 * @Description:
 */


@Service
public class UserProductServiceImpl extends ServiceImpl<UserProductMapper, UserProduct> implements UserProductService {
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public boolean updateProductQuantity(Long fromUserId, Long toUserId) {
        UserProduct fromUserProduct = getById(fromUserId);
        UserProduct toUserProduct = getById(toUserId);
        int num = 10 / 0;

        Integer fromUserProductProductQuantity = fromUserProduct.getProductQuantity();
        fromUserProductProductQuantity -= 1;
        fromUserProduct.setProductQuantity(fromUserProductProductQuantity);
        updateById(fromUserProduct);

        Integer toUserProductProductQuantity = toUserProduct.getProductQuantity();
        toUserProductProductQuantity += 1;
        toUserProduct.setProductQuantity(toUserProductProductQuantity);
        updateById(toUserProduct);

        return true;
    }
}
