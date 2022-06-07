/*
 *  * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.potal.uss.institution.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BaseResultSet;
import com.intermorph.uss.hstry.service.IMEduAplyHstryVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;



/**
 * @Project : egoveframework-all-in-one
 * @Package : com.potal.uss.institution.mapper
 * @File : IMEpInstitutionMapper.java
 * @Title : 기관(포탈)
 * @date : 2022.03.29 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Mapper ("IMEpInstitutionMapper")
public interface IMEpInstitutionMapper {
    /**
     * 등록
     * 
     * @param vo
     * @return int
     */
    int insert(IMEpInstitutionVO vo);

    /**
     * 수정
     * 
     * @param vo
     * @return int
     */
    int update(IMEpInstitutionVO vo);

    /**
     * 삭제
     * 
     * @param vo
     * @return int
     */
    int delete(IMEpInstitutionVO vo);

	
	/**
	 * 상세정보 단껀 
	 * @param vo
	 * @return
	 */
	BaseResultSet selectDetail(IMEpInstitutionVO vo);
	
    
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
	 * 회원 정보 
	 * @param userId
	 * @return
	 */
	IMEpInstitutionVO selectMemberUser(@Param ("userId") String userId);
	
	/**
	 * 기존 교육 이력  
	 * @param userId
	 * @return
	 */
	List<IMEduAplyHstryVO> selectListEduAply(@Param ("listtime") String listtime);
	

}