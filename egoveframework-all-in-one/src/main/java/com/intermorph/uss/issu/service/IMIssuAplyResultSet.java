/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.uss.issu.service;

import java.io.Serializable;

import com.intermorph.cmmn.base.BaseResultSet;
import com.intermorph.crs.agncy.service.IMAgncyVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.issu.vo.resultset;
 * @File : IMIssuAplyRS.java
 * @Title : 발급신청
 * @date : 2022.04.14 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
 
@SuppressWarnings("serial")
public class IMIssuAplyResultSet extends BaseResultSet implements Serializable {

    private IMIssuAplyVO issuAply;
    

    private IMAgncyVO agncy;
    
    public IMIssuAplyVO getIssuAply() {
        return issuAply;
    }

    public void setIssuAply(IMIssuAplyVO issuAply) {
        this.issuAply = issuAply;
    }

	public IMAgncyVO getAgncy() {
		return agncy;
	}

	public void setAgncy(IMAgncyVO agncy) {
		this.agncy = agncy;
	}
    
    
}