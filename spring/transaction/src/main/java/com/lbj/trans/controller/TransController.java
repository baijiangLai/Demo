package com.lbj.trans.controller;


import com.lbj.trans.service.UserBalanceService;
import com.lbj.trans.service.UserProductService;
import com.lbj.trans.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: {baijiang.lai}
 * @CreateTime: 2024-09-27
 * @Description:
 */


@RestController
@RequestMapping("/trans")
@Slf4j
public class TransController {

    @Autowired
    private UserBalanceService userBalanceService; // 余额服务

    @PutMapping("/moneyProduct")
    public String moneyProduct(@RequestBody UserVo userVo) {
        // 更新用户余额和商品数量
        boolean success = userBalanceService.updateBalance(userVo.getFromUserId(), userVo.getToUserId());

        if (success) {
            return "Transaction successful";
        } else {
            return "Transaction failed";
        }
    }
}
