/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.taglibs;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.intermorph.cmmn.base.BaseCondition;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.taglibs
 * @File : IMPageFormTag.java
 * @Title : {간단한 프로그램의 명칭을 기록}
 * @date : 2022. 2. 18
 * @author : 노성용
 * @descrption : {상세한 프로그램의 용도를 기록}
 */

@SuppressWarnings("serial")
public class IMPageFormTag extends TagSupport {
	private String formName;
	private String type;
	private boolean success;
	private BaseCondition condition;

	public IMPageFormTag() {
		super();
		init();
	}

	private void init() {
		setType(null);
		setFormName(null);
		setCondition(null);
		setSuccess(false);
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public void setCondition(BaseCondition condition) {
		this.condition = condition;
	}

	public int doStartTag() throws JspException {
		try {
			StringBuffer buffer = new StringBuffer(100);
			if (formName == null || formName.length() == 0) {
				buffer.append("attribute 'formName' undefined");
				return super.doStartTag();
			}
			if (type == null || type.length() == 0) {
				buffer.append("attribute 'type' undefined");
				pageContext.getOut().write(buffer.toString());
				return super.doStartTag();
			}
			if (condition == null) {
				buffer.append("attribute 'condition' undefined");
				pageContext.getOut().write(buffer.toString());
				return super.doStartTag();
			}
			setSuccess(true);

			buffer.append(" <form name=\"" + formName + "\" id=\"" + formName
					+ "\"  method=\"post\" onsubmit=\"return false;\">");
			if ("search".equals(type)) {
				buffer.append("<input type=\"hidden\" name=\"currentPageNo\"  value=\"1\" />");
			} else {
				buffer.append("<input type=\"hidden\" name=\"currentPageNo\"  value=\"" + condition.getCurrentPageNo()
						+ "\" />");
			}

			buffer.append("<input type=\"hidden\" name=\"recordCountPerPage\"      value=\""
					+ condition.getRecordCountPerPage() + "\" />"); // recordCountPerPage
			buffer.append("<input type=\"hidden\" name=\"pageSize\"      value=\"" + condition.getPageSize() + "\" />");
			buffer.append(
					"<input type=\"hidden\" name=\"sercharAll\"      value=\"" + condition.getSercharAll() + "\" />");
			buffer.append(
					"<input type=\"hidden\" name=\"orderbyKey\"      value=\"" + condition.getOrderbyKey() + "\" />");
			if (!"search".equals(type)) {
				// 검색은 별도 지정
				if (condition.getScKey() != null) {
					buffer.append(
							"<input type=\"hidden\" name=\"scKey\"      value=\"" + condition.getScKey() + "\" />");
				}
				if (condition.getScWord() != null) {
					buffer.append(
							"<input type=\"hidden\" name=\"scWord\"      value=\"" + condition.getScWord() + "\" />");
				}
			}

			pageContext.getOut().write(buffer.toString());

		} catch (Exception io) {
			throw new JspException(io);
		}

		return EVAL_BODY_INCLUDE;
	}

	public int doEndTag() throws JspException {
		try {
			if (success) {

				pageContext.getOut().write("</form>");
			}

		} catch (Exception io) {
			throw new JspException(io);
		}
		return EVAL_PAGE;
	}
}
