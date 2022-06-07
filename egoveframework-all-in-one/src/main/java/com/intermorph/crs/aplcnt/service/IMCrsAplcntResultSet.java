/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.crs.aplcnt.service;

import java.io.Serializable;

import com.intermorph.cmmn.base.BaseResultSet;
import com.intermorph.crs.agncy.service.IMAgncyVO;
import com.intermorph.crs.crs.service.IMCrsVO;
import com.intermorph.crs.mstr.service.IMCrsMstrVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.crs.aplcnt.vo.resultset;
 * @File : IMCrsAplcntRS.java
 * @Title : 운영과정신청자
 * @date : 2022.03.03 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */

@SuppressWarnings("serial")
public class IMCrsAplcntResultSet extends BaseResultSet implements Serializable {

	private IMCrsAplcntVO crsAplcnt;

	private IMCrsMstrVO crsMstr;
	private IMCrsVO crs;

	private IMAgncyVO agncy;
	private String sttDdvs;
	private String crsDts;
	private String sttDdvIds;
	private String sttDdvDts;
	
	private String rndmCdv;
	private String agncySrngCdv;
	private String opsectSrngCdv;
	private String agncySrngRmks;
	private String opsectSrngRmks;
	private String diffYear;
	private String diffMonth;
	private String mmbrAge;

	public IMCrsAplcntVO getCrsAplcnt() {
		return crsAplcnt;
	}

	public void setCrsAplcnt(IMCrsAplcntVO crsAplcnt) {
		this.crsAplcnt = crsAplcnt;
	}

	public String getSttDdvs() {
		return sttDdvs;
	}

	public void setSttDdvs(String sttDdvs) {
		this.sttDdvs = sttDdvs;
	}

	public String getCrsDts() {
		return crsDts;
	}

	public void setCrsDts(String crsDts) {
		this.crsDts = crsDts;
	}

	public IMCrsMstrVO getCrsMstr() {
		return crsMstr;
	}

	public void setCrsMstr(IMCrsMstrVO crsMstr) {
		this.crsMstr = crsMstr;
	}

	public IMAgncyVO getAgncy() {
		return agncy;
	}

	public void setAgncy(IMAgncyVO agncy) {
		this.agncy = agncy;
	}

	public IMCrsVO getCrs() {
		return crs;
	}

	public void setCrs(IMCrsVO crs) {
		this.crs = crs;
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

	public String getRndmCdv() {
		return rndmCdv;
	}

	public void setRndmCdv(String rndmCdv) {
		this.rndmCdv = rndmCdv;
	}

	public String getAgncySrngCdv() {
		return agncySrngCdv;
	}

	public void setAgncySrngCdv(String agncySrngCdv) {
		this.agncySrngCdv = agncySrngCdv;
	}

	public String getOpsectSrngCdv() {
		return opsectSrngCdv;
	}

	public void setOpsectSrngCdv(String opsectSrngCdv) {
		this.opsectSrngCdv = opsectSrngCdv;
	}

	public String getAgncySrngRmks() {
		return agncySrngRmks;
	}

	public void setAgncySrngRmks(String agncySrngRmks) {
		this.agncySrngRmks = agncySrngRmks;
	}

	public String getOpsectSrngRmks() {
		return opsectSrngRmks;
	}

	public void setOpsectSrngRmks(String opsectSrngRmks) {
		this.opsectSrngRmks = opsectSrngRmks;
	}

	public String getDiffYear() {
		return diffYear;
	}

	public void setDiffYear(String diffYear) {
		this.diffYear = diffYear;
	}

	public String getDiffMonth() {
		return diffMonth;
	}

	public void setDiffMonth(String diffMonth) {
		this.diffMonth = diffMonth;
	}

	public String getMmbrAge() {
		return mmbrAge;
	}

	public void setMmbrAge(String mmbrAge) {
		this.mmbrAge = mmbrAge;
	}
	
}