package com.campus.nicecampus.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.internal.OSSHeaders;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyun.oss.model.StorageClass;
import com.campus.nicecampus.service.OssService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
@Service
@Slf4j
public class OssServiceImpl implements OssService {
    @Autowired
    private OSS ossClient;
    @Value("${aliyun.oss.bucketName}")
    private String bucketName;
    @Override
    public PutObjectResult upLoad(String objectName, InputStream inputStream, String contentType){
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(contentType);
        return ossClient.putObject(bucketName, objectName, inputStream, metadata);
    }
}
