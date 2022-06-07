/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;
import com.intermorph.cmmn.base.BaseVO;


/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.service
 * @File : IMCmmnSttsService.java
 * @Title : 공통상태정보
 * @date : 2022.02.28 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
public interface IMCmmnSttsService {

	/**
	 * 멀티 등록
	 * 
	 * @param List<IMCmmnDtVO>
	 * @return int
	 * @throws Exception
	 */
	int insertlistCmmnStts(String tblId, String tblRefId, HttpServletRequest req, IMCmmnSttsVO vo) throws Exception;
	
    /**
     * 단껀  상태 수정  
     * @param tblId
     * @param tblRefId
     * @param refNm
     * @param sttsCdv
     * @param auditVO
     * @return
     * @throws Exception
     */
	int updateCmmnStts(String tblId, String tblRefId,String refNm, String sttsCdv,BaseVO auditVO)
			throws Exception;
	
	
	/**
	 * 단껀 수정 
	 * @param tblId
	 * @param tblRefId
	 * @param refNm
	 * @param sttsCdv
	 * @param sttsRmks
	 * @param auditVO
	 * @return
	 * @throws Exception
	 */
	int updateCmmnStts(String tblId, String tblRefId,String refNm, String sttsCdv,String sttsRmks,BaseVO auditVO)
			throws Exception;
	
	/**
	 * 멀티 수정
	 * 
	 * @param List<IMCmmnDtVO>
	 * @return int
	 * @throws Exception
	 */
	int updatelistCmmnStts(String tblId, String tblRefId, HttpServletRequest req, IMCmmnSttsVO vo) throws Exception;

	/**
	 * 상태 목록
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	List<IMCmmnSttsVO> selectListCmmnStts(IMCmmnSttsVO vo) throws Exception;
	/**
	 * 상태 목록
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	List<IMCmmnSttsVO> selectListCmmnStts(String tblId, String tblRefId) throws Exception;

	/**
	 * 상태 목록 Map
	 * 
	 * @param tblId
	 * @param tblRefId
	 * @return
	 * @throws Exception
	 */
	HashMap<String, String> selectListCmmnSttsResultMap(String tblId, String tblRefId) throws Exception;
	
    
    
	/**
	 * 상태 목록 Map (객체맵)
	 * 
	 * @param tblId
	 * @param tblRefId
	 * @return
	 * @throws Exception
	 */
	HashMap<String, IMCmmnSttsVO> selectListCmmnSttsResultObjectMap(String tblId, String tblRefId) throws Exception;
	
	

    /**
	 * 목록 조회
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	BasePage<BaseResultSet> selectListHstryCmmnStts(BaseCondition condition) throws Exception;
	

  

}