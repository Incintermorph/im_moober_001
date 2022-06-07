<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<im:form  condition="${condition}" formName="FormPageDetail"  type="list">
	<%--추가 검색 조건  todo Condition sc+컬럼+카멜--%>
	<input type="hidden" name="scCrsGrdCdv"    value="<c:out value="${condition.scCrsGrdCdv}"/>" />
	<input type="hidden" name="scCrsDvsnCdv"   value="<c:out value="${condition.scCrsDvsnCdv}"/>" />
	<input type="hidden" name="scSttsCdv"      value="<c:out value="${condition.scSttsCdv}"/>" />
	<input type="hidden" name=scPrgrsSttsCdv      value="<c:out value="${condition.scPrgrsSttsCdv}"/>" />
	<input type="hidden" name="scAgncyId"      value="<c:out value="${condition.scAgncyId}"/>" />
	<input type="hidden" name="scEduYear"      value="<c:out value="${condition.scEduYear}"/>" />
	<input type="hidden" name="scEduRnd"      value="<c:out value="${condition.scEduRnd}"/>" />
	<input type="hidden" name="scProcType"      value="<c:out value="${condition.scProcType}"/>" />
	<input type="hidden" name="scNotCrsDvsnCdv"      value="<c:out value="${condition.scNotCrsDvsnCdv}"/>" />
		
	<%--상세 조회 필요한 키값 --%>
	<input type="hidden" name="crsId"     />
	<input type="hidden" name="crsAplcntId"     />
</im:form>
