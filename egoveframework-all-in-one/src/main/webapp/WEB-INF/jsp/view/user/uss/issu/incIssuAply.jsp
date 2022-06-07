<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<im:form  condition="${condition}" formName="FormPageDetail"  type="list">
	<%--추가 검색 조건  todo Condition sc+컬럼+카멜--%>	
	<%--상세 조회 필요한 키값 --%>
	<input type="hidden" name="issuDvsnCdv"     />
	<input type="hidden" name="qlfcRsltCode"     />
	<input type="hidden" name="crsGrdCdv"     />
	<input type="hidden" name="issuAplyId"     />
</im:form>

<c:set var="dvsnCdvArr">01=법개정 이전,02=법개정 이후</c:set>