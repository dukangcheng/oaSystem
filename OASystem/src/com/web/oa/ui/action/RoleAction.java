package com.web.oa.ui.action;

import java.util.HashSet;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.web.oa.base.BaseAction;
import com.web.oa.model.Privilege;
import com.web.oa.model.Role;
import com.web.oa.service.PrivilegeService;
/**
 * 角色控制器
 * @author dukangcheng
 *
 */
public class RoleAction extends BaseAction<Role>{
    private int[] privilegeIds;
    /**
     * 角色列表
     * @return
     */
    public String list(){
    	List<Role> roleList=roleService.findAll();
    	ActionContext.getContext().put("roleList", roleList);
    	return "list";
    }
    /**
     * 删除 
     * @return
     */
    public String delete(){
    	roleService.delete(model.getId());
    	return "toList";
    }
    /**
     * 添加页面
     * @return
     */
    public String addUI(){
    	
    	return "addUI";
    }
    /**
     * 添加
     * @return
     */
    public String add(){
    	roleService.addRole(model);
    	return "toList";
    }
    /**
     * 修改页面
     * @return
     */
    public String editUI(){
    	Role role=roleService.findById(model.getId());
    	ActionContext.getContext().getValueStack().push(role);
    	return "editUI";
    }
    /**
     * 修改
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
    * 设置权限页面
    * @return
    */
    public String setPrivilegeUI(){
       //获取角色信息
    	Role role=roleService.findById(model.getId());
    	ActionContext.getContext().put("role", role);
       //准备数据
       List<Privilege> privilegeList=privilegeService.findTopList();
       ActionContext.getContext().put("privilegeList", privilegeList);
       //准备回回显的数据
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
     * 设置权限页面
     * @return
     */
    public String setPrivilege(){
    	//获取角色信息
    	Role role=roleService.findById(model.getId());
    	//获取修改后的权限数据
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
