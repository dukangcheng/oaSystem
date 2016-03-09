package com.web.oa.dao;

import java.util.List;

import com.web.oa.base.BaseDao;
import com.web.oa.model.ApproveInfo;
/**
 * 审批信息的Dao抽象接口
 * @author dukangcheng
 *
 */
public interface ApproveInfoDao extends BaseDao<ApproveInfo> {
    /**
     * 根据流程实例ID获取审批信息
     * @param id
     * @return
     */
	public List<ApproveInfo> getApproveInfosByProcessInstanceId(String id);
    /**
     * 根据当前的任务ID获取审批信息
     * @param id
     * @return
     */
	public ApproveInfo getByTaskId(String id);
    /**
     * 获取审批数量根据当前的任务
     * @param id
     * @return
     */
	public int getCountByTaskId(String id);
    /**
     * 根据下一个任务Id获取审批信息
     * @param id
     * @return
     */
	public ApproveInfo getByAfterTaskId(String id);

}
