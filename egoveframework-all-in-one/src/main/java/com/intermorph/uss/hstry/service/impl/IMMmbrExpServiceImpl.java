/*
 *  * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.hstry.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.intermorph.uss.hstry.service.IMMmbrExpMapper;
import com.intermorph.uss.hstry.service.IMMmbrExpService;
import com.intermorph.uss.hstry.service.IMMmbrExpVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.hstry.service.impl
 * @File : MmbrExpServiceImpl.java
 * @Title : 회원경력
 * @date : 2022.03.10 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Service ("IMMmbrExpService")
public class IMMmbrExpServiceImpl extends EgovAbstractServiceImpl   implements IMMmbrExpService {

    @Resource (name = "IMMmbrExpMapper")
    private IMMmbrExpMapper mmbrExpMapper;
    
   @Resource(name = "imMmbrExpIdGnrService")
    private EgovIdGnrService idgenService;


    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.uss.hstry.service.IMMmbrExpService#insertMmbrExp(com.intermorph.uss.hstry.vo.IMMmbrExpVO)
     */
    public int insertMmbrExp(IMMmbrExpVO vo) throws Exception {
        int success = 0;
        vo.setMmbrExpId(idgenService.getNextStringId());
        success = mmbrExpMapper.insert(vo);

        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.uss.hstry.service.IMMmbrExpService#updateMmbrExp(com.intermorph.uss.hstry.vo.IMMmbrExpVO)
     */
    public int updateMmbrExp(IMMmbrExpVO vo) throws Exception {
        int success = 0;
        success = mmbrExpMapper.update(vo);
        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.uss.hstry.service.IMMmbrExpService#deleteMmbrExp(com.intermorph.uss.hstry.vo.IMMmbrExpVO)
     */
    public int deleteMmbrExp(IMMmbrExpVO vo) throws Exception {
        int success = 0;

        success = mmbrExpMapper.delete(vo);

        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.uss.hstry.service.IMMmbrExpService#deletelistMmbrExp(java.util.List)
     */
    public int deletelistMmbrExp(List<IMMmbrExpVO> voList) throws Exception {
        int success = 0;
        if (voList != null) {
            for (IMMmbrExpVO vo : voList) {
                success += mmbrExpMapper.delete(vo);
            }
        }
        return success;
    }


	
	/* (non-Javadoc)
	 * @see com.intermorph.uss.hstry.service.IMMmbrExpService#selectDetail(com.intermorph.cmmn.base.BaseVO)
	 */
	@Override
	public IMMmbrExpVO selectDetailMmbrExp(IMMmbrExpVO vo) throws Exception {
		return mmbrExpMapper.selectDetail(vo);
	}
	

	
	/* (non-Javadoc)
	 * @see com.intermorph.uss.hstry.service.IMMmbrExpService#selectListMmbrExp(Long)
	 */
	public List<IMMmbrExpVO> selectListMmbrExp(Long memberSrl) throws Exception {
		 
		return mmbrExpMapper.selectList(memberSrl);
		
	}
	
   
}