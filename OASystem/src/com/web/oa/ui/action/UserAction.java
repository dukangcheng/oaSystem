package com.web.oa.ui.action;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import com.opensymphony.xwork2.ActionContext;
import com.web.oa.base.BaseAction;
import com.web.oa.model.Orgnization;
import com.web.oa.model.PageBean;
import com.web.oa.model.Role;
import com.web.oa.model.User;
import com.web.oa.service.OrgnizationService;
import com.web.oa.utils.HqlHelper;
import com.web.oa.utils.OrgnizationUtils;
/**
 * �û�������
 * @author dukangcheng
 *
 */
public class UserAction extends BaseAction<User> {
	private int orgnizationId;
	private int[] roleIds;

	/**
	 *  �б�ҳ��
	 * @return
	 */
	public String list() {
		/*// ��ȡȫ�������ݣ�Ȼ�����ֵջ��map������  //�汾1
		List<User> userList = userService.findAll();
		ActionContext.getContext().put("userList", userList);*/
		
		//�汾2
        HqlHelper hh=new HqlHelper(User.class, "u");
        hh.addOrderByClause(true, false, "u.createTime");
		PageBean pageBean=userService.findPageBean(pageNum,hh);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "list";
	}

	/**
	 *  ɾ��
	 * @return
	 */
	public String delete() {
		userService.delete(model.getId());
		return "toList";
	}

	/**
	 *  ���ҳ��
	 * @return
	 */
	public String addUI() {
		// 1.1�������й��������� ׼������: orgnizationList;
		List<Orgnization> topList = orgnizationService.findTopList();
		List<Orgnization> orgnizationList = OrgnizationUtils
				.findChildren(topList);
		ActionContext.getContext().put("orgnizationList", orgnizationList);
		// 1��2��ѡ�������й��������� ׼������: roleList
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);

		return "saveUI";
	}

	/**
	 *  ���
	 * @return
	 */
	public String add() {
		// 1.1 ��ȡ�������� ���ò���
		Orgnization orgnization = orgnizationService.findById(orgnizationId);
		model.setOrg(orgnization);
		// 1��2 ��ȡ�������� ���ø�λ
		List<Role> roles = roleService.findByIds(roleIds);
		model.setRoles(new HashSet<Role>(roles));
		// 2.����Ĭ�ϵ����룺 ��������м��ܣ��˴�����md5 �ļ��ܷ�ʽ��
		String passWord = DigestUtils.md5Hex("1234");
		model.setPassword(passWord);
		// 3.���������ԣ�����Ҫ��ҳ����ʾ������ : ����ʱ��
		model.setCreateTime(new Date());
		// 4.����װ�õ����ݴ������ݿ�
		User user = userService.findByLoginNameAndPassword(
				model.getLoginName(), model.getPassword());
		if(user==null){
			userService.addUser(model);
			return "toList";
		}else{
			addFieldError("add", "��ӵ��û��Ѿ�����");
			// 1.1�������й��������� ׼������: orgnizationList;
			List<Orgnization> topList = orgnizationService.findTopList();
			List<Orgnization> orgnizationList = OrgnizationUtils
					.findChildren(topList);
			ActionContext.getContext().put("orgnizationList", orgnizationList);
			// 1��2��ѡ�������й��������� ׼������: roleList
			List<Role> roleList = roleService.findAll();
			ActionContext.getContext().put("roleList", roleList);
            return "saveUI";		
		}
	}

	/**
	 *  �޸�ҳ��
	 * @return
	 */
	public String editUI() {
		// 1.1 ׼������: orgnizationList;
		List<Orgnization> topList = orgnizationService.findTopList();
		List<Orgnization> orgnizationList = OrgnizationUtils
				.findChildren(topList);
		ActionContext.getContext().put("orgnizationList", orgnizationList);
		// 1.2 ׼������: roleList
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		// 2.׼�����Ե�����
		// 2.1 ��ͨ���Ե�����
		User user = userService.findById(model.getId());
		ActionContext.getContext().getValueStack().push(user);
		// System.out.println(user.getOrg().getName()+"**********");
		// 2.2 �������Ĭ��ѡ��
		if (user.getOrg() != null) {
			orgnizationId = user.getOrg().getId();
		}
		// 2.3��ѡ�������Ĭ��ѡ��
		if (user.getRoles().size() > 0) {
			roleIds = new int[user.getRoles().size()];
			int index = 0;
			for (Role role : user.getRoles()) {
				roleIds[index++] = role.getId();
			}
		}
		return "saveUI";
	}

	/**
	 *  �޸�
	 * @return
	 */
	public String edit() {
		// 1.�õ�user����
		User user = userService.findById(model.getId());
		// 2.����user��������
		user.setName(model.getName());
		user.setDescription(model.getDescription());
		user.setLoginName(model.getLoginName());
		user.setEmail(model.getEmail());
		user.setPhoneNum(model.getPhoneNum());
		// 3.(1)���ù������� ����������
		user.setOrg(orgnizationService.findById(orgnizationId));
		// 3.(2)���ù������ԣ����ڸ�λ
		user.setRoles(new HashSet<Role>(roleService.findByIds(roleIds)));
		// ���޸ĺ�����ݸ��µ����ݿ�
		userService.update(user);

		return "toList";
	}
    /**
     * ��ʼ������
     * @return
     */
	public String initPassword() {
		// 1.��ȡuse����
		User user = userService.findById(model.getId());
		// 2.��������м��ܣ��˴�����md5 �ļ��ܷ�ʽ��
		String passWord = DigestUtils.md5Hex("1234");
		user.setPassword(passWord);
		// 3.�������ʼ�������ݿ�
		userService.update(user);
		return "toList";
	}

	/**
	 *  ��¼ҳ��
	 * @return
	 */
	public String loginUI() {

		return "loginUI";
	}

	/**
	 *  ��¼
	 * @return
	 */
	public String login() {
		User user = userService.findByLoginNameAndPassword(
				model.getLoginName(), DigestUtils.md5Hex(model.getPassword()));
		if(user==null){
			//�û�Ϊ�վͷ��ص�½����
			addFieldError("login", "��¼���û������ڣ�");
			return "loginUI";
		}else{
			//��ȷ�͵�½�û�  ӦΪ  �û�  ��Ҫ�����ض�����Ҳ�ܱ������ݣ�����Ҫsession��Χ
           ActionContext.getContext().getSession().put("user",user);			
			return "toIndex";
		}
	}
    /**
     * ע��
     * @return
     */
	public String logout(){
		ActionContext.getContext().getSession().remove("user");
	    return "logout";
	}
	// -------
	public void setOrgnizationId(int orgnizationId) {
		this.orgnizationId = orgnizationId;
	}

	public int getOrgnizationId() {
		return orgnizationId;
	}

	public void setRoleIds(int[] roleIds) {
		this.roleIds = roleIds;
	}

	public int[] getRoleIds() {
		return roleIds;
	}
}
