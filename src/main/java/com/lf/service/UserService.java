package com.lf.service;

import com.lf.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lfybtx
 * @since 2024-07-27
 */
public interface UserService extends IService<User> {
    /**
     * 根据用户名查询用户信息
     *
     * @param userName
     * @return
     */
    User findUserByUserName(String userName);
}
