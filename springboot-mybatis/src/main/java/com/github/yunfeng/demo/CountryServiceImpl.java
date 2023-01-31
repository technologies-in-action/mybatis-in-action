package com.github.yunfeng.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryDao countryDao;

    @Override
    public int saveCountry(Country country) {
        return countryDao.save(country);
    }

    @Override
    public List<Country> fetchCountryList() {
        return countryDao.findAll();
    }
}
