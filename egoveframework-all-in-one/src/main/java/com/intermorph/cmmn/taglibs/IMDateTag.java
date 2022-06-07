/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.taglibs;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

import com.intermorph.cmmn.IMGlobals;
import com.intermorph.cmmn.util.IMDateUtil;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.taglibs
 * @File    : IMDateTag.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 2. 17
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
public class IMDateTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	private Object yyyyMMddHHmmss;
	private String pattern;
	private String timezone;
	private int addDate = 0;
	
	
	public void setYyyyMMddHHmmss(Object yyyyMMddHHmmss) {
		this.yyyyMMddHHmmss = yyyyMMddHHmmss;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public void setAddDate(int addDate) {
		this.addDate = addDate;
	}

	public IMDateTag() {
		super();
		init();
	}

	private void init() {
		setYyyyMMddHHmmss(null);
		setPattern(null);
		setTimezone(null);
		setAddDate(0);
	}

	public int doStartTag() throws JspException {
		StringBuffer buffer = new StringBuffer();
		try {
			Date date = null;
			if (yyyyMMddHHmmss instanceof Date) {
				date = (Date)yyyyMMddHHmmss;
			} else if (yyyyMMddHHmmss instanceof String) {
				if (((String)yyyyMMddHHmmss).trim().length() == 0) {
					return super.doStartTag();
				}
				
				date = IMDateUtil.getFormatDate(StringUtils.rightPad((String)yyyyMMddHHmmss, 14, "0"), "yyyyMMddHHmmss");
			}
			if (date == null) {
				return super.doStartTag();
			}

			if (pattern == null || pattern.length() == 0) {
				pattern = IMGlobals.IM_FORMAT_DATE;
			}
			if (timezone == null || timezone.length() == 0) {
				timezone = IMGlobals.IM_FORMAT_TIMEZONE;
			}
			SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			TimeZone tz = TimeZone.getTimeZone(timezone);
			formatter.setTimeZone(tz);

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			if (addDate != 0) {
				calendar.add(Calendar.DATE, addDate);
			}
			buffer.append(formatter.format(calendar.getTime()));

			pageContext.getOut().write(buffer.toString());
		} catch (Exception e) {
			throw new JspException(e);
		}
		return super.doStartTag();
	}

	public int doEndTag() throws JspException {
		init();
		return super.doEndTag();
	}
}
