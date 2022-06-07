<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>

<script type="text/javascript" >


var REQ = {
	req : {
		save : null
	},
	init : function(){
	
		this.req.save = imRequest("ajax",{formId: "iMUssMngr"});
		this.req.save.cfg.type   = "json";
		this.req.save.cfg.url    =  "<c:url value="/mng/ussMngr/update.do"/>";
		this.req.save.cfg.message.confirm="<spring:message code="common.update.msg" />";
		this.req.save.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) { 
	        	REQ.close();
	        } else {
	        	COMMT.errorMessage();
	        }
	    };	
	    this.req.save.validator.set(function(){
	    	var form = document.iMUssMngr;
	    	return validateIMUssMngr(form);
	    });
	    
	    	
	
	},
	save :  function(){
		IMGLBObject.request=this;
		this.req.save.go();
	},

	close:  function(){
		COMMT.callCloseLayer(parent);
	},
	modifyPermit :  function(){
		var req = imRequest();
		req.cfg.formId = "FormPageDetail";
		req.cfg.url    = "<c:url value="/mng/ussMngr/modifyPermit.do"/>";
		var form =  document.getElementById(req.cfg.formId );
		form.elements["_pageName"].value= '허용관리';
		form.elements["esntlId"].value= '${param['esntlId']}';
		req.go(); 
	},
	updateLockIncorrect:function(){
		   var req = imRequest("ajax");
		   req.cfg.formId = "iMUssMngr";
		   req.cfg.type   = "json";
		   req.cfg.url    = "<c:url value="/mng/ussMngr/updateLockIncorrect.do"/>";
		   req.cfg.message.confirm="<spring:message code="comUssUmt.common.lockAtConfirm" />";
		   req.cfg.fn.complete = function(act, data) {
		        if (data != null && data.result > 0) { 
		        	REQ.reload();
		        } else {
		        	COMMT.errorMessage();
		        }
		    };	
		   req.go();
			
	},
	modifyPassword:function(){
		var req = imRequest("layer");
		req.cfg.url    = "<c:url value="/mng/ussMngr/modify/passwordLayer.do"/>";
		req.cfg.options.title="<spring:message code="comUssUmt.userManageModifyBtn.passwordChange" />";
		req.cfg.formId = "iMUssMngr";
		req.cfg.options.scroll="hidden";
		req.cfg.options.width=600;
		req.cfg.options.height=300;
		 req.go();
			
	}
}


$(document).ready(function(){
	REQ.init();
});

</script>




<validator:javascript formName="iMUssMngr" staticJavascript="false" xhtml="true" cdata="false"/>

<form:form commandName="iMUssMngr" name="iMUssMngr" method="post" onsubmit="return false;">
<input name="password"   type="hidden" value="ex~Test#$12" />
<form:hidden path="esntlId"/>
<form:hidden path="emplyrSttusCode"/>
<table class="tbl_row">
	<caption>업무사용자정보 상세정보</caption>
	<colgroup>
		<col style="width:200px;">
		<col>
	</colgroup>
	<tbody>
			<tr>
				<th scope="row"><spring:message code="iMUssMngr.emplyrId"/><span class="c_red">*</span></th>
				<td><form:hidden path="emplyrId" readonly=""/> ${iMUssMngr.emplyrId}</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMUssMngr.group"/><span class="c_red">*</span></th>
				<td><form:hidden path="groupId"/>
					<c:forEach items="${agncyList}" var="row">
					<c:if test="${row.agncy.agncyId eq  iMUssMngr.groupId }">
					${row.agncy.agncyNm}
					</c:if>
					</c:forEach>
				</select>
				
				</td>
			</tr>
			
			<tr>
				<th scope="row"><spring:message code="iMUssMngr.userNm"/><span class="c_red">*</span></th>
				<td>${iMUssMngr.userNm}
				<input type="hidden" name="emplyrNm" value="${iMUssMngr.userNm}">
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMUssMngr.ofcpsNm"/><span class="c_red">*</span></th>
				<td><form:input path="ofcpsNm"/></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMUssMngr.telno"/><span class="c_red">*</span></th>
				<td><form:input path="areaNo" size="4" maxlength="4"/>-<form:input path="houseMiddleTelno" size="4" maxlength="4"/>-<form:input path="houseEndTelno" size="4" maxlength="4"/></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMUssMngr.emailAdres"/><span class="c_red">*</span></th>
				<td><form:input path="emailAdres"/></td>
			</tr>
	</tbody>
</table>
</form:form>


<div class="b_box">
	<a href="#" onclick="REQ.save();return false;" class="ad_btn green">저장</a>
	<a href="#" onclick="REQ.close();return false;" class="ad_btn bk">닫기</a>
</div>