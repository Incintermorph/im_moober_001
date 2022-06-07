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
 * @File : IMMmbrEtcVO.java
 * @Title : 회원기타정보
 * @date : 2022.03.10 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class IMMmbrEtcVO extends BaseVO implements Serializable {

/**  ID   */
private String[] mmbrEtcIds;

	/**  회원기타ID (mmbr_etc_id) */
	private String mmbrEtcId;
	/**  사환자연동회원번호 (member_srl) */
	private String memberSrl;
	/**  기타항목 (etc_itm) */
	private String etcItm;
	/**  기타증빙서류ID (etc_evddoc_id) */
	private String etcEvddocId;


	private List<FileVO>  fileList;

	public String[] getMmbrEtcIds() {
		if(this.mmbrEtcIds !=null){
			String[] tempData = new String[this.mmbrEtcIds.length];
			System.arraycopy(this.mmbrEtcIds , 0, tempData, 0, this.mmbrEtcIds.length);
			return tempData;
		}else{
			return null;
		}
	}

	public void setMmbrEtcIds(String[] mmbrEtcIds) {
		if (mmbrEtcIds != null) {
			this.mmbrEtcIds = new String[mmbrEtcIds.length];
			System.arraycopy(mmbrEtcIds, 0, this.mmbrEtcIds, 0, mmbrEtcIds.length);
		} else {
			this.mmbrEtcIds = null;
		}
	}
	

	public String getMmbrEtcId() {
	    return mmbrEtcId;
	}
	
	public void setMmbrEtcId(String mmbrEtcId) {
	    this.mmbrEtcId = mmbrEtcId;
	}
	public String getMemberSrl() {
	    return memberSrl;
	}
	
	public void setMemberSrl(String memberSrl) {
	    this.memberSrl = memberSrl;
	}
	public String getEtcItm() {
	    return etcItm;
	}
	
	public void setEtcItm(String etcItm) {
	    this.etcItm = etcItm;
	}
	public String getEtcEvddocId() {
	    return etcEvddocId;
	}
	
	public void setEtcEvddocId(String etcEvddocId) {
	    this.etcEvddocId = etcEvddocId;
	}



	public List<FileVO> getFileList() {
		return fileList;
	}

	public void setFileList(List<FileVO> fileList) {
		this.fileList = fileList;
	}

	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}