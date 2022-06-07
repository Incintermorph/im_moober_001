<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>


<script type="text/javascript" >


var REQ = {
	req : {
		list : null,
		search : null	
	},
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetail";
		this.req.list.cfg.url    = "<c:url value="/mng/mmbrHstry/selectListEduHis.do"/>";
		
		this.req.search = imRequest();
		this.req.search.cfg.formId = "FormSearch";
		this.req.search.cfg.url    = "<c:url value="/mng/mmbrHstry/selectListEduHis.do"/>";
		
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

	pageload :  function(){
		var form =  document.getElementById(REQ.req.list.cfg.formId);
		form.elements["currentPageNo"].value= 1;
		REQ.req.list.go();
	},
	migData:function(){
		var req = imRequest("ajax",{formId: "FormPageDetail"});
		req.cfg.type   = "json";
		req.cfg.message.confirm="이관은 2분이상 필요합니다. 작업중 해당 창을 닫지 말고 기다려 주세요.  기존 데이터는 업데이트 됩니다. 자료를 이관 하시겠습니까?";
		req.cfg.url    =  "<c:url value="/mng/mmbrHstry/updatelistEduHis.do"/>";
		req.cfg.asynchronous = true; // 인 경우 asynchronous
		req.cfg.message.waiting = "처리중입니다.";
		req.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) { 
	        	REQ.pageload();
	        } else {
	        	COMMT.errorMessage();
	        }
	    };	
	    req.go();
	},
	
}


$(document).ready(function(){
	REQ.init();
});


</script>


<%@ include file="incMmbrHstry.jsp" %>
<c:set var="scSttsArr">1001=신청완료,2001=신청취소,3001=선정,3002=예비선정,3003=미선정,4001=미수료,4002=수료</c:set>
<c:set var="scAcqsgrdcode">2001=2급,3001=3급</c:set>
<im:form  condition="${condition}" formName="FormSearch"  type="search"> <%--검색인 경우 타입 --%>
<%--기본 검색 키 값 정의 키=값,키=값 으로 정의함 --%>
<c:set var="scKey">mberId=회원아이디,mmbrNm=이름,eml=이메일,mblTelno=휴대전화번호</c:set>
	<div class="sch_box">
		<div class="group">
		<select name="scSttsCode" onchange="REQ.search()" >
				<option value="">상태</option>
				<im:cd type="option" codeId="${scSttsArr}"  selectedCode="${condition.scSttsCode}"/>
			</select>		
		<select name="scAcqsgrdcode" onchange="REQ.search()" >
				<option value="">취득등급</option>
				<im:cd type="option" codeId="${scAcqsgrdcode}"  selectedCode="${condition.scAcqsgrdcode}"/>
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
	<c:set var="scOrderInfo">2=회원 아이디,3=이름,5=접수번호</c:set><%-- todo  정렬 조건 추가  --%>
	<im:cd type="set" codeId="${scOrderInfo}" var="orderInfo"/>
	<%@ include file="/WEB-INF/jsp/inc/imPagListOrder.jspf" %>
	<div> 
	<a href="#" onclick="REQ.migData();return false;" class="bt_new bk">자료이관</a> 
	</div>
</div>
<%--목록 내용  --%>
<form name="FormList" id="FormList" method="post" onsubmit="return false;">
<table class="tbl_col">
	<colgroup>
		<col/>
	</colgroup>
	<thead>
		<tr>
			<th scope="col"><spring:message code="im.common.word.No"/></th>
			<th scope="col">회원 번호</th>
			<th scope="col">회원 아이디</th>
			<th scope="col">이름</th>
			<th scope="col">이메일</th>
			<th scope="col">휴대전화번호</th>
			<th scope="col">접수번호</th>
			<th scope="col">양성기관</th>
			<th scope="col">과정명</th>
			<th scope="col">취득등급</th>
			<th scope="col">접수일자</th>
			<th scope="col">교육기간</th>
			<th scope="col">상태</th>
			<th scope="col">수료일자</th>
			<th scope="col">수료번호</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageInfo.list}" var="row" varStatus="i">
		<tr>
			<td class="ar">${pageInfo.descIndex - i.index}</td> 
			<td><c:out value="${row.eduAplyHstry.memberSrl}"/></td>
			<td  class="al"><c:out value="${row.eduAplyHstry.mmbrIdMask}"/></td>
			<td><c:out value="${row.eduAplyHstry.mmbrNmMask}"/></td>
			<td class="al"><c:out value="${row.eduAplyHstry.emlMask}"/></td>
			<td><c:out value="${row.eduAplyHstry.mblTelnoMasking}"/></td>
			<td><c:out value="${row.eduAplyHstry.rcptCode}"/></td>
			<td><c:out value="${row.eduAplyHstry.agncyNm}"/>(${row.eduAplyHstry.agncyCode})</td>
			<td><c:out value="${row.eduAplyHstry.crsNm}"/></td>
			<td><c:out value="${row.eduAplyHstry.acqsGrd}"/>(${row.eduAplyHstry.acqsgrdcode})</td>
			<td><c:out value="${row.eduAplyHstry.rcptYmd}"/></td>
			<td><c:out value="${row.eduAplyHstry.eduYmdInfo}"/></td>
			<td><c:out value="${row.eduAplyHstry.stts}"/>(${row.eduAplyHstry.sttsCode})</td>
			<td><c:out value="${row.eduAplyHstry.qlfcAcqsYmd}"/></td>
			<td><c:out value="${row.eduAplyHstry.lcncIssuCode}"/></td>
			
		</tr>
		</c:forEach>
		<c:if test="${empty pageInfo.list}">
		<tr>
			<td colspan="15"><spring:message code="im.common.msg.nodata" /></td>
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