package com.campus.nicecampus.base.exception;

public class FileTypeIllegalException extends Exception {
    public FileTypeIllegalException(){
        super("文件类型不合法");
    }
    public FileTypeIllegalException(String msg){
        super(msg);
    }
}
