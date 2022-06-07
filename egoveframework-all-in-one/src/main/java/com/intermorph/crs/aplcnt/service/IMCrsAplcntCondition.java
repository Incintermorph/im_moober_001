/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.crs.aplcnt.service;

import java.io.Serializable;

import com.intermorph.cmmn.base.BaseCondition;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.crs.aplcnt.vo.condition
 * @File : IMCrsAplcntCondition.java
 * @Title : 운영과정신청자
 * @date : 2022.03.03 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class IMCrsAplcntCondition extends BaseCondition implements Serializable {
    
	private String scMmbrEsntlId;
	private String scAplyStts;
	private String scAgncyId;
	private String scEduYear;
	private String scEduRnd;
	private String scCrsMstrId;
	private String scCrsId;
	private String scExptSbjYn;
	private String scSttsCdvAGNCYSRNG;
	private String scSttsCdvOPSECTSRNG;
	private String scNotAplyCancleYn;
	private String scNotRNDMCancleYn;
	private String scSttsCdvDPST;
	private String scSttsCdvQLFC;
	private String scSttsCdvFNSH;


	/*등급*/
	private String scCrsGrdCdv;
	/*구분*/
	private String scCrsDvsnCdv;
	
	
	
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

	public String getScCrsMstrId() {
		return scCrsMstrId;
	}

	public void setScCrsMstrId(String scCrsMstrId) {
		this.scCrsMstrId = scCrsMstrId;
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

	public String getScNotAplyCancleYn() {
		return scNotAplyCancleYn;
	}

	public void setScNotAplyCancleYn(String scNotAplyCancleYn) {
		this.scNotAplyCancleYn = scNotAplyCancleYn;
	}

	public String getScCrsId() {
		return scCrsId;
	}

	public void setScCrsId(String scCrsId) {
		this.scCrsId = scCrsId;
	}

	public String getScNotRNDMCancleYn() {
		return scNotRNDMCancleYn;
	}

	public void setScNotRNDMCancleYn(String scNotRNDMCancleYn) {
		this.scNotRNDMCancleYn = scNotRNDMCancleYn;
	}

	public String getScExptSbjYn() {
		return scExptSbjYn;
	}

	public void setScExptSbjYn(String scExptSbjYn) {
		this.scExptSbjYn = scExptSbjYn;
	}

	public String getScSttsCdvAGNCYSRNG() {
		return scSttsCdvAGNCYSRNG;
	}

	public void setScSttsCdvAGNCYSRNG(String scSttsCdvAGNCYSRNG) {
		this.scSttsCdvAGNCYSRNG = scSttsCdvAGNCYSRNG;
	}

	public String getScSttsCdvOPSECTSRNG() {
		return scSttsCdvOPSECTSRNG;
	}

	public void setScSttsCdvOPSECTSRNG(String scSttsCdvOPSECTSRNG) {
		this.scSttsCdvOPSECTSRNG = scSttsCdvOPSECTSRNG;
	}

	public String getScSttsCdvDPST() {
		return scSttsCdvDPST;
	}

	public void setScSttsCdvDPST(String scSttsCdvDPST) {
		this.scSttsCdvDPST = scSttsCdvDPST;
	}

	public String getScSttsCdvQLFC() {
		return scSttsCdvQLFC;
	}

	public void setScSttsCdvQLFC(String scSttsCdvQLFC) {
		this.scSttsCdvQLFC = scSttsCdvQLFC;
	}

	public String getScSttsCdvFNSH() {
		return scSttsCdvFNSH;
	}

	public void setScSttsCdvFNSH(String scSttsCdvFNSH) {
		this.scSttsCdvFNSH = scSttsCdvFNSH;
	}

	
	
	
}