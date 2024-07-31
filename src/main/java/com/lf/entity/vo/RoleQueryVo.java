package com.lf.entity.vo;

import com.lf.entity.Role;
import lombok.Data;
@Data
public class RoleQueryVo extends Role {
    private Long pageNo = 1L;//当前页码
    private Long pageSize = 10L;//每页显示数量
    private Long userId;//用户ID
}
