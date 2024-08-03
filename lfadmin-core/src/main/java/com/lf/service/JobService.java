package com.lf.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lf.entity.Job;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lf.entity.vo.JobQueryVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lfybtx
 * @since 2024-08-03
 */
public interface JobService extends IService<Job> {

    IPage<Job> findListJob(IPage<Job> page, JobQueryVo jobQueryVo);

    /**
     * 查找该岗位是否被用户使用
     * @param id
     * @return
     */
    List<Long> findUserIdByJobId(Long id);


}
