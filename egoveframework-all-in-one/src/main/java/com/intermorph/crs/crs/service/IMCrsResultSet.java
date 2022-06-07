/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.crs.crs.service;

import java.io.Serializable;

import com.intermorph.cmmn.base.BaseResultSet;
import com.intermorph.crs.agncy.service.IMAgncyVO;
import com.intermorph.crs.mstr.service.IMCrsMstrVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.crs.crs.vo.resultset;
 * @File : IMCrsRS.java
 * @Title : 과정(운영과정)
 * @date : 2022.02.28 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
 
@SuppressWarnings("serial")
public class IMCrsResultSet extends BaseResultSet implements Serializable {

    private IMCrsVO crs;
    

	private IMCrsMstrVO crsMstr;
    

    private IMAgncyVO agncy;
    
    private String procType;
    
    private int aply02Cnt;
    private int fnsh02Cnt;
    
    public IMCrsVO getCrs() {
        return crs;
    }

    public void setCrs(IMCrsVO crs) {
        this.crs = crs;
    }

	public IMCrsMstrVO getCrsMstr() {
		return crsMstr;
	}

	public void setCrsMstr(IMCrsMstrVO crsMstr) {
		this.crsMstr = crsMstr;
	}

	public IMAgncyVO getAgncy() {
		return agncy;
	}

	public void setAgncy(IMAgncyVO agncy) {
		this.agncy = agncy;
	}

	public String getProcType() {
		return procType;
	}

	public void setProcType(String procType) {
		this.procType = procType;
	}

	public int getAply02Cnt() {
		return aply02Cnt;
	}

	public void setAply02Cnt(int aply02Cnt) {
		this.aply02Cnt = aply02Cnt;
	}

	public int getFnsh02Cnt() {
		return fnsh02Cnt;
	}

	public void setFnsh02Cnt(int fnsh02Cnt) {
		this.fnsh02Cnt = fnsh02Cnt;
	}

	
	
	
}