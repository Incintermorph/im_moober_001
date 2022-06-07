/*
 *  * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.crs.aplcnt.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BaseResultSet;
import com.intermorph.cmmn.service.IMCmmnSttsVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;



/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.crs.aplcnt.mapper
 * @File : IMCrsAplcntMapper.java
 * @Title : 운영과정신청자
 * @date : 2022.03.03 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Mapper ("IMCrsAplcntMapper")
public interface IMCrsAplcntMapper {
    /**
     * 등록
     * 
     * @param vo
     * @return int
     */
    int insert(IMCrsAplcntVO vo);
    /**
     * 결과등록  
     * @param vo
     * @return
     */
    int insertRslt(IMCrsAplcntVO vo);
    

    /**
     * 수정
     * 
     * @param vo
     * @return int
     */
    int update(IMCrsAplcntVO vo);
    /**
     * 결과 수정 
     * @param vo
     * @return
     */
    int updateRslt(IMCrsAplcntVO vo);

    /**
     * 삭제
     * 
     * @param vo
     * @return int
     */
    int delete(IMCrsAplcntVO vo);

	
	/**
	 * 상세정보 단껀 
	 * @param vo
	 * @return
	 */
	BaseResultSet selectDetail(IMCrsAplcntVO vo);
	
    
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
	 * 수강신청 중복 체크 
	 * @param vo
	 * @return
	 */
    int selectOverAplyCount(IMCrsAplcntVO vo);
    /**
     * 해당 과정 수강신청자 전체 (취소자 제외)
     * @param vo
     * @return
     */
    int selectAplyCount(IMCrsAplcntVO vo);
    
    /**
     * 수료 정보 확인 
     * @param condition
     * @return
     */
    int selectAplyFishCount(BaseCondition condition);
    
    /**
     * 해앙과정 수강신청자 목록 랜덤 
     * @param crsId
     * @return
     */
    List<IMCrsAplcntVO> selectAplyCrsRANDOM(@Param ("crsId") String crsId);
    /**
     * 운영 사무국 상태 목록 
     * @param crsId
     * @return
     */
    List<IMCmmnSttsVO> selectAplyCrsOPSECTSRNG(@Param ("crsId") String crsId);
    /**
     * 수료 결과 정보 
     * @param crsAplcntId
     * @return
     */
    IMCrsAplcntVO  selectDetailResult(@Param ("crsAplcntId") String crsAplcntId);
    
    /**
     *  개인 신청 현황 
     * @param crsAplcntId
     * @return
     */
    List<IMCrsAplcntVO>  selectAplyStat (@Param ("mmbrEsntlId") String mmbrEsntlId);
    /**
     * 개인 이력 검증 목록 
     * @param mmbrEsntlId
     * @return
     */
    List<IMCrsAplcntVO>  selectAplyUserViewHistory (@Param ("mmbrEsntlId") String mmbrEsntlId);
    
    

}