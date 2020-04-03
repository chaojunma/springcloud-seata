package com.mk.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("account")
public class Account {

    // 主键ID
    @TableId(type = IdType.AUTO)
    private Long id;

    // 用户ID
    private Long userId;

    // 总金额
    private Integer total;

    // 已用金额
    private Integer used;

    // 剩余可用金额
    private Integer residue;
}
