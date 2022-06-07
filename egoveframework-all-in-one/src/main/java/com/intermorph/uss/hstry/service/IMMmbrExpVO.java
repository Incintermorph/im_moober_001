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
 * @File : IMMmbrExpVO.java
 * @Title : 회원경력
 * @date : 2022.03.10 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class IMMmbrExpVO extends BaseVO implements Serializable {

/**  ID   */
private String[] mmbrExpIds;

	/**  회원경력ID (mmbr_exp_id) */
	private String mmbrExpId;
	/**  사환자연동회원번호 (member_srl) */
	private String memberSrl;
	/**  업무분야코드값 (work_ctgry_cdv) */
	private String workCtgryCdv;
	/**  경력분야코드값 (exp_ctgry_cdv) */
	private String expCtgryCdv;
	/**  업무시작일자 (work_bgn_ymd) */
	private String workBgnYmd;
	/**  업무종료일자 (work_end_ymd) */
	private String workEndYmd;
	/**  업무시간 (work_hrs) */
	private String workHrs;
	/**  확인기관 (idnty_agncy) */
	private String idntyAgncy;
	/**  업무설명 (work_desc) */
	private String workDesc;
	/**  확인파일ID (idnty_file_id) */
	private String idntyFileId;
	/**  정관파일ID (aoas_file_id) */
	private String aoasFileId;



	private List<FileVO>  fileList;
	private List<FileVO>  fileList2;

	public String[] getMmbrExpIds() {
		if(this.mmbrExpIds !=null){
			String[] tempData = new String[this.mmbrExpIds.length];
			System.arraycopy(this.mmbrExpIds , 0, tempData, 0, this.mmbrExpIds.length);
			return tempData;
		}else{
			return null;
		}
	}

	public void setMmbrExpIds(String[] mmbrExpIds) {
		if (mmbrExpIds != null) {
			this.mmbrExpIds = new String[mmbrExpIds.length];
			System.arraycopy(mmbrExpIds, 0, this.mmbrExpIds, 0, mmbrExpIds.length);
		} else {
			this.mmbrExpIds = null;
		}
	}
	

	public String getMmbrExpId() {
	    return mmbrExpId;
	}
	
	public void setMmbrExpId(String mmbrExpId) {
	    this.mmbrExpId = mmbrExpId;
	}
	public String getMemberSrl() {
	    return memberSrl;
	}
	
	public void setMemberSrl(String memberSrl) {
	    this.memberSrl = memberSrl;
	}
	public String getWorkCtgryCdv() {
	    return workCtgryCdv;
	}
	
	public void setWorkCtgryCdv(String workCtgryCdv) {
	    this.workCtgryCdv = workCtgryCdv;
	}
	public String getExpCtgryCdv() {
	    return expCtgryCdv;
	}
	
	public void setExpCtgryCdv(String expCtgryCdv) {
	    this.expCtgryCdv = expCtgryCdv;
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
	public String getWorkHrs() {
	    return workHrs;
	}
	
	public void setWorkHrs(String workHrs) {
	    this.workHrs = workHrs;
	}
	public String getIdntyAgncy() {
	    return idntyAgncy;
	}
	
	public void setIdntyAgncy(String idntyAgncy) {
	    this.idntyAgncy = idntyAgncy;
	}
	public String getWorkDesc() {
	    return workDesc;
	}
	
	public void setWorkDesc(String workDesc) {
	    this.workDesc = workDesc;
	}
	public String getIdntyFileId() {
	    return idntyFileId;
	}
	
	public void setIdntyFileId(String idntyFileId) {
	    this.idntyFileId = idntyFileId;
	}
	public String getAoasFileId() {
	    return aoasFileId;
	}
	
	public void setAoasFileId(String aoasFileId) {
	    this.aoasFileId = aoasFileId;
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