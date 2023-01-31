package com.github.yunfeng.demo;

import java.util.List;

public interface CountryService {
    int saveCountry(Country country);

    List<Country> fetchCountryList();
}
