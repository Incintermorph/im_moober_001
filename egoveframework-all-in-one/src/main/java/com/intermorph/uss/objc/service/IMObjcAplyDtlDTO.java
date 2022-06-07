/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.objc.service;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.objc.service
 * @File    : IMObjcAplyDtlDTO.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 4. 6
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
public class IMObjcAplyDtlDTO {
	/** 과정등급코드값 */
	private String crsGrdCdv;
	/** 과정구분코드값 */
	private String crsDvsnCdv;
	
	/**  교육년도 (edu_year) */
	private String eduYear;
	/**  교육차수 (edu_rnd) */
	private String eduRnd;
	
	public String getCrsGrdCdv() {
		return crsGrdCdv;
	}
	public void setCrsGrdCdv(String crsGrdCdv) {
		this.crsGrdCdv = crsGrdCdv;
	}
	public String getCrsDvsnCdv() {
		return crsDvsnCdv;
	}
	public void setCrsDvsnCdv(String crsDvsnCdv) {
		this.crsDvsnCdv = crsDvsnCdv;
	}
	public String getEduYear() {
		return eduYear;
	}
	public void setEduYear(String eduYear) {
		this.eduYear = eduYear;
	}
	public String getEduRnd() {
		return eduRnd;
	}
	public void setEduRnd(String eduRnd) {
		this.eduRnd = eduRnd;
	}
	
	
	
}
