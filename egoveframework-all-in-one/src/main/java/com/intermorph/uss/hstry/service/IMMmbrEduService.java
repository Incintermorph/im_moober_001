/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.hstry.service;

import java.util.List;


import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;


/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.hstry.service
 * @File : IMMmbrEduService.java
 * @Title : 회원학력
 * @date : 2022.03.10 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
public interface IMMmbrEduService {

    /**
     * 등록
     * 
     * @param IMMmbrEduVO
     * @return int
     * @throws Exception
     */
    int insertMmbrEdu(IMMmbrEduVO vo) throws Exception;

    /**
     * 수정
     * 
     * @param IMMmbrEduVO
     * @return int
     * @throws Exception
     */
    int updateMmbrEdu(IMMmbrEduVO vo) throws Exception;

    /**

    /**
     * 삭제
     * 
     * @param IMMmbrEduVO
     * @return int
     * @throws Exception
     */
    int deleteMmbrEdu(IMMmbrEduVO vo) throws Exception;

    /**
     * 멀티 삭제
     * 
     * @param List<IMMmbrEduVO>
     * @return int
     * @throws Exception
     */
    int deletelistMmbrEdu(List<IMMmbrEduVO> voList) throws Exception;

    /**
	 * 목록 조회
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	List<IMMmbrEduVO> selectListMmbrEdu(Long memberSrl) throws Exception;
	
	/**
	 * 상세 조회 (단건)
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	IMMmbrEduVO selectDetailMmbrEdu(IMMmbrEduVO vo) throws Exception;
	
	
    

  

}