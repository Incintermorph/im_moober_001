<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>

<c:set var="ATCH_FILE_ID" value="file_id"/>
<c:set var="FMLY_FILE_ID" value="fmly_file_id"/>
<script type="text/javascript" >


var REQ = {

	atchInfo : {
		uploadFolder : 'issyAply',
		encryption: true, 
		fileInputId : '${ATCH_FILE_ID}'
	},
	atchInfo2 : {
		uploadFolder : 'issyAplyFaly',
		encryption: true, 
		fileInputId : '${FMLY_FILE_ID}'
	},
	req : {
		list : null,
		save : null,
		cancel : null,
	},
	saveFile1 : function(data){
		//alert(data.result );
		if (data.result > 0) {
			 if(data.result==1){
				 //첨부파일 정보 성공인 경우 
				 var form =  document.getElementById(REQ.req.save.cfg.formId);
		    	 form.elements["evddocId"].value = data.atchFileId;
			 }
			 REQ.saveStartFile2();
       } else {
       		COMMT.errorMessage();
      }
	},
	saveStartFile2 : function(){
		if(COMMT.chkAttachFile('${FMLY_FILE_ID}')){
    		COMMT.uploadCommonFileUser(REQ.atchInfo2,REQ.saveFile2);
    	}else{
    		REQ.req.save.go("continue");
    	}
	},
	saveFile2 : function(data){
		if (data.result > 0) {
			 if(data.result==1){
				 //첨부파일 정보 성공인 경우 
				 var form =  document.getElementById(REQ.req.save.cfg.formId);
		    	 form.elements["fmlyEvddocId"].value = data.atchFileId;
			 }
			// alert(data.result);
			 REQ.req.save.go("continue");
       } else {
       		COMMT.errorMessage();
      }
	},
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetail";
		this.req.list.cfg.url    = "<c:url value="/user/issuAply/selectList.do"/>";
				
		
		this.req.save = imRequest("ajax",{formId: "iMIssuAply"});
		this.req.save.cfg.type   = "json";
		this.req.save.cfg.url    =  "<c:url value="/user/issuAply/update.do"/>";
		this.req.save.cfg.message.confirm="<spring:message code="common.update.msg" />";
		this.req.save.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) { 
	        	IMGLBObject.request.list();
	        } else {
	        	COMMT.errorMessage();
	        }
	        
	        
	    };	
	    
	    this.req.save.cfg.fn.before = function() {
	    	COMMT.uploadCommonFileUser(REQ.atchInfo,REQ.saveFile1);
	    } 
	    
	    
	    this.req.save.validator.set(function(){
	    	var form = document.iMIssuAply;
	    	return validateIMIssuAply(form);
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
		 

		 this.req.save.validator.set(function(){
			 if(COMMT.chkAttachFile(REQ.atchInfo2.fileInputId)){
			    	if(!COMMT.chkAttachFileSize(REQ.atchInfo2)){
			    			return false;	
			    	}else{
			    			return true;
			    	}
		    	}else{
		    		return true;
		    	}
		    });

	    
	    this.req.save.validator.set({  
            title : "주민번호 앞자리", 
            name : "rrno1", 
            data : ["!null", "number"], 
            check : {  
            	minlength : 6 
            }  
        } );
	    
	    this.req.save.validator.set({  
            title : "주민번호 뒷자리", 
            name : "rrno2", 
            data : ["!null", "number"], 
            check : {  
            	minlength : 7 
            }  
        } );
	    $("[data-role='datepicker']" ).datepicker({
			showOn:"both",
			buttonText: '',
			dateFormat: '${imdatepickerDateType}',
			changeYear: true,
			yearRange: "1900:+nn"
		});
	    
	    

	    this.req.cancel = imRequest("ajax");
	    this.req.cancel.cfg.formId = "iMIssuAply";
	    this.req.cancel.cfg.type   = "json";
	    this.req.cancel.cfg.url    = "<c:url value="/user/issuAply/cancel.do"/>";
	    this.req.cancel.cfg.message.confirm="신청을 취소 하시겠습니까?";
	    this.req.cancel.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) { 
	        	REQ.page(1);
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
	cancel :  function(){
		IMGLBObject.request=this;
		this.req.cancel.go();
	},
	printLicense : function(appId){ <%--자격증--%>
			COMMT.fn_Enc_Print('<c:url value="/user/uss/mypage/selectDetailCrsPrintLicense.do"/>?issuAplyId='+appId,1260,850);
	},
}


$(document).ready(function(){
	REQ.init();
});

</script>

<%@ include file="incIssuAply.jsp" %>
<c:import charEncoding="utf-8"  url="/WEB-INF/jsp/inc/imZipCode.jsp"/>

<validator:javascript formName="iMIssuAply" staticJavascript="false" xhtml="true" cdata="false"/>
		<div class="ct_box bot">
			<p class="p_noti"><span class="c_red"><i class="m_icon_out circle_notifications"></i>[안내]</span></p>
			<ul class="radio">
				<li>법 개정(22년 1월 6일) 이전 자격증도 발급 신청도 가능합니다.</li>
				<li>자격이 정지 혹은 취소된 경우에는 자격증 발급 및 출력이 제한됩니다.</li>
			</ul>
		</div>
		<div class="cb_bar right">
	<div class="add">
		<c:if test="${iMIssuAply.sttsCdv eq '02'}">
		<a href="javascript:;" onclick="REQ.printLicense('${iMIssuAply.issuAplyId}');" class="c_btn green">자격증 출력</a>
		</c:if>
		<a href="javascript:;" onclick="REQ.list()"  class="c_btn l_gray">목록</a>
	</div>
</div>
		<form:form commandName="iMIssuAply" name="iMIssuAply" method="post" onsubmit="return false;">
		<table class="tbl_row al">
			<colgroup>
				<col style="width:17%;">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th scope="row">발급 구분</th>
					<td><im:cd type="print" codeId="IM0022" name="issuDvsnCdv"  selectedCode="${iMIssuAply.issuDvsnCdv}"/>
						<form:hidden path="issuAplyId"/>
						<form:hidden path="issuDvsnCdv"/>
						
						<form:hidden path="sttsCdv"/>
					</td>
				</tr>
				<tr>
					<th scope="row">자격증번호</th>
					<td>
					${iMIssuAply.qlfcRsltCode}
					<input type="hidden"  name="qlfcRsltCode"    value="${iMIssuAply.qlfcRsltCode}">
					</td>
				</tr>
				<tr>
					<th scope="row">이름</th>
					<td>${IMLoginUser.name}</td>
				</tr>
				<tr>
					<th scope="row">이수 양성기관명</th>
					<td>
						<select name="agncyId">
						<option value="">선택</option>
						<c:forEach items="${agncyList}" var="row">
						<option value="${row.agncy.agncyId}"
						<c:if test="${row.agncy.agncyId eq  iMIssuAply.agncyId}">
							selected="selected"
							</c:if> 
							>${row.agncy.agncyNm}</option>
						</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th scope="row">등급</th>
					<td>
					<input type="hidden"  name="crsGrdCdv"    value="${iMIssuAply.crsGrdCdv}">
					<im:cd type="print" name="crsGrdCdv" selectedCode="${iMIssuAply.crsGrdCdv}" codeId="IM0001"/>
					</td>
				</tr>
				<tr>
					<th scope="row">교육 기간</th>
					<td>
						<div class="c_date">
							<input type="text" name="eduBgnDt" title="시작일" value="<im:dt yyyyMMddHHmmss="${iMIssuAply.eduBgnDt}"/>" data-role="datepicker">
						</div>
						~
						<div class="c_date">
							<input type="text" name="eduEndDt"  title="종료일"  value="<im:dt yyyyMMddHHmmss="${iMIssuAply.eduEndDt}"/>" data-role="datepicker">
						</div>
					</td>
				</tr>
				<tr>
					<th scope="row">취득일자</th>
					<td>
						<div class="c_date">
							<im:dt yyyyMMddHHmmss="${lastMmbrQlfc.lcncAcqsYmd}"/>
							<input type="hidden" name="lcncAcqsYmd" value="${lastMmbrQlfc.lcncAcqsYmd}">
						</div>
					</td>
				</tr>
				<tr>
					<th scope="row">주민등록 번호</th>
					<td>
						<input type="text" name="rrno1" class="mo_wide ac"  title="주민등록 번호"  value="${iMIssuAply.rrno1}" size="6"  placeholder="- 없이 숫자만 입력"  maxlength="6"  >
						-
						<input type="password" name="rrno2" class="mo_wide ac" title="주민등록 번호" value="${iMIssuAply.rrno2}"  size="7"   placeholder="- 없이 숫자만 입력"  maxlength="7"  >
						<br> ※ 주민등록번호, 범죄경력자료 수집 근거 : 환경교육의 활성화 및 지원에 관한 법률 시행령 제22조
					</td>
				</tr>
				<tr>
					<th scope="row">핸드폰번호</th>
					<td>
						<form:input path="mmbrTelno1" size="4" maxlength="4"/>-<form:input path="mmbrTelno2" size="4" maxlength="4"/>-<form:input path="mmbrTelno3" size="4" maxlength="4"/>
					</td>
				</tr>
				<tr>
					<th scope="row">주소</th>
					<td>
						<input type="text" class="wd_sm" title="우편번호" id="_postcode_id" value="${iMIssuAply.zipc}"  name="zipc" readonly>
						<a href="javascript:;" class="c_btn bk" onclick="callPostcode()">우편번호 찾기</a>
						<input type="text" name="addr" value="${iMIssuAply.addr}"  id="_address_id" readonly="readonly" class="wide mts" title="주소">
						<input type="text" name="addrDtl" value="${iMIssuAply.addrDtl}" id="_detailAddress_id"  maxlength="200" class="wide mts" title="상세 주소">
					</td>
				</tr>
				<tr>
					<th scope="row">발급 사유 비고</th>
					<td>
						<textarea name="issuRsn" id="" cols="" rows="" title="발급 사유 비고">${iMIssuAply.issuRsn}</textarea>
					</td>
				</tr>
				<c:if test="${iMIssuAply.issuDvsnCdv eq '01'}">
				<tr>
					<th scope="row">증빙 첨부</th>
					<td>
						<p class="tb_note md m_0">1. 자격요건을 갖추었음을 증명하는 서류
						<c:if test="${!empty  iMIssuAply.evddocId}">
						<c:set var="file_Key" value="${iMIssuAply.evddocId}=0"/>
						<a href="javascript:COMMT.fn_egov_downFileEnChkEncfile('<c:out value="${imfunc:encryptString(file_Key)}"/>')"  >
						<img src="${imWebStatic}/common/images/www/icon/ic_attach_file.png" alt="파일 첨부">
						</a>
						</c:if>
						</p>
						<form:hidden path="evddocId"/>
						<input type="file" id="${ATCH_FILE_ID}" value="${ATCH_FILE_ID}" name="evddocIdFile" title="파일찾기" accept="${imExtensionsFiles}" />
						<p class="tb_note md">2. 가족관계 기본 증명서
						<c:if test="${!empty  iMIssuAply.fmlyEvddocId}">
						<c:set var="file_Key" value="${iMIssuAply.fmlyEvddocId}=0"/>
						<a href="javascript:COMMT.fn_egov_downFileEnChkEncfile('<c:out value="${imfunc:encryptString(file_Key)}"/>')"  >
						<img src="${imWebStatic}/common/images/www/icon/ic_attach_file.png" alt="파일 첨부">
						</a>
						</c:if>
						</p>
						<form:hidden path="fmlyEvddocId"/>
						<input type="file" id="${FMLY_FILE_ID}" value="${FMLY_FILE_ID}" name="fmlyEvddocIdFile" title="파일찾기" accept="${imExtensionsFiles}" />
						
					</td>
				</tr>
				</c:if>
				<c:if test="${iMIssuAply.issuDvsnCdv ne '01'}">
				<tr>
					<th scope="row">증빙 첨부</th>
					<td>
						<p class="tb_note md m_0">1. 가족관계 기본 증명서
						<c:if test="${!empty  iMIssuAply.fmlyEvddocId}">
						<c:set var="file_Key" value="${iMIssuAply.fmlyEvddocId}=0"/>
						<a href="javascript:COMMT.fn_egov_downFileEnChkEncfile('<c:out value="${imfunc:encryptString(file_Key)}"/>')"  >
						<img src="${imWebStatic}/common/images/www/icon/ic_attach_file.png" alt="파일 첨부">
						</a>
						</c:if>
						</p>
						<form:hidden path="fmlyEvddocId"/>
						<input type="file" id="${FMLY_FILE_ID}" value="${FMLY_FILE_ID}" name="fmlyEvddocIdFile" title="파일찾기" accept="${imExtensionsFiles}" />
						<p class="tb_note md">2.환경교육사 자격증
						<c:if test="${!empty  iMIssuAply.evddocId}">
						<c:set var="file_Key" value="${iMIssuAply.evddocId}=0"/>
						<a href="javascript:COMMT.fn_egov_downFileEnChkEncfile('<c:out value="${imfunc:encryptString(file_Key)}"/>')"  >
						<img src="${imWebStatic}/common/images/www/icon/ic_attach_file.png" alt="파일 첨부">
						</a>
						</c:if>
						</p>
						<form:hidden path="evddocId"/>
						<input type="file" id="${ATCH_FILE_ID}" value="${ATCH_FILE_ID}" name="evddocIdFile" title="파일찾기" accept="${imExtensionsFiles}" />
					</td>
				</tr>
				</c:if>
			</tbody>
		</table>
		
		</form:form>
		
		
		<div class="cb_bar top">
		<p class="bl_title">심사정보</p>
		</div>
		<table class="tbl_row al">
			<colgroup>
				<col style="width:17%;">
			</colgroup>
			<tr>
				<th scope="row">발급상태</th>
				<td>
				<im:cd type="print" codeId="IM0023" selectedCode="${iMIssuAply.sttsCdv}" />
				</td>
			</tr>
			<tr>
				<th scope="row">처리 비고</th>
				<td></td>
			</tr>
			<tr>
				<th scope="row">신청일</th>
				<td>
				<im:dt yyyyMMddHHmmss="${iMIssuAply.aplyYmd}"/>
				</td>
			</tr>
			<tr>
				<th scope="row">확인일</th>
				<td><im:dt yyyyMMddHHmmss="${iMIssuAply.idntyYmd}"/>
				</td>
			</tr>
		</table>
		
		
		<c:if test="${iMIssuAply.sttsCdv eq '99' || iMIssuAply.sttsCdv eq '05'}">
		<div class="cb_bar">
			<div class="ct_box">
				<p class="p_noti"><span class="c_red"><i class="m_icon_out circle_notifications"></i>[안내]</span></p>
				<ul class="radio">
					<li>임시저장인 경우에는 추가 수정이 가능하나 자격증 발급 가능 여부를 심사하지는 않습니다.</li>
					<li>자격증 발급 신청을 바로 하시려면 저장하시면 됩니다. 저장 후에는 수정이 불가합니다.
				</ul>
			</div>
		</div>
		</c:if>
		<div class="b_box">
			<c:if test="${iMIssuAply.sttsCdv eq '99' || iMIssuAply.sttsCdv eq '05'}">
			<%--
			<a href="javascript:;" onclick="REQ.save('99')"  class="c_btn green mid">임시저장</a>
			--%>
			<a href="javascript:;" onclick="REQ.save('01')"  class="c_btn green mid">저장</a>
			<a href="javascript:;" onclick="REQ.cancel()"  class="c_btn red mid">신청취소</a>
			</c:if>
			
			<c:if test="${iMIssuAply.sttsCdv eq '02'}">
			
			<a href="javascript:;" onclick="REQ.printLicense('${iMIssuAply.issuAplyId}');" class="c_btn green mid">자격증 출력</a>
			</c:if>
			<a href="javascript:;" onclick="REQ.list()" class="c_btn gray mid">목록</a>
		</div>
		
