package com.lf.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 *
 * </p>
 *
 * @author lfybtx
 * @since 2024-07-27
 */
@Data
@TableName("sys_user")
public class User implements Serializable , UserDetails {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 登录名称(用户名)
     */
    private String username;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 帐户是否过期
     */
    @TableField("is_account_non_expired")
    private int isAccountNonExpired;

    /**
     * 帐户是否被锁定
     */
    @TableField("is_account_non_locked")
    private int isAccountNonLocked;

    /**
     * 密码是否过期
     */
    @TableField("is_credentials_non_expired")
    private int isCredentialsNonExpired;

    /**
     * 帐户是否可用
     */
    @TableField("is_enabled")
    private int isEnabled;

    /**
     * 真实姓名
     */
    @TableField("real_name")
    private String realName;

    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 所属部门ID
     */
    @TableField("department_id")
    private Long departmentId;

    /**
     * 所属部门名称
     */
    @TableField("department_name")
    private String departmentName;

    /**
     * 性别(0-男，1-女)
     */
    private Integer gender;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 是否是管理员(1-管理员)
     */
    @TableField("is_admin")
    private Integer isAdmin;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 是否删除(0-未删除，1-已删除)
     */
    @TableField("is_delete")
    private Integer isDelete;



    /**
     * 权限列表
     */
    @TableField(exist = false)
    Collection<? extends GrantedAuthority> authorities;
    /**
     * 查询用户权限列表
     */
    @TableField(exist = false)
    private List<Permission> permissionList;

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired == 1;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked == 1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired == 1;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled == 1;
    }
}
