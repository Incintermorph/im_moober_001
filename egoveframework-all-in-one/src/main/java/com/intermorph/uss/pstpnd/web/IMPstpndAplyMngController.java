/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.pstpnd.web;

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
import com.intermorph.cmmn.service.IMInfoInqHstryService;
import com.intermorph.crs.agncy.service.IMAgncyService;
import com.intermorph.uss.hstry.service.IMMmbrHstryService;
import com.intermorph.uss.pstpnd.service.IMPstpndAplyCondition;
import com.intermorph.uss.pstpnd.service.IMPstpndAplyResultSet;
import com.intermorph.uss.pstpnd.service.IMPstpndAplyService;
import com.intermorph.uss.pstpnd.service.IMPstpndAplyVO;
import com.intermorph.uss.qlfc.service.IMMmbrQlfcService;

import egovframework.com.uss.umt.service.EgovMberManageService;
import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.pstpnd.web
 * @File : IMPstpndAplyController.java
 * @Title : 유예신청
 * @date : 2022.05.03 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Controller
public class IMPstpndAplyMngController extends BaseController {

	@Resource (name = "IMPstpndAplyService")
	private IMPstpndAplyService pstpndAplyService;

	@Resource (name = "propertiesService")
	protected EgovPropertyService propertyService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	@Resource (name = "IMMmbrHstryService")
	private IMMmbrHstryService mmbrHstryService;

	@Resource (name = "IMMmbrQlfcService")
	private IMMmbrQlfcService mmbrQlfcService;

	@Resource (name = "IMAgncyService")
	private IMAgncyService agncyService;

	/** mberManageService */
	@Resource (name = "mberManageService")
	private EgovMberManageService mberManageService;
	

	@Resource(name="IMInfoInqHstryService")
	IMInfoInqHstryService infoInqHstryService;

	/**
	 * 유예신청 목록
	 * 
	 * @param req
	 * @param res
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping (value = "/mng/pstpndAply/{stts}/selectList.do")
	public ModelAndView selectList(HttpServletRequest req, HttpServletResponse res, IMPstpndAplyCondition condition, @PathVariable ("stts") String stts)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		setEmptyValue(condition, "currentPageNo=1", "recordCountPerPage=" + propertyService.getInt("pageUnit"),
				"pageSize=" + propertyService.getInt("pageSize"), "orderbyKey=-1");

		if ("A".equals(stts)) {
			condition.setScNotSttsCdv("9903"); // 대기/ 취소 관리자 제외
			condition.setScSttsCdv(null);
		} else {
			condition.setScSttsCdv(stts);
			condition.setScNotSttsCdv(null);
		}

		mav.addObject("pageInfo", pstpndAplyService.selectListPstpndAply(condition));

		mav.addObject("condition", condition);

		mav.addObject("stts", stts);

		mav.addObject("agncyList", agncyService.selectListAgncy());

		
		//개인정보 이력 등록 
		Gson gson = new Gson();
		//이력 저장 
		infoInqHstryService.insertInfoInqHstry("im_pstpnd_aply","SELECTNOID",IMGlobals.IM_INFOINQ_S,gson.toJson(condition),req);

		
		mav.setViewName("layout/mng/uss/pstpnd/selectListPstpndAply");

		return mav;
	}

	/**
	 * 유예신청 수정 화면
	 * 
	 * @param req
	 * @param res
	 * @param iMPstpndAply
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping (value = "/mng/pstpndAply/{stts}/modify.do")
	public ModelAndView modify(HttpServletRequest req, HttpServletResponse res, IMPstpndAplyVO iMPstpndAply,

			IMPstpndAplyCondition condition, @PathVariable ("stts") String stts) throws Exception {
		ModelAndView mav = new ModelAndView();

		IMPstpndAplyResultSet detail = (IMPstpndAplyResultSet)pstpndAplyService.selectDetailPstpndAply(iMPstpndAply);

		mav.addObject("iMPstpndAply", detail.getPstpndAply());

		mav.addObject("detail", detail);
		IMPstpndAplyCondition usercondition = new IMPstpndAplyCondition();
		usercondition.setScEsntlId(detail.getPstpndAply().getEsntlId());
		usercondition.setScCrsGrdCdv(detail.getPstpndAply().getCrsGrdCdv());
		
		infoInqHstryService.insertInfoInqHstry("im_pstpnd_aply",iMPstpndAply.getPstpndAplyId(),IMGlobals.IM_INFOINQ_R,"pstpnd modify",req);

		mav.addObject("userList", pstpndAplyService.selectListPstpndAply(usercondition).getList());
		
		mav.addObject("condition", condition);

		mav.addObject("stts", stts);
		
		mav.setViewName("layout/mng/uss/pstpnd/modifyPstpndAply");

		return mav;
	}

	/**
	 * 유예신청 등록 저장
	 * 
	 * @param req
	 * @param res
	 * @param iMpstpndAply
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping (value = "/mng/pstpndAply/insert.do")
	public ModelAndView insert(HttpServletRequest req, HttpServletResponse res, @ModelAttribute ("iMPstpndAply") IMPstpndAplyVO iMPstpndAply,
			BindingResult bindingResult, ModelMap model) throws Exception {

		ModelAndView mav = new ModelAndView();
		loginCheckSetAudit(req, iMPstpndAply);
		beanValidator.validate(iMPstpndAply, bindingResult); // validation 수행

		if (bindingResult.hasErrors()) { // 만일 validation 에러가 있으면...
			mav.addObject("result", -1);
		}

		mav.addObject("result", pstpndAplyService.insertPstpndAply(iMPstpndAply));

		mav.setViewName("jsonView");
		return mav;
	}

	/**
	 * 유예신청 수정 저장
	 * 
	 * @param req
	 * @param res
	 * @param iMpstpndAply
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping (value = "/mng/pstpndAply/update.do")
	public ModelAndView update(HttpServletRequest req, HttpServletResponse res, @ModelAttribute ("iMPstpndAply") IMPstpndAplyVO iMPstpndAply,
			BindingResult bindingResult, ModelMap model) throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMPstpndAply);

		if (bindingResult.hasErrors()) { // 만일 validation 에러가 있으면...
			mav.addObject("result", -1);
		}

		mav.addObject("result", pstpndAplyService.updatePstpndAply(iMPstpndAply));

		mav.setViewName("jsonView");
		return mav;
	}

	/**
	 * 발급신청 다중 상태 수정 처리
	 * 
	 * @param req
	 * @param res
	 * @param IMIssuAplyVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping ("/mng/pstpndAply/updateAplylist.do")
	public ModelAndView updateAplylist(HttpServletRequest req, HttpServletResponse res, IMPstpndAplyVO iMPstpndAply) throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMPstpndAply);

		List<IMPstpndAplyVO> pstpndAplys = new ArrayList<IMPstpndAplyVO>();

		for (Long idx : iMPstpndAply.getCheckIndexs()) {
			IMPstpndAplyVO o = new IMPstpndAplyVO();
			o.setPstpndAplyId(iMPstpndAply.getPstpndAplyIds()[idx.intValue()]);
			o.setSttsCdv(iMPstpndAply.getSttsCdv());
			o.copyAudit(iMPstpndAply);

			pstpndAplys.add(o);
		}

		if (pstpndAplys.size() > 0) {
			mav.addObject("result", pstpndAplyService.updatelistPstpndAply(pstpndAplys));
		} else {
			mav.addObject("result", 0);
		}

		mav.setViewName("jsonView");
		return mav;
	}

}