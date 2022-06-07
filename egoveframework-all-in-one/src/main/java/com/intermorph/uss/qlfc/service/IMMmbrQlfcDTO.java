/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.qlfc.service;

import java.util.HashMap;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.qlfc.service
 * @File    : IMMmbrQlfcDTO.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 4. 19
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
public class IMMmbrQlfcDTO {
	/**  고유ID (esntl_id) */
	private String esntlId;
	/**  과정등급코드값 (crs_grd_cdv) */
	private String crsGrdCdv;
	private String lcncAplcntId;
	private String lcncEndYmd;
	public String getEsntlId() {
		return esntlId;
	}
	public void setEsntlId(String esntlId) {
		this.esntlId = esntlId;
	}
	public String getCrsGrdCdv() {
		return crsGrdCdv;
	}
	public void setCrsGrdCdv(String crsGrdCdv) {
		this.crsGrdCdv = crsGrdCdv;
	}
	public String getLcncAplcntId() {
		return lcncAplcntId;
	}
	public void setLcncAplcntId(String lcncAplcntId) {
		this.lcncAplcntId = lcncAplcntId;
	}
	public String getLcncEndYmd() {
		return lcncEndYmd;
	}
	public void setLcncEndYmd(String lcncEndYmd) {
		this.lcncEndYmd = lcncEndYmd;
	}
	
}
