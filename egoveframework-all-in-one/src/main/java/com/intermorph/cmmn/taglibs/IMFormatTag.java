/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.taglibs;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.taglibs
 * @File    : IMFormatTag.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 3. 3
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
public class IMFormatTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	private String value;
	private String pattern;
	private int round = -1;

	public void setValue(String value) {
		this.value = value;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public void init() {
		setValue(null);
		setPattern(null);
	}

	public int doStartTag() throws JspException {
		StringBuffer buffer = new StringBuffer();
		if (value == null || value.equals("")) {
			return super.doStartTag();
		}
		try {
			if (this.pattern == null || "".equals(this.pattern)) {
				setPattern("#,###,###");
			}
			java.text.DecimalFormat df = new java.text.DecimalFormat(this.pattern);
			try {
				if (round > -1) {
					int decimalPlaces = pattern.length() - pattern.indexOf(".");
					BigDecimal bigDecimal = new BigDecimal(value);
					bigDecimal = bigDecimal.setScale(decimalPlaces - 1, RoundingMode.HALF_UP);

					buffer.append(df.format(bigDecimal.doubleValue()));
				} else {
					buffer.append(df.format(Long.parseLong(value)));
				}

			} catch (NumberFormatException e) {
				buffer.append(df.format(Double.parseDouble(value)));
			}
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
