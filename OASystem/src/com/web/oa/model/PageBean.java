package com.web.oa.model;

import java.util.List;

public class PageBean {
    //ָ��
	private int pageSize; // ÿҳ��ʾ��¼��
	private int currentPage; // ��ǰҳ
    //�����ݿ��л�ȡ
	private int recordCount; // ��¼����
	private List recordList; // ÿҳ�ļ�¼�б�
    //����
	private int startPageIndex; // ҳ��Ŀ�ʼ����
	private int endPageIndex; // ҳ��Ľ�������

	private int pageCount; // ��ҳ����

	public PageBean() {
	};

	//��ʼ������
	public PageBean(List recordList, int currentPage, int pageSize, int recordCount) {

		 this.pageSize=pageSize;
		 this.currentPage=currentPage;
		 this.recordCount=recordCount;
		 this.recordList=recordList;
		 this.pageCount=(recordCount+pageSize-1)/pageSize;  //������ҳ����
		 //����ҳ�뿼�������������
		 if(pageCount<=10){//ҳ������С��10ʱ
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
