<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<im:form  condition="${condition}" formName="FormPageDetail"  type="list">
	<%--추가 검색 조건  todo Condition sc+컬럼+카멜--%>	
	<%--상세 조회 필요한 키값 --%>
	<input type="hidden" name="cmmnFileId"     />
</im:form>

<c:set var="mmbrAccssYnArr">N=비회원접근,Y=접근불가</c:set>