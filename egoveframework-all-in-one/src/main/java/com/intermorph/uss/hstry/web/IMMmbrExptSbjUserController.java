/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.hstry.web;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.intermorph.cmmn.base.BaseController;
import com.intermorph.cmmn.util.IMStringUtil;
import com.intermorph.uss.hstry.service.IMMmbrExptSbjService;
import com.intermorph.uss.hstry.service.IMMmbrExptSbjVO;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.hstry.web
 * @File : IMMmbrExptSbjController.java
 * @Title : 회원면제과목
 * @date : 2022.03.10 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Controller
public class IMMmbrExptSbjUserController extends BaseController {


    @Resource (name = "IMMmbrExptSbjService")
	private IMMmbrExptSbjService mmbrExptSbjService;
	
	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;
	


	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileService;

	
	
	/**
	 * 회원면제과목 목록
	 * 
	 * @param req
	 * @param res
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/mmbrExptSbj/selectList.do")
	public ModelAndView selectList(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		Long memberSrl = Long.parseLong(IMStringUtil.getParameter(req, "memberSrl"));
		mav.addObject("selectExptSbj", mmbrExptSbjService.selectListMmbrExptSbj(memberSrl));

		mav.setViewName("/view/user/uss/mmbrHstry/selectListMmbrExptSbj");

		return mav;
	}
	
	/**
	 * 회원면제과목 목록
	 * 
	 * @param req
	 * @param res
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/mmbrExptSbj/selectListOnline.do")
	public ModelAndView selectListOnline(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		Long memberSrl = Long.parseLong(IMStringUtil.getParameter(req, "memberSrl"));
		mav.addObject("selectExptSbjOnline", mmbrExptSbjService.selectListMmbrExptSbjOnline(memberSrl));
		
		mav.setViewName("/view/user/uss/mmbrHstry/selectListMmbrExptSbjOnline");
		
		return mav;
	}
	
	
	
	/**
	 * 회원면제과목  등록 화면 
	 * @param req
	 * @param res
	 * @param iMMmbrExptSbj
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/mmbrExptSbj/regist.do")
	public ModelAndView regist(HttpServletRequest req, HttpServletResponse res, IMMmbrExptSbjVO iMMmbrExptSbj) throws Exception {
		ModelAndView mav = new ModelAndView();

		mav.addObject("iMMmbrExptSbj", iMMmbrExptSbj);
		if("02".equals(iMMmbrExptSbj.getExempSbjDvsn())) {
			mav.setViewName("/view/user/uss/mmbrHstry/registMmbrExptSbjOnline");
		}else {
			mav.setViewName("/view/user/uss/mmbrHstry/registMmbrExptSbj");
		}

		return mav;
	}
	
	/**
	 * 회원면제과목 수정 화면 
	 * @param req
	 * @param res
	 * @param iMMmbrExptSbj
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/mmbrExptSbj/modify.do")
	public ModelAndView modify(HttpServletRequest req, HttpServletResponse res, IMMmbrExptSbjVO iMMmbrExptSbj) throws Exception {
		ModelAndView mav = new ModelAndView();

		IMMmbrExptSbjVO detail = mmbrExptSbjService.selectDetailMmbrExptSbj(iMMmbrExptSbj);
		mav.addObject("iMMmbrExptSbj",  detail);

		
		if (detail!=null &&  detail.getFileId() != null && !"".equals(detail.getFileId())) {
			FileVO fileVO = new FileVO();
			fileVO.setAtchFileId(detail.getFileId());
			mav.addObject("fileList", fileService.selectFileInfs(fileVO));
		}
		
		if("02".equals(iMMmbrExptSbj.getExempSbjDvsn())) {
			mav.setViewName("/view/user/uss/mmbrHstry/modifyMmbrExptSbjOnline");
		}else {
			mav.setViewName("/view/user/uss/mmbrHstry/modifyMmbrExptSbj");
		}
		
		return mav;
	}
	
	
	
	/**
	 * 회원면제과목 등록 저장 
	 * @param req
	 * @param res
	 * @param iMmmbrExptSbj
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/mmbrExptSbj/insert.do")
	public ModelAndView insert(HttpServletRequest req, HttpServletResponse res,
			@ModelAttribute("iMMmbrExptSbj") IMMmbrExptSbjVO iMMmbrExptSbj )
			throws Exception {

		ModelAndView mav = new ModelAndView();
		loginCheckSetAudit(req, iMMmbrExptSbj);
		
		mav.addObject("result", mmbrExptSbjService.insertMmbrExptSbj(iMMmbrExptSbj));

		mav.setViewName("jsonView");
		return mav;
	}
	
	/**
	 * 회원면제과목 수정 저장
	 * @param req
	 * @param res
	 * @param iMmmbrExptSbj
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/mmbrExptSbj/update.do")
	public ModelAndView update(HttpServletRequest req, HttpServletResponse res,
			@ModelAttribute("iMMmbrExptSbj") IMMmbrExptSbjVO iMMmbrExptSbj)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMMmbrExptSbj);
		mav.addObject("result",mmbrExptSbjService.updateMmbrExptSbj(iMMmbrExptSbj));
		
		mav.setViewName("jsonView");
		return mav;
	}

	
	
	
	/**
	 *  회원면제과목  삭제
	 * @param req
	 * @param res
	 * @param iMmmbrExptSbj
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/mmbrExptSbj/delete.do")
	public ModelAndView delete(HttpServletRequest req, HttpServletResponse res, IMMmbrExptSbjVO iMMmbrExptSbj)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMMmbrExptSbj);

		mav.addObject("result", mmbrExptSbjService.deleteMmbrExptSbj(iMMmbrExptSbj));

		mav.setViewName("jsonView");
		return mav;
	}
	
	
	/**
	 *  회원면제과목 다중 삭제 처리
	 * 
	 * @param req
	 * @param res
	 * @param IMMmbrExptSbjVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping ("/user/mmbrExptSbj/deletelist.do")
	public ModelAndView deletelist(HttpServletRequest req, HttpServletResponse res, IMMmbrExptSbjVO iMMmbrExptSbj) throws Exception {
		ModelAndView mav = new ModelAndView();

		loginCheckSetAudit(req, iMMmbrExptSbj);
		

		List<IMMmbrExptSbjVO> mmbrExptSbjs = new ArrayList<IMMmbrExptSbjVO>();
				
		for (Long idx: iMMmbrExptSbj.getCheckIndexs()) {
			IMMmbrExptSbjVO o = new IMMmbrExptSbjVO();
			o.setMmbrExptSbjId(iMMmbrExptSbj.getMmbrExptSbjIds()[idx.intValue()]);
			o.copyAudit(iMMmbrExptSbj);

			mmbrExptSbjs.add(o);
		}

		if (mmbrExptSbjs.size() > 0) {
			mav.addObject("result", mmbrExptSbjService.deletelistMmbrExptSbj(mmbrExptSbjs));
		} else {
			mav.addObject("result", 0);
		}

		mav.setViewName("jsonView");
		return mav;
	}
	
	
	
	

	
}