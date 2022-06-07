<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>

<c:set var="ATCH_FILE_ID" value="file_id"/>

<script type="text/javascript" >


var REQ = {
	atchInfo : {
			uploadFolder : 'pstpndAply',
			fileInputId : '${ATCH_FILE_ID}'
	},
	req : {
		list : null,
		save : null,
		del : null,
	},
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetail";
		this.req.list.cfg.url    = "<c:url value="/user/pstpndAply/selectList.do"/>";
				
		
		this.req.save = imRequest("ajax",{formId: "iMPstpndAply"});
		this.req.save.cfg.type   = "json";
		this.req.save.cfg.url    =  "<c:url value="/user/pstpndAply/update.do"/>";
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
			 if(COMMT.chkAttachFile(REQ.atchInfo.fileInputId)){
			    	if(!COMMT.chkAttachFileSize(REQ.atchInfo)){
			    			return false;	
			    	}else{
			    			return true;
			    	}
		    	}else{
		    		return true;
		    	}
		    });
		 
	    
	  //jquery ui datepicker
		$("[data-role='datepicker']" ).datepicker({
			showOn:"both",
			buttonText: '',
			dateFormat: 'yy-mm-dd',
			changeYear: true,
			yearRange: "1900:+nn"
		});
	  


	    this.req.del = imRequest("ajax");
	    this.req.del.cfg.formId = "iMPstpndAply";
	    this.req.del.cfg.type   = "json";
	    this.req.del.cfg.url    = "<c:url value="/user/pstpndAply/delete.do"/>";
	    this.req.del.cfg.message.confirm="신청을 취소 하시겠습니까?";
	    this.req.del.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) { 
	        	IMGLBObject.request.page(1);
	        } else {
	        	COMMT.errorMessage();
	        }
	    };	
	
	},
	page :  function(pageNo){
		var form =  document.getElementById(this.req.list.cfg.formId);
		form.elements["currentPageNo"].value= pageNo;
		this.req.list.go();
	},
	list :  function(){
		this.req.list.go();
	},
	save :  function(sttsCdv){
		IMGLBObject.request=this;
		var form =  document.getElementById(REQ.req.save.cfg.formId);
   	 	form.elements["sttsCdv"].value = sttsCdv;
		this.req.save.go();
	},
	del :  function(){
		IMGLBObject.request=this;
		this.req.del.go();
	},
	print : function(pstpndAplyId){ 
			COMMT.fn_Enc_Print('<c:url value="/user/pstpndAply/selectDetailPrint.do"/>?pstpndAplyId='+pstpndAplyId,1260,850);
	},
}


$(document).ready(function(){
	REQ.init();
});

</script>

<%@ include file="incPstpndAply.jsp" %>


<c:import charEncoding="utf-8"  url="/WEB-INF/jsp/inc/imZipCode.jsp"/>

<div class="cb_bar right">
	<div class="add">	
		<c:if test="${iMPstpndAply.sttsCdv eq '02'}">
		<a href="javascript:;" onclick="REQ.print('${iMPstpndAply.pstpndAplyId}');" class="c_btn green">확인증 출력</a>
		</c:if>
		<a href="javascript:;" onclick="REQ.list()"  class="c_btn l_gray">목록</a>		
	</div>
</div>



<validator:javascript formName="iMPstpndAply" staticJavascript="false" xhtml="true" cdata="false"/>
<c:set var="pstpndRsnCdvArr">01=본인의 질병,02=그 밖의 불가피한 사유,03=기타</c:set>
<c:set var="trgtYearArr">1=1년,2=2년,3=3년</c:set>
<form:form commandName="iMPstpndAply" name="iMPstpndAply" method="post" onsubmit="return false;">
	<table class="tbl_row al">
		<colgroup>
			<col style="width:17%;">
			<col>
		</colgroup>
			<tbody>
			<tr>
				<th scope="row">취득시기</th>
				<td><form:hidden path="dvsnCdv"/>
				<form:hidden path="sttsCdv"/>
				<im:cd type="print" codeId="${dvsnCdvArr}" selectedCode="${iMPstpndAply.dvsnCdv}"/>
				<form:hidden path="pstpndAplyId"/>
				<form:hidden path="lcncEndYmd"/>
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
				<td><form:hidden path="lcncAcqsYmd"/>${iMPstpndAply.lcncAcqsYmd}</td>
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
						<div class="c_date">
							<input type="text" name="brdt"  value="${iMPstpndAply.brdt}" title="생년월일" data-role="datepicker">
						</div>
				
				</td>
			</tr>
			<tr>
				<th scope="row">대상연도 <span class="c_red">*</span></th>
				<td>
					<im:cd type="radio" codeId="${trgtYearArr}" name="trgtYear" selectedCode="${iMPstpndAply.trgtYear}"/>
					<br>
					<span style="color: red;font-size: 13px;">
					※ 대상연도: 자격증을 취득한 이후 소요된  기간을 의미함  (예시 : 2021년에 자격증 취득 > 현재 2022년이라면 대상연도 1년)
               		</span>
				</td>
			</tr>
			<tr>
					<th scope="row">주소<span class="c_red">*</span></th>
					<td>
						<input type="text" class="wd_sm" title="우편번호" id="_postcode_id"  value="${iMPstpndAply.zipc}" name="zipc" readonly>
						<a href="javascript:;" class="c_btn bk" onclick="callPostcode()">우편번호 찾기</a>
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
			</tr>

	</tbody>
</table>
</form:form>

	<div class="b_box">
			<c:if test="${iMPstpndAply.sttsCdv eq '99' || iMPstpndAply.sttsCdv eq '05'}">
			<%--
			<a href="javascript:;" onclick="REQ.save('99')"  class="c_btn green mid">임시저장</a>
			--%>
			<a href="javascript:;" onclick="REQ.save('01')"  class="c_btn green mid">저장</a>
			<a href="javascript:;" onclick="REQ.del()"  class="c_btn red mid">신청취소</a>
			</c:if>
			
			<c:if test="${iMPstpndAply.sttsCdv eq '02'}">
			<a href="javascript:;" onclick="REQ.print('${iMPstpndAply.pstpndAplyId}');" class="c_btn green mid">확인증 출력</a>
			</c:if>
			<a href="javascript:;" onclick="REQ.list()" class="c_btn gray mid">목록</a>
		</div>