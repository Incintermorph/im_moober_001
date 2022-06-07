/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.pstpnd.web;

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

import com.intermorph.cmmn.base.BaseController;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;
import com.intermorph.cmmn.exception.IMProcErrors;
import com.intermorph.cmmn.exception.IMProcException;
import com.intermorph.crs.crs.service.IMCrsCondition;
import com.intermorph.crs.crs.service.IMCrsService;
import com.intermorph.uss.hstry.service.IMMmbrHstryResultSet;
import com.intermorph.uss.hstry.service.IMMmbrHstryService;
import com.intermorph.uss.hstry.service.IMMmbrHstryVO;
import com.intermorph.uss.pstpnd.service.IMPstpndAplyCondition;
import com.intermorph.uss.pstpnd.service.IMPstpndAplyResultSet;
import com.intermorph.uss.pstpnd.service.IMPstpndAplyService;
import com.intermorph.uss.pstpnd.service.IMPstpndAplyVO;
import com.intermorph.uss.qlfc.service.IMMmbrQlfcService;
import com.intermorph.uss.qlfc.service.IMMmbrQlfcVO;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.pstpnd.web
 * @File : IMPstpndAplyController.java
 * @Title : 유예신청
 * @date : 2022.04.14 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Controller
public class IMPstpndAplyUserController extends BaseController {

	@Resource (name = "IMPstpndAplyService")
	private IMPstpndAplyService pstpndAplyService;

	@Resource (name = "propertiesService")
	protected EgovPropertyService propertyService;

	@Autowired
	private DefaultBeanValidator beanValidator;


    @Resource (name = "IMMmbrQlfcService")
    private IMMmbrQlfcService mmbrQlfcService;


    @Resource (name = "IMMmbrHstryService")
	private IMMmbrHstryService mmbrHstryService;

	@Resource(name = "IMCrsService")
	private IMCrsService crsService;
    
	/**
	 * 유예신청 목록
	 * 
	 * @param req
	 * @param res
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping (value = "/user/pstpndAply/selectList.do")
	public ModelAndView selectList(HttpServletRequest req, HttpServletResponse res, IMPstpndAplyCondition condition) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		condition.setOrderbyKey(1);
		condition.setCurrentPageNo(0);
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		condition.setScEsntlId(user.getUniqId());
		condition.setScNotSttsCdv("03");
		BasePage<BaseResultSet> pageInfo = pstpndAplyService.selectListPstpndAply(condition);
		
		
		mav.addObject("pageInfo", pageInfo);

		IMMmbrHstryVO iMMmbrHstry = new  IMMmbrHstryVO ();
		iMMmbrHstry.setEsntlId(user.getUniqId());
		//IMMmbrHstryResultSet detail = (IMMmbrHstryResultSet) mmbrHstryService.selectDetailMmbrHstry(iMMmbrHstry);

		//mav.addObject("selectEduHis", mmbrHstryService.selectEduHisList(detail.getMmbrHstry().getMemberSrl(),"4003"));//취득 이력만
		
		HashMap<String, IMPstpndAplyVO> applyMap = new HashMap<String, IMPstpndAplyVO>();
		
		if(pageInfo!=null && pageInfo.getList()!=null && pageInfo.getList().size()>0) {
			for(BaseResultSet rs : pageInfo.getList()) {
				IMPstpndAplyResultSet rstpndAply = (IMPstpndAplyResultSet)rs;
				applyMap.put(rstpndAply.getPstpndAply().getCrsGrdCdv(), rstpndAply.getPstpndAply());
			}
		}
		
		mav.addObject("applyMap", applyMap);//최종 신청 데이터 

		List<IMMmbrQlfcVO> listMmbrQlfc =mmbrQlfcService.selectListUserMmbrQlfcPass(user.getUniqId());
		
		mav.addObject("listMmbrQlfc", listMmbrQlfc);
		if(listMmbrQlfc!=null && listMmbrQlfc.size()>0) {
			mav.addObject("listMmbrQlfcCnt", listMmbrQlfc.size());
		}else {
			mav.addObject("listMmbrQlfcCnt", 0);
		}
		
		mav.addObject("condition", condition);
		
		
		
		IMCrsCondition crscondition = new IMCrsCondition();
		crscondition.setScCrsDvsnCdv("CRS_DVSN_003");
		crscondition.setScProcType("I");
		crscondition.setScCrsGrdCdv("CRS_GRD_1");
		
		mav.addObject("listCrCnt1", crsService.selectListCount(crscondition));

		crscondition.setScCrsGrdCdv("CRS_GRD_2");
		mav.addObject("listCrCnt2", crsService.selectListCount(crscondition));

		crscondition.setScCrsGrdCdv("CRS_GRD_3");
		mav.addObject("listCrCnt3", crsService.selectListCount(crscondition));

		mav.setViewName("layout/user/uss/pstpnd/selectListPstpndAply");

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
	@RequestMapping (value = "/user/pstpndAply/regist.do")
	public ModelAndView regist(HttpServletRequest req, HttpServletResponse res, IMPstpndAplyVO iMPstpndAply, IMPstpndAplyCondition condition) throws Exception {
		ModelAndView mav = new ModelAndView();

		mav.addObject("iMPstpndAply", iMPstpndAply);

		mav.addObject("condition", condition);
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		IMMmbrHstryVO iMMmbrHstry = new  IMMmbrHstryVO ();
		iMMmbrHstry.setEsntlId(user.getUniqId());
		IMMmbrHstryResultSet detailHstry = (IMMmbrHstryResultSet) mmbrHstryService.selectDetailMmbrHstry(iMMmbrHstry);

		
		IMMmbrQlfcVO lastMmbrQlfc = mmbrQlfcService.selectDetailMmbrQlfc(user.getUniqId(), iMPstpndAply.getCrsGrdCdv());

		if(!lastMmbrQlfc.getQlfcRsltCode().equals(iMPstpndAply.getQlfcRsltCode())) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}

		
		iMPstpndAply.setLcncEndYmd(lastMmbrQlfc.getLcncEndYmd());
		iMPstpndAply.setLcncIssuCode(lastMmbrQlfc.getLcncIssuCode());
		iMPstpndAply.setMmbrTelno(detailHstry.getMmbrHstry().getMblTelno());
		iMPstpndAply.setLcncAcqsYmd(lastMmbrQlfc.getLcncAcqsYmd());
		
		
		
		
		mav.addObject("detailHstry", detailHstry);

		mav.setViewName("layout/user/uss/pstpnd/registPstpndAply");

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
	@RequestMapping (value = "/user/pstpndAply/selectDetail.do")
	public ModelAndView selectDetail(HttpServletRequest req, HttpServletResponse res, IMPstpndAplyVO iMPstpndAply, IMPstpndAplyCondition condition) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		IMPstpndAplyResultSet detail = (IMPstpndAplyResultSet)pstpndAplyService.selectDetailPstpndAply(iMPstpndAply);
		
		mav.addObject("iMPstpndAply", detail.getPstpndAply());
		mav.addObject("detail", detail);
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		
		if(!detail.getPstpndAply().getEsntlId().equals(user.getUniqId())) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		

		IMPstpndAplyCondition usercondition = new IMPstpndAplyCondition();
		usercondition.setScEsntlId(detail.getPstpndAply().getEsntlId());
		usercondition.setScCrsGrdCdv(detail.getPstpndAply().getCrsGrdCdv());
		

		mav.addObject("userList", pstpndAplyService.selectListPstpndAply(usercondition).getList());
		
		
		mav.addObject("condition", condition);
		
		mav.setViewName("layout/user/uss/pstpnd/selectDetailPstpndAply");
		
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
	@RequestMapping (value = "/user/pstpndAply/modify.do")
	public ModelAndView modify(HttpServletRequest req, HttpServletResponse res, IMPstpndAplyVO iMPstpndAply, IMPstpndAplyCondition condition) throws Exception {
		ModelAndView mav = new ModelAndView();

		IMPstpndAplyResultSet detail = (IMPstpndAplyResultSet)pstpndAplyService.selectDetailPstpndAply(iMPstpndAply);

		mav.addObject("iMPstpndAply", detail.getPstpndAply());
		
		mav.addObject("detail", detail);

		mav.addObject("condition", condition);

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		
		if(!detail.getPstpndAply().getEsntlId().equals(user.getUniqId())) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		
		mav.setViewName("layout/user/uss/pstpnd/modifyPstpndAply");

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
	@RequestMapping (value = "/user/pstpndAply/insert.do")
	public ModelAndView insert(HttpServletRequest req, HttpServletResponse res, @ModelAttribute ("iMPstpndAply") IMPstpndAplyVO iMPstpndAply,
			BindingResult bindingResult, ModelMap model) throws Exception {

		ModelAndView mav = new ModelAndView();
		loginCheckSetAudit(req, iMPstpndAply);
		beanValidator.validate(iMPstpndAply, bindingResult); // validation 수행

		if (bindingResult.hasErrors()) { // 만일 validation 에러가 있으면...
			mav.addObject("result", -1);
		}
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		iMPstpndAply.setEsntlId(user.getUniqId());
		iMPstpndAply.setMberNm(user.getName());
		iMPstpndAply.setMmbrId(user.getId());
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
	@RequestMapping (value = "/user/pstpndAply/update.do")
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
	 *  신청 취소 
	 * @param req
	 * @param res
	 * @param iMPstpndAply
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/pstpndAply/delete.do")
	public ModelAndView delete(HttpServletRequest req, HttpServletResponse res, IMPstpndAplyVO iMPstpndAply)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMPstpndAply);
		IMPstpndAplyResultSet detail = (IMPstpndAplyResultSet) pstpndAplyService.selectDetailPstpndAply(iMPstpndAply);

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		
		if(!detail.getPstpndAply().getFrstRegerId().equals(user.getUniqId())) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		
		mav.addObject("result", pstpndAplyService.deletePstpndAply(iMPstpndAply));

		mav.setViewName("jsonView");
		return mav;
	}
   
}