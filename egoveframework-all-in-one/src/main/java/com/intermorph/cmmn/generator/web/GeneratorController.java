/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.generator.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.intermorph.cmmn.base.BaseController;
import com.intermorph.cmmn.exception.IMProcErrors;
import com.intermorph.cmmn.exception.IMProcException;
import com.intermorph.cmmn.generator.service.GeneratorService;
import com.intermorph.cmmn.generator.service.InputVO;
import com.intermorph.cmmn.generator.service.TableVO;
import com.intermorph.cmmn.util.IMDateUtil;
import com.intermorph.cmmn.util.IMFileUtil;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.utl.fcc.service.EgovStringUtil;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.generator.web
 * @File : GeneratorController.java
 * @Title : {간단한 프로그램의 명칭을 기록}
 * @date : 2022. 2. 21
 * @author : 노성용
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Controller
public class GeneratorController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(GeneratorController.class);

	@Resource(name = "GeneratorService")
	private GeneratorService generatorService;
	
	public final  String GENERATED_FILENAME = "IMGenerateFile";
	public final  String GENERATOR_TMP_DIR = "generatortmp";

	/**
	 * 테이블 목록
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/generator/selectList.do")
	public ModelAndView selectList(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();

		mav.addObject("listTable", generatorService.selectListTable(""));

		mav.setViewName("layout/mng/cmmn/generator/selectListGenerator");

		return mav;
	}

	/**
	 * 테이블 목록
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/generator/insert.do")
	public ModelAndView insert(HttpServletRequest req, HttpServletResponse res, TableVO table) throws Exception {
		ModelAndView mav = new ModelAndView();

		String projectName = req.getParameter("projectName") == null ? "" : (String) req.getParameter("projectName");
		String packageName = req.getParameter("packageName") == null ? "" : (String) req.getParameter("packageName");
		String componentName = req.getParameter("componentName") == null ? ""
				: (String) req.getParameter("componentName");
		String authorName = req.getParameter("authorName") == null ? "" : (String) req.getParameter("authorName");
		String tableSchema = "";

		String[] tableNames = req.getParameterValues("tableNames");
		String[] generateFiles = req.getParameterValues("generateFiles");

		//String GENERATED_FILENAME = "IMGenerateFile.zip";

		if (tableNames != null && tableNames.length > 0 && generateFiles != null && generateFiles.length > 0) {
			String generatedPath = EgovStringUtil.getRandomStr("a".charAt(0), "z".charAt(0))
					+ IMDateUtil.getImToday("yyyyMMddHHmmss");
			String dir = EgovProperties.getProperty("Globals.fileStorePath") +GENERATOR_TMP_DIR+"/" + generatedPath;
			String generatedFile = dir + "/" + GENERATED_FILENAME +"_" +generatedPath+".zip";

			File targetDir = new File(dir);
			if (!targetDir.exists()) {
				targetDir.mkdirs();
			}
			for (Long idx : table.getCheckIndexs()) {
				InputVO inputVO = new InputVO();
				inputVO.setProjectName(projectName);
				inputVO.setPackageName(packageName);
				inputVO.setComponentName(componentName);
				inputVO.setAuthorName(authorName);
				inputVO.setGeneratePath(dir);
				inputVO.setTableName(table.getTableNames()[idx.intValue()]);
				inputVO.setTableComment(table.getTableComments()[idx.intValue()]);
				inputVO.setTableSchema(tableSchema);
 
				for (String generateFile : generateFiles) {
					generatorService.generate(generateFile, inputVO);
				}
			}
			
			List<String> files = new ArrayList<String>();
			List<File> fileList = IMFileUtil.list(dir);
			for (File data : fileList) {
				files.add(data.getAbsolutePath());
			}

			IMFileUtil.zip(generatedFile, files, "UTF-8");
			mav.addObject("generatedPath", generatedPath);

		}

		mav.setViewName("jsonView");
		return mav;
	}
	
	
	/**
	 * 파일 응답 - download
	 * 
	 * @param req
	 * @param res
	 * @throws Exception
	 */
	@RequestMapping ("/mng/generator/download.do")
	public void responseFile(HttpServletRequest req, HttpServletResponse res) throws Exception {

		FileInputStream fin = null;
		BufferedInputStream bis = null;
		ServletOutputStream sos = null;
		String contentType = "application/octet-stream";
		String generatedPath = req.getParameter("generatedPath") == null ? "" : (String) req.getParameter("generatedPath"); 
	

		if (generatedPath == null) {
			throw new IMProcException(IMProcErrors.파일프로세스에러);
		}

		try {
			File generatedFile = new File(EgovProperties.getProperty("Globals.fileStorePath") +GENERATOR_TMP_DIR+"/" + generatedPath + "/" + GENERATED_FILENAME +"_" +generatedPath+".zip");
			if (generatedFile.exists()) {
				res.setContentType(contentType);
				res.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(GENERATED_FILENAME +"_" +generatedPath+".zip", "UTF-8") + ";");
				if (generatedFile.length() > 0) {
					res.setHeader("Content-Length", String.valueOf(generatedFile.length()));
				}
				res.setHeader("Content-Type", contentType);
				res.setHeader("Content-Transfer-Encoding", "binary");
				res.setHeader("Pragma", "no-cache");
				res.setHeader("Expires", "0");

				fin = new FileInputStream(generatedFile);
				bis = new BufferedInputStream(fin);

				sos = res.getOutputStream();
				int read = 0;
				while ((read = bis.read()) != -1) {
					sos.write(read);
				}
			} else {
				throw new IMProcException(IMProcErrors.파일프로세스에러);
			}
		} catch (Exception e) {
			throw new IMProcException(IMProcErrors.파일프로세스에러);
		} finally {
			if (sos != null) {
				sos.close();
			}
			if (bis != null) {
				bis.close();
			}
			if (fin != null) {
				fin.close();
			}
			IMFileUtil.deleteDirectory(EgovProperties.getProperty("Globals.fileStorePath")  + GENERATOR_TMP_DIR+"/" + generatedPath + "/");
		}

	}

}
