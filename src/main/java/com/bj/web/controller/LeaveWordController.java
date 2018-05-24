package com.bj.web.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bj.web.dao.LeaveWordMapper;
import com.bj.web.entity.LeaveWord;

@Controller
@RequestMapping("/leaveWord")
public class LeaveWordController {
	@Autowired
	private LeaveWordMapper leaveWordMapper;
	@RequestMapping("addLeaveWord")
	public void addLeaveWord(){
		LeaveWord leaveWord=new LeaveWord();
		leaveWord.setEmail("123@.com");
		leaveWord.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		int i = leaveWordMapper.insert(leaveWord);
		System.out.println("===================="+i);
	}
}
