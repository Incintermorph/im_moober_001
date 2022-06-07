<%
 /**
  * @Class Name : EgovArticleRegist.jsp
  * @Description : EgovArticleRegist 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.02.01   박정규              최초 생성
  *   2016.06.13   김연호              표준프레임워크 v3.6 개선
  *   2018.06.05   신용호              CK Editor V4.9.2 Upgrade
  *
  *  @author 공통서비스팀 
  *  @since 2009.02.01
  *  @version 1.0
  *  @see
  *  
  */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>

<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" %> --%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<%-- <%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %> --%>
<%-- <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> --%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="ckeditor" uri="http://ckeditor.com" %>

<c:set var="pageTitle"><spring:message code="comCopBbs.articleVO.title"/></c:set>
<%-- <link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />"> --%>
<%-- <link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />"> --%>
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/utl/EgovCmmUtl.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/html/egovframework/com/cmm/utl/ckeditor/ckeditor.js?t=B37D54V'/>" ></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<validator:javascript formName="articleVO" staticJavascript="false" xhtml="true" cdata="false"/>


<c:set var="IMG_FILE_ID" value="pmtfile"/>


<script type="text/javascript">
var REQ = {
	imgInfo : {
		uploadFolder : 'bbsImg',
		fileInputId : '${IMG_FILE_ID}'
			
	},
	req : {
		list : null
	},
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageList";
		this.req.list.cfg.url    = "<c:url value="/mng/cop/bbs/selectArticleList.do"/>";
	    
	},
	list :  function(){
		this.req.list.go();
	}
}

$(document).ready(function(){
	REQ.init();
});

$(function() {
	$("#ntceBgnde").datepicker(   
	        {dateFormat:'yy-mm-dd' 
	         , showOn: 'button' 
	         , buttonImage: '<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif'/>'   
	         , buttonImageOnly: true 
	         
	         , showMonthAfterYear: true
	         , showOtherMonths: true
		     , selectOtherMonths: true
				
	         , changeMonth: true // 월선택 select box 표시 (기본은 false)
	         , changeYear: true  // 년선택 selectbox 표시 (기본은 false)
	         , showButtonPanel: true // 하단 today, done  버튼기능 추가 표시 (기본은 false)
	});
	$("#ntceEndde").datepicker(   
	        {dateFormat:'yy-mm-dd' 
	         , showOn: 'button' 
	         , buttonImage: '<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif'/>'   
	         , buttonImageOnly: true 
	         
	         , showMonthAfterYear: true
	         , showOtherMonths: true
		     , selectOtherMonths: true
				
	         , changeMonth: true // 월선택 select box 표시 (기본은 false)
	         , changeYear: true  // 년선택 selectbox 표시 (기본은 false)
	         , showButtonPanel: true // 하단 today, done  버튼기능 추가 표시 (기본은 false)
	});
});

/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init(){
	
	//filebrowserUploadUrl: '${pageContext.request.contextPath}/utl/wed/insertImage.do', // 파일 업로드를 처리 할 경로 설정.
	var ckeditor_config = {
		//filebrowserImageUploadUrl: '${pageContext.request.contextPath}/utl/wed/insertImageCk.do', // 파일 업로드를 처리 할 경로 설정.
		filebrowserImageUploadUrl: '${pageContext.request.contextPath}/ckUploadImage', // 파일 업로드를 처리 할 경로 설정(CK필터).
	};
	CKEDITOR.replace('nttCn',ckeditor_config);
	
	// 첫 입력란에 포커스
	document.getElementById("articleVO").nttSj.focus();
	

	}
	/* ********************************************************
	 * 저장처리화면
	 ******************************************************** */
	function fn_egov_regist_articleSave() {
		var validateForm = document.getElementById("articleVO");
		validateForm.submit();
	}
	function fn_egov_regist_article(form) {
		CKEDITOR.instances.nttCn.updateElement();

		//input item Client-Side validate
		if (!validateArticleVO(form)) {
			return false;
		} else {
			
			
			
			var validateForm = document.getElementById("articleVO");

			//비밀글은 제목 진하게 할 수 없음.
			//비밀글은 익명게시 불가.
			//비밀글은 공지게시 불가.
			if (validateForm.secretAt.checked) {
				if (validateForm.sjBoldAt.checked) {
					alert("<spring:message code="comCopBbs.articleVO.secretBold" />");
					return;
				}
				if (validateForm.anonymousAt.checked) {
					alert("<spring:message code="comCopBbs.articleVO.secretAnonymous" />");
					return;
				}
				if (validateForm.noticeAt.checked) {
					alert("<spring:message code="comCopBbs.articleVO.secretNotice" />");
					return;
				}
			}

			//익명글은 공지게시 불가.
			if (validateForm.anonymousAt.checked) {
				if (validateForm.noticeAt.checked) {
					alert("<spring:message code="comCopBbs.articleVO.anonymousNotice" />");
					return;
				}
			}

			//게시기간 
			var ntceBgnde = getRemoveFormat(validateForm.ntceBgnde.value);
			var ntceEndde = getRemoveFormat(validateForm.ntceEndde.value);

			if (ntceBgnde == '' && ntceEndde != '') {
				validateForm.ntceBgnde.value = '1900-01-01';
			}
			if (ntceBgnde != '' && ntceEndde == '') {
				validateForm.ntceEndde.value = '9999-12-31';
			}
			if (ntceBgnde == '' && ntceEndde == '') {
				validateForm.ntceBgnde.value = '1900-01-01';
				validateForm.ntceEndde.value = '9999-12-31';
			}

			ntceBgnde = getRemoveFormat(validateForm.ntceBgnde.value);
			ntceEndde = getRemoveFormat(validateForm.ntceEndde.value);

			if (ntceBgnde > ntceEndde) {
				alert("<spring:message code="comCopBbs.articleVO.ntceDeError" />");
				return false;
			}
			if (confirm("<spring:message code="common.regist.msg" />")) {
				if(!COMMT.chkAttachFile('${IMG_FILE_ID}')){
					fn_egov_regist_articleSave();
				}else{
					COMMT.uploadImageFile(REQ.imgInfo,function(data){
		    			 if (data.result > 0) {
		    				 if(data.result==1){
		    					 //첨부파일 정보 성공인 경우 
			    				 var form =  document.getElementById('articleVO');
			    		    	 form.elements["phtFileId"].value = data.atchFileId;
			    		    	 
		    				 }
		    				 
		                   }
		    			 fn_egov_regist_articleSave();
		    		});
					
				}
				
			}
			return false;
		}
	}
	
$(document).ready(function() {
	fn_egov_init();
});
</script>


<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<%@ include file="incEgovArticleList.jsp" %>

<!-- 콘텐츠 -->
<form:form commandName="articleVO" action="${pageContext.request.contextPath}/cop/bbs/insertArticle.do" method="post" onSubmit="fn_egov_regist_article(document.forms[2]); return false;" enctype="multipart/form-data"> 
<!-- 상단타이틀 -->
<h3 class="ct_title">${pageTitle} <spring:message code="title.create" /></h3>
<table class="tbl_row" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.create" /></caption>
	<colgroup>
		<col style="width:200px;">
		<col>
	</colgroup>
	<tbody>
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
		<!-- 글 제목  -->
		<c:set var="title"><spring:message code="comCopBbs.articleVO.regist.nttSj"/> </c:set>	
		<tr>
			<th scope="row"><label for="nttSj">${title}<span class="c_red">*</span></label></th>
			<td>
				<form:input path="nttSj" title="${title} ${inputTxt }" size="70" maxlength="70" />
				<div><form:errors path="nttSj" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 글 제목 진하게 -->
		<c:set var="title"><spring:message code="comCopBbs.articleVO.regist.sjBoldAt"/> </c:set>
		<tr>
			<th scope="row"><label for="sjBoldAt">${title}</label></th>
			<td>
				<form:checkbox path="sjBoldAt" value="Y" />
				<div>	<form:errors path="sjBoldAt" cssClass="error" /></div>
			​</td>
		</tr>
		<!-- 양성기관 선택  (공지사항만출력 ) -->
		<c:if test="${boardMasterVO.bbsId eq 'BBSMSTR_000000000001'}">
		<tr>
			<th scope="row"><label for="agncyId"><spring:message code="comCopBbs.articleVO.regist.agncy"/></label></th>
			<td>
				<c:choose>
					<%--  관리자 일경우 --%>
					<c:when test="${boardMasterVO.authorCode eq 'ROLE_ADMIN'}">
						<select name="agncyId">
							<c:forEach items="${agncyList}" var="row">
								<option value="${row.agncy.agncyId}"<c:if test="${row.agncy.agncyId eq boardMasterVO.groupId}" ></c:if>>${row.agncy.agncyNm}</option>
							</c:forEach>
						</select>
					</c:when>
					<%-- 양성기관일경우 --%> 
					<c:when test="${boardMasterVO.authorCode eq 'ADMIN'}">
						<select name="agncyId">
							<c:forEach items="${agncyList}" var="row">
								<c:if test="${row.agncy.agncyId eq boardMasterVO.groupId}" ><option value="${row.agncy.agncyId}" selected>${row.agncy.agncyNm}</option></c:if>
							</c:forEach>
						</select>
					</c:when>
					<%--  둘다 아닐경우 양성기관 선택 불가 --%>
					<c:otherwise></c:otherwise>
				</c:choose>
			</td>
		</tr>
		</c:if>
		<!-- 글 내용  -->
		<c:set var="title"><spring:message code="comCopBbs.articleVO.regist.nttCn"/></c:set>
		<tr>
			<th scope="row"><label for="nttCn">${title}<span class="c_red">*</span></label></th>
			<td>
				<form:textarea path="nttCn" title="${title} ${inputTxt}" cols="300" rows="20" />   
                  <ckeditor:replace replace="nttCn" basePath="${pageContext.request.contextPath}/html/egovframework/com/cmm/utl/ckeditor/" />
				<div><form:errors path="nttCn" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 공지신청 여부  -->
		<c:set var="title"><spring:message code="comCopBbs.articleVO.regist.noticeAt"/></c:set>
		<tr>
			<th scope="row"><label for="noticeAt">${title}</label></th>
			<td>
				<form:checkbox path="noticeAt" value="Y"/>
				<div><form:errors path="noticeAt" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 비밀글 여부 현재 사용안함-->
 		<c:set var="title"><spring:message code="comCopBbs.articleVO.regist.secretAt"/></c:set> 
 		<tr style="display: none;">
 			<th scope="row"><label for="secretAt">${title}</label></th> 
 			<td> 
 				<form:checkbox path="secretAt" value="Y"/> 
 				<div><form:errors path="secretAt" cssClass="error" /></div> 
 			</td>
 		</tr> 
		<!-- 익명등록 여부  -->
		<c:set var="title"><spring:message code="comCopBbs.articleVO.regist.anonymousAt"/></c:set>
		<tr style="display: none;">
			<th scope="row"><label for="anonymousAt">${title}</label></th>
			<td>
				<form:checkbox path="anonymousAt" value="Y"/>
				<div><form:errors path="anonymousAt" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 유효기간 설정  -->
		<c:set var="title"><spring:message code="comCopBbs.articleVO.regist.ntceDe"/> </c:set>
		<tr style="display: none;">
			<th scope="row"><label for="ntceBgnde">${title}</label></th>
			<td>
				<form:input path="ntceBgnde" title="${title} ${inputTxt}" size="70" maxlength="70" style="width:100px;" readonly="true" />
				&nbsp;~&nbsp;<form:input path="ntceEndde" title="${title} ${inputTxt}" size="70" maxlength="70" style="width:100px;" readonly="true" />
				<div><form:errors path="ntceBgnde" cssClass="error" /></div>       
				<div><form:errors path="ntceEndde" cssClass="error" /></div>
			</td>
		</tr>
		<c:if test="${boardMasterVO.fileAtchPosblAt == 'Y'}">
		<!-- 첨부파일 -->
		<c:set var="title"><spring:message code="comCopBbs.articleVO.regist.atchFile"/></c:set>
		<tr>
			<th scope="row">${title}</th>
			<td>
				<input name="file_1" id="egovComFileUploader" type="file" title="<spring:message code="comCopBbs.articleVO.regist.atchFile"/>" multiple/><!-- 첨부파일 -->
			    <div id="egovComFileList"></div>
			</td>
		</tr>
		</c:if>
		<tr <c:if test="${boardMasterVO.bbsId ne 'BBSMSTR_000000000001'}"> style="display:none;"  </c:if>>
				<th scope="row">본문이미지</th>
				<td><form:hidden path="phtFileId"/>
				<input type="file" id="${IMG_FILE_ID}" value="파일찾기" name="${IMG_FILE_ID}" title="파일찾기" accept="${imExtensionsImages}" onchange="COMMT.previewImg(this, 'img_view', 0, 1000)">
				권장 이미지 넓이는 1000 px 입니다.
				<div id="img_view" style="width:auto;height:1000px;margin-bottom:10px;margin-left:10px;"></div>
				</td>
		</tr>
		<!-- <tr>
			<th scope="row">첨부 파일</th>
			<td>
				<input type="file">
				<ul class="file_li">
					<li>
						<a href="#" class="file">OOOOOOOO.xls</a>
						<a href="#" class="del" title="삭제"><span class="sr_only">삭제</span></a>
					</li>
					<li>
						<a href="#" class="file">OOOOOOOO.xls</a>
						<a href="#" class="del" title="삭제"><span class="sr_only">삭제</span></a>
					</li>
					<li>
						<a href="#" class="file">OOOOOOOO.xls</a>
						<a href="#" class="del" title="삭제"><span class="sr_only">삭제</span></a>
					</li>
				</ul>
				<p class="tb_note">용량 :1회 최대50MB, 5개 파일까지 올릴 수 있습니다.​</p>
			</td>
		</tr> -->
	</tbody>
</table>

<!-- 하단 버튼 -->
<div class="b_box right">
	<button type="submit" class="ad_btn green mid" title="<spring:message code="button.create" /> <spring:message code="input.button" />" /><spring:message code="button.create" /></button><!-- 등록 -->
<%-- 	<a href="<c:url value='/mng/cop/bbs/selectArticleList.do' />?bbsId=${boardMasterVO.bbsId}"  title="<spring:message code="button.list" /> <spring:message code="input.button" />" class="ad_btn bk mid"><spring:message code="button.list" /></a><!-- 목록 --> --%>
	<button type="button" onclick="REQ.list()" class="ad_btn bk mid"><spring:message code="button.list" /></button>
</div>

<input name="searchCnd" type="hidden" value="<c:out value="${searchVO.searchCnd}"/>">
<input name="searchWrd" type="hidden" value="<c:out value="${searchVO.searchWrd}"/>">
<input type="hidden" name="pageIndex"  value="<c:out value='${searchVO.pageIndex}'/>"/>
<input type="hidden" name="bbsTyCode" value="<c:out value='${boardMasterVO.bbsTyCode}'/>" />
<input type="hidden" name="replyPosblAt" value="<c:out value='${boardMasterVO.replyPosblAt}'/>" />
<input type="hidden" name="fileAtchPosblAt" value="<c:out value='${boardMasterVO.fileAtchPosblAt}'/>" />
<input type="hidden" id="atchPosblFileNumber" name="atchPosblFileNumber" value="<c:out value='${boardMasterVO.atchPosblFileNumber}'/>" />
<input type="hidden" name="atchPosblFileSize" value="<c:out value='${boardMasterVO.atchPosblFileSize}'/>" />
<input type="hidden" name="tmplatId" value="<c:out value='${boardMasterVO.tmplatId}'/>" />
<input type="hidden" name="blogId" value="<c:out value='${searchVO.blogId}'/>" />
<input type="hidden" name="blogAt" value="<c:out value='${articleVO.blogAt}'/>"/>
<input type="hidden" name="cmd" value="<c:out value='save'/>">
<input type="hidden" name="bbsId" value="<c:out value='${articleVO.bbsId}'/>">
<input type="hidden" name="forwardFlag" value="true"/>
<input type="hidden" name="_paramMenuNo" value="${nowMenuNo}"/>
</form:form>
<!-- //콘텐츠 -->

<!-- 첨부파일 업로드 가능화일 설정 Start..-->  
<script type="text/javascript">
var maxFileNum = document.getElementById('atchPosblFileNumber').value;
if(maxFileNum==null || maxFileNum==""){
	maxFileNum = 3;
}
var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), maxFileNum );
multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );
</script> 
<!-- 첨부파일 업로드 가능화일 설정 End.-->
