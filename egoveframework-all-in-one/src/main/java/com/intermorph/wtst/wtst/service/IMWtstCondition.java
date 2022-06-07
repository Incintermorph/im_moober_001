/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.wtst.wtst.service;

import java.io.Serializable;

import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.wtst.aplcnt.service.IMWtstAplcntCondition;
import com.intermorph.wtst.place.service.IMWtstPlaceCondition;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.wtst.wtst.vo.condition
 * @File : IMWtstCondition.java
 * @Title : 필기시험
 * @date : 2022.03.31 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class IMWtstCondition extends BaseCondition implements Serializable {
    

	/*등급*/
	private String scCrsGrdCdv;
	/*상태*/
	private String scSttsCdv;
	private String scEduYear;
	private String scEduRnd;
	
	IMWtstPlaceCondition place;
	

	private IMWtstAplcntCondition aplcnt;
	
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
	public IMWtstPlaceCondition getPlace() {
		return place;
	}
	public void setPlace(IMWtstPlaceCondition place) {
		this.place = place;
	}
	public IMWtstAplcntCondition getAplcnt() {
		return aplcnt;
	}
	public void setAplcnt(IMWtstAplcntCondition aplcnt) {
		this.aplcnt = aplcnt;
	}
	
	
	
	
	
}