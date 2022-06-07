/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.crs.mstr.service;

import java.util.List;

import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BaseResultSet;


import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 * 
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.crs.mstr.service
 * @File : IMCrsMstrMapper.java
 * @Title : {간단한 프로그램의 명칭을 기록}
 * @date : 2022. 2. 8
 * @author : 노성용
 * @descrption : {상세한 프로그램의 용도를 기록}
 */

@Mapper("IMCrsMstrMapper")
public interface IMCrsMstrMapper {
	/**
	 * 등록
	 * 
	 * @param vo
	 * @return int
	 */
	int insert(IMCrsMstrVO vo);

	/**
	 * 수정
	 * 
	 * @param vo
	 * @return int
	 */
	int update(IMCrsMstrVO vo);

	/**
	 * 삭제
	 * 
	 * @param vo
	 * @return int
	 */
	int delete(IMCrsMstrVO vo);

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
	 * 상태 단껀 
	 * @param vo
	 * @return
	 */
	BaseResultSet selectDetail(IMCrsMstrVO vo);

}
