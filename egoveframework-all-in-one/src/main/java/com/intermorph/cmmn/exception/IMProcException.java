/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.exception;

import egovframework.rte.fdl.cmmn.exception.BaseRuntimeException;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.exception
 * @File    : IMProcException.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 2. 17
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
public class IMProcException extends BaseRuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * IMProcException 생성자.
	 * 
	 * @param defaultMessage 메세지 지정
	 * @param wrappedException 원인 Exception
	 */
	public IMProcException(String message, String messageKey) {
		this.messageKey = messageKey;
		this.messageParameters = null;
		this.message = message;
		this.wrappedException = null;
	}
	/**
	 * IMProcException 생성자.
	 * 에러 정의 eum 
	 * @param erros
	 */
	public IMProcException(IMProcErrors erros) {
		this.messageKey = erros.messageKey;
		this.messageParameters = null;
		this.message = erros.message;
		this.wrappedException = null;
	}

}
