/*
 *  * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.qlfc.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BaseResultSet;


import egovframework.rte.psl.dataaccess.mapper.Mapper;



/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.qlfc.mapper
 * @File : IMMmbrQlfcMapper.java
 * @Title : 회원자격정보
 * @date : 2022.04.13 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Mapper ("IMMmbrQlfcMapper")
public interface IMMmbrQlfcMapper {
    /**
     * 등록
     * 
     * @param vo
     * @return int
     */
    int insert(IMMmbrQlfcVO vo);
    /**
     * 이력 저장 
     * @param vo
     * @return
     */
    int insertHstry(IMMmbrQlfcVO vo);
    
    /**
     * 이전 유효만료일 
     * @param vo
     * @return
     */
    String selectBackupEndYmd(IMMmbrQlfcVO vo);

    /**
     * 수정
     * 
     * @param vo
     * @return int
     */
    int update(IMMmbrQlfcVO vo);

    /**
     * 삭제
     * 
     * @param vo
     * @return int
     */
    int delete(IMMmbrQlfcVO vo);

	
	/**
	 * 상세정보 단껀 
	 * @param vo
	 * @return
	 */
    IMMmbrQlfcVO selectDetail(@Param ("esntlId") String esntlId,@Param ("crsGrdCdv") String crsGrdCdv);
    
    /**
	 * 상세정보 단껀 
	 * @param vo
	 * @return
	 */
	IMMmbrQlfcVO selectLastDetail(@Param ("esntlId") String esntlId);
	
	/**
	 * 회원 리스트 
	 * @param esntlId
	 * @return
	 */
	List<IMMmbrQlfcVO> selectUserList(@Param ("esntlId") String esntlId);
	

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

}