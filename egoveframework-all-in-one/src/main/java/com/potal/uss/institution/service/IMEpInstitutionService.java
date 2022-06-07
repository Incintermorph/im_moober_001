/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.potal.uss.institution.service;

import java.util.List;

import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;
import com.intermorph.uss.hstry.service.IMEduAplyHstryVO;


/**
 * @Project : egoveframework-all-in-one
 * @Package : com.potal.uss.institution.service
 * @File : IMEpInstitutionService.java
 * @Title : 기관(포탈)
 * @date : 2022.03.29 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
public interface IMEpInstitutionService {

    /**
     * 등록
     * 
     * @param IMEpInstitutionVO
     * @return int
     * @throws Exception
     */
    int insertEpInstitution(IMEpInstitutionVO vo) throws Exception;

    /**
     * 수정
     * 
     * @param IMEpInstitutionVO
     * @return int
     * @throws Exception
     */
    int updateEpInstitution(IMEpInstitutionVO vo) throws Exception;

    /**
	 * 목록 조회
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	BasePage<BaseResultSet> selectListEpInstitution(BaseCondition condition) throws Exception;
	
	/**
	 * 상세 조회 (단건)
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	BaseResultSet selectDetailEpInstitution(IMEpInstitutionVO vo) throws Exception;
	/**
	 * 포탈 회원 정보 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	IMEpInstitutionVO selectMemberUser(String userId)throws Exception ;
	
	
    /**
     * 포탈 교육 이력 목록 
     * @param listtime
     * @return
     * @throws Exception
     */
	List<IMEduAplyHstryVO> selectListEduAply(String listtime) throws Exception ;
  

}