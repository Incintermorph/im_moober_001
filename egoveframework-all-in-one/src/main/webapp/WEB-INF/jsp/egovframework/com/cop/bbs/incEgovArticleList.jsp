<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<form name="FormPageList"  id="FormPageList"  method="post" onsubmit="return false;" >
	<input name="searchCnd" type="hidden" value="<c:out value="${searchVO.searchCnd}"/>">
	<input name="searchWrd" type="hidden" value="<c:out value="${searchVO.searchWrd}"/>">
	<input name="pageIndex" type="hidden" value="<c:out value="${searchVO.pageIndex}"/>">
	<input name="bbsId" type="hidden" value="${boardMasterVO.bbsId}">
</form>