package com.github.yunfeng.demo.controller;

import com.github.yunfeng.demo.entity.Country;
import com.github.yunfeng.demo.service.CountryService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;

    @PostMapping("/country")
    public int saveCountry(@RequestBody Country country) {
        return countryService.saveCountry(country);
    }

    @GetMapping("/countries")
    public List<Country> fetchDepartmentList() {
        return countryService.fetchCountryList();
    }
}
