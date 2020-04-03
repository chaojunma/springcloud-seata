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
@TableName("storage")
public class Storage {

    @TableId(type = IdType.AUTO)
    private Long id;

    // 商品ID
    private Long productId;

    // 总库存
    private Integer total;

    // 已用库存
    private Integer used;

    // 剩余可用库存
    private Integer residue;
}
