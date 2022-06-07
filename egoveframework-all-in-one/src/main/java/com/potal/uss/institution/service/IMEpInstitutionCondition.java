/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.potal.uss.institution.service;

import java.io.Serializable;

import com.intermorph.cmmn.base.BaseCondition;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.potal.uss.institution.vo.condition
 * @File : IMEpInstitutionCondition.java
 * @Title : 기관(포탈)
 * @date : 2022.03.29 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class IMEpInstitutionCondition extends BaseCondition implements Serializable {
    
	private String scStatus;
	private String scOrgName;
	private String scBizNo;
	private String scBizKind;
	public String getScStatus() {
		return scStatus;
	}
	public void setScStatus(String scStatus) {
		this.scStatus = scStatus;
	}
	public String getScOrgName() {
		return scOrgName;
	}
	public void setScOrgName(String scOrgName) {
		this.scOrgName = scOrgName;
	}
	public String getScBizNo() {
		return scBizNo;
	}
	public void setScBizNo(String scBizNo) {
		this.scBizNo = scBizNo;
	}
	public String getScBizKind() {
		return scBizKind;
	}
	public void setScBizKind(String scBizKind) {
		this.scBizKind = scBizKind;
	}
	
	
	
	
}