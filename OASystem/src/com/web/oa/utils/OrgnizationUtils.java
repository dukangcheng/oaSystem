package com.web.oa.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.web.oa.model.Orgnization;
/**
 * ���ŵ����ṹ�Ĺ�����
 * �Բ��ŵĲ�ι�ϵ���˳���
 * @author dukangcheng
 *
 */
public class OrgnizationUtils {

	/**
	 * �������������õ����еĲ��ţ����޸Ĳ�����������ʾ���Ų��
	 */
	public static List<Orgnization> findChildren(List<Orgnization> topList) {
       //���ν�������Ϣ����list������
		List<Orgnization> orgList=new ArrayList<Orgnization>();
		getEveryChildren(topList,"��",orgList);
		return orgList;
	}
    /**
     * ��������
     * @param topList
     * @param prefix
     * @param orgList
     */
	private static void getEveryChildren(Collection<Orgnization> topList,String prefix,List<Orgnization> orgList) {
		for(Orgnization top:topList){
			//����
			Orgnization copyOrg=new Orgnization();// ԭ��������Session�еĶ����ǳ־û�״̬������Ҫʹ�ø�����
			copyOrg.setId(top.getId());
			copyOrg.setName(prefix+top.getName());
			orgList.add(copyOrg);
			//�ݹ�����Ӳ��ŵ���Ϣ
		    getEveryChildren(top.getChildren(), "��"+prefix,orgList);
		}
	}

}
