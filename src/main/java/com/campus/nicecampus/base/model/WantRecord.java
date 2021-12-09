package com.campus.nicecampus.base.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 16321
 */
@Data
@TableName("want_record")
public class WantRecord {
    private Long id;
    private Long userId;
    private String title;
    private String goodsDesc;
    private Double wantPrice;
}
