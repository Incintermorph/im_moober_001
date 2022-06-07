/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.wtst.aplcnt.web;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.intermorph.cmmn.IMGlobals;
import com.intermorph.cmmn.base.BaseController;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;
import com.intermorph.cmmn.exception.IMProcErrors;
import com.intermorph.cmmn.exception.IMProcException;
import com.intermorph.cmmn.exception.IMWtstAplcntStts;
import com.intermorph.cmmn.service.IMCmmnDescService;
import com.intermorph.cmmn.service.IMCmmnDtService;
import com.intermorph.cmmn.service.IMCmmnDtVO;
import com.intermorph.cmmn.service.IMCmmnSttsCondition;
import com.intermorph.cmmn.service.IMCmmnSttsService;
import com.intermorph.cmmn.service.IMCmmnSttsVO;
import com.intermorph.cmmn.service.IMInfoInqHstryService;
import com.intermorph.cmmn.util.ExcelDownloadView;
import com.intermorph.cmmn.util.IMDateUtil;
import com.intermorph.cmmn.util.IMExcelPaser;
import com.intermorph.cmmn.util.IMStringUtil;
import com.intermorph.crs.agncy.service.IMAgncyCondition;
import com.intermorph.crs.agncy.service.IMAgncyService;
import com.intermorph.uss.qlfc.service.IMLcncHstryVO;
import com.intermorph.uss.qlfc.service.IMMmbrQlfcService;
import com.intermorph.wtst.aplcnt.service.IMWtstAplcntCondition;
import com.intermorph.wtst.aplcnt.service.IMWtstAplcntResultDTO;
import com.intermorph.wtst.aplcnt.service.IMWtstAplcntResultSet;
import com.intermorph.wtst.aplcnt.service.IMWtstAplcntService;
import com.intermorph.wtst.aplcnt.service.IMWtstAplcntVO;
import com.intermorph.wtst.place.service.IMWtstPlaceResultSet;
import com.intermorph.wtst.place.service.IMWtstPlaceService;
import com.intermorph.wtst.place.service.IMWtstPlaceVO;
import com.intermorph.wtst.wtst.service.IMWtstVO;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import net.sf.jxls.reader.XLSDataReadException;
import net.sf.jxls.reader.XLSReadStatus;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.wtst.aplcnt.web
 * @File : IMWtstAplcntController.java
 * @Title : 필기시험 신청자
 * @date : 2022.04.07 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Controller
public class IMWtstAplcntMngController extends BaseController {

	@Resource (name = "IMWtstAplcntService")
	private IMWtstAplcntService wtstAplcntService;

	@Resource (name = "propertiesService")
	protected EgovPropertyService propertyService;

	@Resource (name = "IMAgncyService")
	private IMAgncyService agncyService;

	@Resource (name = "IMInfoInqHstryService")
	IMInfoInqHstryService infoInqHstryService;

	@Resource (name = "IMCmmnSttsService")
	private IMCmmnSttsService cmmnSttsService;

	@Resource (name = "IMCmmnDescService")
	private IMCmmnDescService cmmnDescService;

	@Resource (name = "IMWtstPlaceService")
	private IMWtstPlaceService wtstPlaceService;

	@Resource (name = "IMCmmnDtService")
	private IMCmmnDtService cmmnDtService;

	@Resource (name = "IMMmbrQlfcService")
	private IMMmbrQlfcService mmbrQlfcService;

	@Resource (name = "EgovFileMngService")
	private EgovFileMngService fileService;

	/**
	 * 필기시험 신청자 목록
	 * 
	 * @param req
	 * @param res
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping (value = "/mng/wtstAplcnt/{aplyStts}/selectList.do")
	public ModelAndView selectList(HttpServletRequest req, HttpServletResponse res, IMWtstAplcntCondition condition, @PathVariable ("aplyStts") String aplyStts)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		setEmptyValue(condition, "currentPageNo=1", "recordCountPerPage=" + propertyService.getInt("pageUnit"),
				"pageSize=" + propertyService.getInt("pageSize"), "orderbyKey=0", "scEduYear=" + IMGlobals.IM_NOW_YEAR);

		if ("A".equals(aplyStts)) {
			condition.setScNotAplyCancleYn("Y");
			condition.setScAplyStts(null);
			condition.setScExamFnshYn("01");
		} else {
			condition.setScNotAplyCancleYn(null);
			condition.setScAplyStts(aplyStts);
			condition.setScExamFnshYn(null);
		}

		mav.addObject("aplyStts", aplyStts);
		String loginAgncyId = IMStringUtil.getAttribute(req, IMGlobals.IM_LOGIN_USER_AGNCY_KEY);
		if (!"".equals(loginAgncyId)) {
			condition.setScAgncyId(loginAgncyId);
		} else {
			mav.addObject("agncyList", agncyService.selectListAgncy());
		}

		mav.addObject("pageInfo", wtstAplcntService.selectListWtstAplcnt(condition));

		mav.addObject("condition", condition);

		// 개인정보 이력 등록
		Gson gson = new Gson();
		infoInqHstryService.insertInfoInqHstry("im_wtst_aplcnt", "SELECTNOID", IMGlobals.IM_INFOINQ_S, gson.toJson(condition), req);

		mav.setViewName("layout/mng/wtst/aplcnt/selectListWtstAplcnt");

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
	@RequestMapping (value = "/mng/wtstAplcnt/update.do")
	public ModelAndView update(HttpServletRequest req, HttpServletResponse res, @ModelAttribute ("iMWtstAplcnt") IMWtstAplcntVO iMWtstAplcnt,
			BindingResult bindingResult, ModelMap model) throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMWtstAplcnt);

		if (bindingResult.hasErrors()) { // 만일 validation 에러가 있으면...
			mav.addObject("result", -1);
		}

		mav.addObject("result", wtstAplcntService.updateWtstAplcnt(iMWtstAplcnt));

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
	@RequestMapping (value = "/mng/wtstAplcnt/score/update.do")
	public ModelAndView updateScre(HttpServletRequest req, HttpServletResponse res, @ModelAttribute ("iMWtstAplcnt") IMWtstAplcntVO iMWtstAplcnt,
			IMWtstAplcntResultDTO dto, IMCmmnSttsVO cmmnStts) throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMWtstAplcnt);
		Gson gson = new Gson();
		iMWtstAplcnt.setSbjRslt(gson.toJson(dto));

		if (iMWtstAplcnt.getWtstAplcntId() == null || "".equals(iMWtstAplcnt.getWtstAplcntId())) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}

		IMWtstAplcntResultSet detailApply = (IMWtstAplcntResultSet)wtstAplcntService.selectDetailWtstAplcnt(iMWtstAplcnt);

		if (detailApply == null) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}

		iMWtstAplcnt.setMmbrEsntlId(detailApply.getWtstAplcnt().getMmbrEsntlId());
		iMWtstAplcnt.setWtstPlaceId(detailApply.getWtstAplcnt().getWtstPlaceId());
		loginCheckSetAudit(req, iMWtstAplcnt);
		mav.addObject("result", updateScreSave( iMWtstAplcnt, dto, cmmnStts));
		mav.setViewName("jsonView");
		return mav;
	}
	
	/**
	 * 성적 업데이트 처리 
	 * @param iMWtstAplcnt
	 * @param dto
	 * @param cmmnStts
	 * @return
	 * @throws Exception
	 */
	private int updateScreSave(IMWtstAplcntVO iMWtstAplcnt,IMWtstAplcntResultDTO dto, IMCmmnSttsVO cmmnStts) throws Exception {
		
		Gson gson = new Gson();
		iMWtstAplcnt.setSbjRslt(gson.toJson(dto));

		IMWtstPlaceVO iMWtstPlace = new IMWtstPlaceVO();

		iMWtstPlace.setWtstPlaceId(iMWtstAplcnt.getWtstPlaceId());

		IMWtstPlaceResultSet detail = (IMWtstPlaceResultSet)wtstPlaceService.selectDetailWtstPlace(iMWtstPlace);

		HashMap<String, IMCmmnDtVO> dateMap = cmmnDtService.selectListCmmnDtResultMap("IM_WTST", detail.getWtstPlace().getWtstId());

		if ("FNSH".equals(cmmnStts.getRefNm()) && "02".equals(cmmnStts.getSttsCdv())) {
			iMWtstAplcnt.setPassYmd(dateMap.get("passTerm").getBgnDt());
			// 합격일 +2년
			iMWtstAplcnt.setVldEndYmd(IMDateUtil.addDay(iMWtstAplcnt.getPassYmd().substring(0, 8), (365 * 2)) + "235959");
		} else {
			iMWtstAplcnt.setPassYmd(null);
		}
		int result = wtstAplcntService.updateWtstAplcnt(iMWtstAplcnt);
		// 시험 결과 업데이트
		cmmnSttsService.updateCmmnStts("IM_WTST_APLCNT", iMWtstAplcnt.getWtstAplcntId(), cmmnStts.getRefNm(), cmmnStts.getSttsCdv(), null, iMWtstAplcnt);

		if ("FNSH".equals(cmmnStts.getRefNm())) {
			iMWtstAplcnt.setMmbrEsntlId(iMWtstAplcnt.getMmbrEsntlId());

			mmbrQlfcService.updateMmbrQlfcWtst(iMWtstAplcnt, cmmnStts.getSttsCdv(), detail.getWtst().getCrsGrdCdv());
		}
		return result;
	}

	/**
	 * 다중 신청상태 업데이트 처리
	 * 
	 * @param req
	 * @param res
	 * @param iMWtstAplcnt
	 * @param sttsCd
	 * @return
	 * @throws Exception
	 */
	@RequestMapping ("/mng/wtstAplcnt/{sttsCd}/updateAplylist.do")
	public ModelAndView updateAplylist(HttpServletRequest req, HttpServletResponse res, IMWtstAplcntVO iMWtstAplcnt, @PathVariable ("sttsCd") String sttsCd)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMWtstAplcnt);
		int result = 0;
		String sttsKey = null;

		for (IMWtstAplcntStts v : IMWtstAplcntStts.values()) {
			if (v.sttsKey.equals(sttsCd)) {
				sttsKey = v.sttsKey;
			}
		}

		Gson gson = new Gson();
		if (sttsKey != null) {
			for (Long idx : iMWtstAplcnt.getCheckIndexs()) {
				IMWtstAplcntVO o = new IMWtstAplcntVO();
				o.copyAudit(iMWtstAplcnt);
				o.setWtstAplcntId(iMWtstAplcnt.getWtstAplcntIds()[idx.intValue()]);
				result += cmmnSttsService.updateCmmnStts("IM_WTST_APLCNT", iMWtstAplcnt.getWtstAplcntIds()[idx.intValue()], sttsKey,
						iMWtstAplcnt.getChageStts(), iMWtstAplcnt.getChageMsg(), iMWtstAplcnt);

				if ("FNSH".equals(sttsKey)) {
					// 대기나 미응시인경우
					if ("01".equals(iMWtstAplcnt.getChageStts()) || "04".equals(iMWtstAplcnt.getChageStts())) {
						IMWtstAplcntVO vo = new IMWtstAplcntVO();
						vo.setWtstAplcntId(iMWtstAplcnt.getWtstAplcntIds()[idx.intValue()]);
						vo.setSbjRslt(gson.toJson(new IMWtstAplcntResultDTO()));
						vo.copyAudit(iMWtstAplcnt);
						wtstAplcntService.updateWtstAplcnt(vo);

						// 회원 자격정보 업데이트
						IMWtstAplcntResultSet detailApply = (IMWtstAplcntResultSet)wtstAplcntService.selectDetailWtstAplcnt(vo);
						detailApply.getWtstAplcnt().copyAudit(iMWtstAplcnt);
						mmbrQlfcService.updateMmbrQlfcWtst(detailApply.getWtstAplcnt(), iMWtstAplcnt.getChageStts(), detailApply.getWtst().getCrsGrdCdv());
					}

				}

			}
		}

		mav.addObject("result", result);

		mav.setViewName("jsonView");
		return mav;
	}

	/**
	 * 변경 사유
	 * 
	 * @param req
	 * @param res
	 * @param cmmnStts
	 * @return
	 * @throws Exception
	 */
	@RequestMapping (value = "/mng/wtstAplcnt/select/memoLayer.do")
	public ModelAndView memoLayer(HttpServletRequest req, HttpServletResponse res, IMCmmnSttsVO cmmnStts) throws Exception {
		ModelAndView mav = new ModelAndView();

		mav.addObject("cmmnStts", cmmnStts);
		mav.setViewName("layer/mng/wtst/aplcnt/selectListWtstAplcntMemoLayer");

		return mav;
	}

	/**
	 * 공통상태정보 목록
	 * 
	 * @param req
	 * @param res
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping (value = "/mng/wtstAplcnt/cmmnStts/{sttsCdv}/selectListLayer.do")
	public ModelAndView selectList(HttpServletRequest req, HttpServletResponse res, IMCmmnSttsCondition condition, @PathVariable ("sttsCdv") String sttsCdv)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		setEmptyValue(condition, "currentPageNo=1", "recordCountPerPage=" + propertyService.getInt("pageUnit"),
				"pageSize=" + propertyService.getInt("pageSize"), "orderbyKey=0", "scRefNm=" + IMWtstAplcntStts.접수상태.sttsKey);

		condition.setScTblId("IM_WTST_APLCNT");
		// 신청값 체크
		if (condition.getScTblRefId() == null || "".equals(condition.getScTblRefId())) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}

		String sttsKey = null;

		for (IMWtstAplcntStts v : IMWtstAplcntStts.values()) {
			if (v.sttsKey.equals(sttsCdv)) {
				sttsKey = v.sttsKey;
			}
		}
		if (sttsKey == null) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		condition.setScRefNm(sttsKey);

		mav.addObject("pageInfo", cmmnSttsService.selectListHstryCmmnStts(condition));

		mav.addObject("sttsCdv", sttsCdv);
		mav.addObject("condition", condition);

		mav.setViewName("layer/mng/wtst/aplcnt/selectListWtstAplcntCmmnSttsLayer");

		return mav;
	}

	/**
	 * 특이 사항 관리
	 * 
	 * @param req
	 * @param res
	 * @param iMWtstAplcnt
	 * @param sttsCdv
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping (value = "/mng/wtstAplcnt/stts/{sttsCdv}/modify.do")
	public ModelAndView modify(HttpServletRequest req, HttpServletResponse res, IMWtstAplcntVO iMWtstAplcnt, @PathVariable ("sttsCdv") String sttsCdv,
			IMAgncyCondition condition) throws Exception {
		ModelAndView mav = new ModelAndView();

		String sttsKey = null;
		for (IMWtstAplcntStts v : IMWtstAplcntStts.values()) {
			if (v.sttsKey.equals(sttsCdv)) {
				sttsKey = v.sttsKey;
			}
		}

		mav.addObject("sttsCdv", sttsCdv);

		mav.addObject("detail", wtstAplcntService.selectDetailWtstAplcnt(iMWtstAplcnt));
		HashMap<String, IMCmmnSttsVO> cmmnSttsMap = cmmnSttsService.selectListCmmnSttsResultObjectMap("IM_WTST_APLCNT", iMWtstAplcnt.getWtstAplcntId());
		mav.addObject("detailStts", cmmnSttsMap.get(sttsKey));

		mav.setViewName("layer/mng/wtst/aplcnt/modifyWtstAplcntSttsLayer");

		return mav;
	}

	/**
	 * 신청상태 비고 업데이트 처리
	 * 
	 * @param req
	 * @param res
	 * @param iMCrsAplcnt
	 * @param cmmnSttsVO
	 * @param sttsCdv
	 * @return
	 * @throws Exception
	 */
	@RequestMapping (value = "/mng/wtstAplcnt/stts/{sttsCdv}/update.do")
	public ModelAndView updateAplyStts(HttpServletRequest req, HttpServletResponse res, IMWtstAplcntVO iMWtstAplcnt, IMCmmnSttsVO cmmnSttsVO,
			@PathVariable ("sttsCdv") String sttsCdv) throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMWtstAplcnt, cmmnSttsVO);
		int result = 0;
		String sttsKey = null;

		for (IMWtstAplcntStts v : IMWtstAplcntStts.values()) {
			if (v.sttsKey.equals(sttsCdv)) {
				sttsKey = v.sttsKey;
			}
		}
		if (sttsKey != null) {
			result = cmmnSttsService.updateCmmnStts("IM_WTST_APLCNT", iMWtstAplcnt.getWtstAplcntId(), sttsKey, cmmnSttsVO.getSttsCdv(),
					cmmnSttsVO.getSttsRmks(), cmmnSttsVO);

		}

		mav.addObject("result", result);

		mav.setViewName("jsonView");
		return mav;
	}

	/**
	 * 수업번호 업데이트
	 * 
	 * @param req
	 * @param res
	 * @param iMWtstAplcnt
	 * @return
	 * @throws Exception
	 */
	@RequestMapping ("/mng/wtstAplcnt/updateNumberAplylist.do")
	public ModelAndView updateNumberAplylist(HttpServletRequest req, HttpServletResponse res, IMWtstAplcntVO iMWtstAplcnt) throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMWtstAplcnt);
		IMWtstAplcntCondition condition = new IMWtstAplcntCondition();
		condition.setScAplyStts("02");
		condition.setScDpstStts("Y");
		condition.setScWtstPlaceId(iMWtstAplcnt.getWtstPlaceId());
		// 대상 조회
		BasePage<BaseResultSet> page = wtstAplcntService.selectListWtstAplcnt(condition);

		int result = 0;
		if (page != null && page.getList() != null && page.getList().size() > 0) {
			for (BaseResultSet baseResult : page.getList()) {
				IMWtstAplcntResultSet rs = (IMWtstAplcntResultSet)baseResult;

				if (rs.getWtstAplcnt().getTktstno() == null || "".equals(rs.getWtstAplcnt().getTktstno())) {
					// 접수 번호 업데이트
					rs.getWtstAplcnt().copyAudit(iMWtstAplcnt);
					result += wtstAplcntService.updateTktstno(rs.getWtstAplcnt());
				}
			}

		}

		mav.addObject("result", result);

		mav.setViewName("jsonView");
		return mav;
	}

	/**
	 * 수험표
	 * 
	 * @param req
	 * @param res
	 * @param iMWtstAplcnt
	 * @return
	 * @throws Exception
	 */
	@RequestMapping ("/mng/wtstAplcnt/selectDetailExamPrint.do")
	public ModelAndView selectDetailPrintExam(HttpServletRequest req, HttpServletResponse res, IMWtstAplcntVO iMWtstAplcnt) throws Exception {
		ModelAndView mav = new ModelAndView();

		if (iMWtstAplcnt.getWtstAplcntId() == null || "".equals(iMWtstAplcnt.getWtstAplcntId())) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}

		IMWtstAplcntResultSet detailApply = (IMWtstAplcntResultSet)wtstAplcntService.selectDetailWtstAplcnt(iMWtstAplcnt);

		if (detailApply == null) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}

		IMWtstPlaceVO iMWtstPlace = new IMWtstPlaceVO();

		iMWtstPlace.setWtstPlaceId(detailApply.getWtstAplcnt().getWtstPlaceId());

		IMWtstPlaceResultSet detail = (IMWtstPlaceResultSet)wtstPlaceService.selectDetailWtstPlace(iMWtstPlace);

		mav.addObject("detailApply", detailApply);
		mav.addObject("aplcnt", detailApply.getWtstAplcnt());
		mav.addObject("detail", detail);

		mav.addObject("cmmmDescMap", cmmnDescService.selectListCmmnDescResultMap("IM_WTST", detail.getWtstPlace().getWtstId()));
		HashMap<String, IMCmmnSttsVO> sttsMapObjet = cmmnSttsService.selectListCmmnSttsResultObjectMap("IM_WTST_APLCNT", iMWtstAplcnt.getWtstAplcntId());

		mav.addObject("cmmmDtStts", cmmnSttsService.selectListCmmnSttsResultMap("IM_WTST_APLCNT", iMWtstAplcnt.getWtstAplcntId()));
		String aplyStatusCode = sttsMapObjet.get(IMWtstAplcntStts.접수상태.sttsKey).getSttsCdv();
		String aplyDEPTCode = sttsMapObjet.get(IMWtstAplcntStts.입금확인여부.sttsKey).getSttsCdv();

		if ("02".equals(aplyStatusCode) && "Y".equals(aplyDEPTCode) && detailApply.getWtstAplcnt().getTktstno() != null
				&& !"".equals(detailApply.getWtstAplcnt().getTktstno())) {
			mav.setViewName("/view/user/uss/mypage/selectDetailExamPrint");
		} else {
			throw new IMProcException(IMProcErrors.필수값없음);
		}

		return mav;
	}

	/**
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping (value = "/mng/wtstAplcnt/bulkMarkingExcelLayer.do")
	public ModelAndView bulkMarkingExcelLayer(HttpServletRequest req, HttpServletResponse res, IMWtstVO iMWtst) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("iMWtst", iMWtst);
		mav.setViewName("layer/mng/wtst/aplcnt/bulkMarkingExcelLayer");
		return mav;
	}

	/**
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping (value = "/mng/wtstAplcnt/bulkMarkingExcel/sample.do")
	public ModelAndView registExcelSample(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();

		mav.addObject(ExcelDownloadView.TEMPLATE_FILE_NAME, "wtstBulkMarkingExcel");

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
	@RequestMapping (value = "/mng/wtstAplcnt/bulkMarking/update.do")
	public ModelAndView insertExcel(HttpServletRequest req, HttpServletResponse res, FileVO fileVO, IMWtstVO iMWtst) throws Exception {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("jsonView");
		if (fileVO.getAtchFileId() == null || "".equals(fileVO.getAtchFileId())) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}

		fileVO.setFileSn("0");

		FileVO fvo = fileService.selectFileInf(fileVO);

		if (fvo == null) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		String filePath = fvo.getFileStreCours() + File.separator + fvo.getStreFileNm();

		File saveFile = new File(filePath);
		List<IMWtstAplcntVO> iMWtstAplcnts = new ArrayList<IMWtstAplcntVO>();
		Map<String, Object> rowDataes = new HashMap<String, Object>();
		rowDataes.put("iMWtstAplcnts", iMWtstAplcnts);
		int result = 0;
		int success = 0;

		try {
			IMExcelPaser excelParse = new IMExcelPaser();
			excelParse.setUseDefaultValuesForPrimitiveTypes(true);
			XLSReadStatus readStatus = excelParse.parse(rowDataes, saveFile, "iMWtstAplcntTemplate-jxls-config");
			if (readStatus.isStatusOK()) {
				boolean scoreCheck = false;
				Double totalScore;
				Long fltpSbjCnt;

				Double avgScore;
				for (IMWtstAplcntVO iMWtstAplcnt : iMWtstAplcnts) {
					loginCheckSetAudit(req, iMWtstAplcnt);
					IMWtstAplcntResultDTO resultDto = new IMWtstAplcntResultDTO();
					IMCmmnSttsVO cmmnStts = new IMCmmnSttsVO();
					cmmnStts.setRefNm("FNSH");
					
					try {
						// 필수값 체크
						if (!IMStringUtil.isEmpty(iMWtstAplcnt.getTktstno()) && !IMStringUtil.isEmpty(iMWtstAplcnt.getMberNm())
								&& !IMStringUtil.isEmpty(iMWtstAplcnt.getBrdt())) {
							try {
								resultDto.setScore1(Long.parseLong(iMWtstAplcnt.getStrScore1()));
								resultDto.setScore2(Long.parseLong(iMWtstAplcnt.getStrScore2()));
								resultDto.setScore3(Long.parseLong(iMWtstAplcnt.getStrScore3()));
								resultDto.setScore4(Long.parseLong(iMWtstAplcnt.getStrScore4()));
								resultDto.setScore5(Long.parseLong(iMWtstAplcnt.getStrScore5()));
								scoreCheck = false;
								totalScore = 0D;
								fltpSbjCnt = 0L;
								if (resultDto.getScore1() < 0L || resultDto.getScore1() > 100L) {
									scoreCheck = true;									
								}
								if (resultDto.getScore2() < 0L || resultDto.getScore2() > 100L) {
									scoreCheck = true;

								}
								if (resultDto.getScore3() < 0L || resultDto.getScore3() > 100L) {
									scoreCheck = true;

								}
								if (resultDto.getScore4() < 0L || resultDto.getScore4() > 100L) {
									scoreCheck = true;

								}
								if (resultDto.getScore5() < 0L || resultDto.getScore5() > 100L) {
									scoreCheck = true;
								}
								if (scoreCheck) {
									iMWtstAplcnt.setExcelResult("점수는 숫자만 등록해야합니다.(0~100)");
								} else {

									totalScore = Double.parseDouble(iMWtstAplcnt.getStrScore1()) + Double.parseDouble(iMWtstAplcnt.getStrScore2())
											+ Double.parseDouble(iMWtstAplcnt.getStrScore3())+ Double.parseDouble(iMWtstAplcnt.getStrScore4())+ Double.parseDouble(iMWtstAplcnt.getStrScore5());
									avgScore = totalScore/5;
									
									if(resultDto.getScore1()<40) {
										fltpSbjCnt++;
									}
									if(resultDto.getScore2()<40) {
										fltpSbjCnt++;
									}
									if(resultDto.getScore3()<40) {
										fltpSbjCnt++;
									}
									if(resultDto.getScore4()<40) {
										fltpSbjCnt++;
									}
									if(resultDto.getScore5()<40) {
										fltpSbjCnt++;
									}
									resultDto.setFltpSbjCnt(fltpSbjCnt);
									resultDto.setTotalExamCnt(5L);
									resultDto.setAvgScr(Math.round(avgScore*10)/10D+"");
									if(avgScore>60D && fltpSbjCnt < 1L) {
										cmmnStts.setSttsCdv("02");
									}else {
										cmmnStts.setSttsCdv("03");
									}
									
								}
							} catch (NumberFormatException e) {
								iMWtstAplcnt.setExcelResult("점수는 숫자만 등록해야합니다.(0~100)");

							}

						} else {
							iMWtstAplcnt.setExcelResult("필수값 확인 바랍니다.");
						}

						if (IMStringUtil.isEmpty(iMWtstAplcnt.getExcelResult())) {
							// 성적 업데이트 처리
							IMWtstAplcntVO chkVO = wtstAplcntService.selectOneWtstExcel(iMWtstAplcnt);
							chkVO.copyAudit(iMWtstAplcnt);

							if (chkVO == null) {
								iMWtstAplcnt.setExcelResult("필수값에 해당 하는 정보가 없습니다.필수값 확인 바랍니다.");
							} else {
								if (!chkVO.getWtstId().equals(iMWtst.getWtstId())) {
									iMWtstAplcnt.setExcelResult("해당 차수의 응시정보가 아닙니다.");
								}
								// 성적 업데이트 처리
								if (IMStringUtil.isEmpty(iMWtstAplcnt.getExcelResult())) {
									chkVO.setAvgScr(resultDto.getAvgScr());
									chkVO.setFltpSbjCnt(resultDto.getFltpSbjCnt());
									if(updateScreSave(chkVO, resultDto, cmmnStts)>0) {
										success++;
									}
									
								}

								
							}
						}

					} catch (Exception e) {
						iMWtstAplcnt.setExcelResult("필수값 확인 바랍니다.");
						e.printStackTrace();
					}
					result++;
				}

				req.getSession().setAttribute(IMGlobals.EXEXL_SESSION_KEY, iMWtstAplcnts);

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
	@RequestMapping (value = "/mng/wtstAplcnt/bulkMarkingExcel/result.do")
	public ModelAndView registExcelResult(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();

		mav.addObject("list", req.getSession().getAttribute(IMGlobals.EXEXL_SESSION_KEY));

		mav.addObject(ExcelDownloadView.TEMPLATE_FILE_NAME, "wtstBulkMarkingExcelResult");

		mav.setViewName("excelView");
		mav.addObject(ExcelDownloadView.DOWNLOAD_FILE_NAME, ExcelDownloadView.getDownloadName(req));

		return mav;
	}

}