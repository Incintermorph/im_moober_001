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
 * @Package : com.intermorph.cmmn.service
 * @File : IMCmmnAccssHstryVO.java
 * @Title : 공통접근이력
 * @date : 2022.05.13 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class IMCmmnAccssHstryVO extends BaseVO implements Serializable {


	/**  공통접근이력ID (cmmn_accss_hstry_id) */
	private String cmmnAccssHstryId;
	/**  테이블ID (tbl_id) */
	private String tblId;
	/**  테이블참조ID (tbl_ref_id) */
	private String tblRefId;
	/**  참조이름 (ref_nm) */
	private String refNm;
	/**  고유ID (esntl_id) */
	private String esntlId;
	/**  회원정보 (mmbr_info) */
	private String mmbrInfo;
	/**  단말정보 (dvs_info) */
	private String dvsInfo;


	public String getCmmnAccssHstryId() {
	    return cmmnAccssHstryId;
	}
	
	public void setCmmnAccssHstryId(String cmmnAccssHstryId) {
	    this.cmmnAccssHstryId = cmmnAccssHstryId;
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
	public String getEsntlId() {
	    return esntlId;
	}
	
	public void setEsntlId(String esntlId) {
	    this.esntlId = esntlId;
	}
	public String getMmbrInfo() {
	    return mmbrInfo;
	}
	
	public void setMmbrInfo(String mmbrInfo) {
	    this.mmbrInfo = mmbrInfo;
	}
	public String getDvsInfo() {
	    return dvsInfo;
	}
	
	public void setDvsInfo(String dvsInfo) {
	    this.dvsInfo = dvsInfo;
	}



	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}