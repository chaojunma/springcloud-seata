package com.mk.entity;


import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("orders")
public class Order {

    // 主键ID
    @TableId(type = IdType.AUTO)
    private Long id;

    // 用户ID
    private Long userId;

    // 商品ID
    private Long productId;

    // 数量
    private Integer count;

    // 金额
    private Integer money;

    // 订单状态：0：创建中；1：已完结
    private Integer status;
}
