/*
 *  * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.wtst.place.service;

import java.util.List;


import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BaseResultSet;


import egovframework.rte.psl.dataaccess.mapper.Mapper;



/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.wtst.place.mapper
 * @File : IMWtstPlaceMapper.java
 * @Title : 필기시험장소
 * @date : 2022.03.31 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Mapper ("IMWtstPlaceMapper")
public interface IMWtstPlaceMapper {
    /**
     * 등록
     * 
     * @param vo
     * @return int
     */
    int insert(IMWtstPlaceVO vo);

    /**
     * 수정
     * 
     * @param vo
     * @return int
     */
    int update(IMWtstPlaceVO vo);
    /**
     * 상태 변경 
     * @param vo
     * @return
     */
    int updatePrgrsSttsCdv(IMWtstPlaceVO vo);

    /**
     * 삭제
     * 
     * @param vo
     * @return int
     */
    int delete(IMWtstPlaceVO vo);

	
	/**
	 * 상세정보 단껀 
	 * @param vo
	 * @return
	 */
	BaseResultSet selectDetail(IMWtstPlaceVO vo);
	
    
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