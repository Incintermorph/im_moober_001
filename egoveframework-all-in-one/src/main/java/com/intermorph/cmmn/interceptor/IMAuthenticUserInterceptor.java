/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.interceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.intermorph.cmmn.egov.service.IMEgovResultSet;
import com.intermorph.cmmn.egov.service.IMEgovService;
import com.intermorph.cmmn.egov.service.IMLoginVO;

import egovframework.com.cmm.util.EgovUserDetailsHelper;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.interceptor
 * @File    : IMAuthenticUserInterceptor.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 3. 2
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
public class IMAuthenticUserInterceptor extends HandlerInterceptorAdapter {
	/** log */
	private static final Logger LOGGER = LoggerFactory.getLogger(IMAuthenticUserInterceptor.class);

	@Resource (name = "IMEgovService")
	private IMEgovService egovService;
	
	

	/** 사용자 페이지중  관리자 접근 권한 패턴 목록 */
	private List<String> adminAuthPatternList;
	
	public List<String> getAdminAuthPatternList() {
		return adminAuthPatternList;
	}

	public void setAdminAuthPatternList(List<String> adminAuthPatternList) {
		this.adminAuthPatternList = adminAuthPatternList;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//인증된사용자 여부
			boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();	
			//미민증사용자 체크
			String authorCode="ROLE_ANONYMOUS";
			//System.out.println(request.getHeader("Referer"));
			if(isAuthenticated) {
				
				List<String> authList = (List<String>) EgovUserDetailsHelper.getAuthorities();
				authorCode="ROLE_USER";
				//관리자 권한 체크
				if(authList.contains("ROLE_AGNT_ADMIN") || authList.contains("ROLE_ADMIN")){

					AntPathRequestMatcher antPathRequestMatcher = null;
					boolean adminAuthUrlPatternMatcher = false; //관리자 접근 가능 여부 
					for(String adminAuthPattern : adminAuthPatternList){
						antPathRequestMatcher = new AntPathRequestMatcher(adminAuthPattern);
						if(antPathRequestMatcher.matches(request)){
							adminAuthUrlPatternMatcher = true;
						}
					}
					//관리자 접근은 프론트 페이지는 할 수 없으나 예외 체크 
					if(!adminAuthUrlPatternMatcher) { 
						ModelAndView modelAndView = new ModelAndView("redirect:/mng/index.do");
						throw new ModelAndViewDefiningException(modelAndView);
					}
					
					request.setAttribute("adminMode", "Y");
				}
				
				
				
				
				IMLoginVO user = (IMLoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
				request.setAttribute("IMLoginUser", user);
			}else {
				
				LOGGER.debug("request.getRequestURI() : " + request.getRequestURI());
				LOGGER.debug("request.getContextPath() : " + request.getContextPath());
				if(request.getRequestURI().trim().toLowerCase().startsWith(request.getContextPath()+"/user")){
//					ModelAndView modelAndView = new ModelAndView("redirect:/cmmn/login.do?_paramReturnUrl="+request.getRequestURI());
					ModelAndView modelAndView = new ModelAndView("redirect:/cmmn/login.do");
					throw new ModelAndViewDefiningException(modelAndView);
				}
				
			}
			
			LOGGER.debug("menu authorCode : " + authorCode);
			
			
			List<IMEgovResultSet> menuList = egovService.selectListAuthMenuCache(authorCode);
			request.setAttribute("__menuList", menuList);
			
			Map<String, IMEgovResultSet> _admMenuMap = new HashMap<String, IMEgovResultSet>();
			Map<String, IMEgovResultSet> _admMenuUrlMap = new HashMap<String, IMEgovResultSet>();
			
			if(menuList!=null && menuList.size()>0) {
				for(IMEgovResultSet resultSet : menuList) {
					_admMenuMap.put(resultSet.getMenuManage().getMenuNo()+"", resultSet);
					_admMenuUrlMap.put(request.getContextPath()+resultSet.getProgrmManage().getURL(), resultSet);
				}
				
				
			}
			request.setAttribute("_menuMap", _admMenuMap);
			String nowMenuNo = request.getParameter("_paramMenuNo")==null?"":(String)request.getParameter("_paramMenuNo");
			String reqURL= request.getRequestURI();
				
			if(_admMenuUrlMap.get(reqURL)!=null) {
				nowMenuNo = _admMenuUrlMap.get(reqURL).getMenuManage().getMenuNo()+"";
			}
			

			request.setAttribute("_menuUrlMap", _admMenuUrlMap);
			
			
			request.setAttribute("nowMenuNo", nowMenuNo);
			if(!"".equals(nowMenuNo)){
				if(_admMenuMap.get(nowMenuNo)!=null) {
					request.setAttribute("nowMenuResultSet", _admMenuMap.get(nowMenuNo));
					String upMenuNo = _admMenuMap.get(nowMenuNo).getMenuManage().getUpperMenuId()+"";
					if(_admMenuMap.get(upMenuNo)!=null) {
						request.setAttribute("nowUpMenuResultSet", _admMenuMap.get(upMenuNo));
						String topMenuNo = _admMenuMap.get(upMenuNo).getMenuManage().getUpperMenuId()+"";
						if(_admMenuMap.get(topMenuNo)!=null) {
							request.setAttribute("nowTopMenuResultSet", _admMenuMap.get(topMenuNo));
						}
					}
				}
				
			}
			
			
		return true;
	}
}
