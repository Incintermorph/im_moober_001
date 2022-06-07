/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.wtst.wtst.service;

import java.util.List;


import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;


/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.wtst.wtst.service
 * @File : IMWtstService.java
 * @Title : 필기시험
 * @date : 2022.03.31 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
public interface IMWtstService {

    /**
     * 등록
     * 
     * @param IMWtstVO
     * @return int
     * @throws Exception
     */
    int insertWtst(IMWtstVO vo) throws Exception;

    /**
     * 수정
     * 
     * @param IMWtstVO
     * @return int
     * @throws Exception
     */
    int updateWtst(IMWtstVO vo) throws Exception;

    /**
     * 멀티 수정
     * 
     * @param List<IMWtstVO>
     * @return int
     * @throws Exception
     */
    int updatelistWtst(List<IMWtstVO> voList) throws Exception;

    /**
     * 삭제
     * 
     * @param IMWtstVO
     * @return int
     * @throws Exception
     */
    int deleteWtst(IMWtstVO vo) throws Exception;

    /**
     * 멀티 삭제
     * 
     * @param List<IMWtstVO>
     * @return int
     * @throws Exception
     */
    int deletelistWtst(List<IMWtstVO> voList) throws Exception;

    /**
	 * 목록 조회
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	BasePage<BaseResultSet> selectListWtst(BaseCondition condition) throws Exception;
	
	/**
	 * 상세 조회 (단건)
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	BaseResultSet selectDetailWtst(IMWtstVO vo) throws Exception;
	
	
    

  

}