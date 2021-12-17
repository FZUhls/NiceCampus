package com.campus.nicecampus;

import cn.hutool.core.io.FileTypeUtil;

import java.io.File;
import java.io.IOException;

public class FileHeaderTest {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\Henry\\Desktop\\测试表.pdf");
        String type = FileTypeUtil.getType(file);
        System.out.println(type);
    }
}
