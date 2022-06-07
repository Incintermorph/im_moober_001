/*
 *  * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BaseResultSet;


import egovframework.rte.psl.dataaccess.mapper.Mapper;



/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.mapper
 * @File : IMRsltCodeMapper.java
 * @Title : 결과코드
 * @date : 2022.03.30 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Mapper ("IMRsltCodeMapper")
public interface IMRsltCodeMapper {
    /**
     * 등록
     * 
     * @param vo
     * @return int
     */
    int insert(IMRsltCodeVO vo);

    /**
     * 수정
     * 
     * @param vo
     * @return int
     */
    int update(IMRsltCodeVO vo);

  
	
	/**
	 * 상세정보 단껀 
	 * @param vo
	 * @return
	 */
    IMRsltCodeVO selectDetail(IMRsltCodeVO vo);
    
    /**
     * 중복 확인 
     * @param rsltCode
     * @return
     */
    int selectOverChek(@Param ("rsltCode") String rsltCode);
    
    /**
     * 가장최종값 확인 
     * @param stndrdCode
     * @return
     */
    String selectMaxCode(@Param ("stndrdCode") String stndrdCode);

}