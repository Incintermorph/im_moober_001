/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.wtst.place.service;

import java.io.Serializable;

import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.wtst.aplcnt.service.IMWtstAplcntCondition;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.wtst.place.vo.condition
 * @File : IMWtstPlaceCondition.java
 * @Title : 필기시험장소
 * @date : 2022.03.31 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class IMWtstPlaceCondition extends BaseCondition implements Serializable {
    

	private String scWtstId;
	private String scProcType;
	private String scAgncyId;
	

	/*등급*/
	private String scCrsGrdCdv;
	/*상태*/
	private String scSttsCdv;
	private String scEduYear;
	private String scEduRnd;
	private String scAddtnYn;
	private String scPrgrsStts;
	

	private IMWtstAplcntCondition aplcnt;

	public String getScWtstId() {
		return scWtstId;
	}

	public void setScWtstId(String scWtstId) {
		this.scWtstId = scWtstId;
	}

	public String getScProcType() {
		return scProcType;
	}

	public void setScProcType(String scProcType) {
		this.scProcType = scProcType;
	}

	public String getScCrsGrdCdv() {
		return scCrsGrdCdv;
	}

	public void setScCrsGrdCdv(String scCrsGrdCdv) {
		this.scCrsGrdCdv = scCrsGrdCdv;
	}

	public String getScSttsCdv() {
		return scSttsCdv;
	}

	public void setScSttsCdv(String scSttsCdv) {
		this.scSttsCdv = scSttsCdv;
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

	public String getScAgncyId() {
		return scAgncyId;
	}

	public void setScAgncyId(String scAgncyId) {
		this.scAgncyId = scAgncyId;
	}

	public String getScAddtnYn() {
		return scAddtnYn;
	}

	public void setScAddtnYn(String scAddtnYn) {
		this.scAddtnYn = scAddtnYn;
	}

	public String getScPrgrsStts() {
		return scPrgrsStts;
	}

	public void setScPrgrsStts(String scPrgrsStts) {
		this.scPrgrsStts = scPrgrsStts;
	}

	public IMWtstAplcntCondition getAplcnt() {
		return aplcnt;
	}

	public void setAplcnt(IMWtstAplcntCondition aplcnt) {
		this.aplcnt = aplcnt;
	}
	
	
}