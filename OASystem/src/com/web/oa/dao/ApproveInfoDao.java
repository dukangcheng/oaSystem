package com.web.oa.dao;

import java.util.List;

import com.web.oa.base.BaseDao;
import com.web.oa.model.ApproveInfo;
/**
 * ������Ϣ��Dao����ӿ�
 * @author dukangcheng
 *
 */
public interface ApproveInfoDao extends BaseDao<ApproveInfo> {
    /**
     * ��������ʵ��ID��ȡ������Ϣ
     * @param id
     * @return
     */
	public List<ApproveInfo> getApproveInfosByProcessInstanceId(String id);
    /**
     * ���ݵ�ǰ������ID��ȡ������Ϣ
     * @param id
     * @return
     */
	public ApproveInfo getByTaskId(String id);
    /**
     * ��ȡ�����������ݵ�ǰ������
     * @param id
     * @return
     */
	public int getCountByTaskId(String id);
    /**
     * ������һ������Id��ȡ������Ϣ
     * @param id
     * @return
     */
	public ApproveInfo getByAfterTaskId(String id);

}
