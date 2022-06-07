/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.cmmn.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.intermorph.cmmn.base.BaseVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.vo
 * @File : IMCmmnSttsVO.java
 * @Title : 공통상태정보
 * @date : 2022.02.28 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class IMCmmnSttsVO extends BaseVO implements Serializable {

	/**  테이블ID (tbl_id) */
	private String tblId;
	/**  테이블참조ID (tbl_ref_id) */
	private String tblRefId;
	/**  참조이름 (ref_nm) */
	private String refNm;
	/**  상태코드값 (stts_cdv) */
	private String sttsCdv;
	/**  상태비고 (stts_rmks) */
	private String sttsRmks;

	private String chageStts; // 변경 수강상태
	private String chageMsg; // 변경 메시지

	/**
	 * 공통 참조 파라미터명
	 */
	private String[] cmmnCdvRefNms;
	
	


	public String[] getCmmnCdvRefNms() {
		if(this.cmmnCdvRefNms !=null){
			String[] tempData = new String[this.cmmnCdvRefNms.length];
			System.arraycopy(this.cmmnCdvRefNms , 0, tempData, 0, this.cmmnCdvRefNms.length);
			return tempData;
		}else{
			return null;
		}
	}

	public void setCmmnCdvRefNms(String[] cmmnCdvRefNms) {
		if (cmmnCdvRefNms != null) {
			this.cmmnCdvRefNms = new String[cmmnCdvRefNms.length];
			System.arraycopy(cmmnCdvRefNms, 0, this.cmmnCdvRefNms, 0, cmmnCdvRefNms.length);
		} else {
			this.cmmnCdvRefNms = null;
		}
	}


	

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
	public String getSttsCdv() {
	    return sttsCdv;
	}
	
	public void setSttsCdv(String sttsCdv) {
	    this.sttsCdv = sttsCdv;
	}



	public String getSttsRmks() {
		return sttsRmks;
	}

	public void setSttsRmks(String sttsRmks) {
		this.sttsRmks = sttsRmks;
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

	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}