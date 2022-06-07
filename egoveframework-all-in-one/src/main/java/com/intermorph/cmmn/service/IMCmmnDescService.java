/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intermorph.cmmn.base.BaseVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.service
 * @File : IMCmmnDescService.java
 * @Title : {간단한 프로그램의 명칭을 기록}
 * @date : 2022. 2. 18
 * @author : 노성용
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
public interface IMCmmnDescService {

	/**
	 * 저장된 장문 목록 리스트
	 * 
	 * @param vo
	 * @return
	 */
	List<IMCmmnDescVO> selectListCmmnDesc(IMCmmnDescVO vo) throws Exception;
	  /**
	   * 
	   * @param tblId
	   * @param tblRefId
	   * @return
	   * @throws Exception
	   */
	HashMap<String, String> selectListCmmnDescResultMap(String tblId,String tblRefId) throws Exception;
	
	
	/**
	 * 일괄 장문 글 저장 (파람값 참조) 
	 * @param tblId
	 * @param tblRefId
	 * @param res
	 * @param cmmnDesc
	 * @return
	 * @throws Exception
	 */
	int insertCmmnDesclist(String tblId,String tblRefId,  HttpServletRequest req,IMCmmnDescVO cmmnDescVO) throws Exception;
	/**
	 * 일괄 장문 글 저장 (파람값 참조) 
	 * @param tblId
	 * @param tblRefId
	 * @param res
	 * @param cmmnDesc
	 * @return
	 * @throws Exception
	 */
	int updateCmmnDesclist(String tblId,String tblRefId,  HttpServletRequest req,IMCmmnDescVO cmmnDescVO) throws Exception;
	
	/**
	 * 단껀장문등록
	 * @param tblId
	 * @param tblRefId
	 * @param refNm
	 * @param cmmnDesc
	 * @param auditVO
	 * @return
	 * @throws Exception
	 */
	int insertCmmnDesc(String tblId, String tblRefId,String refNm, String cmmnDesc,BaseVO auditVO) throws Exception;
}
