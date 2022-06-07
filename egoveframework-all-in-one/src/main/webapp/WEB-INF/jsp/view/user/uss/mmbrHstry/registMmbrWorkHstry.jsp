<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>
<%@ include file="incMmberCode.jspf" %>


<script type="text/javaScript" language="javascript">

<c:set var="ATCH_FILE_ID" value="file_id"/>
<c:set var="ATCH_FILE_ID2" value="file_id2"/>
var AJAX = {
	fileInfo : {
			uploadFolder : 'ussMmbrWorkHstry',
			fileInputId : '${ATCH_FILE_ID}'
	},
	fileInfo2 : {
		uploadFolder : 'ussMmbrWorkHstryEnc',
		encryption: true, 
		fileInputId : '${ATCH_FILE_ID2}'
	},
	arrSub:null,
	workDvsnHstryCdv : null,
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
		this.req.save = imRequest("ajax",{formId: "iMMmbrWorkHstry"});
		this.req.save.cfg.type   = "json";
		this.req.save.cfg.url    =  "<c:url value="/user/mmbrWorkHstry/insert.do"/>";
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
		    		    	 form.elements["evddocFileId"].value = data.atchFileId;
	    				 }
	    				 if(!COMMT.chkAttachFile('${ATCH_FILE_ID2}')){
	    					 AJAX.req.save.go("continue");
	    				 }else{
	    					 
	    					 COMMT.uploadCommonFileUser(AJAX.fileInfo2,function(data){
	    		    			 if (data.result > 0) {
	    		    				 if(data.result==1){
	    		    					 //첨부파일 정보 성공인 경우 
	    			    				 var form =  document.getElementById(AJAX.req.save.cfg.formId);
	    			    		    	 form.elements["dsgnFileId"].value = data.atchFileId;
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
	        	REQ.unloadPage('iMMmbrWorkHstry');
	        } else {
	        	COMMT.errorMessage();
	        }
	    };
	    

		 this.req.save.validator.set({
		    	title  : "구분",
	            name : "workDvsnHstryCdv",
	            data : ["!null"]
	        });
		 
		if(AJAX.workDvsnHstryCdv!='3001' && AJAX.workDvsnHstryCdv!='999'){
			AJAX.validator();		
		}else{
			 this.req.save.validator.set({
			    	title  : "근무기간 시작일",
		            name : "workBgnYmd",
					check : {
						lt : {name : "workEndYmd", title : "근무기간 종료일"}
				     }
		      });
			
			this.req.save.validator.set(function(){
				 var form =  document.getElementById(AJAX.req.save.cfg.formId);
				 if(form.elements["workTrgtCdv"].value=='999'){
					 if($.trim(form.elements["etcWork"].value).length == 0){
						 COMMT.message('기타 대상업무를 입력하세요.');
						 return false;
					 }else{
						 return true;
					 }
				 }else{
					 return true;
				 }
			    	
		      });
		}
		
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
	validator : function(){
		
		this.req.save.validator.set({
	    	title  : "기관",
            name : "agncyNm",
            data : ["!null"]
        });
	
	 this.req.save.validator.set({
	    	title  : "근무기간 시작일",
            name : "workBgnYmd",
            data : ["!null"]
      });
	 this.req.save.validator.set({
	    	title  : "근무기간 종료일",
            name : "workEndYmd",
            data : ["!null"]
      });

	 this.req.save.validator.set({
	    	title  : "근무기간 시작일",
            name : "workBgnYmd",
			check : {
				lt : {name : "workEndYmd", title : "근무기간 종료일"}
		     }
      });
	 this.req.save.validator.set({
	    	title  : "직급",
            name : "pstn",
            data : ["!null"]
      });
	 this.req.save.validator.set({
	    	title  : "대상업무",
            name : "workTrgtCdv",
            data : ["!null"]
      });
	 this.req.save.validator.set(function(){
		 var form =  document.getElementById(AJAX.req.save.cfg.formId);
		 if(form.elements["workTrgtCdv"].value=='999'){
			 if($.trim(form.elements["etcWork"].value).length == 0){
				 COMMT.message('기타 대상업무를 입력하세요.');
				 return false;
			 }else{
				 return true;
			 }
		 }else{
			 return true;
		 }
	    	
      });
	 
	 
	 
	 this.req.save.validator.set(function(){
			 if(!$('#eduDsgnYn_').prop("checked")){
				 return true;
			 }
	    	if(!COMMT.chkAttachFile('${ATCH_FILE_ID}')){
	    		COMMT.message('지정서 파일을 선택해 주세요.');
	    		return false;
	    	}else{
	    		return true;
	    	}
	    });
    
	 this.req.save.validator.set(function(){
	    	if(!COMMT.chkAttachFile('${ATCH_FILE_ID2}')){
	    		COMMT.message('재직증명 파일을 선택해 주세요.');
	    		return false;
	    	}else{
	    		return true;
	    	}
	    });	
		
	},
	save : function(){
		AJAX.req.save.go();
	},
	cancle : function(){
    	REQ.unloadCancel('iMMmbrWorkHstry');
	},
	esntl : function(v){
		AJAX.workDvsnHstryCdv = v;
		if(v=='3001'|| v=='999'){
			$(".esntlStar").hide();
		}else{
			$(".esntlStar").show();
		}
		AJAX.initReq();
	},
	setOrg : function(key){
		var form =  document.getElementById(AJAX.req.save.cfg.formId);
		form.elements["agncyNm"].value=$("#_potal_name_"+key).html();
		form.elements["agncyCode"].value=key;
		$("#find_org").hide();
	},
	delPotalAgncy: function(){
		var form =  document.getElementById(AJAX.req.save.cfg.formId);
		form.elements["agncyNm"].value="";
		form.elements["agncyCode"].value="";
	}
}
</script>
				<form name="iMMmbrWorkHstry" id="iMMmbrWorkHstry" method="post" onsubmit="return false;">
				<input type="hidden" name="memberSrl" value="${param['memberSrl']}"/>
					<table class="tbl_row al">
						<colgroup>
							<col style="width:30%;">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th scope="row">구분 *</th>
								<td>
									<im:cd codeId="${workDvsnArr}" onclick="AJAX.esntl(this.value)" type="radio" name="workDvsnHstryCdv"/>
								</td>
							</tr>
							<tr>
								<th scope="row">기관명 <span class="esntlStar">*</span></th>
								<td>
									<div class="search_area">
										<input type="text" name="agncyNm" placeholder="재직기관을 선택하여 주세요" title="기관명" readonly="readonly">
										<input type="hidden" name="agncyCode" placeholder="기관코드을 선택하여 주세요" title="기관코드">
										<a href="javascript:;" onclick="AJAX.delPotalAgncy()"  class="del" title="삭제"><span class="sr_only">삭제</span></a>
									</div>										
									<a href="javascript:;" onclick="REQ.openPotalAgncy()"  class="c_btn bk">찾기</a>
									<div class="vspace"></div>
									<input type="checkbox" name="eduDsgnYn"  value="Y" id="eduDsgnYn_">
									<label for="eduDsgnYn_">사회환경교육기관 지정여부​</label>
								</td>
							</tr>
							<tr>
								<th scope="row">근무기간 <span class="esntlStar">*</span></th>
								<td>
									<div class="c_date">
										<input type="text" name="workBgnYmd" placeholder="시작일" title="시작일" data-role="datepicker">
									</div>
									~
									<div class="c_date">
										<input type="text"  name="workEndYmd" placeholder="종료일" title="종료일" data-role="datepicker">
									</div>
									<div class="vspace"></div>
									<input type="checkbox" name="workYn" value="Y"  id="">
									<label for="">현재 근무 중</label>
								</td>
							</tr>
							<tr>
								<th scope="row">직급 <span class="esntlStar">*</span></th>
								<td>
									<input type="text" class="wide" title="직급" maxlength="20" name="pstn">
								</td>
							</tr>
							<tr>
								<th scope="row">대상업무 <span class="esntlStar">*</span></th>
								<td>
									<select name="workTrgtCdv" id="">
										<im:cd codeId="${workTrgtCdvArr}" type="option"/>
									</select>
									<div class="vspace"></div>
									<input type="text" name="etcWork" class="wide" placeholder="기타 대상업무를 입력하세요" title="기타">
								</td>
							</tr>
							<tr>
								<th scope="row">사회환경교육기관 지정서</th>
								<td>
									<input type="hidden" name="evddocFileId" />
									<input type="file" id="${ATCH_FILE_ID}"   title="파일찾기" accept="${imExtensionsPdfFiles}" />
									
								</td>
							</tr>
							<tr>
								<th scope="row">재직증명 첨부 <span class="esntlStar">*</span></th>
								<td>
									<input type="hidden" name="dsgnFileId" />
									<input type="file" id="${ATCH_FILE_ID2}"   title="파일찾기" accept="${imExtensionsPdfFiles}" />
									
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