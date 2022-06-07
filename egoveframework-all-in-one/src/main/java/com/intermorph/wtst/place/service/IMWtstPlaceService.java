/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.wtst.place.service;

import java.util.List;


import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;
import com.intermorph.wtst.aplcnt.service.IMWtstAplcntVO;


/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.wtst.place.service
 * @File : IMWtstPlaceService.java
 * @Title : 필기시험장소
 * @date : 2022.03.31 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
public interface IMWtstPlaceService {

    /**
     * 등록
     * 
     * @param IMWtstPlaceVO
     * @return int
     * @throws Exception
     */
    int insertWtstPlace(IMWtstPlaceVO vo) throws Exception;

    /**
     * 수정
     * 
     * @param IMWtstPlaceVO
     * @return int
     * @throws Exception
     */
    int updateWtstPlace(IMWtstPlaceVO vo) throws Exception;

    /**
     * 멀티 수정
     * 
     * @param List<IMWtstPlaceVO>
     * @return int
     * @throws Exception
     */
    int updatelistWtstPlace(List<IMWtstPlaceVO> voList) throws Exception;

    /**
     * 삭제
     * 
     * @param IMWtstPlaceVO
     * @return int
     * @throws Exception
     */
    int deleteWtstPlace(IMWtstPlaceVO vo) throws Exception;

    /**
     * 멀티 삭제
     * 
     * @param List<IMWtstPlaceVO>
     * @return int
     * @throws Exception
     */
    int deletelistWtstPlace(List<IMWtstPlaceVO> voList) throws Exception;

    /**
	 * 목록 조회
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	BasePage<BaseResultSet> selectListWtstPlace(BaseCondition condition) throws Exception;
	
	/**
	 * 상세 조회 (단건)
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	BaseResultSet selectDetailWtstPlace(IMWtstPlaceVO vo) throws Exception;
	
	
    
	 /**
     * 수정
     * 
     * @param IMWtstAplcntVO
     * @return int
     * @throws Exception
     */
	int updateWtstAplcntPrgrsSttsCdv(IMWtstPlaceVO vo) throws Exception;
  

}