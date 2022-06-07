/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.base;

import java.io.Serializable;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.base
 * @File    : BaseCondition.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 1. 13
 * @author  : 노성용
 * @descrption :
 * 검색을 위한 공통 변수를 담고 있다. BaseCondition을 extend 해서 사용하도록 한다.
 * 전자정부프레임워크 page 객체 참조 처리  
 * 검색을 위해 추가되는 필드는 sc로 시작하도록 한다.
 */
public class BaseCondition  extends PaginationInfo implements Serializable{
private static final long serialVersionUID = 1L;
	
    private String scWord;
	private String scKey;
	private int sercharAll; //1: all :0 이면 페이징 
	private int orderbyKey;

	private String excelResult;
	
	public int getFirstItemNo() {
		return (getCurrentPageNo() - 1) * getRecordCountPerPage();
	}

	public int getEndItemNo() {
		return getCurrentPageNo() * getRecordCountPerPage();
	}


	public int getTotalRecordCountView() {
		System.out.println("getTotalPageCount : "  + super.getTotalPageCount());
		return super.getTotalPageCount();
	}

	
	public int getOrderbyKey() {
		return orderbyKey;
	}

	public void setOrderbyKey(int orderbyKey) {
		this.orderbyKey = orderbyKey;
	}

	
	public String getScWord() {
		return scWord;
	}

	public void setScWord(String scWord) {
		this.scWord = scWord;
	}

	public String getScKey() {
		return scKey;
	}

	public void setScKey(String scKey) {
		this.scKey = scKey;
	}

	public String setScWordDB() {
		return scWord == null ? null : scWord.replaceAll("%", "\\\\%");
	}

	public int getSercharAll() {
		return sercharAll;
	}

	public void setSercharAll(int sercharAll) {
		this.sercharAll = sercharAll;
	}
	
	
	public String getExcelResult() {
		return excelResult;
	}

	public void setExcelResult(String excelResult) {
		this.excelResult = excelResult;
	}

	/**
	 * 페이징 여부 체크 
	 * @return
	 */
	public boolean checkPage() {
		if(this.getCurrentPageNo()>0 && this.getSercharAll()==0) {
			return true;
		}else {
			return false;
		}
	}
	
}
