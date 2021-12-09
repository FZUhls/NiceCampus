package com.campus.nicecampus.base.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 16321
 */
@Data
@TableName("goods_detail")
public class GoodsDetail {
    private Long id;
    private Long userId;
    private String goodsName, goodsDesc;
    private Double price;
    private String labels;
    private String type;
    private String imgUrl;
}
