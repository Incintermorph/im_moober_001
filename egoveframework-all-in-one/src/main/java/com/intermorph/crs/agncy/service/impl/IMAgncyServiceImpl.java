/*
 *  * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.crs.agncy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;
import com.intermorph.crs.agncy.service.IMAgncyCondition;
import com.intermorph.crs.agncy.service.IMAgncyMapper;
import com.intermorph.crs.agncy.service.IMAgncyService;
import com.intermorph.crs.agncy.service.IMAgncyVO;

import egovframework.com.sec.gmt.service.GroupManage;
import egovframework.com.sec.gmt.service.impl.GroupManageDAO;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.crs.agncy.service.impl
 * @File : AgncyServiceImpl.java
 * @Title : 양성기관
 * @date : 2022.02.21 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Service ("IMAgncyService")
public class IMAgncyServiceImpl implements IMAgncyService {

    @Resource (name = "IMAgncyMapper")
    private IMAgncyMapper agncyMapper;
    
    @Resource(name = "imAgncyIdGnrService")
    private EgovIdGnrService idgenService;
    

	@Resource(name="groupManageDAO")
    private GroupManageDAO groupManageDAO;


    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.crs.agncy.service.IMAgncyService#insertAgncy(com.intermorph.crs.agncy.vo.IMAgncyVO)
     */
    public int insertAgncy(IMAgncyVO vo) throws Exception {
        int success = 0;
        vo.setAgncyId(idgenService.getNextStringId());
        success = agncyMapper.insert(vo);
        if(success>0) {
        	GroupManage groupManage = new GroupManage ();
        	vo.copyGroupManage(groupManage);
        	groupManageDAO.insertGroup(groupManage);
        }
        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.crs.agncy.service.IMAgncyService#updateAgncy(com.intermorph.crs.agncy.vo.IMAgncyVO)
     */
    public int updateAgncy(IMAgncyVO vo) throws Exception {
        int success = 0;
        success = agncyMapper.update(vo);
        if(success>0) {
        	GroupManage groupManage = new GroupManage ();
        	vo.copyGroupManage(groupManage);
        	groupManageDAO.updateGroup(groupManage);
        }
        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.crs.agncy.service.IMAgncyService#updatelistAgncy(java.util.List)
     */
    public int updatelistAgncy(List<IMAgncyVO> voList) throws Exception {
        int success = 0;
        if (voList != null) {
            for (IMAgncyVO vo : voList) {
                success += agncyMapper.update(vo);
            }
        }
        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.crs.agncy.service.IMAgncyService#deleteAgncy(com.intermorph.crs.agncy.vo.IMAgncyVO)
     */
    public int deleteAgncy(IMAgncyVO vo) throws Exception {
        int success = 0;

        success = agncyMapper.delete(vo);

        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.crs.agncy.service.IMAgncyService#deletelistAgncy(java.util.List)
     */
    public int deletelistAgncy(List<IMAgncyVO> voList) throws Exception {
        int success = 0;
        if (voList != null) {
            for (IMAgncyVO vo : voList) {
                success += agncyMapper.delete(vo);
            }
        }
        return success;
    }


	
	/* (non-Javadoc)
	 * @see com.intermorph.crs.agncy.service.IMAgncyService#selectDetail(com.intermorph.cmmn.base.BaseVO)
	 */
	@Override
	public BaseResultSet selectDetailAgncy(IMAgncyVO vo) throws Exception {
		return agncyMapper.selectDetail(vo);
	}
	

	
	/* (non-Javadoc)
	 * @see com.intermorph.crs.agncy.service.IMAgncyService#selectListAgncy(com.intermorph.cmmn.base.BaseCondition)
	 */
	public BasePage<BaseResultSet> selectListAgncy(BaseCondition condition) throws Exception {
		int totalCount = 0;
		BasePage<BaseResultSet> paginateInfo = new BasePage<BaseResultSet>();
		if (condition.checkPage()) {
			totalCount = agncyMapper.selectListCount(condition);
			if (totalCount > 0) {
				paginateInfo.adjustPage(totalCount, condition);
				paginateInfo.page(agncyMapper.selectList(condition), totalCount, condition);
			}
		}else {
			condition.setCurrentPageNo(0);
			List<BaseResultSet> list = agncyMapper.selectList(condition);
			totalCount = list.size();
			paginateInfo.adjustPage(totalCount, condition);
			paginateInfo.page(list, totalCount, condition);

		}
		return paginateInfo;
		
	}
	
	/* (non-Javadoc)
	 * @see com.intermorph.crs.agncy.service.IMAgncyService#selectListAgncy()
	 */
	public List<BaseResultSet> selectListAgncy() throws Exception {
			IMAgncyCondition condition = new IMAgncyCondition();
			condition.setScNotAgncyDvsnCdv("AGNCYDVSN_03");
			//양성기관 목록 
			List<BaseResultSet> list = agncyMapper.selectList(condition);
			return list;
	}
	
	/* (non-Javadoc)
	 * @see com.intermorph.crs.agncy.service.IMAgncyService#selectListAgncyDsgn()
	 */
	public List<BaseResultSet> selectListAgncyDsgn() throws Exception {
		IMAgncyCondition condition = new IMAgncyCondition();
		condition.setScNotAgncyDvsnCdv("AGNCYDVSN_03");
		condition.setScDsgnYn("Y");
		//양성기관 목록 
		List<BaseResultSet> list = agncyMapper.selectList(condition);
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.intermorph.crs.agncy.service.IMAgncyService#selectListAgncyPlace()
	 */
	public List<BaseResultSet> selectListAgncyPlace() throws Exception {
		IMAgncyCondition condition = new IMAgncyCondition();
		condition.setScAgncyDvsnCdv("AGNCYDVSN_03");
		//양성기관 목록 
		List<BaseResultSet> list = agncyMapper.selectList(condition);
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.intermorph.crs.agncy.service.IMAgncyService#selectOverCount(com.intermorph.cmmn.base.BaseVO)
	 */
	public int  selectOverCount(IMAgncyVO vo) throws Exception {
		return agncyMapper.selectOverCount(vo);
	}
   
}