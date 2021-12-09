package com.campus.nicecampus.base.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 16321
 */
@Data
@TableName("cart")
public class Cart {
    private Long id;
    private Long userId;
    private Integer goodsId;
}
