package com.chenk.canaltest.service;

public interface MysqlCommandService {
    Object syncInsert(Object param);

    Object syncUpdate(Object param);

    Object syncDelete(Object param);
}
