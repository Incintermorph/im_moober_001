/*
 *  * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.crs.crs.service;

import java.util.List;


import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BaseResultSet;


import egovframework.rte.psl.dataaccess.mapper.Mapper;



/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.crs.crs.mapper
 * @File : IMCrsMapper.java
 * @Title : 과정(운영과정)
 * @date : 2022.02.28 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Mapper ("IMCrsMapper")
public interface IMCrsMapper {
    /**
     * 등록
     * 
     * @param vo
     * @return int
     */
    int insert(IMCrsVO vo);

    /**
     * 수정
     * 
     * @param vo
     * @return int
     */
    int update(IMCrsVO vo);
    
    /**
     * 수정
     * 
     * @param vo
     * @return int
     */
    int updatePrgrsSttsCdv(IMCrsVO vo);

    /**
     * 삭제
     * 
     * @param vo
     * @return int
     */
    int delete(IMCrsVO vo);

	
	/**
	 * 상세정보 단껀 
	 * @param vo
	 * @return
	 */
	BaseResultSet selectDetail(IMCrsVO vo);
	/**
	 * 과정 정보 (체크 정보)
	 * @param vo
	 * @return
	 */
	IMCrsVO selectDetailChkInfo(IMCrsVO vo);
	
    
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