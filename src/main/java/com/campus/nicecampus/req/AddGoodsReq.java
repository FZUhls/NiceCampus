package com.campus.nicecampus.req;

import lombok.Data;
import lombok.ToString;

/**
 * @author Henry
 */
@Data
@ToString
public class AddGoodsReq {
    private String goodsName;
    private String goodsDesc;
    private String type;
    private String labels;
    private String wechatId;
    private String imageName;
    private String price;
}
