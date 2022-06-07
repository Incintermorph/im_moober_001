/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.crs.agncy.service;

import java.io.Serializable;

import com.intermorph.cmmn.base.BaseResultSet;
/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.crs.agncy.vo.resultset;
 * @File : IMAgncyRS.java
 * @Title : 양성기관
 * @date : 2022.02.21 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
 
@SuppressWarnings("serial")
public class IMAgncyResultSet extends BaseResultSet implements Serializable {
    
    private IMAgncyVO agncy;
    
    public IMAgncyVO getAgncy() {
        return agncy;
    }

    public void setAgncy(IMAgncyVO agncy) {
        this.agncy = agncy;
    }
}