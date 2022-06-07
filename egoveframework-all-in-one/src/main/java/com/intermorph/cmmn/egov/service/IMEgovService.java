/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.egov.service;

import java.util.List;

import com.intermorph.cmmn.base.BaseResultSet;

import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.uss.olh.faq.service.FaqVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.egov.service
 * @File    : IMEgovService.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 2. 23
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
public interface IMEgovService {
	/**
	 * 권한별 메뉴 목록 캐시 처리함 (30분)
	 * @param authorCode
	 * @return
	 * @throws Exception
	 */
	List<IMEgovResultSet> selectListAuthMenuCache(String authorCode) throws Exception;
	
	/**
	 * 메인 게시물 리스트
	 * @param bardVO
	 * @return
	 * @throws Exception
	 */
	List<BoardVO> selectArticleMainList (BoardVO  bardVO) throws Exception;
	/**
	 * 메인 FAQ 리스트 
	 * @param bardVO
	 * @return
	 * @throws Exception
	 */
	List<FaqVO> selectFaqMainList (BoardVO  bardVO) throws Exception;
	
	/**
	 *  (운영 과정) 메인 노출 (  보수교육 제외)
	 * @param String 
	 * @param Long 
	 * @return
	 */
	List<BaseResultSet> selectMainListCrs(String scCrsGrdCdv,Long limitNumber)  throws Exception;
	

	
	/**
	 * 아이디 키값 확인 
	 * @param mbrId
	 * @return
	 * @throws Exception
	 */
	String  selectUserId (String mbrId) throws Exception;
}
