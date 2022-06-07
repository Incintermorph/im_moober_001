/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.generator.service;

import java.io.Serializable;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.generator.service
 * @File    : IterateVO.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 2. 21
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
@SuppressWarnings("serial")
public class IterateVO implements Serializable {
	private String nodeName; // 노드명
	private String nodeValue; // 노드값
	private String attrJoin; // 속성 join값
	private String attrTabSize; // 속성 tabsize값
	private String attrSpaceSize; // 속성 spacesize값
	private String attrAudit; // 속성 audit값

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getNodeValue() {
		return nodeValue;
	}

	public void setNodeValue(String nodeValue) {
		this.nodeValue = nodeValue;
	}

	public String getAttrJoin() {
		return attrJoin;
	}

	public void setAttrJoin(String attrJoin) {
		this.attrJoin = attrJoin;
	}

	public String getAttrTabSize() {
		return attrTabSize;
	}

	public void setAttrTabSize(String attrTabSize) {
		this.attrTabSize = attrTabSize;
	}

	public String getAttrSpaceSize() {
		return attrSpaceSize;
	}

	public void setAttrSpaceSize(String attrSpaceSize) {
		this.attrSpaceSize = attrSpaceSize;
	}

	public String getAttrAudit() {
		return attrAudit;
	}

	public void setAttrAudit(String attrAudit) {
		this.attrAudit = attrAudit;
	}
}
