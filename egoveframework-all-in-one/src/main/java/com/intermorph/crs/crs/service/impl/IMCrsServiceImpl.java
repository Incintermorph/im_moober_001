/*
 *  * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.crs.crs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import com.intermorph.crs.crs.service.IMCrsMapper;
import com.intermorph.crs.crs.service.IMCrsService;
import com.intermorph.crs.crs.service.IMCrsVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.crs.crs.service.impl
 * @File : CrsServiceImpl.java
 * @Title : 과정(운영과정)
 * @date : 2022.02.28 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Service ("IMCrsService")
public class IMCrsServiceImpl extends EgovAbstractServiceImpl  implements IMCrsService {

    @Resource (name = "IMCrsMapper")
    private IMCrsMapper crsMapper;
    
    @Resource(name = "imCrsIdGnrService")
    private EgovIdGnrService idgenService;


    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.crs.crs.service.IMCrsService#insertCrs(com.intermorph.crs.crs.vo.IMCrsVO)
     */
    public int insertCrs(IMCrsVO vo) throws Exception {
        int success = 0;
        vo.setCrsId(idgenService.getNextStringId());
        success = crsMapper.insert(vo);

        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.crs.crs.service.IMCrsService#updateCrs(com.intermorph.crs.crs.vo.IMCrsVO)
     */
    public int updateCrs(IMCrsVO vo) throws Exception {
        int success = 0;
        success = crsMapper.update(vo);
        return success;
    }
    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.crs.crs.service.IMCrsService#updateCrsPrgrsSttsCdv(com.intermorph.crs.crs.vo.IMCrsVO)
     */
    public int updateCrsPrgrsSttsCdv(IMCrsVO vo) throws Exception {
    	int success = 0;
    	success = crsMapper.updatePrgrsSttsCdv(vo);
    	return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.crs.crs.service.IMCrsService#updatelistCrs(java.util.List)
     */
    public int updatelistCrs(List<IMCrsVO> voList) throws Exception {
        int success = 0;
        if (voList != null) {
            for (IMCrsVO vo : voList) {
                success += crsMapper.update(vo);
            }
        }
        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.crs.crs.service.IMCrsService#deleteCrs(com.intermorph.crs.crs.vo.IMCrsVO)
     */
    public int deleteCrs(IMCrsVO vo) throws Exception {
        int success = 0;

        success = crsMapper.delete(vo);

        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.crs.crs.service.IMCrsService#deletelistCrs(java.util.List)
     */
    public int deletelistCrs(List<IMCrsVO> voList) throws Exception {
        int success = 0;
        if (voList != null) {
            for (IMCrsVO vo : voList) {
                success += crsMapper.delete(vo);
            }
        }
        return success;
    }


	
	/* (non-Javadoc)
	 * @see com.intermorph.crs.crs.service.IMCrsService#selectDetail(com.intermorph.cmmn.base.BaseVO)
	 */
	@Override
	public BaseResultSet selectDetailCrs(IMCrsVO vo) throws Exception {
		return crsMapper.selectDetail(vo);
	}
	

	
	/* (non-Javadoc)
	 * @see com.intermorph.crs.crs.service.IMCrsService#selectListCrs(com.intermorph.cmmn.base.BaseCondition)
	 */
	public BasePage<BaseResultSet> selectListCrs(BaseCondition condition) throws Exception {
		int totalCount = 0;
		BasePage<BaseResultSet> paginateInfo = new BasePage<BaseResultSet>();
		if (condition.checkPage()) {
			totalCount = crsMapper.selectListCount(condition);
			paginateInfo.adjustPage(totalCount, condition);
			paginateInfo.page(crsMapper.selectList(condition), totalCount, condition);
			
		}else {
			condition.setCurrentPageNo(0);
			List<BaseResultSet> list = crsMapper.selectList(condition);
			totalCount = list.size();
			paginateInfo.adjustPage(totalCount, condition);
			paginateInfo.page(list, totalCount, condition);

		}
		return paginateInfo;
		
	}
	
	/* (non-Javadoc)
	 * @see com.intermorph.crs.crs.service.IMCrsService#selectListCount(com.intermorph.cmmn.base.BaseCondition)
	 */
	public int selectListCount(BaseCondition condition) throws Exception {
		
		return crsMapper.selectListCount(condition);
	}
}