package com.web.oa.service.impl;

import com.web.oa.base.BaseService;
import com.web.oa.model.ApproveInfo;
import com.web.oa.service.ApproveInfoService;
/**
 * 审批Service的实现
 * @author dukangcheng
 *
 */
public class ApproveInfoServiceImpl extends BaseService implements ApproveInfoService{
	@Override
	public ApproveInfo findByTaskId(String id) {
		return approveInfoDao.getByTaskId(id);
	}

	@Override
	public int findCountByTaskId(String id) {
		return approveInfoDao.getCountByTaskId(id);
	}

	@Override
	public ApproveInfo findByAfterTaskId(String id) {
		return approveInfoDao.getByAfterTaskId(id);
	}
	
	
}
