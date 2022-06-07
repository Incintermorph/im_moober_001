/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.wtst.place.service;

import java.io.Serializable;

import com.intermorph.cmmn.base.BaseResultSet;
import com.intermorph.crs.agncy.service.IMAgncyVO;
import com.intermorph.wtst.wtst.service.IMWtstVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.wtst.place.vo.resultset;
 * @File : IMWtstPlaceRS.java
 * @Title : 필기시험장소
 * @date : 2022.03.31 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
 
@SuppressWarnings("serial")
public class IMWtstPlaceResultSet extends BaseResultSet implements Serializable {

    private IMWtstPlaceVO wtstPlace;

    private IMAgncyVO agncy;

    private IMWtstVO wtst;
    
    private String rcptProcType;
    
    private int applyCnt;
    
    public IMAgncyVO getAgncy() {
		return agncy;
	}

	public void setAgncy(IMAgncyVO agncy) {
		this.agncy = agncy;
	}

	public IMWtstPlaceVO getWtstPlace() {
        return wtstPlace;
    }

    public void setWtstPlace(IMWtstPlaceVO wtstPlace) {
        this.wtstPlace = wtstPlace;
    }

	public IMWtstVO getWtst() {
		return wtst;
	}

	public void setWtst(IMWtstVO wtst) {
		this.wtst = wtst;
	}

	public String getRcptProcType() {
		return rcptProcType;
	}

	public void setRcptProcType(String rcptProcType) {
		this.rcptProcType = rcptProcType;
	}

	public int getApplyCnt() {
		return applyCnt;
	}

	public void setApplyCnt(int applyCnt) {
		this.applyCnt = applyCnt;
	}
	
	
}