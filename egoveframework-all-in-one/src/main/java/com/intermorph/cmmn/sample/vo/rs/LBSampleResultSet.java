/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.sample.vo.rs;

import java.io.Serializable;

import com.intermorph.cmmn.base.BaseResultSet;

import egovframework.com.cop.ncm.service.NameCardVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.sample.vo.rs
 * @File : LBSampleResultSet.java
 * @Title : {간단한 프로그램의 명칭을 기록}
 * @date : 2022. 1. 13
 * @author : 노성용
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class LBSampleResultSet extends BaseResultSet implements Serializable {
	private NameCardVO nameCard;

	public NameCardVO getNameCard() {
		return nameCard;
	}

	public void setNameCard(NameCardVO nameCard) {
		this.nameCard = nameCard;
	}

}
