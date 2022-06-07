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

<c:set value="기본과정" var="crsDvsn"/>
<c:if test="${fn:containsIgnoreCase(crsResult.crsDvsnCdv, '02')}">
<c:set value="실무과정" var="crsDvsn"/>
</c:if>


<div class="a4Page markany-guide">
	<div class="content-area">
<div class="cert_frm none-bg">
	<div class="head">
		<p class="num">제 ${crsResult.fnshRsltCode} 호</p>
		<h1>수 료 증</h1>
	</div>
	<div class="body">
		<div class="info right">
			<ul class="user">
				<li>
					<span class="lab">이<span class="sp_2"></span>름 :</span>
					<span class="val">${crsResult.mberNm}</span>
				</li>
				<li>
					<span class="lab">생년월일 :</span>
					<span class="val">${crsResult.brdtPrint}</span>
				</li>
				<li class="wide">
					환경교육사 <span class="em">${crsGrd}</span>급 &nbsp; ${crsDvsn}
				</li>
			</ul>
		</div>
		<p class="desc">위 사람은 환경교육사 (<span class="em">${crsGrd}</span>)급 양성과정 (<span class="em">${crsResult.eduHrs}</span>시간)을<br>아래와 같이 수료하였음을 인정합니다.</p>
		<ul class="user">
			<li>
				<span class="lab">이수시간 :</span>
				<span class="val">${crsResult.eduHrs} 시간</span>
			</li>
			<li>
				<span class="lab">양성기관 :</span>
				<span class="val">${agncy.agncyNm}</span>
			</li>
		</ul>
	</div>
	<div class="foot">
		<p class="date">
			<span>${crsResult.fnshYmdYYYY}년</span>
			<span>${crsResult.fnshYmdMM}월</span>
			<span>${crsResult.fnshYmdDD}일</span>
		</p>
		<div class="info">
			<div class="corp">
				양성기관의 장
				<span class="sign"><img src="${imSiteDomainPath}/cmmn/im/getImage.do?atchFileId=${agncy.sealFileId}" alt="직인"></span>
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