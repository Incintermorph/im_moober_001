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
 * @File : IMLgnSttsMapper.java
 * @Title : 로그인 현황
 * @date : 2022.04.12 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Mapper ("IMLgnSttsMapper")
public interface IMLgnSttsMapper {
    /**
     * 등록
     * 
     * @param vo
     * @return int
     */
    int insert(IMLgnSttsVO vo);

    /**
     * 수정
     * 
     * @param vo
     * @return int
     */
    int update(IMLgnSttsVO vo);
	
	/**
	 * 상세정보 단껀 
	 * @param vo
	 * @return
	 */
    IMLgnSttsVO selectDetail(@Param ("esntlId") String esntlId);
	

}