/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.generator.service;

import java.io.Serializable;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.generator.service
 * @File    : ColumnVO.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 2. 21
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class ColumnVO implements Serializable {

	/** 컬럼명 */
	private String columnName;

	/** 컬럼 설명 */
	private String columnComment;

	/** 데이터형 */
	private String dataType;

	/** 문자형 데이터 길이 */
	private Long charLength;

	/** 숫자형 데이터 길이 */
	private Long numericLength;

	/** 숫자형 데이터 소수점 크기 */
	private Long numericScale;

	/** primary key 여부 */
	private String primaryKeyYn;
	
	private String Field;
	private String Comment;
	private String Key;
	private String Type;
	
	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnComment() {
		return columnComment;
	}

	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Long getCharLength() {
		return charLength;
	}

	public void setCharLength(Long charLength) {
		this.charLength = charLength;
	}

	public Long getNumericLength() {
		return numericLength;
	}

	public void setNumericLength(Long numericLength) {
		this.numericLength = numericLength;
	}

	public Long getNumericScale() {
		return numericScale;
	}

	public void setNumericScale(Long numericScale) {
		this.numericScale = numericScale;
	}

	public String getPrimaryKeyYn() {
		return primaryKeyYn;
	}

	public void setPrimaryKeyYn(String primaryKeyYn) {
		this.primaryKeyYn = primaryKeyYn;
	}

	public String getField() {
		return Field;
	}

	public void setField(String field) {
		Field = field;
	}

	public String getComment() {
		return Comment;
	}

	public void setComment(String comment) {
		Comment = comment;
	}

	public String getKey() {
		return Key;
	}

	public void setKey(String key) {
		Key = key;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}
	
}
