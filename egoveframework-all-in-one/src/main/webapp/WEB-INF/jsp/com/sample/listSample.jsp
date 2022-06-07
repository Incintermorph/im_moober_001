<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="egovframework.com.cmm.EgovWebUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:set  var="kendoBase" value="/egoveframework-all-in-one/js/kendoui.for.jquery.2021.3.1109.trial"/>
<c:set  var="editorBase" value="/egoveframework-all-in-one/js/editor"/>
    
  <link rel="stylesheet" href="<c:url value="/js/editor/toastui-editor.min.css"/>" />

   <h1>Hello ToastUI</h1>
	<div id="editor"></div>
	<button onclick="seeHtml()">html보기</button>
	<button onclick="seeMd()">markdown보기</button>
 
	<script src="<c:url value="/js/editor/toastui-editor-all.min.js"/>"></script>
	<script>
		const Editor = toastui.Editor;
	
		const editor = new Editor({
			  el: document.querySelector('#editor'),
			  height: '600px',
			  //initialEditType: 'markdown',
			  initialEditType: 'wysiwyg',
			  previewStyle: 'vertical'
			});
		
		seeHtml = function(){
			alert(editor.getHTML());
		}
		seeMd = function(){
			alert(editor.getMarkdown());
		}
	</script>
	

${detail.nameCard.searchCnd}<br>
${detail.nameCard.tmplatSeCodeNm}<br>
${detail.nameCard.emplyrId}<br>