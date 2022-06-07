/*
 *  * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.objc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import com.intermorph.uss.objc.service.IMObjcAplyMapper;
import com.intermorph.uss.objc.service.IMObjcAplyService;
import com.intermorph.uss.objc.service.IMObjcAplyVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.objc.service.impl
 * @File : ObjcAplyServiceImpl.java
 * @Title : 이의신청
 * @date : 2022.04.06 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Service ("IMObjcAplyService")
public class IMObjcAplyServiceImpl  extends EgovAbstractServiceImpl  implements IMObjcAplyService {

    @Resource (name = "IMObjcAplyMapper")
    private IMObjcAplyMapper objcAplyMapper;
    
    
    @Resource(name = "imObjcAplyIdGnrService")
    private EgovIdGnrService idgenService;


    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.uss.objc.service.IMObjcAplyService#insertObjcAply(com.intermorph.uss.objc.vo.IMObjcAplyVO)
     */
    public int insertObjcAply(IMObjcAplyVO vo) throws Exception {
        int success = 0;
        vo.setObjcAplyId(idgenService.getNextStringId());
        success = objcAplyMapper.insert(vo);

        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.uss.objc.service.IMObjcAplyService#updateObjcAply(com.intermorph.uss.objc.vo.IMObjcAplyVO)
     */
    public int updateObjcAply(IMObjcAplyVO vo) throws Exception {
        int success = 0;
        success = objcAplyMapper.update(vo);
        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.uss.objc.service.IMObjcAplyService#updatelistObjcAply(java.util.List)
     */
    public int updatelistObjcAply(List<IMObjcAplyVO> voList) throws Exception {
        int success = 0;
        if (voList != null) {
            for (IMObjcAplyVO vo : voList) {
                success += objcAplyMapper.update(vo);
            }
        }
        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.uss.objc.service.IMObjcAplyService#deleteObjcAply(com.intermorph.uss.objc.vo.IMObjcAplyVO)
     */
    public int deleteObjcAply(IMObjcAplyVO vo) throws Exception {
        int success = 0;

        success = objcAplyMapper.delete(vo);

        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.uss.objc.service.IMObjcAplyService#deletelistObjcAply(java.util.List)
     */
    public int deletelistObjcAply(List<IMObjcAplyVO> voList) throws Exception {
        int success = 0;
        if (voList != null) {
            for (IMObjcAplyVO vo : voList) {
                success += objcAplyMapper.delete(vo);
            }
        }
        return success;
    }


	
	/* (non-Javadoc)
	 * @see com.intermorph.uss.objc.service.IMObjcAplyService#selectDetail(com.intermorph.cmmn.base.BaseVO)
	 */
	@Override
	public BaseResultSet selectDetailObjcAply(IMObjcAplyVO vo) throws Exception {
		return objcAplyMapper.selectDetail(vo);
	}
	

	
	/* (non-Javadoc)
	 * @see com.intermorph.uss.objc.service.IMObjcAplyService#selectListObjcAply(com.intermorph.cmmn.base.BaseCondition)
	 */
	public BasePage<BaseResultSet> selectListObjcAply(BaseCondition condition) throws Exception {
		int totalCount = 0;
		BasePage<BaseResultSet> paginateInfo = new BasePage<BaseResultSet>();
		if (condition.checkPage()) {
			totalCount = objcAplyMapper.selectListCount(condition);
			paginateInfo.adjustPage(totalCount, condition);
			paginateInfo.page(objcAplyMapper.selectList(condition), totalCount, condition);
		}else {
			condition.setCurrentPageNo(0);
			List<BaseResultSet> list = objcAplyMapper.selectList(condition);
			totalCount = list.size();
			paginateInfo.adjustPage(totalCount, condition);
			paginateInfo.page(list, totalCount, condition);

		}
		return paginateInfo;
		
	}
	
   
}