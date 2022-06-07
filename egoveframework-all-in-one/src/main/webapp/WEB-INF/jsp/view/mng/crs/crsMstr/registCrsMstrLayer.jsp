<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

등록 페이지
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>

<script type="text/javascript" >

doSave = function(){
	var form = document.iMCrsMstr;
	if(!validateIMCrsMstr(form)){
		return;
	}else{
		//form.submit();
		doSaveAjax('iMCrsMstr');
	}
}

doSaveAjax = function(formId){

	var req = imRequest("ajax");
	req.cfg.formId = "iMCrsMstr";
	req.cfg.type   = "json";
	req.cfg.url    = "<c:url value="/mng/crsMstr/insert.do"/>";
	req.cfg.message.confirm="<spring:message code="common.regist.msg" />";
	req.cfg.fn.complete = function(act, data) {
        if (data != null && data.result > 0) { 
        	 var callback = COMMT.getLayerCallback(parent, "<c:out value="${param['imCallBack']}"/>");
             if (typeof callback === "function") {
                // callback.call(this, returnValue);
           	  callback.call(this);
             }
             COMMT.callCloseLayer(parent);
        } else {
            alert("error");
        }
    };	
	req.go();
	
	
}


var goPage=  function(pageNo){
	var form =  document.getElementById("FormSelect");
	form.elements["currentPageNo"].value= pageNo;
	goSelectList();
}
var goSelectList =  function(){
	document.getElementById("FormSelect").action="/mng/crsMstr/selectList.do";
	document.getElementById("FormSelect").submit();
}


var callClose = function(){
	COMMT.callCloseLayer(parent);
}

</script>





<form name="FormSelect" id="FormSelect" method="post" onsubmit="return false;" action="/mng/crsMstr/selectList.do">
	<input type="hidden" name="currentPageNo"  value="<c:out value="${condition.currentPageNo}"/>" />
	<input type="hidden" name="recordCountPerPage"      value="<c:out value="${condition.recordCountPerPage}"/>" />
	<input type="hidden" name="pageSize"      value="<c:out value="${condition.pageSize}"/>" />
	<input type="hidden" name="orderbyKey"      value="<c:out value="${condition.orderbyKey}"/>" />
</form>



<validator:javascript formName="iMCrsMstr" staticJavascript="false" xhtml="true" cdata="false"/>

<form:form commandName="iMCrsMstr" name="iMCrsMstr" method="post" onsubmit="return false;">
<form:input path="crsMstrNm"/><br>
<input type="datetime-local"/><br>
<input type="date"/><br>
<input type="color"/><br>
<input type="email"/><br>
<input type="week"/><br>
<input type="search"/><br>

</form:form>
<br/>
<a href="#" onclick="callClose();return false;"> 닫기</a>

<a href="javascript:doSave()"> 저장</a>