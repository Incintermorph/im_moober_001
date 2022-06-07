/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.uss.qlfc.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.intermorph.cmmn.base.BaseVO;
import com.intermorph.cmmn.util.IMStringUtil;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.qlfc.service
 * @File : IMLcncHstryVO.java
 * @Title : 자격증 이력
 * @date : 2022.05.04 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings ("serial")
public class IMLcncHstryVO extends BaseVO implements Serializable {

	/** ID */
	private String[] lcncHstryIds;

	/** 자격증이력ID (lcnc_hstry_id) */
	private String lcncHstryId;
	/** 자격증연번 (lcnc_no) */
	private String lcncNo;
	/** 취득년도 (acqs_year) */
	private String acqsYear;
	/** 자격증결과코드 (lcnc_rslt_code) */
	private String lcncRsltCode;
	/** 과정등급 (crs_grd) */
	private String crsGrd;
	/** 자격증취득일시 (lcnc_acqs_dt) */
	private String lcncAcqsDt;
	/** 성별 (sexdstn) */
	private String sexdstn;
	/** 권역 (rgn) */
	private String rgn;
	/** 지역 (area) */
	private String area;
	/** 세부지역 (dtl_area) */
	private String dtlArea;
	/** 생년월일 (brdt) */
	private String brdt;
	/** 양성기관명 (agncy_nm) */
	private String agncyNm;
	/** 회원이름 (mmbr_nm) */
	private String mmbrNm;
	/** 전화번호 (telno) */
	private String telno;
	/** 이메일 (eml) */
	private String eml;
	/** 여부2018 (yn_2018) */
	private String yn_2018;
	/** 여부2019 (yn_2019) */
	private String yn_2019;
	/** 여부2020 (yn_2020) */
	private String yn_2020;
	/** 여부2021 (yn_2021) */
	private String yn_2021;
	private String yn_2022;
	/** 양성기관코드 (agncy_code) */
	private String agncyCode;
	/** 시작일시 (bgn_dt) */
	private String bgnDt;
	/** 종료일시 (end_dt) */
	private String endDt;
	/** 보수교육횟수 (cntnedu_cnt) */
	private String cntneduCnt;
	
	private String lcncEndYmd;

	public String[] getLcncHstryIds() {
		if (this.lcncHstryIds != null) {
			String[] tempData = new String[this.lcncHstryIds.length];
			System.arraycopy(this.lcncHstryIds, 0, tempData, 0, this.lcncHstryIds.length);
			return tempData;
		} else {
			return null;
		}
	}

	public void setLcncHstryIds(String[] lcncHstryIds) {
		if (lcncHstryIds != null) {
			this.lcncHstryIds = new String[lcncHstryIds.length];
			System.arraycopy(lcncHstryIds, 0, this.lcncHstryIds, 0, lcncHstryIds.length);
		} else {
			this.lcncHstryIds = null;
		}
	}

	public String getLcncHstryId() {
		return lcncHstryId;
	}

	public void setLcncHstryId(String lcncHstryId) {
		this.lcncHstryId = lcncHstryId;
	}

	public String getLcncNo() {
		return lcncNo;
	}

	public void setLcncNo(String lcncNo) {
		this.lcncNo = lcncNo;
	}

	public String getAcqsYear() {
		return acqsYear;
	}

	public void setAcqsYear(String acqsYear) {
		this.acqsYear = acqsYear;
	}

	public String getLcncRsltCode() {
		return lcncRsltCode;
	}

	public void setLcncRsltCode(String lcncRsltCode) {
		this.lcncRsltCode = lcncRsltCode;
	}

	public String getCrsGrd() {
		return crsGrd;
	}

	public void setCrsGrd(String crsGrd) {
		this.crsGrd = crsGrd;
	}

	public String getLcncAcqsDt() {
		return lcncAcqsDt;
	}

	public void setLcncAcqsDt(String lcncAcqsDt) {
		this.lcncAcqsDt = lcncAcqsDt;
	}

	public String getSexdstn() {
		return sexdstn;
	}

	public void setSexdstn(String sexdstn) {
		this.sexdstn = sexdstn;
	}

	public String getRgn() {
		return rgn;
	}

	public void setRgn(String rgn) {
		this.rgn = rgn;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDtlArea() {
		return dtlArea;
	}

	public void setDtlArea(String dtlArea) {
		this.dtlArea = dtlArea;
	}

	public String getBrdt() {
		return brdt;
	}

	public void setBrdt(String brdt) {
		this.brdt = brdt;
	}
	public String getBrdtFormat() {
		if(!IMStringUtil.isEmpty(brdt)) {
			brdt = brdt.replaceAll("[.-]", "");
			return brdt.substring(0, 4)+"-"+brdt.substring(4, 6)+"-"+brdt.substring(6, 8);
		}else {
			return brdt;
		}
	}
	
	public String getAgncyNm() {
		return agncyNm;
	}

	public void setAgncyNm(String agncyNm) {
		this.agncyNm = agncyNm;
	}

	public String getMmbrNm() {
		return mmbrNm;
	}

	public void setMmbrNm(String mmbrNm) {
		this.mmbrNm = mmbrNm;
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

	public String getYn_2018() {
		return yn_2018;
	}

	public void setYn_2018(String yn_2018) {
		this.yn_2018 = yn_2018;
	}

	public String getYn_2019() {
		return yn_2019;
	}

	public void setYn_2019(String yn_2019) {
		this.yn_2019 = yn_2019;
	}

	public String getYn_2020() {
		return yn_2020;
	}

	public void setYn_2020(String yn_2020) {
		this.yn_2020 = yn_2020;
	}

	public String getYn_2021() {
		return yn_2021;
	}

	public void setYn_2021(String yn_2021) {
		this.yn_2021 = yn_2021;
	}

	public String getAgncyCode() {
		return agncyCode;
	}

	public void setAgncyCode(String agncyCode) {
		this.agncyCode = agncyCode;
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

	public String getCntneduCnt() {
		return cntneduCnt;
	}

	public void setCntneduCnt(String cntneduCnt) {
		this.cntneduCnt = cntneduCnt;
	}


	public String getLcncEndYmd() {
		return lcncEndYmd;
	}

	public void setLcncEndYmd(String lcncEndYmd) {
		this.lcncEndYmd = lcncEndYmd;
	}

	public String getYn_2022() {
		return yn_2022;
	}

	public void setYn_2022(String yn_2022) {
		this.yn_2022 = yn_2022;
	}

	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}