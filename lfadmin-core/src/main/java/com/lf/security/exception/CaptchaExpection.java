package com.lf.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 验证码异常类
 */
public class CaptchaExpection extends AuthenticationException {
    /**
     * 继承父类构造方法
     * @param msg
     */
    public CaptchaExpection(String msg){
        super(msg);
    }
}
