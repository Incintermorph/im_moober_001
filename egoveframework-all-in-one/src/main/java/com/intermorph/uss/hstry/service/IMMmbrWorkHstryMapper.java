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
 * @File : IMMmbrWorkHstryMapper.java
 * @Title : 회원근무이력
 * @date : 2022.03.11 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Mapper ("IMMmbrWorkHstryMapper")
public interface IMMmbrWorkHstryMapper {
    /**
     * 등록
     * 
     * @param vo
     * @return int
     */
    int insert(IMMmbrWorkHstryVO vo);

    /**
     * 수정
     * 
     * @param vo
     * @return int
     */
    int update(IMMmbrWorkHstryVO vo);

    /**
     * 삭제
     * 
     * @param vo
     * @return int
     */
    int delete(IMMmbrWorkHstryVO vo);

	
	/**
	 * 상세정보 단껀 
	 * @param vo
	 * @return
	 */
    IMMmbrWorkHstryVO selectDetail(IMMmbrWorkHstryVO vo);
	
    
	/**
	 * 목록 검색 조회
	 * @param condition
	 * @return
	 */
	List<IMMmbrWorkHstryVO> selectList(@Param ("memberSrl") Long memberSrl);
	/**
	 * 근무 연수 계산 
	 * @param memberSrl
	 * @return
	 */
	IMMmbrWorkHstryVO selectListDiffSum(@Param ("memberSrl") Long memberSrl);


}