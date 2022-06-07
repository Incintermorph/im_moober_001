<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<im:form  condition="${condition}" formName="FormPageDetail"  type="list">
	<%--추가 검색 조건  todo Condition sc+컬럼+카멜--%>
<input type="hidden" name="scEmplyrSttusCode"   value="${condition.scEmplyrSttusCode}"  />	
<input type="hidden" name="scAuthorCode"   value="${condition.scAuthorCode}"  />	
<input type="hidden" name="scAgncyId"   value="${condition.scAgncyId}"  />	
	<%--상세 조회 필요한 키값 --%>
	<input type="hidden" name="esntlId"  />
	<input type="hidden" name="_pageName"  value="${param['_pageName']}"  />
	<input type="hidden" name="inqRsn"  />
	<input type="hidden" name="imCallBack" value="REQ.modifyPage"  />
</im:form>