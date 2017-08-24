package com.study.dao;

import com.study.model.Weight;

public interface WeightMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Weight record);

    int insertSelective(Weight record);

    Weight selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Weight record);

    int updateByPrimaryKey(Weight record);
}