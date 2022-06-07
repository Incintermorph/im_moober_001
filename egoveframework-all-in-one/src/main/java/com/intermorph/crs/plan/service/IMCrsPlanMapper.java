/*
 *  * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.crs.plan.service;

import java.util.List;


import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BaseResultSet;


import egovframework.rte.psl.dataaccess.mapper.Mapper;



/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.crs.plan.mapper
 * @File : IMCrsPlanMapper.java
 * @Title : 과정계획
 * @date : 2022.03.01 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Mapper ("IMCrsPlanMapper")
public interface IMCrsPlanMapper {
    /**
     * 등록
     * 
     * @param vo
     * @return int
     */
    int insert(IMCrsPlanVO vo);

    /**
     * 수정
     * 
     * @param vo
     * @return int
     */
    int update(IMCrsPlanVO vo);

    /**
     * 삭제
     * 
     * @param vo
     * @return int
     */
    int delete(IMCrsPlanVO vo);

	
	/**
	 * 상세정보 단껀 
	 * @param vo
	 * @return
	 */
	BaseResultSet selectDetail(IMCrsPlanVO vo);
	
    
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

}