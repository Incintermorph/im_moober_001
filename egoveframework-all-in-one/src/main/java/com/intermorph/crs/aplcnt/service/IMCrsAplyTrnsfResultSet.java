/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.crs.aplcnt.service;

import java.io.Serializable;

import com.google.gson.Gson;
import com.intermorph.cmmn.IMGlobals;
import com.intermorph.cmmn.base.BaseResultSet;
import com.intermorph.cmmn.util.IMStringUtil;
import com.intermorph.uss.mngr.service.IMUssPermitDTO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.crs.aplcnt.service;
 * @File : IMCrsAplyTrnsfRS.java
 * @Title : 과정신청이관
 * @date : 2022.05.12 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
 
@SuppressWarnings("serial")
public class IMCrsAplyTrnsfResultSet extends BaseResultSet implements Serializable {
    

    private IMCrsAplyTrnsfVO crsAplyTrnsf;
    private IMCrsAplyTrnsfDTO idDto;
    
    
    
    
    public IMCrsAplyTrnsfVO getCrsAplyTrnsf() {
        return crsAplyTrnsf;
    }

    public void setCrsAplyTrnsf(IMCrsAplyTrnsfVO crsAplyTrnsf) {
        this.crsAplyTrnsf = crsAplyTrnsf;
    }

	public IMCrsAplyTrnsfDTO getIdDto() {
		if(getCrsAplyTrnsf()!=null && !IMStringUtil.isEmpty(getCrsAplyTrnsf().getTrnsfAplyId())) {

			Gson gson = new Gson();
			idDto = gson.fromJson(getCrsAplyTrnsf().getTrnsfAplyId(), IMCrsAplyTrnsfDTO.class);
			
		}
		return idDto;
	}

	public void setIdDto(IMCrsAplyTrnsfDTO idDto) {
		this.idDto = idDto;
	}
    
    
}