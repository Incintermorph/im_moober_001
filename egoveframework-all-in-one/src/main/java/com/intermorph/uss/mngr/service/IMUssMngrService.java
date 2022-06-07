/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.mngr.service;

import java.util.List;

import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;
import com.intermorph.crs.mstr.service.IMCrsMstrVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.mngr.service
 * @File    : IMUssMngrService.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 2. 24
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
public interface IMUssMngrService {

	
	/**
	 * 목록 조회
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	BasePage<BaseResultSet> selectListUssMngr(BaseCondition condition) throws Exception;
	
	/**
	 * 상세 조회 (단건)
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	BaseResultSet selectDetailUssMngr(IMUssMngrVO vo) throws Exception;
	/**
	 * 삭제 상태를 삭제 상태로 변경 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	int deleteUssMngr(IMUssMngrVO vo) throws Exception;
	
	/**
	 * 삭제를 일괄 처리 
	 * @param voList
	 * @return
	 * @throws Exception
	 */
	int deletelistUssMngr(List<IMUssMngrVO> voList) throws Exception;
}
