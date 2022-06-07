<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<im:form  condition="${condition}" formName="FormPageDetail"  type="list">
	<%--추가 검색 조건  todo Condition sc+컬럼+카멜--%>	
	<%--상세 조회 필요한 키값 --%>
	
	<input type="hidden" name="scTrnsfYn"    value="<c:out value="${condition.scTrnsfYn}"/>" />
	<input type="hidden" name="imCallBack"     />
	<input type="hidden" name="crsAplyTrnsfId"     />
</im:form>