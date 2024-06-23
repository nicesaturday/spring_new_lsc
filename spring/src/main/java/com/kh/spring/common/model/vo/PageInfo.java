package com.kh.spring.common.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@Builder
@ToString
public class PageInfo {
	int listCount; 		//   
	int currentPage;
	int pageLimit;
	int boardLimit;
	int maxPage;		// 총페이지 갯수 (마지막 페이지)
	int startPage;		// 하단 맨 앞에 쓰여있는 페이지 수
	int endPage;	
}
