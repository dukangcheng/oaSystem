package com.web.oa.service;

import java.util.List;

import com.web.oa.model.PageBean;
import com.web.oa.model.User;
import com.web.oa.utils.HqlHelper;
/**
 * �û�Service�ӿ�
 * @author dukangcheng
 *
 */
public interface UserService {
    /**
     * ��ȡ���е��û�
     * @return
     */
	public List<User> findAll();
    /**
     * ɾ���û�
     * @param id
     */
	public void delete(int id);
    /**
     * ����û�
     * @param model
     */
	public void addUser(User model);
    /**
     * ����ID��ȡ�û�
     * @param id
     * @return
     */
	public User findById(int id);
    /**
     * �����û�
     * @param user
     */
	public void update(User user);
    /**
     * �����û����������ȡ�û�
     * @param loginName
     * @param password
     * @return
     */
	public User findByLoginNameAndPassword(String loginName, String password);
	//�汾1
    @Deprecated
	public PageBean findPageBean(int pageNum, String hqlList, String hqlCount,
			List<Object> parameters);
   /**
    * �汾2
    * ʹ�÷�ҳ�ķ�ʽ��ȡ��ȡ�û�
    * @param pageNum
    * @param hh
    * @return
    */
	public PageBean findPageBean(int pageNum, HqlHelper hh);
    
}
