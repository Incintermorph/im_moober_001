/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.egov.service;

import java.io.Serializable;

import egovframework.com.cmm.LoginVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.egov.service
 * @File    : IMLoginVO.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 2. 23
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
public class IMLoginVO extends LoginVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6443855135687965970L;

	private String groupId;
	/** 로그인 시간  */
	private String loginDateTime;
	//세션 ID
	private String sesinId;
	

	/**  신청등급코드값 (aply_grd_cdv) */
	private String aplyGrdCdv;
	
	private String di;
	

	public String getLoginDateTime() {
		return loginDateTime;
	}

	public void setLoginDateTime(String loginDateTime) {
		this.loginDateTime = loginDateTime;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getSesinId() {
		return sesinId;
	}

	public void setSesinId(String sesinId) {
		this.sesinId = sesinId;
	}

	public String getAplyGrdCdv() {
		return aplyGrdCdv;
	}

	public void setAplyGrdCdv(String aplyGrdCdv) {
		this.aplyGrdCdv = aplyGrdCdv;
	}

	public String getDi() {
		return di;
	}

	public void setDi(String di) {
		this.di = di;
	}
	
	
	
}	
