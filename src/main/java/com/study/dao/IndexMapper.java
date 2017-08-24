package com.study.dao;

import com.study.model.Index;

public interface IndexMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Index record);

    int insertSelective(Index record);

    Index selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Index record);

    int updateByPrimaryKey(Index record);
    
    /**
     * 查询index表的数量
     * @return
     */
    String selectIndexCount();
}