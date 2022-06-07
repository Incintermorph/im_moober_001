/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.intermorph.cmmn.IMGlobals;
import com.intermorph.cmmn.base.BaseController;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;
import com.intermorph.cmmn.egov.service.IMEgovService;
import com.intermorph.cmmn.exception.IMMmbrAgreeStts;
import com.intermorph.cmmn.service.IMCmmnSttsService;
import com.intermorph.cmmn.service.IMCmmnSttsVO;
import com.intermorph.cmmn.service.IMLgnSttsService;
import com.intermorph.cmmn.service.IMLgnSttsVO;
import com.intermorph.cmmn.service.IMSSOLoginDTO;
import com.intermorph.cmmn.util.IMSsoLoginUtil;
import com.intermorph.cmmn.util.IMStringUtil;
import com.intermorph.wtst.place.service.IMWtstPlaceCondition;
import com.intermorph.wtst.place.service.IMWtstPlaceService;
import com.penta.scpdb.ScpDbAgent;
import com.penta.scpdb.ScpDbAgentException;

import egovframework.com.cmm.EgovBrowserUtil;
import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovBasicLogger;
import egovframework.com.cmm.util.EgovResourceCloseHelper;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.sec.rgm.service.AuthorGroup;
import egovframework.com.sec.rgm.service.EgovAuthorGroupService;
import egovframework.com.uss.umt.service.EgovMberManageService;
import egovframework.com.uss.umt.service.EgovUserManageService;
import egovframework.com.uss.umt.service.MberManageVO;
import egovframework.com.utl.sim.service.EgovFileScrty;
import egovframework.rte.fdl.access.bean.AuthorityResourceMetadata;
import net.sf.ehcache.Ehcache;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.web
 * @File : IMCommonUserController.java
 * @Title : {간단한 프로그램의 명칭을 기록}
 * @date : 2022. 3. 2
 * @author : 노성용
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Controller
public class IMCommonUserController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(IMCommonUserController.class);
	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileService;
	
	@Resource(name = "IMEgovService")
	private IMEgovService iMEgovService;
	
	/** mberManageService */
	@Resource(name = "mberManageService")
	private EgovMberManageService mberManageService;
	

	/** userManageService */
	@Resource(name = "userManageService")
	private EgovUserManageService userManageService;
	

    @Resource(name = "egovAuthorGroupService")
    private EgovAuthorGroupService egovAuthorGroupService;
    
    @Resource(name = "IMLgnSttsService")
    private IMLgnSttsService lgnSttsService;
    
    @Resource (name = "IMWtstPlaceService")
	private IMWtstPlaceService wtstPlaceService;
    
    

	@Resource(name="authorityResource")
	private AuthorityResourceMetadata sessionResourceMetadata;
	
	

	@Resource(name = "ehcache")
	Ehcache ehCache;

	
	
	@Resource(name = "IMCmmnSttsService")
	IMCmmnSttsService cmmnSttsService;
	
	/**
	 * 사용자 첫페이지 화면
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cmmn/index.do")
	public ModelAndView index(HttpServletRequest req,  HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();

		BoardVO  bardVO=  new BoardVO();
		bardVO.setBbsId("BBSMSTR_000000000001");
		bardVO.setFirstIndex(0);
		bardVO.setRecordCountPerPage(3);
		
		mav.addObject("selectNotice", iMEgovService.selectArticleMainList(bardVO));

		bardVO.setRecordCountPerPage(5);
	
		mav.addObject("selectFaq", iMEgovService.selectFaqMainList(bardVO));
		
		//팝업공지 
		bardVO.setBbsId("BBSMSTR_000000000091");
		mav.addObject("selectPopup", iMEgovService.selectArticleMainList(bardVO));
	      
		//원서접수
//		mav.addObject("selectPlace",iMEgovService.selectListWtstPlace());
		IMWtstPlaceCondition condition = new IMWtstPlaceCondition();
		setEmptyValue(condition, "orderbyKey=3" , "scProcType=I", "scSttsCdv=STTS_02");
		
		BasePage<BaseResultSet>	wtstPage =wtstPlaceService.selectListWtstPlace(condition);
		mav.addObject("listWtst",wtstPage.getList());
		
		//1 차 오픈시 1급은 제외 
		//List<BaseResultSet> listCrs1 = iMEgovService.selectMainListCrs("CRS_GRD_1",4L);
		List<BaseResultSet> listCrs2 = iMEgovService.selectMainListCrs("CRS_GRD_2",4L);
		List<BaseResultSet> listCrs3 = iMEgovService.selectMainListCrs("CRS_GRD_3",4L);
		//List<BaseResultSet> listCrsAll = iMEgovService.selectMainListCrs(null,12L);
		List<BaseResultSet> listCrsAll = iMEgovService.selectMainListCrs(null,8L);
		

		//mav.addObject("listCrs1", listCrs1);
		mav.addObject("listCrs2", listCrs2);
		mav.addObject("listCrs3", listCrs3);
		mav.addObject("listCrsAll", listCrsAll);
		
		
		

		
		mav.setViewName("/view/user/cmmn/indexCmmn");
		return mav;
	}

	/**
	 * 로그인 페이지 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cmmn/login.do")
	public ModelAndView login(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		 
    	req.getSession().setAttribute("_paramReturnUrl", req.getParameter("_paramReturnUrl"));
		mav.setViewName("/view/user/cmmn/loginCmmn");
		return mav;
	}
	/**
	 * 로그인 페이지 체크 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cmmn/login/proc.do")
	public ModelAndView loginProc(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		String id= IMStringUtil.getParameter(req, "id");
		String password= IMStringUtil.getParameter(req, "password");
		//System.out.println("id : " + id);
		//System.out.println("password : " + password);
		
		IMSSOLoginDTO resultDto=  IMSsoLoginUtil.login(id, password);
		
		int result =-1;
		int firstAgree =0;
		if(resultDto!=null) {
			//로그인 성공 시 
			if("OK".equals(resultDto.getResult().trim())){
				// 회원 등록 
				// 회원 업데이트 - 전화번호 이메인 회원 번호
				// 이력 등록 
				MberManageVO mberManageVO = new MberManageVO();
				mberManageVO.setMberId(resultDto.getId());
				mberManageVO.setMberNm(resultDto.getName());
				mberManageVO.setMberEmailAdres(resultDto.getEmail());
				mberManageVO.setGroupId("GROUP_00000000000000");
				mberManageVO.setPassword(password);
				mberManageVO.setAreaNo(IMStringUtil.getPhoneNum(resultDto.getTel(), 0));
				mberManageVO.setMiddleTelno(IMStringUtil.getPhoneNum(resultDto.getTel(), 1));
				mberManageVO.setEndTelno(IMStringUtil.getPhoneNum(resultDto.getTel(), 2));
				if(mberManageVO.getAreaNo()==null || "".equals(mberManageVO.getAreaNo())) {
					mberManageVO.setAreaNo("0000");
					mberManageVO.setMiddleTelno("0000");
					mberManageVO.setEndTelno("0000");
				}
				mberManageVO.setMberSttus("P");
				mberManageVO.setZip("00000");
				mberManageVO.setAdres("0000");
				mberManageVO.setIhidnum(resultDto.getMember_srl());
				mberManageVO.setPasswordHint("P01");
				mberManageVO.setPasswordCnsr(UUID.randomUUID().toString());
				mberManageVO.setMoblphonNo(resultDto.getTel());
				int over = userManageService.checkIdDplct(mberManageVO.getMberId());
				if(over==0) {
					//등록 
					//System.out.println(mberManageVO.toString());
					mberManageService.insertMber(mberManageVO);
					AuthorGroup authorGroup = new AuthorGroup();
					authorGroup.setUniqId(mberManageVO.getUniqId());
					authorGroup.setMberTyCode("USR02");
					authorGroup.setAuthorCode("ROLE_USER");
					//권한 부여 
					egovAuthorGroupService.insertAuthorGroup(authorGroup);
					sessionResourceMetadata.reload();
				}else {
					 String uniqId = iMEgovService.selectUserId(mberManageVO.getMberId());
					 if(uniqId==null || "".equals(uniqId)) {
						 result =-2;
					 }else {
						 //uniqId
						 mberManageVO.setUniqId(uniqId);
						 mberManageService.updateMber(mberManageVO);
						 mberManageVO.setPassword(EgovFileScrty.encryptPassword(password, mberManageVO.getMberId()));
						 mberManageService.updatePassword(mberManageVO);
					 }
					 
					 List<IMCmmnSttsVO> listAgree= cmmnSttsService.selectListCmmnStts("COMTNGNRLMBER", uniqId);
					//동의 여부 체크 
						int okAgreeCnt=0;
						int defineAgree=0;
						for(IMMmbrAgreeStts v : IMMmbrAgreeStts.values()) {
							defineAgree++;
							for(IMCmmnSttsVO saveAgree : listAgree) {
								if(v.sttsKey.equals(saveAgree.getRefNm()) &&  v.defaultCode.equals(saveAgree.getSttsCdv())){
									okAgreeCnt++;
								};
								
							}
					    }
						//약관 동의  통과  
						if(defineAgree==okAgreeCnt) {
							firstAgree =okAgreeCnt;
						}
					 
				}
				result =1;
			}else {
				result =0;
			}
		}

		mav.addObject("resultMsg", resultDto.getMsg());
		mav.addObject("result", result);
		mav.addObject("firstAgree", firstAgree);
		
		mav.setViewName("jsonView");
		return mav;
	}
	
	/**
	 * 무명 페이지 
	 * @param req
	 * @param res
	 * @param pagePath
	 * @return
	 * @throws Exception
	 */
	@RequestMapping (value = { "/cmmn/page/{pagePath}/page.do","/user/page/{pagePath}/page.do"})
	public ModelAndView page(HttpServletRequest req, HttpServletResponse res,@PathVariable("pagePath") String pagePath) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("layout/user/cmmn/page/"+pagePath.toLowerCase());
		return mav;
	}
	/**
	 * 무명 페이지  팝업 
	 * @param req
	 * @param res
	 * @param pagePath
	 * @return
	 * @throws Exception
	 */
	@RequestMapping (value = { "/cmmn/page/{pagePath}/page/popup.do","/user/page/{pagePath}/page/popup.do"})
	public ModelAndView pagepopup(HttpServletRequest req, HttpServletResponse res,@PathVariable("pagePath") String pagePath) throws Exception {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("/view/user/cmmn/page/"+pagePath.toLowerCase());
		return mav;
	}
	
	
	/**
	 * 첨부파일로 등록된 파일에 대하여 다운로드를 제공한다.
	 *
	 * @param commandMap
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmmn/common/FileDown.do")
	public void cvplFileDownload(@RequestParam Map<String, Object> commandMap, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String atchFileIdEnc = (String) commandMap.get("atchFileId");
		
		String[] arrAtchFileId = IMStringUtil.decryptString(atchFileIdEnc, IMGlobals.IM_SECURITY_KEY).split("=");
		String atchFileId = arrAtchFileId[0];
		String fileSn = arrAtchFileId[1];
		
			FileVO fileVO = new FileVO();
			fileVO.setAtchFileId(atchFileId);
			fileVO.setFileSn(fileSn);
			FileVO fvo = fileService.selectFileInf(fileVO);

			File uFile = new File(fvo.getFileStreCours(), fvo.getStreFileNm());
			long fSize = uFile.length();

			//System.out.println("fSize : " + fSize);
			if (fSize > 0) {
				String mimetype = "application/x-msdownload";
				
				String userAgent = request.getHeader("User-Agent");
				HashMap<String,String> result = EgovBrowserUtil.getBrowser(userAgent);
				if ( !EgovBrowserUtil.MSIE.equals(result.get(EgovBrowserUtil.TYPEKEY)) ) {
					mimetype = "application/x-stuff";
				}

				String contentDisposition = EgovBrowserUtil.getDisposition(fvo.getOrignlFileNm(),userAgent,"UTF-8");
				//response.setBufferSize(fSize);	// OutOfMemeory 발생
				response.setContentType(mimetype);
				//response.setHeader("Content-Disposition", "attachment; filename=\"" + contentDisposition + "\"");
				response.setHeader("Content-Disposition", contentDisposition);
				//response.setContentLengthLong(fSize);

				response.setHeader("Content-Length", String.valueOf(fSize));
				/*
				 * FileCopyUtils.copy(in, response.getOutputStream());
				 * in.close();
				 * response.getOutputStream().flush();
				 * response.getOutputStream().close();
				 */
				BufferedInputStream in = null;
				BufferedOutputStream out = null;

				try {
					in = new BufferedInputStream(new FileInputStream(uFile));
					out = new BufferedOutputStream(response.getOutputStream());

					FileCopyUtils.copy(in, out);
					out.flush();
				} catch (IOException ex) {
					// 다음 Exception 무시 처리
					// Connection reset by peer: socket write error
					EgovBasicLogger.ignore("IO Exception", ex);
				} finally {
					EgovResourceCloseHelper.close(in, out);
				}

			} else {
				response.setContentType("application/x-msdownload");

				PrintWriter printwriter = response.getWriter();
				
				printwriter.println("<html>");
				printwriter.println("<br><br><br><h2>Could not get file name:<br>" + fvo.getOrignlFileNm() + "</h2>");
				printwriter.println("<br><br><br><center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
				printwriter.println("<br><br><br>&copy; webAccess");
				printwriter.println("</html>");
				
				printwriter.flush();
				printwriter.close();
			}
	}
	/**
	 * 첨부파일로 등록된 파일에 대하여 다운로드를 제공한다.
	 *
	 * @param commandMap
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmmn/common/FileDownEnc.do")
	public synchronized void  cvplFileDownloadEnc(@RequestParam Map<String, Object> commandMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String atchFileIdEnc = (String) commandMap.get("atchFileId");
		
		String[] arrAtchFileId = IMStringUtil.decryptString(atchFileIdEnc, IMGlobals.IM_SECURITY_KEY).split("=");
		String atchFileId = arrAtchFileId[0];
		String fileSn = arrAtchFileId[1];
		
		FileVO fileVO = new FileVO();
		fileVO.setAtchFileId(atchFileId);
		fileVO.setFileSn(fileSn);
		FileVO fvo = fileService.selectFileInf(fileVO);
		
		File uFile = new File(fvo.getFileStreCours(), fvo.getStreFileNm());
		long fSize = uFile.length();
		String decFilePath=null;
		if (fSize > 0) {
			if (!"none".equals(IMGlobals.DAMO_KEYPATH)) {
				String iniFilePath = IMGlobals.DAMO_KEYPATH;
				ScpDbAgent agt = new ScpDbAgent();
				decFilePath = uFile.getAbsolutePath()+".Dec";
				//System.out.println("uFile.getAbsolutePath() : " + uFile.getAbsolutePath());
				int ret = agt.ScpDecFile(iniFilePath, "KEY1", uFile.getAbsolutePath(), decFilePath);
			//	System.out.println("decFilePath : " + decFilePath);
				uFile = new File(decFilePath);
				fSize = uFile.length();
				LOGGER.debug("[java] ScpEncFile : " + ret);
			}
			String mimetype = "application/x-msdownload";
			
			String userAgent = request.getHeader("User-Agent");
			HashMap<String,String> result = EgovBrowserUtil.getBrowser(userAgent);
			if ( !EgovBrowserUtil.MSIE.equals(result.get(EgovBrowserUtil.TYPEKEY)) ) {
				mimetype = "application/x-stuff";
			}
			
			String contentDisposition = EgovBrowserUtil.getDisposition(fvo.getOrignlFileNm(),userAgent,"UTF-8");
			//response.setBufferSize(fSize);	// OutOfMemeory 발생
			response.setContentType(mimetype);
			//response.setHeader("Content-Disposition", "attachment; filename=\"" + contentDisposition + "\"");
			response.setHeader("Content-Disposition", contentDisposition);
			//response.setContentLengthLong(fSize);
			response.setHeader("Content-Length", String.valueOf(fSize));
			
			/*
			 * FileCopyUtils.copy(in, response.getOutputStream());
			 * in.close();
			 * response.getOutputStream().flush();
			 * response.getOutputStream().close();
			 */
			BufferedInputStream in = null;
			BufferedOutputStream out = null;
			
			try {
				in = new BufferedInputStream(new FileInputStream(uFile));
				out = new BufferedOutputStream(response.getOutputStream());
				
				FileCopyUtils.copy(in, out);
				out.flush();
			} catch (IOException ex) {
				// 다음 Exception 무시 처리
				// Connection reset by peer: socket write error
				EgovBasicLogger.ignore("IO Exception", ex);
			} finally {
				if(decFilePath!=null) {
					//복호화된 파일 삭제 
					if (uFile != null && uFile.exists()) {
						uFile.delete();
					}
				}
				EgovResourceCloseHelper.close(in, out);
			}
			
		} else {
			response.setContentType("application/x-msdownload");
			
			PrintWriter printwriter = response.getWriter();
			
			printwriter.println("<html>");
			printwriter.println("<br><br><br><h2>Could not get file name:<br>" + fvo.getOrignlFileNm() + "</h2>");
			printwriter.println("<br><br><br><center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
			printwriter.println("<br><br><br>&copy; webAccess");
			printwriter.println("</html>");
			
			printwriter.flush();
			printwriter.close();
		}
	}

	/**
	 * 세션 체크 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cmmn/chek/session.do")
	public ModelAndView cheksession(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		String enEsntlId = IMStringUtil.getParameter(req, "enEsntlId");
		String sesinId = IMStringUtil.getParameter(req, "sesinId");
		int result=0;
		if(enEsntlId==null || "".equals(enEsntlId) || sesinId==null || "".equals(sesinId)) {
			result=-1;
		}else {
			
			//인증된사용자 여부
			boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();	
				//미민증사용자 체크
			if(!isAuthenticated) {
				String esntlId= IMStringUtil.decryptString(enEsntlId, IMGlobals.IM_SECURITY_KEY);
				IMLgnSttsVO lgnStts = lgnSttsService.selectDetailLgnStts(esntlId);

				mav.addObject("lastMdferIp", lgnStts.getLastMdferIp());
				mav.addObject("lastMdfcnDt", lgnStts.getLastMdfcnDtFormat());
				if(sesinId.equals(lgnStts.getLastSesinId())) {
					//같은 세션 로그아웃 
					result=-2;
				}else {
					result=0;
				}
			}else {
				result=1;
			}
		}
		
		
		mav.addObject("result", result);
		mav.setViewName("jsonView");		
		
		return mav;
	}

	/**
	 * ehcache 캐쉬 삭제 한다.	
	 * @param req
	 * @param res
	 * @param ehCacheName : /resources/egovframework/spring/com/ehcache.xml 에 정의된  cache name 를 변수로 받는다.
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cmmn/common/ehcache/{ehCacheName}/remove.do")
	public ModelAndView removeCache(HttpServletRequest req, HttpServletResponse res,
			@PathVariable("ehCacheName") String ehCacheName) throws Exception {
		ModelAndView mav = new ModelAndView();
		Ehcache cache = ehCache.getCacheManager().getCache(ehCacheName);
		cache.removeAll();
		mav.addObject("result", 0);

		mav.setViewName("jsonView");
		return mav;
	}
}
