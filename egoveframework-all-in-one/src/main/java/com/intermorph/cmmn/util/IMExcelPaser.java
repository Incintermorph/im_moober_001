/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.util;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import net.sf.jxls.reader.ReaderBuilder;
import net.sf.jxls.reader.ReaderConfig;
import net.sf.jxls.reader.XLSReadStatus;
import net.sf.jxls.reader.XLSReader;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.xml.sax.SAXException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.util
 * @File    : IMExcelPaser.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 5. 6
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
public class IMExcelPaser {

	protected final Log log = LogFactory.getLog(getClass());

	/** The extension to look for existing templates */
	private static final String DEFAULT_EXTENSION = ".xml";


	/** The prefix to look for existing templates */
	private static final String DEFAULT_PREFIX = "/com/intermorph/excel/paser/";

	/**
	 * Set the prefix that gets prepended to excel of file names when building a URL.
	 */
	private String prefix = DEFAULT_PREFIX;

	/**
	 * Set the suffix that gets appended to excel of file names when building a URL.
	 */
	private String suffix = DEFAULT_EXTENSION;

	/**
	 * You can override this behaviour and allow to skip errors and continue processing with setSkipErrors(true) method of ReaderConfig class.
	 */
	private Boolean skipErrors = false;

	/**
	 * BeanUtils Converters for primitive types return a default value when a conversion error occurs.
	 */
	private Boolean useDefaultValuesForPrimitiveTypes = false;

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	/**
	 * Set the URL of the Excel workbook source, without localization part nor extension.
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public Boolean getSkipErrors() {
		return skipErrors;
	}

	public void setSkipErrors(Boolean skipErrors) {
		this.skipErrors = skipErrors;
	}

	public void setUseDefaultValuesForPrimitiveTypes(Boolean useDefaultValuesForPrimitiveTypes) {
		this.useDefaultValuesForPrimitiveTypes = useDefaultValuesForPrimitiveTypes;
	}

	/**
	 * 엑셀 파싱
	 * 
	 * @param rowDataes
	 * @param saveFile
	 * @param jxlsConfigName
	 * @return
	 * @throws IOException
	 * @throws SAXException
	 * @throws InvalidFormatException
	 */
	public XLSReadStatus parse(Map<String, Object> rowDataes, File saveFile, String jxlsConfigName) throws IOException, SAXException, InvalidFormatException {
		return parse(rowDataes, new FileInputStream(saveFile), jxlsConfigName);
	}

	/**
	 * 엑셀 파싱
	 * 
	 * @param rowDataes
	 * @param saveFile
	 * @param jxlsConfigName
	 * @return
	 * @throws IOException
	 * @throws SAXException
	 * @throws InvalidFormatException
	 */
	public XLSReadStatus parse(Map<String, Object> rowDataes, InputStream saveFile, String jxlsConfigName) throws IOException, SAXException,
			InvalidFormatException {

		ReaderConfig.getInstance().setSkipErrors(skipErrors);
		ReaderConfig.getInstance().setUseDefaultValuesForPrimitiveTypes(useDefaultValuesForPrimitiveTypes);

		InputStream inputXML = new BufferedInputStream(getClass().getResourceAsStream(getXmlConfig(jxlsConfigName)));
		XLSReader mainReader = ReaderBuilder.buildFromXML(inputXML);
		InputStream xlsStream = new BufferedInputStream(saveFile);

		return mainReader.read(xlsStream, rowDataes);

	}

	/**
	 * Jxls Config Path를 만든다.
	 * 
	 * @param jxlsConfigName
	 * @return
	 */
	public String getXmlConfig(String jxlsConfigName) {
		return this.prefix + jxlsConfigName + this.suffix;
	}
}
