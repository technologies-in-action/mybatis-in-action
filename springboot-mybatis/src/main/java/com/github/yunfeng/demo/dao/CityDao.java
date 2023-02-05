package com.github.yunfeng.demo.dao;

import com.github.yunfeng.demo.entity.City;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CityDao {
    @Insert("insert into cities (id, name) values (#{id}, #{name})")
    int save(City country);

    @Select("select id, name from cities")
    List<City> findAll();
}
