/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.uss.objc.service;

import java.io.Serializable;

import com.intermorph.cmmn.base.BaseCondition;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.objc.vo.condition
 * @File : IMObjcAplyCondition.java
 * @Title : 이의신청
 * @date : 2022.04.06 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class IMObjcAplyCondition extends BaseCondition implements Serializable {
    
	private String scMmbrEsntlId;
	private String scDvsnCdv;
	private String scObjcDvsnCdv;
	private String scSttsCdv;

	public String getScMmbrEsntlId() {
		return scMmbrEsntlId;
	}

	public void setScMmbrEsntlId(String scMmbrEsntlId) {
		this.scMmbrEsntlId = scMmbrEsntlId;
	}

	public String getScDvsnCdv() {
		return scDvsnCdv;
	}

	public void setScDvsnCdv(String scDvsnCdv) {
		this.scDvsnCdv = scDvsnCdv;
	}

	public String getScObjcDvsnCdv() {
		return scObjcDvsnCdv;
	}

	public void setScObjcDvsnCdv(String scObjcDvsnCdv) {
		this.scObjcDvsnCdv = scObjcDvsnCdv;
	}

	public String getScSttsCdv() {
		return scSttsCdv;
	}

	public void setScSttsCdv(String scSttsCdv) {
		this.scSttsCdv = scSttsCdv;
	}
	
	
	
}