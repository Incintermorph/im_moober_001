/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.taglibs;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

import egovframework.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.taglibs
 * @File    : IMAdminPaginationRenderer.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 2. 17
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
public class IMAdminPaginationRenderer  extends AbstractPaginationRenderer implements ServletContextAware{
	private ServletContext servletContext;

	public IMAdminPaginationRenderer() {

	}

	public void initVariables(){

		firstPageLabel    = "<a href=\"?pageIndex={1}\" class=\"img start\" onclick=\"{0}({1});return false; \"><span class=\"sr_only\">처음</span></a>";
		previousPageLabel = "<a href=\"?pageIndex={1}\" class=\"img prev\" onclick=\"{0}({1});return false; \"><span class=\"sr_only\">이전</span></a>";
		
        currentPageLabel  = "<a onClick=\"return false;\"><span class=\"on\">{0}</span></a>";
        otherPageLabel    = "<a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \"><span>{2}</span></a>";
        
        nextPageLabel    = "<a href=\"?pageIndex={1}\" class=\"img next\" onclick=\"{0}({1});return false; \"><span class=\"sr_only\">다음</span></a>";
        lastPageLabel    = "<a href=\"?pageIndex={1}\" class=\"img end\" onclick=\"{0}({1});return false; \"><span class=\"sr_only\">마지막</span></a>";

	}



	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		initVariables();
	}
}
