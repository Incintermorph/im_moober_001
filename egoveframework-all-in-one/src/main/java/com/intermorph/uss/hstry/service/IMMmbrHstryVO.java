/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.uss.hstry.service;

import java.io.Serializable;
import java.text.ParseException;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.intermorph.cmmn.base.BaseVO;
import com.intermorph.cmmn.util.IMDateUtil;
import com.intermorph.cmmn.util.IMStringUtil;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.hstry.vo
 * @File : IMMmbrHstryVO.java
 * @Title : 회원이력
 * @date : 2022.03.10 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class IMMmbrHstryVO extends BaseVO implements Serializable {

/**  ID   */
private Long[] memberSrls;

	/**  사환자연동회원번호 (member_srl) */
	private Long memberSrl;
	/**  고유ID (esntl_id) */
	private String esntlId;
	/**  회원이름 (mmbr_nm) */
	private String mmbrNm;
	/**  성별코드값 (sexdstn_cdv) */
	private String sexdstnCdv;
	/**  생년월일 (brdt) */
	private String brdt;
	/**  지역코드값 (area_cdv) */
	private String areaCdv;
	/**  지역상세 (area_dtl) */
	private String areaDtl;
	/**  휴대전화번호 (mbl_telno) */
	private String mblTelno;
	private String mblTelno1;
	private String mblTelno2;
	private String mblTelno3;
	/**  이메일 (eml) */
	private String eml;
	private String di;
	private String ci;
	/**  근무구분코드값 (work_dvsn_cdv) */
	private String workDvsnCdv;
	/**  신청등급코드값 (aply_grd_cdv) */
	private String aplyGrdCdv;
	/**  면제과목여부 (expt_sbj_yn) */
	private String exptSbjYn;
	
	/**  기관코드 (agncy_code) */
	private String agncyCode;
	/**  기관명 (agncy_nm) */
	private String agncyNm;
	private String trnsfYn;
	private String trnsfDt;
	
	
	private Long diffDay;



	public String[] getMemberSrls() {
		if(this.memberSrls !=null){
			String[] tempData = new String[this.memberSrls.length];
			System.arraycopy(this.memberSrls , 0, tempData, 0, this.memberSrls.length);
			return tempData;
		}else{
			return null;
		}
	}

	public void setMemberSrls(Long[] memberSrls) {
		if (memberSrls != null) {
			this.memberSrls = new Long[memberSrls.length];
			System.arraycopy(memberSrls, 0, this.memberSrls, 0, memberSrls.length);
		} else {
			this.memberSrls = null;
		}
	}
	
	public Long getMemberSrl() {
		return memberSrl;
	}

	public void setMemberSrl(Long memberSrl) {
		this.memberSrl = memberSrl;
	}

	public String getEsntlId() {
	    return esntlId;
	}
	
	public void setEsntlId(String esntlId) {
	    this.esntlId = esntlId;
	}
	public String getMmbrNm() {
	    return mmbrNm;
	}
	public String getMmbrNmMask() {
	     return IMStringUtil.getNameMasking(mmbrNm);
	}
	
	public void setMmbrNm(String mmbrNm) {
	    this.mmbrNm = mmbrNm;
	}
	public String getSexdstnCdv() {
	    return sexdstnCdv;
	}
	
	public void setSexdstnCdv(String sexdstnCdv) {
	    this.sexdstnCdv = sexdstnCdv;
	}
	public String getBrdt() {
	    return brdt;
	}
	
	public String getBrdtFormat() {
		if(!IMStringUtil.isEmpty(brdt)) {
			brdt = brdt.replaceAll("[.-]", "");
			return brdt.substring(0, 4)+"-"+brdt.substring(4, 6)+"-"+brdt.substring(6, 8);
		}else {
			return brdt;
		}
	}
	
	public void setBrdt(String brdt) {
	    this.brdt = brdt;
	}
	public String getAreaCdv() {
	    return areaCdv;
	}
	
	public void setAreaCdv(String areaCdv) {
	    this.areaCdv = areaCdv;
	}
	public String getAreaDtl() {
	    return areaDtl;
	}
	
	public void setAreaDtl(String areaDtl) {
	    this.areaDtl = areaDtl;
	}
	public String getMblTelno() {
	    return mblTelno;
	}
	
	public String getMblTelnoMasking() {
		return IMStringUtil.getPhoneMasking(mblTelno);
	}
	
	public String getMblTelnoDB() {
		return IMStringUtil.getPhoneNumDB(mblTelno1, mblTelno2, mblTelno3);
	}
	
	public void setMblTelno(String mblTelno) {
		setMblTelno1(IMStringUtil.getPhoneNum(mblTelno, 0));
		setMblTelno2(IMStringUtil.getPhoneNum(mblTelno, 1));
		setMblTelno3(IMStringUtil.getPhoneNum(mblTelno, 2));
	    this.mblTelno = mblTelno;
	}
	public String getEml() {
	    return eml;
	}
	

	public String getEmlMask() {
		return IMStringUtil.getEmailMasking(eml);
	}
	
	public void setEml(String eml) {
	    this.eml = eml;
	}
	public String getWorkDvsnCdv() {
	    return workDvsnCdv;
	}
	
	public void setWorkDvsnCdv(String workDvsnCdv) {
	    this.workDvsnCdv = workDvsnCdv;
	}
	public String getAplyGrdCdv() {
	    return aplyGrdCdv;
	}
	
	public void setAplyGrdCdv(String aplyGrdCdv) {
	    this.aplyGrdCdv = aplyGrdCdv;
	}
	public String getExptSbjYn() {
	    return exptSbjYn;
	}
	
	public void setExptSbjYn(String exptSbjYn) {
	    this.exptSbjYn = exptSbjYn;
	}



	public String getMblTelno1() {
		return mblTelno1;
	}

	public void setMblTelno1(String mblTelno1) {
		this.mblTelno1 = mblTelno1;
	}

	public String getMblTelno2() {
		return mblTelno2;
	}

	public void setMblTelno2(String mblTelno2) {
		this.mblTelno2 = mblTelno2;
	}

	public String getMblTelno3() {
		return mblTelno3;
	}

	public void setMblTelno3(String mblTelno3) {
		this.mblTelno3 = mblTelno3;
	}

	public Long getDiffDay() {
		return diffDay;
	}

	public void setDiffDay(Long diffDay) {
		this.diffDay = diffDay;
	}

	

	public String getLastMdfcnDtFormat() {
		try {
			return IMDateUtil.getFormatString(getLastMdfcnDt(), "yyyy.MM.dd HH:mm:ss");
		} catch (ParseException e) {
			return "";
		}
	}
	
	
	public String getAgncyCode() {
		return agncyCode;
	}

	public void setAgncyCode(String agncyCode) {
		this.agncyCode = agncyCode;
	}

	public String getAgncyNm() {
		return agncyNm;
	}

	public void setAgncyNm(String agncyNm) {
		this.agncyNm = agncyNm;
	}

	public String getDi() {
		return di;
	}

	public void setDi(String di) {
		this.di = di;
	}

	public String getCi() {
		return ci;
	}

	public void setCi(String ci) {
		this.ci = ci;
	}

	public String getTrnsfYn() {
		return trnsfYn;
	}

	public void setTrnsfYn(String trnsfYn) {
		this.trnsfYn = trnsfYn;
	}

	public String getTrnsfDt() {
		return trnsfDt;
	}

	public void setTrnsfDt(String trnsfDt) {
		this.trnsfDt = trnsfDt;
	}

	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}