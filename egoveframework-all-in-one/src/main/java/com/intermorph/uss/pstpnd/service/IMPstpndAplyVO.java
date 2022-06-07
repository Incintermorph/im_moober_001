/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.uss.pstpnd.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.intermorph.cmmn.base.BaseVO;
import com.intermorph.cmmn.util.IMStringUtil;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.pstpnd.vo
 * @File : IMPstpndAplyVO.java
 * @Title : 유예신청
 * @date : 2022.04.14 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings ("serial")
public class IMPstpndAplyVO extends BaseVO implements Serializable {

	/** ID */
	private String[] pstpndAplyIds;

	/** 유예신청ID (pstpnd_aply_id) */
	private String pstpndAplyId;
	/** 고유ID (esntl_id) */
	private String esntlId;
	/** 구분코드값 (dvsn_cdv) */
	private String dvsnCdv;
	/** 자격결과코드 (qlfc_rslt_code) */
	private String qlfcRsltCode;
	/** 자격증발급코드 (lcnc_issu_code) */
	private String lcncIssuCode;
	/** 자격증취득일자 (lcnc_acqs_ymd) */
	private String lcncAcqsYmd;
	/** 회원명 (mber_nm) */
	private String mberNm;
	/** 회원명 (mmbr_id) */
	private String mmbrId;
	/** 회원전화번호 (mmbr_telno) */
	private String mmbrTelno;
	private String mmbrTelno1;
	private String mmbrTelno2;
	private String mmbrTelno3;
	/** 생년월일 (brdt) */
	private String brdt;
	/** 대상년도 (trgt_year) */
	private String trgtYear;
	/** 유예사유코드값 (pstpnd_rsn_cdv) */
	private String pstpndRsnCdv;
	/** 유예사유 (pstpnd_rsn) */
	private String pstpndRsn;
	/** 우편번호 (zipc) */
	private String zipc;
	/** 주소 (addr) */
	private String addr;
	/** 주소상세 (addr_dtl) */
	private String addrDtl;
	/** 증빙서류ID (evddoc_id) */
	private String evddocId;
	/** 상태코드값 (stts_cdv) */
	private String sttsCdv;
	private String crsGrdCdv;
	private String lcncEndYmd;
	private String pstpndRndYmd;
	private String aplyYmd;
	private String idntyYmd;

	private String prcsRmks;

	public String[] getPstpndAplyIds() {
		if (this.pstpndAplyIds != null) {
			String[] tempData = new String[this.pstpndAplyIds.length];
			System.arraycopy(this.pstpndAplyIds, 0, tempData, 0, this.pstpndAplyIds.length);
			return tempData;
		} else {
			return null;
		}
	}

	public void setPstpndAplyIds(String[] pstpndAplyIds) {
		if (pstpndAplyIds != null) {
			this.pstpndAplyIds = new String[pstpndAplyIds.length];
			System.arraycopy(pstpndAplyIds, 0, this.pstpndAplyIds, 0, pstpndAplyIds.length);
		} else {
			this.pstpndAplyIds = null;
		}
	}

	public String getPstpndAplyId() {
		return pstpndAplyId;
	}

	public void setPstpndAplyId(String pstpndAplyId) {
		this.pstpndAplyId = pstpndAplyId;
	}

	public String getEsntlId() {
		return esntlId;
	}

	public void setEsntlId(String esntlId) {
		this.esntlId = esntlId;
	}

	public String getDvsnCdv() {
		return dvsnCdv;
	}

	public void setDvsnCdv(String dvsnCdv) {
		this.dvsnCdv = dvsnCdv;
	}

	public String getQlfcRsltCode() {
		return qlfcRsltCode;
	}

	public void setQlfcRsltCode(String qlfcRsltCode) {
		this.qlfcRsltCode = qlfcRsltCode;
	}

	public String getLcncAcqsYmd() {
		return lcncAcqsYmd;
	}

	public void setLcncAcqsYmd(String lcncAcqsYmd) {
		this.lcncAcqsYmd = lcncAcqsYmd;
	}

	public String getMberNm() {
		return mberNm;
	}

	public void setMberNm(String mberNm) {
		this.mberNm = mberNm;
	}
	public String getMmbrTelnoMask() {
		return IMStringUtil.getPhoneMasking(getMmbrTelno());
	}
	public String getMmbrTelno() {
		return mmbrTelno;
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

	public String getTrgtYear() {
		return trgtYear;
	}

	public void setTrgtYear(String trgtYear) {
		this.trgtYear = trgtYear;
	}

	public String getPstpndRsnCdv() {
		return pstpndRsnCdv;
	}

	public void setPstpndRsnCdv(String pstpndRsnCdv) {
		this.pstpndRsnCdv = pstpndRsnCdv;
	}

	public String getPstpndRsn() {
		return pstpndRsn;
	}

	public void setPstpndRsn(String pstpndRsn) {
		this.pstpndRsn = pstpndRsn;
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

	public String getEvddocId() {
		return evddocId;
	}

	public void setEvddocId(String evddocId) {
		this.evddocId = evddocId;
	}

	public String getSttsCdv() {
		return sttsCdv;
	}

	public void setSttsCdv(String sttsCdv) {
		this.sttsCdv = sttsCdv;
	}

	public String getLcncIssuCode() {
		return lcncIssuCode;
	}

	public void setLcncIssuCode(String lcncIssuCode) {
		this.lcncIssuCode = lcncIssuCode;
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

	public String getCrsGrdCdv() {
		return crsGrdCdv;
	}

	public void setCrsGrdCdv(String crsGrdCdv) {
		this.crsGrdCdv = crsGrdCdv;
	}

	public String getLcncEndYmd() {
		return lcncEndYmd;
	}

	public void setLcncEndYmd(String lcncEndYmd) {
		this.lcncEndYmd = lcncEndYmd;
	}

	public String getPstpndRndYmd() {
		return pstpndRndYmd;
	}

	public void setPstpndRndYmd(String pstpndRndYmd) {
		this.pstpndRndYmd = pstpndRndYmd;
	}


	public String getMmbrId() {
		return mmbrId;
	}

	public void setMmbrId(String mmbrId) {
		this.mmbrId = mmbrId;
	}

	public String getAplyYmd() {
		return aplyYmd;
	}

	public void setAplyYmd(String aplyYmd) {
		this.aplyYmd = aplyYmd;
	}

	public String getIdntyYmd() {
		return idntyYmd;
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

	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}