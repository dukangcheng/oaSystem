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
 * ���̶��������
 * @author dukangcheng
 *
 */
public class ProcessDefinitionAction extends BaseAction<WorkflowBean> {
    
	/**
	 * ���̶����б�  �Լ�����������б�
	 * @return
	 */
	public String list(){
		//��ȡ���̲����б�
		List<Deployment> dpList = workflowService.findDeployments();
		//��ȡ·�̶����б�
		List<ProcessDefinition> pdList = workflowService
				.findProcessDefinitions();
		//����ȡ�����̲�������̶����б����Struts��Map������
		ActionContext.getContext().put("dpList", dpList);
		ActionContext.getContext().put("pdList", pdList);
		return "list";
	}
	
	/**
	 * ��������ҳ��
	 * @return
	 */
	public String addUI(){
		return "addUI";
	}
	/**
	 * ��������
	 * @return
	 */
	public String add(){
	   //��ȡ���̲����ļ�  ���̲����ļ���
		String fileName=model.getFileName();
		File file=model.getFile();
		workflowService.addDeployment(file, fileName);
		return "toList";
	}
	/**
	 * ɾ������ģ��
	 * @return
	 */
	public String delete(){
       workflowService.deleteDeployment(model.getDeploymentId());
		return "toList";
	}
    /**
     * �鿴����ͼ
     * @return
     */
	public String processImage(){
		//��ȡ���̲���ID ��   ͼƬ����
		String deploymentId=model.getDeploymentId();
		String imageName=model.getImageName();
		InputStream in=workflowService.findImageInputStream(deploymentId, imageName);
		
		//����ȡ����ͼƬ������д�����������ҳ������
		int a=-1;
		//��������ҳ�����Ӧresponse�л�ȡ������
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
