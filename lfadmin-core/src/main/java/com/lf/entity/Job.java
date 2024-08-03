package com.lf.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author lfybtx
 * @since 2024-08-03
 */
@Data
@TableName("sys_job")
public class Job {


    private static final long serialVersionUID = 1L;

    /**
     * 岗位编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 岗位名称
     */
    private String jobName;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 是否删除(0-未删除，1-已删除)
     */
    private Integer isDelete;

    /**
     * 备注
     */
    private String remark;
}
