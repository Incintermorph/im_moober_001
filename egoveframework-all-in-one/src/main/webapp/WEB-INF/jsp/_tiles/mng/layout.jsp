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
<link rel="stylesheet" href="${imWebStatic}/common/font/CoreUI-Icons.min.css">
<link rel="stylesheet" href="${imWebStatic}/common/font/material-icons.css">
<link rel="stylesheet" href="${imWebStatic}/common/font/notosanskr.css">
<!-- <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700&display=swap"> -->
<link rel="stylesheet" href="${imWebStatic}/lib/jquery.mCustomScrollbar.css">
<script src="${imWebStatic}/common/js/jquery/jquery-1.9.1.min.js"></script>
<script src="${imWebStatic}/lib/jquery.mCustomScrollbar.concat.min.js"></script>
<script src="${imWebStatic}/common/js/adm/design.js"></script>
<!-- jquery ui -->
<link rel="stylesheet" href="${imWebStatic}/lib/jquery-ui-1.13.1.custom/jquery-ui.min.css">
<script src="${imWebStatic}/lib/jquery-ui-1.13.1.custom/jquery-ui.min.js"></script>
<!-- //jquery ui -->
<link rel="stylesheet" href="${imWebStatic}/common/css/adm/common.css">
<%--im framework --%>
<script src="${imWebStatic}/js/common/jquery.imui.cmmt.min.js"></script>
<script src="${imWebStatic}/js/common/jquery.imui.request.i18n.bundle.ko.min.js"></script>
<script src="${imWebStatic}/js/common/jquery.imui.request.min.js"></script>
<script>



var MENU = {
	
	goPage : function(menuNo,goUrl,menuNm){
		var req = null;
		if(menuNo>999999){
			req = imRequest("layer");
			
			req.cfg.options.title=menuNm;
			req.cfg.options.width=1024;
			req.cfg.options.height=768;
			req.cfg.options.scroll="hidden";
		}else{
			req = imRequest();
		}

		req.cfg.formId = "FormMenuParam";
		
		var form =  document.getElementById(req.cfg.formId);
		form.elements["_paramMenuNo"].value= menuNo;
		req.cfg.url    = "${pageContext.request.contextPath}"+goUrl;
		
		req.go();
			
		
		
		
	}
}

jQuery(document).ready(function(){
	IMGLBObject.parameters = jQuery("#FormMenuParam").serialize(); // 공통파라미터
	COMMT.contextPath = '${pageContext.request.contextPath}';
	init();
	
	
});


</script>


<script type="text/javaScript" language="javascript" defer="defer">
	function getCookie(cname) {
 	  var name = cname + "=";
 	  var decodedCookie = decodeURIComponent(document.cookie);
 	  var ca = decodedCookie.split(';');
 	  for(var i = 0; i <ca.length; i++) {
 	    var c = ca[i];
 	    while (c.charAt(0) == ' ') {
 	      c = c.substring(1);
 	    }
 	    if (c.indexOf(name) == 0) {
 	      return c.substring(name.length, c.length);
 	    }
 	  }
 	  return "";
 	}
  
  	function pad(n, width) {
  	  n = n + '';
  	  return n.length >= width ? n : new Array(width - n.length + 1).join('0') + n;
  	}
  
	var objLeftTime;
	//var objClickInfo;
	var latestTime;
	var expireTime;
	var timeInterval = 1000; // 1초 간격 호출
	var firstLocalTime = 0;
	var elapsedLocalTime = 0;
	var stateExpiredTime = false;
	var logoutUrl = "<c:url value='/uat/uia/mng/actionLogout.do'/>";
	var timer;
    <%-- web.xml에 세션 만료 시간 참조 처리  session-timeout --%>
	function init() {
		objLeftTime = document.getElementById("leftTimeInfo");
		
		if (objLeftTime == null) {
			console.log("'leftTimeInfo' ID is not exist!");
			return;
		}
		//objClickInfo = document.getElementById("clickInfo");
		//console.log(objLeftTime.textContent);

		latestTime = getCookie("egovLatestServerTime");
		expireTime = getCookie("egovExpireSessionTime");
		//console.log("latestServerTime = "+latestTime);
		//console.log("expireSessionTime = "+expireTime);
		
		elapsedTime = 0;
		firstLocalTime = (new Date()).getTime();
		showRemaining();
		
		timer = setInterval(showRemaining, timeInterval); // 1초 간격 호출
	}

	function showRemaining() {
		elapsedLocalTime = (new Date()).getTime() - firstLocalTime;
		
		var timeRemaining = expireTime - latestTime - elapsedLocalTime;
		if ( timeRemaining < timeInterval ) {
			clearInterval(timer);
			objLeftTime.innerHTML = "00:00:00";
			//objClickInfo.text = '<spring:message code="comCmm.top.expiredSessionTime"/>'; //시간만료
			stateExpiredTime = true;
			alert('<spring:message code="comCmm.top.expireSessionTimeInfo"/>');//로그인 세션시간이 만료 되었습니다.
			// reload content main page
			$("#sessionInfo").hide();

			window.location.href = logoutUrl;
			//parent.frames["_content"].location.reload();

			return;
		}

		//console.log("timeRemaining = "+timeRemaining);
		var timeHour = Math.floor(timeRemaining/1000/60 / 60); 
		var timeMin = Math.floor((timeRemaining/1000/60) % 60);
		var timeSec = Math.floor((timeRemaining/1000) % 60);
		objLeftTime.innerHTML = pad(timeHour,2) +":"+ pad(timeMin,2) +":"+ pad(timeSec,2);
		
		if(timeSec%10 == 0 ){
			if (stateExpiredTime!=true) {
				chekLoginAjax();
			}
		}
	}
  
	function reqTimeAjax() {
	  	
	  	if (stateExpiredTime==true) {
	  		alert('<spring:message code="comCmm.top.cantIncSessionTime"/>');//시간을 연장할수 없습니다.
	  		return;
	  	}
	  	
		$.ajax({
		    url:'${pageContext.request.contextPath}/uat/uia/refreshSessionTimeout.do', //request 보낼 서버의 경로
		    type:'get', // 메소드(get, post, put 등)
		    data:{}, //보낼 데이터
		    success: function(data) {
		        //서버로부터 정상적으로 응답이 왔을 때 실행
	        	latestTime = getCookie("egovLatestServerTime");
	        	expireTime = getCookie("egovExpireSessionTime");
	        	//console.log("latestServerTime = "+latestTime);
	        	//console.log("expireSessionTime = "+expireTime);
	        	init();
		        //alert("정상수신 , data = "+data);
		    },
		    error: function(err) {
		    	alert("err : "+err);
		        //서버로부터 응답이 정상적으로 처리되지 못햇을 때 실행
		    	//alert("오류발생 , error="+err.state());
		    }
		});
		return false;
	}
	
	function chekLoginAjax() {
	  	
	  	if (stateExpiredTime==true) {
	  		return;
	  	}
	  	
		$.ajax({
		    url:'${pageContext.request.contextPath}/cmmn/chek/session.do', //request 보낼 서버의 경로
		    type:'post', // 메소드(get, post, put 등)
		    data:{enEsntlId: "<c:out value="${imfunc:encryptString(IMLoginUser.uniqId)}"/>",sesinId:"<c:out value="${IMLoginUser.sesinId}"/>"}, //보낼 데이터
		    dataType: "json" ,
		    success: function(data) {
		    	//console.log("result = "+data.result);
		    	if(data.result==0){
		    		stateExpiredTime=true;
		    		alert(data.lastMdfcnDt+'('+data.lastMdferIp+')에서\n\n중복 접속 되어 로그아웃 됩니다.');
		    		location.reload();
		    	}else if(data.result==-2){
				    stateExpiredTime=true;
		    		alert('로그아웃 되었습니다.');
		    		location.reload();
		    	}
		    	
		    },
		    error: function(err) {
		    	//alert("err : "+err);
		        //서버로부터 응답이 정상적으로 처리되지 못햇을 때 실행
		    	//alert("오류발생 , error="+err.state());
		    }
		});
		return false;
	}
		
</script>
</head>
<body ><%--oncontextmenu='return false' --%>
<form name="FormMenuParam" id="FormMenuParam" method="post" onsubmit="return false;">
<input type="text" name="_paramMenuNo" value="${nowMenuNo}"/>
</form>
<div class="adm">
	<!-- header -->
	<div id="header">
		<h1 class="logo">
			<a href="${imSiteMngHome}"><img src="${imWebStatic}/common/images/adm/common/logo_wt.png" alt="환경교육사 자격평가"></a>
		</h1>
		<div class="global">
			
			<div class="info">
				${imLogin_name} [<im:dt yyyyMMddHHmmss="${imLogin_loginDateTime}" pattern="yyyy.MM.dd HH:mm"/>]
				<span id="sessionInfo">
				(
				<spring:message code="comCmm.top.leftSessionTime"/> - <span id="leftTimeInfo">00:00:00</span><!-- 세션만료 남은시간 -->
				)
			    </span>
			</div>
			<div class="menu">
				<a href="#" onclick="reqTimeAjax();return false;" class="ad_btn gray"><spring:message code="comCmm.top.incSessionTime"/></a>
				<a href="<c:url value='/uat/uia/mng/actionLogout.do'/>" class="ad_btn blue">로그아웃</a>
			</div>
		</div>
	</div>
	<!-- //header -->
	<!-- side -->
	
	<div class="side_nav">
		<div class="scroll">
			<ul class="nav">
				<c:forEach items="${_admMenuList}" var="row">	
				 <c:if test="${row.menuManage.upperMenuId eq 0 }">
				<li>
					<a href="#"><i class="${row.menuManage.relateImagePath}"></i>${row.menuManage.menuNm}</a>				
					<ul>
					<c:forEach items="${_admMenuList}" var="row2">
					 <c:if test="${row2.menuManage.upperMenuId eq  row.menuManage.menuNo}">
					 		<li>
								<a href="#"  <c:if test="${row2.menuManage.menuNo eq nowMenuNo}">class="active"</c:if>   onclick="MENU.goPage(${row2.menuManage.menuNo}, '${row2.progrmManage.URL}','${row2.menuManage.menuNm}')" >${row2.menuManage.menuNm}</a>
							</li>
					 </c:if>
					</c:forEach>
					</ul>
				</li>
				 </c:if>
				</c:forEach>
		</div>
	</div>
	<!-- //side -->
	<div id="container">
		<c:if test="${!empty nowMenuResultSet}">
		<div class="foreword">
			<h2>${nowMenuResultSet.menuManage.menuNm}</h2>
			<div class="path">
				<span>홈</span>
				<c:if test="${!empty nowTopMenuResultSet}">
				<span>${nowTopMenuResultSet.menuManage.menuNm}</span>
				</c:if>
				<c:if test="${!empty nowUpMenuResultSet}">
				<span>${nowUpMenuResultSet.menuManage.menuNm}</span>
				</c:if>
				<span>${nowMenuResultSet.menuManage.menuNm}</span>
				<c:if test="${empty param['_pageName']}">
					<c:if test="${_pageType eq  'SELECT'}">
					<span>목록조회</span>
					</c:if>
					<c:if test="${_pageType eq  'REGIST'}">
					<span>신규등록</span>
					</c:if>
					<c:if test="${_pageType eq  'MODIFY'}">
					<span>수정</span>
					</c:if>
				</c:if>
				<c:if test="${!empty param['_pageName']}">
					<span><c:out  value="${param['_pageName']}"/></span>
				</c:if>
			</div>
		</div>
		</c:if>
		<c:if test="${indexPageYn eq 'Y' }">
		<tiles:insertAttribute name="content" />
		</c:if>
		<c:if test="${indexPageYn ne 'Y' }">
		
		<div id="content">
			<!-- 콘텐츠 -->
			<tiles:insertAttribute name="content" />
			<!-- 콘텐츠 -->
		</div>	
		</c:if>
	</div>
</div>
</body>
</html>



