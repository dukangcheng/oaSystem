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
 * 流程测试类
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
	  
	        String str = readDataFromConsole("Please input string：");  
	        System.out.println("The information from console： " + str);  
	        // 创建流程引擎  
	        ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration  
	                .createStandaloneProcessEngineConfiguration();  
	        // 连接数据库的配置  
	        processEngineConfiguration.setJdbcDriver("com.mysql.jdbc.Driver");  
	        processEngineConfiguration  
	                .setJdbcUrl("jdbc:mysql://localhost:3306/activiti?useUnicode=true&characterEncoding=utf8");  
	        processEngineConfiguration.setJdbcUsername("root");  
	        processEngineConfiguration.setJdbcPassword("Dkc230619");  
	        ProcessEngine processEngine = processEngineConfiguration  
	                .buildProcessEngine();  
	  
	        System.out.println(processEngine);  
	        // 获取流程存储服务组件  
	        RepositoryService repositoryService = processEngine  
	                .getRepositoryService();  
	  
	        // 获取运行时服务组件  
	        RuntimeService runtimeService = processEngine.getRuntimeService();  
	  
	        // 获取流程任务组件  
	        TaskService taskService = processEngine.getTaskService();
	        // 1、部署流程文件  
	        repositoryService.createDeployment().name("processDemo")  
	                .addClasspathResource("com/web/oa/activitidemo/"+str+".bpmn").deploy();  
	  
	          
	        Map<Object,Object> map=new HashMap<Object,Object>();  
            FirstClass fc=new FirstClass();
            SecondClass sc=new SecondClass();
	        map.put("object", fc);  
	        /*map.put("milaoshi", mxj);*/             
	        @SuppressWarnings("unused")
			MyDeleGate common=new MyDeleGate(map);  
	          
	        // 2、启动流程  
	        ProcessInstance processInstance = runtimeService  
	                .startProcessInstanceByKey("processDemo");  
	        String end="1";//processInstance.getId() != null  
	        while (end.equalsIgnoreCase("1")) {  
	            map.remove("object");  
	            map.put("object", sc);  
	            System.out.println(processInstance);
	            // 3、查询第一个任务  
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
	                taskService.complete(task.getId(), variables); // 完成任务  
	            }else {  
	                end="0";  
	                System.out.println("任务已经完成");  
	            }   
	  
	        }  
	  
	    }  
}
