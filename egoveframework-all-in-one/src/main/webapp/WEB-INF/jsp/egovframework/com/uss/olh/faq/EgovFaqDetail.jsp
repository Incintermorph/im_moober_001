<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<c:set var="pageTitle"><spring:message code="comUssOlhFaq.faqVO.title"/></c:set>
<%-- <link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />"> --%>
<script type="text/javascript">
var REQ = {
	req : {
		list : null,
		modify : null
	},
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageList";
		this.req.list.cfg.url    = "<c:url value="/mng/uss/olh/faq/selectFaqList.do"/>";
		
		this.req.modify = imRequest();
		this.req.modify.cfg.formId = "FormPageModify";
		this.req.modify.cfg.url    = "<c:url value="/mng/uss/olh/faq/updateFaqView.do"/>";
		
	},
	list :  function(){
		this.req.list.go();
	},
	modifyPage :  function(){
		this.req.modify.go();
	}
}

function fn_egov_delete_faq(faqId){
	if(confirm("<spring:message code="common.delete.msg" />")){	
		// Delete하기 위한 키값을 셋팅
		document.formDelete.faqId.value = faqId;	
		document.formDelete.action = "<c:url value='/uss/olh/faq/deleteFaq.do'/>";
		document.formDelete.submit();	
	}	
}	

$(document).ready(function(){
	REQ.init();
});
</script>
<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<%@ include file="incEgovFaqList.jsp" %>

<form name="FormPageModify"  id="FormPageModify"  method="post" onsubmit="return false;" >
	<input name="searchCnd" type="hidden" value="<c:out value="${searchVO.searchCnd}"/>">
	<input name="searchWrd" type="hidden" value="<c:out value="${searchVO.searchWrd}"/>">
	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
	<input name="faqId" type="hidden" value="<c:out value="${result.faqId}" />">
	<input name="cmd" type="hidden" value="">
</form>

<form id="formDelete" name="formDelete" method="post" onsubmit="return false;">
	<input name="faqId" type="hidden" value="<c:out value="${result.faqId}" />">
	<input name="searchCnd" type="hidden" value="<c:out value="${searchVO.searchCnd}"/>">
	<input name="searchWrd" type="hidden" value="<c:out value="${searchVO.searchWrd}"/>">
	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
	<input type="hidden" name="forwardFlag" value="true"/>
	<input type="hidden" name="_paramMenuNo" value="${nowMenuNo}"/>
</form>

<!-- 콘텐츠 -->
<h3 class="ct_title">${pageTitle} <spring:message code="title.detail" /></h3>
<table class="tbl_row">
	<caption>기관관리자 상세정보</caption>
	<colgroup>
		<col style="width:200px;">
		<col>
	</colgroup>
	<tbody>
		<!-- 질문제목 -->
		<tr>
			<th scope="row"><spring:message code="comUssOlhFaq.faqVO.qestnSj" /></th>
			<td>
				<c:out value="${result.qestnSj}"/>
			</td>
		</tr>
		<!-- 조회수 -->
		<tr>
			<th scope="row"><spring:message code="comUssOlhFaq.faqVO.inqireCo" /></th>
			<td>
				<c:out value="${result.inqireCo}"/>
			</td>
		</tr>
		<!-- 질문 내용 -->
		<tr>
			<th scope="row"><spring:message code="comUssOlhFaq.faqVO.qestnCn" /></th>
			<td>
				<c:out value="${fn:replace(result.qestnCn , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>
		<!-- 답변 내용 -->
		<tr>
			<th scope="row"><spring:message code="comUssOlhFaq.faqVO.answerCn" /></th>
			<td>
				<c:out value="${fn:replace(result.answerCn , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>
		<!-- 등록일자 -->
		<tr>
			<th scope="row"><spring:message code="table.regdate" /></th>
			<td><c:out value="${result.frstRegisterPnttm}"/></td>
		</tr>
		<!-- 첨부파일  -->
		<c:if test="${not empty result.atchFileId}">
		<tr>
			<th scope="row"><spring:message code="comUssOlhFaq.faqVO.atchFile" /></th>
			<td>
				<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
					<c:param name="param_atchFileId" value="${result.atchFileId}" />
				</c:import>
			</td>
		</tr>
		</c:if>
	</tbody>
</table>

<!-- 하단 버튼 -->
<div class="b_box right">
	<button type="button" onclick="REQ.modifyPage()" class="ad_btn green mid"><spring:message code="button.update" /></button>
	<a href="<c:url value='/uss/olh/faq/deleteFaq.do' />" class="ad_btn red mid" onClick="fn_egov_delete_faq('<c:out value="${result.faqId}"/>'); return false;"  title="<spring:message code="button.delete" /> <spring:message code="input.button" />"><spring:message code="button.delete" /></a>
	<button type="button" onclick="REQ.list()" class="ad_btn bk mid"><spring:message code="button.list" /></button>
</div>
