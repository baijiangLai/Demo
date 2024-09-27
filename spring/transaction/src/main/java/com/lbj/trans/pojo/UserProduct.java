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
@NoArgsConstructor
@AllArgsConstructor
@TableName("user_product")  // 指定数据库表名
public class UserProduct {
    @TableField("id")  // 指定字段名
    private Long id;      // 用户ID

    @TableField("product_quantity")    // 指定字段名
    private Integer productQuantity;    // 用户商品数量
}
