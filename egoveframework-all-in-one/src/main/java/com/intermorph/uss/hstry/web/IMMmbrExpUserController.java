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
import com.intermorph.uss.hstry.service.IMMmbrExpService;
import com.intermorph.uss.hstry.service.IMMmbrExpVO;
import com.intermorph.uss.hstry.service.IMMmbrWorkHstryVO;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.hstry.web
 * @File : IMMmbrExpController.java
 * @Title : 회원경력
 * @date : 2022.03.10 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Controller
public class IMMmbrExpUserController extends BaseController {


    @Resource (name = "IMMmbrExpService")
	private IMMmbrExpService mmbrExpService;
	
	
	

	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileService;

	
	
	
	
	/**
	 * 회원경력 목록
	 * 
	 * @param req
	 * @param res
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/mmbrExp/selectList.do")
	public ModelAndView selectList(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		Long memberSrl = Long.parseLong(IMStringUtil.getParameter(req, "memberSrl"));
		mav.addObject("selectExp", mmbrExpService.selectListMmbrExp(memberSrl));


		mav.setViewName("/view/user/uss/mmbrHstry/selectListMmbrExp");


		return mav;
	}
	
	
	
	/**
	 * 회원경력  등록 화면 
	 * @param req
	 * @param res
	 * @param iMMmbrExp
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/mmbrExp/regist.do")
	public ModelAndView regist(HttpServletRequest req, HttpServletResponse res, IMMmbrExpVO iMMmbrExp) throws Exception {
		ModelAndView mav = new ModelAndView();

		mav.addObject("iMMmbrExp", iMMmbrExp);

		mav.setViewName("/view/user/uss/mmbrHstry/registMmbrExp");

		return mav;
	}
	
	/**
	 * 회원경력 수정 화면 
	 * @param req
	 * @param res
	 * @param iMMmbrExp
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/mmbrExp/modify.do")
	public ModelAndView modify(HttpServletRequest req, HttpServletResponse res, IMMmbrExpVO iMMmbrExp) throws Exception {
		ModelAndView mav = new ModelAndView();


		IMMmbrExpVO detail = mmbrExpService.selectDetailMmbrExp(iMMmbrExp);

		mav.addObject("iMMmbrExp", detail);
		
		if (detail!=null ) {
			
			FileVO fileVO = null;
			if(detail.getIdntyFileId() != null && !"".equals(detail.getIdntyFileId())) {
				fileVO = new FileVO();
				fileVO.setAtchFileId(detail.getIdntyFileId());
				mav.addObject("fileList", fileService.selectFileInfs(fileVO));
			}
			if(detail.getAoasFileId() != null && !"".equals(detail.getAoasFileId())) {
				fileVO = new FileVO();
				fileVO.setAtchFileId(detail.getAoasFileId());
				mav.addObject("fileList2", fileService.selectFileInfs(fileVO));
			}
		}

		mav.setViewName("/view/user/uss/mmbrHstry/modifyMmbrExp");
		return mav;
	}
	
	
	
	
	
	/**
	 * 회원경력 등록 저장 
	 * @param req
	 * @param res
	 * @param iMmmbrExp
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/mmbrExp/insert.do")
	public ModelAndView insert(HttpServletRequest req, HttpServletResponse res,
			@ModelAttribute("iMMmbrExp") IMMmbrExpVO iMMmbrExp )
			throws Exception {

		ModelAndView mav = new ModelAndView();
		loginCheckSetAudit(req, iMMmbrExp);

		
		if(iMMmbrExp.getWorkBgnYmd()!=null && !"".equals(iMMmbrExp.getWorkBgnYmd())) {
			iMMmbrExp.setWorkBgnYmd(IMDateUtil.convertStartDate(iMMmbrExp.getWorkBgnYmd(), IMGlobals.IM_FORMAT_DATE, IMGlobals.IM_FORMAT_DBDATETIME,IMGlobals.IM_FORMAT_TIMEZONE));
		}
		
		if(iMMmbrExp.getWorkEndYmd()!=null && !"".equals(iMMmbrExp.getWorkEndYmd())) {
			iMMmbrExp.setWorkEndYmd(IMDateUtil.convertEndDate(iMMmbrExp.getWorkEndYmd(), IMGlobals.IM_FORMAT_DATE, IMGlobals.IM_FORMAT_DBDATETIME,IMGlobals.IM_FORMAT_TIMEZONE));
		}
		
		
		mav.addObject("result", mmbrExpService.insertMmbrExp(iMMmbrExp));

		mav.setViewName("jsonView");
		return mav;
	}
	
	/**
	 * 회원경력 수정 저장
	 * @param req
	 * @param res
	 * @param iMmmbrExp
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/mmbrExp/update.do")
	public ModelAndView update(HttpServletRequest req, HttpServletResponse res,
			@ModelAttribute("iMMmbrExp") IMMmbrExpVO iMMmbrExp)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMMmbrExp);

		if(iMMmbrExp.getWorkBgnYmd()!=null && !"".equals(iMMmbrExp.getWorkBgnYmd())) {
			iMMmbrExp.setWorkBgnYmd(IMDateUtil.convertStartDate(iMMmbrExp.getWorkBgnYmd(), IMGlobals.IM_FORMAT_DATE, IMGlobals.IM_FORMAT_DBDATETIME,IMGlobals.IM_FORMAT_TIMEZONE));
		}
		
		if(iMMmbrExp.getWorkEndYmd()!=null && !"".equals(iMMmbrExp.getWorkEndYmd())) {
			iMMmbrExp.setWorkEndYmd(IMDateUtil.convertEndDate(iMMmbrExp.getWorkEndYmd(), IMGlobals.IM_FORMAT_DATE, IMGlobals.IM_FORMAT_DBDATETIME,IMGlobals.IM_FORMAT_TIMEZONE));
		}
		
		mav.addObject("result",mmbrExpService.updateMmbrExp(iMMmbrExp));
		
		mav.setViewName("jsonView");
		return mav;
	}

	
	
	
	/**
	 *  회원경력  삭제
	 * @param req
	 * @param res
	 * @param iMmmbrExp
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/mmbrExp/delete.do")
	public ModelAndView delete(HttpServletRequest req, HttpServletResponse res, IMMmbrExpVO iMMmbrExp)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMMmbrExp);

		mav.addObject("result", mmbrExpService.deleteMmbrExp(iMMmbrExp));

		mav.setViewName("jsonView");
		return mav;
	}
	
	

	
	

	
}