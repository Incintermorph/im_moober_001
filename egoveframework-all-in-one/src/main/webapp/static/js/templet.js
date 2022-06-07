var TEMPLETE = {
	getCrsPlan : function(obj) {
		var _temp=`<div class="edu">`;
			_temp+=`					<a href="#" class="sort ${obj.css}">${obj.mmdd}</a>`;
			_temp+=`					<div class="detail">`;
			_temp+=`						<div class="blank">`;
			_temp+=`							<p class="name">${obj.agcyNm}</p>`;
			_temp+=`							<ul class="info">`;
			_temp+=`								<li>`;
			_temp+=`									<span class="lab">신청일</span>`;
			_temp+=`									<span class="date">${obj.date1}</span>`;
			_temp+=`								</li>`;
			_temp+=`								<li>`;
			if(obj.writeYn == 'Y'){
				_temp+=`									<span class="lab">시험일</span>`;
			}else{
				_temp+=`									<span class="lab">교육일</span>`;
			}
			_temp+=`									<span class="date">${obj.date2}</span>`;
			_temp+=`								</li>`;
			_temp+=`							</ul>`;
			_temp+=`						</div>`;
			_temp+=`					</div>`;
			_temp+=`				</div>`;
		return _temp;
	}
	
}
