package com.lf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lf.entity.Job;
import com.lf.dao.JobMapper;
import com.lf.entity.vo.JobQueryVo;
import com.lf.service.JobService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lfybtx
 * @since 2024-08-03
 */
@Service
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements JobService {

    @Override
    public IPage<Job> findListJob(IPage<Job> page, JobQueryVo jobQueryVo) {
        //创建条件构造器
        QueryWrapper<Job> queryWrapper = new QueryWrapper<Job>();
        //角色名称
        queryWrapper.like(!ObjectUtils.isEmpty(jobQueryVo.getJobName()),
                "job_name", jobQueryVo.getJobName());
        //排序
        queryWrapper.orderByAsc("id");
        return baseMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<Long> findUserIdByJobId(Long id) {
        return baseMapper.findUserIdByJobId(id);
    }
}
