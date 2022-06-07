package com.intermorph.cmmn.taglibs;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

import egovframework.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;

public class IMUserPaginationRenderer extends AbstractPaginationRenderer implements ServletContextAware{
	private ServletContext servletContext;

	public IMUserPaginationRenderer() {

	}

	public void initVariables(){

		firstPageLabel    = "<a href=\"?pageIndex={1}\" class=\"img start\" onclick=\"{0}({1});return false; \"><span class=\"sr_only\">처음</span></a>";
		previousPageLabel = "<a href=\"?pageIndex={1}\" class=\"img prev\" onclick=\"{0}({1});return false; \"><span class=\"sr_only\">이전</span></a>";
		
        currentPageLabel  = "<span class=\"on\">{0}</span>";
        otherPageLabel    = "<a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \">{2}</a>";
        
        nextPageLabel    = "<a href=\"?pageIndex={1}\" class=\"img next\" onclick=\"{0}({1});return false; \"><span class=\"sr_only\">다음</span></a>";
        lastPageLabel    = "<a href=\"?pageIndex={1}\" class=\"img end\" onclick=\"{0}({1});return false; \"><span class=\"sr_only\">마지막</span></a>";

	}



	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		initVariables();
	}

}
