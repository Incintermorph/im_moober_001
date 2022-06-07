/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.uss.objc.service;

import java.io.Serializable;

import com.google.gson.Gson;
import com.intermorph.cmmn.base.BaseResultSet;
import com.intermorph.uss.mngr.service.IMMberManageVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.objc.vo.resultset;
 * @File : IMObjcAplyRS.java
 * @Title : 이의신청
 * @date : 2022.04.06 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
 
@SuppressWarnings("serial")
public class IMObjcAplyResultSet extends BaseResultSet implements Serializable {
    

    private IMObjcAplyVO objcAply;
    private IMMberManageVO  mberManage;
    
    private IMObjcAplyDtlDTO  objcAplyDtl;
    
    public IMObjcAplyVO getObjcAply() {
        return objcAply;
    }

    public void setObjcAply(IMObjcAplyVO objcAply) {
        this.objcAply = objcAply;
    }

	public IMObjcAplyDtlDTO getObjcAplyDtl() {
		objcAplyDtl =null;
		if(objcAply!=null && objcAply.getObjcAplyDtl()!=null && !"".equals(objcAply.getObjcAplyDtl())) {
			Gson gson = new Gson();
			objcAplyDtl = gson.fromJson(objcAply.getObjcAplyDtl(), IMObjcAplyDtlDTO.class);
		}
		
		return objcAplyDtl;
	}

	public void setObjcAplyDtl(IMObjcAplyDtlDTO objcAplyDtl) {
		this.objcAplyDtl = objcAplyDtl;
	}

	public IMMberManageVO getMberManage() {
		return mberManage;
	}

	public void setMberManage(IMMberManageVO mberManage) {
		this.mberManage = mberManage;
	}


    
    
}