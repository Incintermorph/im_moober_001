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
	<input type="hidden" name="_pageName"   />		
	<%--상세 조회 필요한 키값 --%>
	<input type="hidden" name="tabCode"   value="${param['tabCode']}" />
	<input type="hidden" name="crsId"     />
	<input type="hidden" name="esntlId"     />
	<input type="hidden" name="crsAplcntId"     />
	<input type="hidden" name="downloadName" value=""/>
	<input type="hidden" name="excelResult" value=""/>
	<input type="hidden" name="excelType" value=""/>
	<input type="hidden" name="imCallBack" value="REQ.excel"  />
</im:form>
<im:form  condition="${condition}" formName="FormPageDetailTab"  type="list">
	<%--추가 검색 조건  todo Condition sc+컬럼+카멜--%>
	<input type="hidden" name="scCrsGrdCdv"    value="<c:out value="${condition.scCrsGrdCdv}"/>" />
	<input type="hidden" name="scCrsDvsnCdv"   value="<c:out value="${condition.scCrsDvsnCdv}"/>" />
	<input type="hidden" name="scSttsCdv"      value="<c:out value="${condition.scSttsCdv}"/>" />
	<input type="hidden" name=scPrgrsSttsCdv      value="<c:out value="${condition.scPrgrsSttsCdv}"/>" />
	<input type="hidden" name="scAgncyId"      value="<c:out value="${condition.scAgncyId}"/>" />
	<input type="hidden" name="scEduYear"      value="<c:out value="${condition.scEduYear}"/>" />
	<input type="hidden" name="scEduRnd"      value="<c:out value="${condition.scEduRnd}"/>" />
		
	<%--상세 조회 필요한 키값 --%>
	<input type="hidden" name="tabCode"   value="${param['tabCode']}" />
	<input type="hidden" name="_pageName"   value="${param['_pageName']}" />
	<input type="hidden" name="crsId"   value="${param['crsId']}"   />
	
	<input type="hidden" name="aplcnt.orderbyKey"  value="<c:out value="${condition.aplcnt.orderbyKey}"/>" />
	<input type="hidden" name="aplcnt.scWord"      value="<c:out value="${condition.aplcnt.scWord}"/>"   />
	<input type="hidden" name="aplcnt.scKey"       value="<c:out value="${condition.aplcnt.scKey}"/>"   />
	<input type="hidden" name="aplcnt.scExptSbjYn"       value="<c:out value="${condition.aplcnt.scExptSbjYn}"/>"   />
	<input type="hidden" name="aplcnt.scSttsCdvAGNCYSRNG"       value="<c:out value="${condition.aplcnt.scSttsCdvAGNCYSRNG}"/>"   />
	<input type="hidden" name="aplcnt.scSttsCdvOPSECTSRNG"       value="<c:out value="${condition.aplcnt.scSttsCdvOPSECTSRNG}"/>"   />
	<input type="hidden" name="aplcnt.scSttsCdvDPST"       value="<c:out value="${condition.aplcnt.scSttsCdvDPST}"/>"   />
	<input type="hidden" name="aplcnt.scSttsCdvFNSH"       value="<c:out value="${condition.aplcnt.scSttsCdvFNSH}"/>"   />
	<input type="hidden" name="aplcnt.scSttsCdvQLFC"       value="<c:out value="${condition.aplcnt.scSttsCdvQLFC}"/>"   />
	
	
	
</im:form>


<script type="text/javascript" >
var TAB = {
	list:function(tabCode,tabNm){
		var req = imRequest();
		
		req.cfg.url    = "<c:url value="/mng/crs/modify.do"/>";
		
		req.cfg.formId = "FormPageDetail";
		var form =  document.getElementById(req.cfg.formId );
		form.elements["tabCode"].value= tabCode;
		form.elements["crsId"].value= '${param["crsId"]}';
		form.elements["_pageName"].value= tabNm;
		if(tabCode!='01' && tabCode!='777'){
			req.cfg.url    = "<c:url value="/mng/crs/"/>"+tabCode+"/selectListAplcnt.do";
		}
		if(tabCode=='777'){
			req.cfg.url    = "<c:url value="/mng/crs/noti.do"/>";
		}
		
		req.go();
	}
}


</script>

<c:set var="tabCode" value="01"/>
<c:if test="${!empty param['tabCode']}">
<c:set var="tabCode" value="${param['tabCode'] }"/>
</c:if>





<c:if test="${!empty  param['crsId']}">

	<div class="tb_info">
				<ul>
					<li>${detail.agncy.agncyNm}</li>
					<li>${detail.crs.eduYear} 년</li>
					<li><im:cd type="print" codeId="IM0001" selectedCode="${detail.crsMstr.crsGrdCdv}"/>&nbsp;<im:cd type="print" codeId="IM0002" selectedCode="${detail.crsMstr.crsDvsnCdv}"/></li>
					<li>${detail.crs.eduRnd}차</li>
					<li>정원 : ${detail.crs.prsnl}명</li>
					<li><im:cd type="print" codeId="IM0010" selectedCode="${detail.crs.prgrsSttsCdv}"/></li>
				</ul>
			</div>



<c:if test="${empty  loginUserAgncyId}">
<c:set var="arrTabs">01=기본정보,04=자격심사,05=입금관리,99=수료현황,777=설명</c:set>
</c:if>
<c:if test="${!empty  loginUserAgncyId}">
<c:set var="arrTabs">01=기본정보,02=랜덤선정,03=자격심사,05=입금관리,99=수료관리,777=설명</c:set>
</c:if>
<im:cd type="set" codeId="${arrTabs}" var="arrTagCode"/>



<ul class="nav_tabs">
	<c:forEach items="${arrTagCode}" var="trow">
	<li <c:if test="${tabCode eq trow.code }"> class="on"</c:if>><a href="#" onclick="TAB.list('${trow.code}','${trow.codeNm}')">${trow.codeNm}</a></li>
	</c:forEach>
</ul>
</c:if>