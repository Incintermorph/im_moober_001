/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.uss.pstpnd.service;

import java.io.Serializable;

import com.intermorph.cmmn.base.BaseCondition;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.pstpnd.service
 * @File : IMPstpndAplyCondition.java
 * @Title : 유예신청
 * @date : 2022.04.14 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class IMPstpndAplyCondition extends BaseCondition implements Serializable {
    
	private String scEsntlId;
	private String scCrsGrdCdv;
    private String scDvsnCdv;
    private String scAgncyId;
    private String scEduYear;
    

	private String scNotSttsCdv;
	private String scSttsCdv;

	public String getScEsntlId() {
		return scEsntlId;
	}

	public void setScEsntlId(String scEsntlId) {
		this.scEsntlId = scEsntlId;
	}

	public String getScCrsGrdCdv() {
		return scCrsGrdCdv;
	}

	public void setScCrsGrdCdv(String scCrsGrdCdv) {
		this.scCrsGrdCdv = scCrsGrdCdv;
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

	public String getScNotSttsCdv() {
		return scNotSttsCdv;
	}

	public void setScNotSttsCdv(String scNotSttsCdv) {
		this.scNotSttsCdv = scNotSttsCdv;
	}

	public String getScSttsCdv() {
		return scSttsCdv;
	}

	public void setScSttsCdv(String scSttsCdv) {
		this.scSttsCdv = scSttsCdv;
	}
	
	
	
}