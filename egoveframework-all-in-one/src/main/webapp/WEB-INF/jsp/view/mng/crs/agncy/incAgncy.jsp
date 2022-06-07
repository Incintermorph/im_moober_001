<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<im:form  condition="${condition}" formName="FormPageDetail"  type="list">
	<%--추가 검색 조건  todo Condition sc+컬럼+카멜--%>	
	<input type="hidden" name="scCrsGrdCdv"   value="${condition.scCrsGrdCdv}"  />
	<input type="hidden" name="scAgncyDvsnCdv"   value="${condition.scAgncyDvsnCdv}"  />
	<input type="hidden" name="scNotAgncyDvsnCdv"   value="${condition.scNotAgncyDvsnCdv}"  />
	<input type="hidden" name="scAreaCdv"   value="${condition.scAreaCdv}"  />
	<%--상세 조회 필요한 키값 --%>
	<input type="hidden" name="agncyId"     />
</im:form>
<c:set var="arrYesNo">Y=예,N=아니오</c:set>