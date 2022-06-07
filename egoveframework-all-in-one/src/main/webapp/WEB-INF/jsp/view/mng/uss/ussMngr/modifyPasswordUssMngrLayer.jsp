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
		this.req.save = imRequest("ajax",{formId: "iMUssMngrPasswd"});
		this.req.save.cfg.type   = "json";
		this.req.save.cfg.url    =  "<c:url value="/mng/ussMngr/password/update.do"/>";
		this.req.save.cfg.message.confirm="<spring:message code="common.update.msg" />";
		this.req.save.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) { 
	        	COMMT.callCloseLayer(parent);
	        } else {
	        	COMMT.errorMessage();
	        }
	    };	
	    this.req.save.validator.set(function(){
	    	var form = document.iMUssMngrPasswd;
	    	return validateIMUssMngrPasswd(form);
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
	    
	    
	
	},
	save :  function(){
		this.req.save.go();
	},
	close:  function(){
		COMMT.callCloseLayer(parent);
	}
}


$(document).ready(function(){
	REQ.init();
});

</script>

<validator:javascript formName="iMUssMngrPasswd" staticJavascript="false" xhtml="true" cdata="false"/>

<form:form commandName="iMUssMngrPasswd" name="iMUssMngrPasswd" method="post" onsubmit="return false;">
<form:hidden path="esntlId"/>
<input type="hidden" name="userSe" value="USR"/>
<table class="tbl_row">
	<caption> 상세정보</caption>
	<colgroup>
		<col style="width:120px;">
		<col>
	</colgroup>
	<tbody>
			<tr>
				<th scope="row"><spring:message code="iMUssMngr.emplyrId"/><span class="c_red">*</span></th>
				<td><form:hidden path="emplyrId" /> ${iMUssMngrPasswd.emplyrId}</td>
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
	</tbody>
</table>			
</form:form>
<div class="b_box">
	<a href="#" onclick="REQ.save();return false;" class="ad_btn green">저장</a>
	<a href="#" onclick="REQ.close();return false;" class="ad_btn bk">닫기</a>
</div>