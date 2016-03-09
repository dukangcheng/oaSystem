package com.web.oa.ui.action;

import java.util.HashSet;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.web.oa.base.BaseAction;
import com.web.oa.model.Privilege;
import com.web.oa.model.Role;
import com.web.oa.service.PrivilegeService;
/**
 * ��ɫ������
 * @author dukangcheng
 *
 */
public class RoleAction extends BaseAction<Role>{
    private int[] privilegeIds;
    /**
     * ��ɫ�б�
     * @return
     */
    public String list(){
    	List<Role> roleList=roleService.findAll();
    	ActionContext.getContext().put("roleList", roleList);
    	return "list";
    }
    /**
     * ɾ�� 
     * @return
     */
    public String delete(){
    	roleService.delete(model.getId());
    	return "toList";
    }
    /**
     * ���ҳ��
     * @return
     */
    public String addUI(){
    	
    	return "addUI";
    }
    /**
     * ���
     * @return
     */
    public String add(){
    	roleService.addRole(model);
    	return "toList";
    }
    /**
     * �޸�ҳ��
     * @return
     */
    public String editUI(){
    	Role role=roleService.findById(model.getId());
    	ActionContext.getContext().getValueStack().push(role);
    	return "editUI";
    }
    /**
     * �޸�
     * @return
     */
    public String edit(){
    	Role role=roleService.findById(model.getId());
    	role.setName(model.getName());
    	role.setDescription(model.getDescription());
    	roleService.update(role);
        return "toList";
    }
    
   /**
    * ����Ȩ��ҳ��
    * @return
    */
    public String setPrivilegeUI(){
       //��ȡ��ɫ��Ϣ
    	Role role=roleService.findById(model.getId());
    	ActionContext.getContext().put("role", role);
       //׼������
       List<Privilege> privilegeList=privilegeService.findTopList();
       ActionContext.getContext().put("privilegeList", privilegeList);
       //׼���ػ��Ե�����
       if(role.getPrivileges().size()>0){
    	   privilegeIds=new int[role.getPrivileges().size()];
    	   int index=0;
    	   for(Privilege priv:role.getPrivileges()){
    		   privilegeIds[index++]=priv.getId();
    	   }
       }
       return "setPrivilegeUI"; 
    }
    /**
     * ����Ȩ��ҳ��
     * @return
     */
    public String setPrivilege(){
    	//��ȡ��ɫ��Ϣ
    	Role role=roleService.findById(model.getId());
    	//��ȡ�޸ĺ��Ȩ������
    	List<Privilege> privileges=privilegeService.findByIds(privilegeIds);
    	role.setPrivileges(new HashSet<Privilege>(privileges));
        roleService.update(role);
    	return "toList";
    }
    
    //-------
    public void setPrivilegeIds(int[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}
    public int[] getPrivilegeIds() {
		return privilegeIds;
	}
}
