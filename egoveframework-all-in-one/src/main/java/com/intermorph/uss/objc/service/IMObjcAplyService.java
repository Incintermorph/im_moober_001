/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.objc.service;

import java.util.List;


import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;


/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.objc.service
 * @File : IMObjcAplyService.java
 * @Title : 이의신청
 * @date : 2022.04.06 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
public interface IMObjcAplyService {

    /**
     * 등록
     * 
     * @param IMObjcAplyVO
     * @return int
     * @throws Exception
     */
    int insertObjcAply(IMObjcAplyVO vo) throws Exception;

    /**
     * 수정
     * 
     * @param IMObjcAplyVO
     * @return int
     * @throws Exception
     */
    int updateObjcAply(IMObjcAplyVO vo) throws Exception;

    /**
     * 멀티 수정
     * 
     * @param List<IMObjcAplyVO>
     * @return int
     * @throws Exception
     */
    int updatelistObjcAply(List<IMObjcAplyVO> voList) throws Exception;

    /**
     * 삭제
     * 
     * @param IMObjcAplyVO
     * @return int
     * @throws Exception
     */
    int deleteObjcAply(IMObjcAplyVO vo) throws Exception;

    /**
     * 멀티 삭제
     * 
     * @param List<IMObjcAplyVO>
     * @return int
     * @throws Exception
     */
    int deletelistObjcAply(List<IMObjcAplyVO> voList) throws Exception;

    /**
	 * 목록 조회
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	BasePage<BaseResultSet> selectListObjcAply(BaseCondition condition) throws Exception;
	
	/**
	 * 상세 조회 (단건)
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	BaseResultSet selectDetailObjcAply(IMObjcAplyVO vo) throws Exception;
	
	
    

  

}