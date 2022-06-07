/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.crs.mstr.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.intermorph.cmmn.base.BaseController;
import com.intermorph.cmmn.service.IMCmmnDescService;
import com.intermorph.cmmn.service.IMCmmnDescVO;
import com.intermorph.cmmn.service.IMCommonService;
import com.intermorph.crs.mstr.service.IMCrsMstrCondition;
import com.intermorph.crs.mstr.service.IMCrsMstrResultSet;
import com.intermorph.crs.mstr.service.IMCrsMstrService;
import com.intermorph.crs.mstr.service.IMCrsMstrVO;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.crs.mstr.web
 * @File : IMCrsMstrMngController.java
 * @Title : 과정 마스터 관리
 * @date : 2022. 2. 8
 * @author : 노성용
 * @descrption : 과정 마스터 관리
 */
@Controller
public class IMCrsMstrMngController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(IMCrsMstrMngController.class);
	
	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;

	@Resource(name = "IMCrsMstrService")
	private IMCrsMstrService crsMstrService;
	
	@Resource(name = "IMCmmnDescService")
	private IMCmmnDescService cmmnDescService;

	@Autowired
	private DefaultBeanValidator beanValidator;
	
	private String cmmmDescTblId="IM_CRS_MSTR";
	

	@Resource(name = "IMCommonService")
	private IMCommonService commonService;

	/**
	 * 마스터 과정 목록
	 * 
	 * @param req
	 * @param res
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/crsMstr/selectList.do")
	public ModelAndView selectList(HttpServletRequest req, HttpServletResponse res, IMCrsMstrCondition condition)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		setEmptyValue(condition, "currentPageNo=1", "recordCountPerPage=" + propertyService.getInt("pageUnit"),
				"pageSize=" + propertyService.getInt("pageSize"), "orderbyKey=0");

		mav.addObject("pageInfo", crsMstrService.selectListCrsMstr(condition));

		mav.addObject("condition", condition);

		
		mav.setViewName("layout/mng/crs/crsMstr/selectListCrsMstr");

		return mav;
	}

	/**
	 * 마스터 과목 등록 화면 
	 * @param req
	 * @param res
	 * @param iMCrsMstr
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/crsMstr/regist.do")
	public ModelAndView regist(HttpServletRequest req, HttpServletResponse res, IMCrsMstrVO iMCrsMstr,
			IMCrsMstrCondition condition) throws Exception {
		ModelAndView mav = new ModelAndView();

		mav.addObject("iMCrsMstr", iMCrsMstr);

		mav.addObject("condition", condition);

		mav.setViewName("layout/mng/crs/crsMstr/registCrsMstr");

		return mav;
	}

	/**
	 * 등록
	 * 
	 * @param req
	 * @param res
	 * @param iMCrsMstr
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/crsMstr/regist/layer.do")
	public ModelAndView registLayer(HttpServletRequest req, HttpServletResponse res, IMCrsMstrVO iMCrsMstr,
			IMCrsMstrCondition condition) throws Exception {
		ModelAndView mav = new ModelAndView();

		mav.addObject("iMCrsMstr", iMCrsMstr);

		mav.addObject("condition", condition);

		mav.setViewName("layer/mng/crs/crsMstr/registCrsMstrLayer");

		return mav;
	}

	/**
	 * 마스터 과정 수정 화면 
	 * @param req
	 * @param res
	 * @param iMCrsMstr
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/crsMstr/modify.do")
	public ModelAndView modify(HttpServletRequest req, HttpServletResponse res, IMCrsMstrVO iMCrsMstr,IMCrsMstrCondition condition) throws Exception {
		ModelAndView mav = new ModelAndView();

		IMCrsMstrResultSet detail = (IMCrsMstrResultSet) crsMstrService.selectDetailCrsMstr(iMCrsMstr);

		mav.addObject("iMCrsMstr", detail.getImCrsMstr());

		mav.addObject("condition", condition);
		LOGGER.debug(iMCrsMstr.toString());
		
		mav.addObject("cmmmDescMap", cmmnDescService.selectListCmmnDescResultMap(cmmmDescTblId, iMCrsMstr.getCrsMstrId()));
		
		

		mav.setViewName("layout/mng/crs/crsMstr/modifyCrsMstr");

		return mav;
	}
	
	/**
	 * 마스터 과정  상세 조회 
	 * @param req
	 * @param res
	 * @param iMCrsMstr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/crsMstr/selectOne/json.do")
	public ModelAndView selectOne(HttpServletRequest req, HttpServletResponse res, IMCrsMstrVO iMCrsMstr) throws Exception {
		ModelAndView mav = new ModelAndView();

		IMCrsMstrResultSet detail = (IMCrsMstrResultSet) crsMstrService.selectDetailCrsMstr(iMCrsMstr);
		detail.setCrsGrdCdvNm(commonService.selectCmmCodeOneDetailNm("IM0001", detail.getImCrsMstr().getCrsGrdCdv()));
		detail.setCrsDvsnCdvNm(commonService.selectCmmCodeOneDetailNm("IM0002", detail.getImCrsMstr().getCrsDvsnCdv()));
		mav.addObject("detail", detail);
		
		mav.addObject("cmmmDescMap", cmmnDescService.selectListCmmnDescResultMap(cmmmDescTblId, iMCrsMstr.getCrsMstrId()));
		
		mav.setViewName("jsonView");

		return mav;
	}

	/**
	 * 마스터 과정 등록 저장 
	 * @param req
	 * @param res
	 * @param iMCrsMstr
	 * @param cmmnDesc
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/crsMstr/insert.do")
	public ModelAndView insert(HttpServletRequest req, HttpServletResponse res,
			@ModelAttribute("iMCrsMstr") IMCrsMstrVO iMCrsMstr,
			IMCmmnDescVO cmmnDesc,
			BindingResult bindingResult, ModelMap model)
			throws Exception {

		ModelAndView mav = new ModelAndView();
		loginCheckSetAudit(req, iMCrsMstr,cmmnDesc);
		beanValidator.validate(iMCrsMstr, bindingResult); // validation 수행

		if (bindingResult.hasErrors()) { // 만일 validation 에러가 있으면...
			mav.addObject("result", -1);
		}

		mav.addObject("result", crsMstrService.insertCrsMstr(iMCrsMstr));
		LOGGER.debug(iMCrsMstr.toString());
		cmmnDescService.insertCmmnDesclist(cmmmDescTblId,iMCrsMstr.getCrsMstrId(),req,cmmnDesc);

		mav.setViewName("jsonView");
		return mav;
	}

	/**
	 * 마스터 과정 수정 저장
	 * @param req
	 * @param res
	 * @param iMCrsMstr
	 * @param cmmnDesc
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/crsMstr/update.do")
	public ModelAndView update(HttpServletRequest req, HttpServletResponse res,
			@ModelAttribute("iMCrsMstr") IMCrsMstrVO iMCrsMstr, 
			IMCmmnDescVO cmmnDesc,
			BindingResult bindingResult, ModelMap model)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMCrsMstr,cmmnDesc);

		if (bindingResult.hasErrors()) { // 만일 validation 에러가 있으면...
			mav.addObject("result", -1);
		}

		mav.addObject("result", crsMstrService.updateCrsMstr(iMCrsMstr));
		
		cmmnDescService.updateCmmnDesclist(cmmmDescTblId,iMCrsMstr.getCrsMstrId(),req,cmmnDesc);
		
		mav.setViewName("jsonView");
		return mav;
	}

	/**
	 * 마스터 과정 삭제
	 * @param req
	 * @param res
	 * @param iMCrsMstr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/crsMstr/delete.do")
	public ModelAndView delete(HttpServletRequest req, HttpServletResponse res, IMCrsMstrVO iMCrsMstr)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMCrsMstr);

		mav.addObject("result", crsMstrService.deleteCrsMstr(iMCrsMstr));

		mav.setViewName("jsonView");
		return mav;
	}
	/**
	 * 마스터 과정 일괄 삭제
	 * @param req
	 * @param res
	 * @param iMCrsMstr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/crsMstr/deletelist.do")
	public ModelAndView deletelist(HttpServletRequest req, HttpServletResponse res, IMCrsMstrVO iMCrsMstr)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMCrsMstr);
		
		
		List<IMCrsMstrVO> list =new ArrayList<IMCrsMstrVO>();
		
		for(Long idx : iMCrsMstr.getCheckIndexs()) {			
			IMCrsMstrVO vo = new IMCrsMstrVO();
			vo.setCrsMstrId(iMCrsMstr.getCrsMstrIds()[idx.intValue()]);
			vo.copyAudit(iMCrsMstr);
			list.add(vo);
		}
		if (list.size() > 0) {
			mav.addObject("result", crsMstrService.deleteCrsMstrlist(list));
		}else {
			mav.addObject("result", 0);
		}
		
		mav.setViewName("jsonView");
		return mav;
	}
}
