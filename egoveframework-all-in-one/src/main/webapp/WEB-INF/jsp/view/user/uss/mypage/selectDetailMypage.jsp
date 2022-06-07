<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>

<c:set var="retureUrlVal">
/user/uss/mypage/selectDetail.do
</c:set>

<script type="text/javascript" >


var REQ = {
	req : {
		
	},
	init : function(){
		
	},
	printComplete : function(appId){ <%--수료증--%>
		
		COMMT.fn_Enc_Print('<c:url value="/user/uss/mypage/selectDetailCrsPrint.do"/>?crsAplcntId='+appId,1260,850);
		//COMMT.ready();
	},
	printCompletePass : function(appId){ <%--이수증--%>
		COMMT.fn_Enc_Print('<c:url value="/user/uss/mypage/selectDetailCrsPrintPass.do"/>?crsAplcntId='+appId,1260,850);
		//COMMT.ready();
	},
	hisList : function(){
		var req = imRequest();
		req.cfg.formId = "FormMenuParam";
		
		var form =  document.getElementById(req.cfg.formId);
		form.elements["_paramMenuName"].value= '이전교육현황';
		req.cfg.url    = "<c:url value="/user/uss/mypage/selectListMmbrHstry.do"/>";
		req.go();
	},
	viewScore : function(){
		$("#view_score").show();
	},
	hideScore : function(){
		$("#view_score").hide();
	}
	
}


$(document).ready(function(){
	REQ.init();
});


</script>

		<div class="dash">
			<!-- 20220306 : 상태 정보 추가 -->
			<div class="my_log">
				<div class="info">
					<span class="name">${IMLoginUser.name}</span>
					<p class="log">
						<span class="text">이전 로그인 정보:</span>
						<span class="num"><im:dt yyyyMMddHHmmss="${lgnStts.mdfcnDt}" pattern="yyyy-MM-dd HH:mm:ss"/>(<c:out value=" ${lgnStts.mdfcnIp}"/>)</span>
					</p>
				</div>
			</div>
			<!-- //20220306 : 상태 정보 추가 -->
			<c:if test="${empty hstry}"> <%--이력 정보 없는 경우 --%>
			<div class="row g-0 my_prog">
				<div class="guide">
					<p class="em">등록된 이력정보가 없습니다.</p>
					<p class="desc">교육신청을 하시면 진행하시면 상태가 표시 됩니다.</p>
					<a href="javascript:GREQ.loginChekShow('/user/mmbrHstry/modify.do?retureUrlVal=<c:out value="${imfunc:encryptString(retureUrlVal)}"/>');" class="c_btn d_green md">이력관리 바로가기</a>
				</div>
			</div>
			</c:if>
			<c:if test="${!empty hstry}"> <%--이력 정보 없는 경우 --%>
			<div class="row g-0 my_prog" style="margin-top:25px;">
				<div class="col-xl-3 col-md-12 g-0 s_grade">
					<div class="box">
						<h4 class="lab">신청희망 자격등급</h4>
						<div class="val"><im:cd type="print" codeId="IM0001"   selectedCode="${hstry.mmbrHstry.aplyGrdCdv}"/></div>
						<div class="bar">
							<a href="javascript:GREQ.loginChekShow('/user/mmbrHstry/modify.do?retureUrlVal=<c:out value="${imfunc:encryptString(retureUrlVal)}"/>');" class="btn">이력관리 바로가기</a>
						</div>
					</div>
				</div>
				<c:if test="${empty lastMmbrQlfc}">
				<div class="col-xl-9 col-md-12 g-0">
					<div class="row g-0 s_step">
						<div class="col-md-auto col-sm-6 group g-0 basic">
							<p class="lab">기본과정</p>
							<div class="img"></div>
							<div class="bar">
								<span class="btn disable">수료증 출력</span>
							</div>
						</div>
						<div class="col-md-auto col-sm-6 group g-0 write">
							<p class="lab">필기평가</p>
							<div class="img"></div>
							<div class="bar">
								<span class="btn disable">성적 열람</span>
							</div>
						</div>
						<div class="col-md-auto col-sm-6 group g-0 work">
							<p class="lab">실무과정</p>
							<div class="img "></div>
							<div class="bar">
								<span class="btn disable">수료증 출력</span>
							</div>
						</div>
						<div class="col-md-auto col-sm-6 group g-0 re">
							<p class="lab">보수교육</p>
							<div class="img"></div>
							<div class="bar">
								<span class="btn disable">이수증 출력</span>
							</div>
						</div>
					</div>
				</div>
				</c:if>
				<c:if test="${!empty lastMmbrQlfc}">
				<div class="col-xl-9 col-md-12 g-0">
					<div class="row g-0 s_step">
						<div class="col-md-auto col-sm-6 group g-0 basic <c:if test="${!empty lastMmbrQlfc.bscCrsCmpltnYmd}">active</c:if>"><!-- 활성화 클래스 active -->
							<p class="lab">기본과정</p>
							<div class="img"><c:if test="${!empty lastMmbrQlfc.bscCrsCmpltnYmd}">수료</c:if></div>
							<div class="bar">
							<c:if test="${!empty lastMmbrQlfc.bscCrsCmpltnYmd && !empty lastMmbrQlfc.bscCrsAplcntId}">
								<a href="javascript:;" onclick="REQ.printComplete('${lastMmbrQlfc.bscCrsAplcntId}')" class="btn">수료증 출력</a>
							</c:if>	
							<c:if test="${empty lastMmbrQlfc.bscCrsCmpltnYmd || empty lastMmbrQlfc.bscCrsAplcntId}">
								<a href="#" class="btn disable">수료증 출력</a>
							</c:if>	
							</div>
						</div>
						<div class="col-md-auto col-sm-6 group g-0 write <c:if test="${!empty lastMmbrQlfc.wtstPassYmd}">active</c:if>">
							<p class="lab">필기평가</p>
							<div class="img"><c:if test="${!empty lastMmbrQlfc.wtstPassYmd}">합격</c:if></div>
							<div class="bar">
								<c:if test="${!empty lastMmbrQlfc.wtstPassYmd && !empty lastMmbrQlfc.wtstAplcntId}">
								<a href="javascript:;" onclick="REQ.viewScore('${lastMmbrQlfc.wtstAplcntId}')" class="btn">성적 열람</a>
								</c:if>
								<c:if test="${empty lastMmbrQlfc.wtstPassYmd || empty lastMmbrQlfc.wtstAplcntId}">
								<a href="#" class="btn disable">성적 열람</a>
								</c:if>
							</div>
						</div>
						<div class="col-md-auto col-sm-6 group g-0 work <c:if test="${!empty lastMmbrQlfc.excnCrsCmpltnYmd}">active</c:if>">
							<p class="lab">실무과정</p>
							<div class="img "><c:if test="${!empty lastMmbrQlfc.excnCrsCmpltnYmd}">수료</c:if></div>
							<div class="bar">
								<c:if test="${!empty lastMmbrQlfc.excnCrsCmpltnYmd  && !empty lastMmbrQlfc.excnCrsAplcntId}">
								<a href="javascript:;" onclick="REQ.printComplete('${lastMmbrQlfc.excnCrsAplcntId}')"  class="btn">수료증 출력</a>
								</c:if>
								<c:if test="${empty lastMmbrQlfc.excnCrsCmpltnYmd || empty lastMmbrQlfc.excnCrsAplcntId}">
								<a href="#" class="btn disable">수료증 출력</a>
								</c:if>								
							</div>
						</div>
						<div class="col-md-auto col-sm-6 group g-0 re <c:if test="${!empty lastMmbrQlfc.lcncEndYmd}">active</c:if>">
							<p class="lab">보수교육</p>
							<div class="img">
							<c:if test="${!empty lastMmbrQlfc.lcncEndYmd && lastMmbrQlfc.diffDay>0}">
							D-${lastMmbrQlfc.diffDay}
							</c:if>
							</div>
							<div class="bar">
								<c:if test="${!empty lastMmbrQlfc.cntneduCmpltnYmd && !empty lastMmbrQlfc.cntneduAplcntId}">
								<a href="javascript:;" onclick="REQ.printCompletePass('${lastMmbrQlfc.cntneduAplcntId}')" class="btn">이수증 출력</a>
								</c:if>
								<c:if test="${empty lastMmbrQlfc.cntneduCmpltnYmd || empty lastMmbrQlfc.cntneduAplcntId}">
								<a href="#" class="btn disable">이수증 출력</a>
								</c:if>
							</div>
						</div>
					</div>
				</div>
				
				</c:if>
			</div>			
			</c:if>
			<div class="row g-0 my_state">
				<div class="col-mds-3 g-0 q_link"><!-- 20220410 : div 그룹 클래스 수정1 -->
					<a href="<c:url value="/user/issuAply/selectList.do"/>" class="cert">
						<span class="lab">자격증 발급 신청</span>
						<p>환경교육사 자격증을<br>발급 해 드립니다.</p>
					</a>
					<a href="<c:url value="/user/pstpndAply/selectList.do"/>" class="app">
						<span class="lab">보수교육신청·유예</span>
						<p>보수교육을 신청하시면<br>환경교육을 하실 수 있습니다.</p>
					</a>
				</div>
				<div class="col-mds-6 g-0 after"><!-- 20220410 : div 그룹 클래스 수정2 -->
					<div class="tbl">
						<p class="title">법 개정 후 (2022.00.00 이후)</p>
						<div class="data cols">
							<div class="group">
								<div class="top">
									<h5>교육현황</h5>
									<a href="<c:url value="/user/uss/mypage/crsAplcnt/selectList.do"/>" class="more">더보기</a>
								</div><c:set var="statCdvArr">crsAply01Cnt=교육대기,crsAply02Cnt=선정,crsFnsh02Cnt=수료,qlfcCnt=자격취득,manageCnt=보수교육 대상</c:set>
								<c:forEach items="${selectCrsStat}" var="stat">
								<div class="cell">
									<span class="lab"><im:cd codeId="${statCdvArr}" type="print" selectedCode="${stat.statType}"/></span>
									<span class="val">${stat.cnt}</span>
								</div>
								</c:forEach>
							</div>
							<div class="group">
								<div class="top">
									<h5>응시현황</h5>
									<a href="<c:url value="/user/uss/mypage/wtstAplcnt/selectList.do"/>" class="more">더보기</a>
								</div><c:set var="statCdvArr">wtstAplyCnt=원서접수,wtstFnsh02Cnt=합격</c:set>
								<c:forEach items="${selectWtstStat}" var="stat">
								<div class="cell">
									<span class="lab"><im:cd codeId="${statCdvArr}" type="print" selectedCode="${stat.statType}"/></span>
									<span class="val">${stat.cnt}</span>
								</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
				<div class="col-mds-3 g-0 before"><!-- 20220410 : div 그룹 클래스 수정3 -->
					<div class="tbl">
						<p class="title">법 개정 전 (2022.00.00 이전)</p>
						<div class="data">
							<div class="top">
								<h5>이전 교육현황</h5>
								<a href="javascript:;" onclick="REQ.hisList()" class="more">더보기</a>
							</div>
							<div class="cell">
								<span class="lab">수료</span>
								<c:if test="${empty eduHisMap['4002']}">
								<span class="val">0</span>
								</c:if>
								<c:if test="${!empty eduHisMap['4002']}">
								<span class="val">${eduHisMap['4002']}</span>
								</c:if>
							</div>
							<div class="cell">
								<span class="lab">자격취득</span>
								<c:if test="${empty eduHisMap['4003']}">
								<span class="val">0</span>
								</c:if>
								<c:if test="${!empty eduHisMap['4003']}">
								<span class="val">${eduHisMap['4003']}</span>
								</c:if>
							</div>							
						</div>
					</div>
				</div>
			</div>
			<p class="bl_title">환경교육사 교육 및 자격 취득 이력</p>
			<table class="tbl_data line mo_sm">
				<caption>이력 목록</caption>
				<colgroup>
					<col style="width:15%;">
					<col style="width:25%;">
					<col style="width:25%;">
					<col>
				</colgroup>
				<thead>
					<tr>
						<th scope="col">등급</th>
						<th scope="col">과정</th>
						<th scope="col">상태</th>
						<th scope="col">취득일/합격일</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listMmbrQlfc}" var="row">
					<tr>
						<td rowspan="4"><span class="c_th"><im:cd type="print" codeId="IM0001"   selectedCode="${row.crsGrdCdv}"/></span></td>
						<td>기본과정</td>
						<c:if test="${!empty row.bscCrsCmpltnYmd}">
						<td><span class="c_badge complete">수료</span></td>
						<td><im:dt yyyyMMddHHmmss="${row.bscCrsCmpltnYmd}" pattern="yyyy.MM.dd"/></td>
						</c:if>
						<c:if test="${empty row.bscCrsCmpltnYmd}">
						<td></td>
						<td></td>
						</c:if>
					</tr>
					<tr>
						<td>필기평가</td>
						<c:if test="${!empty row.wtstPassYmd}">
						<td><span class="c_badge pass">합격</span></td>
						<td><im:dt yyyyMMddHHmmss="${row.wtstPassYmd}" pattern="yyyy.MM.dd"/></td>
						</c:if>
						<c:if test="${empty row.wtstPassYmd}">
						<td></td>
						<td></td>
						</c:if>
					</tr>
					<tr>
						<td>실무과정</td>
						<c:if test="${!empty row.excnCrsCmpltnYmd}">
						<td><span class="c_badge complete">수료</span></td>
						<td><im:dt yyyyMMddHHmmss="${row.excnCrsCmpltnYmd}" pattern="yyyy.MM.dd"/>
						<c:if test="${!empty row.lcncAcqsYmd}">
						 / <im:dt yyyyMMddHHmmss="${row.lcncAcqsYmd}" pattern="yyyy.MM.dd"/>
						 </c:if>
						 </td>
						</c:if>
						<c:if test="${empty row.excnCrsCmpltnYmd}">
						<td></td>
						<td></td>
						</c:if>
					</tr>
					<tr>
						<td>보수교육</td>
						<c:if test="${!empty row.cntneduCmpltnYmd}">
						<td><span class="c_badge finish">이수</span></td>
						<td><im:dt yyyyMMddHHmmss="${row.cntneduCmpltnYmd}" pattern="yyyy.MM.dd"/></td>
						</c:if>
						<c:if test="${empty row.cntneduCmpltnYmd}">
						<td></td>
						<td></td>
						</c:if>
					</tr>
					</c:forEach>
					<c:if test="${empty listMmbrQlfc}">
					<tr>
						<td colspan="4"><div class="no_info">교육 및 자격 취득 이력이 없습니다.</div></td>
					</tr>
					</c:if>
				</tbody>
			</table>
		</div>
		
		
		<c:set value="${detailWtstApply}" var="detailApply"/>
		<c:set value="${detailWtstPlace}" var="detail"/>
<%@ include file="/WEB-INF/jsp/inc/imScoreLayer.jspf" %>