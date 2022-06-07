/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.qlfc.service;

import java.util.List;


import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;


/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.qlfc.service
 * @File : IMLcncHstryService.java
 * @Title : 자격증 이력
 * @date : 2022.05.04 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
public interface IMLcncHstryService {

    /**
     * 등록
     * 
     * @param IMLcncHstryVO
     * @return int
     * @throws Exception
     */
    int insertLcncHstry(IMLcncHstryVO vo) throws Exception;

    /**
     * 수정
     * 
     * @param IMLcncHstryVO
     * @return int
     * @throws Exception
     */
    int updateLcncHstry(IMLcncHstryVO vo) throws Exception;

    /**
     * 멀티 수정
     * 
     * @param List<IMLcncHstryVO>
     * @return int
     * @throws Exception
     */
    int updatelistLcncHstry(List<IMLcncHstryVO> voList) throws Exception;

    /**
     * 삭제
     * 
     * @param IMLcncHstryVO
     * @return int
     * @throws Exception
     */
    int deleteLcncHstry(IMLcncHstryVO vo) throws Exception;

    /**
     * 멀티 삭제
     * 
     * @param List<IMLcncHstryVO>
     * @return int
     * @throws Exception
     */
    int deletelistLcncHstry(List<IMLcncHstryVO> voList) throws Exception;

    /**
	 * 목록 조회
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	BasePage<BaseResultSet> selectListLcncHstry(BaseCondition condition) throws Exception;
	
	/**
	 * 상세 조회 (단건)
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	BaseResultSet selectDetailLcncHstry(IMLcncHstryVO vo) throws Exception;
	
	
    

  

}