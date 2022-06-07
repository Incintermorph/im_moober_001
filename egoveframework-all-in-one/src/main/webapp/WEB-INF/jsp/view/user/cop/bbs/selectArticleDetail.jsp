<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>




<script type="text/javascript" >


var REQ = {
	req : {
		list : null
	},
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetail";
		this.req.list.cfg.url    = "<c:url value="/cmmn/cop/bbs/"/>${bbsCode}/selectList.do";
		$("#_copyArea").show();
		
		
		document.title = $("#nttSj").text() + '< ' + document.title;
		
	},
	list :  function(){
		this.req.list.go();
	},
	copyUrl :  function(){
		COMMT.copyURL("<c:url value="/cmmn/cop/bbs/"/>${bbsCode}/selectDetailOpen.do?_paramMenuNo=${nowMenuNo}&nttIdOpn=<c:out value="${imfunc:encryptString(param['nttId'])}"/>");
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
</form>


<div class="tbl_detail">
			<div class="top">
				<p class="sub">
<%-- 					<c:if test="${result.noticeAt eq 'Y' }"> --%>
						<span class="tb_tag green">공지</span>
						<span>
							<c:if test="${!empty result.agncyNm}">
								[<c:out value="${result.agncyNm }"/>]
							</c:if>
						</span>
<%-- 					</c:if> --%>
					<span id="nttSj"><c:out value="${result.nttSj}"/></span>
				</p>
				<ul class="info">
<!-- 					<li> -->
<!-- 						<span class="lab">작성자</span> -->
<%-- 						<span class="val"><c:out value="${result.frstRegisterNm}"/></span> --%>
<!-- 					</li> -->
					<li>
						<span class="lab">등록일</span>
						<span class="val"><c:out value="${result.frstRegisterPnttm}"/></span>
					</li>
					<li>
						<span class="lab">조회수</span>
						<span class="val"><c:out value="${result.inqireCo}"/></span>
					</li>
				</ul>
			</div>
			<c:if test="${!empty  result.phtFileId}">
			<div class="cont ac">
			<img  title="본문이미지"  src="<c:url value="/cmmn/image/selectOne.do?atchFileId="/>${result.phtFileId}" style="max-width: 1000px;" alt="이미지">
			</div>
			</c:if>
			<div class="cont">
				<c:out value="${fn:replace(result.nttCn , crlf , '<br/>')}" escapeXml="false" />
			</div>
			<div class="add_file">
				<c:forEach items="${fileList}" var="row">
				<c:set var="file_Key" value="${row.atchFileId}=${row.fileSn}"/>
				<a href="javascript:COMMT.fn_egov_downFileEnc('<c:out value="${imfunc:encryptString(file_Key)}"/>')"  >
				<c:out value="${row.orignlFileNm}"/>
				</a>	
				</c:forEach>
			</div>
		</div>
		<div class="b_box">
			<a href="javascript:;" onclick="REQ.list()" class="c_btn d_green mid">목록</a>
		</div>