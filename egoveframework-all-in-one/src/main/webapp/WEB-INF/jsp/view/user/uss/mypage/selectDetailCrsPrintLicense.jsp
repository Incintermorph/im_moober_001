<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>
<%@include file="/static/zc_html/jsp/MaFpsCommon.jsp"%>
<%
	
	out = matostring.newInstance(out);
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="format-detection" content="telephone=no, address=no, email=no, date=no, url=no" />
<title>환경교육사 자격평가</title>
<link rel="stylesheet" href="${imDirectWebStatic}/common/font/notosanskr.css">
<link rel="stylesheet" href="${imDirectWebStatic}/common/font/material-icons.css">
<link rel="stylesheet" href="${imDirectWebStatic}/common/css/www/common.css">
<script>
jQuery(function($){
});
</script>
</head>
<body>

<c:set value="3" var="crsGrd"/>								
<c:if test="${fn:containsIgnoreCase(crsResult.crsGrdCdv, '2')}">
<c:set value="2" var="crsGrd"/>
</c:if>
<c:if test="${fn:containsIgnoreCase(crsResult.crsGrdCdv, '1')}">
<c:set value="1" var="crsGrd"/>
</c:if>
<div class="a4Page markany-guide">
	<div class="content-area">
		<div class="cert_frm complete">
			<div class="head">
				<p class="num">제 ${crsResult.qlfcRsltCode} 호</p>
				<h1>환경교육사 자격증</h1>
			</div>
			<div class="body fix">
				<div class="info">
					<ul class="user">
						<li>
							<span class="lab">성<span class="sp_2"></span>명 :</span>
							<span class="val">${crsResult.mberNm}</span>
						</li>
						<li>
							<span class="lab">생년월일 :</span>
							<span class="val">${crsResult.brdtPrint}</span>
						</li>
						<li>
							<span class="lab">자격등급 :</span>
							<span class="val">환경교육사 <span class="em">${crsGrd}</span>급</span>
						</li>
					</ul>
				</div>
				<p class="desc">「환경교육의 활성화 및 지원에 관한 법률」 제16조 제1항 및<br>같은 법 시행령 제15조 제1항에 따라<br>위와 같이 환경교육사 자격을 부여합니다.</p>
			</div>
			<div class="foot">
				<p class="date">
					<span>${crsResult.lcncAcqsYmdYYYY}년</span>
					<span>${crsResult.lcncAcqsYmdMM}월</span>
					<span>${crsResult.lcncAcqsYmdDD}일</span>
				</p>
				<div class="info">
					<div class="corp al">
						환<span class="sp"></span>경<span class="sp"></span>부<span class="sp"></span>장<span class="sp"></span>관<br>
						<span class="sign"><img src="${imDirectWebStatic}/common/images/www/common/cert_sign.png" alt=""></span>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- MarkAny Page Gubun -->
</body>
</html>
<%
String	strHtmlData = out.toString();
out = ((matostring) out).getOldJspWriter();

byte	byteReadHtmlData[] = strHtmlData.getBytes ("utf-8"); 
int 	iReadHtmlDataSize = byteReadHtmlData.length;  
if(maViewerType.equals("exe")){
%>
	<%@include file="/static/zc_html/jsp/MaFpsTail.jsp"%>
<%}else{%>
	<%@include file="/static/zc_html/jsp/zcFpsTail.jsp"%>
<%}%>