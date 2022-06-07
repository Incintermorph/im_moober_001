/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.mngr.web;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.google.gson.Gson;
import com.intermorph.cmmn.IMGlobals;
import com.intermorph.cmmn.base.BaseController;
import com.intermorph.cmmn.base.BaseVO;
import com.intermorph.cmmn.exception.IMProcErrors;
import com.intermorph.cmmn.exception.IMProcException;
import com.intermorph.cmmn.service.IMCmmnDescService;
import com.intermorph.cmmn.service.IMInfoInqHstryService;
import com.intermorph.cmmn.service.IMInfoInqHstryVO;
import com.intermorph.cmmn.util.IMStringUtil;
import com.intermorph.crs.agncy.service.IMAgncyService;
import com.intermorph.uss.mngr.service.IMUssMngrCondition;
import com.intermorph.uss.mngr.service.IMUssMngrResultSet;
import com.intermorph.uss.mngr.service.IMUssMngrService;
import com.intermorph.uss.mngr.service.IMUssMngrVO;
import com.intermorph.uss.mngr.service.IMUssPermitDTO;

import egovframework.com.sec.rgm.service.AuthorGroup;
import egovframework.com.sec.rgm.service.EgovAuthorGroupService;
import egovframework.com.uss.umt.service.EgovUserManageService;
import egovframework.com.utl.sim.service.EgovFileScrty;
import egovframework.rte.fdl.access.bean.AuthorityResourceMetadata;
import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.mngr.web
 * @File    : IMUssrMngController.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 3. 29
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
@Controller
public class IMUssrMngController extends BaseController {
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;

	@Resource(name = "IMUssMngrService")
	private IMUssMngrService ussMngrService;
	

    @Resource (name = "IMAgncyService")
	private IMAgncyService agncyService;
    
    @Resource(name = "egovAuthorGroupService")
    private EgovAuthorGroupService egovAuthorGroupService;
	
	/** userManageService */
	@Resource(name = "userManageService")
	private EgovUserManageService userManageService;
	
	@Resource(name = "IMCmmnDescService")
	private IMCmmnDescService cmmnDescService;
	

	@Resource(name="authorityResource")
	private AuthorityResourceMetadata sessionResourceMetadata;
	
	@Autowired
	private DefaultBeanValidator beanValidator;
	

	@Resource(name="IMInfoInqHstryService")
	IMInfoInqHstryService infoInqHstryService;

	
	/**
	 * 관리자 회원 목록 
	 * @param req
	 * @param res
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/ussMngr/{authorCode}/selectList.do")
	public ModelAndView selectList(HttpServletRequest req, HttpServletResponse res,
			@PathVariable ("authorCode") String authorCode,
			IMUssMngrCondition condition)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		setEmptyValue(condition, "currentPageNo=1", "recordCountPerPage=" + propertyService.getInt("pageUnit"),
				"pageSize=" + propertyService.getInt("pageSize"), "orderbyKey=0");
		
		condition.setScAuthorCode(authorCode);

		mav.addObject("agncyList", agncyService.selectListAgncy());
		
		mav.addObject("pageInfo", ussMngrService.selectListUssMngr(condition));
		mav.addObject("condition", condition);
		
		//개인정보 이력 조회 
		Gson gson = new Gson();
		infoInqHstryService.insertInfoInqHstry("comtnemplyrinfo","SELECTNOID",IMGlobals.IM_INFOINQ_S,gson.toJson(condition),req);
		mav.setViewName("layout/mng/uss/ussMngr/selectListUssMngr");
		
		

		return mav;
	}
	
	/**
	 * 업무사용자정보  등록 화면 
	 * @param req
	 * @param res
	 * @param iMUssMngr
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/ussMngr/regist.do")
	public ModelAndView regist(HttpServletRequest req, HttpServletResponse res, IMUssMngrVO iMUssMngr,
			IMUssMngrCondition condition) throws Exception {
		ModelAndView mav = new ModelAndView();

		mav.addObject("iMUssMngr", iMUssMngr);

		mav.addObject("condition", condition);
		mav.addObject("agncyList", agncyService.selectListAgncy());

		mav.setViewName("layout/mng/uss/ussMngr/registUssMngr");

		return mav;
	}
	
	/**
	 * 업무사용자정보 수정 화면 
	 * @param req
	 * @param res
	 * @param iMComtnemplyrinfo
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping (value = { "/mng/ussMngr/modify.do", "/mng/ussMngr/modifyLayer.do" })
	public ModelAndView modify(HttpServletRequest req, HttpServletResponse res, IMUssMngrVO iMUssMngr,
			IMUssMngrCondition condition,IMInfoInqHstryVO inqHstryVO) throws Exception {
		ModelAndView mav = new ModelAndView();

		String refer = req.getHeader("referer")==null?"":req.getHeader("referer");

		
		boolean chkeInqRsn=false;
		//수정페이지에서는 제외 
		if(refer.indexOf("/selectList.do")!=-1){
			chkeInqRsn=true;
		}
		
		if(IMStringUtil.isEmpty(inqHstryVO.getInqRsn()) && chkeInqRsn) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		
		if(chkeInqRsn) {
			infoInqHstryService.insertInfoInqHstry("comtnemplyrinfo",iMUssMngr.getEsntlId(),IMGlobals.IM_INFOINQ_R,inqHstryVO.getInqRsn(),req);
		}
		
		IMUssMngrResultSet detail = (IMUssMngrResultSet) ussMngrService.selectDetailUssMngr(iMUssMngr);

		mav.addObject("iMUssMngr", detail.getUssMngr());

		mav.addObject("agncyList", agncyService.selectListAgncy());
		
		mav.addObject("condition", condition);
		if(req.getServletPath().indexOf("modifyLayer")!=-1) {
			mav.setViewName("layer/mng/uss/ussMngr/modifyUssMngrLayer");
		}else {
			mav.setViewName("layout/mng/uss/ussMngr/modifyUssMngr");
		}
		return mav;
	}
	
	/**
	 * 업무사용자정보 수정 화면 
	 * @param req
	 * @param res
	 * @param iMComtnemplyrinfo
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/ussMngr/modifyPermit.do")
	public ModelAndView modifyPermit(HttpServletRequest req, HttpServletResponse res, IMUssMngrVO iMUssMngr,
			IMUssMngrCondition condition) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		IMUssMngrResultSet detail = (IMUssMngrResultSet) ussMngrService.selectDetailUssMngr(iMUssMngr);
		
		mav.addObject("iMUssMngr", detail.getUssMngr());
		
		mav.addObject("condition", condition);
		
		HashMap<String, String> mapCmmnDesc = cmmnDescService.selectListCmmnDescResultMap(IMGlobals.IM_USERPERMITCMMMDESCTBLID, detail.getUssMngr().getEsntlId());
		
		if(mapCmmnDesc.get(IMGlobals.IM_USERPERMIT_KEY)==null) {
			mav.addObject("ussPermit", new IMUssPermitDTO());
		}else {
			Gson gson = new Gson();
			//System.out.println(gson.fromJson(mapCmmnDesc.get(IMGlobals.IM_USERPERMIT_KEY), IMUssPermitDTO.class));
			IMUssPermitDTO dto = gson.fromJson(mapCmmnDesc.get(IMGlobals.IM_USERPERMIT_KEY), IMUssPermitDTO.class);
			mav.addObject("ussPermit", dto);
		}
		
		mav.setViewName("layout/mng/uss/ussMngr/modifyUssMngrPermit");
		return mav;
	}
	

	/**
	 * 사용자 허용 정보 변경 
	 * @param req
	 * @param res
	 * @param iMUssMngr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/ussMngr/permit/update.do")
	public ModelAndView updatePermit(HttpServletRequest req, HttpServletResponse res,
			IMUssMngrVO iMUssMngr ) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		BaseVO  auditVO = new BaseVO();
		loginCheckSetAudit(req, auditVO);
		
		IMUssPermitDTO dto = new IMUssPermitDTO();
		iMUssMngr.copyPermitData(dto);
		Gson gson = new Gson();
		
		cmmnDescService.insertCmmnDesc(IMGlobals.IM_USERPERMITCMMMDESCTBLID, iMUssMngr.getEsntlId(), IMGlobals.IM_USERPERMIT_KEY, gson.toJson(dto), auditVO);
		
		mav.addObject("result", 1);
		mav.setViewName("jsonView");
		return mav;
	}
	
	
	
	/**
	 * 비번 변경 화면 
	 * @param req
	 * @param res
	 * @param iMUssMngr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/ussMngr/modify/passwordLayer.do")
	public ModelAndView modifyPasswordLayer(HttpServletRequest req, HttpServletResponse res, IMUssMngrVO iMUssMngr
			) throws Exception {
		ModelAndView mav = new ModelAndView();

		mav.addObject("iMUssMngrPasswd", iMUssMngr);

		mav.setViewName("layer/mng/uss/ussMngr/modifyPasswordUssMngrLayer");

		return mav;
	}
	
	/**
	 * 조회시 사유 등록 기능  
	 * @param req
	 * @param res
	 * @param iMUssMngr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/ussMngr/select/memoLayer.do")
	public ModelAndView memoLayer(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("layer/mng/uss/ussMngr/selectUssMngrMemoLayer");
		
		return mav;
	}
	
	

	/**
	 * 업무 사용자 패스워드 변경 
	 * @param req
	 * @param res
	 * @param iMUssMngr
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/ussMngr/password/update.do")
	public ModelAndView updatepassword(HttpServletRequest req, HttpServletResponse res,
			@ModelAttribute("iMUssMngrPasswd") IMUssMngrVO iMUssMngrPasswd ,
			BindingResult bindingResult, ModelMap model)
					throws Exception {
		
		ModelAndView mav = new ModelAndView();
		
		beanValidator.validate(iMUssMngrPasswd, bindingResult); // validation 수행
		
		if (bindingResult.hasErrors()) { // 만일 validation 에러가 있으면...
			mav.addObject("result", -1);
		}
		iMUssMngrPasswd.chkInitData();
		
		iMUssMngrPasswd.setPassword(EgovFileScrty.encryptPassword(iMUssMngrPasswd.getPassword(), iMUssMngrPasswd.getEmplyrId()));
		userManageService.updatePassword(iMUssMngrPasswd);
		
		
		
		mav.addObject("result", 1);
		mav.setViewName("jsonView");
		return mav;
	}
	
	
	/**
	 * 업무사용자정보 등록 저장 
	 * @param req
	 * @param res
	 * @param iMcomtnemplyrinfo
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/ussMngr/insert.do")
	public ModelAndView insert(HttpServletRequest req, HttpServletResponse res,
			@ModelAttribute("iMUssMngr") IMUssMngrVO iMUssMngr ,
			BindingResult bindingResult, ModelMap model)
			throws Exception {

		ModelAndView mav = new ModelAndView();
		
		beanValidator.validate(iMUssMngr, bindingResult); // validation 수행

		if (bindingResult.hasErrors()) { // 만일 validation 에러가 있으면...
			mav.addObject("result", -1);
		}
		iMUssMngr.chkInitData();
		userManageService.insertUser(iMUssMngr);
		AuthorGroup authorGroup = new AuthorGroup();
		authorGroup.setUniqId(iMUssMngr.getUniqId());
		authorGroup.setMberTyCode("USR03");
		authorGroup.setAuthorCode(iMUssMngr.getAuthorCode());
		egovAuthorGroupService.insertAuthorGroup(authorGroup);
		sessionResourceMetadata.reload();
		
		infoInqHstryService.insertInfoInqHstry("comtnemplyrinfo",iMUssMngr.getUniqId(),IMGlobals.IM_INFOINQ_C,IMGlobals.IM_INFOINQ_C +" " + iMUssMngr.getEmplyrId(),req);
		
		mav.addObject("result", 1);
		mav.setViewName("jsonView");
		return mav;
	}
	/**
	 * 업무 사용자 수정 
	 * @param req
	 * @param res
	 * @param iMUssMngr
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/ussMngr/update.do")
	public ModelAndView update(HttpServletRequest req, HttpServletResponse res,
			@ModelAttribute("iMUssMngr") IMUssMngrVO iMUssMngr ,
			BindingResult bindingResult, ModelMap model)
					throws Exception {
		
		ModelAndView mav = new ModelAndView();
		
		beanValidator.validate(iMUssMngr, bindingResult); // validation 수행
		
		if (bindingResult.hasErrors()) { // 만일 validation 에러가 있으면...
			mav.addObject("result", -1);
		}
		iMUssMngr.chkInitData();
		
		//업무사용자 수정시 히스토리 정보를 등록한다.
		userManageService.insertUserHistory(iMUssMngr);
		
		userManageService.updateUser(iMUssMngr);

		//이력 저장
		infoInqHstryService.insertInfoInqHstry("comtnemplyrinfo",iMUssMngr.getEsntlId(),IMGlobals.IM_INFOINQ_U,IMGlobals.IM_INFOINQ_U+" " + iMUssMngr.getEmplyrId(),req);
		
		sessionResourceMetadata.reload(); 
		
		mav.addObject("result", 1);
		mav.setViewName("jsonView");
		return mav;
	}
	
	
	
	/**
	 * 
	 */
	

	/**
	 * 아이디 중복 체크
	 * @param req
	 * @param res
	 * @param iMUssMngr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping ("/mng/ussMngr/overchek.do")
	public ModelAndView overchek(HttpServletRequest req, HttpServletResponse res, IMUssMngrVO iMUssMngr) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		
		
		if(iMUssMngr.getEmplyrId()==null || "".equals(iMUssMngr.getEmplyrId())) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}

		int usedCnt = userManageService.checkIdDplct(iMUssMngr.getEmplyrId());
		mav.addObject("result", usedCnt);
		
		
		mav.setViewName("jsonView");
		return mav;
	}
	
	
	/**
	 * 관리자 삭제 상태로 변경 
	 * @param req
	 * @param res
	 * @param iMUssMngr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/ussMngr/delete.do")
	public ModelAndView delete(HttpServletRequest req, HttpServletResponse res, IMUssMngrVO iMUssMngr)
			throws Exception {
		ModelAndView mav = new ModelAndView();


		mav.addObject("result", ussMngrService.deleteUssMngr(iMUssMngr));
		sessionResourceMetadata.reload();
		
		//이력 저장 
		infoInqHstryService.insertInfoInqHstry("comtnemplyrinfo",iMUssMngr.getEsntlId(),IMGlobals.IM_INFOINQ_D,IMGlobals.IM_INFOINQ_D+" " + iMUssMngr.getEmplyrId(),req);
		mav.setViewName("jsonView");
		return mav;
	}
	
	
	/**
	 *  양성기관 다중 삭제 처리
	 * 
	 * @param req
	 * @param res
	 * @param IMAgncyVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping ("/mng/ussMngr/deletelist.do")
	public ModelAndView deletelist(HttpServletRequest req, HttpServletResponse res, IMUssMngrVO iMUssMngr) throws Exception {
		ModelAndView mav = new ModelAndView();

		

		List<IMUssMngrVO> ussMngrs = new ArrayList<IMUssMngrVO>();


		for (Long idx: iMUssMngr.getCheckIndexs()) {
			IMUssMngrVO o = new IMUssMngrVO();			
			o.setEsntlId(iMUssMngr.getEsntlIds()[idx.intValue()]);
			ussMngrs.add(o);
		}

		if (ussMngrs.size() > 0) {
			mav.addObject("result", ussMngrService.deletelistUssMngr(ussMngrs));
			sessionResourceMetadata.reload(); 
			//이력 등록 
			for(IMUssMngrVO  vo : ussMngrs) {
				infoInqHstryService.insertInfoInqHstry("comtnemplyrinfo",vo.getEsntlId(),IMGlobals.IM_INFOINQ_D,IMGlobals.IM_INFOINQ_D+" " + vo.getEmplyrId(),req);
			}
		} else {
			mav.addObject("result", 0);
		}

		mav.setViewName("jsonView");
		return mav;
	}
	

	/**
	 * 관리자 삭제 상태로 변경 
	 * @param req
	 * @param res
	 * @param iMUssMngr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/ussMngr/updateLockIncorrect.do")
	public ModelAndView updateLockIncorrect(HttpServletRequest req, HttpServletResponse res, IMUssMngrVO iMUssMngr)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		iMUssMngr.chkInitData();
		userManageService.updateLockIncorrect(iMUssMngr);

		mav.addObject("result", 1);
		mav.setViewName("jsonView");
		return mav;
	}
}
