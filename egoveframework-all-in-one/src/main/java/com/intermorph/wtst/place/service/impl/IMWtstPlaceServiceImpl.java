/*
 *  * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.wtst.place.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import com.intermorph.wtst.aplcnt.service.IMWtstAplcntVO;
import com.intermorph.wtst.place.service.IMWtstPlaceMapper;
import com.intermorph.wtst.place.service.IMWtstPlaceService;
import com.intermorph.wtst.place.service.IMWtstPlaceVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.wtst.place.service.impl
 * @File : WtstPlaceServiceImpl.java
 * @Title : 필기시험장소
 * @date : 2022.03.31 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Service ("IMWtstPlaceService")
public class IMWtstPlaceServiceImpl extends EgovAbstractServiceImpl   implements IMWtstPlaceService {

    @Resource (name = "IMWtstPlaceMapper")
    private IMWtstPlaceMapper wtstPlaceMapper;
    
    @Resource(name = "imWtstPlaceIdGnrService")
    private EgovIdGnrService idgenService;


    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.wtst.place.service.IMWtstPlaceService#insertWtstPlace(com.intermorph.wtst.place.vo.IMWtstPlaceVO)
     */
    public int insertWtstPlace(IMWtstPlaceVO vo) throws Exception {
        int success = 0;
        vo.setWtstPlaceId(idgenService.getNextStringId());
        success = wtstPlaceMapper.insert(vo);

        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.wtst.place.service.IMWtstPlaceService#updateWtstPlace(com.intermorph.wtst.place.vo.IMWtstPlaceVO)
     */
    public int updateWtstPlace(IMWtstPlaceVO vo) throws Exception {
        int success = 0;
        success = wtstPlaceMapper.update(vo);
        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.wtst.place.service.IMWtstPlaceService#updatelistWtstPlace(java.util.List)
     */
    public int updatelistWtstPlace(List<IMWtstPlaceVO> voList) throws Exception {
        int success = 0;
        if (voList != null) {
            for (IMWtstPlaceVO vo : voList) {
                success += wtstPlaceMapper.update(vo);
            }
        }
        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.wtst.place.service.IMWtstPlaceService#deleteWtstPlace(com.intermorph.wtst.place.vo.IMWtstPlaceVO)
     */
    public int deleteWtstPlace(IMWtstPlaceVO vo) throws Exception {
        int success = 0;

        success = wtstPlaceMapper.delete(vo);

        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.wtst.place.service.IMWtstPlaceService#deletelistWtstPlace(java.util.List)
     */
    public int deletelistWtstPlace(List<IMWtstPlaceVO> voList) throws Exception {
        int success = 0;
        if (voList != null) {
            for (IMWtstPlaceVO vo : voList) {
                success += wtstPlaceMapper.delete(vo);
            }
        }
        return success;
    }


	
	/* (non-Javadoc)
	 * @see com.intermorph.wtst.place.service.IMWtstPlaceService#selectDetail(com.intermorph.cmmn.base.BaseVO)
	 */
	@Override
	public BaseResultSet selectDetailWtstPlace(IMWtstPlaceVO vo) throws Exception {
		return wtstPlaceMapper.selectDetail(vo);
	}
	

	
	/* (non-Javadoc)
	 * @see com.intermorph.wtst.place.service.IMWtstPlaceService#selectListWtstPlace(com.intermorph.cmmn.base.BaseCondition)
	 */
	public BasePage<BaseResultSet> selectListWtstPlace(BaseCondition condition) throws Exception {
		int totalCount = 0;
		BasePage<BaseResultSet> paginateInfo = new BasePage<BaseResultSet>();
		if (condition.checkPage()) {
			totalCount = wtstPlaceMapper.selectListCount(condition);
			paginateInfo.adjustPage(totalCount, condition);
			paginateInfo.page(wtstPlaceMapper.selectList(condition), totalCount, condition);
		}else {
			condition.setCurrentPageNo(0);
			List<BaseResultSet> list = wtstPlaceMapper.selectList(condition);
			totalCount = list.size();
			paginateInfo.adjustPage(totalCount, condition);
			paginateInfo.page(list, totalCount, condition);

		}
		return paginateInfo;
		
	}
	
	 /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.wtst.place.service.IMWtstPlaceService#updateWtstAplcntPrgrsSttsCdv(com.intermorph.wtst.aplcnt.vo.IMWtstAplcntVO)
     */
    public int updateWtstAplcntPrgrsSttsCdv(IMWtstPlaceVO vo) throws Exception {
    	int success = 0;
    	success = wtstPlaceMapper.updatePrgrsSttsCdv(vo);
    	return success;
    }
}