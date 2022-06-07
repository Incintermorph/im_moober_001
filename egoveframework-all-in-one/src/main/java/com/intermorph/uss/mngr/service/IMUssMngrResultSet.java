/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.mngr.service;

import java.io.Serializable;

import com.intermorph.cmmn.base.BaseResultSet;
import com.intermorph.crs.agncy.service.IMAgncyVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.mngr.service
 * @File    : IMUssMngrResultSet.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 2. 24
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class IMUssMngrResultSet extends BaseResultSet implements Serializable {
	private IMUssMngrVO  ussMngr;

    private IMAgncyVO agncy;
	public IMUssMngrVO getUssMngr() {
		return ussMngr;
	}

	public void setUssMngr(IMUssMngrVO ussMngr) {
		this.ussMngr = ussMngr;
	}

	public IMAgncyVO getAgncy() {
		return agncy;
	}

	public void setAgncy(IMAgncyVO agncy) {
		this.agncy = agncy;
	}
	
	
}
