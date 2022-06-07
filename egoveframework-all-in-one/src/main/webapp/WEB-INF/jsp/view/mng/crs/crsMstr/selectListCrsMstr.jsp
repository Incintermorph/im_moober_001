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
		this.req.list.cfg.url    = "<c:url value="/mng/crsMstr/selectList.do"/>";
		
		this.req.search = imRequest();
		this.req.search.cfg.formId = "FormSearch";
		this.req.search.cfg.url    = "<c:url value="/mng/crsMstr/selectList.do"/>";
		

		this.req.regist = imRequest();
		this.req.regist.cfg.formId = "FormPageDetail";
		this.req.regist.cfg.url    = "<c:url value="/mng/crsMstr/regist.do"/>";
		
		this.req.modify = imRequest();
		this.req.modify.cfg.formId = "FormPageDetail";
		this.req.modify.cfg.url    = "<c:url value="/mng/crsMstr/modify.do"/>";
		
		
		this.req.del = imRequest("ajax",{formId: "FormList"});
		this.req.del.cfg.type   = "json";
		this.req.del.cfg.url    =  "<c:url value="/mng/crsMstr/deletelist.do"/>";
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

	    IMGLBObject.request=this;
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
		this.req.modify.go();
	},
	search : function(){
		IMGLBObject.request.req.search.go();
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
<%@ include file="incCrsMstr.jsp" %>

<im:form  condition="${condition}" formName="FormSearch"  type="search"> <%--검색인 경우 타입 --%>
<%--기본 검색 키 값 정의 키=값,키=값 으로 정의함 --%>
<c:set var="scKey">crsMstrId=<spring:message code="iMCrsMstr.crsMstrId" /></c:set>
	<div class="sch_box">
		<div class="group">
			<select name="scCrsGrdCdv" onchange="REQ.search()" >
				<option value=""><spring:message code="iMCrsMstr.crsGrd"/></option>
				<im:cd type="option" codeId="IM0001"  selectedCode="${condition.scCrsGrdCdv}"/>
			</select>
			<select name="scCrsDvsnCdv"  onchange="REQ.search()">
				<option value=""><spring:message code="iMCrsMstr.crsDvsn"/></option>
				<im:cd type="option" codeId="IM0002" selectedCode="${condition.scCrsDvsnCdv}"/>
			</select>
			<select name="scSttsCdv"  onchange="REQ.search()">
				<option value=""><spring:message code="iMCrsMstr.stts"/></option>
				<im:cd type="option" codeId="IM0003" selectedCode="${condition.scSttsCdv}"/>
			</select>
		</div>
		<div class="group">
			<select name="scKey" >
				<im:cd type="option" codeId="${scKey}" selectedCode="${condition.scKey}"/>
			</select>
			<input type="text" name="scWord" value="<c:out value="${condition.scWord}"/>"  onKeyup="COMMT.enterCallFunc(event, REQ.search);" style="width:300px;">
			<button type="button" onclick="REQ.search()" class="ad_btn gray">조회</button>
		</div>
	</div>
</im:form>


<%--목록 상단 --%>
<div class="cb_bar">
	<c:set var="scOrderInfo">2=등급,3=과정구분,1=등록일자</c:set>
	<im:cd type="set" codeId="${scOrderInfo}" var="orderInfo"/>
	<%@ include file="/WEB-INF/jsp/inc/imPagListOrder.jspf" %>
	<im:pageBtn type="I" reqName="REQ"></im:pageBtn>
</div>
<%--목록 내용  --%>
<form name="FormList" id="FormList" method="post" onsubmit="return false;">
<table class="tbl_col">
	<colgroup>
		<col style="width:50px;">
		<col style="width:168px;">
		<col>
		<col style="width:70px;">
		<col style="width:100px;">
	</colgroup>
	<thead>
		<tr>
			<th scope="col"><input type="checkbox" title="전체선택" onclick="COMMT.formCheckboxAllProc(this.form,'checkIndexs',this.checked)"></th>
			<th scope="col"><spring:message code="iMCrsMstr.crsMstrId" /></th>
			<th scope="col"><spring:message code="iMCrsMstr.crsMstrNmAdd" /></th>
			<th scope="col"><spring:message code="iMCrsMstr.stts" /></th>
			<th scope="col"><spring:message code="im.common.word.regDt" /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageInfo.list}" var="row" varStatus="i">
		<tr>
			<td><input type="checkbox" name="checkIndexs" title="선택" value="${i.index}">
			<%--삭제시 활용할 키 세팅  --%>
			<input type="hidden" name="crsMstrIds"  value="${row.imCrsMstr.crsMstrId}"/>
			</td>
			<td>${row.imCrsMstr.crsMstrId}</td>
			<td>
			<a href="#" onclick="REQ.modify({'crsMstrId' : '${row.imCrsMstr.crsMstrId}'});return false;">
			<im:cd type="print" codeId="IM0001" selectedCode="${row.imCrsMstr.crsGrdCdv}"/>&nbsp;<im:cd type="print" codeId="IM0002" selectedCode="${row.imCrsMstr.crsDvsnCdv}"/></td>
			</a>
			<td><im:cd type="print" codeId="IM0003" selectedCode="${row.imCrsMstr.sttsCdv}"/> </td>
			<td><im:dt yyyyMMddHHmmss="${row.imCrsMstr.frstRegDt}"/></td>
		</tr>
		</c:forEach>
		<c:if test="${empty pageInfo.list}">
		<tr>
			<td colspan="5"><spring:message code="im.common.msg.nodata" /></td>
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

