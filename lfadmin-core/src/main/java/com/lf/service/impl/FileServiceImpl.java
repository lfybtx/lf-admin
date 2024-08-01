package com.lf.service.impl;

import com.lf.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {
    /**
     * 文件上传
     * @param file 文件上传对象
     * @param module 文件夹名称
     * @return
     */
    @Override
    public String upload(MultipartFile file, String module) {
        return null;
    }
    /**
     * 删除文件
     * @param url
     */
    @Override
    public void deleteFile(String url) {

    }
}
