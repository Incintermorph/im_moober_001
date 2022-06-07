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

import com.intermorph.cmmn.base.BaseController;
import com.intermorph.cmmn.util.IMStringUtil;
import com.intermorph.uss.hstry.service.IMMmbrEduVO;
import com.intermorph.uss.hstry.service.IMMmbrEtcService;
import com.intermorph.uss.hstry.service.IMMmbrEtcVO;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.hstry.web
 * @File : IMMmbrEtcController.java
 * @Title : 회원기타정보
 * @date : 2022.03.10 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Controller
public class IMMmbrEtcUserController extends BaseController {


    @Resource (name = "IMMmbrEtcService")
	private IMMmbrEtcService mmbrEtcService;
	


	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileService;


	
	
	/**
	 * 회원기타정보 목록
	 * 
	 * @param req
	 * @param res
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/mmbrEtc/selectList.do")
	public ModelAndView selectList(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		
		Long memberSrl = Long.parseLong(IMStringUtil.getParameter(req, "memberSrl"));
		mav.addObject("selectEtc", mmbrEtcService.selectListMmbrEtc(memberSrl));


		mav.setViewName("/view/user/uss/mmbrHstry/selectListMmbrEtc");


		return mav;
	}
	
	
	
	/**
	 * 회원기타정보  등록 화면 
	 * @param req
	 * @param res
	 * @param iMMmbrEtc
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/mmbrEtc/regist.do")
	public ModelAndView regist(HttpServletRequest req, HttpServletResponse res, IMMmbrEtcVO iMMmbrEtc) throws Exception {
		ModelAndView mav = new ModelAndView();

		mav.addObject("iMMmbrEtc", iMMmbrEtc);

		mav.setViewName("/view/user/uss/mmbrHstry/registMmbrEtc");

		return mav;
	}
	
	/**
	 * 회원기타정보 수정 화면 
	 * @param req
	 * @param res
	 * @param iMMmbrEtc
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/mmbrEtc/modify.do")
	public ModelAndView modify(HttpServletRequest req, HttpServletResponse res, IMMmbrEtcVO iMMmbrEtc) throws Exception {
		ModelAndView mav = new ModelAndView();

		

		
		IMMmbrEtcVO detail =mmbrEtcService.selectDetailMmbrEtc(iMMmbrEtc);
		mav.addObject("iMMmbrEtc", detail);
		
		if(detail.getEtcEvddocId() != null && !"".equals(detail.getEtcEvddocId())) {
			FileVO fileVO = new FileVO();
			fileVO.setAtchFileId(detail.getEtcEvddocId());
			mav.addObject("fileList", fileService.selectFileInfs(fileVO));
		}

		mav.setViewName("/view/user/uss/mmbrHstry/modifyMmbrEtc");
		
		
		return mav;
	}
	
	
	
	
	
	/**
	 * 회원기타정보 등록 저장 
	 * @param req
	 * @param res
	 * @param iMmmbrEtc
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/mmbrEtc/insert.do")
	public ModelAndView insert(HttpServletRequest req, HttpServletResponse res,
			@ModelAttribute("iMMmbrEtc") IMMmbrEtcVO iMMmbrEtc)
			throws Exception {

		ModelAndView mav = new ModelAndView();
		loginCheckSetAudit(req, iMMmbrEtc);

		mav.addObject("result", mmbrEtcService.insertMmbrEtc(iMMmbrEtc));

		mav.setViewName("jsonView");
		return mav;
	}
	
	/**
	 * 회원기타정보 수정 저장
	 * @param req
	 * @param res
	 * @param iMmmbrEtc
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/mmbrEtc/update.do")
	public ModelAndView update(HttpServletRequest req, HttpServletResponse res,
			@ModelAttribute("iMMmbrEtc") IMMmbrEtcVO iMMmbrEtc)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMMmbrEtc);


		mav.addObject("result",mmbrEtcService.updateMmbrEtc(iMMmbrEtc));
		
		mav.setViewName("jsonView");
		return mav;
	}

	
	
	
	/**
	 *  회원기타정보  삭제
	 * @param req
	 * @param res
	 * @param iMmmbrEtc
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/mmbrEtc/delete.do")
	public ModelAndView delete(HttpServletRequest req, HttpServletResponse res, IMMmbrEtcVO iMMmbrEtc)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMMmbrEtc);

		mav.addObject("result", mmbrEtcService.deleteMmbrEtc(iMMmbrEtc));

		mav.setViewName("jsonView");
		return mav;
	}
	
	
	
	
	

	
}