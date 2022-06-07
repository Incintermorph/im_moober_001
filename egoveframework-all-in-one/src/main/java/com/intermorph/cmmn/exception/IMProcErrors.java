/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.exception;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.exception
 * @File    : IMProcErrors.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 2. 17
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
public enum IMProcErrors {
	로그인정보없음("IM0001", "errors.im.no.logininfo"),
	파일프로세스에러("IM0002", "errors.im.fileProc"),  
	필수값없음("IM0003", "errors.im.no.essentiel"), 
	기타("IM009", "errors.im.etc"); 
	
	/**
	 * 에러 키값
	 */
	public final String messageKey;

	/**
	 * 에러 번들코드
	 */
	public final String message;

	IMProcErrors(final String message, final String messageKey) {
		this.messageKey = messageKey;
		this.message = message;
	}
}
