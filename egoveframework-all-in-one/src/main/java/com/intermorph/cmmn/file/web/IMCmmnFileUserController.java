/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.file.web;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.intermorph.cmmn.IMGlobals;
import com.intermorph.cmmn.base.BaseController;
import com.intermorph.cmmn.file.service.IMCmmnFileResultSet;
import com.intermorph.cmmn.file.service.IMCmmnFileService;
import com.intermorph.cmmn.file.service.IMCmmnFileVO;
import com.intermorph.cmmn.service.IMCmmnAccssHstryService;
import com.intermorph.cmmn.util.IMStringUtil;

import egovframework.com.cmm.EgovBrowserUtil;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovBasicLogger;
import egovframework.com.cmm.util.EgovResourceCloseHelper;
import egovframework.com.cmm.util.EgovUserDetailsHelper;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.file.web
 * @File : IMCmmnFileController.java
 * @Title : 공통파일관리
 * @date : 2022.05.13 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Controller
public class IMCmmnFileUserController extends BaseController {


    @Resource (name = "IMCmmnFileService")
	private IMCmmnFileService cmmnFileService;
	

	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileService;
	
	@Resource(name = "IMCmmnAccssHstryService")
	private IMCmmnAccssHstryService cmmnAccssHstryService;
	
	/**
	 * 공통파일관리 다운로드 
	 * @param req
	 * @param res
	 * @param cmmnFileIdEnc
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmmn/cmmnFile/{cmmnFileIdEnc}/download.do")
	public void modify(HttpServletRequest request, HttpServletResponse response,@PathVariable("cmmnFileIdEnc") String cmmnFileIdEnc) throws Exception {
		
		
		IMCmmnFileVO iMCmmnFile = new IMCmmnFileVO();
		
		iMCmmnFile.setCmmnFileId(IMStringUtil.decryptString(cmmnFileIdEnc, IMGlobals.IM_SECURITY_KEY));
		
		IMCmmnFileResultSet detail = (IMCmmnFileResultSet) cmmnFileService.selectDetailCmmnFile(iMCmmnFile);

		
		boolean isDownloadAuthenticated =true;
		boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		// 로그인 체크 
		if("Y".equals(detail.getCmmnFile().getMmbrAccssYn())){
			//미민증사용자 체크
			if(!isAuthenticated) {
				isDownloadAuthenticated =false;
				PrintWriter printwriter = response.getWriter();
				printwriter.println("<html>");
				printwriter.println("<br><br><br><h2>다운로드 권한이 없습니다.</h2>");
				printwriter.println("<br><br><br><center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
				printwriter.println("<br><br><br>&copy; webAccess");
				printwriter.println("</html>");
				
				printwriter.flush();
				printwriter.close();
			}
		}
		
		if(isDownloadAuthenticated) {
			FileVO fileVO = new FileVO();
			fileVO.setAtchFileId(detail.getCmmnFile().getFileId());
			fileVO.setFileSn("0");
			FileVO fvo = fileService.selectFileInf(fileVO);
			
			File uFile = new File(fvo.getFileStreCours(), fvo.getStreFileNm());
			long fSize = uFile.length();
			if (fSize > 0) {
				String mimetype = "application/x-msdownload";
				
				String userAgent = request.getHeader("User-Agent");
				HashMap<String,String> result = EgovBrowserUtil.getBrowser(userAgent);
				if ( !EgovBrowserUtil.MSIE.equals(result.get(EgovBrowserUtil.TYPEKEY)) ) {
					mimetype = "application/x-stuff";
				}
				cmmnFileService.updateCmmnFileDownCnt(iMCmmnFile);
				cmmnAccssHstryService.insertCmmnAccssHstry(request, "IM_CMMN_ACCSS_HSTRY", iMCmmnFile.getCmmnFileId(), "download");
				// 다운로드 파일명 수정 
				String downfileName=detail.getCmmnFile().getFileNm()+"."+fvo.getFileExtsn();
				String contentDisposition = EgovBrowserUtil.getDisposition(downfileName,userAgent,"UTF-8");
				//response.setBufferSize(fSize);	// OutOfMemeory 발생
				response.setContentType(mimetype);
				//response.setHeader("Content-Disposition", "attachment; filename=\"" + contentDisposition + "\"");
				response.setHeader("Content-Disposition", contentDisposition);
				response.setHeader("Content-Length", String.valueOf(fSize));
				//response.setContentLengthLong(fSize);

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
		
	}
	
	
	
	

	
}