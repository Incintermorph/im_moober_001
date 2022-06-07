<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>


<script type="text/javascript" >


var REQ = {
	req : {
		list : null,
		search : null,
		detail : null
	},
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageList";
		this.req.list.cfg.url    = "<c:url value="/cmmn/cop/bbs/"/>${bbsCode}/selectList.do";
		
		this.req.search = imRequest();
		this.req.search.cfg.formId = "FormSearch";
		this.req.search.cfg.url    = "<c:url value="/cmmn/cop/bbs/"/>${bbsCode}/selectList.do";
		

		this.req.detail = imRequest();
		this.req.detail.cfg.formId = "FormPageDetail";
		this.req.detail.cfg.url    = "<c:url value="/cmmn/cop/bbs/"/>${bbsCode}/selectDetail.do";
		
	},
	page :  function(pageNo){
		var form =  document.getElementById(this.req.list.cfg.formId);
		form.elements["pageIndex"].value= pageNo;
		this.req.list.go();
	},
	detail :  function(mapDataPks){
		COMMT.copyMapDataToFormReset(mapDataPks, this.req.detail.cfg.formId);
		this.req.detail.go();
	},	
	search : function(){
		REQ.req.search.go();
	},
}


$(document).ready(function(){
	REQ.init();
});


</script>
<form name="FormPageDetail"  id="FormPageDetail"  method="post" onsubmit="return false;" >
			    <input name="searchCnd" type="hidden" value="<c:out value="${articleVO.searchCnd}"/>">
			    <input name="searchWrd" type="hidden" value="<c:out value="${articleVO.searchWrd}"/>">
			    <input name="pageIndex" type="hidden" value="<c:out value="${articleVO.pageIndex}"/>">
			    <input name="nttId" type="hidden" value="">
</form>

<form name="FormPageList"  id="FormPageList"  method="post" onsubmit="return false;" >
			    <input name="searchCnd" type="hidden" value="<c:out value="${articleVO.searchCnd}"/>">
			    <input name="searchWrd" type="hidden" value="<c:out value="${articleVO.searchWrd}"/>">
			    <input name="pageIndex" type="hidden" value="<c:out value="${articleVO.pageIndex}"/>">
</form>

<div class="form_box">
				<form name="FormSearch" id="FormSearch" method="post" onsubmit="return false;" >
			<div class="inner">
			    <input name="pageIndex" type="hidden" value="1">
				<div class="comb">
					<div class="ip_gp">
						<c:if test="${bbsId == 'BBSMSTR_000000000001' }">
							<select title="검색 범위" name="scAgncyId">
							<option value="">전체</option>
								<c:forEach items="${agncyList}" var="row">
									<option value="${row.agncy.agncyId}"
										<c:if test="${row.agncy.agncyId eq  articleVO.scAgncyId}">
											selected="selected"
										</c:if> 
									>${row.agncy.agncyNm}</option>
								</c:forEach>
							</select>
						</c:if>
						<c:set var="scKey">0=제목,1=내용</c:set>
						<select title="검색 범위" name="searchCnd">
							<im:cd type="option" codeId="${scKey}" selectedCode="${articleVO.searchCnd}"/>
						</select>
					</div>
					<input type="text" name="searchWrd" value="<c:out value="${articleVO.searchWrd}"/>"  class="key" placeholder="검색어를 입력하세요" title="검색어" onKeyup="COMMT.enterCallFunc(event, REQ.search);">
					<a href="#" onclick="REQ.search()"  class="c_btn bk">검색</a>
				</div>
			</div>
				</form>
		</div>
		<div class="t_bar">
			<div class="count">
				총 <span class="num">${paginationInfo.totalRecordCount}</span>건
			</div>
		</div>
		<div class="tbl_rps_li">
			<table class="tbl_col has_img">
				<caption>공지사항 목록</caption>
				<colgroup>
					<col style="width:100px;">
					<c:if test="${bbsId == 'BBSMSTR_000000000001'}">
						<col style="width:20%;">
					</c:if>
					<col>
					<col style="width:100px;">
<!-- 					<col style="width:100px;"> -->
					<col style="width:120px;">
					<col style="width:100px;">
				</colgroup>
				<thead>
					<tr>
						<th scope="col">번호</th>
						<c:if test="${bbsId == 'BBSMSTR_000000000001'}">
							<th scope="col">양성기관</th>
						</c:if>
						<th scope="col">제목</th>
						<th scope="col">첨부</th>
<!-- 						<th scope="col">작성자</th> -->
						<th scope="col">작성일</th>
						<th scope="col">조회</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty articleVO.searchWrd && empty articleVO.scAgncyId}">
					<c:forEach items="${noticeList}" var="resultInfo" varStatus="status">
					<tr>
						<td class="hidden notice-label"><span class="tb_tag green">공지</span></td>
						<c:if test="${bbsId == 'BBSMSTR_000000000001'}">
							<td class="org" style="text-align:left;">
								<c:if test="${resultInfo.agncyNm != ''}">
									<div data-th="양성기관" >
										<c:out value="${resultInfo.agncyNm }"/>
									</div>
								</c:if>
							</td>
						</c:if>
						<td class="al title">
							<a href="#" onclick="REQ.detail({'nttId' : '${resultInfo.nttId}'});return false;" class="tb_link">
							<c:out value="${resultInfo.nttSj}"/>
							</a>
						</td>
						<td class="img">
							<c:if test="${!empty resultInfo.atchFileId}">
								<img src="${imWebStatic}/common/images/www/icon/ic_attach_file.png" alt="파일 첨부">
							</c:if>
						</td>
<!-- 						<td> -->
<%-- 							<div data-th="작성자"><c:out value='${resultInfo.frstRegisterNm}'/></div> --%>
<!-- 						</td> -->
						<td>
							<div data-th="작성일"><c:out value='${resultInfo.frstRegisterPnttm}'/></div>
						</td>
						<td><c:out value='${resultInfo.inqireCo}'/></td>
					</tr>
					</c:forEach>
					</c:if>
					<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
					<tr>
<%-- 						<td class="hidden"><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td> --%>
						<td class="hidden">
							<c:out value="${paginationInfo.totalRecordCount - ((searchVO.pageIndex-1) * searchVO.pageSize) - status.index}"/>
					 	</td>
						<c:if test="${bbsId == 'BBSMSTR_000000000001'}">
							<td class="org" style="text-align:left;">
								<c:if test="${resultInfo.agncyNm != ''}">
									<div data-th="양성기관">
										<c:out value="${resultInfo.agncyNm }"/>
									</div>
								</c:if>
							</td>
						</c:if>
						<td class="al title">						
							<a href="#" onclick="REQ.detail({'nttId' : '${resultInfo.nttId}'});return false;" class="tb_link">
								<c:out value="${resultInfo.nttSj}"/>
							</a>
						</td>
						<td class="img">
							<c:if test="${!empty resultInfo.atchFileId}">
								<img src="${imWebStatic}/common/images/www/icon/ic_attach_file.png" alt="파일 첨부">
							</c:if>
						</td>
<!-- 						<td> -->
<%-- 							<div data-th="작성자"><c:out value='${resultInfo.frstRegisterNm}'/></div> --%>
<!-- 						</td> -->
						<td>
							<div data-th="작성일"><c:out value='${resultInfo.frstRegisterPnttm}'/></div>
						</td>
						<td><c:out value='${resultInfo.inqireCo}'/></td>
					</tr>
					</c:forEach>
					<c:if test="${empty resultList}">
					<tr>
						<td colspan="5"><div class="no_info"><spring:message code="im.common.msg.nodata" /></div></td>
					</tr>
					</c:if>
				</tbody>
			</table>
		</div>
	
		
		<div class="paginate">
			<div class="inner">		
			<ui:pagination paginationInfo="${paginationInfo}" type="imUserPage" jsFunction="REQ.page"/>
			</div>
	   </div>
