/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.wtst.wtst.service;

import java.io.Serializable;

import com.intermorph.cmmn.base.BaseResultSet;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.wtst.wtst.vo.resultset;
 * @File : IMWtstRS.java
 * @Title : 필기시험
 * @date : 2022.03.31 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
 
@SuppressWarnings("serial")
public class IMWtstResultSet extends BaseResultSet implements Serializable {
    
    private IMWtstVO wtst;
    
    private int placeCnt;
    
    public IMWtstVO getWtst() {
        return wtst;
    }

    public void setWtst(IMWtstVO wtst) {
        this.wtst = wtst;
    }

	public int getPlaceCnt() {
		return placeCnt;
	}

	public void setPlaceCnt(int placeCnt) {
		this.placeCnt = placeCnt;
	}
    
    
}