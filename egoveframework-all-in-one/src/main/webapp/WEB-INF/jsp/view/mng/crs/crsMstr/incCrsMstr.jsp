<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<im:form  condition="${condition}" formName="FormPageDetail"  type="list">
	<%--추가 검색 조건 --%>
	<input type="hidden" name="scCrsGrdCdv"    value="<c:out value="${condition.scCrsGrdCdv}"/>" />
	<input type="hidden" name="scCrsDvsnCdv"   value="<c:out value="${condition.scCrsDvsnCdv}"/>" />
	<input type="hidden" name="scSttsCdv"      value="<c:out value="${condition.scSttsCdv}"/>" />
	<%--상세 조회 필요한 키값 --%>
	<input type="hidden" name="crsMstrId"     />
</im:form>