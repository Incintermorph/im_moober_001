/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.cmmn.file.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.intermorph.cmmn.IMGlobals;
import com.intermorph.cmmn.base.BaseVO;
import com.intermorph.cmmn.util.IMStringUtil;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.file.service
 * @File : IMCmmnFileVO.java
 * @Title : 공통파일관리
 * @date : 2022.05.13 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings ("serial")
public class IMCmmnFileVO extends BaseVO implements Serializable {

	/** ID */
	private String[] cmmnFileIds;

	/** 공통파일ID (cmmn_file_id) */
	private String cmmnFileId;
	/** 파일ID (file_id) */
	private String fileId;
	/** 파일명 (file_nm) */
	private String fileNm;
	/** 파일주제 (file_tpc) */
	private String fileTpc;
	/** 회원접근여부 (mmbr_accss_yn) */
	private String mmbrAccssYn;
	/** 비고 (rmks) */
	private String rmks;
	/** 다운로드횟수 (dwld_cnt) */
	private String dwldCnt;

	public String[] getCmmnFileIds() {
		if (this.cmmnFileIds != null) {
			String[] tempData = new String[this.cmmnFileIds.length];
			System.arraycopy(this.cmmnFileIds, 0, tempData, 0, this.cmmnFileIds.length);
			return tempData;
		} else {
			return null;
		}
	}

	public void setCmmnFileIds(String[] cmmnFileIds) {
		if (cmmnFileIds != null) {
			this.cmmnFileIds = new String[cmmnFileIds.length];
			System.arraycopy(cmmnFileIds, 0, this.cmmnFileIds, 0, cmmnFileIds.length);
		} else {
			this.cmmnFileIds = null;
		}
	}

	public String getCmmnFileId() {
		return cmmnFileId;
	}

	public String getCmmnFileIdEnc() {
		String cmmnFileIdEnc = null;
		try {
			cmmnFileIdEnc = IMStringUtil.encryptString(cmmnFileId, IMGlobals.IM_SECURITY_KEY);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return cmmnFileIdEnc;
	}

	public void setCmmnFileId(String cmmnFileId) {
		this.cmmnFileId = cmmnFileId;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileNm() {
		return fileNm;
	}

	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}

	public String getFileTpc() {
		return fileTpc;
	}

	public void setFileTpc(String fileTpc) {
		this.fileTpc = fileTpc;
	}

	public String getMmbrAccssYn() {
		return mmbrAccssYn;
	}

	public void setMmbrAccssYn(String mmbrAccssYn) {
		this.mmbrAccssYn = mmbrAccssYn;
	}

	public String getRmks() {
		return rmks;
	}

	public void setRmks(String rmks) {
		this.rmks = rmks;
	}

	public String getDwldCnt() {
		return dwldCnt;
	}

	public void setDwldCnt(String dwldCnt) {
		this.dwldCnt = dwldCnt;
	}

	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}