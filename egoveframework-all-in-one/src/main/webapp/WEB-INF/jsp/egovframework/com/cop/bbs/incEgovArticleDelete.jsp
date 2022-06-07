<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<form id="formDelete" name="formDelete" action="<c:url value='/cop/bbs/deleteArticle.do'/>" method="post" style="float:left; margin:0 0 0 3px;">
	<input type="submit" class="ad_btn red mid" value="<spring:message code="button.delete" />" title="<spring:message code="button.delete" /> <spring:message code="input.button" />" onclick="fn_egov_delete_article(this.form); return false;"><!-- 삭제 -->
	<input name="nttId" type="hidden" value="<c:out value="${result.nttId}" />">
	<input name="bbsId" type="hidden" value="<c:out value="${boardMasterVO.bbsId}" />">
	<input name="searchCnd" type="hidden" value="<c:out value="${searchVO.searchCnd}"/>">
	<input name="searchWrd" type="hidden" value="<c:out value="${searchVO.searchWrd}"/>">
	<input name="pageIndex" type="hidden" value="<c:out value="${searchVO.pageIndex}"/>">
	<input type="hidden" name="forwardFlag" value="true"/>
	<input type="hidden" name="_paramMenuNo" value="${nowMenuNo}"/>
</form>