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
		this.req.list.cfg.url    = "<c:url value="/mng/crsAplcnt/cmmnStts/"/>${sttsCdv}/selectListLayer.do";
		
		this.req.search = imRequest();
		this.req.search.cfg.formId = "FormSearch";
		this.req.search.cfg.url   = "<c:url value="/mng/wtstAplcnt/cmmnStts/"/>${sttsCdv}/selectListLayer.do";
		
	
	},
	page :  function(pageNo){
		var form =  document.getElementById(this.req.list.cfg.formId);
		form.elements["currentPageNo"].value= pageNo;
		this.req.list.go();
	},
	search :  function(){
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
	}
}


$(document).ready(function(){
	REQ.init();
});

</script>

<c:set  var="codeId" value=""/>
<c:if test="${sttsCdv eq 'APLY' }">
<c:set  var="codeId" value="IM0021"/>
</c:if>

<im:form  condition="${condition}" formName="FormPageDetail"  type="list">
<input type="hidden" name="scTblRefId"   value="<c:out value="${condition.scTblRefId}"/>" />
<input type="hidden" name="scSttsCdv"   value="<c:out value="${condition.scSttsCdv}"/>" />
	<%--추가 검색 조건  todo Condition sc+컬럼+카멜--%>	
	<%--상세 조회 필요한 키값 --%>
</im:form>
<im:form  condition="${condition}" formName="FormSearch"  type="search"> <%--검색인 경우 타입 --%>
<input type="hidden" name="scTblRefId"  value="<c:out value="${condition.scTblRefId}"/>" />
<c:set var="scKey">mberId=수정자아이디,mmbrNm=수정자명</c:set>
<div class="sch_box">	
		
		<c:if test="${!empty codeId}">
		<div class="group">
		<select name="scSttsCdv"  onchange="REQ.search()">
				<option value="">상태</option>
				<im:cd type="option" codeId="${codeId}" selectedCode="${condition.scSttsCdv}"/>
			</select>	
		</div>
		</c:if>	
		<div class="group">
			<select name="scKey" >
				<im:cd type="option" codeId="${scKey}" selectedCode="${condition.scKey}"/>
			</select>
			<input type="text" name="scWord" value="<c:out value="${condition.scWord}"/>"  onKeyup="COMMT.enterCallFunc(event, REQ.search);" style="width:300px;">
			<button type="button" onclick="REQ.search()" class="ad_btn gray"><spring:message code="im.common.word.select" /></button>
		</div>
	</div>
</im:form>
<form name="FormList" id="FormList" method="post" onsubmit="return false;">
<table class="tbl_col">
	<colgroup>
		<col style="width:50px;">
		<col style="width:90px;"/>
		<col style="width:110px;"/>
		<col style="width:90px;"/>
			<col/>

		<col style="width:150px;">
	</colgroup>
	<thead>
		<tr>
			<th scope="col"><spring:message code="im.common.word.No"/></th>
			<th scope="col">상태</th>
			<th scope="col">수정자아이디</th>
			<th scope="col">수정자명</th>
			<th scope="col">비고</th>
			<th scope="col">수정일시</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageInfo.list}" var="row" varStatus="i">
		<tr>
			<td class="ar">${pageInfo.descIndex - i.index}</td> <%--페이징 역순  --%> <%--순번 : ${pageInfo.ascIndex + i.index} --%>
			<td>
					<c:if test="${!empty codeId}">
					<im:cd type="print" codeId="${codeId}" selectedCode="${row.cmmnStts.sttsCdv}"/>
					</c:if>
					<c:if test="${empty codeId}">
						${row.cmmnStts.sttsCdv}
					</c:if>
			</td>
			<td><c:out value="${row.mberManage.mberIdMask}"/></td>
			<td><c:out value="${row.mberManage.mberNmMask}"/></td>
			<td class="al"><c:out value="${imfunc:textToBr(row.cmmnStts.sttsRmks)}" escapeXml="false"/></td>
			<td><im:dt yyyyMMddHHmmss="${row.cmmnStts.frstRegDt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		</tr>
		</c:forEach>
		<c:if test="${empty pageInfo.list}">
		<tr>
			<td colspan="6"><spring:message code="im.common.msg.nodata" /></td>
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
