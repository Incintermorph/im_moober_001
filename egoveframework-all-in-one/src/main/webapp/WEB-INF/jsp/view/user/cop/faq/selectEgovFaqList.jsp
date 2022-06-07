<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>


<script type="text/javascript" >


var REQ = {
	req : {
		list : null,
	},
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageList";
		this.req.list.cfg.url    = "<c:url value="/cmmn/cop/faq/selectList.do"/>";
				
	},
	page :  function(pageNo){
		var form =  document.getElementById(this.req.list.cfg.formId);
		form.elements["pageIndex"].value= pageNo;
		this.req.list.go();
	}
}

$(document).ready(function(){
	REQ.init();
});


</script>

<form name="FormPageList"  id="FormPageList"  method="post" onsubmit="return false;" >
			    <input name="searchCnd" type="hidden" value="<c:out value="${searchVO.searchCnd}"/>">
			    <input name="searchWrd" type="hidden" value="<c:out value="${searchVO.searchWrd}"/>">
			    <input name="pageIndex" type="hidden" value="<c:out value="${searchVO.pageIndex}"/>">
</form>
		<div class="faq_menu">
			<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
			<dl>
				<dt>
					<a href="#">
						<span class="name">Q</span>
						<div class="sub">
						<c:out value="${imfunc:textToBr(resultInfo.qestnCn)}" escapeXml="false"/> 
						</div>
					</a>
				</dt>
				<dd <c:if test="${resultInfo.faqId eq param['faqId']}">  style="display: block;"</c:if>>
					<span class="ans">A</span>
						<c:out value="${imfunc:textToBr(resultInfo.answerCn)}" escapeXml="false"/>
				</dd>
			</dl>
			</c:forEach>
		</div>
		
		
		
		<div class="paginate">
			<div class="inner">		
			<ui:pagination paginationInfo="${paginationInfo}" type="imUserPage" jsFunction="REQ.page"/>
			</div>
	   </div>