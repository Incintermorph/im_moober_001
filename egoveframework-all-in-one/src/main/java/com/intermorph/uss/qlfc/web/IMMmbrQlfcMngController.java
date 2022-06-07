/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.qlfc.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.intermorph.cmmn.IMGlobals;
import com.intermorph.cmmn.base.BaseController;
import com.intermorph.cmmn.exception.IMProcErrors;
import com.intermorph.cmmn.exception.IMProcException;
import com.intermorph.cmmn.service.IMInfoInqHstryService;
import com.intermorph.crs.agncy.service.IMAgncyService;
import com.intermorph.uss.hstry.service.IMMmbrHstryResultSet;
import com.intermorph.uss.hstry.service.IMMmbrHstryService;
import com.intermorph.uss.hstry.service.IMMmbrHstryVO;
import com.intermorph.uss.pstpnd.service.IMPstpndAplyResultSet;
import com.intermorph.uss.pstpnd.service.IMPstpndAplyService;
import com.intermorph.uss.pstpnd.service.IMPstpndAplyVO;
import com.intermorph.uss.qlfc.service.IMMmbrQlfcCondition;
import com.intermorph.uss.qlfc.service.IMMmbrQlfcService;
import com.intermorph.uss.qlfc.service.IMMmbrQlfcVO;

import egovframework.com.uss.umt.service.EgovMberManageService;
import egovframework.com.uss.umt.service.MberManageVO;
import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.qlfc.web
 * @File : IMMmbrQlfcController.java
 * @Title : 회원자격정보
 * @date : 2022.05.02 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Controller
public class IMMmbrQlfcMngController extends BaseController {

	@Resource (name = "IMMmbrQlfcService")
	private IMMmbrQlfcService mmbrQlfcService;

	@Resource (name = "propertiesService")
	protected EgovPropertyService propertyService;

	/** mberManageService */
	@Resource (name = "mberManageService")
	private EgovMberManageService mberManageService;

	@Resource (name = "IMAgncyService")
	private IMAgncyService agncyService;

	@Resource (name = "IMMmbrHstryService")
	private IMMmbrHstryService mmbrHstryService;

	@Resource (name = "IMPstpndAplyService")
	private IMPstpndAplyService pstpndAplyService;
	

	@Resource(name="IMInfoInqHstryService")
	IMInfoInqHstryService infoInqHstryService;
	
	/**
	 * 회원자격정보 목록
	 * 
	 * @param req
	 * @param res
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping (value = "/mng/mmbrQlfc/{nowType}/selectList.do")
	public ModelAndView selectList(HttpServletRequest req, HttpServletResponse res, IMMmbrQlfcCondition condition, @PathVariable ("nowType") String nowType)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		setEmptyValue(condition, "currentPageNo=1", "recordCountPerPage=" + propertyService.getInt("pageUnit"),
				"pageSize=" + propertyService.getInt("pageSize"), "orderbyKey=2");

		if ("01".equals(nowType)) {
			condition.setScTargetCode("NOW");
		} else if ("02".equals(nowType)) {
			condition.setScTargetCode("PASS");
		} else if ("A".equals(nowType)) {
			condition.setScTargetCode("A");
		} else {
			throw new IMProcException(IMProcErrors.필수값없음);
		}

		mav.addObject("pageInfo", mmbrQlfcService.selectListMmbrQlfc(condition));

		mav.addObject("agncyList", agncyService.selectListAgncy());

		mav.addObject("condition", condition);
		mav.addObject("nowType", nowType);
		

		//개인정보 이력 등록 
		Gson gson = new Gson();
		//이력 저장 
		infoInqHstryService.insertInfoInqHstry("comtngnrlmber","SELECTNOID",IMGlobals.IM_INFOINQ_S,gson.toJson(condition),req);

		if ("A".equals(nowType)) {
			mav.setViewName("layout/mng/uss/mmbrQlfc/selectListMmbrQlfcAll");
		}else {
			mav.setViewName("layout/mng/uss/mmbrQlfc/selectListMmbrQlfc");
		}
		return mav;
	}

	/**
	 * 유예신청 등록 화면
	 * 
	 * @param req
	 * @param res
	 * @param iMPstpndAply
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping (value = "/mng/mmbrQlfc/pstpndAply/{nowType}/regist.do")
	public ModelAndView regist(HttpServletRequest req, HttpServletResponse res, IMPstpndAplyVO iMPstpndAply, IMMmbrQlfcCondition condition, @PathVariable ("nowType") String nowType) throws Exception {
		ModelAndView mav = new ModelAndView();

		mav.addObject("iMPstpndAply", iMPstpndAply);

		mav.addObject("nowType", nowType);
		mav.addObject("condition", condition);

		IMMmbrHstryVO iMMmbrHstry = new IMMmbrHstryVO();
		iMMmbrHstry.setEsntlId(iMPstpndAply.getEsntlId());
		IMMmbrHstryResultSet detailHstry = (IMMmbrHstryResultSet)mmbrHstryService.selectDetailMmbrHstry(iMMmbrHstry);

		IMMmbrQlfcVO lastMmbrQlfc = mmbrQlfcService.selectDetailMmbrQlfc(iMPstpndAply.getEsntlId(), iMPstpndAply.getCrsGrdCdv());

		iMPstpndAply.setLcncEndYmd(lastMmbrQlfc.getLcncEndYmd());
		iMPstpndAply.setLcncIssuCode(lastMmbrQlfc.getLcncIssuCode());
		iMPstpndAply.setMmbrTelno(detailHstry.getMmbrHstry().getMblTelno());
		iMPstpndAply.setLcncAcqsYmd(lastMmbrQlfc.getLcncAcqsYmd());
		iMPstpndAply.setSttsCdv("02"); //바로 승인 처리함 

		MberManageVO userVO = mberManageService.selectMber(iMPstpndAply.getEsntlId());

		iMPstpndAply.setMberNm(userVO.getMberNm());
		iMPstpndAply.setMmbrId(userVO.getMberId());

		mav.addObject("detailHstry", detailHstry);

		mav.setViewName("layout/mng/uss/mmbrQlfc/registPstpndAplyMmbrQlfc");

		return mav;
	}
	
	/**
	 * 유예신청 등록 화면
	 * 
	 * @param req
	 * @param res
	 * @param iMPstpndAply
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping (value = "/mng/mmbrQlfc/pstpndAply/{nowType}/modify.do")
	public ModelAndView modify(HttpServletRequest req, HttpServletResponse res, IMPstpndAplyVO iMPstpndAply, IMMmbrQlfcCondition condition, @PathVariable ("nowType") String nowType) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		IMPstpndAplyResultSet detail = (IMPstpndAplyResultSet)pstpndAplyService.selectDetailPstpndAply(iMPstpndAply);

		mav.addObject("iMPstpndAply", detail.getPstpndAply());
		
		mav.addObject("detail", detail);

		mav.addObject("condition", condition);
		//개인정보 이력 등록 
		//이력 저장 
		infoInqHstryService.insertInfoInqHstry("im_pstpnd_aply",iMPstpndAply.getPstpndAplyId(),IMGlobals.IM_INFOINQ_R,"pstpnd modify",req);

		mav.setViewName("layout/mng/uss/mmbrQlfc/modifyPstpndAplyMmbrQlfc");
		
		return mav;
	}

}