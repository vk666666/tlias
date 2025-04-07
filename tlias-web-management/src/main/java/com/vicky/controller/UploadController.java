package com.vicky.controller;

import com.vicky.pojo.Result;
import com.vicky.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@Slf4j
public class UploadController {
    //本地存储
  /*  @PostMapping("/upload")
    public Result upload(String username,Integer age,MultipartFile image) throws Exception {
        log.info("文件上传：{},{},{}",username,age,image);
        //获取原始文件名
        String originalFilename = image.getOriginalFilename();

        //构造唯一文件名（不能重复-UUID通用唯一识别码）ex:7d92cfd0-9897-4d3c-866b-ba1605ff282d
        int index = originalFilename.lastIndexOf("."); //截取.后面的拓展名
        String extname = originalFilename.substring(index);
        String newFileName = UUID.randomUUID().toString() + extname;

        //按照原始文件名将文件存储在服务器
        image.transferTo(new File("/Users/xiong/Desktop/javaweb_me/" + newFileName));
        return Result.success();
    }*/
    //阿里云存储
    @Autowired
    private AliOSSUtils aliOSSUtils; //bean对象注入
    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("文件上传：{}",image.getOriginalFilename());
        //阿里云oss工具类文件上传
        String url = aliOSSUtils.upload(image);
        log.info("文件上传完成，文件访问url：{}",url);
        return Result.success(url);
    }
}
