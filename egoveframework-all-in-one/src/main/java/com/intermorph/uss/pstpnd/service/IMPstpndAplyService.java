/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.pstpnd.service;

import java.util.List;


import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;


/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.pstpnd.service
 * @File : IMPstpndAplyService.java
 * @Title : 유예신청
 * @date : 2022.04.14 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
public interface IMPstpndAplyService {

    /**
     * 등록
     * 
     * @param IMPstpndAplyVO
     * @return int
     * @throws Exception
     */
    int insertPstpndAply(IMPstpndAplyVO vo) throws Exception;

    /**
     * 수정
     * 
     * @param IMPstpndAplyVO
     * @return int
     * @throws Exception
     */
    int updatePstpndAply(IMPstpndAplyVO vo) throws Exception;

    /**
     * 멀티 수정
     * 
     * @param List<IMPstpndAplyVO>
     * @return int
     * @throws Exception
     */
    int updatelistPstpndAply(List<IMPstpndAplyVO> voList) throws Exception;

    /**
     * 삭제
     * 
     * @param IMPstpndAplyVO
     * @return int
     * @throws Exception
     */
    int deletePstpndAply(IMPstpndAplyVO vo) throws Exception;

    /**
     * 멀티 삭제
     * 
     * @param List<IMPstpndAplyVO>
     * @return int
     * @throws Exception
     */
    int deletelistPstpndAply(List<IMPstpndAplyVO> voList) throws Exception;

    /**
	 * 목록 조회
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	BasePage<BaseResultSet> selectListPstpndAply(BaseCondition condition) throws Exception;
	
	/**
	 * 상세 조회 (단건)
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	BaseResultSet selectDetailPstpndAply(IMPstpndAplyVO vo) throws Exception;
	
	
    

  

}