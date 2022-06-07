/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.crs.crs.web;

import java.util.ArrayList;
import java.util.Date;
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
import com.intermorph.cmmn.service.IMCmmnDescVO;
import com.intermorph.cmmn.service.IMCmmnDtService;
import com.intermorph.cmmn.service.IMCmmnDtVO;
import com.intermorph.cmmn.service.IMCmmnSttsService;
import com.intermorph.cmmn.service.IMCommonService;
import com.intermorph.cmmn.service.IMInfoInqHstryService;
import com.intermorph.cmmn.service.IMInfoInqHstryVO;
import com.intermorph.cmmn.util.ExcelDownloadView;
import com.intermorph.cmmn.util.IMDateUtil;
import com.intermorph.cmmn.util.IMStringUtil;
import com.intermorph.crs.agncy.service.IMAgncyService;
import com.intermorph.crs.agncy.service.IMAgncyVO;
import com.intermorph.crs.aplcnt.service.IMCrsAplcntCondition;
import com.intermorph.crs.aplcnt.service.IMCrsAplcntService;
import com.intermorph.crs.crs.service.IMCrsCondition;
import com.intermorph.crs.crs.service.IMCrsResultSet;
import com.intermorph.crs.crs.service.IMCrsService;
import com.intermorph.crs.crs.service.IMCrsVO;
import com.intermorph.crs.mstr.service.IMCrsMstrService;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.crs.crs.web
 * @File : IMCrsController.java
 * @Title : 과정(운영과정)
 * @date : 2022.02.28 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Controller
public class IMCrsMngController extends BaseController {

	@Resource(name = "IMCrsService")
	private IMCrsService crsService;

	@Resource(name = "IMAgncyService")
	private IMAgncyService agncyService;

	@Resource(name = "IMCrsMstrService")
	private IMCrsMstrService crsMstrService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	@Resource(name = "IMCmmnDescService")
	private IMCmmnDescService cmmnDescService;

	@Resource(name = "IMCmmnDtService")
	private IMCmmnDtService cmmnDtService;
	
	@Resource(name = "IMCommonService")
	private IMCommonService commonService;

	private String cmmmDescTblId = "IM_CRS";

	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileService;
	


    @Resource (name = "IMCrsAplcntService")
	private IMCrsAplcntService crsAplcntService;

	@Resource(name="IMInfoInqHstryService")
	IMInfoInqHstryService infoInqHstryService;

	

	@Resource(name = "IMCmmnSttsService")
	private IMCmmnSttsService  cmmnSttsService;

	/**
	 * 과정(운영과정) 목록
	 * 
	 * @param req
	 * @param res
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/crs/selectList.do")
	public ModelAndView selectList(HttpServletRequest req, HttpServletResponse res, IMCrsCondition condition)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		setEmptyValue(condition, "currentPageNo=1", "recordCountPerPage=" + propertyService.getInt("pageUnit"),
				"pageSize=" + propertyService.getInt("pageSize"), "orderbyKey=0");

		String loginAgncyId = IMStringUtil.getAttribute(req, IMGlobals.IM_LOGIN_USER_AGNCY_KEY);
		if (!"".equals(loginAgncyId)) {
			condition.setScAgncyId(loginAgncyId);
		}
		condition.setScStatYn("Y");
		mav.addObject("pageInfo", crsService.selectListCrs(condition));

		mav.addObject("agncyList", agncyService.selectListAgncy());

		mav.addObject("condition", condition);

		mav.setViewName("layout/mng/crs/crs/selectListCrs");

		return mav;
	}
	/**
	 * 과정(운영과정) 엑셀 
	 * 
	 * @param req
	 * @param res
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/crs/selectListExcel.do")
	public ModelAndView selectListExcel(HttpServletRequest req, HttpServletResponse res, IMCrsCondition condition)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("list", crsService.selectListCrs(condition).getList());
		mav.addObject("commonService", commonService);
		
		
		mav.addObject(ExcelDownloadView.TEMPLATE_FILE_NAME, "selectListCrsExcelTemplate");

		mav.setViewName("excelView");
		mav.addObject(ExcelDownloadView.DOWNLOAD_FILE_NAME, ExcelDownloadView.getDownloadName(req));
		
		return mav;
	}

	/**
	 * 과정(운영과정) 등록 화면
	 * 
	 * @param req
	 * @param res
	 * @param iMCrs
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/crs/regist.do")
	public ModelAndView regist(HttpServletRequest req, HttpServletResponse res, IMCrsVO iMCrs, IMCrsCondition condition)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		mav.addObject("iMCrs", iMCrs);

		mav.addObject("condition", condition);
		iMCrs.setEduYear(IMGlobals.IM_NOW_YEAR); // 기본 값은 현재 년도
		mav.addObject("crsMstrList", crsMstrService.selectListCrsMstr());

		String loginAgncyId = IMStringUtil.getAttribute(req, IMGlobals.IM_LOGIN_USER_AGNCY_KEY);
		if (!"".equals(loginAgncyId)) {
			condition.setScAgncyId(loginAgncyId);
			IMAgncyVO agVo = new IMAgncyVO();
			agVo.setAgncyId(loginAgncyId);

			mav.addObject("agncyDetail", agncyService.selectDetailAgncy(agVo));
		} else {

			mav.addObject("agncyList", agncyService.selectListAgncy());
		}

		mav.setViewName("layout/mng/crs/crs/registCrs");

		return mav;
	}

	/**
	 * 과정(운영과정) 수정 화면
	 * 
	 * @param req
	 * @param res
	 * @param iMCrs
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/crs/modify.do")
	public ModelAndView modify(HttpServletRequest req, HttpServletResponse res, IMCrsVO iMCrs, IMCrsCondition condition)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		IMCrsResultSet detail = (IMCrsResultSet) crsService.selectDetailCrs(iMCrs);

		mav.addObject("detail", detail);
		mav.addObject("iMCrs", detail.getCrs());
		if (detail.getCrs().getAtchFileId() != null && !"".equals(detail.getCrs().getAtchFileId())) {
			FileVO fileVO = new FileVO();
			fileVO.setAtchFileId(detail.getCrs().getAtchFileId());
			mav.addObject("fileList", fileService.selectFileInfs(fileVO));
		}

		mav.addObject("condition", condition);

		mav.addObject("cmmmDescMap", cmmnDescService.selectListCmmnDescResultMap(cmmmDescTblId, iMCrs.getCrsId()));
		mav.addObject("cmmmDtMap", cmmnDtService.selectListCmmnDtResultMap(cmmmDescTblId, iMCrs.getCrsId()));

		mav.setViewName("layout/mng/crs/crs/modifyCrs");

		return mav;
	}

	/**
	 * 과정(운영과정) 등록 저장
	 * 
	 * @param req
	 * @param res
	 * @param iMcrs
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/crs/insert.do")
	public ModelAndView insert(HttpServletRequest req, HttpServletResponse res, @ModelAttribute("iMCrs") IMCrsVO iMCrs,
			IMCmmnDescVO cmmnDesc, IMCmmnDtVO cmmnDt, BindingResult bindingResult, ModelMap model) throws Exception {

		ModelAndView mav = new ModelAndView();
		loginCheckSetAudit(req, iMCrs, cmmnDesc, cmmnDt);
		beanValidator.validate(iMCrs, bindingResult); // validation 수행

		if (bindingResult.hasErrors()) { // 만일 validation 에러가 있으면...
			mav.addObject("result", -1);
		}

		mav.addObject("result", crsService.insertCrs(iMCrs));

		cmmnDescService.insertCmmnDesclist(cmmmDescTblId, iMCrs.getCrsId(), req, cmmnDesc);
		cmmnDtService.insertlistCmmnDt(cmmmDescTblId, iMCrs.getCrsId(), req, cmmnDt);
		mav.setViewName("jsonView");
		return mav;
	}

	/**
	 * 과정(운영과정) 수정 저장
	 * 
	 * @param req
	 * @param res
	 * @param iMcrs
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/crs/update.do")
	public ModelAndView update(HttpServletRequest req, HttpServletResponse res, @ModelAttribute("iMCrs") IMCrsVO iMCrs,
			IMCmmnDescVO cmmnDesc, IMCmmnDtVO cmmnDt, BindingResult bindingResult, ModelMap model) throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMCrs,cmmnDesc,cmmnDt);

		if (bindingResult.hasErrors()) { // 만일 validation 에러가 있으면...
			mav.addObject("result", -1);
		}

		mav.addObject("result", crsService.updateCrs(iMCrs));

		cmmnDescService.updateCmmnDesclist(cmmmDescTblId, iMCrs.getCrsId(), req, cmmnDesc);
		cmmnDtService.updatelistCmmnDt(cmmmDescTblId, iMCrs.getCrsId(), req, cmmnDt);

		mav.setViewName("jsonView");
		return mav;
	}
	/**
	 * 과정(운영과정) 수정 저장
	 * 
	 * @param req
	 * @param res
	 * @param iMcrs
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/crs/{prgrsSttsCdv}/updatePrgrsSttsCdv.do")
	public ModelAndView updatePrgrsSttsCdv(HttpServletRequest req, HttpServletResponse res, IMCrsVO iMCrs,@PathVariable("prgrsSttsCdv") String prgrsSttsCdv) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		loginCheckSetAudit(req, iMCrs);
		iMCrs.setPrgrsSttsCdv(prgrsSttsCdv);
		
		//랜덤 선정 처리 
		if("02".equals(prgrsSttsCdv)) {
			crsAplcntService.updateCrsAlcntRANDOM(iMCrs);
		}else if("04".equals(prgrsSttsCdv)) {
			crsAplcntService.updateAplyCrsOPSECTSRNGtoApply(iMCrs);
		}
		
		 cmmnSttsService.updateCmmnStts(cmmmDescTblId, iMCrs.getCrsId(), "prgrsSttsCdv", prgrsSttsCdv, iMCrs);
	
		 int result =crsService.updateCrsPrgrsSttsCdv(iMCrs);
		
		
		mav.addObject("result",result);
		
	
		mav.setViewName("jsonView");
		return mav;
	}

	/**
	 * 과정(운영과정) 삭제
	 * 
	 * @param req
	 * @param res
	 * @param iMcrs
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/crs/delete.do")
	public ModelAndView delete(HttpServletRequest req, HttpServletResponse res, IMCrsVO iMCrs) throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMCrs);

		mav.addObject("result", crsService.deleteCrs(iMCrs));

		mav.setViewName("jsonView");
		return mav;
	}

	/**
	 * 과정(운영과정) 다중 삭제 처리
	 * 
	 * @param req
	 * @param res
	 * @param IMCrsVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mng/crs/deletelist.do")
	public ModelAndView deletelist(HttpServletRequest req, HttpServletResponse res, IMCrsVO iMCrs) throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMCrs);

		List<IMCrsVO> crss = new ArrayList<IMCrsVO>();

		for (Long idx : iMCrs.getCheckIndexs()) {
			IMCrsVO o = new IMCrsVO();
			o.setCrsId(iMCrs.getCrsIds()[idx.intValue()]);
			o.copyAudit(iMCrs);

			crss.add(o);
		}

		if (crss.size() > 0) {
			mav.addObject("result", crsService.deletelistCrs(crss));
		} else {
			mav.addObject("result", 0);
		}

		mav.setViewName("jsonView");
		return mav;
	}

	/**
	 * 날짜 자동 계산
	 * 
	 * @param req
	 * @param res
	 * @param iMCrs
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mng/crs/defaultdate/json.do")
	public ModelAndView defaultDataJson(HttpServletRequest req, HttpServletResponse res, IMCrsVO iMCrs)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		if (iMCrs.getEduAplTerm_bgnDt() == null || "".equals(iMCrs.getEduAplTerm_bgnDt())) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}

		Date eduAplTerm_bgnDt = IMDateUtil.getFormatDate(iMCrs.getEduAplTerm_bgnDt(), IMGlobals.IM_FORMAT_DATE);

		iMCrs.setEduAplTerm_endDt(
				IMDateUtil.getFormatString(IMDateUtil.addDate(eduAplTerm_bgnDt, 14), IMGlobals.IM_FORMAT_DATE));

		iMCrs.setEduTerm_bgnDt(
				IMDateUtil.getFormatString(IMDateUtil.addDate(eduAplTerm_bgnDt, 25), IMGlobals.IM_FORMAT_DATE));
		iMCrs.setEduTerm_endDt(
				IMDateUtil.getFormatString(IMDateUtil.addDate(eduAplTerm_bgnDt, 27), IMGlobals.IM_FORMAT_DATE));

		iMCrs.setSlctnTerm_bgnDt(
				IMDateUtil.getFormatString(IMDateUtil.addDate(eduAplTerm_bgnDt, 15), IMGlobals.IM_FORMAT_DATE));
		iMCrs.setSlctnTerm_endDt(
				IMDateUtil.getFormatString(IMDateUtil.addDate(eduAplTerm_bgnDt, 19), IMGlobals.IM_FORMAT_DATE));

		iMCrs.setPayTerm_bgnDt(
				IMDateUtil.getFormatString(IMDateUtil.addDate(eduAplTerm_bgnDt, 20), IMGlobals.IM_FORMAT_DATE));
		iMCrs.setPayTerm_endDt(
				IMDateUtil.getFormatString(IMDateUtil.addDate(eduAplTerm_bgnDt, 23), IMGlobals.IM_FORMAT_DATE));

		iMCrs.setFnshYmd_bgnDt(
				IMDateUtil.getFormatString(IMDateUtil.addDate(eduAplTerm_bgnDt, 27), IMGlobals.IM_FORMAT_DATE));
		iMCrs.setOlfcfnshYmd_bgnDt(
				IMDateUtil.getFormatString(IMDateUtil.addDate(eduAplTerm_bgnDt, 30), IMGlobals.IM_FORMAT_DATE));

		mav.addObject("iMCrs", iMCrs);

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
	@RequestMapping (value = { "/mng/crs/{tabCode}/selectListAplcnt.do", "/mng/crs/{tabCode}/selectListAplcntExcel.do" })
	public ModelAndView selectListCrs(HttpServletRequest req, HttpServletResponse res,
			IMCrsVO iMCrs,IMCrsCondition condition,
			@PathVariable("tabCode") String tabCode
			)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		
		//운영과정 정보 
		IMCrsResultSet detail = (IMCrsResultSet) crsService.selectDetailCrs(iMCrs);

		if(detail==null) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		
		mav.addObject("detail", detail);
		
		mav.addObject("cmmmDtMap", cmmnDtService.selectListCmmnDtResultMap(cmmmDescTblId, iMCrs.getCrsId()));
		
		if(condition.getAplcnt()==null) {
			condition.setAplcnt(new IMCrsAplcntCondition());
			condition.getAplcnt().setOrderbyKey(8);
		}
		condition.getAplcnt().setScCrsId(iMCrs.getCrsId());
		condition.getAplcnt().setScNotAplyCancleYn("Y");
		if("05".equals(tabCode) || "99".equals(tabCode)) {
			condition.getAplcnt().setScAplyStts("02"); //선정된 사용자만 노출 	
		}
		if("99".equals(tabCode)) {
			condition.getAplcnt().setScSttsCdvDPST("Y");
		}
		BasePage<BaseResultSet> pageInfo= crsAplcntService.selectListCrsAplcnt(condition.getAplcnt());
		mav.addObject("pageInfo", pageInfo);

		mav.addObject("condition", condition);
		mav.addObject("tabCode", tabCode);
		
		//개인정보 이력 등록 
		Gson gson = new Gson();
		//이력 저장 
		
		if(req.getServletPath().indexOf("selectListAplcntExcel")!=-1) {
			infoInqHstryService.insertInfoInqHstry("im_crs_aplcnt","SELECTNOID",IMGlobals.IM_INFOINQ_E,gson.toJson(iMCrs),req);
			mav.addObject("commonService", commonService);
			mav.addObject("list", pageInfo.getList());
			
			if("04".equals(tabCode)) {
				mav.addObject(ExcelDownloadView.TEMPLATE_FILE_NAME, "selectListCrsAplcntStatus");
			}else if("99".equals(tabCode)){
				if("Attendance".equals(condition.getExcelType())) {
					mav.addObject(ExcelDownloadView.TEMPLATE_FILE_NAME, "selectListCrsAplcntAttendance");
				}else if("Graduate".equals(condition.getExcelType())) {
					mav.addObject(ExcelDownloadView.TEMPLATE_FILE_NAME, "selectListCrsAplcntGraduate");
				}else if("GraduateQlfc".equals(condition.getExcelType())) {
					mav.addObject(ExcelDownloadView.TEMPLATE_FILE_NAME, "selectListCrsAplcntGraduateQlfc");
				}
			}
			mav.setViewName("excelView");
			mav.addObject(ExcelDownloadView.DOWNLOAD_FILE_NAME, ExcelDownloadView.getDownloadName(req));
			
		}else {
			infoInqHstryService.insertInfoInqHstry("im_crs_aplcnt","SELECTNOID",IMGlobals.IM_INFOINQ_S,gson.toJson(condition.getAplcnt()),req);
			mav.setViewName("layout/mng/crs/crs/selectListAplcnt");
			if("03".equals(tabCode) || "04".equals(tabCode)) {
				mav.setViewName("layout/mng/crs/crs/selectListAplcntSimsa");
			}else if("05".equals(tabCode)){
				mav.setViewName("layout/mng/crs/crs/selectListAplcntDpst");
			}else if("99".equals(tabCode)){
				mav.setViewName("layout/mng/crs/crs/selectListAplcntFnsh");
			}
		}
				

		return mav;
	}
	
	
	/**
	 * 안내 페이지
	 * @param req
	 * @param res
	 * @param iMCrs
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping (value = { "/mng/crs/noti.do" })
	public ModelAndView noti(HttpServletRequest req, HttpServletResponse res,
			IMCrsVO iMCrs,IMCrsCondition condition
			)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		//운영과정 정보 
		IMCrsResultSet detail = (IMCrsResultSet) crsService.selectDetailCrs(iMCrs);

		if(detail==null) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		
		mav.addObject("detail", detail);
		
		mav.addObject("condition", condition);
		mav.setViewName("layout/mng/crs/crs/noti");
		return mav;
		
	}

}