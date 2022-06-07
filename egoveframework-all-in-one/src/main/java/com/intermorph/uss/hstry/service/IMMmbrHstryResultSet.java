/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.uss.hstry.service;

import java.io.Serializable;

import com.intermorph.cmmn.base.BaseResultSet;

import egovframework.com.uss.umt.service.MberManageVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.hstry.vo.resultset;
 * @File : IMMmbrHstryRS.java
 * @Title : 회원이력
 * @date : 2022.03.10 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
 
@SuppressWarnings("serial")
public class IMMmbrHstryResultSet extends BaseResultSet implements Serializable {

    private IMMmbrHstryVO mmbrHstry;
    private IMEduAplyHstryVO eduAplyHstry;
    private MberManageVO mberManage;
    private Long cnt;
    
    public IMMmbrHstryVO getMmbrHstry() {
        return mmbrHstry;
    }

    public void setMmbrHstry(IMMmbrHstryVO mmbrHstry) {
        this.mmbrHstry = mmbrHstry;
    }

	public IMEduAplyHstryVO getEduAplyHstry() {
		return eduAplyHstry;
	}

	public void setEduAplyHstry(IMEduAplyHstryVO eduAplyHstry) {
		this.eduAplyHstry = eduAplyHstry;
	}

	public MberManageVO getMberManage() {
		return mberManage;
	}

	public void setMberManage(MberManageVO mberManage) {
		this.mberManage = mberManage;
	}

	public Long getCnt() {
		return cnt;
	}

	public void setCnt(Long cnt) {
		this.cnt = cnt;
	}
    
    
}