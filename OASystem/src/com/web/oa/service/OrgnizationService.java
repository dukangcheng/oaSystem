package com.web.oa.service;

import java.util.List;

import com.web.oa.model.Orgnization;
/**
 * ����Service����ӿ�
 * @author dukangcheng
 *
 */
public interface OrgnizationService {
    /**
     * ��ȡ���еĲ���
     * @return
     */
	public List<Orgnization> findAll();
    /**
     * ɾ������
     * @param id
     */
	public void delete(int id);
    /**
     * ��Ӳ���
     * @param model
     */
	public void addOrg(Orgnization model);
    /**
     * ��ȡ���ţ�����ID��
     * @param id
     * @return
     */
	public Orgnization findById(int id);
    /**
     * �޸Ĳ�����Ϣ
     * @param orgnization
     */
	public void update(Orgnization orgnization);
    /**
     * ��ȡ��������
     * @return
     */
	public List<Orgnization> findTopList();
    /**
     * ��ȡ�ϼ������µ������Ӳ���
     * @param parentId
     * @return
     */
	public List<Orgnization> findChildren(int parentId);


}
