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
 * @File : IMMmbrEtcService.java
 * @Title : 회원기타정보
 * @date : 2022.03.10 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
public interface IMMmbrEtcService {

    /**
     * 등록
     * 
     * @param IMMmbrEtcVO
     * @return int
     * @throws Exception
     */
    int insertMmbrEtc(IMMmbrEtcVO vo) throws Exception;

    /**
     * 수정
     * 
     * @param IMMmbrEtcVO
     * @return int
     * @throws Exception
     */
    int updateMmbrEtc(IMMmbrEtcVO vo) throws Exception;

    /**
     * 삭제
     * 
     * @param IMMmbrEtcVO
     * @return int
     * @throws Exception
     */
    int deleteMmbrEtc(IMMmbrEtcVO vo) throws Exception;

    /**
     * 멀티 삭제
     * 
     * @param List<IMMmbrEtcVO>
     * @return int
     * @throws Exception
     */
    int deletelistMmbrEtc(List<IMMmbrEtcVO> voList) throws Exception;

    /**
	 * 목록 조회
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	List<IMMmbrEtcVO> selectListMmbrEtc(Long memberSrl) throws Exception;
	
	/**
	 * 상세 조회 (단건)
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	IMMmbrEtcVO selectDetailMmbrEtc(IMMmbrEtcVO vo) throws Exception;
	
	
    

  

}