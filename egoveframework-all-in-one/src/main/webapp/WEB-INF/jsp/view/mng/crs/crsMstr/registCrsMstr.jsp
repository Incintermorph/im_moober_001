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
	},
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetail";
		this.req.list.cfg.url    = "<c:url value="/mng/crsMstr/selectList.do"/>";
				
		
		this.req.save = imRequest("ajax",{formId: "iMCrsMstr"});
		this.req.save.cfg.type   = "json";
		this.req.save.cfg.url    =  "<c:url value="/mng/crsMstr/insert.do"/>";
		this.req.save.cfg.message.confirm="<spring:message code="common.regist.msg" />";
		this.req.save.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) { 
	        	IMGLBObject.request.list();
	        } else {
	        	COMMT.errorMessage();
	        }
	    };	
	    this.req.save.validator.set(function(){
	    	var form = document.iMCrsMstr;
	    	return validateIMCrsMstr(form);
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
	}
}


$(document).ready(function(){
	REQ.init();
});

</script>

<%@ include file="incCrsMstr.jsp" %>


<validator:javascript formName="iMCrsMstr" staticJavascript="false" xhtml="true" cdata="false"/>

<div class="cb_bar right">
	<im:pageBtn type="R" reqName="REQ"></im:pageBtn>
</div>

<form:form commandName="iMCrsMstr" name="iMCrsMstr" method="post" onsubmit="return false;">
<table class="tbl_row">
	<caption>기관관리자 상세정보</caption>
	<colgroup>
		<col style="width:200px;">
		<col>
	</colgroup>
	<tbody>
		<tr>
			<th scope="row"><spring:message code="iMCrsMstr.crsGrd"/><span class="c_red">*</span></th>
			<td><im:cd type="radio" codeId="IM0001" name="crsGrdCdv"/></td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="iMCrsMstr.crsDvsn"/><span class="c_red">*</span></th>
			<td><im:cd type="radio" codeId="IM0002" name="crsDvsnCdv"/></td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="iMCrsMstr.ttnfee"/><span class="c_red">*</span></th>
			<td><form:input path="ttnfee" cssStyle="text-align: right;" maxlength="7"/></td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="iMCrsMstr.eduHrs"/><span class="c_red">*</span></th>
			<td><form:input path="eduHrs" cssStyle="text-align: right;" maxlength="3"/></td>
		</tr>
		
		<tr>
			<th scope="row"><spring:message code="iMCrsMstr.atndQlfcStndrd"/></th>
			<td><input type="hidden" name="cmmnDescRefNms" value="atndQlfcStndrd"/>
				<textarea name="atndQlfcStndrd" id="" cols="" rows="" style="height:100px;"></textarea>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="iMCrsMstr.qlfcLmt"/></th>
			<td>
				<input type="hidden" name="cmmnDescRefNms" value="qlfcLmt"/>
				<textarea name="qlfcLmt" id="" cols="" rows="" style="height:100px;"></textarea>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="iMCrsMstr.evlPlan"/></th>
			<td><input type="hidden" name="cmmnDescRefNms" value="evlPlan"/>
				<textarea name="evlPlan" id="" cols="" rows="" style="height:100px;"></textarea>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="iMCrsMstr.cmpltnStndrd"/></th>
			<td><input type="hidden" name="cmmnDescRefNms" value="cmpltnStndrd"/>
				<textarea name="cmpltnStndrd" id="" cols="" rows="" style="height:100px;"></textarea>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="iMCrsMstr.stts"/><span class="c_red">*</span></th>
			<td><im:cd type="radio" codeId="IM0003" defaultSelectedCode="STTS_01" name="sttsCdv"/></td>
		</tr>
	</tbody>
</table>
</form:form>
<div class="b_box right">
	<im:pageBtn type="R" reqName="REQ"></im:pageBtn>
</div>