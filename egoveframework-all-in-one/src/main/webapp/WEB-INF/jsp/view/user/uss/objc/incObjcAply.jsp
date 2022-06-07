<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<im:form  condition="${condition}" formName="FormPageDetail"  type="list">
	<%--추가 검색 조건  todo Condition sc+컬럼+카멜--%>	
	<%--상세 조회 필요한 키값 --%>
	<input type="hidden" name="objcAplyId"     />
	<input type="hidden" name="dvsnCdv" value="01"     />
</im:form>


<c:set var="objcDvsnCdvArr">0101=수강신청,0102=자격심사,0103=선정,0104=출석점수,0105=실기점수,0106=기타,0201=원서접수,0202=점수,0301=발급,0302=재발급,0401=유예신청,0402=유예사유,99=기타</c:set>
<c:set var="dvsnCdvArr">01=교육과정,02=필기시험,03=자격증발급,04=보수교육 유예신청</c:set>
<c:set var="sttsCdvArr">01=문의접수,02=답변완료</c:set>