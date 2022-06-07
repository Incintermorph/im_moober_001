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
 * @File : IMMmbrExptSbjService.java
 * @Title : 회원면제과목
 * @date : 2022.03.10 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
public interface IMMmbrExptSbjService {

    /**
     * 등록
     * 
     * @param IMMmbrExptSbjVO
     * @return int
     * @throws Exception
     */
    int insertMmbrExptSbj(IMMmbrExptSbjVO vo) throws Exception;

    /**
     * 수정
     * 
     * @param IMMmbrExptSbjVO
     * @return int
     * @throws Exception
     */
    int updateMmbrExptSbj(IMMmbrExptSbjVO vo) throws Exception;

    
    /**
     * 삭제
     * 
     * @param IMMmbrExptSbjVO
     * @return int
     * @throws Exception
     */
    int deleteMmbrExptSbj(IMMmbrExptSbjVO vo) throws Exception;

    /**
     * 멀티 삭제
     * 
     * @param List<IMMmbrExptSbjVO>
     * @return int
     * @throws Exception
     */
    int deletelistMmbrExptSbj(List<IMMmbrExptSbjVO> voList) throws Exception;

   /**
    * 회원면제 과목 조회
    * @param memberSrl
    * @return
    * @throws Exception
    */
	List<IMMmbrExptSbjVO> selectListMmbrExptSbj(Long memberSrl) throws Exception;
	
	/**
	 * 회원면제 과목 조회 (단짝 )
	 * @param memberSrl
	 * @return
	 * @throws Exception
	 */
	List<IMMmbrExptSbjVO> selectListMmbrExptSbjOnline(Long memberSrl) throws Exception;
	

	/**
	 * 상세 조회 (단건)
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	IMMmbrExptSbjVO selectDetailMmbrExptSbj(IMMmbrExptSbjVO vo) throws Exception;
	
    

  

}