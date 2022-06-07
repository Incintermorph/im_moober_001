/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.generator.service;

import java.util.List;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.generator.service
 * @File    : XmlVO.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 2. 21
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
public class XmlVO {
	private String filename; // 파일명
	private String source; // 소스
	private List<IterateVO> iterate; // 반복되는 소스

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public List<IterateVO> getIterate() {
		return iterate;
	}

	public void setIterate(List<IterateVO> iterate) {
		this.iterate = iterate;
	}


}
