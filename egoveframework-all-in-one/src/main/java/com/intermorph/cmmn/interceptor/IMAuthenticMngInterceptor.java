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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.intermorph.cmmn.IMGlobals;
import com.intermorph.cmmn.egov.service.IMEgovResultSet;
import com.intermorph.cmmn.egov.service.IMEgovService;
import com.intermorph.cmmn.egov.service.IMLoginVO;

import egovframework.com.cmm.util.EgovUserDetailsHelper;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.interceptor
 * @File : IMAuthenticMngInterceptor.java
 * @Title : {간단한 프로그램의 명칭을 기록}
 * @date : 2022. 2. 14
 * @author : 노성용
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
public class IMAuthenticMngInterceptor extends HandlerInterceptorAdapter {
	/** log */
	private static final Logger LOGGER = LoggerFactory.getLogger(IMAuthenticMngInterceptor.class);

	@Resource (name = "IMEgovService")
	private IMEgovService egovService;
	
	/** 관리자 접근 권한 패턴 목록 */
	private List<String> adminAuthPatternList;

	public List<String> getAdminAuthPatternList() {
		return adminAuthPatternList;
	}

	public void setAdminAuthPatternList(List<String> adminAuthPatternList) {
		this.adminAuthPatternList = adminAuthPatternList;
	}

	/**
	 * 관리자 추가 interceptor
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//System.out.println("request.getRequestURI() : " + request.getRequestURI());
		
		List<String> authList = (List<String>) EgovUserDetailsHelper.getAuthorities();
		
		//관리자 권한 체크
		if(!authList.contains("ROLE_AGNT_ADMIN") && !authList.contains("ROLE_ADMIN")){
			ModelAndView modelAndView = new ModelAndView("redirect:/uat/uia/egovLoginUsr.do?auth_error=1");
			throw new ModelAndViewDefiningException(modelAndView);
		}
		
		IMLoginVO user = (IMLoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		request.setAttribute("IMLoginUser", user);
		
		String loginUserAgncyId="";
		//기관 담당자 코드
		if(authList.contains("ROLE_AGNT_ADMIN")) {
			loginUserAgncyId=user.getGroupId();
		}
		
		request.setAttribute(IMGlobals.IM_LOGIN_USER_AGNCY_KEY, loginUserAgncyId);
		
		//권한 별 메뉴 처리 
		if(authList.contains("ROLE_AGNT_ADMIN") || authList.contains("ROLE_ADMIN")){
			String authorCode="";
			for(String auth : authList) {
				if(auth.indexOf("ADMIN")!=-1) {
					authorCode=auth;
				}
				
			}
			List<IMEgovResultSet> menuList = egovService.selectListAuthMenuCache(authorCode);
			request.setAttribute("_admMenuList", menuList);
			
			Map<String, IMEgovResultSet> _admMenuMap = new HashMap<String, IMEgovResultSet>();
			Map<String, IMEgovResultSet> _admMenuUrlMap = new HashMap<String, IMEgovResultSet>();
			
			if(menuList!=null && menuList.size()>0) {
				for(IMEgovResultSet resultSet : menuList) {
					_admMenuMap.put(resultSet.getMenuManage().getMenuNo()+"", resultSet);
					_admMenuUrlMap.put(request.getContextPath()+resultSet.getProgrmManage().getURL(), resultSet);
				}
				
				
			}
			request.setAttribute("_admMenuMap", _admMenuMap);
			String nowMenuNo = request.getParameter("_paramMenuNo")==null?"":(String)request.getParameter("_paramMenuNo");
			String reqURL= request.getRequestURI();
			//url 로 현재 메뉴 위치 확인 
			if("".equals(nowMenuNo)){
				
				if(_admMenuUrlMap.get(reqURL)!=null) {
					nowMenuNo = _admMenuUrlMap.get(reqURL).getMenuManage().getMenuNo()+"";
				}
			}
			
			if(reqURL!=null && !"".equals(reqURL)) {
				
				String[] arrUrl =  reqURL.split("/");
				String pageType="";
				if(arrUrl[arrUrl.length-1].toLowerCase().startsWith("selectlist")) {
					pageType ="SELECT";
				}else if(arrUrl[arrUrl.length-1].toLowerCase().startsWith("regist")) {
					pageType ="REGIST";
				}else if(arrUrl[arrUrl.length-1].toLowerCase().startsWith("modify")) {
					pageType ="MODIFY";
				}else if(arrUrl[arrUrl.length-1].toLowerCase().startsWith("view")) {
					pageType ="VIEW";
				}

				request.setAttribute("_pageType", pageType);
			}
			
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
			
			
			
		}
		
		return true;
	}
}
