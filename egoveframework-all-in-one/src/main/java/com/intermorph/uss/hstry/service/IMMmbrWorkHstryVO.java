/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.uss.hstry.service;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.intermorph.cmmn.base.BaseVO;

import egovframework.com.cmm.service.FileVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.hstry.vo
 * @File : IMMmbrWorkHstryVO.java
 * @Title : 회원근무이력
 * @date : 2022.03.11 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class IMMmbrWorkHstryVO extends BaseVO implements Serializable {

	/**  ID   */
	private String[] mmbrWorkHstryIds;

	/**  회원근무이력ID (mmbr_work_hstry_id) */
	private String mmbrWorkHstryId;
	/**  사환자연동회원번호 (member_srl) */
	private Long memberSrl;
	/**  근무구분이력코드값 (work_dvsn_hstry_cdv) */
	private String workDvsnHstryCdv;
	/**  기관코드 (agncy_code) */
	private String agncyCode;
	/**  기관명 (agncy_nm) */
	private String agncyNm;
	/**  교육지정여부 (edu_dsgn_yn) */
	private String eduDsgnYn;
	/**  근무시작일자 (work_bgn_ymd) */
	private String workBgnYmd;
	/**  근무종료일자 (work_end_ymd) */
	private String workEndYmd;
	/**  근무여부 (work_yn) */
	private String workYn;
	/**  직급 (pstn) */
	private String pstn;
	/**  업무대상코드값 (work_trgt_cdv) */
	private String workTrgtCdv;
	/**  기타업무 (etc_work) */
	private String etcWork;
	/**  증빙서류파일ID (evddoc_file_id) */
	private String evddocFileId;
	/**  지정파일ID (dsgn_file_id) */
	private String dsgnFileId;
	/*근무 연수 계산 정보 */
	private String diffMonth;
	private String diffYear;

	private List<FileVO>  fileList;
	private List<FileVO>  fileList2;


	public String[] getMmbrWorkHstryIds() {
		if(this.mmbrWorkHstryIds !=null){
			String[] tempData = new String[this.mmbrWorkHstryIds.length];
			System.arraycopy(this.mmbrWorkHstryIds , 0, tempData, 0, this.mmbrWorkHstryIds.length);
			return tempData;
		}else{
			return null;
		}
	}

	public void setMmbrWorkHstryIds(String[] mmbrWorkHstryIds) {
		if (mmbrWorkHstryIds != null) {
			this.mmbrWorkHstryIds = new String[mmbrWorkHstryIds.length];
			System.arraycopy(mmbrWorkHstryIds, 0, this.mmbrWorkHstryIds, 0, mmbrWorkHstryIds.length);
		} else {
			this.mmbrWorkHstryIds = null;
		}
	}
	

	public String getMmbrWorkHstryId() {
	    return mmbrWorkHstryId;
	}
	
	public void setMmbrWorkHstryId(String mmbrWorkHstryId) {
	    this.mmbrWorkHstryId = mmbrWorkHstryId;
	}
	public Long getMemberSrl() {
	    return memberSrl;
	}
	
	public void setMemberSrl(Long memberSrl) {
	    this.memberSrl = memberSrl;
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
	public String getEduDsgnYn() {
	    return eduDsgnYn;
	}
	
	public void setEduDsgnYn(String eduDsgnYn) {
	    this.eduDsgnYn = eduDsgnYn;
	}
	public String getWorkBgnYmd() {
	    return workBgnYmd;
	}
	
	public void setWorkBgnYmd(String workBgnYmd) {
	    this.workBgnYmd = workBgnYmd;
	}
	public String getWorkEndYmd() {
	    return workEndYmd;
	}
	
	public void setWorkEndYmd(String workEndYmd) {
	    this.workEndYmd = workEndYmd;
	}
	public String getWorkYn() {
	    return workYn;
	}
	
	public void setWorkYn(String workYn) {
	    this.workYn = workYn;
	}
	public String getPstn() {
	    return pstn;
	}
	
	public void setPstn(String pstn) {
	    this.pstn = pstn;
	}
	public String getWorkTrgtCdv() {
	    return workTrgtCdv;
	}
	
	public void setWorkTrgtCdv(String workTrgtCdv) {
	    this.workTrgtCdv = workTrgtCdv;
	}
	public String getEtcWork() {
	    return etcWork;
	}
	
	public void setEtcWork(String etcWork) {
	    this.etcWork = etcWork;
	}
	public String getEvddocFileId() {
	    return evddocFileId;
	}
	
	public void setEvddocFileId(String evddocFileId) {
	    this.evddocFileId = evddocFileId;
	}
	public String getDsgnFileId() {
	    return dsgnFileId;
	}
	
	public void setDsgnFileId(String dsgnFileId) {
	    this.dsgnFileId = dsgnFileId;
	}



	public String getWorkDvsnHstryCdv() {
		return workDvsnHstryCdv;
	}

	public void setWorkDvsnHstryCdv(String workDvsnHstryCdv) {
		this.workDvsnHstryCdv = workDvsnHstryCdv;
	}
	
	

	public String getDiffMonth() {
		return diffMonth;
	}

	public void setDiffMonth(String diffMonth) {
		this.diffMonth = diffMonth;
	}

	public String getDiffYear() {
		return diffYear;
	}

	public void setDiffYear(String diffYear) {
		this.diffYear = diffYear;
	}
	

	public List<FileVO> getFileList() {
		return fileList;
	}

	public void setFileList(List<FileVO> fileList) {
		this.fileList = fileList;
	}

	public List<FileVO> getFileList2() {
		return fileList2;
	}

	public void setFileList2(List<FileVO> fileList2) {
		this.fileList2 = fileList2;
	}

	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}