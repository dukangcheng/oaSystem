package com.web.oa.service;

import com.web.oa.model.ApproveInfo;
/**
 * ������ϢService�ӿ�
 * @author dukangcheng
 *
 */
public interface ApproveInfoService {
    /**
     * ��������Id��ȡ������Ϣ
     * @param id
     * @return
     */
	public ApproveInfo findByTaskId(String id);
    /**
     * ��������ID��ȡ��������
     * @param id
     * @return 
     */
	public int findCountByTaskId(String id);
    /**
     * ������һ������ID��ȡ��ǰ������Ϣ
     * @param id
     * @return
     */
	public ApproveInfo findByAfterTaskId(String id);

}
