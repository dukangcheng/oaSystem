package com.web.oa.ui.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.web.oa.base.BaseAction;
import com.web.oa.ui.form.WorkflowBean;
/**
 * 流程定义控制器
 * @author dukangcheng
 *
 */
public class ProcessDefinitionAction extends BaseAction<WorkflowBean> {
    
	/**
	 * 流程定义列表  以及部署的流程列表
	 * @return
	 */
	public String list(){
		//获取流程部署列表
		List<Deployment> dpList = workflowService.findDeployments();
		//获取路程定义列表
		List<ProcessDefinition> pdList = workflowService
				.findProcessDefinitions();
		//将获取的流程部署和流程定义列表放入Struts的Map容器中
		ActionContext.getContext().put("dpList", dpList);
		ActionContext.getContext().put("pdList", pdList);
		return "list";
	}
	
	/**
	 * 部署流程页面
	 * @return
	 */
	public String addUI(){
		return "addUI";
	}
	/**
	 * 部署流程
	 * @return
	 */
	public String add(){
	   //获取流程部署文件  流程部署文件名
		String fileName=model.getFileName();
		File file=model.getFile();
		workflowService.addDeployment(file, fileName);
		return "toList";
	}
	/**
	 * 删除申请模版
	 * @return
	 */
	public String delete(){
       workflowService.deleteDeployment(model.getDeploymentId());
		return "toList";
	}
    /**
     * 查看流程图
     * @return
     */
	public String processImage(){
		//获取流程部署ID 和   图片名称
		String deploymentId=model.getDeploymentId();
		String imageName=model.getImageName();
		InputStream in=workflowService.findImageInputStream(deploymentId, imageName);
		
		//将获取到的图片输入流写到（输出到）页面上面
		int a=-1;
		//服务器对页面的响应response中获取输入流
		OutputStream out=null;
		try {
			out=ServletActionContext.getResponse().getOutputStream();
			while((a=in.read())!=-1){
                out.write(a); 				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try{
			out.close();
			in.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return null;
	}

	
}
