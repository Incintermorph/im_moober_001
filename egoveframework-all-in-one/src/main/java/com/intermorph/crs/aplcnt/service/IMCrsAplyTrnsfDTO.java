/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.crs.aplcnt.service;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.crs.aplcnt.service
 * @File    : IMCrsAplyTrnsfDTO.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 5. 13
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
public class IMCrsAplyTrnsfDTO {
	private String  uniqId;
	private String  uniqDt;
	private String crsAplcntId;
	private String crsAplcntDt;
	private String crsAplcntResult;
	private String wtstAplcntId;
	private String wtstAplcntDt;
	private String wtstAplcntResult;
	
	public String getUniqId() {
		return uniqId;
	}
	public void setUniqId(String uniqId) {
		this.uniqId = uniqId;
	}
	public String getCrsAplcntId() {
		return crsAplcntId;
	}
	public void setCrsAplcntId(String crsAplcntId) {
		this.crsAplcntId = crsAplcntId;
	}
	public String getWtstAplcntId() {
		return wtstAplcntId;
	}
	public void setWtstAplcntId(String wtstAplcntId) {
		this.wtstAplcntId = wtstAplcntId;
	}
	public String getUniqDt() {
		return uniqDt;
	}
	public void setUniqDt(String uniqDt) {
		this.uniqDt = uniqDt;
	}
	public String getCrsAplcntDt() {
		return crsAplcntDt;
	}
	public void setCrsAplcntDt(String crsAplcntDt) {
		this.crsAplcntDt = crsAplcntDt;
	}
	public String getWtstAplcntDt() {
		return wtstAplcntDt;
	}
	public void setWtstAplcntDt(String wtstAplcntDt) {
		this.wtstAplcntDt = wtstAplcntDt;
	}
	public String getCrsAplcntResult() {
		return crsAplcntResult;
	}
	public void setCrsAplcntResult(String crsAplcntResult) {
		this.crsAplcntResult = crsAplcntResult;
	}
	public String getWtstAplcntResult() {
		return wtstAplcntResult;
	}
	public void setWtstAplcntResult(String wtstAplcntResult) {
		this.wtstAplcntResult = wtstAplcntResult;
	}
	
	
}
