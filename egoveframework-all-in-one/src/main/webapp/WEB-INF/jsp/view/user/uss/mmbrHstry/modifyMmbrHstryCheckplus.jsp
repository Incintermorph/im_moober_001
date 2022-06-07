<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ko">
<head>

<script type="text/javaScript" language="javascript">
<c:if test="${errorType ne '00'}">

alert('본인인증이 실패하였습니다.\n다시 본인인증을 시도해주시기 바랍니다.\n실패사유 : ${sMessage} \n현재 창은 닫힙니다.');
self.close();
</c:if>
</script>
</head>
<body>

<c:if test="${errorType eq '00'}">
<form:form commandName="iMMmbrHstry" name="iMMmbrHstry" method="post" target=""  action="${pageContext.request.contextPath}/user/mmbrHstry/modify.do"  onsubmit="return false;">
<form:hidden path="mmbrNm"/>
<form:hidden path="mblTelno"/>
<form:hidden path="brdt"/>
<form:hidden path="di"/>
<form:hidden path="ci"/>
</form:form>

<script type="text/javaScript" language="javascript">
function closeWithSubmit()
{
	
	var obj={'mmbrNm' : '${iMMmbrHstry.mmbrNm}','mblTelno' : '${iMMmbrHstry.mblTelno}','brdt' : '${iMMmbrHstry.brdt}','di' : '${iMMmbrHstry.di}','ci' : '${iMMmbrHstry.ci}'};
   // var f= document.forms.iMMmbrHstry;
    //window.opener.name = "openerNames";
    //f.target = opener.name;
    //f.submit();
    opener.doSetMobile(obj);
    self.close();
}
closeWithSubmit();
</script>

</c:if>
</body>
</html>