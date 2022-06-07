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
 * @File : IMMmbrExptSbjVO.java
 * @Title : 회원면제과목
 * @date : 2022.03.10 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class IMMmbrExptSbjVO extends BaseVO implements Serializable {

/**  ID   */
private String[] mmbrExptSbjIds;

	/**  회원면제과목ID (mmbr_expt_sbj_id) */
	private String mmbrExptSbjId;
	/**  사환자연동회원번호 (member_srl) */
	private Long memberSrl;
	/**  인정과목코드값 (cert_sbj_cdv) */
	private String certSbjCdv;
	/**  계열과목코드값 (line_sbj_cdv) */
	private String lineSbjCdv;
	/**  학습희망여부 (study_dsr_yn) */
	private String studyDsrYn;
	/**  파일ID (file_id) */
	private String fileId;
	private String exempSbjDvsn;

	private List<FileVO>  fileList;


	public String[] getMmbrExptSbjIds() {
		if(this.mmbrExptSbjIds !=null){
			String[] tempData = new String[this.mmbrExptSbjIds.length];
			System.arraycopy(this.mmbrExptSbjIds , 0, tempData, 0, this.mmbrExptSbjIds.length);
			return tempData;
		}else{
			return null;
		}
	}

	public void setMmbrExptSbjIds(String[] mmbrExptSbjIds) {
		if (mmbrExptSbjIds != null) {
			this.mmbrExptSbjIds = new String[mmbrExptSbjIds.length];
			System.arraycopy(mmbrExptSbjIds, 0, this.mmbrExptSbjIds, 0, mmbrExptSbjIds.length);
		} else {
			this.mmbrExptSbjIds = null;
		}
	}
	

	public String getMmbrExptSbjId() {
	    return mmbrExptSbjId;
	}
	
	public void setMmbrExptSbjId(String mmbrExptSbjId) {
	    this.mmbrExptSbjId = mmbrExptSbjId;
	}
	public Long getMemberSrl() {
	    return memberSrl;
	}
	
	public void setMemberSrl(Long memberSrl) {
	    this.memberSrl = memberSrl;
	}
	public String getCertSbjCdv() {
	    return certSbjCdv;
	}
	
	public void setCertSbjCdv(String certSbjCdv) {
	    this.certSbjCdv = certSbjCdv;
	}
	public String getLineSbjCdv() {
	    return lineSbjCdv;
	}
	
	public void setLineSbjCdv(String lineSbjCdv) {
	    this.lineSbjCdv = lineSbjCdv;
	}
	public String getStudyDsrYn() {
	    return studyDsrYn;
	}
	
	public void setStudyDsrYn(String studyDsrYn) {
	    this.studyDsrYn = studyDsrYn;
	}
	public String getFileId() {
	    return fileId;
	}
	
	public void setFileId(String fileId) {
	    this.fileId = fileId;
	}



	public List<FileVO> getFileList() {
		return fileList;
	}

	public void setFileList(List<FileVO> fileList) {
		this.fileList = fileList;
	}
	
	

	public String getExempSbjDvsn() {
		return exempSbjDvsn;
	}

	public void setExempSbjDvsn(String exempSbjDvsn) {
		this.exempSbjDvsn = exempSbjDvsn;
	}

	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}