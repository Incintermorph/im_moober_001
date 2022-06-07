/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.exception;
/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.exception
 * @File    : IMCrsAplcntStts.java
 * @Title   : 원서 접수 신청자 상태 관리
 * @date    : 2022. 4. 5
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
public enum IMWtstAplcntStts {
	접수상태("APLY", "01"),
	시험결과("FNSH", "01"),  
	운영담당자심사("OPSECT_SRNG", "01"), 
	입금확인여부("DPST", "N");
	
	/**
	 * 상태키
	 */
	public final String sttsKey;

	/**
	 * 기본값 
	 */
	public final String defaultCode;

	IMWtstAplcntStts(final String sttsKey, final String defaultCode) {
		this.sttsKey = sttsKey;
		this.defaultCode = defaultCode;
	}
}
