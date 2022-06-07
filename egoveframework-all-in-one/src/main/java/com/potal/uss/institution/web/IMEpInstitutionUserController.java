/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.potal.uss.institution.web;



import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.intermorph.cmmn.base.BaseController;
import com.potal.uss.institution.service.IMEpInstitutionCondition;
import com.potal.uss.institution.service.IMEpInstitutionResultSet;
import com.potal.uss.institution.service.IMEpInstitutionService;
import com.potal.uss.institution.service.IMEpInstitutionVO;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.potal.uss.institution.web
 * @File : IMEpInstitutionController.java
 * @Title : 기관(포탈)
 * @date : 2022.03.29 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Controller
public class IMEpInstitutionUserController extends BaseController {


    @Resource (name = "IMEpInstitutionService")
	private IMEpInstitutionService epInstitutionService;
	
	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;


	
	
	/**
	 * 기관(포탈) 목록
	 * 
	 * @param req
	 * @param res
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/epInstitution/selectList.do")
	public ModelAndView selectList(HttpServletRequest req, HttpServletResponse res, IMEpInstitutionCondition condition)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		setEmptyValue(condition, "currentPageNo=1", "recordCountPerPage=" + propertyService.getInt("pageUnit"),
				"pageSize=" + propertyService.getInt("pageSize"), "orderbyKey=0");

		mav.addObject("pageInfo", epInstitutionService.selectListEpInstitution(condition));

		mav.addObject("condition", condition);

		mav.setViewName("/view/user/uss/epInstitution/selectListEpInstitution");

		return mav;
	}
	
	
	
	/**
	 * 기관(포탈)  등록 화면 
	 * @param req
	 * @param res
	 * @param iMEpInstitution
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/epInstitution/regist.do")
	public ModelAndView regist(HttpServletRequest req, HttpServletResponse res, IMEpInstitutionVO iMEpInstitution,
			IMEpInstitutionCondition condition) throws Exception {
		ModelAndView mav = new ModelAndView();

		mav.addObject("iMEpInstitution", iMEpInstitution);

		mav.addObject("condition", condition);

		/** todo : jsp  setting */
		mav.setViewName("layout/user/toDo/epInstitution/registEpInstitution");

		return mav;
	}
	
	/**
	 * 기관(포탈) 수정 화면 
	 * @param req
	 * @param res
	 * @param iMEpInstitution
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/epInstitution/modify.do")
	public ModelAndView modify(HttpServletRequest req, HttpServletResponse res, IMEpInstitutionVO iMEpInstitution,
			IMEpInstitutionCondition condition) throws Exception {
		ModelAndView mav = new ModelAndView();

		IMEpInstitutionResultSet detail = (IMEpInstitutionResultSet) epInstitutionService.selectDetailEpInstitution(iMEpInstitution);

		mav.addObject("iMEpInstitution", detail.getEpInstitution());

		mav.addObject("condition", condition);
		
		
		/** todo : jsp  setting */
		mav.setViewName("layout/user/toDo/epInstitution/modifyEpInstitution");
		
		return mav;
	}
	
	
	
	
	
	/**
	 * 기관(포탈) 등록 저장 
	 * @param req
	 * @param res
	 * @param iMepInstitution
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/epInstitution/insert.do")
	public ModelAndView insert(HttpServletRequest req, HttpServletResponse res,
			@ModelAttribute("iMEpInstitution") IMEpInstitutionVO iMEpInstitution ,
			BindingResult bindingResult, ModelMap model)
			throws Exception {

		ModelAndView mav = new ModelAndView();
		loginCheckSetAudit(req, iMEpInstitution);

		mav.addObject("result", epInstitutionService.insertEpInstitution(iMEpInstitution));

		mav.setViewName("jsonView");
		return mav;
	}
	
	/**
	 * 기관(포탈) 수정 저장
	 * @param req
	 * @param res
	 * @param iMepInstitution
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/epInstitution/update.do")
	public ModelAndView update(HttpServletRequest req, HttpServletResponse res,
			@ModelAttribute("iMEpInstitution") IMEpInstitutionVO iMEpInstitution,
			BindingResult bindingResult, ModelMap model)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMEpInstitution);

		if (bindingResult.hasErrors()) { // 만일 validation 에러가 있으면...
			mav.addObject("result", -1);
		}

		mav.addObject("result",epInstitutionService.updateEpInstitution(iMEpInstitution));
		
		mav.setViewName("jsonView");
		return mav;
	}

	
	
	
	
	
	

	
}