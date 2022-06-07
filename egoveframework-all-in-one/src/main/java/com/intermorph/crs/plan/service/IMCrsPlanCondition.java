/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.crs.plan.service;

import java.io.Serializable;

import com.intermorph.cmmn.base.BaseCondition;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.crs.plan.vo.condition
 * @File : IMCrsPlanCondition.java
 * @Title : 과정계획
 * @date : 2022.03.01 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class IMCrsPlanCondition extends BaseCondition implements Serializable {
    

	private String scAgncyId;
	/*등급*/
	private String scCrsGrdCdv;
	/*구분*/
	private String scCrsDvsnCdv;
	

	private String scEduYear;
	private String scEduAplTermStartYear;


	public String getScAgncyId() {
		return scAgncyId;
	}


	public void setScAgncyId(String scAgncyId) {
		this.scAgncyId = scAgncyId;
	}


	public String getScCrsGrdCdv() {
		return scCrsGrdCdv;
	}


	public void setScCrsGrdCdv(String scCrsGrdCdv) {
		this.scCrsGrdCdv = scCrsGrdCdv;
	}


	public String getScCrsDvsnCdv() {
		return scCrsDvsnCdv;
	}


	public void setScCrsDvsnCdv(String scCrsDvsnCdv) {
		this.scCrsDvsnCdv = scCrsDvsnCdv;
	}


	public String getScEduYear() {
		return scEduYear;
	}


	public void setScEduYear(String scEduYear) {
		this.scEduYear = scEduYear;
	}


	public String getScEduAplTermStartYear() {
		return scEduAplTermStartYear;
	}


	public void setScEduAplTermStartYear(String scEduAplTermStartYear) {
		this.scEduAplTermStartYear = scEduAplTermStartYear;
	}
	
	
}