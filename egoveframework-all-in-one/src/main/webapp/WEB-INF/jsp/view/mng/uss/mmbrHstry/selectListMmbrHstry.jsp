<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>


<script type="text/javascript" >


var REQ = {
	req : {
		list : null,
		search : null,		
		del : null,
	},
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetail";
		this.req.list.cfg.url    = "<c:url value="/mng/mmbrHstry/selectList.do"/>";
		
		this.req.search = imRequest();
		this.req.search.cfg.formId = "FormSearch";
		this.req.search.cfg.url    = "<c:url value="/mng/mmbrHstry/selectList.do"/>";
		
	},
	page :  function(pageNo){
		var form =  document.getElementById(this.req.list.cfg.formId);
		form.elements["currentPageNo"].value= pageNo;
		this.req.list.go();
	},
	search : function(){
		REQ.req.search.go();
	},
	changePerPage : function(recordCountPerPage){
		var form =  document.getElementById(this.req.list.cfg.formId);
		form.elements["recordCountPerPage"].value= recordCountPerPage;
		if(recordCountPerPage==-1){
			form.elements["sercharAll"].value= "1";
		}else{
			form.elements["sercharAll"].value= "0";
		}
		this.page(1);
	},
	changeOrdeBy : function(orderbyKey){
		var form =  document.getElementById(this.req.list.cfg.formId);
		form.elements["orderbyKey"].value= orderbyKey;
		this.page(1);
	},
	view : function(memberSrl){
		var req = imRequest("popup");
		
		req.cfg.options.width=2048;
		req.cfg.options.height=1024;
		
		req.cfg.formId = "FormPageDetail";
		
		var form =  document.getElementById(req.cfg.formId);
		form.elements["memberSrl"].value= memberSrl;
		req.cfg.url    = "${pageContext.request.contextPath}/mng/mmbrHstry/modify.do";
		req.go();
	}
	
}


$(document).ready(function(){
	REQ.init();
});


</script>


<%@ include file="incMmbrHstry.jsp" %>

<im:form  condition="${condition}" formName="FormSearch"  type="search"> <%--검색인 경우 타입 --%>
<%--기본 검색 키 값 정의 키=값,키=값 으로 정의함 --%>
<c:set var="scKey">mberId=회원아이디,mmbrNm=이름,eml=이메일,mblTelno=휴대전화번호</c:set>
	<div class="sch_box">
		<div class="group">
		<select name="scAplyGrdCdv" onchange="REQ.search()" >
				<option value="">신청희망 자격등급</option>
				<im:cd type="option" codeId="IM0001"  selectedCode="${condition.scAplyGrdCdv}"/>
			</select>
		</div>		
		<div class="group">
			<select name="scKey" >
				<im:cd type="option" codeId="${scKey}" selectedCode="${condition.scKey}"/>
			</select>
			<input type="text" name="scWord" value="<c:out value="${condition.scWord}"/>"  onKeyup="COMMT.enterCallFunc(event, REQ.search);" style="width:300px;">
			<button type="button" onclick="REQ.search()" class="ad_btn gray"><spring:message code="im.common.word.select" /></button>
		</div>
	</div>
</im:form>
 


<%--목록 상단 --%>
<div class="cb_bar">
	<c:set var="scOrderInfo">2=회원 아이디,3=이름</c:set><%-- todo  정렬 조건 추가  --%>
	<im:cd type="set" codeId="${scOrderInfo}" var="orderInfo"/>
	<%@ include file="/WEB-INF/jsp/inc/imPagListOrder.jspf" %>
</div>
<%--목록 내용  --%>
<form name="FormList" id="FormList" method="post" onsubmit="return false;">
<table class="tbl_col">
	<colgroup>
		<col style="width:100px;">		
		<col style="width:200px;">
		<col style="width:100px;">
		<col/>	
		<col style="width:115px;">
		<col style="width:119px;">
		<col style="width:100px;">
		<col style="width:100px;">
		<col style="width:100px;">
		<col style="width:75px;">
	</colgroup>
	<thead>
		<tr>
			<th scope="col"><spring:message code="im.common.word.No"/></th>
			<th scope="col">회원 아이디</th>
			<th scope="col">이름</th>
			<th scope="col">이메일</th>
			<th scope="col">휴대전화번호</th>
			<th scope="col">신청희망 자격등급</th>
			<th scope="col">유사과목 면제</th>
			<th scope="col">등록일</th>
			<th scope="col">수정일</th>
			<th scope="col"><spring:message code="im.common.word.mng" /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageInfo.list}" var="row" varStatus="i">
		<tr>
			<td><c:out value="${row.mmbrHstry.memberSrl}"/></td>
			<td  class="al"><c:out value="${row.mberManage.mberIdMask}"/></td>
			<td><c:out value="${row.mmbrHstry.mmbrNmMask}"/></td>
			<td class="al"><c:out value="${row.mmbrHstry.emlMask}"/></td>
			<td><c:out value="${row.mmbrHstry.mblTelnoMasking}"/></td>
			<td><im:cd type="print" codeId="IM0001" selectedCode="${row.mmbrHstry.aplyGrdCdv}"/></td>
			<td><im:cd type="print" codeId="IMYENO"  selectedCode="${row.mmbrHstry.exptSbjYn}"/></td>
			<td><im:dt yyyyMMddHHmmss="${row.mmbrHstry.frstRegDt}"/></td>
			<td><im:dt yyyyMMddHHmmss="${row.mmbrHstry.lastMdfcnDt}"/></td>
			<td>
			<a href="javascript:REQ.view(<c:out value="${row.mmbrHstry.memberSrl}"/>);">
			[이력관리]
			</a>
			</td>
		</tr>
		</c:forEach>
		<c:if test="${empty pageInfo.list}">
		<tr>
			<td colspan="10"><spring:message code="im.common.msg.nodata" /></td>
		</tr>			
		</c:if>
	</tbody>
</table>
</form>
			
<%--페이징 처리 --%>
<c:import charEncoding="utf-8"  url="/WEB-INF/jsp/inc/imPagination.jsp">
	<c:param name="pageInfo" value="pageInfo"/>
	<c:param name="jsFunction" value="REQ.page"/>
</c:import>