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
		this.req.list.cfg.url    = "<c:url value="/mng/ussMngr/"/>${condition.scAuthorCode}/selectList.do";
		
		this.req.search = imRequest();
		this.req.search.cfg.formId = "FormSearch";
		this.req.search.cfg.url    = "<c:url value="/mng/ussMngr/"/>${condition.scAuthorCode}/selectList.do";
		

		this.req.regist = imRequest();
		this.req.regist.cfg.formId = "FormPageDetail";
		this.req.regist.cfg.url    = "<c:url value="/mng/ussMngr/regist.do"/>";
		
		this.req.modify = imRequest();
		this.req.modify.cfg.formId = "FormPageDetail";
		this.req.modify.cfg.url    = "<c:url value="/mng/ussMngr/modify.do"/>";
		
		
		this.req.del = imRequest("ajax",{formId: "FormList"});
		this.req.del.cfg.type   = "json";
		this.req.del.cfg.url    =  "<c:url value="/mng/ussMngr/deletelist.do"/>";
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
	},
	selectMemo:function(){
		var req = imRequest("layer");
		req.cfg.url    = "<c:url value="/mng/ussMngr/select/memoLayer.do"/>";
		req.cfg.options.title="개인조회 사유 등록";
		req.cfg.formId = "FormPageDetail";
		req.cfg.options.scroll="hidden";
		req.cfg.options.width=600;
		req.cfg.options.height=300;
		req.go();
			
	},
	modify :  function(mapDataPks){
		COMMT.copyMapDataToFormReset(mapDataPks, this.req.modify.cfg.formId);
		//this.req.modify.go();
		REQ.selectMemo();
	},
	modifyPage :  function(memo){
		var form =  document.getElementById(REQ.req.modify.cfg.formId);
		form.elements["inqRsn"].value= memo;
		REQ.req.modify.go();
	}
}


$(document).ready(function(){
	REQ.init();
});


</script>
<%@ include file="incUssMngr.jsp" %>



<im:form  condition="${condition}" formName="FormSearch"  type="search"> <%--검색인 경우 타입 --%>
<input type="hidden" name="scAuthorCode"   value="${condition.scAuthorCode}"  />
<%--기본 검색 키 값 정의 키=값,키=값 으로 정의함 --%>
<c:set var="scKey">emplyrId=<spring:message code="iMUssMngr.emplyrId" />,userNm=<spring:message code="iMUssMngr.userNm"/>,telno=<spring:message code="iMUssMngr.telno"/>,emailAdres=<spring:message code="iMUssMngr.emailAdres"/></c:set>
	<div class="sch_box">
		<div class="group">
			<c:if test="${condition.scAuthorCode eq 'ROLE_AGNT_ADMIN'}">
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
			<select name="scEmplyrSttusCode" onchange="REQ.search()" >
				<option value=""><spring:message code="iMUssMngr.emplyrSttus"/></option>
				<im:cd type="option" codeId="COM013"  except="D"  selectedCode="${condition.scEmplyrSttusCode}"/>
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
	<c:set var="scOrderInfo">1=<spring:message code="im.common.word.regDt" /></c:set><%-- todo  정렬 조건 추가  --%>
	<im:cd type="set" codeId="${scOrderInfo}" var="orderInfo"/>
	<%@ include file="/WEB-INF/jsp/inc/imPagListOrder.jspf" %>
	<im:pageBtn type="I" reqName="REQ"></im:pageBtn>
</div>
<%--목록 내용  --%>
<form name="FormList" id="FormList" method="post" onsubmit="return false;">
<table class="tbl_col">
	<colgroup>
		<col style="width:50px;">
		<col style="width:50px;"/>
		<c:if test="${condition.scAuthorCode eq 'ROLE_AGNT_ADMIN'}">
		<col style="width:150px;"/>
		</c:if>
		<col style="width:200px;">
		<col style="width:100px;">
		<col style="width:100px;">
		<col style="width:115px;"/>
			<col/>
		<col style="width:80px;">
		<col style="width:100px;">
	</colgroup>
	<thead>
		<tr>
			<th scope="col"><input type="checkbox" title="전체선택" onclick="COMMT.formCheckboxAllProc(this.form,'checkIndexs',this.checked)"></th>
			<th scope="col"><spring:message code="im.common.word.No"/></th>
			<c:if test="${condition.scAuthorCode eq 'ROLE_AGNT_ADMIN'}">
			<th scope="col">기관명</th>
			</c:if>
			<th scope="col"><spring:message code="iMUssMngr.emplyrId"/></th>
			<th scope="col"><spring:message code="iMUssMngr.userNm"/></th>
			<th scope="col"><spring:message code="iMUssMngr.ofcpsNm"/></th>
			<th scope="col"><spring:message code="iMUssMngr.telno"/></th>
			<th scope="col"><spring:message code="iMUssMngr.emailAdres"/></th>
			<th scope="col"><spring:message code="iMUssMngr.emplyrSttus"/></th>
			<th scope="col"><spring:message code="im.common.word.regDt" /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageInfo.list}" var="row" varStatus="i">
		<tr>
			<td><input type="checkbox" name="checkIndexs" title="선택" value="${i.index}">
			<%--삭제시 활용할 키 세팅  --%>
			<input type="hidden" name="esntlIds"  value="${row.ussMngr.esntlId}"/>
			</td>
			<td class="ar">${pageInfo.descIndex - i.index}</td> <%--페이징 역순  --%> <%--순번 : ${pageInfo.ascIndex + i.index} --%>
			<c:if test="${condition.scAuthorCode eq 'ROLE_AGNT_ADMIN'}">
			<td class="al">${row.agncy.agncyNm}</td>
			</c:if>
			<td class="al">${row.ussMngr.emplyrIdMask}</td>
			<td>
			<a href="#" onclick="REQ.modify({'esntlId' : '${row.ussMngr.esntlId}' ,'_pageName':'상세 정보' });return false;">
			${row.ussMngr.userNmMask}
			</a>
			</td>
			<td class="al"><c:out value="${row.ussMngr.ofcpsNm}"/></td>
			<td><c:out value="${row.ussMngr.areaNo}"/>-****-<c:out value="${row.ussMngr.houseEndTelno}"/></td>
			<td class="al"><c:out value="${row.ussMngr.emailAdresMask}"/></td>
			<td><im:cd type="print" codeId="COM013" selectedCode="${row.ussMngr.emplyrSttusCode}"/></td>
			<td><im:dt yyyyMMddHHmmss="${row.ussMngr.sbscrbDe}"/></td>
		</tr>
		</c:forEach>
		<c:if test="${empty pageInfo.list}">
		<tr>
			<c:if test="${condition.scAuthorCode eq 'ROLE_AGNT_ADMIN'}">
			<td colspan="10"><spring:message code="im.common.msg.nodata" /></td>
			</c:if>
			<c:if test="${condition.scAuthorCode ne 'ROLE_AGNT_ADMIN'}">
			<td colspan="9"><spring:message code="im.common.msg.nodata" /></td>
			</c:if>
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