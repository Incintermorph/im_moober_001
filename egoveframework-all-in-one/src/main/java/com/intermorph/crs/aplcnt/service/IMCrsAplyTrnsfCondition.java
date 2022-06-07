/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.crs.aplcnt.service;

import java.io.Serializable;

import com.intermorph.cmmn.base.BaseCondition;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.crs.aplcnt.service
 * @File : IMCrsAplyTrnsfCondition.java
 * @Title : 과정신청이관
 * @date : 2022.05.12 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class IMCrsAplyTrnsfCondition extends BaseCondition implements Serializable {
    private String scTrnsfYn;

	public String getScTrnsfYn() {
		return scTrnsfYn;
	}

	public void setScTrnsfYn(String scTrnsfYn) {
		this.scTrnsfYn = scTrnsfYn;
	}
    

}