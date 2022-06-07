/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.hstry.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.intermorph.cmmn.IMGlobals;
import com.intermorph.cmmn.base.BaseController;
import com.intermorph.cmmn.util.IMStringUtil;
import com.intermorph.uss.hstry.web.dto.PotalAgncyResultDTO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.hstry.web
 * @File : IMPotalAgncyUserController.java
 * @Title : {간단한 프로그램의 명칭을 기록}
 * @date : 2022. 3. 17
 * @author : 노성용
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Controller
public class IMPotalAgncyUserController extends BaseController {
	/**
	 * 포탈의 기관 정보 목록 (연동)
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/potal/agncy/selectList.do")
	public ModelAndView selectList(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String page = IMStringUtil.getParameter(req, "page");
		String searchType = IMStringUtil.getParameter(req, "searchType");
		String searchWord = IMStringUtil.getParameter(req, "searchWord");

		if (page == null || "".equals(page)) {
			page = "1";
		}

		Map<String, Object> params = new LinkedHashMap<>(); // 파라미터 세팅
		params.put("page", page);
		params.put("search_type", searchType);
		params.put("search_word", searchWord);
		params.put("sc_biz_kind", "");
		params.put("sc_status", "2001");
		disableSslVerification();
	//	URL url = new URL("https://keep.beta.so/hwangyosa/institution-list");
		URL url = new URL( IMGlobals.IM_POTAL_SSOURL + "/hwangyosa/institution-list");

		StringBuilder postData = new StringBuilder();
		for (Map.Entry<String, Object> param : params.entrySet()) {
			if (postData.length() != 0)
				postData.append('&');
			postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
			postData.append('=');
			postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
		}
		byte[] postDataBytes = postData.toString().getBytes("UTF-8");

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
		conn.setDoOutput(true);
		conn.getOutputStream().write(postDataBytes); // POST 호출

		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

		String inputLine;
		String returnString = "";
		while ((inputLine = in.readLine()) != null) { // response 출력
			// System.out.println(inputLine);
			returnString = returnString.concat(inputLine);
		}

		in.close();

		System.out.println(returnString);
		Gson gson = new Gson();

		PotalAgncyResultDTO result = gson.fromJson(returnString, PotalAgncyResultDTO.class);

		mav.addObject("result", result);

		mav.setViewName("/view/user/uss/mmbrHstry/selectListPotalAgncy");

		return mav;
	}

	private void disableSslVerification() {
		// TODO Auto-generated method stub
		try {
			// Create a trust manager that does not validate certificate chains
			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				public void checkClientTrusted(X509Certificate[] certs, String authType) {
				}

				public void checkServerTrusted(X509Certificate[] certs, String authType) {
				}
			} };

			// Install the all-trusting trust manager
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

			// Create all-trusting host name verifier
			HostnameVerifier allHostsValid = new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			};

			// Install the all-trusting host verifier
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}
	}
}
