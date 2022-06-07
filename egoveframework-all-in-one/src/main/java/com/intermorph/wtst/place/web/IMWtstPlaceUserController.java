/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.wtst.place.web;


import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.intermorph.cmmn.base.BaseController;
import com.intermorph.cmmn.exception.IMProcErrors;
import com.intermorph.cmmn.exception.IMProcException;
import com.intermorph.cmmn.exception.IMWtstAplcntStts;
import com.intermorph.cmmn.service.IMCmmnDescService;
import com.intermorph.cmmn.service.IMCmmnDtService;
import com.intermorph.cmmn.service.IMCmmnSttsService;
import com.intermorph.cmmn.service.IMCommonService;
import com.intermorph.crs.aplcnt.service.IMCrsAplcntCondition;
import com.intermorph.crs.aplcnt.service.IMCrsAplcntService;
import com.intermorph.uss.hstry.service.IMMmbrHstryResultSet;
import com.intermorph.uss.hstry.service.IMMmbrHstryService;
import com.intermorph.uss.hstry.service.IMMmbrHstryVO;
import com.intermorph.wtst.aplcnt.service.IMWtstAplcntResultSet;
import com.intermorph.wtst.aplcnt.service.IMWtstAplcntService;
import com.intermorph.wtst.aplcnt.service.IMWtstAplcntVO;
import com.intermorph.wtst.place.service.IMWtstPlaceCondition;
import com.intermorph.wtst.place.service.IMWtstPlaceResultSet;
import com.intermorph.wtst.place.service.IMWtstPlaceService;
import com.intermorph.wtst.place.service.IMWtstPlaceVO;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.wtst.place.web
 * @File : IMWtstPlaceController.java
 * @Title : 필기시험장소
 * @date : 2022.04.04 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Controller
public class IMWtstPlaceUserController extends BaseController {



    @Resource (name = "IMWtstPlaceService")
	private IMWtstPlaceService wtstPlaceService;
	
	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;


	@Resource(name = "IMCrsAplcntService")
	private IMCrsAplcntService crsAplcntService;


	@Resource(name = "IMCmmnDescService")
	private IMCmmnDescService cmmnDescService;

	@Resource(name = "IMCmmnDtService")
	private IMCmmnDtService cmmnDtService;

    @Resource (name = "IMCommonService")
    private IMCommonService commonService;
    

    @Resource (name = "IMMmbrHstryService")
	private IMMmbrHstryService mmbrHstryService;
	

    @Resource (name = "IMWtstAplcntService")
	private IMWtstAplcntService wtstAplcntService;
    

	@Resource(name = "IMCmmnSttsService")
	private IMCmmnSttsService  cmmnSttsService;
	
	
	/**
	 * 필기시험장소 목록
	 * 
	 * @param req
	 * @param res
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmmn/wtstPlace/{crsGrd}/selectList.do")
	public ModelAndView selectList(HttpServletRequest req, HttpServletResponse res, IMWtstPlaceCondition condition,@PathVariable("crsGrd") String crsGrd)
			throws Exception {
		ModelAndView mav = new ModelAndView();


		setEmptyValue(condition, "currentPageNo=1", "recordCountPerPage=" + propertyService.getInt("pageUnit"),
				"pageSize=" + propertyService.getInt("pageSize"), "orderbyKey=0" , "scProcType=I");

		String grdCode= "CRS_GRD_"+crsGrd.trim();
		String grdNm =commonService.selectCmmCodeOneDetailNm("IM0001", grdCode);
		
		if("".equals(grdNm)) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		condition.setOrderbyKey(3);
		condition.setScSttsCdv("STTS_02");
		condition.setScCrsGrdCdv(grdCode);
		
		mav.addObject("pageInfo", wtstPlaceService.selectListWtstPlace(condition));

		
		mav.addObject("condition", condition);
		mav.addObject("crsGrd", crsGrd);

		mav.setViewName("layout/user/wtst/place/selectListWtstPlace");
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
	@RequestMapping(value = "/cmmn/wtstPlace/{crsGrd}/selectDetail.do")
	public ModelAndView modify(HttpServletRequest req, HttpServletResponse res, IMWtstPlaceVO iMWtstPlace,
			IMWtstPlaceCondition condition,@PathVariable("crsGrd") String crsGrd) throws Exception {
		ModelAndView mav = new ModelAndView();

		IMWtstPlaceResultSet detail = (IMWtstPlaceResultSet) wtstPlaceService.selectDetailWtstPlace(iMWtstPlace);

		mav.addObject("iMWtstPlace", detail.getWtstPlace());
		
		mav.addObject("detail", detail);

		mav.addObject("condition", condition);
		mav.addObject("crsGrd", crsGrd);
		String grdCode= "CRS_GRD_"+crsGrd.trim();

		mav.addObject("cmmmDescMap", cmmnDescService.selectListCmmnDescResultMap("IM_WTST", detail.getWtstPlace().getWtstId()));
		mav.addObject("cmmmDtMap", cmmnDtService.selectListCmmnDtResultMap("IM_WTST", detail.getWtstPlace().getWtstId()));
		IMWtstAplcntVO aplcntVO = new IMWtstAplcntVO();
		aplcntVO.setWtstId(detail.getWtstPlace().getWtstId());
		aplcntVO.setWtstPlaceId(detail.getWtstPlace().getWtstPlaceId());
		
		if(EgovUserDetailsHelper.isAuthenticated()) {
			//이력 기본 데이터
			IMMmbrHstryVO iMMmbrHstry = new  IMMmbrHstryVO ();
			LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
			iMMmbrHstry.setEsntlId(user.getUniqId());
			IMMmbrHstryResultSet detailMmbrHstry = (IMMmbrHstryResultSet) mmbrHstryService.selectDetailMmbrHstry(iMMmbrHstry);
			mav.addObject("detailMmbrHstry", detailMmbrHstry);
			
			aplcntVO.setMmbrEsntlId(user.getUniqId());
			//중복 횟수 
			mav.addObject("overCnt", wtstAplcntService.selectOverAplyCount(aplcntVO));
			
			IMCrsAplcntCondition  aplcntCondition  = new IMCrsAplcntCondition();
			aplcntCondition.setScMmbrEsntlId(user.getUniqId());
			aplcntCondition.setScCrsGrdCdv(grdCode);
			aplcntCondition.setScCrsDvsnCdv("CRS_DVSN_001");
			//기본 과정 수료 카운드 
			mav.addObject("fishCount001", crsAplcntService.selectAplyFishCount(aplcntCondition));
		}
		
		
		
		mav.setViewName("layout/user/wtst/place/selectDetailWtstPlace");
		
		
		return mav;
	}
	
	
	/**
	 * 신청 결과 페이지 
	 * @param req
	 * @param res
	 * @param iMWtstPlace
	 * @param condition
	 * @param crsGrd
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/wtstPlace/{crsGrd}/apply/result.do")
	public ModelAndView resultApply(HttpServletRequest req, HttpServletResponse res, IMWtstPlaceVO iMWtstPlace,
			IMWtstPlaceCondition condition,@PathVariable("crsGrd") String crsGrd,IMWtstAplcntVO iMWtstAplcnt) throws Exception {
		ModelAndView mav = new ModelAndView();
	
		if(iMWtstPlace.getWtstPlaceId()==null || "".equals(iMWtstPlace.getWtstPlaceId())) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		if(iMWtstAplcnt.getWtstAplcntId()==null || "".equals(iMWtstAplcnt.getWtstAplcntId())) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		IMWtstPlaceResultSet detail = (IMWtstPlaceResultSet) wtstPlaceService.selectDetailWtstPlace(iMWtstPlace);
		
		mav.addObject("iMWtstPlace", detail.getWtstPlace());
		
		mav.addObject("detail", detail);
		IMWtstAplcntResultSet  detailApply = (IMWtstAplcntResultSet)wtstAplcntService.selectDetailWtstAplcnt(iMWtstAplcnt);
		

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(!detailApply.getWtstAplcnt().getMmbrEsntlId().equals(user.getUniqId())) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		
		mav.addObject("detailApply", detailApply);
		

		mav.addObject("cmmmDescMap", cmmnDescService.selectListCmmnDescResultMap("IM_WTST", detail.getWtstPlace().getWtstId()));
		mav.addObject("cmmmDtMap", cmmnDtService.selectListCmmnDtResultMap("IM_WTST", detail.getWtstPlace().getWtstId()));
		
		HashMap<String, String> sttsMap = cmmnSttsService.selectListCmmnSttsResultMap("IM_WTST_APLCNT", iMWtstAplcnt.getWtstAplcntId());

		
		
		mav.addObject("aplyStatusCode", sttsMap.get(IMWtstAplcntStts.접수상태.sttsKey));
		mav.addObject("sttsMap", sttsMap);
		mav.addObject("condition", condition);
		mav.addObject("crsGrd", crsGrd);
		
		
		//resultApplyCrs
		mav.setViewName("layout/user/wtst/place/resultApplyWtstPlace");
		
		
		return mav;
	}

	
}