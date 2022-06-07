/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.uss.pstpnd.service;

import java.io.Serializable;
import java.util.List;

import com.intermorph.cmmn.base.BaseResultSet;
import com.intermorph.uss.mngr.service.IMMberManageVO;

import egovframework.com.cmm.service.FileVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.pstpnd.vo.resultset;
 * @File : IMPstpndAplyRS.java
 * @Title : 유예신청
 * @date : 2022.04.14 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
 
@SuppressWarnings("serial")
public class IMPstpndAplyResultSet extends BaseResultSet implements Serializable {
    

    private IMPstpndAplyVO pstpndAply;

    private IMMberManageVO  mberManage;

	private List<FileVO>  fileList;
    
    public IMPstpndAplyVO getPstpndAply() {
        return pstpndAply;
    }

    public void setPstpndAply(IMPstpndAplyVO pstpndAply) {
        this.pstpndAply = pstpndAply;
    }

	public List<FileVO> getFileList() {
		return fileList;
	}

	public void setFileList(List<FileVO> fileList) {
		this.fileList = fileList;
	}

	public IMMberManageVO getMberManage() {
		return mberManage;
	}

	public void setMberManage(IMMberManageVO mberManage) {
		this.mberManage = mberManage;
	}
    
    
}