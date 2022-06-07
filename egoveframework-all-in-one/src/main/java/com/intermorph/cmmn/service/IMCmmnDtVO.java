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
 * @File : IMCmmnDtVO.java
 * @Title : 공통날짜
 * @date : 2022.02.28 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class IMCmmnDtVO extends BaseVO implements Serializable {


	/**  테이블ID (tbl_id) */
	private String tblId;
	/**  테이블참조ID (tbl_ref_id) */
	private String tblRefId;
	/**  참조이름 (ref_nm) */
	private String refNm;
	/**  시작일시 (bgn_dt) */
	private String bgnDt;
	/**  종료일시 (end_dt) */
	private String endDt;



	/**
	 * 공통 참조 파라미터명
	 */
	private String[] cmmnDtRefNms;
	
	
	
	
	

	public String[] getCmmnDtRefNms() {
		if(this.cmmnDtRefNms !=null){
			String[] tempData = new String[this.cmmnDtRefNms.length];
			System.arraycopy(this.cmmnDtRefNms , 0, tempData, 0, this.cmmnDtRefNms.length);
			return tempData;
		}else{
			return null;
		}
	}

	public void setCmmnDtRefNms(String[] cmmnDtRefNms) {
		if (cmmnDtRefNms != null) {
			this.cmmnDtRefNms = new String[cmmnDtRefNms.length];
			System.arraycopy(cmmnDtRefNms, 0, this.cmmnDtRefNms, 0, cmmnDtRefNms.length);
		} else {
			this.cmmnDtRefNms = null;
		}
	}


	public String getTblId() {
	    return tblId;
	}
	
	public void setTblId(String tblId) {
	    this.tblId = tblId;
	}
	public String getTblRefId() {
	    return tblRefId;
	}
	
	public void setTblRefId(String tblRefId) {
	    this.tblRefId = tblRefId;
	}
	public String getRefNm() {
	    return refNm;
	}
	
	public void setRefNm(String refNm) {
	    this.refNm = refNm;
	}
	public String getBgnDt() {
	    return bgnDt;
	}
	
	public void setBgnDt(String bgnDt) {
	    this.bgnDt = bgnDt;
	}
	public String getEndDt() {
	    return endDt;
	}
	
	public void setEndDt(String endDt) {
	    this.endDt = endDt;
	}



	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}