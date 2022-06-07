/*
 *  * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.crs.agncy.service;

import java.util.List;


import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BaseResultSet;


import egovframework.rte.psl.dataaccess.mapper.Mapper;



/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.crs.agncy.mapper
 * @File : IMAgncyMapper.java
 * @Title : 양성기관
 * @date : 2022.02.21 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Mapper ("IMAgncyMapper")
public interface IMAgncyMapper {
    /**
     * 등록
     * 
     * @param vo
     * @return int
     */
    int insert(IMAgncyVO vo);

    /**
     * 수정
     * 
     * @param vo
     * @return int
     */
    int update(IMAgncyVO vo);

    /**
     * 삭제
     * 
     * @param vo
     * @return int
     */
    int delete(IMAgncyVO vo);

	
	/**
	 * 상세정보 단껀 
	 * @param vo
	 * @return
	 */
	BaseResultSet selectDetail(IMAgncyVO vo);
	
    
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
	 * 중복 조회 (사업자 등록 번호)
	 * @param vo
	 * @return
	 */
	int selectOverCount(IMAgncyVO vo);

}