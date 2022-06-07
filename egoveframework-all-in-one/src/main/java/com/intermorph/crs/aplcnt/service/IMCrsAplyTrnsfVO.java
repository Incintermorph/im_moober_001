/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.crs.aplcnt.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.intermorph.cmmn.base.BaseVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.crs.aplcnt.service
 * @File : IMCrsAplyTrnsfVO.java
 * @Title : 과정신청이관
 * @date : 2022.05.12 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class IMCrsAplyTrnsfVO extends BaseVO implements Serializable {

/**  ID   */
private String[] crsAplyTrnsfIds;

	/**  과정신청이관ID (crs_aply_trnsf_id) */
	private String crsAplyTrnsfId;
	/**  과정신청이관연번 (crs_aply_trnsf_no) */
	private String crsAplyTrnsfNo;
	/**  과정명 (crs_nm) */
	private String crsNm;
	/**  회원이름 (mmbr_nm) */
	private String mmbrNm;
	/**  회원ID (mmbr_id) */
	private String mmbrId;
	/**  전화번호 (telno) */
	private String telno;
	/**  이메일 (eml) */
	private String eml;
	/**  생년월일 (brdt) */
	private String brdt;
	/**  양성기관명 (agncy_nm) */
	private String agncyNm;
	/**  양성기관코드 (agncy_code) */
	private String agncyCode;
	/**  교육년도 (edu_year) */
	private String eduYear;
	/**  교육차수 (edu_rnd) */
	private String eduRnd;
	/**  과정등급 (crs_grd) */
	private String crsGrd;
	/**  이관여부 (trnsf_yn) */
	private String trnsfYn;
	/**  이관일자 (trnsf_ymd) */
	private String trnsfYmd;
	/**  이관신청ID (trnsf_aply_id) */
	private String trnsfAplyId;



	public String[] getCrsAplyTrnsfIds() {
		if(this.crsAplyTrnsfIds !=null){
			String[] tempData = new String[this.crsAplyTrnsfIds.length];
			System.arraycopy(this.crsAplyTrnsfIds , 0, tempData, 0, this.crsAplyTrnsfIds.length);
			return tempData;
		}else{
			return null;
		}
	}

	public void setCrsAplyTrnsfIds(String[] crsAplyTrnsfIds) {
		if (crsAplyTrnsfIds != null) {
			this.crsAplyTrnsfIds = new String[crsAplyTrnsfIds.length];
			System.arraycopy(crsAplyTrnsfIds, 0, this.crsAplyTrnsfIds, 0, crsAplyTrnsfIds.length);
		} else {
			this.crsAplyTrnsfIds = null;
		}
	}
	

	public String getCrsAplyTrnsfId() {
	    return crsAplyTrnsfId;
	}
	
	public void setCrsAplyTrnsfId(String crsAplyTrnsfId) {
	    this.crsAplyTrnsfId = crsAplyTrnsfId;
	}
	public String getCrsAplyTrnsfNo() {
	    return crsAplyTrnsfNo;
	}
	
	public void setCrsAplyTrnsfNo(String crsAplyTrnsfNo) {
	    this.crsAplyTrnsfNo = crsAplyTrnsfNo;
	}
	public String getCrsNm() {
	    return crsNm;
	}
	
	public void setCrsNm(String crsNm) {
	    this.crsNm = crsNm;
	}
	public String getMmbrNm() {
	    return mmbrNm;
	}
	
	public void setMmbrNm(String mmbrNm) {
	    this.mmbrNm = mmbrNm;
	}
	public String getMmbrId() {
	    return mmbrId;
	}
	
	public void setMmbrId(String mmbrId) {
	    this.mmbrId = mmbrId;
	}
	public String getTelno() {
	    return telno;
	}
	
	public void setTelno(String telno) {
	    this.telno = telno;
	}
	public String getEml() {
	    return eml;
	}
	
	public void setEml(String eml) {
	    this.eml = eml;
	}
	public String getBrdt() {
	    return brdt;
	}
	
	public void setBrdt(String brdt) {
	    this.brdt = brdt;
	}
	public String getAgncyNm() {
	    return agncyNm;
	}
	
	public void setAgncyNm(String agncyNm) {
	    this.agncyNm = agncyNm;
	}
	public String getAgncyCode() {
	    return agncyCode;
	}
	
	public void setAgncyCode(String agncyCode) {
	    this.agncyCode = agncyCode;
	}
	public String getEduYear() {
	    return eduYear;
	}
	
	public void setEduYear(String eduYear) {
	    this.eduYear = eduYear;
	}
	public String getEduRnd() {
	    return eduRnd;
	}
	
	public void setEduRnd(String eduRnd) {
	    this.eduRnd = eduRnd;
	}
	public String getCrsGrd() {
	    return crsGrd;
	}
	
	public void setCrsGrd(String crsGrd) {
	    this.crsGrd = crsGrd;
	}
	public String getTrnsfYn() {
	    return trnsfYn;
	}
	
	public void setTrnsfYn(String trnsfYn) {
	    this.trnsfYn = trnsfYn;
	}
	public String getTrnsfYmd() {
	    return trnsfYmd;
	}
	
	public void setTrnsfYmd(String trnsfYmd) {
	    this.trnsfYmd = trnsfYmd;
	}
	public String getTrnsfAplyId() {
	    return trnsfAplyId;
	}
	
	public void setTrnsfAplyId(String trnsfAplyId) {
	    this.trnsfAplyId = trnsfAplyId;
	}



	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}