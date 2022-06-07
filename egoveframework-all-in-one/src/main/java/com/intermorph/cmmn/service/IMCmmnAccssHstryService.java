/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;


/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.service
 * @File : IMCmmnAccssHstryService.java
 * @Title : 공통접근이력
 * @date : 2022.05.13 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
public interface IMCmmnAccssHstryService {

   
    
    /**
     * 관련 이력 등록
     * @param request
     * @param tblId
     * @param tblRefId
     * @param refNm
     * @return
     * @throws Exception
     */
    int insertCmmnAccssHstry(HttpServletRequest request,String tblId,String tblRefId,String refNm) throws Exception;
    
    

  

}