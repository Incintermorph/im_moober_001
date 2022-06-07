/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.issu.web;


import java.util.ArrayList;
import java.util.HashMap;
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
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;
import com.intermorph.cmmn.exception.IMProcErrors;
import com.intermorph.cmmn.exception.IMProcException;
import com.intermorph.cmmn.util.IMDateUtil;
import com.intermorph.cmmn.util.IMStringUtil;
import com.intermorph.crs.agncy.service.IMAgncyService;
import com.intermorph.uss.hstry.service.IMMmbrHstryResultSet;
import com.intermorph.uss.hstry.service.IMMmbrHstryService;
import com.intermorph.uss.hstry.service.IMMmbrHstryVO;
import com.intermorph.uss.issu.service.IMIssuAplyCondition;
import com.intermorph.uss.issu.service.IMIssuAplyResultSet;
import com.intermorph.uss.issu.service.IMIssuAplyService;
import com.intermorph.uss.issu.service.IMIssuAplyVO;
import com.intermorph.uss.qlfc.service.IMMmbrQlfcService;
import com.intermorph.uss.qlfc.service.IMMmbrQlfcVO;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.issu.web
 * @File : IMIssuAplyController.java
 * @Title : 발급신청
 * @date : 2022.04.14 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Controller
public class IMIssuAplyUserController extends BaseController {


    @Resource (name = "IMIssuAplyService")
	private IMIssuAplyService issuAplyService;
	

    @Resource (name = "IMMmbrHstryService")
	private IMMmbrHstryService mmbrHstryService;
	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;

	
	@Autowired
	private DefaultBeanValidator beanValidator;
	


    @Resource (name = "IMMmbrQlfcService")
    private IMMmbrQlfcService mmbrQlfcService;
    

	@Resource(name = "IMAgncyService")
	private IMAgncyService agncyService;
	
	/**
	 * 발급신청 목록
	 * 
	 * @param req
	 * @param res
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/issuAply/selectList.do")
	public ModelAndView selectList(HttpServletRequest req, HttpServletResponse res, IMIssuAplyCondition condition)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		setEmptyValue(condition, "currentPageNo=1", "recordCountPerPage=" + propertyService.getInt("pageUnit"),
				"pageSize=" + propertyService.getInt("pageSize"), "orderbyKey=0");
		condition.setOrderbyKey(1);
		condition.setCurrentPageNo(0);
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		condition.setScEsntlId(user.getUniqId());
		condition.setScNotSttsCdv("03");
		BasePage<BaseResultSet> pageInfo = issuAplyService.selectListIssuAply(condition);
		mav.addObject("pageInfo", pageInfo);

		

		HashMap<String, IMIssuAplyVO> applyMap = new HashMap<String, IMIssuAplyVO>();
		
		
		if(pageInfo!=null && pageInfo.getList()!=null && pageInfo.getList().size()>0) {
			for(BaseResultSet rs : pageInfo.getList()) {
				IMIssuAplyResultSet issuAply = (IMIssuAplyResultSet)rs;
				applyMap.put(issuAply.getIssuAply().getQlfcRsltCode(), issuAply.getIssuAply());
			}
		}
		
		mav.addObject("applyMap", applyMap);//최종 신청 데이터 		
		mav.addObject("condition", condition);

		
		List<IMMmbrQlfcVO> listMmbrQlfc =mmbrQlfcService.selectListUserMmbrQlfcPass(user.getUniqId());
		
		
		mav.addObject("listMmbrQlfc", listMmbrQlfc);
		
		if(listMmbrQlfc!=null && listMmbrQlfc.size()>0) {
			mav.addObject("listMmbrQlfcCnt", listMmbrQlfc.size());
		}else {
			mav.addObject("listMmbrQlfcCnt", 0);
		}
		
		

		mav.setViewName("layout/user/uss/issu/selectListIssuAply");

		return mav;
	}
	
	
	
	/**
	 * 발급신청  등록 화면 
	 * @param req
	 * @param res
	 * @param iMIssuAply
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/issuAply/regist.do")
	public ModelAndView regist(HttpServletRequest req, HttpServletResponse res, IMIssuAplyVO iMIssuAply,
			IMIssuAplyCondition condition) throws Exception {
		ModelAndView mav = new ModelAndView();


		mav.addObject("condition", condition);
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		IMMmbrHstryVO iMMmbrHstry = new  IMMmbrHstryVO ();
		iMMmbrHstry.setEsntlId(user.getUniqId());
		IMMmbrHstryResultSet detailHstry = (IMMmbrHstryResultSet) mmbrHstryService.selectDetailMmbrHstry(iMMmbrHstry);

		mav.addObject("detailHstry", detailHstry);
		
		iMIssuAply.setMmbrTelno(detailHstry.getMmbrHstry().getMblTelno());
		iMIssuAply.setBrdt(detailHstry.getMmbrHstry().getBrdt());
		IMMmbrQlfcVO lastMmbrQlfc = mmbrQlfcService.selectDetailMmbrQlfc(user.getUniqId(), iMIssuAply.getCrsGrdCdv());

		if(!lastMmbrQlfc.getQlfcRsltCode().equals(iMIssuAply.getQlfcRsltCode())) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		
		if("01".equals(lastMmbrQlfc.getDvsnCdv())) {
			iMIssuAply.setIssuDvsnCdv("02");
		}else if("02".equals(lastMmbrQlfc.getDvsnCdv())) {
			iMIssuAply.setIssuDvsnCdv("01");
		}
		
		if(IMStringUtil.isEmpty(iMIssuAply.getIssuDvsnCdv())) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		
		mav.addObject("agncyList", agncyService.selectListAgncy());
		

		mav.addObject("iMIssuAply", iMIssuAply);
		mav.addObject("lastMmbrQlfc", lastMmbrQlfc);
		mav.setViewName("layout/user/uss/issu/registIssuAply");

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
	@RequestMapping(value = "/user/issuAply/modify.do")
	public ModelAndView modify(HttpServletRequest req, HttpServletResponse res, IMIssuAplyVO iMIssuAply,
			IMIssuAplyCondition condition) throws Exception {
		ModelAndView mav = new ModelAndView();

		IMIssuAplyResultSet detail = (IMIssuAplyResultSet) issuAplyService.selectDetailIssuAply(iMIssuAply);

		mav.addObject("iMIssuAply", detail.getIssuAply());
		

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		if(!detail.getIssuAply().getEsntlId().equals(user.getUniqId())) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		
		IMMmbrQlfcVO lastMmbrQlfc = mmbrQlfcService.selectDetailMmbrQlfc(user.getUniqId(), detail.getIssuAply().getCrsGrdCdv());
		

		mav.addObject("lastMmbrQlfc", lastMmbrQlfc);
		mav.addObject("condition", condition);

		mav.addObject("detail", detail);
		mav.addObject("agncyList", agncyService.selectListAgncy());
		
		mav.setViewName("layout/user/uss/issu/modifyIssuAply");
		
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
	@RequestMapping(value = "/user/issuAply/selectDetail.do")
	public ModelAndView selectDetail(HttpServletRequest req, HttpServletResponse res, IMIssuAplyVO iMIssuAply,
			IMIssuAplyCondition condition) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		IMIssuAplyResultSet detail = (IMIssuAplyResultSet) issuAplyService.selectDetailIssuAply(iMIssuAply);
		
		mav.addObject("iMIssuAply", detail.getIssuAply());
		
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		if(!detail.getIssuAply().getEsntlId().equals(user.getUniqId())) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		
		IMMmbrQlfcVO lastMmbrQlfc = mmbrQlfcService.selectDetailMmbrQlfc(user.getUniqId(), detail.getIssuAply().getCrsGrdCdv());
		
		
		mav.addObject("lastMmbrQlfc", lastMmbrQlfc);
		mav.addObject("condition", condition);
		mav.addObject("detail", detail);
		
		
		mav.setViewName("layout/user/uss/issu/selectDetailIssuAply");
		
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
	@RequestMapping(value = "/user/issuAply/insert.do")
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

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		iMIssuAply.setEsntlId(user.getUniqId());
		iMIssuAply.setMberNm(user.getName());
		iMIssuAply.setMmbrId(user.getId());
		String eduBgnDt= IMDateUtil.convertStartDate(iMIssuAply.getEduBgnDt(), IMGlobals.IM_FORMAT_DATE, IMGlobals.IM_FORMAT_DBDATETIME,IMGlobals.IM_FORMAT_TIMEZONE);
		String eduEndDt= IMDateUtil.convertEndDate(iMIssuAply.getEduEndDt(), IMGlobals.IM_FORMAT_DATE, IMGlobals.IM_FORMAT_DBDATETIME,IMGlobals.IM_FORMAT_TIMEZONE);
		iMIssuAply.setEduBgnDt(eduBgnDt);
		iMIssuAply.setEduEndDt(eduEndDt);

		
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
	@RequestMapping(value = "/user/issuAply/update.do")
	public ModelAndView update(HttpServletRequest req, HttpServletResponse res,
			@ModelAttribute("iMIssuAply") IMIssuAplyVO iMIssuAply,
			BindingResult bindingResult, ModelMap model)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMIssuAply);

		if (bindingResult.hasErrors()) { // 만일 validation 에러가 있으면...
			mav.addObject("result", -1);
		}
		

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		
		iMIssuAply.setMberNm(user.getName());
		
		
		String eduBgnDt= IMDateUtil.convertStartDate(iMIssuAply.getEduBgnDt(), IMGlobals.IM_FORMAT_DATE, IMGlobals.IM_FORMAT_DBDATETIME,IMGlobals.IM_FORMAT_TIMEZONE);
		String eduEndDt= IMDateUtil.convertEndDate(iMIssuAply.getEduEndDt(), IMGlobals.IM_FORMAT_DATE, IMGlobals.IM_FORMAT_DBDATETIME,IMGlobals.IM_FORMAT_TIMEZONE);
		iMIssuAply.setEduBgnDt(eduBgnDt);
		iMIssuAply.setEduEndDt(eduEndDt);

		IMIssuAplyResultSet detail = (IMIssuAplyResultSet) issuAplyService.selectDetailIssuAply(iMIssuAply);

		if(!detail.getIssuAply().getFrstRegerId().equals(user.getUniqId())) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		
		mav.addObject("result",issuAplyService.updateIssuAply(iMIssuAply));
		
		mav.setViewName("jsonView");
		return mav;
	}

	
	
	
	
	/**
	 *  신청 취소 
	 * @param req
	 * @param res
	 * @param iMIssuAply
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/issuAply/cancel.do")
	public ModelAndView delete(HttpServletRequest req, HttpServletResponse res, IMIssuAplyVO iMIssuAply)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMIssuAply);
		IMIssuAplyResultSet detail = (IMIssuAplyResultSet) issuAplyService.selectDetailIssuAply(iMIssuAply);

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		
		if(!detail.getIssuAply().getFrstRegerId().equals(user.getUniqId())) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		iMIssuAply.setSttsCdv("03");
		mav.addObject("result", issuAplyService.updateIssuAply(iMIssuAply));

		mav.setViewName("jsonView");
		return mav;
	}
	
	

	
}