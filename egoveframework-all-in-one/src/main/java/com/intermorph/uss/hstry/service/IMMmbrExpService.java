/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.hstry.service;

import java.util.List;


/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.hstry.service
 * @File : IMMmbrExpService.java
 * @Title : 회원경력
 * @date : 2022.03.10 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
public interface IMMmbrExpService {

    /**
     * 등록
     * 
     * @param IMMmbrExpVO
     * @return int
     * @throws Exception
     */
    int insertMmbrExp(IMMmbrExpVO vo) throws Exception;

    /**
     * 수정
     * 
     * @param IMMmbrExpVO
     * @return int
     * @throws Exception
     */
    int updateMmbrExp(IMMmbrExpVO vo) throws Exception;


    /**
     * 삭제
     * 
     * @param IMMmbrExpVO
     * @return int
     * @throws Exception
     */
    int deleteMmbrExp(IMMmbrExpVO vo) throws Exception;

    /**
     * 멀티 삭제
     * 
     * @param List<IMMmbrExpVO>
     * @return int
     * @throws Exception
     */
    int deletelistMmbrExp(List<IMMmbrExpVO> voList) throws Exception;

    /**
	 * 목록 조회
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	List<IMMmbrExpVO> selectListMmbrExp(Long memberSrl) throws Exception;
	
	/**
	 * 상세 조회 (단건)
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	IMMmbrExpVO selectDetailMmbrExp(IMMmbrExpVO vo) throws Exception;
	
	
    

  

}