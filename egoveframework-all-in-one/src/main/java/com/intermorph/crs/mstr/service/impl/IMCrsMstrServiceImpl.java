/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.crs.mstr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;
import com.intermorph.crs.mstr.service.IMCrsMstrCondition;
import com.intermorph.crs.mstr.service.IMCrsMstrMapper;
import com.intermorph.crs.mstr.service.IMCrsMstrService;
import com.intermorph.crs.mstr.service.IMCrsMstrVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.crs.mstr.service.impl
 * @File    : IMCrsMstrServiceImpl.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 2. 8
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
@Service ("IMCrsMstrService")
public class IMCrsMstrServiceImpl extends EgovAbstractServiceImpl  implements IMCrsMstrService {
	
	@Resource (name = "IMCrsMstrMapper")
	protected IMCrsMstrMapper iMCrsMstrMapper;
	

    @Resource(name = "imCrsMstrIdGnrService")
    private EgovIdGnrService idgenService;
	
	
	/* (non-Javadoc)
	 * @see com.intermorph.crs.mstr.service.IMCrsMstrService#selectList(com.intermorph.cmmn.base.BaseCondition)
	 */
	@Override
	public BasePage<BaseResultSet> selectListCrsMstr(BaseCondition condition) throws Exception {
		int totalCount = 0;
		BasePage<BaseResultSet> paginateInfo = new BasePage<BaseResultSet>();
		if (condition.checkPage()) {
			totalCount = iMCrsMstrMapper.selectListCount(condition);
			if (totalCount > 0) {
				paginateInfo.adjustPage(totalCount, condition);
				paginateInfo.page(iMCrsMstrMapper.selectList(condition), totalCount, condition);
			}
		}else {
			condition.setCurrentPageNo(0);
			List<BaseResultSet> list = iMCrsMstrMapper.selectList(condition);
			totalCount = list.size();
			paginateInfo.adjustPage(totalCount, condition);
			paginateInfo.page(list, totalCount, condition);

		}
		return paginateInfo;
		
	}
	

	/* (non-Javadoc)
	 * @see com.intermorph.crs.mstr.service.IMCrsMstrService#selectList()
	 */
	@Override
	public List<BaseResultSet>  selectListCrsMstr() throws Exception {
		IMCrsMstrCondition condition = new IMCrsMstrCondition ();
		
		condition.setScUseSttsYn("Y");
		
		return iMCrsMstrMapper.selectList(condition);
		
	}

	/* (non-Javadoc)
	 * @see com.intermorph.crs.mstr.service.IMCrsMstrService#selectDetail(com.intermorph.cmmn.base.BaseVO)
	 */
	@Override
	public BaseResultSet selectDetailCrsMstr(IMCrsMstrVO vo) throws Exception {
		return iMCrsMstrMapper.selectDetail(vo);
	}

	/* (non-Javadoc)
	 * @see com.intermorph.crs.mstr.service.IMCrsMstrService#insert(com.intermorph.cmmn.base.BaseVO)
	 */
	@Override
	public int insertCrsMstr(IMCrsMstrVO vo) throws Exception {
		vo.setCrsMstrId(idgenService.getNextStringId());
		return iMCrsMstrMapper.insert(vo);
	}
	/* (non-Javadoc)
	 * @see com.intermorph.crs.mstr.service.IMCrsMstrService#update(com.intermorph.cmmn.base.BaseVO)
	 */
	@Override
	public int updateCrsMstr(IMCrsMstrVO vo) throws Exception {
		return iMCrsMstrMapper.update(vo);
	}

	/* (non-Javadoc)
	 * @see com.intermorph.crs.mstr.service.IMCrsMstrService#delete(com.intermorph.cmmn.base.BaseVO)
	 */
	@Override
	public int deleteCrsMstr(IMCrsMstrVO vo) throws Exception {
		
		return iMCrsMstrMapper.delete(vo);
	}
	
	/* (non-Javadoc)
	 * @see com.intermorph.crs.mstr.service.IMCrsMstrService#deletelist(list)
	 */
	@Override
	public int deleteCrsMstrlist(List<IMCrsMstrVO> list) throws Exception {
		int result=0;
		for(IMCrsMstrVO vo : list) {
			result += iMCrsMstrMapper.delete(vo);
		}
		return result; 
	}
	
	

}
