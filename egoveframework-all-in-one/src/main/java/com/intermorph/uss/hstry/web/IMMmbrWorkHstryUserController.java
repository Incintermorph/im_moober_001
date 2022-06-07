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
import com.intermorph.uss.hstry.service.IMMmbrWorkHstryService;
import com.intermorph.uss.hstry.service.IMMmbrWorkHstryVO;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.hstry.web
 * @File : IMMmbrWorkHstryController.java
 * @Title : 회원근무이력
 * @date : 2022.03.11 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Controller
public class IMMmbrWorkHstryUserController extends BaseController {


    @Resource (name = "IMMmbrWorkHstryService")
	private IMMmbrWorkHstryService mmbrWorkHstryService;
	
	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;



	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileService;

	
	
	/**
	 * 회원근무이력 목록
	 * 
	 * @param req
	 * @param res
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/mmbrWorkHstry/selectList.do")
	public ModelAndView selectList(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		Long memberSrl = Long.parseLong(IMStringUtil.getParameter(req, "memberSrl"));
		mav.addObject("selectWorkHstry", mmbrWorkHstryService.selectListMmbrWorkHstry(memberSrl));
		mav.addObject("selectWorkHstryDiffSum", mmbrWorkHstryService.selectListDiffSum(memberSrl));

		mav.setViewName("/view/user/uss/mmbrHstry/selectListMmbrWorkHstry");

		return mav;
	}
	
	
	
	/**
	 * 회원근무이력  등록 화면 
	 * @param req
	 * @param res
	 * @param iMMmbrWorkHstry
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/mmbrWorkHstry/regist.do")
	public ModelAndView regist(HttpServletRequest req, HttpServletResponse res, IMMmbrWorkHstryVO iMMmbrWorkHstry) throws Exception {
		ModelAndView mav = new ModelAndView();

		mav.addObject("iMMmbrWorkHstry", iMMmbrWorkHstry);
		

		


		mav.setViewName("/view/user/uss/mmbrHstry/registMmbrWorkHstry");

		return mav;
	}
	
	/**
	 * 회원근무이력 수정 화면 
	 * @param req
	 * @param res
	 * @param iMMmbrWorkHstry
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/mmbrWorkHstry/modify.do")
	public ModelAndView modify(HttpServletRequest req, HttpServletResponse res, IMMmbrWorkHstryVO iMMmbrWorkHstry) throws Exception {
		ModelAndView mav = new ModelAndView();
		IMMmbrWorkHstryVO detail = mmbrWorkHstryService.selectDetailMmbrWorkHstry(iMMmbrWorkHstry);

		mav.addObject("iMMmbrWorkHstry", detail);
		if (detail!=null ) {
			
			FileVO fileVO = null;
			if(detail.getEvddocFileId() != null && !"".equals(detail.getEvddocFileId())) {
				fileVO = new FileVO();
				fileVO.setAtchFileId(detail.getEvddocFileId());
				mav.addObject("fileList", fileService.selectFileInfs(fileVO));
			}
			if(detail.getDsgnFileId() != null && !"".equals(detail.getDsgnFileId())) {
				fileVO = new FileVO();
				fileVO.setAtchFileId(detail.getDsgnFileId());
				mav.addObject("fileList2", fileService.selectFileInfs(fileVO));
			}
		}
		

		mav.setViewName("/view/user/uss/mmbrHstry/modifyMmbrWorkHstry");
		
		return mav;
	}
	
	
	
	
	
	/**
	 * 회원근무이력 등록 저장 
	 * @param req
	 * @param res
	 * @param iMmmbrWorkHstry
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/mmbrWorkHstry/insert.do")
	public ModelAndView insert(HttpServletRequest req, HttpServletResponse res,
			@ModelAttribute("iMMmbrWorkHstry") IMMmbrWorkHstryVO iMMmbrWorkHstry )
			throws Exception {

		ModelAndView mav = new ModelAndView();
		loginCheckSetAudit(req, iMMmbrWorkHstry);
		setEmptyValue(iMMmbrWorkHstry, "workYn=N", "eduDsgnYn=N");
		if(iMMmbrWorkHstry.getWorkBgnYmd()!=null && !"".equals(iMMmbrWorkHstry.getWorkBgnYmd())) {
			iMMmbrWorkHstry.setWorkBgnYmd(IMDateUtil.convertStartDate(iMMmbrWorkHstry.getWorkBgnYmd(), IMGlobals.IM_FORMAT_DATE, IMGlobals.IM_FORMAT_DBDATETIME,IMGlobals.IM_FORMAT_TIMEZONE));
		}
		
		if(iMMmbrWorkHstry.getWorkEndYmd()!=null && !"".equals(iMMmbrWorkHstry.getWorkEndYmd())) {
			iMMmbrWorkHstry.setWorkEndYmd(IMDateUtil.convertEndDate(iMMmbrWorkHstry.getWorkEndYmd(), IMGlobals.IM_FORMAT_DATE, IMGlobals.IM_FORMAT_DBDATETIME,IMGlobals.IM_FORMAT_TIMEZONE));
		}
		
		
		mav.addObject("result", mmbrWorkHstryService.insertMmbrWorkHstry(iMMmbrWorkHstry));

		mav.setViewName("jsonView");
		return mav;
	}
	
	/**
	 * 회원근무이력 수정 저장
	 * @param req
	 * @param res
	 * @param iMmmbrWorkHstry
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/mmbrWorkHstry/update.do")
	public ModelAndView update(HttpServletRequest req, HttpServletResponse res,
			@ModelAttribute("iMMmbrWorkHstry") IMMmbrWorkHstryVO iMMmbrWorkHstry)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMMmbrWorkHstry);

		setEmptyValue(iMMmbrWorkHstry, "workYn=N", "eduDsgnYn=N");
		
		if(iMMmbrWorkHstry.getWorkBgnYmd()!=null && !"".equals(iMMmbrWorkHstry.getWorkBgnYmd())) {
			iMMmbrWorkHstry.setWorkBgnYmd(IMDateUtil.convertStartDate(iMMmbrWorkHstry.getWorkBgnYmd(), IMGlobals.IM_FORMAT_DATE, IMGlobals.IM_FORMAT_DBDATETIME,IMGlobals.IM_FORMAT_TIMEZONE));
		}
		
		if(iMMmbrWorkHstry.getWorkEndYmd()!=null && !"".equals(iMMmbrWorkHstry.getWorkEndYmd())) {
			iMMmbrWorkHstry.setWorkEndYmd(IMDateUtil.convertEndDate(iMMmbrWorkHstry.getWorkEndYmd(), IMGlobals.IM_FORMAT_DATE, IMGlobals.IM_FORMAT_DBDATETIME,IMGlobals.IM_FORMAT_TIMEZONE));
		}
		
		
		
		
		mav.addObject("result",mmbrWorkHstryService.updateMmbrWorkHstry(iMMmbrWorkHstry));
		
		mav.setViewName("jsonView");
		return mav;
	}

	
	/**
	 *  회원근무이력  삭제
	 * @param req
	 * @param res
	 * @param iMmmbrWorkHstry
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/mmbrWorkHstry/delete.do")
	public ModelAndView delete(HttpServletRequest req, HttpServletResponse res, IMMmbrWorkHstryVO iMMmbrWorkHstry)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMMmbrWorkHstry);

		mav.addObject("result", mmbrWorkHstryService.deleteMmbrWorkHstry(iMMmbrWorkHstry));

		mav.setViewName("jsonView");
		return mav;
	}
	
	
	
}