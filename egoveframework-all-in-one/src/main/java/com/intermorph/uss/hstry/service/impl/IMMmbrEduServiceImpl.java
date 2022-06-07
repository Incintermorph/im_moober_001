/*
 *  * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.hstry.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.intermorph.uss.hstry.service.IMMmbrEduMapper;
import com.intermorph.uss.hstry.service.IMMmbrEduService;
import com.intermorph.uss.hstry.service.IMMmbrEduVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.hstry.service.impl
 * @File : MmbrEduServiceImpl.java
 * @Title : 회원학력
 * @date : 2022.03.10 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Service ("IMMmbrEduService")
public class IMMmbrEduServiceImpl extends EgovAbstractServiceImpl   implements IMMmbrEduService {

    @Resource (name = "IMMmbrEduMapper")
    private IMMmbrEduMapper mmbrEduMapper;
    
    @Resource(name = "imMmbrEduIdGnrService")
    private EgovIdGnrService idgenService;


    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.uss.hstry.service.IMMmbrEduService#insertMmbrEdu(com.intermorph.uss.hstry.vo.IMMmbrEduVO)
     */
    public int insertMmbrEdu(IMMmbrEduVO vo) throws Exception {
        int success = 0;
        vo.setMmbrEduId(idgenService.getNextStringId());
        success = mmbrEduMapper.insert(vo);

        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.uss.hstry.service.IMMmbrEduService#updateMmbrEdu(com.intermorph.uss.hstry.vo.IMMmbrEduVO)
     */
    public int updateMmbrEdu(IMMmbrEduVO vo) throws Exception {
        int success = 0;
        success = mmbrEduMapper.update(vo);
        return success;
    }

    
    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.uss.hstry.service.IMMmbrEduService#deleteMmbrEdu(com.intermorph.uss.hstry.vo.IMMmbrEduVO)
     */
    public int deleteMmbrEdu(IMMmbrEduVO vo) throws Exception {
        int success = 0;

        success = mmbrEduMapper.delete(vo);

        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.uss.hstry.service.IMMmbrEduService#deletelistMmbrEdu(java.util.List)
     */
    public int deletelistMmbrEdu(List<IMMmbrEduVO> voList) throws Exception {
        int success = 0;
        if (voList != null) {
            for (IMMmbrEduVO vo : voList) {
                success += mmbrEduMapper.delete(vo);
            }
        }
        return success;
    }


	
	/* (non-Javadoc)
	 * @see com.intermorph.uss.hstry.service.IMMmbrEduService#selectDetail(com.intermorph.cmmn.base.BaseVO)
	 */
	@Override
	public IMMmbrEduVO selectDetailMmbrEdu(IMMmbrEduVO vo) throws Exception {
		return mmbrEduMapper.selectDetail(vo);
	}
	

	
	/* (non-Javadoc)
	 * @see com.intermorph.uss.hstry.service.IMMmbrEduService#selectListMmbrEdu(com.intermorph.cmmn.base.BaseCondition)
	 */
	public List<IMMmbrEduVO> selectListMmbrEdu(Long memberSrl) throws Exception {
		return mmbrEduMapper.selectList(memberSrl);
		
	}
	
   
}