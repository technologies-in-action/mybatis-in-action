package com.github.yunfeng.demo;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("在不使用springboot的情况下，对Mapper做单元测试")
class CountryDaoWithoutSpringBootTest extends BaseSqlTest {
    @BeforeEach
    void beforeEach() {
        executeSqlInFile("schema-city.sql");
    }

    @Test
    @DisplayName("测试保存单个城市")
    void testSaveOneCountrySuccess() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CountryDao countryDao = session.getMapper(CountryDao.class);
            int count = countryDao.save(Country.builder().id(6L).name("CHINA").build());
            Assertions.assertTrue(count > 0);
        }
    }

    @Test
    @DisplayName("测试查询所有城市")
    void testFindAllCountiesSuccess() {
        executeSqlInFile("data-city.sql");
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CountryDao countryDao = session.getMapper(CountryDao.class);
            List<Country> countries = countryDao.findAll();
            Assertions.assertEquals(5, countries.size());
        }
    }
}