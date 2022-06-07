/*
 *  * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.service;

import java.util.List;


import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BaseResultSet;


import egovframework.rte.psl.dataaccess.mapper.Mapper;



/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.mapper
 * @File : IMCmmnDtMapper.java
 * @Title : 공통날짜
 * @date : 2022.02.28 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Mapper ("IMCmmnDtMapper")
public interface IMCmmnDtMapper {
    /**
     * 등록
     * 
     * @param vo
     * @return int
     */
    int insert(IMCmmnDtVO vo);

    /**
     * 수정
     * 
     * @param vo
     * @return int
     */
    int update(IMCmmnDtVO vo);

    
	/**
	 * 저장된  날짜 목록 리스트 
	 * @param condition
	 * @return
	 */
	List<IMCmmnDtVO> selectList(IMCmmnDtVO vo);

  

}