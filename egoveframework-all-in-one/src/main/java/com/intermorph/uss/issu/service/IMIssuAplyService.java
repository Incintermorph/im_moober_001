/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.issu.service;

import java.util.List;


import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;


/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.issu.service
 * @File : IMIssuAplyService.java
 * @Title : 발급신청
 * @date : 2022.04.14 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
public interface IMIssuAplyService {

    /**
     * 등록
     * 
     * @param IMIssuAplyVO
     * @return int
     * @throws Exception
     */
    int insertIssuAply(IMIssuAplyVO vo) throws Exception;

    /**
     * 수정
     * 
     * @param IMIssuAplyVO
     * @return int
     * @throws Exception
     */
    int updateIssuAply(IMIssuAplyVO vo) throws Exception;

    /**
     * 멀티 수정
     * 
     * @param List<IMIssuAplyVO>
     * @return int
     * @throws Exception
     */
    int updatelistIssuAply(List<IMIssuAplyVO> voList) throws Exception;

    /**
     * 삭제
     * 
     * @param IMIssuAplyVO
     * @return int
     * @throws Exception
     */
    int deleteIssuAply(IMIssuAplyVO vo) throws Exception;

    /**
     * 멀티 삭제
     * 
     * @param List<IMIssuAplyVO>
     * @return int
     * @throws Exception
     */
    int deletelistIssuAply(List<IMIssuAplyVO> voList) throws Exception;

    /**
	 * 목록 조회
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	BasePage<BaseResultSet> selectListIssuAply(BaseCondition condition) throws Exception;
	
	/**
	 * 상세 조회 (단건)
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	BaseResultSet selectDetailIssuAply(IMIssuAplyVO vo) throws Exception;
	
	
    

  

}