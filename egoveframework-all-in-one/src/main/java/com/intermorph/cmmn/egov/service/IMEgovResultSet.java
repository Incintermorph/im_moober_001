/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.egov.service;

import com.intermorph.cmmn.base.BaseResultSet;

import egovframework.com.sym.mnu.mpm.service.MenuManageVO;
import egovframework.com.sym.prm.service.ProgrmManageVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.egov.service
 * @File    : IMEgovResultSet.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 2. 23
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */

@SuppressWarnings("serial")
public class IMEgovResultSet extends BaseResultSet {
	private MenuManageVO menuManage;
	
	private ProgrmManageVO progrmManage;
	
	
	public MenuManageVO getMenuManage() {
		return menuManage;
	}
	public void setMenuManage(MenuManageVO menuManage) {
		this.menuManage = menuManage;
	}
	public ProgrmManageVO getProgrmManage() {
		return progrmManage;
	}
	public void setProgrmManage(ProgrmManageVO progrmManage) {
		this.progrmManage = progrmManage;
	}
	
	
	
	
	
	
}
