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
		del : null,
	},
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetail";
		this.req.list.cfg.url    = "<c:url value="/mng/objcAply/selectList.do"/>";
				
		
		this.req.save = imRequest("ajax",{formId: "iMObjcAply"});
		this.req.save.cfg.type   = "json";
		this.req.save.cfg.url    =  "<c:url value="/mng/objcAply/update.do"/>";
		this.req.save.cfg.message.confirm="<spring:message code="common.update.msg" />";
		this.req.save.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) { 
	        	IMGLBObject.request.list();
	        } else {
	        	COMMT.errorMessage();
	        }
	    };	
	    this.req.save.validator.set({
	        title : "답변",
	        name : "ansDesc",
	        data : ["!null"]
	    });
	    
	    
	    this.req.del = imRequest("ajax");
	    this.req.del.cfg.formId = "iMObjcAply";
	    this.req.del.cfg.type   = "json";
	    this.req.del.cfg.url    = "<c:url value="/mng/objcAply/delete.do"/>";
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
	save :  function(){
		IMGLBObject.request=this;
		this.req.save.go();
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


<validator:javascript formName="iMObjcAply" staticJavascript="false" xhtml="true" cdata="false"/>

<div class="b_box right">
	<div> 
	<a href="#" onclick="REQ.list();return false;" class="ad_btn bk">목록</a>
	<a href="#" onclick="REQ.save();return false;" class="ad_btn green">저장</a>
	</div>
</div>

<form:form commandName="iMObjcAply" name="iMObjcAply" method="post" onsubmit="return false;">
<form:hidden path="objcAplyId"/>
<table class="tbl_row">
	<caption>이의신청 상세정보</caption>
	<colgroup>
		<col style="width:200px;">
		<col>
	</colgroup>
	<tbody>
			<tr>
				<th scope="row">회원 아이디</th>
				<td><c:out value="${detail.mberManage.mberId}"/></td>
			</tr>	
			<tr>
				<th scope="row">이름</th>
				<td><c:out value="${detail.mberManage.mberNm}"/></td>
			</tr>	
			<tr>
				<th scope="row">이메일</th>
				<td><c:out value="${detail.mberManage.mberEmailAdres}"/></td>
			</tr>	
			<tr>
				<th scope="row">휴대전화번호</th>
				<td><c:out value="${detail.mberManage.moblphonNo}"/></td>
			</tr>
			<tr>
				<th scope="row">구분</th>
				<td>
					<im:cd type="print" codeId="${dvsnCdvArr}" selectedCode="${detail.objcAply.dvsnCdv}"/>
				</td>
			</tr>
			<c:if test="${iMObjcAply.dvsnCdv eq '01' || iMObjcAply.dvsnCdv eq '03'}">
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
			<c:if test="${iMObjcAply.dvsnCdv eq '01' || iMObjcAply.dvsnCdv eq '02'}">
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
		<br/>
		<p class="ct_title">관리자 답변</p>
			
		<table class="tbl_row">
			<caption>이의신청 상세정보</caption>
			<colgroup>
				<col style="width:200px;">
				<col>
			</colgroup>
			<tbody>
			<tr>
				<th scope="row">답변<span class="c_red">*</span></th>
				<td>
					<textarea  name="ansDesc" id="" cols="" rows="" title="내용">${detail.objcAply.ansDesc}</textarea>
				</td>
			</tr>
			<tr>
				<th scope="row">상태<span class="c_red">*</span></th>
				<td>
				<select name="sttsCdv" >
				<im:cd type="option" codeId="${sttsCdvArr}" selectedCode="${detail.objcAply.sttsCdv}"/>
				</select> (상태를 '답변완료'로 수정해야 사용자에게 조회 됩니다.)
				</td>
			</tr>

	</tbody>
</table>
</form:form>
<div class="b_box right">
	<div> 
	<a href="#" onclick="REQ.list();return false;" class="ad_btn bk">목록</a>
	<a href="#" onclick="REQ.save();return false;" class="ad_btn green">저장</a>
	</div>
</div>
	