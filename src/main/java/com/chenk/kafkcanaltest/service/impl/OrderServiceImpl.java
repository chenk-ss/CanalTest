package com.chenk.kafkcanaltest.service.impl;

import com.chenk.kafkcanaltest.dto.entity.Order;
import com.chenk.kafkcanaltest.mapper.OrderMapper;
import com.chenk.kafkcanaltest.service.MysqlCommandService;
import com.chenk.kafkcanaltest.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: chenke
 * @since: 2021/7/16
 */
@Service
public class OrderServiceImpl implements OrderService, MysqlCommandService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Object insert(Object param) {
        return orderMapper.insert((Order) param);
    }

    @Override
    public Object update(Object param) {
        return orderMapper.updateById((Order) param);
    }

    @Override
    public Object delete(Object param) {
        return orderMapper.deleteById((Integer) param);
    }
}
