/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import egovframework.com.cmm.service.Globals;
import egovframework.com.cmm.util.EgovUserDetailsHelper;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.interceptor
 * @File    : IMAnonymousInterceptor.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 3. 3
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
public class IMAnonymousInterceptor extends HandlerInterceptorAdapter {
	/** log */
	private static final Logger LOGGER = LoggerFactory.getLogger(HandlerInterceptorAdapter.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//인증된사용자 여부
			boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();	

			if(isAuthenticated) {
				
				LOGGER.debug("isAuthenticated Go MainPage");
				ModelAndView modelAndView = new ModelAndView("redirect:"+Globals.MAIN_PAGE);
				throw new ModelAndViewDefiningException(modelAndView);
			}
			
		return true;
	}

}
