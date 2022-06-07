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
<script src="${imWebStatic}/common/js/www/design.js"></script>
<link rel="stylesheet" href="${imWebStatic}/common/css/www/common.css">
<script src="${imWebStatic}/js/common/jquery.imui.cmmt.min.js"></script>

<script src="${imWebStatic}/js/common/jquery.imui.request.i18n.bundle.ko.min.js"></script>
<script src="${imWebStatic}/js/common/jquery.imui.request.min.js"></script>

<script type="text/javaScript" language="javascript">
jQuery(document).ready(function(){
	COMMT.contextPath = '${pageContext.request.contextPath}';
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

<c:set var="clseUrl" value="${pageContext.request.contextPath}/user/uss/mypage/selectDetail.do"/>
<c:if test="${!empty param['retureUrlVal']}">
<c:set var="clseUrl">${pageContext.request.contextPath}<c:out  value="${imfunc:decryptString(param['retureUrlVal'])}" escapeXml="false"/></c:set>
</c:if>



<script>
jQuery(function($){
	//팝업
	$('a[href="#find_org"], a[href="#add_layear"], a[href="#add_subject"], a[href="#major_subject"], a[href="#major_subject2"], a[href="#add_edu"], a[href="#add_career"]').on('click', function(e){
		e.preventDefault();
		var target = $($(this).attr('href'));
		if(target.is(':hidden')){
			target.fadeIn();
			$('body').css('overflow', 'hidden');
		} else {
			target.fadeOut();
			$('body').css('overflow', 'auto');
			
		};
		target.attr("tabindex","0");
		$(this).attr('data-focus','on');
		target.focus();
	});

	//jquery ui datepicker
	$("[data-role='datepicker']" ).datepicker({
		showOn:"both",
		buttonText: '',
		dateFormat: 'yy-mm-dd',
		changeYear: true,
		yearRange: "1900:+nn"
	});
});






var REQ = {
	req : {
		save1 : null
	},
	listPotalOrgHTML : null,
	init : function(){
		REQ.listPotalOrgHTML=$("#listPotalOrg").html();
		
		

		<c:if test="${saveMode eq 'U'}"> 
	    REQ.validator('${detail.mmbrHstry.workDvsnCdv}');
	    </c:if>
		<c:if test="${saveMode ne 'U'}"> 
	    REQ.validator('');
	    </c:if>
	    
	},
	validator : function(workDvsnCdv){
		
		this.req.save1 = imRequest("ajax",{formId: "iMMmbrHstry"});
		this.req.save1.cfg.type   = "json";

		this.req.save1.cfg.message.confirm="<spring:message code="common.update.msg" />";
		this.req.save1.cfg.url    =  "<c:url value="/user/mmbrHstry/update.do"/>";
		<c:if test="${saveMode ne 'U'}"> 
		this.req.save1.cfg.message.confirm="<spring:message code="common.regist.msg" />";
		this.req.save1.cfg.url    =  "<c:url value="/user/mmbrHstry/insert.do"/>";
		</c:if>
		this.req.save1.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) { 
	        	REQ.reload();
	        } else {
	        	COMMT.errorMessage();
	        }
	    };
		
		<%--
	    this.req.save1.validator.set({
	    	title  : "성별",
            name : "sexdstnCdv",
            data : ["!null"]
        });
        --%>
	    this.req.save1.validator.set({
	    	title  : "생년월일",
            name : "brdt",
            data : ["!null"]
        });
	
	    
	    this.req.save1.validator.set({
	    	title  : "구분",
            name : "workDvsnCdv",
            data : ["!null"]
        });
		   

	    if(workDvsnCdv!='3001' && workDvsnCdv!='999'){
	    	this.req.save1.validator.set({
		    	title  : "현재 재직기관",
	            name : "agncyNm",
	            data : ["!null"]
	        });
    	}
	    
	    this.req.save1.validator.set({
	    	title  : "지역",
            name : "areaCdv",
            data : ["!null"]
        });
	    this.req.save1.validator.set({
	    	title  : "지역",
            name : "areaCdv",
            data : ["!null"]
        });
	    this.req.save1.validator.set({
	    	title  : "세부지역",
            name : "areaDtl",
            data : ["!null"]
        });
	    this.req.save1.validator.set({
	    	title  : "이메일",
            name : "eml",
            data : ["!null","email"] ,
            check : {  
                maxlength : 30 
            }  

        });
	    this.req.save1.validator.set({
	    	title  : "휴대전화번호",
            name : "mblTelno1",
            data : ["!null","number"] ,
            check : {  
                maxlength : 4 
            }  
        });
	    this.req.save1.validator.set({
	    	title  : "휴대전화번호",
            name : "mblTelno2",
            data : ["!null","number"] ,
            check : {  
                maxlength : 4 
            }  
        });
	    this.req.save1.validator.set({
	    	title  : "휴대전화번호",
            name : "mblTelno3",
            data : ["!null","number"] ,
            check : {  
                maxlength : 4 
            }
        });
	    
	    this.req.save1.validator.set({
	    	title  : "신청희망 자격급수",
            name : "aplyGrdCdv",
            data : ["!null"]
        });
	    this.req.save1.validator.set({
	    	title  : "유사과목 면제",
            name : "exptSbjYn",
            data : ["!null"]
        });
	},
	save1 : function(){
		this.req.save1.go();
	},
	reload : function(){
		var req = imRequest();
		req.cfg.formId = "FormReload";
		
		req.cfg.url    = "<c:url value="/user/mmbrHstry/modify.do"/>";
		<c:if test="${adminMode eq 'Y'}">
		req.cfg.url    = "<c:url value="/mng/mmbrHstry/modify.do"/>";
		</c:if>
		req.go();
	},
	closePage : function(){
		<c:if test="${adminMode eq 'Y'}">
		window.close();
		</c:if>
		<c:if test="${adminMode ne 'Y'}">
		window.location.href='${clseUrl}';
		</c:if>
	},
	close : function(){
		$.dialog("confirm", {
			message : "이력관리 작업창을 닫으시겠습니까?",
			button1  : {
                callback : REQ.closePage
            }
		});
	},
	addMmbrWorkHstry : function(){
		
		var req = imRequest("ajax");
		req.cfg.formId = "FormReload";
		req.cfg.type        = "html";

        req.cfg.containerId = "add_layear_cnt";
		req.cfg.url    = "<c:url value="/user/mmbrWorkHstry/regist.do"/>";
		req.cfg.fn.complete = function(act, data) {
            AJAX.init();
            $("#add_layear_title").html("근무이력");
    		REQ.loadPage();        
        };
        var form =  document.getElementById(req.cfg.formId);
		form.elements["mmbrWorkHstryId"].value= '';
		
		req.go();
	},
	modifyMmbrWorkHstry : function(mmbrWorkHstryId){
		
		var req = imRequest("ajax");
		req.cfg.formId = "FormReload";
		req.cfg.type        = "html";

        req.cfg.containerId = "add_layear_cnt";
		req.cfg.url    = "<c:url value="/user/mmbrWorkHstry/modify.do"/>";
		req.cfg.fn.complete = function(act, data) {
            AJAX.init();
            $("#add_layear_title").html("근무이력");
    		REQ.loadPage();        
        };
        var form =  document.getElementById(req.cfg.formId);
		form.elements["mmbrWorkHstryId"].value= mmbrWorkHstryId;
		
		req.go();
	},
	addMmbrExptSbj : function(){
		
		var req = imRequest("ajax");
		req.cfg.formId = "FormReload";
		req.cfg.type        = "html";

        req.cfg.containerId = "add_layear_cnt";
		req.cfg.url    = "<c:url value="/user/mmbrExptSbj/regist.do"/>";
		req.cfg.fn.complete = function(act, data) {
            AJAX.init();
            $("#add_layear_title").html("면제과목");
    		REQ.loadPage();        
        };
        var form =  document.getElementById(req.cfg.formId);
		form.elements["mmbrExptSbjId"].value= '';
		form.elements["exempSbjDvsn"].value= '01';
		
		req.go();
	},
	addMmbrExptSbjOnline : function(){
		
		var req = imRequest("ajax");
		req.cfg.formId = "FormReload";
		req.cfg.type        = "html";

        req.cfg.containerId = "add_layear_cnt";
		req.cfg.url    = "<c:url value="/user/mmbrExptSbj/regist.do"/>";
		req.cfg.fn.complete = function(act, data) {
            AJAX.init();
            $("#add_layear_title").html("단짝수료과목");
    		REQ.loadPage();        
        };
        var form =  document.getElementById(req.cfg.formId);
		form.elements["mmbrExptSbjId"].value= '';
		form.elements["exempSbjDvsn"].value= '02';
		
		req.go();
	},
	modifyMmbrExptSbj : function(mmbrExptSbjId){
		
		var req = imRequest("ajax");
		req.cfg.formId = "FormReload";
		req.cfg.type        = "html";

        req.cfg.containerId = "add_layear_cnt";
		req.cfg.url    = "<c:url value="/user/mmbrExptSbj/modify.do"/>";
		req.cfg.fn.complete = function(act, data) {
            AJAX.init();
            $("#add_layear_title").html("면제과목");
    		REQ.loadPage();        
        };
        var form =  document.getElementById(req.cfg.formId);
		form.elements["mmbrExptSbjId"].value= mmbrExptSbjId;
		form.elements["exempSbjDvsn"].value= '01';
		req.go();
	},
	modifyMmbrExptSbjOnline : function(mmbrExptSbjId){
		
		var req = imRequest("ajax");
		req.cfg.formId = "FormReload";
		req.cfg.type        = "html";

        req.cfg.containerId = "add_layear_cnt";
		req.cfg.url    = "<c:url value="/user/mmbrExptSbj/modify.do"/>";
		req.cfg.fn.complete = function(act, data) {
            AJAX.init();
            $("#add_layear_title").html("단짝수료과목");
    		REQ.loadPage();        
        };
        var form =  document.getElementById(req.cfg.formId);
		form.elements["mmbrExptSbjId"].value= mmbrExptSbjId;
		form.elements["exempSbjDvsn"].value= '02';
		req.go();
	},
	addMmbrEdu : function(){
		
		var req = imRequest("ajax");
		req.cfg.formId = "FormReload";
		req.cfg.type        = "html";

        req.cfg.containerId = "add_layear_cnt";
		req.cfg.url    = "<c:url value="/user/mmbrEdu/regist.do"/>";
		req.cfg.fn.complete = function(act, data) {
            AJAX.init();
            $("#add_layear_title").html("학력사항");
    		REQ.loadPage();        
        };
        var form =  document.getElementById(req.cfg.formId);
		form.elements["mmbrEduId"].value= '';
		
		req.go();
	},
	modifyMmbrEdu : function(mmbrEduId){
		
		var req = imRequest("ajax");
		req.cfg.formId = "FormReload";
		req.cfg.type        = "html";

        req.cfg.containerId = "add_layear_cnt";
		req.cfg.url    = "<c:url value="/user/mmbrEdu/modify.do"/>";
		req.cfg.fn.complete = function(act, data) {
            AJAX.init();
            $("#add_layear_title").html("학력사항");
    		REQ.loadPage();        
        };
        var form =  document.getElementById(req.cfg.formId);
		form.elements["mmbrEduId"].value= mmbrEduId;
		req.go();
	},
	addMmbrExp : function(){
		
		var req = imRequest("ajax");
		req.cfg.formId = "FormReload";
		req.cfg.type        = "html";

        req.cfg.containerId = "add_layear_cnt";
		req.cfg.url    = "<c:url value="/user/mmbrExp/regist.do"/>";
		req.cfg.fn.complete = function(act, data) {
            AJAX.init();
            $("#add_layear_title").html("환경교육 경력사항");
    		REQ.loadPage();        
        };
        var form =  document.getElementById(req.cfg.formId);
		form.elements["mmbrExpId"].value= '';
		
		req.go();
	},
	modifyMmbrExp : function(mmbrExpId){
		
		var req = imRequest("ajax");
		req.cfg.formId = "FormReload";
		req.cfg.type        = "html";

        req.cfg.containerId = "add_layear_cnt";
		req.cfg.url    = "<c:url value="/user/mmbrExp/modify.do"/>";
		req.cfg.fn.complete = function(act, data) {
            AJAX.init();
            $("#add_layear_title").html("환경교육 경력사항");
    		REQ.loadPage();        
        };
        var form =  document.getElementById(req.cfg.formId);
		form.elements["mmbrExpId"].value= mmbrExpId;
		req.go();
	},
	addMmbrEtc : function(){
		
		var req = imRequest("ajax");
		req.cfg.formId = "FormReload";
		req.cfg.type        = "html";

        req.cfg.containerId = "add_layear_cnt";
		req.cfg.url    = "<c:url value="/user/mmbrEtc/regist.do"/>";
		req.cfg.fn.complete = function(act, data) {
            AJAX.init();
            $("#add_layear_title").html("기타사항");
    		REQ.loadPage();        
        };
        var form =  document.getElementById(req.cfg.formId);
		form.elements["mmbrEtcId"].value= '';
		
		req.go();
	},
	modifyMmbrEtc : function(mmbrEtcId){
		
		var req = imRequest("ajax");
		req.cfg.formId = "FormReload";
		req.cfg.type        = "html";

        req.cfg.containerId = "add_layear_cnt";
		req.cfg.url    = "<c:url value="/user/mmbrEtc/modify.do"/>";
		req.cfg.fn.complete = function(act, data) {
            AJAX.init();
            $("#add_layear_title").html("기타사항");
    		REQ.loadPage();        
        };
        var form =  document.getElementById(req.cfg.formId);
		form.elements["mmbrEtcId"].value= mmbrEtcId;
		req.go();
	},
	loadPage : function(){
		$("#add_layear").show();
        $('body').css('overflow', 'hidden');
	},
	saveHisinfo : function(){
		var req  = imRequest("ajax",{formId: "iMMmbrHstry"});
		req.cfg.type   = "json";
		req.cfg.asynchronous = true;
		req.cfg.url    =  "<c:url value="/user/mmbrHstry/updateLastMdfcn.do"/>";
		req.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) {
	        	$("#modifyTime").html(data.detail.mmbrHstry.lastMdfcnDtFormat);
	        	$("#modifyTimeMsg").html('업데이트 되었습니다.');
	        } else {
	        	COMMT.errorMessage();
	        }
	    };	
	    req.go();
	},
	openPotalAgncy : function(){
		var form =  document.getElementById('FormPortalOrg');
		form.elements["REQ"].value= "AJAX";
		form.elements["scWord"].value= "";
		$("#listPotalOrg").html(REQ.listPotalOrgHTML);
		$("#find_org").show();
	},
	openPotalAgncyMain : function(){
		var form =  document.getElementById('FormPortalOrg');
		form.elements["REQ"].value= "REQ";
		form.elements["scWord"].value= "";
		$("#listPotalOrg").html(REQ.listPotalOrgHTML);
		$("#find_org").show();
		
	},
	delPotalAgncyMain : function(){
		var form =  document.getElementById('iMMmbrHstry');
		form.elements["agncyNm"].value="";
		form.elements["agncyCode"].value="";
		
	},
	setOrg : function(key){
		var form =  document.getElementById('iMMmbrHstry');
		form.elements["agncyNm"].value=$("#_potal_name_"+key).html();
		form.elements["agncyCode"].value=key;
		$("#find_org").hide();
	},
	searchPotalOrg : function(){

		var req  = imRequest("ajax",{formId: "FormPortalOrg"});
		req.cfg.type        = "html";
		req.cfg.url    = "<c:url value="/user/epInstitution/selectList.do"/>";
		req.cfg.containerId = "listPotalOrg";
		req.cfg.fn.complete = function(act, data) {
			
        };
        req.validator.set({
	    	message : "검색어를 입력하세요.",
            name : "searchWord",
            data : ["!null"]
        });
        req.go();
        
	},
	unloadCancel : function(ty){
		$("#add_layear").hide();
		$("#find_org").hide();
		$("add_layear_cnt").html("");
		$('body').css('overflow', 'auto');
	},
	unloadPage : function(ty){
		REQ.unloadCancel(ty);
		
		var req = imRequest("ajax");
		req.cfg.formId = "FormReload";
		req.cfg.type        = "html";
		req.cfg.asynchronous = true; 
		req.cfg.fn.complete = function(act, data) {
			REQ.saveHisinfo();
        };
		if(ty=='iMMmbrExptSbj'){
			req.cfg.containerId = "selectListMmbrExptSbj";
			req.cfg.url    = "<c:url value="/user/mmbrExptSbj/selectList.do"/>";
		}else if(ty=='iMMmbrExptSbjOnline'){
			req.cfg.containerId = "selectListMmbrExptSbjOnline";
			req.cfg.url    = "<c:url value="/user/mmbrExptSbj/selectListOnline.do"/>";
		}else if(ty=='iMMmbrWorkHstry'){
			req.cfg.containerId = "selectListMmbrWorkHstry";
			req.cfg.url    = "<c:url value="/user/mmbrWorkHstry/selectList.do"/>";
			req.cfg.fn.complete = function(act, data) {
				AJAXLIST.init();
				REQ.saveHisinfo();
	        };
		}else if(ty=='iMMmbrEdu'){
			req.cfg.containerId = "selectListMmbrEdu";
			req.cfg.url    = "<c:url value="/user/mmbrEdu/selectList.do"/>";
		
		}else if(ty=='iMMmbrExp'){
			req.cfg.containerId = "selectListMmbrExp";
			req.cfg.url    = "<c:url value="/user/mmbrExp/selectList.do"/>";
			
		}else if(ty=='iMMmbrEtc'){
				req.cfg.containerId = "selectListMmbrEtc";
				req.cfg.url    = "<c:url value="/user/mmbrEtc/selectList.do"/>";
		}
		
        req.go();
	}
	

	<c:if test="${adminMode eq 'Y'}">
	,excel : function(){
		var req = imRequest();
		req.cfg.formId = "FormExcel";
		req.cfg.url    = "<c:url value="/mng/mmbrHstry/selectAplyUserViewHistoryExcel.do"/>";
		req.go();
	}
	</c:if>
}


$(document).ready(function(){
	REQ.init();
});


var doSetMobile=function(obj){

    var form =  document.getElementById('iMMmbrHstry');
	form.elements["mmbrNm"].value= obj.mmbrNm;
	form.elements["mblTelno"].value= obj.mblTelno;
	form.elements["brdt"].value= obj.brdt;
	form.elements["di"].value= obj.di;
	form.elements["ci"].value= obj.ci;
	var req = imRequest();
	req.cfg.formId = "iMMmbrHstry";
	req.cfg.url    = "<c:url value="/user/mmbrHstry/modify.do"/>";
	req.go();
}

</script>

<script language='javascript'>
	window.name ="Parent_window";
	
	function fnPopup(){
		window.open('', 'popupChk', 'width=500, height=550, top=100, left=100, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no');
		document.form_chk.action = "https://nice.checkplus.co.kr/CheckPlusSafeModel/checkplus.cb";
		document.form_chk.target = "popupChk";
		document.form_chk.submit();
	}
	</script>
	
</head>
<body class="p_frm" oncontextmenu='return false'>

<%@ include file="incMmberCode.jspf" %>

<form name="form_chk" method="post">
		<input type="hidden" name="m" value="checkplusService">						<!-- 필수 데이타로, 누락하시면 안됩니다. -->
		<input type="hidden" name="EncodeData" value="${sEncData}">		<!-- 위에서 업체정보를 암호화 한 데이타입니다. -->
</form>
<form name="FormReload" id="FormReload" method="post" onsubmit="return false;">
<input type="hidden" name="_paramMenuNo" value="${nowMenuNo}"/>
<input type="hidden" name="mmbrExptSbjId" />
<input type="hidden" name="mmbrWorkHstryId" />
<input type="hidden" name="mmbrEduId" />
<input type="hidden" name="mmbrExpId" />
<input type="hidden" name="mmbrEtcId" />
<input type="hidden" name="exempSbjDvsn" />

<input type="hidden" name="retureUrlVal" value="${param['retureUrlVal']}" />
<input type="hidden" name="memberSrl" value="${detail.mmbrHstry.memberSrl}"/>
</form>
<div class="page_frame cr_manage">
	<div class="frm_top">
		<div class="inner">
			<div class="head">
				<h1>이력관리</h1>
				<c:if test="${saveMode eq 'U'}"> 
				<div class="noti">
					<p class="desc">
						<i class="m_icon_out circle_notifications"></i>
						<c:if test="${empty detail.mmbrHstry.lastMdfcnDt}">
						<span class="time" id="modifyTime">
						<im:dt yyyyMMddHHmmss="${detail.mmbrHstry.frstRegDt}" pattern="yyyy.MM.dd HH:mm:ss"/>
						</span>
						</c:if>
						<c:if test="${!empty detail.mmbrHstry.lastMdfcnDt}">
						<span class="time" id="modifyTime">
						<im:dt yyyyMMddHHmmss="${detail.mmbrHstry.lastMdfcnDt}" pattern="yyyy.MM.dd HH:mm:ss"/>
						</span>
						</c:if>
						<span  id="modifyTimeMsg">
						<c:if test="${detail.mmbrHstry.diffDay < 0}">
						업데이트(최근 ${detail.mmbrHstry.diffDay*-1}일) 되었습니다.
						</c:if>
						<c:if test="${detail.mmbrHstry.diffDay eq 0}">
						 업데이트 되었습니다.
						</c:if>
						</span>
					</p>
				</div>
				</c:if>
			</div>
			<div class="nav">
				<div class="role">
					<a href="#role_pop" title="이력관리 란?">
						<i class="m_icon more_vert"><span class="sr_only">정보</span></i>
						<div class="role_pop">
							<p class="lab">[이력관리 란?]</p>
							<ul class="item_li">
								<li>이력 관리는 수강 신청 및 필기 평가 원서 접수 후 <span class="c_green">수강 및 응시에 자격심사</span>에 필요한 서류들을 입력하는 화면입니다.</li>
								<li>정보 누락 및 허위 정보 기재로 인해 불이익 당하시는 일이 없도록 정확한 정보를 기재하여 주시기 바랍니다.</li>
							</ul>
						</div>
					</a>
					<a href="#" onclick="REQ.close();return false;" title="닫기" class="x" >
						<i class="m_icon clear"><span class="sr_only">닫기</span></i>
					</a>
				</div>
			</div>
		</div>
	</div>
	<div class="frm_ct">
		<!-- 콘텐츠 -->
		<p class="bl_title">기본정보 (필수입력)  </p>
		<form name="iMMmbrHstry" id="iMMmbrHstry" method="post" onsubmit="return false;">
		<input type="hidden" name="memberSrl" value="${detail.mmbrHstry.memberSrl}"/>
		<input type="hidden" name="esntlId" value="${detail.mmbrHstry.esntlId}"/>
		<input type="hidden" name="ci" value="${detail.mmbrHstry.ci}"/>
		<input type="hidden" name="di" value="${detail.mmbrHstry.di}"/>
		<input type="hidden" name="mblTelno" />
		
		<table class="tbl_row al">
			<colgroup>
				<col style="width:16%;">
				<col>
				<col style="width:16%;">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th scope="row">이름</th>
					<td>${detail.mmbrHstry.mmbrNm}
					<input type="hidden" name="mmbrNm" value="${detail.mmbrHstry.mmbrNm}"/>
					</td>
					<th scope="row">생년월일</th>
					<td>
						<div class="c_date">${detail.mmbrHstry.brdt}
							<input type="hidden" name="brdt" value="${detail.mmbrHstry.brdt}"/>
						</div>
					</td>
					<%--
					<th scope="row">성별</th>
					<td>
						<im:cd codeId="${sexdstnCdvArr}" type="radio" name="sexdstnCdv" selectedCode="${detail.mmbrHstry.sexdstnCdv}" />
					</td>
					 --%>
				</tr>
				<tr>
					<th scope="row">구분</th>
					<td>
					<im:cd codeId="${workDvsnArr}" type="radio" onclick="REQ.validator(this.value);" name="workDvsnCdv" selectedCode="${detail.mmbrHstry.workDvsnCdv}" />
					</td>
					<th scope="row">현재 재직기관</th>
					<td>
						<div class="search_area">
							<input type="text" name="agncyNm" value="${detail.mmbrHstry.agncyNm}" placeholder="재직기관을 선택하여 주세요" title="기관명" readonly="readonly">
							<input type="hidden" name="agncyCode" value="${detail.mmbrHstry.agncyCode}">
							<a href="javascript:;" onclick="REQ.delPotalAgncyMain()"  class="del" title="삭제"><span class="sr_only">삭제</span></a>
						</div>									
						<a href="javascript:;" onclick="REQ.openPotalAgncyMain()" class="c_btn bk">찾기</a>
						
					</td>
				</tr>
				<tr>
					<th scope="row">지역</th>
					<td>
						<select name="areaCdv"  title="지역">
						<option value="">선택</option>
						<im:cd type="option" codeId="IM0007" selectedCode="${detail.mmbrHstry.areaCdv}"/>
						</select>
					</td>
					<th scope="row">세부지역</th>
					<td>
						<input type="text"  name="areaDtl" value="${detail.mmbrHstry.areaDtl}" class="wide" placeholder="구/시, 군을 입력하세요" title="세부지역">
					</td>
				</tr>
				<tr>
					<th scope="row">이메일</th>
					<td>
					<input type="text" name="eml"  value="${detail.mmbrHstry.eml}"/>
					</td>
					<th scope="row">휴대전화번호</th>
					<td>
					<input type="text" name="mblTelno1"  size="3" maxlength="3" value="${detail.mmbrHstry.mblTelno1}"/>-
					<input type="text" name="mblTelno2"  size="4" maxlength="4" value="${detail.mmbrHstry.mblTelno2}" />-
					<input type="text" name="mblTelno3"  size="4" maxlength="4" value="${detail.mmbrHstry.mblTelno3}" />
					</td>
				</tr>
				<tr>
					<th scope="row">신청희망 자격급수</th>
					<td colspan="3">
						<im:cd type="radio" codeId="IM0001" name="aplyGrdCdv"  selectedCode="${detail.mmbrHstry.aplyGrdCdv}"/>
						<input type="hidden" name="exptSbjYn" value="M"/>
					</td>
				</tr>
			</tbody>
		</table>
		</form>
		
		<c:if test="${saveMode eq 'U'}">
		<div class="ct_box">
			<ul class="ref red">
<!-- 				<li>필수 이력정보를 추가하셔야 수강신청 가능합니다.</li> -->
				<li>아래의 이력정보를 추가하여 주시기 바랍니다.</li>
			</ul>
		</div>
		<div class="b_box">
			<a href="javascript:;" onclick="REQ.save1()"  class="c_btn md green">저장</a>
		</div>
		</c:if>
		<c:if test="${saveMode ne 'U'}">
		<div class="hs_no_data">
			<p class="note">입력된 기본정보가 없습니다.</p>
			<ul class="item_li">
				<li>먼저 기본정보를 입력하셔야 <span class="c_green">이력 관리 항목을 등록</span>하실 수 있습니다.</li>
			</ul>
			<p class="lab top">[이력관리 란?]</p>
			<ul class="item_li">
				<li>이력 관리는 수강 신청 및 필기 평가 원서 접수 후 <span class="c_bk" >수강 및 응시 자격심사</span>에 필요한 서류들을 입력하는 화면으로 기본 정보를 저장 하시면 하단에 입력할 수 있는 항목들이 보여 집니다.</li>
				<li>정보 누락 및 허위 정보 기재로 인해 불이익 당하시는 일이 없도록 정확한 정보를 기재하여 주시기 바랍니다.</li>
			</ul>
		</div>
		
		<div class="b_box">
			<c:if test="${empty detail.mmbrHstry.di}">
			<a href="javascript:;" onclick="fnPopup()"  class="c_btn md green">휴대폰 본인인증</a>
			</c:if>
			<c:if test="${!empty detail.mmbrHstry.di}">
			<a href="javascript:;" onclick="REQ.save1()"  class="c_btn md green">저장</a>
			</c:if>
		</div>
		</c:if>
	
	<c:if test="${saveMode eq 'U'}">  <%--최초 등록시 기본 정보를 등록해야 추가 정보를 등록 할 수 있음  --%>
		
		<div class="cb_bar top">
<!-- 			<p class="bl_title">환경교육사 자격 취득이력</p> -->
			<p class="bl_title">환경교육사 (구 사회환경 교육지도사) 자격취득이력</p>
		</div>
		<div class="tbl_rps_data">
			<table class="tbl_data">
				<caption>자격 취득이력</caption>
				<colgroup>
					<col style="width:17%;">
					<col>
					<col style="width:17%;">
					<col style="width:17%;">
					<col style="width:17%;">
				</colgroup>
				<thead>
					<tr>
						<th scope="col">취득시기</th>
						<th scope="col">자격번호</th>						
						<th scope="col">취득등급</th>
						<th scope="col">자격취득일</th>
						<th scope="col">보수교육 유효기간</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listMmbrQlfc}" var="row">
					<tr>
						<td>
							<div class="th">취득시기</div>
							<div class="td"><im:cd type="print" codeId="${dvsnCdvArr}" selectedCode="${row.dvsnCdv}"/></div>
						</td>
						<td>
							<div class="th">자격번호</div>
							<div class="td">${row.qlfcRsltCode}</div>
						</td>
						<td>
							<div class="th">취득등급</div>
							<div class="td">
							<im:cd type="print" name="crsGrdCdv" selectedCode="${row.crsGrdCdv}" codeId="IM0001"/>
							</div>
						</td>
						<td>
							<div class="th">자격취득일</div>
							<div class="td"><im:dt yyyyMMddHHmmss="${row.lcncAcqsYmd}" pattern="yyyy.MM.dd"/></div>
						</td>
						<td>
							<div class="th">유효기간</div>
							<div class="td"><im:dt yyyyMMddHHmmss="${row.lcncEndYmd}" pattern="yyyy.MM.dd"/></div>
						</td>
					</tr>
					</c:forEach>
					<c:if test="${empty listMmbrQlfc}">
					<tr>
						<td colspan="5"><div class="no_info">환경교육사 자격 취득이력이 없습니다.</div></td>
					</tr>
					</c:if>
				</tbody>
			</table>
		</div>
		
		
		<div class="cb_bar top">
			<p class="bl_title">교육수료이력</p>
		</div>
		<div class="tbl_rps_data">
			<table class="tbl_data">
				<caption> 교육수료이력</caption>
				<colgroup>
					<col style="width:16%;">
					<col style="width:16%;">
					<col>
					<col>
					<col style="width:16%;">
					<col style="width:16%;">
				</colgroup>
				<thead>
					<tr>
						<th scope="col">취득시기</th>
						<th scope="col">수료번호</th>
						<th scope="col">과정명</th>
						<th scope="col">교육수료 기관</th>
						<th scope="col">수료등급</th>
						<th scope="col">수료일</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${selectEduHis01}" var="row">
					<tr>
						<td>
							<div class="th">취득시기</div>
							<div class="td"><im:cd type="print" codeId="${dvsnCdvArr}" selectedCode="01"/></div>
						</td>
						<td>
							<div class="th">수료번호</div>
							<div class="td">${row.eduAplyHstry.lcncCode}</div>
						</td>
						<td>
							<div class="th">과정명</div>
							<div class="td">${row.eduAplyHstry.crsNm}</div>
						</td>
						<td>
							<div class="th">교육수료 기관</div>
							<div class="td"><c:out value="${row.eduAplyHstry.agncyNm}"/></div>
						</td>
						<td>
							<div class="th">수료등급</div>
							<div class="td"><c:out value="${row.eduAplyHstry.acqsGrd}"/></div>
						</td>
						<td>
							<div class="th">수료일</div>
							<div class="td"><c:out value="${row.eduAplyHstry.qlfcAcqsYmdFormat}"/></div>
						</td>
					</tr>
					</c:forEach>
					<c:forEach items="${selectEduHis02}" var="row">
					<tr>
						<td>
							<div class="th">취득시기</div>
							<div class="td"><im:cd type="print" codeId="${dvsnCdvArr}" selectedCode="02"/></div>
						</td>
						<td>
							<div class="th">수료번호</div>
							<div class="td"><c:out value="${row.crsAplcnt.fnshRsltCode}"/></div>
						</td>
						<td>
							<div class="th">과정명</div>
							<div class="td"><im:cd type="print" codeId="IM0001" selectedCode="${row.crsMstr.crsGrdCdv}"/>&nbsp;<im:cd type="print" codeId="IM0002" selectedCode="${row.crsMstr.crsDvsnCdv}"/></div>
						</td>
						<td>
							<div class="th">교육수료 기관</div>
							<div class="td"><c:out value="${row.agncy.agncyNm}"/></div>
						</td>
						<td>
							<div class="th">수료등급</div>
							<div class="td"><im:cd type="print" codeId="IM0001" selectedCode="${row.crsMstr.crsGrdCdv}"/></div>
						</td>
						<td>
							<div class="th">수료일</div>
							<div class="td"><im:dt yyyyMMddHHmmss="${row.crsAplcnt.fnshYmd}"/></div>
						</td>
					</tr>
					</c:forEach>
					<c:if test="${empty selectEduHis01 && empty selectEduHis02}">
					<tr>
						<td colspan="6"><div class="no_info">교육수료이력 정보가 없습니다.</div></td>
					</tr>
					</c:if>
				</tbody>
			</table>
		</div>
		
		
		
		<div class="cb_bar top">
			<p class="bl_title">면제과목 (해당 시 입력) </p>
			<div class="add">
				<a href="#major_subject" class="c_btn bk">유사전공 인정과목 확인</a>
				<a href="#" onclick="REQ.addMmbrExptSbj()" class="c_btn green">추가</a>
			</div>
		</div>
		<div class="tbl_rps_data" id="selectListMmbrExptSbj">
			<%@ include file="selectListMmbrExptSbj.jsp" %>
		</div>
		
		
		<div class="cb_bar top">
			<p class="bl_title">단짝 수료과목 (해당 시 입력) </p>
			<div class="add">
				<a href="#major_subject2" class="c_btn bk">단짝 수료 인정과목 확인</a>
				<a href="javascript:;" onclick="REQ.addMmbrExptSbjOnline()" class="c_btn green">추가</a>
			</div>
		</div>
		<div class="tbl_rps_data" id="selectListMmbrExptSbjOnline">
			<%@ include file="selectListMmbrExptSbjOnline.jsp" %>
		</div>
		
		
		
		
		<div class="cb_bar top">
			<p class="bl_title">직장 (해당 시 입력)
			<span  id="c_cyan_id">
			<c:if test="${!empty selectWorkHstryDiffSum}">
			 :  
			<span class="c_cyan">
			총 <im:numberFormat value="${selectWorkHstryDiffSum.diffYear}" pattern="##"/>년 <im:numberFormat value="${selectWorkHstryDiffSum.diffMonth}" pattern="##"/>개월
			</span>
			</c:if>
			</span>
			</p>
			<div class="add">
				<a href="javascript:;" onclick="REQ.addMmbrWorkHstry()" class="c_btn green">추가</a>
			</div>
		</div>
		<div class="tbl_rps_data" id="selectListMmbrWorkHstry">
			<%@ include file="selectListMmbrWorkHstry.jsp" %>
		</div>
		<div class="cb_bar top">
			<p class="bl_title">학력사항 (해당 시 입력)</p>
			<div class="add">
				<a href="javascript:;" onclick="REQ.addMmbrEdu()" class="c_btn green">추가</a>
			</div>
		</div>
		<div class="tbl_rps_data" id="selectListMmbrEdu">
			<%@ include file="selectListMmbrEdu.jsp" %>
		</div>
<!-- 		<div class="ct_box"> -->
<!-- 			<ul class="ref orange"> -->
<!-- 				<li>환경관련 학사학위 증명서류(해당자에 한함)</li> -->
<!-- 			</ul> -->
<!-- 		</div> -->
		<div class="cb_bar top">
			<p class="bl_title">환경교육 경력사항 (해당 시 입력)</p>
			<div class="add">
				<a href="javascript:;" onclick="REQ.addMmbrExp()" class="c_btn green">추가</a>
			</div>
		</div>
		<div class="tbl_rps_data" id="selectListMmbrExp">
			<%@ include file="selectListMmbrExp.jsp" %>
			
		</div>
		<div class="ct_box">
			<ul class="ref orange">
<!-- 				<li>3급(정규)과정 신청 시, 환경교육 경력사항은 필수 아님(시스템 상 등록을 위해 기타 파일 첨부 요청)</li> -->
				<li>사회환경교육기관 경력의 경우, 정관 필수 첨부</li>
			</ul>
		</div>
		<div class="cb_bar top">
			<p class="bl_title">기타사항</p>
			<div class="add">
				<a href="javascript:;" onclick="REQ.addMmbrEtc()" class="c_btn green">추가</a>
			</div>
		</div>
		<div class="tbl_rps_data" id="selectListMmbrEtc">
			<%@ include file="selectListMmbrEtc.jsp" %>
			
		</div>
		<div class="ct_box">
			<ul class="ref orange">
				<li>인턴 지원자의 경우 추가제출서류(응시원서, 자기소개서, 개인정보활용동의서 등) 필수 첨부</li>
			</ul>
		</div>
		<c:if test="${adminMode eq 'Y'}">
		<div class="cb_bar top">
			<p class="bl_title">자격등급 검토 이력</p>
			<div class="add">
				<a href="#" onclick="REQ.excel();return false;" class="c_btn bk">상세엑셀다운로드</a>
			</div>
		</div>
		<div class="fx_box sm">
			<table class="tbl_row2 mo_sm">
				<colgroup>
					<col style="width:10%;">
					<col>
					<col style="width:15%;">
					<col style="width:20%;">
					<col style="width:15%;">
					<col style="width:20%;">
				</colgroup>
				<thead>
					<tr>
						<th scope="col" rowspan="2">검토 등급</th>
						<th scope="col" rowspan="2">과정 및 시험</th>
						<th scope="col" colspan="2">양성기관</th>
						<th scope="col" colspan="2">운영사무국</th>
					</tr>
					<tr>
						<th scope="col">검토결과</th>
						<th scope="col">최종검토일</th>
						<th scope="col">검토결과</th>
						<th scope="col">최종검토일</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${selectAplyUserViewHistory}" var="row">
					<tr>
						<td><im:cd type="print" codeId="IM0001" selectedCode="${row.crsGrdCdv}"/></td>
						<td>
						<im:cd type="print" codeId="IM0011" selectedCode="${row.crsDvsnCdv}"/></td>
						<td>
						<im:cd type="print" codeId="IM0017" selectedCode="${row.agncySrngCdv}"/>
						</td>
						<td>
						${row.agncySrngDtFomat}
						</td>
						<td>
						<im:cd type="print" codeId="IM0017" selectedCode="${row.opsectSrngCdv}"/>
						</td>
						<td>
						${row.opsectSrngDtFomat}
						</td>
					</tr>
					</c:forEach>
					<c:if test="${empty selectAplyUserViewHistory}">
					<tr>
						<td colspan="6">
						<div class="no_info">등록된 정보가 없습니다.</div>
						</td>
					</tr>
					</c:if>
				</tbody>
			</table>
		</div>
		
<form name="FormExcel" id="FormExcel" method="post" onsubmit="return false;">
<input type="hidden" name="mmbrEsntlId" value="${detail.mmbrHstry.esntlId}"/>
<input type="hidden" name="downloadName" value="자격등급 검토 이력_${detail.mmbrHstry.mmbrNm}"/>
</form>
		
		</c:if>
		<!-- <div class="ct_box">
			<ul class="ref orange">
				<li>교육생이 신청한 희망급수에 대한 자격 검토 결과 이력을 관리합니다.</li>
			</ul>
		</div> -->
<!-- 		<div class="ct_box"> -->
<!-- 			<ul class="ref orange"> -->
<!-- 				<li>자격 검토는 교육신청후 진행됩니다.</li> -->
<!-- 				<li>미승인인 경우에는 이력관리 현황을 확인하시고 정보를 수정하신 후 교육신청을 하시기 바랍니다.</li> -->
<!-- 			</ul> -->
<!-- 		</div> -->
		<!-- //콘텐츠 -->
	</c:if> 
	</div>
</div>


<div class="ct_dialog" id="add_layear"  style="display:none;">
	<div class="tb">
		<div class="inner">
			<div class="outer" style="max-width:640px;"><!-- max-width 값 필수 -->
				<div class="top">
					<div class="bowl">
						<p class="title"  id="add_layear_title"></p>
						<a href="#" class="p_close"><span class="sr_only">닫기</span></a>
					</div>
				</div>
				<div class="ct" id="add_layear_cnt">
				</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/jsp/inc/imPotalOrg.jspf" %>

<div class="ct_dialog" id="major_subject" style="display:none;">
	<div class="tb">
		<div class="inner">
			<div class="outer" style="max-width:640px;"><!-- max-width 값 필수 -->
				<div class="top">
					<div class="bowl">
						<p class="title">유사전공 인정과목</p>
						<a href="#" class="p_close"><span class="sr_only">닫기</span></a>
					</div>
				</div>
				<div class="ct">
					<!-- 콘텐츠 -->
					<table class="tbl_data">
						<caption>유사전공 인정과목</caption>
						<colgroup>
							<col style="width:22%;">
							<col>
							<col style="width:20%;">
						</colgroup>
						<thead>
							<tr>
								<th scope="col">과목계열</th>
								<th scope="col">인정과목</th>
								<th scope="col">과목수</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>환경과 철학</td>
								<td class="al">환경학개론, 환경철학, 환경보호론, 환경사회학 등</td>
								<td>1과목</td>
							</tr>
							<tr>
								<td>환경교육론</td>
								<td class="al">환경교육론, 지속가능발전교육론 등</td>
								<td>1과목</td>
							</tr>
							<tr>
								<td>환경생태학</td>
								<td class="al">환경생태학, 생물환경론 등</td>
								<td>1과목</td>
							</tr>
							<tr>
								<td>생활환경문제와<br>환경보건</td>
								<td class="al">수질환경(학), 수질오염(론), 토양환경(학), 토양오염(론), 대기환경(학), 대기오염(론) 환경과 에머지, 자원과 에너지, 환경화학, 환경일반화학, 소음진동(학), 자원과 폐기물관리(학), 자원순환형 폐기물관리(론), 환경보건(학), 환경과 건강, 환경위생 및 보건 등</td>
								<td>2과목</td>
							</tr>
							<tr>
								<td>기후위기와<br>지구환경문제</td>
								<td class="al">지구환경론, 환경지리학 등</td>
								<td>1과목</td>
							</tr>
						</tbody>
					</table>
					<div class="b_box">
						<a href="#major_subject"  class="c_btn wt">닫기</a>
					</div>
					<!-- //콘텐츠 -->
				</div>
			</div>
		</div>
	</div>
</div>

<div class="ct_dialog" id="major_subject2" style="display:none;">
	<div class="tb">
		<div class="inner">
			<div class="outer" style="max-width:640px;"><!-- max-width 값 필수 -->
				<div class="top">
					<div class="bowl">
						<p class="title">단짝수료 인정과목</p>
						<a href="#" class="p_close"><span class="sr_only">닫기</span></a>
					</div>
				</div>
				<div class="ct">
					<!-- 콘텐츠 -->
					<table class="tbl_data">
						<caption>유사전공 인정과목</caption>
						<colgroup>
							<col style="width:22%;">
							<col>
							<col style="width:20%;">
						</colgroup>
						<thead>
							<tr>
								<th scope="col">과목계열</th>
								<th scope="col">인정과목</th>
								<th scope="col">과목수</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>환경과 철학</td>
								<td class="al">환경학개론, 환경철학, 환경보호론, 환경사회학 등</td>
								<td>1과목</td>
							</tr>
							<tr>
								<td>환경교육론</td>
								<td class="al">환경교육론, 지속가능발전교육론 등</td>
								<td>1과목</td>
							</tr>
							<tr>
								<td>환경생태학</td>
								<td class="al">환경생태학, 생물환경론 등</td>
								<td>1과목</td>
							</tr>
							<tr>
								<td>생활환경문제와<br>환경보건</td>
								<td class="al">수질환경(학), 수질오염(론), 토양환경(학), 토양오염(론), 대기환경(학), 대기오염(론) 환경과 에머지, 자원과 에너지, 환경화학, 환경일반화학, 소음진동(학), 자원과 폐기물관리(학), 자원순환형 폐기물관리(론), 환경보건(학), 환경과 건강, 환경위생 및 보건 등</td>
								<td>2과목</td>
							</tr>
							<tr>
								<td>기후위기와<br>지구환경문제</td>
								<td class="al">지구환경론, 환경지리학 등</td>
								<td>1과목</td>
							</tr>
						</tbody>
					</table>
					<div class="b_box">
						<a href="#major_subject2"  class="c_btn wt">닫기</a>
					</div>
					<!-- //콘텐츠 -->
				</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/jsp/inc/imUserTimer.jspf" %>

<c:if test="${empty detail.mmbrHstry.di}">
<!-- 20220502 휴대폰본인인증 추가-->
<div class="ct_dialog" id="add_career" style="display:block;">
	<div class="tb">
		<div class="inner">
			<div class="outer" style="max-width:600px;"><!-- max-width 값 필수 -->
				<div class="top">
					<div class="bowl">
						<p class="title">휴대폰 본인인증</p>
						<a href="#" class="p_close"><span class="sr_only">닫기</span></a>
					</div>
				</div>
				<div class="ct">
					<!-- 콘텐츠 -->
					<ul class="item_li bot">
						<li>이력관리의 기본 정보 확인과 이전 교육이력 확인을 위해
							본인인증이 필요 합니다.
						</li>
					</ul>
					<div class="text-md-center ac">
						<img src="${imWebStatic}/common/images/www/icon/ic_mobile.png" alt="핸드폰본인인증">
						<p class="mt">${IMLoginUser.name}님 본인명의로 등록된 휴대폰으로 인증하여주세요.</p>
					</div>
					<div class="b_box">
						<a href="#" onclick="fnPopup();return false;"  class="c_btn md green">휴대폰 본인인증</a>
					</div>
					<!-- //콘텐츠 -->
				</div>
			</div>
		</div>
	</div>
</div>
<!-- //휴대폰본인인증 -->
</c:if>

</body>
</html>