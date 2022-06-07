/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipException;

import javax.imageio.ImageIO;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import com.intermorph.cmmn.exception.IMProcErrors;
import com.intermorph.cmmn.exception.IMProcException;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.util
 * @File    : IMFileUtil.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 2. 21
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
public class IMFileUtil extends FileUtils {
	/**
	 * 주어진 경로의 디렉토리 및 파일 목록을 리턴.
	 * 
	 * @param path
	 * @return ArrayList
	 */
	public static List<File> list(String path) {
		File[] listFiles = new File(path).listFiles();
		if (listFiles == null) {
			return null;
		} else {
			List<File> list = new ArrayList<File>();
			for (File f : listFiles) {
				list.add(f);
			}
			return list;
		}
	}

	/**
	 * 주어진 경로의 디렉토리 목록을 리턴.
	 * 
	 * @param path
	 * @return ArrayList
	 */
	public static List<File> listDirectory(String path) {
		File[] listFiles = new File(path).listFiles();
		if (listFiles == null) {
			return null;
		} else {
			List<File> list = new ArrayList<File>();
			for (File f : listFiles) {
				if (f.isDirectory()) {
					list.add(f);
				}
			}
			return list;
		}
	}

	/**
	 * 주어진 경로의 모든 하위 디렉토리 목록을 리턴.
	 * 
	 * @param path
	 * @return ArrayList
	 */
	public static List<File> listAllSubDirectory(String path) {
		File[] listFiles = new File(path).listFiles();
		if (listFiles == null) {
			return null;
		} else {
			List<File> list = new ArrayList<File>();
			for (File f : listFiles) {
				if (f.isDirectory()) {
					list.add(f);
					list.addAll(listAllSubDirectory(f.getAbsolutePath()));
				}
			}
			return list;
		}
	}

	/**
	 * 주어진 경로의 파일 목록을 리턴.
	 * 
	 * @param path
	 * @return ArrayList
	 */
	public static List<File> listFiles(String path) {
		File[] listFiles = new File(path).listFiles();
		if (listFiles == null) {
			return null;
		} else {
			List<File> list = new ArrayList<File>();
			for (File f : listFiles) {
				if (f.isFile()) {
					list.add(f);
				}
			}
			return list;
		}
	}

	/**
	 * 주어진 경로의 모든 하위 파일 목록을 리턴.
	 * 
	 * @param path
	 * @return ArrayList
	 */
	public static List<File> listAllSubFiles(String path) {
		File[] listFiles = new File(path).listFiles();
		if (listFiles == null) {
			return null;
		} else {
			List<File> list = new ArrayList<File>();
			for (File f : listFiles) {
				if (f.isDirectory()) {
					list.addAll(listAllSubFiles(f.getAbsolutePath()));
				} else if (f.isFile()) {
					list.add(f);
				}
			}
			return list;
		}
	}

	/**
	 * 주어진 경로의 모든 하위 파일/디렉토리 목록을 리턴.
	 * 
	 * @param path
	 * @return ArrayList
	 */
	public static List<File> getAll(String path) {

		File[] listFiles = new File(path).listFiles();
		if (listFiles == null) {
			return null;
		} else {
			List<File> list = new ArrayList<File>();
			for (int i = 0; i < listFiles.length; i++) {
				list.add(listFiles[i]);
				if (listFiles[i].isDirectory()) {
					list.addAll(getAll(listFiles[i].getAbsolutePath()));
				}
			}
			return list;
		}
	}

	/**
	 * 파일복사
	 * 
	 * @param sourceFile
	 * @param targetFile
	 * @throws Exception
	 */
	public static void copy(String sourceFile, String targetFile) throws Exception {
		FileInputStream fis = new FileInputStream(sourceFile);
		FileOutputStream fos = new FileOutputStream(targetFile);
		FileChannel fci = fis.getChannel();
		FileChannel fco = fos.getChannel();
		try {
			fci.transferTo(0, fci.size(), fco);
		} catch (Exception e) {
			throw new IMProcException(IMProcErrors.파일프로세스에러);
		} finally {
			if (fci != null) {
				fci.close();
			}
			if (fco != null) {
				fco.close();
			}
			if (fis != null) {
				fis.close();
			}
			if (fos != null) {
				fos.close();
			}
		}
	}

	/**
	 * 파일이동
	 * 
	 * @param source
	 * @param target
	 * @throws Exception
	 */
	public static void move(String source, String target) throws Exception {
		File targetDir = new File(new File(target).getParent());
		if (!targetDir.exists()) {
			targetDir.mkdirs();
		}
		File sourceFile = new File(source);
		if (sourceFile.exists() && sourceFile.isFile()) {
			copy(source, target);
			sourceFile.delete();
		}
	}

	/**
	 * 파일삭제
	 * 
	 * @param filePath
	 * @throws Exception
	 */
	public static void delete(String filePath) throws Exception {
		File sourceFile = new File(filePath);
		if (sourceFile.exists() && sourceFile.isFile()) {
			sourceFile.delete();
		}
	}

	/**
	 * 주어진 경로에서 wildcard에 해당되는 파일을 삭제한다. (예)*.jpg, icon.* 등
	 * 
	 * @param path
	 * @param filter
	 */
	public static void delete(String path, String wildcard) {
		File dir = new File(path);
		if (dir.exists()) {
			FileFilter fileFilter = new WildcardFileFilter(wildcard);
			File[] files = dir.listFiles(fileFilter);
			if (files != null) {
				for (File f : files) {
					f.delete();
				}
			}
		}
	}

	/**
	 * 폴더삭제
	 * 
	 * @param targetFolder
	 * @return
	 */
	public static boolean deleteDirectory(String directory) {
		File dir = new File(directory);
		File[] files = dir.listFiles();
		int size = files.length;

		if (size > 0) {
			for (int i = 0; i < size; i++) {
				if (files[i].isFile()) {
					files[i].delete();
				} else {
					deleteDirectory(files[i].getAbsolutePath());
				}
			}
		}
		dir.delete();

		return !dir.exists();
	}

	/**
	 * 주어진 경로에 파일을 생성한다.(경로가 존재하지 않으면 생성한다)
	 * 
	 * @param pathname
	 * @param filename
	 * @return File
	 */
	public static File createFile(String path, String filename) {
		File dir = new File(path);
		if (!dir.exists() || !dir.isDirectory()) {
			dir.mkdirs();
		}
		return new File(dir + File.separator + filename);
	}

	/**
	 * 이미지 크기 int[0] : width, int[1] : height
	 * 
	 * @param sourceFile
	 * @return
	 * @throws Exception
	 */
	public static int[] imageSize(String sourceFile) throws Exception {
		int[] size = { 0, 0 };
		File source = new File(sourceFile);
		BufferedImage sourceImage = ImageIO.read(source);
		size[0] = sourceImage.getWidth();
		size[1] = sourceImage.getHeight();
		return size;
	}
	
	/**
	 * 
	 * @param sourceFile
	 * @param targetFile
	 * @param width
	 * @param height
	 * @param sourceDelete
	 * @return
	 * @throws Exception
	 */
	public static File convertJpg(String sourceFile) throws Exception {
		File source = new File(sourceFile);
		if (!source.isFile() || source.length() == 0) {
			return null;
		}
		File afterFile = new File(sourceFile+".jpg");

		BufferedImage beforeImg = ImageIO.read(source); 
		BufferedImage afterImg = new BufferedImage(beforeImg.getWidth(), beforeImg.getHeight(), BufferedImage.TYPE_INT_RGB); 
		afterImg.createGraphics().drawImage(beforeImg, 0, 0, Color.white, null); 
		ImageIO.write(afterImg, "jpg", afterFile);
		return afterFile;
	}

	/**
	 * width > 0 && height > 0 : 가로/세로 크기 기준으로 비율유지 리사이즈, width > 0 : 가로크기 기준으로 비율유지 리사이즈, height > 0 : 세로크기 기준으로 비율유지 리사이즈
	 * 
	 * @param sourceFile
	 * @param width
	 * @param height
	 * @param sourceDelete
	 * @return File
	 * @throws Exception
	 */
	public static File resizeImageJpg(String sourceFile, String targetFile, int width, int height, boolean sourceDelete) throws Exception {
		File source = new File(sourceFile);
		if (!source.isFile() || source.length() == 0) {
			return null;
		}

		BufferedImage sourceImage = ImageIO.read(source);

		int resizeWidth = 0;
		int resizeHeight = 0;
		double resizeRatio = 1.0;

		if (width > 0 && height > 0) { // 가로/세로 크기 기준으로 비율유지 리사이즈
			double sourceRatio = (double)sourceImage.getWidth() / sourceImage.getHeight();
			double targetRatio = (double)width / height;

			if (targetRatio > 1) { // 가로형으로 만들기
				if (sourceRatio < targetRatio) { // 타겟이 더 가로형일 때.
					resizeRatio = (double)height / sourceImage.getHeight(); // 세로에 맞춘다.(세로의 비율)
					if (resizeRatio > 1) { // 타겟의 세로가 더 클 때 원본 유지.
						resizeRatio = 1.0;
					}

				} else { // 원본이 더 가로형일 때.
					resizeRatio = (double)width / sourceImage.getWidth(); // 가로에 맞춘다.(가로의 비율)
					if (resizeRatio > 1) { // 타겟의 가로가 더 클 때 원본 유지.
						resizeRatio = 1.0;
					}
				}
			} else { // 세로형으로 만들기.
				if (sourceRatio > targetRatio) { // 타겟이 더 세로형일 때.
					resizeRatio = (double)width / sourceImage.getWidth(); // 가로에 맞춘다.(가로의 비율)
					if (resizeRatio > 1) { // 타겟의 가로가 더 클 때 원본 유지.
						resizeRatio = 1.0;
					}

				} else { // 원본이 더 세로형일 때.
					resizeRatio = (double)height / sourceImage.getHeight(); // 세로에 맞춘다.(세로의 비율)
					if (resizeRatio > 1) { // 타겟의 세로가 더 클 때 원본 유지.
						resizeRatio = 1.0;
					}
				}
			}

		} else if (width > 0) { // 가로크기 기준으로(가로만 맞춤) 비율유지 리사이즈
			if (sourceImage.getWidth() > width) {
				resizeRatio = (double)width / sourceImage.getWidth(); // 가로에 맞춘다.(가로의 비율)
			} else { // 타겟의 가로가 더 클 때 원본 유지.
				resizeRatio = 1.0;
			}
		} else if (height > 0) { // 세로크기 기준으로(세로만 맞춤) 비율유지 리사이즈
			if (sourceImage.getHeight() > height) {
				resizeRatio = (double)height / sourceImage.getHeight(); // 세로에 맞춘다.(세로의 비율)
			} else { // 타겟의 세로가 더 클 때 원본 유지.
				resizeRatio = 1.0;
			}
		}
		resizeWidth = (int)(sourceImage.getWidth() * resizeRatio);
		resizeHeight = (int)(sourceImage.getHeight() * resizeRatio);

		if (resizeWidth == sourceImage.getWidth() && resizeHeight == sourceImage.getHeight()) {
			// 원본 복사
			copy(sourceFile, targetFile);
			if (sourceDelete) {
				source.delete();
			}
			return new File(targetFile);
		} else {
			File target = new File(targetFile);

			int type = sourceImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : sourceImage.getType();

			BufferedImage destImage = new BufferedImage(resizeWidth, resizeHeight, type);

			Graphics2D graphics = destImage.createGraphics();
			graphics.setComposite(AlphaComposite.Src);
			Image scaleImage = sourceImage.getScaledInstance(resizeWidth, resizeHeight, BufferedImage.SCALE_SMOOTH);
			graphics.drawImage(scaleImage, 0, 0, null);
			graphics.dispose();

			ImageIO.write(destImage, "jpg", target);

			if (sourceDelete) {
				source.delete();
			}
			return target;
		}
	}

	/**
	 * 비율에 맞게 축소 리사이징을 하고, 이미지의 중앙을 기준으로 주어진 크기만큼 crop한다.
	 * 
	 * @param sourceFile
	 * @param targetFile
	 * @param width
	 * @param height
	 * @param vertical (left, center[default], right)
	 * @param horizontal (top, center[default], bottom)
	 * @param sourceDelete
	 * @return File
	 * @throws Exception
	 */
	public static File cropImageJpg(String sourceFile, String targetFile, int width, int height, String vertical, String horizontal, boolean sourceDelete)
			throws Exception {
		File source = new File(sourceFile);
		if (!source.isFile() || source.length() == 0) {
			return null;
		}
		BufferedImage sourceImage = ImageIO.read(source);

		double resizeRatio = 1.0;
		double wRatio = (double)width / sourceImage.getWidth();
		double hRatio = (double)height / sourceImage.getHeight();

		if (wRatio > 1 || hRatio > 1) { // 원본이미지가 crop되는 이미지보다 작을때.
			resizeRatio = 1.0;
		} else {
			resizeRatio = wRatio > hRatio ? wRatio : hRatio; // 비율이 더 큰 것으로.
		}
		int resizeWidth = (int)(sourceImage.getWidth() * resizeRatio);
		int resizeHeight = (int)(sourceImage.getHeight() * resizeRatio);

		int type = sourceImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : sourceImage.getType();
		BufferedImage destImage = new BufferedImage(Math.min(resizeWidth, width), Math.min(resizeHeight, height), type); // 더 작은 크기로

		File target = new File(targetFile);
		Graphics2D graphics = destImage.createGraphics();
		graphics.setComposite(AlphaComposite.Src);
		Image scaleImage = sourceImage.getScaledInstance(resizeWidth, resizeHeight, BufferedImage.SCALE_SMOOTH);

		int posX = horizontal.equalsIgnoreCase("center") ? (resizeWidth - width) / 2 : horizontal.equalsIgnoreCase("right") ? resizeWidth - width : 0;
		int posY = vertical.equalsIgnoreCase("center") ? (resizeHeight - height) / 2 : vertical.equalsIgnoreCase("bottom") ? resizeHeight - height : 0;
		posX = Math.max(posX, 0); // 0 보다는 커야한다.
		posY = Math.max(posY, 0); // 0 보다는 커야한다.

		graphics.drawImage(scaleImage, 0, 0, width, height, posX, posY, posX + width, posY + height, null);
		graphics.dispose();

		ImageIO.write(destImage, "jpg", target);

		if (sourceDelete) {
			source.delete();
		}
		return target;
	}

	/**
	 * 압축풀기
	 * 
	 * @param zipFileName
	 * @param extractDir
	 * @return
	 * @throws Exception
	 */
	public static String unzip(String zipFileName, String extractDir) throws Exception {

		String encodeing = getCharset(zipFileName);
		if (encodeing==null || "".equals(encodeing.trim())) {
			encodeing = "UTF-8";
		}
		return unzip(new File(zipFileName), extractDir, encodeing);
	}

	/**
	 * 압축풀기
	 * 
	 * @param zipFileName
	 * @param extractDir
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
	public static String unzip(String zipFileName, String extractDir, String encoding) {

		return unzip(new File(zipFileName), extractDir, encoding);
	}

	/**
	 * 압축풀기
	 * 
	 * @param zipFile
	 * @param extractDir
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
	public static String unzip(File zipFile, String extractDir, String encoding) {
		boolean result = true;
		String entryFileName = "";
		String unzipFileName = "";

		InputStream in = null;
		OutputStream out = null;

		byte[] buffer = new byte[16384];
		try {
			ZipFile archive;
			try {
				archive = new ZipFile(zipFile, encoding);
			} catch (ZipException ZE) {
				archive = new ZipFile(zipFile);
			}

			for (Enumeration<?> e = archive.getEntries(); e.hasMoreElements();) {
				ZipEntry entry = (ZipEntry)e.nextElement();

				if (!entry.isDirectory()) {
					entryFileName = entry.getName();
					entryFileName = entryFileName.replace('/', File.separatorChar);
					unzipFileName = extractDir + entryFileName;

					File unzipFile = new File(unzipFileName);

					String parent = unzipFile.getParent();
					if (parent != null) {
						File parentFile = new File(parent);
						if (!parentFile.exists()) {
							parentFile.mkdirs();
						}
					}

					in = archive.getInputStream(entry);
					out = new FileOutputStream(unzipFileName);

					int count;
					while ((count = in.read(buffer)) != -1) {
						out.write(buffer, 0, count);
					}
					in.close();
					out.close();
				}
			}
			archive.close();
		} catch (Exception e) {
			result = false;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					result = false;
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
					result = false;
				}
			}
		}

		return result ? extractDir : "";
	}

	/**
	 * zip 으로 압축하기
	 * 
	 * @param zipFilePath
	 * @param addFilePaths
	 * @param encoding
	 * @return zipFile
	 * @throws Exception
	 */
	public static File zip(String zipFilePath, List<String> addFilePaths, String encoding) throws Exception {
		File zipFile = new File(zipFilePath);
		if (!zipFile.exists()) {
			zipFile.createNewFile();
		}

		ZipArchiveOutputStream zos = new ZipArchiveOutputStream(new FileOutputStream(zipFile));
		zos.setEncoding(encoding);

		int length = 0;
		byte[] buffer = new byte[8 * 1024];
		ZipArchiveEntry entry;
		for (String path : addFilePaths) {
			File src = new File(path);
			if (src.isDirectory()) {
				List<File> files = getAll(src.getAbsolutePath());
				for (File file : files) {
					StringBuffer name = new StringBuffer();
					name.append(file.getParent().substring(src.getParent().length())).append(File.separator).append(file.getName());
					if (file.isDirectory()) {
						name.append(File.separator);
					}
					entry = new ZipArchiveEntry(name.toString());
					zos.putArchiveEntry(entry);
					if (file.isFile()) {
						FileInputStream fis = new FileInputStream(file);
						while ((length = fis.read(buffer, 0, buffer.length)) >= 0) {
							zos.write(buffer, 0, length);
						}
						fis.close();
					}
					zos.closeArchiveEntry();
				}
			} else {
				entry = new ZipArchiveEntry(src.getName());
				zos.putArchiveEntry(entry);
				FileInputStream fis = new FileInputStream(src);
				while ((length = fis.read(buffer, 0, buffer.length)) >= 0) {
					zos.write(buffer, 0, length);
				}
				fis.close();
				zos.closeArchiveEntry();

			}
		}
		zos.close();
		return zipFile;
	}

	/**
	 * 텍스트 파일내용 읽기
	 * 
	 * @param filename
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
	public static String read(String filename, String encoding) throws Exception {
		StringBuffer content = new StringBuffer();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), encoding));
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			content.append(line).append("\r\n");
		}
		bufferedReader.close();
		return content.toString();
	}

	/**
	 * 파일내용 쓰기
	 * 
	 * @param filename
	 * @param content
	 * @param encoding
	 * @throws Exception
	 */
	public static void write(String filename, String content, String encoding) throws Exception {
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), encoding));
		bufferedWriter.write(content);
		bufferedWriter.close();
	}

	/**
	 * 파일의 charset 을 읽는다.
	 * 
	 * @param filename
	 * @return
	 * @throws Exception
	 */
	public static String getCharset(String filename) throws Exception {
		String[] charsets = { "UTF-8", "EUC-KR", "CP437", "KSC-5601", "ISO-8859-1" };

		String matched = "";
		for (String charsetName : charsets) {
			Charset charset = Charset.forName(charsetName);
			CharsetDecoder decoder = charset.newDecoder();
			decoder.reset();
			byte[] buffer = new byte[10240];
			boolean identified = false;
			BufferedInputStream input = new BufferedInputStream(new FileInputStream(filename));
			if (input.read(buffer) != -1) {
				try {
					decoder.decode(ByteBuffer.wrap(buffer));
					identified = true;
				} catch (CharacterCodingException e) {
					identified = false;
				}
			}
			input.close();
			if (identified) {
				matched = charsetName;
				break;
			}
		}
		return matched;
	}

	/**
	 * 파일목록을 날짜순으로 정렬하기
	 * 
	 * @param files
	 */
	public static void sortByDate(List<File> files) {
		class CompareFileByDate implements Comparator<Object> {
			public int compare(Object object1, Object object2) {
				File file1 = (File)object1;
				File file2 = (File)object2;

				if (file1.lastModified() > file2.lastModified()) {
					return 1;
				} else if (file1.lastModified() < file2.lastModified()) {
					return -1;
				} else {
					return 0;
				}
			}
		}
		Collections.sort(files, new CompareFileByDate());
	}

	/**
	 * 파일목록을 이름순으로 정렬하기
	 * 
	 * @param files
	 */
	public static void sortByName(List<File> files) {
		class CompareFileByName implements Comparator<Object> {
			public int compare(Object object1, Object object2) {
				File file1 = (File)object1;
				File file2 = (File)object2;

				return file1.getName().toLowerCase(Locale.getDefault()).compareTo(file2.getName().toLowerCase(Locale.getDefault()));
			}
		}
		Collections.sort(files, new CompareFileByName());
	}

	
}
