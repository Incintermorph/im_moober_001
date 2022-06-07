/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.crs.mstr.service;

import java.io.Serializable;

import com.intermorph.cmmn.base.BaseResultSet;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.crs.mstr.service
 * @File    : IMCrsMstrResultSet.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 2. 8
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class IMCrsMstrResultSet extends BaseResultSet implements Serializable {

	private IMCrsMstrVO imCrsMstr;
	
	
	private String crsGrdCdvNm;
	private String crsDvsnCdvNm;

	public IMCrsMstrVO getImCrsMstr() {
		return imCrsMstr;
	}

	public void setImCrsMstr(IMCrsMstrVO imCrsMstr) {
		this.imCrsMstr = imCrsMstr;
	}

	public String getCrsGrdCdvNm() {
		return crsGrdCdvNm;
	}

	public void setCrsGrdCdvNm(String crsGrdCdvNm) {
		this.crsGrdCdvNm = crsGrdCdvNm;
	}

	public String getCrsDvsnCdvNm() {
		return crsDvsnCdvNm;
	}

	public void setCrsDvsnCdvNm(String crsDvsnCdvNm) {
		this.crsDvsnCdvNm = crsDvsnCdvNm;
	}


	
	

}
