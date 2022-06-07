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
<c:set var="pstpndRsnCdvArr">01=본인의 질병,02=그 밖의 불가피한 사유,03=기타</c:set>
<c:set var="pstpndRsnCdvPrint"><im:cd type="print" codeId="${pstpndRsnCdvArr}" selectedCode="${iMPstpndAply.pstpndRsnCdv}"/></c:set>

<c:set var="idntyYmdYYYY"><im:dt yyyyMMddHHmmss="${detail.pstpndAply.idntyYmd}" pattern="yyyy"/></c:set>
<c:set var="idntyYmdMM"><im:dt yyyyMMddHHmmss="${detail.pstpndAply.idntyYmd}" pattern="MM"/></c:set>
<c:set var="idntyYmdDD"><im:dt yyyyMMddHHmmss="${detail.pstpndAply.idntyYmd}" pattern="dd"/></c:set>


<c:set var="lcncEndYmdYYYY"><im:dt yyyyMMddHHmmss="${detail.pstpndAply.lcncEndYmd}" pattern="yyyy"/></c:set>
<c:set var="lcncEndYmdMM"><im:dt yyyyMMddHHmmss="${detail.pstpndAply.lcncEndYmd}" pattern="MM"/></c:set>
<c:set var="lcncEndYmdDD"><im:dt yyyyMMddHHmmss="${detail.pstpndAply.lcncEndYmd}" pattern="dd"/></c:set>


<c:set var="pstpndRndYmdYYYY"><im:dt yyyyMMddHHmmss="${detail.pstpndAply.pstpndRndYmd}" pattern="yyyy"/></c:set>
<c:set var="pstpndRndYmdMM"><im:dt yyyyMMddHHmmss="${detail.pstpndAply.pstpndRndYmd}" pattern="MM"/></c:set>
<c:set var="pstpndRndYmdDD"><im:dt yyyyMMddHHmmss="${detail.pstpndAply.pstpndRndYmd}" pattern="dd"/></c:set>

<div class="a4Page markany-guide">
	<div class="content-area">
		<div class="cert_confirm_frm">
			<div class="head">
				<h1>환경교육사 보수교육 유예확인서</h1>
			</div>
			<div class="body">
				<table>
					<colgroup>
						<col style="width:15%;">
						<col>
						<col style="width:20%;">
						<col>
					</colgroup>
					<tbody>
					<tr>
						<th scope="row">성명</th>
						<td>${detail.pstpndAply.mberNm}</td>
						<th scope="row">생년월일</th>
						<td>${detail.pstpndAply.brdtPrint}</td>
					</tr>
					<tr>
						<th scope="row">주소</th>
						<td colspan="3">[${iMPstpndAply.zipc}]	${iMPstpndAply.addr}  &nbsp; ${iMPstpndAply.addrDtl}</td>
					</tr>
					<tr>
						<th scope="row">유예 사유</th>
						<td>${pstpndRsnCdvPrint}</td>
						<th scope="row">자격증 번호</th>
						<td>${iMPstpndAply.qlfcRsltCode}</td>
					</tr>
					<tr>
						<th scope="row">유예 기간</th>
						<td colspan="3">${lcncEndYmdYYYY}년 ${lcncEndYmdMM}월 ${pstpndRndYmdDD}일 ~ ${pstpndRndYmdYYYY}년 ${pstpndRndYmdMM}월 ${pstpndRndYmdDD}일</td>
					</tr>
					</tbody>
				</table>
				<div class="cont">
					<p class="desc">
						「환경교육사 양성기관 지정·운영 지침」 제17조 제5항에 따라 위와 같이
						<br>환경교육사 보수교육 유예 대상자에 해당함을 확인합니다.
					</p>
					<p class="date">
						<span>${idntyYmdYYYY}년</span>
						<span>${idntyYmdMM}월</span>
						<span>${idntyYmdDD}일</span>
					</p>
				</div>
			</div>
			<div class="foot">
				<div class="info">
					<div class="corp">
						국가환경교육센터장
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