package com.lf.security.filter;

import com.lf.config.redis.RedisService;
import com.lf.security.CustomerUserDetailsService;
import com.lf.security.exception.CustomerAuthenticationException;
import com.lf.security.handler.LoginFailureHandler;
import com.lf.utils.JwtUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Data
@Component
public class CheckTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;
    @Autowired
    private LoginFailureHandler loginFailureHandler;
    @Autowired
    private RedisService redisService;
    //获取登录请求地址
    @Value("${request.login.url}")
    private String loginUrl;
    @Value("${request.captcha.url}")
    private String captchaUrl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            //获取当前请求的url地址
            String url = request.getRequestURI();
            //如果当前请求不是登录请求，则需要进行token验证
            if (!url.equals(loginUrl)&&!url.equals(captchaUrl)) {
                this.validateToken(request);
            }
        } catch (AuthenticationException e) {
            loginFailureHandler.onAuthenticationFailure(request, response, e);
        }
        //登录请求不需要验证token
        doFilter(request, response, filterChain);
    }

    /**
     * 验证token
     *
     * @param request
     */
    private void validateToken(HttpServletRequest request) throws
            AuthenticationException {
        //获取token信息
        String token = request.getHeader("token");
        if (ObjectUtils.isEmpty(token)) {
            token = request.getParameter("token");
        }
        //如果请求中不存在token信息，则抛出异常
        if (ObjectUtils.isEmpty(token)) {
            throw new CustomerAuthenticationException("token不存在");
        }
        //判断redis中是否存在该token
        String tokenKey = "token_" + token;
        String redisToken = redisService.get(tokenKey);
        //如果redis里面没有token,说明该token失效
        if (ObjectUtils.isEmpty(redisToken)) {
            throw new CustomerAuthenticationException("登录用户已过期，请重新登录！");
        }
        //如果token和Redis中的token不一致，则验证失败
        if (!token.equals(redisToken)) {
            throw new CustomerAuthenticationException("token验证失败");
        }
        //如果存在token，则从token中解析出用户名
        String username = jwtUtils.getUsernameFromToken(token);
        //如果用户名为空，则解析失败
        if (ObjectUtils.isEmpty(username)) {
            throw new CustomerAuthenticationException("token解析失败");
        }
        //获取用户信息
        UserDetails userDetails = customerUserDetailsService.loadUserByUsername(username);
        //判断用户信息是否为空
        if (userDetails == null) {
            throw new CustomerAuthenticationException("token验证失败");
        }
        //创建身份验证对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authenticationToken.setDetails(new
                WebAuthenticationDetailsSource().buildDetails(request));
        //设置到Spring Security上下文
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
