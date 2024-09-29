package com.lbj.trans.service;


import com.lbj.trans.pojo.UserProduct;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @Author: {baijiang.lai}
 * @CreateTime: 2024-09-27
 * @Description:
 */


@SpringBootTest
public class UserProductServiceTest {
    @Resource
    private UserProductService userProductService;
    @Test
    public void test() {
        Long fromId = 1L;
        Long toId = 2L;

        userProductService.updateProductQuantity(fromId, toId);
    }
}
