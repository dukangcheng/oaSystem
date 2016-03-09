package com.web.oa.service;

import com.web.oa.model.ApproveInfo;
/**
 * 审批信息Service接口
 * @author dukangcheng
 *
 */
public interface ApproveInfoService {
    /**
     * 根据任务Id获取审批信息
     * @param id
     * @return
     */
	public ApproveInfo findByTaskId(String id);
    /**
     * 根据任务ID获取审批数量
     * @param id
     * @return 
     */
	public int findCountByTaskId(String id);
    /**
     * 根据下一个任务ID获取当前审批信息
     * @param id
     * @return
     */
	public ApproveInfo findByAfterTaskId(String id);

}
