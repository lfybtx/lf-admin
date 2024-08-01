package com.lf.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lf.entity.Department;
import com.lf.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lf.entity.vo.UserQueryVo;

import java.util.List;

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

    /**
     * 分页查询用户信息
     * @param page
     * @param userQueryVo
     * @return
     */
    IPage<User> findUserListByPage(IPage<User> page, UserQueryVo userQueryVo);

    public List<Department> findDepartmentList(Department department);
}
