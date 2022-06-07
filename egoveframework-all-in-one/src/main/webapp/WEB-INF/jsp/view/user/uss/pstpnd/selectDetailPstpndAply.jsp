<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>

<script type="text/javascript" >


var REQ = {
	req : {
		list : null,
		del : null,
		modify : null,
	},
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetail";
		this.req.list.cfg.url    = "<c:url value="/user/pstpndAply/selectList.do"/>";
				
		
		this.req.modify = imRequest();
		this.req.modify.cfg.formId = "FormPageDetail";
		this.req.modify.cfg.url    = "<c:url value="/user/pstpndAply/modify.do"/>";
		
	
	},
	page :  function(pageNo){
		var form =  document.getElementById(this.req.list.cfg.formId);
		form.elements["currentPageNo"].value= pageNo;
		this.req.list.go();
	},
	list :  function(){
		this.req.list.go();
	},
	modify :  function(mapDataPks){
		COMMT.copyMapDataToFormReset(mapDataPks, this.req.modify.cfg.formId);
		REQ.req.modify.go();
	},
	print : function(pstpndAplyId){ 
		COMMT.fn_Enc_Print('<c:url value="/user/uss/mypage/selectDetailPstpndAplyPrint.do"/>?pstpndAplyId='+pstpndAplyId,1260,850);
	},
}


$(document).ready(function(){
	REQ.init();
});

</script>

<%@ include file="incPstpndAply.jsp" %>



<c:set var="dvsnCdvArr">01=법개정이전,02=법개정이후</c:set>
<c:set var="pstpndRsnCdvArr">01=본인의 질병,02=그 밖의 불가피한 사유,03=기타</c:set>
<c:set var="trgtYearArr">1=1년,2=2년,3=3년</c:set>
<div class="cb_bar right">
	<div class="add">
		<c:if test="${iMPstpndAply.sttsCdv eq '99' || iMPstpndAply.sttsCdv eq '05'}">
		<a href="javascript:;" onclick="REQ.modify({'pstpndAplyId':'${iMPstpndAply.pstpndAplyId}'});return false;"   class="c_btn bk">수정</a>
		</c:if>
		<c:if test="${iMPstpndAply.sttsCdv eq '02'}">
		<a href="javascript:;" onclick="REQ.print('${iMPstpndAply.pstpndAplyId}');" class="c_btn green">확인증 출력</a>
		</c:if>
		<a href="javascript:;" onclick="REQ.list()"  class="c_btn l_gray">목록</a>
	</div>
</div>

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
				<im:cd type="print" codeId="${dvsnCdvArr}" selectedCode="${iMPstpndAply.dvsnCdv}"/>
				</td>
			</tr>
			<tr>
				<th scope="row">자격증 발급번호</th>
				<td><form:hidden path="qlfcRsltCode"/>${iMPstpndAply.qlfcRsltCode}</td>
			</tr>
			<tr>
				<th scope="row">자격증번호</th>
				<td><form:hidden path="lcncIssuCode"/>${iMPstpndAply.lcncIssuCode}</td>
			</tr>
			<tr>
				<th scope="row">자격취득일자</th>
				<td><form:hidden path="lcncAcqsYmd"/>${iMPstpndAply.lcncAcqsYmd}</td>
			</tr>
			<tr>
				<th scope="row">이름</th>
				<td>${IMLoginUser.name}</td>
			</tr>
			<tr>
					<th scope="row">핸드폰번호 </th>
					<td>${iMPstpndAply.mmbrTelno}
					</td>
			</tr>
			<tr>
				<th scope="row">생년월일 </th>
				<td>
						<div class="c_date">
							${iMPstpndAply.brdt}
						</div>
				
				</td>
			</tr>
			<tr>
				<th scope="row">대상연도 </th>
				<td>
					<im:cd type="print" codeId="${trgtYearArr}" selectedCode="${iMPstpndAply.trgtYear}"/>
				</td>
			</tr>
			<tr>
					<th scope="row">주소</th>
					<td>[${iMPstpndAply.zipc}]	${iMPstpndAply.addr}  &nbsp; ${iMPstpndAply.addrDtl}
					</td>
			</tr>
			<tr>
				<th scope="row">유예사유 </th>
				<td><im:cd type="print" codeId="${pstpndRsnCdvArr}" selectedCode="${iMPstpndAply.pstpndRsnCdv}"/>
				</td>
			</tr>
			<tr>
				<th scope="row">유예사유 비고  </th>
				<td>
					<c:out value="${imfunc:textToBr(iMPstpndAply.pstpndRsn)}" escapeXml="false"/>
				</td>
			</tr>
			<tr>
				<th scope="row">첨부파일</th>
				<td>
				<c:forEach items="${detail.fileList}" var="frow">
						<c:set var="file_Key" value="${frow.atchFileId}=${frow.fileSn}"/>
						<a href="javascript:COMMT.fn_egov_downFileEnc('<c:out value="${imfunc:encryptString(file_Key)}"/>')" class="file_link" >
							<c:out value="${frow.orignlFileNm}"/>
						</a>	
				</c:forEach>
				
				</td>
			</tr>
			</tr>

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
				<im:cd type="print" codeId="IM0024" selectedCode="${iMPstpndAply.sttsCdv}" />
				</td>
			</tr>
			<c:if test="${iMPstpndAply.sttsCdv eq '04'}">
			<tr>
				<th scope="row">처리 비고</th>
				<td><c:out value="${imfunc:textToBr(iMPstpndAply.prcsRmks)}" escapeXml="false"/></td>
			</tr>
			</c:if>
			<tr>
				<th scope="row">신청일</th>
				<td>
				<im:dt yyyyMMddHHmmss="${iMPstpndAply.aplyYmd}"/>
				</td>
			</tr>
			<tr>
				<th scope="row">확인일</th>
				<td><im:dt yyyyMMddHHmmss="${iMPstpndAply.idntyYmd}"/>
				</td>
			</tr>
		</table>
		

<div class="cb_bar top">
		<p class="bl_title">유예 신청 이력</p>
</div>

<table class="tbl_col">
	<colgroup>
		<col style="width:120px;"/>
		<col style="width:120px;"/>
		<col style="width:120px;"/>
		<col style="width:120px;"/>
	</colgroup>
	<thead>
	<tr>
			<th scope="col">신청일</th>
			<th scope="col">유예상태</th>
				<th scope="col">유예기간 만료일</th>
				<th scope="col">확인일</th>
	</tr>				
	</thead>	
	
	<tbody>
		<c:forEach items="${userList}" var="row" varStatus="i">
		<tr>
			<td><im:dt yyyyMMddHHmmss="${row.pstpndAply.aplyYmd}"/></td>
			<td><im:cd type="print" codeId="IM0024" selectedCode="${row.pstpndAply.sttsCdv}"/></td>
			<td><im:dt yyyyMMddHHmmss="${row.pstpndAply.lcncEndYmd}"/></td>
			<%--
			<td class="al"><c:out value="${imfunc:textToBr(row.pstpndAply.prcsRmks)}" escapeXml="false"/></td>
			 --%>
			<td><im:dt yyyyMMddHHmmss="${row.pstpndAply.idntyYmd}"/></td>
		</tr>		
		</c:forEach>
	</tbody>	
</table>	