/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.crs.plan.service;

import java.io.Serializable;

import com.intermorph.cmmn.base.BaseResultSet;
import com.intermorph.crs.agncy.service.IMAgncyVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.crs.plan.vo.resultset;
 * @File : IMCrsPlanRS.java
 * @Title : 과정계획
 * @date : 2022.03.01 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */

@SuppressWarnings("serial")
public class IMCrsPlanResultSet extends BaseResultSet implements Serializable {

	private IMCrsPlanVO crsPlan;

	private IMAgncyVO agncy;

	public IMCrsPlanVO getCrsPlan() {
		return crsPlan;
	}

	public void setCrsPlan(IMCrsPlanVO crsPlan) {
		this.crsPlan = crsPlan;
	}

	public IMAgncyVO getAgncy() {
		return agncy;
	}

	public void setAgncy(IMAgncyVO agncy) {
		this.agncy = agncy;
	}

}