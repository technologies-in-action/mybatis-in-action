package com.github.yunfeng.demo.dao;

import com.github.yunfeng.demo.entity.Country;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CountryDao {
    @Insert("insert into countries (id, name) values (#{id}, #{name})")
    int save(Country country);

    @Select("select id, name from countries")
    List<Country> findAll();

    List<Country> findByIds(List<Long> ids);
}
