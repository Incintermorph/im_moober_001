<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<im:form  condition="${condition}" formName="FormPageDetail"  type="list">
	
	<input type="hidden" name="scDvsnCdv"  value="${condition.scDvsnCdv}"    />
	<input type="hidden" name="scAgncyId"  value="${condition.scAgncyId}"    />
	<input type="hidden" name="scEduYear"  value="${condition.scEduYear}"    />
	<input type="hidden" name="scCrsGrdCdv"  value="${condition.scCrsGrdCdv}"    />
	<input type="hidden" name="scEsntlId"  value="${condition.scEsntlId}"    />
		
	<%--상세 조회 필요한 키값 --%>
	<input type="hidden" name="pstpndAplyId"     />
	
</im:form>