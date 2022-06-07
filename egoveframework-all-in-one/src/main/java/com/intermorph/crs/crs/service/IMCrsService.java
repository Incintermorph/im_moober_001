/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.crs.crs.service;

import java.util.List;


import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;


/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.crs.crs.service
 * @File : IMCrsService.java
 * @Title : 과정(운영과정)
 * @date : 2022.02.28 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
public interface IMCrsService {

    /**
     * 등록
     * 
     * @param IMCrsVO
     * @return int
     * @throws Exception
     */
    int insertCrs(IMCrsVO vo) throws Exception;

    /**
     * 수정
     * 
     * @param IMCrsVO
     * @return int
     * @throws Exception
     */
    int updateCrs(IMCrsVO vo) throws Exception;
    /**
     * 수정
     * 
     * @param IMCrsVO
     * @return int
     * @throws Exception
     */
    int updateCrsPrgrsSttsCdv(IMCrsVO vo) throws Exception;

    /**
     * 멀티 수정
     * 
     * @param List<IMCrsVO>
     * @return int
     * @throws Exception
     */
    int updatelistCrs(List<IMCrsVO> voList) throws Exception;

    /**
     * 삭제
     * 
     * @param IMCrsVO
     * @return int
     * @throws Exception
     */
    int deleteCrs(IMCrsVO vo) throws Exception;

    /**
     * 멀티 삭제
     * 
     * @param List<IMCrsVO>
     * @return int
     * @throws Exception
     */
    int deletelistCrs(List<IMCrsVO> voList) throws Exception;

    /**
	 * 목록 조회
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	BasePage<BaseResultSet> selectListCrs(BaseCondition condition) throws Exception;
	
	/**
	 * 상세 조회 (단건)
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	BaseResultSet selectDetailCrs(IMCrsVO vo) throws Exception;
	
	/**
	 * 조회 카운트
	 * @param condition
	 * @return
	 */
	int selectListCount(BaseCondition condition) throws Exception;

  

}