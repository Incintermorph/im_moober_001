<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="egovframework.com.cmm.EgovWebUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 

<script>
jQuery(function($){
	$("[data-role='datepicker']" ).datepicker({
		showOn:"both",
		buttonText: '',
		dateFormat: 'yy-mm-dd',
		changeYear: true,
		yearRange: "1900:+nn"
	});
});
</script>
<ul class="nav_tabs">
				<li class="on"><a href="#">4뎁스</a></li>
				<li><a href="#">4뎁스</a></li>
				<li><a href="#">4뎁스</a></li>
				<li><a href="#">4뎁스</a></li>
			</ul>
			<ul class="nav_link">
				<li class="on"><a href="#">5뎁스</a></li>
				<li><a href="#">5뎁스</a></li>
				<li><a href="#">5뎁스</a></li>
				<li><a href="#">5뎁스</a></li>
			</ul>
			<br>
			<div>
				<p class="ct_title">배지 클래스 (내용 강조), 버튼과 색상 같음</p>
				<a href="#" class="ad_badge gray">배지</a>
				<a href="#" class="ad_badge bk">배지</a>
				<a href="#" class="ad_badge blue">배지</a>
				<a href="#" class="ad_badge sky">배지</a>
				<a href="#" class="ad_badge purple">배지</a>
				<a href="#" class="ad_badge green">배지</a>
				<a href="#" class="ad_badge white">배지</a>
			</div>
			<br><br>
			<div>
				<p class="ct_title">버튼 클래스(기능), 사이즈 클래스도 동일하게 추가</p>
				<a href="#" class="bt_new sm">bt_new</a>
				<a href="#" class="bt_modify sm">bt_modify</a>
				<a href="#" class="bt_detail sm">bt_detail</a>
				<a href="#" class="bt_del sm">bt_del</a>
				<a href="#" class="bt_save sm">bt_save</a>
				<br><br>
				<a href="#" class="bt_new">bt_new</a>
				<a href="#" class="bt_modify">bt_modify</a>
				<a href="#" class="bt_detail">bt_detail</a>
				<a href="#" class="bt_del">bt_del</a>
				<a href="#" class="bt_save">bt_save</a>
				<br><br>
				<a href="#" class="bt_new md">bt_new</a>
				<a href="#" class="bt_modify md">bt_modify</a>
				<a href="#" class="bt_detail md">bt_detail</a>
				<a href="#" class="bt_del md">bt_del</a>
				<a href="#" class="bt_save md">bt_save</a>
				<br><br>
				<p class="ct_title">버튼 클래스</p>
				<p>데이터 테이블 목록에 들어가는 작은 사이즈 : 클래스 sm</p>
				<a href="#" class="ad_btn gray sm">gray</a>
				<a href="#" class="ad_btn bk sm">bk</a>
				<a href="#" class="ad_btn blue sm">blue</a>
				<a href="#" class="ad_btn sky sm">sky</a>
				<a href="#" class="ad_btn purple sm">purple</a>
				<a href="#" class="ad_btn green sm">green</a>
				<a href="#" class="ad_btn white sm">white</a>
				<br><br>
				<p>일반 사이즈</p>
				<a href="#" class="ad_btn gray">gray</a>
				<a href="#" class="ad_btn bk">bk</a>
				<a href="#" class="ad_btn blue">blue</a>
				<a href="#" class="ad_btn sky">sky</a>
				<a href="#" class="ad_btn purple">purple</a>
				<a href="#" class="ad_btn green">green</a>
				<a href="#" class="ad_btn white">white</a>
				<br><br>
				<p>팝업 하단, 페이지 이벤트 등에 들어가는 큰 사이즈 : 클래스 md</p>
				<a href="#" class="ad_btn gray mid">gray</a>
				<a href="#" class="ad_btn bk mid">bk</a>
				<a href="#" class="ad_btn blue mid">blue</a>
				<a href="#" class="ad_btn sky mid">sky</a>
				<a href="#" class="ad_btn purple mid">purple</a>
				<a href="#" class="ad_btn green mid">green</a>
				<a href="#" class="ad_btn white mid">white</a>
			</div>
			<br><br>
			<p class="ct_title">콘텐츠 검색</p>
			<div class="sch_box">
				<div class="group">
					<div class="ip_gp">
						<span class="lab">기간</span>
						<div class="ad_datepicker">
							<input type="text" placeholder="시작일" data-role="datepicker">
						</div>
						~
						<div class="ad_datepicker">
							<input type="text" placeholder="종료일" data-role="datepicker">
						</div>
					</div>
					<div class="ip_gp">
						<button type="button" class="ad_btn gray">당일</button>
						<button type="button" class="ad_btn white">3일</button>
						<button type="button" class="ad_btn white">7일</button>
					</div>
				</div>
				<div class="group">
					<select name="" id="">
						<option value="">양성기관</option>
					</select>
					<select name="" id="">
						<option value="">년도</option>
					</select>
					<select name="" id="">
						<option value="">과정명</option>
					</select>
					<select name="" id="">
						<option value="">차수</option>
					</select>
				</div>
				<div class="group">
					<span class="lab">조건</span>
					<select name="" id="">
						<option value="">선택</option>
					</select>
					<input type="text" style="width:300px;">
					<button type="button" class="ad_btn gray">조회</button>
				</div>
			</div>
			<br>
			<p class="ct_title">데이터 테이블</p>
			<div class="cb_bar">
				<div>
					<span class="lab mr">목록개수</span>
					<select name="" id="">
						<option value="">10개</option>
					</select>
					<ul class="sorter_li">
						<li class="desc"><a href="#">등록일자</a></li>
						<li class="asc"><a href="#">등록일자</a></li>
						<li><a href="#">조회수</a></li>
						<li><a href="#">댓글수</a></li>
					</ul>
				</div>
				<div>
					<a href="#" class="ad_btn green">EXCEL</a>
					<a href="#" class="bt_new">신규등록</a>
					<a href="#" class="bt_del">삭제</a>
				</div>
			</div>
			<table class="tbl_col">
				<colgroup>
					<col style="width:50px;">
					<col style="width:60px;">
					<col style="width:110px;">
					<col>
					<col style="width:70px;">
					<col>
					<col style="width:70px;">
					<col style="width:80px;">
					<col style="width:120px;">
					<col style="width:70px;">
					<col style="width:70px;">
					<col style="width:70px;">
					<col style="width:70px;">
					<col style="width:100px;">
				</colgroup>
				<thead>
					<tr>
						<th scope="col"><input type="checkbox" title="전체선택"></th>
						<th scope="col">No</th>
						<th scope="col">운영과정 아이디</th>
						<th scope="col">양성기관</th>
						<th scope="col">년도</th>
						<th scope="col"><a href="#" class="sorter desc">과정명<i class="arrow"></i></a></th>
						<!-- sorter 클래스 desc, asc, 없음 -->
						<th scope="col">차수</th>
						<th scope="col">진행상태</th>
						<th scope="col">교육신청기간</th>
						<th scope="col">정원</th>
						<th scope="col">수강자</th>
						<th scope="col">수료자</th>
						<th scope="col">상태</th>
						<th scope="col"><a href="#" class="sorter desc">등록일<i class="arrow"></i></a></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><input type="checkbox" title="선택"></td>
						<td>1234</td>
						<td>seq</td>
						<td>인천광역시 미추홀구 인천업사이클에코센터</td>
						<td>2022</td>
						<td><a href="#">3급 기본과정</a></td>
						<td>1</td>
						<td><span class="ad_badge gray">선정전</span></td>
						<td>2022-02-21 ~<br>2022-02-21</td>
						<td>300</td>
						<td>29</td>
						<td>28</td>
						<td>운영</td>
						<td>2022-02-01</td>
					</tr>
					<tr>
						<td><input type="checkbox" title="선택"></td>
						<td>2</td>
						<td>seq</td>
						<td></td>
						<td></td>
						<td><a href="#">3급 기본과정</a></td>
						<td></td>
						<td><span class="ad_badge purple">랜덤선정</span></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td><input type="checkbox" title="선택"></td>
						<td>3</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td><span class="ad_badge sky">선정완료</span></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</tbody>
			</table>
			<div class="paginate info">
				<div class="count">
					<span>전체 120개</span>
					<span>현재 <span class="current">1</span>/200</span>
				</div>
				<div class="inner">
					<a href="#" class="img start"><span class="sr_only">처음</span></a>
					<a href="#" class="img prev"><span class="sr_only">이전</span></a>
					<span class="on">1</span>
					<a href="#">2</a>
					<a href="#">3</a>
					<a href="#">4</a>
					<a href="#">5</a>
					<a href="#" class="img next"><span class="sr_only">다음</span></a>
					<a href="#" class="img end"><span class="sr_only">마지막</span></a>
				</div>
			</div>
			<br>
			<p class="ct_title">상세 테이블</p>
			<table class="tbl_row">
				<caption>기관관리자 상세정보</caption>
				<colgroup>
					<col style="width:200px;">
					<col>
				</colgroup>
				<tbody>
					<tr>
						<th scope="row">아이디<span class="c_red">*</span></th>
						<td>Nohkkh<a href="#" class="ml ad_btn gray">비밀번호 변경</a></td>
					</tr>
					<tr>
						<th scope="row">소속 기관<span class="c_red">*</span></th>
						<td>인천광역시 미추홀구 인천업사이클에코센터​</td>
					</tr>
					<tr>
						<th scope="row">이름<span class="c_red">*</span></th>
						<td>홍길동</td>
					</tr>
					<tr>
						<th scope="row">직급<span class="c_red">*</span></th>
						<td>
							<input type="text" value="부장">
						</td>
					</tr>
					<tr>
						<th scope="row">전화번호<span class="c_red">*</span></th>
						<td><input type="text" value="0212341234"></td>
					</tr>
					<tr>
						<th scope="row">이메일<span class="c_red">*</span></th>
						<td><input type="text" value="nohkkh@test.com​"></td>
					</tr>
					<tr>
						<th scope="row">상태<span class="c_red">*</span></th>
						<td>
							<input type="radio" id="">
							<label for="">운영</label>
							<input type="radio" id="">
							<label for="">대기</label>
							<input type="radio" id="">
							<label for="">폐강</label>
						</td>
					</tr>
					<tr>
						<th scope="row">팝업 입력</th>
						<td>
							<input type="text" readonly>
							<a href="#sample" class="ad_btn gray">선택</a>
						</td>
					</tr>
					<tr>
						<th scope="row">날짜 입력</th>
						<td>
							<div class="ad_datepicker">
								<input type="text" placeholder="시작일" data-role="datepicker">
							</div>
							~
							<div class="ad_datepicker">
								<input type="text" placeholder="종료일" data-role="datepicker">
							</div>
						</td>
					</tr>
					<tr>
						<th scope="row">셀렉트박스 입력</th>
						<td>
							<select name="" id="">
								<option value="">선택</option>
							</select>
						</td>
					</tr>
					<tr>
						<th scope="row">텍스트 입력</th>
						<td>
							<textarea name="" id="" cols="" rows="" style="height:100px;"></textarea>
						</td>
					</tr>
					<tr>
						<th scope="row">첨부 파일</th>
						<td>
							<input type="file">
							<ul class="file_li">
								<li>
									<a href="#" class="file">OOOOOOOO.xls</a>
									<a href="#" class="del" title="삭제"><span class="sr_only">삭제</span></a>
								</li>
								<li>
									<a href="#" class="file">OOOOOOOO.xls</a>
									<a href="#" class="del" title="삭제"><span class="sr_only">삭제</span></a>
								</li>
								<li>
									<a href="#" class="file">OOOOOOOO.xls</a>
									<a href="#" class="del" title="삭제"><span class="sr_only">삭제</span></a>
								</li>
							</ul>
							<p class="tb_note">용량 :1회 최대50MB, 5개 파일까지 올릴 수 있습니다.​</p>
						</td>
					</tr>
					<tr>
						<th scope="row">주민번호 입력</th>
						<td>
							<input type="text" style="width:120px;">
							~
							<input type="text" style="width:120px;">
						</td>
					</tr>
					<tr>
						<th scope="row">휴대폰 입력</th>
						<td>
							<select name="" id="">
								<option value="">010</option>
							</select>
							<input type="text" style="width:120px;">
							~
							<input type="text" style="width:120px;">
						</td>
					</tr>
					<tr>
						<th scope="row">생년월일 입력</th>
						<td>
							<select name="" id="" style="width:80px;">
								<option value="">년</option>
							</select>
							<select name="" id="" style="width:60px;">
								<option value="">월</option>
							</select>
							<select name="" id="" style="width:60px;">
								<option value="">일</option>
							</select>
						</td>
					</tr>
					<tr>
						<th scope="row">주소 입력</th>
						<td>
							<input type="text" readonly>
							<a href="#" class="ad_btn gray">주소 찾기</a>
							<input type="text" class="wide mts">
							<input type="text" class="wide mts">
						</td>
					</tr>
				</tbody>
			</table>
			<div class="b_box right">
				<a href="#" class="ad_btn bk mid">목록</a>
				<a href="#" class="ad_btn green mid">저장</a>
				<a href="#" class="ad_btn bk mid">삭제</a>
			</div>