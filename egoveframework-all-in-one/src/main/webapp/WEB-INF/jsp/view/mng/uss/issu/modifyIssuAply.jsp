<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>

<script type="text/javascript" >


var REQ = {
	req : {
		list : null,
		save : null,
	},
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetail";
		this.req.list.cfg.url    = "<c:url value="/mng/issuAply/${stts}/selectList.do"/>";
				
		
		this.req.save = imRequest("ajax",{formId: "iMIssuAply"});
		this.req.save.cfg.type   = "json";
		this.req.save.cfg.url    =  "<c:url value="/mng/issuAply/update.do"/>";
		this.req.save.cfg.message.confirm="<spring:message code="common.update.msg" />";
		this.req.save.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) { 
	        	IMGLBObject.request.list();
	        } else {
	        	COMMT.errorMessage();
	        }
	    };	
	    this.req.save.validator.set({  
            title : "발급상태", 
            name : "sttsCdv", 
            data : ["!null"],
        } );
	    this.req.save.validator.set({  
            title : "처리 비고", 
            name : "prcsRmks", 
            data : ["!null"],
        } );
	    
	    
	
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
	},
}


$(document).ready(function(){
	REQ.init();
});

</script>

<%@ include file="incIssuAply.jsp" %>


<validator:javascript formName="iMIssuAply" staticJavascript="false" xhtml="true" cdata="false"/>

<div class="cb_bar right">
	<div> 
	<a href="#" onclick="REQ.list();return false;" class="ad_btn bk">목록</a> 
	<c:if test="${iMIssuAply.sttsCdv ne '03' }">
	<a href="#" onclick="REQ.save();return false;" class="ad_btn green">저장</a>
	</c:if> 
	</div>
</div>

<form:form commandName="iMIssuAply" name="iMIssuAply" method="post" onsubmit="return false;">
<table class="tbl_row">
	<caption>발급신청 상세정보</caption>
	<colgroup>
		<col style="width:200px;">
		<col>
	</colgroup>
	<tbody>
				<tr>
					<th scope="row">발급 구분</th>
					<td><im:cd type="print" codeId="IM0022" name="issuDvsnCdv"  selectedCode="${iMIssuAply.issuDvsnCdv}"/>
						
					</td>
				</tr>
				<tr>
					<th scope="row">자격증번호</th>
					<td>
					${iMIssuAply.qlfcRsltCode}
					</td>
				</tr>
				<tr>
					<th scope="row">이름</th>
					<td>${IMLoginUser.name}</td>
				</tr>
				<tr>
					<th scope="row">이수 양성기관명</th>
					<td>
						${detail.agncy.agncyNm}
					</td>
				</tr>
				<tr>
					<th scope="row">등급</th>
					<td>
					<im:cd type="print" name="crsGrdCdv" selectedCode="${iMIssuAply.crsGrdCdv}" codeId="IM0001"/>
					</td>
				</tr>
				<tr>
					<th scope="row">교육 기간</th>
					<td>
						
							<im:dt yyyyMMddHHmmss="${iMIssuAply.eduBgnDt}"/>
						
						~
						
							<im:dt yyyyMMddHHmmss="${iMIssuAply.eduEndDt}"/>
						
					</td>
				</tr>
				<tr>
					<th scope="row">취득일자</th>
					<td>
						
							<im:dt yyyyMMddHHmmss="${iMIssuAply.lcncAcqsYmd}"/>
						
					</td>
				</tr>
				<tr>
					<th scope="row">주민등록 번호</th>
					<td>
						${iMIssuAply.rrno1} - ${iMIssuAply.rrno2} 
					</td>
				</tr>
				<tr>
					<th scope="row">핸드폰번호</th>
					<td>${iMIssuAply.mmbrTelno}
					</td>
				</tr>
				<tr>
					<th scope="row">주소</th>
					<td>[${iMIssuAply.zipc}]  ${iMIssuAply.addr} &nbsp; ${iMIssuAply.addrDtl}
					</td>
				</tr>
				<tr>
					<th scope="row">발급 사유 비고</th>
					<td>
						${iMIssuAply.issuRsn}
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
						<p class="tb_note md">2. 가족관계 기본 증명서
						<c:if test="${!empty  iMIssuAply.fmlyEvddocId}">
						<c:set var="file_Key" value="${iMIssuAply.fmlyEvddocId}=0"/>
						<a href="javascript:COMMT.fn_egov_downFileEnChkEncfile('<c:out value="${imfunc:encryptString(file_Key)}"/>')"  >
						<img src="${imWebStatic}/common/images/www/icon/ic_attach_file.png" alt="파일 첨부">
						</a>
						</c:if>
						</p>
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
						<p class="tb_note md">2.환경교육사 자격증
						<c:if test="${!empty  iMIssuAply.evddocId}">
						<c:set var="file_Key" value="${iMIssuAply.evddocId}=0"/>
						<a href="javascript:COMMT.fn_egov_downFileEnChkEncfile('<c:out value="${imfunc:encryptString(file_Key)}"/>')"  >
						<img src="${imWebStatic}/common/images/www/icon/ic_attach_file.png" alt="파일 첨부">
						</a>
						</c:if>
						</p>
						
					</td>
				</tr>
				</c:if>
			</tbody>
</table>
<input type="hidden" name="issuAplyId"  value="${iMIssuAply.issuAplyId}"/>
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
				<c:if test="${iMIssuAply.sttsCdv eq '03' }">
				<im:cd type="print" codeId="IM0023" name="sttsCdv" selectedCode="${iMIssuAply.sttsCdv}" except="99,01" />
				</c:if>
				<c:if test="${iMIssuAply.sttsCdv ne '03' }">
				<im:cd type="radio" codeId="IM0023" name="sttsCdv" selectedCode="${iMIssuAply.sttsCdv}" except="99,01" />
				</c:if>
				</td>
			</tr>
			<tr>
				<th scope="row">자격증 발급번호</th>
				<td>${iMIssuAply.lcncIssuCode}</td>
			</tr>
			<tr>
				<th scope="row">처리 비고</th>
				<td><textarea name="prcsRmks" id="" cols="" rows="" title="발급 사유 비고">${iMIssuAply.prcsRmks}</textarea></td>
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
</form:form>
<div class="b_box right">	
	<div> 
	<a href="#" onclick="REQ.list();return false;" class="ad_btn bk">목록</a>
	<c:if test="${iMIssuAply.sttsCdv ne '03' }"> 
	<a href="#" onclick="REQ.save();return false;" class="ad_btn green">저장</a>
	</c:if> 
	</div>
</div>