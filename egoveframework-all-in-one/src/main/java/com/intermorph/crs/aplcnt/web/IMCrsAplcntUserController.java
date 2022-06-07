/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.crs.aplcnt.web;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.intermorph.cmmn.base.BaseController;
import com.intermorph.cmmn.exception.IMCrsAplcntStts;
import com.intermorph.cmmn.service.IMCmmnDtService;
import com.intermorph.cmmn.service.IMCmmnDtVO;
import com.intermorph.cmmn.service.IMCmmnSttsService;
import com.intermorph.cmmn.util.IMDateUtil;
import com.intermorph.crs.aplcnt.service.IMCrsAplcntResultSet;
import com.intermorph.crs.aplcnt.service.IMCrsAplcntService;
import com.intermorph.crs.aplcnt.service.IMCrsAplcntVO;
import com.intermorph.crs.crs.service.IMCrsResultSet;
import com.intermorph.crs.crs.service.IMCrsService;
import com.intermorph.crs.crs.service.IMCrsVO;
import com.intermorph.uss.qlfc.service.IMMmbrQlfcService;
import com.intermorph.uss.qlfc.service.IMMmbrQlfcVO;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.crs.aplcnt.web
 * @File : IMCrsAplcntController.java
 * @Title : 운영과정신청자
 * @date : 2022.03.03 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Controller
public class IMCrsAplcntUserController extends BaseController {

	@Resource(name = "IMCrsAplcntService")
	private IMCrsAplcntService crsAplcntService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;

	@Resource(name = "IMCrsService")
	private IMCrsService crsService;

	@Resource(name = "IMCmmnSttsService")
	private IMCmmnSttsService  cmmnSttsService;

    @Resource (name = "IMMmbrQlfcService")
    private IMMmbrQlfcService mmbrQlfcService;

    

	@Resource(name = "IMCmmnDtService")
	private IMCmmnDtService cmmnDtService;
	
	
	/**
	 * 운영과정신청자 등록 저장
	 * 
	 * @param req
	 * @param res
	 * @param iMcrsAplcnt
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/crsAplcnt/apply.do" , method = RequestMethod.POST)
	public ModelAndView insert(HttpServletRequest req, HttpServletResponse res, IMCrsAplcntVO iMCrsAplcnt,
			BindingResult bindingResult, ModelMap model) throws Exception {

		ModelAndView mav = new ModelAndView();
		loginCheckSetAudit(req, iMCrsAplcnt);
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		// 로그인한 사용자 정보
		iMCrsAplcnt.setMmbrEsntlId(user.getUniqId());

		int resultCode = 0;

		if (iMCrsAplcnt.getCrsId() == null) {
			resultCode = -1;
		} else {			
			if(resultCode==0) {
				IMCrsVO iMCrs = new IMCrsVO();
				iMCrs.setCrsId(iMCrsAplcnt.getCrsId());
				IMCrsResultSet detail = (IMCrsResultSet) crsService.selectDetailCrs(iMCrs);
				
				HashMap<String, IMCmmnDtVO> cmmmDtMap= cmmnDtService.selectListCmmnDtResultMap("IM_CRS", iMCrs.getCrsId());
				
				IMMmbrQlfcVO lastMmbrQlfc = mmbrQlfcService.selectDetailMmbrQlfc(user.getUniqId(), detail.getCrsMstr().getCrsGrdCdv());
				//실무 과정인 경우 체크 
				String CRS_DVSN_CHEK="Y";
				
				if(detail.getCrsMstr().getCrsDvsnCdv().equals("CRS_DVSN_002")) {
					 //시험 통과 여부 
					if(!"02".equals(lastMmbrQlfc.getWtstSttsCdv()) || lastMmbrQlfc.getWtstPassYmd()==null || "".equals(lastMmbrQlfc.getWtstPassYmd())|| lastMmbrQlfc.getWtstVldEndYmd()==null || "".equals(lastMmbrQlfc.getWtstVldEndYmd())){
						CRS_DVSN_CHEK="NO_PASS2";
					}else {
						//유효기간 체크 
						if(IMDateUtil.diffDate(lastMmbrQlfc.getWtstVldEndYmd(), cmmmDtMap.get("eduTerm").getBgnDt(), "yyyyMMddHHmmss")<0) {
							CRS_DVSN_CHEK="NO_PASS_DATE2";
						}
					}
				}

				if(detail.getCrsMstr().getCrsDvsnCdv().equals("CRS_DVSN_003")) {
					if(!"02".equals(lastMmbrQlfc.getLcncSttsCdv()) || lastMmbrQlfc.getQlfcRsltCode()==null || "".equals(lastMmbrQlfc.getQlfcRsltCode())|| 
							lastMmbrQlfc.getLcncEndYmd()==null || "".equals(lastMmbrQlfc.getLcncEndYmd())){
						CRS_DVSN_CHEK="NO_PASS3";
					}else {
						//유효기간 체크 
						if(IMDateUtil.diffDate(lastMmbrQlfc.getLcncEndYmd(), cmmmDtMap.get("eduTerm").getBgnDt(), "yyyyMMddHHmmss")<0) {
							CRS_DVSN_CHEK="NO_PASS_DATE3";
						}
					}
						
				}
				if("Y".equals(CRS_DVSN_CHEK)) {
					resultCode = crsAplcntService.insertCrsAplcnt(iMCrsAplcnt);
				}else {
					resultCode = -8;
					mav.addObject("resultMsgCode", CRS_DVSN_CHEK);
				}
			}
		}

		mav.addObject("resultVO", iMCrsAplcnt);
		mav.addObject("result", resultCode);
		
		mav.setViewName("jsonView");
		return mav;
	}
	
	/**
	 * 수강신청 취소 
	 * @param req
	 * @param res
	 * @param iMCrsAplcnt
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/crsAplcnt/cancel.do" , method = RequestMethod.POST)
	public ModelAndView applycancel(HttpServletRequest req, HttpServletResponse res, IMCrsAplcntVO iMCrsAplcnt,
			BindingResult bindingResult, ModelMap model) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		loginCheckSetAudit(req, iMCrsAplcnt);
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		// 로그인한 사용자 정보
		iMCrsAplcnt.setMmbrEsntlId(user.getUniqId());
		
		
		IMCrsAplcntResultSet detailApply = (IMCrsAplcntResultSet) crsAplcntService.selectDetailCrsAplcnt(iMCrsAplcnt);
		

		
		int resultCode = 0;
		

		/**
		 * 본인 결과만 확인 
		 */
		if(!detailApply.getCrsAplcnt().getMmbrEsntlId().equals(user.getUniqId())) {
			resultCode = -1;
		}

		HashMap<String, String> sttsMap = cmmnSttsService.selectListCmmnSttsResultMap("IM_CRS_APLCNT", iMCrsAplcnt.getCrsAplcntId());
		sttsMap.get(IMCrsAplcntStts.신청상태.sttsKey);
		//학습자는 신청 상태가 대기인경우만 취소 가능 
		if(sttsMap.get(IMCrsAplcntStts.신청상태.sttsKey)==null ||
				!sttsMap.get(IMCrsAplcntStts.신청상태.sttsKey).equals(IMCrsAplcntStts.신청상태.defaultCode)) {
			resultCode = -2;
		}
		
		/**
		 * 신청 상태 취소로 변경 			
		 */
		if(resultCode==0) {
			resultCode = cmmnSttsService.updateCmmnStts("IM_CRS_APLCNT", iMCrsAplcnt.getCrsAplcntId(), IMCrsAplcntStts.신청상태.sttsKey, "03", iMCrsAplcnt);
		}
		
		
		mav.addObject("resultVO", iMCrsAplcnt);
		mav.addObject("result", resultCode);
		mav.setViewName("jsonView");
		return mav;
	}

}