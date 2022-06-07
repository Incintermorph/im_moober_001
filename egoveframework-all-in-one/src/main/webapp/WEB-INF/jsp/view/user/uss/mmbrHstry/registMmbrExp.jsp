<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>
<%@ include file="incMmberCode.jspf" %>


<script type="text/javaScript" language="javascript">

<c:set var="ATCH_FILE_ID" value="file_id"/>
<c:set var="ATCH_FILE_ID2" value="file_id2"/>
var AJAX = {
	fileInfo : {
			uploadFolder : 'ussMmbrExp',
			fileInputId : '${ATCH_FILE_ID}'
	},
	fileInfo2 : {
		uploadFolder : 'ussMmbrExp',
		fileInputId : '${ATCH_FILE_ID2}'
	},
	req : {
		save : null
	},
	init: function(){
		$(".esntlStar").hide();
		
   		//jquery ui datepicker
   		$("[data-role='datepicker']" ).datepicker({
   			showOn:"both",
   			buttonText: '',
   			dateFormat: 'yy-mm-dd',
   			changeYear: true,
   			yearRange: "1900:+nn"
   		});
   		
   		AJAX.initReq();
		
	},
	initReq: function(){
		this.req.save = imRequest("ajax",{formId: "iMMmbrExp"});
		this.req.save.cfg.type   = "json";
		this.req.save.cfg.url    =  "<c:url value="/user/mmbrExp/insert.do"/>";
		this.req.save.cfg.message.confirm="<spring:message code="common.regist.msg" />";
		this.req.save.cfg.fn.before = function() {
	    	if(!COMMT.chkAttachFile('${ATCH_FILE_ID}')){
	    		return true;
	    	}else{
	    		COMMT.uploadCommonFileUser(AJAX.fileInfo,function(data){
	    			 if (data.result > 0) {
	    				 if(data.result==1){
	    					 //첨부파일 정보 성공인 경우 
		    				 var form =  document.getElementById(AJAX.req.save.cfg.formId);
		    		    	 form.elements["idntyFileId"].value = data.atchFileId;
	    				 }
	    				 if(!COMMT.chkAttachFile('${ATCH_FILE_ID2}')){
	    					 AJAX.req.save.go("continue");
	    				 }else{
	    					 
	    					 COMMT.uploadCommonFileUser(AJAX.fileInfo2,function(data){
	    		    			 if (data.result > 0) {
	    		    				 if(data.result==1){
	    		    					 //첨부파일 정보 성공인 경우 
	    			    				 var form =  document.getElementById(AJAX.req.save.cfg.formId);
	    			    		    	 form.elements["aoasFileId"].value = data.atchFileId;
	    		    				 }
	    		    				 AJAX.req.save.go("continue");
	    		    				 
	    		                    } else {
	    		                    	COMMT.errorMessage();
	    		                   }
	    		    		});
	    					 
	    				 }
	                    } else {
	                    	COMMT.errorMessage();
	                   }
	    		});
	    		
	    	}
	 	}
		
		
		
		this.req.save.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) { 
	        	REQ.unloadPage('iMMmbrExp');
	        } else {
	        	COMMT.errorMessage();
	        }
	    };
	    

			AJAX.validator();		
		
	},
	validator : function(){
		
		this.req.save.validator.set({
	    	title  : "활동분야",
            name : "workCtgryCdv",
            data : ["!null"]
        });
	
	 this.req.save.validator.set({
	    	title  : "재직기간",
            name : "workBgnYmd",
            data : ["!null"]
      });
	 this.req.save.validator.set({
	    	title  : "재직기간",
            name : "workEndYmd",
            data : ["!null"]
      });
	 
	 this.req.save.validator.set({
	    	title  : "활동시간",
            name : "workHrs",
            data : ["!null","number"]
      });
	 this.req.save.validator.set({
	    	title  : "확인기관",
            name : "idntyAgncy",
            data : ["!null"]
      });
	 this.req.save.validator.set({
	    	title  : "활동내역",
            name : "workDesc",
            data : ["!null"]
      });
	 
	 
	 this.req.save.validator.set(function(){
	    	if(!COMMT.chkAttachFile('${ATCH_FILE_ID}')){
	    		COMMT.message('확인서 파일을 선택해 주세요.');
	    		return false;
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
	 this.req.save.validator.set(function(){
		 if(COMMT.chkAttachFile(AJAX.fileInfo2.fileInputId)){
		    	if(!COMMT.chkAttachFileSize(AJAX.fileInfo2)){
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
	cancle : function(){
    	REQ.unloadCancel('iMMmbrExp');
	}
}
</script>
				<form name="iMMmbrExp" id="iMMmbrExp" method="post" onsubmit="return false;">
				<input type="hidden" name="memberSrl" value="${param['memberSrl']}"/>
					<table class="tbl_row al">
						<colgroup>
							<col style="width:25%;">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th scope="row">활동분야 *</th>
								<td>
									<im:cd codeId="${workCtgryCdvArr}" type="checkbox" name="workCtgryCdv"/>
								</td>
							</tr>
							<tr>
								<th scope="row">활동기간 *</th>
								<td>
									<div class="c_date">
										<input type="text" name="workBgnYmd" placeholder="시작일" title="시작일" data-role="datepicker">
									</div>
									~
									<div class="c_date">
										<input type="text" name="workEndYmd" placeholder="종료일" title="종료일" data-role="datepicker">
									</div>
								</td>
							</tr>
							<tr>
								<th scope="row">활동시간 *</th>
								<td>
									<input type="text" class="wd_s" name="workHrs" title="활동시간" maxlength="3"><br/>
									상근직의 경우 년간 80시간으로 산출하여 입력 
								</td>
							</tr>
							<tr>
								<th scope="row">확인기관 *</th>
								<td>
									<input type="text" name="idntyAgncy"  class="wide" title="확인기관" maxlength="20">
								</td>
							</tr>
							<tr>
								<th scope="row">활동내역 *</th>
								<td>
									<input type="text" name="workDesc"  class="wide" title="활동내역" maxlength="20">
								</td>
							</tr>
							<tr>
								<th scope="row">경력증명서 *</th>
								<td>
									<input type="hidden" name="idntyFileId" />
									<input type="file" id="${ATCH_FILE_ID}"   title="파일찾기" accept="${imExtensionsPdfFiles}" />
								</td>
							</tr>
							<tr>
								<th scope="row">정관</th>
								<td>
									<input type="hidden" name="aoasFileId" />
									<input type="file" id="${ATCH_FILE_ID2}"   title="파일찾기" accept="${imExtensionsPdfFiles}" />
								</td>
							</tr>
						</tbody>
					</table><span style="color: red;">파일 첨부는 pdf만 가능합니다.</span>
					</form>
					<div class="b_box">
						<a href="javascript:;"  onclick="AJAX.cancle();" class="c_btn wt">취소</a>
						<a href="javascript:;"  onclick="AJAX.save();" class="c_btn d_green">저장</a>
					</div>