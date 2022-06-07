/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.crs.aplcnt.web;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import com.intermorph.cmmn.exception.IMCrsAplcntStts;
import com.intermorph.cmmn.exception.IMProcErrors;
import com.intermorph.cmmn.exception.IMProcException;
import com.intermorph.cmmn.service.IMCmmnSttsCondition;
import com.intermorph.cmmn.service.IMCmmnSttsService;
import com.intermorph.cmmn.service.IMCmmnSttsVO;
import com.intermorph.cmmn.service.IMInfoInqHstryService;
import com.intermorph.cmmn.service.IMInfoInqHstryVO;
import com.intermorph.cmmn.util.IMStringUtil;
import com.intermorph.crs.agncy.service.IMAgncyCondition;
import com.intermorph.crs.agncy.service.IMAgncyService;
import com.intermorph.crs.aplcnt.service.IMCrsAplcntCondition;
import com.intermorph.crs.aplcnt.service.IMCrsAplcntService;
import com.intermorph.crs.aplcnt.service.IMCrsAplcntVO;
import com.intermorph.crs.mstr.service.IMCrsMstrService;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.crs.aplcnt.web
 * @File : IMCrsAplcntController.java
 * @Title : 과정신청자
 * @date : 2022.03.04 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Controller
public class IMCrsAplcntMngController extends BaseController {


    @Resource (name = "IMCrsAplcntService")
	private IMCrsAplcntService crsAplcntService;
	
	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;

	

	@Resource(name = "IMAgncyService")
	private IMAgncyService agncyService;

	@Resource(name = "IMCrsMstrService")
	private IMCrsMstrService crsMstrService;
	

	@Resource(name = "IMCmmnSttsService")
	private IMCmmnSttsService  cmmnSttsService;
	

	@Resource(name="IMInfoInqHstryService")
	IMInfoInqHstryService infoInqHstryService;

	
	
	/**
	 * 과정신청자 목록
	 * 
	 * @param req
	 * @param res
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/crsAplcnt/{aplyStts}/selectList.do")
	public ModelAndView selectList(HttpServletRequest req, HttpServletResponse res, IMCrsAplcntCondition condition,@PathVariable("aplyStts") String aplyStts)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		setEmptyValue(condition, "currentPageNo=1", "recordCountPerPage=" + propertyService.getInt("pageUnit"),
				"pageSize=" + propertyService.getInt("pageSize"), "orderbyKey=0","scEduYear="+IMGlobals.IM_NOW_YEAR
				);
		
		
		if("A".equals(aplyStts)) {
			condition.setScNotAplyCancleYn("Y");
			condition.setScAplyStts(null);
			condition.setScSttsCdvFNSH("01");
		}else {
			condition.setScNotAplyCancleYn(null);
			condition.setScAplyStts(aplyStts);
			condition.setScSttsCdvFNSH(null);
		}
		mav.addObject("condition", condition);
		mav.addObject("aplyStts", aplyStts);
		

		String loginAgncyId = IMStringUtil.getAttribute(req, IMGlobals.IM_LOGIN_USER_AGNCY_KEY);
		if (!"".equals(loginAgncyId)) {
			condition.setScAgncyId(loginAgncyId);
		}else {
			mav.addObject("agncyList", agncyService.selectListAgncy());
		}

		mav.addObject("pageInfo", crsAplcntService.selectListCrsAplcnt(condition));

		//개인정보 이력 등록 
		Gson gson = new Gson();
		//이력 저장 
		infoInqHstryService.insertInfoInqHstry("im_crs_aplcnt","SELECTNOID",IMGlobals.IM_INFOINQ_S,gson.toJson(condition),req);
		
		mav.setViewName("layout/mng/crs/crsAplcnt/selectListCrsAplcnt");

		return mav;
	}
	
	
	

	
	
	/**
	 *  과정신청자 다중 신청상태 업데이트 처리
	 * 
	 * @param req
	 * @param res
	 * @param IMCrsAplcntVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping ("/mng/crsAplcnt/{crsAplcntStts}/updateAplylist.do")
	public ModelAndView updateAplylist(HttpServletRequest req, HttpServletResponse res, IMCrsAplcntVO iMCrsAplcnt,@PathVariable("crsAplcntStts") String crsAplcntStts) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		loginCheckSetAudit(req, iMCrsAplcnt);
		int result =0;
		String sttsKey =null;

        for(IMCrsAplcntStts v : IMCrsAplcntStts.values()) {
			if(v.sttsKey.equals(crsAplcntStts)){
				sttsKey=v.sttsKey;
			}
        }
        
		if(sttsKey!=null) {
			for (Long idx: iMCrsAplcnt.getCheckIndexs()) {
				IMCrsAplcntVO o = new IMCrsAplcntVO();
				o.copyAudit(iMCrsAplcnt);
				o.setCrsAplcntId(iMCrsAplcnt.getCrsAplcntIds()[idx.intValue()]);
				result += cmmnSttsService.updateCmmnStts("IM_CRS_APLCNT", iMCrsAplcnt.getCrsAplcntIds()[idx.intValue()], sttsKey, iMCrsAplcnt.getChageStts(),iMCrsAplcnt.getChageMsg(), iMCrsAplcnt);
				
				//수료 처리 및 미수료 변경 처리 
				if(IMCrsAplcntStts.수료상태.sttsKey.equals(sttsKey) ) {
					o.setFnshSttsCdv(iMCrsAplcnt.getChageStts());
					crsAplcntService.updateCrsAplcntRslt(o,"FNSH");
				}
				//자격증 취득 처리 
				if(IMCrsAplcntStts.자격취득상태.sttsKey.equals(sttsKey) ) {
					o.setQlfcSttsCdv(iMCrsAplcnt.getChageStts());
					crsAplcntService.updateCrsAplcntRslt(o,"QLFC");
				}
				//crsAplcnts.add(o);
			}
		}
		
		
		mav.addObject("result", result);
		
		mav.setViewName("jsonView");
		return mav;
	}
	
	/**
	 * 특이 사항 관리 (상태별)
	 * @param req
	 * @param res
	 * @param iMCrsAplcnt
	 * @param crsAplcntStts
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/crsAplcnt/stts/{crsAplcntStts}/modify.do")
	public ModelAndView modifyStts(HttpServletRequest req, HttpServletResponse res, IMCrsAplcntVO iMCrsAplcnt,
			@PathVariable("crsAplcntStts") String crsAplcntStts,
			IMAgncyCondition condition) throws Exception {
		ModelAndView mav = new ModelAndView();

		String sttsKey =null;
		for(IMCrsAplcntStts v : IMCrsAplcntStts.values()) {
			if(v.sttsKey.equals(crsAplcntStts)){
				sttsKey=v.sttsKey;
			}
        }
		
		mav.addObject("crsAplcntStts", sttsKey);
		

		mav.addObject("detail", crsAplcntService.selectDetailCrsAplcnt(iMCrsAplcnt));
		HashMap<String, IMCmmnSttsVO>  cmmnSttsMap= cmmnSttsService.selectListCmmnSttsResultObjectMap("IM_CRS_APLCNT", iMCrsAplcnt.getCrsAplcntId());
		mav.addObject("detailStts", cmmnSttsMap.get(sttsKey) );
		
		
		mav.setViewName("layer/mng/crs/crsAplcnt/modifyCrsAplcntSttsLayer");
		
		return mav;
	}
	
	/**
	 * 신청상태 비고 업데이트 처리
	 * @param req
	 * @param res
	 * @param iMCrsAplcnt
	 * @param cmmnSttsVO
	 * @param crsAplcntStts
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/crsAplcnt/stts/{crsAplcntStts}/update.do")
	public ModelAndView updateAplyStts(HttpServletRequest req, HttpServletResponse res, IMCrsAplcntVO iMCrsAplcnt,IMCmmnSttsVO cmmnSttsVO,@PathVariable("crsAplcntStts") String crsAplcntStts) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		loginCheckSetAudit(req, iMCrsAplcnt,cmmnSttsVO);
		int result =0;
		String sttsKey =null;

        for(IMCrsAplcntStts v : IMCrsAplcntStts.values()) {
			if(v.sttsKey.equals(crsAplcntStts)){
				sttsKey=v.sttsKey;
			}
        }
		if(sttsKey!=null) {
			result = cmmnSttsService.updateCmmnStts("IM_CRS_APLCNT", iMCrsAplcnt.getCrsAplcntId(), sttsKey, cmmnSttsVO.getSttsCdv(),cmmnSttsVO.getSttsRmks() , cmmnSttsVO);
			//운영사무국 심사인 경우 별도 처리 (수수료변경)
			if(crsAplcntStts.equals(IMCrsAplcntStts.기관심사.sttsKey)) {
				crsAplcntService.updateCrsAplcnt(iMCrsAplcnt);
			}
		}
		
		
		mav.addObject("result", result);
		
		mav.setViewName("jsonView");
		return mav;
	}
	
	
	
	/**
	 * 변경 사유 등록
	 * @param req
	 * @param res
	 * @param cmmnStts
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/crsAplcnt/select/memoLayer.do")
	public ModelAndView memoLayer(HttpServletRequest req, HttpServletResponse res,IMCmmnSttsVO cmmnStts) throws Exception {
		ModelAndView mav = new ModelAndView();

		mav.addObject("cmmnStts", cmmnStts);
		mav.setViewName("layer/mng/crs/crsAplcnt/selectListCrsAplcntMemoLayer");
		
		return mav;
	}
	

	/**
	 * 공통상태정보 목록
	 * 
	 * @param req
	 * @param res
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/crsAplcnt/cmmnStts/{sttsCdv}/selectListLayer.do")
	public ModelAndView selectList(HttpServletRequest req, HttpServletResponse res, IMCmmnSttsCondition condition,@PathVariable("sttsCdv") String sttsCdv)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		setEmptyValue(condition, "currentPageNo=1", "recordCountPerPage=" + propertyService.getInt("pageUnit"),
				"pageSize=" + propertyService.getInt("pageSize"), "orderbyKey=0", "scRefNm="+IMCrsAplcntStts.신청상태.sttsKey);

		condition.setScTblId("IM_CRS_APLCNT");
		//신청값 체크 
		if(condition.getScTblRefId()==null || "".equals(condition.getScTblRefId())){
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		
		
		
		String sttsKey =null;

        for(IMCrsAplcntStts v : IMCrsAplcntStts.values()) {
			if(v.sttsKey.equals(sttsCdv)){
				sttsKey=v.sttsKey;
			}
        }
        if(sttsKey==null) {
        	throw new IMProcException(IMProcErrors.필수값없음);
        }
        condition.setScRefNm(sttsKey);
        
		
		mav.addObject("pageInfo", cmmnSttsService.selectListHstryCmmnStts(condition));

		mav.addObject("sttsCdv", sttsCdv);
		mav.addObject("condition", condition);
		
		mav.setViewName("layer/mng/crs/crsAplcnt/selectListCrsAplcntCmmnSttsLayer");

		return mav;
	}
	
}