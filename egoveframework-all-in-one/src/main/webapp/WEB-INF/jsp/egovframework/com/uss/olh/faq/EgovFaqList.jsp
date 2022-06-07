<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>

<c:set var="pageTitle"><spring:message code="comUssOlhFaq.faqVO.title"/></c:set>

<script type="text/javascript">
var REQ = {
	req : {
		list : null,
		search : null,
		detail : null,
		regist : null,
	},
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageList";
		this.req.list.cfg.url    = "<c:url value="/mng/uss/olh/faq/selectFaqList.do"/>";
		
		this.req.search = imRequest();
		this.req.search.cfg.formId = "FormSearch";
		this.req.search.cfg.url    = "<c:url value="/mng/uss/olh/faq/selectFaqList.do"/>";
		
		this.req.detail = imRequest();
		this.req.detail.cfg.formId = "FormPageList";
		this.req.detail.cfg.url    = "<c:url value="/mng/uss/olh/faq/selectFaqDetail.do"/>";
		
		this.req.regist = imRequest();
		this.req.regist.cfg.formId = "FormPageList";
		this.req.regist.cfg.url    = "<c:url value="/mng/uss/olh/faq/insertFaqView.do"/>";
	},
	page :  function(pageNo){
		var form =  document.getElementById(this.req.list.cfg.formId);
		form.elements["pageIndex"].value= pageNo;
		this.req.list.go();
	},
	list :  function(){
		this.req.list.go();
	},
	search : function(){
		REQ.req.search.go();
	},
	detail :  function(mapDataPks){
		COMMT.copyMapDataToFormReset(mapDataPks, this.req.detail.cfg.formId);
		this.req.detail.go();
	},
	regist :  function(){
		this.req.regist.go();
	},
	
}

$(document).ready(function(){
	REQ.init();
	
	if(${searchVO.forwardFlag}) {
		REQ.list();
	}
});
</script>
<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<%@ include file="incEgovFaqList.jsp" %>

<form name="FormSearch" id="FormSearch" method="post" onsubmit="return false;" >
<input name="faqId" type="hidden" value="<c:out value='${searchVO.faqId}'/>">
<input name="pageIndex" type="hidden" value="1">
<div class="sch_box">
	<div class="group">
		<select name="lab" title="<spring:message code="title.searchCondition" /> <spring:message code="input.cSelect" />">
			<option value="0"  <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> ><spring:message code="comUssOlhFaq.faqVO.qestnSj" /></option><!-- 질문제목 -->
		</select>
		<input class="s_input" name="searchWrd" type="text"  size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${searchVO.searchWrd}"/>'  maxlength="155" >
		<a href="#" onclick="REQ.search();return false;"  class="ad_btn gray"><spring:message code="button.inquire" /></a>
	</div>
</div>

<div class="cb_bar">
	<div>
		<span class="lab mr"></span>
	</div>
	<div>
		<a href="#" onclick="REQ.regist();return false;"  title="<spring:message code="button.create" /> <spring:message code="input.button" />" class="bt_new"><spring:message code="button.create" /></a><!-- 등록 -->
	</div>
</div>
</form>
<table class="tbl_col" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle}<spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 80px;"/>
		<col style="width: 120px;"/>
		<col />
		<col style="width: 100px;"/>
		<col style="width: 100px;"/>
	</colgroup>
	<thead>
		<tr>
			<th scope="col"><spring:message code="table.num" /></th><!-- 번호 -->
			<th scope="col"><spring:message code="comUssOlhFaq.faqVO.qestnSj" /></th><!-- 질문제목 -->
			<th scope="col"><spring:message code="comUssOlhFaq.faqVO.qestnCn"/></th><!-- 질문제목 -->
			<th scope="col"><spring:message code="comUssOlhFaq.faqVO.inqireCo" /></th><!-- 조회수 -->
			<th scope="col"><spring:message code="table.regdate" /></th><!-- 등록일자 -->
		</tr>	
	</thead>
	<tbody>
		<c:if test="${fn:length(resultList) == 0}">
		<tr>
			<td colspan="5"><spring:message code="common.nodata.msg" /></td>
		</tr>
		</c:if>
		<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
		<tr>
			<td class="ar">
			<%-- 
			<c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/>
			--%>
			<c:out value="${paginationInfo.totalRecordCount - ((searchVO.pageIndex-1) * searchVO.pageSize) - status.index}"/>
			</td>
			<td class="al">
			<a href="#" onclick="REQ.detail({'faqId' : '${resultInfo.faqId}'});return false;" class="tb_link"><c:out value='${fn:substring(resultInfo.qestnSj, 0, 40)}'/></a>
			</td>
			<td class="al"><c:out value='${resultInfo.qestnCn}'/></td>
			<td class="ar"><c:out value='${resultInfo.inqireCo}'/></td>
			<td><c:out value='${resultInfo.frstRegisterPnttm}'/></td>
		</tr>
		</c:forEach>
	</tbody>
</table>

<!-- paging navigation -->
<div class="paginate info">
	<div class="count">
		<span>전체 ${paginationInfo.totalRecordCount}개</span>
		<span>현재 <span class="current">${paginationInfo.currentPageNo}</span>/${paginationInfo.totalPageCount}</span>
	</div>
	<div class="inner">
		<ul>
			<ui:pagination paginationInfo="${paginationInfo}" type="imUserPage" jsFunction="REQ.page"/>
		</ul>
	</div>
</div>


