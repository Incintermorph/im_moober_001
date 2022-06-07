/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.service;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.service
 * @File    : IMSSOLoginDTO.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 3. 11
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
public class IMSSOLoginDTO {
	private String result; 
	private String msg; 
	private String name; 
	private String tel; 
	private String id; 
	private String member_srl; 
	private String email;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMember_srl() {
		return member_srl;
	}
	public void setMember_srl(String member_srl) {
		this.member_srl = member_srl;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	} 
	
	
}
