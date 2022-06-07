/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.crs.aplcnt.service;

import java.util.List;


import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;


/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.crs.aplcnt.service
 * @File : IMCrsAplyTrnsfService.java
 * @Title : 과정신청이관
 * @date : 2022.05.12 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
public interface IMCrsAplyTrnsfService {

    /**
     * 등록
     * 
     * @param IMCrsAplyTrnsfVO
     * @return int
     * @throws Exception
     */
    int insertCrsAplyTrnsf(IMCrsAplyTrnsfVO vo) throws Exception;

    /**
     * 수정
     * 
     * @param IMCrsAplyTrnsfVO
     * @return int
     * @throws Exception
     */
    int updateCrsAplyTrnsf(IMCrsAplyTrnsfVO vo) throws Exception;

    /**
     * 멀티 수정
     * 
     * @param List<IMCrsAplyTrnsfVO>
     * @return int
     * @throws Exception
     */
    int updatelistCrsAplyTrnsf(List<IMCrsAplyTrnsfVO> voList) throws Exception;

    /**
     * 삭제
     * 
     * @param IMCrsAplyTrnsfVO
     * @return int
     * @throws Exception
     */
    int deleteCrsAplyTrnsf(IMCrsAplyTrnsfVO vo) throws Exception;

    /**
     * 멀티 삭제
     * 
     * @param List<IMCrsAplyTrnsfVO>
     * @return int
     * @throws Exception
     */
    int deletelistCrsAplyTrnsf(List<IMCrsAplyTrnsfVO> voList) throws Exception;

    /**
	 * 목록 조회
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	BasePage<BaseResultSet> selectListCrsAplyTrnsf(BaseCondition condition) throws Exception;
	
	/**
	 * 상세 조회 (단건)
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	BaseResultSet selectDetailCrsAplyTrnsf(IMCrsAplyTrnsfVO vo) throws Exception;
	
	
    

  

}