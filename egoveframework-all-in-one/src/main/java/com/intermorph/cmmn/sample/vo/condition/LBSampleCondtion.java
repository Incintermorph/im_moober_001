package com.intermorph.cmmn.sample.vo.condition;

import java.io.Serializable;

import com.intermorph.cmmn.base.BaseCondition;

/**
 * 
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.sample.vo.codition
 * @File    : LBSampleCondtion.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 1. 13
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class LBSampleCondtion  extends BaseCondition implements Serializable{
	
	private String srchCodeId;

	public String getSrchCodeId() {
		return srchCodeId;
	}

	public void setSrchCodeId(String srchCodeId) {
		this.srchCodeId = srchCodeId;
	}
	
	
	
} 
