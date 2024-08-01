package com.lf.dao;

import com.lf.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lfybtx
 * @since 2024-07-27
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
