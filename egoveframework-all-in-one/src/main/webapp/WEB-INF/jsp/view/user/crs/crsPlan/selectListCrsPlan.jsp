<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>
<script src="${imWebStatic}/js/templet.js"></script>

<script type="text/javascript" >


var REQ = {
	list :null,
	init : function(){
		REQ.list =  new Array();		
		<c:forEach items="${list}" var="row" varStatus="i">
		<c:set var="num" value="1"/>
		<c:if test="${row.crsPlan.crsGrdCdv eq 'CRS_GRD_2'}">
		<c:set var="num" value="2"/>
		</c:if>
		<c:if test="${row.crsPlan.crsGrdCdv eq 'CRS_GRD_3'}">
		<c:set var="num" value="3"/>
		</c:if>
		<c:set var="mms">
			<im:dt yyyyMMddHHmmss="${row.crsPlan.eduAplTerm_bgnDt}" pattern="MM"/>
		</c:set>
		
		<c:set var="mm">
		${mms*1}
		</c:set>
	     <c:set var="rCss" value="l_green"/>
	     <c:if test="${row.crsPlan.crsDvsnCdv eq 'CRS_DVSN_001'}">
	     	<c:set var="rCss" value="l_green"/>
	     </c:if>	
	     <c:if test="${row.crsPlan.crsDvsnCdv eq 'CRS_DVSN_001_01'}">
	     	<c:set var="rCss" value="blue"/>
	     </c:if>	
	     
	     <c:if test="${row.crsPlan.crsDvsnCdv eq 'CRS_DVSN_002'}">
	     	<c:set var="rCss" value="green"/>
	     </c:if>	
	     	
	     
	     <c:if test="${row.crsPlan.crsDvsnCdv eq 'CRS_DVSN_003'}">
	     	<c:set var="rCss" value="cyan"/>
	     </c:if>
	     <c:if test="${row.crsPlan.crsDvsnCdv eq 'CRS_DVSN_001_01'}">
	     	<c:if test="${fn:substring(row.crsPlan.eduTerm_bgnDt,0,8) == fn:substring(row.crsPlan.eduTerm_endDt,0,8) }">
			     REQ.list[REQ.list.length]={
					css : '<c:out value="${rCss}"/>',
					agcyNm : '<c:out value="${row.agncy.agncyNm}"/>',
					mmdd : '<im:dt yyyyMMddHHmmss="${row.crsPlan.eduAplTerm_bgnDt}" pattern="MM-dd"/>',
					date1 : '<im:dt yyyyMMddHHmmss="${row.crsPlan.eduAplTerm_bgnDt}"/>~<im:dt yyyyMMddHHmmss="${row.crsPlan.eduAplTerm_endDt}"/>',
					date2 : '<im:dt yyyyMMddHHmmss="${row.crsPlan.eduTerm_bgnDt}"/>',
					viewid : '_td_${num}_${row.crsPlan.crsDvsnCdv}_${mm}',
					writeYn : 'Y'
				 };
		     </c:if>
		     
		     <c:if test="${fn:substring(row.crsPlan.eduTerm_bgnDt,0,8) != fn:substring(row.crsPlan.eduTerm_endDt,0,8) }">
			     REQ.list[REQ.list.length]={
					css : '<c:out value="${rCss}"/>',
					agcyNm : '<c:out value="${row.agncy.agncyNm}"/>',
					mmdd : '<im:dt yyyyMMddHHmmss="${row.crsPlan.eduAplTerm_bgnDt}" pattern="MM-dd"/>',
					date1 : '<im:dt yyyyMMddHHmmss="${row.crsPlan.eduAplTerm_bgnDt}"/>~<im:dt yyyyMMddHHmmss="${row.crsPlan.eduAplTerm_endDt}"/>',
					date2 : '<im:dt yyyyMMddHHmmss="${row.crsPlan.eduTerm_bgnDt}"/>~<im:dt yyyyMMddHHmmss="${row.crsPlan.eduTerm_endDt}"/>',
					viewid : '_td_${num}_${row.crsPlan.crsDvsnCdv}_${mm}',
					writeYn : 'Y'
				 };
	   	  	 </c:if>
	     </c:if>
	     
	     <c:if test="${row.crsPlan.crsDvsnCdv ne 'CRS_DVSN_001_01'}">
	     	 <c:if test="${fn:substring(row.crsPlan.eduTerm_bgnDt,0,8) == fn:substring(row.crsPlan.eduTerm_endDt,0,8) }">
			     REQ.list[REQ.list.length]={
		 			css : '<c:out value="${rCss}"/>',
		 			agcyNm : '<c:out value="${row.agncy.agncyNm}"/>',
		 			mmdd : '<im:dt yyyyMMddHHmmss="${row.crsPlan.eduAplTerm_bgnDt}" pattern="MM-dd"/>',
		 			date1 : '<im:dt yyyyMMddHHmmss="${row.crsPlan.eduAplTerm_bgnDt}"/>~<im:dt yyyyMMddHHmmss="${row.crsPlan.eduAplTerm_endDt}"/>',
		 			date2 : '<im:dt yyyyMMddHHmmss="${row.crsPlan.eduTerm_bgnDt}"/>',
		 			viewid : '_td_${num}_${row.crsPlan.crsDvsnCdv}_${mm}',
					writeYn : 'N'
				 };
			 </c:if>
		     <c:if test="${fn:substring(row.crsPlan.eduTerm_bgnDt,0,8) != fn:substring(row.crsPlan.eduTerm_endDt,0,8) }">
			     REQ.list[REQ.list.length]={
		 			css : '<c:out value="${rCss}"/>',
		 			agcyNm : '<c:out value="${row.agncy.agncyNm}"/>',
		 			mmdd : '<im:dt yyyyMMddHHmmss="${row.crsPlan.eduAplTerm_bgnDt}" pattern="MM-dd"/>',
		 			date1 : '<im:dt yyyyMMddHHmmss="${row.crsPlan.eduAplTerm_bgnDt}"/>~<im:dt yyyyMMddHHmmss="${row.crsPlan.eduAplTerm_endDt}"/>',
		 			date2 : '<im:dt yyyyMMddHHmmss="${row.crsPlan.eduTerm_bgnDt}"/>~<im:dt yyyyMMddHHmmss="${row.crsPlan.eduTerm_endDt}"/>',
		 			viewid : '_td_${num}_${row.crsPlan.crsDvsnCdv}_${mm}',
					writeYn : 'N'
				 };
		     </c:if>
	     </c:if>
	     
		</c:forEach>
		REQ.list.forEach (function (obj, index) {
			$("#"+obj.viewid).addClass("active");
			$("#"+obj.viewid).html($("#"+obj.viewid).html()+TEMPLETE.getCrsPlan(obj));
		});
	}
}

$(document).ready(function(){
	REQ.init();
});






</script>


<div class="ct_box bg bot">
			<ul class="radio">
				<li>연간 교육및 평가 일정은 변경될 수 있으며 정확한 일정은 교육신청과 원서접수에서 확인하여 주시기 바랍니다.</li>
			</ul>
		</div>
		<p class="bl_title">${condition.scEduYear}년 연간교육일정(교육신청일 표기: 월-일)</p>
		
		<im:cd type="set" codeId="IM0011" var="CODE_IM0011"/>
<%-- 		<c:set var="codeNum">3=3급,2=2급,1=1급</c:set> --%>
		<c:set var="codeNum">3=3급,2=2급</c:set>
		<im:cd type="set" codeId="${codeNum}" var="CODE_NUM"/>
		<div class="tc_yearly"><!-- 20220213 : 클래스 수정 -->
			<table>
				<caption>연간교육일정</caption>
				<colgroup>
					<col style="width:18%;">
				</colgroup>
				<thead>
					<tr>
						<th scope="col">교육과정명</th>
						<c:forEach begin="1" end="12" var="mm">
						<th scope="col">${mm}월</th>
						</c:forEach>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${CODE_NUM}" var="num">
					<tr>
						<td colspan="13" class="link"><a href="<c:url value="/cmmn/crs/C/"/>${num.code}/selectList.do">${num.code}급 교육신청 바로가기 ></a></td>
					</tr>
					<c:forEach items="${CODE_IM0011}" var="row">
					<tr>
						<td class="subj">${row.codeNm}</td>
						<c:forEach begin="1" end="12" var="mm">						
						<td id="_td_${num.code}_${row.code}_${mm}"></td>
						</c:forEach>
					</tr>
					</c:forEach>		
					</c:forEach>					
				</tbody>
			</table>
		</div>


