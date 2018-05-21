package com.study.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.study.dao.IndexMapper;
import com.study.service.fitst.IInstantMessage;

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
