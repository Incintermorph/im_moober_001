/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.file.web;


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

import com.intermorph.cmmn.file.service.IMCmmnFileCondition;
import com.intermorph.cmmn.file.service.IMCmmnFileResultSet;
import com.intermorph.cmmn.file.service.IMCmmnFileService;
import com.intermorph.cmmn.file.service.IMCmmnFileVO;


import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.file.web
 * @File : IMCmmnFileController.java
 * @Title : 공통파일관리
 * @date : 2022.05.13 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Controller
public class IMCmmnFileMngController extends BaseController {


    @Resource (name = "IMCmmnFileService")
	private IMCmmnFileService cmmnFileService;
	
	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;

	@Autowired
	private DefaultBeanValidator beanValidator;
	

	
	
	/**
	 * 공통파일관리 목록
	 * 
	 * @param req
	 * @param res
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/cmmnFile/selectList.do")
	public ModelAndView selectList(HttpServletRequest req, HttpServletResponse res, IMCmmnFileCondition condition)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		setEmptyValue(condition, "currentPageNo=1", "recordCountPerPage=" + propertyService.getInt("pageUnit"),
				"pageSize=" + propertyService.getInt("pageSize"), "orderbyKey=0");

		mav.addObject("pageInfo", cmmnFileService.selectListCmmnFile(condition));

		mav.addObject("condition", condition);

		mav.setViewName("layout/mng/cmmn/file/selectListCmmnFile");

		return mav;
	}
	
	
	
	/**
	 * 공통파일관리  등록 화면 
	 * @param req
	 * @param res
	 * @param iMCmmnFile
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/cmmnFile/regist.do")
	public ModelAndView regist(HttpServletRequest req, HttpServletResponse res, IMCmmnFileVO iMCmmnFile,
			IMCmmnFileCondition condition) throws Exception {
		ModelAndView mav = new ModelAndView();

		mav.addObject("iMCmmnFile", iMCmmnFile);

		mav.addObject("condition", condition);

		mav.setViewName("layout/mng/cmmn/file/registCmmnFile");

		return mav;
	}
	
	/**
	 * 공통파일관리 수정 화면 
	 * @param req
	 * @param res
	 * @param iMCmmnFile
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/cmmnFile/modify.do")
	public ModelAndView modify(HttpServletRequest req, HttpServletResponse res, IMCmmnFileVO iMCmmnFile,
			IMCmmnFileCondition condition) throws Exception {
		ModelAndView mav = new ModelAndView();

		IMCmmnFileResultSet detail = (IMCmmnFileResultSet) cmmnFileService.selectDetailCmmnFile(iMCmmnFile);

		mav.addObject("iMCmmnFile", detail.getCmmnFile());
		mav.addObject("detail", detail);
		
		mav.addObject("condition", condition);
		
		
		mav.setViewName("layout/mng/cmmn/file/modifyCmmnFile");
		
		return mav;
	}
	
	
	
	
	
	/**
	 * 공통파일관리 등록 저장 
	 * @param req
	 * @param res
	 * @param iMcmmnFile
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/cmmnFile/insert.do")
	public ModelAndView insert(HttpServletRequest req, HttpServletResponse res,
			@ModelAttribute("iMCmmnFile") IMCmmnFileVO iMCmmnFile ,
			BindingResult bindingResult, ModelMap model)
			throws Exception {

		ModelAndView mav = new ModelAndView();
		loginCheckSetAudit(req, iMCmmnFile);
		beanValidator.validate(iMCmmnFile, bindingResult); // validation 수행

		if (bindingResult.hasErrors()) { // 만일 validation 에러가 있으면...
			mav.addObject("result", -1);
		}
		iMCmmnFile.setDwldCnt("0");
		mav.addObject("result", cmmnFileService.insertCmmnFile(iMCmmnFile));

		mav.setViewName("jsonView");
		return mav;
	}
	
	/**
	 * 공통파일관리 수정 저장
	 * @param req
	 * @param res
	 * @param iMcmmnFile
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/cmmnFile/update.do")
	public ModelAndView update(HttpServletRequest req, HttpServletResponse res,
			@ModelAttribute("iMCmmnFile") IMCmmnFileVO iMCmmnFile,
			BindingResult bindingResult, ModelMap model)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMCmmnFile);

		if (bindingResult.hasErrors()) { // 만일 validation 에러가 있으면...
			mav.addObject("result", -1);
		}

		mav.addObject("result",cmmnFileService.updateCmmnFile(iMCmmnFile));
		
		mav.setViewName("jsonView");
		return mav;
	}

	
	
	
	/**
	 *  공통파일관리  삭제
	 * @param req
	 * @param res
	 * @param iMcmmnFile
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/cmmnFile/delete.do")
	public ModelAndView delete(HttpServletRequest req, HttpServletResponse res, IMCmmnFileVO iMCmmnFile)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMCmmnFile);

		mav.addObject("result", cmmnFileService.deleteCmmnFile(iMCmmnFile));

		mav.setViewName("jsonView");
		return mav;
	}
	
	
	/**
	 *  공통파일관리 다중 삭제 처리
	 * 
	 * @param req
	 * @param res
	 * @param IMCmmnFileVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping ("/mng/cmmnFile/deletelist.do")
	public ModelAndView deletelist(HttpServletRequest req, HttpServletResponse res, IMCmmnFileVO iMCmmnFile) throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMCmmnFile);
		

		List<IMCmmnFileVO> cmmnFiles = new ArrayList<IMCmmnFileVO>();
				
		for (Long idx: iMCmmnFile.getCheckIndexs()) {
			IMCmmnFileVO o = new IMCmmnFileVO();
			o.setCmmnFileId(iMCmmnFile.getCmmnFileIds()[idx.intValue()]);
			o.copyAudit(iMCmmnFile);

			cmmnFiles.add(o);
		}

		if (cmmnFiles.size() > 0) {
			mav.addObject("result", cmmnFileService.deletelistCmmnFile(cmmnFiles));
		} else {
			mav.addObject("result", 0);
		}

		mav.setViewName("jsonView");
		return mav;
	}
	
	
	
	

	
}