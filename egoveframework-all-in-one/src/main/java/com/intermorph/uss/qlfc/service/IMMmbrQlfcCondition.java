/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.uss.qlfc.service;

import java.io.Serializable;

import com.intermorph.cmmn.base.BaseCondition;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.qlfc.vo.condition
 * @File : IMMmbrQlfcCondition.java
 * @Title : 회원자격정보
 * @date : 2022.05.02 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class IMMmbrQlfcCondition extends BaseCondition implements Serializable {

    private String scTargetCode;
    private String scDvsnCdv;
    private String scAgncyId;
    private String scEduYear;
    private String scCrsGrdCdv;
    private String scLcncSttsCdv;

	public String getScTargetCode() {
		return scTargetCode;
	}

	public void setScTargetCode(String scTargetCode) {
		this.scTargetCode = scTargetCode;
	}

	public String getScDvsnCdv() {
		return scDvsnCdv;
	}

	public void setScDvsnCdv(String scDvsnCdv) {
		this.scDvsnCdv = scDvsnCdv;
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

	public String getScCrsGrdCdv() {
		return scCrsGrdCdv;
	}

	public void setScCrsGrdCdv(String scCrsGrdCdv) {
		this.scCrsGrdCdv = scCrsGrdCdv;
	}

	public String getScLcncSttsCdv() {
		return scLcncSttsCdv;
	}

	public void setScLcncSttsCdv(String scLcncSttsCdv) {
		this.scLcncSttsCdv = scLcncSttsCdv;
	}
    
    

}