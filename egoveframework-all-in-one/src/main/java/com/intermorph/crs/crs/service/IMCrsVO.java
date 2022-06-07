/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.crs.crs.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.intermorph.cmmn.base.BaseVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.crs.crs.vo
 * @File : IMCrsVO.java
 * @Title : 과정(운영과정)
 * @date : 2022.02.28 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class IMCrsVO extends BaseVO implements Serializable {

/**  ID   */
private String[] crsIds;

	/**  과정ID (crs_id) */
	private String crsId;
	private String crsIdOpn;
	/**  과정마스터ID (crs_mstr_id) */
	private String crsMstrId;
	/**  교육년도 (edu_year) */
	private String eduYear;
	/**  교육차수 (edu_rnd) */
	private String eduRnd;
	/**  교육수수료 (ttnfee) */
	private String ttnfee;
	/**  교육시간 (edu_hrs) */
	private String eduHrs;
	/**  선정방식코드값 (slctn_how_cdv) */
	private String slctnHowCdv;
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
	/**  첨부파일ID (atch_file_id) */
	private String atchFileId;
	/**  상태코드값 (stts_cdv) */
	private String sttsCdv;
	/** 진행상태 prgrs_stts_cdv*/
	private String prgrsSttsCdv;
	
	private String eduAplTerm_bgnDt;
	private String eduAplTerm_endDt;
	private String eduTerm_bgnDt;
	private String eduTerm_endDt;
	private String slctnTerm_bgnDt;
	private String slctnTerm_endDt;
	private String payTerm_bgnDt;
	private String payTerm_endDt;
	private String fnshYmd_bgnDt;
	private String olfcfnshYmd_bgnDt;



	public String[] getCrsIds() {
		if(this.crsIds !=null){
			String[] tempData = new String[this.crsIds.length];
			System.arraycopy(this.crsIds , 0, tempData, 0, this.crsIds.length);
			return tempData;
		}else{
			return null;
		}
	}

	public void setCrsIds(String[] crsIds) {
		if (crsIds != null) {
			this.crsIds = new String[crsIds.length];
			System.arraycopy(crsIds, 0, this.crsIds, 0, crsIds.length);
		} else {
			this.crsIds = null;
		}
	}
	

	public String getCrsId() {
	    return crsId;
	}
	
	public void setCrsId(String crsId) {
	    this.crsId = crsId;
	}
	public String getCrsMstrId() {
	    return crsMstrId;
	}
	
	public void setCrsMstrId(String crsMstrId) {
	    this.crsMstrId = crsMstrId;
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
	public String getTtnfee() {
	    return ttnfee;
	}
	
	public void setTtnfee(String ttnfee) {
	    this.ttnfee = ttnfee;
	}
	public String getEduHrs() {
	    return eduHrs;
	}
	
	public void setEduHrs(String eduHrs) {
	    this.eduHrs = eduHrs;
	}
	public String getSlctnHowCdv() {
	    return slctnHowCdv;
	}
	
	public void setSlctnHowCdv(String slctnHowCdv) {
	    this.slctnHowCdv = slctnHowCdv;
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
	public String getAtchFileId() {
	    return atchFileId;
	}
	
	public void setAtchFileId(String atchFileId) {
	    this.atchFileId = atchFileId;
	}
	public String getSttsCdv() {
	    return sttsCdv;
	}
	
	public void setSttsCdv(String sttsCdv) {
	    this.sttsCdv = sttsCdv;
	}



	public String getEduAplTerm_bgnDt() {
		return eduAplTerm_bgnDt;
	}

	public void setEduAplTerm_bgnDt(String eduAplTerm_bgnDt) {
		this.eduAplTerm_bgnDt = eduAplTerm_bgnDt;
	}

	public String getEduAplTerm_endDt() {
		return eduAplTerm_endDt;
	}

	public void setEduAplTerm_endDt(String eduAplTerm_endDt) {
		this.eduAplTerm_endDt = eduAplTerm_endDt;
	}

	public String getEduTerm_bgnDt() {
		return eduTerm_bgnDt;
	}

	public void setEduTerm_bgnDt(String eduTerm_bgnDt) {
		this.eduTerm_bgnDt = eduTerm_bgnDt;
	}

	public String getEduTerm_endDt() {
		return eduTerm_endDt;
	}

	public void setEduTerm_endDt(String eduTerm_endDt) {
		this.eduTerm_endDt = eduTerm_endDt;
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

	public String getFnshYmd_bgnDt() {
		return fnshYmd_bgnDt;
	}

	public void setFnshYmd_bgnDt(String fnshYmd_bgnDt) {
		this.fnshYmd_bgnDt = fnshYmd_bgnDt;
	}

	public String getOlfcfnshYmd_bgnDt() {
		return olfcfnshYmd_bgnDt;
	}

	public void setOlfcfnshYmd_bgnDt(String olfcfnshYmd_bgnDt) {
		this.olfcfnshYmd_bgnDt = olfcfnshYmd_bgnDt;
	}

	public String getPrgrsSttsCdv() {
		return prgrsSttsCdv;
	}

	public void setPrgrsSttsCdv(String prgrsSttsCdv) {
		this.prgrsSttsCdv = prgrsSttsCdv;
	}

	
	public String getCrsIdOpn() {
		return crsIdOpn;
	}

	public void setCrsIdOpn(String crsIdOpn) {
		this.crsIdOpn = crsIdOpn;
	}

	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}