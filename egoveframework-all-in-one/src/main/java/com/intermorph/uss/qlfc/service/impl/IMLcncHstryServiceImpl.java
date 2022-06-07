/*
 *  * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.qlfc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;


import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import com.intermorph.uss.qlfc.service.IMLcncHstryMapper;
import com.intermorph.uss.qlfc.service.IMLcncHstryService;
import com.intermorph.uss.qlfc.service.IMLcncHstryVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.qlfc.service.impl
 * @File : LcncHstryServiceImpl.java
 * @Title : 자격증 이력
 * @date : 2022.05.04 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Service ("IMLcncHstryService")
public class IMLcncHstryServiceImpl extends EgovAbstractServiceImpl  implements IMLcncHstryService {

    @Resource (name = "IMLcncHstryMapper")
    private IMLcncHstryMapper lcncHstryMapper;
    
    @Resource(name = "imLcncHstryIdGnrService")
    private EgovIdGnrService idgenService;


    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.uss.qlfc.service.IMLcncHstryService#insertLcncHstry(com.intermorph.uss.qlfc.vo.IMLcncHstryVO)
     */
    public int insertLcncHstry(IMLcncHstryVO vo) throws Exception {
        int success = 0;
        lcncHstryMapper.drop(vo);

        vo.setLcncHstryId(idgenService.getNextStringId());
        success = lcncHstryMapper.insert(vo);

        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.uss.qlfc.service.IMLcncHstryService#updateLcncHstry(com.intermorph.uss.qlfc.vo.IMLcncHstryVO)
     */
    public int updateLcncHstry(IMLcncHstryVO vo) throws Exception {
        int success = 0;
        success = lcncHstryMapper.update(vo);
        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.uss.qlfc.service.IMLcncHstryService#updatelistLcncHstry(java.util.List)
     */
    public int updatelistLcncHstry(List<IMLcncHstryVO> voList) throws Exception {
        int success = 0;
        if (voList != null) {
            for (IMLcncHstryVO vo : voList) {
                success += lcncHstryMapper.update(vo);
            }
        }
        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.uss.qlfc.service.IMLcncHstryService#deleteLcncHstry(com.intermorph.uss.qlfc.vo.IMLcncHstryVO)
     */
    public int deleteLcncHstry(IMLcncHstryVO vo) throws Exception {
        int success = 0;

        success = lcncHstryMapper.delete(vo);

        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.uss.qlfc.service.IMLcncHstryService#deletelistLcncHstry(java.util.List)
     */
    public int deletelistLcncHstry(List<IMLcncHstryVO> voList) throws Exception {
        int success = 0;
        if (voList != null) {
            for (IMLcncHstryVO vo : voList) {
                success += lcncHstryMapper.delete(vo);
            }
        }
        return success;
    }


	
	/* (non-Javadoc)
	 * @see com.intermorph.uss.qlfc.service.IMLcncHstryService#selectDetail(com.intermorph.cmmn.base.BaseVO)
	 */
	@Override
	public BaseResultSet selectDetailLcncHstry(IMLcncHstryVO vo) throws Exception {
		return lcncHstryMapper.selectDetail(vo);
	}
	

	
	/* (non-Javadoc)
	 * @see com.intermorph.uss.qlfc.service.IMLcncHstryService#selectListLcncHstry(com.intermorph.cmmn.base.BaseCondition)
	 */
	public BasePage<BaseResultSet> selectListLcncHstry(BaseCondition condition) throws Exception {
		int totalCount = 0;
		BasePage<BaseResultSet> paginateInfo = new BasePage<BaseResultSet>();
		if (condition.checkPage()) {
			totalCount = lcncHstryMapper.selectListCount(condition);
			
			paginateInfo.adjustPage(totalCount, condition);
			paginateInfo.page(lcncHstryMapper.selectList(condition), totalCount, condition);
			
		}else {
			condition.setCurrentPageNo(0);
			List<BaseResultSet> list = lcncHstryMapper.selectList(condition);
			totalCount = list.size();
			paginateInfo.adjustPage(totalCount, condition);
			paginateInfo.page(list, totalCount, condition);

		}
		return paginateInfo;
		
	}
	
   
}