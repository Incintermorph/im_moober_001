/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.crs.agncy.web;

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

import com.intermorph.cmmn.base.BaseController;
import com.intermorph.cmmn.exception.IMProcErrors;
import com.intermorph.cmmn.exception.IMProcException;
import com.intermorph.cmmn.service.IMCmmnDescService;
import com.intermorph.cmmn.service.IMCmmnDescVO;
import com.intermorph.crs.agncy.service.IMAgncyCondition;
import com.intermorph.crs.agncy.service.IMAgncyResultSet;
import com.intermorph.crs.agncy.service.IMAgncyService;
import com.intermorph.crs.agncy.service.IMAgncyVO;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.crs.agncy.web
 * @File    : IMAgncyMngController.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 3. 2
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
@Controller
public class IMAgncyMngController  extends BaseController {
	

    @Resource (name = "IMAgncyService")
	private IMAgncyService agncyService;
	
	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;

	@Autowired
	private DefaultBeanValidator beanValidator;
	


	private String cmmmDescTblId = "IM_AGNCY";
	

	@Resource(name = "IMCmmnDescService")
	private IMCmmnDescService cmmnDescService;
	
	/**
	 * 양성기관 목록
	 * 
	 * @param req
	 * @param res
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/agncy/selectList.do")
	public ModelAndView selectList(HttpServletRequest req, HttpServletResponse res, IMAgncyCondition condition)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		setEmptyValue(condition, "currentPageNo=1", "recordCountPerPage=" + propertyService.getInt("pageUnit"),
				"pageSize=" + propertyService.getInt("pageSize"), "orderbyKey=0", "scNotAgncyDvsnCdv=AGNCYDVSN_03");
		
		mav.addObject("pageInfo", agncyService.selectListAgncy(condition));

		mav.addObject("condition", condition);

		mav.setViewName("layout/mng/crs/agncy/selectListAgncy");

		return mav;
	}
	
	/**
	 * 양성기관 목록
	 * 
	 * @param req
	 * @param res
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/agncy/place/selectList.do")
	public ModelAndView selectListDivs(HttpServletRequest req, HttpServletResponse res, IMAgncyCondition condition)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		setEmptyValue(condition, "currentPageNo=1", "recordCountPerPage=" + propertyService.getInt("pageUnit"),
				"pageSize=" + propertyService.getInt("pageSize"), "orderbyKey=0", "scAgncyDvsnCdv=AGNCYDVSN_03");
		
		mav.addObject("pageInfo", agncyService.selectListAgncy(condition));

		mav.addObject("condition", condition);

		mav.setViewName("layout/mng/crs/agncy/selectListAgncyPlace");

		return mav;
	}
	
	
	
	/**
	 * 양성기관  등록 화면 
	 * @param req
	 * @param res
	 * @param iMAgncy
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/agncy/regist.do")
	public ModelAndView regist(HttpServletRequest req, HttpServletResponse res, IMAgncyVO iMAgncy,
			IMAgncyCondition condition) throws Exception {
		ModelAndView mav = new ModelAndView();

		mav.addObject("iMAgncy", iMAgncy);

		mav.addObject("condition", condition);

		mav.setViewName("layout/mng/crs/agncy/registAgncy");

		return mav;
	}
	/**
	 * 장소  등록 화면 
	 * @param req
	 * @param res
	 * @param iMAgncyPlace
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/agncy/place/regist.do")
	public ModelAndView registPlace(HttpServletRequest req, HttpServletResponse res, IMAgncyVO iMAgncyPlace,
			IMAgncyCondition condition) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("iMAgncyPlace", iMAgncyPlace);
		
		mav.addObject("condition", condition);
		
		mav.setViewName("layout/mng/crs/agncy/registAgncyPlace");
		
		return mav;
	}
	
	/**
	 * 양성기관   상세 정보 
	 * @param req
	 * @param res
	 * @param iMAgncy
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/agncy/selectOne/json.do")
	public ModelAndView selectOne(HttpServletRequest req, HttpServletResponse res, IMAgncyVO iMAgncy) throws Exception {
		ModelAndView mav = new ModelAndView();

		IMAgncyResultSet detail = (IMAgncyResultSet) agncyService.selectDetailAgncy(iMAgncy);

		mav.addObject("detail", detail);

		
		mav.setViewName("jsonView");
		
		return mav;
	}
	/**
	 * 양성기관 수정 화면 
	 * @param req
	 * @param res
	 * @param iMAgncy
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/agncy/modify.do")
	public ModelAndView modify(HttpServletRequest req, HttpServletResponse res, IMAgncyVO iMAgncy,
			IMAgncyCondition condition) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		IMAgncyResultSet detail = (IMAgncyResultSet) agncyService.selectDetailAgncy(iMAgncy);
		
		mav.addObject("iMAgncy", detail.getAgncy());
		
		mav.addObject("cmmmDescMap", cmmnDescService.selectListCmmnDescResultMap(cmmmDescTblId, iMAgncy.getAgncyId()));
		
		
		mav.addObject("condition", condition);
		
		
		mav.setViewName("layout/mng/crs/agncy/modifyAgncy");
		
		return mav;
	}
	/**
	 * 장소 수정 화면 
	 * @param req
	 * @param res
	 * @param iMAgncyPlace
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/agncy/place/modify.do")
	public ModelAndView modifyPlace(HttpServletRequest req, HttpServletResponse res, IMAgncyVO iMAgncyPlace,
			IMAgncyCondition condition) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		IMAgncyResultSet detail = (IMAgncyResultSet) agncyService.selectDetailAgncy(iMAgncyPlace);
		
		mav.addObject("iMAgncyPlace", detail.getAgncy());
		
		mav.addObject("cmmmDescMap", cmmnDescService.selectListCmmnDescResultMap(cmmmDescTblId, iMAgncyPlace.getAgncyId()));
		
		
		mav.addObject("condition", condition);
		
		
		mav.setViewName("layout/mng/crs/agncy/modifyAgncyPlace");
		
		return mav;
	}
	
	
	
	
	
	/**
	 * 양성기관 등록 저장 
	 * @param req
	 * @param res
	 * @param iMagncy
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/agncy/insert.do")
	public ModelAndView insert(HttpServletRequest  req, HttpServletResponse res,
			@ModelAttribute("iMAgncy") IMAgncyVO iMAgncy,
			IMCmmnDescVO cmmnDesc,
			BindingResult bindingResult, ModelMap model)
			throws Exception {

		ModelAndView mav = new ModelAndView();
		loginCheckSetAudit(req, iMAgncy,cmmnDesc);
		beanValidator.validate(iMAgncy, bindingResult); // validation 수행

		if (bindingResult.hasErrors()) { // 만일 validation 에러가 있으면...
			mav.addObject("result", -1);
		}

		mav.addObject("result", agncyService.insertAgncy(iMAgncy));
		
		cmmnDescService.insertCmmnDesclist(cmmmDescTblId, iMAgncy.getAgncyId(), req, cmmnDesc);
		

		mav.setViewName("jsonView");
		return mav;
	}
	/**
	 * 양성기관 등록 저장 
	 * @param req
	 * @param res
	 * @param iMagncy
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/agncy/place/insert.do")
	public ModelAndView insertplace(HttpServletRequest  req, HttpServletResponse res,
			@ModelAttribute("iMAgncyPlace") IMAgncyVO iMAgncyPlace,
			BindingResult bindingResult, ModelMap model)
					throws Exception {
		
		ModelAndView mav = new ModelAndView();
		loginCheckSetAudit(req, iMAgncyPlace);
		beanValidator.validate(iMAgncyPlace, bindingResult); // validation 수행
		
		if (bindingResult.hasErrors()) { // 만일 validation 에러가 있으면...
			mav.addObject("result", -1);
		}
		
		mav.addObject("result", agncyService.insertAgncy(iMAgncyPlace));
		
		mav.setViewName("jsonView");
		return mav;
	}
	
	/**
	 * 양성기관 수정 저장
	 * @param req
	 * @param res
	 * @param iMagncy
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/agncy/update.do")
	public ModelAndView update(HttpServletRequest req, HttpServletResponse res,
			@ModelAttribute("iMAgncy") IMAgncyVO iMAgncy,
			IMCmmnDescVO cmmnDesc,
			BindingResult bindingResult, ModelMap model)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMAgncy,cmmnDesc);

		if (bindingResult.hasErrors()) { // 만일 validation 에러가 있으면...
			mav.addObject("result", -1);
		}

		mav.addObject("result",agncyService.updateAgncy(iMAgncy));

		cmmnDescService.updateCmmnDesclist(cmmmDescTblId, iMAgncy.getAgncyId(), req, cmmnDesc);
		
		mav.setViewName("jsonView");
		return mav;
	}
	/**
	 * 양성기관 수정 저장
	 * @param req
	 * @param res
	 * @param iMagncy
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/agncy/place/update.do")
	public ModelAndView updateplace(HttpServletRequest req, HttpServletResponse res,
			@ModelAttribute("iMAgncyPlace") IMAgncyVO iMAgncyPlace,
			BindingResult bindingResult, ModelMap model)
					throws Exception {
		ModelAndView mav = new ModelAndView();
		
		loginCheckSetAudit(req, iMAgncyPlace);
		
		if (bindingResult.hasErrors()) { // 만일 validation 에러가 있으면...
			mav.addObject("result", -1);
		}
		
		mav.addObject("result",agncyService.updateAgncy(iMAgncyPlace));
		
		mav.setViewName("jsonView");
		return mav;
	}

	
	
	
	/**
	 *  양성기관  삭제
	 * @param req
	 * @param res
	 * @param iMagncy
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/agncy/delete.do")
	public ModelAndView delete(HttpServletRequest req, HttpServletResponse res, IMAgncyVO iMAgncy)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMAgncy);

		mav.addObject("result", agncyService.deleteAgncy(iMAgncy));

		mav.setViewName("jsonView");
		return mav;
	}
	
	
	/**
	 *  양성기관 다중 삭제 처리
	 * 
	 * @param req
	 * @param res
	 * @param IMAgncyVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping ("/mng/agncy/deletelist.do")
	public ModelAndView deletelist(HttpServletRequest req, HttpServletResponse res, IMAgncyVO iMAgncy) throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMAgncy);
		

		List<IMAgncyVO> agncys = new ArrayList<IMAgncyVO>();
				
		for (Long idx: iMAgncy.getCheckIndexs()) {
			IMAgncyVO o = new IMAgncyVO();			
			o.setAgncyId(iMAgncy.getAgncyIds()[idx.intValue()]);
			
			o.copyAudit(iMAgncy);

			agncys.add(o);
		}

		if (agncys.size() > 0) {
			mav.addObject("result", agncyService.deletelistAgncy(agncys));
		} else {
			mav.addObject("result", 0);
		}

		mav.setViewName("jsonView");
		return mav;
	}
	
	/**
	 * 사업자번호 중복 체크 
	 * @param req
	 * @param res
	 * @param iMAgncy
	 * @return
	 * @throws Exception
	 */
	@RequestMapping ("/mng/agncy/overchek.do")
	public ModelAndView overchek(HttpServletRequest req, HttpServletResponse res, IMAgncyVO iMAgncy) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		
		
		if(iMAgncy.getBrno()==null || "".equals(iMAgncy.getBrno())) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		
		mav.addObject("result", agncyService.selectOverCount(iMAgncy));
		
		
		mav.setViewName("jsonView");
		return mav;
	}
	

	

}
