/*
 *  * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.potal.uss.institution.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;
import com.intermorph.uss.hstry.service.IMEduAplyHstryVO;
import com.potal.uss.institution.service.IMEpInstitutionMapper;
import com.potal.uss.institution.service.IMEpInstitutionService;
import com.potal.uss.institution.service.IMEpInstitutionVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.potal.uss.institution.service.impl
 * @File : EpInstitutionServiceImpl.java
 * @Title : 기관(포탈)
 * @date : 2022.03.29 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Service ("IMEpInstitutionService")
public class IMEpInstitutionServiceImpl extends EgovAbstractServiceImpl  implements IMEpInstitutionService {

    @Resource (name = "IMEpInstitutionMapper")
    private IMEpInstitutionMapper epInstitutionMapper;
    


    /*
     * (non-Javadoc)
     * 
     * @see com.potal.uss.institution.service.IMEpInstitutionService#insertEpInstitution(com.potal.uss.institution.vo.IMEpInstitutionVO)
     */
    public int insertEpInstitution(IMEpInstitutionVO vo) throws Exception {
        int success = 0;
        success = epInstitutionMapper.insert(vo);

        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.potal.uss.institution.service.IMEpInstitutionService#updateEpInstitution(com.potal.uss.institution.vo.IMEpInstitutionVO)
     */
    public int updateEpInstitution(IMEpInstitutionVO vo) throws Exception {
        int success = 0;
        success = epInstitutionMapper.update(vo);
        return success;
    }

    

   
	
	/* (non-Javadoc)
	 * @see com.potal.uss.institution.service.IMEpInstitutionService#selectDetail(com.intermorph.cmmn.base.BaseVO)
	 */
	@Override
	public BaseResultSet selectDetailEpInstitution(IMEpInstitutionVO vo) throws Exception {
		return epInstitutionMapper.selectDetail(vo);
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see com.potal.uss.institution.service.IMEpInstitutionService#selectListEpInstitution(com.intermorph.cmmn.base.BaseCondition)
	 */
	public BasePage<BaseResultSet> selectListEpInstitution(BaseCondition condition) throws Exception {
		int totalCount = 0;
		BasePage<BaseResultSet> paginateInfo = new BasePage<BaseResultSet>();
		if (condition.checkPage()) {
			totalCount = epInstitutionMapper.selectListCount(condition);
			if (totalCount > 0) {
				paginateInfo.adjustPage(totalCount, condition);
				paginateInfo.page(epInstitutionMapper.selectList(condition), totalCount, condition);
			}
		}else {
			condition.setCurrentPageNo(0);
			List<BaseResultSet> list = epInstitutionMapper.selectList(condition);
			totalCount = list.size();
			paginateInfo.adjustPage(totalCount, condition);
			paginateInfo.page(list, totalCount, condition);

		}
		return paginateInfo;
		
	}

	/* (non-Javadoc)
	 * @see com.potal.uss.institution.service.IMEpInstitutionService#selectMemberUser(String)
	 */
	public IMEpInstitutionVO selectMemberUser(String userId)throws Exception {
		return epInstitutionMapper.selectMemberUser(userId);
	}
	

	/* (non-Javadoc)
	 * @see com.potal.uss.institution.service.IMEpInstitutionService#selectListEduAply(String)
	 */
	public List<IMEduAplyHstryVO> selectListEduAply(String listtime) throws Exception  {
		return epInstitutionMapper.selectListEduAply(listtime);
	}
	

   
}