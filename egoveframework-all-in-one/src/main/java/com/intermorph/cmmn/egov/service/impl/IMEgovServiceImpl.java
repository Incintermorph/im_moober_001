/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.egov.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.intermorph.cmmn.base.BaseResultSet;
import com.intermorph.cmmn.egov.service.IMEgovMapper;
import com.intermorph.cmmn.egov.service.IMEgovResultSet;
import com.intermorph.cmmn.egov.service.IMEgovService;
import com.intermorph.cmmn.egov.service.IMEgovVO;

import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.uss.olh.faq.service.FaqVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.egov.service.impl
 * @File : IMEgovServiceImpl.java
 * @Title : {간단한 프로그램의 명칭을 기록}
 * @date : 2022. 2. 23
 * @author : 노성용
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Service("IMEgovService")
public class IMEgovServiceImpl extends EgovAbstractServiceImpl  implements IMEgovService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IMEgovServiceImpl.class);

	@Resource(name = "IMEgovMapper")
	protected IMEgovMapper iMEgovMapper;

	@Resource(name = "ehcache")
	Ehcache ehCache;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.cmmn.egov.service.IMEgovService#selectListAuthMenuCache(String)
	 */
	@Override
	public List<IMEgovResultSet> selectListAuthMenuCache(String authorCode) throws Exception {

		String ehCacheName = "authMenu";
		Ehcache cache = ehCache.getCacheManager().getCache(ehCacheName);
		Element value = null;
		List<IMEgovResultSet> list = null;
		String cacheKey = ehCacheName + "_" + authorCode;

		IMEgovVO vo = new IMEgovVO();
		vo.setAuthorCode(authorCode);

		if (cache == null) {
			list = iMEgovMapper.selectListAuthMenu(vo);
		} else {
			value = cache.get(cacheKey);
			if (value == null) {
				list = iMEgovMapper.selectListAuthMenu(vo);
				cache.put(new Element(cacheKey, list));
				LOGGER.debug("set Cache selectListAuthMenuCache : "+ authorCode);
			} else {
				list = (List<IMEgovResultSet>) value.getObjectValue();
				LOGGER.debug("get Cache selectListAuthMenuCache : " + authorCode);
			}
		}

		return list;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.cmmn.egov.service.IMEgovService#selectArticleMainList(BoardVO)
	 */
	@Override
	public List<BoardVO> selectArticleMainList(BoardVO bardVO) throws Exception {
		
		String ehCacheName = "frontMainPage";
		Ehcache cache = ehCache.getCacheManager().getCache(ehCacheName);
		Element value = null;
		List<BoardVO> list = null;
		String cacheKey = ehCacheName + "_Article" + bardVO.getBbsId();


		if (cache == null) {
			list = iMEgovMapper.selectArticleMainList(bardVO);
		} else {
			value = cache.get(cacheKey);
			if (value == null) {
				list = iMEgovMapper.selectArticleMainList(bardVO);
				cache.put(new Element(cacheKey, list));
				LOGGER.debug("set Cache selectArticleMainList ");
			} else {
				list = (List<BoardVO>) value.getObjectValue();
				LOGGER.debug("get Cache selectArticleMainList  ");
			}
		}
		
		return list;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.cmmn.egov.service.IMEgovService#selectFaqMainList(BoardVO)
	 */
	@Override
	public List<FaqVO> selectFaqMainList(BoardVO bardVO) throws Exception {

		String ehCacheName = "frontMainPage";
		Ehcache cache = ehCache.getCacheManager().getCache(ehCacheName);
		Element value = null;
		List<FaqVO> list = null;
		String cacheKey = ehCacheName + "_FaqMain";


		if (cache == null) {
			list = iMEgovMapper.selectFaqMainList(bardVO);
		} else {
			value = cache.get(cacheKey);
			if (value == null) {
				list = iMEgovMapper.selectFaqMainList(bardVO);
				cache.put(new Element(cacheKey, list));
				LOGGER.debug("set Cache selectFaqMainList ");
			} else {
				list = (List<FaqVO>) value.getObjectValue();
				LOGGER.debug("get Cache selectFaqMainList  ");
			}
		}

		return list;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.cmmn.egov.service.IMEgovService#selectMainListCrs(String)
	 */
	@Override
	public List<BaseResultSet> selectMainListCrs(String scCrsGrdCdv,Long limitNumber)  throws Exception{
		
		String ehCacheName = "frontMainPage";
		Ehcache cache = ehCache.getCacheManager().getCache(ehCacheName);
		Element value = null;
		List<BaseResultSet> list = null;
		String cacheKey = ehCacheName + "_MainListCrs" + scCrsGrdCdv;

		if (cache == null) {
			list = iMEgovMapper.selectMainListCrs(scCrsGrdCdv,limitNumber);
		} else {
			value = cache.get(cacheKey);
			if (value == null) {
				list = iMEgovMapper.selectMainListCrs(scCrsGrdCdv,limitNumber);
				cache.put(new Element(cacheKey, list));
				LOGGER.debug("set Cache selectMainListCrs ");
			} else {
				list = (List<BaseResultSet>) value.getObjectValue();
				LOGGER.debug("get Cache selectMainListCrs  ");
			}
		}

		
		return list;
		
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.cmmn.egov.service.IMEgovService#selectUserId(String)
	 */
	@Override
	public String selectUserId (String mbrId)  throws Exception{
		return iMEgovMapper.selectUserId(mbrId);
	}
	
	

}
