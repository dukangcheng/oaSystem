package com.web.oa.ui.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.web.oa.base.BaseAction;
import com.web.oa.model.Orgnization;
import com.web.oa.service.OrgnizationService;
import com.web.oa.utils.OrgnizationUtils;
/**
 * 部门控制器
 * @author dukangcheng
 *
 */
public class OrgnizationAction extends BaseAction<Orgnization>{
    private int parentId;
	/**
	 * 部门列表
	 * @return
	 */
	public String list(){
		List<Orgnization> orgnizationList=null;
       if(parentId==0){
        	orgnizationList=orgnizationService.findTopList();
        }else{
            orgnizationList=orgnizationService.findChildren(parentId);
            Orgnization parent=orgnizationService.findById(parentId);
            ActionContext.getContext().put("parent", parent);
        }
        
		ActionContext.getContext().put("orgnizationList", orgnizationList);
		return "list";
	}
	/**
	 * 删除
	 * @return
	 */
	public String delete(){
		orgnizationService.delete(model.getId());
		return "toList";
	}
	/**
	 * 添加页面
	 * @return
	 */
	public String addUI(){
		//准备数据:  orgnizationList
		List<Orgnization> topList=orgnizationService.findTopList();
        List<Orgnization> orgnizationList=OrgnizationUtils.findChildren(topList);
		ActionContext.getContext().put("orgnizationList", orgnizationList);
		return "saveUI";
	}
	/**
	 * 添加
	 * @return
	 */
	public String add(){
		//1.新建对象并封装属性
		model.setParent(orgnizationService.findById(parentId));
		orgnizationService.addOrg(model);
		return "toList";
	}
	/**
	 * 修改页面
	 * @return
	 */
	public String editUI(){
	    //准备数据:orgnizationList
		List<Orgnization> topList=orgnizationService.findTopList();
        List<Orgnization> orgnizationList=OrgnizationUtils.findChildren(topList);
		ActionContext.getContext().put("orgnizationList", orgnizationList);

		//获取回显的信息
		Orgnization orgnization=orgnizationService.findById(model.getId());
		ActionContext.getContext().getValueStack().push(orgnization);
		
		if(orgnization.getParent()!=null){
			model.setParent(orgnization.getParent());
		}
		return "saveUI";
	}
	/**
	 * 修改
	 * @return
	 */
	public String edit(){
		Orgnization orgnization=orgnizationService.findById(model.getId());
		orgnization.setName(model.getName());
        orgnization.setDescription(model.getDescription());
        orgnization.setParent(orgnizationService.findById(parentId));
        orgnizationService.update(orgnization);
        return "toList";
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public int getParentId() {
		return parentId;
	}
}
