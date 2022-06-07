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

import com.intermorph.cmmn.IMGlobals;
import com.intermorph.cmmn.service.IMCmmnDescVO;
import com.intermorph.cmmn.service.IMCmmnDtMapper;
import com.intermorph.cmmn.service.IMCmmnDtService;
import com.intermorph.cmmn.service.IMCmmnDtVO;
import com.intermorph.cmmn.util.IMDateUtil;
import com.intermorph.cmmn.util.IMStringUtil;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.service.impl
 * @File    : IMCmmnDtServiceImpl.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 2. 28
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
@Service("IMCmmnDtService")
public class IMCmmnDtServiceImpl extends EgovAbstractServiceImpl  implements IMCmmnDtService {


	@Resource(name = "IMCmmnDtMapper")
	protected IMCmmnDtMapper cmmnDtMapper;
	

	private int savelist(List<IMCmmnDtVO> list) throws Exception {
		int result = 0;

		for (IMCmmnDtVO vo : list) {
			if (cmmnDtMapper.update(vo) == 0) {
				cmmnDtMapper.insert(vo);
			}
		}
		result++;
		return result;
	}

	
	/* (non-Javadoc)
	 * @see com.intermorph.cmmn.service.IMCmmnDtService#insertlistCmmnDt(String , String , HttpServletRequest , IMCmmnDtVO )
	 */
	@Override
	public int insertlistCmmnDt(String tblId, String tblRefId, HttpServletRequest req, IMCmmnDtVO vo) throws Exception {
		int result = 0;
		List<IMCmmnDtVO> list = new ArrayList<IMCmmnDtVO>();
		String begParam=null;
		String endParam=null;
		String begDt=null;
		String endDt=null;
		if (vo.getCmmnDtRefNms() != null && vo.getCmmnDtRefNms().length > 0) {
			for (String refNm : vo.getCmmnDtRefNms()) {
				begParam = refNm +"_bgnDt";
				endParam = refNm +"_endDt";
				begDt = req.getParameter(begParam) == null ? "" : (String) req.getParameter(begParam);
				endDt = req.getParameter(endParam) == null ? "" : (String) req.getParameter(endParam);
				IMCmmnDtVO o = new IMCmmnDtVO();
				o.setTblRefId(tblRefId);
				o.setTblId(tblId);
				o.setRefNm(refNm);
				if(!"".equals(begDt)) {
					begDt= IMDateUtil.convertStartDate(begDt, IMGlobals.IM_FORMAT_DATE, IMGlobals.IM_FORMAT_DBDATETIME,IMGlobals.IM_FORMAT_TIMEZONE);
				}
				if(!"".equals(endDt)) {
					endDt =IMDateUtil.convertEndDate(endDt, IMGlobals.IM_FORMAT_DATE, IMGlobals.IM_FORMAT_DBDATETIME,IMGlobals.IM_FORMAT_TIMEZONE);
				}
				o.setBgnDt(begDt);
				o.setEndDt(endDt);
				o.copyAudit(vo);
				list.add(o);
			}
		}
		if (list.size() > 0) {
			result = savelist(list);
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see com.intermorph.cmmn.service.IMCmmnDtService#updatelistCmmnDt(String , String , HttpServletRequest , IMCmmnDtVO )
	 */
	@Override
	public int updatelistCmmnDt(String tblId, String tblRefId, HttpServletRequest req, IMCmmnDtVO vo) throws Exception {
		return insertlistCmmnDt(tblId, tblRefId, req, vo);
	}

	/* (non-Javadoc)
	 * @see com.intermorph.cmmn.service.IMCmmnDtService#selectListCmmnDt(com.intermorph.cmmn.service.IMCmmnDtVO)
	 */
	@Override
	public List<IMCmmnDtVO> selectListCmmnDt(IMCmmnDtVO vo) throws Exception {
		
		return cmmnDtMapper.selectList(vo);
		
	}

	/* (non-Javadoc)
	 * @see com.intermorph.cmmn.service.IMCmmnDtService#selectListCmmnDtResultMap(java.lang.String, java.lang.String)
	 */
	@Override
	public HashMap<String, IMCmmnDtVO> selectListCmmnDtResultMap(String tblId, String tblRefId) throws Exception {
		HashMap<String, IMCmmnDtVO> resultMap = new HashMap<String, IMCmmnDtVO>();
		IMCmmnDtVO vo= new IMCmmnDtVO();
		vo.setTblId(tblId);
		vo.setTblRefId(tblRefId);
		List<IMCmmnDtVO> list = cmmnDtMapper.selectList(vo);
		if (list!=null && list.size()>0) {
			for (IMCmmnDtVO rvo : list) {
				resultMap.put(rvo.getRefNm(), rvo);
			}
		}
		return resultMap;
	}

}
