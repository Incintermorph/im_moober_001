/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.uss.hstry.service;

import java.io.Serializable;

import com.intermorph.cmmn.base.BaseVO;
import com.intermorph.cmmn.util.IMStringUtil;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.crs.crsMstr.vo
 * @File : IMEduAplyHstryVO.java
 * @Title : 학습이력
 * @date : 2022.03.17 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class IMEduAplyHstryVO extends BaseVO implements Serializable {
	/**  사환자연동회원번호 (member_srl) */
	private String memberSrl;
	/**  회원ID (mmbr_id) */
	private String mmbrId;
	/**  회원명 (mmbr_nm) */
	private String mmbrNm;
	/**  휴대전화번호 (mbl_telno) */
	private String mblTelno;
	/**  이메일 (eml) */
	private String eml;
	/**  생년월일 (brdt) */
	private String brdt;
	/**  접수코드 (rcpt_code) */
	private String rcptCode;
	/**  과정명 (crs_nm) */
	private String crsNm;
	/**  과정구분 (crs_dvsn) */
	private String crsDvsn;
	/**  과정구분코드명 (crs_dvsn_code_nm) */
	private String crsDvsnCodeNm;
	/**  양성기관명 (agncy_nm) */
	private String agncyNm;
	/**  양성기관코드 (agncy_code) */
	private String agncyCode;
	/**  접수일자 (rcpt_ymd) */
	private String rcptYmd;
	/**  상태 (stts) */
	private String stts;
	/**  상태코드 (stts_code) */
	private String sttsCode;
	/**  입금일자정보 (dpst_ymd_info) */
	private String dpstYmdInfo;
	/**  교육일자정보 (edu_ymd_info) */
	private String eduYmdInfo;
	/**  자격증발급번호 (lcnc_issu_code) */
	private String lcncIssuCode;
	/**  자격증코드 (lcnc_code) */
	private String lcncCode;
	/**  취득등급 (acqs_grd) */
	private String acqsGrd;
	/**  취득등급코드 (acqsgrdcode) */
	private String acqsgrdcode;
	/**  자격취득일자 (qlfc_acqs_ymd) */
	private String qlfcAcqsYmd;
	/**  발급일자 (issu_ymd) */
	private String issuYmd;
	public String getMemberSrl() {
		return memberSrl;
	}
	public void setMemberSrl(String memberSrl) {
		this.memberSrl = memberSrl;
	}
	public String getMmbrId() {
		return mmbrId;
	}
	public void setMmbrId(String mmbrId) {
		this.mmbrId = mmbrId;
	}
	public String getMmbrNm() {
		return mmbrNm;
	}
	public void setMmbrNm(String mmbrNm) {
		this.mmbrNm = mmbrNm;
	}
	public String getMblTelno() {
		return mblTelno;
	}
	public void setMblTelno(String mblTelno) {
		this.mblTelno = mblTelno;
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
	public String getRcptCode() {
		return rcptCode;
	}
	public void setRcptCode(String rcptCode) {
		this.rcptCode = rcptCode;
	}
	public String getCrsNm() {
		return crsNm;
	}
	public void setCrsNm(String crsNm) {
		this.crsNm = crsNm;
	}
	public String getCrsDvsn() {
		return crsDvsn;
	}
	public void setCrsDvsn(String crsDvsn) {
		this.crsDvsn = crsDvsn;
	}
	public String getCrsDvsnCodeNm() {
		return crsDvsnCodeNm;
	}
	public void setCrsDvsnCodeNm(String crsDvsnCodeNm) {
		this.crsDvsnCodeNm = crsDvsnCodeNm;
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
	public String getRcptYmd() {
		return rcptYmd;
	}
	public void setRcptYmd(String rcptYmd) {
		this.rcptYmd = rcptYmd;
	}
	public String getStts() {
		return stts;
	}
	public void setStts(String stts) {
		this.stts = stts;
	}
	public String getSttsCode() {
		return sttsCode;
	}
	public void setSttsCode(String sttsCode) {
		this.sttsCode = sttsCode;
	}
	public String getDpstYmdInfo() {
		return dpstYmdInfo;
	}
	public void setDpstYmdInfo(String dpstYmdInfo) {
		this.dpstYmdInfo = dpstYmdInfo;
	}
	public String getEduYmdInfo() {
		return eduYmdInfo;
	}
	public void setEduYmdInfo(String eduYmdInfo) {
		this.eduYmdInfo = eduYmdInfo;
	}
	public String getLcncIssuCode() {
		return lcncIssuCode;
	}
	public void setLcncIssuCode(String lcncIssuCode) {
		this.lcncIssuCode = lcncIssuCode;
	}
	public String getLcncCode() {
		return lcncCode;
	}
	public void setLcncCode(String lcncCode) {
		this.lcncCode = lcncCode;
	}
	public String getAcqsGrd() {
		return acqsGrd;
	}
	public void setAcqsGrd(String acqsGrd) {
		this.acqsGrd = acqsGrd;
	}
	public String getAcqsgrdcode() {
		return acqsgrdcode;
	}
	public void setAcqsgrdcode(String acqsgrdcode) {
		this.acqsgrdcode = acqsgrdcode;
	}
	public String getQlfcAcqsYmd() {
		return qlfcAcqsYmd;
	}
	public String getQlfcAcqsYmdFormat() {
		if(!IMStringUtil.isEmpty(qlfcAcqsYmd)) {
			qlfcAcqsYmd = qlfcAcqsYmd.replaceAll("[.-]", "");
			if(qlfcAcqsYmd.length()>7) {
				return qlfcAcqsYmd.substring(0, 4)+"-"+qlfcAcqsYmd.substring(4, 6)+"-"+qlfcAcqsYmd.substring(6, 8);
			}else {
				return qlfcAcqsYmd;
			}
		}else {
			return qlfcAcqsYmd;
		}
	}
	public void setQlfcAcqsYmd(String qlfcAcqsYmd) {
		this.qlfcAcqsYmd = qlfcAcqsYmd;
	}
	public String getIssuYmd() {
		return issuYmd;
	}
	public void setIssuYmd(String issuYmd) {
		this.issuYmd = issuYmd;
	}
	
	public String getMblTelnoMasking() {
		return IMStringUtil.getPhoneMasking(mblTelno);
	}
	public String getMmbrNmMask() {
	     return IMStringUtil.getNameMasking(mmbrNm);
	}
	
	public String getMmbrIdMask() {
		return IMStringUtil.getIdMasking(getMmbrId());
	}
	

	public String getEmlMask() {
		return IMStringUtil.getEmailMasking(eml);
	}
}
