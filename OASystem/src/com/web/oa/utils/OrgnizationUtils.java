package com.web.oa.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.web.oa.model.Orgnization;
/**
 * 部门的树结构的工具类
 * 对部门的层次关系做了抽象
 * @author dukangcheng
 *
 */
public class OrgnizationUtils {

	/**
	 * 遍历部门树，得到所有的部门，并修改部门名称以显示部门层次
	 */
	public static List<Orgnization> findChildren(List<Orgnization> topList) {
       //依次将机构信息放入list集合中
		List<Orgnization> orgList=new ArrayList<Orgnization>();
		getEveryChildren(topList,"●",orgList);
		return orgList;
	}
    /**
     * 遍历部门
     * @param topList
     * @param prefix
     * @param orgList
     */
	private static void getEveryChildren(Collection<Orgnization> topList,String prefix,List<Orgnization> orgList) {
		for(Orgnization top:topList){
			//顶点
			Orgnization copyOrg=new Orgnization();// 原对象是在Session中的对象，是持久化状态，所以要使用副本。
			copyOrg.setId(top.getId());
			copyOrg.setName(prefix+top.getName());
			orgList.add(copyOrg);
			//递归遍历子部门的信息
		    getEveryChildren(top.getChildren(), "　"+prefix,orgList);
		}
	}

}
