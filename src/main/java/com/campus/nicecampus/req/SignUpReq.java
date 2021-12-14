package com.campus.nicecampus.req;

import lombok.Data;

@Data
public class SignUpReq {
    private String username;
    private String password;
    private String password2;
    private String tel;
}
