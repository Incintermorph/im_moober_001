<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>
		<div class="ext_link">
			<ul class="guide">
				<li>회원정보 수정은 환경교육포털 사이트에서 가능합니다.</li>
				<li>이동 후에는 재 로그인이 필요합니다.</li>
			</ul>
			<div class="box">
				<a href="https://www.keep.go.kr/portal/161?action=password&action-value=mypage" class="c_btn green md">회원정보 수정</a>
				<a href="https://www.keep.go.kr/portal/162" class="c_btn green md">비밀번호 수정</a>
				<a href="https://www.keep.go.kr/portal/163?action=password&action-value=leave" class="c_btn gray md">회원탈퇴</a>
			</div>
		</div>
<script type="text/javaScript" language="javascript" defer="defer">
sessionStorage.getItem('hwangyosa');
   
	var domainhost = "http://"
	if (document.location.protocol != 'http:') {
		 domainhost = "https://"+window.location.host;
	}
	domainhost= domainhost+window.location.host+'${pageContext.request.contextPath}/user/page/intro05/page.do';
	sessionStorage.setItem('hwangyosa', domainhost );
</script>
		 