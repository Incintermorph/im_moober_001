<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>


<script type="text/javascript" >


var REQ = {
	req : {
		list : null,
		search : null,
		detail : null
	},
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetail";
		this.req.list.cfg.url    = "<c:url value="/user/uss/mypage/wtstAplcnt/selectList.do"/>";
		
		this.req.search = imRequest();
		this.req.search.cfg.formId = "FormSearch";
		this.req.search.cfg.url    = "<c:url value="/user/uss/mypage/wtstAplcnt/selectList.do"/>";
		

		this.req.detail = imRequest();
		this.req.detail.cfg.formId = "FormPageDetail";
		this.req.detail.cfg.url    = "<c:url value="/user/uss/mypage/wtstAplcnt/selectDetail.do"/>";
		
		

	    
	    
		
	},
	page :  function(pageNo){
		var form =  document.getElementById(this.req.list.cfg.formId);
		form.elements["currentPageNo"].value= pageNo;
		this.req.list.go();
	},
	detail :  function(mapDataPks){
		REQ.req.detail.cfg.message.confirm=null;
		COMMT.copyMapDataToFormReset(mapDataPks, this.req.detail.cfg.formId);
		var form =  document.getElementById(REQ.req.detail.cfg.formId);
		form.elements["modifyYn"].value= "N";
		this.req.detail.go();
	},	
	searchAplyStts : function(aplyStts){
		var form =  document.getElementById(this.req.search.cfg.formId);
		form.elements["scAplyStts"].value= aplyStts;
		REQ.req.search.go();
	},
	printAdmissionTicket: function(wtstAplcntId){ 
		COMMT.fn_Enc_Print('<c:url value="/user/uss/mypage/selectDetailExamPrint.do"/>?wtstAplcntId='+wtstAplcntId,1260,850);
	},
	printAdmissionTicketModify: function(mapDataPks){
		REQ.req.detail.cfg.message.confirm="수험표에 사진첨부가 필요합니다.<br>사진 등록하는 화면으로 이동 하시겠습니까?";
		COMMT.copyMapDataToFormReset(mapDataPks, REQ.req.detail.cfg.formId);

		var form =  document.getElementById(REQ.req.detail.cfg.formId);
		form.elements["modifyYn"].value= "Y";
		REQ.req.detail.go();
	},
	viewScore : function(wtstAplcntId){
		var req = imRequest("ajax");
		req.cfg.formId = "FormPageDetail";
		req.cfg.type        = "html";

        req.cfg.containerId = "resultScoreDiv";
		req.cfg.url    = "<c:url value="/user/uss/mypage/wtstAplcnt/selectDetailScore.do"/>";
		req.cfg.fn.complete = function(act, data) {
			$("#view_score").show();          
        };
        var form =  document.getElementById(req.cfg.formId);
		form.elements["wtstAplcntId"].value= wtstAplcntId;
		req.go();
		
		
	},
	hideScore : function(){
		$("#view_score").hide();
	}
}


$(document).ready(function(){
	REQ.init();
});


</script>

<%@ include file="incMypageWtstAplcnt.jsp" %>


<div class="cb_bar">
			<div class="ct_box">
				<ul class="radio">
					<span class="c_red"><i class="m_icon_out circle_notifications"></i>중요</span> : 수험표 출력 전 반드시 사진 첨부 여부를 확인하여 주시기 바랍니다.
					<li>수정 및 취소는 <b>원서접수 기간에만 가능</b>합니다.</li>
					<li>원서접수 승인여부와 수험표 출력은 아래의 <b>접수결과 탭에서</b> 확인하실 수 있습니다.</li>
				</ul>
			</div>
		</div>
		<ul class="nav_tabs">
			<li <c:if test="${condition.scAplyStts eq '01A' }"> class="on"</c:if>><a href="#c_01" onclick="REQ.searchAplyStts('01A')">접수<span class="count">(${cnt01A}건)</span></a></li>
			<li <c:if test="${condition.scAplyStts eq '03' }"> class="on"</c:if>><a href="#c_02" onclick="REQ.searchAplyStts('03')">접수취소<span class="count">(${cnt03}건)</span></a></li>
			<li <c:if test="${condition.scAplyStts eq '0204' }"> class="on"</c:if>><a href="#c_02" onclick="REQ.searchAplyStts('0204')">접수결과<span class="count">(${cnt0204}건)</span></a></li>
			<li <c:if test="${condition.scAplyStts eq '02A' }"> class="on"</c:if>><a href="#c_02" onclick="REQ.searchAplyStts('02A')">시험결과<span class="count">(${cnt02A}건)</span></a></li>
			
		</ul>
		<im:form  condition="${condition}" formName="FormSearch"  type="search"> <%--검색인 경우 타입 --%>
		<input type="hidden" name="scAplyStts"      value="<c:out value="${condition.scAplyStts}"/>" />
		<div class="t_bar">
			<div class="count">
				총 <span class="num">${condition.totalRecordCount}</span>건
			</div>
		</div>
		</im:form>
		<div class="tbl_rps_data">
			<table class="tbl_data">
				<caption>교육신청 목록</caption>
				<colgroup>
					<c:if test="${condition.scAplyStts eq '01A' || condition.scAplyStts eq '03' }">
						<col style="width:220px;">
						<col/>
						<col style="width:80px;">
						<col style="width:80px;">
						<col style="width:120px;">
						<col style="width:100px;">
						<col style="width:220px;">
						<col style="width:120px;">
					</c:if>
					<c:if test="${condition.scAplyStts eq '0204'}">
						<col style="width:220px;">
<!-- 						<col style="width:80px;"> -->
<!-- 						<col style="width:80px;"> -->
						<col style="width:120px;">
						<col style="width:100px;">
						<col style="width:210px;">
						<col style="width:120px;">
						<col style="width:120px;">
						<col style="width:180px;">
						<col style="width:150px;">						
					</c:if>
					<c:if test="${condition.scAplyStts eq '02A'}">
						<col style="width:150px;">
						<col />
						<col style="width:80px;">
						<col style="width:80px;">
						<col style="width:120px;">
						<col style="width:120px;">
						<col style="width:150px;">
						<col style="width:120px;">
						
					</c:if>
				</colgroup>
				<thead>
					<tr>
						<c:if test="${condition.scAplyStts eq '01A' || condition.scAplyStts eq '03' }">
							<th scope="col">접수번호</th>
						</c:if>
						<c:if test="${condition.scAplyStts eq '02A' }">
							<th scope="col">수험번호 </th>
						</c:if>
						<th scope="col">필기시험명</th>
						<c:if test="${condition.scAplyStts ne '0204' }">
							<th scope="col">연도</th>
							<th scope="col">차수</th>
						</c:if>
						<c:if test="${condition.scAplyStts eq '01A' || condition.scAplyStts eq '03' || condition.scAplyStts eq '0204' }">
						<th scope="col">원서접수상태</th>
						<th scope="col">응시료</th>						
						<th scope="col">납부기간</th>
						<c:if test="${condition.scAplyStts eq '0204' }">
						<th scope="col">입금확인여부</th>
						</c:if>
						<th scope="col">시험일자</th>
						</c:if>
						<c:if test="${condition.scAplyStts eq '0204' }">
						<th scope="col">시험장</th>
						</c:if>
						<c:if test="${condition.scAplyStts eq '02A' }">
						<th scope="col">시험결과</th>
						<th scope="col">합격일자</th>
						<th scope="col">유효기간 만료일</th>
						</c:if>
						<c:if test="${condition.scAplyStts eq '02A' || condition.scAplyStts eq '0204' }">
						<th scope="col">관리</th>
						</c:if>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pageInfo.list}" var="row" varStatus="i">
					<tr>
						<c:if test="${condition.scAplyStts eq '01A' || condition.scAplyStts eq '03' }">
						<td>
							<div class="th">접수번호</div>
							<div class="td">
								<c:if test="${condition.scAplyStts eq '01A'}">
								<a href="javascript";" onclick="REQ.detail({'wtstAplcntId' : '${row.wtstAplcnt.wtstAplcntId}'});return false;"  class="tb_link">
								${row.wtstAplcnt.wtstAplcntId}
								</a>
								</c:if>
								<c:if test="${condition.scAplyStts eq '03'}">
								${row.wtstAplcnt.wtstAplcntId}
								</c:if>
							</div>
						</td>
						</c:if>
						<c:if test="${condition.scAplyStts eq '02A'}">
						<td>
							<div class="th">수험번호</div>
							<div class="td">
							<c:if test="${empty  row.wtstAplcnt.tktstno}">
							미발급
							</c:if>
							<c:if test="${!empty  row.wtstAplcnt.tktstno}">
							<a href="javascript";" onclick="REQ.detail({'wtstAplcntId' : '${row.wtstAplcnt.wtstAplcntId}'});return false;"  class="tb_link">
							${row.wtstAplcnt.tktstno}
							</a>
							</c:if>
							</div>
						</td>
						</c:if>
						<td>
							<div class="th">필기시험명</div>
							<div class="td">
							환경교육사 <im:cd type="print" codeId="IM0001" selectedCode="${row.wtst.crsGrdCdv}"/> 필기시험
							</div>
						</td>
						<c:if test="${condition.scAplyStts ne '0204'}">
							<td>
								<div class="th">연도</div>
								<div class="td"><c:out value="${row.wtst.eduYear}"/></div>
							</td>
							<td>
								<div class="th">차수</div>
								<div class="td"><c:out value="${row.wtst.eduRnd}"/></div>
							</td>
						</c:if>
						<c:if test="${condition.scAplyStts eq '01A' || condition.scAplyStts eq '03' || condition.scAplyStts eq '0204' }">
						
						
						<td>
							<div class="th">원서접수상태</div>
							<div class="td">
							<c:set var="APLY">
							<im:cd  codeId="${row.sttDdvs}" type="print" selectedCode="APLY" />
							</c:set>
							<im:cd type="print" codeId="IM0021" selectedCode="${APLY}"/>
							</div>							
						</td>
						<td>
							<div class="th">응시료</div>
							<div class="td ar"><im:numberFormat value="${row.wtst.tstfee}"/> 원</div>							
						</td>
						<td>
							<div class="th">납부기간</div>
							<div class="td">
							<c:set var="payTerm_bgnDt">
							<im:cd  codeId="${row.wtstDts}" type="print" selectedCode="payTerm_bgnDt" />
							</c:set>
							<c:set var="payTerm_endDt">
							<im:cd  codeId="${row.wtstDts}" type="print" selectedCode="payTerm_endDt" />
							</c:set>
							<im:dt yyyyMMddHHmmss="${payTerm_bgnDt}" pattern="yyyy.MM.dd"/>
							~
							<im:dt yyyyMMddHHmmss="${payTerm_endDt}" pattern="yyyy.MM.dd"/>
							</div>							
						</td>
						<c:if test="${condition.scAplyStts eq '0204' }">
						<td>
							<div class="th">입금확인여부</div>
							<div class="td">
							<c:set var="DPST">
							<im:cd  codeId="${row.sttDdvs}" type="print" selectedCode="DPST" />
							</c:set>
							<im:cd type="print" codeId="${dpstYnArr}" selectedCode="${DPST}"/>
							</div>							
						</td>
						</c:if>
						<td>
							<div class="th">시험일자</div>
							<div class="td">
						<c:if test="${condition.scAplyStts ne '0204' }">
						<im:dt yyyyMMddHHmmss="${row.wtst.tstYmd}"/>
						</c:if>
						<c:if test="${condition.scAplyStts eq '0204' }">
							<a href="#" onclick="REQ.detail({'wtstAplcntId' : '${row.wtstAplcnt.wtstAplcntId}'});return false;"  class="tb_link">
							<im:dt yyyyMMddHHmmss="${row.wtst.tstYmd}"/>
							</a>
						</c:if>
							</div>							
						</td>
						</c:if>
						<c:if test="${condition.scAplyStts eq '0204' }">
						
						<td>
							<div class="th">시험장</div>
							<div class="td al">${row.agncy.agncyNm}</div>							
						</td>
						</c:if>
						
						<c:if test="${condition.scAplyStts eq '02A' }">
						
						<td>
							<div class="th">시험결과</div>
							<div class="td">
							<c:set var="FNSH">
								<im:cd  codeId="${row.sttDdvs}" type="print" selectedCode="FNSH" />
							</c:set>
								<im:cd type="print" codeId="IM0025" selectedCode="${FNSH}"  />
							</div>							
						</td>
						<td>
							<div class="th">합격일자</div>
							<div class="td"><im:dt yyyyMMddHHmmss="${row.wtstAplcnt.passYmd}"/></div>							
						</td>
						<td>
							<div class="th">유효기간<br>만료일</div>
							<div class="td"><im:dt yyyyMMddHHmmss="${row.wtstAplcnt.vldEndYmd}"/></div>							
						</td>
						</c:if>
						<c:if test="${condition.scAplyStts eq '02A' || condition.scAplyStts eq '0204' }">
						<td>
							<div class="th">관리</div>
							<div class="td">
								<c:if test="${condition.scAplyStts eq '0204' && !empty  row.wtstAplcnt.tktstno}">
								
								<c:if test="${!empty  row.wtstAplcnt.phtFileId}">
								<a href="#" onclick="REQ.printAdmissionTicket('${row.wtstAplcnt.wtstAplcntId}');return false;"  class="c_btn bk sm">수험표 출력</a>
								</c:if>
								<c:if test="${empty  row.wtstAplcnt.phtFileId}">
								<a href="#" onclick="REQ.printAdmissionTicketModify({'wtstAplcntId' : '${row.wtstAplcnt.wtstAplcntId}'});return false;"  class="c_btn bk sm">수험표 출력</a>
								</c:if>
								</c:if>
								<c:if test="${condition.scAplyStts eq '02A' && FNSH ne '01'}">
								<a href="#" onclick="REQ.viewScore('${row.wtstAplcnt.wtstAplcntId}');return false;"  class="c_btn bk sm">성적열람</a>
								</c:if>
							</div>
						</td>
						</c:if>
					</tr>
					</c:forEach>
					<c:if test="${empty  pageInfo.list}">
						<tr>
<%-- 							<c:if test="${condition.scAplyStts eq '03' }"> --%>
<%-- 							<td colspan="9"><div class="no_info"><spring:message code="im.common.msg.nodata" /></div></td> --%>
<%-- 							</c:if> --%>
							<c:if test="${condition.scAplyStts eq '01A' || condition.scAplyStts eq '02A' || condition.scAplyStts eq '03'}">
							<td colspan="8"><div class="no_info"><spring:message code="im.common.msg.nodata" /></div></td>
							</c:if>
							
						</tr>
					</c:if>
				</tbody>
			</table>
		</div>
		
		
		<div class="note_box top">
			<ul class="item_li">
				<li>필기평가 및 원서접수에 대한 문의 및 이의 신청은 <a href="javascript:GREQ.loginChekShow('/user/objcAply/selectList.do');" class="tb_link"> 문의하기</a>에서 가능합니다.</li>
			</ul>
		</div>
				
<%@ include file="/WEB-INF/jsp/inc/imScoreLayer.jspf" %>
		
	