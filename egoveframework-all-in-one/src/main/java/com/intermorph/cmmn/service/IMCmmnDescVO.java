/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.service;

import java.io.Serializable;

import com.intermorph.cmmn.base.BaseVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.service
 * @File    : IMCmmnDescVO.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 2. 18
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class IMCmmnDescVO extends BaseVO implements Serializable {
	/** 테이블 ID */
	private String tblId;
	/** 참조아이디(키) */
	private String tblRefId;
	/** 참조 이름 */
	private String refNm;
	/**
	 * 공통 참조 파라미터명
	 */
	private String[] cmmnDescRefNms;
	/** 장문글 */
	private String cmmnDesc;
	public String getTblId() {
		return tblId;
	}
	public void setTblId(String tblId) {
		this.tblId = tblId;
	}
	public String getTblRefId() {
		return tblRefId;
	}
	public void setTblRefId(String tblRefId) {
		this.tblRefId = tblRefId;
	}
	public String getRefNm() {
		return refNm;
	}
	public void setRefNm(String refNm) {
		this.refNm = refNm;
	}
	public String[] getCmmnDescRefNms() {
		if(this.cmmnDescRefNms !=null){
			String[] tempData = new String[this.cmmnDescRefNms.length];
			System.arraycopy(this.cmmnDescRefNms , 0, tempData, 0, this.cmmnDescRefNms.length);
			return tempData;
		}else{
			return null;
		}
	}
	public void setCmmnDescRefNms(String[] cmmnDescRefNms) {
		if (cmmnDescRefNms != null) {
			this.cmmnDescRefNms = new String[cmmnDescRefNms.length];
			System.arraycopy(cmmnDescRefNms, 0, this.cmmnDescRefNms, 0, cmmnDescRefNms.length);
		} else {
			this.cmmnDescRefNms = null;
		}
	}
	
	public String getCmmnDesc() {
		return cmmnDesc;
	}
	public void setCmmnDesc(String cmmnDesc) {
		this.cmmnDesc = cmmnDesc;
	}
}
