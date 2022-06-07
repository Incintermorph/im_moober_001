<%@ page pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="format-detection" content="telephone=no, address=no, email=no, date=no, url=no" />
<title>${imWebSiteWwwTitle}</title>
<link rel="stylesheet" href="${imWebStatic}/common/css/www/common.css">

<script language="javascript">
function fncGoAfterErrorPage(){
    history.back(-2);
}
</script>
</head>
<body>
<c:set var="pageTitle"><spring:message code="comCmmErr.runtimeException.code"/></c:set><!-- 시스템 에러 -->
	<div class="p_error">
		<div class="inner">
			<div class="img">
				<img src="${imWebStatic}/common/images/www/common/img_error.png" alt="">
			</div>
			<h1>
				<spring:message code="comCmmErr.runtimeException.title" /></p><!-- 알 수 없는 오류가 발생했습니다! -->
				<span>${pageTitle}</span>
			</h1>
			<div class="help">
				<a href="javascript:fncGoAfterErrorPage();" class="c_btn b_green">이전 페이지</a>
			</div>
		</div>
	</div>
</body>
</html>