<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>

<c:set var="ATCH_FILE_ID" value="file_id"/>
<script type="text/javascript" >


var REQ = {
	req : {
		list : null,
		save : null,
	},
	atchInfo : {
		uploadFolder : 'pstpndAply',
		fileInputId : '${ATCH_FILE_ID}'
	},
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetail";
		this.req.list.cfg.url    = "<c:url value="/mng/mmbrQlfc/${nowType}/selectList.do"/>";
				
		
		this.req.save = imRequest("ajax",{formId: "iMPstpndAply"});
		this.req.save.cfg.type   = "json";
		this.req.save.cfg.url    =  "<c:url value="/mng/pstpndAply/update.do"/>";
		this.req.save.cfg.message.confirm="<spring:message code="common.update.msg" />";
		this.req.save.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) { 
	        	IMGLBObject.request.list();
	        } else {
	        	COMMT.errorMessage();
	        }
	    };	
	    this.req.save.cfg.fn.before = function() {
	    	if(!COMMT.chkAttachFile('${ATCH_FILE_ID}')){
				 REQ.req.save.go("continue");
	    	}else{
	    	 COMMT.uploadCommonFileUser(REQ.atchInfo,function(data){
    			 if (data.result > 0) {
    				 if(data.result==1){
    					 //첨부파일 정보 성공인 경우 
	    				 var form =  document.getElementById(REQ.req.save.cfg.formId);
	    		    	 form.elements["evddocId"].value = data.atchFileId;
    				 }
    			 }
    			 REQ.req.save.go("continue");
	    	 });
	    	}
    			 
	    };
	    this.req.save.validator.set(function(){
	    	var form = document.iMPstpndAply;
	    	return validateIMPstpndAply(form);
	    });
	    
	    this.req.save.validator.set(function(){
 	    	if(!COMMT.chkAttachFile('${ATCH_FILE_ID}')){
 	    		return true;
 	    	}else{
 	    		if(!COMMT.chkAttachFileSize(REQ.atchInfo)){
	    			return false;	
	    		}else{
	    			return true;
	    		}
 	    	}
 	    });
	    
	    $("[data-role='datepicker']" ).datepicker({
			showOn:"both",
			buttonText: '',
			dateFormat: '${imdatepickerDateType}',
			changeYear: true,
			yearRange: "1900:+nn"
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

<%@ include file="incMmbrQlfc.jsp" %>

<c:import charEncoding="utf-8"  url="/WEB-INF/jsp/inc/imZipCode.jsp"/>

<validator:javascript formName="iMPstpndAply" staticJavascript="false" xhtml="true" cdata="false"/>

<div class="cb_bar right">
	<im:pageBtn type="R" reqName="REQ"></im:pageBtn>
</div>

<c:set var="pstpndRsnCdvArr">01=본인의 질병,02=그 밖의 불가피한 사유,03=기타</c:set>
<c:set var="trgtYearArr">1=1년,2=2년,3=3년</c:set>
<form:form commandName="iMPstpndAply" name="iMPstpndAply" method="post" onsubmit="return false;">
<table class="tbl_row">
	<caption>유예신청 상세정보</caption>
	<colgroup>
		<col style="width:200px;">
		<col>
	</colgroup>
	<tbody>
			<tr>
				<th scope="row">취득시기</th>
				<td>
				<form:hidden path="mmbrId"/>
				<form:hidden path="esntlId"/>
				<form:hidden path="mberNm"/>
				<form:hidden path="dvsnCdv"/>
				<form:hidden path="sttsCdv"/><form:hidden path="pstpndAplyId"/>
				<form:hidden path="crsGrdCdv"/><form:hidden path="lcncEndYmd"/>
				<im:cd type="print" codeId="${dvsnCdvArr}" selectedCode="${iMPstpndAply.dvsnCdv}"/>
				</td>
			</tr>
			<tr>
				<th scope="row">자격증 발급번호</th>
				<td><form:hidden path="lcncIssuCode"/>${iMPstpndAply.lcncIssuCode}</td>
			</tr>
			<tr>
				<th scope="row">자격증번호</th>
				<td><form:hidden path="qlfcRsltCode"/>${iMPstpndAply.qlfcRsltCode}</td>
			</tr>
			<tr>
				<th scope="row">자격취득일자</th>
				<td>
				<im:dt yyyyMMddHHmmss="${iMPstpndAply.lcncAcqsYmd}"/>
				<form:hidden path="lcncAcqsYmd"/></td>
			</tr>
			<tr>
				<th scope="row">이름</th>
				<td>${iMPstpndAply.mberNm}</td>
			</tr>
			<tr>
					<th scope="row">핸드폰번호 <span class="c_red">*</span></th>
					<td>
					<input type="text" name="mmbrTelno1"  size="3" maxlength="3" value="${iMPstpndAply.mmbrTelno1}"/>-
					<input type="text" name="mmbrTelno2"  size="4" maxlength="4" value="${iMPstpndAply.mmbrTelno2}" />-
					<input type="text" name="mmbrTelno3"  size="4" maxlength="4" value="${iMPstpndAply.mmbrTelno3}" />
					</td>
			</tr>
			<tr>
				<th scope="row">생년월일 <span class="c_red">*</span></th>
				<td>
						<div class="ad_datepicker">
							<input type="text" name="brdt"  value="${iMPstpndAply.brdt}" title="생년월일" data-role="datepicker">
							
						</div>
				
				</td>
			</tr>
			<tr>
				<th scope="row">대상연도 <span class="c_red">*</span></th>
				<td>
					<im:cd type="radio" codeId="${trgtYearArr}" selectedCode="${iMPstpndAply.trgtYear}" name="trgtYear"/>
				</td>
			</tr>
			<tr>
					<th scope="row">주소<span class="c_red">*</span></th>
					<td>
						<input type="text" class="wd_sm" title="우편번호" id="_postcode_id"  value="${iMPstpndAply.zipc}" name="zipc" readonly>
						<a href="#" class="ml ad_btn gray" onclick="callPostcode()">주소찾기</a>
						<input type="text" name="addr"  id="_address_id"  value="${iMPstpndAply.addr}" readonly="readonly" class="wide mts" title="주소">
						<input type="text" name="addrDtl" id="_detailAddress_id" value="${iMPstpndAply.addrDtl}" maxlength="150" class="wide mts" title="상세 주소">
					</td>
			</tr>
			<tr>
				<th scope="row">유예사유 <span class="c_red">*</span></th>
				<td>
				<select name="pstpndRsnCdv">
				<option value="">선택</option>
				<im:cd type="option" codeId="${pstpndRsnCdvArr}" selectedCode="${iMPstpndAply.pstpndRsnCdv}"/>
				</select>
				</td>
			</tr>
			<tr>
				<th scope="row">유예사유 비고 <span class="c_red">*</span> </th>
				<td>
				<textarea name="pstpndRsn" id="" cols="" rows="" title="유예사유 비고">${iMPstpndAply.pstpndRsn}</textarea>
				</td>
			</tr>
			<tr>
				<th scope="row">첨부파일<span class="c_red">*</span></th>
				<td>
				
				<c:forEach items="${detail.fileList}" var="frow">
						<c:set var="file_Key" value="${frow.atchFileId}=${frow.fileSn}"/>
						<a href="javascript:COMMT.fn_egov_downFileEnc('<c:out value="${imfunc:encryptString(file_Key)}"/>')" class="file_link" >
							<c:out value="${frow.orignlFileNm}"/><br> 
						</a>	
				</c:forEach>
				
				<form:hidden path="evddocId"/>
				보수교육 유예 사유를 증빙할 수 있는 서류를 첨부하세요.<br> 
				<input type="file" id="${ATCH_FILE_ID}" value="${ATCH_FILE_ID}" name="evddocIdFile" title="파일찾기" accept="${imExtensionsFiles}" />
				
				</td>
			</tr>

	</tbody>
</table>
</form:form>
<div class="b_box right">
	<im:pageBtn type="R" reqName="REQ"></im:pageBtn>
</div>