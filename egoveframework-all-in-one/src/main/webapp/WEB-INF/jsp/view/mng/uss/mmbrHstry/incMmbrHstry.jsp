<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<im:form  condition="${condition}" formName="FormPageDetail"  type="list">
	<input type="hidden" name="scAplyGrdCdv"    value="${condition.scAplyGrdCdv}"  />
	<input type="hidden" name="scSttsCode"    value="${condition.scSttsCode}"  />
	<input type="hidden" name="scAcqsgrdcode"    value="${condition.scAcqsgrdcode}"  />
	<input type="hidden" name="memberSrl"     />
</im:form>