/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.objc.web;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.intermorph.cmmn.IMGlobals;
import com.intermorph.cmmn.base.BaseController;
import com.intermorph.cmmn.service.IMInfoInqHstryService;
import com.intermorph.cmmn.service.IMInfoInqHstryVO;
import com.intermorph.crs.agncy.service.IMAgncyService;
import com.intermorph.uss.objc.service.IMObjcAplyCondition;
import com.intermorph.uss.objc.service.IMObjcAplyResultSet;
import com.intermorph.uss.objc.service.IMObjcAplyService;
import com.intermorph.uss.objc.service.IMObjcAplyVO;

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
public class IMObjcAplyMngController extends BaseController {


    @Resource (name = "IMObjcAplyService")
	private IMObjcAplyService objcAplyService;
	
	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;
	


	@Resource(name = "IMAgncyService")
	private IMAgncyService agncyService;
	

	@Resource(name="IMInfoInqHstryService")
	IMInfoInqHstryService infoInqHstryService;
	
	/**
	 * 이의신청 목록
	 * 
	 * @param req
	 * @param res
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/objcAply/selectList.do")
	public ModelAndView selectList(HttpServletRequest req, HttpServletResponse res, IMObjcAplyCondition condition)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		setEmptyValue(condition, "currentPageNo=1", "recordCountPerPage=" + propertyService.getInt("pageUnit"),
				"pageSize=" + propertyService.getInt("pageSize"), "orderbyKey=0");

		mav.addObject("pageInfo", objcAplyService.selectListObjcAply(condition));

		mav.addObject("condition", condition);


		//개인정보 이력 등록 
		Gson gson = new Gson();
		infoInqHstryService.insertInfoInqHstry("im_objc_aply","SELECTNOID",IMGlobals.IM_INFOINQ_S,gson.toJson(condition),req);
		
		
		mav.setViewName("layout/mng/uss/objc/selectListObjcAply");

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
	@RequestMapping(value = "/mng/objcAply/modify.do")
	public ModelAndView modify(HttpServletRequest req, HttpServletResponse res, IMObjcAplyVO iMObjcAply,
			IMObjcAplyCondition condition) throws Exception {
		ModelAndView mav = new ModelAndView();

		IMObjcAplyResultSet detail = (IMObjcAplyResultSet) objcAplyService.selectDetailObjcAply(iMObjcAply);



		mav.addObject("iMObjcAply", detail.getObjcAply());
		mav.addObject("detail", detail);

		mav.addObject("condition", condition);
		mav.addObject("agncyList", agncyService.selectListAgncy());

		//개인정보 이력 등록 
		infoInqHstryService.insertInfoInqHstry("im_objc_aply",iMObjcAply.getObjcAplyId(),IMGlobals.IM_INFOINQ_R,"Checking User Questions",req);
		mav.setViewName("layout/mng/uss/objc/modifyObjcAply");
		
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
	@RequestMapping(value = "/mng/objcAply/update.do")
	public ModelAndView update(HttpServletRequest req, HttpServletResponse res,
			@ModelAttribute("iMObjcAply") IMObjcAplyVO iMObjcAply)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMObjcAply);

		mav.addObject("result",objcAplyService.updateObjcAply(iMObjcAply));
		
		mav.setViewName("jsonView");
		return mav;
	}

	
	
	
	
	

	
}