/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.generator.service.impl;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.apache.xerces.parsers.DOMParser;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.intermorph.cmmn.generator.service.ColumnVO;
import com.intermorph.cmmn.generator.service.GeneratorService;
import com.intermorph.cmmn.generator.service.InputVO;
import com.intermorph.cmmn.generator.service.IterateVO;
import com.intermorph.cmmn.generator.service.TableMapper;
import com.intermorph.cmmn.generator.service.XmlVO;
import com.intermorph.cmmn.util.IMDateUtil;
import com.intermorph.cmmn.util.IMFileUtil;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.generator.service.impl
 * @File    : GeneratorServiceImpl.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 2. 21
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
@Service("GeneratorService")
public class GeneratorServiceImpl extends EgovAbstractServiceImpl  implements GeneratorService {

	@Resource(name = "TableMapper")
	private TableMapper tableMapper;

	

	/* (non-Javadoc)
	 * @see com.intermorph.cmmn.generator.service.GeneratorService#selectListTable(java.lang.String)
	 */
	@Override
	public List<String> selectListTable(@Param ("tableSchema") String tableSchema) throws Exception{
		return tableMapper.selectListTable(tableSchema);
	}
	
	/* (non-Javadoc)
	 * @see com.intermorph.cmmn.generator.service.GeneratorService#getListColumn(java.lang.String)
	 */
	@Override
	public List<ColumnVO> selectListColumn(String tableName) throws Exception {
		return tableMapper.selectListColumn(tableName);
	}

	/* (non-Javadoc)
	 * @see com.intermorph.cmmn.generator.service.GeneratorService#generate(java.lang.String, com.intermorph.cmmn.generator.service.InputVO)
	 */
	@Override
	public void generate(String xml, InputVO inputVO) throws Exception {
		
		XmlVO xmlVO = loadXml(xml);

		File targetDir = new File(inputVO.getGeneratePath() + "/" + inputVO.getTableName());
		if (!targetDir.exists()) {
			targetDir.mkdirs();
		}
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("project_name", inputVO.getProjectName());
		map.put("package_name", inputVO.getPackageName());
		map.put("component_name", inputVO.getComponentName());
		map.put("author_name", inputVO.getAuthorName());
		map.put("table_name", removePrefix(inputVO.getTableName().toLowerCase()));
		map.put("db_table_name", inputVO.getTableName().toLowerCase());
		map.put("create_date", IMDateUtil.getImToday("yyyy.MM.dd"));
		
		map.put("table_comment", inputVO.getTableComment());
		
		List<ColumnVO> columns = tableMapper.selectListColumn(inputVO.getTableName());
		
		for(ColumnVO column : columns) {
			if("PRI".equals(column.getKey())){
				column.setPrimaryKeyYn("Y");
			}
			column.setColumnName(column.getField());
			column.setDataType(column.getType());
			column.setColumnComment(column.getComment());
		}

		List<String> EXCEPT_COLUMNS = Arrays.asList("del_yn", "frst_reger_id", "frst_reger_ip", "frst_reg_dt", "last_mdfer_id", "last_mdfcn_dt",
				"last_mdfer_ip");

		if (xmlVO.getIterate() != null) {
			for (int x = 0; x < xmlVO.getIterate().size(); x++) {
				IterateVO iterateVO = xmlVO.getIterate().get(x);
				int tabSize = iterateVO.getAttrTabSize() == null || iterateVO.getAttrTabSize().equals("") ? 0 : Integer.parseInt(iterateVO.getAttrTabSize());
				int spaceSize = iterateVO.getAttrSpaceSize() == null || iterateVO.getAttrSpaceSize().equals("") ? 0
						: Integer.parseInt(iterateVO.getAttrSpaceSize());
				String join = iterateVO.getAttrJoin() == null || iterateVO.getAttrJoin().equals("") ? "" : iterateVO.getAttrJoin();

				boolean columnStarted = false;
				StringBuffer buffer = new StringBuffer();
				Map<String, String> columnMap = new HashMap<String, String>();
				for (int y = 0; y < columns.size(); y++) {
					ColumnVO column = columns.get(y);

					if (EXCEPT_COLUMNS.contains(column.getColumnName().toLowerCase()) && iterateVO.getAttrAudit().equalsIgnoreCase("false")) {
						continue;
					}
					if (iterateVO.getNodeName().startsWith("primary_key_") && !"Y".equals(column.getPrimaryKeyYn())) {
						continue;
					}
					String javaType = "String";
					String jdbcType = "VARCHAR";
					if (column.getDataType().equalsIgnoreCase("NUMBER") || column.getDataType().equalsIgnoreCase("INT")
							|| column.getDataType().equalsIgnoreCase("INTEGER")) {
						javaType = "Long";
						jdbcType = "NUMERIC";
						if (column.getNumericScale() != null && column.getNumericScale() > 0) {
							javaType = "Double";
						}
					}
					columnMap.put("java_type", javaType);
					columnMap.put("jdbc_type", jdbcType);
					columnMap.put("column_name", removePrefix(column.getColumnName().toLowerCase()));
					columnMap.put("db_column_name", column.getColumnName());
					columnMap.put("column_comment", column.getColumnComment());

					String iterValue = iterateVO.getNodeValue().trim();
					String spaces = "";
					for (int z = 0; z < tabSize; z++) {
						spaces += "\t";
					}
					for (int z = 0; z < spaceSize; z++) {
						spaces += " ";
					}
					iterValue = spaces + (columnStarted == true ? join : "") + iterValue;
					iterValue = iterValue.replaceAll("\n", "\n" + spaces);

					buffer.append(replaceData(iterValue + "\r\n", columnMap));

					columnStarted = true;
				}
				map.put("iterate_" + iterateVO.getNodeName(), buffer.toString());
			}

		}
		if (xmlVO.getSource() != null) {
			String source = replaceData(xmlVO.getSource().trim(), map);
			String filename = replaceData(xmlVO.getFilename(), map);
			IMFileUtil.write(targetDir + "/" + filename, source, "UTF-8");
		} else {
			System.out.println(" xmlVO.getSource() : " + xmlVO.getSource());
			System.out.println(" xml : " + xml);
			}
		
		
		
		

	}
	
	/**
	 * xml 템플릿 로드
	 * 
	 * @param xml
	 * @return XmlVO
	 * @throws Exception
	 */
	protected XmlVO loadXml(String xml) throws Exception {
		XmlVO xmlVO = new XmlVO();
		
		String PACKAGE = "com/intermorph/mapper/xml";

		URL url = getClass().getClassLoader().getResource(PACKAGE + "/" + xml);
		DOMParser parser = new DOMParser();
		parser.parse(url.getPath());
		Document doc = parser.getDocument();

		Node rootNode = getNode("generator", doc.getChildNodes());
		Node filenameNode = getNode("filename", rootNode.getChildNodes());
		Node sourceNode = getNode("source", rootNode.getChildNodes());
		Node iterateNode = getNode("iterate", rootNode.getChildNodes());

		// System.out.println("filenameNode=" + filenameNode.getTextContent());
		// System.out.println("sourceNode=" + sourceNode.getTextContent());
		xmlVO.setFilename(filenameNode.getTextContent()); // 파일명
		xmlVO.setSource(sourceNode.getTextContent()); // 소스

		if (iterateNode != null) {
			List<IterateVO> iterate = new ArrayList<IterateVO>();
			NodeList nodes = iterateNode.getChildNodes();
			for (int x = 0; x < nodes.getLength(); x++) {
				Node node = nodes.item(x);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					IterateVO iter = new IterateVO();
					iter.setNodeName(node.getNodeName());
					iter.setNodeValue(node.getTextContent());
					iter.setAttrJoin(getNodeAttr("join", node));
					iter.setAttrTabSize(getNodeAttr("tabsize", node));
					iter.setAttrSpaceSize(getNodeAttr("spacesize", node));
					iter.setAttrAudit(getNodeAttr("audit", node));
					iterate.add(iter);
				}

			}
			xmlVO.setIterate(iterate);
		}
		return xmlVO;
	}

	/**
	 * xml 노드
	 * 
	 * @param tagName
	 * @param nodes
	 * @return Node
	 */
	protected Node getNode(String tagName, NodeList nodes) {
		for (int x = 0; x < nodes.getLength(); x++) {
			Node node = nodes.item(x);
			if (node.getNodeName().equalsIgnoreCase(tagName)) {
				return node;
			}
		}
		return null;
	}

	/**
	 * xml 속성
	 * 
	 * @param attrName
	 * @param node
	 * @return String
	 */
	protected String getNodeAttr(String attrName, Node node) {
		NamedNodeMap attrs = node.getAttributes();
		for (int y = 0; y < attrs.getLength(); y++) {
			Node attr = attrs.item(y);
			if (attr.getNodeName().equalsIgnoreCase(attrName)) {
				return attr.getNodeValue();
			}
		}
		return "";
	}

	/**
	 * source 문자열에서 map의 문자열로 치환
	 * 
	 * @param source
	 * @param map
	 * @return String
	 */
	protected String replaceData(String source, Map<String, String> map) throws Exception {

		if (map != null) {
			for (Iterator<?> it = map.keySet().iterator(); it.hasNext();) {
				String key = (String)it.next();
				String values = (String)map.get(key);

				if (values != null) {
					source = source.replaceAll("<!" + key + "_Uppercase!>", toUppercase(values));
					source = source.replaceAll("<!" + key + "_Lowercase!>", toLowercase(values));
					source = source.replaceAll("<!" + key + "_Camel!>", toCamel(values));
					source = source.replaceAll("<!" + key + "_Pascal!>", toPascal(values));
					source = source.replaceAll("<!" + key + "!>", values);
				}
			}
		}
		return source;
	}

	/**
	 * 카멜 스타일 ( to_camel_style -> toCamelStyle )
	 * 
	 * @param s
	 * @return String
	 */
	protected String toCamel(String s) {
		if (s == null || s.length() == 0) {
			return s;
		}
		s = removeDelimiter(s);
		s = s.substring(0, 1).toLowerCase() + s.substring(1);
		return s;
	}

	/**
	 * 파스칼 스타일 ( to_pascal_style -> ToPascalStyle )
	 * 
	 * @param s
	 * @return String
	 */
	protected String toPascal(String s) {
		if (s == null || s.length() == 0) {
			return s;
		}
		s = removeDelimiter(s);
		s = s.substring(0, 1).toUpperCase() + s.substring(1);
		return s;
	}

	/**
	 * 소문자
	 * 
	 * @param s
	 * @return String
	 */
	protected String toLowercase(String s) {
		if (s == null || s.length() == 0) {
			return s;
		}
		return s.toLowerCase();
	}

	/**
	 * 대문자
	 * 
	 * @param s
	 * @return String
	 */
	protected String toUppercase(String s) {
		if (s == null || s.length() == 0) {
			return s;
		}
		return s.toUpperCase();
	}

	/**
	 * _[a-z] 의 형태가 포함된 문자열에서 _ 를 제거하고 [A-Z] 로 변환
	 * 
	 * @param s
	 * @return String
	 */
	protected String removeDelimiter(String s) {
		String re = s;
		int REGEX_FLAG = Pattern.CASE_INSENSITIVE | Pattern.DOTALL;
		Pattern delimiter = Pattern.compile("_[a-z]", REGEX_FLAG);
		Matcher matcher = delimiter.matcher(re);
		while (matcher.find()) {
			String group = matcher.group();
			re = matcher.replaceFirst(group.substring(1).toUpperCase());
			matcher = delimiter.matcher(re);
		}
		return re;
	}

	/**
	 *  im_ 로 시작하는 문자열에서 해당 prefix를 제거
	 * 
	 * @param s
	 * @return String
	 */
	protected String removePrefix(String s) {
		String re = s;
		int REGEX_FLAG = Pattern.CASE_INSENSITIVE | Pattern.DOTALL;
		Pattern[] prefix = { Pattern.compile("^im_", REGEX_FLAG), Pattern.compile("^im_", REGEX_FLAG) };
		for (Pattern pattern : prefix) {
			Matcher matcher = pattern.matcher(s);
			if (matcher.find()) {
				re = matcher.replaceFirst("");
				break;
			}
		}
		return re;
	}


}
