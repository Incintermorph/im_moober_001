/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.egov.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.intermorph.cmmn.base.BaseResultSet;

import egovframework.com.cmm.service.FileVO;
import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.uss.olh.faq.service.FaqVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.egov.service
 * @File    : IMEgovMapper.java
 * @Title   : 전자정부 연계 모듈 
 * @date    : 2022. 2. 23
 * @author  : 노성용
 * @descrption :
 * 전자 정부 프레임워크에서 추가적으로 쿼리 수행 해야 하는경우 활용함 
 */
@Mapper ("IMEgovMapper")
public interface IMEgovMapper {
	/**
	 * 권한별 메뉴 목록 
	 * @param vo
	 * @return
	 */
	List<IMEgovResultSet> selectListAuthMenu(IMEgovVO vo);
	/**
	 *  메인 페이지 게시물 목록
	 * @return
	 */
	List<BoardVO> selectArticleMainList (BoardVO  bardVO);
	/**
	 * 메인 FAQ리스트 
	 * @param bardVO
	 * @return
	 */
	List<FaqVO> selectFaqMainList (BoardVO  bardVO);
	
	
	/**
	 *  (운영 과정) 메인 노출 
	 * @param String 
	 * @return
	 */
	List<BaseResultSet> selectMainListCrs(@Param ("scCrsGrdCdv") String scCrsGrdCdv,@Param ("limitNumber") Long limitNumber);
	
	/**
	 * 아이디 키 값  
	 * @param mberId
	 * @return
	 */
	String selectUserId(@Param ("mberId") String mberId);
	
	/**
	 * 파일 목록 
	 * @param atchFileId
	 * @return
	 */
	List<FileVO> selectFileList(@Param ("atchFileId") String atchFileId);
	
}
