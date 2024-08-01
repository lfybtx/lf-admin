package com.lf.dao;

import com.lf.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lfybtx
 * @since 2024-07-27
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 删除角色权限关系
     * @param roleId
     */
    @Delete("delete from sys_role_permission where role_id = #{roleId}")
    void deleteRolePermission(Long roleId);
    /**
     * 保存角色权限关系
     * @param roleId
     * @param permissionIds
     * @return
     */
    int saveRolePermission(Long roleId, List<Long> permissionIds);

    /**
     * 通过角色id查使用过的userId
     * @param roleId
     * @return
     */
    List<Long> findUserIdByRoleId(Long roleId);
}
