<%
 /**
  * @Class Name : EgovArticleScrapRegist.jsp
  * @Description : EgovArticleScrapRegist 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.02.01   박정규              최초 생성
  *   2016.06.13   김연호              표준프레임워크 v3.6 개선
  *
  *  @author 공통서비스팀 
  *  @since 2009.02.01
  *  @version 1.0
  *  @see
  *  
  */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<c:set var="pageTitle"><spring:message code="comCopScp.articleScrapVO.title"/></c:set>
<%-- <link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />"> --%>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="articleScrapVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">

/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init(){

	// 첫 입력란에 포커스
	document.getElementById("articleScrapVO").scrapNm.focus();

}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_regist_articleScrap(form){
	//input item Client-Side validate
	if (!validateArticleScrapVO(form)) {	
		return false;
	} else {
		
		if(confirm("<spring:message code="common.regist.msg" />")){	
			form.submit();	
		}
	} 
}

$(document).ready(function() {
	fn_egov_init();
});
</script>


<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<!-- 콘텐츠 -->
<form:form commandName="articleScrapVO" action="${pageContext.request.contextPath}/cop/scp/insertArticleScrap.do" method="post" onSubmit="fn_egov_regist_articleScrap(document.forms[1]); return false;" >
<!-- 타이틀 -->
<h3 class="ct_title">${pageTitle} <spring:message code="title.create" /></h3>
<!-- 등록폼 -->
<table class="tbl_row" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle } <spring:message code="title.create" /></caption>
	<colgroup>
		<col style="width:200px;">
		<col>
	</colgroup>
	<tbody>
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
		<!-- 스크랩 제목 -->
		<c:set var="title"><spring:message code="comCopScp.articleScrapVO.regist.scrapNm"/> </c:set>
		<tr>
			<th scope="row">${title}<span class="c_red">*</span></th>
			<td>
				<form:input path="scrapNm" title="${title} ${inputTxt}" size="70" maxlength="70" />
   				<div><form:errors path="scrapNm" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 글 제목  -->
		<c:set var="title"><spring:message code="comCopScp.articleScrapVO.regist.nttSj"/> </c:set>
		<tr>
			<th scope="row">${title }</th>
			<td>
				<c:out value="${articleVO.nttSj}" escapeXml="false" />
			</td>
		</tr>
		<!-- 작성자  -->
		<c:set var="title"><spring:message code="table.reger"/> </c:set>
		<tr>
			<th scope="row">${title}</th>
			<td>
				<c:out value="${articleVO.frstRegisterNm}" escapeXml="false" />
			</td>
		</tr>
		<!-- 작성시간  -->
		<c:set var="title"><spring:message code="table.regdate"/> </c:set>
		<tr>
			<th scope="row">${title}</th>
			<td>
				<c:out value="${articleVO.frstRegisterPnttm}" escapeXml="false" />
			</td>
		</tr>
		<!-- 조회수 -->
		<c:set var="title"><spring:message code="comCopScp.articleScrapVO.regist.inqireCo"/> </c:set>
		<tr>
			<th scope="row">${title}</th>
			<td>
				<c:out value="${articleVO.inqireCo}" escapeXml="false" />
			</td>
		</tr>
		<!-- 글 내용  -->
		<c:set var="title"><spring:message code="comCopScp.articleScrapVO.regist.nttCn"/> </c:set>
		<tr>
			<th scope="row">${title }</th>
			<td>
				<c:out value="${fn:replace(articleVO.nttCn , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>
	</tbody>
</table>
<!-- 하단 버튼 -->
<div class="b_box right">
	<button type="submit" class="ad_btn green mid" title="<spring:message code="button.create" /> <spring:message code="input.button" />"><spring:message code="button.create" /></button>
	<a href="<c:url value='/mng/cop/scp/selectArticleScrapList.do' />" class="ad_btn bk mid"  title="<spring:message code="button.list" />  <spring:message code="input.button" />"><spring:message code="button.list" /></a><!-- 목록 -->
</div>

<input name="nttId" type="hidden" value="<c:out value='${articleVO.nttId}'/>"/>
<input name="bbsId" type="hidden" value="<c:out value='${articleVO.bbsId}'/>"/>
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
<input name="cmd" type="hidden" value="<c:out value='save'/>">
</form:form>
<!-- //콘텐츠 -->

