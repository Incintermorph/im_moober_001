/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;
import com.intermorph.cmmn.base.BaseVO;
import com.intermorph.cmmn.service.IMCmmnSttsMapper;
import com.intermorph.cmmn.service.IMCmmnSttsService;
import com.intermorph.cmmn.service.IMCmmnSttsVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.service.impl
 * @File    : IMCmmnSttsServiceImpl.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 2. 28
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
@Service("IMCmmnSttsService")
public class IMCmmnSttsServiceImpl extends EgovAbstractServiceImpl  implements IMCmmnSttsService {
	

	@Resource(name = "IMCmmnSttsMapper")
	protected IMCmmnSttsMapper cmmnSttsMapper;
	
	

	private int savelist(List<IMCmmnSttsVO> list) throws Exception {
		int result = 0;

		for (IMCmmnSttsVO vo : list) {
			if (cmmnSttsMapper.update(vo) == 0) {
				cmmnSttsMapper.insert(vo);
			}
			//이력 등록 
			cmmnSttsMapper.insertHstry(vo);
			
		}
		result++;
		return result;
	}

	

	/* (non-Javadoc)
	 * @see com.intermorph.cmmn.service.IMCmmnSttsService#updateCmmnStts(java.lang.String, java.lang.String, java.lang.String, BaseVO)
	 */
	@Override
	public int updateCmmnStts(String tblId, String tblRefId,String refNm, String sttsCdv,BaseVO auditVO)
			throws Exception {
		int result = 0;

		List<IMCmmnSttsVO> list = new ArrayList<IMCmmnSttsVO>();
		
		IMCmmnSttsVO vo = new IMCmmnSttsVO();
			vo.setTblRefId(tblRefId);
			vo.setTblId(tblId);
			vo.setRefNm(refNm);
			vo.setSttsCdv(sttsCdv);
			vo.copyAudit(auditVO);
			list.add(vo);
			
			result = savelist(list);
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.intermorph.cmmn.service.IMCmmnSttsService#updateCmmnStts(java.lang.String, java.lang.String, java.lang.String,java.lang.String, BaseVO)
	 */
	@Override
	public int updateCmmnStts(String tblId, String tblRefId,String refNm, String sttsCdv,String sttsRmks,BaseVO auditVO)
			throws Exception {
		int result = 0;
		
		List<IMCmmnSttsVO> list = new ArrayList<IMCmmnSttsVO>();
		
		IMCmmnSttsVO vo = new IMCmmnSttsVO();
		vo.setTblRefId(tblRefId);
		vo.setTblId(tblId);
		vo.setRefNm(refNm);
		vo.setSttsCdv(sttsCdv);
		vo.setSttsRmks(sttsRmks);
		vo.copyAudit(auditVO);
		
		list.add(vo);
		
		result = savelist(list);
		
		return result;
	}
	

	/* (non-Javadoc)
	 * @see com.intermorph.cmmn.service.IMCmmnSttsService#insertlistCmmnStts(java.lang.String, java.lang.String, javax.servlet.http.HttpServletRequest, com.intermorph.cmmn.service.IMCmmnSttsVO)
	 */
	@Override
	public int insertlistCmmnStts(String tblId, String tblRefId, HttpServletRequest req, IMCmmnSttsVO vo)
			throws Exception {
		int result = 0;
		List<IMCmmnSttsVO> list = new ArrayList<IMCmmnSttsVO>();
		if (vo.getCmmnCdvRefNms() != null && vo.getCmmnCdvRefNms().length > 0) {
			for (String refNm : vo.getCmmnCdvRefNms()) {
				String refNmCdv = req.getParameter(refNm) == null ? "" : (String) req.getParameter(refNm);
				IMCmmnSttsVO o = new IMCmmnSttsVO();
				o.setTblRefId(tblRefId);
				o.setTblId(tblId);
				o.setRefNm(refNm);
				o.setSttsCdv(refNmCdv);
				if(refNmCdv!=null &&  !"".equals(refNmCdv)) {
					o.copyAudit(vo);
					list.add(o);
				}
			}
		}
		if (list.size() > 0) {
			result = savelist(list);
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see com.intermorph.cmmn.service.IMCmmnSttsService#updatelistCmmnStts(java.lang.String, java.lang.String, javax.servlet.http.HttpServletRequest, com.intermorph.cmmn.service.IMCmmnSttsVO)
	 */
	@Override
	public int updatelistCmmnStts(String tblId, String tblRefId, HttpServletRequest req, IMCmmnSttsVO vo)
			throws Exception {
		return insertlistCmmnStts(tblId, tblRefId, req, vo);
	}

	/* (non-Javadoc)
	 * @see com.intermorph.cmmn.service.IMCmmnSttsService#selectListCmmnStts(com.intermorph.cmmn.service.IMCmmnSttsVO)
	 */
	@Override
	public List<IMCmmnSttsVO> selectListCmmnStts(IMCmmnSttsVO vo) throws Exception {
		
		return cmmnSttsMapper.selectList(vo);
	}
	/* (non-Javadoc)
	 * @see com.intermorph.cmmn.service.IMCmmnSttsService#selectListCmmnStts(java.lang.String, java.lang.String)
	 */
	@Override
	public List<IMCmmnSttsVO> selectListCmmnStts(String tblId, String tblRefId) throws Exception {
		IMCmmnSttsVO vo= new IMCmmnSttsVO();
		vo.setTblId(tblId);
		vo.setTblRefId(tblRefId);
		return cmmnSttsMapper.selectList(vo);
	}

	/* (non-Javadoc)
	 * @see com.intermorph.cmmn.service.IMCmmnSttsService#selectListCmmnSttsResultMap(java.lang.String, java.lang.String)
	 */
	@Override
	public HashMap<String, String> selectListCmmnSttsResultMap(String tblId, String tblRefId) throws Exception {
		HashMap<String, String> resultMap = new HashMap<String, String>();
		IMCmmnSttsVO vo= new IMCmmnSttsVO();
		vo.setTblId(tblId);
		vo.setTblRefId(tblRefId);
		List<IMCmmnSttsVO> list = cmmnSttsMapper.selectList(vo);
		if (list!=null && list.size()>0) {
			for (IMCmmnSttsVO rvo : list) {
				resultMap.put(rvo.getRefNm(), rvo.getSttsCdv());
			}
		}

		return resultMap;
	}
	/* (non-Javadoc)
	 * @see com.intermorph.cmmn.service.IMCmmnSttsService#selectListCmmnSttsResultObjectMap(java.lang.String, IMCmmnSttsVO)
	 */
	@Override
	public HashMap<String, IMCmmnSttsVO> selectListCmmnSttsResultObjectMap(String tblId, String tblRefId) throws Exception {
		HashMap<String, IMCmmnSttsVO> resultMap = new HashMap<String, IMCmmnSttsVO>();
		IMCmmnSttsVO vo= new IMCmmnSttsVO();
		vo.setTblId(tblId);
		vo.setTblRefId(tblRefId);
		List<IMCmmnSttsVO> list = cmmnSttsMapper.selectList(vo);
		if (list!=null && list.size()>0) {
			for (IMCmmnSttsVO rvo : list) {
				resultMap.put(rvo.getRefNm(), rvo);
			}
		}
		return resultMap;
	}

	

	/* (non-Javadoc)
	 * @see com.intermorph.cmmn.service.IMCmmnSttsService#selectListHstryCmmnStts(com.intermorph.cmmn.base.BaseCondition)
	 */
	public BasePage<BaseResultSet> selectListHstryCmmnStts(BaseCondition condition) throws Exception {
		int totalCount = 0;
		BasePage<BaseResultSet> paginateInfo = new BasePage<BaseResultSet>();
		if (condition.checkPage()) {
			totalCount = cmmnSttsMapper.selectListHstryCount(condition);
			
			paginateInfo.adjustPage(totalCount, condition);
			paginateInfo.page(cmmnSttsMapper.selectListHstry(condition), totalCount, condition);
			
		}else {
			condition.setCurrentPageNo(0);
			List<BaseResultSet> list = cmmnSttsMapper.selectListHstry(condition);
			totalCount = list.size();
			paginateInfo.adjustPage(totalCount, condition);
			paginateInfo.page(list, totalCount, condition);

		}
		return paginateInfo;
		
	}
	
}
