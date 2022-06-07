/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.web;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.cmm.SessionVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovResourceCloseHelper;
import egovframework.com.cmm.web.EgovImageProcessController;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.web
 * @File    : IMImageProcessController.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 2. 23
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
@Controller
public class IMImageProcessController extends HttpServlet {
	 @Resource(name = "EgovFileMngService")
	    private EgovFileMngService fileService;

	    private static final Logger LOGGER = LoggerFactory.getLogger(EgovImageProcessController.class);

	    /**
	     * 첨부된 이미지에 대한 미리보기 기능을 제공한다.
	     *
	     * @param atchFileId
	     * @param fileSn
	     * @param sessionVO
	     * @param model
	     * @param response
	     * @throws Exception
	     */
	    @RequestMapping (value = { "/im/cmmn/getImage.do",  "/cmmn/im/getImage.do","/cmmn/image/selectOne.do"})
	    public void getImageInf(SessionVO sessionVO, ModelMap model, @RequestParam Map<String, Object> commandMap, HttpServletResponse response) throws Exception {

			//@RequestParam("atchFileId") String atchFileId,
			//@RequestParam("fileSn") String fileSn,
			String atchFileId = (String)commandMap.get("atchFileId");
			String fileSn = (String)commandMap.get("fileSn");
			String thumb = (String)commandMap.get("thumb");
			String addStr="";
			if("1".equals(thumb)  || "2".equals(thumb)|| "3".equals(thumb)) {
				addStr=".thumb"+thumb+".jpg";
			}

			FileVO vo = new FileVO();

			vo.setAtchFileId(atchFileId);
			vo.setFileSn(fileSn);

			//------------------------------------------------------------
			// fileSn이 없는 경우 마지막 파일 참조
			//------------------------------------------------------------
			if (fileSn == null || fileSn.equals("")) {
				int newMaxFileSN = fileService.getMaxFileSN(vo);
				vo.setFileSn(Integer.toString(newMaxFileSN - 1));
			}
			//------------------------------------------------------------

			FileVO fvo = fileService.selectFileInf(vo);

			//String fileLoaction = fvo.getFileStreCours() + fvo.getStreFileNm();

			File file = null;
			FileInputStream fis = null;

			BufferedInputStream in = null;
			ByteArrayOutputStream bStream = null;

			try {
			    file = new File(fvo.getFileStreCours(), fvo.getStreFileNm()+addStr);
			    fis = new FileInputStream(file);

			    in = new BufferedInputStream(fis);
			    bStream = new ByteArrayOutputStream();

			    int imgByte;
			    while ((imgByte = in.read()) != -1) {
			    	bStream.write(imgByte);
			    }

				String type = "";

				if (fvo.getFileExtsn() != null && !"".equals(fvo.getFileExtsn())) {
				    if ("jpg".equals(fvo.getFileExtsn().toLowerCase())) {
					type = "image/jpeg";
				    } else {
				    	type = "image/" + fvo.getFileExtsn().toLowerCase();
				    }
				    /*type = "image/" + fvo.getFileExtsn().toLowerCase();*/

				} else {
					LOGGER.debug("Image fileType is null.");
				}

				response.setHeader("Content-Type", EgovWebUtil.removeCRLF(type));
				response.setContentLength(bStream.size());

				bStream.writeTo(response.getOutputStream());

				response.getOutputStream().flush();
				response.getOutputStream().close();

			} finally {
				EgovResourceCloseHelper.close(bStream, in, fis);
			}
	    }

}
