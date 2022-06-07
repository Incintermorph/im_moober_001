/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.uss.qlfc.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.intermorph.cmmn.base.BaseVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.qlfc.vo
 * @File : IMMmbrQlfcVO.java
 * @Title : 회원자격정보
 * @date : 2022.04.13 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class IMMmbrQlfcVO extends BaseVO implements Serializable {


	/**  고유ID (esntl_id) */
	private String esntlId;
	/**  과정등급코드값 (crs_grd_cdv) */
	private String crsGrdCdv;
	/**  기본과정상태코드값 (bsc_crs_stts_cdv) */
	private String bscCrsSttsCdv;
	/**  기본과정신청자ID (bsc_crs_aplcnt_id) */
	private String bscCrsAplcntId;
	/**  기본과정수료일자 (bsc_crs_cmpltn_ymd) */
	private String bscCrsCmpltnYmd;
	/**  기본과정수료결과코드 (bsc_crs_cmpltn_rslt_code) */
	private String bscCrsCmpltnRsltCode;
	/**  필기시험상태코드값 (wtst_stts_cdv) */
	private String wtstSttsCdv;
	/**  필기시험신청자ID (wtst_aplcnt_id) */
	private String wtstAplcntId;
	/**  필기시험합격일자 (wtst_pass_ymd) */
	private String wtstPassYmd;

	/**  필기시험합격일자 (wtst_vld_end_ymd) */
	private String wtstVldEndYmd;
	/**  실무과정태코드값 (excn_crs_stts_cdv) */
	private String excnCrsSttsCdv;
	/**  실무과정수료일자 (excn_crs_cmpltn_ymd) */
	private String excnCrsCmpltnYmd;
	/**  실무과정신청자ID (excn_crs_aplcnt_id) */
	private String excnCrsAplcntId;
	/**  실무과정수료결과코드 (excn_crs_cmpltn_rslt_code) */
	private String excnCrsCmpltnRsltCode;
	/**  자격증상태코드값 (lcnc_stts_cdv) */
	private String lcncSttsCdv;
	/**  자격증신청자ID (lcnc_aplcnt_id) */
	private String lcncAplcntId;
	/**  자격증취득일자 (lcnc_acqs_ymd) */
	private String lcncAcqsYmd;
	/**  자격결과코드 (qlfc_rslt_code) */
	private String qlfcRsltCode;
	/**  자격증종료일자 (lcnc_end_ymd) */
	private String lcncEndYmd;
	/**  자격증발급코드 (lcnc_issu_code) */
	private String lcncIssuCode;
	/**  발급신청ID (issu_aply_id) */
	private String issuAplyId;
	/**  자격증발급일자 (lcnc_issu_ymd) */
	private String lcncIssuYmd;
	/**  보수교육신청자ID (cntnedu_aplcnt_id) */
	private String cntneduAplcntId;
	/**  보수교육상태코드값 (cntnedu_stts_cdv) */
	private String cntneduSttsCdv;
	/**  보수교육수료일자 (cntnedu_cmpltn_ymd) */
	private String cntneduCmpltnYmd;
	/**  보수교육수료결과코드 (cntnedu_cmpltn_rslt_code) */
	private String cntneduCmpltnRsltCode;
	private String hstryId;
	private String pstpndAplyId;
	private Long vlatCnt;
	private Long cntneduCnt;
	
	private String dvsnCdv;  //법개정 이후 
	private int diffDay;
	private Long preveduCnt;
	private String cntneduBgnDt;
	private String cntneduEndDt;
	private String cntneduAgncyId;
	private String crsGrd;



	

	public String getEsntlId() {
	    return esntlId;
	}
	
	public void setEsntlId(String esntlId) {
	    this.esntlId = esntlId;
	}
	public String getCrsGrdCdv() {
	    return crsGrdCdv;
	}
	
	public void setCrsGrdCdv(String crsGrdCdv) {
	    this.crsGrdCdv = crsGrdCdv;
	}
	public String getBscCrsSttsCdv() {
	    return bscCrsSttsCdv;
	}
	
	public void setBscCrsSttsCdv(String bscCrsSttsCdv) {
	    this.bscCrsSttsCdv = bscCrsSttsCdv;
	}
	public String getBscCrsAplcntId() {
	    return bscCrsAplcntId;
	}
	
	public void setBscCrsAplcntId(String bscCrsAplcntId) {
	    this.bscCrsAplcntId = bscCrsAplcntId;
	}
	public String getBscCrsCmpltnYmd() {
	    return bscCrsCmpltnYmd;
	}
	
	public void setBscCrsCmpltnYmd(String bscCrsCmpltnYmd) {
	    this.bscCrsCmpltnYmd = bscCrsCmpltnYmd;
	}
	public String getWtstSttsCdv() {
	    return wtstSttsCdv;
	}
	
	public void setWtstSttsCdv(String wtstSttsCdv) {
	    this.wtstSttsCdv = wtstSttsCdv;
	}
	public String getWtstAplcntId() {
	    return wtstAplcntId;
	}
	
	public void setWtstAplcntId(String wtstAplcntId) {
	    this.wtstAplcntId = wtstAplcntId;
	}
	
	
	
	public String getExcnCrsSttsCdv() {
		return excnCrsSttsCdv;
	}

	public void setExcnCrsSttsCdv(String excnCrsSttsCdv) {
		this.excnCrsSttsCdv = excnCrsSttsCdv;
	}

	public String getExcnCrsCmpltnYmd() {
	    return excnCrsCmpltnYmd;
	}
	
	public void setExcnCrsCmpltnYmd(String excnCrsCmpltnYmd) {
	    this.excnCrsCmpltnYmd = excnCrsCmpltnYmd;
	}
	public String getExcnCrsAplcntId() {
	    return excnCrsAplcntId;
	}
	
	public void setExcnCrsAplcntId(String excnCrsAplcntId) {
	    this.excnCrsAplcntId = excnCrsAplcntId;
	}
	public String getLcncSttsCdv() {
	    return lcncSttsCdv;
	}
	
	public void setLcncSttsCdv(String lcncSttsCdv) {
	    this.lcncSttsCdv = lcncSttsCdv;
	}
	public String getLcncAplcntId() {
	    return lcncAplcntId;
	}
	
	public void setLcncAplcntId(String lcncAplcntId) {
	    this.lcncAplcntId = lcncAplcntId;
	}
	public String getLcncAcqsYmd() {
	    return lcncAcqsYmd;
	}
	
	public void setLcncAcqsYmd(String lcncAcqsYmd) {
	    this.lcncAcqsYmd = lcncAcqsYmd;
	}
	public String getLcncEndYmd() {
	    return lcncEndYmd;
	}
	
	public void setLcncEndYmd(String lcncEndYmd) {
	    this.lcncEndYmd = lcncEndYmd;
	}
	public String getCntneduAplcntId() {
	    return cntneduAplcntId;
	}
	
	public void setCntneduAplcntId(String cntneduAplcntId) {
	    this.cntneduAplcntId = cntneduAplcntId;
	}
	public String getCntneduSttsCdv() {
	    return cntneduSttsCdv;
	}
	
	public void setCntneduSttsCdv(String cntneduSttsCdv) {
	    this.cntneduSttsCdv = cntneduSttsCdv;
	}
	public String getCntneduCmpltnYmd() {
	    return cntneduCmpltnYmd;
	}
	
	public void setCntneduCmpltnYmd(String cntneduCmpltnYmd) {
	    this.cntneduCmpltnYmd = cntneduCmpltnYmd;
	}

	public String getWtstPassYmd() {
		return wtstPassYmd;
	}

	public void setWtstPassYmd(String wtstPassYmd) {
		this.wtstPassYmd = wtstPassYmd;
	}

	public int getDiffDay() {
		return diffDay;
	}

	public void setDiffDay(int diffDay) {
		this.diffDay = diffDay;
	}

	public String getBscCrsCmpltnRsltCode() {
		return bscCrsCmpltnRsltCode;
	}

	public void setBscCrsCmpltnRsltCode(String bscCrsCmpltnRsltCode) {
		this.bscCrsCmpltnRsltCode = bscCrsCmpltnRsltCode;
	}

	public String getExcnCrsCmpltnRsltCode() {
		return excnCrsCmpltnRsltCode;
	}

	public void setExcnCrsCmpltnRsltCode(String excnCrsCmpltnRsltCode) {
		this.excnCrsCmpltnRsltCode = excnCrsCmpltnRsltCode;
	}

	public String getQlfcRsltCode() {
		return qlfcRsltCode;
	}

	public void setQlfcRsltCode(String qlfcRsltCode) {
		this.qlfcRsltCode = qlfcRsltCode;
	}

	public String getCntneduCmpltnRsltCode() {
		return cntneduCmpltnRsltCode;
	}

	public void setCntneduCmpltnRsltCode(String cntneduCmpltnRsltCode) {
		this.cntneduCmpltnRsltCode = cntneduCmpltnRsltCode;
	}

	public String getLcncIssuCode() {
		return lcncIssuCode;
	}

	public void setLcncIssuCode(String lcncIssuCode) {
		this.lcncIssuCode = lcncIssuCode;
	}

	public String getIssuAplyId() {
		return issuAplyId;
	}

	public void setIssuAplyId(String issuAplyId) {
		this.issuAplyId = issuAplyId;
	}

	public String getLcncIssuYmd() {
		return lcncIssuYmd;
	}

	public void setLcncIssuYmd(String lcncIssuYmd) {
		this.lcncIssuYmd = lcncIssuYmd;
	}

	public String getHstryId() {
		return hstryId;
	}

	public void setHstryId(String hstryId) {
		this.hstryId = hstryId;
	}

	public String getWtstVldEndYmd() {
		return wtstVldEndYmd;
	}

	public void setWtstVldEndYmd(String wtstVldEndYmd) {
		this.wtstVldEndYmd = wtstVldEndYmd;
	}
	
	public Long getVlatCntDefault() {
		if(vlatCnt==null) {
			return 0L;
		}else {
			return vlatCnt;
		}
	}

	public Long getVlatCnt() {
		return vlatCnt;
	}

	public void setVlatCnt(Long vlatCnt) {
		this.vlatCnt = vlatCnt;
	}

	public Long getCntneduCntDefault() {
		if(cntneduCnt==null) {
			return 0L;
		}else {
			return cntneduCnt;
		}
	}
	public Long getCntneduCnt() {
		return cntneduCnt;
	}

	public void setCntneduCnt(Long cntneduCnt) {
		this.cntneduCnt = cntneduCnt;
	}

	public String getDvsnCdv() {
		return dvsnCdv;
	}

	public void setDvsnCdv(String dvsnCdv) {
		this.dvsnCdv = dvsnCdv;
	}

	public String getPstpndAplyId() {
		return pstpndAplyId;
	}

	public void setPstpndAplyId(String pstpndAplyId) {
		this.pstpndAplyId = pstpndAplyId;
	}

	public Long getPreveduCnt() {
		return preveduCnt;
	}

	public void setPreveduCnt(Long preveduCnt) {
		this.preveduCnt = preveduCnt;
	}

	public String getCntneduBgnDt() {
		return cntneduBgnDt;
	}

	public void setCntneduBgnDt(String cntneduBgnDt) {
		this.cntneduBgnDt = cntneduBgnDt;
	}

	public String getCntneduEndDt() {
		return cntneduEndDt;
	}

	public void setCntneduEndDt(String cntneduEndDt) {
		this.cntneduEndDt = cntneduEndDt;
	}

	public String getCntneduAgncyId() {
		return cntneduAgncyId;
	}

	public void setCntneduAgncyId(String cntneduAgncyId) {
		this.cntneduAgncyId = cntneduAgncyId;
	}

	public String getCrsGrd() {
		return crsGrd;
	}

	public void setCrsGrd(String crsGrd) {
		this.crsGrd = crsGrd;
	}

	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}