/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.crs.mstr.service;

import java.io.Serializable;

import com.intermorph.cmmn.base.BaseCondition;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.crs.mstr.service
 * @File    : IMCrsMstrCondition.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 2. 8
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class IMCrsMstrCondition extends BaseCondition implements Serializable {

	/** 검색 과정 마스터 ID */
	private String scCrsMstrId;
	/** 검색 과정마스터명 */
	private String scCrsMstrNm;
	/*등급*/
	private String scCrsGrdCdv;
	/*구분*/
	private String scCrsDvsnCdv;
	/*상태*/
	private String scSttsCdv;
	/**
	 * 사용 가능 여부 
	 */
	private String scUseSttsYn;
	
	public String getScCrsMstrId() {
		return scCrsMstrId;
	}
	public void setScCrsMstrId(String scCrsMstrId) {
		this.scCrsMstrId = scCrsMstrId;
	}
	public String getScCrsMstrNm() {
		return scCrsMstrNm;
	}
	public void setScCrsMstrNm(String scCrsMstrNm) {
		this.scCrsMstrNm = scCrsMstrNm;
	}
	public String getScCrsMstrNmDB() {
		return scCrsMstrNm == null ? null : scCrsMstrNm.replaceAll("%", "\\\\%");
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
	public String getScSttsCdv() {
		return scSttsCdv;
	}
	public void setScSttsCdv(String scSttsCdv) {
		this.scSttsCdv = scSttsCdv;
	}
	public String getScUseSttsYn() {
		return scUseSttsYn;
	}
	public void setScUseSttsYn(String scUseSttsYn) {
		this.scUseSttsYn = scUseSttsYn;
	}
	
	
	

}
