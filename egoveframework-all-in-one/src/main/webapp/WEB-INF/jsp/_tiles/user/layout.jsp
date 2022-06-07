<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/inc/metadata.jspf" %>

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
<script src="https://www.w3schools.com/lib/w3data.js"></script>

<script src="${imWebStatic}/js/common/jquery.imui.cmmt.min.js"></script>
<script src="${imWebStatic}/js/common/jquery.imui.request.i18n.bundle.ko.min.js"></script>
<script src="${imWebStatic}/js/common/jquery.imui.request.min.js"></script> 
<script>

var GREQ = {
		req : {
			login : null
		},
		init: function(){
			this.req.login = imRequest();
			this.req.login.cfg.formId = "FormLoginParam";
			this.req.login.cfg.url    = "<c:url value="/cmmn/login.do"/>";
		},
		login : function(){
			GREQ.req.login.go();
		},
		loginClose : function(){
			$("#manage_login").hide();
		},
		loginReturnUrl : function(returnUrl){
			$("#_paramReturnUrl").val(returnUrl);
		},
		loginShow : function(){
			$("#manage_login").show();
		},
		loginChekShow : function(gourl){
			if(gourl=='dir'){
				return;
			}
			<c:if test="${!empty imLogin_uniqId}">
			window.location.href=COMMT.contextPath + gourl;
			</c:if>
			<c:if test="${empty imLogin_uniqId}">
			
			if(gourl.substr(1, 4).toLowerCase()=='user'){
				GREQ.loginReturnUrl(gourl);
				$("#manage_login").show();	
			}else{
				window.location.href=COMMT.contextPath + gourl;	
			}
			
			</c:if>
			
		},
		loginChek : function(gourl){
			GREQ.loginReturnUrl(gourl);
			$("#manage_login").show();
		}
}

jQuery(document).ready(function(){
	IMGLBObject.parameters = jQuery("#FormMenuParam").serialize(); // 공통파라미터
	COMMT.contextPath = '${pageContext.request.contextPath}';
	GREQ.init();
	
	
	$(document).bind('keydown',function(e){
		/* F12 방지 */
		if ( e.keyCode == 123 ) {
			e.preventDefault(); 
			e.returnValue = false;
		}
		

	});
	
});


	document.onmousedown=disableclick; 
	function disableclick(event){ 
		if (event.button==2) { 
			alert('마우스 우클릭은 사용할 수 없습니다.'); 
			return false; 
		} 
	}
	
</script>
<script type="text/javascript">



</script>

<style>
/* 바로가기 메뉴 여백 */
.main .sc_link {padding:40px 165px 50px 0;}
</style>
</head>
<body >
<div id="skip">
	<a href="#gnb">주메뉴 바로가기</a>
	<a href="#content">본문 바로가기</a>
</div>
<div class="ctm">
	<!-- header -->
	<div id="header">
		<div class="global">
	<div class="top rows">
		<h1 class="logo">
			<a href="${imSiteUserHome}"><span class="sr_only">${imWebSiteWwwTitle}</span></a>
		</h1>
		<a href="#sidr" class="nav_toggle">
			<span class="line"></span>
			<span class="line"></span>
			<span class="line"></span>
			<span class="sr_only">모바일 메뉴</span>
		</a>
	</div>
	<div class="rows">
		<div class="link">
			<ul>
				<li>
					<a href="http://me.go.kr/" target="_blank"><span>환경부</span></a>
				</li>
				<li>
					<a href="https://www.keep.go.kr/" target="_blank"><span>환경교육포털</span></a>
				</li>
				<li>
					<a href="https://danzzak.getsmart.co.kr/" target="_blank"><span>온라인 학습(단짝)</span></a>
				</li>
			</ul>
		</div>
		<div class="member">
			<c:if test="${empty imLogin_uniqId}">
			<ul>
				<li>
					<a href="<c:url value="/cmmn/login.do"/>" class="log">로그인</a>
				</li>
				<li>
					<a href="${imSiteMemberjoinPage}" target="_blank" class="join">회원가입</a>
				</li>
			</ul>	
			</c:if>
			<c:if test="${!empty imLogin_uniqId}">
				<span class="user">${imLogin_name}님 반갑습니다.</span>
			<ul>
				<li>
					<a href="<c:url value="/uat/uia/actionLogout.do"/>" class="log">로그아웃</a>
				</li>
				<li>
<!-- 					<a href="#" onclick="javascript:GREQ.loginChekShow('/user/mmbrHstry/modify.do');" class="state">이력관리</a> -->
					<a href="#" onclick="javascript:GREQ.loginChekShow('/user/mmbrHstry/modify.do');" class="join">이력관리</a>
				</li>
			</ul>	
			</c:if>
		</div>
	</div>
</div>
<div class="frm">
	<div class="nav">
		<div class="rows">
			<ul class="menu" id="gnb">
				<c:forEach items="${__menuList}" var="row">	
				 <c:if test="${row.menuManage.upperMenuId eq 0 }">
				<li>
					<a href="javascript:;" onclick="javascript:GREQ.loginChekShow('${row.progrmManage.URL}');">${row.menuManage.menuNm}</a>
					<ul>
						<c:forEach items="${__menuList}" var="row2">
						 <c:if test="${row2.menuManage.upperMenuId eq  row.menuManage.menuNo}">
						 <li  <c:if test="${row2.menuManage.menuNo eq nowMenuNo}">class="active"</c:if>>
						 <a href="javascript:;" onclick="javascript:GREQ.loginChekShow('${row2.progrmManage.URL}');" >${row2.menuManage.menuNm}</a>
						 </li>
						 </c:if>
					 	</c:forEach>
					</ul>
				</li>
				</c:if>
				</c:forEach>
			</ul>
		</div>
	</div>
</div>	
</div>
	<!-- //header -->
	<!-- 모바일 메뉴 -->
	<div class="side_bar" id="sidr">
		<div class="inner">
			<div class="head">
				<button type="button" class="tog"><i class="m_icon close"></i></button>
				<ul class="gl">
					<c:if test="${empty imLogin_uniqId}">
					<li><a href="<c:url value="/cmmn/login.do"/>">로그인</a></li>
					<li><a href="${imSiteMemberjoinPage}" target="_blank">회원가입</a></li>
					</c:if>
					<c:if test="${!empty imLogin_uniqId}">
					<li><a href="<c:url value="/uat/uia/actionLogout.do"/>">로그아웃</a></li>
					<li>
					<a href="#" onclick="javascript:GREQ.loginChekShow('/user/mmbrHstry/modify.do');" class="state">이력관리</a>
					</li>
					</c:if>
				</ul>
			</div>
			<ul class="nav">
				<c:forEach items="${__menuList}" var="row">	
						<c:if test="${row.menuManage.upperMenuId eq 0 }">
						<li>
							<a href="javascript:;" onclick="javascript:GREQ.loginChekShow('${row.progrmManage.URL}');">${row.menuManage.menuNm}</a>
							<ul>
								<c:forEach items="${__menuList}" var="row2">
								 <c:if test="${row2.menuManage.upperMenuId eq  row.menuManage.menuNo}">
								 <li  <c:if test="${row2.menuManage.menuNo eq nowMenuNo}">class="active"</c:if>>
								 <a href="javascript:;" onclick="javascript:GREQ.loginChekShow('${row2.progrmManage.URL}');" >${row2.menuManage.menuNm}</a>
								 </li>
								 </c:if>
							 	</c:forEach>
							</ul>
						</li>
						</c:if>
						</c:forEach>
			</ul>
			<ul class="foot">
				<li><a href="http://me.go.kr/home/web/main.do" target="_blank"><img src="${imWebStatic}/common/images/www/common/img_gl_me.png" alt="환경부"></a></li>
				<li><a href="https://www.keep.go.kr/portal/1" target="_blank"><img src="${imWebStatic}/common/images/www/common/img_gl_keep.png" alt="환경교육포털"></a></li>
				<li><a href="https://danzzak.getsmart.co.kr/" target="_blank"><img src="${imWebStatic}/common/images/www/common/img_gl_danzzak.png" alt="단짝"></a></li>
			</ul>
		</div>
	</div>
	<!-- //모바일 메뉴 -->
	
	<c:if test="${!empty nowUpMenuResultSet}">
	<c:set var="relateImagePath" value="${nowUpMenuResultSet.menuManage.relateImagePath}"/>
	</c:if>
	<c:if test="${empty nowUpMenuResultSet}">
	<c:set var="relateImagePath" value="${nowMenuResultSet.menuManage.relateImagePath}"/>
	</c:if>
	<div class="foreword ${relateImagePath}">
		<h2><span>${nowUpMenuResultSet.menuManage.menuNm}​</span></h2>
		<div class="path">
			<div class="rows">
				<div class="home"><span class="sr_only">홈</span></div>
				<c:if test="${!empty nowUpMenuResultSet}">
				<div><span>${nowUpMenuResultSet.menuManage.menuNm}​</span></div>
				</c:if>
				<div><span>${nowMenuResultSet.menuManage.menuNm}</span></div>
			</div>
		</div>
	</div>
	<div id="content">
		<!-- 콘텐츠 -->
		<div class="ct_head">
			<c:if test="${empty param['_paramMenuName']}">
			<h3 class="ct_title">${nowMenuResultSet.menuManage.menuNm}</h3>
			</c:if>
			<c:if test="${!empty param['_paramMenuName']}">
			<h3 class="ct_title">${param['_paramMenuName']}</h3>
			</c:if>
			<ul class="chk_us_li" id="_copyArea" style="display: none;">
				<li><a href="#" onclick="REQ.copyUrl()" class="url" title="URL 복사"><i></i><span class="sr_only">URL 복사</span></a></li>
			</ul>
		</div>
		<tiles:insertAttribute name="content" />
		<!-- //콘텐츠 -->
	</div>
	<!-- footer -->
	<div id="footer">
		<div class="rows">
			<p class="logo"><img src="${imWebStatic}/common/images/www/common/logo_footer.png" alt="환경교육사 자격평가"></p>
			<ul class="menu">
				<li class="em"><a target="blank" href="http://www.epa.or.kr/kor/user/etc/person_info.jsp?topmenu=E&leftcode=540">개인정보 처리방침</a></li>
				<li><a target="blank" href="<c:url value="/cmmn/page/rejection_email/page/popup.do"/>">이메일무단수집거부</a></li>
				<li><a href="https://www.keep.go.kr/portal/185" target="_blank">이용약관</a></li>
			</ul>
			<address>
				<p class="addr">서울특별시 성동구 광나루로 320-2 YD빌딩( 우:04799 ) E-mail : licence@epa.or.kr</p>
				<p class="copy">COPYRIGHT 2022 MINISTRY OF ENVIRONMENT. ALL RIGHTS RESERVED</p>
			</address>
		</div>	
	</div>
	<!-- //footer -->
	
<c:if test="${empty imLogin_uniqId}">
		
<div class="ct_dialog alert" id="manage_login" style="display:none;">
	<div class="tb">
		<div class="inner">
			<div class="outer" style="max-width:300px;"><!-- max-width 값 필수 -->
				<div class="top">
					<div class="bowl">
						<p class="title">Confirm</p>
						<a href="#" onclick="GREQ.loginClose()"  class="p_close"><span class="sr_only">닫기</span></a>
					</div>
				</div>
				<div class="ct">
					<!-- 콘텐츠 -->
					<p class="at_desc">로그인이 필요한 기능입니다. <br>로그인 페이지로 이동하겠습니까?</p>
					<div class="fb_box">
						<a href="javascript:;" onclick="GREQ.login()"  class="c_btn d_green">확인</a>
						<a href="javascript:;" onclick="GREQ.loginClose()"  class="c_btn wt">취소</a>
					</div>
					<!-- //콘텐츠 -->
				</div>
			</div>
		</div>
	</div>
</div>
</c:if>

<c:if test="${!empty imLogin_uniqId}">
<%@ include file="/WEB-INF/jsp/inc/imUserTimer.jspf" %>
</c:if>
</div>
<div style="display: none;">
<form name="FormMenuParam" id="FormMenuParam" method="post" onsubmit="return false;">
<input type="hidden" name="_paramMenuNo" value="${nowMenuNo}"/>
<input type="hidden" name="_paramMenuName" />
</form>
<form name="FormLoginParam" id="FormLoginParam" method="post" onsubmit="return false;">
<input type="hidden" name="_paramReturnUrl" id="_paramReturnUrl" />
</form>
</div>
</body>
</html>



