package com.web.oa.activitidemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
/**
 * ���̲�����
 * @author dukangcheng
 *
 */
public class ProcessDemo {
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
	        // ������������  
	        ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration  
	                .createStandaloneProcessEngineConfiguration();  
	        // �������ݿ������  
	        processEngineConfiguration.setJdbcDriver("com.mysql.jdbc.Driver");  
	        processEngineConfiguration  
	                .setJdbcUrl("jdbc:mysql://localhost:3306/activiti?useUnicode=true&characterEncoding=utf8");  
	        processEngineConfiguration.setJdbcUsername("root");  
	        processEngineConfiguration.setJdbcPassword("Dkc230619");  
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
	        // 1�����������ļ�  
	        repositoryService.createDeployment().name("processDemo")  
	                .addClasspathResource("com/web/oa/activitidemo/"+str+".bpmn").deploy();  
	  
	          
	        Map<Object,Object> map=new HashMap<Object,Object>();  
            FirstClass fc=new FirstClass();
            SecondClass sc=new SecondClass();
	        map.put("object", fc);  
	        /*map.put("milaoshi", mxj);*/             
	        @SuppressWarnings("unused")
			MyDeleGate common=new MyDeleGate(map);  
	          
	        // 2����������  
	        ProcessInstance processInstance = runtimeService  
	                .startProcessInstanceByKey("processDemo");  
	        String end="1";//processInstance.getId() != null  
	        while (end.equalsIgnoreCase("1")) {  
	            map.remove("object");  
	            map.put("object", sc);  
	            System.out.println(processInstance);
	            // 3����ѯ��һ������  
	            Task task = taskService.createTaskQuery()  
	                    .processInstanceId(processInstance.getId()).singleResult();             
	                              
	            if (task != null) {  
	                System.out.println("============" + task.getId() + "============" + task.getName() + "============");  
	                str = readDataFromConsole("Please input result:");  
	                Map<String, Object> variables = new HashMap<String, Object>();  
	                Boolean result;  
	                if (str.equalsIgnoreCase("true")) {  
	                    result = true;  
	                } else {  
	                    result = false;  
	                }  
	                variables.put("result", result);  
	                taskService.complete(task.getId(), variables); // �������  
	            }else {  
	                end="0";  
	                System.out.println("�����Ѿ����");  
	            }   
	  
	        }  
	  
	    }  
}
