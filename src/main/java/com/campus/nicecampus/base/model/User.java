package com.campus.nicecampus.base.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 16321
 */
@Data
@TableName("user")
public class User {
    private Long id;
    private String username;
    private String password;
    private String tel;
    private String wechatId;
    private String iconUrl;
    private String signature;
}
