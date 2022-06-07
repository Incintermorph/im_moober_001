/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.generator.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.generator.service
 * @File : GeneratorService.java
 * @Title : {간단한 프로그램의 명칭을 기록}
 * @date : 2022. 2. 21
 * @author : 노성용
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
public interface GeneratorService {
	
	/**
	 * 테이블목록 
	 * @param tableSchema
	 * @return
	 * @throws Exception
	 */
	List<String> selectListTable(@Param ("tableSchema") String tableSchema) throws Exception;
	/**
	 * 테이블의 컬럼정보 
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	List<ColumnVO> selectListColumn(String tableName) throws Exception;

	/**
	 * 파일 생성
	 * 
	 * @param xml
	 * @param inputVO
	 * @throws Exception
	 */
	void generate(String xml, InputVO inputVO) throws Exception;
}
