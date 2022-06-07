/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn;

import com.intermorph.cmmn.util.IMDateUtil;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.service.Globals;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn
 * @File    : IMGlobals.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 2. 16
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
public class IMGlobals extends Globals {
	
	public static final String IM_CONF_PATH = EgovProperties.getPathProperty("Globals.IMConfigPath");
	
	public static final String LOGIN_DUPLICATION = EgovProperties.getProperty("Globals.login.duplication");
	public static final String SITE_DOMAIN = EgovProperties.getProperty("Globals.site.domain");
	public static final String NICE_ID = EgovProperties.getProperty("Globals.nice.id");
	public static final String NICE_PW = EgovProperties.getProperty("Globals.nice.pw");
	public static final String DAMO_KEYPATH = EgovProperties.getProperty("Globals.DAMO.KEYPATH");
	public static final String NICE_REQ = "LicenseMmbrHstry";
	public static final String IM_WEB_ADMIN = EgovProperties.getProperty("IM.WEB.ADMIN");
	
	public static final String IM_SEPARATOR = String.valueOf((char)0x01); 
	

	public static final String EXEXL_SESSION_KEY = "IM_EXEXL_RESULT_SESSTION_KEY";
	
	// static 이미지  CSS 경로 
	public static  String IM_WEB_STATIC = EgovProperties.getProperty(IM_CONF_PATH,"IM.WEB.STATIC");
	// 프론트 사이트 타이틀 
	public static final String IM_WEB_SITE_WWW_TITLE = EgovProperties.getProperty(IM_CONF_PATH,"IM.WEB.SITE.WWW.TITLE");
	// 관리자  사이트 타이틀 
	public static final String IM_WEB_SITE_MNG_TITLE = EgovProperties.getProperty(IM_CONF_PATH,"IM.WEB.SITE.MNG.TITLE");
	public static final String IM_SECURITY_KEY = EgovProperties.getProperty(IM_CONF_PATH,"IM.SECURITY.KEY");
	
	
	public static final String IM_FORMAT_TIMEZONE = EgovProperties.getProperty(IM_CONF_PATH,"IM.FORMAT.TIMEZONE");
	public static final String IM_FORMAT_DATE = EgovProperties.getProperty(IM_CONF_PATH,"IM.FORMAT.DATE");
	public static final String IM_FORMAT_DATEYM = EgovProperties.getProperty(IM_CONF_PATH,"IM.FORMAT.DATEYM");
	public static final String IM_FORMAT_DATETIME = EgovProperties.getProperty(IM_CONF_PATH,"IM.FORMAT.DATETIME");
	public static final String IM_FORMAT_DATETIMEHM = EgovProperties.getProperty(IM_CONF_PATH,"IM.FORMAT.DATETIMEHM");
	public static final String IM_FORMAT_DBDATE = EgovProperties.getProperty(IM_CONF_PATH,"IM.FORMAT.DBDATE");
	public static final String IM_FORMAT_DBDATETIME = EgovProperties.getProperty(IM_CONF_PATH,"IM.FORMAT.DBDATETIME");
	public static final String IM_FORMAT_DBDATETIMESTART = EgovProperties.getProperty(IM_CONF_PATH,"IM.FORMAT.DBDATETIMESTART");
	public static final String IM_FORMAT_DBDATETIMEEND = EgovProperties.getProperty(IM_CONF_PATH,"IM.FORMAT.DBDATETIMEEND");
	public static final String IM_IMAGES_EXTENSIONS = EgovProperties.getProperty(IM_CONF_PATH,"IM.IMAGES.EXTENSIONS");
	public static final String IM_FILES_EXTENSIONS = EgovProperties.getProperty(IM_CONF_PATH,"IM.FILES.EXTENSIONS");
	
	public static final int IM_IMAGES_HEIGHT_THUMB1 = Integer.parseInt(EgovProperties.getProperty(IM_CONF_PATH,"IM.IMAGES.HEIGHT.THUMB1"));
	public static final int IM_IMAGES_HEIGHT_THUMB2 = Integer.parseInt(EgovProperties.getProperty(IM_CONF_PATH,"IM.IMAGES.HEIGHT.THUMB2"));
	public static final int IM_IMAGES_HEIGHT_THUMB3 = Integer.parseInt(EgovProperties.getProperty(IM_CONF_PATH,"IM.IMAGES.HEIGHT.THUMB3"));
	
	public static final String IM_NOW_YEAR = IMDateUtil.getImToday("yyyy");
	public static final String IM_NOW_DATETIME = IMDateUtil.getImToday("yyyyMMddHHmmss");
	
	public static final String IM_USERPERMITCMMMDESCTBLID  ="MEMBER_PERMIT";
	public static final String IM_USERPERMIT_KEY  ="userPermitOption";
	

	public static final String IM_LOGIN_USER_AGNCY_KEY  ="loginUserAgncyId";
	
	public static final String IM_POTAL_SSOURL = EgovProperties.getProperty(IM_CONF_PATH,"IM.POTAL.SSOURL");
	public static final String IM_INFOINQ_U="update";
	public static final String IM_INFOINQ_D="delete";
	public static final String IM_INFOINQ_C="insert";
	public static final String IM_INFOINQ_R="select";
	public static final String IM_INFOINQ_S="selectList";
	public static final String IM_INFOINQ_E="selectExcel";
	
	
	/**
	 * IM설정 확인
	 *  설정 위치 : /resources/egovframework/egovProps/conf/IMConfig.properties  
	 * @param propertyKey
	 * @return
	 * @throws Exception
	 */
	public static String getProperty(String propertyKey) throws Exception {
		return EgovProperties.getProperty(IM_CONF_PATH, propertyKey);
	}
	
	/**
	 * 글로벌 설정 확인  
	 * @param propertyKey
	 * @return
	 * @throws Exception
	 */
	public static String getGlobalProperty(String propertyKey) throws Exception {
		return EgovProperties.getProperty(propertyKey);
	}
	
}
