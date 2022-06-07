<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>


<script type="text/javascript" >


var REQ = {

	req : {
			crslist : null
	},
	init : function(){

		this.req.crslist = imRequest();
		this.req.crslist.cfg.formId = "FormPageDetail";
		this.req.crslist.cfg.url    = "<c:url value="/mng/crs/selectList.do"/>";
		},
	crslist : function(){
		REQ.req.crslist.go();
	}
}


$(document).ready(function(){
	REQ.init();
});


</script>
<%@ include file="incCrs.jsp" %>



<div class="b_box right">
				<div>
					<a href="#" onclick="COMMT.ready();return false;" class="ad_btn green">메뉴얼 다운로드</a>
	 				<a href="#" onclick="REQ.crslist();return false;" class="ad_btn bk">목록</a>
				</div>
			</div>
			<br/>
			<table class="tbl_col description">
				<colgroup>
					<col style="width:60px;">
					<col style="width:140px;">
					<col style="width:170px;">
					<col>
				</colgroup>
				<thead>
				<tr>
					<th scope="col">No</th>
					<th scope="col">진행상태</th>
					<th scope="col">업무처리기관</th>
					<th scope="col">설명</th>
				</tr>
				</thead>
				<tbody>
				<tr>
					<td>1</td>
					<td>심사전</td>
					<td>-</td>
					<td class="al">교육생이 수강신청 한 상태입니다.</td>
				</tr>
				<tr>
					<td>1</td>
					<td>랜덤선정</td>
					<td><span class="ad_badge blue">양성기관</span></td>
					<td class="al">
						정원의 1.5 배수 선정하고 선정 순서대로 나열됩니다.
						<br/>랜덤 선정이 완료되어야 자격심사를 시작할 수 있습니다.
						<br/><strong class="red">랜덤 선정 결과는 교육생 수강상태에 노출되지 않습니다.</strong>
					</td>
				</tr>
				<tr>
					<td>3</td>
					<td>선정완료</td>
					<td><span class="ad_badge blue">양성기관</span></td>
					<td class="al">
						1차, 2차 자격심사를 양성기관과 운영사무국에서 진행하며 모든 심사가 완료되었을 때 양성기관에서 선정 완료를 합니다.
						<br/><strong class="red">랜덤 선정 결과는 교육생 수강상태에 노출되지 않습니다.</strong>

					</td>
				</tr>
				<tr>
					<td>4</td>
					<td>통보</td>
					<td><span class="ad_badge purple">운영사무국</span></td>
					<td class="al">
						선정 완료 후 운영 사무국에서 <strong>통보할 때 교육생 화면에 선정된 상태가 노출됩니다.</strong>

					</td>
				</tr>
				<tr>
					<td>5</td>
					<td>교육완료</td>
					<td><span class="ad_badge blue">양성기관</span></td>
					<td class="al">
						교육완료후 수료, 미수료, 자격 취득여부를 최종 등록하고 교육완료로 상태 변경을 하시면 됩니다.
					</td>
				</tr>
				</tbody>
			</table>
			<div class="description-guide">
				<li>
					<span>진행 상태 변경은 순차적으로 진행되며 상태 변경후에는 역순의 변경은 불가합니다.</span>
				</li>
				<li>
					<span>교육신청은 통보 후에 양성기관에서 입금 확인합니다.</span>
				</li>
			</div>

		