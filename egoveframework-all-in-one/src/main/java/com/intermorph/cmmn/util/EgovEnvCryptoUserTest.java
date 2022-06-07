/**
 * 
 */
package com.intermorph.cmmn.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.penta.scpdb.ScpDbAgent;

import egovframework.rte.fdl.cryptography.EgovEnvCryptoService;
import egovframework.rte.fdl.cryptography.impl.EgovEnvCryptoServiceImpl;
/**
 * 
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.util
 * @File    : EgovEnvCryptoUserTest.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 2. 8
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
public class EgovEnvCryptoUserTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovEnvCryptoUserTest.class);
	 
	public static void main(String[] args) {
		
		Double avgScore=23.1233;
		
		System.out.println("avgScore : " + Math.round(avgScore*10)/10D);
		
	}
	public static void main1(String[] args) {
		String accountNum = "1234567890";
		
		String accountID = "webmaster";
		
		String phonest = "023339456";
		String phonestName = " ";
		String email = "test@test.com";

		String phonestST = "1566-2059";
		
		
		System.out.println("phonestST : " + phonestST.replaceAll("-", ""));
		System.out.println("phonestST Masking : " + IMStringUtil.getPhoneMasking(phonestST));
		
		System.out.println("1212 : " + IMStringUtil.getAccountNumMasking(accountNum));
		
		System.out.println("getIdMasking : " + IMStringUtil.getIdMasking(accountID));
		
		System.out.println("getPhoneMasking : " + IMStringUtil.getPhoneMasking(phonest));
		System.out.println("getNameMasking : " + IMStringUtil.getNameMasking(phonestName));
		System.out.println("getEmailMasking : " + IMStringUtil.getEmailMasking(email));
	
		HashMap<String, String> map = new LinkedHashMap<String, String>();
		
		
		map.put("1A", "1B3");
		map.put("3B", "3B1");
		map.put("2B", "2B1");
		map.put("1B", "1A1");
		Object[] key= map.keySet().toArray();
		Arrays.sort(key);
		HashMap<String, String> map2 = new LinkedHashMap<String, String>();
		for (Object nKey : key)
		{
			map2.put(nKey.toString(), map.get(nKey.toString()));
		}
		map=null;
		for (String nKey : map2.keySet())
		{
			System.out.println(map2.get(nKey));
		}
	}


	public static void main2(String[] args) {
 
		String[] arrCryptoString = { 
		"sungyong2",         //데이터베이스 접속 계정 설정
		"aof51004!S",   //데이터베이스 접속 패드워드 설정
		"url",            //데이터베이스 접속 주소 설정
		"databaseDriver"  //데이터베이스 드라이버
              };
 
 
		LOGGER.info("------------------------------------------------------");		
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:/context-crypto-test.xml"});
		EgovEnvCryptoService cryptoService = context.getBean(EgovEnvCryptoServiceImpl.class);
		LOGGER.info("------------------------------------------------------");
 
		String label = "";
		try {
			for(int i=0; i < arrCryptoString.length; i++) {		
				if(i==0)label = "사용자 아이디";
				if(i==1)label = "사용자 비밀번호";
				if(i==2)label = "접속 주소";
				if(i==3)label = "데이터 베이스 드라이버";
				System.out.println(label+" 원본(orignal):" + arrCryptoString[i]);
				System.out.println(label+" 인코딩(encrypted):" + cryptoService.encrypt(arrCryptoString[i]));
				System.out.println("------------------------------------------------------");
			}
		} catch (IllegalArgumentException e) {
			LOGGER.error("["+e.getClass()+"] IllegalArgumentException : " + e.getMessage());
		} catch (Exception e) {
			LOGGER.error("["+e.getClass()+"] Exception : " + e.getMessage());
		}
 
	}
}
