/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.hstry.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;


/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.hstry.service
 * @File : IMMmbrHstryService.java
 * @Title : 회원이력
 * @date : 2022.03.10 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
public interface IMMmbrHstryService {
	
	/**
	 * 이력 등록
	 * 
	 * @param IMMmbrHstryVO
	 * @return int
	 * @throws Exception
	 */
	int insertEduAplyHstry(IMEduAplyHstryVO vo) throws Exception;

    /**
     * 등록
     * 
     * @param IMMmbrHstryVO
     * @return int
     * @throws Exception
     */
    int insertMmbrHstry(IMMmbrHstryVO vo) throws Exception;

    /**
     * 수정
     * 
     * @param IMMmbrHstryVO
     * @return int
     * @throws Exception
     */
    int updateMmbrHstry(IMMmbrHstryVO vo) throws Exception;
    
    /**
     * 수정 정보 하위 정보시에도 업데이트 
     * 
     * @param IMMmbrHstryVO
     * @return int
     * @throws Exception
     */
    int updateLastMdfcn(IMMmbrHstryVO vo) throws Exception;

    /**
     * 멀티 수정
     * 
     * @param List<IMMmbrHstryVO>
     * @return int
     * @throws Exception
     */
    int updatelistMmbrHstry(List<IMMmbrHstryVO> voList) throws Exception;

    /**
	 * 목록 조회
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	BasePage<BaseResultSet> selectListMmbrHstry(BaseCondition condition) throws Exception;
	
	/**
	 * 목록 조회
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	BasePage<BaseResultSet> selectListEduHisHstry(BaseCondition condition) throws Exception;
	
	/**
	 * 상세 조회 (단건)
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	BaseResultSet selectDetailMmbrHstry(IMMmbrHstryVO vo) throws Exception;
	
	/**
	 * 학습 이력 목록 
	 * @param memberSrl
	 * @return
	 * @throws Exception
	 */
	List<BaseResultSet> selectEduHisList(Long memberSrl,String sttsCode) throws Exception;
	
	
    
	/**
	 * 학습 이력 목록 통계
	 * @param memberSrl
	 * @return
	 * @throws Exception
	 */
	List<BaseResultSet> selectEduHisListStat(Long memberSrl) throws Exception;
	
	
	

  

}