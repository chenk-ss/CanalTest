package com.chenk.canaltest.service.impl;

import com.chenk.canaltest.dto.entity.User;
import com.chenk.canaltest.mapper.UserMapper;
import com.chenk.canaltest.service.MysqlCommandService;
import com.chenk.canaltest.service.UserService;
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
    public Object syncInsert(Object param) {
        return userMapper.insert((User) param);
    }

    @Override
    public Object syncUpdate(Object param) {
        return userMapper.updateById((User) param);
    }

    @Override
    public Object syncDelete(Object param) {
        return userMapper.deleteById(((User) param).getId());
    }
}
