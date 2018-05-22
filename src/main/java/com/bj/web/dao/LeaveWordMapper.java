package com.bj.web.dao;

import com.bj.web.entity.LeaveWord;

public interface LeaveWordMapper {
    int deleteByPrimaryKey(String id);

    int insert(LeaveWord record);

    int insertSelective(LeaveWord record);

    LeaveWord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LeaveWord record);

    int updateByPrimaryKey(LeaveWord record);
}