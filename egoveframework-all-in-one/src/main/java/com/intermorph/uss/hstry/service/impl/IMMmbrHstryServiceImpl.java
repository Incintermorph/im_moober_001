/*
 *  * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.hstry.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;
import com.intermorph.cmmn.util.IMStringUtil;
import com.intermorph.uss.hstry.service.IMEduAplyHstryVO;
import com.intermorph.uss.hstry.service.IMMmbrHstryMapper;
import com.intermorph.uss.hstry.service.IMMmbrHstryResultSet;
import com.intermorph.uss.hstry.service.IMMmbrHstryService;
import com.intermorph.uss.hstry.service.IMMmbrHstryVO;
import com.intermorph.uss.qlfc.service.IMLcncHstryMapper;
import com.intermorph.uss.qlfc.service.IMLcncHstryVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.hstry.service.impl
 * @File : MmbrHstryServiceImpl.java
 * @Title : 회원이력
 * @date : 2022.03.10 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Service ("IMMmbrHstryService")
public class IMMmbrHstryServiceImpl  extends EgovAbstractServiceImpl   implements IMMmbrHstryService {

    @Resource (name = "IMMmbrHstryMapper")
    private IMMmbrHstryMapper mmbrHstryMapper;
    

    
    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.uss.hstry.service.IMMmbrHstryService#insertEduAplyHstry(IMEduAplyHstryVO)
     */
    public int insertEduAplyHstry(IMEduAplyHstryVO vo) throws Exception{
    	int success = 0;
    	success = mmbrHstryMapper.updateEduHis(vo);
    	if(success==0){
    		success = mmbrHstryMapper.insertEduHis(vo);
    	}
    	return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.uss.hstry.service.IMMmbrHstryService#insertMmbrHstry(com.intermorph.uss.hstry.vo.IMMmbrHstryVO)
     */
    public int insertMmbrHstry(IMMmbrHstryVO vo) throws Exception {
        int success = 0;
        success = mmbrHstryMapper.insert(vo);

        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.uss.hstry.service.IMMmbrHstryService#updateMmbrHstry(com.intermorph.uss.hstry.vo.IMMmbrHstryVO)
     */
    public int updateMmbrHstry(IMMmbrHstryVO vo) throws Exception {
        int success = 0;
        success = mmbrHstryMapper.update(vo);
        return success;
    }
    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.uss.hstry.service.IMMmbrHstryService#updateLastMdfcn(com.intermorph.uss.hstry.vo.IMMmbrHstryVO)
     */
    public int updateLastMdfcn(IMMmbrHstryVO vo) throws Exception {
    	int success = 0;
    	success = mmbrHstryMapper.updateLastMdfcn(vo);
    	return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.uss.hstry.service.IMMmbrHstryService#updatelistMmbrHstry(java.util.List)
     */
    public int updatelistMmbrHstry(List<IMMmbrHstryVO> voList) throws Exception {
        int success = 0;
        if (voList != null) {
            for (IMMmbrHstryVO vo : voList) {
                success += mmbrHstryMapper.update(vo);
            }
        }
        return success;
    }
   

	
	/* (non-Javadoc)
	 * @see com.intermorph.uss.hstry.service.IMMmbrHstryService#selectDetail(com.intermorph.cmmn.base.BaseVO)
	 */
	@Override
	public BaseResultSet selectDetailMmbrHstry(IMMmbrHstryVO vo) throws Exception {
		return mmbrHstryMapper.selectDetail(vo);
	}
	

	
	/* (non-Javadoc)
	 * @see com.intermorph.uss.hstry.service.IMMmbrHstryService#selectListMmbrHstry(com.intermorph.cmmn.base.BaseCondition)
	 */
	public BasePage<BaseResultSet> selectListMmbrHstry(BaseCondition condition) throws Exception {
		int totalCount = 0;
		BasePage<BaseResultSet> paginateInfo = new BasePage<BaseResultSet>();
		if (condition.checkPage()) {
			totalCount = mmbrHstryMapper.selectListCount(condition);
			if (totalCount > 0) {
				paginateInfo.adjustPage(totalCount, condition);
				paginateInfo.page(mmbrHstryMapper.selectList(condition), totalCount, condition);
			}
		}else {
			condition.setCurrentPageNo(0);
			List<BaseResultSet> list = mmbrHstryMapper.selectList(condition);
			totalCount = list.size();
			paginateInfo.adjustPage(totalCount, condition);
			paginateInfo.page(list, totalCount, condition);

		}
		return paginateInfo;
		
	}
	/* (non-Javadoc)
	 * @see com.intermorph.uss.hstry.service.IMMmbrHstryService#selectListEduHisHstry(com.intermorph.cmmn.base.BaseCondition)
	 */
	public BasePage<BaseResultSet> selectListEduHisHstry(BaseCondition condition) throws Exception {
		int totalCount = 0;
		BasePage<BaseResultSet> paginateInfo = new BasePage<BaseResultSet>();
		if (condition.checkPage()) {
			totalCount = mmbrHstryMapper.selectEduHisCount(condition);
			if (totalCount > 0) {
				paginateInfo.adjustPage(totalCount, condition);
				paginateInfo.page(mmbrHstryMapper.selectEduHis(condition), totalCount, condition);
			}
		}else {
			condition.setCurrentPageNo(0);
			List<BaseResultSet> list = mmbrHstryMapper.selectEduHis(condition);
			totalCount = list.size();
			paginateInfo.adjustPage(totalCount, condition);
			paginateInfo.page(list, totalCount, condition);
			
		}
		return paginateInfo;
		
	}

	/* (non-Javadoc)
	 * @see com.intermorph.uss.hstry.service.IMMmbrHstryService#selectEduHisList(Long,String)
	 */
	public List<BaseResultSet> selectEduHisList( Long memberSrl,String sttsCode) throws Exception{
		return mmbrHstryMapper.selectEduHisList(memberSrl, sttsCode);
	}
	/* (non-Javadoc)
	 * @see com.intermorph.uss.hstry.service.IMMmbrHstryService#selectEduHisListStat(Long)
	 */
	public List<BaseResultSet> selectEduHisListStat( Long memberSrl) throws Exception{
		return mmbrHstryMapper.selectEduHisListStat(memberSrl);
	}
}