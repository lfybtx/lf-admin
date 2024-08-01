package com.lf.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component

public class OssConfig {
    private String endPoint;
    private String keyId;
    private String keySecret;
    private String bucketName;
}
