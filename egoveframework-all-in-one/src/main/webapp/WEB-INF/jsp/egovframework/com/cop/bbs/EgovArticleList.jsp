<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>

<c:set var="pageTitle"><spring:message code="comCopBbs.articleVO.title"/></c:set>

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
		this.req.list.cfg.url    = "<c:url value="/mng/cop/bbs/selectArticleList.do"/>";
		
		this.req.search = imRequest();
		this.req.search.cfg.formId = "FormSearch";
		this.req.search.cfg.url    = "<c:url value="/mng/cop/bbs/selectArticleList.do"/>";
		
		this.req.detail = imRequest();
		this.req.detail.cfg.formId = "FormPageDetail";
		this.req.detail.cfg.url    = "<c:url value="/mng/cop/bbs/selectArticleDetail.do"/>";
		
		this.req.regist = imRequest();
		this.req.regist.cfg.formId = "FormPageList";
		this.req.regist.cfg.url    = "<c:url value="/mng/cop/bbs/insertArticleView.do"/>";
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
	
	if(${articleVO.forwardFlag}) {
		REQ.list();
	}
});

</script>

<%@ include file="incEgovArticleList.jsp" %>

<form name="FormPageDetail"  id="FormPageDetail"  method="post" onsubmit="return false;" >
	<input name="searchCnd" type="hidden" value="<c:out value="${searchVO.searchCnd}"/>">
	<input name="searchWrd" type="hidden" value="<c:out value="${searchVO.searchWrd}"/>">
	<input name="pageIndex" type="hidden" value="<c:out value="${searchVO.pageIndex}"/>">
	<input name="bbsId" type="hidden" value="${boardMasterVO.bbsId}">
	<input name="nttId" type="hidden" value="">
</form>

<form name="FormPageRegist"  id="FormPageRegist"  method="post" onsubmit="return false;" >
	<input name="bbsId" type="hidden" value="${boardMasterVO.bbsId}">
</form>


<form name="FormSearch" id="FormSearch" method="post" onsubmit="return false;" >
	<input name="bbsId" type="hidden" value="${boardMasterVO.bbsId}">
	<input name="pageIndex" type="hidden" value="1">
	<div class="sch_box">
		<div class="group">
			<select name="searchCnd" title="<spring:message code="title.searchCondition" /> <spring:message code="input.cSelect" />">
				<option value="0"  <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> ><spring:message code="comCopBbs.articleVO.list.nttSj" /></option><!-- 글 제목  -->
				<option value="1"  <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if> ><spring:message code="comCopBbs.articleVO.list.nttCn" /></option><!-- 글 내용 -->
				<option value="2"  <c:if test="${searchVO.searchCnd == '2'}">selected="selected"</c:if> ><spring:message code="table.reger" /></option><!-- 작성자 -->
			</select>
			<input class="s_input" name="searchWrd" type="text"  size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${searchVO.searchWrd}"/>'  maxlength="155" >
			<a href="#" onclick="REQ.search();return false;"  class="ad_btn gray"><spring:message code="button.inquire" /></a>
		</div>
	</div>
	
	<div class="cb_bar">
		<div>
			<span class="lab mr"></span>
		</div>
		<c:if test="${preview != 'true'}">
		<div>
<%-- 			<a href="<c:url value='/mng/cop/bbs/insertArticleView.do?bbsId=${boardMasterVO.bbsId}' />"  title="<spring:message code="button.create" /> <spring:message code="input.button" />" class="bt_new"><spring:message code="button.create" /></a><!-- 등록 --> --%>
			<!-- 등록버튼 -->
			<c:choose>
				<c:when test="${boardMasterVO.authorCode ne 'ROLE_ADMIN'and boardMasterVO.bbsId eq 'BBSMSTR_000000000081'}">
				</c:when>
				<c:otherwise>
					<a href="#" onclick="REQ.regist();return false;"  title="<spring:message code="button.create" /> <spring:message code="input.button" />" class="bt_new"><spring:message code="button.create" /></a><!-- 등록 -->
				</c:otherwise>
			</c:choose>
			
		</div>
		</c:if>
	</div>
</form>

<table class="tbl_col" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 80px;"/>
		<c:if test="${boardMasterVO.bbsId eq 'BBSMSTR_000000000001'}">
		<col style="width: 200px;"/>
		</c:if>
		<col />
		<c:if test="${boardMasterVO.atchPosblFileNumber>0}">
		<col style="width: 100px;"/>
		</c:if>
		<col style="width: 100px;"/>
		<col style="width: 100px;"/>
		<col style="width: 100px;"/>
	</colgroup>
	<thead>
		<tr>
			<th scope="col"><spring:message code="table.num" /></th><!-- 번호 -->
			<c:if test="${boardMasterVO.bbsId eq 'BBSMSTR_000000000001'}">
			<th scope="col">양성기관명</th>
			</c:if>
			<th scope="col"><spring:message code="comCopBbs.articleVO.list.nttSj" /></th><!--글 제목  -->
			<c:if test="${boardMasterVO.atchPosblFileNumber>0}">
			<th scope="col">첨부</th>
			</c:if>
			<th scope="col"><spring:message code="table.reger" /></th><!-- 작성자명 -->
			<th scope="col"><spring:message code="table.regdate" /></th><!-- 작성시각 -->
			<th scope="col"><spring:message code="comCopBbs.articleVO.list.inqireCo" /></th><!-- 조회수  -->
		</tr>	
	</thead>
	<tbody>
		<!-- 공지사항 본문 -->
		<c:forEach items="${noticeList}" var="noticeInfo" varStatus="status">
		<tr>
			<td><img src="<c:url value='/images/egovframework/com/cop/bbs/icon_notice.png'/>" alt="notice"></td>
			<c:if test="${boardMasterVO.bbsId eq 'BBSMSTR_000000000001'}">
			<td  class="al">
				<c:out value="${noticeInfo.agncyNm }"/>
			</td>
			</c:if>
			<td  class="al">
				<form name="subForm" method="post" action="<c:url value='/mng/cop/bbs/selectArticleDetail.do'/>">
					<input name="nttId" type="hidden" value="<c:out value="${noticeInfo.nttId}"/>">
				    <input name="bbsId" type="hidden" value="<c:out value="${noticeInfo.bbsId}"/>">
				    <input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
				    
				    <span>
				    <a href="#" onclick="REQ.detail({'nttId' : '${noticeInfo.nttId}'});return false;" class="tb_link">
				    	<c:out value='${fn:substring(noticeInfo.nttSj, 0, 40)}'/><c:if test="${noticeInfo.commentCo != ''}">	<c:out value='[${noticeInfo.commentCo}]'/></c:if>
					</a>
					</span>
				</form>
			</td>
			<c:if test="${boardMasterVO.atchPosblFileNumber>0}">
			<td>
			<c:if test="${!empty noticeInfo.atchFileId}">
				<img src="${imWebStatic}/common/images/www/icon/ic_attach_file.png" alt="파일 첨부">
			</c:if>
			</td>
			</c:if>
			<td><c:out value='${noticeInfo.frstRegisterNm}'/></td>
			<td><c:out value='${noticeInfo.frstRegisterPnttm}'/></td>
			<td class="ar"><c:out value='${noticeInfo.inqireCo}'/></td>		
		</tr>
		</c:forEach>
		<!-- 게시글 본문 -->
		<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
		<tr>
			<td class="ar">
			<%--
			<c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/>
			 --%>
			<c:out value="${paginationInfo.totalRecordCount - ((searchVO.pageIndex-1) * searchVO.pageSize) - status.index}"/>
			</td>
			<c:if test="${boardMasterVO.bbsId eq 'BBSMSTR_000000000001'}">
			<td  class="al">
				<c:out value="${resultInfo.agncyNm }"/>
			</td>
			</c:if>
			
		<c:choose>
			<c:when test="${resultInfo.sjBoldAt == 'Y'}">
			<!-- 제목 Bold인 경우  -->
			<td class="al">
			<form name="subForm" method="post" action="<c:url value='/mng/cop/bbs/selectArticleDetail.do'/>">
					<input name="nttId" type="hidden" value="<c:out value="${resultInfo.nttId}"/>">
				    <input name="bbsId" type="hidden" value="<c:out value="${resultInfo.bbsId}"/>">
				    <input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
				    <span>
				    <c:if test="${resultInfo.replyLc!=0}"><c:forEach begin="0" end="${resultInfo.replyLc}" step="1">&nbsp;	</c:forEach><img src="<c:url value='/images/egovframework/com/cop/bbs/icon_reply.png'/>" alt="secret"></c:if>
				    <a href="#" onclick="REQ.detail({'nttId' : '${resultInfo.nttId}'});return false;" class="tb_link">
				    	<c:out value='${fn:substring(resultInfo.nttSj, 0, 40)}'/><c:if test="${resultInfo.commentCo != ''}">	<c:out value='[${resultInfo.commentCo}]'/></c:if>
					</a>
					</span>
			</form>
			</td>
			</c:when>	
			<c:when test="${resultInfo.secretAt == 'Y' && sessionUniqId != resultInfo.frstRegisterId && boardMasterVO.authorCode ne 'ROLE_ADMIN'}">
			<!-- 비밀글이며 작성자가 본인이 아닌 경우(클릭 불가) -->
			<td class="al">
				<c:if test="${resultInfo.replyLc!=0}">
		    		<c:forEach begin="0" end="${resultInfo.replyLc}" step="1">
		    			&nbsp;
		    		</c:forEach>
		    	</c:if>
				<img src="<c:url value='/images/egovframework/com/cop/bbs/icon_lock.png'/>" alt="secret">&nbsp;<c:out value='${fn:substring(resultInfo.nttSj, 0, 40)}'/>
				<c:if test="${resultInfo.commentCo != ''}">
					<c:out value='[${resultInfo.commentCo}]'/>
				</c:if>
			</td>
			</c:when>
			<c:otherwise>
			<!-- 나머지 경우 -->
			<td class="al">
				<c:choose>
				<c:when test="${preview == 'true'}">					
					    <input name="nttId" type="hidden" value="<c:out value="${resultInfo.nttId}"/>">
					    <input name="bbsId" type="hidden" value="<c:out value="${resultInfo.bbsId}"/>">
					    <input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
					    <span>
					    <c:if test="${resultInfo.replyLc!=0}"><c:forEach begin="0" end="${resultInfo.replyLc}" step="1">&nbsp;	</c:forEach><img src="<c:url value='/images/egovframework/com/cop/bbs/icon_reply.png'/>" alt="secret"></c:if>
					    <a href="#" onclick="REQ.detail({'nttId' : '${resultInfo.nttId}'});return false;" class="tb_link">
				    		<c:out value='${fn:substring(resultInfo.nttSj, 0, 40)}'/><c:if test="${resultInfo.commentCo != ''}">	<c:out value='[${resultInfo.commentCo}]'/></c:if>
						</a>
						</span>
				</c:when>
				<c:otherwise>
			    	<form name="subForm" method="post" action="<c:url value='/mng/cop/bbs/selectArticleDetail.do'/>">
			    			
						    <input name="nttId" type="hidden" value="<c:out value="${resultInfo.nttId}"/>">
						    <input name="bbsId" type="hidden" value="<c:out value="${resultInfo.bbsId}"/>">
						    <input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
						    <span>
						    <c:if test="${resultInfo.replyLc!=0}"><c:forEach begin="0" end="${resultInfo.replyLc}" step="1">&nbsp;	</c:forEach><img src="<c:url value='/images/egovframework/com/cop/bbs/icon_reply.png'/>" alt="secret"></c:if>
						    <a href="#" onclick="REQ.detail({'nttId' : '${resultInfo.nttId}'});return false;" class="tb_link">
					    		<c:out value='${fn:substring(resultInfo.nttSj, 0, 40)}'/><c:if test="${resultInfo.commentCo != ''}">	<c:out value='[${resultInfo.commentCo}]'/></c:if>
							</a>
							</span>
					</form>
				</c:otherwise>
				</c:choose>
			</td>
			</c:otherwise>
		</c:choose>
			<c:if test="${boardMasterVO.atchPosblFileNumber>0}">
			<td>
			<c:if test="${!empty resultInfo.atchFileId}">
				<img src="${imWebStatic}/common/images/www/icon/ic_attach_file.png" alt="파일 첨부">
			</c:if>
			</td>
			</c:if>
			<td><c:out value='${resultInfo.frstRegisterNm}'/></td>
			<td><c:out value='${resultInfo.frstRegisterPnttm}'/></td>
			<td class="ar"><c:out value='${resultInfo.inqireCo}'/></td>		
		</tr>
		</c:forEach>
	
		<c:if test="${fn:length(resultList) == 0}">
		<!-- 글이 없는 경우 -->
		<tr>
			
			<c:if test="${boardMasterVO.bbsId eq 'BBSMSTR_000000000001'}">
			<td colspan="7"><spring:message code="common.nodata.msg" /></td>
			</c:if>
			<c:if test="${boardMasterVO.bbsId ne 'BBSMSTR_000000000001'}">
			<c:if test="${boardMasterVO.atchPosblFileNumber>0}">
			<td colspan="6"><spring:message code="common.nodata.msg" /></td>
			</c:if>
			<c:if test="${boardMasterVO.atchPosblFileNumber<1}">
			<td colspan="5"><spring:message code="common.nodata.msg" /></td>
			</c:if>
			
			</c:if>
			
		</tr>
		</c:if>
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
