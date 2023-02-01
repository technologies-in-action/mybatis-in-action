package com.github.yunfeng.demo.service;

import com.github.yunfeng.demo.entity.Country;

import java.util.List;

public interface CountryService {
    int saveCountry(Country country);

    List<Country> fetchCountryList();
}
