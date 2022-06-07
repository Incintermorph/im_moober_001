/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.intermorph.cmmn.base.BaseResultSet;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.service
 * @File : IMCmmnDtService.java
 * @Title : 공통날짜
 * @date : 2022.02.28 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
public interface IMCmmnDtService {

	/**
	 * 멀티 등록
	 * 
	 * @param List<IMCmmnDtVO>
	 * @return int
	 * @throws Exception
	 */
	int insertlistCmmnDt(String tblId, String tblRefId, HttpServletRequest req, IMCmmnDtVO vo) throws Exception;

	/**
	 * 멀티 수정
	 * 
	 * @param List<IMCmmnDtVO>
	 * @return int
	 * @throws Exception
	 */
	int updatelistCmmnDt(String tblId, String tblRefId, HttpServletRequest req, IMCmmnDtVO vo) throws Exception;

	/**
	 * 날짜 목록
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	List<IMCmmnDtVO> selectListCmmnDt(IMCmmnDtVO vo) throws Exception;

	/**
	 * 날짜 목록 Map
	 * 
	 * @param tblId
	 * @param tblRefId
	 * @return
	 * @throws Exception
	 */
	HashMap<String, IMCmmnDtVO> selectListCmmnDtResultMap(String tblId, String tblRefId) throws Exception;

}