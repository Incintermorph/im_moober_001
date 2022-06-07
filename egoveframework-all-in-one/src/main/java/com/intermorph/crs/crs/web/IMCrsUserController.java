/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.crs.crs.web;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.intermorph.cmmn.IMGlobals;
import com.intermorph.cmmn.base.BaseController;
import com.intermorph.cmmn.exception.IMProcErrors;
import com.intermorph.cmmn.exception.IMProcException;
import com.intermorph.cmmn.service.IMCmmnDescService;
import com.intermorph.cmmn.service.IMCmmnDtService;
import com.intermorph.cmmn.service.IMCmmnDtVO;
import com.intermorph.cmmn.service.IMCommonService;
import com.intermorph.cmmn.util.IMDateUtil;
import com.intermorph.cmmn.util.IMStringUtil;
import com.intermorph.crs.agncy.service.IMAgncyService;
import com.intermorph.crs.agncy.service.IMAgncyVO;
import com.intermorph.crs.aplcnt.service.IMCrsAplcntResultSet;
import com.intermorph.crs.aplcnt.service.IMCrsAplcntService;
import com.intermorph.crs.aplcnt.service.IMCrsAplcntVO;
import com.intermorph.crs.crs.service.IMCrsCondition;
import com.intermorph.crs.crs.service.IMCrsResultSet;
import com.intermorph.crs.crs.service.IMCrsService;
import com.intermorph.crs.crs.service.IMCrsVO;
import com.intermorph.uss.hstry.service.IMMmbrHstryResultSet;
import com.intermorph.uss.hstry.service.IMMmbrHstryService;
import com.intermorph.uss.hstry.service.IMMmbrHstryVO;
import com.intermorph.uss.qlfc.service.IMMmbrQlfcService;
import com.intermorph.uss.qlfc.service.IMMmbrQlfcVO;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.crs.crs.web
 * @File    : IMCrsUserController.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 3. 2
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
@Controller
public class IMCrsUserController extends BaseController {
	

	@Resource(name = "IMCrsService")
	private IMCrsService crsService;

	@Resource(name = "IMAgncyService")
	private IMAgncyService agncyService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;
	

    @Resource (name = "IMCommonService")
    private IMCommonService commonService;
    

	@Resource(name = "IMCmmnDescService")
	private IMCmmnDescService cmmnDescService;

	@Resource(name = "IMCmmnDtService")
	private IMCmmnDtService cmmnDtService;
	

	private String cmmmDescTblId = "IM_CRS";
	


	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileService;
	

	@Resource(name = "IMCrsAplcntService")
	private IMCrsAplcntService crsAplcntService;
	

    @Resource (name = "IMMmbrHstryService")
	private IMMmbrHstryService mmbrHstryService;
	

    @Resource (name = "IMMmbrQlfcService")
    private IMMmbrQlfcService mmbrQlfcService;
    
	/**
	 * 과정(운영과정) 목록
	 * 
	 * @param req
	 * @param res
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmmn/crs/{crsType}/{crsGrd}/selectList.do")
	public ModelAndView selectList(HttpServletRequest req, HttpServletResponse res, IMCrsCondition condition,@PathVariable("crsType") String crsType,@PathVariable("crsGrd") String crsGrd)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		setEmptyValue(condition, "currentPageNo=1", "recordCountPerPage=" + propertyService.getInt("pageUnit"),
				"pageSize=" + propertyService.getInt("pageSize"), "orderbyKey=0" , "scProcType=I");

		String grdCode= "";
		String grdNm ="";
		
		if(!"A".equals(crsGrd.toUpperCase())) {
			grdCode= "CRS_GRD_"+crsGrd.trim();
			grdNm =commonService.selectCmmCodeOneDetailNm("IM0001", grdCode);
		
			if("".equals(grdNm)) {
				throw new IMProcException(IMProcErrors.필수값없음);
			}
			condition.setScCrsGrdCdv(grdCode);
		}
		//보수교육 
		if("R".equals(crsType)) {
			condition.setScCrsDvsnCdv("CRS_DVSN_003");
		}else {
			//보수교육 제외
			condition.setScNotCrsDvsnCdv("CRS_DVSN_003");
		}
		condition.setOrderbyKey(-2);
		condition.setScSttsCdv("STTS_02");
		
		
		mav.addObject("pageInfo", crsService.selectListCrs(condition));
		
		mav.addObject("condition", condition);
		
		mav.addObject("crsGrd", crsGrd.toUpperCase());
		mav.addObject("crsType", crsType);
		
		mav.addObject("agncyList", agncyService.selectListAgncyDsgn());

		mav.setViewName("layout/user/crs/crs/selectListCrs");

		return mav;
	}
	/**
	 * 과정(운영과정) 목록 외부 오픈 URL 
	 * 
	 * @param req
	 * @param res
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmmn/crs/{crsType}/{crsGrd}/selectDetailOpen.do")
	public ModelAndView selectDetailOpen(HttpServletRequest req, HttpServletResponse res,IMCrsVO iMCrs, IMCrsCondition condition,@PathVariable("crsType") String crsType,@PathVariable("crsGrd") String crsGrd)
			throws Exception {
		if(IMStringUtil.isEmpty(iMCrs.getCrsIdOpn())) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		
		String crsId = IMStringUtil.decryptString(iMCrs.getCrsIdOpn(), IMGlobals.IM_SECURITY_KEY);
		iMCrs.setCrsId(crsId);
		
		return selectDetail(req, res, iMCrs, condition, crsType, crsGrd);
		
	}
	/**
	 * 과정(운영과정) 목록
	 * 
	 * @param req
	 * @param res
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmmn/crs/{crsType}/{crsGrd}/selectDetail.do")
	public ModelAndView selectDetail(HttpServletRequest req, HttpServletResponse res,IMCrsVO iMCrs, IMCrsCondition condition,@PathVariable("crsType") String crsType,@PathVariable("crsGrd") String crsGrd)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		
		
		IMCrsResultSet detail = (IMCrsResultSet) crsService.selectDetailCrs(iMCrs);

		mav.addObject("detail", detail);
		
		if(detail==null) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		if(!"STTS_02".equals(detail.getCrs().getSttsCdv())){
			throw new IMProcException(IMProcErrors.필수값없음);
		}

		mav.addObject("cmmmDescMap", cmmnDescService.selectListCmmnDescResultMap(cmmmDescTblId, iMCrs.getCrsId()));
		HashMap<String, IMCmmnDtVO> cmmmDtMap= cmmnDtService.selectListCmmnDtResultMap(cmmmDescTblId, iMCrs.getCrsId());
		mav.addObject("cmmmDtMap", cmmmDtMap);

		
		IMAgncyVO agVo = new IMAgncyVO();
		agVo.setAgncyId(detail.getCrs().getAgncyId());

		mav.addObject("agncyDetail", agncyService.selectDetailAgncy(agVo));
		
		if (detail.getCrs().getAtchFileId() != null && !"".equals(detail.getCrs().getAtchFileId())) {
			FileVO fileVO = new FileVO();
			fileVO.setAtchFileId(detail.getCrs().getAtchFileId());
			mav.addObject("fileList", fileService.selectFileInfs(fileVO));
		}
		
		mav.addObject("condition", condition);
		
		mav.addObject("crsGrd", crsGrd);
		mav.addObject("crsType", crsType);
		
		
		
		if(EgovUserDetailsHelper.isAuthenticated()) {
			//이력 기본 데이터
			IMMmbrHstryVO iMMmbrHstry = new  IMMmbrHstryVO ();
			LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
			iMMmbrHstry.setEsntlId(user.getUniqId());
			IMMmbrHstryResultSet detailMmbrHstry = (IMMmbrHstryResultSet) mmbrHstryService.selectDetailMmbrHstry(iMMmbrHstry);
			mav.addObject("detailMmbrHstry", detailMmbrHstry);
			
			//현재 등급 진행 정보 
			
			//희망 급수 정보   
			
			IMMmbrQlfcVO lastMmbrQlfc = mmbrQlfcService.selectDetailMmbrQlfc(user.getUniqId(), detail.getCrsMstr().getCrsGrdCdv());
			//실무 과정인 경우 체크 
			String CRS_DVSN_CHEK="N";
			
			if(detail.getCrsMstr().getCrsDvsnCdv().equals("CRS_DVSN_002")) {
				CRS_DVSN_CHEK="Y";
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
			
			mav.addObject("CRS_DVSN_CHEK", CRS_DVSN_CHEK);
			mav.addObject("lastMmbrQlfc", lastMmbrQlfc);
			
		}

		
		mav.setViewName("layout/user/crs/crs/selectDetailCrs");
		
		return mav;
	}
	
	/**
	 * 운영과정신청자 결과 화면
	 * 
	 * @param req
	 * @param res
	 * @param iMCrsAplcnt
	 * @param condition
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/crs/{crsGrd}/apply/result.do")
	public ModelAndView resultApply(HttpServletRequest req, HttpServletResponse res, IMCrsAplcntVO iMCrsAplcnt,@PathVariable("crsGrd") String crsGrd,IMCrsCondition condition)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		
		if(iMCrsAplcnt.getCrsAplcntId()==null || "".equals(iMCrsAplcnt.getCrsAplcntId())) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		
		IMCrsAplcntResultSet detailApply = (IMCrsAplcntResultSet) crsAplcntService.selectDetailCrsAplcnt(iMCrsAplcnt);

		if(detailApply==null) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		/**
		 * 본인 결과만 확인 
		 */
		if(!detailApply.getCrsAplcnt().getMmbrEsntlId().equals(user.getUniqId())) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		
		IMCrsVO iMCrs = new IMCrsVO();

		iMCrs.setCrsId(detailApply.getCrsAplcnt().getCrsId());

		IMCrsResultSet detail = (IMCrsResultSet) crsService.selectDetailCrs(iMCrs);
		

		mav.addObject("detail", detail);
		mav.addObject("cmmmDescMap", cmmnDescService.selectListCmmnDescResultMap(cmmmDescTblId, iMCrs.getCrsId()));
		mav.addObject("cmmmDtMap", cmmnDtService.selectListCmmnDtResultMap(cmmmDescTblId, iMCrs.getCrsId()));

		

		mav.addObject("condition", condition);
		
		mav.addObject("crsGrd", crsGrd);
		
				
		mav.setViewName("layout/user/crs/crs/resultApplyCrs");
		

		return mav;
	}
	
	
}
