/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc
 *
 */
package com.potal.uss.institution.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.intermorph.cmmn.base.BaseVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.potal.uss.institution.vo
 * @File : IMEpInstitutionVO.java
 * @Title : 기관(포탈)
 * @date : 2022.03.29 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class IMEpInstitutionVO extends BaseVO implements Serializable {

	/** (inst_no) */
	private String instNo;
	/** (org_name) */
	private String orgName;
	/** (biz_kind) */
	private String bizKind;
	
	private String bizKindName;
	/** (biz_no) */
	private String bizNo;
	/** (repre_name) */
	private String repreName;
	/** (post) */
	private String post;
	/** (addr1) */
	private String addr1;
	/** (addr2) */
	private String addr2;
	/** (member_srl) */
	private String memberSrl;
	/** (tel) */
	private String tel;
	/** (fax) */
	private String fax;
	/** (course_level) */
	private String courseLevel;
	/** (org_logo_fname) */
	private String orgLogoFname;
	/** (org_logo_fpath) */
	private String orgLogoFpath;
	/** (status) */
	private String status;
	/** (org_cert_comment) */
	private String orgCertComment;
	/** (asis_org_userid) */
	private String asisOrgUserid;
	/** (site_url) */
	private String siteUrl;
	/** (createdate) */
	private String createdate;
	/** (creator) */
	private String creator;
	/** (updateddate) */
	private String updateddate;
	/** (updator) */
	private String updator;
	/** (use_yn) */
	private String useYn;


	private String name;
	private String userid;  
	private String email;


	public String getInstNo() {
	    return instNo;
	}
	
	public void setInstNo(String instNo) {
	    this.instNo = instNo;
	}
	public String getOrgName() {
	    return orgName;
	}
	
	public void setOrgName(String orgName) {
	    this.orgName = orgName;
	}
	public String getBizKind() {
	    return bizKind;
	}
	
	public void setBizKind(String bizKind) {
	    this.bizKind = bizKind;
	}
	public String getBizNo() {
	    return bizNo;
	}
	
	public void setBizNo(String bizNo) {
	    this.bizNo = bizNo;
	}
	public String getRepreName() {
	    return repreName;
	}
	
	public void setRepreName(String repreName) {
	    this.repreName = repreName;
	}
	public String getPost() {
	    return post;
	}
	
	public void setPost(String post) {
	    this.post = post;
	}
	public String getAddr1() {
	    return addr1;
	}
	
	public void setAddr1(String addr1) {
	    this.addr1 = addr1;
	}
	public String getAddr2() {
	    return addr2;
	}
	
	public void setAddr2(String addr2) {
	    this.addr2 = addr2;
	}
	public String getMemberSrl() {
	    return memberSrl;
	}
	
	public void setMemberSrl(String memberSrl) {
	    this.memberSrl = memberSrl;
	}
	public String getTel() {
	    return tel;
	}
	
	public void setTel(String tel) {
	    this.tel = tel;
	}
	public String getFax() {
	    return fax;
	}
	
	public void setFax(String fax) {
	    this.fax = fax;
	}
	public String getCourseLevel() {
	    return courseLevel;
	}
	
	public void setCourseLevel(String courseLevel) {
	    this.courseLevel = courseLevel;
	}
	public String getOrgLogoFname() {
	    return orgLogoFname;
	}
	
	public void setOrgLogoFname(String orgLogoFname) {
	    this.orgLogoFname = orgLogoFname;
	}
	public String getOrgLogoFpath() {
	    return orgLogoFpath;
	}
	
	public void setOrgLogoFpath(String orgLogoFpath) {
	    this.orgLogoFpath = orgLogoFpath;
	}
	public String getStatus() {
	    return status;
	}
	
	public void setStatus(String status) {
	    this.status = status;
	}
	public String getOrgCertComment() {
	    return orgCertComment;
	}
	
	public void setOrgCertComment(String orgCertComment) {
	    this.orgCertComment = orgCertComment;
	}
	public String getAsisOrgUserid() {
	    return asisOrgUserid;
	}
	
	public void setAsisOrgUserid(String asisOrgUserid) {
	    this.asisOrgUserid = asisOrgUserid;
	}
	public String getSiteUrl() {
	    return siteUrl;
	}
	
	public void setSiteUrl(String siteUrl) {
	    this.siteUrl = siteUrl;
	}
	public String getCreatedate() {
	    return createdate;
	}
	
	public void setCreatedate(String createdate) {
	    this.createdate = createdate;
	}
	public String getCreator() {
	    return creator;
	}
	
	public void setCreator(String creator) {
	    this.creator = creator;
	}
	public String getUpdateddate() {
	    return updateddate;
	}
	
	public void setUpdateddate(String updateddate) {
	    this.updateddate = updateddate;
	}
	public String getUpdator() {
	    return updator;
	}
	
	public void setUpdator(String updator) {
	    this.updator = updator;
	}
	public String getUseYn() {
	    return useYn;
	}
	
	public void setUseYn(String useYn) {
	    this.useYn = useYn;
	}



	public String getBizKindName() {
		return bizKindName;
	}

	public void setBizKindName(String bizKindName) {
		this.bizKindName = bizKindName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}