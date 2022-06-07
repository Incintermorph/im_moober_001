/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.crs.agncy.service;

import java.util.List;


import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;


/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.crs.agncy.service
 * @File : IMAgncyService.java
 * @Title : 양성기관
 * @date : 2022.02.21 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
public interface IMAgncyService {

    /**
     * 등록
     * 
     * @param IMAgncyVO
     * @return int
     * @throws Exception
     */
    int insertAgncy(IMAgncyVO vo) throws Exception;

    /**
     * 수정
     * 
     * @param IMAgncyVO
     * @return int
     * @throws Exception
     */
    int updateAgncy(IMAgncyVO vo) throws Exception;

    /**
     * 멀티 수정
     * 
     * @param List<IMAgncyVO>
     * @return int
     * @throws Exception
     */
    int updatelistAgncy(List<IMAgncyVO> voList) throws Exception;

    /**
     * 삭제
     * 
     * @param IMAgncyVO
     * @return int
     * @throws Exception
     */
    int deleteAgncy(IMAgncyVO vo) throws Exception;

    /**
     * 멀티 삭제
     * 
     * @param List<IMAgncyVO>
     * @return int
     * @throws Exception
     */
    int deletelistAgncy(List<IMAgncyVO> voList) throws Exception;

    /**
	 * 목록 조회
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	BasePage<BaseResultSet> selectListAgncy(BaseCondition condition) throws Exception;
	
	/**
	 * 목록 조회  기관 정보 목록 
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	List<BaseResultSet> selectListAgncy() throws Exception;
	
	/**
	 * 목록 조회 사용가능한 기관 정보 목록 
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	List<BaseResultSet> selectListAgncyDsgn() throws Exception;
	
	/**
	 * 장소 조회 
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	List<BaseResultSet> selectListAgncyPlace() throws Exception;
	
	/**
	 * 상세 조회 (단건)
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	BaseResultSet selectDetailAgncy(IMAgncyVO vo) throws Exception;
	
	
    
	/**
	 * 중복 조회 (사업자 등록 번호)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	int selectOverCount(IMAgncyVO vo) throws Exception;
  

}