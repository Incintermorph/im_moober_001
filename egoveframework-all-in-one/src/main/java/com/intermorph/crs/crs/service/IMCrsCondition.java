/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.crs.crs.service;

import java.io.Serializable;

import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.crs.aplcnt.service.IMCrsAplcntCondition;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.crs.crs.vo.condition
 * @File : IMCrsCondition.java
 * @Title : 과정(운영과정)
 * @date : 2022.02.28 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class IMCrsCondition extends BaseCondition implements Serializable {
    
	/** 검색 과정 마스터 ID */
	private String scCrsMstrId;
	private String scCrsId;
	private String scAgncyId;
	/** 검색 과정마스터명 */
	private String scCrsMstrNm;
	/*등급*/
	private String scCrsGrdCdv;
	/*구분*/
	private String scCrsDvsnCdv;
	/*제외 구분 */
	private String scNotCrsDvsnCdv;
	/*상태*/
	private String scSttsCdv;
	/**
	 * 통계 여부 
	 */
	private String scStatYn;
	/*진행상태*/
	private String scPrgrsSttsCdv;
	private String scEduYear;
	private String scEduRnd;
	private String scProcType;
	private String excelType;
	
	private IMCrsAplcntCondition aplcnt;
	
	
	public String getScCrsMstrId() {
		return scCrsMstrId;
	}
	public void setScCrsMstrId(String scCrsMstrId) {
		this.scCrsMstrId = scCrsMstrId;
	}
	public String getScCrsId() {
		return scCrsId;
	}
	public void setScCrsId(String scCrsId) {
		this.scCrsId = scCrsId;
	}
	public String getScCrsMstrNm() {
		return scCrsMstrNm;
	}
	public void setScCrsMstrNm(String scCrsMstrNm) {
		this.scCrsMstrNm = scCrsMstrNm;
	}
	public String getScCrsGrdCdv() {
		return scCrsGrdCdv;
	}
	public void setScCrsGrdCdv(String scCrsGrdCdv) {
		this.scCrsGrdCdv = scCrsGrdCdv;
	}
	public String getScCrsDvsnCdv() {
		return scCrsDvsnCdv;
	}
	public void setScCrsDvsnCdv(String scCrsDvsnCdv) {
		this.scCrsDvsnCdv = scCrsDvsnCdv;
	}
	public String getScSttsCdv() {
		return scSttsCdv;
	}
	public void setScSttsCdv(String scSttsCdv) {
		this.scSttsCdv = scSttsCdv;
	}
	public String getScPrgrsSttsCdv() {
		return scPrgrsSttsCdv;
	}
	public void setScPrgrsSttsCdv(String scPrgrsSttsCdv) {
		this.scPrgrsSttsCdv = scPrgrsSttsCdv;
	}
	public String getScAgncyId() {
		return scAgncyId;
	}
	public void setScAgncyId(String scAgncyId) {
		this.scAgncyId = scAgncyId;
	}
	public String getScEduYear() {
		return scEduYear;
	}
	public void setScEduYear(String scEduYear) {
		this.scEduYear = scEduYear;
	}
	public String getScEduRnd() {
		return scEduRnd;
	}
	public void setScEduRnd(String scEduRnd) {
		this.scEduRnd = scEduRnd;
	}
	public String getScProcType() {
		return scProcType;
	}
	public void setScProcType(String scProcType) {
		this.scProcType = scProcType;
	}
	public IMCrsAplcntCondition getAplcnt() {
		return aplcnt;
	}
	public void setAplcnt(IMCrsAplcntCondition aplcnt) {
		this.aplcnt = aplcnt;
	}
	public String getScStatYn() {
		return scStatYn;
	}
	public void setScStatYn(String scStatYn) {
		this.scStatYn = scStatYn;
	}
	public String getScNotCrsDvsnCdv() {
		return scNotCrsDvsnCdv;
	}
	public void setScNotCrsDvsnCdv(String scNotCrsDvsnCdv) {
		this.scNotCrsDvsnCdv = scNotCrsDvsnCdv;
	}
	public String getExcelType() {
		return excelType;
	}
	public void setExcelType(String excelType) {
		this.excelType = excelType;
	}
	
}