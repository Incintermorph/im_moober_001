/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.crs.plan.web;


import java.util.ArrayList;
import java.util.List;

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

import com.intermorph.cmmn.IMGlobals;
import com.intermorph.cmmn.base.BaseController;
import com.intermorph.cmmn.service.IMCmmnDtService;
import com.intermorph.cmmn.service.IMCmmnDtVO;
import com.intermorph.cmmn.util.IMStringUtil;
import com.intermorph.crs.agncy.service.IMAgncyService;
import com.intermorph.crs.agncy.service.IMAgncyVO;
import com.intermorph.crs.plan.service.IMCrsPlanCondition;
import com.intermorph.crs.plan.service.IMCrsPlanResultSet;
import com.intermorph.crs.plan.service.IMCrsPlanService;
import com.intermorph.crs.plan.service.IMCrsPlanVO;


import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.crs.plan.web
 * @File : IMCrsPlanController.java
 * @Title : 과정계획
 * @date : 2022.03.01 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Controller
public class IMCrsPlanMngController extends BaseController {


    @Resource (name = "IMCrsPlanService")
	private IMCrsPlanService crsPlanService;
	

	@Resource(name = "IMAgncyService")
	private IMAgncyService agncyService;
	

	@Resource(name = "IMCmmnDtService")
	private IMCmmnDtService cmmnDtService;
	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;

	@Autowired
	private DefaultBeanValidator beanValidator;
	

	private String cmmmDescTblId = "IM_CRS_PLAN";
	
	
	/**
	 * 과정계획 목록
	 * 
	 * @param req
	 * @param res
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/crsPlan/selectList.do")
	public ModelAndView selectList(HttpServletRequest req, HttpServletResponse res, IMCrsPlanCondition condition)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		setEmptyValue(condition, "currentPageNo=1", "recordCountPerPage=" + propertyService.getInt("pageUnit"),
				"pageSize=" + propertyService.getInt("pageSize"), "orderbyKey=0");
		String loginAgncyId = IMStringUtil.getAttribute(req, IMGlobals.IM_LOGIN_USER_AGNCY_KEY);
		if (!"".equals(loginAgncyId)) {
			condition.setScAgncyId(loginAgncyId);
		}else {

			mav.addObject("agncyList", agncyService.selectListAgncy());
		}
		mav.addObject("pageInfo", crsPlanService.selectListCrsPlan(condition));

		mav.addObject("condition", condition);

		mav.setViewName("layout/mng/crs/crsPlan/selectListCrsPlan");

		return mav;
	}
	
	
	
	/**
	 * 과정계획  등록 화면 
	 * @param req
	 * @param res
	 * @param iMCrsPlan
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/crsPlan/regist.do")
	public ModelAndView regist(HttpServletRequest req, HttpServletResponse res, IMCrsPlanVO iMCrsPlan,
			IMCrsPlanCondition condition) throws Exception {
		ModelAndView mav = new ModelAndView();

		mav.addObject("iMCrsPlan", iMCrsPlan);

		mav.addObject("condition", condition);
		
		String loginAgncyId = IMStringUtil.getAttribute(req, IMGlobals.IM_LOGIN_USER_AGNCY_KEY);
		if (!"".equals(loginAgncyId)) {
			IMAgncyVO agVo = new IMAgncyVO();
			agVo.setAgncyId(loginAgncyId);

			mav.addObject("agncyDetail", agncyService.selectDetailAgncy(agVo));
		} else {

			mav.addObject("agncyList", agncyService.selectListAgncy());
		}

		mav.setViewName("layout/mng/crs/crsPlan/registCrsPlan");

		return mav;
	}
	
	/**
	 * 과정계획 수정 화면 
	 * @param req
	 * @param res
	 * @param iMCrsPlan
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/crsPlan/modify.do")
	public ModelAndView modify(HttpServletRequest req, HttpServletResponse res, IMCrsPlanVO iMCrsPlan,
			IMCrsPlanCondition condition) throws Exception {
		ModelAndView mav = new ModelAndView();

		IMCrsPlanResultSet detail = (IMCrsPlanResultSet) crsPlanService.selectDetailCrsPlan(iMCrsPlan);

		mav.addObject("iMCrsPlan", detail.getCrsPlan());

		mav.addObject("detail", detail);

		mav.addObject("condition", condition);
		String loginAgncyId = IMStringUtil.getAttribute(req, IMGlobals.IM_LOGIN_USER_AGNCY_KEY);
		if (!"".equals(loginAgncyId)) {
			IMAgncyVO agVo = new IMAgncyVO();
			agVo.setAgncyId(loginAgncyId);

			mav.addObject("agncyDetail", agncyService.selectDetailAgncy(agVo));
		} else {

			mav.addObject("agncyList", agncyService.selectListAgncy());
		}

		mav.addObject("cmmmDtMap", cmmnDtService.selectListCmmnDtResultMap(cmmmDescTblId, iMCrsPlan.getCrsPlanId()));
		
		
		mav.setViewName("layout/mng/crs/crsPlan/modifyCrsPlan");
		
		return mav;
	}
	
	
	
	
	
	/**
	 * 과정계획 등록 저장 
	 * @param req
	 * @param res
	 * @param iMcrsPlan
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/crsPlan/insert.do")
	public ModelAndView insert(HttpServletRequest req, HttpServletResponse res,
			@ModelAttribute("iMCrsPlan") IMCrsPlanVO iMCrsPlan ,
			IMCmmnDtVO cmmnDt,
			BindingResult bindingResult, ModelMap model)
			throws Exception {

		ModelAndView mav = new ModelAndView();
		loginCheckSetAudit(req, iMCrsPlan,cmmnDt);
		beanValidator.validate(iMCrsPlan, bindingResult); // validation 수행

		if (bindingResult.hasErrors()) { // 만일 validation 에러가 있으면...
			mav.addObject("result", -1);
		}

		mav.addObject("result", crsPlanService.insertCrsPlan(iMCrsPlan));
		

		cmmnDtService.insertlistCmmnDt(cmmmDescTblId, iMCrsPlan.getCrsPlanId(), req, cmmnDt);

		mav.setViewName("jsonView");
		return mav;
	}
	
	/**
	 * 과정계획 수정 저장
	 * @param req
	 * @param res
	 * @param iMcrsPlan
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/crsPlan/update.do")
	public ModelAndView update(HttpServletRequest req, HttpServletResponse res,
			@ModelAttribute("iMCrsPlan") IMCrsPlanVO iMCrsPlan,
			IMCmmnDtVO cmmnDt,
			BindingResult bindingResult, ModelMap model)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMCrsPlan,cmmnDt);

		if (bindingResult.hasErrors()) { // 만일 validation 에러가 있으면...
			mav.addObject("result", -1);
		}

		mav.addObject("result",crsPlanService.updateCrsPlan(iMCrsPlan));
		cmmnDtService.insertlistCmmnDt(cmmmDescTblId, iMCrsPlan.getCrsPlanId(), req, cmmnDt);
		
		mav.setViewName("jsonView");
		return mav;
	}

	
	
	
	/**
	 *  과정계획  삭제
	 * @param req
	 * @param res
	 * @param iMcrsPlan
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/crsPlan/delete.do")
	public ModelAndView delete(HttpServletRequest req, HttpServletResponse res, IMCrsPlanVO iMCrsPlan)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMCrsPlan);

		mav.addObject("result", crsPlanService.deleteCrsPlan(iMCrsPlan));

		mav.setViewName("jsonView");
		return mav;
	}
	
	
	/**
	 *  과정계획 다중 삭제 처리
	 * 
	 * @param req
	 * @param res
	 * @param IMCrsPlanVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping ("/mng/crsPlan/deletelist.do")
	public ModelAndView deletelist(HttpServletRequest req, HttpServletResponse res, IMCrsPlanVO iMCrsPlan) throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMCrsPlan);
		

		List<IMCrsPlanVO> crsPlans = new ArrayList<IMCrsPlanVO>();
				
		for (Long idx: iMCrsPlan.getCheckIndexs()) {
			IMCrsPlanVO o = new IMCrsPlanVO();
			o.setCrsPlanId(iMCrsPlan.getCrsPlanIds()[idx.intValue()]);
			o.copyAudit(iMCrsPlan);

			crsPlans.add(o);
		}

		if (crsPlans.size() > 0) {
			mav.addObject("result", crsPlanService.deletelistCrsPlan(crsPlans));
		} else {
			mav.addObject("result", 0);
		}

		mav.setViewName("jsonView");
		return mav;
	}
	
	
	
	

	
}