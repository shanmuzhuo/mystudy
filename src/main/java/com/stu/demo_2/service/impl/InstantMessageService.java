package com.stu.demo_2.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.stu.demo_2.service.IInstantMessage;
import com.study.dao.IndexMapper;

/**
 * @author zzx
 *	
 */
@Service
public class InstantMessageService implements IInstantMessage {

	@Resource
	private  IndexMapper indexDao;
	
	public String selectcountIndex() {
		String count = this.indexDao.selectIndexCount();
		return count;
	}

}
