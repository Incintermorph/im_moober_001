/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.crs.plan.service;

import java.util.List;


import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;


/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.crs.plan.service
 * @File : IMCrsPlanService.java
 * @Title : 과정계획
 * @date : 2022.03.01 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
public interface IMCrsPlanService {

    /**
     * 등록
     * 
     * @param IMCrsPlanVO
     * @return int
     * @throws Exception
     */
    int insertCrsPlan(IMCrsPlanVO vo) throws Exception;

    /**
     * 수정
     * 
     * @param IMCrsPlanVO
     * @return int
     * @throws Exception
     */
    int updateCrsPlan(IMCrsPlanVO vo) throws Exception;

    /**
     * 멀티 수정
     * 
     * @param List<IMCrsPlanVO>
     * @return int
     * @throws Exception
     */
    int updatelistCrsPlan(List<IMCrsPlanVO> voList) throws Exception;

    /**
     * 삭제
     * 
     * @param IMCrsPlanVO
     * @return int
     * @throws Exception
     */
    int deleteCrsPlan(IMCrsPlanVO vo) throws Exception;

    /**
     * 멀티 삭제
     * 
     * @param List<IMCrsPlanVO>
     * @return int
     * @throws Exception
     */
    int deletelistCrsPlan(List<IMCrsPlanVO> voList) throws Exception;

    /**
	 * 목록 조회
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	BasePage<BaseResultSet> selectListCrsPlan(BaseCondition condition) throws Exception;
	
	/**
	 * 상세 조회 (단건)
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	BaseResultSet selectDetailCrsPlan(IMCrsPlanVO vo) throws Exception;
	
	
    

  

}