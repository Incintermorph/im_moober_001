/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.mypage.web;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.intermorph.cmmn.base.BaseController;
import com.intermorph.cmmn.base.BaseResultSet;
import com.intermorph.cmmn.exception.IMProcErrors;
import com.intermorph.cmmn.exception.IMProcException;
import com.intermorph.cmmn.exception.IMWtstAplcntStts;
import com.intermorph.cmmn.service.IMCmmnDescService;
import com.intermorph.cmmn.service.IMCmmnDtService;
import com.intermorph.cmmn.service.IMCmmnSttsService;
import com.intermorph.cmmn.service.IMCmmnSttsVO;
import com.intermorph.cmmn.service.IMLgnSttsService;
import com.intermorph.cmmn.service.IMLgnSttsVO;
import com.intermorph.crs.agncy.service.IMAgncyResultSet;
import com.intermorph.crs.agncy.service.IMAgncyService;
import com.intermorph.crs.agncy.service.IMAgncyVO;
import com.intermorph.crs.aplcnt.service.IMCrsAplcntCondition;
import com.intermorph.crs.aplcnt.service.IMCrsAplcntResultSet;
import com.intermorph.crs.aplcnt.service.IMCrsAplcntService;
import com.intermorph.crs.aplcnt.service.IMCrsAplcntVO;
import com.intermorph.crs.crs.service.IMCrsResultSet;
import com.intermorph.crs.crs.service.IMCrsService;
import com.intermorph.crs.crs.service.IMCrsVO;
import com.intermorph.uss.hstry.service.IMMmbrHstryResultSet;
import com.intermorph.uss.hstry.service.IMMmbrHstryService;
import com.intermorph.uss.hstry.service.IMMmbrHstryVO;
import com.intermorph.uss.issu.service.IMIssuAplyResultSet;
import com.intermorph.uss.issu.service.IMIssuAplyService;
import com.intermorph.uss.issu.service.IMIssuAplyVO;
import com.intermorph.uss.pstpnd.service.IMPstpndAplyResultSet;
import com.intermorph.uss.pstpnd.service.IMPstpndAplyService;
import com.intermorph.uss.pstpnd.service.IMPstpndAplyVO;
import com.intermorph.uss.qlfc.service.IMMmbrQlfcService;
import com.intermorph.uss.qlfc.service.IMMmbrQlfcVO;
import com.intermorph.wtst.aplcnt.service.IMWtstAplcntCondition;
import com.intermorph.wtst.aplcnt.service.IMWtstAplcntResultSet;
import com.intermorph.wtst.aplcnt.service.IMWtstAplcntService;
import com.intermorph.wtst.aplcnt.service.IMWtstAplcntVO;
import com.intermorph.wtst.place.service.IMWtstPlaceResultSet;
import com.intermorph.wtst.place.service.IMWtstPlaceService;
import com.intermorph.wtst.place.service.IMWtstPlaceVO;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.mypage.web
 * @File    : IMUssMypageUserController.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 3. 3
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
@Controller
public class IMUssMypageUserController extends BaseController {

    @Resource (name = "IMCrsAplcntService")
	private IMCrsAplcntService crsAplcntService;
    
    @Resource (name = "IMWtstAplcntService")
    private IMWtstAplcntService wtstAplcntService;
    

	@Resource(name = "IMCmmnDescService")
	private IMCmmnDescService cmmnDescService;
	

	@Resource(name = "IMCrsService")
	private IMCrsService crsService;

	@Resource(name = "IMAgncyService")
	private IMAgncyService agncyService;
	

	@Resource(name = "IMCmmnDtService")
	private IMCmmnDtService cmmnDtService;
	

	@Resource(name = "IMCmmnSttsService")
	private IMCmmnSttsService cmmnSttsService;

	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;
	

    @Resource (name = "IMWtstPlaceService")
	private IMWtstPlaceService wtstPlaceService;
    

    @Resource(name = "IMLgnSttsService")
    private IMLgnSttsService lgnSttsService;

    @Resource (name = "IMMmbrHstryService")
	private IMMmbrHstryService mmbrHstryService;
    
    @Resource (name = "IMMmbrQlfcService")
    private IMMmbrQlfcService mmbrQlfcService;
    

    @Resource (name = "IMIssuAplyService")
	private IMIssuAplyService issuAplyService;
    

	@Resource (name = "IMPstpndAplyService")
	private IMPstpndAplyService pstpndAplyService;
	/**
	 * 마이페이지>교육신청 현황 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user/uss/mypage/crsAplcnt/selectList.do")
	public ModelAndView selectList(HttpServletRequest req, HttpServletResponse res ,  IMCrsAplcntCondition condition) throws Exception {
		ModelAndView mav = new ModelAndView();
		

		setEmptyValue(condition, "currentPageNo=1", "recordCountPerPage=" + propertyService.getInt("pageUnit"),
				"pageSize=" + propertyService.getInt("pageSize"), "orderbyKey=0","scAplyStts=01");
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		condition.setScMmbrEsntlId(user.getUniqId());
		mav.addObject("pageInfo", crsAplcntService.selectListCrsAplcnt(condition));
		

		String orgScAplyStts = condition.getScAplyStts();
		condition.setScAplyStts("01");
		mav.addObject("cnt01",crsAplcntService.selectListCount(condition));
		
		condition.setScAplyStts("020405");
		mav.addObject("cnt020405",crsAplcntService.selectListCount(condition));
		
		condition.setScAplyStts("03");
		mav.addObject("cnt03",crsAplcntService.selectListCount(condition));
		
		condition.setScAplyStts("02A");
		mav.addObject("cnt02A",crsAplcntService.selectListCount(condition));

		condition.setScAplyStts(orgScAplyStts);
		mav.addObject("condition", condition);
		mav.setViewName("layout/user/uss/mypage/selectListMypageCrsAplcnt");
		return mav;
	}
	/**
	 * 마이페이지>교육신청 현황 상세
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user/uss/mypage/crsAplcnt/selectDetail.do")
	public ModelAndView selectDetail(HttpServletRequest req, HttpServletResponse res ,  IMCrsAplcntCondition condition,IMCrsAplcntVO iMCrsAplcnt) throws Exception {
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
		

		mav.addObject("detailApply", detailApply);
		mav.addObject("detail", detail);
		mav.addObject("cmmmDescMap", cmmnDescService.selectListCmmnDescResultMap("IM_CRS", iMCrs.getCrsId()));
		mav.addObject("cmmmDtMap", cmmnDtService.selectListCmmnDtResultMap("IM_CRS", iMCrs.getCrsId()));

		mav.addObject("cmmmDtStts", cmmnSttsService.selectListCmmnSttsResultMap("IM_CRS_APLCNT", iMCrsAplcnt.getCrsAplcntId()));
		
		

		//양성기관 상세 정보
		IMAgncyVO agVo = new IMAgncyVO();
		agVo.setAgncyId(detail.getCrs().getAgncyId());

		mav.addObject("agncyDetail", agncyService.selectDetailAgncy(agVo));
		

		mav.addObject("condition", condition);
		
		
		mav.setViewName("layout/user/uss/mypage/selectDetailMypageCrsAplcnt");
		return mav;
	}
	/**
	 * 마이페이지>응시현황 현황
	 * @param req
	 * @param res
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user/uss/mypage/wtstAplcnt/selectList.do")
	public ModelAndView selectWtstList(HttpServletRequest req, HttpServletResponse res ,  IMWtstAplcntCondition condition) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		setEmptyValue(condition, "currentPageNo=0", "recordCountPerPage=" + propertyService.getInt("pageUnit"),
				"pageSize=" + propertyService.getInt("pageSize"), "orderbyKey=0","scAplyStts=01A");
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		condition.setScMmbrEsntlId(user.getUniqId());
		mav.addObject("pageInfo", wtstAplcntService.selectListWtstAplcnt(condition));
		
		String orgScAplyStts = condition.getScAplyStts();
		condition.setScAplyStts("01A");
		mav.addObject("cnt01A",wtstAplcntService.selectListCount(condition));
		
		
		condition.setScAplyStts("03");
		mav.addObject("cnt03",wtstAplcntService.selectListCount(condition));
		
		
		condition.setScAplyStts("0204");
		mav.addObject("cnt0204",wtstAplcntService.selectListCount(condition));
		
		
		
		condition.setScAplyStts("02A");
		mav.addObject("cnt02A",wtstAplcntService.selectListCount(condition));
		
		
		

		condition.setScAplyStts(orgScAplyStts);
		mav.addObject("condition", condition);
		mav.setViewName("layout/user/uss/mypage/selectListMypageWtstAplcnt");
		return mav;
	}
	
	/**
	 * 마이페이지>응시현황 상세
	 * @param req
	 * @param res
	 * @param condition
	 * @param iMWtstAplcnt
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping (value = { "/user/uss/mypage/wtstAplcnt/selectDetail.do", "/user/uss/mypage/wtstAplcnt/selectDetailScore.do" })
	public ModelAndView selectWtstDetail(HttpServletRequest req, HttpServletResponse res ,  IMWtstAplcntCondition condition,IMWtstAplcntVO iMWtstAplcnt) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		
		if(iMWtstAplcnt.getWtstAplcntId()==null || "".equals(iMWtstAplcnt.getWtstAplcntId())) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		
		IMWtstAplcntResultSet  detailApply = (IMWtstAplcntResultSet)wtstAplcntService.selectDetailWtstAplcnt(iMWtstAplcnt);
		

		if(detailApply==null) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		/**
		 * 본인 결과만 확인 
		 */
		if(!detailApply.getWtstAplcnt().getMmbrEsntlId().equals(user.getUniqId())) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		
		IMWtstPlaceVO iMWtstPlace = new IMWtstPlaceVO();

		iMWtstPlace.setWtstPlaceId(detailApply.getWtstAplcnt().getWtstPlaceId());

		IMWtstPlaceResultSet detail = (IMWtstPlaceResultSet) wtstPlaceService.selectDetailWtstPlace(iMWtstPlace);
		

		mav.addObject("detailApply", detailApply);
		mav.addObject("detail", detail);

		mav.addObject("cmmmDescMap", cmmnDescService.selectListCmmnDescResultMap("IM_WTST", detail.getWtstPlace().getWtstId()));
		mav.addObject("cmmmDtMap", cmmnDtService.selectListCmmnDtResultMap("IM_WTST", detail.getWtstPlace().getWtstId()));
		
		HashMap<String, IMCmmnSttsVO> sttsMapObjet = cmmnSttsService.selectListCmmnSttsResultObjectMap("IM_WTST_APLCNT", iMWtstAplcnt.getWtstAplcntId());


		mav.addObject("cmmmDtStts", cmmnSttsService.selectListCmmnSttsResultMap("IM_WTST_APLCNT", iMWtstAplcnt.getWtstAplcntId()));
		
		mav.addObject("aplyStatusCode", sttsMapObjet.get(IMWtstAplcntStts.접수상태.sttsKey).getSttsCdv());
		
		mav.addObject("sttsMap", sttsMapObjet);

		mav.addObject("condition", condition);
		if(req.getServletPath().indexOf("selectDetailScore")!=-1) {
			mav.setViewName("/view/user/uss/mypage/selectDetailMypageWtstAplcntScore");
		}else {
			mav.setViewName("layout/user/uss/mypage/selectDetailMypageWtstAplcnt");
		}
		return mav;
	}
	
	
	/**
	 * 마이페이지 > 대시보드
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user/uss/mypage/selectDetail.do")
	public ModelAndView selectListMypage(HttpServletRequest req, HttpServletResponse res ) throws Exception {
		ModelAndView mav = new ModelAndView();
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		//로그인 정보 
		IMLgnSttsVO lgnStts = lgnSttsService.selectDetailLgnStts(user.getUniqId());

		mav.addObject("lgnStts", lgnStts);
		//이력 정보 
		IMMmbrHstryVO iMMmbrHstry = new  IMMmbrHstryVO ();
		iMMmbrHstry.setEsntlId(user.getUniqId());
		IMMmbrHstryResultSet detailHstry = (IMMmbrHstryResultSet) mmbrHstryService.selectDetailMmbrHstry(iMMmbrHstry);
		mav.addObject("hstry", detailHstry);
		
		
		
		mav.addObject("listMmbrQlfc", mmbrQlfcService.selectListUserMmbrQlfc(user.getUniqId()));
		Long memberSrl = null;
		if(detailHstry!=null) {
			memberSrl = detailHstry.getMmbrHstry().getMemberSrl();
			//희망 급수 정보   
			IMMmbrQlfcVO lastMmbrQlfc = mmbrQlfcService.selectDetailMmbrQlfc(user.getUniqId(), detailHstry.getMmbrHstry().getAplyGrdCdv());
			
			
			//시험 응시정보 
			if(lastMmbrQlfc!=null &&  lastMmbrQlfc.getWtstAplcntId()!=null && !"".equals(lastMmbrQlfc.getWtstAplcntId()) && lastMmbrQlfc.getWtstPassYmd()!=null && !"".equals(lastMmbrQlfc.getWtstPassYmd())){
				IMWtstAplcntVO iMWtstAplcnt = new IMWtstAplcntVO ();
				iMWtstAplcnt.setWtstAplcntId(lastMmbrQlfc.getWtstAplcntId());
				
				IMWtstAplcntResultSet  detailApply = (IMWtstAplcntResultSet)wtstAplcntService.selectDetailWtstAplcnt(iMWtstAplcnt);
				
				IMWtstPlaceVO iMWtstPlace = new IMWtstPlaceVO();

				iMWtstPlace.setWtstPlaceId(detailApply.getWtstAplcnt().getWtstPlaceId());

				IMWtstPlaceResultSet detail = (IMWtstPlaceResultSet) wtstPlaceService.selectDetailWtstPlace(iMWtstPlace);

				mav.addObject("detailWtstApply", detailApply);
				mav.addObject("detailWtstPlace", detail);
				
			}
			mav.addObject("lastMmbrQlfc", lastMmbrQlfc);
		}else {
			if(user.getIhidNum()!=null && !"".equals(user.getIhidNum())) {
				memberSrl = Long.parseLong(user.getIhidNum());
			}
		}
		List<BaseResultSet> list = mmbrHstryService.selectEduHisListStat(memberSrl);
		
		
		HashMap<String, Long> eduHisMap = new HashMap<String, Long>();
		if(list!=null && list.size()>0) {
			for(BaseResultSet rs : list) {
				IMMmbrHstryResultSet mmbrHstryResultSet=(IMMmbrHstryResultSet)rs;
				eduHisMap.put(mmbrHstryResultSet.getEduAplyHstry().getSttsCode(), mmbrHstryResultSet.getCnt());
			}
		
		}

		mav.addObject("eduHisMap", eduHisMap);
		

		mav.addObject("selectCrsStat", crsAplcntService.selectAplyStat(user.getUniqId()));
		mav.addObject("selectWtstStat", wtstAplcntService.selectAplyStat(user.getUniqId()));
		
		mav.setViewName("layout/user/uss/mypage/selectDetailMypage");
		return mav;
	}
	/**
	 * 마이페이지 > 대시보드
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user/uss/mypage/selectListMmbrHstry.do")
	public ModelAndView selectListMmbrHstry(HttpServletRequest req, HttpServletResponse res ) throws Exception {
		ModelAndView mav = new ModelAndView();
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		//로그인 정보 
		IMLgnSttsVO lgnStts = lgnSttsService.selectDetailLgnStts(user.getUniqId());
		
		mav.addObject("lgnStts", lgnStts);
		//이력 정보 
		IMMmbrHstryVO iMMmbrHstry = new  IMMmbrHstryVO ();
		iMMmbrHstry.setEsntlId(user.getUniqId());
		IMMmbrHstryResultSet detailHstry = (IMMmbrHstryResultSet) mmbrHstryService.selectDetailMmbrHstry(iMMmbrHstry);
		mav.addObject("hstry", detailHstry);
		Long memberSrl = null;
		if(detailHstry!=null) {
			memberSrl = detailHstry.getMmbrHstry().getMemberSrl();
		}else {
			if(user.getIhidNum()!=null && !"".equals(user.getIhidNum())) {
				memberSrl = Long.parseLong(user.getIhidNum());
			}
		}
		int selectEduHisCnt = 0;
		if(memberSrl!=null) {
			List<BaseResultSet> list = mmbrHstryService.selectEduHisList(memberSrl,null);
			mav.addObject("selectEduHis", list);
			if(list!=null && list.size()>0) {
				selectEduHisCnt = list.size();
			}
		}
		mav.addObject("selectEduHisCnt",selectEduHisCnt);
		
		mav.setViewName("layout/user/uss/mypage/selectListMypageMmbrHstry");
		return mav;
	}
	
	
	/**
	 * 과정 수료증 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping (value = { "/user/uss/mypage/selectDetailCrsPrint.do", "/user/uss/mypage/selectDetailCrsPrintPass.do"})
	public ModelAndView selectDetailCrsPrint(HttpServletRequest req, HttpServletResponse res , IMCrsAplcntVO iMCrsAplcnt ) throws Exception {
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
		
		IMCrsAplcntVO crsResult  = crsAplcntService.selectDetailResult(iMCrsAplcnt.getCrsAplcntId());
				
		
		if(crsResult==null) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		if(crsResult.getFnshRsltCode()==null || "".equals(crsResult.getFnshRsltCode())) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}

		mav.addObject("crsResult",crsResult);
		IMAgncyVO agVO = new IMAgncyVO();
		agVO.setAgncyId(crsResult.getAgncyId());
		
		IMAgncyResultSet detailAgncy = (IMAgncyResultSet) agncyService.selectDetailAgncy(agVO);
		
		mav.addObject("agncy", detailAgncy.getAgncy());
		
		if (req.getServletPath().startsWith("/user/uss/mypage/selectDetailCrsPrintPass")) {
			//이수증 보수교육
			if("CRS_DVSN_003".equals(crsResult.getCrsDvsnCdv())){
				mav.setViewName("/view/user/uss/mypage/selectDetailCrsPrintPass");
			}else{
				throw new IMProcException(IMProcErrors.필수값없음);
			}	
		}else {
			if(!"CRS_DVSN_003".equals(crsResult.getCrsDvsnCdv())){
				mav.setViewName("/view/user/uss/mypage/selectDetailCrsPrint");
			}else {
				throw new IMProcException(IMProcErrors.필수값없음);
			}
		}
		return mav;
	}
	
	/**
	 * 과정 자격증 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user/uss/mypage/selectDetailCrsPrintLicense.do")
	public ModelAndView selectDetailCrsPrintLicense(HttpServletRequest req, HttpServletResponse res, IMIssuAplyVO iMIssuAply ) throws Exception {
		ModelAndView mav = new ModelAndView();
		

		if(iMIssuAply.getIssuAplyId()==null || "".equals(iMIssuAply.getIssuAplyId())) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		
		IMIssuAplyResultSet detailApply = (IMIssuAplyResultSet) issuAplyService.selectDetailIssuAply(iMIssuAply);
		

		if(detailApply==null) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		
		
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		/**
		 * 본인 결과만 확인 
		 */
		if(!detailApply.getIssuAply().getEsntlId().equals(user.getUniqId())) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		
		IMIssuAplyVO crsResult = detailApply.getIssuAply();

		if(crsResult==null) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		
		if(!"02".equals(crsResult.getSttsCdv())) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		
		if(crsResult.getQlfcRsltCode()==null || "".equals(crsResult.getQlfcRsltCode())) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}

		mav.addObject("crsResult",crsResult);
		
		mav.setViewName("/view/user/uss/mypage/selectDetailCrsPrintLicense");
		return mav;
	}
	/**
	 * 수험표
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user/uss/mypage/selectDetailExamPrint.do")
	public ModelAndView selectDetailPrintExam(HttpServletRequest req, HttpServletResponse res,IMWtstAplcntVO iMWtstAplcnt ) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		if(iMWtstAplcnt.getWtstAplcntId()==null || "".equals(iMWtstAplcnt.getWtstAplcntId())) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		
		IMWtstAplcntResultSet  detailApply = (IMWtstAplcntResultSet)wtstAplcntService.selectDetailWtstAplcnt(iMWtstAplcnt);
		

		if(detailApply==null) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		/**
		 * 본인 결과만 확인 
		 */
		if(!detailApply.getWtstAplcnt().getMmbrEsntlId().equals(user.getUniqId())) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		
		IMWtstPlaceVO iMWtstPlace = new IMWtstPlaceVO();

		iMWtstPlace.setWtstPlaceId(detailApply.getWtstAplcnt().getWtstPlaceId());

		IMWtstPlaceResultSet detail = (IMWtstPlaceResultSet) wtstPlaceService.selectDetailWtstPlace(iMWtstPlace);
		

		mav.addObject("detailApply", detailApply);
		mav.addObject("aplcnt", detailApply.getWtstAplcnt());
		mav.addObject("detail", detail);
		
		mav.addObject("cmmmDescMap", cmmnDescService.selectListCmmnDescResultMap("IM_WTST", detail.getWtstPlace().getWtstId()));
		HashMap<String, IMCmmnSttsVO> sttsMapObjet = cmmnSttsService.selectListCmmnSttsResultObjectMap("IM_WTST_APLCNT", iMWtstAplcnt.getWtstAplcntId());


		mav.addObject("cmmmDtStts", cmmnSttsService.selectListCmmnSttsResultMap("IM_WTST_APLCNT", iMWtstAplcnt.getWtstAplcntId()));
		String aplyStatusCode=sttsMapObjet.get(IMWtstAplcntStts.접수상태.sttsKey).getSttsCdv();
		String aplyDEPTCode=sttsMapObjet.get(IMWtstAplcntStts.입금확인여부.sttsKey).getSttsCdv();
		
		if("02".equals(aplyStatusCode) && "Y".equals(aplyDEPTCode) && detailApply.getWtstAplcnt().getTktstno()!=null && !"".equals(detailApply.getWtstAplcnt().getTktstno()) ) {
			mav.setViewName("/view/user/uss/mypage/selectDetailExamPrint");
		}else {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		
		return mav;
	}
	/**
	 * 유예확인서
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user/uss/mypage/selectDetailPstpndAplyPrint.do")
	public ModelAndView selectDetailPstpndAplyPrint(HttpServletRequest req, HttpServletResponse res , IMPstpndAplyVO iMPstpndAply ) throws Exception {
		ModelAndView mav = new ModelAndView();

		IMPstpndAplyResultSet detail = (IMPstpndAplyResultSet)pstpndAplyService.selectDetailPstpndAply(iMPstpndAply);
		
		mav.addObject("iMPstpndAply", detail.getPstpndAply());
		mav.addObject("detail", detail);
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		
		if(!detail.getPstpndAply().getEsntlId().equals(user.getUniqId())) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		//승인인 경우만 출력 가능함 
		if(!detail.getPstpndAply().getSttsCdv().equals("02")) {
			throw new IMProcException(IMProcErrors.필수값없음);
		}
		
		mav.setViewName("/view/user/uss/mypage/selectDetailPstpndAplyPrint");
		return mav;
	}
	/**
	 * 과정 과정 수료증 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user/uss/mypage/selectDetailCrsOldPrint.do")
	public ModelAndView selectDetailCrsOldPrint(HttpServletRequest req, HttpServletResponse res ) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		
		mav.setViewName("/view/user/uss/mypage/selectDetailCrsPrint");
		return mav;
	}
}
