/*
 *  * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.wtst.aplcnt.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BaseResultSet;
import com.intermorph.cmmn.service.IMCmmnSttsVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;



/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.wtst.aplcnt.mapper
 * @File : IMWtstAplcntMapper.java
 * @Title : 필기시험 신청자
 * @date : 2022.04.04 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Mapper ("IMWtstAplcntMapper")
public interface IMWtstAplcntMapper {
    /**
     * 등록
     * 
     * @param vo
     * @return int
     */
    int insert(IMWtstAplcntVO vo);

    /**
     * 수정
     * 
     * @param vo
     * @return int
     */
    int update(IMWtstAplcntVO vo);
    /**
     * 상태 변경 
     * @param vo
     * @return
     */
    int updatePrgrsSttsCdv(IMWtstAplcntVO vo);
   

    /**
     * 삭제
     * 
     * @param vo
     * @return int
     */
    int delete(IMWtstAplcntVO vo);
    /**
     * 중복 체크 
     * @param vo
     * @return
     */
    int selectOverAplyCount(IMWtstAplcntVO vo);
    /**
     * 신청자수 
     * @param vo
     * @return
     */
    int selectAplyCount(IMWtstAplcntVO vo);
    /**
     * 대기 순번 큰 값
     * @param vo
     * @return
     */
    Long selectAplyMAXWaitOrder(IMWtstAplcntVO vo);

	
	/**
	 * 상세정보 단껀 
	 * @param vo
	 * @return
	 */
	BaseResultSet selectDetail(IMWtstAplcntVO vo);
	
    
	/**
	 * 목록 검색 조회
	 * @param condition
	 * @return
	 */
	List<BaseResultSet> selectList(BaseCondition condition);

    /**
	 * 검색 조회 수
	 * 
	 * @param condition
	 * @return
	 */
	int selectListCount(BaseCondition condition);

    /**
     * 운영 사무국 상태 목록 
     * @param crsId
     * @return
     */
    List<IMCmmnSttsVO> selectAplyOPSECTSRNG(@Param ("wtstPlaceId") String wtstPlaceId);
    
    /**
     * 개인 신청 현황 
     * @param mmbrEsntlId
     * @return
     */
    List<IMWtstAplcntVO> selectAplyStat(@Param ("mmbrEsntlId") String mmbrEsntlId);
    
    /**
     * 엑셀 데이터 확인
     * @param vo
     * @return
     */
    IMWtstAplcntVO selectOneWtstExcel(IMWtstAplcntVO vo);

}