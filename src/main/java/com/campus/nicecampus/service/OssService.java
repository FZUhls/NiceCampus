package com.campus.nicecampus.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;

public interface OssService {
    PutObjectResult upLoad(String objectName, InputStream inputStream, String contentType);
    void delete(String imgUrl);
}
