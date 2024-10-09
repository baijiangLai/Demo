package com.lbj.trans.pojo;


import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: {baijiang.lai}
 * @CreateTime: 2024-09-30
 * @Description:
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("test_version")  // 指定数据库表名
public class TestVersion {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("pid")
    private String pid;

    @TableField("ver")
    private Integer ver;

//    @TableField("version") // 新增版本字段
//    private Integer version; // 用于乐观锁
}
