package com.campus.nicecampus.req;

import lombok.Data;

@Data
public class WantRecordReq {
    private String title;
    private String goodsDesc;
    private String wantPrice;
    private String weChatId;
}
