package com.github.yunfeng.demo;

import com.github.yunfeng.demo.dao.CountryDao;

import java.util.List;

public abstract class BaseMappers {
    private static final List<Class<?>> MAPPERS = List.of(CountryDao.class);
    static List<Class<?>> getMappers() {
        return MAPPERS;
    }
}
