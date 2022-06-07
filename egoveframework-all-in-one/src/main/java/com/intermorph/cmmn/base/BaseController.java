/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.base;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;

import com.intermorph.cmmn.exception.IMProcErrors;
import com.intermorph.cmmn.exception.IMProcException;
import com.intermorph.cmmn.util.IMStringUtil;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.exception.EgovXssException;
import egovframework.com.cmm.util.EgovUserDetailsHelper;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.base
 * @File    : BaseController.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 1. 13
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
public class BaseController {

	protected final Log log = LogFactory.getLog(getClass());
	
	/**
	 * empty value 에 대해 디폴트값 설정 Setting
	 * 
	 * @param target
	 * @param pairValues
	 * @throws AofException
	 */
	public void setEmptyValue(Object target, String... pairValues) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		if (pairValues != null) {
			for (String pair : pairValues) {
				String[] values = pair.split("=");
				if (values.length == 2) {
					map.put(values[0], values[1]);
				}
			}
		}
		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()) {
			try {
				String name = it.next();
				PropertyDescriptor desc = BeanUtils.getPropertyDescriptor(target.getClass(), name);
				Method writeMethod = desc.getWriteMethod();
				if (writeMethod != null) {
					String writeValue = map.get(name);
					Method readMethod = desc.getReadMethod();
					Object readValue = readMethod.invoke(target, new Object[0]);
					String returnType = desc.getPropertyType().getName();
					if ("int".equals(returnType) || "java.lang.Integer".equals(returnType)) {
						if (readValue == null || Integer.parseInt(String.valueOf(readValue)) == 0) {
							writeMethod.invoke(target, new Object[] { Integer.parseInt(writeValue.trim()) });
						}
					} else if ("long".equals(returnType) || "java.lang.Long".equals(returnType)) {
						if (readValue == null || Long.parseLong(String.valueOf(readValue)) == 0) {
							writeMethod.invoke(target, new Object[] { Long.parseLong(writeValue.trim()) });
						}
					} else if ("float".equals(returnType) || "java.lang.Float".equals(returnType)) {
						if (readValue == null || Float.parseFloat(String.valueOf(readValue)) == 0) {
							writeMethod.invoke(target, new Object[] { Float.parseFloat(writeValue.trim()) });
						}
					} else if ("double".equals(returnType) || "java.lang.Double".equals(returnType)) {
						if (readValue == null || Double.parseDouble(String.valueOf(readValue)) == 0) {
							writeMethod.invoke(target, new Object[] { Double.parseDouble(writeValue.trim()) });
						}
					} else if ("java.lang.String".equals(returnType)) {
						if (readValue == null || String.valueOf(readValue).length() == 0) {
							writeMethod.invoke(target, new Object[] { writeValue });
						}
					}
				}
			} catch (Exception e) {
				log.debug(e.getMessage());
			}
		}

	}
	
	/**
	 * 로그인 정보를 체크 하고 
	 * 회원 정보 및 IP 를 세팅한다.
	 * @param req
	 * @param vos
	 * @throws Exception
	 */
	public void loginCheckSetAudit(HttpServletRequest req, BaseVO... vos) throws Exception {
		//인증된사용자 여부
		boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();	
		//미민증사용자 체크
		if(!isAuthenticated) {
			throw new IMProcException(IMProcErrors.로그인정보없음);
		}
		/** 로그인 정보 확인  */
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		if (vos != null) {
			for (BaseVO vo : vos) {
				if (vo == null) {
					continue;
				} 
				vo.setFrstRegerId(user.getUniqId());
				vo.setFrstRegerIp(IMStringUtil.getRemoteAddr(req));
				vo.setLastMdferId(user.getUniqId());
				vo.setLastMdferIp(IMStringUtil.getRemoteAddr(req));
			}
		}
		
		
	}

}
