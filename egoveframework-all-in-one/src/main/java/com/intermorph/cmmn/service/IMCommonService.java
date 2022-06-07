/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.service;

import java.util.List;

import egovframework.com.cmm.service.CmmnDetailCode;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.service
 * @File    : IMCommonService.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 2. 16
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
public interface IMCommonService {
	/**
	 * 코드 목록  캐쉬 처리 
	 * @param codeId
	 * @return
	 * @throws Exception
	 */
	List<CmmnDetailCode> selectCmmCodeDetail(String codeId) throws Exception;
	/**
	 * 코드 목록  캐쉬 처리 
	 * @param codeId
	 * @return
	 * @throws Exception
	 */
	List<CmmnDetailCode> selectCmmCodeDetailSort(String codeId,Long sort) throws Exception;
	/**
	 *    코드 상세 
	 * @param codeId
	 * @param code
	 * @return
	 * @throws Exception
	 */
	CmmnDetailCode selectCmmCodeOneDetail(String codeId,String code) throws Exception;
	/**
	 *  코드 이름 
	 * @param codeId
	 * @param code
	 * @return
	 * @throws Exception
	 */
	String selectCmmCodeOneDetailNm(String codeId,String code) throws Exception;
	
}
