package com.campus.nicecampus;

import cn.hutool.core.util.HexUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileHeaderTest {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\Henry\\Desktop\\测试表.xls");
        FileInputStream fileInputStream = new FileInputStream(file);
        String hex = String.valueOf(HexUtil.encodeHex(fileInputStream.readAllBytes())).toUpperCase();
        System.out.println(hex);
    }
}
