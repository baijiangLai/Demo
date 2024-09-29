package com.lbj.trans.pojo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: {baijiang.lai}
 * @CreateTime: 2024-09-27
 * @Description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_balance")  // 指定数据库表名
public class UserBalance {
    @TableField("id")  // 指定字段名
    private Long id;      // 用户ID

    @TableField("balance")   // 指定字段名
    private Integer balance;   // 用户账户余额
}
