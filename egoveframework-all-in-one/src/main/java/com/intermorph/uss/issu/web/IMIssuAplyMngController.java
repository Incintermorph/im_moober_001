/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.issu.web;


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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.google.gson.Gson;
import com.intermorph.cmmn.IMGlobals;
import com.intermorph.cmmn.base.BaseController;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;
import com.intermorph.cmmn.service.IMCommonService;
import com.intermorph.cmmn.service.IMInfoInqHstryService;
import com.intermorph.cmmn.util.ExcelDownloadView;
import com.intermorph.crs.agncy.service.IMAgncyService;
import com.intermorph.uss.issu.service.IMIssuAplyCondition;
import com.intermorph.uss.issu.service.IMIssuAplyResultSet;
import com.intermorph.uss.issu.service.IMIssuAplyService;
import com.intermorph.uss.issu.service.IMIssuAplyVO;


import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.issu.web
 * @File : IMIssuAplyController.java
 * @Title : 발급신청
 * @date : 2022.04.27 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Controller
public class IMIssuAplyMngController extends BaseController {


    @Resource (name = "IMIssuAplyService")
	private IMIssuAplyService issuAplyService;
	

	@Resource(name = "IMAgncyService")
	private IMAgncyService agncyService;
	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;

	@Autowired
	private DefaultBeanValidator beanValidator;
	

	@Resource(name="IMInfoInqHstryService")
	IMInfoInqHstryService infoInqHstryService;


	@Resource(name = "IMCommonService")
	private IMCommonService commonService;
	
	
	/**
	 * 발급신청 목록
	 * 
	 * @param req
	 * @param res
	 * @param dto
	 * @return
	 * @throws Exception
	 * 
	 */
	@RequestMapping (value = { "/mng/issuAply/{stts}/selectList.do", "/mng/issuAply/{stts}/selectListExcel.do" })
	public ModelAndView selectList(HttpServletRequest req, HttpServletResponse res, IMIssuAplyCondition condition,@PathVariable("stts") String stts)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		setEmptyValue(condition, "currentPageNo=1", "recordCountPerPage=" + propertyService.getInt("pageUnit"),
				"pageSize=" + propertyService.getInt("pageSize"), "orderbyKey=-4");
		if("A".equals(stts)) {
			condition.setScNotSttsCdv("9903"); // 대기/ 취소  관리자 제외
			condition.setScSttsCdv(null); 
		}else {
			condition.setScNotSttsCdv(null); // 대기/ 취소  관리자 제외
			condition.setScSttsCdv(stts); 
		}

		if(req.getServletPath().indexOf("selectListExcel")!=-1) {

			condition.setCurrentPageNo(0);
		}
		
		BasePage<BaseResultSet>  pageInfo = issuAplyService.selectListIssuAply(condition);
		mav.addObject("pageInfo", pageInfo);
		
		
		mav.addObject("agncyList", agncyService.selectListAgncy());
		
		mav.addObject("stts", stts);
		mav.addObject("condition", condition);
		
		//개인정보 이력 등록 
		Gson gson = new Gson();
		//이력 저장
		if(req.getServletPath().indexOf("selectListExcel")!=-1) {
			
			infoInqHstryService.insertInfoInqHstry("im_issu_aply","SELECTNOID",IMGlobals.IM_INFOINQ_E,gson.toJson(condition),req);
			mav.addObject("commonService", commonService);
			mav.addObject("list", pageInfo.getList());
			//todo excel  다운로드 
			//selectListExcelIssuAply
			mav.addObject(ExcelDownloadView.TEMPLATE_FILE_NAME, "selectListExcelIssuAply");
			mav.setViewName("excelView");
			mav.addObject(ExcelDownloadView.DOWNLOAD_FILE_NAME, ExcelDownloadView.getDownloadName(req));
			
		}else {
			infoInqHstryService.insertInfoInqHstry("im_issu_aply","SELECTNOID",IMGlobals.IM_INFOINQ_S,gson.toJson(condition),req);
			if("02".equals(stts)) {
				mav.setViewName("layout/mng/uss/issu/selectListIssuAplyHistory");
			}else {
				mav.setViewName("layout/mng/uss/issu/selectListIssuAply");
			}
		}

		return mav;
	}
	
	
	
	
	
	/**
	 * 발급신청 수정 화면 
	 * @param req
	 * @param res
	 * @param iMIssuAply
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/issuAply/{stts}/modify.do")
	public ModelAndView modify(HttpServletRequest req, HttpServletResponse res, IMIssuAplyVO iMIssuAply,
			IMIssuAplyCondition condition,@PathVariable("stts") String stts) throws Exception {
		ModelAndView mav = new ModelAndView();

		IMIssuAplyResultSet detail = (IMIssuAplyResultSet) issuAplyService.selectDetailIssuAply(iMIssuAply);

		mav.addObject("iMIssuAply", detail.getIssuAply());

		mav.addObject("detail", detail);
		mav.addObject("condition", condition);
		mav.addObject("stts", stts);
		//개인정보 이력 등록 
			infoInqHstryService.insertInfoInqHstry("im_issu_aply",iMIssuAply.getIssuAplyId(),IMGlobals.IM_INFOINQ_R,"Checking User Issu apply",req);
		
		mav.setViewName("layout/mng/uss/issu/modifyIssuAply");
		
		return mav;
	}
	
	
	
	
	
	/**
	 * 발급신청 등록 저장 
	 * @param req
	 * @param res
	 * @param iMissuAply
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/issuAply/insert.do")
	public ModelAndView insert(HttpServletRequest req, HttpServletResponse res,
			@ModelAttribute("iMIssuAply") IMIssuAplyVO iMIssuAply ,
			BindingResult bindingResult, ModelMap model)
			throws Exception {

		ModelAndView mav = new ModelAndView();
		loginCheckSetAudit(req, iMIssuAply);
		beanValidator.validate(iMIssuAply, bindingResult); // validation 수행

		if (bindingResult.hasErrors()) { // 만일 validation 에러가 있으면...
			mav.addObject("result", -1);
		}

		mav.addObject("result", issuAplyService.insertIssuAply(iMIssuAply));

		mav.setViewName("jsonView");
		return mav;
	}
	
	/**
	 * 발급신청 수정 저장
	 * @param req
	 * @param res
	 * @param iMissuAply
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/issuAply/update.do")
	public ModelAndView update(HttpServletRequest req, HttpServletResponse res,
			@ModelAttribute("iMIssuAply") IMIssuAplyVO iMIssuAply,
			BindingResult bindingResult, ModelMap model)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMIssuAply);

		if (bindingResult.hasErrors()) { // 만일 validation 에러가 있으면...
			mav.addObject("result", -1);
		}

		mav.addObject("result",issuAplyService.updateIssuAply(iMIssuAply));
		
		mav.setViewName("jsonView");
		return mav;
	}

	
	
	
	
	
	/**
	 *  발급신청 다중 상태 수정 처리
	 * 
	 * @param req
	 * @param res
	 * @param IMIssuAplyVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping ("/mng/issuAply/updateAplylist.do")
	public ModelAndView deletelist(HttpServletRequest req, HttpServletResponse res, IMIssuAplyVO iMIssuAply) throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMIssuAply);
		

		List<IMIssuAplyVO> issuAplys = new ArrayList<IMIssuAplyVO>();
				
		for (Long idx: iMIssuAply.getCheckIndexs()) {
			IMIssuAplyVO o = new IMIssuAplyVO();
			o.setIssuAplyId(iMIssuAply.getIssuAplyIds()[idx.intValue()]);
			o.setSttsCdv(iMIssuAply.getSttsCdv());
			o.copyAudit(iMIssuAply);

			issuAplys.add(o);
		}

		if (issuAplys.size() > 0) {
			mav.addObject("result", issuAplyService.updatelistIssuAply(issuAplys));
		} else {
			mav.addObject("result", 0);
		}

		mav.setViewName("jsonView");
		return mav;
	}
	
	
	
	

	
}