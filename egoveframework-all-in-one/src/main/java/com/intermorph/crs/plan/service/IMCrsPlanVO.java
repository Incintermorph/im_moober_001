/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.crs.plan.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.intermorph.cmmn.base.BaseVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.crs.plan.vo
 * @File : IMCrsPlanVO.java
 * @Title : 과정계획
 * @date : 2022.03.01 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class IMCrsPlanVO extends BaseVO implements Serializable {

	/** ID */
	private String[] crsPlanIds;

	/** 과정계획ID (crs_plan_id) */
	private String crsPlanId;
	/** 기관ID (agncy_id) */
	private String agncyId;
	/** 교육년도 (edu_year) */
	private String eduYear;
	/** 과정등급코드값 (crs_grd_cdv) */
	private String crsGrdCdv;
	/** 과정구분코드값 (crs_dvsn_cdv) */
	private String crsDvsnCdv;

	private String eduAplTerm_bgnDt;
	private String eduAplTerm_endDt;
	private String eduTerm_bgnDt;
	private String eduTerm_endDt;

	public String[] getCrsPlanIds() {
		if (this.crsPlanIds != null) {
			String[] tempData = new String[this.crsPlanIds.length];
			System.arraycopy(this.crsPlanIds, 0, tempData, 0, this.crsPlanIds.length);
			return tempData;
		} else {
			return null;
		}
	}

	public void setCrsPlanIds(String[] crsPlanIds) {
		if (crsPlanIds != null) {
			this.crsPlanIds = new String[crsPlanIds.length];
			System.arraycopy(crsPlanIds, 0, this.crsPlanIds, 0, crsPlanIds.length);
		} else {
			this.crsPlanIds = null;
		}
	}

	public String getCrsPlanId() {
		return crsPlanId;
	}

	public void setCrsPlanId(String crsPlanId) {
		this.crsPlanId = crsPlanId;
	}

	public String getAgncyId() {
		return agncyId;
	}

	public void setAgncyId(String agncyId) {
		this.agncyId = agncyId;
	}

	public String getEduYear() {
		return eduYear;
	}

	public void setEduYear(String eduYear) {
		this.eduYear = eduYear;
	}

	public String getCrsGrdCdv() {
		return crsGrdCdv;
	}

	public void setCrsGrdCdv(String crsGrdCdv) {
		this.crsGrdCdv = crsGrdCdv;
	}

	public String getCrsDvsnCdv() {
		return crsDvsnCdv;
	}

	public void setCrsDvsnCdv(String crsDvsnCdv) {
		this.crsDvsnCdv = crsDvsnCdv;
	}

	public String getEduAplTerm_bgnDt() {
		return eduAplTerm_bgnDt;
	}

	public void setEduAplTerm_bgnDt(String eduAplTerm_bgnDt) {
		this.eduAplTerm_bgnDt = eduAplTerm_bgnDt;
	}

	public String getEduAplTerm_endDt() {
		return eduAplTerm_endDt;
	}

	public void setEduAplTerm_endDt(String eduAplTerm_endDt) {
		this.eduAplTerm_endDt = eduAplTerm_endDt;
	}

	public String getEduTerm_bgnDt() {
		return eduTerm_bgnDt;
	}

	public void setEduTerm_bgnDt(String eduTerm_bgnDt) {
		this.eduTerm_bgnDt = eduTerm_bgnDt;
	}

	public String getEduTerm_endDt() {
		return eduTerm_endDt;
	}

	public void setEduTerm_endDt(String eduTerm_endDt) {
		this.eduTerm_endDt = eduTerm_endDt;
	}

	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}