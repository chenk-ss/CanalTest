package com.chenk.kafkcanaltest.service.impl;

import com.chenk.kafkcanaltest.dto.entity.User;
import com.chenk.kafkcanaltest.mapper.UserMapper;
import com.chenk.kafkcanaltest.service.MysqlCommandService;
import com.chenk.kafkcanaltest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: chenke
 * @since: 2021/7/16
 */
@Service
public class UserServiceImpl implements UserService, MysqlCommandService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Object insert(Object param) {
        return userMapper.insert((User) param);
    }

    @Override
    public Object update(Object param) {
        return userMapper.updateById((User) param);
    }

    @Override
    public Object delete(Object param) {
        return userMapper.deleteById((Integer) param);
    }
}
