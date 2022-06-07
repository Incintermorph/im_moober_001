<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<im:form  condition="${condition}" formName="FormPageDetail"  type="list">
	<input type="hidden" name="scCrsGrdCdv"    value="<c:out value="${condition.scCrsGrdCdv}"/>" />
	<input type="hidden" name="scSttsCdv"      value="<c:out value="${condition.scSttsCdv}"/>" />
	<input type="hidden" name="scEduYear"      value="<c:out value="${condition.scEduYear}"/>" />
	<input type="hidden" name="scEduRnd"      value="<c:out value="${condition.scEduRnd}"/>" />
	<input type="hidden" name="tabCode"   value="${param['tabCode']}" />
	<input type="hidden" name="_pageName"   value="" />
	<input type="hidden" name="wtstId"     />
	<input type="hidden" name="esntlId"     />
	<input type="hidden" name="wtstPlaceId"     />
	<input type="hidden" name="downloadName"    value="" />
	<input type="hidden" name="excelResult"    value="" />
	<input type="hidden" name="imCallBack" value="REQ.excel"  />
</im:form>



<im:form  condition="${condition}" formName="FormPageDetailTab"  type="list">
	<input type="hidden" name="scCrsGrdCdv"    value="<c:out value="${condition.scCrsGrdCdv}"/>" />
	<input type="hidden" name="scSttsCdv"      value="<c:out value="${condition.scSttsCdv}"/>" />
	<input type="hidden" name="scEduYear"      value="<c:out value="${condition.scEduYear}"/>" />
	<input type="hidden" name="scEduRnd"      value="<c:out value="${condition.scEduRnd}"/>" />
	
	<input type="hidden" name="_pageName"   value="${param['_pageName']}"  />
	<input type="hidden" name="tabCode"   value="${param['tabCode']}" />
	<input type="hidden" name="wtstId"    value="${param['wtstId']}" />
	<input type="hidden" name="wtstPlaceId"     />
	<input type="hidden" name="wtstAplcntId"    value="" />
	
	
	
	<input type="hidden" name="aplcnt.orderbyKey"  value="<c:out value="${condition.aplcnt.orderbyKey}"/>" />
	<input type="hidden" name="aplcnt.scWord"      value="<c:out value="${condition.aplcnt.scWord}"/>"   />
	<input type="hidden" name="aplcnt.scKey"       value="<c:out value="${condition.aplcnt.scKey}"/>"   />
	<input type="hidden" name="aplcnt.scExempDvsnCdv"       value="<c:out value="${condition.aplcnt.scExempDvsnCdv}"/>"   />
	<input type="hidden" name="aplcnt.scExamFnshYn"       value="<c:out value="${condition.aplcnt.scExamFnshYn}"/>"   />
	<input type="hidden" name="aplcnt.scFnshYn"       value="<c:out value="${condition.aplcnt.scFnshYn}"/>"   />
	<input type="hidden" name="aplcnt.scDpstStts"       value="<c:out value="${condition.aplcnt.scDpstStts}"/>"   />
	
	
	
</im:form>

<c:set var="addtnYnArr">Y=예,N=아니오</c:set>


<im:cd type="keyset" codeId="IMYENO" var="ARR_IMYENO"/>
<im:cd type="keyset" codeId="IM0017" var="ARR_IM0017"/>
<im:cd type="keyset" codeId="IM0021" var="ARR_IM0021"/>
<im:cd type="keyset" codeId="IM0025" var="ARR_IM0025"/>


<c:if test="${!empty  param['wtstId']}">

<c:set var="tabCode" value="01"/>
<c:if test="${!empty param['tabCode']}">
<c:set var="tabCode" value="${param['tabCode'] }"/>
</c:if>



<script type="text/javascript" >
var TAB = {
	list:function(tabCode,tabNm){
		var req = imRequest();
		
		req.cfg.url    = "<c:url value="/mng/wtst/modify.do"/>";
		
		req.cfg.formId = "FormPageDetail";
		var form =  document.getElementById(req.cfg.formId );
		form.elements["tabCode"].value= tabCode;
		form.elements["wtstId"].value= '${param["wtstId"]}';
		form.elements["_pageName"].value= tabNm;
		if(tabCode=='02'){
			req.cfg.url    = "<c:url value="/mng/wtst/selectListPlace.do"/>";
		}else if(tabCode=='04'){
				req.cfg.url   = "<c:url value="/mng/wtst/04/selectListAplcnt.do"/>";
	    }else if(tabCode=='05'){
				req.cfg.url   = "<c:url value="/mng/wtst/05/selectListAplcnt.do"/>";
	    }else if(tabCode=='99'){
				req.cfg.url   = "<c:url value="/mng/wtst/99/selectListAplcnt.do"/>";
		}
		req.go();
	}
}


</script>


<c:set var="arrTabs">01=기본정보,02=시험장,04=입금관리,05=응시자,99=필기시험결과</c:set>
<im:cd type="set" codeId="${arrTabs}" var="arrTagCode"/>



<ul class="nav_tabs">
	<c:forEach items="${arrTagCode}" var="trow">
	<li <c:if test="${tabCode eq trow.code }"> class="on"</c:if>><a href="#" onclick="TAB.list('${trow.code}','${trow.codeNm}')">${trow.codeNm}</a></li>
	</c:forEach>
</ul>





</c:if>