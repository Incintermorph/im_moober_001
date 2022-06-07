<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>

<script type="text/javascript" >


var REQ = {
	req : {
		list : null,
		save : null
	},
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetail";
		this.req.list.cfg.url    = "<c:url value="/mng/pstpndAply/${stts}/selectList.do"/>";
				
		
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

<%@ include file="incPstpndAply.jsp" %>

<c:set var="pstpndRsnCdvArr">01=본인의 질병,02=그 밖의 불가피한 사유,03=기타</c:set>
<c:set var="trgtYearArr">1=1년,2=2년,3=3년</c:set>

<div class="b_box right">	
	<div> 
	<a href="#" onclick="REQ.list();return false;" class="ad_btn bk">목록</a>
	<c:if test="${iMPstpndAply.sttsCdv ne '03' }"> 
	<a href="#" onclick="REQ.save();return false;" class="ad_btn green">저장</a>
	</c:if> 
	</div>
</div>
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
				<im:cd type="print" codeId="${dvsnCdvArr}" selectedCode="${iMPstpndAply.dvsnCdv}"/>
				</td>
			</tr>
			<tr>
				<th scope="row">자격증 발급번호</th>
				<td>${iMPstpndAply.lcncIssuCode}</td>
			</tr>
			<tr>
				<th scope="row">자격증번호</th>
				<td>${iMPstpndAply.qlfcRsltCode}</td>
			</tr>
			<tr>
				<th scope="row">자격취득일자</th>
				<td>
				<im:dt yyyyMMddHHmmss="${iMPstpndAply.lcncAcqsYmd}"/>
				</td>
			</tr>
			<tr>
				<th scope="row">이름</th>
				<td>${iMPstpndAply.mberNm}</td>
			</tr>
			<tr>
					<th scope="row">핸드폰번호 <span class="c_red">*</span></th>
					<td>
					${iMPstpndAply.mmbrTelno}
					</td>
			</tr>
			<tr>
				<th scope="row">생년월일 <span class="c_red">*</span></th>
				<td>
					${iMPstpndAply.brdtPrint}
				
				</td>
			</tr>
			<tr>
				<th scope="row">대상연도 <span class="c_red">*</span></th>
				<td>
					<im:cd type="print" codeId="${trgtYearArr}" selectedCode="${iMPstpndAply.trgtYear}" name="trgtYear"/>
				</td>
			</tr>
			<tr>
					<th scope="row">주소<span class="c_red">*</span></th>
					<td>
						[${iMPstpndAply.zipc}]	${iMPstpndAply.addr}  &nbsp; ${iMPstpndAply.addrDtl}
					</td>
			</tr>
			<tr>
				<th scope="row">유예사유 <span class="c_red">*</span></th>
				<td>
				<im:cd type="print" codeId="${pstpndRsnCdvArr}" selectedCode="${iMPstpndAply.pstpndRsnCdv}"/>
				</td>
			</tr>
			<tr>
				<th scope="row">유예사유 비고 <span class="c_red">*</span> </th>
				<td>
							<c:out value="${imfunc:textToBr(iMPstpndAply.pstpndRsn)}" escapeXml="false"/>
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
				</td>
			</tr>

	</tbody>
</table>

<form:form commandName="iMPstpndAply" name="iMPstpndAply" method="post" onsubmit="return false;">
<form:hidden path="pstpndAplyId"/>
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
				<c:if test="${iMPstpndAply.sttsCdv eq '03' }">
				<im:cd type="print" codeId="IM0024" name="sttsCdv" selectedCode="${iMPstpndAply.sttsCdv}" except="99,01" />
				</c:if>
				<c:if test="${iMPstpndAply.sttsCdv ne '03' }">
				<im:cd type="radio" codeId="IM0024" name="sttsCdv" selectedCode="${iMPstpndAply.sttsCdv}" except="99,01" />
				</c:if>
				</td>
			</tr>
			<tr>
				<th scope="row">처리 비고</th>
				<td><textarea name="prcsRmks" id="" cols="" rows="" title="발급 사유 비고">${iMPstpndAply.prcsRmks}</textarea></td>
			</tr>
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
</form:form>

<div class="cb_bar top">
		<p class="bl_title">유예 신청 이력</p>
</div>

<table class="tbl_col">
	<colgroup>
		<col style="width:120px;"/>
		<col style="width:120px;"/>
		<col style="width:120px;"/>
		<col />
		<col style="width:120px;"/>
	</colgroup>
	<thead>
	<tr>
			<th scope="col">신청일</th>
			<th scope="col">유예상태</th>
				<th scope="col">유예기간 만료일</th>
				<th scope="col">비고</th>
				<th scope="col">확인일</th>
	</tr>				
	</thead>	
	
	<tbody>
		<c:forEach items="${userList}" var="row" varStatus="i">
		<tr>
			<td><im:dt yyyyMMddHHmmss="${row.pstpndAply.aplyYmd}"/></td>
			<td><im:cd type="print" codeId="IM0024" selectedCode="${row.pstpndAply.sttsCdv}"/></td>
			<td><im:dt yyyyMMddHHmmss="${row.pstpndAply.lcncEndYmd}"/></td>
			<td class="al"><c:out value="${imfunc:textToBr(row.pstpndAply.prcsRmks)}" escapeXml="false"/></td>
			<td><im:dt yyyyMMddHHmmss="${row.pstpndAply.idntyYmd}"/></td>
		</tr>		
		</c:forEach>
	</tbody>	
</table>	
		
<div class="b_box right">	
	<div> 
	<a href="#" onclick="REQ.list();return false;" class="ad_btn bk">목록</a>
	<c:if test="${iMPstpndAply.sttsCdv ne '03' }"> 
	<a href="#" onclick="REQ.save();return false;" class="ad_btn green">저장</a>
	</c:if> 
	</div>
</div>