/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.exception;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.exception
 * @File    : IMCmmnStts.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 4. 25
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
public enum IMCmmnStts {
	발급신청("ISSU_APLY", "01","ISSU_APLY"),   
	유예신청("PSTPND_APLY", "01","PSTPND_APLY");
	
	/**
	 * 상태키
	 */
	public final String sttsKey;

	/**
	 * 에러 번들코드
	 */
	public final String defaultCode;
	public final String sttsCompoent;

	IMCmmnStts(final String sttsKey, final String defaultCode, final String sttsCompoent ) {
		this.sttsKey = sttsKey;
		this.defaultCode = defaultCode;
		this.sttsCompoent = sttsCompoent;
	}
}
