package com.lf.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lf.entity.Job;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lfybtx
 * @since 2024-08-03
 */
@Mapper
public interface JobMapper extends BaseMapper<Job> {

    /**
     * 通过岗位id查使用过的userId
     * @param jobId
     * @return
     */
    List<Long> findUserIdByJobId(Long jobId);
}
