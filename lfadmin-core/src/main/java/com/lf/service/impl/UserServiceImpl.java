package com.lf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lf.dao.DepartmentMapper;
import com.lf.entity.Department;
import com.lf.entity.User;
import com.lf.dao.UserMapper;
import com.lf.entity.vo.UserQueryVo;
import com.lf.service.FileService;
import com.lf.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lf.utils.SystemConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    FileService fileService;
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

    /**
     * 分页查询用户信息
     *
     * @param page
     * @param userQueryVo
     * @return
     */
    @Override
    public IPage<User> findUserListByPage(IPage<User> page, UserQueryVo userQueryVo)
    {
        //创建条件构造器对象
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        Department department=new Department();
        department.setId(userQueryVo.getDepartmentId());
        List<Department> departmentList = findDepartmentList(department);
        // 提取所有部门ID
        List<Long> departmentIds = departmentList.stream()
                .map(Department::getId)
                .collect(Collectors.toList());
        // 部门条件：在部门ID集合中
        if (!departmentIds.isEmpty()) {
            queryWrapper.in("department_id", departmentIds);
        }
        //用户名
        queryWrapper.like(!ObjectUtils.isEmpty(userQueryVo.getUsername()),
                "username",userQueryVo.getUsername());
        //真实姓名
        queryWrapper.like(!ObjectUtils.isEmpty(userQueryVo.getRealName()),
                "real_name",userQueryVo.getRealName());
        //电话
        queryWrapper.like(!ObjectUtils.isEmpty(userQueryVo.getPhone()),
                "phone",userQueryVo.getPhone());
        //查询并返回数据
        return baseMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<Department> findDepartmentList(Department department) {
        // 查找指定departmentId的部门
        Long departmentId = department.getId();
        List<Department> allDepartments = departmentMapper.selectList(null); // 获取所有部门
        List<Department> result = new ArrayList<>();

        // 递归查找所有子部门
        findAllChildDepartments(allDepartments, departmentId, result);
        result.add(department);
        return result;
    }

    @Override
    public boolean deleteById(Long id) {
        //查询
        User user = baseMapper.selectById(id);
        //删除用户角色关系
        baseMapper.deleteUserRole(id);
        //删除用户
        if (baseMapper.deleteById(id) > 0) {
            //判断用户是否存在
            if (user != null && !ObjectUtils.isEmpty(user.getAvatar())
                    && !user.getAvatar().equals(SystemConstants.DEFAULT_AVATAR)) {
                //删除文件
                fileService.deleteFile(user.getAvatar());
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean saveUserRole(Long userId, List<Long> roleIds) {
        //删除该用户对应的角色信息
        baseMapper.deleteUserRole(userId);
        //保存用户角色信息
        return baseMapper.saveUserRole(userId,roleIds)>0;
    }

    private void findAllChildDepartments(List<Department> allDepartments, Long parentId, List<Department> result) {
        for (Department department : allDepartments) {
            if (department.getPid().equals(parentId)) {
                result.add(department);
                // 递归查找子部门
                findAllChildDepartments(allDepartments, department.getId(), result);
            }
        }
    }


}
