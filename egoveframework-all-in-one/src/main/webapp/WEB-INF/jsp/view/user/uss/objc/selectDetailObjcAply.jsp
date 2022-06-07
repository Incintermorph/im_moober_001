<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script type="text/javascript" >


var REQ = {
	req : {
		list : null,
		modify : null,
		del : null,
	},
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetail";
		this.req.list.cfg.url    = "<c:url value="/user/objcAply/selectList.do"/>";
				
		this.req.modify = imRequest();
		this.req.modify.cfg.formId = "FormPageDetail";
		this.req.modify.cfg.url    = "<c:url value="/user/objcAply/modify.do"/>";
				
		    
	    
	    this.req.del = imRequest("ajax");
	    this.req.del.cfg.formId = "iMObjcAply";
	    this.req.del.cfg.type   = "json";
	    this.req.del.cfg.url    = "<c:url value="/user/objcAply/delete.do"/>";
	    this.req.del.cfg.message.confirm="<spring:message code="common.delete.msg" />";
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
	modify :  function(mapDataPks){
		COMMT.copyMapDataToFormReset(mapDataPks, this.req.modify.cfg.formId);
		REQ.req.modify.go();
	},
	del :  function(){
		IMGLBObject.request=this;
		this.req.del.go();
	}
}


$(document).ready(function(){
	REQ.init();
});

</script>

<%@ include file="incObjcAply.jsp" %>



<form:form commandName="iMObjcAply" name="iMObjcAply" method="post" onsubmit="return false;">
<form:hidden path="objcAplyId"/>

<table class="tbl_row">
	<caption>이의신청 상세정보</caption>
	<colgroup>
		<col style="width:20%;">
		<col>
	</colgroup>
	<tbody>
			<tr>
				<th scope="row">구분</th>
				<td>
					<im:cd type="print" codeId="${dvsnCdvArr}" selectedCode="${detail.objcAply.dvsnCdv}"/>
				</td>
			</tr>
			<c:if test="${iMObjcAply.dvsnCdv eq '01' || iMObjcAply.dvsnCdv eq '03' }">
			<tr>
				<th scope="row">양성기관</th>
				<td>
						<c:forEach items="${agncyList}" var="row">
						<c:if test="${iMObjcAply.agncyId eq row.agncy.agncyId }">
						${row.agncy.agncyNm}
						</c:if>
						</c:forEach>
				</td>
			</tr>
			</c:if>
			<tr>
				<th scope="row">등급</th>
				<td>
					<im:cd type="print" codeId="IM0001" selectedCode="${detail.objcAplyDtl.crsGrdCdv}"/>
				</td>
			</tr>
			
			<c:if test="${iMObjcAply.dvsnCdv eq '01'}">
			<tr>
				<th scope="row">과정구분</th>
				<td>
				<im:cd type="print" codeId="IM0002" selectedCode="${detail.objcAplyDtl.crsDvsnCdv}"/>
			</tr>
			</c:if>
			
			<c:if test="${iMObjcAply.dvsnCdv eq '01' || iMObjcAply.dvsnCdv eq '02' }">
			<tr>
				<th scope="row">년도</th>
				<td>${detail.objcAplyDtl.eduYear}</td>
			</tr>
			<tr>
				<th scope="row">차수</th>
				<td>${detail.objcAplyDtl.eduRnd}</td>
			</tr>
			</c:if>
			<tr>
				<th scope="row">제목</th>
				<td><c:out value="${detail.objcAply.aplyNm}" /></td>
			</tr>
			<tr>
				<th scope="row">문의구분</th>
				<td>
					<im:cd type="print" codeId="${objcDvsnCdvArr}" selectedCode="${detail.objcAply.objcDvsnCdv}"/>
				</td>
			</tr>
			<tr>
				<th scope="row">내용</th>
				<td>
				<c:out value="${imfunc:textToBr(detail.objcAply.aplyDesc)}" escapeXml="false"/>
				</td>
			</tr>

	</tbody>
</table>
</form:form>


<c:if test="${detail.objcAply.sttsCdv eq '02'}">
<div class="cb_bar top">
<p class="bl_title">관리자 답변 내용입니다.</p>
</div>

<table class="tbl_row">
	<caption>이의신청 상세정보</caption>
	<colgroup>
		<col style="width:20%;">
		<col>
	</colgroup>
	<tbody>
			<tr>
				<th scope="row">답변</th>
				<td>
					<c:out value="${imfunc:textToBr(detail.objcAply.ansDesc)}" escapeXml="false"/>
				</td>
			</tr>
			<tr>
				<th scope="row">답변일자</th>
				<td>
					<im:dt yyyyMMddHHmmss="${detail.objcAply.ansRegYmd}"/>
				</td>
			</tr>
	</tbody>
</table>			
</c:if>

		<div class="b_box">
			<c:if test="${detail.objcAply.sttsCdv eq '01'}">
			<a href="javascript:;"  onclick="REQ.modify({'objcAplyId' : '${iMObjcAply.objcAplyId}'});" class="c_btn bk mid">수정</a>
			</c:if>
			<a href="javascript:;"  onclick="REQ.del()" class="c_btn red mid">삭제</a>			
			<a href="javascript:;"  onclick="REQ.list()" class="c_btn wt mid">목록</a>
		</div>