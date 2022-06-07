/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.wtst.aplcnt.service;

import java.io.Serializable;
import java.util.List;

import com.google.gson.Gson;
import com.intermorph.cmmn.IMGlobals;
import com.intermorph.cmmn.base.BaseResultSet;
import com.intermorph.crs.agncy.service.IMAgncyVO;
import com.intermorph.uss.mngr.service.IMUssPermitDTO;
import com.intermorph.wtst.place.service.IMWtstPlaceVO;
import com.intermorph.wtst.wtst.service.IMWtstVO;

import egovframework.com.cmm.service.FileVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.wtst.aplcnt.vo.resultset;
 * @File : IMWtstAplcntRS.java
 * @Title : 필기시험 신청자
 * @date : 2022.04.04 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
 
@SuppressWarnings("serial")
public class IMWtstAplcntResultSet extends BaseResultSet implements Serializable {

    private IMWtstAplcntVO wtstAplcnt;


    private IMWtstPlaceVO wtstPlace;

	private IMAgncyVO agncy;

    private IMWtstVO wtst;
    
    
	private String sttDdvs;
	private String sttDdvIds;
	private String sttDdvDts;
	private String wtstDts;
	private String fnshYn;  //수료여부
	

	private List<FileVO>  fileList1;
	private List<FileVO>  fileList2;
	private List<FileVO>  fileList3;
	
	private IMWtstAplcntResultDTO score; 
	
    public IMWtstAplcntVO getWtstAplcnt() {
        return wtstAplcnt;
    }

    public void setWtstAplcnt(IMWtstAplcntVO wtstAplcnt) {
        this.wtstAplcnt = wtstAplcnt;
    }

	public String getSttDdvs() {
		return sttDdvs;
	}

	public void setSttDdvs(String sttDdvs) {
		this.sttDdvs = sttDdvs;
	}

	public String getSttDdvIds() {
		return sttDdvIds;
	}

	public void setSttDdvIds(String sttDdvIds) {
		this.sttDdvIds = sttDdvIds;
	}

	public String getSttDdvDts() {
		return sttDdvDts;
	}

	public void setSttDdvDts(String sttDdvDts) {
		this.sttDdvDts = sttDdvDts;
	}

	public String getWtstDts() {
		return wtstDts;
	}

	public void setWtstDts(String wtstDts) {
		this.wtstDts = wtstDts;
	}

	public IMWtstPlaceVO getWtstPlace() {
		return wtstPlace;
	}

	public void setWtstPlace(IMWtstPlaceVO wtstPlace) {
		this.wtstPlace = wtstPlace;
	}

	public IMWtstVO getWtst() {
		return wtst;
	}

	public void setWtst(IMWtstVO wtst) {
		this.wtst = wtst;
	}

	public List<FileVO> getFileList1() {
		return fileList1;
	}

	public void setFileList1(List<FileVO> fileList1) {
		this.fileList1 = fileList1;
	}

	public List<FileVO> getFileList2() {
		return fileList2;
	}

	public void setFileList2(List<FileVO> fileList2) {
		this.fileList2 = fileList2;
	}

	public List<FileVO> getFileList3() {
		return fileList3;
	}

	public void setFileList3(List<FileVO> fileList3) {
		this.fileList3 = fileList3;
	}

	public IMAgncyVO getAgncy() {
		return agncy;
	}

	public void setAgncy(IMAgncyVO agncy) {
		this.agncy = agncy;
	}

	public String getFnshYn() {
		return fnshYn;
	}

	public void setFnshYn(String fnshYn) {
		this.fnshYn = fnshYn;
	}

	public IMWtstAplcntResultDTO getScore() {
		if(this.getWtstAplcnt()!=null && getWtstAplcnt().getSbjRslt()!=null && !"".equals(getWtstAplcnt().getSbjRslt())) {
			Gson gson = new Gson();
			score = gson.fromJson(getWtstAplcnt().getSbjRslt(), IMWtstAplcntResultDTO.class);
		}else {
			score = new IMWtstAplcntResultDTO(); 
		}
		
		return score;
	}

	public void setScore(IMWtstAplcntResultDTO score) {
		this.score = score;
	}

	
    
}