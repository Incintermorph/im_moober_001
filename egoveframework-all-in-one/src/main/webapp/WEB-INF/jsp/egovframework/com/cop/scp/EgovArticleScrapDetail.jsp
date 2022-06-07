<%
 /**
  * @Class Name : EgovArticleScrapDetail.jsp
  * @Description : EgovArticleScrapDetail 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.02.01   박정규              최초 생성
  *   2016.06.13   김연호              표준프레임워크 v3.6 개선
  *  @author 공통서비스팀 
  *  @since 2009.02.01
  *  @version 1.0
  *  @see
  *  
  */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<c:set var="pageTitle"><spring:message code="comCopScp.articleScrapVO.title"/></c:set>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript">
/* ********************************************************
 * 삭제처리
 ******************************************************** */
 function fn_egov_delete_articleScrap(form){
	if(confirm("<spring:message code="common.delete.msg" />")){	
		// Delete하기 위한 키값을 셋팅
		form.submit();	
	}	
}	

</script>
<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<!-- 콘텐츠 -->
<h3 class="ct_title">${pageTitle} <spring:message code="title.detail" /></h3>
<!-- 상세조회 -->
<table class="tbl_row" summary="<spring:message code="common.summary.inqire" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.detail" /></caption>
	<colgroup>
		<col style="width:200px;">
		<col>
	</colgroup>
	<tbody>
		<!-- 스크랩명 -->
		<tr>
			<th scope="row"><spring:message code="comCopScp.articleScrapVO.detail.scrapNm" /></th>
			<td><c:out value="${result.scrapNm}"/></td>
		</tr>
		<!-- 글 제목 -->
		<tr>
			<th scope="row"><spring:message code="comCopScp.articleScrapVO.detail.nttSj" /></th>
			<td>
				<form name="subForm" method="post" action="<c:url value='/cop/bbs/selectArticleDetail.do'/>">
				    <input name="nttId" type="hidden" value="<c:out value="${articleVO.nttId}"/>">
				    <input name="bbsId" type="hidden" value="<c:out value="${articleVO.bbsId}"/>">
				    <span class="link"><input type="submit" value="<c:out value='${fn:substring(articleVO.nttSj, 0, 40)}'/>" style="border:0px solid #e0e0e0;"></span>
				</form>
			</td>
		</tr>
		<!-- 작성자 -->
		<tr>
			<th scope="row"><spring:message code="table.reger" /></th>
			<td>
				<c:out value="${articleVO.frstRegisterNm}"/>
			</td>
		</tr>
		<!-- 작성시각 -->
		<tr>
			<th scope="row"><spring:message code="table.regdate" /></th>
			<td>
				<c:out value="${articleVO.frstRegisterPnttm}"/>
			</td>
		</tr>
		<!-- 조회수 -->
		<tr>
			<th scope="row"><spring:message code="comCopScp.articleScrapVO.detail.inqireCo" /></th>
			<td>
				<c:out value="${articleVO.inqireCo}"/>
			</td>
		</tr>
		<!-- 글 내용 -->
		<tr>
			<th scope="row"><spring:message code="comCopScp.articleScrapVO.detail.nttCn" /></th>
			<td>
				<c:out value="${fn:replace(articleVO.nttCn , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>
	</tbody>
</table>
<!-- 하단 버튼 -->
<div class="b_box right">
	<form name="articleScrapForm" action="<c:url value='/mng/cop/scp/updateArticleScrapView.do'/>" method="post" style="float:left;">
		<button type="submit" class="ad_btn green mid" title="<spring:message code="title.update" /> <spring:message code="input.button" />"><spring:message code="button.update" /></button><!-- 수정 -->
		<input name="scrapId" type="hidden" value="${result.scrapId}">
		<input name="nttId" type="hidden" value="<c:out value='${articleVO.nttId}'/>"/>
		<input name="bbsId" type="hidden" value="<c:out value='${articleVO.bbsId}'/>"/>
	</form>
	<form name="formDelete" action="<c:url value='/cop/scp/deleteArticleScrap.do'/>" method="post" style="float:left; margin:0 0 0 3px;">
		<button type="submit" class="ad_btn red mid" title="<spring:message code="button.delete" /> <spring:message code="input.button" />" onclick="fn_egov_delete_articleScrap(this.form); return false;"><spring:message code="button.delete" /></button><!-- 삭제 -->
		<input name="scrapId" type="hidden" value="${result.scrapId}">
	</form>
	<form name="formList" action="<c:url value='/mng/cop/scp/selectArticleScrapList.do'/>" method="post" style="float:left; margin:0 0 0 3px;">
		<button type="submit" class="ad_btn bk mid" title="<spring:message code="title.update" /> <spring:message code="input.button" />"><spring:message code="button.list" /></button><!-- 목록 -->
	</form>
</div>
<!-- //콘텐츠 -->
