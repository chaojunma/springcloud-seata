package com.mk.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderVO {

    // 用户ID
    private Long userId;

    // 商品ID
    private Long productId;

    // 商品价格
    private Integer price;

    // 商品数量
    private Integer amount;
}
