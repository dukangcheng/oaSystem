package com.web.oa.utils;

import java.util.ArrayList;
import java.util.List;

import com.web.oa.model.Topic;
/**
 * ��ȡ�����ķ�ҳ��ģ����ѯ�Ĺ�����
 * @author dukangcheng
 *
 */
public class HqlHelper {
	private String fromClause;
	private String whereClause="";  //��֤where�Ӿ�ĳ�ʼlengthΪ0��
	private String orderByClause="";  //��֤orderBy�Ӿ�ĳ�ʼlengthΪ0
	private String alias;
	private List<Object> parameters = new ArrayList<Object>();
    //ƴ��from�Ӿ�   ��ʹ��ָ���ı���
	public HqlHelper(Class clazz, String alias) {
		this.fromClause = " from " + clazz.getSimpleName() + " " + alias;
		this.alias = alias + ".";
	}
    //ƴ��where�Ӿ�     ֻ����where�Ӿ����в���Ҫ���ݲ����������Ĳ���Ҫ���ݣ�������Ҫ�ɱ�����б�
	public HqlHelper addWhereClause(String condition, Object... param) {
		// ƴ���ַ���
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
	
    //���ݽ��յĲ����ж��Ƿ�ִ��where�Ӿ�
	public HqlHelper addWhereClause(boolean append,String condition,Object...param){
		if(append){
			addWhereClause(condition, param);
		}
		return this;
	}
    //ƴ��orderBy�Ӿ�
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
   	//���ݽ��յĲ����ж�ִ������orderBy�Ӿ�
	public HqlHelper addOrderByClause(boolean append,boolean asc,String propertyName){
		if(append){
			addOrderByClause(asc, propertyName);
		}
		return this;
	}
	//��ȡhql  List���
	public String getHqlList(){
		return this.fromClause+this.whereClause+this.orderByClause;
	}
    //��ȡhql  count���
	public String getHqlCount(){
		return "select count(*) "+this.fromClause+this.whereClause;
	}
	
	// ----

	public void setAlias(String alias) {
		this.alias = alias;
	}
    //��ȡ���ʺ����Ӧ�Ĳ���
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
