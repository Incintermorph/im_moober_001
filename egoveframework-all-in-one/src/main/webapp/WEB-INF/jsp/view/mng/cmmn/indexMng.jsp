<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>

<script type="text/javascript" >


var REQ = {
	
	init : function(){
	
	},
	modifyInfo:function(){
		var req = imRequest("layer");
		req.cfg.url    = "<c:url value="/mng/ussMngr/modifyLayer.do"/>";
		req.cfg.options.title="개인정보수정";
		req.cfg.formId = "FormUser";
		req.cfg.options.scroll="hidden";
		req.cfg.options.width=700;
		req.cfg.options.height=410;
		 req.go();
			
	},
	modifyPassword:function(){
		var req = imRequest("layer");
		req.cfg.url    = "<c:url value="/mng/ussMngr/modify/passwordLayer.do"/>";
		req.cfg.options.title="<spring:message code="comUssUmt.userManageModifyBtn.passwordChange" />";
		req.cfg.formId = "FormUser";
		req.cfg.options.scroll="hidden";
		req.cfg.options.width=700;
		req.cfg.options.height=300;
		req.go();
	},
	removeMainCache:function(){
		
		var req = imRequest("ajax",{formId: "iMUssMngr"});
		req.cfg.type   = "json";
		req.cfg.url    =  "<c:url value="/mng/common/ehcache/frontMainPage/remove.do"/>";
		req.cfg.message.success="메인 캐시가 삭제 되었습니다.";
		req.cfg.fn.complete = function(act, data) {
	        
	    };	
	    req.go();
	    
	},
	removeMenuCache:function(){
		
		var req = imRequest("ajax",{formId: "iMUssMngr"});
		req.cfg.type   = "json";
		req.cfg.url    =  "<c:url value="/mng/common/ehcache/authMenu/remove.do"/>";
		req.cfg.message.success="메뉴 캐시가 삭제 되었습니다.";
		req.cfg.fn.complete = function(act, data) {
	        
	    };	
	    req.go();
	    
	} , 
	/**
     * 코드 에디터 - codemirror 5.65.5 버전 사용.
     */
    codeMirror : function($selector, options) {
        if ($selector.length == 0) {
            return;
        }
        var defaultOption = {
            mode : "htmlmixed", // "javascript", "application/xml", "text/html"
            theme : "eclipse",
            lineNumbers : true,
            styleActiveLine : true,
            matchBrackets : true,
            indentUnit : 4,
            tabSize : 4,
            indentWithTabs : true,
            smartIndent : false
        };
        options = options || {};
        options = $.extend(true, defaultOption, options);
        if (typeof options.fullscreen !== "boolean" || false !== options.fullscreen) {
            options.extraKeys = {
                "F11" : function(cm) {
                    cm.setOption("fullScreen", !cm.getOption("fullScreen"));
                },
                "Esc": function(cm) {
                    if (cm.getOption("fullScreen")) {
                        cm.setOption("fullScreen", false);
                    }
                }
            };
        }
        if (options.fullscreen) {
            delete options.fullscreen;
        }
        return CodeMirror.fromTextArea($selector.get(0), options);
    },
}


$(document).ready(function(){
	REQ.init();


});

</script>


	<div class="dash-head">
			<div>
				<a href="#" onclick="REQ.modifyInfo();return false;" class="ad_btn white mid"><i class="cil-pencil"></i>개인정보 수정</a>
				<a href="#" onclick="REQ.modifyPassword();return false;" class="ad_btn white mid"><i class="cil-user"></i>비밀번호 변경</a>
				<a href="#" onclick="REQ.removeMainCache();return false;" class="ad_btn white mid"><i class="cil-settings"></i>메인 캐시 지우기</a>
				<a href="#" onclick="REQ.removeMenuCache();return false;" class="ad_btn white mid"><i class="cil-settings"></i>메뉴 캐시 지우기</a>
			</div>
		</div>
			

<a href="<c:url value="/mng/page/codemirror/page.do"/>">codemirror</a>			<br>
<a href="<c:url value="/mng/page/study/page.do"/>">study</a>			<br>


<form name="FormUser" id="FormUser"  method="post">
<input type="hidden" name="esntlId"   value="${IMLoginUser.uniqId}"/>
<input type="hidden" name="emplyrId"   value="${IMLoginUser.id}"/>
</form>

