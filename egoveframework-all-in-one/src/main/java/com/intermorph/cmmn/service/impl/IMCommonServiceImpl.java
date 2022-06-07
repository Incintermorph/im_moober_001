/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.intermorph.cmmn.service.IMCommonService;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.impl.CmmUseDAO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.service.impl
 * @File : IMCommonServiceImpl.java
 * @Title : {간단한 프로그램의 명칭을 기록}
 * @date : 2022. 2. 16
 * @author : 노성용
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Service("IMCommonService")
public class IMCommonServiceImpl extends EgovAbstractServiceImpl  implements IMCommonService {

	@Resource(name = "cmmUseDAO")
	private CmmUseDAO cmmUseDAO;

	@Resource(name = "ehcache")
	Ehcache ehCache;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.intermorph.cmmn.service.IMCommonService#selectCmmCodeDetail(java.lang.
	 * String)
	 */
	@Override
	public List<CmmnDetailCode> selectCmmCodeDetail(String codeId) throws Exception {

		String ehCacheName = "cmmCode";
		Ehcache cache = ehCache.getCacheManager().getCache(ehCacheName);
		Element value = null;
		List<CmmnDetailCode> list = null;
		String cacheKey = ehCacheName + "_" + codeId;

		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId(codeId);

		if (cache == null) {
			// System.out.println("get cache error");
			list = cmmUseDAO.selectCmmCodeDetail(vo);
		} else {
			value = cache.get(cacheKey);
			if (value == null) {
				list = cmmUseDAO.selectCmmCodeDetail(vo);
				// System.out.println("call quiery");
				cache.put(new Element(cacheKey, list));
			} else {
				list = (List<CmmnDetailCode>) value.getObjectValue();
				// System.out.println("get cache");
			}
		}

		return list;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.intermorph.cmmn.service.IMCommonService#selectCmmCodeDetail(java.lang.
	 * String)
	 */
	@Override
	public List<CmmnDetailCode> selectCmmCodeDetailSort(String codeId,Long sort) throws Exception {
		
		String ehCacheName = "cmmCode";
		Ehcache cache = ehCache.getCacheManager().getCache(ehCacheName);
		Element value = null;
		List<CmmnDetailCode> list = null;
		String cacheKey = ehCacheName + "_" + codeId;
		
		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId(codeId);
		vo.setSort(sort);
		if (cache == null) {
			// System.out.println("get cache error");
			list = cmmUseDAO.selectCmmCodeDetail(vo);
		} else {
			value = cache.get(cacheKey);
			if (value == null) {
				list = cmmUseDAO.selectCmmCodeDetail(vo);
				// System.out.println("call quiery");
				cache.put(new Element(cacheKey, list));
			} else {
				list = (List<CmmnDetailCode>) value.getObjectValue();
				// System.out.println("get cache");
			}
		}
		
		return list;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.intermorph.cmmn.service.IMCommonService#selectCmmCodeDetail(java.lang.String,java.lang.String)
	 */
	@Override
	public CmmnDetailCode selectCmmCodeOneDetail(String codeId,String code) throws Exception {		
		
		List<CmmnDetailCode> list = selectCmmCodeDetail(codeId);
		
		for(CmmnDetailCode vo  : list) {
			 if(code.equals(vo.getCode())){
				 return vo;
			 }
		}
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.intermorph.cmmn.service.IMCommonService#selectCmmCodeOneDetailNm(java.lang.String,java.lang.String)
	 */
	@Override
	public String selectCmmCodeOneDetailNm(String codeId,String code) throws Exception {		
		
		List<CmmnDetailCode> list = selectCmmCodeDetail(codeId);
		
		for(CmmnDetailCode vo  : list) {
			 if(code.equals(vo.getCode())){
				 return vo.getCodeNm();
			 }
		}
		return "";
	}

}
