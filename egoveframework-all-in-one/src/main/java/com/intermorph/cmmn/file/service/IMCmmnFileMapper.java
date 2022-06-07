/*
 *  * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.file.service;

import java.util.List;


import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BaseResultSet;


import egovframework.rte.psl.dataaccess.mapper.Mapper;



/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.file.service
 * @File : IMCmmnFileMapper.java
 * @Title : 공통파일관리
 * @date : 2022.05.13 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Mapper ("IMCmmnFileMapper")
public interface IMCmmnFileMapper {
    /**
     * 등록
     * 
     * @param vo
     * @return int
     */
    int insert(IMCmmnFileVO vo);

    /**
     * 수정
     * 
     * @param vo
     * @return int
     */
    int update(IMCmmnFileVO vo);
    
    /**
     * 다운로드 카운트 증가 
     * 
     * @param vo
     * @return int
     */
    int updateDownCnt(IMCmmnFileVO vo);

    /**
     * 삭제
     * 
     * @param vo
     * @return int
     */
    int delete(IMCmmnFileVO vo);

	
	/**
	 * 상세정보 단껀 
	 * @param vo
	 * @return
	 */
	BaseResultSet selectDetail(IMCmmnFileVO vo);
	
    
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