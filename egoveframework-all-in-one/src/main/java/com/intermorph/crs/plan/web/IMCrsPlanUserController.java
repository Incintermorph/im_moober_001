/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.crs.plan.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.intermorph.cmmn.IMGlobals;
import com.intermorph.cmmn.base.BaseController;
import com.intermorph.crs.plan.service.IMCrsPlanCondition;
import com.intermorph.crs.plan.service.IMCrsPlanService;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.crs.plan.web
 * @File    : IMCrsPlanUserController.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 3. 2
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
@Controller
public class IMCrsPlanUserController extends BaseController {


    @Resource (name = "IMCrsPlanService")
	private IMCrsPlanService crsPlanService;
	

	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;
	/**
	 * 과정계획 목록
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmmn/crsPlan/selectList.do")
	public ModelAndView selectList(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		IMCrsPlanCondition condition = new IMCrsPlanCondition();
		condition.setScEduYear(IMGlobals.IM_NOW_YEAR);
		condition.setScEduAplTermStartYear(IMGlobals.IM_NOW_YEAR);
		mav.addObject("list", crsPlanService.selectListCrsPlan(condition).getList());
		mav.addObject("condition", condition);

		mav.setViewName("layout/user/crs/crsPlan/selectListCrsPlan");

		return mav;
	}
}
