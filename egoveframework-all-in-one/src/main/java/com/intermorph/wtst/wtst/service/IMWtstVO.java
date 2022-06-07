/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.wtst.wtst.service;

import java.io.Serializable;
import java.text.ParseException;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.intermorph.cmmn.base.BaseVO;
import com.intermorph.cmmn.util.IMDateUtil;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.wtst.wtst.vo
 * @File : IMWtstVO.java
 * @Title : 필기시험
 * @date : 2022.03.31 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class IMWtstVO extends BaseVO implements Serializable {

/**  ID   */
private String[] wtstIds;

	/**  필기시험ID (wtst_id) */
	private String wtstId;
	/**  과정등급코드값 (crs_grd_cdv) */
	private String crsGrdCdv;
	/**  교육년도 (edu_year) */
	private String eduYear;
	/**  교육차수 (edu_rnd) */
	private String eduRnd;
	/**  시험일자 (tst_ymd) */
	private String tstYmd;
	/**  시험시작시간 (tst_bgng_hrs) */
	private String tstBgngHrs;
	/**  시험종료시간 (tst_end_hrs) */
	private String tstEndHrs;
	/**  추가여부 (addtn_yn) */
	private String addtnYn;
	/**  응시료 (tstfee) */
	private String tstfee;
	/**  상태코드값 (stts_cdv) */
	private String sttsCdv;
	

	private String pbancTerm_bgnDt;
	private String pbancTerm_endDt;
	private String rcptTerm_bgnDt;
	private String rcptTerm_endDt;
	private String passTerm_bgnDt;
	private String passTerm_endDt;
	
	private String slctnTerm_bgnDt;
	private String slctnTerm_endDt;
	private String payTerm_bgnDt;
	private String payTerm_endDt;



	public String[] getWtstIds() {
		if(this.wtstIds !=null){
			String[] tempData = new String[this.wtstIds.length];
			System.arraycopy(this.wtstIds , 0, tempData, 0, this.wtstIds.length);
			return tempData;
		}else{
			return null;
		}
	}

	public void setWtstIds(String[] wtstIds) {
		if (wtstIds != null) {
			this.wtstIds = new String[wtstIds.length];
			System.arraycopy(wtstIds, 0, this.wtstIds, 0, wtstIds.length);
		} else {
			this.wtstIds = null;
		}
	}
	

	public String getWtstId() {
	    return wtstId;
	}
	
	public void setWtstId(String wtstId) {
	    this.wtstId = wtstId;
	}
	public String getCrsGrdCdv() {
	    return crsGrdCdv;
	}
	
	public void setCrsGrdCdv(String crsGrdCdv) {
	    this.crsGrdCdv = crsGrdCdv;
	}
	public String getEduYear() {
	    return eduYear;
	}
	
	public void setEduYear(String eduYear) {
	    this.eduYear = eduYear;
	}
	public String getEduRnd() {
	    return eduRnd;
	}
	
	public void setEduRnd(String eduRnd) {
	    this.eduRnd = eduRnd;
	}
	public String getTstYmd() {
	    return tstYmd;
	}
	public String getTstYmdDateTime() {

		try {
			return IMDateUtil.getFormatString(tstYmd, "yyyy.MM.dd");
		} catch (ParseException e) {
			return null;
		}
	}
	
	public void setTstYmd(String tstYmd) {
	    this.tstYmd = tstYmd;
	}
	public String getTstBgngHrs() {
	    return tstBgngHrs;
	}
	
	public void setTstBgngHrs(String tstBgngHrs) {
	    this.tstBgngHrs = tstBgngHrs;
	}
	public String getTstEndHrs() {
	    return tstEndHrs;
	}
	
	public void setTstEndHrs(String tstEndHrs) {
	    this.tstEndHrs = tstEndHrs;
	}
	public String getAddtnYn() {
	    return addtnYn;
	}
	
	public void setAddtnYn(String addtnYn) {
	    this.addtnYn = addtnYn;
	}
	public String getTstfee() {
	    return tstfee;
	}
	
	public void setTstfee(String tstfee) {
	    this.tstfee = tstfee;
	}
	public String getSttsCdv() {
	    return sttsCdv;
	}
	
	public void setSttsCdv(String sttsCdv) {
	    this.sttsCdv = sttsCdv;
	}



	public String getPbancTerm_bgnDt() {
		return pbancTerm_bgnDt;
	}

	public void setPbancTerm_bgnDt(String pbancTerm_bgnDt) {
		this.pbancTerm_bgnDt = pbancTerm_bgnDt;
	}

	public String getPbancTerm_endDt() {
		return pbancTerm_endDt;
	}

	public void setPbancTerm_endDt(String pbancTerm_endDt) {
		this.pbancTerm_endDt = pbancTerm_endDt;
	}

	public String getRcptTerm_bgnDt() {
		return rcptTerm_bgnDt;
	}

	public void setRcptTerm_bgnDt(String rcptTerm_bgnDt) {
		this.rcptTerm_bgnDt = rcptTerm_bgnDt;
	}

	public String getRcptTerm_endDt() {
		return rcptTerm_endDt;
	}

	public void setRcptTerm_endDt(String rcptTerm_endDt) {
		this.rcptTerm_endDt = rcptTerm_endDt;
	}

	public String getPassTerm_bgnDt() {
		return passTerm_bgnDt;
	}

	public void setPassTerm_bgnDt(String passTerm_bgnDt) {
		this.passTerm_bgnDt = passTerm_bgnDt;
	}

	public String getPassTerm_endDt() {
		return passTerm_endDt;
	}

	public void setPassTerm_endDt(String passTerm_endDt) {
		this.passTerm_endDt = passTerm_endDt;
	}

	public String getSlctnTerm_bgnDt() {
		return slctnTerm_bgnDt;
	}

	public void setSlctnTerm_bgnDt(String slctnTerm_bgnDt) {
		this.slctnTerm_bgnDt = slctnTerm_bgnDt;
	}

	public String getSlctnTerm_endDt() {
		return slctnTerm_endDt;
	}

	public void setSlctnTerm_endDt(String slctnTerm_endDt) {
		this.slctnTerm_endDt = slctnTerm_endDt;
	}

	public String getPayTerm_bgnDt() {
		return payTerm_bgnDt;
	}

	public void setPayTerm_bgnDt(String payTerm_bgnDt) {
		this.payTerm_bgnDt = payTerm_bgnDt;
	}

	public String getPayTerm_endDt() {
		return payTerm_endDt;
	}

	public void setPayTerm_endDt(String payTerm_endDt) {
		this.payTerm_endDt = payTerm_endDt;
	}

	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}