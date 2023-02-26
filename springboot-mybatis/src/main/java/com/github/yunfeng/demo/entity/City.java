package com.github.yunfeng.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@TableName("cities")
public class City {
    private Long id;
    private String name;
}
