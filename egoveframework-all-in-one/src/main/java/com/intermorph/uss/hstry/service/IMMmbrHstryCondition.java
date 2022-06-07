/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.uss.hstry.service;

import java.io.Serializable;

import com.intermorph.cmmn.base.BaseCondition;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.hstry.vo.condition
 * @File : IMMmbrHstryCondition.java
 * @Title : 회원이력
 * @date : 2022.03.10 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class IMMmbrHstryCondition extends BaseCondition implements Serializable {
    
	private String scAplyGrdCdv;
	private String scSttsCode;
	private String scAcqsgrdcode;

	public String getScAplyGrdCdv() {
		return scAplyGrdCdv;
	}

	public void setScAplyGrdCdv(String scAplyGrdCdv) {
		this.scAplyGrdCdv = scAplyGrdCdv;
	}

	public String getScSttsCode() {
		return scSttsCode;
	}

	public void setScSttsCode(String scSttsCode) {
		this.scSttsCode = scSttsCode;
	}

	public String getScAcqsgrdcode() {
		return scAcqsgrdcode;
	}

	public void setScAcqsgrdcode(String scAcqsgrdcode) {
		this.scAcqsgrdcode = scAcqsgrdcode;
	}
	
	
	
	
}