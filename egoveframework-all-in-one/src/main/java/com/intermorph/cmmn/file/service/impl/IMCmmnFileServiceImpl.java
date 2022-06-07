/*
 *  * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.file.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;


import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import com.intermorph.cmmn.file.service.IMCmmnFileMapper;
import com.intermorph.cmmn.file.service.IMCmmnFileService;
import com.intermorph.cmmn.file.service.IMCmmnFileVO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.file.service.impl
 * @File : CmmnFileServiceImpl.java
 * @Title : 공통파일관리
 * @date : 2022.05.13 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Service ("IMCmmnFileService")
public class IMCmmnFileServiceImpl extends EgovAbstractServiceImpl  implements IMCmmnFileService {

    @Resource (name = "IMCmmnFileMapper")
    private IMCmmnFileMapper cmmnFileMapper;
    
    @Resource(name = "imCmmnFileIdGnrService")
    private EgovIdGnrService idgenService;


    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.cmmn.file.service.IMCmmnFileService#insertCmmnFile(com.intermorph.cmmn.file.vo.IMCmmnFileVO)
     */
    public int insertCmmnFile(IMCmmnFileVO vo) throws Exception {
        int success = 0;
        vo.setCmmnFileId(idgenService.getNextStringId());
        success = cmmnFileMapper.insert(vo);

        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.cmmn.file.service.IMCmmnFileService#updateCmmnFile(com.intermorph.cmmn.file.vo.IMCmmnFileVO)
     */
    public int updateCmmnFile(IMCmmnFileVO vo) throws Exception {
        int success = 0;
        success = cmmnFileMapper.update(vo);
        return success;
    }
    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.cmmn.file.service.IMCmmnFileService#updateCmmnFileDownCnt(com.intermorph.cmmn.file.vo.IMCmmnFileVO)
     */
    public int updateCmmnFileDownCnt(IMCmmnFileVO vo) throws Exception {
    	int success = 0;
    	success = cmmnFileMapper.updateDownCnt(vo);
    	return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.cmmn.file.service.IMCmmnFileService#updatelistCmmnFile(java.util.List)
     */
    public int updatelistCmmnFile(List<IMCmmnFileVO> voList) throws Exception {
        int success = 0;
        if (voList != null) {
            for (IMCmmnFileVO vo : voList) {
                success += cmmnFileMapper.update(vo);
            }
        }
        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.cmmn.file.service.IMCmmnFileService#deleteCmmnFile(com.intermorph.cmmn.file.vo.IMCmmnFileVO)
     */
    public int deleteCmmnFile(IMCmmnFileVO vo) throws Exception {
        int success = 0;

        success = cmmnFileMapper.delete(vo);

        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.cmmn.file.service.IMCmmnFileService#deletelistCmmnFile(java.util.List)
     */
    public int deletelistCmmnFile(List<IMCmmnFileVO> voList) throws Exception {
        int success = 0;
        if (voList != null) {
            for (IMCmmnFileVO vo : voList) {
                success += cmmnFileMapper.delete(vo);
            }
        }
        return success;
    }


	
	/* (non-Javadoc)
	 * @see com.intermorph.cmmn.file.service.IMCmmnFileService#selectDetail(com.intermorph.cmmn.base.BaseVO)
	 */
	@Override
	public BaseResultSet selectDetailCmmnFile(IMCmmnFileVO vo) throws Exception {
		return cmmnFileMapper.selectDetail(vo);
	}
	

	
	/* (non-Javadoc)
	 * @see com.intermorph.cmmn.file.service.IMCmmnFileService#selectListCmmnFile(com.intermorph.cmmn.base.BaseCondition)
	 */
	public BasePage<BaseResultSet> selectListCmmnFile(BaseCondition condition) throws Exception {
		int totalCount = 0;
		BasePage<BaseResultSet> paginateInfo = new BasePage<BaseResultSet>();
		if (condition.checkPage()) {
			totalCount = cmmnFileMapper.selectListCount(condition);
			
			paginateInfo.adjustPage(totalCount, condition);
			paginateInfo.page(cmmnFileMapper.selectList(condition), totalCount, condition);
			
		}else {
			condition.setCurrentPageNo(0);
			List<BaseResultSet> list = cmmnFileMapper.selectList(condition);
			totalCount = list.size();
			paginateInfo.adjustPage(totalCount, condition);
			paginateInfo.page(list, totalCount, condition);

		}
		return paginateInfo;
		
	}
	
   
}