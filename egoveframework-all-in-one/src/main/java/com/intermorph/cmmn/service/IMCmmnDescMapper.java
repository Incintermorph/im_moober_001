/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.service
 * @File    : IMCmmnDescMapper.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 2. 18
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */

@Mapper("IMCmmnDescMapper")
public interface IMCmmnDescMapper {
	 /**
     * 등록
     * 
     * @param vo
     * @return int
     */
    int insert(IMCmmnDescVO vo);
    
    

   /**
    * 등록
    * 
    * @param vo
    * @return int
    */
   int update(IMCmmnDescVO vo);
   
   

 /**
  * 저장된  장문 목록 리스트 
  * @param vo
  * @return
  */
   List<IMCmmnDescVO> selectList(IMCmmnDescVO vo);

}
