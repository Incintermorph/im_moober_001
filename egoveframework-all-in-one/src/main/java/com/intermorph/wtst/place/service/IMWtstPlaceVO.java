/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.wtst.place.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.intermorph.cmmn.base.BaseVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.wtst.place.vo
 * @File : IMWtstPlaceVO.java
 * @Title : 필기시험장소
 * @date : 2022.03.31 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class IMWtstPlaceVO extends BaseVO implements Serializable {

/**  ID   */
private String[] wtstPlaceIds;

	/**  필기시험장소ID (wtst_place_id) */
	private String wtstPlaceId;
	/**  필기시험ID (wtst_id) */
	private String wtstId;
	/**  기관ID (agncy_id) */
	private String agncyId;
	/**  문의처 (cntpnt) */
	private String cntpnt;
	/**  은행코드값 (bnk_cdv) */
	private String bnkCdv;
	/**  계좌번호 (accno) */
	private String accno;
	/**  예금주 (acchdr) */
	private String acchdr;
	/**  정원 (prsnl) */
	private String prsnl;
	/**  정원제한 (prsnl_lmt) */
	private String prsnlLmt;
	/**  진행상태코드값 (prgrs_stts_cdv) */
	private String prgrsSttsCdv;
	/**  순서 (ord) */
	private String ord;



	public String[] getWtstPlaceIds() {
		if(this.wtstPlaceIds !=null){
			String[] tempData = new String[this.wtstPlaceIds.length];
			System.arraycopy(this.wtstPlaceIds , 0, tempData, 0, this.wtstPlaceIds.length);
			return tempData;
		}else{
			return null;
		}
	}

	public void setWtstPlaceIds(String[] wtstPlaceIds) {
		if (wtstPlaceIds != null) {
			this.wtstPlaceIds = new String[wtstPlaceIds.length];
			System.arraycopy(wtstPlaceIds, 0, this.wtstPlaceIds, 0, wtstPlaceIds.length);
		} else {
			this.wtstPlaceIds = null;
		}
	}
	

	public String getWtstPlaceId() {
	    return wtstPlaceId;
	}
	
	public void setWtstPlaceId(String wtstPlaceId) {
	    this.wtstPlaceId = wtstPlaceId;
	}
	public String getWtstId() {
	    return wtstId;
	}
	
	public void setWtstId(String wtstId) {
	    this.wtstId = wtstId;
	}
	public String getAgncyId() {
	    return agncyId;
	}
	
	public void setAgncyId(String agncyId) {
	    this.agncyId = agncyId;
	}
	public String getCntpnt() {
	    return cntpnt;
	}
	
	public void setCntpnt(String cntpnt) {
	    this.cntpnt = cntpnt;
	}
	public String getBnkCdv() {
	    return bnkCdv;
	}
	
	public void setBnkCdv(String bnkCdv) {
	    this.bnkCdv = bnkCdv;
	}
	public String getAccno() {
	    return accno;
	}
	
	public void setAccno(String accno) {
	    this.accno = accno;
	}
	public String getAcchdr() {
	    return acchdr;
	}
	
	public void setAcchdr(String acchdr) {
	    this.acchdr = acchdr;
	}
	public String getPrsnl() {
	    return prsnl;
	}
	
	public void setPrsnl(String prsnl) {
	    this.prsnl = prsnl;
	}
	public String getPrsnlLmt() {
	    return prsnlLmt;
	}
	
	public void setPrsnlLmt(String prsnlLmt) {
	    this.prsnlLmt = prsnlLmt;
	}
	public String getPrgrsSttsCdv() {
	    return prgrsSttsCdv;
	}
	
	public void setPrgrsSttsCdv(String prgrsSttsCdv) {
	    this.prgrsSttsCdv = prgrsSttsCdv;
	}
	public String getOrd() {
	    return ord;
	}
	
	public void setOrd(String ord) {
	    this.ord = ord;
	}



	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}