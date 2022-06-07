<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>

<%--과목 정보 --%>
<im:cd  codeId="IMEXM1" type="set" var="examArr" sort="1"/>
<c:if test="${detail.wtst.crsGrdCdv eq 'CRS_GRD_2' }">
<im:cd  codeId="IMEXM2" type="set" var="examArr"  sort="1" />
</c:if>
<c:if test="${detail.wtst.crsGrdCdv eq 'CRS_GRD_3' }">
<im:cd  codeId="IMEXM3" type="set" var="examArr"  sort="1"/>
</c:if>

<script type="text/javascript" >
(function() {
	  /**
	   * Decimal adjustment of a number.
	   *
	   * @param {String}  type  The type of adjustment.
	   * @param {Number}  value The number.
	   * @param {Integer} exp   The exponent (the 10 logarithm of the adjustment base).
	   * @returns {Number} The adjusted value.
	   */
	  function decimalAdjust(type, value, exp) {
	    // If the exp is undefined or zero...
	    if (typeof exp === 'undefined' || +exp === 0) {
	      return Math[type](value);
	    }
	    value = +value;
	    exp = +exp;
	    // If the value is not a number or the exp is not an integer...
	    if (isNaN(value) || !(typeof exp === 'number' && exp % 1 === 0)) {
	      return NaN;
	    }
	    // Shift
	    value = value.toString().split('e');
	    value = Math[type](+(value[0] + 'e' + (value[1] ? (+value[1] - exp) : -exp)));
	    // Shift back
	    value = value.toString().split('e');
	    return +(value[0] + 'e' + (value[1] ? (+value[1] + exp) : exp));
	  }

	  // Decimal round
	  if (!Math.round10) {
	    Math.round10 = function(value, exp) {
	      return decimalAdjust('round', value, exp);
	    };
	  }
	  // Decimal floor
	  if (!Math.floor10) {
	    Math.floor10 = function(value, exp) {
	      return decimalAdjust('floor', value, exp);
	    };
	  }
	  // Decimal ceil
	  if (!Math.ceil10) {
	    Math.ceil10 = function(value, exp) {
	      return decimalAdjust('ceil', value, exp);
	    };
	  }
	})();

var REQ = {
	req : {
		wtstPlacelist : null,
		list : null,
		save : null
	},
	init : function(){
		this.req.save = imRequest("ajax",{formId: "FormSave"});
		this.req.save.cfg.type   = "json";
		this.req.save.cfg.url    =  "<c:url value="/mng/wtstAplcnt/score/update.do"/>";
		this.req.save.cfg.message.confirm="저장 하시겠습니까?";
		this.req.save.cfg.fn.complete = function(act, data) {
			if (data != null && data.result>0) {
	        		REQ.req.list.go();
	        } else {
	        	COMMT.errorMessage();
	        }
	    };

		<c:forEach items="${examArr}" var="row">
		
		this.req.save.validator.set({
	    	title  : "${row.codeNm}",
            name : "${row.code}",
            data : ["!null","number"] ,
            check : {  
                maxlength : 3, 
                ge : 0, 
                le : 100 

            }  
        });
		
		</c:forEach>
	    
	    

		this.req.wtstPlacelist = imRequest();
		this.req.wtstPlacelist.cfg.formId = "FormPageDetail";
		this.req.wtstPlacelist.cfg.url    = "<c:url value="/mng/wtst/selectList.do"/>";
		
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetailTab";
		this.req.list.cfg.url    = "<c:url value="/mng/wtst/99/selectListAplcnt.do"/>";
		
	},
	wtstPlacelist : function(){
		REQ.req.wtstPlacelist.go();
	},
	list : function(){
		REQ.req.list.go();
	},
	save : function(){
		REQ.req.save.go();
	},
	calAvg : function(){
		var passResult=true;
		
		var form =  document.getElementById("FormSave");
		var scoreVar=0;
		var sumVar=0;
		var cntVar=0;
		var failVar=0;
		<c:forEach items="${examArr}" var="row">
		cntVar++;
		scoreVar = form.elements["${row.code}"].value;
		if(scoreVar==''){
			form.elements["${row.code}"].value='0';
			passResult=false;
			failVar++;
		}else{
			if (isNaN(scoreVar)){
				form.elements["${row.code}"].value='0';
				passResult=false;
				failVar++;
			}else{
				if(Number(scoreVar)>100){
					form.elements["${row.code}"].value='100';
					sumVar=Number(sumVar)+100;
				}else{
					form.elements["${row.code}"].value=Number(scoreVar);
					sumVar=Number(sumVar)+Number(scoreVar);
					if(Number(scoreVar)<40){
						failVar++;
						passResult=false;
					}
				}
			}
		}
		</c:forEach>
		
		var averageScore = Math.round10(sumVar/cntVar,-2);
		form.elements["avgScr"].value=averageScore;
		if(averageScore<60){
			passResult=false;
		}
		
		form.elements["totalExamCnt"].value=cntVar;
		form.elements["fltpSbjCnt"].value=failVar;
		if(passResult){
			$("#resultMent").html("합격");
			form.elements["sttsCdv"].value='02';
		}else{
			$("#resultMent").html("불합격");
			form.elements["sttsCdv"].value='03';
		}
		
		
	}
	
}


$(document).ready(function(){
	REQ.init();
});


</script>
	

<%@ include file="incWtst.jsp" %>	
<%--목록 상단 --%>	

<div class="b_box right">
				<a href="#" onclick="REQ.save();return false;"  class="ad_btn green mid">저장</a>
				<a href="#" onclick="REQ.list();return false;"  class="ad_btn bk mid">이전</a>
				<a href="#" onclick="REQ.wtstPlacelist();return false;" class="ad_btn bk mid">목록</a>
</div>
	
<table class="tbl_row">
	<caption>필기시험 상세정보</caption>
	<colgroup>
		<col style="width:200px;">
		<col>
		<col style="width:200px;">
		<col>
	</colgroup>
	<tbody>
		<th scope="row">필기시험명(등급)</th>
			<td>
			환경교육사 <im:cd type="print" codeId="IM0001" selectedCode="${detail.wtst.crsGrdCdv}"/> 필기시험
			</td>
			<th scope="row">연도 / 차수 </th>
			<td><c:out value="${detail.wtst.eduYear}"/>년/<c:out value="${detail.wtst.eduRnd}"/>차
			</td>
		<tr>
				<th scope="row">양성기관명</th>
				<td colspan="3"><c:out value="${detail.agncy.agncyNm}"/></td>
		</tr>
		<tr>
				<th scope="row">회원아이디</th>
				<td>${detailApply.wtstAplcnt.mberId}</td>
				<th scope="row">이름</th>
				<td>${detailApply.wtstAplcnt.mberNm}</td>
		</tr>
		<tr>
				<th scope="row">생년월일</th>
				<td>${detailApply.wtstAplcnt.brdt}</td>
				<th scope="row">휴대전화번호</th>
				<td>${detailApply.wtstAplcnt.mmbrTelno}</td>
		</tr>
		<tr>
			<th scope="row">구분</th>
			<td>
			<im:cd codeId="${workDvsnArr}" type="print" selectedCode="${detailApply.wtstAplcnt.workDvsnCdv}"/>
			</td>
			<th scope="row">현재 재직기관</th>
			<td>${detailApply.wtstAplcnt.agncyNm}</td>
		</tr>
		<tr>
				<th scope="row">이메일</th>
				<td colspan="3">${detailApply.wtstAplcnt.mberEmailAdres}</td>
		</tr>
		<tr>
				<th scope="row">수험번호</th>
				<td >${detailApply.wtstAplcnt.tktstno}</td>
				<th scope="row">합격일</th>
				<td ><im:dt yyyyMMddHHmmss="${cmmmDtMap['passTerm'].bgnDt}"/></td>
		</tr>
	</tbody>
</table>


<form name="FormSave" id="FormSave" method="post" onsubmit="return false;">	
<input type="hidden" name="wtstAplcntId"  value="${detailApply.wtstAplcnt.wtstAplcntId}"  />
<table class="tbl_row">
	<caption>필기시험 상세정보</caption>
	<colgroup>
		<c:forEach items="${examArr}" var="row">
		<col/>
		</c:forEach>
	</colgroup>
	<tbody>
		<c:forEach items="${examArr}" var="row">
		<th scope="row" class="ac">${row.codeNm}</th>
		</c:forEach>
	</tbody>
	<tbody>
		<td class="ac"><input type="text" name="score1" onkeyup="REQ.calAvg()" value="${detailApply.score.score1}" size="3" maxlength="3" style="text-align: right"  /></td>
		<td class="ac"><input type="text" name="score2" onkeyup="REQ.calAvg()" value="${detailApply.score.score2}" size="3" maxlength="3" style="text-align: right"  /></td>
		<td class="ac"><input type="text" name="score3" onkeyup="REQ.calAvg()" value="${detailApply.score.score3}" size="3" maxlength="3" style="text-align: right"  /></td>
		<td class="ac"><input type="text" name="score4" onkeyup="REQ.calAvg()" value="${detailApply.score.score4}" size="3" maxlength="3" style="text-align: right"  /></td>
		<td class="ac"><input type="text" name="score5" onkeyup="REQ.calAvg()" value="${detailApply.score.score5}" size="3" maxlength="3" style="text-align: right"  /></td>
		
		
	</tbody>
</table>
<table class="tbl_row">
	<caption>필기시험 상세정보</caption>
	<colgroup>
		<col style="width:200px;">
		<col>
		<col style="width:200px;">
		<col>
	</colgroup>
	<tbody>
		<tr>
			<th scope="row">평균</th>
			<td>
			<input type="text" name="avgScr" readonly="readonly" style="text-align: right"  size="6"  value="${detailApply.wtstAplcnt.avgScr}" />
			</td>
			<th scope="row">과락 과목수</th>
			<td>
			<input type="hidden" name="totalExamCnt"   value="${detailApply.score.totalExamCnt}"  value="5"  />
			<input type="text" name="fltpSbjCnt" value="${detailApply.wtstAplcnt.fltpSbjCnt}"  readonly="readonly" style="text-align: right"  size="3" />
			</td>
			
		</tr>
		<tr>
			<th scope="row">시험결과</th>
			<td colspan="3"><span id="resultMent"><im:cd type="print" codeId="IM0025" selectedCode="${cmmmDtStts['FNSH']}"/></span>
			
			<input type="hidden" name="refNm"  value="FNSH"  />
			<input type="hidden" name="sttsCdv"  value="${cmmmDtStts['FNSH']}"  />
			
			
			</td>
		</tr>
	</tbody>
</table>
</form>

<div class="b_box right">
	<a href="#" onclick="REQ.save();return false;"  class="ad_btn green mid">저장</a>
	<a href="#" onclick="REQ.list();return false;"  class="ad_btn bk mid">이전</a>
	<a href="#" onclick="REQ.wtstPlacelist();return false;" class="ad_btn bk mid">목록</a>
</div>