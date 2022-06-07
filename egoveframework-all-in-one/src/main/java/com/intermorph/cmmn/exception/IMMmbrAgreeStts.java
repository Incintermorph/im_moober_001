/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.exception;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.exception
 * @File    : IMMmbrAgreeStts.java
 * @Title   : 최초 로그인시 동의 받는 항목 관리
 * @date    : 2022. 3. 16
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
public enum IMMmbrAgreeStts {
	개인정보수집및이용동의("agreeYN_03_01", "Y"),
	//개인정보제3자제공동의("agreeYN_03_02", "Y"), 삭제   
	수신안내동의("agreeYN_03_03", "Y");
	
	/**
	 * 상태키
	 */
	public final String sttsKey;

	/**
	 * 에러 번들코드
	 */
	public final String defaultCode;

	IMMmbrAgreeStts(final String sttsKey, final String defaultCode) {
		this.sttsKey = sttsKey;
		this.defaultCode = defaultCode;
	}
}
