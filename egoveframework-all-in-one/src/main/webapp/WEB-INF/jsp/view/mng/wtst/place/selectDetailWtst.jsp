<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>


<script type="text/javascript" >


var REQ = {
	req : {
		list : null,
	},
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetail";
		this.req.list.cfg.url    = "<c:url value="/mng/wtstPlace/selectList.do"/>";
	},
	page :  function(pageNo){
		var form =  document.getElementById(this.req.list.cfg.formId);
		form.elements["currentPageNo"].value= pageNo;
		this.req.list.go();
	},
	list :  function(){
		this.req.list.go();
	}
}


$(document).ready(function(){
	REQ.init();
});

</script>

<%@ include file="incWtstPlace.jsp" %>


<div class="b_box right">
	<div> 
	<a href="#" onclick="REQ.list();return false;" class="ad_btn bk">목록</a>
	</div>
</div>
<table class="tbl_row">
	<caption>필기시험 상세정보</caption>
	<colgroup>
		<col style="width:200px;">
		<col>
	</colgroup>
	<tbody>
			<tr>
				<th scope="row"><spring:message code="iMWtst.crsGrdCdv"/></th>
				<td><im:cd type="print" codeId="IM0001" name="crsGrdCdv" selectedCode="${iMWtst.crsGrdCdv}" /></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMWtst.eduYear"/></th>
				<td>
				${iMWtst.eduYear}
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMWtst.eduRnd"/></th>
				<td>
				${iMWtst.eduRnd}
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMWtst.addtnYn"/></th>
				<td>
				<im:cd type="print" codeId="IMYENO"  selectedCode="${iMWtst.addtnYn}"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMWtst.pbancTerm"/>
				</th>
				<td>
				<im:dt yyyyMMddHHmmss="${cmmmDtMap['pbancTerm'].bgnDt}"/>
				~
				<im:dt yyyyMMddHHmmss="${cmmmDtMap['pbancTerm'].endDt}"/>  
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMWtst.rcptTerm"/></th>
				<td>
				
				<im:dt yyyyMMddHHmmss="${cmmmDtMap['rcptTerm'].bgnDt}"/>
				~
				<im:dt yyyyMMddHHmmss="${cmmmDtMap['rcptTerm'].endDt}"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMWtst.tstYmd"/></th>
				<td>
				<im:dt yyyyMMddHHmmss="${iMWtst.tstYmd}"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMWtst.tstHrs"/></th>
				<td>
						${iMWtst.tstBgngHrs} ~ ${iMWtst.tstEndHrs}
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMWtst.passTerm"/></th>
				<td>
				<im:dt yyyyMMddHHmmss="${cmmmDtMap['passTerm'].bgnDt}"/>  
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMWtst.slctnTerm"/></th>
				<td>
				<im:dt yyyyMMddHHmmss="${cmmmDtMap['slctnTerm'].bgnDt}"/>
				~
				<im:dt yyyyMMddHHmmss="${cmmmDtMap['slctnTerm'].endDt}"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMWtst.payTerm"/></th>
				<td>
					<im:dt yyyyMMddHHmmss="${cmmmDtMap['payTerm'].bgnDt}"/>
					~
					<im:dt yyyyMMddHHmmss="${cmmmDtMap['payTerm'].endDt}"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMWtst.tstfee"/></th>
				<td>
				<im:numberFormat value="${iMWtst.tstfee}"/> 원
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMWtst.ntce"/></th>
				<td>
				<c:out value="${imfunc:textToBr(cmmmDescMap['ntce'])}" escapeXml="false"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMWtst.brng"/></th>
				<td>
				<c:out value="${imfunc:textToBr(cmmmDescMap['brng'])}" escapeXml="false"/>
				</td>
			</tr>
			
			<tr>
				<th scope="row"><spring:message code="iMWtst.stts"/></th>
				<td><im:cd type="print" codeId="IM0003" selectedCode="${iMWtst.sttsCdv}" name="sttsCdv"/></td>
			</tr>

	</tbody>
</table>

<div class="b_box right">
	<div> 
	<a href="#" onclick="REQ.list();return false;" class="ad_btn bk">목록</a>
	</div>
</div>