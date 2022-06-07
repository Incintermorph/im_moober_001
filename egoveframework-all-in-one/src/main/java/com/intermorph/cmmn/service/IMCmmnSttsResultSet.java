/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.cmmn.service;

import java.io.Serializable;

import com.intermorph.cmmn.base.BaseResultSet;
import com.intermorph.uss.mngr.service.IMMberManageVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.vo.resultset;
 * @File : IMCmmnSttsRS.java
 * @Title : 공통상태정보
 * @date : 2022.04.07 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
 
@SuppressWarnings("serial")
public class IMCmmnSttsResultSet extends BaseResultSet implements Serializable {

    private IMCmmnSttsVO cmmnStts;
    private IMMberManageVO  mberManage;
    
    public IMCmmnSttsVO getCmmnStts() {
        return cmmnStts;
    }

    public void setCmmnStts(IMCmmnSttsVO cmmnStts) {
        this.cmmnStts = cmmnStts;
    }

	public IMMberManageVO getMberManage() {
		return mberManage;
	}

	public void setMberManage(IMMberManageVO mberManage) {
		this.mberManage = mberManage;
	}
    
    
}