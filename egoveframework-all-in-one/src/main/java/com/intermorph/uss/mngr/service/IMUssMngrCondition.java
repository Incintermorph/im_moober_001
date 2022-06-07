/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.mngr.service;

import java.io.Serializable;

import com.intermorph.cmmn.base.BaseCondition;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.mngr.service
 * @File    : IMUssMngrCondition.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 2. 24
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class IMUssMngrCondition extends BaseCondition implements Serializable {
	private String scEmplyrSttusCode;
	private String scAuthorCode;

	private String scAgncyId;
	
	
	public String getScAuthorCode() {
		return scAuthorCode;
	}
	
	public String getScAuthorCodeUpper() {
		if(scAuthorCode!=null) {
			return scAuthorCode.toUpperCase();
		}else {
			return scAuthorCode;
		}
	}

	public void setScAuthorCode(String scAuthorCode) {
		this.scAuthorCode = scAuthorCode;
	}

	public String getScEmplyrSttusCode() {
		return scEmplyrSttusCode;
	}

	public void setScEmplyrSttusCode(String scEmplyrSttusCode) {
		this.scEmplyrSttusCode = scEmplyrSttusCode;
	}

	public String getScAgncyId() {
		return scAgncyId;
	}

	public void setScAgncyId(String scAgncyId) {
		this.scAgncyId = scAgncyId;
	}
	

	
	
}
