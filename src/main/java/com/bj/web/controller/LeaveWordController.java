package com.bj.web.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bj.web.dao.LeaveWordMapper;
import com.bj.web.entity.LeaveWord;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/leaveWord")
public class LeaveWordController {
	@Autowired
	private LeaveWordMapper leaveWordMapper;
	
	
	@RequestMapping("addLeaveWord")
	public void addLeaveWord(){
		LeaveWord leaveWord=new LeaveWord();
		leaveWord.setEmail("123@.com");
		leaveWord.setName("lily");
		leaveWord.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		int i = leaveWordMapper.insert(leaveWord);
		System.out.println("===================="+i);
	}
	@RequestMapping("testQueryUserListLikeName")
	public void testQueryUserListLikeName() {  
	    //设置分页条件，Parameters:pageNum 页码pageSize 每页显示数量count 是否进行count查询  
	    PageHelper.startPage(0, 3, true);  
	    List<LeaveWord> leaveWords = this.leaveWordMapper.queryUserListLikeName("lily");  
	   /* PageInfo<LeaveWord> pageInfo = new PageInfo<LeaveWord>(leaveWords);  
	      
	    //打印分页信息  
	    System.out.println("数据总数：" + pageInfo.getTotal());  
	    System.out.println("数据总页数：" + pageInfo.getPages());  
	    System.out.println("最后一页：" + pageInfo.getLastPage()); */ 
	    for (LeaveWord user : leaveWords) {  
	        System.out.println("============1222222222222"+user.toString());  
	    }  
	}  
}
