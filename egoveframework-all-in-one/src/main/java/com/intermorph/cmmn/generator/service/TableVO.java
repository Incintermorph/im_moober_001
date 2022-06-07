/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.generator.service;

import java.io.Serializable;

import com.intermorph.cmmn.base.BaseVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.generator.service
 * @File    : TableVO.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 2. 21
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class TableVO extends BaseVO implements Serializable {
	/** 테이블명 */
	private String tableName;

	/** 테이블 설명 */
	private String tableComment;
	/** 테이블명 */
	private String[] tableNames;
	
	/** 테이블 설명 */
	private String[] tableComments;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableComment() {
		return tableComment;
	}

	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}

	public String[] getTableNames() {
		return tableNames;
	}

	public void setTableNames(String[] tableNames) {
		this.tableNames = tableNames;
	}

	public String[] getTableComments() {
		return tableComments;
	}

	public void setTableComments(String[] tableComments) {
		this.tableComments = tableComments;
	}

	

}
