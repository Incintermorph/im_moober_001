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
<c:if test="${fn:containsIgnoreCase(detail.wtst.crsGrdCdv, '2')}">
<c:set value="2" var="crsGrd"/>
</c:if>
<c:if test="${fn:containsIgnoreCase(detail.wtst.crsGrdCdv, '1')}">
<c:set value="1" var="crsGrd"/>
</c:if>
<div class="a4Page markany-guide">
	<div class="content-area">
		<div class="cert_exam_frm">
			<div class="head">
				<h1>수 험 표</h1>
			</div>
			<div class="body">
				<div class="us_info">
					<table>
						<caption>수험자 정보</caption>
						<colgroup>
							<col style="width:22%;">
							<col style="width:22%;">
							<col>
						</colgroup>
						<tbody>
						<tr>
							<td rowspan="5">
								<div class="pr_img">
									<img src="${imSiteDomainPath}/cmmn/im/getImage.do?atchFileId=${aplcnt.phtFileId}" alt="">
									<!-- 사진 120px*160px -->
								</div>
							</td>
							<th scope="row">수험번호</th>
							<td>${aplcnt.tktstno}</td>
						</tr>
						<tr>
							<th scope="row">시험명</th>
							<td>환경교육사 ${crsGrd}급 필기시험 필기시험</td>
						</tr>
						<tr>
							<th scope="row">이름</th>
							<td>${aplcnt.mberNm}</td>
						</tr>
						<tr>
							<th scope="row">생년월일</th>
							<td>${aplcnt.brdtPrint}</td>
						</tr>
						<tr>
							<th scope="row">시험일자시</th>
							<td>
								${detail.wtst.tstYmdDateTime}&nbsp;${detail.wtst.tstBgngHrs}
								<br>(시험 시간 30분전 입실)
							</td>
						</tr>
						</tbody>
					</table>
				</div>
				<div class="guide">
					<ul>
						<li>
							<span class="ord">※</span>
							수험표는 최종합격 발표시까지 보관합니다.
						</li>
						<li>
							<span class="ord">※</span>
							아래 안내를 꼭 참조하시기 바랍니다.
						</li>
						<li>
							<span class="ord">※</span>
							문의 전화 : ${detail.wtstPlace.cntpnt}
						</li>
					</ul>
				</div>
				<table>
					<caption>수험 안내</caption>
					<colgroup>
						<col style="width:22%;">
						<col>
					</colgroup>
					<tbody>
					<tr>
						<th scope="row">시험장소</th>
						<td>${detail.agncy.agncyNm}
						<br/>
						${detail.agncy.addr}&nbsp;&nbsp;${detail.agncy.addrDtl}
						</td>
					</tr>
					<tr>
						<th scope="row">유의사항</th>
						<td>
								${imfunc:textToBr(cmmmDescMap['ntce'])}
						</td>
					</tr>
					<tr>
						<th scope="row">지참물 </th>
						<td>
							${imfunc:textToBr(cmmmDescMap['brng'])}
						</td>
					</tr>
					</tbody>
				</table>
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