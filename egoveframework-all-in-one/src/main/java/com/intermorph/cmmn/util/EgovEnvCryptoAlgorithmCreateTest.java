/*
 * 
 */
package com.intermorph.cmmn.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.cryptography.EgovPasswordEncoder;
/**
 * 
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.util
 * @File    : EgovEnvCryptoAlgorithmCreateTest.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 2. 8
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
public class EgovEnvCryptoAlgorithmCreateTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovEnvCryptoAlgorithmCreateTest.class);
	 
	//계정암호화키 키
	public String algorithmKey = "aof51004!S";
 
	//계정암호화 알고리즘(MD5, SHA-1, SHA-256)
	public String algorithm = "SHA-256";
 
	//계정암호화키 블럭사이즈
	public int algorithmBlockSize = 1024;
 
	public static void main(String[] args) {
		EgovEnvCryptoAlgorithmCreateTest cryptoTest = new EgovEnvCryptoAlgorithmCreateTest();
 
		EgovPasswordEncoder egovPasswordEncoder = new EgovPasswordEncoder();
		egovPasswordEncoder.setAlgorithm(cryptoTest.algorithm);
 
		System.out.println("------------------------------------------------------");
		System.out.println("알고리즘(algorithm) : "+cryptoTest.algorithm);
		System.out.println("알고리즘 키(algorithmKey) : "+cryptoTest.algorithmKey);
		System.out.println("알고리즘 키 Hash(algorithmKeyHash) : "+egovPasswordEncoder.encryptPassword(cryptoTest.algorithmKey));
		System.out.println("알고리즘 블럭사이즈(algorithmBlockSize)  :"+cryptoTest.algorithmBlockSize);
 
	}
}
