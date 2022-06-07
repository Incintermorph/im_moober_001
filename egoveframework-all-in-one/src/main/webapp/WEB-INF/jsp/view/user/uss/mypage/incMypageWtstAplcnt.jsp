<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<im:form  condition="${condition}" formName="FormPageDetail"  type="list">
	<%--추가 검색 조건  todo Condition sc+컬럼+카멜--%>	
	<%--상세 조회 필요한 키값 --%>
	<input type="hidden" name="scAplyStts"      value="<c:out value="${condition.scAplyStts}"/>" />
	<input type="hidden" name="wtstAplcntId"     />
	<input type="hidden" name="modifyYn"     />
</im:form>

<c:set var="dpstYnArr">Y=예,N=아니오</c:set> 