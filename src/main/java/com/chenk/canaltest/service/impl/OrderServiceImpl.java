package com.chenk.canaltest.service.impl;

import com.chenk.canaltest.dto.entity.Order;
import com.chenk.canaltest.mapper.OrderMapper;
import com.chenk.canaltest.service.MysqlCommandService;
import com.chenk.canaltest.service.OrderService;
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
        return orderMapper.deleteById(((Order) param).getId());
    }
}
