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
 * @File : IMMmbrEduVO.java
 * @Title : 회원학력
 * @date : 2022.03.10 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class IMMmbrEduVO extends BaseVO implements Serializable {

/**  ID   */
private String[] mmbrEduIds;

	/**  회원학력ID (mmbr_edu_id) */
	private String mmbrEduId;
	/**  사환자연동회원번호 (member_srl) */
	private String memberSrl;
	/**  학교명 (scl_nm) */
	private String sclNm;
	/**  학과명 (mjr_nm) */
	private String mjrNm;
	/**  전공명 (spec_nm) */
	private String specNm;
	/**  학위코드값 (dge_cdv) */
	private String dgeCdv;
	/**  학위일자 (dge_ymd) */
	private String dgeYmd;
	/**  학위파일ID (dge_file_id) */
	private String dgeFileId;
	private String rechTpc;
	
	private List<FileVO>  fileList;



	public String[] getMmbrEduIds() {
		if(this.mmbrEduIds !=null){
			String[] tempData = new String[this.mmbrEduIds.length];
			System.arraycopy(this.mmbrEduIds , 0, tempData, 0, this.mmbrEduIds.length);
			return tempData;
		}else{
			return null;
		}
	}

	public void setMmbrEduIds(String[] mmbrEduIds) {
		if (mmbrEduIds != null) {
			this.mmbrEduIds = new String[mmbrEduIds.length];
			System.arraycopy(mmbrEduIds, 0, this.mmbrEduIds, 0, mmbrEduIds.length);
		} else {
			this.mmbrEduIds = null;
		}
	}
	

	public String getMmbrEduId() {
	    return mmbrEduId;
	}
	
	public void setMmbrEduId(String mmbrEduId) {
	    this.mmbrEduId = mmbrEduId;
	}
	public String getMemberSrl() {
	    return memberSrl;
	}
	
	public void setMemberSrl(String memberSrl) {
	    this.memberSrl = memberSrl;
	}
	public String getSclNm() {
	    return sclNm;
	}
	
	public void setSclNm(String sclNm) {
	    this.sclNm = sclNm;
	}
	public String getMjrNm() {
	    return mjrNm;
	}
	
	public void setMjrNm(String mjrNm) {
	    this.mjrNm = mjrNm;
	}
	public String getSpecNm() {
	    return specNm;
	}
	
	public void setSpecNm(String specNm) {
	    this.specNm = specNm;
	}
	public String getDgeCdv() {
	    return dgeCdv;
	}
	
	public void setDgeCdv(String dgeCdv) {
	    this.dgeCdv = dgeCdv;
	}
	public String getDgeYmd() {
	    return dgeYmd;
	}
	
	public void setDgeYmd(String dgeYmd) {
	    this.dgeYmd = dgeYmd;
	}
	public String getDgeFileId() {
	    return dgeFileId;
	}
	
	public void setDgeFileId(String dgeFileId) {
	    this.dgeFileId = dgeFileId;
	}



	public List<FileVO> getFileList() {
		return fileList;
	}

	public void setFileList(List<FileVO> fileList) {
		this.fileList = fileList;
	}

	public String getRechTpc() {
		return rechTpc;
	}

	public void setRechTpc(String rechTpc) {
		this.rechTpc = rechTpc;
	}

	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}