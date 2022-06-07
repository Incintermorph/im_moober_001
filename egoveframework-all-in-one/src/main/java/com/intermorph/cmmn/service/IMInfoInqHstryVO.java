/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.intermorph.cmmn.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.intermorph.cmmn.base.BaseVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.vo
 * @File : IMInfoInqHstryVO.java
 * @Title : 정보조회이력
 * @date : 2022.03.16 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class IMInfoInqHstryVO extends BaseVO implements Serializable {

/**  ID   */
private String[] infoInqHstryIds;

	/**  정보조회이력ID (info_inq_hstry_id) */
	private String infoInqHstryId;
	/**  테이블ID (tbl_id) */
	private String tblId;
	/**  테이블참조ID (tbl_ref_id) */
	private String tblRefId;
	/**  참조이름 (ref_nm) */
	private String refNm;
	/**  조회사유 (inq_rsn) */
	private String inqRsn;
	/**  조회 url (inq_url) */
	private String inqUrl;
	private String menuNo;



	public String[] getInfoInqHstryIds() {
		if(this.infoInqHstryIds !=null){
			String[] tempData = new String[this.infoInqHstryIds.length];
			System.arraycopy(this.infoInqHstryIds , 0, tempData, 0, this.infoInqHstryIds.length);
			return tempData;
		}else{
			return null;
		}
	}

	public void setInfoInqHstryIds(String[] infoInqHstryIds) {
		if (infoInqHstryIds != null) {
			this.infoInqHstryIds = new String[infoInqHstryIds.length];
			System.arraycopy(infoInqHstryIds, 0, this.infoInqHstryIds, 0, infoInqHstryIds.length);
		} else {
			this.infoInqHstryIds = null;
		}
	}
	

	public String getInfoInqHstryId() {
	    return infoInqHstryId;
	}
	
	public void setInfoInqHstryId(String infoInqHstryId) {
	    this.infoInqHstryId = infoInqHstryId;
	}
	public String getTblId() {
	    return tblId;
	}
	
	public void setTblId(String tblId) {
	    this.tblId = tblId;
	}
	public String getTblRefId() {
	    return tblRefId;
	}
	
	public void setTblRefId(String tblRefId) {
	    this.tblRefId = tblRefId;
	}
	public String getRefNm() {
	    return refNm;
	}
	
	public void setRefNm(String refNm) {
	    this.refNm = refNm;
	}
	public String getInqRsn() {
	    return inqRsn;
	}
	
	public void setInqRsn(String inqRsn) {
	    this.inqRsn = inqRsn;
	}



	public String getInqUrl() {
		return inqUrl;
	}

	public void setInqUrl(String inqUrl) {
		this.inqUrl = inqUrl;
	}

	public String getMenuNo() {
		return menuNo;
	}

	public void setMenuNo(String menuNo) {
		this.menuNo = menuNo;
	}

	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}