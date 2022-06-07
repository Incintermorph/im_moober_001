/*
 *  * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.hstry.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.intermorph.uss.hstry.service.IMMmbrWorkHstryMapper;
import com.intermorph.uss.hstry.service.IMMmbrWorkHstryService;
import com.intermorph.uss.hstry.service.IMMmbrWorkHstryVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.hstry.service.impl
 * @File : MmbrWorkHstryServiceImpl.java
 * @Title : 회원근무이력
 * @date : 2022.03.11 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Service ("IMMmbrWorkHstryService")
public class IMMmbrWorkHstryServiceImpl extends EgovAbstractServiceImpl   implements IMMmbrWorkHstryService {

    @Resource (name = "IMMmbrWorkHstryMapper")
    private IMMmbrWorkHstryMapper mmbrWorkHstryMapper;
    
    @Resource(name = "imMmbrWorkHstryIdGnrService")
    private EgovIdGnrService idgenService;


    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.uss.hstry.service.IMMmbrWorkHstryService#insertMmbrWorkHstry(com.intermorph.uss.hstry.vo.IMMmbrWorkHstryVO)
     */
    public int insertMmbrWorkHstry(IMMmbrWorkHstryVO vo) throws Exception {
        int success = 0;
        vo.setMmbrWorkHstryId(idgenService.getNextStringId());
        success = mmbrWorkHstryMapper.insert(vo);

        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.uss.hstry.service.IMMmbrWorkHstryService#updateMmbrWorkHstry(com.intermorph.uss.hstry.vo.IMMmbrWorkHstryVO)
     */
    public int updateMmbrWorkHstry(IMMmbrWorkHstryVO vo) throws Exception {
        int success = 0;
        success = mmbrWorkHstryMapper.update(vo);
        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.uss.hstry.service.IMMmbrWorkHstryService#updatelistMmbrWorkHstry(java.util.List)
     */
    public int updatelistMmbrWorkHstry(List<IMMmbrWorkHstryVO> voList) throws Exception {
        int success = 0;
        if (voList != null) {
            for (IMMmbrWorkHstryVO vo : voList) {
                success += mmbrWorkHstryMapper.update(vo);
            }
        }
        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.uss.hstry.service.IMMmbrWorkHstryService#deleteMmbrWorkHstry(com.intermorph.uss.hstry.vo.IMMmbrWorkHstryVO)
     */
    public int deleteMmbrWorkHstry(IMMmbrWorkHstryVO vo) throws Exception {
        int success = 0;

        success = mmbrWorkHstryMapper.delete(vo);

        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.uss.hstry.service.IMMmbrWorkHstryService#deletelistMmbrWorkHstry(java.util.List)
     */
    public int deletelistMmbrWorkHstry(List<IMMmbrWorkHstryVO> voList) throws Exception {
        int success = 0;
        if (voList != null) {
            for (IMMmbrWorkHstryVO vo : voList) {
                success += mmbrWorkHstryMapper.delete(vo);
            }
        }
        return success;
    }


	
	/* (non-Javadoc)
	 * @see com.intermorph.uss.hstry.service.IMMmbrWorkHstryService#selectDetail(com.intermorph.cmmn.base.BaseVO)
	 */
	@Override
	public IMMmbrWorkHstryVO selectDetailMmbrWorkHstry(IMMmbrWorkHstryVO vo) throws Exception {
		return mmbrWorkHstryMapper.selectDetail(vo);
	}
	
	
	/* (non-Javadoc)
	 * @see com.intermorph.uss.hstry.service.IMMmbrWorkHstryService#selectListDiffSum(Long)
	 */
	@Override
	public IMMmbrWorkHstryVO selectListDiffSum(Long memberSrl) throws Exception {
		return mmbrWorkHstryMapper.selectListDiffSum(memberSrl);
	}
	

	
	/* (non-Javadoc)
	 * @see com.intermorph.uss.hstry.service.IMMmbrWorkHstryService#selectListMmbrWorkHstry(Long)
	 */
	public List<IMMmbrWorkHstryVO> selectListMmbrWorkHstry(Long memberSrl) throws Exception {
		return mmbrWorkHstryMapper.selectList(memberSrl);
		
	}
	
   
}