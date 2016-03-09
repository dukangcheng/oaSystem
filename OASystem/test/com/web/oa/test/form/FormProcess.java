package com.web.oa.test.form;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.activiti.engine.FormService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

public class FormProcess {
	private static String readDataFromConsole(String prompt) {  
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
        String str = null;  
        try {  
            System.out.print(prompt);  
            str = br.readLine();  
  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return str;  
    }  
  
    public static void main(String[] args) {  
  
        String str = readDataFromConsole("Please input string��");  
        System.out.println("The information from console�� " + str);  
          
        ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration  
                .createStandaloneProcessEngineConfiguration();  
        // �������ݿ������  
        processEngineConfiguration.setJdbcDriver("com.mysql.jdbc.Driver");  
        processEngineConfiguration  
                .setJdbcUrl("jdbc:mysql://localhost:3306/activiti?useUnicode=true&characterEncoding=utf8");  
        processEngineConfiguration.setJdbcUsername("root");  
        processEngineConfiguration.setJdbcPassword("Dkc230619");  
        processEngineConfiguration.setDatabaseSchemaUpdate("update");  
        ProcessEngine processEngine = processEngineConfiguration  
                .buildProcessEngine();  
  
        System.out.println(processEngine);  
        // ��ȡ���̴洢�������  
        RepositoryService repositoryService = processEngine  
                .getRepositoryService();  
  
        // ��ȡ����ʱ�������  
        RuntimeService runtimeService = processEngine.getRuntimeService();  
  
        // ��ȡ�����������  
        TaskService taskService = processEngine.getTaskService();  
  
        //��ȡ���̵ı����  
        FormService formService=processEngine.getFormService();  
          
        // 1�����������ļ�  
        InputStream in =repositoryService.createDeployment().name("MyProcess")  
        .getClass().getClassLoader().getResourceAsStream("com/web/oa/test/form/"+ str +".zip");  
        ZipInputStream zipInputStream = new ZipInputStream(in);  
        Deployment deployment = processEngine.getRepositoryService()// �����̶���Ͳ��������ص�Service  
                .createDeployment()// ����һ���������  
                .name("���̶���")// ��Ӳ��������  
                .addZipInputStream(zipInputStream)// ָ��zip��ʽ���ļ���ɲ���  
                .deploy();// ��ɲ���  
                  
          
        // 2����������--��ʽһ  
        /*ProcessInstance processInstance = runtimeService 
                .startProcessInstanceByKey("myProcess"); 
        */  
          
        /** 
         * 2����������--��ʽ�� 
         * �˴�ʹ�����ַ�ʽ�����������⣬�����е����̶����keyֵ��ΪmyProcessʱ����ѯ����������Ϊ���� 
         * ��������ʹ��ʱ�������Ǹ���ǰ̨��ʾ�Ķ�����ѡ������֮һ���Ϳ���ֱ�ӻ�ȡprocessDefinitionID��Ҳû������ 
         */  
        ProcessDefinitionQuery query = repositoryService  
                .createProcessDefinitionQuery()  
                .processDefinitionKey("myProcess").active()  
                .orderByProcessDefinitionVersion().desc();  
        ProcessDefinition  processDefinition = query.list().get(0);   
        Map<String, String> formProperties = new HashMap<String, String>();  
          
        formProperties.put("numberOfDays", "20150601");  
        //�˴�����ڶ�������Ϊnullֵ�򱨴���ʹ�������κ�����ҲҪʹ��map��ʽ�Ķ���  
        ProcessInstance processInstance = formService  
                .submitStartFormData(processDefinition.getId(), formProperties);  
  
        String end="1";  
        while (end.equalsIgnoreCase("1")) {  
              
            // 3����ѯ����  
            Task task = taskService.createTaskQuery()  
                    .processInstanceId(processInstance.getId()).singleResult();  
            if (task != null) {  
                Object renderedTaskForm = formService.getRenderedTaskForm(task.getId());  
                System.out.println("������---"+renderedTaskForm.toString());  
                System.out.println("��������---"+task.getName());                             
                  
                /** 
                 * ����������̶���˳��ִ�п��Բ���ͣ�ģ���ȥ���˶δ��뼴�� 
                 * ע����ʵ�д��ڵ���ҳ����ת����������Ҫ�����һ�������ģ����Խ�����ͣ���Ϊ��һ���Ĳ��� 
                 */  
                str = readDataFromConsole("Please input next:");  
                Map<String, Object> variables = new HashMap<String, Object>();  
                variables.put("result", str);  
                  
                //��ʽһ�������̵�������������һ��������Ҫ����ʹ��map���ݣ��˴��ı������Ǹ���һ���������õ�  
                taskService.complete(task.getId(),variables);  
                //��ʽ�����������������  
                //formService.submitTaskFormData(task.getId(), formProperties);  
                //ע�����ϵ����ַ�ʽ���Ի���  
            }else {  
                end="0";  
                System.out.println("�����Ѿ����");  
            }   
  
        }  
  
    }  
}
