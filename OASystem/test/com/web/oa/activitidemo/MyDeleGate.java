package com.web.oa.activitidemo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;


public class MyDeleGate implements TaskListener{
    static Map<Object,Object> maps;
	public MyDeleGate(){}
	public MyDeleGate(Map<Object,Object> maps){
		this.maps=maps;
	}
	  @Override  
	    public void notify(DelegateTask delegateTask) {  
	              
	        Object obj=maps.get("object");  
	        Class clazz=obj.getClass();  
	        Method[] methods=clazz.getDeclaredMethods();  
	        for(int i=0;i<methods.length;i++ ){                                
	            try {                 
	                  
	                Method beforMehod = clazz.getMethod(methods[i].getName());  
	                beforMehod.invoke(obj);  
	                  
	            } catch (NoSuchMethodException | SecurityException e1) {  
	                // TODO Auto-generated catch block  
	                e1.printStackTrace();  
	            } catch (IllegalAccessException e) {  
	                // TODO Auto-generated catch block  
	                e.printStackTrace();  
	            } catch (IllegalArgumentException e) {  
	                // TODO Auto-generated catch block  
	                e.printStackTrace();  
	            } catch (InvocationTargetException e) {  
	                // TODO Auto-generated catch block  
	                e.printStackTrace();  
	            }  
	  
	        }  
	    }  
}
