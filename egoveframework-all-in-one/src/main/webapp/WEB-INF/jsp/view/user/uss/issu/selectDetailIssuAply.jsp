<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>

<script type="text/javascript" >


var REQ = {


	req : {
		list : null,
		modify : null
	},
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetail";
		this.req.list.cfg.url    = "<c:url value="/user/issuAply/selectList.do"/>";
				

		this.req.modify = imRequest();
		this.req.modify.cfg.formId = "FormPageDetail";
		this.req.modify.cfg.url    = "<c:url value="/user/issuAply/modify.do"/>";
		
	
	},
	modify :  function(mapDataPks){
		COMMT.copyMapDataToFormReset(mapDataPks, this.req.modify.cfg.formId);
		REQ.req.modify.go();
	},
	page :  function(pageNo){
		var form =  document.getElementById(this.req.list.cfg.formId);
		form.elements["currentPageNo"].value= pageNo;
		this.req.list.go();
	},
	list :  function(){
		this.req.list.go();
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
		<c:if test="${iMIssuAply.sttsCdv eq '99' || iMIssuAply.sttsCdv eq '05'}">
		<a href="javascript:;" onclick="REQ.modify({'issuAplyId':'${iMIssuAply.issuAplyId}'});return false;"   class="c_btn bk">수정</a>
		</c:if>
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
						<div class="c_date">
							<im:dt yyyyMMddHHmmss="${iMIssuAply.eduBgnDt}"/>
						</div>
						~
						<div class="c_date">
							<im:dt yyyyMMddHHmmss="${iMIssuAply.eduEndDt}"/>
						</div>
					</td>
				</tr>
				<tr>
					<th scope="row">취득일자</th>
					<td>
						<div class="c_date">
							<im:dt yyyyMMddHHmmss="${iMIssuAply.lcncAcqsYmd}"/>
						</div>
					</td>
				</tr>
				<tr>
					<th scope="row">주민등록 번호</th>
					<td>
						${iMIssuAply.rrnoMasking}
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
						<a href="javascript:COMMT.fn_egov_downFileEnc('<c:out value="${imfunc:encryptString(file_Key)}"/>')"  >
						<img src="${imWebStatic}/common/images/www/icon/ic_attach_file.png" alt="파일 첨부">
						</a>
						</c:if>
						</p>
						<p class="tb_note md">2.환경교육사 자격증
						<c:if test="${!empty  iMIssuAply.evddocId}">
						<c:set var="file_Key" value="${iMIssuAply.evddocId}=0"/>
						<a href="javascript:COMMT.fn_egov_downFileEnc('<c:out value="${imfunc:encryptString(file_Key)}"/>')"  >
						<img src="${imWebStatic}/common/images/www/icon/ic_attach_file.png" alt="파일 첨부">
						</a>
						</c:if>
						</p>
						
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
			<c:if test="${iMIssuAply.sttsCdv eq '04'}">
			<tr>
				<th scope="row">처리 비고</th>
				<td><c:out value="${imfunc:textToBr(iMIssuAply.prcsRmks)}" escapeXml="false"/></td>
			</tr>
			</c:if>
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
		
		
