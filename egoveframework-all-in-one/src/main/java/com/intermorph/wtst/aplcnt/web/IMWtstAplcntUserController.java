/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.wtst.aplcnt.web;

import java.util.HashMap;

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
import com.intermorph.cmmn.exception.IMCrsAplcntStts;
import com.intermorph.cmmn.exception.IMWtstAgreeStts;
import com.intermorph.cmmn.exception.IMWtstAplcntStts;
import com.intermorph.cmmn.service.IMCmmnSttsService;
import com.intermorph.cmmn.service.IMCmmnSttsVO;
import com.intermorph.wtst.aplcnt.service.IMWtstAplcntCondition;
import com.intermorph.wtst.aplcnt.service.IMWtstAplcntResultSet;
import com.intermorph.wtst.aplcnt.service.IMWtstAplcntService;
import com.intermorph.wtst.aplcnt.service.IMWtstAplcntVO;
import com.intermorph.wtst.place.service.IMWtstPlaceResultSet;
import com.intermorph.wtst.place.service.IMWtstPlaceService;
import com.intermorph.wtst.place.service.IMWtstPlaceVO;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.wtst.aplcnt.web
 * @File : IMWtstAplcntController.java
 * @Title : 필기시험 신청자
 * @date : 2022.04.04 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Controller
public class IMWtstAplcntUserController extends BaseController {

	@Resource (name = "IMWtstAplcntService")
	private IMWtstAplcntService wtstAplcntService;

	@Resource (name = "propertiesService")
	protected EgovPropertyService propertyService;

	@Resource (name = "IMCmmnSttsService")
	private IMCmmnSttsService cmmnSttsService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	@Resource (name = "IMWtstPlaceService")
	private IMWtstPlaceService wtstPlaceService;

	/**
	 * 필기시험 신청자 목록
	 * 
	 * @param req
	 * @param res
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping (value = "/user/wtstAplcnt/selectList.do")
	public ModelAndView selectList(HttpServletRequest req, HttpServletResponse res, IMWtstAplcntCondition condition) throws Exception {
		ModelAndView mav = new ModelAndView();

		setEmptyValue(condition, "currentPageNo=1", "recordCountPerPage=" + propertyService.getInt("pageUnit"),
				"pageSize=" + propertyService.getInt("pageSize"), "orderbyKey=0");

		mav.addObject("pageInfo", wtstAplcntService.selectListWtstAplcnt(condition));

		mav.addObject("condition", condition);

		mav.setViewName("layout/user/wtst/aplcnt/selectListWtstAplcnt");

		return mav;
	}

	/**
	 * 필기시험 신청자 등록 저장
	 * 
	 * @param req
	 * @param res
	 * @param iMwtstAplcnt
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping (value = "/user/wtstAplcnt/insert.do")
	public ModelAndView insert(HttpServletRequest req, HttpServletResponse res, @ModelAttribute ("iMWtstAplcnt") IMWtstAplcntVO iMWtstAplcnt,
			BindingResult bindingResult, ModelMap model,IMCmmnSttsVO vo) throws Exception {

		ModelAndView mav = new ModelAndView();
		loginCheckSetAudit(req, iMWtstAplcnt,vo);

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		iMWtstAplcnt.setMmbrEsntlId(user.getUniqId());
		iMWtstAplcnt.setMberId(user.getId());
		iMWtstAplcnt.setMberNm(user.getName());

		beanValidator.validate(iMWtstAplcnt, bindingResult); // validation 수행

		if (bindingResult.hasErrors()) { // 만일 validation 에러가 있으면...
			mav.addObject("result", -1);
		}
		
		//약관 체크 
		int okAgreeCnt=0;
		int defineAgree=0;
		int pass=0;
		String convPvsnYn = iMWtstAplcnt.getConvPvsnYn();
		for(IMWtstAgreeStts v : IMWtstAgreeStts.values()) {
			pass=0;
			if("Y".equals(convPvsnYn)){
				if("Y".equals(v.convPvsnYn) || "A".equals(v.convPvsnYn)){
					pass=1;	
				}
			}else {
				if("A".equals(v.convPvsnYn)){
					pass=1;	
				}
			}	
			if(pass==1) {
				defineAgree++;
				for(String agreeKey : vo.getCmmnCdvRefNms()) {
					if(v.sttsKey.equals(agreeKey)){
						okAgreeCnt++;
					};
					
				}
			}
		 }

			
		//약관 동의 체크 
		if(defineAgree!=okAgreeCnt) {				
				return mav.addObject("result", -99);
		}
		
		
		mav.addObject("result", wtstAplcntService.insertWtstAplcnt(iMWtstAplcnt));
		mav.addObject("resultVO", iMWtstAplcnt);
		
		//약관 동의 정보 추가 
		cmmnSttsService.insertlistCmmnStts("IM_WTST_APLCNT", iMWtstAplcnt.getWtstAplcntId(), req, vo);

		mav.setViewName("jsonView");
		return mav;
	}

	/**
	 * 필기시험 신청자 수정 저장
	 * 
	 * @param req
	 * @param res
	 * @param iMwtstAplcnt
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping (value = "/user/wtstAplcnt/update.do")
	public ModelAndView update(HttpServletRequest req, HttpServletResponse res, @ModelAttribute ("iMWtstAplcnt") IMWtstAplcntVO iMWtstAplcnt,
			BindingResult bindingResult, ModelMap model) throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMWtstAplcnt);

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		IMWtstAplcntResultSet detailApply = (IMWtstAplcntResultSet)wtstAplcntService.selectDetailWtstAplcnt(iMWtstAplcnt);

		/**
		 * 본인 결과만 수정
		 */
		if (!detailApply.getWtstAplcnt().getMmbrEsntlId().equals(user.getUniqId())) {
			mav.addObject("result", -1);
		} else {

			IMWtstPlaceVO iMWtstPlace = new IMWtstPlaceVO();

			iMWtstPlace.setWtstPlaceId(detailApply.getWtstAplcnt().getWtstPlaceId());

			IMWtstPlaceResultSet detail = (IMWtstPlaceResultSet)wtstPlaceService.selectDetailWtstPlace(iMWtstPlace);

			// 접수 기간에만 수정 가능함
			if ("I".equals(detail.getRcptProcType()) || "Y".equals(iMWtstAplcnt.getModifyYn())) {
				mav.addObject("result", wtstAplcntService.updateWtstAplcnt(iMWtstAplcnt));

			} else {
				mav.addObject("result", -3);
			}

		}
		mav.setViewName("jsonView");
		return mav;
	}

	/**
	 * 필기시험 신청자 삭제
	 * 
	 * @param req
	 * @param res
	 * @param iMwtstAplcnt
	 * @return
	 * @throws Exception
	 */
	@RequestMapping (value = "/user/wtstAplcnt/cancel.do")
	public ModelAndView cancel(HttpServletRequest req, HttpServletResponse res, IMWtstAplcntVO iMWtstAplcnt) throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMWtstAplcnt);
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		IMWtstAplcntResultSet detailApply = (IMWtstAplcntResultSet)wtstAplcntService.selectDetailWtstAplcnt(iMWtstAplcnt);

		int resultCode = 0;

		/**
		 * 본인 결과만 확인
		 */
		if (!detailApply.getWtstAplcnt().getMmbrEsntlId().equals(user.getUniqId())) {
			resultCode = -1;
		}

		IMWtstPlaceVO iMWtstPlace = new IMWtstPlaceVO();

		iMWtstPlace.setWtstPlaceId(detailApply.getWtstAplcnt().getWtstPlaceId());

		IMWtstPlaceResultSet detail = (IMWtstPlaceResultSet)wtstPlaceService.selectDetailWtstPlace(iMWtstPlace);

		HashMap<String, String> sttsMap = cmmnSttsService.selectListCmmnSttsResultMap("IM_WTST_APLCNT", iMWtstAplcnt.getWtstAplcntId());
		sttsMap.get(IMCrsAplcntStts.신청상태.sttsKey);
		// 학습자는 신청 상태가 대기인경우만 취소 가능
		/**
		 * if(sttsMap.get(IMWtstAplcntStts.접수상태.sttsKey)==null) { resultCode = -2; } if(!sttsMap.get(IMWtstAplcntStts.접수상태.sttsKey).equals("01") &&
		 * !sttsMap.get(IMWtstAplcntStts.접수상태.sttsKey).equals("0101")) { resultCode = -2; }
		 **/
		// 접수 기간에만 취소 가능함
		if (!"I".equals(detail.getRcptProcType())) {
			resultCode = -3;
		}

		/**
		 * 신청 상태 취소로 변경
		 */
		if (resultCode == 0) {
			resultCode = cmmnSttsService.updateCmmnStts("IM_WTST_APLCNT", iMWtstAplcnt.getWtstAplcntId(), IMWtstAplcntStts.접수상태.sttsKey, "03", iMWtstAplcnt);
		}

		mav.addObject("resultVO", iMWtstAplcnt);
		mav.addObject("result", resultCode);

		mav.setViewName("jsonView");
		return mav;
	}

}