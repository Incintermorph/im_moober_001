<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>
<%@ include file="incMmberCode.jspf" %>

<script type="text/javaScript" language="javascript">

<c:set var="ATCH_FILE_ID" value="file_id"/>
var AJAX = {
	fileInfo : {
			uploadFolder : 'ussExptSbj',
			fileInputId : '${ATCH_FILE_ID}'
	},
	arrSub:null,	
	loadYN:false,	
	req : {
		save : null,
		del : null,
	},
	init: function(){
		this.req.save = imRequest("ajax",{formId: "iMMmbrExptSbj"});
		this.req.save.cfg.type   = "json";
		this.req.save.cfg.url    =  "<c:url value="/user/mmbrExptSbj/update.do"/>";
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
		    		    	 form.elements["fileId"].value = data.atchFileId;
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
	        	REQ.unloadPage('iMMmbrExptSbjOnline');
	        } else {
	        	COMMT.errorMessage();
	        }
	    };
	    

		 this.req.save.validator.set({
		    	title  : "과목계열",
	            name : "lineSbjCdv",
	            data : ["!null"]
	        });
		
		 this.req.save.validator.set({
		    	title  : "인정과목",
	            name : "certSbjCdv",
	            data : ["!null"]
	        });
		
		 this.req.save.validator.set({
		    	title  : "수강희망여부",
	            name : "studyDsrYn",
	            data : ["!null"]
	      });
		 
		 
		 this.req.save.validator.set(function(){
			 var form =  document.getElementById(AJAX.req.save.cfg.formId);
	    	 if(form.elements["fileId"].value==''){
		    	if(!COMMT.chkAttachFile('${ATCH_FILE_ID}')){
		    		COMMT.message('증빙서류 파일을 선택해 주세요.');
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
		 
	    
	    AJAX.arrSub =  new Array();
	    <c:forEach items="${certSbjCdvArr}" var="row" varStatus="i">
	    AJAX.arrSub[AJAX.arrSub.length]={
	    		code : '<c:out value="${row.code}"/>',
	    		codeNm : '<c:out value="${row.codeNm}"/>'
				};
	    
	    </c:forEach>
	   // alert(AJAX.arrSub.length);
	   AJAX.onchangeLine('${iMMmbrExptSbj.lineSbjCdv}');
	   
	   this.req.del = imRequest("ajax");
	    this.req.del.cfg.formId = "iMMmbrExptSbj";
	    this.req.del.cfg.type   = "json";
	    this.req.del.cfg.url    = "<c:url value="/user/mmbrExptSbj/delete.do"/>";
	    this.req.del.cfg.message.confirm="<spring:message code="common.delete.msg" />";
	    this.req.del.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) { 
	        	REQ.unloadPage('iMMmbrExptSbj');
	        } else {
	        	COMMT.errorMessage();
	        }
	    };	
	},
	save : function(){
		AJAX.req.save.go();
	},
	del : function(){
		AJAX.req.del.go();
	},
	cancle : function(){
		REQ.unloadCancel('iMMmbrExptSbj');
	},
	onchangeLine : function(lineValue){
		var form =  document.getElementById("iMMmbrExptSbj");
		var selectBox = form.elements["certSbjCdv"];
	    selectBox.options.length=1;
	    for(var i=0;i<AJAX.arrSub.length;i++){
	    	//console.log(AJAX.arrSub[i].code.substr(0,3));
	    	if(lineValue==AJAX.arrSub[i].code.substr(0,3)){
		    	var otp=document.createElement("OPTION");
		    	otp.text=AJAX.arrSub[i].codeNm;
		    	otp.value=AJAX.arrSub[i].code;
		    	selectBox.add(otp);
	    	}
	    }
	    if(!AJAX.loadYN){
	    	AJAX.loadYN=true;
	    	form.elements["certSbjCdv"].value='${iMMmbrExptSbj.certSbjCdv}';
	    }
	}
}
</script>
				<form name="iMMmbrHstry" id="iMMmbrExptSbj" method="post" onsubmit="return false;">
				<input type="hidden" name="memberSrl" value="${param['memberSrl']}"/>
				<input type="hidden" name="mmbrExptSbjId" value="${iMMmbrExptSbj.mmbrExptSbjId}"/>
				<input type="hidden" name="exempSbjDvsn" value="02"/>
				<input type="hidden" name="studyDsrYn" value="M"/>

					<table class="tbl_row al">
						<colgroup>
							<col style="width:26%;">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th scope="row">과목계열 *</th>
								<td>
									<select name="lineSbjCdv"  onchange="AJAX.onchangeLine(this.value)" title="과목계열">
										<option value="">선택</option>
										<im:cd type="option" codeId="IM0018" selectedCode="${iMMmbrExptSbj.lineSbjCdv}" />
									</select>									
								</td>
							</tr>
							<tr>
								<th scope="row">수료 인정과목 *</th>
								<td>
									<select name="certSbjCdv" id="__certSbjCdv" title="인정과목">
										<option value="">선택</option>
									</select>
								</td>
							</tr>							
							<tr>
								<th scope="row">증빙서류<br>수료증 *</th>
								<td>
									<c:forEach items="${fileList}" var="row">
									<div id="__file_<c:out value="${row.atchFileId}"/>_<c:out value="${row.fileSn}"/>">
									<c:set var="file_Key" value="${row.atchFileId}=${row.fileSn}"/>
									<a href="javascript:COMMT.fn_egov_downFileEnc('<c:out value="${imfunc:encryptString(file_Key)}"/>')"  >
										<c:out value="${row.orignlFileNm}"/>&nbsp;[<c:out value="${imfunc:fileSizeView(row.fileMg)}"/>&nbsp;byte]
									</a> 
									</div>
									</c:forEach>
									<input type="hidden" name="fileId" value="${iMMmbrExptSbj.fileId}"/>
										<input type="file" id="${ATCH_FILE_ID}" value="${ATCH_FILE_ID}" name="FileId" title="파일찾기" accept="${imExtensionsPdfFiles}" />
										
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