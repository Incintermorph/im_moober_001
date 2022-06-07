/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.cmmn.service;

import java.io.Serializable;
import java.text.ParseException;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.intermorph.cmmn.base.BaseVO;
import com.intermorph.cmmn.util.IMDateUtil;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.vo
 * @File : IMLgnSttsVO.java
 * @Title : 로그인 현황
 * @date : 2022.04.12 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class IMLgnSttsVO extends BaseVO implements Serializable {
	/**  고유ID (esntl_id) */
	private String esntlId;
	/**  수정일시 (mdfcn_dt) */
	private String mdfcnDt;
	/**  수정IP (mdfcn_ip) */
	private String mdfcnIp;
	/**  로그인횟수 (lgn_cnt) */
	private String lgnCnt;
	/**  세션 아이디 */
	private String lastSesinId;



	public String getLastMdfcnDtFormat() {
		try {
			return IMDateUtil.getFormatString(getLastMdfcnDt(), "yyyy.MM.dd HH:mm:ss");
		} catch (ParseException e) {
			return "";
		}
	}
	

	public String getEsntlId() {
	    return esntlId;
	}
	
	public void setEsntlId(String esntlId) {
	    this.esntlId = esntlId;
	}
	public String getMdfcnDt() {
	    return mdfcnDt;
	}
	
	public String getMdfcnDtFormat() {
		try {
			return IMDateUtil.getFormatString(getMdfcnDt(), "yyyy.MM.dd HH:mm:ss");
		} catch (ParseException e) {
			return "";
		}
	}
	
	
	public void setMdfcnDt(String mdfcnDt) {
	    this.mdfcnDt = mdfcnDt;
	}
	public String getMdfcnIp() {
	    return mdfcnIp;
	}
	
	public void setMdfcnIp(String mdfcnIp) {
	    this.mdfcnIp = mdfcnIp;
	}
	public String getLgnCnt() {
	    return lgnCnt;
	}
	
	public void setLgnCnt(String lgnCnt) {
	    this.lgnCnt = lgnCnt;
	}



	public String getLastSesinId() {
		return lastSesinId;
	}


	public void setLastSesinId(String lastSesinId) {
		this.lastSesinId = lastSesinId;
	}


	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}