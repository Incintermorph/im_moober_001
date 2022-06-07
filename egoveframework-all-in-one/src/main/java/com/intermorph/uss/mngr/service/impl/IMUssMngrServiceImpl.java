/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.mngr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;
import com.intermorph.uss.mngr.service.IMUssMngrMapper;
import com.intermorph.uss.mngr.service.IMUssMngrService;
import com.intermorph.uss.mngr.service.IMUssMngrVO;

import egovframework.com.uss.umt.service.impl.UserManageDAO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.mngr.service.impl
 * @File    : IMUssMngrServiceImpl.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 2. 24
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
@Service ("IMUssMngrService")
public class IMUssMngrServiceImpl extends EgovAbstractServiceImpl  implements IMUssMngrService {
	

	@Resource (name = "IMUssMngrMapper")
	protected IMUssMngrMapper ussMngrMapper;
	

	/** userManageDAO */
	@Resource(name="userManageDAO")
	private UserManageDAO userManageDAO;
	

	/* (non-Javadoc)
	 * @see com.intermorph.uss.mngr.service.IMUssMngrService#selectListUssMngr(com.intermorph.cmmn.base.BaseCondition)
	 */
	@Override
	public BasePage<BaseResultSet> selectListUssMngr(BaseCondition condition) throws Exception {
		int totalCount = 0;
		BasePage<BaseResultSet> paginateInfo = new BasePage<BaseResultSet>();
		if (condition.checkPage()) {
			totalCount = ussMngrMapper.selectListCount(condition);
			if (totalCount > 0) {
				paginateInfo.adjustPage(totalCount, condition);
				paginateInfo.page(ussMngrMapper.selectList(condition), totalCount, condition);
			}
		}else {
			condition.setCurrentPageNo(0);
			List<BaseResultSet> list = ussMngrMapper.selectList(condition);
			totalCount = list.size();
			paginateInfo.adjustPage(totalCount, condition);
			paginateInfo.page(list, totalCount, condition);

		}
		return paginateInfo;
	}

	/* (non-Javadoc)
	 * @see com.intermorph.uss.mngr.service.IMUssMngrService#selectDetailUssMngr(com.intermorph.crs.mstr.service.IMCrsMstrVO)
	 */
	@Override
	public BaseResultSet selectDetailUssMngr(IMUssMngrVO vo) throws Exception {
		return ussMngrMapper.selectDetail(vo);
	}

	
	  /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.crs.agncy.service.IMAgncyService#deleteUssMngr(IMUssMngrVO)
     */
    public int deleteUssMngr(IMUssMngrVO vo) throws Exception {
        int success = 0;

        success = ussMngrMapper.delete(vo);
        vo.chkInitData();
        userManageDAO.insertUserHistory(vo);
        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.crs.agncy.service.IMAgncyService#deletelistUssMngr(java.util.List)
     */
    public int deletelistUssMngr(List<IMUssMngrVO> voList) throws Exception {
        int success = 0;
        if (voList != null) {
            for (IMUssMngrVO vo : voList) {
                success += ussMngrMapper.delete(vo);
                vo.chkInitData();
                userManageDAO.insertUserHistory(vo);
            }
        }
        return success;
    }
}
