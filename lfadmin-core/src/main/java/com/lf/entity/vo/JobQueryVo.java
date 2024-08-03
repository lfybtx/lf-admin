package com.lf.entity.vo;

import com.lf.entity.Job;
import lombok.Data;

@Data
public class JobQueryVo extends Job {
    private Long pageNo = 1L;//当前页码
    private Long pageSize = 10L;//每页显示数量
    private Long userId;//用户ID
}
