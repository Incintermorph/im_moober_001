/**
 * 
 */
package com.intermorph.cmmn.sample.vo;

import java.io.Serializable;

import com.intermorph.cmmn.base.BaseVO;
/**
 * 
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.sample.vo
 * @File    : LBSample.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 1. 13
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class LBSample extends BaseVO implements Serializable {

	/** cs_call_manager_seq */
	private Long callManagerSeq;
	/** cs_call_manager_seqs */
	private Long[] callManagerSeqs;
	/** cs_call_name */
	private String callName;
	/** cs_title */
	private String title;
	/** cs_description */
	private String description;
	/** cs_call_category_cd */
	private String callCategoryCd;
	/** cs_call_state_cd */
	private String callStateCd;
	/** cs_call_auth_cd */
	private String callAuthCd;
	/** cs_call_type_cd */
	private String callTypeCd;
	/** cs_upd_member_name */
	private String updMemberName;
	/** 요청자 회원 SEQ */
	private Long callMemberSeq;
	/** 요청자 연락처 */
	private String callPhone;

	/** 실제 연락가능한 연락처 */
	private String realPhone;
	private String realEmail;
	public Long getCallManagerSeq() {
		return callManagerSeq;
	}
	public void setCallManagerSeq(Long callManagerSeq) {
		this.callManagerSeq = callManagerSeq;
	}
	public Long[] getCallManagerSeqs() {
		return callManagerSeqs;
	}
	public void setCallManagerSeqs(Long[] callManagerSeqs) {
		this.callManagerSeqs = callManagerSeqs;
	}
	public String getCallName() {
		return callName;
	}
	public void setCallName(String callName) {
		this.callName = callName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCallCategoryCd() {
		return callCategoryCd;
	}
	public void setCallCategoryCd(String callCategoryCd) {
		this.callCategoryCd = callCategoryCd;
	}
	public String getCallStateCd() {
		return callStateCd;
	}
	public void setCallStateCd(String callStateCd) {
		this.callStateCd = callStateCd;
	}
	public String getCallAuthCd() {
		return callAuthCd;
	}
	public void setCallAuthCd(String callAuthCd) {
		this.callAuthCd = callAuthCd;
	}
	public String getCallTypeCd() {
		return callTypeCd;
	}
	public void setCallTypeCd(String callTypeCd) {
		this.callTypeCd = callTypeCd;
	}
	public String getUpdMemberName() {
		return updMemberName;
	}
	public void setUpdMemberName(String updMemberName) {
		this.updMemberName = updMemberName;
	}
	public Long getCallMemberSeq() {
		return callMemberSeq;
	}
	public void setCallMemberSeq(Long callMemberSeq) {
		this.callMemberSeq = callMemberSeq;
	}
	public String getCallPhone() {
		return callPhone;
	}
	public void setCallPhone(String callPhone) {
		this.callPhone = callPhone;
	}
	public String getRealPhone() {
		return realPhone;
	}
	public void setRealPhone(String realPhone) {
		this.realPhone = realPhone;
	}
	public String getRealEmail() {
		return realEmail;
	}
	public void setRealEmail(String realEmail) {
		this.realEmail = realEmail;
	}
	
	
	
}
