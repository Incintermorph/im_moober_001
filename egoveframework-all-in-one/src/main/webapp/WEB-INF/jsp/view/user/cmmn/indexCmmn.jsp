<%@ page language="java" contentType="text/html; charset=UTF-8" %>
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
<script src="${imWebStatic}/lib/jquery.rwdImageMaps.min.js"></script>
<script src="${imWebStatic}/common/js/www/design.js"></script>
<link rel="stylesheet" href="${imWebStatic}/common/css/www/common.css">
<script src="${imWebStatic}/js/common/jquery.imui.cmmt.min.js"></script>

<script src="${imWebStatic}/js/common/jquery.imui.request.i18n.bundle.ko.min.js"></script>
<script src="${imWebStatic}/js/common/jquery.imui.request.min.js"></script>
<script type="text/javaScript" language="javascript">


var GREQ = {
		req : {
			login : null
		},
		popupkey : 'openkek220322', 
		init: function(){
			this.req.login = imRequest();
			this.req.login.cfg.formId = "FormLoginParam";
			this.req.login.cfg.url    = "<c:url value="/cmmn/login.do"/>";
			GREQ.popupSetOpen();
			$(".mainpageItem").show();
			GREQ.chekIe();
			
		},
		login : function(){
			GREQ.req.login.go();
		},
		chekIe : function(){
			var agent = navigator.userAgent.toLowerCase();
			if ( (navigator.appName == 'Netscape' && agent.indexOf('trident') != -1) || (agent.indexOf("msie") != -1)) {
			  //   alert("ie ");
			}else{
			     // ie가 아닐 경우
			}
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
		},
		popupSetClose : function(){
			$("#popup_notice_div").hide();
			
			var expdate = new Date();
		    // 기본적으로 30일동안 기억하게 함. 일수를 조절하려면 * 30에서 숫자를 조절하면 됨
		    expdate.setTime(expdate.getTime() + 1000 * 3600 * 24 ); // 30일
		    COMMT.setCookie(GREQ.popupkey, "Y", expdate);
			
		},
		popupSetOpen : function(){
			if(""==COMMT.getCookie(GREQ.popupkey)){
				$("#popup_notice_div").show();
			}
			
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
</head>
<body oncontextmenu='return false'>
<div id="skip">
	<a href="#gnb">주메뉴 바로가기</a>
	<a href="#content">본문 바로가기</a>
</div>
<div class="ctm main">
	<!-- header -->
	<div id="header" class="">
		<div class="global">
			<div class="top rows">
				<h1 class="logo">
					<a href="${imSiteUserHome}"><span class="sr_only">환경교육사</span></a>
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
<%-- 							<a href="<c:url value="/user/page/intro05/page.do"/>" class="join">회원정보수정</a> --%>
<!-- 							<a href="#" onclick="javascript:GREQ.loginChekShow('/user/mmbrHstry/modify.do');" class="state">이력관리</a> -->
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
<%-- 						<a href="<c:url value="/user/page/intro05/page.do"/>">회원가입</a> --%>
						<a href="#" onclick="javascript:GREQ.loginChekShow('/user/mmbrHstry/modify.do');" class="state">이력관리</a>
					</li>
					</c:if>
				</ul>
			</div>
			<ul class="nav">
				<c:forEach items="${__menuList}" var="row">	
						<c:if test="${row.menuManage.upperMenuId eq 0 }">
						<li>
							<a href="#">${row.menuManage.menuNm}</a>
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
	<div id="content">
		<!-- 콘텐츠 -->
		<div class="visual">
			<div class="rows">
				<!-- 팝업 공지 -->
				<c:if test="${fn:length(selectPopup) != 0 }"> <!--  팝업이 리스트가 없을 경우 출력 x -->
					<div class="pop_ban" id="popup_notice_div" style="display: none">
						<div class="head">
							<p class="title">${selectPopup[0].nttSj }</p>
							<button type="button"><i class="m_icon clear"><span class="sr_only">닫기</span></i></button>
						</div>
						<div class="frm">
							<div class="pop_sd">
								<div class="viewer">
									<!--  팝업 공지 리스트  -->
									<c:forEach items="${selectPopup}" var="selectPopup">
										<div>
											<div class="ban_item" data-pop-title="${selectPopup.nttSj}">
												<c:out value="${fn:replace(selectPopup.nttCn , crlf , '<br/>')}" escapeXml="false" />
											</div>
										</div>
									</c:forEach>
									<!--  //팝업공지 리스트  -->
								</div>
							</div>
							<div class="foot">
								<input type="checkbox" id="pclose" onclick="GREQ.popupSetClose();">
								<label for="pclose">팝업공지 오늘 하루 닫기</label>
							</div>
						</div>
					</div>
				</c:if>
				
				<div class="cont">
					<div class="slogan">
						<h2 class="title">환경교육사 자격관리</h2>
						<p class="guide" style="line-height:30px;">
							교육으로 더 나은 환경을 만드는 사람들,
							<br>환경교육사를 양성합니다.
						</p>
					</div>
					<div class="m_sd" data-slick-autoplay-status="Y">
						<div class="viewer">
							<div>
								<div class="slide_item" data-dot-title="제도소개">
									<a href="<c:url value="/cmmn/page/intro01/page.do"/>"><img src="${imWebStatic}/common/images/www/temp/m_sd_01.png" alt=""></a>
								</div>
							</div>
							<div class="mainpageItem" style="display: none">
								<div class="slide_item" data-dot-title="연간교육일정" >
									<a href="<c:url value="/cmmn/crsPlan/selectList.do"/>"><img src="${imWebStatic}/common/images/www/temp/m_sd_02.png" alt=""></a>
								</div>
							</div>
							<div class="mainpageItem" style="display: none">  
								<div class="slide_item" data-dot-title="증명서발급" >
									<img src="${imWebStatic}/common/images/www/temp/m_sd_03.png" alt="증명서발급" usemap="#iss_cert">
									<map name="iss_cert">
										<area shape="rect" coords="60, 146, 315, 198" alt="수료증 출력" 
										href="${pageContext.request.contextPath}/user/uss/mypage/crsAplcnt/selectList.do?scAplyStts=02A">
										<area shape="rect" coords="60, 203, 315, 255" alt="자격증 출력" 
										href="${pageContext.request.contextPath}/user/issuAply/selectList.do">
										<area shape="rect" coords="60, 260, 315, 312" alt="자격증 발급 재발급" 
										href="${pageContext.request.contextPath}/user/issuAply/selectList.do">
									</map>
								</div>
							</div>
							<div class="mainpageItem" style="display: none">
								<div class="slide_item" data-dot-title="양성기관소개">
									<!-- 20220308 : 양성기관소개 -->
									<img src="${imWebStatic}/common/images/www/temp/m_sd_04.png" alt="양성기관소개" usemap="#inst_intro">
									<map name="inst_intro">
										<area shape="rect" coords="64, 141, 206, 308" alt="3급 양성기관" href="${pageContext.request.contextPath}/cmmn/agncy/selectList.do">
										<area shape="rect" coords="213, 141, 355, 308" alt="2급 양성기관" href="${pageContext.request.contextPath}/cmmn/agncy/selectList.do">
									</map>
									<!-- //20220308 : 양성기관소개 -->
								</div>
							</div>
						</div>
						<div class="control">
							<button class="m_icon pause" title="슬라이드 정지 "></button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="rows">
			<ul class="sc_link">
				<li class="history">
					<div class="hover">
						<span class="lab">교육신청</span>
						<p>환경교육사 교육을<br/>신청하세요.</p>
						<p class="quick_link">
							<a href="javascript:GREQ.loginChekShow('/cmmn/crs/C/3/selectList.do');" class="c_btn d_green em">3급</a>
							<a href="javascript:GREQ.loginChekShow('/cmmn/crs/C/2/selectList.do');" class="c_btn d_green em">2급</a>
						</p>
					</div>
				</li>
				<li class="faq">
					<div class="hover">
						<span class="lab">평가신청</span>
						<p>환경교육사 필기<br>평가를 신청하세요.</p>
						<p class="quick_link">
							<a href="javascript:GREQ.loginChekShow('/cmmn/wtstPlace/3/selectList.do');" class="c_btn d_green em">3급</a>
							<a href="javascript:GREQ.loginChekShow('/cmmn/wtstPlace/2/selectList.do');" class="c_btn d_green em">2급</a>
						</p>
					</div>
				</li>
				<li class="reedu">
					<a class="hover" href="javascript:GREQ.loginChekShow('/user/uss/mypage/wtstAplcnt/selectList.do?scAplyStts=02A');">
						<span class="lab">결과조회</span>
						<p>필기평가 결과를<br>조회하세요.</p>
					</a>
				</li>
				<li class="my">
					<a class="hover" href="javascript:GREQ.loginChekShow('/user/pstpndAply/selectList.do');">
						<span class="lab">보수교육</span>
						<p>보수교육대상자인지<br>확인해 보세요.</p>
					</a>
				</li>
			</ul>
		</div>
		<div class="section course">
			<div class="rows">
				<div class="cs_head">
					<h2 class="title">교육과정</h2>
					<ul class="cs_tab">
						<li class="on"><a href="#cs_1">전체</a></li>
						<li><a href="#cs_2">3급</a></li>
						<li><a href="#cs_3">2급</a></li>
						<%--<li><a href="#cs_4">1급</a></li>  --%>
					</ul>
				</div>
				<div id="cs_1">
					<div class="edu_sd" data-slick-autoplay-status="N">
						<div class="edu_li sd">
							<c:forEach begin="0" end="7" var="indx" > <%--1차 오픈은 1급 제외 오픈시 숫자 11로 변경 필요함  --%>
							<c:set var="crsInfo" value="${listCrsAll[indx]}"/>
							<%@ include file="incCrsIndex.jspf" %>
							</c:forEach>
						</div>
						<div class="arrows">
							<button class="m_icon play"></button>
						</div>
					</div>
				</div>
				<div id="cs_2">
					<div class="edu_sd">
						<div class="edu_li">
						<c:forEach begin="0" end="3" var="indx" >
							<c:set var="crsInfo" value="${listCrs3[indx]}"/>
							<%@ include file="incCrsIndex.jspf" %>
						</c:forEach>	
						</div>
						<div class="add">
							<a href="<c:url value="/cmmn/crs/C/3/selectList.do"/>">더보기</a>
						</div>
					</div>
				</div>
				<div id="cs_3">
					<div class="edu_sd">
						<div class="edu_li">
						<c:forEach begin="0" end="3" var="indx" >
							<c:set var="crsInfo" value="${listCrs2[indx]}"/>
							<%@ include file="incCrsIndex.jspf" %>
						</c:forEach>
						</div>
						<div class="add">
							<a href="<c:url value="/cmmn/crs/C/2/selectList.do"/>">더보기</a>
						</div>
					</div>
				</div>
				<div id="cs_4" style="display: none;">
					<div class="edu_sd">
						<div class="edu_li">
						<c:forEach begin="0" end="3" var="indx" >
							<c:set var="crsInfo" value="${listCrs1[indx]}"/>
							<%@ include file="incCrsIndex.jspf" %>
						</c:forEach>	
						</div>
						<div class="add">						
								<a href="<c:url value="/cmmn/crs/1/selectList.do"/>">더보기</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="section board">
			<div class="rows">
				<div class="article notice">
					<div class="head">
						<h3>공지사항</h3>
						<a href="<c:url value="/cmmn/cop/bbs/notice/selectList.do"/>" class="more">더보기</a>
					</div>
					<div class="frm">
						<ul>
								
							<c:set var="menuNo"/>
							<c:set var="menuUrl"  value="${pageContext.request.contextPath}/cmmn/cop/bbs/notice/selectList.do"/>
							
							
							<c:if test="${!empty  _menuUrlMap[menuUrl]}">
							<c:set var="menuNo" value="${_menuUrlMap[menuUrl].menuManage.menuNo}"/>
							</c:if>
							<c:forEach items="${selectNotice}" var="resultInfo">
							<li>
								<a href="<c:url value="/cmmn/cop/bbs/notice/selectDetail.do"/>?nttId=${resultInfo.nttId}&_paramMenuNo=${menuNo}">
									<div class="info"><span class="writer">${resultInfo.agncyNm}</span></div>
									<p class="title"><c:out value="${resultInfo.nttSj}"/></p>
									<span class="date"><c:out value='${resultInfo.frstRegisterPnttm}'/></span>
								</a>
							</li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="article faq">
					<div class="head">
						<h3>FAQ</h3>
						<a href="<c:url value="/cmmn/cop/faq/selectList.do"/>" class="more">더보기</a>
					</div>
					<div class="frm">
						<ul>
							<c:forEach items="${selectFaq}" var="resultInfo">
							<li>
								<a href="<c:url value="/cmmn/cop/faq/selectList.do"/>?faqId=${resultInfo.faqId}">
									<span class="writer"><c:out value="${resultInfo.qestnSj}"/></span>
									<p class="title"><c:out value="${resultInfo.qestnCn}"/></p>
								</a>
							</li>
							</c:forEach>
						</ul>
					</div>
				</div>
				
				<div class="card_ban">
					<a href="https://danzzak.getsmart.co.kr/" class="link" target="_blank">
						<strong>온라인 학습<br>기본과정(3급)</strong>     
						<p>온라인 과정은 <br>단짝에서 수강 가능합니다.</p>
						<i class="arrow"></i>
					</a>
					<%--
					<c:if test="${!empty listWtst}">
						<div class="card_exam" data-slick-autoplay-status="Y">
							<div class="now">신청중</div>
							<div class="control">
							<button class="m_icon pause" title="슬라이드 정지"></button>
							</div>
							<div class="exam_sd">
								<c:forEach items="${listWtst}" var="row" varStatus="i">
								<c:set value="3" var="urlNum"/>								
								<c:if test="${fn:containsIgnoreCase(crsInfo.crsMstr.crsGrdCdv, '2')}">
								<c:set value="2" var="urlNum"/>
								</c:if>
								<c:if test="${fn:containsIgnoreCase(crsInfo.crsMstr.crsGrdCdv, '1')}">
								<c:set value="1" var="urlNum"/>
								</c:if>
									<c:set var="goUrlWtst" value="${pageContext.request.contextPath}/cmmn/wtstPlace/${urlNum}/selectList.do"/>
									<c:set var="goUrlWtstDetail" value="${pageContext.request.contextPath}/cmmn/wtstPlace/${urlNum}/selectDetail.do"/>
									<c:set var="menuNo"/>
									<c:if test="${!empty  _menuUrlMap[goUrlWtst]}">
									<c:set var="menuNo" value="${_menuUrlMap[goUrlWtst].menuManage.menuNo}"/>
									</c:if>
									<div class="slide">
										<div class="head">
											<p class="name">필기평가</p>
										</div>
										<div class="exam">
											<p class="title">
												<a href="${goUrlWtstDetail}?wtstPlaceId=${row.wtstPlace.wtstPlaceId}&_paramMenuNo=${menuNo}" >환경교육사  <im:cd type="print" codeId="IM0001" selectedCode="${row.wtst.crsGrdCdv}"/></a>
											</p>
											<div class="info">
												<div class="day">
													<p class="lab">원서 접수기간</p>
													<div class="date"><im:dt yyyyMMddHHmmss="${row.wtst.rcptTerm_bgnDt}"/>~<im:dt yyyyMMddHHmmss="${row.wtst.rcptTerm_endDt}"/></div>
												</div>
												<div class="cols">
													<div class="day">
														<p class="lab">시험일</p>
														<div class="date"><im:dt yyyyMMddHHmmss="${row.wtst.tstYmd}"/></div>
													</div>
													<div class="day">
														<p class="lab">합격일</p>
														<div class="date"><im:dt yyyyMMddHHmmss="${row.wtst.passTerm_bgnDt}"/></div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</c:if>
					--%>
				</div>
			</div>
		</div>
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
				<p class="addr">서울특별시 성동구 광나루로 320-2 YD빌딩( 우:04799 ) TEL : E-mail : licence@epa.or.kr</p>
				<p class="copy">COPYRIGHT 2022 MINISTRY OF ENVIRONMENT. ALL RIGHTS RESERVED</p>
			</address>
		</div>
	</div>
	<!-- //footer -->
	<!-- <a href="#" class="btn_top" title="TOP"><i class=""></i></a> -->
	<c:if test="${!empty imLogin_uniqId}">
		<%@ include file="/WEB-INF/jsp/inc/imUserTimer.jspf" %>
	</c:if>
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