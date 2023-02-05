package com.github.yunfeng.demo.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class City {
    private Long id;
    private String name;
}
