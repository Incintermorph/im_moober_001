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
import com.intermorph.crs.crs.service.IMCrsVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.crs.aplcnt.service
 * @File : IMCrsAplcntService.java
 * @Title : 운영과정신청자
 * @date : 2022.03.03 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
public interface IMCrsAplcntService {

	/**
	 * 등록
	 * 
	 * @param IMCrsAplcntVO
	 * @return int
	 * @throws Exception
	 */
	int insertCrsAplcnt(IMCrsAplcntVO vo) throws Exception;

	/**
	 * 수료 처리 결과 수정
	 * 
	 * @param IMCrsAplcntVO
	 * @return int
	 * @throws Exception
	 */
	int updateCrsAplcntRslt(IMCrsAplcntVO vo, String rsltCodeDvsn) throws Exception;

	/**
	 * 수정
	 * 
	 * @param IMCrsAplcntVO
	 * @return int
	 * @throws Exception
	 */
	int updateCrsAplcnt(IMCrsAplcntVO vo) throws Exception;

	/**
	 * 멀티 수정
	 * 
	 * @param List<IMCrsAplcntVO>
	 * @return int
	 * @throws Exception
	 */
	int updatelistCrsAplcnt(List<IMCrsAplcntVO> voList) throws Exception;

	/**
	 * 삭제
	 * 
	 * @param IMCrsAplcntVO
	 * @return int
	 * @throws Exception
	 */
	int deleteCrsAplcnt(IMCrsAplcntVO vo) throws Exception;

	/**
	 * 멀티 삭제
	 * 
	 * @param List<IMCrsAplcntVO>
	 * @return int
	 * @throws Exception
	 */
	int deletelistCrsAplcnt(List<IMCrsAplcntVO> voList) throws Exception;

	/**
	 * 목록 조회
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	BasePage<BaseResultSet> selectListCrsAplcnt(BaseCondition condition) throws Exception;
	
	/**
	 * 목록 조회 카운트
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	int selectListCount(BaseCondition condition) throws Exception;

	/**
	 * 상세 조회 (단건)
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	BaseResultSet selectDetailCrsAplcnt(IMCrsAplcntVO vo) throws Exception;

	/**
	 * 랜덤 처리
	 * 
	 * @param crsId
	 * @return
	 * @throws Exception
	 */
	int updateCrsAlcntRANDOM(IMCrsVO vo) throws Exception;

	/**
	 * 통보 처리
	 * 
	 * @param crsId
	 * @return
	 * @throws Exception
	 */
	int updateAplyCrsOPSECTSRNGtoApply(IMCrsVO vo) throws Exception;

	/**
	 * 과정 수강 확인
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	int selectAplyFishCount(BaseCondition condition) throws Exception;

	/**
	 * 수료처리 결과
	 * 
	 * @param crsAplcntId
	 * @return
	 * @throws Exception
	 */
	IMCrsAplcntVO selectDetailResult(String crsAplcntId) throws Exception;

	/**
	 * 개인 신청 현황 
	 * 
	 * @param crsAplcntId
	 * @return
	 * @throws Exception
	 */
	List<IMCrsAplcntVO> selectAplyStat(String mmbrEsntlId) throws Exception;
	
	/**
	 * 개인 검증 현황  
	 * 
	 * @param crsAplcntId
	 * @return
	 * @throws Exception
	 */
	List<IMCrsAplcntVO> selectAplyUserViewHistory(String mmbrEsntlId) throws Exception;
}