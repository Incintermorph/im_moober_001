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
		this.req.list.cfg.url    = "<c:url value="/mng/crsAplyTrnsf/selectList.do"/>";
				
		
		this.req.save = imRequest("ajax",{formId: "iMCrsAplyTrnsf"});
		this.req.save.cfg.type   = "json";
		this.req.save.cfg.url    =  "<c:url value="/mng/crsAplyTrnsf/update.do"/>";
		this.req.save.cfg.message.confirm="<spring:message code="common.update.msg" />";
		this.req.save.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) { 
	        	IMGLBObject.request.list();
	        } else {
	        	COMMT.errorMessage();
	        }
	    };	
	    this.req.save.validator.set(function(){
	    	var form = document.iMCrsAplyTrnsf;
	    	return validateIMCrsAplyTrnsf(form);
	    });
	    
	    
	    this.req.del = imRequest("ajax");
	    this.req.del.cfg.formId = "iMCrsAplyTrnsf";
	    this.req.del.cfg.type   = "json";
	    this.req.del.cfg.url    = "<c:url value="/mng/crsAplyTrnsf/delete.do"/>";
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
	}
}


$(document).ready(function(){
	REQ.init();
});

</script>

<%@ include file="incCrsAplyTrnsf.jsp" %>


<validator:javascript formName="iMCrsAplyTrnsf" staticJavascript="false" xhtml="true" cdata="false"/>

<div class="cb_bar right">
	<im:pageBtn type="M" reqName="REQ"></im:pageBtn>
</div>

<form:form commandName="iMCrsAplyTrnsf" name="iMCrsAplyTrnsf" method="post" onsubmit="return false;">
<table class="tbl_row">
	<caption>과정신청이관 상세정보</caption>
	<colgroup>
		<col style="width:200px;">
		<col>
	</colgroup>
	<tbody>
			<tr>
				<th scope="row"><spring:message code="iMCrsAplyTrnsf.crsAplyTrnsfId"/><span class="c_red">*</span></th>
				<td><form:input path="crsAplyTrnsfId"/></td>
			</tr>
	<tr>
				<th scope="row"><spring:message code="iMCrsAplyTrnsf.crsAplyTrnsfNo"/><span class="c_red">*</span></th>
				<td><form:input path="crsAplyTrnsfNo"/></td>
			</tr>
	<tr>
				<th scope="row"><spring:message code="iMCrsAplyTrnsf.crsNm"/><span class="c_red">*</span></th>
				<td><form:input path="crsNm"/></td>
			</tr>
	<tr>
				<th scope="row"><spring:message code="iMCrsAplyTrnsf.mmbrNm"/><span class="c_red">*</span></th>
				<td><form:input path="mmbrNm"/></td>
			</tr>
	<tr>
				<th scope="row"><spring:message code="iMCrsAplyTrnsf.mmbrId"/><span class="c_red">*</span></th>
				<td><form:input path="mmbrId"/></td>
			</tr>
	<tr>
				<th scope="row"><spring:message code="iMCrsAplyTrnsf.telno"/><span class="c_red">*</span></th>
				<td><form:input path="telno"/></td>
			</tr>
	<tr>
				<th scope="row"><spring:message code="iMCrsAplyTrnsf.eml"/><span class="c_red">*</span></th>
				<td><form:input path="eml"/></td>
			</tr>
	<tr>
				<th scope="row"><spring:message code="iMCrsAplyTrnsf.brdt"/><span class="c_red">*</span></th>
				<td><form:input path="brdt"/></td>
			</tr>
	<tr>
				<th scope="row"><spring:message code="iMCrsAplyTrnsf.agncyNm"/><span class="c_red">*</span></th>
				<td><form:input path="agncyNm"/></td>
			</tr>
	<tr>
				<th scope="row"><spring:message code="iMCrsAplyTrnsf.agncyCode"/><span class="c_red">*</span></th>
				<td><form:input path="agncyCode"/></td>
			</tr>
	<tr>
				<th scope="row"><spring:message code="iMCrsAplyTrnsf.eduYear"/><span class="c_red">*</span></th>
				<td><form:input path="eduYear"/></td>
			</tr>
	<tr>
				<th scope="row"><spring:message code="iMCrsAplyTrnsf.eduRnd"/><span class="c_red">*</span></th>
				<td><form:input path="eduRnd"/></td>
			</tr>
	<tr>
				<th scope="row"><spring:message code="iMCrsAplyTrnsf.crsGrd"/><span class="c_red">*</span></th>
				<td><form:input path="crsGrd"/></td>
			</tr>
	<tr>
				<th scope="row"><spring:message code="iMCrsAplyTrnsf.trnsfYn"/><span class="c_red">*</span></th>
				<td><form:input path="trnsfYn"/></td>
			</tr>
	<tr>
				<th scope="row"><spring:message code="iMCrsAplyTrnsf.trnsfYmd"/><span class="c_red">*</span></th>
				<td><form:input path="trnsfYmd"/></td>
			</tr>
	<tr>
				<th scope="row"><spring:message code="iMCrsAplyTrnsf.trnsfAplyId"/><span class="c_red">*</span></th>
				<td><form:input path="trnsfAplyId"/></td>
			</tr>

	</tbody>
</table>
</form:form>
<div class="b_box right">
	<im:pageBtn type="M" reqName="REQ"></im:pageBtn>
</div>