/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.exception;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.exception
 * @File    : IMWtstAgreeStts.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 4. 18
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
public enum IMWtstAgreeStts {
	개인정보수집및이용동의("agreeYN_WT_01", "Y","A"),
	//개인정보제3자제공동의("agreeYN_03_02", "Y"), 삭제   
	민감정보수집및이용동의("agreeYN_WT_02", "Y","Y");
	
	/**
	 * 상태키
	 */
	public final String sttsKey;

	/**
	 * 에러 번들코드
	 */
	public final String defaultCode;
	public final String convPvsnYn;

	IMWtstAgreeStts(final String sttsKey, final String defaultCode, final String convPvsnYn ) {
		this.sttsKey = sttsKey;
		this.defaultCode = defaultCode;
		this.convPvsnYn = convPvsnYn;
	}
}
