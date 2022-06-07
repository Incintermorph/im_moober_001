package com.intermorph.cmmn.taglibs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.intermorph.cmmn.service.IMCommonService;
import com.intermorph.cmmn.util.IMStringUtil;

import org.apache.taglibs.standard.tag.common.core.Util;
import egovframework.com.cmm.service.CmmnDetailCode;

@SuppressWarnings("serial")
public class IMCmmCodeTag extends TagSupport {
	private String codeId;
	private String styleClass;
	private String style;
	private String except;	
	private String sort;
	private String name;
	private String selectedCode;
	private String defaultSelectedCode;
	private String onclick;
	private String var;
	private String type = "print";
	private int cols;

	private IMCommonService commonService;

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public IMCommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(IMCommonService commonService) {
		this.commonService = commonService;
	}

	public String getExcept() {
		return except;
	}

	public void setExcept(String except) {
		this.except = except;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSelectedCode() {
		return selectedCode;
	}

	public void setSelectedCode(String selectedCode) {
		this.selectedCode = selectedCode;
	}

	public String getDefaultSelectedCode() {
		return defaultSelectedCode;
	}

	public void setDefaultSelectedCode(String defaultSelectedCode) {
		this.defaultSelectedCode = defaultSelectedCode;
	}

	public String getOnclick() {
		return onclick;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public IMCmmCodeTag() {
		super();
		init();
	}

	private void init() {
		setType("print");
		setVar(null);
		setName(null);
		setOnclick(null);
		setStyle(null);
		setStyleClass(null);
		setCodeId(null);
		setSelectedCode(null);
		setDefaultSelectedCode(null);
		setExcept(null);
		setSort(null);
		setExcept(null);
		setCols(0);
	}

	@SuppressWarnings("unchecked")
	public int doStartTag() throws JspException {
		try {
			StringBuffer buffer = new StringBuffer(100);
			if (type == null || type.length() == 0) {
				buffer.append("attribute 'type' undefined");
				pageContext.getOut().write(buffer.toString());
				return super.doStartTag();
			}
			if (codeId == null || codeId.length() == 0) {
				buffer.append("attribute 'codeId' undefined");
				pageContext.getOut().write(buffer.toString());
				return super.doStartTag();
			}

			List<CmmnDetailCode> listCode = null;
			List<CmmnDetailCode> printListCode = new ArrayList<CmmnDetailCode>();
			List<String> exceptList = new ArrayList<String>();

			if (except != null && except.length() > 0) {
				exceptList.addAll(Arrays.asList(except.split(",")));
			}

			if (codeId.indexOf(',') > -1 || codeId.indexOf('=') > -1) {
				String[] codeGroups = codeId.split(",");
				for (int i = 0; i < codeGroups.length; i++) {
					String[] nameValues = codeGroups[i].split("=");

					if (!exceptList.contains(nameValues[0])) {
						CmmnDetailCode code = new CmmnDetailCode();
						if (nameValues.length == 1) {
							code.setCodeNm(nameValues[0]);
							code.setCode(nameValues[0]);
						}else if (nameValues.length == 2) {
							code.setCodeNm(nameValues[1]);
							code.setCode(nameValues[0]);
						}
						printListCode.add(code);
					}
				}
			} else {
				if (commonService == null) {
					WebApplicationContext ctx = WebApplicationContextUtils
							.getRequiredWebApplicationContext(pageContext.getServletContext());
					commonService = (IMCommonService) ctx.getBean("IMCommonService");
				}
				Long sortLong=0L;
				try {
					if(!IMStringUtil.isEmpty(getSort())) {
						sortLong =Long.parseLong(getSort());	
					}
				}catch (NumberFormatException num) {
					sortLong=0L;
				}
						
				listCode = commonService.selectCmmCodeDetailSort(getCodeId(),sortLong);
				
				for (int i = 0; i < listCode.size(); i++) {
					CmmnDetailCode code = listCode.get(i);
					if (!exceptList.contains(code.getCode())) {
						printListCode.add(listCode.get(i)); 
					}
				}
			}
			/**
			 * 사용하지 않은 코드는 처리 하지 않음 for (int i = 0; i < listCode.size(); i++) {
			 * CmmnDetailCode code = listCode.get(i); boolean addable = false; if
			 * ("print".equals(type)) { addable = true; } else { if
			 * ("Y".equals(code.getUseAt()) && !exceptList.contains(code.getCode())) {
			 * addable = true; } } if (addable) { printListCode.add(code); }
			 * printListCode.add(listCode.get(i)); }
			 **/

			if (selectedCode == null || selectedCode.length() == 0) {
				if (defaultSelectedCode != null && defaultSelectedCode.length() > 0) {
					selectedCode = defaultSelectedCode;
				}
			}

			if (selectedCode == null || selectedCode.length() == 0) {
				selectedCode = "";
			}

			List<String> selectedList = new ArrayList<String>();
			List<String> ignoreSelectedList = new ArrayList<String>();
			if (selectedCode.indexOf(',') > -1) {
				for (String sel : selectedCode.split(",")) {
					selectedList.add(sel);
					ignoreSelectedList.add(sel.toLowerCase(Locale.getDefault()));
				}
			} else {
				selectedList.add(selectedCode);
				ignoreSelectedList.add(selectedCode.toLowerCase(Locale.getDefault()));
			}

			if ("print".equals(type)) {
				for (CmmnDetailCode code : printListCode) {
					if (selectedList.contains(code.getCode())
							|| (ignoreSelectedList.contains(code.getCode().toLowerCase(Locale.getDefault())))) {
						if (buffer.length() > 0) {
							buffer.append(',');
						}
						buffer.append(code.getCodeNm());
					}
				}

			} else if ("option".equals(type)) {

				for (CmmnDetailCode code : printListCode) {

					buffer.append("<option value='" + code.getCode() + "' ");
					if (selectedList.contains(code.getCodeNm())
							|| (ignoreSelectedList.contains(code.getCode().toLowerCase(Locale.getDefault())))) {
						buffer.append(" selected='selected' ");
					}
					if (styleClass != null && styleClass.length() > 0) {
						buffer.append(" class='" + Util.escapeXml(styleClass) + "'");
					}
					if (style != null && style.length() > 0) {
						buffer.append(" style='" + Util.escapeXml(style) + "'");
					}
					buffer.append('>').append(code.getCodeNm());
					buffer.append("</option>");
				}

			} else if ("hidden".equals(type)) {
				if (name == null || name.length() == 0) {
					buffer.append("attribute 'name' undefined");
					pageContext.getOut().write(buffer.toString());
					return super.doStartTag();
				}
				for (CmmnDetailCode code : printListCode) {
					buffer.append("<input type='" + type + "' name='" + name + "' value='" + code.getCode() + "' >");
				}
			} else if ("radio".equals(type) || "checkbox".equals(type)) {
				if (name == null || name.length() == 0) {
					buffer.append("attribute 'name' undefined");
					pageContext.getOut().write(buffer.toString());
					return super.doStartTag();
				}

				for (int i = 0; i < printListCode.size(); i++) {
					CmmnDetailCode code = printListCode.get(i);

					String id = name + "-" + i;
					buffer.append(" <input type='" + type + "' name='" + name + "' id='" + id + "' value='"
							+ code.getCode() + "' ");
					if (selectedList.contains(code.getCode())
							|| (ignoreSelectedList.contains(code.getCode().toLowerCase(Locale.getDefault())))) {
						buffer.append(" checked='checked' ");
					}
					String sClass = "radio".equals(type) ? "im-radio " : "im-checkbox ";
					if (styleClass != null && styleClass.length() > 0) {
						sClass += Util.escapeXml(styleClass);
					}
					buffer.append(" class='" + Util.escapeXml(sClass) + "'");

					if (style != null && style.length() > 0) {
						buffer.append(" style='" + Util.escapeXml(style) + "'");
					}
					if (onclick != null && onclick.length() > 0) {
						buffer.append(" onclick='" + Util.escapeXml(onclick) + "'");
					}
					buffer.append("/>");

					buffer.append("<label for='" + id + "' ");

					String lClass = "radio".equals(type) ? "im-radio-label " : "im-checkbox-label ";

					buffer.append(" class='" + Util.escapeXml(lClass) + "'");

					buffer.append(">" + code.getCodeNm() + "</label>");

					if (cols > 0) {
						if ((i + 1) % cols == 0) {
							buffer.append("<br>");
						}
					}
				}

			} else if ("set".equals(type)) {
				if (var == null || var.length() == 0) {
					buffer.append("attribute 'var' undefined");
					pageContext.getOut().write(buffer.toString());
					return super.doStartTag();
				}

				HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

				request.setAttribute(var, printListCode);

				return super.doStartTag();
			}else if ("keyset".equals(type)) {
				if (var == null || var.length() == 0) {
					buffer.append("attribute 'var' undefined");
					pageContext.getOut().write(buffer.toString());
					return super.doStartTag();
				}
				
				String arrkeySet="";
				StringBuffer arr = new StringBuffer();
				if(printListCode!=null && printListCode.size()>0) {
					int start =0;
					for(CmmnDetailCode code :   printListCode) {
					if(start==0) {
						arr.append(code.getCode()+"="+code.getCodeNm());
					}else {
						arr.append(","+code.getCode()+"="+code.getCodeNm());
					}
					start++;
					}
					arrkeySet=arr.toString();
				}

				HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

				request.setAttribute(var, arrkeySet);

				return super.doStartTag();
			}
			// System.out.println("test : " + buffer.toString());
			pageContext.getOut().write(buffer.toString());

		} catch (Exception io) {
			throw new JspException(io);
		}
		return super.doStartTag();

	}

}