package com.lf.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lf.entity.Role;
import com.lf.entity.dto.RolePermissionDTO;
import com.lf.entity.vo.RolePermissionVo;
import com.lf.service.PermissionService;
import com.lf.service.RoleService;
import com.lf.utils.Result;
import com.lf.entity.vo.RoleQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lfybtx
 * @since 2024-07-27
 */
@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    /**
     * 分页查询角色列表
     *
     * @param roleQueryVo
     * @return
     */
    @GetMapping("/list")
    public Result list(RoleQueryVo roleQueryVo) {
        //创建分页对象
        IPage<Role> page = new Page<Role>
                (roleQueryVo.getPageNo(), roleQueryVo.getPageSize());
        //调用分页查询方法
        roleService.findRoleListByUserId(page, roleQueryVo);
        //返回数据
        return Result.ok(page);
    }

    /**
     * 添加角色
     *
     * @param role
     * @return
     **/

    @PostMapping("/add")
    public Result add(@RequestBody Role role) {
        if (roleService.save(role)) {
            return Result.ok().message("角色添加成功");
        }
        return Result.error().message("角色添加失败");
    }

    /**
     * 修改角色
     *
     * @param role
     * @return
     */
    @PutMapping("/update")
    public Result update(@RequestBody Role role) {
        if (roleService.updateById(role)) {
            return Result.ok().message("角色修改成功");
        }
        return Result.error().message("角色修改失败");
    }

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Long id) {
        List<Long> userIdByRoleId = roleService.findUserIdByRoleId(id);
        if (userIdByRoleId.size()>0){
            return Result.error().message("当前角色已分配给用户，无法删除");
        }

        if (roleService.removeById(id)) {
            roleService.deleteRolePermission(id);
            return Result.ok().message("角色删除成功");
        }
        return Result.error().message("角色删除失败");
    }

    /**
     * 分配权限-查询权限树数据
     *
     * @param userId
     * @param roleId
     * @return
     */
    @GetMapping("/getAssignPermissionTree")
    public Result getAssignPermissionTree(Long userId, Long roleId) {
        //调用查询权限树数据的方法
        RolePermissionVo permissionTree =
                permissionService.findPermissionTree(userId, roleId);
        //返回数据
        return Result.ok(permissionTree);
    }

    /**
     * 分配权限-保存权限数据
     *
     * @param rolePermissionDTO
     * @return
     */
    @PostMapping("/saveRoleAssign")
    public Result saveRoleAssign(@RequestBody RolePermissionDTO rolePermissionDTO) {
        if (roleService.saveRolePermission(rolePermissionDTO.getRoleId(), rolePermissionDTO.getList())) {
            return Result.ok().message("权限分配成功");
        } else {
            return Result.error().message("权限分配失败");
        }
    }
}

