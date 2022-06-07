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
		overchek : null,
	},
	overchekid : null,
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetail";
		this.req.list.cfg.url    = "<c:url value="/mng/ussMngr/"/>${condition.scAuthorCode}/selectList.do";
				
		
		this.req.save = imRequest("ajax",{formId: "iMUssMngr"});
		this.req.save.cfg.type   = "json";
		this.req.save.cfg.url    =  "<c:url value="/mng/ussMngr/insert.do"/>";
		this.req.save.cfg.message.confirm="<spring:message code="common.regist.msg" />";
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
	    this.req.save.validator.set(function(){
	    	var form =  document.getElementById(REQ.req.save.cfg.formId);
	    	if(REQ.overchekid == form.elements["emplyrId"].value){
	    		return true;
	    	}else{
	    		COMMT.message('아이디 중복 확인 필요합니다.');
	    		return false;
	    	}
	    });
	    
	    this.req.save.validator.set(function(){
	    	var form =  document.getElementById(REQ.req.save.cfg.formId)
	    	if(form.elements["password"].value != form.elements["password2"].value){
	            alert("<spring:message code="fail.user.passwordUpdate2" />");
	            return false;
	        }else{
	        	return true;
	        }
	    });
	    
	    
        this.req.overchek = imRequest("ajax",{formId: "iMUssMngr"});
   		this.req.overchek.cfg.type   = "json";
   		this.req.overchek.cfg.url    =  "<c:url value="/mng/ussMngr/overchek.do"/>";
   		this.req.overchek.cfg.fn.complete = function(act, data) {
   	        if (data != null && data.result >= 0) { 
   	        	if(data.result==0){
   	        		var form =  document.getElementById(REQ.req.overchek.cfg.formId);
   	        		REQ.overchekid=form.elements["emplyrId"].value;
   	        		COMMT.message('사용 가능한 아이디 입니다.');
   	        	}else{
   	        		REQ.overchekid=null;
   	        		COMMT.message('이미 사용중인 아이디 입니다.');
   	        	}
   	        } else {
   	        	COMMT.errorMessage();
   	        }
   	    };
   	    
   	    this.req.overchek.validator.set({
   	    	title : "<spring:message code="iMUssMngr.emplyrId"/>",
               name : "emplyrId",
               data : ["!null"]
        });
	
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
	overchek :  function(){
		IMGLBObject.request=this;
		this.req.overchek.go();
	}
}


$(document).ready(function(){
	REQ.init();
});

</script>

<%@ include file="incUssMngr.jsp" %>


<validator:javascript formName="iMUssMngr" staticJavascript="false" xhtml="true" cdata="false"/>

<div class="cb_bar right">
	<im:pageBtn type="R" reqName="REQ"></im:pageBtn>
</div>

<form:form commandName="iMUssMngr" name="iMUssMngr" method="post" onsubmit="return false;">
<input type="hidden" name="authorCode"  value="${condition.scAuthorCodeUpper}"/>
<table class="tbl_row">
	<caption>업무사용자정보 상세정보</caption>
	<colgroup>
		<col style="width:200px;">
		<col>
	</colgroup>
	<tbody>
			<tr>
				<th scope="row"><spring:message code="iMUssMngr.emplyrId"/><span class="c_red">*</span></th>
				<td><form:input path="emplyrId"/><a href="#" onclick="REQ.overchek()" class="ml ad_btn gray">중복 확인</a></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMUssMngr.password"/><span class="c_red">*</span></th>
				<td><form:password path="password"/>
				<div><spring:message code="info.password.rule.password1" /></div> 
					<div><spring:message code="info.password.rule.pwdcheckcomb3" /></div> 
					<div><spring:message code="info.password.rule.pwdcheckseries" /></div>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="comUssUmt.deptUserManageRegist.passConfirm"/><span class="c_red">*</span></th>
				<td><input name="password2" id="password2"  type="password" /></td>
			</tr>
			
			<tr>
				<th scope="row"><spring:message code="iMUssMngr.group"/><span class="c_red">*</span></th>
				<td>
				<c:set var="groupId" />
				<c:set var="groupNm" />
				<%--전체 관리자는 국가환경 센터 고정으로 처리함  --%>
				<c:if test="${condition.scAuthorCodeUpper eq  'ROLE_ADMIN'}">
						<c:forEach items="${agncyList}" var="row">
						<c:if test="${row.agncy.linkCode eq '200632'}">
						<c:set var="groupId" value="${row.agncy.agncyId}" />
						<c:set var="groupNm" value="${row.agncy.agncyNm}"/>
						</c:if>
					</c:forEach>
				</c:if>
					
				<c:if test="${empty  groupId}">
				<select name="groupId">
					<option value="">선택</option>
					<c:forEach items="${agncyList}" var="row">
					<option value="${row.agncy.agncyId}">${row.agncy.agncyNm}</option>
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
				<td><form:input path="emplyrNm"/></td>
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
						<im:cd type="option" codeId="COM013"  except="D"  />
					</select>
				</td>
			</tr>
	</tbody>
</table>
</form:form>
<div class="b_box right">
	<im:pageBtn type="R" reqName="REQ"></im:pageBtn>
</div>