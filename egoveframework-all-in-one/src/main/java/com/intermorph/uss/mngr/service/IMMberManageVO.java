/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.mngr.service;

import java.io.Serializable;

import com.intermorph.cmmn.util.IMStringUtil;

import egovframework.com.uss.umt.service.MberManageVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.mngr.service
 * @File    : IMMberManageVO.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 4. 6
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class IMMberManageVO extends MberManageVO implements Serializable{
	public String getMberIdMask() {
		return IMStringUtil.getIdMasking(getMberId());
	}
	public String getMberNmMask() {
	     return IMStringUtil.getNameMasking(getMberNm());
	}
	

	public String getMberEmailAdresMask() {
		return IMStringUtil.getEmailMasking(getMberEmailAdres());
	}
	
	public String getMoblphonNoMask() {
		return IMStringUtil.getPhoneMasking(getMoblphonNo());
	}
}
