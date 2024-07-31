package com.lf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lf.dao.UserMapper;
import com.lf.entity.Role;
import com.lf.dao.RoleMapper;
import com.lf.entity.User;
import com.lf.entity.vo.RoleQueryVo;
import com.lf.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lfybtx
 * @since 2024-07-27
 */
@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public IPage<Role> findRoleListByUserId(IPage<Role> page, RoleQueryVo roleQueryVo) {
        //创建条件构造器
        QueryWrapper<Role> queryWrapper = new QueryWrapper<Role>();
        //角色名称
        queryWrapper.like(!ObjectUtils.isEmpty(roleQueryVo.getRoleName()),
                "role_name", roleQueryVo.getRoleName());
        //排序
        queryWrapper.orderByAsc("id");
        //根据用户ID查询用户信息
        User user = userMapper.selectById(roleQueryVo.getUserId());
        //如果用户不为空、且不是管理员，则只能查询自己创建的角色
        if (user != null && !ObjectUtils.isEmpty(user.getIsAdmin()) &&
                user.getIsAdmin() != 1) {
            queryWrapper.eq("create_user", roleQueryVo.getUserId());
        }
        return baseMapper.selectPage(page, queryWrapper);
    }

    /**
     * 保存角色权限关系
     *
     * @param roleId
     * @param permissionIds
     * @return
     */
    @Override
    public boolean saveRolePermission(Long roleId, List<Long> permissionIds) {
        //删除该角色对应的权限信息
        deleteRolePermission(roleId);
        //保存角色权限
        if (permissionIds != null && !permissionIds.isEmpty()) {
            return baseMapper.saveRolePermission(roleId, permissionIds) > 0;
        }
        return true;
    }

    @Override
    public void deleteRolePermission(Long roleId) {
        baseMapper.deleteRolePermission(roleId);
    }

    @Override
    public List<Long> findUserIdByRoleId(Long roleId) {
        return baseMapper.findUserIdByRoleId(roleId);
    }
}
