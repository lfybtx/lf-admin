package com.lf.security.filter;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.lf.config.redis.RedisService;
import com.lf.security.exception.CaptchaExpection;
import com.lf.security.handler.LoginFailureHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CaptchaFilter extends OncePerRequestFilter {

    @Autowired
    LoginFailureHandler loginFailureHandler;
    @Autowired
    RedisService redisService;
    //获取登录请求地址
    @Value("${request.login.url}")
    private String loginUrl;
    @Value("${request.captcha.url}")
    private String captchaUrl;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException,IOException {
        // 拦截请求路径
        String url = httpServletRequest.getRequestURI();
        if (url.equals(loginUrl)) {
            // 验证码校验
            try {
                validateCaptcha(httpServletRequest);
            } catch (CaptchaExpection e) {
                // 验证码错误 捕捉异常 返回登陆失败处理器
                loginFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
            }
        }
        // 正确 继续往前走到下一个过滤器
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void validateCaptcha(HttpServletRequest httpServletRequest) throws CaptchaExpection {
        String code = httpServletRequest.getParameter("code"); // 获取请求中的验证码
        String uuid = httpServletRequest.getParameter("uuid"); // 获取请求中的验证码对应的key

        // 验证码错误的两种情况 为空 字符比对
        if (StringUtils.isBlank(code) || StringUtils.isBlank(uuid)) {
            throw new CaptchaExpection("验证码错误");
        } else if (!code.equalsIgnoreCase(redisService.get(uuid).toString())) {
            throw new CaptchaExpection("验证码错误");
        } else {
            // 正确情况 清除缓存 保证验证码一次性使用
            redisService.del(uuid);
        }
    }
}
