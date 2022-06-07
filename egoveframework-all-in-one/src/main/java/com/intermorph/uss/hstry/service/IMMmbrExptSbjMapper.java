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
 * @File : IMMmbrExptSbjMapper.java
 * @Title : 회원면제과목
 * @date : 2022.03.10 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Mapper ("IMMmbrExptSbjMapper")
public interface IMMmbrExptSbjMapper {
    /**
     * 등록
     * 
     * @param vo
     * @return int
     */
    int insert(IMMmbrExptSbjVO vo);

    /**
     * 수정
     * 
     * @param vo
     * @return int
     */
    int update(IMMmbrExptSbjVO vo);

    /**
     * 삭제
     * 
     * @param vo
     * @return int
     */
    int delete(IMMmbrExptSbjVO vo);

	
	/**
	 * 회원면제과목 이력 조회 
	 * @param condition
	 * @return 
	 */
	List<IMMmbrExptSbjVO> selectList(@Param ("memberSrl") Long memberSrl, @Param ("exempSbjDvsn") String exempSbjDvsn);
	/**
	 * 상세 정보 
	 * @param vo
	 * @return
	 */
    IMMmbrExptSbjVO selectDetail(IMMmbrExptSbjVO vo);

    
}