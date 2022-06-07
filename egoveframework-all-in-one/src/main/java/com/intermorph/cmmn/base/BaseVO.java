/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.base;



import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.base
 * @File    : BaseVO.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 1. 13
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class BaseVO implements Serializable {

	/** 삭제여부 */
	private String delYn;

	/** 최초등록자ID */
	private String frstRegerId;

	/** 최초등록 일시 */
	private String frstRegDt;

	/** 최초등록IP */
	private String frstRegerIp;
	

	/**  최종수정자 ID */
	private String lastMdferId;

	/** 최종수정 일시 */
	private String lastMdfcnDt;

	/** 최종수정IP */
	private String lastMdferIp;
	
	private String excelResult;

	/** 멀티데이타 수정/삭제에 이용 */
	private Long[] checkIndexs;

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}

	public boolean equals(Object o) {
		return EqualsBuilder.reflectionEquals(this, o);
	}

	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	/**
	 * 해당 vo 에서 기본 컬럼 복사 한다. 
	 * frstRegisterId, frstRegisterIp
	 * lastUpdusrId,lastUpdusrIp
	 * @param vo
	 */
	public void copyAudit(BaseVO vo) {
		this.frstRegerId = vo.getFrstRegerId();
		this.frstRegerIp = vo.getFrstRegerIp();
		this.lastMdferId = vo.getLastMdferId();
		this.lastMdferIp = vo.getLastMdferIp();
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}


	

	

	public String getFrstRegerId() {
		return frstRegerId;
	}

	public void setFrstRegerId(String frstRegerId) {
		this.frstRegerId = frstRegerId;
	}

	public String getFrstRegDt() {
		return frstRegDt;
	}

	public void setFrstRegDt(String frstRegDt) {
		this.frstRegDt = frstRegDt;
	}

	public String getFrstRegerIp() {
		return frstRegerIp;
	}

	public void setFrstRegerIp(String frstRegerIp) {
		this.frstRegerIp = frstRegerIp;
	}

	public String getLastMdferId() {
		return lastMdferId;
	}

	public void setLastMdferId(String lastMdferId) {
		this.lastMdferId = lastMdferId;
	}

	public String getLastMdfcnDt() {
		return lastMdfcnDt;
	}

	public void setLastMdfcnDt(String lastMdfcnDt) {
		this.lastMdfcnDt = lastMdfcnDt;
	}

	public String getLastMdferIp() {
		return lastMdferIp;
	}

	public void setLastMdferIp(String lastMdferIp) {
		this.lastMdferIp = lastMdferIp;
	}


	public String getExcelResult() {
		return excelResult;
	}

	public void setExcelResult(String excelResult) {
		this.excelResult = excelResult;
	}

	public Long[] getCheckIndexs() {
		if(this.checkIndexs !=null){
			Long[] tempData = new Long[this.checkIndexs.length];
			System.arraycopy(this.checkIndexs , 0, tempData, 0, this.checkIndexs.length);
			return tempData;
		}else{
			return null;
		}
	}

	public void setCheckIndexs(Long[] checkIndexs) {
		if (checkIndexs != null) {
			this.checkIndexs = new Long[checkIndexs.length];
			System.arraycopy(checkIndexs, 0, this.checkIndexs, 0, checkIndexs.length);
		} else {
			this.checkIndexs = null;
		}
	}

	

}
