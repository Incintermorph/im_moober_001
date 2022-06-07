<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>

<c:set var="jsFunction" value="${param['jsFunction']}"/>
<c:if test="${!empty param['pageInfo']}">
	<c:set var="pageInfo" value="${param['pageInfo']}"/>
</c:if>
<c:set var="pageInfo" value="${requestScope[pageInfo]}"/>

<c:if test="${pageInfo.totalRecordCount>0}">
	<div class="paginate">
		<c:if test="${pageInfo.condition.sercharAll eq 0}">
			<div class="inner">
				<ui:pagination paginationInfo="${pageInfo.condition}" type="imUserPage" jsFunction="${jsFunction}"/>
		    </div>
	  </c:if>
	  
	   </div>
</c:if>