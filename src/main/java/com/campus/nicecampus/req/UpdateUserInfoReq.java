package com.campus.nicecampus.req;

import lombok.Data;

/**
 * @author Henry
 */
@Data
public class UpdateUserInfoReq {
    private String username;
    private String signature;
    private String weChatId;
}
