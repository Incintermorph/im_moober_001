/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.util;


import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.util
 * @File    : ScrEncDecUtil.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 3. 11
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
public class ScrEncDecUtil {
	private static String salt = "keepgokrhiscoepago";

	public static String fn_encrypt(String strToEncrypt, String secret) {
	    try
	    {
	        byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	        IvParameterSpec ivspec = new IvParameterSpec(iv);

	        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
	       // System.out.println(salt);
	        KeySpec spec = new PBEKeySpec(secret.toCharArray(), salt.getBytes(), 65536, 256);
	        //System.out.println("rtest : " + salt.getBytes().toString());
	        SecretKey tmp = factory.generateSecret(spec);
	       /// System.out.println("secretKey 0: " + tmp.getEncoded());
	        SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        //System.out.println("secretKey1 : " + secretKey);
	        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);

	        //System.out.println("secretKey2 : " + secretKey);
	        //System.out.println("fn_encrypt: " + Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8"))) );
	        
	     
	        
	        return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
	    }
	    catch (Exception e)
	    {
	        System.out.println("Error while encrypting: " + e.toString());
	    }
	    return null;
	}

	public static String fn_encrypt_url(String strToEncrypt, String secret) {
	    try
	    {
	    	byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	        IvParameterSpec ivspec = new IvParameterSpec(iv);

	        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
	        KeySpec spec = new PBEKeySpec(secret.toCharArray(), salt.getBytes(), 65536, 256);
	        SecretKey tmp = factory.generateSecret(spec);
	        SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
	        String  dd = Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));


	        //System.out.println("fn_encrypt_url: " + java.net.URLEncoder.encode(dd, "UTF-8").replace("%2F", "$2F") );
	        return java.net.URLEncoder.encode(dd, "UTF-8").replace("%2F", "$2F");
	    }
	    catch (Exception e)
	    {
	    	e.printStackTrace();
	        System.out.println("Error while encrypting: " + e.toString());
	    }
	    return null;
	}

	public static String fn_decrypt(String strToDecrypt, String secret) {
	    try
	    {
	        byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	        IvParameterSpec ivspec = new IvParameterSpec(iv);

	        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
	        KeySpec spec = new PBEKeySpec(secret.toCharArray(), salt.getBytes(), 65536, 256);
	        SecretKey tmp = factory.generateSecret(spec);
	        SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
	        
	       // System.out.println("fn_decrypt: " + new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt))) );
	        
	        return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
	    }
	    catch (Exception e) {

	        System.out.println("Error while decrypting: " + e.toString());
	    }
	    return null;
	}

	public static String fn_decrypt_url(String strToDecrypt, String secret) {

		try
	    {
			strToDecrypt = strToDecrypt.replace("$2F" , "/");

	        byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	        IvParameterSpec ivspec = new IvParameterSpec(iv);

	        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
	        KeySpec spec = new PBEKeySpec(secret.toCharArray(), salt.getBytes(), 65536, 256);
	        SecretKey tmp = factory.generateSecret(spec);
	        SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
	        String strDecryptedString = new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));

	        System.out.println("fn_decrypt_url: " + strDecryptedString );
	        
	        return strDecryptedString;
	    }
	    catch (Exception e) {

	    	e.printStackTrace();
	        System.out.println("Error while decrypting: " + e.toString());

	        return "";
	    }


	}

}
