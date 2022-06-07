/*
 *  * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import com.intermorph.cmmn.service.IMLgnSttsMapper;
import com.intermorph.cmmn.service.IMLgnSttsService;
import com.intermorph.cmmn.service.IMLgnSttsVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.service.impl
 * @File : LgnSttsServiceImpl.java
 * @Title : 로그인 현황
 * @date : 2022.04.12 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Service ("IMLgnSttsService")
public class IMLgnSttsServiceImpl  implements IMLgnSttsService {

    @Resource (name = "IMLgnSttsMapper")
    private IMLgnSttsMapper lgnSttsMapper;
    
   

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.cmmn.service.IMLgnSttsService#insertLgnStts(com.intermorph.cmmn.vo.IMLgnSttsVO)
     */
    public int insertLgnStts(IMLgnSttsVO vo) throws Exception {
        int success = 0;
        success = lgnSttsMapper.update(vo);
        if(success == 0) {
        	success = lgnSttsMapper.insert(vo);
        }
        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.cmmn.service.IMLgnSttsService#updateLgnStts(com.intermorph.cmmn.vo.IMLgnSttsVO)
     */
    public int updateLgnStts(IMLgnSttsVO vo) throws Exception {
        int success = 0;
        success = lgnSttsMapper.update(vo);
        return success;
    }



	
	/* (non-Javadoc)
	 * @see com.intermorph.cmmn.service.IMLgnSttsService#selectDetail(String)
	 */
	@Override
	public IMLgnSttsVO selectDetailLgnStts(String esntlId) throws Exception {
		
		return lgnSttsMapper.selectDetail(esntlId);
	}
	

	
	
   
}