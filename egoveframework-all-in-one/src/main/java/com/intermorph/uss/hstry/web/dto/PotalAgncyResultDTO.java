/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.hstry.web.dto;

import java.util.List;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.util.potal
 * @File    : PotalAgncyResultDTO.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 3. 17
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
public class PotalAgncyResultDTO {
	private String msg;
	private String paginationAdmin;
	private List<PotalAgncyListDTO> institutionList;
	
	private Long page;
	private Long totalCount;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<PotalAgncyListDTO> getInstitutionList() {
		return institutionList;
	}
	public void setInstitutionList(List<PotalAgncyListDTO> institutionList) {
		this.institutionList = institutionList;
	}
	public Long getPage() {
		return page;
	}
	public void setPage(Long page) {
		this.page = page;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public String getPaginationAdmin() {
		return paginationAdmin;
	}
	public void setPaginationAdmin(String paginationAdmin) {
		this.paginationAdmin = paginationAdmin;
	}
	
	
}
