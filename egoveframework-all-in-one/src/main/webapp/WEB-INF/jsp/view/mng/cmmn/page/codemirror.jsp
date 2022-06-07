<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>
<script src="<c:out value="${imWebStatic}"/>/3rdparty/codemirror-5.65.5/lib/codemirror.js"></script>
<script src="<c:out value="${imWebStatic}"/>/3rdparty/codemirror-5.65.5/mode/htmlmixed/htmlmixed.js"></script>
<script src="<c:out value="${imWebStatic}"/>/3rdparty/codemirror-5.65.5/mode/javascript/javascript.js"></script>
<script src="<c:out value="${imWebStatic}"/>/3rdparty/codemirror-5.65.5/mode/xml/xml.js"></script>
<script src="<c:out value="${imWebStatic}"/>/3rdparty/codemirror-5.65.5/mode/css/css.js"></script>
<script src="<c:out value="${imWebStatic}"/>/3rdparty/codemirror-5.65.5/mode/sql/sql.js"></script>
<script src="<c:out value="${imWebStatic}"/>/3rdparty/codemirror-5.65.5/mode/clike/clike.js"></script>
<script src="<c:out value="${imWebStatic}"/>/3rdparty/codemirror-5.65.5/addon/selection/active-line.js"></script>
<script src="<c:out value="${imWebStatic}"/>/3rdparty/codemirror-5.65.5/addon/edit/matchbrackets.js"></script>
<script src="<c:out value="${imWebStatic}"/>/3rdparty/codemirror-5.65.5/addon/display/fullscreen.js"></script>

<link rel="stylesheet" type="text/css" href="<c:out value="${imWebStatic}"/>/3rdparty/codemirror-5.65.5/lib/codemirror.css">
<link rel="stylesheet" href="<c:out value="${imWebStatic}"/>/3rdparty/codemirror-5.65.5/theme/eclipse.css">
<link rel="stylesheet" href="<c:out value="${imWebStatic}"/>/3rdparty/codemirror-5.65.5/theme/midnight.css">
<link rel="stylesheet" href="<c:out value="${imWebStatic}"/>/3rdparty/codemirror-5.65.5/theme/solarized.css">
<link rel="stylesheet" href="<c:out value="${imWebStatic}"/>/3rdparty/codemirror-5.65.5/theme/material-darker.css">
<link rel="stylesheet" href="<c:out value="${imWebStatic}"/>/3rdparty/codemirror-5.65.5/theme/mdn-like.css">
<link rel="stylesheet" href="<c:out value="${imWebStatic}"/>/3rdparty/codemirror-5.65.5/addon/display/fullscreen.css">


<script type="text/javascript" >


var REQ = {
	
	init : function(){
	
	},
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

    var $textareaObj = $("#codemirror-textarea");
	REQ.codeMirror($textareaObj);
});

</script>




  <textarea id="codemirror-textarea"></textarea>
			
