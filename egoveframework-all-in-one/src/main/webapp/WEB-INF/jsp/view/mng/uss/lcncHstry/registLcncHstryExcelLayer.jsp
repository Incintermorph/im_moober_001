<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>
<script type="text/javascript" >


<c:set var="ATCH_FILE_ID" value="excelfile"/>
var REQ = {
	fileInfo : {
		uploadFolder : 'excel',
		fileInputId : '${ATCH_FILE_ID}'
		
	},
	req : {
		save : null,
	},
	init : function(){
		this.req.save = imRequest("ajax",{formId: "FormMemo"});
		this.req.save.cfg.type   = "json";
		this.req.save.cfg.url    =  "<c:url value="/mng/lcncHstry/insertExcel.do"/>";
		this.req.save.cfg.message.confirm="<spring:message code="common.regist.msg" />";
		this.req.save.cfg.asynchronous = true; // 인 경우 asynchronous
		this.req.save.cfg.message.waiting = "처리중입니다.";
		
		this.req.save.cfg.fn.before = function() {	
		    	if(!COMMT.chkAttachFile('${ATCH_FILE_ID}')){

		    		return true;
		    	}else{
		    		COMMT.uploadCommonFile(REQ.fileInfo,function(data){
		    			 if (data.result > 0) {
		    				 if(data.result==1){
		    					 //첨부파일 정보 성공인 경우 
			    				 var form =  document.getElementById(REQ.req.save.cfg.formId);
			    		    	 form.elements["atchFileId"].value = data.atchFileId;
		    				 }
		    				 REQ.req.save.go("continue");
		                    } else {
		                    	COMMT.errorMessage();
		                   }
		    		});
		    		
		    	}
		 }
		
	    this.req.save.validator.set(function(){
	    	if(!COMMT.chkAttachFile('${ATCH_FILE_ID}')){
	    		COMMT.message('일괄 등록 파일(엑셀)을 선택하세요.');
	    		return false;
	    	}else{
	    		return true;
	    	}
	    });
	    
   		this.req.save.cfg.fn.complete = function(act, data) {
   	        if (data != null && data.result > 0) { 
   	        	//alert(data.result);
   	        	//alert(data.success);
   	        	
   	        	
	   	       
            
   	        	
	        	if(data.result!=data.success){
	        		$("#resultBtn").show();
	        		alert("저장중  저장 실패가발생 했습니다. '결과 다운로드' 확인 후 재 시도 바랍니다.");
	        	}else{
	        		alert(data.success +"껀 저장 되었습니다.");
	        		var callback = COMMT.getLayerCallback(parent, "<c:out value="${param['imCallBack']}"/>");
	 	    		if (typeof callback === "function") {
	 			            callback.call(this);
	 			    }
	        		COMMT.callCloseLayer(parent);
	        	}
   	        } else {
   	        	COMMT.errorMessage();
   	        }
   	    };	
	},
	save:  function(){
		REQ.req.save.go();
	},
	close:  function(){
		COMMT.callCloseLayer(parent);
	}
}


$(document).ready(function(){
	REQ.init();
});

</script>
<div>
	 업로드 '샘플다운로드' 후 양식을 꼭 맞추어서 등록해주세요.<br>
	 필수값은  연번,취득연도,자격번호,자격급수,자격취득일,생년월일,양성기관,이름,연락처 입니다.<br>
	 연번은 키 값입니다.<br>
	 생년월일은 0000.00.00 으로 8자리를 숫자 이여야 합니다.<br>
	엑셀 텍스트로 저장된 파일이여야 합니다. 특히 날짜(생일,시작일,종료일).문자열 숫자. 전화번호 등 <br>
	 전화번호는 000-0000-00000 으로  구분자는 -만 있어야 합니다.<br>
	 날짜 형식은 엑셀에서 사용자 지정에서    yyyy-mm-dd로 변경하시기 바랍니다..<br>
	 유효기간 만료일은 yyyymmdd 즉 20201231 형식으로등록해 주세요.
	 <br>
</div>
<form name="FormMemo" id="FormMemo" method="post" onsubmit="return false;">
<table class="tbl_row">
	<caption> 상세정보</caption>
	<colgroup>
		<col style="width:120px;">
		<col>
	</colgroup>
	<tbody>
			<tr>
				<th scope="row">일괄파일<span class="c_red">*</span></th>
				<td>
					<input type="hidden" name="atchFileId" value="">
					<input type="file" id="${ATCH_FILE_ID}" value="${ATCH_FILE_ID}" name="FileId" title="파일찾기" accept=".xls,.xlsx" />
				</td>
			</tr>
	</tbody>
</table>	
</form>
<div class="b_box">
	<a href="<c:url value="/mng/lcncHstry/registExcel/sample.do"/>"  class="ad_btn green">샘플다운로드</a>
	<a href="#" onclick="REQ.save();return false;" class="ad_btn green">저장</a>
	
	<a href="<c:url value="/mng/lcncHstry/registExcelResult.do"/>" id="resultBtn" style="display: none;" class="ad_btn green">결과 다운로드</a>
	<a href="#" onclick="REQ.close();return false;" class="ad_btn bk">닫기</a> 
</div>