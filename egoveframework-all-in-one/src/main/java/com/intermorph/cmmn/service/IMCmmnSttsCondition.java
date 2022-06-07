/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.cmmn.service;

import java.io.Serializable;

import com.intermorph.cmmn.base.BaseCondition;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.vo.condition
 * @File : IMCmmnSttsCondition.java
 * @Title : 공통상태정보
 * @date : 2022.04.07 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class IMCmmnSttsCondition extends BaseCondition implements Serializable {
	/**  테이블ID (tbl_id) */
	private String scTblId;
	/**  테이블참조ID (tbl_ref_id) */
	private String scTblRefId;
	/**  참조이름 (ref_nm) */
	private String scRefNm;
	private String scSttsCdv;
	
	
	public String getScTblId() {
		return scTblId;
	}
	public void setScTblId(String scTblId) {
		this.scTblId = scTblId;
	}
	public String getScTblRefId() {
		return scTblRefId;
	}
	public void setScTblRefId(String scTblRefId) {
		this.scTblRefId = scTblRefId;
	}
	public String getScRefNm() {
		return scRefNm;
	}
	public void setScRefNm(String scRefNm) {
		this.scRefNm = scRefNm;
	}
	public String getScSttsCdv() {
		return scSttsCdv;
	}
	public void setScSttsCdv(String scSttsCdv) {
		this.scSttsCdv = scSttsCdv;
	}
	
	

}