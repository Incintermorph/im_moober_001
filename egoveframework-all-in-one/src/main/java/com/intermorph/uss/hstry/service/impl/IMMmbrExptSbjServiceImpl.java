/*
 *  * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.hstry.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.intermorph.uss.hstry.service.IMMmbrExptSbjMapper;
import com.intermorph.uss.hstry.service.IMMmbrExptSbjService;
import com.intermorph.uss.hstry.service.IMMmbrExptSbjVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.hstry.service.impl
 * @File : MmbrExptSbjServiceImpl.java
 * @Title : 회원면제과목
 * @date : 2022.03.10 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Service ("IMMmbrExptSbjService")
public class IMMmbrExptSbjServiceImpl extends EgovAbstractServiceImpl   implements IMMmbrExptSbjService {

    @Resource (name = "IMMmbrExptSbjMapper")
    private IMMmbrExptSbjMapper mmbrExptSbjMapper;
    
    @Resource(name = "imMmbrExptSbjIdGnrService")
    private EgovIdGnrService idgenService;


    /*
     * (non-Javadoc) 
     * 
     * @see com.intermorph.uss.hstry.service.IMMmbrExptSbjService#insertMmbrExptSbj(com.intermorph.uss.hstry.vo.IMMmbrExptSbjVO)
     */
    public int insertMmbrExptSbj(IMMmbrExptSbjVO vo) throws Exception {
        int success = 0;
        vo.setMmbrExptSbjId(idgenService.getNextStringId());
        success = mmbrExptSbjMapper.insert(vo);

        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.uss.hstry.service.IMMmbrExptSbjService#updateMmbrExptSbj(com.intermorph.uss.hstry.vo.IMMmbrExptSbjVO)
     */
    public int updateMmbrExptSbj(IMMmbrExptSbjVO vo) throws Exception {
        int success = 0;
        success = mmbrExptSbjMapper.update(vo);
        return success;
    }

   
    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.uss.hstry.service.IMMmbrExptSbjService#deleteMmbrExptSbj(com.intermorph.uss.hstry.vo.IMMmbrExptSbjVO)
     */
    public int deleteMmbrExptSbj(IMMmbrExptSbjVO vo) throws Exception {
        int success = 0;

        success = mmbrExptSbjMapper.delete(vo);

        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.uss.hstry.service.IMMmbrExptSbjService#deletelistMmbrExptSbj(java.util.List)
     */
    public int deletelistMmbrExptSbj(List<IMMmbrExptSbjVO> voList) throws Exception {
        int success = 0;
        if (voList != null) {
            for (IMMmbrExptSbjVO vo : voList) {
                success += mmbrExptSbjMapper.delete(vo);
            }
        }
        return success;
    }


	
	/* (non-Javadoc)
	 * @see com.intermorph.uss.hstry.service.IMMmbrExptSbjService#selectListMmbrExptSbj(Long)
	 */
	public List<IMMmbrExptSbjVO> selectListMmbrExptSbj(Long memberSrl) throws Exception {
				return mmbrExptSbjMapper.selectList(memberSrl,"01");
		
	}
	
	
	/* (non-Javadoc)
	 * @see com.intermorph.uss.hstry.service.IMMmbrExptSbjService#selectListMmbrExptSbjOnline(Long)
	 */
	public List<IMMmbrExptSbjVO> selectListMmbrExptSbjOnline(Long memberSrl) throws Exception {
		return mmbrExptSbjMapper.selectList(memberSrl,"02");
		
	}
	
	
	/* (non-Javadoc)
	 * @see com.intermorph.uss.hstry.service.IMMmbrExptSbjService#selectDetail(com.intermorph.cmmn.base.BaseVO)
	 */
	@Override
	public IMMmbrExptSbjVO selectDetailMmbrExptSbj(IMMmbrExptSbjVO vo) throws Exception {
		return mmbrExptSbjMapper.selectDetail(vo);
	}
	

   
}