package com.github.yunfeng.demo.dao;

import com.github.yunfeng.demo.entity.City;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@MybatisTest
@DisplayName("springboot集成mybatis测试")
class CityDaoSpringTest {
    @Autowired
    private CityDao cityDao;

    @Test
    @DisplayName("测试保存单个城市")
    void testSaveOneCountrySuccess() {
        int count = cityDao.save(City.builder().id(2L).name("Los Angeles").build());
        Assertions.assertTrue(count > 0);
    }

    @Test
    @DisplayName("测试查询所有城市")
    void testFindAllCountiesSuccess() {
        List<City> all = cityDao.findAll();
        Assertions.assertEquals(1, all.size());
    }
}