/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.qlfc.web;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.intermorph.cmmn.IMGlobals;
import com.intermorph.cmmn.base.BaseController;
import com.intermorph.cmmn.exception.IMProcErrors;
import com.intermorph.cmmn.exception.IMProcException;
import com.intermorph.cmmn.util.ExcelDownloadView;
import com.intermorph.cmmn.util.IMExcelPaser;
import com.intermorph.cmmn.util.IMStringUtil;
import com.intermorph.uss.qlfc.service.IMLcncHstryCondition;
import com.intermorph.uss.qlfc.service.IMLcncHstryResultSet;
import com.intermorph.uss.qlfc.service.IMLcncHstryService;
import com.intermorph.uss.qlfc.service.IMLcncHstryVO;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import net.sf.jxls.reader.XLSDataReadException;
import net.sf.jxls.reader.XLSReadStatus;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.qlfc.web
 * @File : IMLcncHstryController.java
 * @Title : 자격증 이력
 * @date : 2022.05.04 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Controller
public class IMLcncHstryMngController extends BaseController {


    @Resource (name = "IMLcncHstryService")
	private IMLcncHstryService lcncHstryService;
	
	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;


    @Resource(name = "EgovFileMngService")
    private EgovFileMngService fileService;
	


	
	/**
	 * 자격증 이력 목록
	 * 
	 * @param req
	 * @param res
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/lcncHstry/selectList.do")
	public ModelAndView selectList(HttpServletRequest req, HttpServletResponse res, IMLcncHstryCondition condition)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		setEmptyValue(condition, "currentPageNo=1", "recordCountPerPage=" + propertyService.getInt("pageUnit"),
				"pageSize=" + propertyService.getInt("pageSize"), "orderbyKey=0");

		mav.addObject("pageInfo", lcncHstryService.selectListLcncHstry(condition));

		mav.addObject("condition", condition);

		mav.setViewName("layout/mng/uss/lcncHstry/selectListLcncHstry");

		return mav;
	}
	
	
	
	
	/**
	 * 자격증 이력 수정 화면 
	 * @param req
	 * @param res
	 * @param iMLcncHstry
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/lcncHstry/modify.do")
	public ModelAndView modify(HttpServletRequest req, HttpServletResponse res, IMLcncHstryVO iMLcncHstry,
			IMLcncHstryCondition condition) throws Exception {
		ModelAndView mav = new ModelAndView();

		IMLcncHstryResultSet detail = (IMLcncHstryResultSet) lcncHstryService.selectDetailLcncHstry(iMLcncHstry);

		mav.addObject("iMLcncHstry", detail.getLcncHstry());

		mav.addObject("condition", condition);
		
		
		mav.setViewName("layout/mng/uss/lcncHstry/modifyLcncHstry");
		
		return mav;
	}
	
	
	
	
	
	
	
	/**
	 * 자격증 이력 수정 저장
	 * @param req
	 * @param res
	 * @param iMlcncHstry
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/lcncHstry/update.do")
	public ModelAndView update(HttpServletRequest req, HttpServletResponse res,
			@ModelAttribute("iMLcncHstry") IMLcncHstryVO iMLcncHstry)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMLcncHstry);

	

		mav.addObject("result",lcncHstryService.updateLcncHstry(iMLcncHstry));
		
		mav.setViewName("jsonView");
		return mav;
	}
	
	
	/**
	 * 자격증 이력 수정 저장
	 * @param req
	 * @param res
	 * @param iMlcncHstry
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/lcncHstry/insert.do")
	public ModelAndView insert(HttpServletRequest req, HttpServletResponse res,
			@ModelAttribute("iMLcncHstry") IMLcncHstryVO iMLcncHstry)
					throws Exception {
		ModelAndView mav = new ModelAndView();
		
		loginCheckSetAudit(req, iMLcncHstry);
		
		
		
		//mav.addObject("result",lcncHstryService.updateLcncHstry(iMLcncHstry));
		
		mav.setViewName("jsonView");
		return mav;
	}

	
	
	
	/**
	 *  자격증 이력  삭제
	 * @param req
	 * @param res
	 * @param iMlcncHstry
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/lcncHstry/delete.do")
	public ModelAndView delete(HttpServletRequest req, HttpServletResponse res, IMLcncHstryVO iMLcncHstry)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMLcncHstry);

		mav.addObject("result", lcncHstryService.deleteLcncHstry(iMLcncHstry));

		mav.setViewName("jsonView");
		return mav;
	}
	
	
	/**
	 *  자격증 이력 다중 삭제 처리
	 * 
	 * @param req
	 * @param res
	 * @param IMLcncHstryVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping ("/mng/lcncHstry/deletelist.do")
	public ModelAndView deletelist(HttpServletRequest req, HttpServletResponse res, IMLcncHstryVO iMLcncHstry) throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMLcncHstry);
		

		List<IMLcncHstryVO> lcncHstrys = new ArrayList<IMLcncHstryVO>();
				
		for (Long idx: iMLcncHstry.getCheckIndexs()) {
			IMLcncHstryVO o = new IMLcncHstryVO();
			o.setLcncHstryId(iMLcncHstry.getLcncHstryIds()[idx.intValue()]);
			o.copyAudit(iMLcncHstry);

			lcncHstrys.add(o);
		}

		if (lcncHstrys.size() > 0) {
			mav.addObject("result", lcncHstryService.deletelistLcncHstry(lcncHstrys));
		} else {
			mav.addObject("result", 0);
		}

		mav.setViewName("jsonView");
		return mav;
	}
	
	
	/**
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/lcncHstry/registExcelLayer.do")
	public ModelAndView registExcelLayer(HttpServletRequest req, HttpServletResponse res) throws Exception {		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("layer/mng/uss/lcncHstry/registLcncHstryExcelLayer");
		return mav;
	}
	/**
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/lcncHstry/insertExcel.do")
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
		List<IMLcncHstryVO> iMLcncHstrys = new ArrayList<IMLcncHstryVO>();
		Map<String, Object> rowDataes = new HashMap<String, Object>();
		rowDataes.put("iMLcncHstrys", iMLcncHstrys);
		int result =0;
		int success =0;
		
		try {
			IMExcelPaser excelParse = new IMExcelPaser();
			excelParse.setUseDefaultValuesForPrimitiveTypes(true);
			XLSReadStatus readStatus = excelParse.parse(rowDataes, saveFile, "IMLcncHstryTemplate-jxls-config");
			if (readStatus.isStatusOK()) {

				
				for (IMLcncHstryVO iMLcncHstry : iMLcncHstrys) {
					System.out.println("getLcncNo" + iMLcncHstry.getLcncNo() + ",getMmbrNm" + iMLcncHstry.getMmbrNm() );
					loginCheckSetAudit(req, iMLcncHstry);
					try {
						
						if(!"Y".equals(iMLcncHstry.getYn_2018())) {
							iMLcncHstry.setYn_2018("N");
						}
						if(!"Y".equals(iMLcncHstry.getYn_2019())) {
							iMLcncHstry.setYn_2019("N");
						}
						if(!"Y".equals(iMLcncHstry.getYn_2020())) {
							iMLcncHstry.setYn_2020("N");
						}
						if(!"Y".equals(iMLcncHstry.getYn_2021())) {
							iMLcncHstry.setYn_2021("N");
						}
						if(!"Y".equals(iMLcncHstry.getYn_2022())) {
							iMLcncHstry.setYn_2022("N");
						}

						if(IMStringUtil.isEmpty(iMLcncHstry.getCntneduCnt())) {
							iMLcncHstry.setCntneduCnt("0");
						}else {
							try {
								iMLcncHstry.setCntneduCnt(Long.parseLong(iMLcncHstry.getCntneduCnt())+"");
							}catch (NumberFormatException e) {
								iMLcncHstry.setCntneduCnt("0");
							}
						}
						try {
							iMLcncHstry.setLcncNo(Long.parseLong(iMLcncHstry.getLcncNo())+"");
							if(lcncHstryService.insertLcncHstry(iMLcncHstry)>0) {
								success++;
								iMLcncHstry.setExcelResult("OK");
							}else {
								iMLcncHstry.setExcelResult("FAIL");
							}
						}catch (NumberFormatException e) {
							iMLcncHstry.setExcelResult("연번은 숫자만 등록해야합니다.");
						}
						
					}catch (Exception e) {
						iMLcncHstry.setExcelResult("필수값 확인 바랍니다.");
						e.printStackTrace();
					}
					result++;
				}
				
				
				
				req.getSession().setAttribute(IMGlobals.EXEXL_SESSION_KEY, iMLcncHstrys);
				
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
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/lcncHstry/registExcel/sample.do")
	public ModelAndView registExcelSample(HttpServletRequest req, HttpServletResponse res) throws Exception {	
		ModelAndView mav = new ModelAndView();
		
		mav.addObject(ExcelDownloadView.TEMPLATE_FILE_NAME, "lcncHstryRegistExcel");
		
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
	@RequestMapping(value = "/mng/lcncHstry/registExcelResult.do")
	public ModelAndView registExcelResult(HttpServletRequest req, HttpServletResponse res) throws Exception {	
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("list", req.getSession().getAttribute(IMGlobals.EXEXL_SESSION_KEY));
		
		mav.addObject(ExcelDownloadView.TEMPLATE_FILE_NAME, "lcncHstryRegistExcelResult");
		
		mav.setViewName("excelView");
		mav.addObject(ExcelDownloadView.DOWNLOAD_FILE_NAME, ExcelDownloadView.getDownloadName(req));

		return mav;
	}
	
}