package com.lf.controller;

import com.google.code.kaptcha.Producer;
import com.lf.config.redis.RedisService;
import com.lf.entity.Captcha;
import com.lf.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.Base64;

@RestController
@RequestMapping("/")
public class CaptchaController {
    // 注入验证码生成工具
    @Autowired
    private Producer producer;

    @Autowired
    private RedisService redisService;

    @GetMapping("/captcha")
    public Result captcha() throws IOException {
        // 生成验证码
        String uuid = UUID.randomUUID().toString();
        String code = producer.createText(); // 随机验证码

        // 生成验证码图片
        BufferedImage image = producer.createImage(code);

        // 将图片转换为 Base64 编码的字符串
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        String base64Image = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(outputStream.toByteArray());

        // 存入缓存
        redisService.set(uuid, code, 120L);

        // 返回结果
        return Result.ok(new Captcha(uuid, base64Image));
    }
}
