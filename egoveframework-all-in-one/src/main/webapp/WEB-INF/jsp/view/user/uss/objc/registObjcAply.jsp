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
		this.req.list.cfg.url    = "<c:url value="/user/objcAply/selectList.do"/>";
				
		
		this.req.save = imRequest("ajax",{formId: "iMObjcAply"});
		this.req.save.cfg.type   = "json";
		this.req.save.cfg.url    =  "<c:url value="/user/objcAply/insert.do"/>";
		this.req.save.cfg.message.confirm="<spring:message code="common.regist.msg" />";
		this.req.save.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) { 
	        	IMGLBObject.request.list();
	        } else {
	        	COMMT.errorMessage();
	        }
	    };	
	    
	    this.req.save.validator.set({
	        title : "구분",
	        name : "dvsnCdv",
	        data : ["!null"]
	    });
	    <c:if test="${iMObjcAply.dvsnCdv eq '01' || iMObjcAply.dvsnCdv eq '03'}">
	    this.req.save.validator.set({
	        title : "양성기관",
	        name : "agncyId",
	        data : ["!null"]
	    });
	    </c:if>
	    this.req.save.validator.set({
	        title : "등급",
	        name : "crsGrdCdv",
	        data : ["!null"]
	    });
	    <c:if test="${iMObjcAply.dvsnCdv eq '01'}">
	    this.req.save.validator.set({
	        title : "과정구분",
	        name : "crsDvsnCdv",
	        data : ["!null"]
	    });
	    </c:if>

	    <c:if test="${iMObjcAply.dvsnCdv eq '01' || iMObjcAply.dvsnCdv eq '02'}">
	    this.req.save.validator.set({
	        title : "년도",
	        name : "eduYear",
	        data : ["!null"]
	    });
	    this.req.save.validator.set({
	        title : "차수",
	        name : "eduRnd",
	        data : ["!null"]
	    });
	    </c:if>
	    this.req.save.validator.set({
	        title : "제목",
	        name : "aplyNm",
	        data : ["!null"]
	    });
	    this.req.save.validator.set({
	        title : "문의 구분",
	        name : "objcDvsnCdv",
	        data : ["!null"]
	    });
	    this.req.save.validator.set({
	        title : "내용",
	        name : "aplyDesc",
	        data : ["!null"]
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

<%@ include file="incObjcAply.jsp" %>


<validator:javascript formName="iMObjcAply" staticJavascript="false" xhtml="true" cdata="false"/>


<form:form commandName="iMObjcAply" name="iMObjcAply" method="post" onsubmit="return false;">
<form:hidden path="dvsnCdv"/>
<table class="tbl_row">
	<caption>이의신청 상세정보</caption>
	<colgroup>
		<col style="width:20%;">
		<col>
	</colgroup>
	<tbody>
			<tr>
				<th scope="row">구분</th>
				<td><im:cd type="print" codeId="${dvsnCdvArr}" selectedCode="${iMObjcAply.dvsnCdv}"/></td>
			</tr>
			<c:if test="${iMObjcAply.dvsnCdv eq '01' || iMObjcAply.dvsnCdv eq '03'}">
			<th scope="row">양성기관<span class="c_red">*</span></th>
				<td>
					<select name="agncyId" >
						<option value="">선택</option>
						<c:forEach items="${agncyList}" var="row">
						<option value="${row.agncy.agncyId}">${row.agncy.agncyNm}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			</c:if>
			<tr>
				<th scope="row">등급<span class="c_red">*</span></th>
				<td><im:cd type="radio" codeId="IM0001" name="crsGrdCdv"/></td>
			</tr>
			
			<c:if test="${iMObjcAply.dvsnCdv eq '01'}">
			<tr>
				<th scope="row">과정구분<span class="c_red">*</span></th>
				<td><im:cd type="radio" codeId="IM0002" name="crsDvsnCdv"/></td>
			</tr>
			</c:if>
			<c:if test="${iMObjcAply.dvsnCdv eq '01' || iMObjcAply.dvsnCdv eq '02'}">
			<tr>
				<th scope="row">년도<span class="c_red">*</span></th>
				<td>
				<select name="eduYear" >
				<c:forEach begin="${imNowYear}" var="num" end="${imNowYear+1}">
				<option value="${num}">${num}</option>
				</c:forEach>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMCrs.eduRnd"/><span class="c_red">*</span></th>
				<td>
				<select name="eduRnd" >
				<option value="">선택</option>
				<c:forEach begin="${imEduRndStart}" var="num" end="${imEduRndEnd}">
				<option value="${num}">${num}</option>
				</c:forEach>
			</select>
				</td>
			</tr>
			</c:if>
			<tr>
				<th scope="row">제목<span class="c_red">*</span></th>
				<td><form:input path="aplyNm" maxlength="80"/></td>
			</tr>
			<tr>
				<th scope="row">문의구분<span class="c_red">*</span></th>
				<td>
				<c:set var="objcDvsnCdvArr">0101=수강신청,0102=자격심사,0103=선정,0104=출석점수,0105=실기점수,99=기타</c:set>
				
				<c:if test="${iMObjcAply.dvsnCdv eq '02'}">
				<c:set var="objcDvsnCdvArr">0201=원서접수,0102=자격심사,0103=선정,0202=점수,99=기타</c:set>
				</c:if>
				<c:if test="${iMObjcAply.dvsnCdv eq '03'}">
				<c:set var="objcDvsnCdvArr">0301=발급,0302=재발급,99=기타</c:set>
				</c:if>
				<c:if test="${iMObjcAply.dvsnCdv eq '04'}">
				<c:set var="objcDvsnCdvArr">0401=유예신청,0402=유예사유,99=기타</c:set>
				</c:if>
				<select name="objcDvsnCdv" >
				<option value="">선택</option>
				<im:cd type="option" codeId="${objcDvsnCdvArr}"/>
				</select>
				</td>
			</tr>
			<tr>
				<th scope="row">내용<span class="c_red">*</span></th>
				<td>
				<textarea name="aplyDesc" id="" cols="" rows="" title="내용"></textarea>
				</td>
			</tr>

	</tbody>
</table>
</form:form>
		<div class="b_box">
			<a href="javascript:;"  onclick="REQ.save()" class="c_btn d_green mid">확인</a>
			<a href="javascript:;"  onclick="REQ.list()" class="c_btn wt mid">취소</a>
		</div>