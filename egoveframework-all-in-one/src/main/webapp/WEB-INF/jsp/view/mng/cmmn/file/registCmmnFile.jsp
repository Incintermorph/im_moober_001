<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>

<c:set var="ATCH_FILE_ID" value="file_id"/>
<script type="text/javascript" >


var REQ = {
	fileInfo : {
			uploadFolder : 'cmmnFile',
			fileInputId : '${ATCH_FILE_ID}'
	},
	req : {
		list : null,
		save : null,
	},
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetail";
		this.req.list.cfg.url    = "<c:url value="/mng/cmmnFile/selectList.do"/>";
				
		
		this.req.save = imRequest("ajax",{formId: "iMCmmnFile"});
		this.req.save.cfg.type   = "json";
		this.req.save.cfg.url    =  "<c:url value="/mng/cmmnFile/insert.do"/>";
		this.req.save.cfg.message.confirm="<spring:message code="common.regist.msg" />";
		this.req.save.cfg.fn.before = function() {
	    	if(!COMMT.chkAttachFile('${ATCH_FILE_ID}')){
	    		return true;
	    	}else{
	    		COMMT.uploadCommonFile(REQ.fileInfo,function(data){
	    			 if (data.result > 0) {
	    				 if(data.result==1){
	    					 //첨부파일 정보 성공인 경우 
		    				 var form =  document.getElementById(REQ.req.save.cfg.formId);
		    		    	 form.elements["fileId"].value = data.atchFileId;
	    				 }
	    				 REQ.req.save.go("continue");
	                    } else {
	                    	COMMT.errorMessage();
	                   }
	    		});
	    		
	    	}
	 	}
		this.req.save.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) { 
	        	IMGLBObject.request.list();
	        } else {
	        	COMMT.errorMessage();
	        }
	    };	
	    this.req.save.validator.set(function(){
	    	var form = document.iMCmmnFile;
	    	return validateIMCmmnFile(form);
	    });
	    
	    this.req.save.validator.set(function(){
	    	if(!COMMT.chkAttachFile('${ATCH_FILE_ID}')){
	    		COMMT.message('첨부파일을 추가해 주세요.');
	    		return true;
	    	}else{
	    		return true;
	    	}
	    });
 		 this.req.save.validator.set(function(){
			 if(COMMT.chkAttachFile(REQ.fileInfo.fileInputId)){
			    	if(!COMMT.chkAttachFileSize(REQ.fileInfo)){
			    			return false;	
			    	}else{
			    			return true;
			    	}
		    	}else{
		    		return true;
		    	}
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

<%@ include file="incCmmnFile.jsp" %>


<validator:javascript formName="iMCmmnFile" staticJavascript="false" xhtml="true" cdata="false"/>

<div class="cb_bar right">
	<im:pageBtn type="R" reqName="REQ"></im:pageBtn>
</div>

<form:form commandName="iMCmmnFile" name="iMCmmnFile" method="post" onsubmit="return false;">
<table class="tbl_row">
	<caption>공통파일관리 상세정보</caption>
	<colgroup>
		<col style="width:200px;">
		<col>
	</colgroup>
	<tbody>
			
			<tr>
				<th scope="row"><spring:message code="iMCmmnFile.fileTpc"/><span class="c_red">*</span></th>
				<td><form:input path="fileTpc" maxlength="20"/></td>
			</tr>	
			<tr>
				<th scope="row"><spring:message code="iMCmmnFile.fileNm"/><span class="c_red">*</span></th>
				<td><form:input path="fileNm" maxlength="20"/><br>확장자는 제외 하고 작성해주세요.</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMCmmnFile.file"/><span class="c_red">*</span></th>
				<td><form:hidden path="fileId"/>
				<input type="file" id="${ATCH_FILE_ID}" value="${ATCH_FILE_ID}" name="FileId" title="파일찾기" accept="${imExtensionsFiles}" />
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMCmmnFile.mmbrAccss"/><span class="c_red">*</span></th>
				<td>
				<im:cd type="radio" codeId="${mmbrAccssYnArr}" selectedCode="N" name="mmbrAccssYn"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMCmmnFile.rmks"/></th>
				<td><form:input path="rmks" maxlength="300"/></td>
			</tr>

	</tbody>
</table>
</form:form>
<div class="b_box right">
	<im:pageBtn type="R" reqName="REQ"></im:pageBtn>
</div>