/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.taglibs;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;


/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.taglibs
 * @File    : IMPageBtnTag.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 2. 18
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class IMPageBtnTag extends TagSupport {
	private String type;
	private String auth;
	private String reqName;
	private boolean success;
	
	public IMPageBtnTag() {
		super();
		init();
	}

	
	public void setAuth(String auth) {
		this.auth = auth;
	}


	public void setType(String type) {
		this.type = type;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public void setReqName(String reqName) {
		this.reqName = reqName;
	}


	private void init() {
		setType(null);
		setAuth("A");
		setReqName(null);
		setSuccess(false);
	}

	public int doStartTag() throws JspException {
		try {
			StringBuffer buffer = new StringBuffer(100);
			
			if (type == null || type.length() == 0) {
				buffer.append("attribute 'type' undefined");
				pageContext.getOut().write(buffer.toString());
				return super.doStartTag();
			}
			if (reqName == null || reqName.length() == 0) {
				buffer.append("attribute 'reqName' undefined");
				pageContext.getOut().write(buffer.toString());
				return super.doStartTag();
			}
			
			setSuccess(true);

			
		
			buffer.append("<div> ");
		
			pageContext.getOut().write(buffer.toString());

		} catch (Exception io) {
			throw new JspException(io);
		}

		return EVAL_BODY_INCLUDE;
	}

	public int doEndTag() throws JspException {
		try {
			if (success) {

				StringBuffer buffer = new StringBuffer(100);
				if("I".equals(type)) {
					buffer.append("<a href=\"#\" onclick=\""+reqName+".regist();return false;\" class=\"bt_new bk\">신규등록</a> ");
					buffer.append("<a href=\"#\" onclick=\""+reqName+".del();return false;\"  class=\"bt_del red\">삭제</a> ");
				}else if("R".equals(type)) {
						buffer.append("<a href=\"#\" onclick=\""+reqName+".list();return false;\" class=\"ad_btn bk\">목록</a> ");
						buffer.append("<a href=\"#\" onclick=\""+reqName+".save();return false;\" class=\"ad_btn green\">저장</a> ");
				}else if("M".equals(type)) {
					buffer.append("<a href=\"#\" onclick=\""+reqName+".list();return false;\" class=\"ad_btn bk\">목록</a> ");
					buffer.append("<a href=\"#\" onclick=\""+reqName+".save();return false;\" class=\"ad_btn green\">저장</a> ");
					buffer.append("<a href=\"#\" onclick=\""+reqName+".del();return false;\" class=\"ad_btn bk\">삭제</a> ");
					
			    }
				buffer.append("</div>");	
				pageContext.getOut().write(buffer.toString());
			}

		} catch (Exception io) {
			throw new JspException(io);
		}
		return EVAL_PAGE;
	}
}
