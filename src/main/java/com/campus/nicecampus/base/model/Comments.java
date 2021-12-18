package com.campus.nicecampus.base.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 16321
 */
@Data
@TableName("comments")
public class Comments {
    private Long id;
    private Long goodsId;
    private Long userId;
    private String commentContent;
    private Integer score;
    private Integer commentType;
}
