package com.bj.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bj.web.dao.LeaveWordMapper;
import com.bj.web.entity.LeaveWord;
import com.bj.web.service.LeaveWordService;

@Service("leaveWordService")
public class LeaveWordServiceImpl implements LeaveWordService{

	@Autowired
	private LeaveWordMapper leaveWordMapper;
	public int addLeaveWord(LeaveWord leaveWord){
		return leaveWordMapper.insert(leaveWord);
	}
	/*@Override
	public PageInfo<LeaveWord> getLeaveWordList(Integer pageNo,Integer pageSize) {
		 pageNo = pageNo == null?1:pageNo;
		 pageSize = pageSize == null?10:pageSize;
		 PageHelper.startPage(pageNo, pageSize);
		 List<LeaveWord> list= leaveWordMapper.getLeaveWordList();
		PageInfo<LeaveWord> page = new PageInfo<LeaveWord>(list);
		return page;
	}*/

}
