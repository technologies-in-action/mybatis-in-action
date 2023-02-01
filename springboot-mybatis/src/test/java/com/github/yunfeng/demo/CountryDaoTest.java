package com.github.yunfeng.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@MybatisTest
@DisplayName("springboot集成mybatis测试")
class CountryDaoTest {
    @Autowired
    private CountryDao countryDao;

    @Test
    @DisplayName("测试保存单个国家")
    void testSaveOneCountrySuccess() {
        int count = countryDao.save(Country.builder().id(6L).name("CHINA").build());
        Assertions.assertTrue(count > 0);
    }

    @Test
    @DisplayName("测试查询所有国家")
    void testFindAllCountiesSuccess() {
        List<Country> all = countryDao.findAll();
        Assertions.assertEquals(5, all.size());
    }

    @Test
    @DisplayName("测试根据ID查询一个国家")
    void testFindCountiesByIdsSuccess() {
        Country country = countryDao.findById(1L);
        Assertions.assertEquals("USA", country.getName());
    }
}