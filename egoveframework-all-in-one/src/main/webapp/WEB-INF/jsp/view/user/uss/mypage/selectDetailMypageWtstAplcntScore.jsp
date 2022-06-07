<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>
<%--과목 정보 --%>
<im:cd  codeId="IMEXM1" type="set" var="examArr" sort="1"/>
<c:if test="${detail.wtst.crsGrdCdv eq 'CRS_GRD_2' }">
<im:cd  codeId="IMEXM2" type="set" var="examArr" sort="1"/>
</c:if>
<c:if test="${detail.wtst.crsGrdCdv eq 'CRS_GRD_3' }">
<im:cd  codeId="IMEXM3" type="set" var="examArr" sort="1"/>
</c:if>
<table class="tbl_row al">
						<colgroup>
							<col style="width:25%;">
							<col>
							<col style="width:25%;">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th scope="row">수험번호</th>
								<td>${detailApply.wtstAplcnt.tktstno}</td>
								<th scope="row">필기시험명</th>
								<td>환경교육사 <im:cd type="print" codeId="IM0001" selectedCode="${detail.wtst.crsGrdCdv}"/> 필기시험</td>
							</tr>
							<tr>
								<th scope="row">연도</th>
								<td><c:out value="${detail.wtst.eduYear}"/>년</td>
								<th scope="row">차수</th>
								<td><c:out value="${detail.wtst.eduRnd}"/>차</td>
							</tr>
							<tr>
								<th scope="row">과목수</th>
								<td>${detailApply.score.totalExamCnt}</td>
								<th scope="row">평균성적</th>
								<td>${detailApply.wtstAplcnt.avgScr}</td>
							</tr>
							<tr>
								<th scope="row">과락유무</th>
								<td>
								<c:if test="${!empty  detailApply.wtstAplcnt.fltpSbjCnt}">
								<c:if test="${detailApply.wtstAplcnt.fltpSbjCnt>0}">
								예
								</c:if>
								<c:if test="${detailApply.wtstAplcnt.fltpSbjCnt eq 0}">
								아니오
								</c:if>
								</c:if>
								</td>
								<th scope="row">시험결과</th>
								<td><im:cd type="print" codeId="IM0025" selectedCode="${cmmmDtStts['FNSH']}"/></td>
							</tr>
						</tbody>
					</table>
					<table class="tbl_row al">
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
							<td class="ac">${detailApply.score.score1}</td>
							<td class="ac">${detailApply.score.score2}</td>
							<td class="ac">${detailApply.score.score3}</td>
							<td class="ac">${detailApply.score.score4}</td>
							<td class="ac">${detailApply.score.score5}</td>
						</tbody>
					</table>