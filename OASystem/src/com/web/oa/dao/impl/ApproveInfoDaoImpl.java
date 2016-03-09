package com.web.oa.dao.impl;

import java.util.List;

import com.web.oa.base.BaseDaoImpl;
import com.web.oa.dao.ApproveInfoDao;
import com.web.oa.model.ApproveInfo;
/**
 * 审批信息Dao接口的实现
 * @author dukangcheng
 *
 */
public class ApproveInfoDaoImpl extends BaseDaoImpl<ApproveInfo> implements ApproveInfoDao {
	@Override
	public List<ApproveInfo> getApproveInfosByProcessInstanceId(String id) {
		return (List<ApproveInfo>) this.getHibernateTemplate().find("from ApproveInfo a where a.processInstanceId=?", id);
	}

	@Override
	public ApproveInfo getByTaskId(String id) {
		return (ApproveInfo) this.getHibernateTemplate().find("form ApproveInfo a where a.taskId=?", id).get(0);
	}

	@Override
	public int getCountByTaskId(String id) {
		return this.getHibernateTemplate().find("from ApproveInfo a where a.processInstanceId=?", id).size();
	}

	@Override
	public ApproveInfo getByAfterTaskId(String id) {
		return (ApproveInfo) this.getHibernateTemplate().find("from ApproveInfo a where a.afterTaskId=?", id).get(0);
	}

}
