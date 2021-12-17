package com.campus.nicecampus.base.exception;

public class AuthorityException extends Exception {
    public AuthorityException(){
         super("权限异常");
    }
    public AuthorityException(String msg){
        super(msg);
    }
}
