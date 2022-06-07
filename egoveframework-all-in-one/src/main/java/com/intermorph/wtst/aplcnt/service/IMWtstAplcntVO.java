/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.wtst.aplcnt.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.intermorph.cmmn.base.BaseVO;
import com.intermorph.cmmn.util.IMStringUtil;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.wtst.aplcnt.vo
 * @File : IMWtstAplcntVO.java
 * @Title : 필기시험 신청자
 * @date : 2022.04.04 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings ("serial")
public class IMWtstAplcntVO extends BaseVO implements Serializable {

	/** ID */
	private String[] wtstAplcntIds;

	/** 필기시험신청자ID (wtst_aplcnt_id) */
	private String wtstAplcntId;
	private String wtstId;
	/** 필기시험장소ID (wtst_place_id) */
	private String wtstPlaceId;
	/** 회원고유ID (mmbr_esntl_id) */
	private String mmbrEsntlId;
	/** 회원ID (mber_id) */
	private String mberId;
	/** 성별코드값 (sexdstn_cdv) */
	private String sexdstnCdv;
	/** 회원명 (mber_nm) */
	private String mberNm;
	/** 회원이메일주소 (mber_email_adres) */
	private String mberEmailAdres;
	/** 생년월일 (brdt) */
	private String brdt;
	/** 회원전화번호 (mmbr_telno) */
	private String mmbrTelno;
	private String mblTelno1;
	private String mblTelno2;
	private String mblTelno3;
	/** 사진파일ID (pht_file_id) */
	private String phtFileId;
	/** 편의증빙서류ID (conv_evddoc_id) */
	private String convEvddocId;
	/** 편의제공여부 (conv_pvsn_yn) */
	private String convPvsnYn;
	/** 면제구분코드값 (exemp_dvsn_cdv) */
	private String exempDvsnCdv;
	/** 면제증빙서류ID (exemp_evddoc_id) */
	private String exempEvddocId;

	/** 수험번호 (tktstno) */
	private String tktstno;
	/** 대기순서 (wtn_ord) */
	private Long wtnOrd;
	private Long fltpSbjCnt;
	private String avgScr;
	private String sbjRslt;
	private String passYmd;
	private String vldEndYmd;
	

	private String strScore1;
	private String strScore2;
	private String strScore3;
	private String strScore4;
	private String strScore5;
	

	/**  근무구분코드값 (work_dvsn_cdv) */
	private String workDvsnCdv;
	/**  기관코드 (agncy_code) */
	private String agncyCode;
	/**  기관명 (agncy_nm) */
	private String agncyNm;
	private int cnt;
	private String statType;
	private String modifyYn;
	
	private String batchYn;	


	private String chageStts; // 변경 수강상태
	private String chageMsg; // 변경 메시지

	public String[] getWtstAplcntIds() {
		if (this.wtstAplcntIds != null) {
			String[] tempData = new String[this.wtstAplcntIds.length];
			System.arraycopy(this.wtstAplcntIds, 0, tempData, 0, this.wtstAplcntIds.length);
			return tempData;
		} else {
			return null;
		}
	}

	public void setWtstAplcntIds(String[] wtstAplcntIds) {
		if (wtstAplcntIds != null) {
			this.wtstAplcntIds = new String[wtstAplcntIds.length];
			System.arraycopy(wtstAplcntIds, 0, this.wtstAplcntIds, 0, wtstAplcntIds.length);
		} else {
			this.wtstAplcntIds = null;
		}
	}

	public String getWtstAplcntId() {
		return wtstAplcntId;
	}

	public void setWtstAplcntId(String wtstAplcntId) {
		this.wtstAplcntId = wtstAplcntId;
	}

	public String getWtstPlaceId() {
		return wtstPlaceId;
	}

	public void setWtstPlaceId(String wtstPlaceId) {
		this.wtstPlaceId = wtstPlaceId;
	}

	public String getMmbrEsntlId() {
		return mmbrEsntlId;
	}

	public void setMmbrEsntlId(String mmbrEsntlId) {
		this.mmbrEsntlId = mmbrEsntlId;
	}

	public String getMberId() {
		return mberId;
	}

	public void setMberId(String mberId) {
		this.mberId = mberId;
	}

	public String getSexdstnCdv() {
		return sexdstnCdv;
	}

	public void setSexdstnCdv(String sexdstnCdv) {
		this.sexdstnCdv = sexdstnCdv;
	}

	public String getMberNm() {
		return mberNm;
	}

	public void setMberNm(String mberNm) {
		this.mberNm = mberNm;
	}

	public String getMberEmailAdres() {
		return mberEmailAdres;
	}

	public void setMberEmailAdres(String mberEmailAdres) {
		this.mberEmailAdres = mberEmailAdres;
	}

	public String getMmbrTelno() {
		return mmbrTelno;
	}

	public void setMmbrTelno(String mmbrTelno) {
		setMblTelno1(IMStringUtil.getPhoneNum(mmbrTelno, 0));
		setMblTelno2(IMStringUtil.getPhoneNum(mmbrTelno, 1));
		setMblTelno3(IMStringUtil.getPhoneNum(mmbrTelno, 2));
		this.mmbrTelno = mmbrTelno;
	}

	public String getMmbrTelnoDB() {
		return IMStringUtil.getPhoneNumDB(mblTelno1, mblTelno2, mblTelno3);
	}

	public String getPhtFileId() {
		return phtFileId;
	}

	public void setPhtFileId(String phtFileId) {
		this.phtFileId = phtFileId;
	}

	public String getConvEvddocId() {
		return convEvddocId;
	}

	public void setConvEvddocId(String convEvddocId) {
		this.convEvddocId = convEvddocId;
	}

	public String getConvPvsnYn() {
		return convPvsnYn;
	}

	public void setConvPvsnYn(String convPvsnYn) {
		this.convPvsnYn = convPvsnYn;
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

	public String getExempDvsnCdv() {
		return exempDvsnCdv;
	}

	public void setExempDvsnCdv(String exempDvsnCdv) {
		this.exempDvsnCdv = exempDvsnCdv;
	}

	public String getExempEvddocId() {
		return exempEvddocId;
	}

	public void setExempEvddocId(String exempEvddocId) {
		this.exempEvddocId = exempEvddocId;
	}

	public String getWtstId() {
		return wtstId;
	}

	public void setWtstId(String wtstId) {
		this.wtstId = wtstId;
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

	public String getTktstno() {
		return tktstno;
	}

	public void setTktstno(String tktstno) {
		this.tktstno = tktstno;
	}

	public Long getWtnOrd() {
		return wtnOrd;
	}

	public void setWtnOrd(Long wtnOrd) {
		this.wtnOrd = wtnOrd;
	}

	public String getMberIdMask() {
		return IMStringUtil.getIdMasking(mberId);
	}

	public String getMberNmMask() {
		return IMStringUtil.getNameMasking(mberNm);
	}

	public String getMberEmailAdresMask() {
		return IMStringUtil.getEmailMasking(mberEmailAdres);
	}

	public String getMmbrTelnoMask() {
		return IMStringUtil.getPhoneMasking(mmbrTelno);
	}

	public String getChageStts() {
		return chageStts;
	}

	public void setChageStts(String chageStts) {
		this.chageStts = chageStts;
	}

	public String getChageMsg() {
		return chageMsg;
	}

	public void setChageMsg(String chageMsg) {
		this.chageMsg = chageMsg;
	}

	public String getWorkDvsnCdv() {
		return workDvsnCdv;
	}

	public void setWorkDvsnCdv(String workDvsnCdv) {
		this.workDvsnCdv = workDvsnCdv;
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

	public Long getFltpSbjCnt() {
		return fltpSbjCnt;
	}

	public void setFltpSbjCnt(Long fltpSbjCnt) {
		this.fltpSbjCnt = fltpSbjCnt;
	}

	public String getAvgScr() {
		return avgScr;
	}

	public void setAvgScr(String avgScr) {
		this.avgScr = avgScr;
	}

	public String getSbjRslt() {
		return sbjRslt;
	}

	public void setSbjRslt(String sbjRslt) {
		this.sbjRslt = sbjRslt;
	}

	public String getPassYmd() {
		return passYmd;
	}

	public void setPassYmd(String passYmd) {
		this.passYmd = passYmd;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public String getStatType() {
		return statType;
	}

	public void setStatType(String statType) {
		this.statType = statType;
	}

	public String getVldEndYmd() {
		return vldEndYmd;
	}

	public void setVldEndYmd(String vldEndYmd) {
		this.vldEndYmd = vldEndYmd;
	}

	public String getModifyYn() {
		return modifyYn;
	}

	public void setModifyYn(String modifyYn) {
		this.modifyYn = modifyYn;
	}

	public String getBatchYn() {
		return batchYn;
	}

	public void setBatchYn(String batchYn) {
		this.batchYn = batchYn;
	}

	public String getStrScore1() {
		return strScore1;
	}

	public void setStrScore1(String strScore1) {
		this.strScore1 = strScore1;
	}

	public String getStrScore2() {
		return strScore2;
	}

	public void setStrScore2(String strScore2) {
		this.strScore2 = strScore2;
	}

	public String getStrScore3() {
		return strScore3;
	}

	public void setStrScore3(String strScore3) {
		this.strScore3 = strScore3;
	}

	public String getStrScore4() {
		return strScore4;
	}

	public void setStrScore4(String strScore4) {
		this.strScore4 = strScore4;
	}

	public String getStrScore5() {
		return strScore5;
	}

	public void setStrScore5(String strScore5) {
		this.strScore5 = strScore5;
	}

	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}