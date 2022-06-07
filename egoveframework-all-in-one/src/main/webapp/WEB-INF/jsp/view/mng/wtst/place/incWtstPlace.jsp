<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<im:form  condition="${condition}" formName="FormPageDetail"  type="list">
	<%--추가 검색 조건  todo Condition sc+컬럼+카멜--%>	
	<%--상세 조회 필요한 키값 --%>
	<input type="hidden" name="scCrsGrdCdv"    value="<c:out value="${condition.scCrsGrdCdv}"/>" />
	<input type="hidden" name="scEduYear"      value="<c:out value="${condition.scEduYear}"/>" />
	<input type="hidden" name="scEduRnd"      value="<c:out value="${condition.scEduRnd}"/>" />
	<input type="hidden" name="scAgncyId"      value="<c:out value="${condition.scAgncyId}"/>" />
	<input type="hidden" name="scAddtnYn"      value="<c:out value="${condition.scAddtnYn}"/>" />
	<input type="hidden" name="scPrgrsStts"      value="<c:out value="${condition.scPrgrsStts}"/>" />
	<input type="hidden" name="esntlId"     />
	<input type="hidden" name="wtstPlaceId"     />
	<input type="hidden" name="_pageName"     value="${param['_pageName']}"  />
	<input type="hidden" name="tabCode"   value="${param['tabCode']}" />
</im:form>

<im:form  condition="${condition}" formName="FormPageDetailTab"  type="list">
	<%--추가 검색 조건  todo Condition sc+컬럼+카멜--%>
	<input type="hidden" name="scCrsGrdCdv"    value="<c:out value="${condition.scCrsGrdCdv}"/>" />
	<input type="hidden" name="scEduYear"      value="<c:out value="${condition.scEduYear}"/>" />
	<input type="hidden" name="scEduRnd"      value="<c:out value="${condition.scEduRnd}"/>" />
	<input type="hidden" name="scAgncyId"      value="<c:out value="${condition.scAgncyId}"/>" />
	<input type="hidden" name="scAddtnYn"      value="<c:out value="${condition.scAddtnYn}"/>" />
	<input type="hidden" name="scPrgrsStts"      value="<c:out value="${condition.scPrgrsStts}"/>" />
	
	<input type="hidden" name="_pageName"     />
	<input type="hidden" name="tabCode"   value="${param['tabCode']}" />
	<input type="hidden" name="wtstPlaceId"    value="${param['wtstPlaceId']}" />
	
	
	<input type="hidden" name="aplcnt.orderbyKey"  value="<c:out value="${condition.aplcnt.orderbyKey}"/>" />
	<input type="hidden" name="aplcnt.scWord"      value="<c:out value="${condition.aplcnt.scWord}"/>"   />
	<input type="hidden" name="aplcnt.scKey"       value="<c:out value="${condition.aplcnt.scKey}"/>"   />
	<input type="hidden" name="aplcnt.scFnshYn"       value="<c:out value="${condition.aplcnt.scFnshYn}"/>"   />
	<input type="hidden" name="aplcnt.scExempDvsnCdv"       value="<c:out value="${condition.aplcnt.scExempDvsnCdv}"/>"   />
	<input type="hidden" name="aplcnt.scDpstStts"       value="<c:out value="${condition.aplcnt.scDpstStts}"/>"   />
	
	
</im:form>







<c:if test="${!empty  param['wtstPlaceId']}">


<c:set var="tabCode" value="01"/>
<c:if test="${!empty param['tabCode']}">
<c:set var="tabCode" value="${param['tabCode'] }"/>
</c:if>

<script type="text/javascript" >
var TAB = {
	list:function(tabCode,tabNm){
		var req = imRequest();
		
		req.cfg.formId = "FormPageDetail";
		var form =  document.getElementById(req.cfg.formId );
		form.elements["tabCode"].value= tabCode;
		form.elements["wtstPlaceId"].value= '${param["wtstPlaceId"]}';
		form.elements["_pageName"].value= tabNm;
		if(tabCode=='01'){
			req.cfg.url    = "<c:url value="/mng/wtstPlace/modify.do"/>";
		}else if(tabCode=='02'){
			req.cfg.url    = "<c:url value="/mng/wtstPlace/selectDetailWtst.do"/>";
		}else{
			req.cfg.url    = "<c:url value="/mng/wtstPlace/"/>"+tabCode+"/selectListAplcnt.do";
		}
		req.go();
	}
}


</script>


<div class="tb_info">
	<ul>
		<li>${detail.agncy.agncyNm}</li>
		<li>${detail.wtst.eduYear} 년</li>
		<li><im:cd type="print" codeId="IM0001" selectedCode="${detail.wtst.crsGrdCdv}"/></li>
		<li>${detail.wtst.eduRnd}차</li>
		<li>정원 : ${detail.wtstPlace.prsnl}명</li>
		<c:if test="${detail.wtst.addtnYn eq 'Y'}">
		<li>추가모집</li>
		</c:if>
		<li><im:cd type="print" codeId="IM0020" selectedCode="${detail.wtstPlace.prgrsSttsCdv}"/></li>
	</ul>
</div>

<c:if test="${empty  loginUserAgncyId}">
<c:set var="arrTabs">01=기본정보,02=일정상세정보,03=자격심사,04=입금관리,05=응시자</c:set>
</c:if>
<c:if test="${!empty  loginUserAgncyId}">
<c:set var="arrTabs">01=기본정보,02=일정상세정보,04=입금관리,05=응시자</c:set>
</c:if>
<im:cd type="set" codeId="${arrTabs}" var="arrTagCode"/>



<ul class="nav_tabs">
	<c:forEach items="${arrTagCode}" var="trow">
	<li <c:if test="${tabCode eq trow.code }"> class="on"</c:if>><a href="#" onclick="TAB.list('${trow.code}','${trow.codeNm}')">${trow.codeNm}</a></li>
	</c:forEach>
</ul>
</c:if>