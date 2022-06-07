/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.uss.issu.service;

import java.io.Serializable;
import java.text.ParseException;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.intermorph.cmmn.base.BaseVO;
import com.intermorph.cmmn.util.IMDateUtil;
import com.intermorph.cmmn.util.IMStringUtil;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.issu.vo
 * @File : IMIssuAplyVO.java
 * @Title : 발급신청
 * @date : 2022.04.14 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings ("serial")
public class IMIssuAplyVO extends BaseVO implements Serializable {

	/** ID */
	private String[] issuAplyIds;

	/** 발급신청ID (issu_aply_id) */
	private String issuAplyId;
	/** 고유ID (esntl_id) */
	private String esntlId;
	/** 발급구분코드값 (issu_dvsn_cdv) */
	private String issuDvsnCdv;
	/** 자격결과코드 (qlfc_rslt_code) */
	private String qlfcRsltCode;
	/** 회원명 (mber_nm) */
	private String mberNm;
	/** 회원전화번호 (mmbr_telno) */
	private String mmbrTelno;
	private String mmbrTelno1;
	private String mmbrTelno2;
	private String mmbrTelno3;
	/** 양성기관ID (agncy_id) */
	private String agncyId;
	/** 과정등급코드값 (crs_grd_cdv) */
	private String crsGrdCdv;
	/** 교육시작일시 (edu_bgn_dt) */
	private String eduBgnDt;
	/** 교육종료일시 (edu_end_dt) */
	private String eduEndDt;
	/** 자격증취득일자 (lcnc_acqs_ymd) */
	private String lcncAcqsYmd;
	/** 우편번호 (zipc) */
	private String zipc;
	/** 주소 (addr) */
	private String addr;
	/** 주소상세 (addr_dtl) */
	private String addrDtl;
	/** 발급사유 (issu_rsn) */
	private String issuRsn;
	/** 주민등록번호 (rrno) */
	private String rrno;
	private String rrno1;
	private String rrno2;
	/** 증빙서류ID (evddoc_id) */
	private String evddocId;
	/** 가족증빙서류ID (fmly_evddoc_id) */
	private String fmlyEvddocId;
	/** 상태코드값 (stts_cdv) */
	private String sttsCdv;
	private String aplyYmd;
	private String brdt;
	private String mmbrId;
	private String idntyYmd;
	private String prcsRmks;
	private String lcncIssuCode;

	public String[] getIssuAplyIds() {
		if (this.issuAplyIds != null) {
			String[] tempData = new String[this.issuAplyIds.length];
			System.arraycopy(this.issuAplyIds, 0, tempData, 0, this.issuAplyIds.length);
			return tempData;
		} else {
			return null;
		}
	}

	public void setIssuAplyIds(String[] issuAplyIds) {
		if (issuAplyIds != null) {
			this.issuAplyIds = new String[issuAplyIds.length];
			System.arraycopy(issuAplyIds, 0, this.issuAplyIds, 0, issuAplyIds.length);
		} else {
			this.issuAplyIds = null;
		}
	}

	public String getIssuAplyId() {
		return issuAplyId;
	}

	public void setIssuAplyId(String issuAplyId) {
		this.issuAplyId = issuAplyId;
	}

	public String getEsntlId() {
		return esntlId;
	}

	public void setEsntlId(String esntlId) {
		this.esntlId = esntlId;
	}

	public String getIssuDvsnCdv() {
		return issuDvsnCdv;
	}

	public void setIssuDvsnCdv(String issuDvsnCdv) {
		this.issuDvsnCdv = issuDvsnCdv;
	}

	public String getQlfcRsltCode() {
		return qlfcRsltCode;
	}

	public void setQlfcRsltCode(String qlfcRsltCode) {
		this.qlfcRsltCode = qlfcRsltCode;
	}

	public String getMberNm() {
		return mberNm;
	}

	public void setMberNm(String mberNm) {
		this.mberNm = mberNm;
	}

	public String getMberNmMask() {
	     return IMStringUtil.getNameMasking(mberNm);
	}
	
	public String getMmbrTelno() {
		return mmbrTelno;
	}

	public String getMmbrTelnoMask() {
		return IMStringUtil.getPhoneMasking(mmbrTelno);
	}
	
	public String getMmbrTelnoDB() {
		return IMStringUtil.getPhoneNumDB(mmbrTelno1, mmbrTelno2, mmbrTelno3);
	}

	public void setMmbrTelno(String mmbrTelno) {

		setMmbrTelno1(IMStringUtil.getPhoneNum(mmbrTelno, 0));
		setMmbrTelno2(IMStringUtil.getPhoneNum(mmbrTelno, 1));
		setMmbrTelno3(IMStringUtil.getPhoneNum(mmbrTelno, 2));
		this.mmbrTelno = mmbrTelno;
	}

	

	public String getAgncyId() {
		return agncyId;
	}

	public void setAgncyId(String agncyId) {
		this.agncyId = agncyId;
	}

	public String getCrsGrdCdv() {
		return crsGrdCdv;
	}

	public void setCrsGrdCdv(String crsGrdCdv) {
		this.crsGrdCdv = crsGrdCdv;
	}

	public String getEduBgnDt() {
		return eduBgnDt;
	}

	public void setEduBgnDt(String eduBgnDt) {
		this.eduBgnDt = eduBgnDt;
	}

	public String getEduEndDt() {
		return eduEndDt;
	}

	public void setEduEndDt(String eduEndDt) {
		this.eduEndDt = eduEndDt;
	}

	public String getLcncAcqsYmd() {
		return lcncAcqsYmd;
	}
	

	public String getLcncAcqsYmdYYYY() {
		
		try {
			return IMDateUtil.getFormatString(lcncAcqsYmd, "yyyy");
		} catch (ParseException e) {
			return null;
		}
	}
	public String getLcncAcqsYmdMM() {
		
		try {
			return IMDateUtil.getFormatString(lcncAcqsYmd, "MM");
		} catch (ParseException e) {
			return null;
		}
	}
	public String getLcncAcqsYmdDD() {
		
		try {
			return IMDateUtil.getFormatString(lcncAcqsYmd, "dd");
		} catch (ParseException e) {
			return null;
		}
	}

	public void setLcncAcqsYmd(String lcncAcqsYmd) {
		this.lcncAcqsYmd = lcncAcqsYmd;
	}

	public String getZipc() {
		return zipc;
	}

	public void setZipc(String zipc) {
		this.zipc = zipc;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getAddrDtl() {
		return addrDtl;
	}

	public void setAddrDtl(String addrDtl) {
		this.addrDtl = addrDtl;
	}

	public String getIssuRsn() {
		return issuRsn;
	}

	public void setIssuRsn(String issuRsn) {
		this.issuRsn = issuRsn;
	}

	public String getRrno() {
		return rrno;
	}
	public String getRrnoDB() {
		return IMStringUtil.getRrnoDB(rrno1, rrno2);		
	}
	
	public String getRrnoMasking() {
		return IMStringUtil.getRrnoMasking(rrno);		
	}

	public void setRrno(String rrno) {
		 setRrno1(IMStringUtil.getRrno(rrno, 0));
		 setRrno2(IMStringUtil.getRrno(rrno, 1));
		this.rrno = rrno;
	}

	public String getEvddocId() {
		return evddocId;
	}

	public void setEvddocId(String evddocId) {
		this.evddocId = evddocId;
	}

	public String getFmlyEvddocId() {
		return fmlyEvddocId;
	}

	public void setFmlyEvddocId(String fmlyEvddocId) {
		this.fmlyEvddocId = fmlyEvddocId;
	}

	public String getSttsCdv() {
		return sttsCdv;
	}

	public void setSttsCdv(String sttsCdv) {
		this.sttsCdv = sttsCdv;
	}

	public String getMmbrTelno1() {
		return mmbrTelno1;
	}

	public void setMmbrTelno1(String mmbrTelno1) {
		this.mmbrTelno1 = mmbrTelno1;
	}

	public String getMmbrTelno2() {
		return mmbrTelno2;
	}

	public void setMmbrTelno2(String mmbrTelno2) {
		this.mmbrTelno2 = mmbrTelno2;
	}

	public String getMmbrTelno3() {
		return mmbrTelno3;
	}

	public void setMmbrTelno3(String mmbrTelno3) {
		this.mmbrTelno3 = mmbrTelno3;
	}

	public String getAplyYmd() {
		return aplyYmd;
	}

	public void setAplyYmd(String aplyYmd) {
		this.aplyYmd = aplyYmd;
	}

	public String getBrdt() {
		return brdt;
	}

	public String getBrdtPrint() {
		if(!IMStringUtil.isEmpty(brdt)) {
			brdt = brdt.replaceAll("[.-]", "");
			return brdt.substring(0, 4)+"."+brdt.substring(4, 6)+"."+brdt.substring(6, 8);
		}else {
			return brdt;
		}
	}
	
	public void setBrdt(String brdt) {
		this.brdt = brdt;
	}

	public String getMmbrId() {
		return mmbrId;
	}

	public String getMmbrIdMask() {
		return IMStringUtil.getIdMasking(mmbrId);
	}

	public void setMmbrId(String mmbrId) {
		this.mmbrId = mmbrId;
	}

	public String getIdntyYmd() {
		return idntyYmd;
	}
	
	public String getIdntyYmdFormat() {
		
		try {
			return IMDateUtil.getFormatString(idntyYmd, "yyyy-MM-dd");
		} catch (ParseException e) {
			return null;
		}
	}

	public void setIdntyYmd(String idntyYmd) {
		this.idntyYmd = idntyYmd;
	}

	public String getPrcsRmks() {
		return prcsRmks;
	}

	public void setPrcsRmks(String prcsRmks) {
		this.prcsRmks = prcsRmks;
	}

	public String getRrno1() {
		return rrno1;
	}

	public void setRrno1(String rrno1) {
		this.rrno1 = rrno1;
	}

	public String getRrno2() {
		return rrno2;
	}

	public void setRrno2(String rrno2) {
		this.rrno2 = rrno2;
	}

	public String getLcncIssuCode() {
		return lcncIssuCode;
	}

	public void setLcncIssuCode(String lcncIssuCode) {
		this.lcncIssuCode = lcncIssuCode;
	}

	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}