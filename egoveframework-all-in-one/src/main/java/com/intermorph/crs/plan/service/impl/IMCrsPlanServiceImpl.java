/*
 *  * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.crs.plan.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import com.intermorph.crs.plan.service.IMCrsPlanMapper;
import com.intermorph.crs.plan.service.IMCrsPlanService;
import com.intermorph.crs.plan.service.IMCrsPlanVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.crs.plan.service.impl
 * @File : CrsPlanServiceImpl.java
 * @Title : 과정계획
 * @date : 2022.03.01 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Service ("IMCrsPlanService")
public class IMCrsPlanServiceImpl extends EgovAbstractServiceImpl   implements IMCrsPlanService {

    @Resource (name = "IMCrsPlanMapper")
    private IMCrsPlanMapper crsPlanMapper;
    
    @Resource(name = "imCrsPlanIdGnrService")
    private EgovIdGnrService idgenService;


    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.crs.plan.service.IMCrsPlanService#insertCrsPlan(com.intermorph.crs.plan.vo.IMCrsPlanVO)
     */
    public int insertCrsPlan(IMCrsPlanVO vo) throws Exception {
        int success = 0;
        vo.setCrsPlanId(idgenService.getNextStringId());
        success = crsPlanMapper.insert(vo);

        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.crs.plan.service.IMCrsPlanService#updateCrsPlan(com.intermorph.crs.plan.vo.IMCrsPlanVO)
     */
    public int updateCrsPlan(IMCrsPlanVO vo) throws Exception {
        int success = 0;
        success = crsPlanMapper.update(vo);
        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.crs.plan.service.IMCrsPlanService#updatelistCrsPlan(java.util.List)
     */
    public int updatelistCrsPlan(List<IMCrsPlanVO> voList) throws Exception {
        int success = 0;
        if (voList != null) {
            for (IMCrsPlanVO vo : voList) {
                success += crsPlanMapper.update(vo);
            }
        }
        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.crs.plan.service.IMCrsPlanService#deleteCrsPlan(com.intermorph.crs.plan.vo.IMCrsPlanVO)
     */
    public int deleteCrsPlan(IMCrsPlanVO vo) throws Exception {
        int success = 0;

        success = crsPlanMapper.delete(vo);

        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.crs.plan.service.IMCrsPlanService#deletelistCrsPlan(java.util.List)
     */
    public int deletelistCrsPlan(List<IMCrsPlanVO> voList) throws Exception {
        int success = 0;
        if (voList != null) {
            for (IMCrsPlanVO vo : voList) {
                success += crsPlanMapper.delete(vo);
            }
        }
        return success;
    }


	
	/* (non-Javadoc)
	 * @see com.intermorph.crs.plan.service.IMCrsPlanService#selectDetail(com.intermorph.cmmn.base.BaseVO)
	 */
	@Override
	public BaseResultSet selectDetailCrsPlan(IMCrsPlanVO vo) throws Exception {
		return crsPlanMapper.selectDetail(vo);
	}
	

	
	/* (non-Javadoc)
	 * @see com.intermorph.crs.plan.service.IMCrsPlanService#selectListCrsPlan(com.intermorph.cmmn.base.BaseCondition)
	 */
	public BasePage<BaseResultSet> selectListCrsPlan(BaseCondition condition) throws Exception {
		int totalCount = 0;
		BasePage<BaseResultSet> paginateInfo = new BasePage<BaseResultSet>();
		if (condition.checkPage()) {
			totalCount = crsPlanMapper.selectListCount(condition);
			if (totalCount > 0) {
				paginateInfo.adjustPage(totalCount, condition);
				paginateInfo.page(crsPlanMapper.selectList(condition), totalCount, condition);
			}
		}else {
			condition.setCurrentPageNo(0);
			List<BaseResultSet> list = crsPlanMapper.selectList(condition);
			totalCount = list.size();
			paginateInfo.adjustPage(totalCount, condition);
			paginateInfo.page(list, totalCount, condition);

		}
		return paginateInfo;
		
	}
	
   
}