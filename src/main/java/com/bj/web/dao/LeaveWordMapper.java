package com.bj.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bj.web.entity.LeaveWord;

public interface LeaveWordMapper {
    int deleteByPrimaryKey(String id);

    int insert(LeaveWord record);

    int insertSelective(LeaveWord record);

    LeaveWord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LeaveWord record);

    int updateByPrimaryKey(LeaveWord record);
    
    List<LeaveWord> queryUserListLikeName(@Param("name") String name);  

}