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
 * @File : IMInfoInqHstryService.java
 * @Title : 정보조회이력
 * @date : 2022.03.16 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
public interface IMInfoInqHstryService {

 

    /**
     * 이력 등록 
     * @param tblId : 참조 테이블 
     * @param tblRefId : 참조 테이브키
     * @param refNm : 참조값 : select, selectList, update, delete , insert
     * @param inqRsn : 조회 사유
     * @param req
     * @return
     * @throws Exception
     */
    int insertInfoInqHstry(String  tblId,String tblRefId,String  refNm, String inqRsn,  HttpServletRequest req) throws Exception;
  

  

}