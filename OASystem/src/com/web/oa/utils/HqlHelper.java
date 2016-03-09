package com.web.oa.utils;

import java.util.ArrayList;
import java.util.List;

import com.web.oa.model.Topic;
/**
 * 抽取出来的分页和模糊查询的工具类
 * @author dukangcheng
 *
 */
public class HqlHelper {
	private String fromClause;
	private String whereClause="";  //保证where子句的初始length为0；
	private String orderByClause="";  //保证orderBy子句的初始length为0
	private String alias;
	private List<Object> parameters = new ArrayList<Object>();
    //拼接from子句   并使用指定的别名
	public HqlHelper(Class clazz, String alias) {
		this.fromClause = " from " + clazz.getSimpleName() + " " + alias;
		this.alias = alias + ".";
	}
    //拼接where子句     只有在where子句中中才需要传递参数，其他的不需要传递，所以需要可变参数列表
	public HqlHelper addWhereClause(String condition, Object... param) {
		// 拼接字符串
		if (whereClause.length() == 0) {
			this.whereClause = " where " + alias + condition+" ";
		} else {
			this.whereClause += " and " + alias + condition+" ";
		}

		if (param != null && param.length > 0) {
			for (Object p : param) {
				parameters.add(p);
			}
		}
		return this;
	}
	
    //根据接收的参数判断是否执行where子句
	public HqlHelper addWhereClause(boolean append,String condition,Object...param){
		if(append){
			addWhereClause(condition, param);
		}
		return this;
	}
    //拼接orderBy子句
	private HqlHelper addOrderByClause(boolean asc, String propertyName) {
		if (propertyName !=null) {
				if (orderByClause.length() == 0) {
					this.orderByClause = " order by " + propertyName + " "
							+ (asc ? " asc " : " desc ");
				} else {
					this.orderByClause += "  , " + alias + propertyName + " "
							+ (asc ? " asc " : " desc ");
				}
		}
		return this;
	}
   	//根据接收的参数判断执行那条orderBy子句
	public HqlHelper addOrderByClause(boolean append,boolean asc,String propertyName){
		if(append){
			addOrderByClause(asc, propertyName);
		}
		return this;
	}
	//获取hql  List语句
	public String getHqlList(){
		return this.fromClause+this.whereClause+this.orderByClause;
	}
    //获取hql  count语句
	public String getHqlCount(){
		return "select count(*) "+this.fromClause+this.whereClause;
	}
	
	// ----

	public void setAlias(String alias) {
		this.alias = alias;
	}
    //获取与问好相对应的参数
	public List<Object> getParameters() {
		return parameters;
	}

	public void setParameters(List<Object> parameters) {
		this.parameters = parameters;
	}

	public String getAlias() {
		return alias;
	}

	public String getFromClause() {
		return fromClause;
	}

	public void setFromClause(String fromClause) {
		this.fromClause = fromClause;
	}

	public String getWhereClause() {
		return whereClause;
	}

	public void setWhereClause(String whereClause) {
		this.whereClause = whereClause;
	}

	public String getOrderByClause() {
		return orderByClause;
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}
	

}
