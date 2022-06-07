/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.egov.service.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.intermorph.cmmn.IMGlobals;
import com.intermorph.cmmn.exception.IMProcErrors;
import com.intermorph.cmmn.exception.IMProcException;
import com.intermorph.cmmn.util.IMStringUtil;
import com.intermorph.crs.agncy.service.IMAgncyService;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.BoardMasterVO;
import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.cop.bbs.service.EgovArticleService;
import egovframework.com.cop.bbs.service.EgovBBSMasterService;
import egovframework.com.cop.bbs.service.EgovBBSSatisfactionService;
import egovframework.com.cop.cmt.service.EgovArticleCommentService;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.egov.service.web
 * @File : IMEgovArticleController.java
 * @Title : {간단한 프로그램의 명칭을 기록}
 * @date : 2022. 3. 4
 * @author : 노성용
 * @descrption : {상세한 프로그램의 용도를 기록}
 */

@Controller
public class IMEgovArticleController {

	// private static final Logger LOGGER =
	// LoggerFactory.getLogger(IMEgovArticleController.class);

	@Resource(name = "EgovArticleService")
	private EgovArticleService egovArticleService;

	@Resource(name = "EgovBBSMasterService")
	private EgovBBSMasterService egovBBSMasterService;

	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileMngService;

	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;

	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;

	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileService;

	@Resource(name = "EgovArticleCommentService")
	protected EgovArticleCommentService egovArticleCommentService;

	@Resource(name = "EgovBBSSatisfactionService")
	private EgovBBSSatisfactionService bbsSatisfactionService;

	@Resource(name = "IMAgncyService")
	private IMAgncyService agncyService;
	
	// {authorCode}
	/**
	 * 프론트 게시판 코드 관리
	 * @param bbsId
	 * @return
	 */
	private String  checkBbsId(String bbsId) {
		HashMap<String, String> bbsMap = new HashMap<String, String>();
		//URL 코드 , bbs 코드
		bbsMap.put("notice", "BBSMSTR_000000000001");
		bbsMap.put("resource", "BBSMSTR_000000000011");
		return  bbsMap.get(bbsId);
		
	}

	/**
	 * 게시물에 대한 목록을 조회한다.
	 * 
	 * @param boardVO
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cmmn/cop/bbs/{bbsCode}/selectList.do")
	public String selectArticleList(@ModelAttribute("searchVO") BoardVO boardVO, @PathVariable("bbsCode") String bbsCode,
			ModelMap model) throws Exception {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		BoardMasterVO vo = new BoardMasterVO();
		boardVO.setSearchFrontAt("Y"); // 기간 검색 추가 

		
		vo.setBbsId(checkBbsId(bbsCode));
		vo.setUniqId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
		BoardMasterVO master = egovBBSMasterService.selectBBSMasterInf(vo);

		boardVO.setPageUnit(propertyService.getInt("pageUnit"));
		boardVO.setPageSize(propertyService.getInt("pageSize"));
		boardVO.setBbsId(checkBbsId(bbsCode));

		PaginationInfo paginationInfo = new PaginationInfo();

		paginationInfo.setCurrentPageNo(boardVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(boardVO.getPageUnit());
		paginationInfo.setPageSize(boardVO.getPageSize());

		boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		boardVO.setLastIndex(paginationInfo.getLastRecordIndex());
		boardVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> map = egovArticleService.selectArticleList(boardVO);
		int totCnt = Integer.parseInt((String) map.get("resultCnt"));
	

		// 공지사항 추출
		List<BoardVO> noticeList = egovArticleService.selectNoticeArticleList(boardVO);
		paginationInfo.setTotalRecordCount(totCnt);

		//// -----------------------------

		if (user != null) {
			model.addAttribute("sessionUniqId", user.getUniqId());
		}
		
		model.addAttribute("agncyList", agncyService.selectListAgncyDsgn());
				
				
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("articleVO", boardVO);
		model.addAttribute("bbsCode", bbsCode);
		model.addAttribute("bbsId", checkBbsId(bbsCode));
		model.addAttribute("boardMasterVO", master);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("noticeList", noticeList);
		return "layout/user/cop/bbs/selectArticleList";
	}
	
	
	/**
	 * 게시물에 대한 상세 정보를 조회한다.
	 * 
	 * @param boardVO
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cmmn/cop/bbs/{bbsCode}/selectDetailOpen.do")
	public String selectArticleDetailEnc(@ModelAttribute("searchVO") BoardVO boardVO, ModelMap model,
			@PathVariable("bbsCode") String bbsCode) throws Exception {
		
		if(IMStringUtil.isEmpty(boardVO.getNttIdOpn())) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		String nttidStr = IMStringUtil.decryptString(boardVO.getNttIdOpn(), IMGlobals.IM_SECURITY_KEY);
		
		boardVO.setNttId(Long.parseLong(nttidStr));
		
		return selectArticleDetail(boardVO, model, bbsCode);
	}
	
	/**
	 * 게시물에 대한 상세 정보를 조회한다.
	 * 
	 * @param boardVO
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cmmn/cop/bbs/{bbsCode}/selectDetail.do")
	public String selectArticleDetail(@ModelAttribute("searchVO") BoardVO boardVO, ModelMap model,
			@PathVariable("bbsCode") String bbsCode) throws Exception {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		boardVO.setLastUpdusrId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

		boardVO.setBbsId(checkBbsId(bbsCode));
		BoardVO vo = egovArticleService.selectArticleDetail(boardVO);

		if (vo.getAtchFileId() != null && !"".equals(vo.getAtchFileId())) {
			FileVO fileVO = new FileVO();
			fileVO.setAtchFileId(vo.getAtchFileId());
			model.addAttribute("fileList", fileService.selectFileInfs(fileVO));
		}

		model.addAttribute("result", vo);
		model.addAttribute("sessionUniqId", (user == null || user.getUniqId() == null) ? "" : user.getUniqId());

		// 비밀글은 작성자만 볼수 있음
		if (!EgovStringUtil.isEmpty(vo.getSecretAt()) && vo.getSecretAt().equals("Y")
				&& !((user == null || user.getUniqId() == null) ? "" : user.getUniqId()).equals(vo.getFrstRegisterId()))
			return "forward:/cmmn/cop/bbs/"+bbsCode+"/selectArticleList.do";

		// ----------------------------
		// template 처리 (기본 BBS template 지정 포함)
		// ----------------------------
		BoardMasterVO master = new BoardMasterVO();

		master.setBbsId(boardVO.getBbsId());
		master.setUniqId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

		BoardMasterVO masterVo = egovBBSMasterService.selectBBSMasterInf(master);

		if (masterVo.getTmplatCours() == null || masterVo.getTmplatCours().equals("")) {
			masterVo.setTmplatCours("/css/egovframework/com/cop/tpl/egovBaseTemplate.css");
		}

		//// -----------------------------

		// ----------------------------
		// 2009.06.29 : 2단계 기능 추가
		// 2011.07.01 : 댓글, 만족도 조사 기능의 종속성 제거
		// ----------------------------
		if (egovArticleCommentService != null) {
			if (egovArticleCommentService.canUseComment(boardVO.getBbsId())) {
				model.addAttribute("useComment", "true");
			}
		}
		if (bbsSatisfactionService != null) {
			if (bbsSatisfactionService.canUseSatisfaction(boardVO.getBbsId())) {
				model.addAttribute("useSatisfaction", "true");
			}
		}
		//// --------------------------

		model.addAttribute("boardMasterVO", masterVo);

		model.addAttribute("articleVO", boardVO);
		
		model.addAttribute("bbsCode", bbsCode);
		model.addAttribute("bbsId", boardVO.getBbsId());
		return "layout/user/cop/bbs/selectArticleDetail"; 
	}

}
