<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>


<script type="text/javascript" >


var REQ = {
	req : {
		list : null,
		search : null,
		modify : null
	},
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetail";
		this.req.list.cfg.url    = "<c:url value="/mng/wtstPlace/selectList.do"/>";
		
		this.req.search = imRequest();
		this.req.search.cfg.formId = "FormSearch";
		this.req.search.cfg.url    = "<c:url value="/mng/wtstPlace/selectList.do"/>";
		

		this.req.regist = imRequest();
		this.req.regist.cfg.formId = "FormPageDetail";
		this.req.regist.cfg.url    = "<c:url value="/mng/wtstPlace/regist.do"/>";
		
		this.req.modify = imRequest();
		this.req.modify.cfg.formId = "FormPageDetail";
		this.req.modify.cfg.url    = "<c:url value="/mng/wtstPlace/modify.do"/>";
		
		
	
	},
	page :  function(pageNo){
		var form =  document.getElementById(this.req.list.cfg.formId);
		form.elements["currentPageNo"].value= pageNo;
		this.req.list.go();
	},
	modify :  function(mapDataPks){
		COMMT.copyMapDataToFormReset(mapDataPks, this.req.modify.cfg.formId);
		if(mapDataPks.tabCode=='02'){
			this.req.modify.cfg.url    = "<c:url value="/mng/wtstPlace/selectDetailWtst.do"/>";	
		}
		this.req.modify.go();
	},
	search : function(){
		this.req.search.go();
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
	}
}


$(document).ready(function(){
	REQ.init();
});


</script>
<%@ include file="incWtstPlace.jsp" %>



<im:form  condition="${condition}" formName="FormSearch"  type="search"> <%--검색인 경우 타입 --%>
<%--기본 검색 키 값 정의 키=값,키=값 으로 정의함 --%>
<c:set var="scKey">wtstId=<spring:message code="iMWtst.wtstId" /></c:set>
	<div class="sch_box">
			<div class="group">
			 <c:if test="${empty  loginUserAgncyId}">
			<select name="scAgncyId" onchange="REQ.search()">
					<option value=""><spring:message code="iMCrs.agncyNm"/></option>
					<c:forEach items="${agncyList}" var="row">
					<option value="${row.agncy.agncyId}"
					<c:if test="${row.agncy.agncyId eq  condition.scAgncyId}">
					selected="selected"
					</c:if> 
					>${row.agncy.agncyNm}</option>
					</c:forEach>
			</select>
		     </c:if>
		     
		     <select name="scEduYear" onchange="REQ.search()" >
				<option value=""><spring:message code="iMWtst.eduYear"/></option>
				<c:forEach begin="${imStartYear}" var="num" end="${imEduYearEnd}">
				<option value="${num}"
					<c:if test="${num eq  condition.scEduYear}">
					selected="selected"
					</c:if> 
					>${num}</option>
				</c:forEach>
			</select>
			<select name="scEduRnd" onchange="REQ.search()" >
				<option value=""><spring:message code="iMWtst.eduRnd"/></option>
				<c:forEach begin="${imEduRndStart}" var="num" end="${imEduRndEnd}">
				<option value="${num}"
					<c:if test="${num eq  condition.scEduRnd}">
					selected="selected"
					</c:if> 
					>${num}</option>
				</c:forEach>
			</select>
			<select name="scAddtnYn" onchange="REQ.search()" >
				<option value=""><spring:message code="iMWtst.addtnYn"/></option>
				<im:cd type="option" codeId="IMYENO"  selectedCode="${condition.scAddtnYn}"/>
			</select>	
			<select name="scPrgrsStts" onchange="REQ.search()" >
				<option value=""><spring:message code="iMWtstPlace.prgrsStts"/></option>
				<im:cd type="option" codeId="IM0020"  selectedCode="${condition.scPrgrsStts}"/>
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
	<c:set var="scOrderInfo">2=<spring:message code="iMWtst.eduYear" />,4=<spring:message code="iMWtst.crsGrd" />,1=<spring:message code="im.common.word.regDt" /></c:set><%-- todo  정렬 조건 추가  --%>
	<im:cd type="set" codeId="${scOrderInfo}" var="orderInfo"/>
	<%@ include file="/WEB-INF/jsp/inc/imPagListOrder.jspf" %>
</div>
<%--목록 내용  --%>
<form name="FormList" id="FormList" method="post" onsubmit="return false;">
<table class="tbl_col">
	<colgroup>
		<col style="width:180px;">
		<col style="width:60px;">
		<col style="width:60px;">
		<col style="width:60px;">
		<col style="width:100px;">
		<col style="width:165px;">
		<col style="width:100px;">
	<col/>
		<col style="width:90px;">
		<col style="width:90px;">
		<col style="width:90px;">
	</colgroup>
	<thead>
		<tr>
			<th scope="col"><spring:message code="iMWtst.wtstId" /></th>
			<th scope="col"><spring:message code="iMWtst.crsGrd"/></th>
			<th scope="col"><spring:message code="iMWtst.eduYear"/></th>
			<th scope="col"><spring:message code="iMWtst.eduRnd"/></th>
			<th scope="col"><spring:message code="iMWtst.addtnYn"/></th>
			<th scope="col"><spring:message code="iMWtst.rcptTerm"/></th>
			<th scope="col"><spring:message code="iMWtst.tstYmd"/></th>
			<th scope="col"><spring:message code="iMAgncy.agncyNm"/></th>
			<th scope="col">신청자</th>
			<th scope="col"><spring:message code="iMWtstPlace.prsnl"/></th>
			<th scope="col"><spring:message code="iMWtstPlace.prgrsStts"/></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageInfo.list}" var="row" varStatus="i">
		<tr>
			<td>
			<a href="#" onclick="REQ.modify({'wtstPlaceId' : '${row.wtstPlace.wtstPlaceId}','tabCode':'02','_pageName':'일정상세정보'});return false;">
				${row.wtstPlace.wtstId}
			</a>
			</td>
			<td><im:cd type="print" codeId="IM0001" selectedCode="${row.wtst.crsGrdCdv}"/></td>
			<td><c:out value="${row.wtst.eduYear}"/></td>
			<td><c:out value="${row.wtst.eduRnd}"/></td>
			<td><im:cd type="print" codeId="IMYENO" selectedCode="${row.wtst.addtnYn}"/>
			</td>	
			<td><im:dt yyyyMMddHHmmss="${row.wtst.rcptTerm_bgnDt}"/>~<im:dt yyyyMMddHHmmss="${row.wtst.rcptTerm_endDt}"/></td>	
			<td><im:dt yyyyMMddHHmmss="${row.wtst.tstYmd}"/></td>
			<td class="al">
			<a href="#" onclick="REQ.modify({'wtstPlaceId' : '${row.wtstPlace.wtstPlaceId}','tabCode':'01','_pageName':'기본정보'});return false;">
				<c:out value="${row.agncy.agncyNm}"/>
			</a>
			</td>
			<td class="ar"><c:out value="${row.applyCnt}"/></td>
			<td class="ar"><c:out value="${row.wtstPlace.prsnl}"/></td>
			<td>
				<im:cd type="print" codeId="IM0020" selectedCode="${row.wtstPlace.prgrsSttsCdv}"/>
			</td>
		</tr>
		</c:forEach>
		<c:if test="${empty pageInfo.list}">
		<tr>
			<td colspan="11"><spring:message code="im.common.msg.nodata" /></td>
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