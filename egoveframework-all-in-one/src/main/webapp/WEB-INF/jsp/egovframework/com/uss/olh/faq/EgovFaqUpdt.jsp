<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<c:set var="pageTitle"><spring:message code="comUssOlhFaq.faqVO.title" /></c:set>
<%-- <link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />"> --%>
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="faqVO" staticJavascript="false"	xhtml="true" cdata="false" />
<script type="text/javascript">
var REQ = {
	req : {
		list : null
	},
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageList";
		this.req.list.cfg.url    = "<c:url value="/mng/uss/olh/faq/selectFaqList.do"/>";
	},
	list :  function(){
		this.req.list.go();
	}
}

$(document).ready(function(){
	REQ.init();
});

/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init() {
	
	//------------------------------------------
	//------------------------- 첨부파일 수정 Start
	//-------------------------------------------
	var maxFileNum = 3
	var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), maxFileNum);
	multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );
	//------------------------- 첨부파일 수정 End
	
	// 첫 입력란에 포커스..
	document.getElementById("faqVO").qestnSj.focus();
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_updt_faq(form) {
	
	var resultExtension = EgovMultiFilesChecker.checkExtensions("egovComFileUploader", "<c:out value='${fileUploadExtensions}'/>"); // 결과가 false인경우 허용되지 않음
	if (!resultExtension) return true;
	var resultSize = EgovMultiFilesChecker.checkFileSize("egovComFileUploader", <c:out value='${fileUploadMaxSize}'/>); // 파일당 1M까지 허용 (1K=1024), 결과가 false인경우 허용되지 않음
	if (!resultSize) return true;
	
	if (!validateFaqVO(form)) {
		return false;
	} else {
		if (confirm("<spring:message code="common.update.msg" />")) {
			form.submit();
		}
	}
}
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_inqire_faqlist() {
	faqVO.action = "<c:url value='/mng/uss/olh/faq/selectFaqList.do'/>";
	faqVO.submit();
}

$(document).ready(function() {
	fn_egov_init();
});
</script>

<!-- javascript warning tag  -->
<noscript class="noScriptTitle">	<spring:message code="common.noScriptTitle.msg" />	</noscript>

<%@ include file="incEgovFaqList.jsp" %>

<form:form commandName="faqVO" action="${pageContext.request.contextPath}/uss/olh/faq/updateFaq.do" method="post" onSubmit="fn_egov_updt_faq(document.forms[2]); return false;" enctype="multipart/form-data">
<!-- 콘텐츠 -->
<h3 class="ct_title">${pageTitle} <spring:message code="title.update" /></h3>
<table class="tbl_row" summary="<spring:message code="common.summary.update" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.update" /></caption>
	<colgroup>
		<col style="width:200px;">
		<col>
	</colgroup>
	<tbody>
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
		<!-- 질문 제목   -->
		<c:set var="title"><spring:message code="comUssOlhFaq.faqVO.qestnSj"/> </c:set>
		<tr>
			<th scope="row"><label for="qestnSj">${title}<span class="c_red">*</span></label></th>
			<td>
				<form:input path="qestnSj" title="${title} ${inputTxt}" size="70" maxlength="70" />
   				<div><form:errors path="qestnSj" cssClass="error" /></div>     
			</td>
		</tr>
		<!-- 질문 내용  -->
		<c:set var="title"><spring:message code="comUssOlhFaq.faqVO.qestnCn"/> </c:set>
		<tr>
			<th scope="row"><label for="qestnCn">${title }<span class="c_red">*</span></label></th>
			<td>
				<form:textarea path="qestnCn" title="${title} ${inputTxt}" cols="300" rows="20" />   
				<div><form:errors path="qestnCn" cssClass="error" /></div>  
			</td>
		</tr>
		<!-- 답변 내용  -->
		<c:set var="title"><spring:message code="comUssOlhFaq.faqVO.answerCn"/> </c:set>
		<tr>
			<th scope="row"><label for="answerCn">${title }<span class="c_red">*</span></label></th>
			<td>
				<form:textarea path="answerCn" title="${title} ${inputTxt}" cols="300" rows="20" />   
				<div><form:errors path="answerCn" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 첨부파일 -->
		<c:set var="title"><spring:message code="comUssOlhFaq.faqVO.atchFile"/></c:set>
		<tr>
			<th scope="row"><label for="file_1">${title}</label></th>
			<td>
				<!-- 첨부목록을 보여주기 위한 -->
				<c:if test="${not empty faqVO.atchFileId}">
					<c:import charEncoding="utf-8" url="/cmm/fms/selectFileInfsForUpdate.do" >
						<c:param name="param_atchFileId" value="${faqVO.atchFileId}" />
					</c:import>		
				</c:if>
			     <!-- attached file Start -->
				<div>
					<input type="file" multiple name="file_1" id="egovComFileUploader">
					<div id="egovComFileList"></div>
				</div>
				<!-- attached file End -->
			</td>
		</tr>
	</tbody>
</table>

<!-- 하단 버튼 -->
<div class="b_box right">
	<input type="submit" class="ad_btn green mid" value="<spring:message code="button.update" />" title="<spring:message code="button.update" /> <spring:message code="input.button" />" />
<%-- 	<a href="<c:url value='/mng/uss/olh/faq/selectFaqList.do' />" class="ad_btn bk mid" title="<spring:message code="button.list" /> <spring:message code="input.button" />"><spring:message code="button.list" /></a> --%>
	<button type="button" onclick="REQ.list()" class="ad_btn bk mid"><spring:message code="button.list" /></button>
</div>

<input name="searchCnd" type="hidden" value="<c:out value="${searchVO.searchCnd}"/>">
<input name="searchWrd" type="hidden" value="<c:out value="${searchVO.searchWrd}"/>">
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>" />
<input name="faqId" type="hidden" value="${faqVO.faqId}">
<input type="hidden" name="forwardFlag" value="true"/>
<input type="hidden" name="_paramMenuNo" value="${nowMenuNo}"/>
<!-- //콘텐츠 -->
</form:form>

