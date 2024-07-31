package com.lf.service;

import com.lf.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lf.entity.vo.PermissionQueryVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lfybtx
 * @since 2024-07-27
 */
public interface PermissionService extends IService<Permission> {
    /**
     * 根据用户ID查询权限列表
     * @param userId
     * @return
     */
    List<Permission> findPermissionListByUserId(Long userId);

    /**
     * 查询菜单列表
     * @param permissionQueryVo
     * @return
     */
    List<Permission> findPermissionList(PermissionQueryVo permissionQueryVo);
    /**
     * 查询上级菜单列表
     * @return
     */
    List<Permission> findParentPermissionList();
    /**
     * 检查菜单是否有子菜单
     * @param id
     * @return
     */
    boolean hasChildrenOfPermission(Long id);
}
