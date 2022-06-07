/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.uss.qlfc.service;

import java.io.Serializable;

import com.intermorph.cmmn.base.BaseResultSet;
import com.intermorph.crs.agncy.service.IMAgncyVO;
import com.intermorph.uss.hstry.service.IMMmbrHstryVO;
import com.intermorph.uss.mngr.service.IMMberManageVO;
import com.intermorph.uss.pstpnd.service.IMPstpndAplyVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.qlfc.vo.resultset;
 * @File : IMMmbrQlfcRS.java
 * @Title : 회원자격정보
 * @date : 2022.05.02 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
 
@SuppressWarnings("serial")
public class IMMmbrQlfcResultSet extends BaseResultSet implements Serializable {
    

    private IMMmbrQlfcVO mmbrQlfc;
    
    private IMMberManageVO  mberManage;
	private IMAgncyVO agncy;
    
    private IMPstpndAplyVO pstpndAply;
    

    private IMMmbrHstryVO mmbrHstry;
    
    private String cntneduTargetYn;
    private Long mmbrAge;
    
    public IMMmbrQlfcVO getMmbrQlfc() {
        return mmbrQlfc;
    }

    public void setMmbrQlfc(IMMmbrQlfcVO mmbrQlfc) {
        this.mmbrQlfc = mmbrQlfc;
    }

	public IMMberManageVO getMberManage() {
		return mberManage;
	}

	public void setMberManage(IMMberManageVO mberManage) {
		this.mberManage = mberManage;
	}

	public IMPstpndAplyVO getPstpndAply() {
		return pstpndAply;
	}

	public void setPstpndAply(IMPstpndAplyVO pstpndAply) {
		this.pstpndAply = pstpndAply;
	}

	public IMMmbrHstryVO getMmbrHstry() {
		return mmbrHstry;
	}

	public void setMmbrHstry(IMMmbrHstryVO mmbrHstry) {
		this.mmbrHstry = mmbrHstry;
	}

	public String getCntneduTargetYn() {
		return cntneduTargetYn;
	}

	public void setCntneduTargetYn(String cntneduTargetYn) {
		this.cntneduTargetYn = cntneduTargetYn;
	}

	public Long getMmbrAge() {
		return mmbrAge;
	}

	public void setMmbrAge(Long mmbrAge) {
		this.mmbrAge = mmbrAge;
	}

	public IMAgncyVO getAgncy() {
		return agncy;
	}

	public void setAgncy(IMAgncyVO agncy) {
		this.agncy = agncy;
	}
    
    
}