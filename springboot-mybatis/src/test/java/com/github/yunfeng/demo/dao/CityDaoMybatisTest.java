package com.github.yunfeng.demo.dao;

import com.github.yunfeng.demo.BaseSqlTest;
import com.github.yunfeng.demo.entity.City;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("在不使用springboot的情况下，对Mapper做单元测试")
@Mapper
class CityDaoMybatisTest extends BaseSqlTest {
    @BeforeEach
    void beforeEach() {
        executeSqlInFile("schema-city.sql");
    }

    @Test
    @DisplayName("测试保存单个城市")
    void testSaveOneCountrySuccess() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CityDao cityDao = session.getMapper(CityDao.class);
            int count = cityDao.save(City.builder().id(6L).name("Los Angeles").build());
            Assertions.assertTrue(count > 0);
        }
    }

    @Test
    @DisplayName("测试查询所有城市")
    void testFindAllCountiesSuccess() {
        executeSqlInFile("data-city.sql");
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CityDao cityDao = session.getMapper(CityDao.class);
            List<City> cities = cityDao.findAll();
            Assertions.assertEquals(1, cities.size());
        }
    }
}