/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.wtst.aplcnt.service;

import java.util.List;


import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;
import com.intermorph.crs.crs.service.IMCrsVO;
import com.intermorph.wtst.place.service.IMWtstPlaceVO;


/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.wtst.aplcnt.service
 * @File : IMWtstAplcntService.java
 * @Title : 필기시험 신청자
 * @date : 2022.04.04 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
public interface IMWtstAplcntService {

    /**
     * 등록
     * 
     * @param IMWtstAplcntVO
     * @return int
     * @throws Exception
     */
    int insertWtstAplcnt(IMWtstAplcntVO vo) throws Exception;

    /**
     * 수정
     * 
     * @param IMWtstAplcntVO
     * @return int
     * @throws Exception
     */
    int updateWtstAplcnt(IMWtstAplcntVO vo) throws Exception;
  
    
    
    /**
     * 멀티 수정
     * 
     * @param List<IMWtstAplcntVO>
     * @return int
     * @throws Exception
     */
    int updatelistWtstAplcnt(List<IMWtstAplcntVO> voList) throws Exception;

    
    
    /**
     * 삭제
     * 
     * @param IMWtstAplcntVO
     * @return int
     * @throws Exception
     */
    int deleteWtstAplcnt(IMWtstAplcntVO vo) throws Exception;

    /**
     * 멀티 삭제
     * 
     * @param List<IMWtstAplcntVO>
     * @return int
     * @throws Exception
     */
    int deletelistWtstAplcnt(List<IMWtstAplcntVO> voList) throws Exception;

    /**
	 * 목록 조회
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	BasePage<BaseResultSet> selectListWtstAplcnt(BaseCondition condition) throws Exception;
	
	/**
	 * 조회 결과 카운드
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	int selectListCount(BaseCondition condition) throws Exception;
	
	/**
	 * 상세 조회 (단건)
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	BaseResultSet selectDetailWtstAplcnt(IMWtstAplcntVO vo) throws Exception;
	
	
    /**
     * 신청 체크 
     * @param vo
     * @return
     * @throws Exception
     */
	int selectOverAplyCount(IMWtstAplcntVO vo) throws Exception;
	
	/**
	 * 신청자 수
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	int selectAplyCount(IMWtstAplcntVO vo)throws Exception;
  
	
	
	/**
	 * 선정 상태 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	int updateAplyOPSECTSRNGtoApply(IMWtstPlaceVO vo) throws Exception;
	
	

    /**
     * 접수 번호 업데이트
     * @param vo
     * @return
     * @throws Exception
     */
    int updateTktstno(IMWtstAplcntVO vo) throws Exception;
    
    /**
     * 개인 현황 
     * @param mmbrEsntlId
     * @return
     * @throws Exception
     */
    List<IMWtstAplcntVO> selectAplyStat(String mmbrEsntlId) throws Exception;
    
    /**
     * 엑셀 응시정보 체크 
     * @param vo
     * @return
     * @throws Exception
     */
    IMWtstAplcntVO selectOneWtstExcel(IMWtstAplcntVO vo) throws Exception;

}