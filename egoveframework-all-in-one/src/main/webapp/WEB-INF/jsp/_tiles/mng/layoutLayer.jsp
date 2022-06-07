<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>


<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="format-detection" content="telephone=no, address=no, email=no, date=no, url=no" />
<title><c:out  value="${imWebSiteMngTitle}"/></title>
<script src="${imWebStatic}/common/js/jquery/jquery-1.9.1.min.js"></script>
<link rel="stylesheet" href="${imWebStatic}/lib/jquery-ui-1.13.1.custom/jquery-ui.min.css">
<script src="${imWebStatic}/lib/jquery-ui-1.13.1.custom/jquery-ui.min.js"></script>
<%--im framework --%>
<script src="${imWebStatic}/js/common/jquery.imui.cmmt.min.js"></script>
<script src="${imWebStatic}/js/common/jquery.imui.request.i18n.bundle.ko.min.js"></script>
<script src="${imWebStatic}/js/common/jquery.imui.request.min.js"></script>

<link rel="stylesheet" href="${imWebStatic}/common/css/adm/common.css">

<script type="text/javaScript" language="javascript">
jQuery(document).ready(function(){
	IMGLBObject.parameters = jQuery("#FormMenuParam").serialize(); // 공통파라미터
	COMMT.contextPath = '${pageContext.request.contextPath}';
});
</script>

</head>
<body>

<form name="FormMenuParam" id="FormMenuParam" method="post" onsubmit="return false;">
<input type="hidden" name="_paramMenuNo" value="${nowMenuNo}"/>
</form>
<tiles:insertAttribute name="content" />
</body>
</html>



