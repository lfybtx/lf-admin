package com.lf.security.exception;

import org.springframework.security.core.AuthenticationException;

public class CustomerAuthenticationException extends AuthenticationException {
    public CustomerAuthenticationException(String msg) {
        super(msg);
    }
}
