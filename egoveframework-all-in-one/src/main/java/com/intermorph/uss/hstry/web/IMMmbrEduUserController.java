/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.hstry.web;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.intermorph.cmmn.IMGlobals;
import com.intermorph.cmmn.base.BaseController;
import com.intermorph.cmmn.util.IMDateUtil;
import com.intermorph.cmmn.util.IMStringUtil;
import com.intermorph.uss.hstry.service.IMMmbrEduService;
import com.intermorph.uss.hstry.service.IMMmbrEduVO;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.hstry.web
 * @File : IMMmbrEduController.java
 * @Title : 회원학력
 * @date : 2022.03.10 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Controller
public class IMMmbrEduUserController extends BaseController {


    @Resource (name = "IMMmbrEduService")
	private IMMmbrEduService mmbrEduService;
	
	


	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileService;

	
	
	/**
	 * 회원학력 목록
	 * 
	 * @param req
	 * @param res
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/mmbrEdu/selectList.do")
	public ModelAndView selectList(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		Long memberSrl = Long.parseLong(IMStringUtil.getParameter(req, "memberSrl"));
		mav.addObject("selectEdu", mmbrEduService.selectListMmbrEdu(memberSrl));


		mav.setViewName("/view/user/uss/mmbrHstry/selectListMmbrEdu");


		return mav;
	}
	
	
	
	/**
	 * 회원학력  등록 화면 
	 * @param req
	 * @param res
	 * @param iMMmbrEdu
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/mmbrEdu/regist.do")
	public ModelAndView regist(HttpServletRequest req, HttpServletResponse res, IMMmbrEduVO iMMmbrEdu) throws Exception {
		ModelAndView mav = new ModelAndView();

		mav.addObject("iMMmbrEdu", iMMmbrEdu);


		mav.setViewName("/view/user/uss/mmbrHstry/registMmbrEdu");

		return mav;
	}
	
	/**
	 * 회원학력 수정 화면 
	 * @param req
	 * @param res
	 * @param iMMmbrEdu
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/mmbrEdu/modify.do")
	public ModelAndView modify(HttpServletRequest req, HttpServletResponse res, IMMmbrEduVO iMMmbrEdu) throws Exception {
		ModelAndView mav = new ModelAndView();

		IMMmbrEduVO detail =mmbrEduService.selectDetailMmbrEdu(iMMmbrEdu);
		mav.addObject("iMMmbrEdu", detail);
		
		if(detail.getDgeFileId() != null && !"".equals(detail.getDgeFileId())) {
			FileVO fileVO = new FileVO();
			fileVO.setAtchFileId(detail.getDgeFileId());
			mav.addObject("fileList", fileService.selectFileInfs(fileVO));
		}
		

		mav.setViewName("/view/user/uss/mmbrHstry/modifyMmbrEdu");
		
		return mav;
	}
	
	
	
	
	
	/**
	 * 회원학력 등록 저장 
	 * @param req
	 * @param res
	 * @param iMmmbrEdu
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/mmbrEdu/insert.do")
	public ModelAndView insert(HttpServletRequest req, HttpServletResponse res,
			@ModelAttribute("iMMmbrEdu") IMMmbrEduVO iMMmbrEdu)
			throws Exception {

		ModelAndView mav = new ModelAndView();
		loginCheckSetAudit(req, iMMmbrEdu);

		
		if(iMMmbrEdu.getDgeYmd()!=null && !"".equals(iMMmbrEdu.getDgeYmd())) {
			iMMmbrEdu.setDgeYmd(IMDateUtil.convertStartDate(iMMmbrEdu.getDgeYmd(), IMGlobals.IM_FORMAT_DATE, IMGlobals.IM_FORMAT_DBDATETIME,IMGlobals.IM_FORMAT_TIMEZONE));
		}

		mav.addObject("result", mmbrEduService.insertMmbrEdu(iMMmbrEdu));

		mav.setViewName("jsonView");
		return mav;
	}
	
	/**
	 * 회원학력 수정 저장
	 * @param req
	 * @param res
	 * @param iMmmbrEdu
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/mmbrEdu/update.do")
	public ModelAndView update(HttpServletRequest req, HttpServletResponse res,
			@ModelAttribute("iMMmbrEdu") IMMmbrEduVO iMMmbrEdu)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMMmbrEdu);

		
		if(iMMmbrEdu.getDgeYmd()!=null && !"".equals(iMMmbrEdu.getDgeYmd())) {
			iMMmbrEdu.setDgeYmd(IMDateUtil.convertStartDate(iMMmbrEdu.getDgeYmd(), IMGlobals.IM_FORMAT_DATE, IMGlobals.IM_FORMAT_DBDATETIME,IMGlobals.IM_FORMAT_TIMEZONE));
		}
		
		mav.addObject("result",mmbrEduService.updateMmbrEdu(iMMmbrEdu));
		
		mav.setViewName("jsonView");
		return mav;
	}

	
	
	
	/**
	 *  회원학력  삭제
	 * @param req
	 * @param res
	 * @param iMmmbrEdu
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/mmbrEdu/delete.do")
	public ModelAndView delete(HttpServletRequest req, HttpServletResponse res, IMMmbrEduVO iMMmbrEdu)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMMmbrEdu);

		mav.addObject("result", mmbrEduService.deleteMmbrEdu(iMMmbrEdu));

		mav.setViewName("jsonView");
		return mav;
	}
	
	
	
	
	

	
}