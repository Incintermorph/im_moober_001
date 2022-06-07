/*
 *  * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.wtst.wtst.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import com.intermorph.wtst.wtst.service.IMWtstMapper;
import com.intermorph.wtst.wtst.service.IMWtstService;
import com.intermorph.wtst.wtst.service.IMWtstVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.wtst.wtst.service.impl
 * @File : WtstServiceImpl.java
 * @Title : 필기시험
 * @date : 2022.03.31 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Service ("IMWtstService")
public class IMWtstServiceImpl extends EgovAbstractServiceImpl   implements IMWtstService {

    @Resource (name = "IMWtstMapper")
    private IMWtstMapper wtstMapper;
    
    @Resource(name = "imWtstIdGnrService")
    private EgovIdGnrService idgenService;


    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.wtst.wtst.service.IMWtstService#insertWtst(com.intermorph.wtst.wtst.vo.IMWtstVO)
     */
    public int insertWtst(IMWtstVO vo) throws Exception {
        int success = 0;
        vo.setWtstId(idgenService.getNextStringId());
        success = wtstMapper.insert(vo);

        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.wtst.wtst.service.IMWtstService#updateWtst(com.intermorph.wtst.wtst.vo.IMWtstVO)
     */
    public int updateWtst(IMWtstVO vo) throws Exception {
        int success = 0;
        success = wtstMapper.update(vo);
        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.wtst.wtst.service.IMWtstService#updatelistWtst(java.util.List)
     */
    public int updatelistWtst(List<IMWtstVO> voList) throws Exception {
        int success = 0;
        if (voList != null) {
            for (IMWtstVO vo : voList) {
                success += wtstMapper.update(vo);
            }
        }
        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.wtst.wtst.service.IMWtstService#deleteWtst(com.intermorph.wtst.wtst.vo.IMWtstVO)
     */
    public int deleteWtst(IMWtstVO vo) throws Exception {
        int success = 0;

        success = wtstMapper.delete(vo);

        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.wtst.wtst.service.IMWtstService#deletelistWtst(java.util.List)
     */
    public int deletelistWtst(List<IMWtstVO> voList) throws Exception {
        int success = 0;
        if (voList != null) {
            for (IMWtstVO vo : voList) {
                success += wtstMapper.delete(vo);
            }
        }
        return success;
    }


	
	/* (non-Javadoc)
	 * @see com.intermorph.wtst.wtst.service.IMWtstService#selectDetail(com.intermorph.cmmn.base.BaseVO)
	 */
	@Override
	public BaseResultSet selectDetailWtst(IMWtstVO vo) throws Exception {
		return wtstMapper.selectDetail(vo);
	}
	

	
	/* (non-Javadoc)
	 * @see com.intermorph.wtst.wtst.service.IMWtstService#selectListWtst(com.intermorph.cmmn.base.BaseCondition)
	 */
	public BasePage<BaseResultSet> selectListWtst(BaseCondition condition) throws Exception {
		int totalCount = 0;
		BasePage<BaseResultSet> paginateInfo = new BasePage<BaseResultSet>();
		if (condition.checkPage()) {
			totalCount = wtstMapper.selectListCount(condition);
			paginateInfo.adjustPage(totalCount, condition);
			paginateInfo.page(wtstMapper.selectList(condition), totalCount, condition);
			
		}else {
			condition.setCurrentPageNo(0);
			List<BaseResultSet> list = wtstMapper.selectList(condition);
			totalCount = list.size();
			paginateInfo.adjustPage(totalCount, condition);
			paginateInfo.page(list, totalCount, condition);

		}
		return paginateInfo;
		
	}
	
   
}