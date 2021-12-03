// Paging = function(totalCnt, dataSize, pageSize, pageNo, token, pagination){
Paging = function(pagination){  
	totalCnt = parseInt(pagination.totalCount); // 전체레코드수 
	dataSize = parseInt(10);  // ***페이지당 보여줄 데이타수*** 
	pageSize = parseInt(5); // 페이지 그룹 범위 1 2 3 5 6 7 8 9 10 
	pageNo = parseInt(pagination.pageNo); // 현재페이지 
	
	startPage = pagination.startPage;
	endPage = pagination.endPage;
	lastPage = Math.ceil(totalCnt / dataSize);
	
	var html = ""; 
	if(totalCnt == 0){ return ""; } // 페이지 카운트 
	var pageCnt = totalCnt % dataSize; 
	
	if(pageCnt == 0){ 
		pageCnt = parseInt(totalCnt / dataSize); 
	}else{ 
		pageCnt = parseInt(totalCnt / dataSize) + 1; 
	} 
	
	var pRCnt = parseInt(pageNo / pageSize); 
	if(pageNo % pageSize == 0){ 
		pRCnt = parseInt(pageNo / pageSize) - 1; 
	}
	
	//이전 화살표 
	html += '<button type="button" class="first_btn" data-no="1">처음페이지</button>';
	if(pageNo > pageSize){ 
		var s2; 
		
		if(pageNo % pageSize == 0){ 
			s2 = pageNo - pageSize - 4; 
		}else{ 
			s2 = pageNo - pageNo % pageSize - 4; 
		} 
		
		html += '<button type="button" class="prev_btn" data-no="'+s2+'">이전페이지</button>';
	}else{ 
		// html += '<button type="button" class="first_btn">처음페이지</button>';
		html += '<button type="button" class="prev_btn">이전페이지</button>';
	}
	
	//paging Bar 
	for(var index=pRCnt * pageSize + 1; index<(pRCnt + 1)*pageSize + 1;index++){ 
		if(index == pageNo){ 
			html += '<a href="#n" class="num on" data-no="'+index+'">'+index+'</a>';
		}else{ 
			html += '<a href="#n" class="num" data-no="'+index+'">'+index+'</a>';
		} if(index == pageCnt){ 
			break; 
		} 
	} 
	
	//다음 화살표 
	if(pageCnt > (pRCnt + 1) * pageSize){ 
		html += '<button type="button" class="next_btn" data-no="'+((pRCnt + 1)*pageSize+1)+'">다음페이지</button>';
	}else{ 
		html += '<button type="button" class="next_btn">다음페이지</button>';
		// html += '<button type="button" class="last_btn">마지막페이지</button>';
	}
	html += '<button type="button" class="last_btn" data-no="'+lastPage+'">마지막페이지</button>';
	return html; 
}
