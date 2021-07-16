package com.chenk.canaltest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenk.canaltest.dto.entity.Order;
import org.springframework.stereotype.Repository;

/**
 * @author: chenke
 * @since: 2021/7/16
 */
@Repository
public interface OrderMapper extends BaseMapper<Order> {
}
