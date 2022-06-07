/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.crs.agncy.service;

import java.io.Serializable;

import com.intermorph.cmmn.base.BaseCondition;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.crs.agncy.vo.condition
 * @File : IMAgncyCondition.java
 * @Title : 양성기관
 * @date : 2022.02.21 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class IMAgncyCondition extends BaseCondition implements Serializable {
    
	private String scCrsGrdCdv;
	private String scAgncyDvsnCdv;
	private String scNotAgncyDvsnCdv;
	private String scAreaCdv;
	private String scDsgnYn;
	private String scLinkCode;
	
	
	public String getScCrsGrdCdv() {
		return scCrsGrdCdv;
	}
	public void setScCrsGrdCdv(String scCrsGrdCdv) {
		this.scCrsGrdCdv = scCrsGrdCdv;
	}
	public String getScAgncyDvsnCdv() {
		return scAgncyDvsnCdv;
	}
	public void setScAgncyDvsnCdv(String scAgncyDvsnCdv) {
		this.scAgncyDvsnCdv = scAgncyDvsnCdv;
	}
	public String getScAreaCdv() {
		return scAreaCdv;
	}
	public void setScAreaCdv(String scAreaCdv) {
		this.scAreaCdv = scAreaCdv;
	}
	public String getScNotAgncyDvsnCdv() {
		return scNotAgncyDvsnCdv;
	}
	public void setScNotAgncyDvsnCdv(String scNotAgncyDvsnCdv) {
		this.scNotAgncyDvsnCdv = scNotAgncyDvsnCdv;
	}
	public String getScDsgnYn() {
		return scDsgnYn;
	}
	public void setScDsgnYn(String scDsgnYn) {
		this.scDsgnYn = scDsgnYn;
	}
	public String getScLinkCode() {
		return scLinkCode;
	}
	public void setScLinkCode(String scLinkCode) {
		this.scLinkCode = scLinkCode;
	}
	
	
	
}