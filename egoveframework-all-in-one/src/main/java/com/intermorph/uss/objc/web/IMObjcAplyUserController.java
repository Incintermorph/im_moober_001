/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.objc.web;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.google.gson.Gson;
import com.intermorph.cmmn.base.BaseController;
import com.intermorph.cmmn.exception.IMProcErrors;
import com.intermorph.cmmn.exception.IMProcException;
import com.intermorph.crs.agncy.service.IMAgncyService;
import com.intermorph.uss.objc.service.IMObjcAplyCondition;
import com.intermorph.uss.objc.service.IMObjcAplyDtlDTO;
import com.intermorph.uss.objc.service.IMObjcAplyResultSet;
import com.intermorph.uss.objc.service.IMObjcAplyService;
import com.intermorph.uss.objc.service.IMObjcAplyVO;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.objc.web
 * @File : IMObjcAplyController.java
 * @Title : 이의신청
 * @date : 2022.04.06 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Controller
public class IMObjcAplyUserController extends BaseController {


    @Resource (name = "IMObjcAplyService")
	private IMObjcAplyService objcAplyService;
	
    

	@Resource(name = "IMAgncyService")
	private IMAgncyService agncyService;
	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;

	@Autowired
	private DefaultBeanValidator beanValidator;
	

	
	
	/**
	 * 이의신청 목록
	 * 
	 * @param req
	 * @param res
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/objcAply/selectList.do")
	public ModelAndView selectList(HttpServletRequest req, HttpServletResponse res, IMObjcAplyCondition condition)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		setEmptyValue(condition, "currentPageNo=1", "recordCountPerPage=" + propertyService.getInt("pageUnit"),
				"pageSize=" + propertyService.getInt("pageSize"), "orderbyKey=0");


		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		condition.setScMmbrEsntlId(user.getUniqId());
		
		mav.addObject("pageInfo", objcAplyService.selectListObjcAply(condition));

		mav.addObject("condition", condition);

		mav.setViewName("layout/user/uss/objc/selectListObjcAply");

		return mav;
	}
	
	
	
	/**
	 * 이의신청  등록 화면 
	 * @param req
	 * @param res
	 * @param iMObjcAply
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/objcAply/regist.do")
	public ModelAndView regist(HttpServletRequest req, HttpServletResponse res, IMObjcAplyVO iMObjcAply,
			IMObjcAplyCondition condition) throws Exception {
		ModelAndView mav = new ModelAndView();

		mav.addObject("iMObjcAply", iMObjcAply);
		
		mav.addObject("agncyList", agncyService.selectListAgncyDsgn());
		
		mav.addObject("condition", condition);

		mav.setViewName("layout/user/uss/objc/registObjcAply");

		return mav;
	}
	
	/**
	 * 이의신청 상세 화면 
	 * @param req
	 * @param res
	 * @param iMObjcAply
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/objcAply/selectDetail.do")
	public ModelAndView selectDetail(HttpServletRequest req, HttpServletResponse res, IMObjcAplyVO iMObjcAply,
			IMObjcAplyCondition condition) throws Exception {
		ModelAndView mav = new ModelAndView();

		IMObjcAplyResultSet detail = (IMObjcAplyResultSet) objcAplyService.selectDetailObjcAply(iMObjcAply);

		
		if(detail==null) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		
		mav.addObject("iMObjcAply", detail.getObjcAply());
		mav.addObject("detail", detail);
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		if(!detail.getObjcAply().getFrstRegerId().equals(user.getUniqId())) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}

		
		
		mav.addObject("condition", condition);
		mav.addObject("agncyList", agncyService.selectListAgncy());
		
		mav.setViewName("layout/user/uss/objc/selectDetailObjcAply");
		
		return mav;
	}
	
	/**
	 * 이의신청 수정 화면 
	 * @param req
	 * @param res
	 * @param iMObjcAply
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/objcAply/modify.do")
	public ModelAndView modify(HttpServletRequest req, HttpServletResponse res, IMObjcAplyVO iMObjcAply,
			IMObjcAplyCondition condition) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		IMObjcAplyResultSet detail = (IMObjcAplyResultSet) objcAplyService.selectDetailObjcAply(iMObjcAply);
		if(detail==null) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		mav.addObject("iMObjcAply", detail.getObjcAply());
		mav.addObject("detail", detail);

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(!detail.getObjcAply().getFrstRegerId().equals(user.getUniqId())) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		
		if(!"01".equals(detail.getObjcAply().getSttsCdv())) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}

		mav.addObject("condition", condition);
		mav.addObject("agncyList", agncyService.selectListAgncy());
		
		mav.setViewName("layout/user/uss/objc/modifyObjcAply");
		
		return mav;
	}
	
	
	
	
	
	/**
	 * 이의신청 등록 저장 
	 * @param req
	 * @param res
	 * @param iMobjcAply
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/objcAply/insert.do")
	public ModelAndView insert(HttpServletRequest req, HttpServletResponse res,
			@ModelAttribute("iMObjcAply") IMObjcAplyVO iMObjcAply ,IMObjcAplyDtlDTO dtl,
			BindingResult bindingResult, ModelMap model)
			throws Exception {

		ModelAndView mav = new ModelAndView();
		loginCheckSetAudit(req, iMObjcAply);
		//등록자 정보 세팅 
		iMObjcAply.setMmbrEsntlId(iMObjcAply.getFrstRegerId());
		
		
		Gson gson = new Gson();
		
		iMObjcAply.setObjcAplyDtl(gson.toJson(dtl));
		
		
		beanValidator.validate(iMObjcAply, bindingResult); // validation 수행

		if (bindingResult.hasErrors()) { // 만일 validation 에러가 있으면...
			mav.addObject("result", -1);
		}
		

		mav.addObject("result", objcAplyService.insertObjcAply(iMObjcAply));

		mav.setViewName("jsonView");
		return mav;
	}
	
	/**
	 * 이의신청 수정 저장
	 * @param req
	 * @param res
	 * @param iMobjcAply
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/objcAply/update.do")
	public ModelAndView update(HttpServletRequest req, HttpServletResponse res,
			@ModelAttribute("iMObjcAply") IMObjcAplyVO iMObjcAply,IMObjcAplyDtlDTO dtl,
			BindingResult bindingResult, ModelMap model)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMObjcAply);
		IMObjcAplyResultSet detail = (IMObjcAplyResultSet) objcAplyService.selectDetailObjcAply(iMObjcAply);
		if(detail==null) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(!detail.getObjcAply().getFrstRegerId().equals(user.getUniqId())) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		
		if(!"01".equals(detail.getObjcAply().getSttsCdv())) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		
		Gson gson = new Gson();
		iMObjcAply.setObjcAplyDtl(gson.toJson(dtl));
		
		if (bindingResult.hasErrors()) { // 만일 validation 에러가 있으면...
			mav.addObject("result", -1);
		}

		mav.addObject("result",objcAplyService.updateObjcAply(iMObjcAply));
		
		mav.setViewName("jsonView");
		return mav;
	}

	
	
	
	/**
	 *  이의신청  삭제
	 * @param req
	 * @param res
	 * @param iMobjcAply
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/objcAply/delete.do")
	public ModelAndView delete(HttpServletRequest req, HttpServletResponse res, IMObjcAplyVO iMObjcAply)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMObjcAply);

		mav.addObject("result", objcAplyService.deleteObjcAply(iMObjcAply));

		mav.setViewName("jsonView");
		return mav;
	}
	
	
	
	

	
}