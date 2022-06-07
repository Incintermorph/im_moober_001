<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>
<%@ include file="incMmberCode.jspf" %>

<script type="text/javaScript" language="javascript">

<c:set var="ATCH_FILE_ID" value="file_id"/>
var AJAX = {
	fileInfo : {
			uploadFolder : 'ussMmbrEdu',
			fileInputId : '${ATCH_FILE_ID}'
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
		this.req.save = imRequest("ajax",{formId: "iMMmbrEdu"});
		this.req.save.cfg.type   = "json";
		this.req.save.cfg.url    =  "<c:url value="/user/mmbrEdu/insert.do"/>";
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
		    		    	 form.elements["dgeFileId"].value = data.atchFileId;
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
	        	REQ.unloadPage('iMMmbrEdu');
	        } else {
	        	COMMT.errorMessage();
	        }
	    };

		AJAX.validator();		
		
	},
	validator : function(){
		
	this.req.save.validator.set({
	    	title  : "학교명",
            name : "sclNm",
            data : ["!null"]
     });
	
	 this.req.save.validator.set({
	    	title  : "학과",
            name : "mjrNm",
            data : ["!null"]
      });
	 this.req.save.validator.set({
	    	title  : "전공",
            name : "specNm",
            data : ["!null"]
      });
	 
	 this.req.save.validator.set({
	    	title  : "학위",
            name : "dgeCdv",
            data : ["!null"]
      });
	 this.req.save.validator.set({
	    	title  : "학위취득일",
            name : "dgeYmd",
            data : ["!null"],
			check : {
		         lt : {name : "_now", title : "현재날짜"}
		     }
      });
	 
	 
	 
	 this.req.save.validator.set(function(){
	    	if(!COMMT.chkAttachFile('${ATCH_FILE_ID}')){
	    		COMMT.message('학위증명서 파일을 선택해 주세요.');
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

	 this.req.save.validator.set({
	    	title  : "논문주제",
            name : "rechTpc",
            check : {  
                maxlength : 250 
            }  
      });	
		
	},
	save : function(){
		AJAX.req.save.go();
	},
	cancle : function(){
    	REQ.unloadCancel('iMMmbrEdu');
	}
}




</script> 
				<form name="iMMmbrEdu" id="iMMmbrEdu" method="post" onsubmit="return false;">
				<input type="hidden" name="memberSrl" value="${param['memberSrl']}"/>
				<input type="hidden" name="_now" value="<im:dt yyyyMMddHHmmss="${imNowDatetime }" pattern="yyyy-MM-dd"/>"/>
					<table class="tbl_row al">
						<colgroup>
							<col style="width:25%;">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th scope="row">학교명 *</th>
								<td>
									<input type="text" name="sclNm" maxlength="20" class="wide" title="학교명">
								</td>
							</tr>
							<tr>
								<th scope="row">학과 *</th>
								<td>
									<input type="text"  name="mjrNm" maxlength="20" class="wide" title="학과">
								</td>
							</tr>
							<tr>
								<th scope="row">전공 *</th>
								<td>
									<input type="text" class="wide" maxlength="20" name="specNm" title="전공">
								</td>
							</tr>
							<tr>
								<th scope="row">학위 *</th>
								<td>
									<im:cd codeId="${dgeCdvArr}" type="radio" name="dgeCdv"/>
								</td>
							</tr>
							<tr>
								<th scope="row">학위취득일 *</th>
								<td>
									<div class="c_date">
										<input type="text" title="학위취득일" data-role="datepicker" name="dgeYmd">
									</div>
								</td>
							</tr>
							<tr>
								<th scope="row">학위증명서 *</th>
								<td>
									<input type="hidden" name="dgeFileId" />
									<input type="file" id="${ATCH_FILE_ID}"   title="파일찾기" accept="${imExtensionsPdfFiles}" />
									
								</td>
							</tr>
							<tr>
								<th scope="row">논문주제</th>
								<td>
								<div class="ct_box">
									<ul class="ref orange">
										<li>200자 내외 입력</li>
									</ul>
								</div>
									<textarea  name="rechTpc" id="rechTpc"  cols="" rows="" style="height:100px;"></textarea>
								</td>
							</tr>
						</tbody>
					</table>
					<span style="color: red;">파일 첨부는 pdf만 가능합니다.</span>
					</form>
					<div class="b_box">
						<a href="javascript:;"  onclick="AJAX.cancle();" class="c_btn wt">취소</a>
						<a href="javascript:;"  onclick="AJAX.save();" class="c_btn d_green">저장</a>
					</div>