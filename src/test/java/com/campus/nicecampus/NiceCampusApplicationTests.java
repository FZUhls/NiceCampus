package com.campus.nicecampus;

import com.campus.nicecampus.service.OssService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.xmlunit.builder.Input;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootTest
@Slf4j
class NiceCampusApplicationTests {
    @Autowired
    OssService ossService;
    @Test
    void contextLoads() throws FileNotFoundException {
        File file = new File("D:\\worksplace\\yesterdayYouHadSaidTomorrow\\NiceCampus\\src\\main\\resources\\static\\image\\card1.jpeg");
        log.info("文件名--{}",file.getName());
        InputStream inputStream = new FileInputStream(file);
        ossService.upLoad("testPic",inputStream,"image/jpeg");
    }

}
