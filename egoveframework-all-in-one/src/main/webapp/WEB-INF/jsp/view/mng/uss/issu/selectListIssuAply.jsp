<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>


<script type="text/javascript" >


var REQ = {
	req : {
		list : null,
		search : null,
		modify : null,
		updateAply : null,
	},
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetail";
		this.req.list.cfg.url    = "<c:url value="/mng/issuAply/${stts}/selectList.do"/>";
		
		this.req.search = imRequest();
		this.req.search.cfg.formId = "FormSearch";
		this.req.search.cfg.url    = "<c:url value="/mng/issuAply/${stts}/selectList.do"/>";
		

		this.req.modify = imRequest();
		this.req.modify.cfg.formId = "FormPageDetail";
		this.req.modify.cfg.url    = "<c:url value="/mng/issuAply/${stts}/modify.do"/>";
		
		this.req.updateAply = imRequest("ajax",{formId: "FormList"});
		this.req.updateAply.cfg.type   = "json";
		this.req.updateAply.cfg.url    =  "<c:url value="/mng/issuAply/updateAplylist.do"/>";
		this.req.updateAply.cfg.message.confirm="변경하시겠습니까?";
		this.req.updateAply.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) { 
	        	REQ.page(1);
	        } else {
	        	COMMT.errorMessage();
	        }
	    };	
	    this.req.updateAply.validator.set({
	    	message : "변경할 정보를 선택하세요",
            name : "checkIndexs",
            data : ["!null"]
        });
	
	},
	page :  function(pageNo){
		var form =  document.getElementById(this.req.list.cfg.formId);
		form.elements["currentPageNo"].value= pageNo;
		this.req.list.go();
	},
	modify :  function(mapDataPks){
		COMMT.copyMapDataToFormReset(mapDataPks, this.req.modify.cfg.formId);
		REQ.req.modify.go();
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
	excel : function(){
		COMMT.ready();
	},
	updateAply : function(sttsCdv){
		var form =  document.getElementById(REQ.req.updateAply.cfg.formId);
		form.elements["sttsCdv"].value= sttsCdv;
		REQ.req.updateAply.go();
	},
   	tab :  function(stts){
   		var form =  document.getElementById(this.req.list.cfg.formId);
   		form.elements["currentPageNo"].value= 1;
   		this.req.list.cfg.url    = "<c:url value="/mng/issuAply/"/>"+stts+"/selectList.do";
   		this.req.list.go();
   	},
}


$(document).ready(function(){
	REQ.init();
});


</script>
<%@ include file="incIssuAply.jsp" %>

<c:set var="arrTabs">A=신청,03=신청취소</c:set>
<im:cd type="set" codeId="${arrTabs}" var="arrTagCode"/>


<ul class="nav_tabs">
	<c:forEach items="${arrTagCode}" var="trow">
	<li <c:if test="${stts eq trow.code }"> class="on"</c:if>><a href="#" onclick="REQ.tab('${trow.code}')">${trow.codeNm}</a></li>
	</c:forEach>
</ul>


<im:form  condition="${condition}" formName="FormSearch"  type="search"> <%--검색인 경우 타입 --%>
<%--기본 검색 키 값 정의 키=값,키=값 으로 정의함 --%>
<c:set var="scKey">mmbrId=<spring:message code="iMIssuAply.mmbrId"/>,mberNm=<spring:message code="iMIssuAply.mberNm"/>,mmbrTelno=<spring:message code="iMIssuAply.mmbrTelno"/></c:set>
	<div class="sch_box">
		<div class="group">
			
			<select name="scIssuDvsnCdv" onchange="REQ.search()" >
				<option value=""><spring:message code="iMIssuAply.issuDvsnCdv"/></option>
				<im:cd type="option" codeId="IM0022"  selectedCode="${condition.scIssuDvsnCdv}"/>
			</select>
			
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
			 <select name="scEduYear" onchange="REQ.search()" >
				<option value=""><spring:message code="iMCrs.eduYear"/></option>
				<c:forEach begin="2015" var="num" end="${imEduYearEnd}">
				<option value="${num}"
					<c:if test="${num eq  condition.scEduYear}">
					selected="selected"
					</c:if> 
					>${num}</option>
				</c:forEach>
			</select>
			
			<select name="scCrsGrdCdv" onchange="REQ.search()" >
				<option value=""><spring:message code="iMIssuAply.crsGrdCdv"/></option>
				<im:cd type="option" codeId="IM0001"  selectedCode="${condition.scCrsGrdCdv}"/>
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
	<c:set var="scOrderInfo">2=<spring:message code="iMIssuAply.mmbrId"/>,3=<spring:message code="iMIssuAply.mberNm"/>,4=<spring:message code="iMIssuAply.aplyYmd"/></c:set><%-- todo  정렬 조건 추가  --%>
	<im:cd type="set" codeId="${scOrderInfo}" var="orderInfo"/>
	<%@ include file="/WEB-INF/jsp/inc/imPagListOrder.jspf" %>
	<c:if test="${stts eq 'A'}">
	 <div>
	 <%--
	 <a href="#" onclick="REQ.excel();return false;" class="bt_new bk">엑셀</a>
	  --%>
	 <a href="#"  onclick="REQ.updateAply('03')"   class="bt_new bk">발급신청취소</a>
	 <a href="#"  onclick="REQ.updateAply('02')"  class="bt_new bk">발급 승인</a>
	 <a href="#"  onclick="REQ.updateAply('04')"  class="bt_new bk">발급 미승인</a>
	</div>
	</c:if>
</div>
<%--목록 내용  --%>
<form name="FormList" id="FormList" method="post" onsubmit="return false;">
<input type="hidden" name="sttsCdv">
<table class="tbl_col">
	<colgroup>
		<col style="width:50px;">
		<col style="width:50px;">
		<col style="width:100px;">
		<col style="width:120px;">
		<col style="width:100px;">
		<col style="width:110px;">
		<col/>
		<col style="width:80px;">
		<col style="width:110px;">
		<col style="width:90px;">
		<col style="width:90px;">
		<col style="width:90px;">
	</colgroup>
	<thead>
		<tr>
			<th scope="col"><input type="checkbox" title="전체선택" onclick="COMMT.formCheckboxAllProc(this.form,'checkIndexs',this.checked)"></th>
			<th scope="col"><spring:message code="im.common.word.No"/></th>
			<th scope="col"><spring:message code="iMIssuAply.issuDvsnCdv"/></th>
			<th scope="col"><spring:message code="iMIssuAply.mmbrId"/></th>
			<th scope="col"><spring:message code="iMIssuAply.mberNm"/></th>
			<th scope="col"><spring:message code="iMIssuAply.mmbrTelno"/></th>
			<th scope="col"><spring:message code="iMIssuAply.agncyNm"/></th>
			<th scope="col"><spring:message code="iMIssuAply.crsGrdCdv"/></th>
			<th scope="col"><spring:message code="iMIssuAply.lcncAcqsYmd"/></th>
			<th scope="col"><spring:message code="iMIssuAply.sttsCdv"/></th>
			<th scope="col"><spring:message code="iMIssuAply.aplyYmd"/></th>
			<th scope="col"><spring:message code="iMIssuAply.idntyYmd"/></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageInfo.list}" var="row" varStatus="i">
		<tr>
			<td><input type="checkbox" name="checkIndexs" title="선택" value="${i.index}">
			<%--삭제시 활용할 키 세팅  --%>
			<input type="hidden" name="issuAplyIds"  value="${row.issuAply.issuAplyId}"/>
			</td>
			<td class="ar">${pageInfo.descIndex - i.index}</td> <%--페이징 역순  --%> <%--순번 : ${pageInfo.ascIndex + i.index} --%>
				<td>
				<a href="#" onclick="REQ.modify({'issuAplyId' : '${row.issuAply.issuAplyId}'});return false;">
				<im:cd type="print" codeId="IM0022" name="issuDvsnCdv"  selectedCode="${row.issuAply.issuDvsnCdv}"/>
				</a>
				</td>
				<td class="al"><c:out value="${row.issuAply.mmbrIdMask}"/></td>
				<td><c:out value="${row.issuAply.mberNmMask}"/></td>
				<td><c:out value="${row.issuAply.mmbrTelnoMask}"/></td>
				<td class="al"><c:out value="${row.agncy.agncyNm}"/></td>
				<td><im:cd type="print" name="crsGrdCdv" selectedCode="${row.issuAply.crsGrdCdv}" codeId="IM0001"/></td>
				<td><im:dt yyyyMMddHHmmss="${row.issuAply.lcncAcqsYmd}"/></td>
				<td><im:cd type="print" codeId="IM0023" selectedCode="${row.issuAply.sttsCdv}" /></td>
				<td><im:dt yyyyMMddHHmmss="${row.issuAply.aplyYmd}"/></td>
				<td><im:dt yyyyMMddHHmmss="${row.issuAply.idntyYmd}"/></td>
		</tr>
		</c:forEach>
		<c:if test="${empty pageInfo.list}">
		<tr>
			<td colspan="12"><spring:message code="im.common.msg.nodata" /></td>
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