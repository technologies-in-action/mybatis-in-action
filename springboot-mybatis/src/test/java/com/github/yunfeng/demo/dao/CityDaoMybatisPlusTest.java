package com.github.yunfeng.demo.dao;

import com.github.yunfeng.demo.entity.City;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DisplayName("MybatisPlus测试")
@MybatisPlusTest
@MapperScan("com.github.yunfeng.demo.dao")
class CityDaoMybatisPlusTest {
    @Autowired
    private CityMapper cityMapper;

    @Test
    @DisplayName("测试保存单个城市")
    void testSaveOneCountrySuccess() {
        int insert = cityMapper.insert(City.builder().id(6L).name("Los Angeles").build());
        Assertions.assertEquals(1, insert);
    }

    @Test
    @DisplayName("测试查询所有城市")
    void testFindAllCountiesSuccess() {
        List<City> cities = cityMapper.selectList(null);
        Assertions.assertEquals(1, cities.size());
    }
}