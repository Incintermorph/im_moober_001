/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.util;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.intermorph.cmmn.IMGlobals;
import com.intermorph.cmmn.service.IMFileVO;
import com.penta.scpdb.ScpDbAgent;
import com.penta.scpdb.ScpDbAgentException;

import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.util
 * @File : IMFileMngUtil.java
 * @Title : {간단한 프로그램의 명칭을 기록}
 * @date : 2022. 2. 22
 * @author : 노성용
 * @descrption : {상세한 프로그램의 용도를 기록}
 */

@Component ("IMFileMngUtil")
public class IMFileMngUtil extends EgovFileMngUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovFileMngUtil.class);

	public static final int BUFF_SIZE = 2048;

	@Resource (name = "egovFileIdGnrService")
	private EgovIdGnrService idgenService;

	/**
	 * 첨부파일에 대한 목록 정보를 취득한다.
	 *
	 * @param files
	 * @return
	 * @throws Exception
	 */
	public List<IMFileVO> parseFileInfForPath(Map<String, MultipartFile> files, String KeyStr, int fileKeyParam, String atchFileId, String storePath)
			throws Exception {
		int fileKey = fileKeyParam;

		String storePathString = "";
		String atchFileIdString = "";
		String todayDir = IMDateUtil.getImToday("yyyy/MM/dd") + File.separator;
		if ("".equals(storePath) || storePath == null) {
			storePathString = EgovProperties.getProperty("Globals.fileStorePath") + todayDir;
		} else {
			storePathString = EgovProperties.getProperty("Globals.fileStorePath") + storePath + File.separator + todayDir;
		}

		if ("".equals(atchFileId) || atchFileId == null) {
			atchFileIdString = idgenService.getNextStringId();
		} else {
			atchFileIdString = atchFileId;
		}

		File saveFolder = new File(EgovWebUtil.filePathBlackList(storePathString));

		if (!saveFolder.exists() || saveFolder.isFile()) {
			// 2017.03.03 조성원 시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
			if (saveFolder.mkdirs()) {
				LOGGER.debug("[file.mkdirs] saveFolder : Creation Success ");
			} else {
				LOGGER.error("[file.mkdirs] saveFolder : Creation Fail ");
			}
		}

		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		MultipartFile file;
		String filePath = "";

		List<IMFileVO> result = new ArrayList<IMFileVO>();
		IMFileVO fvo;

		while (itr.hasNext()) {
			Entry<String, MultipartFile> entry = itr.next();

			file = entry.getValue();
			String orginFileName = file.getOriginalFilename();

			// --------------------------------------
			// 원 파일명이 없는 경우 처리
			// (첨부가 되지 않은 input file type)
			// --------------------------------------
			if ("".equals(orginFileName)) {
				continue;
			}
			//// ------------------------------------

			int index = orginFileName.lastIndexOf(".");
			// String fileName = orginFileName.substring(0, index);
			String fileExt = orginFileName.substring(index + 1);
			String newName = KeyStr + getImTimeStamp() + fileKey;
			long size = file.getSize();

			if (!"".equals(orginFileName)) {
				filePath = storePathString + File.separator + newName;
				file.transferTo(new File(EgovWebUtil.filePathBlackList(filePath)));
			}

			fvo = new IMFileVO();
			fvo.setContentType(file.getContentType());
			fvo.setFileExtsn(fileExt);
			fvo.setFileStreCours(storePathString);
			fvo.setFileMg(Long.toString(size));
			fvo.setOrignlFileNm(orginFileName);
			fvo.setStreFileNm(newName);
			fvo.setAtchFileId(atchFileIdString);
			fvo.setFileSn(String.valueOf(fileKey));

			result.add(fvo);

			fileKey++;
		}

		return result;
	}

	/**
	 * 첨부파일에 대한 목록 정보를 취득한다.해당 파일을 암호화 한다.
	 *
	 * @param files
	 * @return
	 * @throws Exception
	 */
	public List<IMFileVO> parseFileInfForPathEnc(Map<String, MultipartFile> files, String KeyStr, int fileKeyParam, String atchFileId, String storePath)
			throws Exception {
		int fileKey = fileKeyParam;

		String storePathString = "";
		String atchFileIdString = "";
		String todayDir = IMDateUtil.getImToday("yyyy/MM/dd") + File.separator;
		if ("".equals(storePath) || storePath == null) {
			storePathString = EgovProperties.getProperty("Globals.fileStorePath") + todayDir;
		} else {
			storePathString = EgovProperties.getProperty("Globals.fileStorePath") + storePath + File.separator + todayDir;
		}

		if ("".equals(atchFileId) || atchFileId == null) {
			atchFileIdString = idgenService.getNextStringId();
		} else {
			atchFileIdString = atchFileId;
		}

		File saveFolder = new File(EgovWebUtil.filePathBlackList(storePathString));

		if (!saveFolder.exists() || saveFolder.isFile()) {
			// 2017.03.03 조성원 시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
			if (saveFolder.mkdirs()) {
				LOGGER.debug("[file.mkdirs] saveFolder : Creation Success ");
			} else {
				LOGGER.error("[file.mkdirs] saveFolder : Creation Fail ");
			}
		}

		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		MultipartFile file;
		String filePath = "";

		List<IMFileVO> result = new ArrayList<IMFileVO>();
		IMFileVO fvo;

		while (itr.hasNext()) {
			Entry<String, MultipartFile> entry = itr.next();

			file = entry.getValue();
			String orginFileName = file.getOriginalFilename();

			// --------------------------------------
			// 원 파일명이 없는 경우 처리
			// (첨부가 되지 않은 input file type)
			// --------------------------------------
			if ("".equals(orginFileName)) {
				continue;
			}
			//// ------------------------------------

			int index = orginFileName.lastIndexOf(".");
			// String fileName = orginFileName.substring(0, index);
			String fileExt = orginFileName.substring(index + 1);
			String newName = KeyStr + getImTimeStamp() + fileKey;
			long size = file.getSize();

			if (!"".equals(orginFileName)) {
				filePath = storePathString + File.separator + newName;
				//System.out.println("IMGlobals.DAMO_KEYPATH : " + IMGlobals.DAMO_KEYPATH);
				if (!"none".equals(IMGlobals.DAMO_KEYPATH)) {
					String iniFilePath = IMGlobals.DAMO_KEYPATH;

					String orgfilePath = filePath + ".org."+newName;
				//	System.out.println("orgfilePath : " + orgfilePath);
					
					

					try {
						ScpDbAgent agt = new ScpDbAgent();
						file.transferTo(new File(EgovWebUtil.filePathBlackList(orgfilePath)));
						
						int ret;
						ret = agt.ScpEncFile(iniFilePath, "KEY1", orgfilePath, filePath);

					//	System.out.println("orgfilePath : " + filePath);
					//	System.out.println("ret : " + ret);
						
						File orgfile =new File(filePath);
						size = orgfile.length();//암호화된 파일 사이즈
						LOGGER.debug("[java] ScpEncFile : " + ret);
					} catch (ScpDbAgentException e) {
						System.out.println(e.toString());
						System.out.println(e.returnedValue());
						
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}finally {
						File orgfile =new File(orgfilePath);
						if (orgfile != null && orgfile.exists()) {
							orgfile.delete();
						}
					}
				} else {
					file.transferTo(new File(EgovWebUtil.filePathBlackList(filePath)));
				}
			}

			fvo = new IMFileVO();
			fvo.setContentType(file.getContentType());
			fvo.setFileExtsn(fileExt);
			fvo.setFileStreCours(storePathString);
			fvo.setFileMg(Long.toString(size));
			fvo.setOrignlFileNm(orginFileName);
			fvo.setStreFileNm(newName);
			fvo.setAtchFileId(atchFileIdString);
			fvo.setFileSn(String.valueOf(fileKey));

			result.add(fvo);

			fileKey++;
		}

		return result;
	}

	/**
	 * 공통 컴포넌트 utl.fcc 패키지와 Dependency제거를 위해 내부 메서드로 추가 정의함 응용어플리케이션에서 고유값을 사용하기 위해 시스템에서17자리의TIMESTAMP값을 구하는 기능
	 *
	 * @param
	 * @return Timestamp 값
	 * @see
	 */
	private static String getImTimeStamp() {

		String rtnStr = null;

		// 문자열로 변환하기 위한 패턴 설정(년도-월-일 시:분:초:초(자정이후 초))
		String pattern = "yyyyMMddhhmmssSSS";

		SimpleDateFormat sdfCurrent = new SimpleDateFormat(pattern, Locale.KOREA);
		Timestamp ts = new Timestamp(System.currentTimeMillis());

		rtnStr = sdfCurrent.format(ts.getTime());

		return rtnStr;
	}
}
