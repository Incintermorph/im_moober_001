/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.intermorph.cmmn.IMGlobals;
import com.intermorph.cmmn.service.IMSSOLoginDTO;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.util
 * @File    : IMSsoLoginUtil.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 3. 11
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
public class IMSsoLoginUtil {

	static String secret = "K0E4E7P9G9O3K2R0YDEPAGO";
	
	
	public static IMSSOLoginDTO login (String user_id, String user_password) throws Exception{
		String  id= ScrEncDecUtil.fn_encrypt(user_id, secret);
		String  pw= ScrEncDecUtil.fn_encrypt(user_password, secret);
		

		try {
			
			 Map<String,Object> params = new LinkedHashMap<>(); // 파라미터 세팅
		        params.put("user_id", id);
		        params.put("user_pwd", pw);
		    
			//URL url = new URL("https://keep.go.kr/ssoLogin/loginAction");
			URL url = new URL(IMGlobals.IM_POTAL_SSOURL +"/ssoLogin/loginAction");
			
			 StringBuilder postData = new StringBuilder();
		        for(Map.Entry<String,Object> param : params.entrySet()) {
		            if(postData.length() != 0) postData.append('&');
		            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
		            postData.append('=');
		            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
		        }
		        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
		 
		        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		        conn.setRequestMethod("POST");
		        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
		        conn.setDoOutput(true);
		        conn.getOutputStream().write(postDataBytes); // POST 호출
		 
		        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		 
		        String inputLine;
				String returnString = "";
		        while((inputLine = in.readLine()) != null) { // response 출력
		            //System.out.println(inputLine);
		            returnString = returnString.concat(inputLine);
		        }
		 
		        in.close();



			//System.out.println(returnString);
			
			Gson gson = new Gson();
			
			IMSSOLoginDTO result = gson.fromJson(returnString, IMSSOLoginDTO.class);
			if(result!=null && result.getResult()!=null) {
				if("OK".equals(result.getResult().trim())){
					result.setEmail(ScrEncDecUtil.fn_decrypt(result.getEmail(), secret));
					result.setName(ScrEncDecUtil.fn_decrypt(result.getName(), secret));
					result.setTel(ScrEncDecUtil.fn_decrypt(result.getTel(), secret));
					result.setMember_srl(ScrEncDecUtil.fn_decrypt(result.getMember_srl(), secret));
				}
			}

			//System.out.println("result.getMember_srl() : " + result.getMember_srl());
			
			return result;
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
