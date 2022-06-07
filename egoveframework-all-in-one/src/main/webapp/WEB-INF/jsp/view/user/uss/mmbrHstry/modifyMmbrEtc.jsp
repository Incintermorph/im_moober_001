<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>
<%@ include file="incMmberCode.jspf" %>

<script type="text/javaScript" language="javascript">

<c:set var="ATCH_FILE_ID" value="file_id"/>
var AJAX = {
	fileInfo : {
			uploadFolder : 'ussMmbrEtc',
			fileInputId : '${ATCH_FILE_ID}'
	},
	req : {
		save : null,
		del : null
	},
	init: function(){
   		
   		AJAX.initReq();
		
	},
	initReq: function(){
		this.req.save = imRequest("ajax",{formId: "iMMmbrEtc"});
		this.req.save.cfg.type   = "json";
		this.req.save.cfg.url    =  "<c:url value="/user/mmbrEtc/update.do"/>";
		this.req.save.cfg.message.confirm="<spring:message code="common.update.msg" />";
		this.req.save.cfg.fn.before = function() {
	    	if(!COMMT.chkAttachFile('${ATCH_FILE_ID}')){
	    		return true;
	    	}else{
	    		COMMT.uploadCommonFileUser(AJAX.fileInfo,function(data){
	    			 if (data.result > 0) {
	    				 if(data.result==1){
	    					 //첨부파일 정보 성공인 경우 
		    				 var form =  document.getElementById(AJAX.req.save.cfg.formId);
		    		    	 form.elements["etcEvddocId"].value = data.atchFileId;
	    				 }
	    				 AJAX.req.save.go("continue");
	                    } else {
	                    	COMMT.errorMessage();
	                   }
	    		});
	    		
	    	}
	 	}
		
		
		
		this.req.save.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) { 
	        	REQ.unloadPage('iMMmbrEtc');
	        } else {
	        	COMMT.errorMessage();
	        }
	    };
	    
	    

		AJAX.validator();
		
		 this.req.del = imRequest("ajax");
		    this.req.del.cfg.formId = "iMMmbrEtc";
		    this.req.del.cfg.type   = "json";
		    this.req.del.cfg.url    = "<c:url value="/user/mmbrEtc/delete.do"/>";
		    this.req.del.cfg.message.confirm="<spring:message code="common.delete.msg" />";
		    this.req.del.cfg.fn.complete = function(act, data) {
		        if (data != null && data.result > 0) { 
		        	REQ.unloadPage('iMMmbrEtc');
		        } else {
		        	COMMT.errorMessage();
		        }
		    };	
		
		
	},
	validator : function(){
		
	this.req.save.validator.set({
	    	title  : "기타항목",
            name : "etcItm",
            data : ["!null"]
     });
	
	 
	 this.req.save.validator.set(function(){
		 var form =  document.getElementById(AJAX.req.save.cfg.formId);
    	 if(form.elements["etcEvddocId"].value==''){
	    	if(!COMMT.chkAttachFile('${ATCH_FILE_ID}')){
	    		COMMT.message('기타증빙 파일을 선택해 주세요.');
	    		return false;
	    	}else{
	    		return true;
	    	}
    	 }else{
    			return true;
    	 }
	    });
	 
	 this.req.save.validator.set(function(){
		 if(COMMT.chkAttachFile(AJAX.fileInfo.fileInputId)){
		    	if(!COMMT.chkAttachFileSize(AJAX.fileInfo)){
		    			return false;	
		    	}else{
		    			return true;
		    	}
	    	}else{
	    		return true;
	    	}
	    });
    	
	 
	 
		
	},
	save : function(){
		AJAX.req.save.go();
	},
	del : function(){
		AJAX.req.del.go();
	},
	cancle : function(){
		REQ.unloadCancel('iMMmbrEtc');
	}
}
</script>
				<form name="iMMmbrEtc" id="iMMmbrEtc" method="post" onsubmit="return false;">
				<input type="hidden" name="memberSrl" value="${param['memberSrl']}"/>
				<input type="hidden" name="mmbrEtcId" value="${param['mmbrEtcId']}"/>
					<table class="tbl_row al">
						<colgroup>
							<col style="width:27%;">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th scope="row">기타증빙 내용 *</th>
								<td>
									<input type="text" name="etcItm"  value="${iMMmbrEtc.etcItm}" maxlength="20" class="wide" title="기타항목">
								</td>
							</tr>
							<tr>
								<th scope="row">기타증빙  *</th>
								<td>
									<c:forEach items="${fileList}" var="row">
									<div id="__file_<c:out value="${row.atchFileId}"/>_<c:out value="${row.fileSn}"/>">
									<c:set var="file_Key" value="${row.atchFileId}=${row.fileSn}"/>
									<a href="javascript:COMMT.fn_egov_downFileEnc('<c:out value="${imfunc:encryptString(file_Key)}"/>')"  >
										<c:out value="${row.orignlFileNm}"/>&nbsp;[<c:out value="${imfunc:fileSizeView(row.fileMg)}"/>&nbsp;byte]
									</a> 
									</div>
									</c:forEach>
									<input type="hidden" name="etcEvddocId" value="${iMMmbrEtc.etcEvddocId}"/>
									<input type="file" id="${ATCH_FILE_ID}"   title="파일찾기" accept="${imExtensionsPdfFiles}" />
									
								</td>
							</tr>
						</tbody>
					</table><span style="color: red;">파일 첨부는 pdf만 가능합니다.</span>
					</form>
					<div class="b_box">
						<a href="javascript:;"  onclick="AJAX.cancle();" class="c_btn wt">취소</a>
						<a href="javascript:;"  onclick="AJAX.save();" class="c_btn d_green">저장</a>
						<a href="javascript:;"  onclick="AJAX.del();" class="c_btn gray">삭제</a>
					</div>