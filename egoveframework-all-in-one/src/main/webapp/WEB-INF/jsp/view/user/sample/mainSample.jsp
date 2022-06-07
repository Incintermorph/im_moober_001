<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="egovframework.com.cmm.EgovWebUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:set  var="kendoBase" value="/egoveframework-all-in-one/js/kendoui.for.jquery.2021.3.1109.trial"/>
<c:set  var="editorBase" value="/egoveframework-all-in-one/js/editor"/>

<!DOCTYPE html>
<html>
<head>
    <title>Popup editing</title>
    <meta charset="utf-8">
    
  <link rel="stylesheet" href="<c:url value="/js/editor/toastui-editor.min.css"/>" />
    
</head>
<body> 

${detail.nameCard.searchCnd}<br>
${detail.nameCard.tmplatSeCodeNm}<br>
${detail.nameCard.emplyrId}<br>
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
	
	
	
	
	<input type="file" id="file-input" />
<button id="upload-button">Upload File</button>

<script>
	document.querySelector('#upload-button').addEventListener('click', function() {
		if(document.querySelector('#file-input').files.length == 0) {
			alert('Error : No file selected');
			return;
		}

		let file = document.querySelector('#file-input').files[0];
		let allowed_mime_types = [ 'image/jpeg', 'image/png' ];
		let allowed_size_mb = 2;
	
		if(allowed_mime_types.indexOf(file.type) == -1) {
			alert('Error : Incorrect file type');
			return;
		}

		if(file.size > allowed_size_mb*1024*1024) {
			alert('Error : Exceeded size');
			return;
		}

		let data = new FormData();
		data.append('file', document.querySelector('#file-input').files[0]);

		let request = new XMLHttpRequest();
		request.open('POST', 'upload.php'); 
		request.addEventListener('load', function(e) {
			console.log(request.response);
		});
		request.send(data);
	});
</script>
</body>
</html>
