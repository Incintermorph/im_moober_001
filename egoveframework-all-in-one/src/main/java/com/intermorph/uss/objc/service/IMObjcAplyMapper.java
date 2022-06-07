/*
 *  * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.objc.service;

import java.util.List;


import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BaseResultSet;


import egovframework.rte.psl.dataaccess.mapper.Mapper;



/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.objc.mapper
 * @File : IMObjcAplyMapper.java
 * @Title : 이의신청
 * @date : 2022.04.06 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Mapper ("IMObjcAplyMapper")
public interface IMObjcAplyMapper {
    /**
     * 등록
     * 
     * @param vo
     * @return int
     */
    int insert(IMObjcAplyVO vo);

    /**
     * 수정
     * 
     * @param vo
     * @return int
     */
    int update(IMObjcAplyVO vo);

    /**
     * 삭제
     * 
     * @param vo
     * @return int
     */
    int delete(IMObjcAplyVO vo);

	
	/**
	 * 상세정보 단껀 
	 * @param vo
	 * @return
	 */
	BaseResultSet selectDetail(IMObjcAplyVO vo);
	
    
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