/*
 *  * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.hstry.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BaseResultSet;


import egovframework.rte.psl.dataaccess.mapper.Mapper;



/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.hstry.mapper
 * @File : IMMmbrExpMapper.java
 * @Title : 회원경력
 * @date : 2022.03.10 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Mapper ("IMMmbrExpMapper")
public interface IMMmbrExpMapper {
    /**
     * 등록
     * 
     * @param vo
     * @return int
     */
    int insert(IMMmbrExpVO vo);

    /**
     * 수정
     * 
     * @param vo
     * @return int
     */
    int update(IMMmbrExpVO vo);

    /**
     * 삭제
     * 
     * @param vo
     * @return int
     */
    int delete(IMMmbrExpVO vo);

	
	/**
	 * 상세정보 단껀 
	 * @param vo
	 * @return
	 */
    IMMmbrExpVO selectDetail(IMMmbrExpVO vo);
	
    
	/**
	 * 목록 검색 조회
	 * @param Long
	 * @return
	 */
	List<IMMmbrExpVO> selectList(@Param ("memberSrl") Long memberSrl);


}