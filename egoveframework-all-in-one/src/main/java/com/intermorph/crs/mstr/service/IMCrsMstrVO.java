/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.crs.mstr.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.intermorph.cmmn.base.BaseVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.crs.mstr.service
 * @File : IMCrsMstrVO.java
 * @Title : {간단한 프로그램의 명칭을 기록}
 * @date : 2022. 2. 8
 * @author : 노성용
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class IMCrsMstrVO extends BaseVO implements Serializable {
	/** 과정 마스터 ID */
	private String crsMstrId;
	private String[] crsMstrIds;
	/** 과정마스터명 */
	private String crsMstrNm;

	/** 과정등급코드값 */
	private String crsGrdCdv;
	/** 과정구분코드값 */
	private String crsDvsnCdv;
	/** 교육수수료 */
	private Long ttnfee;
	/** 교육시간 */
	private Long eduHrs;
	
	/** 상태코드값 */
	private String sttsCdv;
	
	public String getCrsMstrId() {
		return crsMstrId;
	}

	public void setCrsMstrId(String crsMstrId) {
		this.crsMstrId = crsMstrId;
	}

	public String getCrsMstrNm() {
		return crsMstrNm;
	}

	public void setCrsMstrNm(String crsMstrNm) {
		this.crsMstrNm = crsMstrNm;
	}

	
	
	
	public String getCrsGrdCdv() {
		return crsGrdCdv;
	}

	public void setCrsGrdCdv(String crsGrdCdv) {
		this.crsGrdCdv = crsGrdCdv;
	}

	public String getCrsDvsnCdv() {
		return crsDvsnCdv;
	}

	public void setCrsDvsnCdv(String crsDvsnCdv) {
		this.crsDvsnCdv = crsDvsnCdv;
	}

	public Long getTtnfee() {
		return ttnfee;
	}

	public void setTtnfee(Long ttnfee) {
		this.ttnfee = ttnfee;
	}

	public Long getEduHrs() {
		return eduHrs;
	}

	public void setEduHrs(Long eduHrs) {
		this.eduHrs = eduHrs;
	}	

	public String getSttsCdv() {
		return sttsCdv;
	}

	public void setSttsCdv(String sttsCdv) {
		this.sttsCdv = sttsCdv;
	}
	
	

	public String[] getCrsMstrIds() {
		if(this.crsMstrIds !=null){
			String[] tempData = new String[this.crsMstrIds.length];
			System.arraycopy(this.crsMstrIds , 0, tempData, 0, this.crsMstrIds.length);
			return tempData;
		}else{
			return null;
		}
	}

	public void setCrsMstrIds(String[] crsMstrIds) {
		if (crsMstrIds != null) {
			this.crsMstrIds = new String[crsMstrIds.length];
			System.arraycopy(crsMstrIds, 0, this.crsMstrIds, 0, crsMstrIds.length);
		} else {
			this.crsMstrIds = null;
		}
	}
	
	


	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
