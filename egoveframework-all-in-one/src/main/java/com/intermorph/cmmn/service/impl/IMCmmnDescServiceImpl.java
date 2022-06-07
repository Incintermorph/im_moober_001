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

import com.intermorph.cmmn.base.BaseVO;
import com.intermorph.cmmn.service.IMCmmnDescMapper;
import com.intermorph.cmmn.service.IMCmmnDescService;
import com.intermorph.cmmn.service.IMCmmnDescVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.service.impl
 * @File : IMCmmnDescServiceImpl.java
 * @Title : {간단한 프로그램의 명칭을 기록}
 * @date : 2022. 2. 18
 * @author : 노성용
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Service("IMCmmnDescService")
public class IMCmmnDescServiceImpl extends EgovAbstractServiceImpl  implements IMCmmnDescService {

	@Resource(name = "IMCmmnDescMapper")
	protected IMCmmnDescMapper cmmnDescMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.intermorph.cmmn.service.IMCmmnDescService#selectList(com.intermorph.cmmn.
	 * service.IMCmmnDescVO)
	 */
	@Override
	public List<IMCmmnDescVO> selectListCmmnDesc(IMCmmnDescVO vo) throws Exception {


		return cmmnDescMapper.selectList(vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.intermorph.cmmn.service.IMCmmnDescService#selectList(String,String)
	 */
	@Override
	public HashMap<String, String> selectListCmmnDescResultMap(String tblId,String tblRefId) throws Exception {

		HashMap<String, String> resultMap = new HashMap<String, String>();
		IMCmmnDescVO vo= new IMCmmnDescVO();
		vo.setTblId(tblId);
		vo.setTblRefId(tblRefId);
		List<IMCmmnDescVO> list = cmmnDescMapper.selectList(vo);
		if (list!=null && list.size()>0) {
			for (IMCmmnDescVO rvo : list) {
				resultMap.put(rvo.getRefNm(), rvo.getCmmnDesc());
			}
		}

		return resultMap;
	}

	private int savelist(List<IMCmmnDescVO> list) throws Exception {
		int result = 0;

		for (IMCmmnDescVO vo : list) {
			if (cmmnDescMapper.update(vo) == 0) {
				cmmnDescMapper.insert(vo);
			}
		}
		result++;
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.cmmn.service.IMCmmnDescService#insertCmmnDesc(String , String ,String , String ,BaseVO )
	 */
	@Override
	public int insertCmmnDesc(String tblId, String tblRefId,String refNm, String cmmnDesc,BaseVO auditVO)
			throws Exception {
		int result = 0;

		List<IMCmmnDescVO> list = new ArrayList<IMCmmnDescVO>();
		
			IMCmmnDescVO vo = new IMCmmnDescVO();
			vo.setTblRefId(tblRefId);
			vo.setTblId(tblId);
			vo.setRefNm(refNm);
			vo.setCmmnDesc(cmmnDesc);
			vo.copyAudit(auditVO);
			list.add(vo);
			result = savelist(list);
		
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.cmmn.service.IMCmmnDescService#savelist(String,String,HttpServletRequest,IMCmmnDescVO)
	 */
	@Override
	public int insertCmmnDesclist(String tblId, String tblRefId, HttpServletRequest req, IMCmmnDescVO cmmnDescVO)
			throws Exception {
		int result = 0;
		List<IMCmmnDescVO> list = new ArrayList<IMCmmnDescVO>();
		if (cmmnDescVO.getCmmnDescRefNms() != null && cmmnDescVO.getCmmnDescRefNms().length > 0) {
			for (String refNm : cmmnDescVO.getCmmnDescRefNms()) {
				String cmmnDesc = req.getParameter(refNm) == null ? "" : (String) req.getParameter(refNm);
				IMCmmnDescVO vo = new IMCmmnDescVO();
				vo.setTblRefId(tblRefId);
				vo.setTblId(tblId);
				vo.setRefNm(refNm);
				vo.setCmmnDesc(cmmnDesc);
				vo.copyAudit(cmmnDescVO);
				list.add(vo);
			}
		}
		if (list.size() > 0) {
			result = savelist(list);
		}

		return result;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.cmmn.service.IMCmmnDescService#savelist(String,String,HttpServletRequest,IMCmmnDescVO)
	 */
	@Override
	public int updateCmmnDesclist(String tblId, String tblRefId, HttpServletRequest req, IMCmmnDescVO cmmnDescVO)
			throws Exception {
		int result = 0;
		List<IMCmmnDescVO> list = new ArrayList<IMCmmnDescVO>();
		if (cmmnDescVO.getCmmnDescRefNms() != null && cmmnDescVO.getCmmnDescRefNms().length > 0) {
			for (String refNm : cmmnDescVO.getCmmnDescRefNms()) {
				String cmmnDesc = req.getParameter(refNm) == null ? "" : (String) req.getParameter(refNm);
				IMCmmnDescVO vo = new IMCmmnDescVO();
				vo.setTblRefId(tblRefId);
				vo.setTblId(tblId);
				vo.setRefNm(refNm);
				vo.setCmmnDesc(cmmnDesc);
				vo.copyAudit(cmmnDescVO);
				list.add(vo);
			}
		}
		if (list.size() > 0) {
			result = savelist(list);
		}
		
		return result;
	}

}
