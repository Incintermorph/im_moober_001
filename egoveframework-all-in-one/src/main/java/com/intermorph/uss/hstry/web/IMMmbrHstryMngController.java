/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.hstry.web;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.intermorph.cmmn.IMGlobals;
import com.intermorph.cmmn.base.BaseController;
import com.intermorph.cmmn.exception.IMProcErrors;
import com.intermorph.cmmn.exception.IMProcException;
import com.intermorph.cmmn.service.IMCommonService;
import com.intermorph.cmmn.service.IMInfoInqHstryService;
import com.intermorph.cmmn.util.ExcelDownloadView;
import com.intermorph.cmmn.util.IMStringUtil;
import com.intermorph.crs.aplcnt.service.IMCrsAplcntCondition;
import com.intermorph.crs.aplcnt.service.IMCrsAplcntService;
import com.intermorph.crs.aplcnt.service.IMCrsAplcntVO;
import com.intermorph.uss.hstry.service.IMEduAplyHstryVO;
import com.intermorph.uss.hstry.service.IMMmbrEduService;
import com.intermorph.uss.hstry.service.IMMmbrEtcService;
import com.intermorph.uss.hstry.service.IMMmbrExpService;
import com.intermorph.uss.hstry.service.IMMmbrExptSbjService;
import com.intermorph.uss.hstry.service.IMMmbrHstryCondition;
import com.intermorph.uss.hstry.service.IMMmbrHstryResultSet;
import com.intermorph.uss.hstry.service.IMMmbrHstryService;
import com.intermorph.uss.hstry.service.IMMmbrHstryVO;
import com.intermorph.uss.hstry.service.IMMmbrWorkHstryService;
import com.intermorph.uss.qlfc.service.IMMmbrQlfcService;
import com.potal.uss.institution.service.IMEpInstitutionService;

import egovframework.com.uss.umt.service.EgovMberManageService;
import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.hstry.web
 * @File : IMMmbrHstryController.java
 * @Title : 회원이력
 * @date : 2022.03.10 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Controller
public class IMMmbrHstryMngController extends BaseController {
	


    @Resource (name = "IMMmbrHstryService")
	private IMMmbrHstryService mmbrHstryService;
	
	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;
	
    @Resource (name = "IMMmbrExptSbjService")
    private IMMmbrExptSbjService mmbrExptSbjService;
	
    
    @Resource (name = "IMMmbrExpService")
    private IMMmbrExpService mmbrExpService;
    
    
    @Resource (name = "IMMmbrEduService")
    private IMMmbrEduService mmbrEduService;
    
    
    @Resource (name = "IMMmbrEtcService")
    private IMMmbrEtcService mmbrEtcService;
    
    

    @Resource (name = "IMMmbrWorkHstryService")
	private IMMmbrWorkHstryService mmbrWorkHstryService;


	/** mberManageService */
	@Resource(name = "mberManageService")
	private EgovMberManageService mberManageService;
	

	@Resource(name="IMInfoInqHstryService")
	IMInfoInqHstryService infoInqHstryService;
	

    @Resource (name = "IMMmbrQlfcService")
    private IMMmbrQlfcService mmbrQlfcService;

    @Resource (name = "IMCrsAplcntService")
	private IMCrsAplcntService crsAplcntService;
    

	@Resource(name = "IMCommonService")
	private IMCommonService commonService;
	
	@Resource(name = "IMEpInstitutionService")
	private IMEpInstitutionService epInstitutionService;
	
	/**
	 * 회원이력 목록
	 * 
	 * @param req
	 * @param res
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/mmbrHstry/selectList.do")
	public ModelAndView selectList(HttpServletRequest req, HttpServletResponse res, IMMmbrHstryCondition condition)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		setEmptyValue(condition, "currentPageNo=1", "recordCountPerPage=" + propertyService.getInt("pageUnit"),
				"pageSize=" + propertyService.getInt("pageSize"), "orderbyKey=0");

		mav.addObject("pageInfo", mmbrHstryService.selectListMmbrHstry(condition));

		mav.addObject("condition", condition);
		
		


		//개인정보 이력 등록 
		Gson gson = new Gson();		
		infoInqHstryService.insertInfoInqHstry("im_mmbr_hstry","SELECTNOID",IMGlobals.IM_INFOINQ_S,gson.toJson(condition),req);
		mav.setViewName("layout/mng/uss/mmbrHstry/selectListMmbrHstry");

		return mav;
	}
	
	
	
	/**
	 * 회원이력 수정 화면 
	 * @param req
	 * @param res
	 * @param iMMmbrHstry
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/mmbrHstry/modify.do")
	public ModelAndView modify(HttpServletRequest req, HttpServletResponse res,IMMmbrHstryVO iMMmbrHstry) throws Exception {
		ModelAndView mav = new ModelAndView();
		IMMmbrHstryResultSet detail = (IMMmbrHstryResultSet) mmbrHstryService.selectDetailMmbrHstry(iMMmbrHstry);
		
		String saveMode="U";
		String adminMode="Y";
		if(detail==null) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		
		if(!"Y".equals(detail.getMmbrHstry().getTrnsfYn())) {
			loginCheckSetAudit(req, detail.getMmbrHstry());
			//마이그레이션 데이터 
			mmbrQlfcService.updateMig(detail.getMmbrHstry());
		}
		
		mav.addObject("selectExptSbj", mmbrExptSbjService.selectListMmbrExptSbj(detail.getMmbrHstry().getMemberSrl()));
		mav.addObject("selectExptSbjOnline", mmbrExptSbjService.selectListMmbrExptSbjOnline(detail.getMmbrHstry().getMemberSrl()));
		mav.addObject("selectWorkHstry", mmbrWorkHstryService.selectListMmbrWorkHstry(detail.getMmbrHstry().getMemberSrl()));
		mav.addObject("selectWorkHstryDiffSum", mmbrWorkHstryService.selectListDiffSum(detail.getMmbrHstry().getMemberSrl()));
		mav.addObject("selectExp", mmbrExpService.selectListMmbrExp(detail.getMmbrHstry().getMemberSrl()));
		mav.addObject("selectEdu", mmbrEduService.selectListMmbrEdu(detail.getMmbrHstry().getMemberSrl()));
		mav.addObject("selectEtc", mmbrEtcService.selectListMmbrEtc(detail.getMmbrHstry().getMemberSrl()));
		mav.addObject("selectEduHis01", mmbrHstryService.selectEduHisList(detail.getMmbrHstry().getMemberSrl(),"4002"));//수료 이력만
		
		mav.addObject("listMmbrQlfc", mmbrQlfcService.selectListUserMmbrQlfcPass(detail.getMmbrHstry().getEsntlId()));
		
		mav.addObject("selectAplyUserViewHistory",crsAplcntService.selectAplyUserViewHistory(detail.getMmbrHstry().getEsntlId()));
		
		
		IMCrsAplcntCondition crscondition = new  IMCrsAplcntCondition();
		crscondition.setScMmbrEsntlId(detail.getMmbrHstry().getEsntlId());
		crscondition.setScAplyStts("02");
		crscondition.setScSttsCdvFNSH("02");
		mav.addObject("selectEduHis02", crsAplcntService.selectListCrsAplcnt(crscondition).getList());
		
		
		mav.addObject("detail", detail);
		mav.addObject("saveMode", saveMode);
		mav.addObject("adminMode", adminMode);
		
		//개인정보 이력 등록

		infoInqHstryService.insertInfoInqHstry("im_mmbr_hstry",detail.getMmbrHstry().getMemberSrl()+"",IMGlobals.IM_INFOINQ_R,"User History Review",req);
		
		mav.setViewName("/view/user/uss/mmbrHstry/modifyMmbrHstry");
		
		return mav;
	}
	
	/**
	 * 검토이력 다운로드 
	 * @param req
	 * @param res
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/mmbrHstry/selectAplyUserViewHistoryExcel.do")
	public ModelAndView selectListExcel(HttpServletRequest req, HttpServletResponse res, IMCrsAplcntVO vo)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		
		if(IMStringUtil.isEmpty(vo.getMmbrEsntlId())){
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		
		mav.addObject("list",crsAplcntService.selectAplyUserViewHistory(vo.getMmbrEsntlId()));
		mav.addObject("commonService", commonService);
		
		
		mav.addObject(ExcelDownloadView.TEMPLATE_FILE_NAME, "selectAplyUserViewHistoryExcel");

		mav.setViewName("excelView");
		mav.addObject(ExcelDownloadView.DOWNLOAD_FILE_NAME, ExcelDownloadView.getDownloadName(req));
		
		return mav;
	}

	
	
	/**
	 * 회원이력 목록 (이전 교육자료)
	 * 
	 * @param req
	 * @param res
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/mmbrHstry/selectListEduHis.do")
	public ModelAndView selectListEduHis(HttpServletRequest req, HttpServletResponse res, IMMmbrHstryCondition condition)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		setEmptyValue(condition, "currentPageNo=1", "recordCountPerPage=" + propertyService.getInt("pageUnit"),
				"pageSize=" + propertyService.getInt("pageSize"), "orderbyKey=-5");

		mav.addObject("pageInfo", mmbrHstryService.selectListEduHisHstry(condition));

		mav.addObject("condition", condition);
		
		


		//개인정보 이력 등록 
		Gson gson = new Gson();		
		infoInqHstryService.insertInfoInqHstry("im_edu_aply_hstry","SELECTNOID",IMGlobals.IM_INFOINQ_S,gson.toJson(condition),req);
		mav.setViewName("layout/mng/uss/mmbrHstry/selectListMmbrHstryEduHis");

		return mav;
	}
	/**
	 * 포탈 업데이트 처리 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/mmbrHstry/updatelistEduHis.do")
	public ModelAndView updatelistEduHis(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		
		
		List<IMEduAplyHstryVO> listPotal = epInstitutionService.selectListEduAply(null);
		
		int result=0;
		
		for(IMEduAplyHstryVO vo  :   listPotal) {
			loginCheckSetAudit(req, vo);			
			mmbrHstryService.insertEduAplyHstry(vo);
			result++;
		}

		mav.addObject("result", result);
		mav.setViewName("jsonView");
		return mav;
	}
	

}
