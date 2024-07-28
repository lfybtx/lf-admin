package com.lf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lf.entity.User;
import com.lf.dao.UserMapper;
import com.lf.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lfybtx
 * @since 2024-07-27
 */
@Transactional
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /**
     * 根据用户名查询用户信息
     * @param userName
     * @return
     */
    @Override
    public User findUserByUserName(String userName) {
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("username",userName);
//        wrapper.select("*");
        return baseMapper.selectOne(wrapper);
    }
}
