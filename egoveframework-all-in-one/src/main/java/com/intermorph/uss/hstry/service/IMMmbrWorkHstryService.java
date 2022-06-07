/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.hstry.service;

import java.util.List;


import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;


/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.hstry.service
 * @File : IMMmbrWorkHstryService.java
 * @Title : 회원근무이력
 * @date : 2022.03.11 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
public interface IMMmbrWorkHstryService {

    /**
     * 등록
     * 
     * @param IMMmbrWorkHstryVO
     * @return int
     * @throws Exception
     */
    int insertMmbrWorkHstry(IMMmbrWorkHstryVO vo) throws Exception;

    /**
     * 수정
     * 
     * @param IMMmbrWorkHstryVO
     * @return int
     * @throws Exception
     */
    int updateMmbrWorkHstry(IMMmbrWorkHstryVO vo) throws Exception;

    /**
     * 멀티 수정
     * 
     * @param List<IMMmbrWorkHstryVO>
     * @return int
     * @throws Exception
     */
    int updatelistMmbrWorkHstry(List<IMMmbrWorkHstryVO> voList) throws Exception;

    /**
     * 삭제
     * 
     * @param IMMmbrWorkHstryVO
     * @return int
     * @throws Exception
     */
    int deleteMmbrWorkHstry(IMMmbrWorkHstryVO vo) throws Exception;

    /**
     * 멀티 삭제
     * 
     * @param List<IMMmbrWorkHstryVO>
     * @return int
     * @throws Exception
     */
    int deletelistMmbrWorkHstry(List<IMMmbrWorkHstryVO> voList) throws Exception;

    /**
	 * 목록 조회
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	List<IMMmbrWorkHstryVO> selectListMmbrWorkHstry(Long memberSrl) throws Exception;
	
	/**
	 * 상세 조회 (단건)
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	IMMmbrWorkHstryVO selectDetailMmbrWorkHstry(IMMmbrWorkHstryVO vo) throws Exception;
	
	
    /**
     * 근무 연수 계산 
     * @param memberSrl
     * @return
     * @throws Exception
     */
	IMMmbrWorkHstryVO selectListDiffSum(Long memberSrl) throws Exception;
  

}