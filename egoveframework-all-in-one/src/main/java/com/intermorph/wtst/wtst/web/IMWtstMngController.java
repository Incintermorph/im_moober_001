/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.wtst.wtst.web;


import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.google.gson.Gson;
import com.intermorph.cmmn.IMGlobals;
import com.intermorph.cmmn.base.BaseController;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;
import com.intermorph.cmmn.exception.IMProcErrors;
import com.intermorph.cmmn.exception.IMProcException;
import com.intermorph.cmmn.exception.IMWtstAplcntStts;
import com.intermorph.cmmn.service.IMCmmnDescService;
import com.intermorph.cmmn.service.IMCmmnDescVO;
import com.intermorph.cmmn.service.IMCmmnDtService;
import com.intermorph.cmmn.service.IMCmmnDtVO;
import com.intermorph.cmmn.service.IMCmmnSttsService;
import com.intermorph.cmmn.service.IMCmmnSttsVO;
import com.intermorph.cmmn.service.IMInfoInqHstryService;
import com.intermorph.cmmn.util.ExcelDownloadView;
import com.intermorph.cmmn.util.IMDateUtil;
import com.intermorph.wtst.aplcnt.service.IMWtstAplcntCondition;
import com.intermorph.wtst.aplcnt.service.IMWtstAplcntResultSet;
import com.intermorph.wtst.aplcnt.service.IMWtstAplcntService;
import com.intermorph.wtst.aplcnt.service.IMWtstAplcntVO;
import com.intermorph.wtst.place.service.IMWtstPlaceCondition;
import com.intermorph.wtst.place.service.IMWtstPlaceResultSet;
import com.intermorph.wtst.place.service.IMWtstPlaceService;
import com.intermorph.wtst.place.service.IMWtstPlaceVO;
import com.intermorph.wtst.wtst.service.IMWtstCondition;
import com.intermorph.wtst.wtst.service.IMWtstResultSet;
import com.intermorph.wtst.wtst.service.IMWtstService;
import com.intermorph.wtst.wtst.service.IMWtstVO;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.wtst.wtst.web
 * @File : IMWtstController.java
 * @Title : 필기시험
 * @date : 2022.03.31 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Controller
public class IMWtstMngController extends BaseController {


    @Resource (name = "IMWtstService")
	private IMWtstService wtstService;
	
	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;

	@Autowired
	private DefaultBeanValidator beanValidator;
	

    @Resource (name = "IMWtstPlaceService")
	private IMWtstPlaceService wtstPlaceService;

	private String cmmmDescTblId = "IM_WTST";
	

	@Resource(name = "IMCmmnDescService")
	private IMCmmnDescService cmmnDescService;

	@Resource(name = "IMCmmnDtService")
	private IMCmmnDtService cmmnDtService;


	@Resource (name = "IMWtstAplcntService")
	private IMWtstAplcntService wtstAplcntService;

	@Resource(name="IMInfoInqHstryService")
	IMInfoInqHstryService infoInqHstryService;
	

	@Resource(name = "IMCmmnSttsService")
	private IMCmmnSttsService cmmnSttsService;
	
	
	/**
	 * 필기시험 목록
	 * 
	 * @param req
	 * @param res
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/wtst/selectList.do")
	public ModelAndView selectList(HttpServletRequest req, HttpServletResponse res, IMWtstCondition condition)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		setEmptyValue(condition, "currentPageNo=1", "recordCountPerPage=" + propertyService.getInt("pageUnit"),
				"pageSize=" + propertyService.getInt("pageSize"), "orderbyKey=0");

		mav.addObject("pageInfo", wtstService.selectListWtst(condition));

		mav.addObject("condition", condition);

		mav.setViewName("layout/mng/wtst/wtst/selectListWtst");

		return mav;
	}
	
	
	
	/**
	 * 필기시험  등록 화면 
	 * @param req
	 * @param res
	 * @param iMWtst
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/wtst/regist.do")
	public ModelAndView regist(HttpServletRequest req, HttpServletResponse res, IMWtstVO iMWtst,
			IMWtstCondition condition) throws Exception {
		ModelAndView mav = new ModelAndView();

		mav.addObject("iMWtst", iMWtst);

		mav.addObject("condition", condition);

		mav.setViewName("layout/mng/wtst/wtst/registWtst");

		return mav;
	}
	
	/**
	 * 필기시험 수정 화면 
	 * @param req
	 * @param res
	 * @param iMWtst
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/wtst/modify.do")
	public ModelAndView modify(HttpServletRequest req, HttpServletResponse res, IMWtstVO iMWtst,
			IMWtstCondition condition) throws Exception {
		ModelAndView mav = new ModelAndView();

		IMWtstResultSet detail = (IMWtstResultSet) wtstService.selectDetailWtst(iMWtst);

		mav.addObject("iMWtst", detail.getWtst());

		mav.addObject("condition", condition);
		
		mav.addObject("cmmmDescMap", cmmnDescService.selectListCmmnDescResultMap(cmmmDescTblId, iMWtst.getWtstId()));
		mav.addObject("cmmmDtMap", cmmnDtService.selectListCmmnDtResultMap(cmmmDescTblId, iMWtst.getWtstId()));

		
		mav.setViewName("layout/mng/wtst/wtst/modifyWtst");
		
		return mav;
	}
	
	
	
	
	
	/**
	 * 필기시험 등록 저장 
	 * @param req
	 * @param res
	 * @param iMWtst
	 * @param cmmnDesc
	 * @param cmmnDt
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/wtst/insert.do")
	public ModelAndView insert(HttpServletRequest req, HttpServletResponse res,
			@ModelAttribute("iMWtst") IMWtstVO iMWtst ,
			IMCmmnDescVO cmmnDesc, IMCmmnDtVO cmmnDt, 
			BindingResult bindingResult, ModelMap model)
			throws Exception {

		ModelAndView mav = new ModelAndView();
		loginCheckSetAudit(req, iMWtst,cmmnDesc,cmmnDt);
		beanValidator.validate(iMWtst, bindingResult); // validation 수행

		if (bindingResult.hasErrors()) { // 만일 validation 에러가 있으면...
			mav.addObject("result", -1);
		}
		iMWtst.setTstYmd(IMDateUtil.convertStartDate(iMWtst.getTstYmd(), IMGlobals.IM_FORMAT_DATE, IMGlobals.IM_FORMAT_DBDATETIME,IMGlobals.IM_FORMAT_TIMEZONE));
		
		mav.addObject("result", wtstService.insertWtst(iMWtst));
		
		
		
		cmmnDescService.insertCmmnDesclist(cmmmDescTblId, iMWtst.getWtstId(), req, cmmnDesc);
		cmmnDtService.insertlistCmmnDt(cmmmDescTblId, iMWtst.getWtstId(), req, cmmnDt);

		mav.setViewName("jsonView");
		return mav;
	}
	
	
	/**
	 * 날짜 자동 계산
	 * 
	 * @param req
	 * @param res
	 * @param iMWtst
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mng/wtst/defaultdate/json.do")
	public ModelAndView defaultDataJson(HttpServletRequest req, HttpServletResponse res, IMWtstVO iMWtst)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		if (iMWtst.getPbancTerm_bgnDt() == null || "".equals(iMWtst.getPbancTerm_bgnDt())) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}

		Date pbancTerm_bgnDt = IMDateUtil.getFormatDate(iMWtst.getPbancTerm_bgnDt(), IMGlobals.IM_FORMAT_DATE);

		iMWtst.setPbancTerm_endDt(
				IMDateUtil.getFormatString(IMDateUtil.addDate(pbancTerm_bgnDt, 14), IMGlobals.IM_FORMAT_DATE));

		iMWtst.setRcptTerm_bgnDt(
				IMDateUtil.getFormatString(IMDateUtil.addDate(pbancTerm_bgnDt, 16), IMGlobals.IM_FORMAT_DATE));
		iMWtst.setRcptTerm_endDt(
				IMDateUtil.getFormatString(IMDateUtil.addDate(pbancTerm_bgnDt, 23), IMGlobals.IM_FORMAT_DATE));

		iMWtst.setTstYmd(
				IMDateUtil.getFormatString(IMDateUtil.addDate(pbancTerm_bgnDt, 32), IMGlobals.IM_FORMAT_DATE));
		iMWtst.setPassTerm_bgnDt(
				IMDateUtil.getFormatString(IMDateUtil.addDate(pbancTerm_bgnDt, 40), IMGlobals.IM_FORMAT_DATE));
		iMWtst.setPassTerm_endDt(
				IMDateUtil.getFormatString(IMDateUtil.addDate(pbancTerm_bgnDt, 50), IMGlobals.IM_FORMAT_DATE));

		iMWtst.setSlctnTerm_bgnDt(
				IMDateUtil.getFormatString(IMDateUtil.addDate(pbancTerm_bgnDt, 24), IMGlobals.IM_FORMAT_DATE));
		iMWtst.setSlctnTerm_endDt(
				IMDateUtil.getFormatString(IMDateUtil.addDate(pbancTerm_bgnDt, 26), IMGlobals.IM_FORMAT_DATE));

		iMWtst.setPayTerm_bgnDt(
				IMDateUtil.getFormatString(IMDateUtil.addDate(pbancTerm_bgnDt, 27), IMGlobals.IM_FORMAT_DATE));

		iMWtst.setPayTerm_endDt(
				IMDateUtil.getFormatString(IMDateUtil.addDate(pbancTerm_bgnDt, 30), IMGlobals.IM_FORMAT_DATE));

		mav.addObject("iMWtst", iMWtst);

		mav.setViewName("jsonView");
		return mav;
	}
	
	
	/**
	 * 필기시험 수정 저장
	 * @param req
	 * @param res
	 * @param iMWtst
	 * @param cmmnDesc
	 * @param cmmnDt
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/wtst/update.do")
	public ModelAndView update(HttpServletRequest req, HttpServletResponse res,
			@ModelAttribute("iMWtst") IMWtstVO iMWtst,
			IMCmmnDescVO cmmnDesc, IMCmmnDtVO cmmnDt, 
			BindingResult bindingResult, ModelMap model)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMWtst,cmmnDesc,cmmnDt);

		if (bindingResult.hasErrors()) { // 만일 validation 에러가 있으면...
			mav.addObject("result", -1);
		}
		iMWtst.setTstYmd(IMDateUtil.convertStartDate(iMWtst.getTstYmd(), IMGlobals.IM_FORMAT_DATE, IMGlobals.IM_FORMAT_DBDATETIME,IMGlobals.IM_FORMAT_TIMEZONE));
		mav.addObject("result",wtstService.updateWtst(iMWtst));
		
		cmmnDescService.insertCmmnDesclist(cmmmDescTblId, iMWtst.getWtstId(), req, cmmnDesc);
		cmmnDtService.insertlistCmmnDt(cmmmDescTblId, iMWtst.getWtstId(), req, cmmnDt);
		
		mav.setViewName("jsonView");
		return mav;
	}
	
	
	/**
	 *  필기시험  삭제
	 * @param req
	 * @param res
	 * @param iMwtst
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/wtst/delete.do")
	public ModelAndView delete(HttpServletRequest req, HttpServletResponse res, IMWtstVO iMWtst)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMWtst);

		mav.addObject("result", wtstService.deleteWtst(iMWtst));

		mav.setViewName("jsonView");
		return mav;
	}
	
	
	/**
	 *  필기시험 다중 삭제 처리
	 * 
	 * @param req
	 * @param res
	 * @param IMWtstVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping ("/mng/wtst/deletelist.do")
	public ModelAndView deletelist(HttpServletRequest req, HttpServletResponse res, IMWtstVO iMWtst) throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMWtst);
		

		List<IMWtstVO> wtsts = new ArrayList<IMWtstVO>();
				
		for (Long idx: iMWtst.getCheckIndexs()) {
			IMWtstVO o = new IMWtstVO();
			o.setWtstId(iMWtst.getWtstIds()[idx.intValue()]);
			o.copyAudit(iMWtst);

			wtsts.add(o);
		}

		if (wtsts.size() > 0) {
			mav.addObject("result", wtstService.deletelistWtst(wtsts));
		} else {
			mav.addObject("result", 0);
		}

		mav.setViewName("jsonView");
		return mav;
	}
	
	
	

	/**
	 * 필기시험장소 목록
	 * 
	 * @param req
	 * @param res
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/wtst/selectListPlace.do")
	public ModelAndView selectListPlace(HttpServletRequest req, HttpServletResponse res,IMWtstVO iMWtst,IMWtstCondition condition)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		
		if(condition.getPlace()==null) {
			condition.setPlace(new IMWtstPlaceCondition());
		}
		setEmptyValue(condition.getPlace(), "orderbyKey=2");
		

		if(iMWtst==null|| iMWtst.getWtstId()==null || "".equals(iMWtst.getWtstId())) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		condition.getPlace().setScWtstId(iMWtst.getWtstId());
		mav.addObject("pageInfo", wtstPlaceService.selectListWtstPlace(condition.getPlace()));

		mav.addObject("condition", condition);
		
		

		mav.setViewName("layout/mng/wtst/wtst/selectListPlace");

		return mav;
	}

	
	/**
	 * 신청자 목록
	 * 
	 * @param req
	 * @param res
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping (value = { "/mng/wtst/{tabSubCode}/selectListAplcnt.do", "/mng/wtst/{tabSubCode}/selectListAplcntExcel.do" })
	public ModelAndView selectListCrs(HttpServletRequest req, HttpServletResponse res,
			IMWtstVO iMWtst,IMWtstCondition condition,
			@PathVariable("tabSubCode") String tabSubCode
			)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		

				
		if(condition.getAplcnt()==null) {
			condition.setAplcnt(new IMWtstAplcntCondition());
			condition.getAplcnt().setOrderbyKey(-4);
		}
		condition.getAplcnt().setScWtstId(iMWtst.getWtstId());
		condition.getAplcnt().setScNotAplyCancleYn("Y");
		condition.getAplcnt().setScAplyStts("02"); //선정된 사용자만 노출
		if("05".equals(tabSubCode) || "99".equals(tabSubCode)) {
			condition.getAplcnt().setScDpstStts("Y");  	//입금된 사용자만 노출됨 
		}
		
		BasePage<BaseResultSet> pageInfo= wtstAplcntService.selectListWtstAplcnt(condition.getAplcnt());
		mav.addObject("pageInfo", pageInfo);

		mav.addObject("condition", condition);
		mav.addObject("tabSubCode", tabSubCode);

		IMWtstResultSet detailWtst = (IMWtstResultSet) wtstService.selectDetailWtst(iMWtst);

		mav.addObject("detailWtst", detailWtst);

		//개인정보 이력 등록 
		Gson gson = new Gson();
		if(req.getServletPath().indexOf("selectListAplcntExcel")!=-1) {
			infoInqHstryService.insertInfoInqHstry("im_crs_aplcnt","SELECTNOID",IMGlobals.IM_INFOINQ_E,gson.toJson(iMWtst),req);
			mav.addObject("list", pageInfo.getList());
			
			if("05".equals(tabSubCode)) {
				mav.addObject(ExcelDownloadView.TEMPLATE_FILE_NAME, "selectListWtstAplcntApply");
			}else {
				throw new IMProcException(IMProcErrors.필수값없음);
			}
			mav.setViewName("excelView");
			mav.addObject(ExcelDownloadView.DOWNLOAD_FILE_NAME, ExcelDownloadView.getDownloadName(req));
			
			
		}else {

			//이력 저장 
			infoInqHstryService.insertInfoInqHstry("im_wtst_aplcnt","SELECTNOID",IMGlobals.IM_INFOINQ_S,gson.toJson(condition.getAplcnt()),req);
			
			mav.setViewName("layout/mng/wtst/wtst/selectListAplcntResult");
			if("04".equals(tabSubCode)){
				mav.setViewName("layout/mng/wtst/wtst/selectListPlaceAplcntDpst");	
			}else if("05".equals(tabSubCode)){ //응시자
				mav.setViewName("layout/mng/wtst/wtst/selectListPlaceAplcntApply");	
			}
		}
		return mav;
	}
	/**
	 * 필기시험 수정자 수정 
	 * @param req
	 * @param res
	 * @param iMWtstAplcnt
	 * @return
	 * @throws Exception
	 */
	@RequestMapping (value = "/mng/wtst/wtstAplcnt/modify.do")
	public ModelAndView modify(HttpServletRequest req, HttpServletResponse res, @ModelAttribute ("iMWtstAplcnt") IMWtstAplcntVO iMWtstAplcnt,IMWtstCondition condition) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		if(iMWtstAplcnt.getWtstAplcntId()==null || "".equals(iMWtstAplcnt.getWtstAplcntId())) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		
		IMWtstAplcntResultSet  detailApply = (IMWtstAplcntResultSet)wtstAplcntService.selectDetailWtstAplcnt(iMWtstAplcnt);
		

		if(detailApply==null) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		
		
		
		IMWtstPlaceVO iMWtstPlace = new IMWtstPlaceVO();

		iMWtstPlace.setWtstPlaceId(detailApply.getWtstAplcnt().getWtstPlaceId());

		IMWtstPlaceResultSet detail = (IMWtstPlaceResultSet) wtstPlaceService.selectDetailWtstPlace(iMWtstPlace);
		

		mav.addObject("detailApply", detailApply);
		mav.addObject("aplcnt", detailApply.getWtstAplcnt());
		mav.addObject("detail", detail);
		mav.addObject("condition",  condition);
		
		mav.addObject("cmmmDescMap", cmmnDescService.selectListCmmnDescResultMap("IM_WTST", detail.getWtstPlace().getWtstId()));
		
		mav.addObject("cmmmDtStts", cmmnSttsService.selectListCmmnSttsResultMap("IM_WTST_APLCNT", iMWtstAplcnt.getWtstAplcntId()));
		
		mav.addObject("cmmmDtMap", cmmnDtService.selectListCmmnDtResultMap("IM_WTST", detail.getWtstPlace().getWtstId()));
		
		mav.setViewName("layout/mng/wtst/wtst/modifyAplcntResult");

		return mav;
	}
	
	
	/**
	 * 신청자 목록 (자격심사)
	 * 
	 * @param req
	 * @param res
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/wtst/wtstPlace/{tabSubCode}/selectListAplcnt.do")
	public ModelAndView selectListwtst(HttpServletRequest req, HttpServletResponse res,
			IMWtstPlaceVO iMWtstPlace,
			IMWtstCondition condition,
			@PathVariable("tabSubCode") String tabSubCode
			)
					throws Exception {
		
		ModelAndView mav = new ModelAndView();
		
		
		IMWtstPlaceResultSet detail = (IMWtstPlaceResultSet) wtstPlaceService.selectDetailWtstPlace(iMWtstPlace);
		
		
		if(detail==null) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		mav.addObject("detail", detail);
		
		if(condition.getAplcnt()==null) {
			condition.setAplcnt(new IMWtstAplcntCondition());
			condition.getAplcnt().setOrderbyKey(-4);
		}
		condition.getAplcnt().setScWtstPlaceId(iMWtstPlace.getWtstPlaceId());
		condition.getAplcnt().setScNotAplyCancleYn("Y");
		if("03".equals(tabSubCode)) {
			condition.getAplcnt().setScFnshYnCheck("Y"); 
		}
		if("04".equals(tabSubCode) || "05".equals(tabSubCode) || "06".equals(tabSubCode)) {
			condition.getAplcnt().setScAplyStts("02"); //선정된 사용자만 노출 	
		}
		
		if("05".equals(tabSubCode)) {
			condition.getAplcnt().setScDpstStts("Y"); 
		}
		
		mav.addObject("pageInfo", wtstAplcntService.selectListWtstAplcnt(condition.getAplcnt()));
		
		mav.addObject("condition", condition);
		mav.addObject("tabSubCode", tabSubCode);
		
		//개인정보 이력 등록 
		Gson gson = new Gson();
		//이력 저장 
		infoInqHstryService.insertInfoInqHstry("im_wtst_aplcnt","SELECTNOID",IMGlobals.IM_INFOINQ_S,gson.toJson(condition.getAplcnt()),req);
		
		mav.setViewName("layout/mng/wtst/wtst/selectListPlaceAplcnt");
		if("04".equals(tabSubCode)){
			mav.setViewName("layout/mng/wtst/wtst/selectListPlaceAplcntDpst");	
		}else if("05".equals(tabSubCode)){ //응시자
			mav.setViewName("layout/mng/wtst/wtst/selectListPlaceAplcntApply");	
		}
		
		return mav;
	}
	
}