<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>


<script type="text/javascript" >


var REQ = {
	req : {
		list : null,
		search : null,
		modify : null,
		regist : null,
		del : null,
	},
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetail";
		this.req.list.cfg.url    = "<c:url value="/mng/wtst/selectList.do"/>";
		
		this.req.search = imRequest();
		this.req.search.cfg.formId = "FormSearch";
		this.req.search.cfg.url    = "<c:url value="/mng/wtst/selectList.do"/>";
		

		this.req.regist = imRequest();
		this.req.regist.cfg.formId = "FormPageDetail";
		this.req.regist.cfg.url    = "<c:url value="/mng/wtst/regist.do"/>";
		
		this.req.modify = imRequest();
		this.req.modify.cfg.formId = "FormPageDetail";
		this.req.modify.cfg.url    = "<c:url value="/mng/wtst/modify.do"/>";
		
		
		this.req.del = imRequest("ajax",{formId: "FormList"});
		this.req.del.cfg.type   = "json";
		this.req.del.cfg.url    =  "<c:url value="/mng/wtst/deletelist.do"/>";
		this.req.del.cfg.message.confirm="<spring:message code="common.delete.msg" />";
		this.req.del.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) { 
	        	IMGLBObject.request.page(1);
	        } else {
	        	COMMT.errorMessage();
	        }
	    };	
	    this.req.del.validator.set({
	    	message : "<spring:message code="im.common.msg.delete.choice" />",
            name : "checkIndexs",
            data : ["!null"]
        });
	
	},
	page :  function(pageNo){
		var form =  document.getElementById(this.req.list.cfg.formId);
		form.elements["currentPageNo"].value= pageNo;
		this.req.list.go();
	},
	regist :  function(){
		this.req.regist.go();
	},
	modify :  function(mapDataPks){
		COMMT.copyMapDataToFormReset(mapDataPks, this.req.modify.cfg.formId);
		this.req.modify.cfg.url    = "<c:url value="/mng/wtst/modify.do"/>";
		if(mapDataPks.tabCode=='02'){
			this.req.modify.cfg.url    = "<c:url value="/mng/wtst/selectListPlace.do"/>";	
		}
		this.req.modify.go();
	},
	search : function(){
		this.req.search.go();
	},
	del : function(){
		IMGLBObject.request=this;
		this.req.del.go();
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
<%@ include file="incWtst.jsp" %>



<im:form  condition="${condition}" formName="FormSearch"  type="search"> <%--검색인 경우 타입 --%>
<%--기본 검색 키 값 정의 키=값,키=값 으로 정의함 --%>
<c:set var="scKey">wtstId=<spring:message code="iMWtst.wtstId" /></c:set>
	<div class="sch_box">	
			<div class="group">
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
			<select name="scCrsGrdCdv" onchange="REQ.search()" >
				<option value=""><spring:message code="iMWtst.crsGrd"/></option>
				<im:cd type="option" codeId="IM0001"  selectedCode="${condition.scCrsGrdCdv}"/>
			</select>
			
			<select name="scSttsCdv"  onchange="REQ.search()">
				<option value=""><spring:message code="iMWtst.stts"/></option>
				<im:cd type="option" codeId="IM0003" selectedCode="${condition.scSttsCdv}"/>
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
	<c:set var="scOrderInfo">2=<spring:message code="iMWtst.eduYear" />,3=<spring:message code="iMWtst.crsGrd" />,1=<spring:message code="im.common.word.regDt" /></c:set><%-- todo  정렬 조건 추가  --%>
	<im:cd type="set" codeId="${scOrderInfo}" var="orderInfo"/>
	<%@ include file="/WEB-INF/jsp/inc/imPagListOrder.jspf" %>
	<im:pageBtn type="I" reqName="REQ"></im:pageBtn>
</div>
<%--목록 내용  --%>
<form name="FormList" id="FormList" method="post" onsubmit="return false;">
<table class="tbl_col">
	<colgroup>
		<col style="width:50px;">
		<col style="width:180px;">
		<col style="width:60px;">
		<col style="width:60px;">
		<col style="width:60px;">
		<col style="width:100px;">
	<col/>
	<col/>
	<col/>
	<col/>
		<col style="width:80px;">
		<col style="width:60px;">
		<col style="width:100px;">
	</colgroup>
	<thead>
		<tr>
			<th scope="col"><input type="checkbox" title="전체선택" onclick="COMMT.formCheckboxAllProc(this.form,'checkIndexs',this.checked)"></th>
			<th scope="col"><spring:message code="iMWtst.wtstId" /></th>
			<th scope="col"><spring:message code="iMWtst.crsGrd"/></th>
			<th scope="col"><spring:message code="iMWtst.eduYear"/></th>
			<th scope="col"><spring:message code="iMWtst.eduRnd"/></th>
			<th scope="col"><spring:message code="iMWtst.addtnYn"/></th>
			<th scope="col"><spring:message code="iMWtst.pbancTerm"/></th>
			<th scope="col"><spring:message code="iMWtst.rcptTerm"/></th>
			<th scope="col"><spring:message code="iMWtst.tstYmd"/></th>
			<th scope="col"><spring:message code="iMWtst.passTermDt"/></th>
			<th scope="col"><spring:message code="iMWtst.lecturCnt"/></th>
			<th scope="col"><spring:message code="iMWtst.stts"/></th>
			<th scope="col"><spring:message code="im.common.word.regDt" /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageInfo.list}" var="row" varStatus="i">
		<tr>
			<td><input type="checkbox" name="checkIndexs" title="선택" value="${i.index}">
			<%--삭제시 활용할 키 세팅  --%>
			<input type="hidden" name="wtstIds"  value="${row.wtst.wtstId}"/>
			</td>
			<td>
			<a href="#" onclick="REQ.modify({'wtstId' : '${row.wtst.wtstId}','tabCode':'01','_pageName':'기본 정보'});return false;">
			${row.wtst.wtstId}
			</a>
			</td>
			<td><im:cd type="print" codeId="IM0001" selectedCode="${row.wtst.crsGrdCdv}"/></td>
			<td><c:out value="${row.wtst.eduYear}"/></td>
			<td><c:out value="${row.wtst.eduRnd}"/></td>
			<td>
			<im:cd type="print" codeId="${addtnYnArr}" selectedCode="${row.wtst.addtnYn}"/>
			</td>
			<td><im:dt yyyyMMddHHmmss="${row.wtst.pbancTerm_bgnDt}"/>~<br><im:dt yyyyMMddHHmmss="${row.wtst.pbancTerm_endDt}"/></td>	
			<td><im:dt yyyyMMddHHmmss="${row.wtst.rcptTerm_bgnDt}"/>~<br><im:dt yyyyMMddHHmmss="${row.wtst.rcptTerm_endDt}"/></td>	
			<td><im:dt yyyyMMddHHmmss="${row.wtst.tstYmd}"/></td>	
			<td><im:dt yyyyMMddHHmmss="${row.wtst.passTerm_bgnDt}"/></td>		
			<td>
			<a href="#" onclick="REQ.modify({'wtstId' : '${row.wtst.wtstId}','tabCode':'02','_pageName':'시험장'});return false;">
			<c:out value="${row.placeCnt}"/>
			</a>
			</td>
			<td><im:cd type="print" codeId="IM0003" selectedCode="${row.wtst.sttsCdv}"/></td>
			<td><im:dt yyyyMMddHHmmss="${row.wtst.frstRegDt}"/></td>
		</tr>
		</c:forEach>
		<c:if test="${empty pageInfo.list}">
		<tr>
			<td colspan="13"><spring:message code="im.common.msg.nodata" /></td>
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