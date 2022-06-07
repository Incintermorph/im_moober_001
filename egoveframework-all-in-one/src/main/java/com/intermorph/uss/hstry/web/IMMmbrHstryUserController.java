/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.hstry.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.intermorph.cmmn.IMGlobals;
import com.intermorph.cmmn.base.BaseController;
import com.intermorph.cmmn.egov.service.IMLoginVO;
import com.intermorph.cmmn.exception.IMProcErrors;
import com.intermorph.cmmn.exception.IMProcException;
import com.intermorph.cmmn.util.IMStringUtil;
import com.intermorph.crs.aplcnt.service.IMCrsAplcntCondition;
import com.intermorph.crs.aplcnt.service.IMCrsAplcntService;
import com.intermorph.uss.hstry.service.IMMmbrEduService;
import com.intermorph.uss.hstry.service.IMMmbrEtcService;
import com.intermorph.uss.hstry.service.IMMmbrExpService;
import com.intermorph.uss.hstry.service.IMMmbrExptSbjService;
import com.intermorph.uss.hstry.service.IMMmbrHstryResultSet;
import com.intermorph.uss.hstry.service.IMMmbrHstryService;
import com.intermorph.uss.hstry.service.IMMmbrHstryVO;
import com.intermorph.uss.hstry.service.IMMmbrWorkHstryService;
import com.intermorph.uss.qlfc.service.IMMmbrQlfcService;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.umt.service.EgovMberManageService;
import egovframework.com.uss.umt.service.MberManageVO;
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
public class IMMmbrHstryUserController extends BaseController {

	@Resource (name = "IMMmbrHstryService")
	private IMMmbrHstryService mmbrHstryService;

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

	@Resource (name = "propertiesService")
	protected EgovPropertyService propertyService;

	/** mberManageService */
	@Resource (name = "mberManageService")
	private EgovMberManageService mberManageService;

	@Resource (name = "IMMmbrQlfcService")
	private IMMmbrQlfcService mmbrQlfcService;

	@Resource (name = "IMCrsAplcntService")
	private IMCrsAplcntService crsAplcntService;

	// checkplus 파라미터 치환 함수
	private String CHECKPLUS__requestReplace(String paramValue, String gubun) {
		String result = "";

		if (paramValue != null) {
			paramValue = paramValue.replaceAll("<", "&lt;").replaceAll(">", "&gt;");

			paramValue = paramValue.replaceAll("\\*", "");
			paramValue = paramValue.replaceAll("\\?", "");
			paramValue = paramValue.replaceAll("\\[", "");
			paramValue = paramValue.replaceAll("\\{", "");
			paramValue = paramValue.replaceAll("\\(", "");
			paramValue = paramValue.replaceAll("\\)", "");
			paramValue = paramValue.replaceAll("\\^", "");
			paramValue = paramValue.replaceAll("\\$", "");
			paramValue = paramValue.replaceAll("'", "");
			paramValue = paramValue.replaceAll("@", "");
			paramValue = paramValue.replaceAll("%", "");
			paramValue = paramValue.replaceAll(";", "");
			paramValue = paramValue.replaceAll(":", "");
			paramValue = paramValue.replaceAll("-", "");
			paramValue = paramValue.replaceAll("#", "");
			paramValue = paramValue.replaceAll("--", "");
			paramValue = paramValue.replaceAll("-", "");
			paramValue = paramValue.replaceAll(",", "");

			if (gubun != "encodeData") {
				paramValue = paramValue.replaceAll("\\+", "");
				paramValue = paramValue.replaceAll("/", "");
				paramValue = paramValue.replaceAll("=", "");
			}

			result = paramValue;
		}
		return result;
	}

	@RequestMapping (value = "/cmmn/mmbrHstry/checkplus/success.do")
	public ModelAndView checkplusSuccess(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();

		req.setCharacterEncoding("UTF-8");

		String sEncodeData = CHECKPLUS__requestReplace(req.getParameter("EncodeData"), "encodeData");
		String sReserved1 = CHECKPLUS__requestReplace(req.getParameter("param_r1"), "");
		String sReserved2 = CHECKPLUS__requestReplace(req.getParameter("param_r2"), "");
		String sReserved3 = CHECKPLUS__requestReplace(req.getParameter("param_r3"), "");

		String sMessage = "";
		String errorType = "00";
		if (IMStringUtil.isEmpty(sEncodeData)) {
			errorType = "01";
			sMessage = "인증실패";
			// 에러 페이지 연결
		} else {

			NiceID.Check.CPClient niceCheck = new NiceID.Check.CPClient();

			// LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

			String sCipherTime = ""; // 복호화한 시간
			String sRequestNumber = IMGlobals.NICE_REQ + "_"; // 요청 번호
			String sResponseNumber = ""; // 인증 고유번호
			String sAuthType = ""; // 인증 수단
			String sName = ""; // 성명
			String sDupInfo = ""; // 중복가입 확인값 (DI_64 byte)
			String sConnInfo = ""; // 연계정보 확인값 (CI_88 byte)
			String sBirthDate = ""; // 생일
			String sGender = ""; // 성별
			String sNationalInfo = ""; // 내/외국인정보 (개발가이드 참조)
			String sPlainData = "";
			String sMobileNumber = ""; // 휴대전화번호
			String sMobileNo = ""; // sMobileNo
			String sMobileCo = ""; // sMobileCo

			int iReturn = niceCheck.fnDecode(IMGlobals.NICE_ID, IMGlobals.NICE_PW, sEncodeData);

			if (iReturn == 0) {
				sPlainData = niceCheck.getPlainData();
				sCipherTime = niceCheck.getCipherDateTime();

				// 데이타를 추출합니다.
				@SuppressWarnings ("rawtypes")
				java.util.HashMap mapresult = niceCheck.fnParse(sPlainData);

				sRequestNumber = (String)mapresult.get("REQ_SEQ");
				sResponseNumber = (String)mapresult.get("RES_SEQ");
				sAuthType = (String)mapresult.get("AUTH_TYPE");
				sName = (String)mapresult.get("NAME");
				// sName = (String)mapresult.get("UTF8_NAME"); //charset utf8 사용시 주석 해제 후 사용
				sBirthDate = (String)mapresult.get("BIRTHDATE");
				sGender = (String)mapresult.get("GENDER");
				sNationalInfo = (String)mapresult.get("NATIONALINFO");
				sDupInfo = (String)mapresult.get("DI");
				sConnInfo = (String)mapresult.get("CI");
				sMobileNo = (String)mapresult.get("MOBILE_NO");
				sMobileCo = (String)mapresult.get("MOBILE_CO");

			//	String session_sRequestNumber = (String)req.getSession().getAttribute("REQ_SEQ");
				
					IMMmbrHstryVO iMMmbrHstry = new IMMmbrHstryVO();
					iMMmbrHstry.setMblTelno(sMobileNo);
					iMMmbrHstry.setDi(sDupInfo);
					iMMmbrHstry.setCi(sConnInfo);
					iMMmbrHstry.setMmbrNm(sName);
					iMMmbrHstry.setBrdt(sBirthDate);

					mav.addObject("iMMmbrHstry", iMMmbrHstry);

					req.getSession().setAttribute("memberDi", sDupInfo);
					req.getSession().setAttribute("memberCi", sConnInfo);

				

			}
		}

		mav.addObject("errorType", errorType);
		mav.addObject("sMessage", sMessage);

		mav.setViewName("/view/user/uss/mmbrHstry/modifyMmbrHstryCheckplus");
		return mav;
	}

	@RequestMapping (value = "/cmmn/mmbrHstry/checkplus/fail.do")
	public ModelAndView checkplusFail(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();

		mav.addObject("sMessage", "인증실패");
		mav.addObject("errorType", "99");
		mav.setViewName("/view/user/uss/mmbrHstry/modifyMmbrHstryCheckplus");
		return mav;
	}

	/**
	 * 실명 인증 세팅
	 * 
	 * @param req
	 * @param res
	 * @throws Exception
	 */
	public void setCheckplus(HttpServletRequest req, HttpServletResponse res) throws Exception {

		NiceID.Check.CPClient niceCheck = new NiceID.Check.CPClient();

		String sSiteCode = IMGlobals.NICE_ID; // NICE로부터 부여받은 사이트 코드
		String sSitePassword = IMGlobals.NICE_PW; // NICE로부터 부여받은 사이트 패스워드

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String sRequestNumber = com.intermorph.cmmn.IMGlobals.NICE_REQ + "_" + user.getUniqId(); // 요청 번호, 이는 성공/실패후에 같은 값으로 되돌려주게 되므로
		// 업체에서 적절하게 변경하여 쓰거나, 아래와 같이 생성한다.

		// sRequestNumber = niceCheck.getRequestNO(sSiteCode);
		req.getSession().setAttribute("REQ_SEQ", sRequestNumber); // 해킹등의 방지를 위하여 세션을 쓴다면, 세션에 요청번호를 넣는다.

		String sAuthType = ""; // 없으면 기본 선택화면, M(휴대폰), X(인증서공통), U(공동인증서), F(금융인증서), S(PASS인증서), C(신용카드)
		String customize = ""; // 없으면 기본 웹페이지 / Mobile : 모바일페이지

		// CheckPlus(본인인증) 처리 후, 결과 데이타를 리턴 받기위해 다음예제와 같이 http부터 입력합니다.
		// 리턴url은 인증 전 인증페이지를 호출하기 전 url과 동일해야 합니다. ex) 인증 전 url : http://www.~ 리턴 url : http://www.~

		String sReturnUrl = IMGlobals.SITE_DOMAIN + req.getContextPath() + "/cmmn/mmbrHstry/checkplus/success.do"; // 성공시 이동될 URL

		String sErrorUrl = IMGlobals.SITE_DOMAIN + req.getContextPath() + "/cmmn/mmbrHstry/checkplus/fail.do"; // 실패시 이동될 URL
	//	System.out.println("sReturnUrl : " + sReturnUrl);
	//	System.out.println("sErrorUrl : " + sErrorUrl);
		// 입력될 plain 데이타를 만든다.
		String sPlainData = "7:REQ_SEQ" + sRequestNumber.getBytes().length + ":" + sRequestNumber + "8:SITECODE" + sSiteCode.getBytes().length + ":" + sSiteCode
				+ "9:AUTH_TYPE" + sAuthType.getBytes().length + ":" + sAuthType + "7:RTN_URL" + sReturnUrl.getBytes().length + ":" + sReturnUrl + "7:ERR_URL"
				+ sErrorUrl.getBytes().length + ":" + sErrorUrl + "9:CUSTOMIZE" + customize.getBytes().length + ":" + customize;

		String sMessage = "";
		String sEncData = "";

		int iReturn = niceCheck.fnEncode(sSiteCode, sSitePassword, sPlainData);
		if (iReturn == 0) {
			sEncData = niceCheck.getCipherData();
		} else if (iReturn == -1) {
			sMessage = "암호화 시스템 에러입니다.";
		} else if (iReturn == -2) {
			sMessage = "암호화 처리오류입니다.";
		} else if (iReturn == -3) {
			sMessage = "암호화 데이터 오류입니다.";
		} else if (iReturn == -9) {
			sMessage = "입력 데이터 오류입니다.";
		} else {
			sMessage = "알수 없는 에러 입니다. iReturn : " + iReturn;
		}

		req.setAttribute("sEncData", sEncData);
		req.setAttribute("sMessage", sMessage);

	}

	/**
	 * 회원이력 수정 화면
	 * 
	 * @param req
	 * @param res
	 * @param iMMmbrHstry
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping (value = "/user/mmbrHstry/modify.do")
	public ModelAndView modify(HttpServletRequest req, HttpServletResponse res, IMMmbrHstryVO iMMmbrHstry) throws Exception {
		ModelAndView mav = new ModelAndView();
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		iMMmbrHstry.setEsntlId(user.getUniqId());
		IMMmbrHstryResultSet detail = (IMMmbrHstryResultSet)mmbrHstryService.selectDetailMmbrHstry(iMMmbrHstry);

		String saveMode = "U";

		if (detail != null && detail.getMmbrHstry() != null && detail.getMmbrHstry().getMemberSrl() != null) {

			if (!"Y".equals(detail.getMmbrHstry().getTrnsfYn())) {
				loginCheckSetAudit(req, detail.getMmbrHstry());
				// 마이그레이션 데이터
				mmbrQlfcService.updateMig(detail.getMmbrHstry());
			}

			mav.addObject("selectExptSbj", mmbrExptSbjService.selectListMmbrExptSbj(detail.getMmbrHstry().getMemberSrl()));
			mav.addObject("selectExptSbjOnline", mmbrExptSbjService.selectListMmbrExptSbjOnline(detail.getMmbrHstry().getMemberSrl()));
			mav.addObject("selectWorkHstry", mmbrWorkHstryService.selectListMmbrWorkHstry(detail.getMmbrHstry().getMemberSrl()));
			mav.addObject("selectWorkHstryDiffSum", mmbrWorkHstryService.selectListDiffSum(detail.getMmbrHstry().getMemberSrl()));

			mav.addObject("selectExp", mmbrExpService.selectListMmbrExp(detail.getMmbrHstry().getMemberSrl()));
			mav.addObject("selectEdu", mmbrEduService.selectListMmbrEdu(detail.getMmbrHstry().getMemberSrl()));
			mav.addObject("selectEtc", mmbrEtcService.selectListMmbrEtc(detail.getMmbrHstry().getMemberSrl()));
			mav.addObject("selectEduHis01", mmbrHstryService.selectEduHisList(detail.getMmbrHstry().getMemberSrl(), "4002"));// 수료 이력만

			mav.addObject("listMmbrQlfc", mmbrQlfcService.selectListUserMmbrQlfcPass(user.getUniqId()));

			IMCrsAplcntCondition crscondition = new IMCrsAplcntCondition();
			crscondition.setScMmbrEsntlId(user.getUniqId());
			crscondition.setScAplyStts("02");
			crscondition.setScSttsCdvFNSH("02");
			mav.addObject("selectEduHis02", crsAplcntService.selectListCrsAplcnt(crscondition).getList());

		} else {

			MberManageVO mberManageDB = mberManageService.selectMber(user.getUniqId());
			iMMmbrHstry.setMemberSrl(Long.parseLong(mberManageDB.getIhidnum()));
			iMMmbrHstry.setEsntlId(user.getUniqId());
			detail = new IMMmbrHstryResultSet();
			detail.setMmbrHstry(iMMmbrHstry);
			detail.getMmbrHstry().setMblTelno(iMMmbrHstry.getMblTelno());
			iMMmbrHstry.setEml(mberManageDB.getMberEmailAdres());
			if (!IMStringUtil.isEmpty(iMMmbrHstry.getDi())) {

				String session_memberDi = (String)req.getSession().getAttribute("memberDi");
				if (!session_memberDi.equals(iMMmbrHstry.getDi())) {
					throw new IMProcException(IMProcErrors.필수값없음);
				}
				// 회원과 이름이 다르면 에러 처리
				if (!mberManageDB.getMberNm().equals(iMMmbrHstry.getMmbrNm())) {
					throw new IMProcException(IMProcErrors.필수값없음);
				}
			} else {

				setCheckplus(req, res);

				iMMmbrHstry.setMmbrNm(mberManageDB.getMberNm());
				req.getSession().setAttribute("memberDi", null);
				req.getSession().setAttribute("memberCi", null);

			}

			saveMode = "I";
		}

		mav.addObject("detail", detail);
		mav.addObject("saveMode", saveMode);

		mav.setViewName("/view/user/uss/mmbrHstry/modifyMmbrHstry");

		return mav;
	}

	/**
	 * 회원이력 등록 저장
	 * 
	 * @param req
	 * @param res
	 * @param iMmmbrHstry
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping (value = "/user/mmbrHstry/insert.do")
	public ModelAndView insert(HttpServletRequest req, HttpServletResponse res, @ModelAttribute ("iMMmbrHstry") IMMmbrHstryVO iMMmbrHstry,
			BindingResult bindingResult, ModelMap model) throws Exception {

		ModelAndView mav = new ModelAndView();
		loginCheckSetAudit(req, iMMmbrHstry);

		mav.addObject("result", mmbrHstryService.insertMmbrHstry(iMMmbrHstry));

		IMLoginVO user = (IMLoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		user.setAplyGrdCdv(iMMmbrHstry.getAplyGrdCdv());
		user.setDi(iMMmbrHstry.getDi());
		req.getSession().setAttribute("loginVO", user);

		req.getSession().setAttribute("memberDi", null);
		req.getSession().setAttribute("memberCi", null);
		mav.setViewName("jsonView");
		return mav;
	}

	/**
	 * 회원이력 수정 저장
	 * 
	 * @param req
	 * @param res
	 * @param iMmmbrHstry
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping (value = "/user/mmbrHstry/update.do")
	public ModelAndView update(HttpServletRequest req, HttpServletResponse res, @ModelAttribute ("iMMmbrHstry") IMMmbrHstryVO iMMmbrHstry) throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMMmbrHstry);

		mav.addObject("result", mmbrHstryService.updateMmbrHstry(iMMmbrHstry));
		IMLoginVO user = (IMLoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		user.setAplyGrdCdv(iMMmbrHstry.getAplyGrdCdv());
		req.getSession().setAttribute("loginVO", user);
		mav.setViewName("jsonView");
		return mav;
	}

	/**
	 * 회원이력 수정 저장
	 * 
	 * @param req
	 * @param res
	 * @param iMmmbrHstry
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping (value = "/user/mmbrHstry/updateLastMdfcn.do")
	public ModelAndView updateLastMdfcn(HttpServletRequest req, HttpServletResponse res, @ModelAttribute ("iMMmbrHstry") IMMmbrHstryVO iMMmbrHstry)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMMmbrHstry);

		mav.addObject("result", mmbrHstryService.updateMmbrHstry(iMMmbrHstry));

		mav.addObject("detail", mmbrHstryService.selectDetailMmbrHstry(iMMmbrHstry));
		mav.setViewName("jsonView");
		return mav;
	}

}