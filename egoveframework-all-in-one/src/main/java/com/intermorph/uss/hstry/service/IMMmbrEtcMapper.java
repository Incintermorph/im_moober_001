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
 * @File : IMMmbrEtcMapper.java
 * @Title : 회원기타정보
 * @date : 2022.03.10 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Mapper ("IMMmbrEtcMapper")
public interface IMMmbrEtcMapper {
    /**
     * 등록
     * 
     * @param vo
     * @return int
     */
    int insert(IMMmbrEtcVO vo);

    /**
     * 수정
     * 
     * @param vo
     * @return int
     */
    int update(IMMmbrEtcVO vo);

    /**
     * 삭제
     * 
     * @param vo
     * @return int
     */
    int delete(IMMmbrEtcVO vo);

	
	/**
	 * 상세정보 단껀 
	 * @param vo
	 * @return
	 */
    IMMmbrEtcVO selectDetail(IMMmbrEtcVO vo);
	
    
	/**
	 * 목록 검색 조회
	 * @param condition
	 * @return
	 */
	List<IMMmbrEtcVO> selectList(@Param ("memberSrl") Long memberSrl);

}