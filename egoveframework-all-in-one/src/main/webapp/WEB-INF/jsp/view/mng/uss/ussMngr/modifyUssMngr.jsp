<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>

<script type="text/javascript" >


var REQ = {
	req : {
		list : null,
		save : null,
		del : null,
	},
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetail";
		this.req.list.cfg.url    = "<c:url value="/mng/ussMngr/"/>${condition.scAuthorCode}/selectList.do";
				
		
		this.req.save = imRequest("ajax",{formId: "iMUssMngr"});
		this.req.save.cfg.type   = "json";
		this.req.save.cfg.url    =  "<c:url value="/mng/ussMngr/update.do"/>";
		this.req.save.cfg.message.confirm="<spring:message code="common.update.msg" />";
		this.req.save.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) { 
	        	IMGLBObject.request.list();
	        } else {
	        	COMMT.errorMessage();
	        }
	    };	
	    this.req.save.validator.set(function(){
	    	var form = document.iMUssMngr;
	    	return validateIMUssMngr(form);
	    });
	    
	    
	    this.req.del = imRequest("ajax");
	    this.req.del.cfg.formId = "iMUssMngr";
	    this.req.del.cfg.type   = "json";
	    this.req.del.cfg.url    = "<c:url value="/mng/ussMngr/delete.do"/>";
	    this.req.del.cfg.message.confirm="<spring:message code="common.delete.msg" />";
	    this.req.del.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) { 
	        	IMGLBObject.request.page(1);
	        } else {
	        	COMMT.errorMessage();
	        }
	    };	
	
	},
	page :  function(pageNo){
		var form =  document.getElementById(this.req.list.cfg.formId);
		form.elements["currentPageNo"].value= pageNo;
		this.req.list.go();
	},
	list :  function(){
		this.req.list.go();
	},
	save :  function(){
		IMGLBObject.request=this;
		this.req.save.go();
	},
	del :  function(){
		IMGLBObject.request=this;
		this.req.del.go();
	},
	reload :  function(){
		var req = imRequest();
		req.cfg.formId = "FormPageDetail";
		req.cfg.url    = "<c:url value="/mng/ussMngr/modify.do"/>";
		var form =  document.getElementById(req.cfg.formId );
		form.elements["esntlId"].value= '${param['esntlId']}';
		req.go(); 
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
<ul class="nav_tabs">
	<li class="on"><a href="#">상세 수정</a></li>
	<li><a href="#" onclick="REQ.modifyPermit();return false;">허용관리</a></li>
</ul>

<%@ include file="incUssMngr.jsp" %>


<validator:javascript formName="iMUssMngr" staticJavascript="false" xhtml="true" cdata="false"/>

<div class="cb_bar right">
	<im:pageBtn type="M" reqName="REQ">
	
	<a href="#" onclick="REQ.modifyPassword();return false;" class="ad_btn bk"><spring:message code="comUssUmt.userManageModifyBtn.passwordChange" /></a>
	<a href="#" onclick="REQ.updateLockIncorrect();return false;" class="ad_btn bk"><spring:message code="comUssUmt.common.lockAtBtn" /></a>
	</im:pageBtn>
</div>

<form:form commandName="iMUssMngr" name="iMUssMngr" method="post" onsubmit="return false;">
<input name="password"   type="hidden" value="ex~Test#$12" />
<form:hidden path="esntlId"/>
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
				<td>
					<c:set var="groupId" />
					<c:set var="groupNm" />
					<c:if test="${condition.scAuthorCodeUpper eq  'ROLE_ADMIN'}">
						<c:forEach items="${agncyList}" var="row">
						<c:if test="${row.agncy.agncyId eq  iMUssMngr.groupId}">
						<c:set var="groupId" value="${row.agncy.agncyId}" />
						<c:set var="groupNm" value="${row.agncy.agncyNm}"/>
						</c:if>
					</c:forEach>
				</c:if>
				<c:if test="${empty  groupId}">
					<select name="groupId">
					<option value="">선택</option>
					<c:forEach items="${agncyList}" var="row">
					<option value="${row.agncy.agncyId}"
					<c:if test="${row.agncy.agncyId eq  iMUssMngr.groupId }">
					selected="selected"
					</c:if> 
					>${row.agncy.agncyNm}</option>
					</c:forEach>
				</select>
				</c:if>
				<c:if test="${!empty  groupId}">
				<input type="hidden" name="groupId" value="${groupId}" />
				${groupNm}
				</c:if>
				</td>
			</tr>
			
			<tr>
				<th scope="row"><spring:message code="iMUssMngr.userNm"/><span class="c_red">*</span></th>
				<td><input type="text" name="emplyrNm" value="${iMUssMngr.userNm}">
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
			<tr>
				<th scope="row"><spring:message code="iMUssMngr.emplyrSttus"/><span class="c_red">*</span></th>
				<td>
					<select name="emplyrSttusCode" >
						<im:cd type="option" codeId="COM013"  except="D"  selectedCode="${iMUssMngr.emplyrSttusCode}"/>
					</select>
				</td>
			</tr>
			<!-- 로그인인증제한여부 -->
			<c:set var="title"><spring:message code="comUssUmt.common.lockAt"/></c:set>
			<tr>
				<th><label for="lockAt">${title}</label></th>
				<td class="left">
				<c:if test="${iMUssMngr.lockAt eq 'Y'}">예</c:if>
				<c:if test="${iMUssMngr.lockAt ne 'Y'}">아니오</c:if>
				</td>
			</tr>
	</tbody>
</table>
</form:form>
<div class="b_box right">
	<im:pageBtn type="M" reqName="REQ">
	<a href="#" onclick="REQ.modifyPassword();return false;" class="ad_btn bk"><spring:message code="comUssUmt.userManageModifyBtn.passwordChange" /></a>
	<a href="#" onclick="REQ.updateLockIncorrect();return false;" class="ad_btn bk"><spring:message code="comUssUmt.common.lockAtBtn" /></a>
	</im:pageBtn>
</div>