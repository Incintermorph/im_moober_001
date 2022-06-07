/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.sample.service;

import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BaseResultSet;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.sample.service
 * @File    : SampleMapper.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 1. 13
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
@Mapper ("SampleMapper")
public interface SampleMapper {

	/**
	 * 상세 조회 
	 * @param conditionVO
	 * @return BaseResultSet
	 */
	BaseResultSet getDetail(BaseCondition conditionVO);

}
