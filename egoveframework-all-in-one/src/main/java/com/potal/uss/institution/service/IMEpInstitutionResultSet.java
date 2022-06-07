/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.potal.uss.institution.service;

import java.io.Serializable;

import com.intermorph.cmmn.base.BaseResultSet;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.potal.uss.institution.vo.resultset;
 * @File : IMEpInstitutionRS.java
 * @Title : 기관(포탈)
 * @date : 2022.03.29 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
 
@SuppressWarnings("serial")
public class IMEpInstitutionResultSet extends BaseResultSet implements Serializable {
    private static final long serialVersionUID = 1L;

    private IMEpInstitutionVO epInstitution;
    
    public IMEpInstitutionVO getEpInstitution() {
        return epInstitution;
    }

    public void setEpInstitution(IMEpInstitutionVO epInstitution) {
        this.epInstitution = epInstitution;
    }
}