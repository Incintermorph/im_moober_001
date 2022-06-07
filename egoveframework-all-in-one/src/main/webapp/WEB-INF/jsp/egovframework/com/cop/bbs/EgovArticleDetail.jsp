<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<c:set var="pageTitle"><spring:message code="comCopBbs.articleVO.title"/></c:set>
<%-- <link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />"> --%>
<script type="text/javascript">
var REQ = {
	req : {
		list : null,
		modify : null
	},
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageList";
		this.req.list.cfg.url    = "<c:url value="/mng/cop/bbs/selectArticleList.do"/>";
		
		this.req.modify = imRequest();
		this.req.modify.cfg.formId = "FormPageModify";
		this.req.modify.cfg.url    = "<c:url value="/mng/cop/bbs/updateArticleView.do"/>";
		
	},
	list :  function(){
		this.req.list.go();
	},
	modifyPage :  function(){
		this.req.modify.go();
	}
}


$(document).ready(function(){
	REQ.init();
});

/* ********************************************************
 * 삭제처리
 ******************************************************** */
 function fn_egov_delete_article(form){
	if(confirm("<spring:message code="common.delete.msg" />")){	
		// Delete하기 위한 키값을 셋팅
		form.submit();	
	}	
}

</script>
<!-- 댓글 작성 스크립트  -->
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="articleCommentVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">
function fn_egov_insert_commentList() {
	
	var form = document.getElementById("articleCommentVO");

	if (!validateArticleCommentVO(form)){
		return;
	}
	if (confirm('<spring:message code="common.regist.msg" />')) {
		form.submit();
	}
}

function fn_egov_updt_commentList() {
	
	var form = document.getElementById("articleCommentVO");
	
	if (!validateArticleCommentVO(form)){
		return;
	}

	if (confirm('<spring:message code="common.update.msg" />')) {
		form.modified.value = "true";
		form.action = "<c:url value='/cop/cmt/updateArticleComment.do'/>";
		form.submit();
	}
}

function fn_egov_selectCommentForupdt(commentNo) {
	
	var form = document.getElementById("articleCommentVO");
	
	form.commentNo.value = commentNo;
	form.action = "<c:url value='/mng/cop/bbs/selectArticleDetail.do'/>";
	form.submit();
}

function fn_egov_deleteCommentList(commentNo) {

	var form = document.getElementById("articleCommentVO");
	
	if (confirm('<spring:message code="common.delete.msg" />')) {
		form.modified.value = "true";
		form.commentNo.value = commentNo;
		form.action = "<c:url value='/cop/cmt/deleteArticleComment.do'/>";
		form.submit();
	}
}

/* 댓글페이징 */
function fn_egov_select_commentList(pageNo) {
	
	var form = document.getElementById("articleCommentVO");
	
	form.subPageIndex.value = pageNo;
	form.commentNo.value = '';
	form.action = "<c:url value='/mng/cop/bbs/selectArticleDetail.do'/>";
	form.submit();
}

</script>

<!-- 2009.06.29 : 2단계 기능 추가  -->
<c:if test="${useSatisfaction == 'true'}">
<c:import url="/cop/stf/selectSatisfactionList.do" charEncoding="utf-8">
	<c:param name="type" value="head" />
</c:import>
</c:if>

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<%@ include file="incEgovArticleList.jsp" %>

<form name="FormPageModify"  id="FormPageModify"  method="post" onsubmit="return false;" >
	<input type="hidden" name="parnts" value="<c:out value='${result.parnts}'/>" >
	<input type="hidden" name="sortOrdr" value="<c:out value='${result.sortOrdr}'/>" >
	<input type="hidden" name="replyLc" value="<c:out value='${result.replyLc}'/>" >
	<input type="hidden" name="nttSj" value="<c:out value='${result.nttSj}'/>" >
	<input name="nttId" type="hidden" value="<c:out value="${result.nttId}" />">
	<input name="bbsId" type="hidden" value="<c:out value="${boardMasterVO.bbsId}" />">
	<input name="searchCnd" type="hidden" value="<c:out value="${searchVO.searchCnd}"/>">
	<input name="searchWrd" type="hidden" value="<c:out value="${searchVO.searchWrd}"/>">
	<input name="pageIndex" type="hidden" value="<c:out value="${searchVO.pageIndex}"/>">
</form>

<!-- 콘텐츠 -->
<!-- 타이틀 -->
<h3 class="ct_title">${pageTitle} <spring:message code="title.detail" /></h3><!-- 게시글 상세조회 -->

<!-- 상세조회 -->
<table class="tbl_row" summary="<spring:message code="common.summary.inqire" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.detail" /></caption>
	<colgroup>
		<col style="width:200px;">
		<col>
	</colgroup>
	<tbody>
		<!-- 글 제목 -->
		<tr>
			<th scope="row"><spring:message code="comCopBbs.articleVO.detail.nttSj" /></th>
			<td><c:out value="${result.nttSj}"/></td>
		</tr>
		<!-- 양성기관 -->
		<c:if test="${result.agncyNm != ''}">
		<tr>
			<th scope="row"><spring:message code="comCopBbs.articleVO.detail.agncy" /></th>
			<td><c:out value="${result.agncyNm}"/></td>
		</tr>
		</c:if>
		<!-- 작성자 -->
		<tr>
			<th scope="row"><spring:message code="table.reger" /></th>
			<td><c:out value="${result.frstRegisterNm}"/>​</td>
		</tr>
		<!-- 작성시각 -->
		<tr>
			<th scope="row"><spring:message code="table.regdate" /></th>
			<td><c:out value="${result.frstRegisterPnttm}"/></td>
		</tr>
		<!-- 조회수 -->
		<tr>
			<th scope="row"><spring:message code="comCopBbs.articleVO.detail.inqireCo" /></th>
			<td><c:out value="${result.inqireCo}"/></td>
		</tr>
		<!-- 글 내용 -->
		<tr>
			<th scope="row"><spring:message code="comCopBbs.articleVO.detail.nttCn" /></th>
			<td>
			<c:if test="${!empty  result.phtFileId}">
			<img id="본문이미지" src="<c:url value="/cmmn/im/getImage.do?atchFileId="/>${result.phtFileId}" style="max-width: 1000px;" alt="이미지">
			<br>
			</c:if>
			<c:out value="${fn:replace(result.nttCn , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>
		<!-- 게시일자 -->
		<tr style="display: none;">
			<th scope="row"><spring:message code="comCopBbs.articleVO.detail.ntceDe" /></th>
			<td><c:out value="${result.ntceBgnde} ~ ${result.ntceEndde}" escapeXml="false" /></td>
		</tr>
		<!-- 첨부파일  -->
		<c:if test="${not empty result.atchFileId}">
		<tr>
			<th scope="row"><spring:message code="comCopBbs.articleVO.detail.atchFile" /></th>
			<td>
				<c:forEach items="${fileList}" var="row">
				<c:set var="file_Key" value="${row.atchFileId}=${row.fileSn}"/>
				<a href="javascript:COMMT.fn_egov_downFileEnc('<c:out value="${imfunc:encryptString(file_Key)}"/>')" class="file_link" >
					<c:out value="${row.orignlFileNm}"/>
				</a><br>	
				</c:forEach>
			</td>
		</tr>
		</c:if>
	</tbody>
</table>
<!-- 하단 버튼 -->
<div class="b_box right">
	<c:if test="${result.ntcrId != 'anonymous'}">
	<!-- 익명글 수정/삭제 불가  -->
		<c:if test="${boardMasterVO.authorCode eq 'ROLE_ADMIN'}">
			<button type="button" onclick="REQ.modifyPage()" class="ad_btn green mid"><spring:message code="button.update" /></button>
			<%@ include file="incEgovArticleDelete.jsp" %>
		</c:if>
		
		<c:if test="${boardMasterVO.authorCode ne 'ROLE_ADMIN'}">
			<c:if test ="${boardMasterVO.bbsId ne 'BBSMSTR_000000000081' }">
				<button type="button" onclick="REQ.modifyPage()" class="ad_btn green mid"><spring:message code="button.update" /></button>
				<%@ include file="incEgovArticleDelete.jsp" %>
			</c:if>
		</c:if>
	</c:if>
	
	<c:if test="${boardMasterVO.replyPosblAt == 'Y' }">
	<form name="formReply" action="<c:url value='/mng/cop/bbs/replyArticleView.do'/>" method="post" style="float:left; margin:0 0 0 3px;">
		<button type="submit" class="ad_btn bk mid"><spring:message code="button.reply" /></button><!-- 답글 -->
		<input name="nttId" type="hidden" value="<c:out value="${result.nttId}" />">
		<input name="bbsId" type="hidden" value="<c:out value="${boardMasterVO.bbsId}" />">
	</form>
	</c:if>
	
	<button type="button" onclick="REQ.list()" class="ad_btn bk mid"><spring:message code="button.list" /></button>
	<form name="formScrap" action="<c:url value='/mng/cop/scp/insertArticleScrapView.do'/>" method="post" style="float:left; margin:0 0 0 3px;">
		<c:choose>
			<c:when test="${boardMasterVO.authorCode ne 'ROLE_ADMIN'and boardMasterVO.bbsId eq 'BBSMSTR_000000000081'}">
			</c:when>
			<c:otherwise>
				<%-- 스크랩 삭제 
				<button type="submit" class="ad_btn bk mid"><spring:message code="button.scrap" /></button><!-- 스크랩 -->
				 --%>
			</c:otherwise>
		</c:choose>
		<input name="nttId" type="hidden" value="<c:out value="${result.nttId}" />">
		<input name="bbsId" type="hidden" value="<c:out value="${boardMasterVO.bbsId}" />">
	</form>
</div>
<!-- //콘텐츠 -->