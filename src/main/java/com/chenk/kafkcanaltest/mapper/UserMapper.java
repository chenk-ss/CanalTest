package com.chenk.kafkcanaltest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenk.kafkcanaltest.dto.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author: chenke
 * @since: 2021/7/16
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
}
