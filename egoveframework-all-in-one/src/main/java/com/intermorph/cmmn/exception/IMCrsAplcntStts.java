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
 * @Title   : 과정 수강자 상태 관리
 * @date    : 2022. 3. 3
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
public enum IMCrsAplcntStts {
	신청상태("APLY", "01"),
	수료상태("FNSH", "01"),  
	자격취득상태("QLFC", "01"), 
	기관심사("AGNCY_SRNG", "01"),
	운영담당자심사("OPSECT_SRNG", "01"), 
	입금확인여부("DPST", "N"), 
	랜덤상태("RNDM", "01"); 
	
	/**
	 * 상태키
	 */
	public final String sttsKey;

	/**
	 * 기본값
	 */
	public final String defaultCode;

	IMCrsAplcntStts(final String sttsKey, final String defaultCode) {
		this.sttsKey = sttsKey;
		this.defaultCode = defaultCode;
	}
}
