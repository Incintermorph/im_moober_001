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
	<div class="paginate info">
	<c:if test="${pageInfo.condition.sercharAll eq 0}">
			<div class="count">
				<span>전체 ${pageInfo.condition.totalRecordCount} 개</span>
				<span>현재 <span class="current">${pageInfo.condition.currentPageNo}</span>/${pageInfo.condition.lastPageNo}</span>
			</div>
			<div class="inner">
				<ui:pagination paginationInfo="${pageInfo.condition}" type="imAdminPage" jsFunction="${jsFunction}"/>
		    </div>
	  </c:if>
	  <c:if test="${pageInfo.condition.sercharAll ne 0}">
	  		<div class="count">
				<span>전체 ${pageInfo.condition.totalRecordCount} 개</span>
				<span></span>
			</div>
			<div class="inner">
			</div>
	  </c:if>
	  </div>
</c:if>