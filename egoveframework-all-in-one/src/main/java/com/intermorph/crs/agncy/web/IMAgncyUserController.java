/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.crs.agncy.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.intermorph.cmmn.base.BaseController;
import com.intermorph.cmmn.exception.IMProcErrors;
import com.intermorph.cmmn.exception.IMProcException;
import com.intermorph.cmmn.service.IMCommonService;
import com.intermorph.crs.agncy.service.IMAgncyCondition;
import com.intermorph.crs.agncy.service.IMAgncyService;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.crs.agncy.web
 * @File    : IMAgncyUserController.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 3. 2
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
@Controller
public class IMAgncyUserController extends BaseController {
	

    @Resource (name = "IMAgncyService")
	private IMAgncyService agncyService;
    
    @Resource (name = "IMCommonService")
    private IMCommonService commonService;
    
	/**
	 * 양성기관 목록
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
//	@RequestMapping(value = "/cmmn/agncy/{crsGrd}/selectList.do")
//	public ModelAndView selectList(HttpServletRequest req, HttpServletResponse res,@PathVariable("crsGrd") String crsGrd)
//			throws Exception {
//		ModelAndView mav = new ModelAndView();
//		
//		IMAgncyCondition condition = new IMAgncyCondition();
//		String grdCode= "CRS_GRD_"+crsGrd.trim();
//		String grdNm =commonService.selectCmmCodeOneDetailNm("IM0001", grdCode);
//		
//		if("".equals(grdNm)) {
//			throw new IMProcException(IMProcErrors.필수값없음);
//		}
//		condition.setOrderbyKey(2);
//		condition.setScCrsGrdCdv(grdCode);
//		
//		mav.addObject("list", agncyService.selectListAgncy(condition).getList());
//
//		mav.setViewName("layout/user/crs/agncy/selectListAgncy");
//
//		return mav;
//	}
	
	
	@RequestMapping(value = "/cmmn/agncy/selectList.do")
	public ModelAndView selectList(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		
		IMAgncyCondition condition = new IMAgncyCondition();
//		String grdCode= "CRS_GRD_"+crsGrd.trim();
//		String grdNm =commonService.selectCmmCodeOneDetailNm("IM0001", grdCode);
		
//		if("".equals(grdNm)) {
//			throw new IMProcException(IMProcErrors.필수값없음);
//		}
		condition.setOrderbyKey(2);
//		condition.setScCrsGrdCdv("양성기관");
		condition.setScDsgnYn("Y");
		condition.setScNotAgncyDvsnCdv("AGNCYDVSN_03");
		mav.addObject("list", agncyService.selectListAgncy(condition).getList());

		mav.setViewName("layout/user/crs/agncy/selectListAgncy");

		return mav;
	}
}
