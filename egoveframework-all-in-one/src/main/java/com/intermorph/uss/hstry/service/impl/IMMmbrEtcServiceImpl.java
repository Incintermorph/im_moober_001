/*
 *  * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.hstry.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.intermorph.uss.hstry.service.IMMmbrEtcMapper;
import com.intermorph.uss.hstry.service.IMMmbrEtcService;
import com.intermorph.uss.hstry.service.IMMmbrEtcVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.hstry.service.impl
 * @File : MmbrEtcServiceImpl.java
 * @Title : 회원기타정보
 * @date : 2022.03.10 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Service ("IMMmbrEtcService")
public class IMMmbrEtcServiceImpl  extends EgovAbstractServiceImpl   implements IMMmbrEtcService {

    @Resource (name = "IMMmbrEtcMapper")
    private IMMmbrEtcMapper mmbrEtcMapper;
    
    @Resource(name = "imMmbrEtcIdGnrService")
    private EgovIdGnrService idgenService;


    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.uss.hstry.service.IMMmbrEtcService#insertMmbrEtc(com.intermorph.uss.hstry.vo.IMMmbrEtcVO)
     */
    public int insertMmbrEtc(IMMmbrEtcVO vo) throws Exception {
        int success = 0;
        vo.setMmbrEtcId(idgenService.getNextStringId());
        success = mmbrEtcMapper.insert(vo);

        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.uss.hstry.service.IMMmbrEtcService#updateMmbrEtc(com.intermorph.uss.hstry.vo.IMMmbrEtcVO)
     */
    public int updateMmbrEtc(IMMmbrEtcVO vo) throws Exception {
        int success = 0;
        success = mmbrEtcMapper.update(vo);
        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.uss.hstry.service.IMMmbrEtcService#deleteMmbrEtc(com.intermorph.uss.hstry.vo.IMMmbrEtcVO)
     */
    public int deleteMmbrEtc(IMMmbrEtcVO vo) throws Exception {
        int success = 0;

        success = mmbrEtcMapper.delete(vo);

        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.uss.hstry.service.IMMmbrEtcService#deletelistMmbrEtc(java.util.List)
     */
    public int deletelistMmbrEtc(List<IMMmbrEtcVO> voList) throws Exception {
        int success = 0;
        if (voList != null) {
            for (IMMmbrEtcVO vo : voList) {
                success += mmbrEtcMapper.delete(vo);
            }
        }
        return success;
    }


	
	/* (non-Javadoc)
	 * @see com.intermorph.uss.hstry.service.IMMmbrEtcService#selectDetail(com.intermorph.cmmn.base.BaseVO)
	 */
	@Override
	public IMMmbrEtcVO selectDetailMmbrEtc(IMMmbrEtcVO vo) throws Exception {
		return mmbrEtcMapper.selectDetail(vo);
	}
	

	
	/* (non-Javadoc)
	 * @see com.intermorph.uss.hstry.service.IMMmbrEtcService#selectListMmbrEtc(Long)
	 */
	public List<IMMmbrEtcVO> selectListMmbrEtc(Long memberSrl) throws Exception {
		return mmbrEtcMapper.selectList(memberSrl);
		
	}
	
   
}