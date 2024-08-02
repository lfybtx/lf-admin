package com.lf.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;
import com.google.code.kaptcha.util.Config;

/**
 * 验证码生成工具配置类
 */
@Configuration
public class CaptchaConfig {
    @Bean
    public DefaultKaptcha producer() {
        // 设置动态验证码的属性
        Properties properties = new Properties();
        properties.put("kaptcha.border", "no");
        properties.put("kaptcha.textproducer.font.color", "black");
        properties.put("kaptcha.textproducer.char.space", "4");
        properties.put("kaptcha.textproducer.font.size", "30");
        properties.put("kaptcha.image.height", "40");
        properties.put("kaptcha.image.width", "120");

        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}

