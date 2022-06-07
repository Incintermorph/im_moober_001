/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.cmmn.file.service;

import java.io.Serializable;
import java.util.List;

import com.intermorph.cmmn.base.BaseResultSet;

import egovframework.com.cmm.service.FileVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.file.service;
 * @File : IMCmmnFileRS.java
 * @Title : 공통파일관리
 * @date : 2022.05.13 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
 
@SuppressWarnings("serial")
public class IMCmmnFileResultSet extends BaseResultSet implements Serializable {

    private IMCmmnFileVO cmmnFile;

	private List<FileVO>  fileList;
	
    public IMCmmnFileVO getCmmnFile() {
        return cmmnFile;
    }

    public void setCmmnFile(IMCmmnFileVO cmmnFile) {
        this.cmmnFile = cmmnFile;
    }

	public List<FileVO> getFileList() {
		return fileList;
	}

	public void setFileList(List<FileVO> fileList) {
		this.fileList = fileList;
	}
    
    
}