<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<im:form  condition="${condition}" formName="FormPageDetail"  type="list">
	<%--추가 검색 조건  todo Condition sc+컬럼+카멜--%>	
	<%--상세 조회 필요한 키값 --%>
	<input type="hidden" name="scNotSttsCdv"  value="${condition.scNotSttsCdv}"    />
	<input type="hidden" name="scSttsCdv"  value="${condition.scSttsCdv}"    />
	<input type="hidden" name="scIssuDvsnCdv"  value="${condition.scIssuDvsnCdv}"    />
	<input type="hidden" name="scAgncyId"  value="${condition.scAgncyId}"    />
	<input type="hidden" name="scEduYear"  value="${condition.scEduYear}"    />
	<input type="hidden" name="scCrsGrdCdv"  value="${condition.scCrsGrdCdv}"    />
	<input type="hidden" name="issuAplyId"     />	
	<input type="hidden" name="imCallBack" value="REQ.excel"  />
	<input type="hidden" name="excelResult" value=""/>
	<input type="hidden" name="downloadName" value=""/>
</im:form>