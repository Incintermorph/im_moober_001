/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.util;


import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.AbstractView;
/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.util
 * @File    : ExcelDownloadView.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 3. 18
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
public class ExcelDownloadView extends AbstractView {
	
	/** Template File Name */
	public final static String TEMPLATE_FILE_NAME = "templateFileName";

	/** Download File Name */
	public final static String DOWNLOAD_FILE_NAME = "downloadFileName";

	/** The content type for an Excel response */
	private static final String CONTENT_TYPE = "application/vnd.ms-excel";

	/** The extension to look for existing templates */
	private static final String DEFAULT_EXTENSION = ".xlsx";

	/** The prefix to look for existing templates */
	private static final String DEFAULT_PREFIX = "/com/intermorph/excel/download/";

	/** File Name Encoding */
	private String fileNameEnconding = "UTF-8";

	/**
	 * Set the prefix that gets prepended to excel of file names when building a URL.
	 */
	private String prefix = DEFAULT_PREFIX;

	/**
	 * Set the suffix that gets appended to excel of file names when building a URL.
	 */
	private String suffix = DEFAULT_EXTENSION;

	/**
	 * Default Constructor. Sets the content type of the view to "application/vnd.ms-excel".
	 */
	public ExcelDownloadView() {
		setContentType(CONTENT_TYPE);
	}

	public void setFileNameEnconding(String fileNameEnconding) {
		this.fileNameEnconding = fileNameEnconding;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	/**
	 * Set the URL of the Excel workbook source, without localization part nor extension.
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	protected boolean generatesDownloadContent() {
		return true;
	}

	/**
	 * Renders the Excel view, given the specified model.
	 */
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		InputStream resource = getTemplateReource(model, request);

		XLSTransformer transformer = new XLSTransformer();
		Workbook workbook = transformer.transformXLS(resource, model);

		StringBuffer content = new StringBuffer();
		content.append("attachment;fileName=\"");
		content.append(URLEncoder.encode((String)model.get(DOWNLOAD_FILE_NAME) + this.suffix, this.fileNameEnconding));
		content.append("\";");

		response.setHeader("Content-Disposition", content.toString());
		// Set the content type.
		response.setContentType(getContentType());

		// Flush byte array to servlet output stream.
		ServletOutputStream out = response.getOutputStream();
		workbook.write(out);

		out.flush();
	}

	/**
	 * Creates the workbook reource from an existing XLS document.
	 * 
	 * @param url the URL of the Excel template without localization part nor extension
	 * @param request current HTTP request
	 * @return the Resource
	 * @throws Exception in case of failure
	 */
	protected InputStream getTemplateReource(Map<String, Object> model, HttpServletRequest request) throws Exception {
		if (model.get(DOWNLOAD_FILE_NAME) == null) {
			throw new Exception("The downloadFileName is empty. It is required.");
		}

		if (model.get(TEMPLATE_FILE_NAME) == null) {
			throw new Exception("The TemplefileName is empty. It is required.");
		}

		String templateUrl = getTemplateUrl(model.get(TEMPLATE_FILE_NAME));
		InputStream inputXML = new BufferedInputStream(getClass().getResourceAsStream(templateUrl));

		// Create the Excel document from the source.
		if (logger.isDebugEnabled()) {
			logger.debug("Loading Excel workbook from " + templateUrl);
		}

		return inputXML;
	}

	/**
	 * Template url를 만든다.
	 * 
	 * @param templateFileName
	 * @return
	 */
	protected String getTemplateUrl(Object templateFileName) {
		return this.prefix + templateFileName + this.suffix;
	}
	/**
	 * downloadName 
	 * @param req
	 * @return
	 */
	public static String getDownloadName(HttpServletRequest req) {
	String downloadName =  IMStringUtil.getParameter(req, "downloadName");
		
		if("".equals(downloadName)) {
			downloadName = "download_"+IMDateUtil.getImToday("yyyyMMdd");
		}else {
			downloadName = downloadName+"_"+IMDateUtil.getImToday("yyyyMMdd");
		}
		
		return downloadName;
	}
}
