/*
 *  * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.hstry.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BaseResultSet;


import egovframework.rte.psl.dataaccess.mapper.Mapper;



/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.hstry.mapper
 * @File : IMMmbrHstryMapper.java
 * @Title : 회원이력
 * @date : 2022.03.10 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Mapper ("IMMmbrHstryMapper")
public interface IMMmbrHstryMapper {
	/**
	 * 이력 등록
	 * 
	 * @param vo
	 * @return int
	 */
	int insertEduHis(IMEduAplyHstryVO vo);
	
	/**
	 * 이력 수정
	 * 
	 * @param vo
	 * @return int
	 */
	int updateEduHis(IMEduAplyHstryVO vo);
    /**
     * 등록
     * 
     * @param vo
     * @return int
     */
    int insert(IMMmbrHstryVO vo);

    /**
     * 수정
     * 
     * @param vo
     * @return int
     */
    int update(IMMmbrHstryVO vo);
    
    /**
     * 수정 (수정자 정보)
     * 
     * @param vo
     * @return int
     */
    int updateLastMdfcn(IMMmbrHstryVO vo);

    /**
     * 삭제
     * 
     * @param vo
     * @return int
     */
    int delete(IMMmbrHstryVO vo);

	
	/**
	 * 상세정보 단껀 
	 * @param vo
	 * @return
	 */
	BaseResultSet selectDetail(IMMmbrHstryVO vo);
	
    
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
	 * 목록 검색 조회 (학습이력)
	 * @param condition
	 * @return
	 */
	List<BaseResultSet> selectEduHis(BaseCondition condition);
	
	/**
	 * 검색 조회 수(학습이력)
	 * 
	 * @param condition
	 * @return
	 */
	int selectEduHisCount(BaseCondition condition);
	

	/**
	 * 학습 이력  검색 조회(이전 학습이력)
	 * @param condition
	 * @return
	 */
	List<BaseResultSet> selectEduHisList(@Param ("memberSrl") Long memberSrl, @Param ("sttsCode") String sttsCode);
	/**
	 * 학습이력 (이전학습 이력 통계)
	 * @param memberSrl
	 * @return
	 */
	List<BaseResultSet>  selectEduHisListStat (@Param ("memberSrl") Long memberSrl);
}