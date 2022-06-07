/*
 *  * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.service;

import java.util.List;


import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BaseResultSet;


import egovframework.rte.psl.dataaccess.mapper.Mapper;



/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.mapper
 * @File : IMCmmnSttsMapper.java
 * @Title : 공통상태정보
 * @date : 2022.02.28 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Mapper ("IMCmmnSttsMapper")
public interface IMCmmnSttsMapper {
    /**
     * 등록
     * 
     * @param vo
     * @return int
     */
    int insert(IMCmmnSttsVO vo);
    /**
     * 이력 등록 
     * @param vo
     * @return
     */
    int insertHstry(IMCmmnSttsVO vo);

    /**
     * 수정
     * 
     * @param vo
     * @return int
     */
    int update(IMCmmnSttsVO vo);


	/**
	 * 저장된  상태 목록 리스트 
	 * @param condition
	 * @return
	 */
	List<IMCmmnSttsVO> selectList(IMCmmnSttsVO vo);
	/**
	 * 목록 검색 조회
	 * @param condition
	 * @return
	 */
	List<BaseResultSet> selectListHstry(BaseCondition condition);

    /**
	 * 검색 조회 수
	 * 
	 * @param condition
	 * @return
	 */
	int selectListHstryCount(BaseCondition condition);
	
	

}