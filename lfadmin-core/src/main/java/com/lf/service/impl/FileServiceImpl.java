package com.lf.service.impl;

import com.lf.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {


    /**
     * 头像文件上传
     *
     * @param file   文件上传对象
     * @param module 文件夹名称
     * @return 文件的访问 URL
     */
    @Override
    public String avatarUpload(MultipartFile file, String module) {
        // 获取项目的绝对路径
        String projectPath = System.getProperty("user.dir");

        // 设置 assets 目录的路径
        String assetsPath = projectPath + File.separator + "vue-lfadmin" + File.separator + "src" + File.separator + "assets"+ File.separator+module;

        // 确保目录存在
        File directory = new File(assetsPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // 设置文件名并生成路径
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + "_" + originalFilename;
        String filePath = assetsPath + File.separator + fileName;

        try {
            // 保存文件
            File dest = new File(filePath);
            file.transferTo(dest);
            // 返回文件的 URL
            return "src/assets/avatar/" + fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }



    /**
     * 删除文件
     *
     * @param url
     */
    @Override
    public void deleteAvatar(String url) {

    }
}
