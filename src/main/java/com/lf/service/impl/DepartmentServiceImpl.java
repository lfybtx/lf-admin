package com.lf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lf.entity.Department;
import com.lf.dao.DepartmentMapper;
import com.lf.entity.User;
import com.lf.service.DepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lf.service.UserService;
import com.lf.utils.DepartmentTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lfybtx
 * @since 2024-07-27
 */
@Service
@Transactional
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    @Autowired
    private UserService userService;
    @Override
    public List<Department> findDepartmentList(Department department) {
        QueryWrapper<Department> queryWrapper = new QueryWrapper<Department>();
        //部门名称
        //第一个判断条件,若为ture,则表示此句有效,若为false,则此句无效
        queryWrapper.like(!ObjectUtils.isEmpty(department.getDepartmentName()), "department_name", department.getDepartmentName());
        //排序
        queryWrapper.orderByAsc("order_num");
        List<Department> departmentList = baseMapper.selectList(queryWrapper);
        List<Department> departmentTree = new ArrayList<>();
        if (!departmentList.isEmpty()) { //如果list不为空,即没有条件或者条件正确
            //departmentList.stream().....getAsLong() 返回集合中最小的Pid
            departmentTree = DepartmentTree.makeDepartmentTree(departmentList, departmentList.stream().mapToLong(Department::getPid).min().getAsLong());
        }//如果list为空,即条件错误或无数据的时候,直接返回一个空集合
        //生成部门树
        return departmentTree;
    }

    @Override
    public List<Department> findParentDepartment() {
        //创建条件构造器对象
        QueryWrapper<Department> queryWrapper = new QueryWrapper<Department>();
        //排序
        queryWrapper.orderByAsc("order_num");
        //查询部门列表
        List<Department> departmentList = baseMapper.selectList(queryWrapper);
        //创建部门对象
        Department department = new Department();
        department.setId(0L);
        department.setDepartmentName("顶级部门");
        department.setPid(-1L);
        departmentList.add(department);
        //生成部门树列表
        List<Department> departmentTree =
                DepartmentTree.makeDepartmentTree(departmentList, -1L);
        //返回部门列表
        return departmentTree;
    }

    @Override
    public boolean hasChildrenOfDepartment(Long id) {
        //创建条件构造器对象
        QueryWrapper<Department> queryWrapper = new QueryWrapper<Department>();
        queryWrapper.eq("pid",id);
        //如果数量大于0，表示存在
        if(baseMapper.selectCount(queryWrapper)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean hasUserOfDepartment(Long id) {
        //创建条件构造器对象
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("department_id",id);
        //如果数量大于0，表示存在
        if(userService.getBaseMapper().selectCount(queryWrapper)>0){
            return true;
        }
        return false;
    }
}
