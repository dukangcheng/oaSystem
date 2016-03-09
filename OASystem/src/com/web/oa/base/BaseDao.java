package com.web.oa.base;

import java.util.List;
/**
 * �����ݷ��ʲ������˽ӿ���Ϊ����
 * ���ݲ�Ļ����ӿڣ������ṩ���������ݷ��ʲ�
 * ��ͨ�õļ��ַ��� �ӿ��б�ʾ�������͵ķ��ͣ��������κ�
 * ��Ŀ�е�model��
 * @author dukangcheng
 *
 * @param <T>
 */
public interface BaseDao<T> {
	
	/**
	 * �Գ־ò����ݵĴ洢�����ĳ���
	 */
	public void save(T entities);
	
	/**
     * �Գ־ò����ݵ��޸Ĳ����ĳ���
     */
	public void update(T entites);
	
	/**
     * �Գ־û������ɾ�������ĳ��󣨸���ID��
     */
	public void delete(int id);
	/**
     * �Գ־û�����Ĳ�ѯ������Id���ĳ���
     */
	public T getById(Integer id);
	/**
     * �Գ־û������������ѯ������ID���ĳ���
     */
	public List<T> getByIds(int[] ids);
	/**
     * �����г־û�������в�ѯ�ĳ���
     */
	public List<T> getAll();

	/**
     * ��ȡ��ҳ�ķ�ʽ�Գ־û������ѯ�ĳ���
     */
	public List getPageList(int pageSize, List parameters, int pageNum,String hql);
	/**
     * ָ�������µĳ־û�����ļ����������ĳ���
     */
	public int getBeanCount(List parameters, String countSql);
}
