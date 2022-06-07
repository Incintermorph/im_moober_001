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
		this.req.list.cfg.url    = "<c:url value="/mng/crsPlan/selectList.do"/>";
		
		this.req.search = imRequest();
		this.req.search.cfg.formId = "FormSearch";
		this.req.search.cfg.url    = "<c:url value="/mng/crsPlan/selectList.do"/>";
		

		this.req.regist = imRequest();
		this.req.regist.cfg.formId = "FormPageDetail";
		this.req.regist.cfg.url    = "<c:url value="/mng/crsPlan/regist.do"/>";
		
		this.req.modify = imRequest();
		this.req.modify.cfg.formId = "FormPageDetail";
		this.req.modify.cfg.url    = "<c:url value="/mng/crsPlan/modify.do"/>";
		
		
		this.req.del = imRequest("ajax",{formId: "FormList"});
		this.req.del.cfg.type   = "json";
		this.req.del.cfg.url    =  "<c:url value="/mng/crsPlan/deletelist.do"/>";
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
<%@ include file="incCrsPlan.jsp" %>



<im:form  condition="${condition}" formName="FormSearch"  type="search"> <%--검색인 경우 타입 --%>
<%--기본 검색 키 값 정의 키=값,키=값 으로 정의함 --%>
<c:set var="scKey">crsPlanId=<spring:message code="iMCrsPlan.crsPlanId" /></c:set>
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
				<option value=""><spring:message code="iMCrs.eduYear"/></option>
				<c:forEach begin="${imEduYearStart}" var="num" end="${imEduYearEnd}">
				<option value="${num}"
					<c:if test="${num eq  condition.scEduYear}">
					selected="selected"
					</c:if> 
					>${num}</option>
				</c:forEach>
			</select>
			<select name="scCrsGrdCdv" onchange="REQ.search()" >
				<option value=""><spring:message code="iMCrsMstr.crsGrd"/></option>
				<im:cd type="option" codeId="IM0001"  selectedCode="${condition.scCrsGrdCdv}"/>
			</select>
			<select name="scCrsDvsnCdv"  onchange="REQ.search()">
				<option value=""><spring:message code="iMCrsMstr.crsDvsn"/></option>
				<im:cd type="option" codeId="IM0011" selectedCode="${condition.scCrsDvsnCdv}"/>
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
	<c:set var="scOrderInfo">2=<spring:message code="iMCrs.eduAplTerm"/>,3=<spring:message code="iMCrs.eduTerm"/>,1=<spring:message code="im.common.word.regDt" /></c:set><%-- todo  정렬 조건 추가  --%>
	<im:cd type="set" codeId="${scOrderInfo}" var="orderInfo"/>
	<%@ include file="/WEB-INF/jsp/inc/imPagListOrder.jspf" %>
	<im:pageBtn type="I" reqName="REQ"></im:pageBtn>
</div>
<%--목록 내용  --%>
<form name="FormList" id="FormList" method="post" onsubmit="return false;">
<table class="tbl_col">
	<colgroup>
		<col style="width:50px;">
		<col style="width:168px;"/>
		<col style="width:190px;">
		<col style="width:50px;">
		<col style="width:180px;">
	
	<col/>
	<col/>
		<col style="width:100px;">
	</colgroup>
	<thead>
		<tr>
			<th scope="col"><input type="checkbox" title="전체선택" onclick="COMMT.formCheckboxAllProc(this.form,'checkIndexs',this.checked)"></th>
			<th scope="col"><spring:message code="iMCrsPlan.crsPlanId"/></th>
			<th scope="col"><spring:message code="iMCrs.agncyNm" /></th>
			<th scope="col"><spring:message code="iMCrsPlan.eduYear"/></th>
			<th scope="col"><spring:message code="iMCrs.crsNm"/></th>
			<th scope="col"><spring:message code="iMCrs.eduAplTerm"/></th>
			<th scope="col"><spring:message code="iMCrs.eduTerm"/></th>
			<th scope="col"><spring:message code="im.common.word.regDt" /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageInfo.list}" var="row" varStatus="i">
		<tr>
			<td><input type="checkbox" name="checkIndexs" title="선택" value="${i.index}">
			<%--삭제시 활용할 키 세팅  --%>
			<input type="hidden" name="crsPlanIds"  value="${row.crsPlan.crsPlanId}"/>
			</td>
			<td>
			<a href="#" onclick="REQ.modify({'crsPlanId' : '${row.crsPlan.crsPlanId}'});return false;">
			${row.crsPlan.crsPlanId}
			</a>
			</td>
			<td class="al"><c:out value="${row.agncy.agncyNm}"/></td>
			<td><c:out value="${row.crsPlan.eduYear}"/></td>
			<td>
			<a href="#" onclick="REQ.modify({'crsPlanId' : '${row.crsPlan.crsPlanId}'});return false;">
			<im:cd type="print" codeId="IM0001" selectedCode="${row.crsPlan.crsGrdCdv}"/>&nbsp;<im:cd type="print" codeId="IM0011" selectedCode="${row.crsPlan.crsDvsnCdv}"/></td>
			</a>
			</td>
			<td>
				<im:dt yyyyMMddHHmmss="${row.crsPlan.eduAplTerm_bgnDt}"/>
				~
				<im:dt yyyyMMddHHmmss="${row.crsPlan.eduAplTerm_endDt}"/>
			</td>
			<td>
				<im:dt yyyyMMddHHmmss="${row.crsPlan.eduTerm_bgnDt}"/>
				~
				<im:dt yyyyMMddHHmmss="${row.crsPlan.eduTerm_endDt}"/>
			</td>
			<td><im:dt yyyyMMddHHmmss="${row.crsPlan.frstRegDt}"/></td>
		</tr>
		</c:forEach>
		<c:if test="${empty pageInfo.list}">
		<tr>
			<td colspan="8"><spring:message code="im.common.msg.nodata" /></td>
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