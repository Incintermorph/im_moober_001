/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.crs.aplcnt.web;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.intermorph.cmmn.IMGlobals;
import com.intermorph.cmmn.base.BaseController;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;
import com.intermorph.cmmn.egov.service.IMEgovService;
import com.intermorph.cmmn.exception.IMProcErrors;
import com.intermorph.cmmn.exception.IMProcException;
import com.intermorph.cmmn.util.ExcelDownloadView;
import com.intermorph.cmmn.util.IMDateUtil;
import com.intermorph.cmmn.util.IMExcelPaser;
import com.intermorph.cmmn.util.IMStringUtil;
import com.intermorph.crs.agncy.service.IMAgncyCondition;
import com.intermorph.crs.agncy.service.IMAgncyResultSet;
import com.intermorph.crs.agncy.service.IMAgncyService;
import com.intermorph.crs.aplcnt.service.IMCrsAplcntService;
import com.intermorph.crs.aplcnt.service.IMCrsAplcntVO;
import com.intermorph.crs.aplcnt.service.IMCrsAplyTrnsfCondition;
import com.intermorph.crs.aplcnt.service.IMCrsAplyTrnsfDTO;
import com.intermorph.crs.aplcnt.service.IMCrsAplyTrnsfResultSet;
import com.intermorph.crs.aplcnt.service.IMCrsAplyTrnsfService;
import com.intermorph.crs.aplcnt.service.IMCrsAplyTrnsfVO;
import com.intermorph.crs.crs.service.IMCrsCondition;
import com.intermorph.crs.crs.service.IMCrsResultSet;
import com.intermorph.crs.crs.service.IMCrsService;
import com.intermorph.wtst.aplcnt.service.IMWtstAplcntService;
import com.intermorph.wtst.aplcnt.service.IMWtstAplcntVO;
import com.intermorph.wtst.place.service.IMWtstPlaceCondition;
import com.intermorph.wtst.place.service.IMWtstPlaceResultSet;
import com.intermorph.wtst.place.service.IMWtstPlaceService;
import com.potal.uss.institution.service.IMEpInstitutionService;
import com.potal.uss.institution.service.IMEpInstitutionVO;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.sec.rgm.service.AuthorGroup;
import egovframework.com.sec.rgm.service.EgovAuthorGroupService;
import egovframework.com.uss.umt.service.EgovMberManageService;
import egovframework.com.uss.umt.service.EgovUserManageService;
import egovframework.com.uss.umt.service.MberManageVO;
import egovframework.rte.fdl.access.bean.AuthorityResourceMetadata;
import egovframework.rte.fdl.property.EgovPropertyService;
import net.sf.jxls.reader.XLSDataReadException;
import net.sf.jxls.reader.XLSReadStatus;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.crs.aplcnt.web
 * @File : IMCrsAplyTrnsfController.java
 * @Title : 과정신청이관
 * @date : 2022.05.12 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Controller
public class IMCrsAplyTrnsfMngController extends BaseController {


    @Resource (name = "IMCrsAplyTrnsfService")
	private IMCrsAplyTrnsfService crsAplyTrnsfService;
	
	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;

	

    @Resource(name = "EgovFileMngService")
    private EgovFileMngService fileService;
    
    
    @Resource(name = "IMEpInstitutionService")
    private IMEpInstitutionService epInstitutionService;



	@Resource(name = "IMCrsAplcntService")
	private IMCrsAplcntService crsAplcntService;
	
	
	/** mberManageService */
	@Resource(name = "mberManageService")
	private EgovMberManageService mberManageService;
	

	/** userManageService */
	@Resource(name = "userManageService")
	private EgovUserManageService userManageService;
	

    @Resource(name = "egovAuthorGroupService")
    private EgovAuthorGroupService egovAuthorGroupService;
    

	@Resource(name="authorityResource")
	private AuthorityResourceMetadata sessionResourceMetadata;

	@Resource(name = "IMEgovService")
	private IMEgovService iMEgovService;
	

	@Resource (name = "IMAgncyService")
	private IMAgncyService agncyService;
	


    @Resource (name = "IMWtstPlaceService")
	private IMWtstPlaceService wtstPlaceService;
    
	@Resource(name = "IMCrsService")
	private IMCrsService crsService;
	

	@Resource (name = "IMWtstAplcntService")
	private IMWtstAplcntService wtstAplcntService;
	
	
	/**
	 * 과정신청이관 목록
	 * 
	 * @param req
	 * @param res
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/crsAplyTrnsf/selectList.do")
	public ModelAndView selectList(HttpServletRequest req, HttpServletResponse res, IMCrsAplyTrnsfCondition condition)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		setEmptyValue(condition, "currentPageNo=1", "recordCountPerPage=" + propertyService.getInt("pageUnit"),
				"pageSize=" + propertyService.getInt("pageSize"), "orderbyKey=0");

		mav.addObject("pageInfo", crsAplyTrnsfService.selectListCrsAplyTrnsf(condition));

		mav.addObject("condition", condition);

		mav.setViewName("layout/mng/crs/crsAplcnt/selectListCrsAplyTrnsf");

		return mav;
	}
	
	
	
	
	
	/**
	 * 과정신청이관  등록 화면 
	 * @param req
	 * @param res
	 * @param iMCrsAplyTrnsf
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/crsAplyTrnsf/registExcelLayer.do")
	public ModelAndView registExcelLayer(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		
		mav.setViewName("layer/mng/crs/crsAplcnt/registCrsAplyTrnsfExcelLayer");
		
		return mav;
	}
	
	/**
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/crsAplyTrnsf/registExcel/sample.do")
	public ModelAndView registExcelSample(HttpServletRequest req, HttpServletResponse res) throws Exception {	
		ModelAndView mav = new ModelAndView();
		
		mav.addObject(ExcelDownloadView.TEMPLATE_FILE_NAME, "crsAplyTrnsfRegistExcel");
		
		mav.setViewName("excelView");
		mav.addObject(ExcelDownloadView.DOWNLOAD_FILE_NAME, ExcelDownloadView.getDownloadName(req));
		
		return mav;
	}
	
	/**
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/crsAplyTrnsf/registExcelResult.do")
	public ModelAndView registExcelResult(HttpServletRequest req, HttpServletResponse res) throws Exception {	
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("list", req.getSession().getAttribute(IMGlobals.EXEXL_SESSION_KEY));
		
		mav.addObject(ExcelDownloadView.TEMPLATE_FILE_NAME, "crsAplyTrnsfRegistExcelResult");
		
		mav.setViewName("excelView");
		mav.addObject(ExcelDownloadView.DOWNLOAD_FILE_NAME, ExcelDownloadView.getDownloadName(req));

		return mav;
	}
	
	/**
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/crsAplyTrnsf/insertExcel.do")
	public ModelAndView insertExcel(HttpServletRequest req, HttpServletResponse res, FileVO fileVO) throws Exception {		
		ModelAndView mav = new ModelAndView();

		mav.setViewName("jsonView");
		if(fileVO.getAtchFileId()==null || "".equals(fileVO.getAtchFileId()) ) {
			throw new IMProcException(IMProcErrors.필수값없음); 
		}
		
		fileVO.setFileSn("0");
		

		FileVO fvo = fileService.selectFileInf(fileVO);
		
		
		if(fvo==null ) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		String filePath = fvo.getFileStreCours()+File.separator+fvo.getStreFileNm();

		File saveFile = new File(filePath);
		List<IMCrsAplyTrnsfVO> iMCrsAplyTrnsfs = new ArrayList<IMCrsAplyTrnsfVO>();
		Map<String, Object> rowDataes = new HashMap<String, Object>();
		rowDataes.put("iMCrsAplyTrnsfs", iMCrsAplyTrnsfs);
		int result =0;
		int success =0;
		
		try {
			IMExcelPaser excelParse = new IMExcelPaser();
			excelParse.setUseDefaultValuesForPrimitiveTypes(true);
			XLSReadStatus readStatus = excelParse.parse(rowDataes, saveFile, "IMCrsAplyTrnsfTemplate-jxls-config");
			if (readStatus.isStatusOK()) {

				
				for (IMCrsAplyTrnsfVO iMCrsAplyTrnsf : iMCrsAplyTrnsfs) {
					
					loginCheckSetAudit(req, iMCrsAplyTrnsf);
					try {
						try {
							iMCrsAplyTrnsf.setCrsAplyTrnsfNo(Long.parseLong(iMCrsAplyTrnsf.getCrsAplyTrnsfNo())+"");
							iMCrsAplyTrnsf.setEduYear(Long.parseLong(iMCrsAplyTrnsf.getEduYear())+"");
							iMCrsAplyTrnsf.setEduRnd(Long.parseLong(iMCrsAplyTrnsf.getEduRnd())+"");
							if(crsAplyTrnsfService.insertCrsAplyTrnsf(iMCrsAplyTrnsf)>0) {
								success++;
								iMCrsAplyTrnsf.setExcelResult("OK");
							}else {
								iMCrsAplyTrnsf.setExcelResult("FAIL");
							}
						}catch (NumberFormatException e) {
							iMCrsAplyTrnsf.setExcelResult("연번,교육연도,차수는 숫자만 등록해야합니다.");
						}
						
					}catch (Exception e) {
						iMCrsAplyTrnsf.setExcelResult("필수값 확인 바랍니다.");
						e.printStackTrace();
					}
					result++;
				}
				
				
				
				req.getSession().setAttribute(IMGlobals.EXEXL_SESSION_KEY, iMCrsAplyTrnsfs);
				
			}
		} catch (XLSDataReadException xlsException) {
			if (saveFile != null && saveFile.exists()) {
				saveFile.delete();
			    fileService.deleteFileInf(fileVO);
			}
			log.error(xlsException);
			result = -1;

			return mav;
		} finally {
			if (saveFile != null && saveFile.exists()) {
				saveFile.delete();

			    fileService.deleteFileInf(fileVO);
			}
		}
		

		mav.addObject("result", result);
		mav.addObject("success", success);
		return mav;
	}
	
	
	
	/**
	 * 과정신청이관 수정 화면 
	 * @param req
	 * @param res
	 * @param iMCrsAplyTrnsf
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/crsAplyTrnsf/modify.do")
	public ModelAndView modify(HttpServletRequest req, HttpServletResponse res, IMCrsAplyTrnsfVO iMCrsAplyTrnsf,
			IMCrsAplyTrnsfCondition condition) throws Exception {
		ModelAndView mav = new ModelAndView();

		IMCrsAplyTrnsfResultSet detail = (IMCrsAplyTrnsfResultSet) crsAplyTrnsfService.selectDetailCrsAplyTrnsf(iMCrsAplyTrnsf);

		mav.addObject("iMCrsAplyTrnsf", detail.getCrsAplyTrnsf());

		mav.addObject("condition", condition);
		
		
		mav.setViewName("layout/mng/crs/crsAplcnt/modifyCrsAplyTrnsf");
		
		return mav;
	}
	
	
	
	
	
	/**
	 * 과정신청이관 등록 저장 
	 * @param req
	 * @param res
	 * @param iMcrsAplyTrnsf
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/crsAplyTrnsf/insert.do")
	public ModelAndView insert(HttpServletRequest req, HttpServletResponse res,
			@ModelAttribute("iMCrsAplyTrnsf") IMCrsAplyTrnsfVO iMCrsAplyTrnsf )
			throws Exception {

		ModelAndView mav = new ModelAndView();
		loginCheckSetAudit(req, iMCrsAplyTrnsf);

		mav.addObject("result", crsAplyTrnsfService.insertCrsAplyTrnsf(iMCrsAplyTrnsf));

		mav.setViewName("jsonView");
		return mav;
	}
	
	/**
	 * 과정신청이관 수정 저장
	 * @param req
	 * @param res
	 * @param iMcrsAplyTrnsf
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/crsAplyTrnsf/update.do")
	public ModelAndView update(HttpServletRequest req, HttpServletResponse res,
			@ModelAttribute("iMCrsAplyTrnsf") IMCrsAplyTrnsfVO iMCrsAplyTrnsf)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMCrsAplyTrnsf);

		

		mav.addObject("result",crsAplyTrnsfService.updateCrsAplyTrnsf(iMCrsAplyTrnsf));
		
		mav.setViewName("jsonView");
		return mav;
	}
	
	/**
	 * 과정신청이관 수정 저장
	 * @param req
	 * @param res
	 * @param iMcrsAplyTrnsf
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/crsAplyTrnsf/updateMigMember.do")
	public ModelAndView updateMigMember(HttpServletRequest req, HttpServletResponse res)
					throws Exception {
		ModelAndView mav = new ModelAndView();
		
		IMCrsAplyTrnsfCondition condition= new IMCrsAplyTrnsfCondition();
		
		BasePage<BaseResultSet> page = crsAplyTrnsfService.selectListCrsAplyTrnsf(condition);
		String  uniqId="";
		int result=0;
		Gson gson = new Gson();
		for(BaseResultSet  rs :  page.getList()) {
			IMCrsAplyTrnsfResultSet crsAplyTrnsfResultSet= (IMCrsAplyTrnsfResultSet)rs;
			uniqId="";
			if(IMStringUtil.isEmpty(crsAplyTrnsfResultSet.getCrsAplyTrnsf().getTrnsfAplyId())) {
			
			//	System.out.println("userid : " + crsAplyTrnsfResultSet.getCrsAplyTrnsf().getMmbrId());
				
				IMEpInstitutionVO resultVO = epInstitutionService.selectMemberUser(crsAplyTrnsfResultSet.getCrsAplyTrnsf().getMmbrId());
				result++;
				if(resultVO!=null) {
					
					
					MberManageVO mberManageVO = new MberManageVO();
					mberManageVO.setMberId(resultVO.getUserid());
					mberManageVO.setMberNm(resultVO.getName());
					mberManageVO.setMberEmailAdres(resultVO.getEmail());
					mberManageVO.setGroupId("GROUP_00000000000000");
					mberManageVO.setPassword(UUID.randomUUID().toString().replaceAll("-", ""));
					mberManageVO.setAreaNo(IMStringUtil.getPhoneNum(resultVO.getTel(), 0));
					mberManageVO.setMiddleTelno(IMStringUtil.getPhoneNum(resultVO.getTel(), 1));
					mberManageVO.setEndTelno(IMStringUtil.getPhoneNum(resultVO.getTel(), 2));
					if(mberManageVO.getAreaNo()==null || "".equals(mberManageVO.getAreaNo())) {
						mberManageVO.setAreaNo("0000");
						mberManageVO.setMiddleTelno("0000");
						mberManageVO.setEndTelno("0000");
					}
					mberManageVO.setMberSttus("P");
					mberManageVO.setZip("00000");
					mberManageVO.setAdres("0000");
					mberManageVO.setIhidnum(resultVO.getMemberSrl());
					mberManageVO.setPasswordHint("P01");
					mberManageVO.setPasswordCnsr(UUID.randomUUID().toString());
					mberManageVO.setMoblphonNo(resultVO.getTel());
					int over = userManageService.checkIdDplct(mberManageVO.getMberId());
					if(over==0) {
						//등록 
						//System.out.println(mberManageVO.toString());
						mberManageService.insertMber(mberManageVO);
						AuthorGroup authorGroup = new AuthorGroup();
						authorGroup.setUniqId(mberManageVO.getUniqId());
						authorGroup.setMberTyCode("USR02");
						authorGroup.setAuthorCode("ROLE_USER");
						uniqId=mberManageVO.getUniqId();
						//권한 부여 
						egovAuthorGroupService.insertAuthorGroup(authorGroup);
					}else {
						 uniqId = iMEgovService.selectUserId(mberManageVO.getMberId());
					}
					
					if(!IMStringUtil.isEmpty(uniqId)) {
						IMCrsAplyTrnsfDTO dto = new IMCrsAplyTrnsfDTO();
						dto.setUniqId(uniqId);
						dto.setUniqDt(IMDateUtil.getImToday("yyyyMMddHHmmss"));
						IMCrsAplyTrnsfVO update = new IMCrsAplyTrnsfVO();
						update.setCrsAplyTrnsfNo(crsAplyTrnsfResultSet.getCrsAplyTrnsf().getCrsAplyTrnsfNo());
						update.setTrnsfAplyId(gson.toJson(dto));
						update.setTrnsfYn("M");
						loginCheckSetAudit(req, update);
						crsAplyTrnsfService.updateCrsAplyTrnsf(update);
					}
				}

			}
			

			sessionResourceMetadata.reload();
			
		}
		mav.addObject("result", result);
		mav.setViewName("jsonView");
		return mav;
	}
	/**
	 * 과정신청이관 수정 저장 ( 과정 신청)
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/crsAplyTrnsf/updateMigCrs.do")
	public ModelAndView updateMigCrs(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		
		IMCrsAplyTrnsfCondition condition= new IMCrsAplyTrnsfCondition();
		
		BasePage<BaseResultSet> page = crsAplyTrnsfService.selectListCrsAplyTrnsf(condition);
		
		int result=0;
		Gson gson = new Gson();
		IMCrsAplcntVO iMCrsAplcnt;
		IMAgncyCondition agncyCondition;
		IMCrsCondition crscondition;
		for(BaseResultSet  rs :  page.getList()) {
			IMCrsAplyTrnsfResultSet crsAplyTrnsfResultSet= (IMCrsAplyTrnsfResultSet)rs;
			
			if(!IMStringUtil.isEmpty(crsAplyTrnsfResultSet.getCrsAplyTrnsf().getTrnsfAplyId())) {
				if(!IMStringUtil.isEmpty(crsAplyTrnsfResultSet.getIdDto().getUniqId()) && 
						IMStringUtil.isEmpty(crsAplyTrnsfResultSet.getIdDto().getCrsAplcntId())){
					iMCrsAplcnt = new IMCrsAplcntVO ();
					iMCrsAplcnt.setMmbrEsntlId(crsAplyTrnsfResultSet.getIdDto().getUniqId());
					
					//수강신청 처리 
					agncyCondition = new IMAgncyCondition();
					// 연계 포탈 코드 확인 후 유지보수 기관 체크
					agncyCondition.setScLinkCode(crsAplyTrnsfResultSet.getCrsAplyTrnsf().getAgncyCode());
					BasePage<BaseResultSet> baseResult = agncyService.selectListAgncy(agncyCondition);

					crscondition = new IMCrsCondition();
					
					if (baseResult != null && baseResult.getTotalRecordCount() > 0) {

						IMAgncyResultSet rs3 = (IMAgncyResultSet)baseResult.getList().get(0);
						
						crscondition.setScAgncyId(rs3.getAgncy().getAgncyId());
					}
					
					if ("1".equals(crsAplyTrnsfResultSet.getCrsAplyTrnsf().getCrsGrd().trim())) {
						crscondition.setScCrsGrdCdv("CRS_GRD_1");
					} else if ("2".equals(crsAplyTrnsfResultSet.getCrsAplyTrnsf().getCrsGrd().trim())) {
						crscondition.setScCrsGrdCdv("CRS_GRD_2");
					} else if ("3".equals(crsAplyTrnsfResultSet.getCrsAplyTrnsf().getCrsGrd().trim())) {
						crscondition.setScCrsGrdCdv("CRS_GRD_3");
					}
					crscondition.setScCrsDvsnCdv("CRS_DVSN_001");
					crscondition.setScEduYear(crsAplyTrnsfResultSet.getCrsAplyTrnsf().getEduYear());
					crscondition.setScEduRnd(crsAplyTrnsfResultSet.getCrsAplyTrnsf().getEduRnd());
					
					if(!IMStringUtil.isEmpty(crscondition.getScAgncyId()) && 
							!IMStringUtil.isEmpty(crscondition.getScCrsGrdCdv()) && 
							!IMStringUtil.isEmpty(crscondition.getScEduYear())&& 
							!IMStringUtil.isEmpty(crscondition.getScEduRnd())
							){
						BasePage<BaseResultSet> crsbaseResult = 	crsService.selectListCrs(crscondition);

						if (crsbaseResult != null && crsbaseResult.getTotalRecordCount() > 0) {
							IMCrsResultSet rs3 = (IMCrsResultSet)crsbaseResult.getList().get(0);
							iMCrsAplcnt.setCrsId(rs3.getCrs().getCrsId());							
							iMCrsAplcnt.setDsrAplyGrdCdv(crscondition.getScCrsGrdCdv());
							iMCrsAplcnt.setWorkDvsnCdv("999");
							iMCrsAplcnt.setBatchYn("Y");
							loginCheckSetAudit(req, iMCrsAplcnt);
							
							int resultint= crsAplcntService.insertCrsAplcnt(iMCrsAplcnt);
							IMCrsAplyTrnsfDTO idDto = crsAplyTrnsfResultSet.getIdDto();
							if(!IMStringUtil.isEmpty(iMCrsAplcnt.getCrsAplcntId())){
								idDto.setCrsAplcntId(iMCrsAplcnt.getCrsAplcntId());
								idDto.setCrsAplcntDt(IMDateUtil.getImToday("yyyyMMddHHmmss"));
								idDto.setCrsAplcntResult(resultint+"");
								IMCrsAplyTrnsfVO update = new IMCrsAplyTrnsfVO();
								update.setCrsAplyTrnsfNo(crsAplyTrnsfResultSet.getCrsAplyTrnsf().getCrsAplyTrnsfNo());
								update.setTrnsfAplyId(gson.toJson(idDto));
								update.setTrnsfYn("C");
								loginCheckSetAudit(req, update);
								crsAplyTrnsfService.updateCrsAplyTrnsf(update);
								result++;
							}else {
								IMCrsAplyTrnsfVO update = new IMCrsAplyTrnsfVO();
								update.setCrsAplyTrnsfNo(crsAplyTrnsfResultSet.getCrsAplyTrnsf().getCrsAplyTrnsfNo());
								idDto.setCrsAplcntResult(resultint+"");
								update.setTrnsfAplyId(gson.toJson(idDto));
								
								loginCheckSetAudit(req, update);
								crsAplyTrnsfService.updateCrsAplyTrnsf(update);
								result++;
							}
						}
						
					}
					
					
				}
				
			}
			
			
			
		}
		mav.addObject("result", result);
		mav.setViewName("jsonView");
		return mav;
	}
	/**
	 * 과정신청이관 응시현황 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/crsAplyTrnsf/updateMigWtst.do")
	public ModelAndView updateMigWtst(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		
		IMCrsAplyTrnsfCondition condition= new IMCrsAplyTrnsfCondition();
		
		BasePage<BaseResultSet> page = crsAplyTrnsfService.selectListCrsAplyTrnsf(condition);
		
		int result=0;
		Gson gson = new Gson();
		IMWtstAplcntVO iMWtstAplcnt;
		IMAgncyCondition agncyCondition;
		IMWtstPlaceCondition wtstcondition;
		for(BaseResultSet  rs :  page.getList()) {
			IMCrsAplyTrnsfResultSet crsAplyTrnsfResultSet= (IMCrsAplyTrnsfResultSet)rs;
			
			if(!IMStringUtil.isEmpty(crsAplyTrnsfResultSet.getCrsAplyTrnsf().getTrnsfAplyId())) {
				if(!IMStringUtil.isEmpty(crsAplyTrnsfResultSet.getIdDto().getUniqId()) && 
						!IMStringUtil.isEmpty(crsAplyTrnsfResultSet.getIdDto().getCrsAplcntId()) &&
						IMStringUtil.isEmpty(crsAplyTrnsfResultSet.getIdDto().getWtstAplcntId())
						){
					iMWtstAplcnt = new IMWtstAplcntVO ();
					iMWtstAplcnt.setMmbrEsntlId(crsAplyTrnsfResultSet.getIdDto().getUniqId());
					
					//수강신청 처리 
					agncyCondition = new IMAgncyCondition();
					// 연계 포탈 코드 확인 후 유지보수 기관 체크
					agncyCondition.setScLinkCode(crsAplyTrnsfResultSet.getCrsAplyTrnsf().getAgncyCode());
					BasePage<BaseResultSet> baseResult = agncyService.selectListAgncy(agncyCondition);
					
					wtstcondition = new IMWtstPlaceCondition();
					
					
					if ("1".equals(crsAplyTrnsfResultSet.getCrsAplyTrnsf().getCrsGrd().trim())) {
						wtstcondition.setScCrsGrdCdv("CRS_GRD_1");
					} else if ("2".equals(crsAplyTrnsfResultSet.getCrsAplyTrnsf().getCrsGrd().trim())) {
						wtstcondition.setScCrsGrdCdv("CRS_GRD_2");
					} else if ("3".equals(crsAplyTrnsfResultSet.getCrsAplyTrnsf().getCrsGrd().trim())) {
						wtstcondition.setScCrsGrdCdv("CRS_GRD_3");
					}
					wtstcondition.setScEduYear(crsAplyTrnsfResultSet.getCrsAplyTrnsf().getEduYear());
					wtstcondition.setScEduRnd(crsAplyTrnsfResultSet.getCrsAplyTrnsf().getEduRnd());
					
					if(	!IMStringUtil.isEmpty(wtstcondition.getScCrsGrdCdv()) && 
							!IMStringUtil.isEmpty(wtstcondition.getScEduYear())&& 
							!IMStringUtil.isEmpty(wtstcondition.getScEduRnd())
							){
						BasePage<BaseResultSet> crsbaseResult = 	 wtstPlaceService.selectListWtstPlace(wtstcondition);
						
						if (crsbaseResult != null && crsbaseResult.getTotalRecordCount() > 0) {
							IMWtstPlaceResultSet rs3 = (IMWtstPlaceResultSet)crsbaseResult.getList().get(0);
							iMWtstAplcnt.setWtstPlaceId(rs3.getWtstPlace().getWtstPlaceId());
							iMWtstAplcnt.setMberId(crsAplyTrnsfResultSet.getCrsAplyTrnsf().getMmbrId());
							iMWtstAplcnt.setMberNm(crsAplyTrnsfResultSet.getCrsAplyTrnsf().getMmbrNm());
							iMWtstAplcnt.setMberEmailAdres(crsAplyTrnsfResultSet.getCrsAplyTrnsf().getEml());
							iMWtstAplcnt.setBrdt(crsAplyTrnsfResultSet.getCrsAplyTrnsf().getBrdt());
							iMWtstAplcnt.setMblTelno1(IMStringUtil.getPhoneNum(crsAplyTrnsfResultSet.getCrsAplyTrnsf().getTelno(), 0));
							iMWtstAplcnt.setMblTelno2(IMStringUtil.getPhoneNum(crsAplyTrnsfResultSet.getCrsAplyTrnsf().getTelno(), 1));
							iMWtstAplcnt.setMblTelno3(IMStringUtil.getPhoneNum(crsAplyTrnsfResultSet.getCrsAplyTrnsf().getTelno(), 2));
						
							iMWtstAplcnt.setConvPvsnYn("N");
							
							
							//iMWtstAplcnt.setDsrAplyGrdCdv(wtstcondition.getScCrsGrdCdv());
							iMWtstAplcnt.setWorkDvsnCdv("999");
							iMWtstAplcnt.setBatchYn("Y");
							loginCheckSetAudit(req, iMWtstAplcnt);
							
							int resultint= wtstAplcntService.insertWtstAplcnt(iMWtstAplcnt);
							IMCrsAplyTrnsfDTO idDto = crsAplyTrnsfResultSet.getIdDto();
							if(!IMStringUtil.isEmpty(iMWtstAplcnt.getWtstAplcntId())){
								idDto.setWtstAplcntId(iMWtstAplcnt.getWtstAplcntId());
								idDto.setWtstAplcntDt(IMDateUtil.getImToday("yyyyMMddHHmmss"));
								idDto.setCrsAplcntResult(resultint+"");
								IMCrsAplyTrnsfVO update = new IMCrsAplyTrnsfVO();
								update.setCrsAplyTrnsfNo(crsAplyTrnsfResultSet.getCrsAplyTrnsf().getCrsAplyTrnsfNo());
								update.setTrnsfAplyId(gson.toJson(idDto));
								update.setTrnsfYn("W");
								loginCheckSetAudit(req, update);
								crsAplyTrnsfService.updateCrsAplyTrnsf(update);
								result++;
							}else {
								IMCrsAplyTrnsfVO update = new IMCrsAplyTrnsfVO();
								update.setCrsAplyTrnsfNo(crsAplyTrnsfResultSet.getCrsAplyTrnsf().getCrsAplyTrnsfNo());
								idDto.setWtstAplcntResult(resultint+"");
								update.setTrnsfAplyId(gson.toJson(idDto));
								
								loginCheckSetAudit(req, update);
								crsAplyTrnsfService.updateCrsAplyTrnsf(update);
								result++;
							}
						}
						
					}
					
					
				}
				
			}
			
			
			
		}
		mav.addObject("result", result);
		mav.setViewName("jsonView");
		return mav;
	}

	
	
	
	/**
	 *  과정신청이관  삭제
	 * @param req
	 * @param res
	 * @param iMcrsAplyTrnsf
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/crsAplyTrnsf/delete.do")
	public ModelAndView delete(HttpServletRequest req, HttpServletResponse res, IMCrsAplyTrnsfVO iMCrsAplyTrnsf)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMCrsAplyTrnsf);

		mav.addObject("result", crsAplyTrnsfService.deleteCrsAplyTrnsf(iMCrsAplyTrnsf));

		mav.setViewName("jsonView");
		return mav;
	}
	
	
	
	
	
	

	
}