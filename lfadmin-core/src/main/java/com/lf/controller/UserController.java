package com.lf.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lf.entity.User;
import com.lf.entity.vo.UserQueryVo;
import com.lf.service.UserService;
import com.lf.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lfybtx
 * @since 2024-07-27
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
     * 查询所有用户列表
     *
     * @return
     */
    @GetMapping("/listAll")
    public Result listAll() {
        return Result.ok(userService.list());
    }

    /**
     * 查询用户列表
     * @param userQueryVo
     * @return
     */
    @GetMapping("/list")
    public Result list(UserQueryVo userQueryVo) {
        //创建分页信息
        IPage<User> page = new Page<User>(userQueryVo.getPageNo(),
                userQueryVo.getPageSize());
        //调用分页查询方法
        userService.findUserListByPage(page, userQueryVo);
        //返回数据
        return Result.ok(page);
    }
    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        //查询用户
        User item = userService.findUserByUserName(user.getUsername());
        //判断对象是否为空
        if (item != null) {
            return Result.error().message("该登录名称已被使用，请重新输入！");
        }
        //密码加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //调用保存用户信息的方法
        if(userService.save(user)){
            return Result.ok().message("用户添加成功");
        }
        return Result.error().message("用户添加失败");
    }
}

