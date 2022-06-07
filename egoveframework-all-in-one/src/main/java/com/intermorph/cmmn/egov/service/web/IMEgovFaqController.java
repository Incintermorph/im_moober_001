/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.egov.service.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.uss.olh.faq.service.EgovFaqService;
import egovframework.com.uss.olh.faq.service.FaqVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.egov.service.web
 * @File    : IMEgovFaqController.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 3. 4
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */

@Controller
public class IMEgovFaqController {

	
	@Resource(name = "EgovFaqService")
	private EgovFaqService egovFaqService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	
	/**
	 * FAQ 목록을 조회한다.
	 * @param searchVO
	 * @param model
	 * @return	"layout/user/cop/bbs/selectEgovFaqList"
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmmn/cop/faq/selectList.do")
	public String selectFaqList(@ModelAttribute("searchVO") FaqVO searchVO, ModelMap model) throws Exception {

		/** EgovPropertyService.SiteList */
		searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		searchVO.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<?> FaqList = egovFaqService.selectFaqList(searchVO);
		model.addAttribute("resultList", FaqList);

		int totCnt = egovFaqService.selectFaqListCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("searchVO", searchVO);
		

		return "layout/user/cop/faq/selectEgovFaqList";
	}
}
