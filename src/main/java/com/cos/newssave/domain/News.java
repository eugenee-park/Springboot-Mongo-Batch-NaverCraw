package com.cos.newssave.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
@Document(collection = "naver_news") //컬렉션 이름 변경
public class News {
	@Id
	private String _id; 
	
	private String title;        // 뉴스제목
	private String createdAt;    // 뉴스발행일 
	private String company;      //신문사명
	
}








