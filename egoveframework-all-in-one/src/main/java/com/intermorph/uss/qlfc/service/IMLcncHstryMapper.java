/*
 *  * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.qlfc.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BaseResultSet;


import egovframework.rte.psl.dataaccess.mapper.Mapper;



/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.qlfc.service
 * @File : IMLcncHstryMapper.java
 * @Title : 자격증 이력
 * @date : 2022.05.04 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Mapper ("IMLcncHstryMapper")
public interface IMLcncHstryMapper {
    /**
     * 등록
     * 
     * @param vo
     * @return int
     */
    int insert(IMLcncHstryVO vo);

    /**
     * 수정
     * 
     * @param vo
     * @return int
     */
    int update(IMLcncHstryVO vo);

    /**
     * 삭제
     * 
     * @param vo
     * @return int
     */
    int delete(IMLcncHstryVO vo);
    
    /**
     * 삭제
     * 
     * @param vo
     * @return int
     */
    int drop(IMLcncHstryVO vo);

	
	/**
	 * 상세정보 단껀 
	 * @param vo
	 * @return
	 */
	BaseResultSet selectDetail(IMLcncHstryVO vo);
	
    
	/**
	 * 목록 검색 조회
	 * @param condition
	 * @return
	 */
	List<BaseResultSet> selectList(BaseCondition condition);

    /**
	 * 검색 조회 수
	 * 
	 * @param condition
	 * @return
	 */
	int selectListCount(BaseCondition condition);
	
	/**
	 * 마이그레이션 데이터  
	 * @param brdt
	 * @param mmbrNm
	 * @return
	 */
	List<IMLcncHstryVO> selectListUser(@Param ("brdt") String brdt,@Param ("mmbrNm") String mmbrNm);

}