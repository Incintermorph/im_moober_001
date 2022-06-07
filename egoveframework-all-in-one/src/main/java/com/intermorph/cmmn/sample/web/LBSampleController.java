/**
 * 
 */
package com.intermorph.cmmn.sample.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.intermorph.cmmn.base.BaseController;
import com.intermorph.cmmn.base.BaseResultSet;
import com.intermorph.cmmn.sample.service.SampleService;
import com.intermorph.cmmn.sample.vo.LBSample;
import com.intermorph.cmmn.sample.vo.condition.LBSampleCondtion;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

/**
 * 
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.sample.web
 * @File : LBSampleController.java
 * @Title : {간단한 프로그램의 명칭을 기록}
 * @date : 2022. 1. 13
 * @author : 노성용
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Controller
public class LBSampleController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(LBSampleController.class);

	@Resource(name = "ehcache")
	Ehcache ehCache;

	@Resource(name = "SampleService")
	private SampleService sampleService;

	/**
	 * 메인 샘플
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/lb/sample/main.do")
	public ModelAndView main(HttpServletRequest req, HttpServletResponse res, LBSample dto) throws Exception {
		ModelAndView mav = new ModelAndView();
		LOGGER.debug("Page Link  : /lb/sample/main.do");
		mav.addObject("dto", dto);
		LBSampleCondtion condtion = new LBSampleCondtion();
		condtion.setSrchCodeId("COM001");

		
		String ehCacheName = "sample";
		Ehcache cache = ehCache.getCacheManager().getCache( ehCacheName );
		Element value = null;
		BaseResultSet detail = null;
		String cacheKey =  ehCacheName  + "key";
		if (cache == null) {
			LOGGER.debug("sample undefined.");
		} else {
			value = cache.get(cacheKey);
			if (value == null) {
				LOGGER.debug("cache put.");
				detail = sampleService.getDetail(condtion);
				cache.put(new Element(cacheKey, detail));
			} else {
				LOGGER.debug("cache get.");
				detail = (BaseResultSet) value.getObjectValue();
			}
		}

		mav.addObject("detail", detail);
		mav.setViewName("/com/sample/mainSample");
		return mav;
	}

	/**
	 * 메인 샘플
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/lb/sample/list.do")
	public ModelAndView main3(HttpServletRequest req, HttpServletResponse res, LBSample dto) throws Exception {
		ModelAndView mav = new ModelAndView();
		LOGGER.debug("Page Link  : /lb/sample/main.do");
		mav.addObject("dto", dto);
		LBSampleCondtion condtion = new LBSampleCondtion();
		condtion.setSrchCodeId("COM001");

		mav.addObject("detail", sampleService.getDetail(condtion));
		mav.setViewName("layout/user/sample/listSample");
		return mav;
	}

	/**
	 * 메인 샘플
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/lb/sample/listUser.do")
	public ModelAndView listsaple(HttpServletRequest req, HttpServletResponse res, LBSample dto) throws Exception {
		ModelAndView mav = new ModelAndView();
		LOGGER.debug("Page Link  : /lb/sample/main.do");
		mav.addObject("dto", dto);
		LBSampleCondtion condtion = new LBSampleCondtion();
		condtion.setSrchCodeId("COM001");

		mav.addObject("detail", sampleService.getDetail(condtion));
		mav.setViewName("layout/user/sample/listUser");
		return mav;
	}

	
	/**
	 * ehcache 캐쉬 삭제 한다.	
	 * @param req
	 * @param res
	 * @param ehCacheName : /resources/egovframework/spring/com/ehcache.xml 에 정의된  cache name 를 변수로 받는다.
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/lb/ehcache/{ehCacheName}/remove.do")
	public ModelAndView removeCache(HttpServletRequest req, HttpServletResponse res,
			@PathVariable("ehCacheName") String ehCacheName) throws Exception {
		ModelAndView mav = new ModelAndView();
		Ehcache cache = ehCache.getCacheManager().getCache(ehCacheName);
		cache.removeAll();
		mav.addObject("result", 0);

		mav.setViewName("jsonView");
		return mav;
	}

}
