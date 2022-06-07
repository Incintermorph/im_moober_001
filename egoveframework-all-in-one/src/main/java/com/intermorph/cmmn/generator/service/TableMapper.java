/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.generator.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import egovframework.rte.psl.dataaccess.mapper.Mapper;


/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.generator.service
 * @File    : TableMapper.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 2. 21
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */

@Mapper ("TableMapper")
public interface TableMapper {

	/**
	 * 테이블 컬럼  정보
	 * @param tableName
	 * @return
	 */
	List<String> selectListTable(@Param ("tableSchema") String tableSchema);
	
	/**
	 * 테이블 컬럼  정보
	 * @param tableName
	 * @return
	 */
	List<ColumnVO> selectListColumn(@Param ("tableName") String tableName);
	
	

}
