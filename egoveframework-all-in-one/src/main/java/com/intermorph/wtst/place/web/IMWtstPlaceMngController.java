/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.wtst.place.web;


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
import com.intermorph.cmmn.exception.IMProcErrors;
import com.intermorph.cmmn.exception.IMProcException;
import com.intermorph.cmmn.service.IMCmmnDescService;
import com.intermorph.cmmn.service.IMCmmnDtService;
import com.intermorph.cmmn.service.IMCmmnSttsService;
import com.intermorph.cmmn.service.IMInfoInqHstryService;
import com.intermorph.cmmn.util.IMStringUtil;
import com.intermorph.crs.agncy.service.IMAgncyCondition;
import com.intermorph.crs.agncy.service.IMAgncyResultSet;
import com.intermorph.crs.agncy.service.IMAgncyService;
import com.intermorph.wtst.aplcnt.service.IMWtstAplcntCondition;
import com.intermorph.wtst.aplcnt.service.IMWtstAplcntService;
import com.intermorph.wtst.place.service.IMWtstPlaceCondition;
import com.intermorph.wtst.place.service.IMWtstPlaceResultSet;
import com.intermorph.wtst.place.service.IMWtstPlaceService;
import com.intermorph.wtst.place.service.IMWtstPlaceVO;
import com.intermorph.wtst.wtst.service.IMWtstResultSet;
import com.intermorph.wtst.wtst.service.IMWtstService;
import com.intermorph.wtst.wtst.service.IMWtstVO;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.wtst.place.web
 * @File : IMWtstPlaceController.java
 * @Title : 필기시험장소
 * @date : 2022.03.31 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Controller
public class IMWtstPlaceMngController extends BaseController {


    @Resource (name = "IMWtstPlaceService")
	private IMWtstPlaceService wtstPlaceService;

	@Resource (name = "IMWtstAplcntService")
	private IMWtstAplcntService wtstAplcntService;
	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;


	@Resource(name = "IMCmmnSttsService")
	private IMCmmnSttsService  cmmnSttsService;
	
	@Autowired
	private DefaultBeanValidator beanValidator;
	

	@Resource(name = "IMAgncyService")
	private IMAgncyService agncyService;

    @Resource (name = "IMWtstService")
	private IMWtstService wtstService;
	

	@Resource(name = "IMCmmnDescService")
	private IMCmmnDescService cmmnDescService;
    

	@Resource(name = "IMCmmnDtService")
	private IMCmmnDtService cmmnDtService;
	

	@Resource(name="IMInfoInqHstryService")
	IMInfoInqHstryService infoInqHstryService;
	
	
	/**
	 * 필기시험장소 목록
	 * 
	 * @param req
	 * @param res
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/wtstPlace/selectList.do")
	public ModelAndView selectList(HttpServletRequest req, HttpServletResponse res, IMWtstPlaceCondition condition)
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
		
		mav.addObject("pageInfo", wtstPlaceService.selectListWtstPlace(condition));

		mav.addObject("condition", condition);

		mav.setViewName("layout/mng/wtst/place/selectListWtstPlace");

		return mav;
	}
	
	
	
	/**
	 * 필기시험장소  등록 화면 레이어
	 * @param req
	 * @param res
	 * @param iMWtstPlace
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/wtstPlace/regist/layer.do")
	public ModelAndView regist(HttpServletRequest req, HttpServletResponse res, IMWtstPlaceVO iMWtstPlace
			) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("agncyList", agncyService.selectListAgncyPlace());

		IMAgncyCondition agncyCondition = new IMAgncyCondition();
		//연계 포탈 코드 - 국간 환경교육센터 데이터 기본 세팅  
		agncyCondition.setScLinkCode("200632");
		BasePage<BaseResultSet> baseResult=  agncyService.selectListAgncy(agncyCondition);
		
		if(baseResult!=null && baseResult.getTotalRecordCount()>0) {
			IMAgncyResultSet rs =(IMAgncyResultSet)  baseResult.getList().get(0);
			
			iMWtstPlace.setCntpnt(rs.getAgncy().getTelno());
			iMWtstPlace.setBnkCdv(rs.getAgncy().getBnkCdv());
			iMWtstPlace.setAccno(rs.getAgncy().getAccno());
			iMWtstPlace.setAcchdr(rs.getAgncy().getAcchdr());
			
		}
		
		mav.addObject("iMWtstPlace", iMWtstPlace);
		

		mav.setViewName("layer/mng/wtst/place/registWtstPlaceLayer");

		return mav;
	}
	
	/**
	 * 필기시험장소 수정 화면  레이어
	 * @param req
	 * @param res
	 * @param iMWtstPlace
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/wtstPlace/modify/layer.do")
	public ModelAndView modifyLayer(HttpServletRequest req, HttpServletResponse res, IMWtstPlaceVO iMWtstPlace,
			IMWtstPlaceCondition condition) throws Exception {
		ModelAndView mav = new ModelAndView();

		IMWtstPlaceResultSet detail = (IMWtstPlaceResultSet) wtstPlaceService.selectDetailWtstPlace(iMWtstPlace);

		mav.addObject("iMWtstPlace", detail.getWtstPlace());
		mav.addObject("detail", detail);
		

		mav.addObject("condition", condition);
		
		
		mav.setViewName("layer/mng/wtst/place/modifyWtstPlaceLayer");
		
		return mav;
	}
	
	/**
	 * 필기시험장소 수정 화면 
	 * @param req
	 * @param res
	 * @param iMWtstPlace
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/wtstPlace/modify.do")
	public ModelAndView modify(HttpServletRequest req, HttpServletResponse res, IMWtstPlaceVO iMWtstPlace,
			IMWtstPlaceCondition condition) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		IMWtstPlaceResultSet detail = (IMWtstPlaceResultSet) wtstPlaceService.selectDetailWtstPlace(iMWtstPlace);
		
		mav.addObject("iMWtstPlace", detail.getWtstPlace());
		mav.addObject("detail", detail);
		

		mav.addObject("condition", condition);
		
		mav.setViewName("layout/mng/wtst/place/modifyWtstPlace");
		
		return mav;
	}
	
	/**
	 * 필기시험장소 수정 화면 
	 * @param req
	 * @param res
	 * @param iMWtstPlace
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/wtstPlace/selectDetailWtst.do")
	public ModelAndView selectDetailWtst(HttpServletRequest req, HttpServletResponse res, IMWtstPlaceVO iMWtstPlace,
			IMWtstPlaceCondition condition) throws Exception {
		ModelAndView mav = new ModelAndView();

		IMWtstPlaceResultSet detail = (IMWtstPlaceResultSet) wtstPlaceService.selectDetailWtstPlace(iMWtstPlace);
		mav.addObject("detail", detail);
		
		
		IMWtstVO iMWtst = new IMWtstVO();
		iMWtst.setWtstId(detail.getWtstPlace().getWtstId());
		IMWtstResultSet detailWtst = (IMWtstResultSet) wtstService.selectDetailWtst(iMWtst);

		mav.addObject("iMWtst", detailWtst.getWtst());

		mav.addObject("condition", condition);
		
		mav.addObject("cmmmDescMap", cmmnDescService.selectListCmmnDescResultMap("IM_WTST", iMWtst.getWtstId()));
		mav.addObject("cmmmDtMap", cmmnDtService.selectListCmmnDtResultMap("IM_WTST", iMWtst.getWtstId()));
		
		mav.addObject("condition", condition);
		
		mav.setViewName("layout/mng/wtst/place/selectDetailWtst");
		
		return mav;
	}
	
	
	
	
	
	/**
	 * 필기시험장소 등록 저장 
	 * @param req
	 * @param res
	 * @param iMwtstPlace
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/wtstPlace/insert.do")
	public ModelAndView insert(HttpServletRequest req, HttpServletResponse res,
			@ModelAttribute("iMWtstPlace") IMWtstPlaceVO iMWtstPlace ,
			BindingResult bindingResult, ModelMap model)
			throws Exception {

		ModelAndView mav = new ModelAndView();
		loginCheckSetAudit(req, iMWtstPlace);
		beanValidator.validate(iMWtstPlace, bindingResult); // validation 수행

		if (bindingResult.hasErrors()) { // 만일 validation 에러가 있으면...
			mav.addObject("result", -1);
		}

		mav.addObject("result", wtstPlaceService.insertWtstPlace(iMWtstPlace));

		mav.setViewName("jsonView");
		return mav;
	}
	
	/**
	 * 필기시험장소 수정 저장
	 * @param req
	 * @param res
	 * @param iMwtstPlace
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/wtstPlace/update.do")
	public ModelAndView update(HttpServletRequest req, HttpServletResponse res,
			@ModelAttribute("iMWtstPlace") IMWtstPlaceVO iMWtstPlace,
			BindingResult bindingResult, ModelMap model)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMWtstPlace);

		if (bindingResult.hasErrors()) { // 만일 validation 에러가 있으면...
			mav.addObject("result", -1);
		}

		mav.addObject("result",wtstPlaceService.updateWtstPlace(iMWtstPlace));
		
		mav.setViewName("jsonView");
		return mav;
	}

	
	
	
	/**
	 *  필기시험장소  삭제
	 * @param req
	 * @param res
	 * @param iMwtstPlace
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/wtstPlace/delete.do")
	public ModelAndView delete(HttpServletRequest req, HttpServletResponse res, IMWtstPlaceVO iMWtstPlace)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMWtstPlace);

		mav.addObject("result", wtstPlaceService.deleteWtstPlace(iMWtstPlace));

		mav.setViewName("jsonView");
		return mav;
	}
	
	
	/**
	 *  필기시험장소 다중 삭제 처리
	 * 
	 * @param req
	 * @param res
	 * @param IMWtstPlaceVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping ("/mng/wtstPlace/deletelist.do")
	public ModelAndView deletelist(HttpServletRequest req, HttpServletResponse res, IMWtstPlaceVO iMWtstPlace) throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMWtstPlace);
		

		List<IMWtstPlaceVO> wtstPlaces = new ArrayList<IMWtstPlaceVO>();
				
		for (Long idx: iMWtstPlace.getCheckIndexs()) {
			IMWtstPlaceVO o = new IMWtstPlaceVO();
			
			o.setWtstPlaceId(iMWtstPlace.getWtstPlaceIds()[idx.intValue()]);
			o.copyAudit(iMWtstPlace);

			wtstPlaces.add(o);
		}

		if (wtstPlaces.size() > 0) {
			mav.addObject("result", wtstPlaceService.deletelistWtstPlace(wtstPlaces));
		} else {
			mav.addObject("result", 0);
		}

		mav.setViewName("jsonView");
		return mav;
	}
	/**
	 *  필기시험장소 다중 수정 순서 처리
	 * 
	 * @param req
	 * @param res
	 * @param IMWtstPlaceVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping ("/mng/wtstPlace/udpateOrderlist.do")
	public ModelAndView udpateOrderlist(HttpServletRequest req, HttpServletResponse res, IMWtstPlaceVO iMWtstPlace) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		loginCheckSetAudit(req, iMWtstPlace);
		
		
		List<IMWtstPlaceVO> wtstPlaces = new ArrayList<IMWtstPlaceVO>();
		
		for (int i=0;i<iMWtstPlace.getWtstPlaceIds().length;i++) {
			IMWtstPlaceVO o = new IMWtstPlaceVO();
			
			o.setWtstPlaceId(iMWtstPlace.getWtstPlaceIds()[i]);
			o.setOrd(i+"");
			o.copyAudit(iMWtstPlace);
			
			wtstPlaces.add(o);
		}
		
		if (wtstPlaces.size() > 0) {
			mav.addObject("result", wtstPlaceService.updatelistWtstPlace(wtstPlaces));
		} else {
			mav.addObject("result", 0);
		}
		
		mav.setViewName("jsonView");
		return mav;
	}
	
	
	
	/**
	 * 과정신청자 목록
	 * 
	 * @param req
	 * @param res
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/wtstPlace/{tabCode}/selectListAplcnt.do")
	public ModelAndView selectListAplcnt(HttpServletRequest req, HttpServletResponse res,
			 IMWtstPlaceVO iMWtstPlace,
			IMWtstPlaceCondition condition,
			@PathVariable("tabCode") String tabCode
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
		if("03".equals(tabCode)) {
			condition.getAplcnt().setScFnshYnCheck("Y"); 
		}
		if("04".equals(tabCode) || "05".equals(tabCode) || "06".equals(tabCode)) {
			condition.getAplcnt().setScAplyStts("02"); //선정된 사용자만 노출 	
		}
		
		if("05".equals(tabCode)) {
			condition.getAplcnt().setScDpstStts("Y"); 
		}
		
		mav.addObject("pageInfo", wtstAplcntService.selectListWtstAplcnt(condition.getAplcnt()));

		mav.addObject("condition", condition);
		mav.addObject("tabCode", tabCode);
		
		//개인정보 이력 등록 
		Gson gson = new Gson();
		//이력 저장 
		infoInqHstryService.insertInfoInqHstry("im_wtst_aplcnt","SELECTNOID",IMGlobals.IM_INFOINQ_S,gson.toJson(condition.getAplcnt()),req);
		
		mav.setViewName("layout/mng/wtst/place/selectListAplcnt");
		if("04".equals(tabCode)){
			mav.setViewName("layout/mng/wtst/place/selectListAplcntDpst");	
		}else if("05".equals(tabCode)){ //응시자
			mav.setViewName("layout/mng/wtst/place/selectListAplcntApply");	
		}

		return mav;
	}
	

	
	/**
	 * 과정(운영과정) 수정 저장
	 * 
	 * @param req
	 * @param res
	 * @param iMWtstPlace
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/wtstPlace/{prgrsSttsCdv}/updatePrgrsSttsCdv.do")
	public ModelAndView updatePrgrsSttsCdv(HttpServletRequest req, HttpServletResponse res, IMWtstPlaceVO iMWtstPlace,@PathVariable("prgrsSttsCdv") String prgrsSttsCdv) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		loginCheckSetAudit(req, iMWtstPlace);
		iMWtstPlace.setPrgrsSttsCdv(prgrsSttsCdv);
		
		//선정 확정  
		if("02".equals(prgrsSttsCdv) || "04".equals(prgrsSttsCdv)) {
			 wtstAplcntService.updateAplyOPSECTSRNGtoApply(iMWtstPlace);
		}
		
		 cmmnSttsService.updateCmmnStts("IM_WTST_PLACE", iMWtstPlace.getWtstPlaceId(), "prgrsSttsCdv", prgrsSttsCdv, iMWtstPlace);
	
		 int result =wtstPlaceService.updateWtstAplcntPrgrsSttsCdv(iMWtstPlace); 
		
		
		mav.addObject("result",result);
		
	
		mav.setViewName("jsonView");
		return mav;
	}
	
}