/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.wtst.aplcnt.service;

import java.io.Serializable;

import com.intermorph.cmmn.base.BaseCondition;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.wtst.aplcnt.vo.condition
 * @File : IMWtstAplcntCondition.java
 * @Title : 필기시험 신청자
 * @date : 2022.04.04 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings ("serial")
public class IMWtstAplcntCondition extends BaseCondition implements Serializable {

	private String scMmbrEsntlId;
	private String scAplyStts;
	private String scDpstStts;
	private String scWtstPlaceId;

	private String scNotAplyCancleYn;
	private String scAgncyId;
	private String scEduYear;
	private String scEduRnd;
	private String scFnshYnCheck;
	private String scFnshYn;
	private String scExamFnshYn;
	private String scExempDvsnCdv;
	private String scConvPvsnYn;
	
	/* 등급 */
	private String scCrsGrdCdv;
	private String scWtstId;
	private String scPrgrsStts;

	public String getScMmbrEsntlId() {
		return scMmbrEsntlId;
	}

	public void setScMmbrEsntlId(String scMmbrEsntlId) {
		this.scMmbrEsntlId = scMmbrEsntlId;
	}

	public String getScAplyStts() {
		return scAplyStts;
	}

	public void setScAplyStts(String scAplyStts) {
		this.scAplyStts = scAplyStts;
	}

	public String getScNotAplyCancleYn() {
		return scNotAplyCancleYn;
	}

	public void setScNotAplyCancleYn(String scNotAplyCancleYn) {
		this.scNotAplyCancleYn = scNotAplyCancleYn;
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

	public String getScCrsGrdCdv() {
		return scCrsGrdCdv;
	}

	public void setScCrsGrdCdv(String scCrsGrdCdv) {
		this.scCrsGrdCdv = scCrsGrdCdv;
	}

	public String getScDpstStts() {
		return scDpstStts;
	}

	public void setScDpstStts(String scDpstStts) {
		this.scDpstStts = scDpstStts;
	}

	public String getScWtstPlaceId() {
		return scWtstPlaceId;
	}

	public void setScWtstPlaceId(String scWtstPlaceId) {
		this.scWtstPlaceId = scWtstPlaceId;
	}

	public String getScFnshYnCheck() {
		return scFnshYnCheck;
	}

	public void setScFnshYnCheck(String scFnshYnCheck) {
		this.scFnshYnCheck = scFnshYnCheck;
	}

	public String getScFnshYn() {
		return scFnshYn;
	}

	public void setScFnshYn(String scFnshYn) {
		this.scFnshYn = scFnshYn;
	}

	public String getScExempDvsnCdv() {
		return scExempDvsnCdv;
	}

	public void setScExempDvsnCdv(String scExempDvsnCdv) {
		this.scExempDvsnCdv = scExempDvsnCdv;
	}

	public String getScWtstId() {
		return scWtstId;
	}

	public void setScWtstId(String scWtstId) {
		this.scWtstId = scWtstId;
	}

	public String getScPrgrsStts() {
		return scPrgrsStts;
	}

	public void setScPrgrsStts(String scPrgrsStts) {
		this.scPrgrsStts = scPrgrsStts;
	}

	public String getScExamFnshYn() {
		return scExamFnshYn;
	}

	public void setScExamFnshYn(String scExamFnshYn) {
		this.scExamFnshYn = scExamFnshYn;
	}

	public String getScConvPvsnYn() {
		return scConvPvsnYn;
	}

	public void setScConvPvsnYn(String scConvPvsnYn) {
		this.scConvPvsnYn = scConvPvsnYn;
	}	

}