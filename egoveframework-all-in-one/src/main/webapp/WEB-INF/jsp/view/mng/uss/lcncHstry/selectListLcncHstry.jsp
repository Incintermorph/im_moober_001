<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>


<script type="text/javascript" >


var REQ = {
	req : {
		list : null,
		search : null,
		modify : null,
		del : null,
	},
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetail";
		this.req.list.cfg.url    = "<c:url value="/mng/lcncHstry/selectList.do"/>";
		
		this.req.search = imRequest();
		this.req.search.cfg.formId = "FormSearch";
		this.req.search.cfg.url    = "<c:url value="/mng/lcncHstry/selectList.do"/>";
		

		
		this.req.modify = imRequest();
		this.req.modify.cfg.formId = "FormPageDetail";
		this.req.modify.cfg.url    = "<c:url value="/mng/lcncHstry/modify.do"/>";
		
		
		this.req.del = imRequest("ajax",{formId: "FormList"});
		this.req.del.cfg.type   = "json";
		this.req.del.cfg.url    =  "<c:url value="/mng/lcncHstry/deletelist.do"/>";
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
	modify :  function(mapDataPks){
		COMMT.copyMapDataToFormReset(mapDataPks, this.req.modify.cfg.formId);
		REQ.req.modify.go();
	},
	search : function(){
		REQ.req.search.go();
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
	pageload :  function(){
		var form =  document.getElementById(REQ.req.list.cfg.formId);
		form.elements["currentPageNo"].value= 1;
		REQ.req.list.go();
	},
	regist:function(){
		var req = imRequest("layer");
		req.cfg.url    = "<c:url value="/mng/lcncHstry/registExcelLayer.do"/>";
		req.cfg.options.title="일괄 등록";
		req.cfg.formId = "FormPageDetail";

		var form =  document.getElementById(req.cfg.formId);
		form.elements["imCallBack"].value= "REQ.pageload";
		
		req.cfg.options.scroll="hidden";
		req.cfg.options.width=600;
		req.cfg.options.height=300;
		req.go();
			
	},
}


$(document).ready(function(){
	REQ.init();
});


</script>
<%@ include file="incLcncHstry.jsp" %>



<im:form  condition="${condition}" formName="FormSearch"  type="search"> <%--검색인 경우 타입 --%>
<%--기본 검색 키 값 정의 키=값,키=값 으로 정의함 --%>
<c:set var="scKey">lcncNo=<spring:message code="iMLcncHstry.lcncNo" />,mmbrNm=<spring:message code="iMLcncHstry.mmbrNm" />,lcncRsltCode=<spring:message code="iMLcncHstry.lcncRsltCode" /></c:set>
	<div class="sch_box">		
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
	<div> 
	<a href="#" onclick="REQ.regist();return false;" class="bt_new bk">일괄등록</a> 
	</div>
	
</div>
<%--목록 내용  --%>
<form name="FormList" id="FormList" method="post" onsubmit="return false;">
<table class="tbl_col">
	<colgroup>
		<col style="width:50px;">
		<col/>
			<col/>
	<col/>
	<col/>
	<col/>
	<col/>
	<col/>
	<col/>
	<col/>
	<col/>
	<col/>
	<col/>
	<col/>
	<col/>
	<col style="width:100px;">
	</colgroup>
	<thead>
		<tr>
	<th scope="col"><spring:message code="iMLcncHstry.lcncNo"/></th>
	<th scope="col"><spring:message code="iMLcncHstry.acqsYear"/></th>
	<th scope="col"><spring:message code="iMLcncHstry.lcncRsltCode"/></th>
	<th scope="col"><spring:message code="iMLcncHstry.crsGrd"/></th>
	<th scope="col"><spring:message code="iMLcncHstry.lcncAcqsDt"/></th>
	<th scope="col"><spring:message code="iMLcncHstry.brdt"/></th>
	<th scope="col"><spring:message code="iMLcncHstry.agncyNm"/></th>
	<th scope="col"><spring:message code="iMLcncHstry.mmbrNm"/></th>
	<th scope="col"><spring:message code="iMLcncHstry.telno"/></th>
	<th scope="col"><spring:message code="iMLcncHstry.eml"/></th>
	<th scope="col"><spring:message code="iMLcncHstry.yn_2018"/></th>
	<th scope="col"><spring:message code="iMLcncHstry.yn_2019"/></th>
	<th scope="col"><spring:message code="iMLcncHstry.yn_2020"/></th>
	<th scope="col"><spring:message code="iMLcncHstry.yn_2021"/></th>
	<th scope="col">여부2022</th>
	<th scope="col"><spring:message code="iMLcncHstry.cntneduCnt"/></th>
	<th scope="col">유효기간만료일</th>

			<th scope="col"><spring:message code="im.common.word.regDt" /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageInfo.list}" var="row" varStatus="i">
	<td><c:out value="${row.lcncHstry.lcncNo}"/></td>
	<td><c:out value="${row.lcncHstry.acqsYear}"/></td>
	<td>
	<a href="#" onclick="REQ.modify({'lcncHstryId' : '${row.lcncHstry.lcncHstryId}'});return false;">
	<c:out value="${row.lcncHstry.lcncRsltCode}"/>
	</a>
	</td>
	<td><c:out value="${row.lcncHstry.crsGrd}"/></td>
	<td><c:out value="${row.lcncHstry.lcncAcqsDt}"/></td>
	<td><c:out value="${row.lcncHstry.brdt}"/></td>
	<td><c:out value="${row.lcncHstry.agncyNm}"/></td>
	<td><c:out value="${row.lcncHstry.mmbrNm}"/></td>
	<td><c:out value="${row.lcncHstry.telno}"/></td>
	<td><c:out value="${row.lcncHstry.eml}"/></td>
	<td><c:out value="${row.lcncHstry.yn_2018}"/></td>
	<td><c:out value="${row.lcncHstry.yn_2019}"/></td>
	<td><c:out value="${row.lcncHstry.yn_2020}"/></td>
	<td><c:out value="${row.lcncHstry.yn_2021}"/></td>
	<td><c:out value="${row.lcncHstry.yn_2022}"/></td>
	<td><c:out value="${row.lcncHstry.cntneduCnt}"/></td>
	<td><c:out value="${row.lcncHstry.lcncEndYmd}"/></td>
			
			<td><im:dt yyyyMMddHHmmss="${row.lcncHstry.frstRegDt}"/></td>
		</tr>
		</c:forEach>
		<c:if test="${empty pageInfo.list}">
		<tr>
			<td colspan="28"><spring:message code="im.common.msg.nodata" /></td>
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