/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.crs.aplcnt.service;

import java.io.Serializable;
import java.text.ParseException;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.intermorph.cmmn.base.BaseVO;
import com.intermorph.cmmn.util.IMDateUtil;
import com.intermorph.cmmn.util.IMStringUtil;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.crs.aplcnt.vo
 * @File : IMCrsAplcntVO.java
 * @Title : 운영과정신청자
 * @date : 2022.03.03 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class IMCrsAplcntVO extends BaseVO implements Serializable {

/**  ID   */
private String[] crsAplcntIds;

	/**  과정신청자ID (crs_aplcnt_id) */
	private String crsAplcntId;
	/**  과정ID (crs_id) */
	private String crsId;
	/**  회원고유ID (mmbr_esntl_id) */
	private String mmbrEsntlId;
	

	/**  회원ID (mber_id) */
	private String mberId;
	/**  회원명 (mber_nm) */
	private String mberNm;
	/**  회원이메일주소 (mber_email_adres) */
	private String mberEmailAdres;
	/**  회원전화번호 (mmbr_telno) */
	private String mmbrTelno;
	private String chageStts; //변경 수강상태
	private String chageMsg; //변경 메시지
	
	private String ttnfeeChgCdv;
	private String exptSbjYn;
	private String dsrAplyGrdCdv;

	/**  생년월일 (brdt) */
	private String brdt;
	/**  수료상태코드값 (fnsh_stts_cdv) */
	private String fnshSttsCdv;
	/**  수료결과코드 (fnsh_rslt_code) */
	private String fnshRsltCode;
	/**  자격상태코드값 (qlfc_stts_cdv) */
	private String qlfcSttsCdv;
	/**  자격결과코드 (qlfc_rslt_code) */
	private String qlfcRsltCode;
	private String fnshYmd;
	private String qlfcAcqsYmd;
	private String qlfcVldYmd;

	/**  교육수수료 (ttnfee) */
	private String ttnfee;
	/**교육시간*/
	private String eduHrs;	
	private String batchYn;	
	
	

	private String agncySrngCdv;
	private String agncySrngDt;
	private String opsectSrngCdv;
	private String opsectSrngDt;
	private String agncySrngRmks;
	private String opsectSrngRmks;
	/**  근무구분코드값 (work_dvsn_cdv) */
	private String workDvsnCdv;
	/**  기관코드 (agncy_code) */
	private String agncyCode;
	/**  기관명 (agncy_nm) */
	private String agncyNm;
	/** 양성기관 ID (agncy_id) */
	private String agncyId;
	/** 과정등급코드값CRS_GRD_CDV*/
	private String crsGrdCdv;
	/** 과정구분코드값 CRS_DVSN_CDV*/
	private String crsDvsnCdv;

	private int cnt;
	private String statType;
	private String fnshVldYmd;
	

	private Long cntneduCnt;
	private String cntneduBgnDt;
	private String cntneduEndDt;
	private String cntneduAgncyId;

	public String[] getCrsAplcntIds() {
		if(this.crsAplcntIds !=null){
			String[] tempData = new String[this.crsAplcntIds.length];
			System.arraycopy(this.crsAplcntIds , 0, tempData, 0, this.crsAplcntIds.length);
			return tempData;
		}else{
			return null;
		}
	}

	public void setCrsAplcntIds(String[] crsAplcntIds) {
		if (crsAplcntIds != null) {
			this.crsAplcntIds = new String[crsAplcntIds.length];
			System.arraycopy(crsAplcntIds, 0, this.crsAplcntIds, 0, crsAplcntIds.length);
		} else {
			this.crsAplcntIds = null;
		}
	}
	

	public String getCrsAplcntId() {
	    return crsAplcntId;
	}
	
	public void setCrsAplcntId(String crsAplcntId) {
	    this.crsAplcntId = crsAplcntId;
	}
	public String getCrsId() {
	    return crsId;
	}
	
	public void setCrsId(String crsId) {
	    this.crsId = crsId;
	}
	public String getMmbrEsntlId() {
	    return mmbrEsntlId;
	}
	
	public void setMmbrEsntlId(String mmbrEsntlId) {
	    this.mmbrEsntlId = mmbrEsntlId;
	}



	public String getMberId() {
		return mberId;
	}
	public String getMberIdMask() {
		return IMStringUtil.getIdMasking(mberId);
	}
	public void setMberId(String mberId) {
		this.mberId = mberId;
	}

	public String getMberNm() {
		return mberNm;
	}
	
	public String getMberNmMask() {
	     return IMStringUtil.getNameMasking(mberNm);
	}

	public void setMberNm(String mberNm) {
		this.mberNm = mberNm;
	}

	public String getMberEmailAdres() {
		return mberEmailAdres;
	}
	
	public String getMberEmailAdresMask() {
		return IMStringUtil.getEmailMasking(mberEmailAdres);
	}

	public void setMberEmailAdres(String mberEmailAdres) {
		this.mberEmailAdres = mberEmailAdres;
	}

	public String getMmbrTelno() {
		return mmbrTelno;
	}
	
	public String getMmbrTelnoMask() {
		return IMStringUtil.getPhoneMasking(mmbrTelno);
	}

	public void setMmbrTelno(String mmbrTelno) {
		this.mmbrTelno = mmbrTelno;
	}


	public String getChageStts() {
		return chageStts;
	}

	public void setChageStts(String chageStts) {
		this.chageStts = chageStts;
	}
	
	

	public String getTtnfeeChgCdv() {
		return ttnfeeChgCdv;
	}

	public void setTtnfeeChgCdv(String ttnfeeChgCdv) {
		this.ttnfeeChgCdv = ttnfeeChgCdv;
	}

	public String getExptSbjYn() {
		return exptSbjYn;
	}

	public void setExptSbjYn(String exptSbjYn) {
		this.exptSbjYn = exptSbjYn;
	}

	public String getDsrAplyGrdCdv() {
		return dsrAplyGrdCdv;
	}

	public void setDsrAplyGrdCdv(String dsrAplyGrdCdv) {
		this.dsrAplyGrdCdv = dsrAplyGrdCdv;
	}

	public String getTtnfee() {
		return ttnfee;
	}

	public void setTtnfee(String ttnfee) {
		this.ttnfee = ttnfee;
	}

	public String getChageMsg() {
		return chageMsg;
	}

	public void setChageMsg(String chageMsg) {
		this.chageMsg = chageMsg;
	}

	public String getBrdt() {
		return brdt;
	}


	public String getBrdtFormat() {
		if(!IMStringUtil.isEmpty(brdt)) {
			brdt = brdt.replaceAll("[.-]", "");
			return brdt.substring(0, 4)+"-"+brdt.substring(4, 6)+"-"+brdt.substring(6, 8);
		}else {
			return brdt;
		}
	}
	
	public String getBrdtPrint() {
		if(!IMStringUtil.isEmpty(brdt)) {
			brdt = brdt.replaceAll("[.-]", "");
			return brdt.substring(0, 4)+"."+brdt.substring(4, 6)+"."+brdt.substring(6, 8);
		}else {
			return brdt;
		}
	}
	
	public void setBrdt(String brdt) {
		this.brdt = brdt;
	}

	public String getFnshSttsCdv() {
		return fnshSttsCdv;
	}

	public void setFnshSttsCdv(String fnshSttsCdv) {
		this.fnshSttsCdv = fnshSttsCdv;
	}

	public String getFnshRsltCode() {
		return fnshRsltCode;
	}

	public void setFnshRsltCode(String fnshRsltCode) {
		this.fnshRsltCode = fnshRsltCode;
	}

	public String getQlfcSttsCdv() {
		return qlfcSttsCdv;
	}

	public void setQlfcSttsCdv(String qlfcSttsCdv) {
		this.qlfcSttsCdv = qlfcSttsCdv;
	}

	public String getQlfcRsltCode() {
		return qlfcRsltCode;
	}

	public void setQlfcRsltCode(String qlfcRsltCode) {
		this.qlfcRsltCode = qlfcRsltCode;
	}

	public String getFnshYmd() {
		return fnshYmd;
	}
	public String getFnshYmdFomat() {
		
		try {
			return IMDateUtil.getFormatString(fnshYmd, "yyyy-MM-dd");
		} catch (ParseException e) {
			return null;
		}
	}
	
	public String getFnshYmdYYYY() {
		
		try {
			return IMDateUtil.getFormatString(fnshYmd, "yyyy");
		} catch (ParseException e) {
			return null;
		}
	}
	public String getFnshYmdMM() {
		
		try {
			return IMDateUtil.getFormatString(fnshYmd, "MM");
		} catch (ParseException e) {
			return null;
		}
	}
	public String getFnshYmdDD() {
		
		try {
			return IMDateUtil.getFormatString(fnshYmd, "dd");
		} catch (ParseException e) {
			return null;
		}
	}

	public void setFnshYmd(String fnshYmd) {
		this.fnshYmd = fnshYmd;
	}

	public String getQlfcAcqsYmd() {
		return qlfcAcqsYmd;
	}
	public String getQlfcAcqsYmdFomat() {
		try {
			return IMDateUtil.getFormatString(qlfcAcqsYmd, "yyyy-MM-dd");
		} catch (ParseException e) {
			return null;
		}
	}
	public String getQlfcAcqsYmdYYYY() {
		try {
			return IMDateUtil.getFormatString(qlfcAcqsYmd, "yyyy");
		} catch (ParseException e) {
			return null;
		}
	}
	public String getQlfcAcqsYmdMM() {
		try {
			return IMDateUtil.getFormatString(qlfcAcqsYmd, "MM");
		} catch (ParseException e) {
			return null;
		}
	}
	public String getQlfcAcqsYmdDD() {
		try {
			return IMDateUtil.getFormatString(qlfcAcqsYmd, "dd");
		} catch (ParseException e) {
			return null;
		}
	}

	public void setQlfcAcqsYmd(String qlfcAcqsYmd) {
		this.qlfcAcqsYmd = qlfcAcqsYmd;
	}

	public String getWorkDvsnCdv() {
		return workDvsnCdv;
	}

	public void setWorkDvsnCdv(String workDvsnCdv) {
		this.workDvsnCdv = workDvsnCdv;
	}

	public String getAgncyCode() {
		return agncyCode;
	}

	public void setAgncyCode(String agncyCode) {
		this.agncyCode = agncyCode;
	}

	public String getAgncyNm() {
		return agncyNm;
	}

	public void setAgncyNm(String agncyNm) {
		this.agncyNm = agncyNm;
	}

	public String getEduHrs() {
		return eduHrs;
	}

	public void setEduHrs(String eduHrs) {
		this.eduHrs = eduHrs;
	}

	public String getAgncyId() {
		return agncyId;
	}

	public void setAgncyId(String agncyId) {
		this.agncyId = agncyId;
	}

	public String getCrsGrdCdv() {
		return crsGrdCdv;
	}

	public void setCrsGrdCdv(String crsGrdCdv) {
		this.crsGrdCdv = crsGrdCdv;
	}

	public String getCrsDvsnCdv() {
		return crsDvsnCdv;
	}

	public void setCrsDvsnCdv(String crsDvsnCdv) {
		this.crsDvsnCdv = crsDvsnCdv;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public String getStatType() {
		return statType;
	}

	public void setStatType(String statType) {
		this.statType = statType;
	}

	public String getQlfcVldYmd() {
		return qlfcVldYmd;
	}

	public void setQlfcVldYmd(String qlfcVldYmd) {
		this.qlfcVldYmd = qlfcVldYmd;
	}

	public String getFnshVldYmd() {
		return fnshVldYmd;
	}

	public void setFnshVldYmd(String fnshVldYmd) {
		this.fnshVldYmd = fnshVldYmd;
	}

	public Long getCntneduCnt() {
		return cntneduCnt;
	}

	public void setCntneduCnt(Long cntneduCnt) {
		this.cntneduCnt = cntneduCnt;
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

	public String getAgncySrngCdv() {
		return agncySrngCdv;
	}

	public void setAgncySrngCdv(String agncySrngCdv) {
		this.agncySrngCdv = agncySrngCdv;
	}

	public String getAgncySrngDt() {
		return agncySrngDt;
	}

	public void setAgncySrngDt(String agncySrngDt) {
		this.agncySrngDt = agncySrngDt;
	}
	
	public String getAgncySrngDtFomat() {
		try {
			return IMDateUtil.getFormatString(agncySrngDt, "yyyy-MM-dd");
		} catch (ParseException e) {
			return null;
		}
	}

	public String getOpsectSrngCdv() {
		return opsectSrngCdv;
	}

	public void setOpsectSrngCdv(String opsectSrngCdv) {
		this.opsectSrngCdv = opsectSrngCdv;
	}

	public String getOpsectSrngDt() {
		return opsectSrngDt;
	}
	
	public String getOpsectSrngDtFomat() {
		try {
			return IMDateUtil.getFormatString(opsectSrngDt, "yyyy-MM-dd");
		} catch (ParseException e) {
			return null;
		}
	}

	public void setOpsectSrngDt(String opsectSrngDt) {
		this.opsectSrngDt = opsectSrngDt;
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

	public String getBatchYn() {
		return batchYn;
	}

	public void setBatchYn(String batchYn) {
		this.batchYn = batchYn;
	}

	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}