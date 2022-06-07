<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="format-detection" content="telephone=no, address=no, email=no, date=no, url=no" />
<title>환경교육사 자격평가 관리자</title>
<link rel="shortcut icon" type="image/x-icon" href="${imWebStatic}/common/images/www/common/favicon.ico">
<link rel="stylesheet" href="${imWebStatic}/common/font/CoreUI-Icons.min.css">
<link rel="stylesheet" href="${imWebStatic}/common/font/material-icons.css">
<link rel="stylesheet" href="${imWebStatic}/common/font/notosanskr.css">
<link rel="stylesheet" href="${imWebStatic}/common/../lib/jquery.mCustomScrollbar.css">
<script src="${imWebStatic}/common/js/jquery/jquery-1.9.1.min.js"></script>
<script src="${imWebStatic}/lib/jquery.mCustomScrollbar.concat.min.js"></script>
<!-- jquery ui -->
<link rel="stylesheet" href="${imWebStatic}/common/../lib/jquery-ui-1.13.1.custom/jquery-ui.min.css">
<script src="${imWebStatic}/lib/jquery-ui-1.13.1.custom/jquery-ui.min.js"></script>
<!-- //jquery ui -->
<link rel="stylesheet" href="${imWebStatic}/common/css/adm/common.css">
<script src="${imWebStatic}/common/js/adm/design.js"></script>
<script src="${imWebStatic}/js/common/jquery.imui.cmmt.min.js"></script>

<script>



var REQ = {
		init : function(){
			<c:if test="${not empty fn:trim(loginMessage) &&  loginMessage ne ''}">
			alert("<c:out value='${loginMessage}'/>");
			</c:if>		
			if(""!=COMMT.getCookie("saveMngid")){
				document.loginForm.id.value=COMMT.getCookie("saveMngid");
				$("#chekSave").prop("checked",true)
			}
		},
		login :function(){
			if (document.loginForm.id.value =="") {
		        alert("<spring:message code="comUatUia.validate.idCheck" />"); <%-- 아이디를 입력하세요 --%>
		    } else if (document.loginForm.password.value =="") {
		        alert("<spring:message code="comUatUia.validate.passCheck" />"); <%-- 비밀번호를 입력하세요 --%>
		    } else {
		    	if($("#chekSave").prop("checked")){
		    		REQ.saveid(document.loginForm.id.value);
		    	}else{
		    		REQ.saveid("");
		    	}
		    	// $('input:radio[name=search_type]:input[value=' + st + ']').prop("checked", true);
		        document.loginForm.action="<c:url value='/uat/uia/actionLogin.do'/>";
		        //document.loginForm.j_username.value = document.loginForm.userSe.value + document.loginForm.username.value;
		        //document.loginForm.action="<c:url value='/j_spring_security_check'/>";
		        document.loginForm.submit();
		    }
		},
		saveid :function(saveID) {
		    var expdate = new Date();
		    // 기본적으로 30일동안 기억하게 함. 일수를 조절하려면 * 30에서 숫자를 조절하면 됨
		    expdate.setTime(expdate.getTime() + 1000 * 3600 * 24 * 30); // 30일
		     COMMT.setCookie("saveMngid", saveID, expdate);
		}
}




$(document).ready(function(){
	REQ.init();
});
</script>
</head>
<body>
<div class="p_login">
	<div class="inner">
		<div class="ct">
			<div class="head">
<%-- 				<p class="logo"><img src="${imWebStatic}/common/images/adm/common/logo_wt.png" alt="환경교육사 자격평가"></p> --%>
					<p class="logo"><img src="${imWebStatic}/common/images/adm/common/logo.png" alt="환경교육사 자격평가"></p>
				<p class="guide"><span>환경교육사 관리 사이트</span>입니다.</p>
			</div>
			
			<form name="loginForm" id="loginForm" action="<c:url value='/uat/uia/actionLogin.do'/>" method="post">
			<input name="userSe" type="hidden" value="USR"/>
			<div class="form">
				<fieldset>
					<legend>로그인</legend>
					<div class="input">
						<div class="box id">
							<i class="m_icon account_circle"></i>
							<input type="text" name="id" id="id" title="아이디" placeholder="아이디를 입력하세요.">
						</div>
						<div class="box pw">
							<i class="m_icon lock"></i>
							<input type="password" name="password"  onKeyup="COMMT.enterCallFunc(event, REQ.login);" title="비밀번호" placeholder="비밀번호를 입력하세요.">
						</div>
						<div class="save">
							<input type="checkbox" id="chekSave">
							<label for="chekSave">아이디 저장</label>
						</div>
						<a href="#" onclick="REQ.login()"  class="enter">로그인</a>
					</div>
				</fieldset>
				<div class="help">
					<ul>
						<li><a href="${imSiteUserHome}">사용자 페이지</a></li>
						<li><a href="<c:url value="/cmmn/login.do"/>">사용자 로그인 페이지</a></li>
					</ul>
				</div>
			</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>