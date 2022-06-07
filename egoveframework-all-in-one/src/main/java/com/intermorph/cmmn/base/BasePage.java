/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.base
 * @File    : BasePage.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 1. 13
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
public class BasePage<T> implements Serializable  {
	private static final long serialVersionUID = 1L;
	private List<T>  list;
	private int totalRecordCount;
	private BaseCondition condition;

	public BasePage() {
		list = new ArrayList<T>();
	}

	public List<T> getList() {
		return list;
	}

	

	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	public BaseCondition getCondition() {
		return condition;
	}

	/**
	 * 현재 페이지 번호를 조절한다.
	 * 
	 * @param totalCount
	 */
	public void adjustPage(int totalCount, BaseCondition condition) {
		if (condition != null && condition.getCurrentPageNo() > 0) {
			int page = totalCount / condition.getRecordCountPerPage();
			if (totalCount % condition.getRecordCountPerPage() > 0) {
				page += 1;
			}
			if (condition.getCurrentPageNo() > page) {
				condition.setCurrentPageNo(Math.max(1, page));
			}
		}
	}

	/**
	 * 페이징 처리 객체로 만든다
	 * 
	 * @param itemList
	 * @param totalCount
	 * @param condition
	 */
	public void page(List<T> list, int totalCount, BaseCondition condition) {
		this.list = list;
		this.totalRecordCount = totalCount;
		condition.setTotalRecordCount(totalCount);
		this.condition = condition;
	}

	/**
	 * ascending 정렬의 시작 index usage : <c:out value="${pageInfo.ascIndex + i.index}"/>
	 * 
	 * @return
	 */
	public int getAscIndex() {
		if (condition != null && condition.getCurrentPageNo() > 0) {
			return (condition.getCurrentPageNo() - 1) * condition.getRecordCountPerPage() + 1;
		} else {
			return 1;
		}
	}

	/**
	 * descending 정렬의 시작 index usage : <c:out value="${pageInfo.descIndex - i.index}"/>
	 * 
	 * @return
	 */
	public int getDescIndex() {
		if (condition != null && condition.getCurrentPageNo() > 0) {
			return totalRecordCount - ((condition.getCurrentPageNo() - 1) * condition.getRecordCountPerPage());
		} else {
			return totalRecordCount;
		}
	}
}
