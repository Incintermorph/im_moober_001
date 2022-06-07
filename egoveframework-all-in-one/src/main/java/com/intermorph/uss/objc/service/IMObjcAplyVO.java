/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.uss.objc.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.intermorph.cmmn.base.BaseVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.objc.vo
 * @File : IMObjcAplyVO.java
 * @Title : 이의신청
 * @date : 2022.04.06 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class IMObjcAplyVO extends BaseVO implements Serializable {

/**  ID   */
private String[] objcAplyIds;

	/**  이의신청아이디 (objc_aply_id) */
	private String objcAplyId;
	/**  회원고유ID (mmbr_esntl_id) */
	private String mmbrEsntlId;
	/**  기관ID (agncy_id) */
	private String agncyId;
	/**  구분코드값 (dvsn_cdv) */
	private String dvsnCdv;
	/**  이의신청상세 (objc_aply_dtl) */
	private String objcAplyDtl;
	/**  신청명 (aply_nm) */
	private String aplyNm;
	/**  신청내용 (aply_desc) */
	private String aplyDesc;
	/**  이의구분코드값 (objc_dvsn_cdv) */
	private String objcDvsnCdv;
	/**  상태코드값 (stts_cdv) */
	private String sttsCdv;
	/**  답변내용 (ans_desc) */
	private String ansDesc;
	/**  답변등록자ID (ans_reger_id) */
	private String ansRegerId;
	/**  답변등록일자 (ans_reg_ymd) */
	private String ansRegYmd;



	public String[] getObjcAplyIds() {
		if(this.objcAplyIds !=null){
			String[] tempData = new String[this.objcAplyIds.length];
			System.arraycopy(this.objcAplyIds , 0, tempData, 0, this.objcAplyIds.length);
			return tempData;
		}else{
			return null;
		}
	}

	public void setObjcAplyIds(String[] objcAplyIds) {
		if (objcAplyIds != null) {
			this.objcAplyIds = new String[objcAplyIds.length];
			System.arraycopy(objcAplyIds, 0, this.objcAplyIds, 0, objcAplyIds.length);
		} else {
			this.objcAplyIds = null;
		}
	}
	

	public String getObjcAplyId() {
	    return objcAplyId;
	}
	
	public void setObjcAplyId(String objcAplyId) {
	    this.objcAplyId = objcAplyId;
	}
	public String getMmbrEsntlId() {
	    return mmbrEsntlId;
	}
	
	public void setMmbrEsntlId(String mmbrEsntlId) {
	    this.mmbrEsntlId = mmbrEsntlId;
	}
	public String getAgncyId() {
	    return agncyId;
	}
	
	public void setAgncyId(String agncyId) {
	    this.agncyId = agncyId;
	}
	public String getDvsnCdv() {
	    return dvsnCdv;
	}
	
	public void setDvsnCdv(String dvsnCdv) {
	    this.dvsnCdv = dvsnCdv;
	}
	public String getObjcAplyDtl() {
	    return objcAplyDtl;
	}
	
	public void setObjcAplyDtl(String objcAplyDtl) {
	    this.objcAplyDtl = objcAplyDtl;
	}
	public String getAplyNm() {
	    return aplyNm;
	}
	
	public void setAplyNm(String aplyNm) {
	    this.aplyNm = aplyNm;
	}
	public String getAplyDesc() {
	    return aplyDesc;
	}
	
	public void setAplyDesc(String aplyDesc) {
	    this.aplyDesc = aplyDesc;
	}
	public String getObjcDvsnCdv() {
	    return objcDvsnCdv;
	}
	
	public void setObjcDvsnCdv(String objcDvsnCdv) {
	    this.objcDvsnCdv = objcDvsnCdv;
	}
	public String getSttsCdv() {
	    return sttsCdv;
	}
	
	public void setSttsCdv(String sttsCdv) {
	    this.sttsCdv = sttsCdv;
	}
	public String getAnsDesc() {
	    return ansDesc;
	}
	
	public void setAnsDesc(String ansDesc) {
	    this.ansDesc = ansDesc;
	}
	public String getAnsRegerId() {
	    return ansRegerId;
	}
	
	public void setAnsRegerId(String ansRegerId) {
	    this.ansRegerId = ansRegerId;
	}
	public String getAnsRegYmd() {
	    return ansRegYmd;
	}
	
	public void setAnsRegYmd(String ansRegYmd) {
	    this.ansRegYmd = ansRegYmd;
	}



	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}