package com.web.oa.model;

import java.util.List;

public class PageBean {
    //指定
	private int pageSize; // 每页显示记录数
	private int currentPage; // 当前页
    //从数据库中获取
	private int recordCount; // 记录总数
	private List recordList; // 每页的记录列表
    //计算
	private int startPageIndex; // 页码的开始索引
	private int endPageIndex; // 页码的结束索引

	private int pageCount; // 总页码数

	public PageBean() {
	};

	//初始化数据
	public PageBean(List recordList, int currentPage, int pageSize, int recordCount) {

		 this.pageSize=pageSize;
		 this.currentPage=currentPage;
		 this.recordCount=recordCount;
		 this.recordList=recordList;
		 this.pageCount=(recordCount+pageSize-1)/pageSize;  //计算总页码数
		 //计算页码考试与结束的索引
		 if(pageCount<=10){//页码总数小于10时
			 startPageIndex=1;
			 endPageIndex=pageCount;
		 }else{
			 startPageIndex=currentPage-4;
			 endPageIndex=currentPage+5;
			 if(startPageIndex<1){
				 startPageIndex=1;
				 endPageIndex=10;
			 }
			 if(endPageIndex>pageCount){
				 startPageIndex=pageCount-9;
				 endPageIndex=pageCount;
			 }
		 }
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public List getRecordList() {
		return recordList;
	}

	public void setRecordList(List recordList) {
		this.recordList = recordList;
	}

	public int getStartPageIndex() {
		return startPageIndex;
	}

	public void setStartPageIndex(int startPageIndex) {
		this.startPageIndex = startPageIndex;
	}

	public int getEndPageIndex() {
		return endPageIndex;
	}

	public void setEndPageIndex(int endPageIndex) {
		this.endPageIndex = endPageIndex;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

}
