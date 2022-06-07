<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="/WEB-INF/tlds/im.tags.tld"      prefix="im"   %>
<%@ taglib uri="/WEB-INF/tlds/im.tags.func.tld"      prefix="imfunc"   %>
<%@ page import="com.intermorph.cmmn.IMGlobals" %>


<c:set var="imWebStatic" value="<%=IMGlobals.IM_WEB_STATIC%>"/>
<c:set var="imWebSiteWwwTitle" value="<%=IMGlobals.IM_WEB_SITE_WWW_TITLE%>"/>
<c:set var="imWebSiteMngTitle" value="<%=IMGlobals.IM_WEB_SITE_MNG_TITLE%>"/>
<c:set var="imExtensionsImages" value="<%=IMGlobals.IM_IMAGES_EXTENSIONS%>"/>
<c:set var="imExtensionsFiles" value="<%=IMGlobals.IM_FILES_EXTENSIONS%>"/>
<c:set var="imNowYear" value="<%=IMGlobals.IM_NOW_YEAR%>"/>
<c:set var="imStartYear" value="2022"/>

<c:set var="imEduYearStart" value="2010"/>
<c:set var="imEduYearEnd" value="${imNowYear+1}"/>
<c:set var="imEduRndStart" value="1"/>
<c:set var="imEduRndEnd" value="10"/>
<c:set var="imNowDatetime" value="<%=IMGlobals.IM_NOW_DATETIME%>"/>

<c:set var="imSiteMngHome" value="<%=IMGlobals.IM_WEB_ADMIN%>"/>
<c:set var="imSiteMngHome" value="${pageContext.request.contextPath}${imSiteMngHome}"/>
<c:set var="imSiteUserHome" value="<%=IMGlobals.MAIN_PAGE%>"/>
<c:set var="imSiteUserHome" value="${pageContext.request.contextPath}${imSiteUserHome}"/>
<c:set var="imdatepickerDateType" value="yy-mm-dd"/>


<c:if test="${!empty IMLoginUser}">

<c:set var="imLogin_name" value="${IMLoginUser.name}"/>
<c:set var="imLogin_loginDateTime" value="${IMLoginUser.loginDateTime}"/>
<c:set var="imLogin_uniqId" value="${IMLoginUser.uniqId}"/>
<c:set var="imLogin_id" value="${IMLoginUser.id}"/>
</c:if>

<c:set var="imSiteMemberjoinPage" value="https://keep.go.kr/portal/179"/>
<c:set var="imSiteMemberSearchPassPage" value="https://keep.go.kr/portal/180?action=namefact&sub-action=idsearch_result"/>