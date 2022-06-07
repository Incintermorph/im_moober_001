/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.cmmn.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.intermorph.cmmn.base.BaseVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.vo
 * @File : IMRsltCodeVO.java
 * @Title : 결과코드
 * @date : 2022.03.30 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class IMRsltCodeVO extends BaseVO implements Serializable {


	/**  결과코드 (rslt_code) */
	private String rsltCode;
	/**  결과코드구분 (rslt_code_dvsn) */
	private String rsltCodeDvsn;
	/**  결과참조ID (rslt_ref_id) */
	private String rsltRefId;
	/**  결과테이블ID (rslt_tbl_id) */
	private String rsltTblId;
	/**  기준코드 (stndrd_code) */
	private String stndrdCode;


	

	public String getRsltCode() {
	    return rsltCode;
	}
	
	public void setRsltCode(String rsltCode) {
	    this.rsltCode = rsltCode;
	}
	public String getRsltCodeDvsn() {
	    return rsltCodeDvsn;
	}
	
	public void setRsltCodeDvsn(String rsltCodeDvsn) {
	    this.rsltCodeDvsn = rsltCodeDvsn;
	}
	public String getRsltRefId() {
	    return rsltRefId;
	}
	
	public void setRsltRefId(String rsltRefId) {
	    this.rsltRefId = rsltRefId;
	}
	public String getRsltTblId() {
	    return rsltTblId;
	}
	
	public void setRsltTblId(String rsltTblId) {
	    this.rsltTblId = rsltTblId;
	}
	public String getStndrdCode() {
	    return stndrdCode;
	}
	
	public void setStndrdCode(String stndrdCode) {
	    this.stndrdCode = stndrdCode;
	}



	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}