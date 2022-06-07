/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.util;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;

import egovframework.com.utl.fcc.service.EgovStringUtil;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.util
 * @File : IMStringUtil.java
 * @Title : {간단한 프로그램의 명칭을 기록}
 * @date : 2022. 2. 22
 * @author : 노성용
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
public class IMStringUtil extends EgovStringUtil {
	/**
	 * 3 자리 전화 번호 - 없는 경우는 자리수 기준
	 * 
	 * @param phoneNum
	 * @param num (3이하만 처리) 첫째자리는 IMStringUtil(phoneNum,0)
	 * @return
	 */
	public static String getPhoneNum(String phoneNum, int num) {
		if (phoneNum == null) {
			return "";
		}
		String[] arr = phoneNum.trim().split("-");
		if (num >= 3) {
			return "";
		}
		if (arr.length == 3) {
			return arr[num];
		} else {
			phoneNum = phoneNum.replace("-", "").trim();
			if (phoneNum.trim().length() > 7) {
				if (num == 0) {
					if ("02".equals(phoneNum.trim().substring(0, 2))) {
						return "02";
					} else {
						return phoneNum.substring(0, 3);
					}

				} else if (num == 1) {
					if ("02".equals(phoneNum.trim().substring(0, 2))) {
						return phoneNum.trim().substring(2, phoneNum.trim().length() - 4);
					} else {
						return phoneNum.trim().substring(3, phoneNum.trim().length() - 4);
					}

				} else if (num == 2) {
					return phoneNum.trim().substring(phoneNum.trim().length() - 4, phoneNum.trim().length());

				}
			} else {
				return "";
			}

		}

		return "";
	}

	/**
	 * 전화번호 - 등록
	 * 
	 * @param phone1
	 * @param phone2
	 * @param phone3
	 * @return
	 */
	public static String getPhoneNumDB(String phone1, String phone2, String phone3) {
		if (phone1 != null && !"".equals(phone1) && phone2 != null && !"".equals(phone2) && phone3 != null && !"".equals(phone3)) {
			return phone1 + "-" + phone2 + "-" + phone3;
		} else {
			return null;
		}
	}

	/**
	 * 전화번호 - 등록
	 * 
	 * @param phone1
	 * @param phone2
	 * @param phone3
	 * @return
	 */
	public static String getRrnoDB(String rrno1, String rrno2) {
		if (rrno1 != null && !"".equals(rrno1) && rrno2 != null && !"".equals(rrno2)) {
			return rrno1 + rrno2;
		} else {
			return null;
		}
	}

	/**
	 * 주민번호
	 * 
	 * @param rrno
	 * @param num (주민번호) 첫째자리는 IMStringUtil(rrno,0)
	 * @return
	 */
	public static String getRrno(String rrno, int num) {
		if (rrno == null) {
			return "";
		}
		String[] arr = rrno.trim().split("-");
		if (num >= 2) {
			return "";
		}
		if (arr.length == 2) {
			return arr[num];
		} else {
			rrno = rrno.replace("-", "").trim();
			if (rrno.trim().length() == 13) {
				if (num == 0) {
					return rrno.substring(0, 6);

				} else if (num == 1) {
					return rrno.trim().substring(6, 13);

				} else {
					return "";
				}
			}
		}

		return "";
	}
	/**
	 * 마스킹 처리 (주민번호)
	 * 
	 * @param rrno
	 * @return
	 */
	public static String getRrnoMasking(String rrno) {
		if (rrno == null || "".equals(rrno.trim())) {
			return null;
		}
		rrno = rrno.replace("-", "").trim();
		if(rrno.length()==13) {
			return rrno.substring(0, 6) +"-"+ rrno.trim().substring(3, 4)+"******";
		}else {
			return "";
		}
	}

	/**
	 * 마스킹 처리 (ID)
	 * 
	 * @param orgId
	 * @return
	 */
	public static String getIdMasking(String orgId) {
		if (orgId == null || "".equals(orgId.trim())) {
			return null;
		}
		orgId = orgId.trim();
		String maskinId = "";

		if (orgId.length() < 6 && orgId.length() > 2) {
			maskinId = orgId.substring(0, 2) + "***";
		} else if (orgId.length() > 6) {
			maskinId = orgId.substring(0, 2) + "***" + orgId.substring(5, orgId.length());
		} else {
			maskinId = orgId.substring(0, 2) + "***";
		}

		return maskinId;
	}

	/**
	 * 마스킹 처리 (전화번호) 010-0101-0000 / 01001010000
	 * 
	 * @param orgphone
	 * @return
	 */
	public static String getPhoneMasking(String orgphone) {
		if (orgphone == null || "".equals(orgphone.trim())) {
			return null;
		}
		orgphone = orgphone.trim();
		String[] arr = orgphone.trim().split("-");

		String maskinPhone = "";
		if (arr.length == 3) {
			maskinPhone = arr[0] + "-****-" + arr[2];
		} else {
			orgphone = orgphone.replaceAll("-", "");
			maskinPhone = getPhoneNum(orgphone, 0) + "-****-" + getPhoneNum(orgphone, 2);
		}

		return maskinPhone;
	}

	/**
	 * 이메일 마스킹 처리 aaaa@aaa.com -> a***@aaa.com
	 * 
	 * @param email
	 * @return
	 */
	public static String getEmailMasking(String email) {

		if (email == null || "".equals(email.trim())) {
			return null;
		}
		email = email.trim();
		/*
		 * 요구되는 메일 포맷 {userId}@domain.com
		 */
		String regex = "\\b(\\S+)+@(\\S+.\\S+)";
		Matcher matcher = Pattern.compile(regex).matcher(email);
		if (matcher.find()) {
			String id = matcher.group(1); // 마스킹 처리할 부분인 userId
			/*
			 * userId의 길이를 기준으로 세글자 초과인 경우 뒤 세자리를 마스킹 처리하고, 세글자인 경우 뒤 두글자만 마스킹, 세글자 미만인 경우 모두 마스킹 처리
			 */
			int length = id.length();
			if (length < 3) {
				char[] c = new char[length];
				Arrays.fill(c, '*');
				return email.replace(id, String.valueOf(c));
			} else if (length == 3) {
				return email.replaceAll("\\b(\\S+)[^@][^@]+@(\\S+)", "$1**@$2");
			} else {
				return email.replaceAll("\\b(\\S+)[^@][^@][^@]+@(\\S+)", "$1***@$2");
			}
		}
		return email;

	}

	/**
	 * 이름 마스킹 처리
	 * 
	 * @param orgName
	 * @return
	 */
	public static String getNameMasking(String orgName) {
		if (orgName == null || "".equals(orgName.trim())) {
			return null;
		}
		orgName = orgName.trim();
		String maskinName = "";
		// null 또는 빈값 체크
		if (orgName != null && !"".equals(orgName)) {
			// 이름 가운데 글자 마스킹
			String middleMask = "";
			// 이름이 외자 또는 4자 이상인 경우 분기
			if (orgName.length() > 2) {
				middleMask = orgName.substring(1, orgName.length() - 1);
			} else {
				middleMask = orgName.substring(1, orgName.length());
			}
			// 마스킹 변수 선언(*)
			String masking = "";
			// 가운데 글자 마스킹 하기위한 증감값
			for (int i = 0; i < middleMask.length(); i++) {
				masking += "*";
			}
			// 선언 방식(20211208 수정)
			// 1. 각각 분기
			// 이름이 외자 또는 4자 이상인 경우 분기
			if (orgName.length() > 2) {
				maskinName = orgName.substring(0, 1) + middleMask.replace(middleMask, masking) + orgName.substring(orgName.length() - 1, orgName.length());
			} else {
				maskinName = orgName.substring(0, 1) + middleMask.replace(middleMask, masking);
			}

		}

		return maskinName;
	}

	/**
	 * 계좌번호 마스팅 처리
	 * 
	 * @param orgAccountNum
	 * @return
	 */
	public static String getAccountNumMasking(String orgAccountNum) {
		String maskingAccountNum = "";
		if (orgAccountNum == null || "".equals(orgAccountNum)) {
			return null;
		}
		if (orgAccountNum.length() < 10) {
			return null;
		}
		if (orgAccountNum != null && !"".equals(orgAccountNum)) {
			// 계좌번호 가운데 글자 마스킹 변수 선언
			String middleMask = orgAccountNum.substring(3, orgAccountNum.length() - 3);
			// 마스킹 변수 선언(*)
			String masking = "";
			// 앞 3자리, 맨뒤 3자리를 빼고 모두 마스킹 하기위한 증감값
			for (int i = 0; i < middleMask.length(); i++) {
				masking += "*";
			}
			maskingAccountNum = orgAccountNum.substring(0, 3) + orgAccountNum.replace(middleMask, masking)
					+ orgAccountNum.substring(orgAccountNum.length() - 3, orgAccountNum.length());

		}
		return maskingAccountNum;
	}

	/**
	 * 주어진 숫자를 파일 형식으로 전달
	 * 
	 * @param size
	 * @return
	 */
	public static String fileSizeView(long filesize) {
		String[] suffix = { "B", "KB", "MB", "GB", "TB", "PB", "EB" };
		int unit = 1024;
		if (filesize < unit) {
			return filesize + " " + suffix[0];
		}
		int exp = (int)Math.floor(Math.log(filesize) / Math.log(unit));
		return (Math.floor(filesize / Math.pow(unit, exp) * 10) / 10) + " " + suffix[exp];
	}

	/**
	 * null 이면 "" 으로 리턴한다.
	 * 
	 * @param request
	 * @param parameterName
	 * @return
	 */
	public static String getParameter(HttpServletRequest request, String parameterName) {
		return request.getParameter(parameterName) == null ? "" : (String)request.getParameter(parameterName);
	}

	/**
	 * null 이면 "" 으로 리턴한다.
	 * 
	 * @param request
	 * @param parameterName
	 * @return
	 */
	public static String getAttribute(HttpServletRequest request, String parameterName) {
		return request.getAttribute(parameterName) == null ? "" : (String)request.getAttribute(parameterName);
	}

	/**
	 * 주어진 문자열을 암호화.
	 * 
	 * @param String str
	 * @param String encryptKey
	 * @return String
	 * @throws Exception
	 * @throws InvalidKeyException
	 */
	public static String encryptString(String str, String encryptKey) throws Exception {
		if (str == null || str.length() == 0) {
			return "";
		}

		Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
		SecretKeySpec keySpec = new SecretKeySpec(encryptKey.getBytes(), "DESede");
		cipher.init(Cipher.ENCRYPT_MODE, keySpec);

		byte[] plainText = str.getBytes("UTF-8");
		byte[] cipherText = cipher.doFinal(plainText);
		return encodeBase64Escape(cipherText);
	}

	/**
	 * 주어진 문자열을 복호화.
	 * 
	 * @param String str
	 * @param String decryptKey
	 * @return String
	 * @throws Exception
	 * @throws InvalidKeyException
	 */
	public static String decryptString(String str, String decryptKey) throws Exception {
		if (str == null || str.length() == 0) {
			return "";
		}
		Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
		SecretKeySpec keySpec = new SecretKeySpec(decryptKey.getBytes(), "DESede");
		cipher.init(Cipher.DECRYPT_MODE, keySpec);

		byte[] cipherText = cipher.doFinal(decodeBase64Escape(str));
		return new String(cipherText, "UTF-8");
	}

	/**
	 * encodeBase64Escape된 문자열을 decoding 한다.
	 * 
	 * @param str
	 * @return
	 * @throws IOException
	 */
	public static byte[] decodeBase64Escape(String str) throws IOException {
		return Base64.decodeBase64(str.replaceAll("@", "=").replaceAll("!", "/").replaceAll(" ", "+")); // 파라미터가 get 방식으로 넘어왔을 경우 + 가 space로 인식되기 때문에.
	}

	/**
	 * base64 encoding을 하면 마지막에 = 이 padding 된다. = 은 parameter에 이용되면 정상동작하지 않으므로 @으로 치환하여 사용한다. '/' 는 url에 포함되면 안되기 때문에 !로 치환하여 사용한다.
	 * 
	 * @param bytes
	 * @return
	 * @throws IOException
	 */
	public static String encodeBase64Escape(byte[] bytes) throws IOException {
		String enc = new String(Base64.encodeBase64(bytes));
		return enc.replaceAll("=", "@").replaceAll("/", "!");
	}

	/**
	 * null 이면 "" 으로 리턴한다.
	 * 
	 * @param request
	 * @param parameterName
	 * @return
	 */
	public static String getRemoteAddr(HttpServletRequest request) {
		String ip;
		ip = request.getHeader("X-Forwarded-For");
		if (ip == null) {
			try {
				ip = Inet4Address.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				ip = request.getRemoteAddr();
			}
		}

		return ip;
	}
	
	
	/**
	 * Checks if is empty.
	 * 
	 * @param s
	 * @return boolean
	 */
	public static boolean isEmpty(String s) {
		return s == null || s.length() == 0;
	}

	/**
	 * Checks if is empty.
	 * 
	 * @param s
	 * @return boolean
	 */
	public static boolean isEmpty(Long l) {
		return l == null || l.equals(0L);
	}

	/**
	 * Checks if is empty.
	 * 
	 * @param s
	 * @return boolean
	 */
	public static boolean isEmpty(Double d) {
		return d == null || d.equals(0D);
	}
}
