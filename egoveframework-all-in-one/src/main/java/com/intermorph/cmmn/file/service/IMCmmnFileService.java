/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.file.service;

import java.util.List;


import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;


/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.file.service
 * @File : IMCmmnFileService.java
 * @Title : 공통파일관리
 * @date : 2022.05.13 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
public interface IMCmmnFileService {

    /**
     * 등록
     * 
     * @param IMCmmnFileVO
     * @return int
     * @throws Exception
     */
    int insertCmmnFile(IMCmmnFileVO vo) throws Exception;

    /**
     * 수정
     * 
     * @param IMCmmnFileVO
     * @return int
     * @throws Exception
     */
    int updateCmmnFile(IMCmmnFileVO vo) throws Exception;
    
    /**
     * 수정
     * 
     * @param IMCmmnFileVO
     * @return int
     * @throws Exception
     */
    int updateCmmnFileDownCnt(IMCmmnFileVO vo) throws Exception;

    /**
     * 멀티 수정
     * 
     * @param List<IMCmmnFileVO>
     * @return int
     * @throws Exception
     */
    int updatelistCmmnFile(List<IMCmmnFileVO> voList) throws Exception;

    /**
     * 삭제
     * 
     * @param IMCmmnFileVO
     * @return int
     * @throws Exception
     */
    int deleteCmmnFile(IMCmmnFileVO vo) throws Exception;

    /**
     * 멀티 삭제
     * 
     * @param List<IMCmmnFileVO>
     * @return int
     * @throws Exception
     */
    int deletelistCmmnFile(List<IMCmmnFileVO> voList) throws Exception;

    /**
	 * 목록 조회
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	BasePage<BaseResultSet> selectListCmmnFile(BaseCondition condition) throws Exception;
	
	/**
	 * 상세 조회 (단건)
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	BaseResultSet selectDetailCmmnFile(IMCmmnFileVO vo) throws Exception;
	
	
    

  

}