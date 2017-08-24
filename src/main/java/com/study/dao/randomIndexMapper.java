package com.study.dao;

import com.study.model.randomIndex;

public interface randomIndexMapper {
    int insert(randomIndex record);

    int insertSelective(randomIndex record);
}