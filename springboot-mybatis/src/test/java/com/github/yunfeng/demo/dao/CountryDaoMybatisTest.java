package com.github.yunfeng.demo.dao;

import com.github.yunfeng.demo.BaseSqlTest;
import com.github.yunfeng.demo.entity.Country;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

@DisplayName("在不使用springboot的情况下，对Mapper做单元测试")
@Mapper
class CountryDaoMybatisTest extends BaseSqlTest {
    @BeforeEach
    void beforeEach() {
        executeSqlInFile("schema-country.sql");
    }

    @Test
    @DisplayName("测试保存单个国家")
    void testSaveOneCountrySuccess() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CountryDao countryDao = session.getMapper(CountryDao.class);
            int count = countryDao.save(Country.builder().id(6L).name("CHINA").build());
            Assertions.assertTrue(count > 0);
        }
    }

    @Test
    @DisplayName("测试查询所有国家")
    void testFindAllCountiesSuccess() {
        executeSqlInFile("data-country.sql");
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CountryDao countryDao = session.getMapper(CountryDao.class);
            List<Country> countries = countryDao.findAll();
            Assertions.assertEquals(5, countries.size());
        }
    }

    @Test
    @DisplayName("测试根据ID查询一个国家")
    void testFindCountiesByIdSuccess() {
        executeSqlInFile("data-country.sql");
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CountryDao countryDao = session.getMapper(CountryDao.class);
            List<Country> countries = countryDao.findByIds(Collections.singletonList(1L));
            Country country = countries.get(0);
            Assertions.assertEquals("USA", country.getName());
        }
    }
}