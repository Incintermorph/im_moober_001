/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.crs.agncy.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.intermorph.cmmn.base.BaseVO;
import com.intermorph.cmmn.util.IMStringUtil;

import egovframework.com.sec.gmt.service.GroupManage;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.crs.agncy.vo
 * @File : IMAgncyVO.java
 * @Title : 양성기관
 * @date : 2022.02.21 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class IMAgncyVO extends BaseVO implements Serializable {

	private String[] agncyIds;
	/** 기관ID (agncy_id) */
	private String agncyId;
	/** 기관명 (agncy_nm) */
	private String agncyNm;
	/**  기관구분코드값 (agncy_dvsn_cdv) */
	private String agncyDvsnCdv;
	/** 사업자등록번호 (brno) */
	private String brno;
	/** 과정등급코드값 (crs_grd_cdv) */
	private String crsGrdCdv;
	/** 전화번호 (telno) */
	private String telno;
	private String telno1;
	private String telno2;
	private String telno3;
	/** fAX (fax) */
	private String fax;
	/** 은행코드값 (bnk_cdv) */
	private String bnkCdv;
	/** 계좌번호 (accno) */
	private String accno;
	/** 예금주 (acchdr) */
	private String acchdr;
	/** 우편번호 (zipc) */
	private String zipc;
	/** 주소 (addr) */
	private String addr;
	/**  주소시도 (addr_sido) */
	private String addrSido;
	/**  주소시군구 (addr_sigungu) */
	private String addrSigungu;
	
	/** 주소상세 (addr_dtl) */
	private String addrDtl;
	/** 기관URL (agncy_url) */
	private String agncyUrl;
	/** 로고 파일 ID(logo) */
	private String logoFileId;
	/** 직인 파일 ID(logo) */
	private String sealFileId;
	/** 지정여부 (dsgn_yn) */
	private String dsgnYn;
	
	/**  지역코드값 (area_cdv) */
	private String areaCdv;
	/**  지역상세 (area_dtl) */
	private String areaDtl;
	/**  연계코드 (link_code) */
	private String linkCode;
	/**  수료코드 (fnsh_code) */
	private String fnshCode;
	
	/** 장문글 */
	private String cmmnDesc;
	
	
	
	public String getAgncyId() {
		return agncyId;
	}

	public void setAgncyId(String agncyId) {
		this.agncyId = agncyId;
	}

	public String getAgncyNm() {
		return agncyNm;
	}

	public void setAgncyNm(String agncyNm) {
		this.agncyNm = agncyNm;
	}

	public String getAgncyDvsnCdv() {
		return agncyDvsnCdv;
	}

	public void setAgncyDvsnCdv(String agncyDvsnCdv) {
		this.agncyDvsnCdv = agncyDvsnCdv;
	}

	public String getBrno() {
		return brno;
	}

	public void setBrno(String brno) {
		this.brno = brno;
	}

	public String getCrsGrdCdv() {
		return crsGrdCdv;
	}

	public void setCrsGrdCdv(String crsGrdCdv) {
		this.crsGrdCdv = crsGrdCdv;
	}

	public String getTelno() {
		return telno;
	}
	
	public String getTelnoDB() {
		return IMStringUtil.getPhoneNumDB(telno1, telno2, telno3);
	}
	
	public String getTelno1() {
		return telno1;
	}

	public void setTelno1(String telno1) {
		this.telno1 = telno1;
	}

	public String getTelno2() {
		return telno2;
	}

	public void setTelno2(String telno2) {
		this.telno2 = telno2;
	}

	public String getTelno3() {
		return telno3;
	}

	public void setTelno3(String telno3) {
		this.telno3 = telno3;
	}
	

	public void setTelno(String telno) {
		setTelno1(IMStringUtil.getPhoneNum(telno, 0));
		setTelno2(IMStringUtil.getPhoneNum(telno, 1));
		setTelno3(IMStringUtil.getPhoneNum(telno, 2));
		this.telno = telno;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
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

	public String getZipc() {
		return zipc;
	}

	public void setZipc(String zipc) {
		this.zipc = zipc;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getAddrDtl() {
		return addrDtl;
	}

	public void setAddrDtl(String addrDtl) {
		this.addrDtl = addrDtl;
	}

	public String getAgncyUrl() {
		return agncyUrl;
	}

	public void setAgncyUrl(String agncyUrl) {
		this.agncyUrl = agncyUrl;
	}	
	public String getLogoFileId() {
		return logoFileId;
	}

	public void setLogoFileId(String logoFileId) {
		this.logoFileId = logoFileId;
	}

	public String getDsgnYn() {
		return dsgnYn;
	}

	public void setDsgnYn(String dsgnYn) {
		this.dsgnYn = dsgnYn;
	}

	public String getAddrSido() {
		return addrSido;
	}

	public void setAddrSido(String addrSido) {
		this.addrSido = addrSido;
	}

	public String getAddrSigungu() {
		return addrSigungu;
	}

	public void setAddrSigungu(String addrSigungu) {
		this.addrSigungu = addrSigungu;
	}
	
	

	public String getSealFileId() {
		return sealFileId;
	}

	public void setSealFileId(String sealFileId) {
		this.sealFileId = sealFileId;
	}
	
	
	public String getAreaCdvRgn() {
		String areaCdvRgn="";
		if(areaCdv!=null && areaCdv.length()>2) {
			areaCdvRgn = areaCdv.substring(0, 2);
		}
		return areaCdvRgn;
	}

	public String getAreaCdv() {
		return areaCdv;
	}

	public void setAreaCdv(String areaCdv) {
		this.areaCdv = areaCdv;
	}

	public String getAreaDtl() {
		return areaDtl;
	}

	public void setAreaDtl(String areaDtl) {
		this.areaDtl = areaDtl;
	}

	public String[] getAgncyIds() {
		if(this.agncyIds !=null){
			String[] tempData = new String[this.agncyIds.length];
			System.arraycopy(this.agncyIds , 0, tempData, 0, this.agncyIds.length);
			return tempData;
		}else{
			return null;
		}
	}

	public void setAgncyIds(String[] agncyIds) {
		if (agncyIds != null) {
			this.agncyIds = new String[agncyIds.length];
			System.arraycopy(agncyIds, 0, this.agncyIds, 0, agncyIds.length);
		} else {
			this.agncyIds = null;
		}
	}
	
	
	public String getLinkCode() {
		return linkCode;
	}

	public void setLinkCode(String linkCode) {
		this.linkCode = linkCode;
	}

	
	public String getCmmnDesc() {
		return cmmnDesc;
	}

	public void setCmmnDesc(String cmmnDesc) {
		this.cmmnDesc = cmmnDesc;
	}

	/**
	 * 그룹정보 세팅 
	 * @param groupManage
	 */
	public void copyGroupManage(GroupManage groupManage) {
		 groupManage.setGroupId(this.getAgncyId());
		 groupManage.setGroupNm(getAgncyNm());
		 groupManage.setGroupDc(getAgncyNm() +" Agncy Copy");
	}
	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getFnshCode() {
		return fnshCode;
	}

	public void setFnshCode(String fnshCode) {
		this.fnshCode = fnshCode;
	}
	
	
}