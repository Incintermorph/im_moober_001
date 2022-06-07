<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>
<script type="text/javascript" >


var REQ = {
	init : function(){
	
	}
}


$(document).ready(function(){
	REQ.init();
});


</script>
<p class="bl_title">양성기관 현황</p>
	<div class="gd_box">
			<p>
				환경교육사 제도는 시범운영('15~'16)을 거쳐 2017년부터 본격적으로 시행되었습니다. 
				<br>2017년부터 양성과정 지정·운영이 시작되어, 현재 총 8개 기관에서 환경교육사 양성과정을 운영하고 있습니다.
			</p>
	</div>
	<div class="row gx-5">
			<c:forEach items="${list}" var="row" varStatus="i">
			<div class="col-lg-4 col-md-6 col-sm-12">
				<div class="organ_card">
					<div class="inner">
						<div class="head">
							<div class="logo"><img src="<c:url value="/cmmn/image/selectOne.do?atchFileId="/>${row.agncy.logoFileId}&thumb=1" alt="자연의벗연구소"></div>
							<p class="title"><c:out value="${row.agncy.agncyNm}"/></p>
						</div>
						<ul class="info">
							<li>
								<span class="lab">TEL</span>
								<span class="val"><c:out value="${row.agncy.telno}"/></span>
							</li>
							<li>
								<span class="lab">FAX</span>
								<span class="val"><c:out value="${row.agncy.fax}"/></span>
							</li>
							<li>
								<span class="lab">주소</span>
								<span class="val"><c:out value="${row.agncy.addr}"/>&nbsp&nbsp<c:out value="${row.agncy.addrDtl}"/></span>
							</li>
						</ul>
						<!-- 소개글 레이어 -->
						<div class="intro">
							<div class="head">
								<p class="title"><c:out value="${row.agncy.agncyNm}"/></p>
							</div>
							<div class="text"><c:out value="${row.agncy.cmmnDesc}"/></div>
							<div class="bar">
								<a href="<c:out value="${row.agncy.agncyUrl}"/>" class="link" target="_blank">홈페이지</a>
							</div>
						</div>
						<!-- //소개글 레이어 -->
					</div>
				</div>
			</div>
			</c:forEach>
			
	</div>
			