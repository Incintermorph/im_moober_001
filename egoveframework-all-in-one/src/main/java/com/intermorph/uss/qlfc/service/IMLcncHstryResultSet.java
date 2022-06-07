/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.uss.qlfc.service;

import java.io.Serializable;

import com.intermorph.cmmn.base.BaseResultSet;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.qlfc.service;
 * @File : IMLcncHstryRS.java
 * @Title : 자격증 이력
 * @date : 2022.05.04 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
 
@SuppressWarnings("serial")
public class IMLcncHstryResultSet extends BaseResultSet implements Serializable {

    private IMLcncHstryVO lcncHstry;
    
    public IMLcncHstryVO getLcncHstry() {
        return lcncHstry;
    }

    public void setLcncHstry(IMLcncHstryVO lcncHstry) {
        this.lcncHstry = lcncHstry;
    }
}