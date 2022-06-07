/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.util;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.intermorph.cmmn.IMGlobals;

import egovframework.rte.fdl.cryptography.EgovEnvCryptoService;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.util
 * @File    : IMFunc.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 3. 1
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
@Component
public class IMFunc {

	/** log */
	private static final Logger LOGGER = LoggerFactory.getLogger(IMFunc.class);

	/** 암호화서비스 */
	@Resource(name = "egovEnvCryptoService")
	private EgovEnvCryptoService cryptoService;
	
	

	/**
	 * 파일사이즈
	 * 
	 * @param size
	 * @return
	 */
	public static String fileSizeView(long filesize) {
		return IMStringUtil.fileSizeView(filesize);
	}
	
	/**
	 * textarea에 저장된  \r\n 을 "&lt;br>로 바꾼다
	 * 
	 * @param s
	 * @return
	 */
	public static String textToBr(String s) {
		if (s == null) {
			return "";
		}
		return s.replaceAll("\r\n", "<br>");
	}
	
	/**
	 * 암호화 
	 * @param s
	 * @return
	 */
	
	public static String encryptString(String s) {
		
		if (s == null) {
			return "";
		}
		try {
			return IMStringUtil.encryptString(s, IMGlobals.IM_SECURITY_KEY);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	/**
	 * 복호화 
	 * @param s
	 * @return
	 */
	
	public static String decryptString(String s) {
		
		if (s == null) {
			return "";
		}
		try {
			return IMStringUtil.decryptString(s, IMGlobals.IM_SECURITY_KEY);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	

}
