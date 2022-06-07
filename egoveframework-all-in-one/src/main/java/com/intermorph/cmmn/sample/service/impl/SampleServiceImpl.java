/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.sample.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BaseResultSet;
import com.intermorph.cmmn.sample.service.SampleMapper;
import com.intermorph.cmmn.sample.service.SampleService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.sample.service.impl
 * @File    : SampleServiceImpl.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 1. 13
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
@Service ("SampleService")
public class SampleServiceImpl extends EgovAbstractServiceImpl   implements SampleService {
	@Resource (name = "SampleMapper")
	protected SampleMapper sampleMapper;

	/* (non-Javadoc)
	 * @see com.linkbrain.sample.service.SampleService#getDetail(com.linkbrain.base.BaseCondition)
	 */
	@Override
	public BaseResultSet getDetail(BaseCondition conditionVO) throws Exception {
		return sampleMapper.getDetail(conditionVO);
	}

}
