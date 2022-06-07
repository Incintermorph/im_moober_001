/**
 * 
 */
package com.intermorph.study.cnts.web;

import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.intermorph.cmmn.IMGlobals;
import com.intermorph.cmmn.base.BaseController;
import com.intermorph.cmmn.util.IMDateUtil;
import com.intermorph.cmmn.util.IMStringUtil;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;

/**
 * @author 노성용
 *
 */
@Controller
public class IMStudyCntsUserController extends BaseController{
	

	
	/**
	 *  학습창 접근 토큰 생성 
	 * @param userUniqId
	 * @return
	 * @throws Exception
	 */
	private String getAccessToken(String userUniqId) throws Exception{		
		
		String accessToken = IMDateUtil.getImToday("yyyyMMddHHmmss")+IMGlobals.IM_SEPARATOR+userUniqId+IMGlobals.IM_SEPARATOR+UUID.randomUUID().toString().replaceAll("[-]", "");
		accessToken = IMStringUtil.encryptString(accessToken, IMGlobals.IM_SECURITY_KEY);
		return accessToken;
		
	}
	
	/**
	 * 학습창 접큰 토큰 유효성 체크 
	 * @param accessToken
	 * @return
	 * @throws Exception
	 */
	private Boolean checkAccessToken(String accessToken) throws Exception{
		Boolean checkAccess= false;
		
		String decryptAccessToken = IMStringUtil.decryptString(accessToken, IMGlobals.IM_SECURITY_KEY);
		
		String[] arrAccessToken =decryptAccessToken.split(IMGlobals.IM_SEPARATOR);
		
		for(String str : arrAccessToken ) {
			System.out.println("str : " + str);
		}
		checkAccess= true;
		
		return checkAccess;
		
	}
	
	/**
	 * initialize
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping (value = { "/user/study/cnts/initialize.do"})
	public ModelAndView initialize(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		

		HashMap<String, String> dataModel = new HashMap<String, String>();
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		
		for (IMStudyCntsDataEnum v : IMStudyCntsDataEnum.values()) {
			dataModel.put(v.dataKey, v.defaultValue);
		}
		
		dataModel.put("cmi.learner_id", user.getId());
		dataModel.put("cmi.learner_name", user.getName());
		
		dataModel.put("accessToken", getAccessToken(user.getUniqId()));
		dataModel.put("startTime", System.currentTimeMillis()+"");
		
		
		//디비에서 기존 정보 확인함  
		mav.addObject("dataModel", dataModel);
		mav.addObject("result", "true");
		mav.addObject("message", "initialize");
		mav.setViewName("jsonView");	
		
		
		
		return mav;
	}
	/**
	 * terminate
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping (value = { "/cmmn/study/cnts/commit.do"})
	public ModelAndView commit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		checkAccessToken(IMStringUtil.getParameter(req, "accessToken"));
		
		mav.addObject("dataModel", saveData(req, res));
		mav.addObject("result", "true");
		mav.addObject("message", "commit");
		mav.setViewName("jsonView");		
		
		return mav;
	}
	
	private HashMap<String, String>  saveData(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HashMap<String, String> dataModel = new HashMap<String, String>();
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		
		String paramValue;
		for (IMStudyCntsDataEnum v : IMStudyCntsDataEnum.values()) {
			paramValue = IMStringUtil.getParameter(req, v.dataKey);
			if(IMStringUtil.isEmpty(paramValue)){
				dataModel.put(v.dataKey, v.defaultValue);
			}else {
				dataModel.put(v.dataKey, paramValue);
			}
		}
		
		String resultCode="success";
		
		
		String strProg = (String)dataModel.get("cmi.progress_measure");
		//소수점 인정 자리수는 4자리
		if(strProg.length()>7) {
			strProg = strProg.substring(0, 6);
		}
		//진도율 저장 
		Double progress_measure = Double.parseDouble(strProg);
		Double save_progress_measure = Double.parseDouble(dataModel.get("save_progress_measure"));
		if(progress_measure>save_progress_measure && progress_measure <= 1D ) {
			//진도율 저장
			save_progress_measure = progress_measure;
			dataModel.put("save_progress_measure", progress_measure+"");
		 
		}	
		
		Double cmi_completion_threshold = Double.parseDouble(dataModel.get("cmi.completion_threshold"));
		
		if(save_progress_measure>=cmi_completion_threshold) {
			dataModel.put("cmi.completion_status", "complete");
			dataModel.put("cmi.success_status", "passed");

		}else {
			dataModel.put("cmi.completion_status", "incomplete");
			dataModel.put("cmi.success_status", "failed");
		}
		
		
		//학습시간 계산 
		long startTime = Long.parseLong(dataModel.get("startTime"));
		long attemptSessionTime = System.currentTimeMillis() - startTime;
		
		long studyTime = Long.parseLong(dataModel.get("studyTime"));
		long cmisession_time = Long.parseLong(dataModel.get("cmi.session_time"));
		
		
		
		dataModel.put("attemptSessionTime", attemptSessionTime+"");
		//5분이상 커밋 시간은 저장 하지 않음 
		long chektime=1000*60*5;
		if(attemptSessionTime<chektime) {
			studyTime += attemptSessionTime;
			cmisession_time += attemptSessionTime;
			dataModel.put("studyTime", studyTime+"");
			dataModel.put("cmi.total_time", studyTime+"");
			
			dataModel.put("cmi.session_time", cmisession_time+"");
			resultCode="timeover";
			
		}
		dataModel.put("startTime", System.currentTimeMillis()+"");
		
		dataModel.put("cmi.learner_id", user.getId());
		dataModel.put("cmi.learner_name", user.getName());
		dataModel.put("resultCode", resultCode);
			

		//디비 업데이트 필요함  	
		return dataModel;
	}
	/**
	 * terminate
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping (value = { "/cmmn/study/cnts/terminate.do"})
	public ModelAndView terminate(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();

		checkAccessToken(IMStringUtil.getParameter(req, "accessToken"));
		
		mav.addObject("dataModel", saveData(req, res));
		mav.addObject("result", "true");
		mav.addObject("message", "terminate");
		mav.setViewName("jsonView");		
		
		return mav;
	}
	

	/**
	 * restore
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping (value = { "/cmmn/study/cnts/restore.do"})
	public ModelAndView restore(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", "true");
		mav.addObject("message", "restore");
		mav.setViewName("jsonView");		
		
		return mav;
	}
	
	/**
	 * others data
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping (value = { "/cmmn/study/cnts/get/others/data.do"})
	public ModelAndView others(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", "true");
		mav.addObject("message", "others data");
		mav.setViewName("jsonView");		
		
		return mav;
	}
	
}
