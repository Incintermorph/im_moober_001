/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.web;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.intermorph.cmmn.IMGlobals;
import com.intermorph.cmmn.base.BaseController;
import com.intermorph.cmmn.exception.IMProcErrors;
import com.intermorph.cmmn.exception.IMProcException;
import com.intermorph.cmmn.service.IMFileVO;
import com.intermorph.cmmn.util.IMFileMngUtil;
import com.intermorph.cmmn.util.IMFileUtil;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.utl.sim.service.EgovFileTool;
import net.sf.ehcache.Ehcache;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.web
 * @File    : IMCommonMngController.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 2. 14
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
@Controller
public class IMCommonMngController extends BaseController {
	
	@Resource(name = "ehcache")
	Ehcache ehCache;

	

    @Resource(name="EgovFileMngService")
    private EgovFileMngService fileMngService;

    @Resource(name="IMFileMngUtil")
    private IMFileMngUtil fileUtil;
    

    @Resource(name = "EgovFileMngService")
    private EgovFileMngService fileService;
	
	
	/**
	 * ehcache 캐쉬 삭제 한다.	
	 * @param req
	 * @param res
	 * @param ehCacheName : /resources/egovframework/spring/com/ehcache.xml 에 정의된  cache name 를 변수로 받는다.
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mng/common/ehcache/{ehCacheName}/remove.do")
	public ModelAndView removeCache(HttpServletRequest req, HttpServletResponse res,
			@PathVariable("ehCacheName") String ehCacheName) throws Exception {
		ModelAndView mav = new ModelAndView();
		Ehcache cache = ehCache.getCacheManager().getCache(ehCacheName);
		cache.removeAll();
		mav.addObject("result", 0);

		mav.setViewName("jsonView");
		return mav;
	}
	/**
	 * 관리자 첫페이지 화면 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mng/index.do")
	public ModelAndView index(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();

		mav.addObject("indexPageYn", "Y");
		mav.setViewName("layout/mng/cmmn/indexMng");
		return mav;
	}
	/**
	 * 관리자 레이아웃 샘플 
	 * @param req
	 * @param res
	 * @param ehCacheName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mng/common/layout/sample.do")
	public ModelAndView layoutSample(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("layout/mng/cmmn/common/layoutSample");
		return mav;
	}
	
	

	
	@RequestMapping (value = { "/mng/common/image/save.do", "/user/common/image/save.do" })
	public ModelAndView saveFileImage(final MultipartHttpServletRequest multiRequest, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		//imageWidth=100&imageHeight=200"
		
		//System.out.println("imageWidth : " + multiRequest.getParameter("imageWidth"));
		//System.out.println("imageHeight : " + multiRequest.getParameter("imageHeight"));
	//	System.out.println("uploadFolder : " + multiRequest.getParameter("uploadFolder"));
		String uploadFolder = multiRequest.getParameter("uploadFolder")==null?"temp":(String)multiRequest.getParameter("uploadFolder");
		
		int result=0;
		try {
			//MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)req;
			final Map<String, MultipartFile> files = multiRequest.getFileMap();

			String uploadFolderDir = "im"+ File.separator +"image"+File.separator+uploadFolder;
			
	    	String atchFileId = "";

	    	List<IMFileVO> resultFile = null;
	    	if(!files.isEmpty()){
	    		resultFile = fileUtil.parseFileInfForPath(files, "IMG_"+uploadFolder+"_", 0, "", uploadFolderDir);
	    	    atchFileId = fileMngService.insertFileInfs(resultFile);
	    	    String sourceFile = null;

				String thumbFilePath =null;
				String sourceFilePath =null;
	    	    for(IMFileVO f : resultFile) {
	    	    	
	    	    	sourceFile = f.getFileStreCours() + File.separator + f.getStreFileNm();
	    	    	//File source = new File(sourceFile);
	    	    
	    	    	//thumbFilePath = sourceFile + ".thumb.jpg";
	    	    	

				
					 sourceFilePath = sourceFile;
					
					//PNG는 JPG로 변경 후 처리함 
					if (f.getContentType().toLowerCase().trim().equals("image/png")) {
						File jpgFile=IMFileUtil.convertJpg(sourceFile);
						//System.out.println(jpgFile.getAbsolutePath());
						sourceFile = jpgFile.getAbsolutePath();
					}
					thumbFilePath = sourceFilePath + ".thumb1.jpg";
					//System.out.println("thumbFilePath = " + thumbFilePath);
					IMFileUtil.resizeImageJpg(sourceFile, thumbFilePath, 0, IMGlobals.IM_IMAGES_HEIGHT_THUMB1, false);
					thumbFilePath = sourceFilePath + ".thumb2.jpg";
					//System.out.println("thumbFilePath = " + thumbFilePath);
					IMFileUtil.resizeImageJpg(sourceFile, thumbFilePath, 0, IMGlobals.IM_IMAGES_HEIGHT_THUMB2, false);
					thumbFilePath = sourceFilePath + ".thumb3.jpg";
					//System.out.println("thumbFilePath = " + thumbFilePath);
					IMFileUtil.resizeImageJpg(sourceFile, thumbFilePath, 0, IMGlobals.IM_IMAGES_HEIGHT_THUMB3, false);
					
					
	    	    }
	    	    
	    	    
	    	    result = 1;
	    	}
			mav.addObject("atchFileId", atchFileId);
						
		} catch (Exception e) {
			result = 0;
			e.printStackTrace();
		}
		mav.addObject("result", result);
		
		mav.setViewName("jsonView");
		return mav;
	}
	
	@RequestMapping (value = { "/mng/common/file/save.do", "/user/common/file/save.do" })
	public ModelAndView saveFile(final MultipartHttpServletRequest multiRequest, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		//imageWidth=100&imageHeight=200"
		
		//System.out.println("imageWidth : " + multiRequest.getParameter("imageWidth"));
		//System.out.println("imageHeight : " + multiRequest.getParameter("imageHeight"));
		String uploadFolder = multiRequest.getParameter("uploadFolder")==null?"temp":(String)multiRequest.getParameter("uploadFolder");
		String encryptionYn = multiRequest.getParameter("encryptionYn")==null?"N":(String)multiRequest.getParameter("encryptionYn");
		if(!"Y".equals(encryptionYn)) {
			encryptionYn="N";
		}
		int result=0;
		try {
			//MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)req;
			final Map<String, MultipartFile> files = multiRequest.getFileMap();
			String uploadFolderDir = "im"+ File.separator +"file"+File.separator+uploadFolder;
	    	String atchFileId = "";

	    	List<IMFileVO> resultFile = null;
	    	System.out.println("encryptionYn : " + encryptionYn);
	    	if(!files.isEmpty()){
	    		if(!"Y".equals(encryptionYn)) {
	    			resultFile = fileUtil.parseFileInfForPath(files, "FIL_"+uploadFolder+"_", 0, "", uploadFolderDir);
	    		}else {
	    			
	    			resultFile = fileUtil.parseFileInfForPathEnc(files, "FIL_"+uploadFolder+"_", 0, "", uploadFolderDir);
	    		}
	    	    atchFileId = fileMngService.insertFileInfs(resultFile);
	    	    result = 1;
	    	}
			mav.addObject("atchFileId", atchFileId);
						
		} catch (Exception e) {
			result = 0;
			e.printStackTrace();
		}
		mav.addObject("result", result);
		
		mav.setViewName("jsonView");
		return mav;
	}
	
	  /**
	   * 첨부파일에 대한 삭제를 처리한다.
	   * @param req
	   * @param res
	   * @param fileVO
	   * @return
	   * @throws Exception
	   */
    @RequestMapping (value = { "/mng/common/file/delete.do", "/user/common/file/delete.do" })
    public ModelAndView deleteFile(HttpServletRequest req, HttpServletResponse res, FileVO fileVO) throws Exception {
    	ModelAndView mav = new ModelAndView();
    	
    	
    	if(fileVO.getAtchFileId()==null || "".equals(fileVO.getAtchFileId()) ) {
			throw new IMProcException(IMProcErrors.필수값없음); 
		}

    	if(fileVO.getFileSn()==null || "".equals(fileVO.getFileSn()) ) {
			throw new IMProcException(IMProcErrors.필수값없음); 
		}
			
    	FileVO fvo = fileService.selectFileInf(fileVO);

		    fileService.deleteFileInf(fileVO);
		

			//파일삭제
			EgovFileTool.deleteFile(fvo.getFileStreCours()+File.separator+fvo.getStreFileNm());
			mav.addObject("result", 1);
			
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
	@RequestMapping (value = { "/mng/page/{pagePath}/page.do"})
	public ModelAndView page(HttpServletRequest req, HttpServletResponse res,@PathVariable("pagePath") String pagePath) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("layout/mng/cmmn/page/"+pagePath.toLowerCase());
		return mav;
	}
	
}
