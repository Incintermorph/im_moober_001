/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.mngr.service;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.intermorph.cmmn.util.IMStringUtil;

import java.io.Serializable;
import java.util.UUID;

import egovframework.com.uss.umt.service.UserManageVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.mngr.service
 * @File    : thisVO.java
 * @Title   : 관리자 관리 (전자정부프레임워크 참조처리)
 * @date    : 2022. 2. 24
 * @author  : 노성용
 * @descrption :
 * 관리자 관리 (전자정부프레임워크 참조처리)
 */

@SuppressWarnings("serial")
public class IMUssMngrVO extends UserManageVO implements Serializable {

	/** 멀티데이타 수정/삭제에 이용 */
	private Long[] checkIndexs;

	
	private String[] esntlIds;
	/**  회원아이디 (emplyr_id) */
	private String emplyrId;
	/**  조직ID (orgnzt_id) */
	private String orgnztId;
	/**  이름 (user_nm) */
	private String userNm;
	/**  비밀번호 (password) */
	private String password;
	/**  사원번호 (empl_no) */
	private String emplNo;
	/**  주민번호 (ihidnum) */
	private String ihidnum;
	/**  성별코드 (sexdstn_code) */
	private String sexdstnCode;
	/**  생년월일 (brthdy) */
	private String brthdy;
	/**  fax번호 (fxnum) */
	private String fxnum;
	/**  집주소 (house_adres) */
	private String houseAdres;
	/**  비밀번호  힌트 (password_hint) */
	private String passwordHint;
	/**   비밀번호정답(password_cnsr) */
	private String passwordCnsr;
	/**  집전화번호뒷자리 (house_end_telno) */
	private String houseEndTelno;
	/**  지역번호 (area_no) */
	private String areaNo;
	/**  상세주소 (detail_adres) */
	private String detailAdres;
	/**  우편번호(zip) */
	private String zip;
	/**  사무실전화번호 (offm_telno) */
	private String offmTelno;
	/**  모바일 번호 (mbtlnum) */
	private String mbtlnum;
	/**  이메일주소 (email_adres) */
	private String emailAdres;
	/**  직위명 (ofcps_nm) */
	private String ofcpsNm;
	/**  집전화번호 (house_middle_telno) */
	private String houseMiddleTelno;
	/**  그룹ID (group_id) */
	private String groupId;
	/**  소속기관코드 (pstinst_code) */
	private String pstinstCode;
	/**  사용자상태코드 (emplyr_sttus_code) */
	private String emplyrSttusCode;
	/**  고유아이디 (esntl_id) */
	private String esntlId;
	/**  인증  DN 값 (crtfc_dn_value) */
	private String crtfcDnValue;
	/**  가입일자 (sbscrb_de) */
	private String sbscrbDe;
	/**  잠김여부 (lock_at) */
	private String lockAt;
	/**  잠김회수 (lock_cnt) */
	private String lockCnt;
	/**  잠김일자 (lock_last_pnttm) */
	private String lockLastPnttm;
	/**  비밀번호 변경 일자  (chg_pwd_last_pnttm) */
	private String chgPwdLastPnttm;
	/**권한코드*/
	private String authorCode;
	
	private String permitStart;
	private String permitEnd;
	private String[] permitAcessips;

	private String usePermitYn;
	
	

	public String getUsePermitYn() {
		return usePermitYn;
	}

	public void setUsePermitYn(String usePermitYn) {
		this.usePermitYn = usePermitYn;
	}

	public String getPermitStart() {
		return permitStart;
	}

	public void setPermitStart(String permitStart) {
		this.permitStart = permitStart;
	}

	public String getPermitEnd() {
		return permitEnd;
	}

	public void setPermitEnd(String permitEnd) {
		this.permitEnd = permitEnd;
	}

	public String[] getPermitAcessips() {
		if(this.permitAcessips !=null){
			String[] tempData = new String[this.permitAcessips.length];
			System.arraycopy(this.permitAcessips , 0, tempData, 0, this.permitAcessips.length);
			return tempData;
		}else{
			return null;
		}
	}

	public void setPermitAcessips(String[] permitAcessips) {
		if (permitAcessips != null) {
			this.permitAcessips = new String[permitAcessips.length];
			System.arraycopy(permitAcessips, 0, this.permitAcessips, 0, permitAcessips.length);
		} else {
			this.permitAcessips = null;
		}
	}
	
	public String[] getEsntlIds() {
		if(this.esntlIds !=null){
			String[] tempData = new String[this.esntlIds.length];
			System.arraycopy(this.esntlIds , 0, tempData, 0, this.esntlIds.length);
			return tempData;
		}else{
			return null;
		}
	}

	public void setEsntlIds(String[] esntlIds) {
		if (esntlIds != null) {
			this.esntlIds = new String[esntlIds.length];
			System.arraycopy(esntlIds, 0, this.esntlIds, 0, esntlIds.length);
		} else {
			this.esntlIds = null;
		}
	}
	

	public Long[] getCheckIndexs() {
		if(this.checkIndexs !=null){
			Long[] tempData = new Long[this.checkIndexs.length];
			System.arraycopy(this.checkIndexs , 0, tempData, 0, this.checkIndexs.length);
			return tempData;
		}else{
			return null;
		}
	}

	public void setCheckIndexs(Long[] checkIndexs) {
		if (checkIndexs != null) {
			this.checkIndexs = new Long[checkIndexs.length];
			System.arraycopy(checkIndexs, 0, this.checkIndexs, 0, checkIndexs.length);
		} else {
			this.checkIndexs = null;
		}
	}


	public String getEmplyrId() {
	    return emplyrId;
	}
	public String getEmplyrIdMask() {
		return IMStringUtil.getIdMasking(emplyrId);
	}
	
	public void setEmplyrId(String emplyrId) {
	    this.emplyrId = emplyrId;
	}
	public String getOrgnztId() {
	    return orgnztId;
	}
	
	public void setOrgnztId(String orgnztId) {
	    this.orgnztId = orgnztId;
	}
	public String getUserNm() {
	    return userNm;
	}
	public String getUserNmMask() {
	     return IMStringUtil.getNameMasking(userNm);
	}
	
	
	public void setUserNm(String userNm) {
	    this.userNm = userNm;
	}
	public String getPassword() {
	    return password;
	}
	
	public void setPassword(String password) {
	    this.password = password;
	}
	public String getEmplNo() {
	    return emplNo;
	}
	
	public void setEmplNo(String emplNo) {
	    this.emplNo = emplNo;
	}
	public String getIhidnum() {
	    return ihidnum;
	}
	
	public void setIhidnum(String ihidnum) {
	    this.ihidnum = ihidnum;
	}
	public String getSexdstnCode() {
	    return sexdstnCode;
	}
	
	public void setSexdstnCode(String sexdstnCode) {
	    this.sexdstnCode = sexdstnCode;
	}
	public String getBrthdy() {
	    return brthdy;
	}
	
	public void setBrthdy(String brthdy) {
	    this.brthdy = brthdy;
	}
	public String getFxnum() {
	    return fxnum;
	}
	
	public void setFxnum(String fxnum) {
	    this.fxnum = fxnum;
	}
	public String getHouseAdres() {
	    return houseAdres;
	}
	
	public void setHouseAdres(String houseAdres) {
	    this.houseAdres = houseAdres;
	}
	public String getPasswordHint() {
	    return passwordHint;
	}
	
	public void setPasswordHint(String passwordHint) {
	    this.passwordHint = passwordHint;
	}
	public String getPasswordCnsr() {
	    return passwordCnsr;
	}
	
	public void setPasswordCnsr(String passwordCnsr) {
	    this.passwordCnsr = passwordCnsr;
	}
	public String getHouseEndTelno() {
	    return houseEndTelno;
	}
	
	public void setHouseEndTelno(String houseEndTelno) {
	    this.houseEndTelno = houseEndTelno;
	}
	public String getAreaNo() {
	    return areaNo;
	}
	
	public void setAreaNo(String areaNo) {
	    this.areaNo = areaNo;
	}
	public String getDetailAdres() {
	    return detailAdres;
	}
	
	public void setDetailAdres(String detailAdres) {
	    this.detailAdres = detailAdres;
	}
	public String getZip() {
	    return zip;
	}
	
	public void setZip(String zip) {
	    this.zip = zip;
	}
	public String getOffmTelno() {
	    return offmTelno;
	}
	
	public void setOffmTelno(String offmTelno) {
	    this.offmTelno = offmTelno;
	}
	public String getMbtlnum() {
	    return mbtlnum;
	}
	
	public void setMbtlnum(String mbtlnum) {
	    this.mbtlnum = mbtlnum;
	}
	public String getEmailAdres() {
	    return emailAdres;
	}
	public String getEmailAdresMask() {
		return IMStringUtil.getEmailMasking(emailAdres);
	}
	
	public void setEmailAdres(String emailAdres) {
	    this.emailAdres = emailAdres;
	}
	public String getOfcpsNm() {
	    return ofcpsNm;
	}
	
	public void setOfcpsNm(String ofcpsNm) {
	    this.ofcpsNm = ofcpsNm;
	}
	public String getHouseMiddleTelno() {
	    return houseMiddleTelno;
	}
	
	public void setHouseMiddleTelno(String houseMiddleTelno) {
	    this.houseMiddleTelno = houseMiddleTelno;
	}
	public String getGroupId() {
	    return groupId;
	}
	
	public void setGroupId(String groupId) {
	    this.groupId = groupId;
	}
	public String getPstinstCode() {
	    return pstinstCode;
	}
	
	public void setPstinstCode(String pstinstCode) {
	    this.pstinstCode = pstinstCode;
	}
	public String getEmplyrSttusCode() {
	    return emplyrSttusCode;
	}
	
	public void setEmplyrSttusCode(String emplyrSttusCode) {
	    this.emplyrSttusCode = emplyrSttusCode;
	}
	public String getEsntlId() {
	    return esntlId;
	}
	
	public void setEsntlId(String esntlId) {
	    this.esntlId = esntlId;
	}
	public String getCrtfcDnValue() {
	    return crtfcDnValue;
	}
	
	public void setCrtfcDnValue(String crtfcDnValue) {
	    this.crtfcDnValue = crtfcDnValue;
	}
	public String getSbscrbDe() {
	    return sbscrbDe;
	}
	
	public void setSbscrbDe(String sbscrbDe) {
	    this.sbscrbDe = sbscrbDe;
	}
	public String getLockAt() {
	    return lockAt;
	}
	
	public void setLockAt(String lockAt) {
	    this.lockAt = lockAt;
	}
	public String getLockCnt() {
	    return lockCnt;
	}
	
	public void setLockCnt(String lockCnt) {
	    this.lockCnt = lockCnt;
	}
	public String getLockLastPnttm() {
	    return lockLastPnttm;
	}
	
	public void setLockLastPnttm(String lockLastPnttm) {
	    this.lockLastPnttm = lockLastPnttm;
	}
	public String getChgPwdLastPnttm() {
	    return chgPwdLastPnttm;
	}
	
	public void setChgPwdLastPnttm(String chgPwdLastPnttm) {
	    this.chgPwdLastPnttm = chgPwdLastPnttm;
	}

	public void chkInitData() {
		
		if ("".equals(this.getOrgnztId())) {
			this.setOrgnztId(null);
		}
		if ("".equals(this.getGroupId())) {
			this.setGroupId(null);
		}
		
		if (this.getZip()==null || "".equals(this.getZip())) {
			this.setZip("0000");
		}
		if (this.getHomeadres()==null || "".equals(this.getHomeadres())) {
			this.setHomeadres("no data");
		}
		if (this.getPasswordHint()==null || "".equals(this.getPasswordHint())) {
			this.setPasswordHint("no data");
		}
		if (this.getPasswordCnsr()==null || "".equals(this.getPasswordCnsr())) {
			this.setPasswordCnsr(UUID.randomUUID().toString());
		}
		this.setHomemiddleTelno(this.getHouseMiddleTelno());
		this.setHomeendTelno(this.getHouseEndTelno());
		this.setUniqId(this.getEsntlId());
		
	}
	
	public void copyPermitData(IMUssPermitDTO dto) {
		dto.setPermitAcessips(getPermitAcessips());
		dto.setPermitEnd(this.getPermitEnd());
		dto.setPermitStart(this.getPermitStart());
		dto.setUsePermitYn(this.getUsePermitYn());
	}

	public String getAuthorCode() {
		return authorCode;
	}

	public void setAuthorCode(String authorCode) {
		this.authorCode = authorCode;
	}

	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
