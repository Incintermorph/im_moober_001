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
 * @File : IMInfoInqHstryMapper.java
 * @Title : 정보조회이력
 * @date : 2022.03.16 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Mapper ("IMInfoInqHstryMapper")
public interface IMInfoInqHstryMapper {
    /**
     * 등록
     * 
     * @param vo
     * @return int
     */
    int insert(IMInfoInqHstryVO vo);

    

}