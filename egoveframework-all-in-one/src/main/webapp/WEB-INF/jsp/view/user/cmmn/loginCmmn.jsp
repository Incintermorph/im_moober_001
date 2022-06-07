<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="format-detection" content="telephone=no, address=no, email=no, date=no, url=no" />
<title>${imWebSiteWwwTitle}</title>
<link rel="shortcut icon" type="image/x-icon" href="${imWebStatic}/common/images/www/common/favicon.ico">
<link rel="stylesheet" href="${imWebStatic}/common/font/notosanskr.css">
<link rel="stylesheet" href="${imWebStatic}/common/font/material-icons.css">
<script src="${imWebStatic}/common/js/jquery/jquery-1.9.1.min.js"></script>
<!-- jquery ui -->
<link rel="stylesheet" href="${imWebStatic}/lib/jquery-ui-1.13.1.custom/jquery-ui.min.css">
<script src="${imWebStatic}/lib/jquery-ui-1.13.1.custom/jquery-ui.min.js"></script>
<!-- //jquery ui -->
<script src="${imWebStatic}/lib/slick.min.js"></script>
<script src="${imWebStatic}/common/js/www/design.js"></script>
<link rel="stylesheet" href="${imWebStatic}/common/css/www/common.css">
<script src="${imWebStatic}/js/common/jquery.imui.cmmt.min.js"></script>
<script src="${imWebStatic}/js/common/jquery.imui.request.i18n.bundle.ko.min.js"></script>
<script src="${imWebStatic}/js/common/jquery.imui.request.min.js"></script> 
</head>
<body>
<script>



var REQ = {
		init : function(){
			<c:if test="${not empty fn:trim(loginMessage) &&  loginMessage ne ''}">
			alert("<c:out value='${loginMessage}'/>");
			</c:if>		
			if(""!=COMMT.getCookie("saveid")){
				document.loginForm.id.value=COMMT.getCookie("saveid");
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
		    COMMT.setCookie("saveid", saveID, expdate);
		},
		loginProc :function(){
			if (document.loginForm.id.value =="") {
		        alert("<spring:message code="comUatUia.validate.idCheck" />"); <%-- 아이디를 입력하세요 --%>
		        return;
		    } else if (document.loginForm.password.value =="") {
		        alert("<spring:message code="comUatUia.validate.passCheck" />"); <%-- 비밀번호를 입력하세요 --%>
		        return;
		    }
			 var req = imRequest("ajax");
			 req.cfg.formId = "loginForm";
			 req.cfg.type   = "json";
			 req.cfg.asynchronous = true; // 인 경우 asynchronous
		     req.cfg.message.waiting = "로그인 처리중입니다.";
			 req.cfg.url    = "<c:url value="/cmmn/login/proc.do"/>";
			 
			 req.cfg.fn.complete = function(act, data) {
			      if (data != null && data.result >= 0 && data.resultMsg) {
			    	  if(data.result==1){
			    		  if(data.firstAgree==0){
			    			  $("#agree_dialog").show();
			    			//  REQ.login();
			    		  }else{
			    		  	REQ.login();
			    		  }
			    	  }else{
			    		  alert(data.resultMsg.replace("로그인 시도 횟수 : 1회 / 최대 : 5회", " "));
			    	  }
			        	
			       } else {
			    	   alert("<c:out value='${loginMessage}'/>");
			      }
			 };	
			 req.go();
		},
		agreeCancel :function(){
			 $("#agree_dialog").hide();
		},
		agreeConfirm :function(){
			 if(!$('#agreeYN_03_01').prop("checked")){
				 COMMT.message('개인정보 수집 및 이용동의는 필수 입니다.');
				 return;
			 }
			 <%--
			 if(!$('#agreeYN_03_02').prop("checked")){
				 COMMT.message('개인정보 제3자 제공동의는 필수 입니다.');
				 return;
			 }
			 --%>
			 if(!$('#agreeYN_03_03').prop("checked")){
				 COMMT.message('수신안내 동의는 필수 입니다.');
				 return;
			 }
				var form =  document.getElementById("loginForm");
				form.elements["agreeYN_03_01"].value= 'Y';
				<%--
				form.elements["agreeYN_03_02"].value= 'Y';
				--%>
				form.elements["agreeYN_03_03"].value= 'Y';
			 	REQ.login();
		}
}




$(document).ready(function(){
	REQ.init();
});
</script>
<div class="p_login">
	<div class="inner">
		<div class="ct">
			<div class="head">
				<p class="logo"><a href="${imSiteUserHome}"><img src="${imWebStatic}/common/images/www/common/logo.png" alt="환경교육사 자격평가"></a></p>
				<p class="guide"><span>환경교육사 홈페이지</span>에 오신 것을 환영합니다.</p>
			</div>
			
			<form name="loginForm" id="loginForm" action="<c:url value='/uat/uia/actionLogin.do'/>" method="post">
			<input name="userSe" type="hidden" value="GNR"/>
			
			<input name="cmmnCdvRefNms" type="hidden" value="agreeYN_03_01"/>
			<input name="agreeYN_03_01" type="hidden" value=""/>
			<input name="cmmnCdvRefNms" type="hidden" value="agreeYN_03_02"/>			
			<input name="agreeYN_03_02" type="hidden" value=""/>
			<input name="cmmnCdvRefNms" type="hidden" value="agreeYN_03_03"/>
			<input name="agreeYN_03_03" type="hidden" value=""/>
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
							<input type="password" name="password"  onKeyup="COMMT.enterCallFunc(event, REQ.loginProc);" title="비밀번호" placeholder="비밀번호를 입력하세요.">
						</div>
						<div class="save">
							<input type="checkbox" id="chekSave">
							<label for="chekSave">아이디 저장</label>
						</div>
						<a href="#" onclick="REQ.loginProc()" class="enter"><span>로그인</span></a>
					</div>
				</fieldset>
				<div class="help">
					<ul>
						<li><a href="${imSiteMemberjoinPage}" target="_blank">회원가입</a></li>
						<li><a href="${imSiteMemberSearchPassPage}" target="_blank">아이디/비밀번호 찾기</a></li>
					</ul>
				</div>
			</div>
			</form>
		</div>
	</div>
</div>
<div class="ct_dialog" id="agree_dialog"  style="display:none ;">
	<div class="tb">
		<div class="inner">
			<div class="outer" style="max-width:850px;"><!-- max-width 값 필수 -->
				<div class="top">
					<div class="bowl">
						<p class="title">약관동의</p>
						<a href="#" class="p_close"><span class="sr_only">닫기</span></a>
					</div>
				</div>
				<div class="ct">
					<!-- 콘텐츠 -->
					<p class="c_title">개인정보 수집 및 이용동의(필수)</p>
					<div class="termbox">
						<!-- 약관 -->
						<table class="tbl_data line mo_sm">
							<caption>개인정보 수집 및 이용동의</caption>
							<colgroup>
								<col>
								<col>
								<col style="width:25%;">
							</colgroup>
							<thead>
								<tr>
									<th scope="col">수집이용하려는 개인정보의 항목</th>
									<th scope="col">개인정보의 수집이용 목적</th>
									<th scope="col">개인정보이용기간</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>성명, 성별, 주소, 생년월일, 휴대폰번호, 이메일</td>
									<td>본인실명확인, 교육 신청자 선정 결과 알림, 자격증 발급신청 결과 알림, 보수교육 관련 알림 등 기타 자격취득에 필요한 공지 알림 (알림톡 또는 SMS)
									</td>
									<td rowspan="2">당사자가 이용중지 요청 시까지</td>
								</tr>
								<tr>
									<td>재직기관(구분, 재직기관명, 직급), 학력사항(학교명, 과정(학과,전공), 학위, 학위취득일, 학위증명서), 환경교육 경력사항(활동분야, 재직기간, 활동시간, 확인기관, 활동내역, 확인서, 정관)</td>
									<td>교육 참가자격 요건 확인</td>
								</tr>
							</tbody>
						</table>
						<!-- //약관 -->
					</div>
					<div class="ct_box">
						<ul class="ref orange sm">
							<li>귀하께서는 개인정보 제공 및 활용에 거부할 권리가 있습니다. 그러나 위 제공내용은 환경교육사 교육신청에 반드시 필요한 사항으로 거부하실 경우 환경교육사 교육신청이 불가능함을 알려드립니다.</li>
						</ul>
					</div>
					<div class="ag_term">
						<input type="checkbox" id="agreeYN_03_01">
						<label for="agreeYN_03_01">동의합니다.</label>
					</div>
					<%--
					<p class="c_title">개인정보 제3자 제공동의(필수)</p>
					<div class="termbox">
						<!-- 약관 -->
						<table class="tbl_data line mo_sm">
							<caption>개인정보 제3자 제공동의</caption>
							<colgroup>
								<col>
								<col>
								<col>
								<col>
							</colgroup>
							<thead>
								<tr>
									<th scope="col">개인정보를 제공받는 자</th>
									<th scope="col">제공하는 개인정보의 항목</th>
									<th scope="col">개인정보를 제공받는 자의<br>개인정보 이용목적</th>
									<th scope="col">개인정보를 제공받는 자의<br>개인정보 이용기간 및 보유기간</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>환경교육사 양성기관, 보수교육 운영기관</td>
									<td>성명, 성별, 주소, 생년월일, 휴대폰번호, 이메일, 재직기관(구분, 재직기관명, 직급), 학력사항(학교명, 과정(학과,전공), 학위, 학위취득일, 학위증명서), 환경교육 경력사항(활동분야, 재직기간, 활동시간, 확인기관, 활동내역, 확인서, 정관)</td>
									<td>본인실명확인, 교육 참가자격 요건</td>
									<td>당사자가 이용중지 요청 시까지</td>
								</tr>
							</tbody>
						</table>
						<!-- //약관 -->
					</div>
					<div class="ag_term">
						<input type="checkbox" id="agreeYN_03_02">
						<label for="agreeYN_03_02">동의합니다.</label>
					</div>
					 --%>
					<!-- <div class="ag_box top">
						<ul>
							<li>
								<span class="lab">SMS 수신여부:</span>
								<input type="checkbox" id="t_02">
								<label for="t_02">수신</label>
							</li>
							<li>
								<span class="lab">이메일 수신여부:</span>
								<input type="checkbox" id="t_03">
								<label for="t_03">수신</label>
							</li>
						</ul>
					</div> -->
					<div class="ag_term">
						<input type="checkbox" id="agreeYN_03_03">
						<label class="c_green" for="agreeYN_03_03">수신 미동의시 보수교육 대상자 안내를 받으실 수 없음을 확인하였습니다.</label>
					</div>
					<div class="b_box">
						<a href="javascript:;" onclick="REQ.agreeCancel()" class="c_btn wt">취소</a>
						<a href="javascript:;" onclick="REQ.agreeConfirm()" class="c_btn d_green">확인</a>
					</div>
					<!-- //콘텐츠 -->
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>