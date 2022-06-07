/*
 *  * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.crs.aplcnt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;


import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import com.intermorph.crs.aplcnt.service.IMCrsAplyTrnsfMapper;
import com.intermorph.crs.aplcnt.service.IMCrsAplyTrnsfService;
import com.intermorph.crs.aplcnt.service.IMCrsAplyTrnsfVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.crs.aplcnt.service.impl
 * @File : CrsAplyTrnsfServiceImpl.java
 * @Title : 과정신청이관
 * @date : 2022.05.12 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Service ("IMCrsAplyTrnsfService")
public class IMCrsAplyTrnsfServiceImpl extends EgovAbstractServiceImpl  implements IMCrsAplyTrnsfService {

    @Resource (name = "IMCrsAplyTrnsfMapper")
    private IMCrsAplyTrnsfMapper crsAplyTrnsfMapper;
    
    @Resource(name = "imCrsAplyTrnsfIdGnrService")
    private EgovIdGnrService idgenService;


    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.crs.aplcnt.service.IMCrsAplyTrnsfService#insertCrsAplyTrnsf(com.intermorph.crs.aplcnt.vo.IMCrsAplyTrnsfVO)
     */
    public int insertCrsAplyTrnsf(IMCrsAplyTrnsfVO vo) throws Exception {
        int success = 0;
        success = crsAplyTrnsfMapper.update(vo);
        if(success<1) {
	        vo.setCrsAplyTrnsfId(idgenService.getNextStringId());
	        success = crsAplyTrnsfMapper.insert(vo);
        }
        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.crs.aplcnt.service.IMCrsAplyTrnsfService#updateCrsAplyTrnsf(com.intermorph.crs.aplcnt.vo.IMCrsAplyTrnsfVO)
     */
    public int updateCrsAplyTrnsf(IMCrsAplyTrnsfVO vo) throws Exception {
        int success = 0;
        success = crsAplyTrnsfMapper.update(vo);
        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.crs.aplcnt.service.IMCrsAplyTrnsfService#updatelistCrsAplyTrnsf(java.util.List)
     */
    public int updatelistCrsAplyTrnsf(List<IMCrsAplyTrnsfVO> voList) throws Exception {
        int success = 0;
        if (voList != null) {
            for (IMCrsAplyTrnsfVO vo : voList) {
                success += crsAplyTrnsfMapper.update(vo);
            }
        }
        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.crs.aplcnt.service.IMCrsAplyTrnsfService#deleteCrsAplyTrnsf(com.intermorph.crs.aplcnt.vo.IMCrsAplyTrnsfVO)
     */
    public int deleteCrsAplyTrnsf(IMCrsAplyTrnsfVO vo) throws Exception {
        int success = 0;

        success = crsAplyTrnsfMapper.delete(vo);

        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.crs.aplcnt.service.IMCrsAplyTrnsfService#deletelistCrsAplyTrnsf(java.util.List)
     */
    public int deletelistCrsAplyTrnsf(List<IMCrsAplyTrnsfVO> voList) throws Exception {
        int success = 0;
        if (voList != null) {
            for (IMCrsAplyTrnsfVO vo : voList) {
                success += crsAplyTrnsfMapper.delete(vo);
            }
        }
        return success;
    }


	
	/* (non-Javadoc)
	 * @see com.intermorph.crs.aplcnt.service.IMCrsAplyTrnsfService#selectDetail(com.intermorph.cmmn.base.BaseVO)
	 */
	@Override
	public BaseResultSet selectDetailCrsAplyTrnsf(IMCrsAplyTrnsfVO vo) throws Exception {
		return crsAplyTrnsfMapper.selectDetail(vo);
	}
	

	
	/* (non-Javadoc)
	 * @see com.intermorph.crs.aplcnt.service.IMCrsAplyTrnsfService#selectListCrsAplyTrnsf(com.intermorph.cmmn.base.BaseCondition)
	 */
	public BasePage<BaseResultSet> selectListCrsAplyTrnsf(BaseCondition condition) throws Exception {
		int totalCount = 0;
		BasePage<BaseResultSet> paginateInfo = new BasePage<BaseResultSet>();
		if (condition.checkPage()) {
			totalCount = crsAplyTrnsfMapper.selectListCount(condition);
			
			paginateInfo.adjustPage(totalCount, condition);
			paginateInfo.page(crsAplyTrnsfMapper.selectList(condition), totalCount, condition);
			
		}else {
			condition.setCurrentPageNo(0);
			List<BaseResultSet> list = crsAplyTrnsfMapper.selectList(condition);
			totalCount = list.size();
			paginateInfo.adjustPage(totalCount, condition);
			paginateInfo.page(list, totalCount, condition);

		}
		return paginateInfo;
		
	}
	
   
}