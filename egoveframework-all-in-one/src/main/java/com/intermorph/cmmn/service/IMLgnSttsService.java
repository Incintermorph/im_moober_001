/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.service;

import java.util.List;


import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;


/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.service
 * @File : IMLgnSttsService.java
 * @Title : 로그인 현황
 * @date : 2022.04.12 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
public interface IMLgnSttsService {

    /**
     * 등록
     * 
     * @param IMLgnSttsVO
     * @return int
     * @throws Exception
     */
    int insertLgnStts(IMLgnSttsVO vo) throws Exception;

    /**
     * 수정
     * 
     * @param IMLgnSttsVO
     * @return int
     * @throws Exception
     */
    int updateLgnStts(IMLgnSttsVO vo) throws Exception;

    

	/**
	 * 상세 조회 (단건)
	 * @param condition
	 * @return
	 * @throws Exception
	 */
    IMLgnSttsVO selectDetailLgnStts(String esntlId) throws Exception;
    

  

}