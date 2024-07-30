package com.lf.service;

import com.lf.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lfybtx
 * @since 2024-07-27
 */
public interface DepartmentService extends IService<Department> {
    /**
     * 查询部门列表
     *
     * @param department
     * @return
     */
    List<Department> findDepartmentList(Department department);

    /**
     * 查询上级部门列表
     * * @return
     */


    List<Department> findParentDepartment();

    /**
     * * 判断部门下是否有子部门
     * * @param id
     * * @return
     */


    boolean hasChildrenOfDepartment(Long id);

    /**
     * * 判断部门下是否存在用户
     * * @param id
     * * @return
     */


    boolean hasUserOfDepartment(Long id);
}
