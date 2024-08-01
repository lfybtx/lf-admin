package com.lf.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lf.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lf.entity.vo.RoleQueryVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lfybtx
 * @since 2024-07-27
 */
public interface RoleService extends IService<Role> {
    /**
     * 根据用户查询角色列表
     * @param page
     * @param roleQueryVo
     * @return
     */
    IPage<Role> findRoleListByUserId(IPage<Role> page, RoleQueryVo roleQueryVo);

    /**
     * 保存角色权限关系
     * @param roleId
     * @param permissionIds
     * @return
     */
    boolean saveRolePermission(Long roleId, List<Long> permissionIds);

    /**
     * 删除角色权限列表
     * @param roleId
     */
    void deleteRolePermission(Long roleId);

    /**
     * 通过角色id查使用过的userId
     * @param roleId
     * @return
     */
    List<Long> findUserIdByRoleId(Long roleId);
}
