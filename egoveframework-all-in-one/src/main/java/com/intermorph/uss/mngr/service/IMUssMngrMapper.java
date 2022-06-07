/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.mngr.service;

import java.util.List;

import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BaseResultSet;
import com.intermorph.crs.agncy.service.IMAgncyVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.mngr.service
 * @File    : IMUssMngrMapper.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 2. 24
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
@Mapper("IMUssMngrMapper")
public interface IMUssMngrMapper {
	
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
	BaseResultSet selectDetail(IMUssMngrVO vo);
	

    /**
     * 삭제
     * 
     * @param vo
     * @return int
     */
    int delete(IMUssMngrVO vo);


}
